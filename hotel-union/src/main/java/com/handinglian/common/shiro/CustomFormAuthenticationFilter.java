package com.handinglian.common.shiro;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.handinglian.common.constants.Constants;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.common.utils.StringUtils;
import com.handinglian.system.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Fly
 * @Description 自定义表单验证
 * @Date Created in 12:23 2017/8/31
 * @Modified by
 */
@Slf4j
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_MESSAGE_PARAM = "message";

    private String messageParam = DEFAULT_MESSAGE_PARAM;


    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String json = "";
        try {
            json = getRequestPostStr((HttpServletRequest) request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginDto loginDto = JSON.parseObject(json, LoginDto.class);
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        boolean rememberMe = isRememberMe(request);
        String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host);
    }

    /**
     * 描述:获取 post 请求内容
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    /**
     * 描述:获取 post 请求的 byte[] 数组
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 获取登录用户名
     */
    @Override
    protected String getUsername(ServletRequest request) {
        String username = super.getUsername(request);
        if (StringUtils.isBlank(username)){
            username = StringUtils.toString(request.getAttribute(getUsernameParam()), StringUtils.EMPTY);
        }
        return username;
    }

    /**
     * 获取登录密码
     */
    @Override
    protected String getPassword(ServletRequest request) {
        String password = super.getPassword(request);
        if (StringUtils.isBlank(password)){
            password = StringUtils.toString(request.getAttribute(getPasswordParam()), StringUtils.EMPTY);
        }
        return password;
    }

    /**
     * 获取记住我
     */
    @Override
    protected boolean isRememberMe(ServletRequest request) {
        String isRememberMe = WebUtils.getCleanParam(request, getRememberMeParam());
        if (StringUtils.isBlank(isRememberMe)){
            isRememberMe = StringUtils.toString(request.getAttribute(getRememberMeParam()), StringUtils.EMPTY);
        }
        return StringUtils.toBoolean(isRememberMe);
    }


    public String getMessageParam() {
        return messageParam;
    }

    /**
     * 登录成功之后跳转URL
     */
    @Override
    public String getSuccessUrl() {
        return super.getSuccessUrl();
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request,
                                        ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //这里是个坑，如果不设置的接受的访问源，那么前端都会报跨域错误，因为这里还没到corsConfig里面
        httpServletResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        //we handled the success redirect directly, prevent the chain from continuing:
        ResultData result = new ResultData(Constants.LOGIN_SUCCESS, Constants.LOGIN_SUCCESS_CONTENT);
        httpServletResponse.getWriter().write(JSONObject.toJSON(result).toString());
        return false;
    }

    /**
     * 登录失败调用事件
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e, ServletRequest request, ServletResponse response) {
        String className = e.getClass().getName();
        String message;
        if (IncorrectCredentialsException.class.getName().equals(className)
                || UnknownAccountException.class.getName().equals(className)){
            message = "用户或密码错误, 请重试.";
        } else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")){
            message = StringUtils.replace(e.getMessage(), "msg:", "");
        } else{
            message = "系统出现点问题，请稍后再试！";
        }
        log.error("登录异常", e);

        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);

        return true;
    }

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }

            ResultData result = new ResultData(Constants.NOT_LOGIN, Constants.NOT_LOGIN_CONTENT);
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //这里是个坑，如果不设置的接受的访问源，那么前端都会报跨域错误，因为这里还没到corsConfig里面
            httpServletResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(JSONObject.toJSON(result).toString());
            return false;
        }
    }
}
