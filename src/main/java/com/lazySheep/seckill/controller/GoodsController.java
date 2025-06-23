package com.lazySheep.seckill.controller;

import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.controller
 * @date 2025/6/19 16:48
 * @project seckill
 * @description
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private UserService userService;
    //跳转到商品列表页
//    @RequestMapping(value = "/toList")
//    public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
//    public String toList(Model model, @CookieValue("userTicket") String ticket,
//                         HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        //如果 cookie 没有生成
//        if (!StringUtils.hasText(ticket)) {
//            return "login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        //从redis中获取信息
//        User user = userService.getUserByCookie(ticket, httpServletRequest, httpServletResponse);
//        //如果用户没有登录
//        if (user == null) {
//            return "login";
//        }
//        model.addAttribute("user", user);
//        return "goodsList";
//    }

    //跳转到商品列表页
    @RequestMapping(value = "/toList")
    public String toList(Model model, User user) {
        //如果用户没有登录
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "goodsList";
    }
}