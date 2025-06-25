package com.lazySheep.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazySheep.seckill.mapper.SeckillGoodsMapper;
import com.lazySheep.seckill.pojo.SeckillGoods;
import com.lazySheep.seckill.service.SeckillGoodsService;
import org.springframework.stereotype.Service;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service.impl
 * @date 2025/6/23 22:04
 * @project seckill
 * @description
 */
@Service
public class SeckillGoodsServiceImpl extends
        ServiceImpl<SeckillGoodsMapper, SeckillGoods>
        implements SeckillGoodsService {
}
