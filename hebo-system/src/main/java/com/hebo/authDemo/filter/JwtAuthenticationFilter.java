package com.hebo.authDemo.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import com.hebo.authDemo.entity.LoginUser;
import com.hebo.authDemo.entity.User;
import com.hebo.authDemo.utils.RedisCache;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RedisCache redisCache;
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
        Long expireTime = JSONObject.parseObject(jwt.getPayload("expireTime").toString(), Long.class);
        if (Objects.isNull(user)){
            throw new RuntimeException("非法请求！");
        }else {
            if (System.currentTimeMillis()>expireTime){
                throw new RuntimeException("登录失效，请重新登录");
            }
        }
        //从redis中获取用户信息
        Object object = redisCache.getCacheObject(user.getUserId().toString());
        if (Objects.isNull(object)){
            throw new RuntimeException("用户未登录");
        }
        User user1 = JSONObject.parseObject(object.toString(), User.class);
        //存入securityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(new LoginUser(user1),null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);


    }
}