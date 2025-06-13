package com.lazySheep.seckill.controller;

import com.lazySheep.seckill.service.UserService;
import com.lazySheep.seckill.vo.LoginVo;
import com.lazySheep.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    //Login function
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin
    (@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        log.info("{}",loginVo);
        return userService.doLogin(loginVo,request,response);
    }

}