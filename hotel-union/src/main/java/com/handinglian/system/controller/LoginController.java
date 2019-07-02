package com.handinglian.system.controller;

import com.apidoc.annotation.Api;
import com.handinglian.common.shiro.MyFormAuthenticationFilter;
import com.handinglian.common.utils.StringUtils;
import com.handinglian.system.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Api("登录")
@Controller
@Slf4j
public class LoginController {

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String loginUrl(){
        return "redirect:" + "/login.html";
    }

    /**
     * 登录失败或已经登录，真正登录的POST请求由Filter完成
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model, @RequestBody LoginDto loginDto){
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();

        // 如果已经登录，则跳转到管理首页
        if(principal != null){
            return "redirect:" + "/index.jsp";
        }

        String userName = WebUtils.getCleanParam(request, MyFormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
        String message = (String)request.getAttribute(MyFormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);

        if (StringUtils.isBlank(message) || StringUtils.equals(message, null)){
            message = "用户或密码错误, 请重试.";
        }
        model.addAttribute(MyFormAuthenticationFilter.DEFAULT_USERNAME_PARAM, loginDto.getUsername());
        model.addAttribute(MyFormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, loginDto.getPassword());
        model.addAttribute("loginError", true);
        return "redirect:/login.html";
    }

}
