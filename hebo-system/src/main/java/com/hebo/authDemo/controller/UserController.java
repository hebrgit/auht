package com.hebo.authDemo.controller;


import com.hebo.authDemo.entity.User;
import com.hebo.authDemo.service.IUserService;
import com.hebo.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author hebo
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;


    @GetMapping("/logout")
    public Response logout () {

        userService.logout();

        return Response.success();

    }
}
