package com.lazySheep.seckill.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.util
 * @date 2025/6/4 15:54
 * @project seckill
 * @description
 */
public class MD5UtilTest {

    @Test
    public void test(){
        System.out.println(MD5Util.md5("123"));

        System.out.println(MD5Util.inputPassToMidPass("123"));

        System.out.println(MD5Util.midPassToDBPass(MD5Util.inputPassToMidPass("123"),"UCmP7xHA"));

        System.out.println(MD5Util.inputPassToDBPass("123","UCmP7xHA"));

    }

}
