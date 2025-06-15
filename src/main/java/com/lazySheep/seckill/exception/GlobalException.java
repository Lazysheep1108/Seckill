package com.lazySheep.seckill.exception;

import com.lazySheep.seckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.exception
 * @date 2025/6/15 16:49
 * @project seckill
 * @description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private RespBeanEnum respBeanEnum;
}