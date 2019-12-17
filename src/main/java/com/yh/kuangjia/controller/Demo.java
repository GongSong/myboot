package com.yh.kuangjia.controller;

import com.yh.kuangjia.util.Security.AESPKCS5Util;
import com.yh.kuangjia.util.Security.MD5Util;

import java.text.MessageFormat;

public class Demo {

    public static void main(String[] args) {
        System.out.println(AESPKCS5Util.encrypt("15237861068"));
    }
}
