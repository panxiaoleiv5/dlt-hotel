package com.handinglian.system.controller;

import com.apidoc.annotation.Api;
import com.handinglian.common.constants.Constants;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.common.shiro.CustomFormAuthenticationFilter;
import com.handinglian.common.utils.StringUtil;
import com.handinglian.system.dto.LoginDto;
import com.mysql.cj.protocol.a.ResultsetFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api("登录")
@RestController
@Slf4j
public class LoginController {

    /**
     * 登录失败或已经登录，真正登录的POST请求由Filter完成
     */
    @PostMapping("/login")
    public ResultData login(HttpServletRequest request, @RequestBody(required = false) LoginDto loginDto){
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        // 如果已经登录，返回登录成功
        if(principal != null){
            return new ResultData(Constants.LOGIN_SUCCESS, Constants.LOGIN_SUCCESS_CONTENT);
        }

        String message = (String)request.getAttribute(CustomFormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
        if (StringUtil.isNotBlank(message)){
            return new ResultData(Constants.LOGIN_FAILURE, message);
        }

        return new ResultData(Constants.NOT_LOGIN, Constants.NOT_LOGIN_CONTENT);
    }

    /**
     * 退出
     */
    @GetMapping("/logout")
    public ResultData logout(){
        return new ResultData(Constants.LOGOUT, Constants.LOGOUT_CONTENT);
    }

}
