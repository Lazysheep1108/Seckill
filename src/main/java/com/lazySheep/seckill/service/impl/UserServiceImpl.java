package com.lazySheep.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazySheep.seckill.exception.GlobalException;
import com.lazySheep.seckill.mapper.UserMapper;
import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.service.UserService;
import com.lazySheep.seckill.util.CookieUtil;
import com.lazySheep.seckill.util.MD5Util;
import com.lazySheep.seckill.util.UUIDUtil;
import com.lazySheep.seckill.util.ValidatorUtil;
import com.lazySheep.seckill.vo.LoginVo;
import com.lazySheep.seckill.vo.RespBean;
import com.lazySheep.seckill.vo.RespBeanEnum;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service.impl
 * @date 2025/6/6 15:50
 * @project seckill
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //Determine whether it is a null parameter check
//        if(!StringUtils.hasText(password) || !StringUtils.hasText(mobile)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }

        //Mobile phone verification
//        if(!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }

        //Query the database
        User user = userMapper.selectById(mobile);
        if (null == user) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        if (!MD5Util.midPassToDBPass(password, user.getSlat()).equals(user.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        //generate cookie
        String ticket = UUIDUtil.uuid();
        //request.getSession().setAttribute(ticket, user);
        //为了实现分布式 session,把 session 放在 redis 里面
        redisTemplate.opsForValue().set("user:" + ticket, user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);

        return RespBean.success(ticket);
    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.hasText(userTicket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        //如果用户不为空,延长cookie的使用时间
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }

    @Override
    public RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response) {
        //更新用户密码,同时删除用户在Redis的缓存对象
        User user = getUserByCookie(userTicket, request, response);

        if (user == null) {
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }

        user.setPassword(MD5Util.inputPassToDBPass(password, user.getSlat()));
        int i = userMapper.updateById(user);
        if (i == 1) {
            //删除redis
            redisTemplate.delete("user:" + userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWROD_UPDATE_FAIL);
    }
}
