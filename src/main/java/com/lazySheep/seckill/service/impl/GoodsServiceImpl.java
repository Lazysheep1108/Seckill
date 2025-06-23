package com.lazySheep.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazySheep.seckill.mapper.GoodsMapper;
import com.lazySheep.seckill.pojo.Goods;
import com.lazySheep.seckill.service.GoodsService;
import com.lazySheep.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service.impl
 * @date 2025/6/23 22:02
 * @project seckill
 * @description
 */
@Service
public class GoodsServiceImpl
        extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    //获取商品列表
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }
}
