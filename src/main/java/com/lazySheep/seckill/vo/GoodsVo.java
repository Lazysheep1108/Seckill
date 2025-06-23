package com.lazySheep.seckill.vo;

import com.lazySheep.seckill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.vo
 * @date 2025/6/23 21:31
 * @project seckill
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {
    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}