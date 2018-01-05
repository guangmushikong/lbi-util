package com.lbi.util;

/**
 * 全球唯一标识符（GUID，Globally Unique Identifier）生成工具类
 * 基于Twitter-Snowflake，64位自增ID算法 https://www.lanindex.com/twitter-snowflake%EF%BC%8C64%E4%BD%8D%E8%87%AA%E5%A2%9Eid%E7%AE%97%E6%B3%95%E8%AF%A6%E8%A7%A3/
 */
public class GUIDUtil {
    static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
    public static long getGUID(){
        return idWorker.nextId();
    }
}
