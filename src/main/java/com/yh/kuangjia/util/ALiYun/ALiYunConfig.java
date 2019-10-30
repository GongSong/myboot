package com.yh.kuangjia.util.ALiYun;

import com.yh.kuangjia.util.ResourceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ALiYunConfig {
    private final static Log log = LogFactory.getLog(ALiYunConfig.class);

    public static String accessKeyId = "";
    public static String accessKeySecret = "";
    public static String bucketName = "";

    static {
        try {
            accessKeyId = ResourceUtil.getSystemValue("System", "accessKeyId");
            accessKeySecret = ResourceUtil.getSystemValue("System", "accessKeySecret");
            bucketName = ResourceUtil.getSystemValue("System", "bucketName");
        } catch (Exception e) {
            log.error(" Not Found in System.properties .");
        }
    }
}
