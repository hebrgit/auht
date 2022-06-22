package com.hebo.authDemo.mapper;

import com.hebo.authDemo.entity.SysUserRole;
import com.hebo.authDemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author hebo
 * @since 2022-06-19
 */
public interface UserMapper extends BaseMapper<User> {

    User selectUserInfo(String userName);


    List<SysUserRole>  selectUserRole(Long userId);

}
