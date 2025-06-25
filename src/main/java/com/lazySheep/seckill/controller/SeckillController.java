package com.lazySheep.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lazySheep.seckill.pojo.Order;
import com.lazySheep.seckill.pojo.SeckillOrder;
import com.lazySheep.seckill.pojo.User;
import com.lazySheep.seckill.service.GoodsService;
import com.lazySheep.seckill.service.OrderService;
import com.lazySheep.seckill.service.SeckillOrderService;
import com.lazySheep.seckill.vo.GoodsVo;
import com.lazySheep.seckill.vo.RespBeanEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.controller
 * @date 2025/6/24 23:20
 * @project seckill
 * @description
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Resource
    private GoodsService goodsService;

    @Resource
    private SeckillOrderService seckillOrderService;

    @Resource
    private OrderService orderService;


    @RequestMapping(value = "/doSeckill")
    public String doSeckill(Model model, User user, Long goodsId) {
        System.out.println("-----秒杀 V1.0--------");
        //===================秒杀 v1.0 start =========================
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if (goodsVo.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.ENTRY_STOCK.getMessage());
            return "secKillFail";
        }
        //解决重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(
                new QueryWrapper<SeckillOrder>().eq("user_id",user.getId()).eq("goods_id",goodsId));

        if (seckillOrder != null) {
            model.addAttribute("errmsg", RespBeanEnum.REPEAT_ERROR.getMessage());
            return "secKillFail";
        }
        //抢购
        Order order = orderService.seckill(user, goodsVo);
        if (order == null) {
            model.addAttribute("errmsg", RespBeanEnum.ENTRY_STOCK.getMessage());
            return "secKillFail";
        }
        model.addAttribute("order", order);
        model.addAttribute("goods", goodsVo);
        return "orderDetail";
    }
    //===================秒杀 v1.0 end... =========================
}