package com.lazySheep.seckill.exception;

import com.lazySheep.seckill.vo.RespBean;
import com.lazySheep.seckill.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.exception
 * @date 2025/6/15 16:50
 * @project seckill
 * @description
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    //Handle all exception
    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e) {
        //if is GlobalException,do
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return RespBean.error(ex.getRespBeanEnum());
        } else if (e instanceof BindException) {
            //如果是绑定异常 ：由于我们自定义的注解只会在控制台打印错误信息，想让改信息传给前端。
            //需要获取改异常 BindException，进行打印
            BindException ex = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BING_ERROR);
            respBean.setMessage(" 参 数 校 验 异 常 ~ ： " +
                    ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }
}