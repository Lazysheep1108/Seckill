package com.lazySheep.seckill.util;


import java.util.UUID;


public class UUIDUtil {

    public static String uuid() {
        //默认下: 生成的字符串形式 xxxx-yyyy-zzz-ddd
        //不想要- ,所以使用 replace("-","")
        return UUID.randomUUID().toString().replace("-","");
    }


}
