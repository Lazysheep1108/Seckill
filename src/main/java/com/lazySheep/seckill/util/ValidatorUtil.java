package com.lazySheep.seckill.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.util
 * @date 2025/6/6 10:56
 * @project seckill
 * @description check the parameters of Login
 */

//Mobile phone verification
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean isMobile(String mobile){
        if(!StringUtils.hasText(mobile)){
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();//返回校验结果 true 为正确
    }
}
