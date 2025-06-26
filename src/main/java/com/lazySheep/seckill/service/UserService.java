package com.lazySheep.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.vo.LoginVo;
import com.lazySheep.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service
 * @date 2025/6/6 15:48
 * @project seckill
 * @description
 */
public interface UserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    //根据 cookie 获取用户
    User getUserByCookie(String userTicket,HttpServletRequest request, HttpServletResponse response);

    //更改密码
    RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response);
}
