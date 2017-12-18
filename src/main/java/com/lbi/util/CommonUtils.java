package com.lbi.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isNoneEmpty;

public class CommonUtils {
    public static boolean CheckAdcode(String code){
        code=FormatAdcode(code);
        if(code!=null){
            String fcode=code.substring(0,2);
            Long n=0l;
            try{
                n=Long.parseLong(fcode);
            }catch (Exception ex){

            }
            if(n>=11 && n<=82)return true;
        }
        return false;
    }
    public static int getAdminLevel(String code){
        code=FormatAdcode(code);
        if(code!=null){
            String provCode=code.substring(0,2);
            String distCode=code.substring(2,4);
            String countyCode=code.substring(4,6);
            String townCode=code.substring(6,9);
            String villageCode=code.substring(9,12);
            //System.out.println(provCode+"|"+distCode+"|"+countyCode+"|"+townCode+"|"+villageCode);
            if(!villageCode.equalsIgnoreCase("000"))return 5;
            else if(!townCode.equalsIgnoreCase("000"))return 4;
            else if(!countyCode.equalsIgnoreCase("00"))return 3;
            else if(!distCode.equalsIgnoreCase("00"))return 2;
            else if(!provCode.equalsIgnoreCase("00"))return 1;
        }
        return -1;
    }
    public static String FormatAdcode(String code){
        if(StringUtils.isNumeric(code)){
            String s;
            if(code.length()>=12)s=code.substring(0,12);
            else s=code+String.format("%1$0"+(12-code.length())+"d",0);
            return s;
        }else return null;
    }
    public static String removeBlank(String str){
        if(isNoneEmpty(str)){
            str=str.replaceAll(" ","");
            //name=name.replaceAll(" ","");
            //name=name.replaceAll("\\s*", "");
            //name=name.replaceAll("\\n+", "");
            //name=name.replaceAll("<br\\\\s*?/?>", "");
            //name=name.replaceAll("<[^>]+>", "");
            str= StringUtils.deleteWhitespace(str);
            //name=StringUtils.removePattern(name,"<br\\s*?/?>");//处理换行
            //name=StringUtils.removePattern(name,"<[^>]+>");//去掉HTML标签
            //name=StringUtils.removePattern(name,"\\n+");//去掉多余的空行
            return str;
        }
        return null;
    }

    /**
     * 判断字符是否是中文
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }

    }
    public static Map<String, String> getParameterMap(String url) {
        Map<String, String> map=null;
        try{
            //url = URLDecoder.decode(url, "utf-8");
            if (url.indexOf('?') != -1) {
                String contents = url.substring(url.indexOf('?') + 1);
                map = new HashMap<String, String>();
                String[] keyValues = contents.split("&");
                for (int i = 0; i < keyValues.length; i++) {
                    String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
                    String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);
                    //System.out.println(key+" : "+value);
                    map.put(key,value);
                }
            }
        }catch (Exception ex){
            System.out.println("err:"+url);
            ex.printStackTrace();
        }
        return map;
    }
}
