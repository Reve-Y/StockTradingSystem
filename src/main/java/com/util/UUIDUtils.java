package com.util;

import java.util.UUID;

/**
 *  获取随机字符串
 */
public class UUIDUtils {

    public static String getUUID(){
        String res = UUID.randomUUID().toString().replace("-","");
        return res;
    }
}
