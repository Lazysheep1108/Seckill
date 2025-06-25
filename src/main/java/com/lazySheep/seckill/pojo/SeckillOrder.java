package com.lazySheep.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.pojo
 * @date 2025/6/24 18:05
 * @project seckill
 * @description
 */
@Data
@TableName("t_seckill_order")
public class SeckillOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}