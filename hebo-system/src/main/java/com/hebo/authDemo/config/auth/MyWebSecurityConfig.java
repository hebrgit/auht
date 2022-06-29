package com.hebo.authDemo.config.auth;

import com.hebo.authDemo.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName MyWebSecurityConfig
 * @Author hebo
 * @Date 2022/6/18 13:24
 **/
@Configuration
public class MyWebSecurityConfig {


    @Autowired
    private CustomizeAuthenticationEntryPoint entryPoint;

    @Autowired
    private CustomizeLoginSuccessHandler successHandler;

    @Autowired
    private CustomizeLoginFailHandler failHandler;
//    @Autowired
//    MyAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.cors()////允许跨越
                .and()
                .csrf().disable()
                //不通过session获取securityContexter
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin().permitAll()
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .exceptionHandling()

                .authenticationEntryPoint(entryPoint)
                .and()
//                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



}
