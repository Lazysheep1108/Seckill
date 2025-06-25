package com.lazySheep.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazySheep.seckill.mapper.OrderMapper;
import com.lazySheep.seckill.pojo.Order;
import com.lazySheep.seckill.pojo.SeckillGoods;
import com.lazySheep.seckill.pojo.SeckillOrder;
import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.service.OrderService;
import com.lazySheep.seckill.service.SeckillGoodsService;
import com.lazySheep.seckill.service.SeckillOrderService;
import com.lazySheep.seckill.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service.impl
 * @date 2025/6/24 22:10
 * @project seckill
 * @description
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private SeckillGoodsService seckillGoodsService;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private SeckillOrderService seckillOrderService;

    @Override
    public Order seckill(User user, GoodsVo goodsVo) {

        //查询后端库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>()
                .eq("goods_id", goodsVo.getId()));
        //数量减一并设置回去
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        seckillGoodsService.updateById(seckillGoods);

        //生成普通订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goodsVo.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsCount(1);
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setCreateDate(new Date());
        orderMapper.insert(order);


        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getId());
        seckillOrderService.save(seckillOrder);
        return order;

    }
}
