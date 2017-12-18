package com.lbi.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class testCommonUtils {
    @Test
    public void testCheckAdcode(){
        System.out.println(CommonUtils.CheckAdcode("11"));
        System.out.println(CommonUtils.CheckAdcode("abc"));
        System.out.println(CommonUtils.CheckAdcode("01"));
    }
    @Test
    public void testFormatAdcode(){
        System.out.println(CommonUtils.FormatAdcode("11"));
        System.out.println(CommonUtils.FormatAdcode("abc"));
        System.out.println(CommonUtils.FormatAdcode("01"));
    }
    @Test
    public void testAdminLevel(){
        System.out.println(CommonUtils.getAdminLevel("653130103001"));
        System.out.println(CommonUtils.getAdminLevel("652824501"));
        System.out.println(CommonUtils.getAdminLevel("659003"));
        System.out.println(CommonUtils.getAdminLevel("6590"));
        System.out.println(CommonUtils.getAdminLevel("65"));
        System.out.println(CommonUtils.getAdminLevel("北京"));
    }
    @Test
    public void testIsMessyCode(){
        System.out.println(CommonUtils.isMessyCode("Ã©Å¸Â©Ã©Â¡ÂºÃ¥Â¹Â³"));
        System.out.println(CommonUtils.isMessyCode("你好"));
    }
}
