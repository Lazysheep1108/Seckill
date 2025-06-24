package com.lazySheep.seckill.controller;

import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.service.GoodsService;
import com.lazySheep.seckill.service.UserService;
import com.lazySheep.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    @Autowired
    private GoodsService goodsService;
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
        //展示商品
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";
    }

    @RequestMapping(value = "/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);

        //============处理秒杀倒计时和状态 start ==============
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int secKillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;
        if (nowDate.before(startDate)) {
            //秒杀还没有开始
            remainSeconds = (int) ((startDate.getTime() - nowDate.getTime()) / 1000);
        } else if (nowDate.after(endDate)) {//秒杀结束
            secKillStatus = 2;
            remainSeconds = -1;
        } else {
            //秒杀进行中
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("secKillStatus", secKillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        //============处理秒杀倒计时和状态 end==============
        model.addAttribute("goods", goodsVo);
        return "goodsDetail";
    }
}