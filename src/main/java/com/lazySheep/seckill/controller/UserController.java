package com.lazySheep.seckill.controller;

import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.controller
 * @date 2025/6/25 15:51
 * @project seckill
 * @description
 */

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 返回用户信息, 同时我们也演示如何携带参数
     * @param user
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }
}