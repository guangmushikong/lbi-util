package com.lbi.util;

import at.orz.hash.CityHash;

public class CityHashUtil {
    public static long getCityHash64(String str){
        //byte[] bytes=url.getBytes("utf-8");
        byte[] bytes=str.getBytes();
        long cityhash= CityHash.cityHash64(bytes,0,bytes.length);
        return cityhash;
    }
}
