package com.hebo.authDemo.config;

import com.hebo.authDemo.service.impl.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.net.HttpCookie;

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

    @Autowired
    private CustomizeLoginFailHandler failHandler;
    @Autowired
     MyAuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.cors()////允许跨越
                .and()
                .csrf().disable()
                //不通过session获取securityContexter
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authorize -> authorize.antMatchers("/login","/user/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin().permitAll()
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .authenticationProvider(authenticationProvider)
                .build();
    }



//    @Bean
//    AuthenticationManager ldapAuthenticationManager(
//            BaseLdapPathContextSource contextSource) {
//        LdapBindAuthenticationManagerFactory factory =
//                new LdapBindAuthenticationManagerFactory(contextSource);
//        factory.setUserDnPatterns("uid={0},ou=people");
//        factory.setUserDetailsContextMapper(new PersonContextMapper());
//        return factory.createAuthenticationManager();
//    }

}
