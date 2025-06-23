package com.lazySheep.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lazySheep.seckill.pojo.Goods;
import com.lazySheep.seckill.vo.GoodsVo;

import java.util.List;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service
 * @date 2025/6/23 22:00
 * @project seckill
 * @description
 */
public interface GoodsService extends IService<Goods> {
    //商品列表
    List<GoodsVo> findGoodsVo();
}