package com.hebo.authDemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebo.authDemo.entity.LoginUser;
import com.hebo.authDemo.entity.User;
import com.hebo.authDemo.exception.UsernameAndPasswordException;
import com.hebo.authDemo.service.ISysRoleMenuService;
import com.hebo.authDemo.service.ISysUserRoleService;
import com.hebo.authDemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    ISysRoleMenuService roleMenuService;

    @Autowired
    ISysUserRoleService userRoleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = iUserService.selectUserInfo(username);
        if (Objects.isNull(user)){
            throw new UsernameAndPasswordException("用户名和密码错误");
        }

        return new LoginUser(user);
    }
}
