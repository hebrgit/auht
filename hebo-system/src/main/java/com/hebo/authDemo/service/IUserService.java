package com.hebo.authDemo.service;

import com.hebo.authDemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebo.authDemo.exception.HebrException;
import com.hebo.dto.Response;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author hebo
 * @since 2022-06-19
 */
public interface IUserService extends IService<User> {

    User login(User user);
}
