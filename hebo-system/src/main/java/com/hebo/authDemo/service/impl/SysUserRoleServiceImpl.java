package com.hebo.authDemo.service.impl;

import com.hebo.authDemo.entity.SysUserRole;
import com.hebo.authDemo.mapper.SysUserRoleMapper;
import com.hebo.authDemo.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author hebo
 * @since 2022-06-22
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
