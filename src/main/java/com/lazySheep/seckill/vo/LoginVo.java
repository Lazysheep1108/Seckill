package com.lazySheep.seckill.vo;

import com.lazySheep.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.vo
 * @date 2025/6/6 10:54
 * @project seckill
 * @description Login parameters
 */

@Data
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min = 32)
    private String password;
}
