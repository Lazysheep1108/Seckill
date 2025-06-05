package com.lazySheep.seckill.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.vo
 * @date 2025/6/5 11:57
 * @project seckill
 * @description
 */
@AllArgsConstructor
@Getter
@ToString
public enum RespBeanEnum {
    //General
    SUCCESS(200, "SUCCESS"), ERROR(500, "Server has exception"),
    //Login moudle
    LOGIN_ERROR(500210, "The username or password is incorrect"),
    MOBILE_ERROR(500211, "The format of the mobile phone number is incorrect"),
    BING_ERROR(500212, "Parameter binding exception"),
    MOBILE_NOT_EXIST(500213, "The mobile phone number doesn't exist"),
    PASSWORD_UPDATE_FAIL(500214, "Failed to update the password");
    private final Integer code;
    private final String message;
}
