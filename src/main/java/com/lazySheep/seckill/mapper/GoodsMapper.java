package com.lazySheep.seckill.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lazySheep.seckill.pojo.Goods;
import com.lazySheep.seckill.vo.GoodsVo;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVo> findGoodsVo();

    //根据goodsId查询商品详情页面
    GoodsVo findGoodsVoByGoodsId(Long goodsId);

}
