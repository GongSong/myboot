package com.yh.kuangjia.util;

import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    public static String join(Collection var0, String var1) {
        StringBuffer var2 = new StringBuffer();
        for (Iterator var3 = var0.iterator(); var3.hasNext(); var2.append((String) var3.next())) {
            if (var2.length() != 0) {
                var2.append(var1);
            }
        }
        return var2.toString();
    }

    /**
     * 获取文本类型选项
     *
     * @param propertyID
     * @param propertText
     * @return
     */
    public static String GetPropertyText(int propertyID, String propertText) {
        String text = "";
        if (StringUtils.isEmpty(propertText)) return text;

        String p = MessageFormat.format("<BEGIN{0}>(?<key>[\\s\\S]*)<END{0}>", propertyID);
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(propertText);
        while (matcher.find()) {
            text += matcher.group(0) + "；";
        }
        return text;
    }

    /**
     * 获取值类型选项
     *
     * @param propertyID
     * @param propertyValue
     * @return
     */
    public static List<Integer> GetPropertyValue(int propertyID, String propertyValue) {
        List<Integer> list = new ArrayList<>();
        if (StringUtils.isEmpty(propertyValue)) return list;

        String p = MessageFormat.format("(?<=^|\\s+?|\\D+?){0}-(?<key>[0-9]*);", propertyID);
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(propertyValue);
        while (matcher.find()) {
            list.add(Integer.parseInt(matcher.group(1)));
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

}
