package com.hebo.authDemo.controller;


import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author tech-winning
 * @version 1.0
 * @description: TODO
 * @date 2022/6/13 11:40
 */
@RestController
@RequestMapping("/sys")
public class LoginController {

    @GetMapping("/login")
    public String Login (){

        return "sucess";
    }
}
