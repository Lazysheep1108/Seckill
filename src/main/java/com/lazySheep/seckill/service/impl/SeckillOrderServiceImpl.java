package com.lazySheep.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazySheep.seckill.mapper.SeckillOrderMapper;
import com.lazySheep.seckill.pojo.SeckillOrder;
import com.lazySheep.seckill.service.SeckillOrderService;
import org.springframework.stereotype.Service;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.service.impl
 * @date 2025/6/24 23:36
 * @project seckill
 * @description
 */
@Service
public class SeckillOrderServiceImpl
        extends ServiceImpl<SeckillOrderMapper, SeckillOrder>
        implements SeckillOrderService {
}