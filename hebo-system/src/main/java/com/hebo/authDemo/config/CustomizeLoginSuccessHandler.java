package com.hebo.authDemo.config;

import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.alibaba.fastjson.JSON;
import com.hebo.authDemo.entity.LoginUser;
import com.hebo.authDemo.entity.User;
import com.hebo.dto.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @Author: Hutengfei
 * @Description: 登出成功处理逻辑
 * @Date Create in 2019/9/4 10:17
 */
@Component
public class CustomizeLoginSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Response result = Response.success(200,"登录成功",authentication);

        LoginUser principal = (LoginUser)authentication.getPrincipal();
        User user = principal.getUser();
        Long userId = user.getUserId();

        response.setContentType("text/json;charset=utf-8");
//        response.addHeader("auth");
        response.getWriter().write(JSON.toJSONString(result));
    }
}





