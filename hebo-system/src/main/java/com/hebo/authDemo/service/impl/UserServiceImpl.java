package com.hebo.authDemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebo.authDemo.config.MyAuthenticationManager;
import com.hebo.authDemo.entity.User;
import com.hebo.authDemo.exception.HebrException;
import com.hebo.authDemo.mapper.UserMapper;
import com.hebo.authDemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hebo.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author hebo
 * @since 2022-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


//    @Autowired
    private ProviderManager myAuthenticationManager;

    @Override

    public Response login(User user) {

        //Authentication authenticate方法进行验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
//        Authentication authenticate = myAuthenticationManager.authenticate(authenticationToken);
        //如果验证未通过，给出相应的提示
//        if (Objects.isNull(authenticate)){
//            throw new HebrException("登录失败！");
//        }


        //如果认证通过，使用userid 生成jwt，jwt 封装进Response

        return null;
    }
}
