package com.hebo.authDemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebo.authDemo.entity.LoginUser;
import com.hebo.authDemo.entity.User;
import com.hebo.authDemo.exception.HebrException;
import com.hebo.authDemo.service.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName LoginUserService
 * @Author hebo
 * @Date 2022/6/19 17:27
 **/

@Service
public class LoginUserService implements UserDetailsService {

    @Autowired
    IUserService iUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = iUserService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));

        if (Objects.isNull(user)){
            throw new HebrException("用户名和密码错误");
        }

        return new LoginUser(user);
    }
}
