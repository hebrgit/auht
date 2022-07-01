package com.hebo.authDemo.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebo.authDemo.entity.SysMenu;
import com.hebo.authDemo.service.ISysMenuService;
import com.hebo.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hebo
 * @since 2022-06-22
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

//    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/listMenu")
    public Response listMenu(){
        List<SysMenu> sysMenus = menuService.list(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getDeleteFlag, 0));
        sysMenus.stream().forEach(x->{
            setMenu(sysMenus,x);
        });
        return Response.success(sysMenus.stream().filter(x->0 == x.getParentId()).collect(Collectors.toList()));
    }

    private void setMenu(List<SysMenu> sysMenus,SysMenu sysMenu){
        List<SysMenu> menus = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(sysMenus)){
            sysMenus.forEach(x->{
                if(x.getParentId().equals(sysMenu.getMenuId())){
                    x.setHasChildren(true);
                    menus.add(x);
                    sysMenu.setSysMenus(menus);
                }
            });
        }
    }
}
