package com.lazySheep.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazySheep.seckill.mapper.UserMapper;
import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.service.UserService;
import com.lazySheep.seckill.util.MD5Util;
import com.lazySheep.seckill.util.ValidatorUtil;
import com.lazySheep.seckill.vo.LoginVo;
import com.lazySheep.seckill.vo.RespBean;
import com.lazySheep.seckill.vo.RespBeanEnum;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service.impl
 * @date 2025/6/6 15:50
 * @project seckill
 * @description
 */
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //Determine whether it is a null parameter check
        if(!StringUtils.hasText(password) || !StringUtils.hasText(mobile)){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        //Mobile phone verification
        if(!ValidatorUtil.isMobile(mobile)){
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }

        //Query the database
        User user = userMapper.selectById(mobile);
        if(user==null){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if(!MD5Util.midPassToDBPass(password,user.getSlat()).equals(user.getPassword())){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        return RespBean.success();
    }
}
