package com.lazySheep.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.controller
 * @date 2025/6/13 14:41
 * @project seckill
 * @description
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

}