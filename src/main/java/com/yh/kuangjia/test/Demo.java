package com.yh.kuangjia.test;

import com.yh.kuangjia.util.ALiYun.ALiYunConfig;
import com.yh.kuangjia.util.QRCodeUtil.QrUtil.PutZipUtil;
import com.yh.kuangjia.util.ResourceUtil;

import java.util.ResourceBundle;

public class Demo {
    public static void main(String[] args) {
        String accessKeyId = ALiYunConfig.accessKeyId;
        System.out.println(accessKeyId);
    }
}
