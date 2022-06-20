package com.hebo.authDemo.config;

import com.hebo.authDemo.service.impl.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @ClassName MyWebSecurityConfig
 * @Author hebo
 * @Date 2022/6/18 13:24
 **/
@Configuration
public class MyWebSecurityConfig {

//    @Autowired
//    private MyAuthenticationManager myAuthenticationManager;

    @Autowired
    private CustomizeAuthenticationEntryPoint entryPoint;

    @Autowired
    private CustomizeLoginSuccessHandler successHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.cors()////允许跨越
                .and()
                .csrf().disable()
                //不通过session获取securityContexter
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize.antMatchers("/login","/user/login").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)

                .and()
                .formLogin().permitAll()
                .successHandler(successHandler)
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService UserDetailsService(){

        return  new LoginUserService();
    }


}
