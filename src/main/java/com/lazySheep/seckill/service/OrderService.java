package com.lazySheep.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lazySheep.seckill.pojo.Order;
import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.vo.GoodsVo;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service
 * @date 2025/6/24 22:08
 * @project seckill
 * @description
 */
public interface OrderService extends IService<Order> {
    //秒杀
    Order seckill(User user, GoodsVo goodsVo);
}