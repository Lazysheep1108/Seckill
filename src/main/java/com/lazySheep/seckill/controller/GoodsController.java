package com.lazySheep.seckill.controller;

import com.lazySheep.seckill.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

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
    //跳转到商品列表页
    @RequestMapping(value = "/toList")
    public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
        //如果 cookie 没有生成
        if (!StringUtils.hasText(ticket)) {
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        //如果用户没有登录
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "goodsList";
    }
}