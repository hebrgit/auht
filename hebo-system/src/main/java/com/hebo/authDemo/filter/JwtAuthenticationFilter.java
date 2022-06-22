package com.hebo.authDemo.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import com.hebo.authDemo.entity.LoginUser;
import com.hebo.authDemo.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * @ClassName JwtAuthenticationFilter
 * @Author hebo
 * @Date 2022/6/21 23:37
 **/
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        //解析token
        if (Strings.isEmpty(token)){
            filterChain.doFilter(request,response);
            return;
        }
        JWT jwt = JWTUtil.parseToken(token);
        User user = JSONObject.parseObject(jwt.getPayload("userId").toString(), User.class);
        if (Objects.isNull(user)){
            throw new RuntimeException("非法请求！");
        }
        //从redis中获取用户信息 TODO 暂时直接从token中解析得到

        //存入securityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(new LoginUser(user),null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);


    }
}
