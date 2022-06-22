package com.hebo.authDemo.service.impl;

import com.hebo.authDemo.entity.User;
import com.hebo.authDemo.mapper.UserMapper;
import com.hebo.authDemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public User selectUserInfo(String userName) {
        return this.baseMapper.selectUserInfo(userName);
    }
}
