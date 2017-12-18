package com.lbi.util;

import org.junit.Test;

/**
 * Created by lmk on 2017/8/8.
 */
public class testSnowflakeIdWorker {
    @Test
    public void test(){
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            //System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }
        System.out.println("|"+idWorker.timeGen());
    }
}
