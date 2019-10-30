package com.yh.kuangjia.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ResourceBundle;

/**
 * 读取系统配置信息
 * 
 * @author Robin 2014年7月15日 上午11:42:50
 */
public final class ResourceUtil {

    private final static Log      log = LogFactory.getLog(ResourceUtil.class);

    private static ResourceBundle system;
    static {
        try {
            system = ResourceBundle.getBundle("application");
        } catch (Exception e) {
            log.error("application.properties Not Found,", e);
        }
    }

    /**
     * 读取application.properties文件里面的值
     * 
     * @param key
     * @return
     */
    public static String getValue(String key) {
        String msg = null;
        try {
            msg = system.getString(key);
        } catch (Exception e) {
            log.error("Key['" + key + "'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getSystemValue(String filte,String key) {

        String msg = null;
        try {
            ResourceBundle systemInfo=ResourceBundle.getBundle("templates/"+filte);
            msg = systemInfo.getString(key);
        } catch (Exception e) {
            log.error("Key['" + key + "'] Not Found in application.properties .");
        }
        return msg;
    }

    public static boolean isProductMode() {
        String msg = null;
        try {
            msg = system.getString("productMode");
            return Boolean.valueOf(msg);
        } catch (Exception e) {
            log.error("Key['productMode'] Not Found or error config in application.properties .");
        }
        return false;
    }

    public static String getRootDomain() {
        String msg = null;
        try {
            msg = system.getString("root_domain");
        } catch (Exception e) {
            log.error("Key['root_domain'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getAppDomain() {
        String msg = null;
        try {
            msg = system.getString("app_domain");
        } catch (Exception e) {
            log.error("Key['app_domain'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getAdminDomain() {
        String msg = null;
        try {
            msg = system.getString("admin_domain");
        } catch (Exception e) {
            log.error("Key['admin_domain'] Not Found in application.properties .");
        }
        return msg;
    }
    public static String getAdminDomain2() {
        String msg = null;
        try {
            msg = system.getString("admin_domain2");
        } catch (Exception e) {
            log.error("Key['admin_domain2'] Not Found in application.properties .");
        }
        return msg;
    }
    public static String getApiDomain() {
        String msg = null;
        try {
            msg = system.getString("api_domain");
        } catch (Exception e) {
            log.error("Key['api_domain'] Not Found in application.properties .");
        }
        return msg;
    }
    
    public static String getCoachApiDomain() {
        String msg = null;
        try {
            msg = system.getString("coach_api_domain");
        } catch (Exception e) {
            log.error("Key['coach_api_domain'] Not Found in application.properties .");
        }
        return msg;
    }
    public static String getStyleDomain() {
        String msg = null;
        try {
            msg = system.getString("style_domain");
        } catch (Exception e) {
            log.error("Key['style_domain'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getStyleDomain(String msg) {
        try {
            if (StringUtil.isNotBlank(msg)) {
                msg = system.getString("style_domain") + msg + "?v=" + system.getString("style_version");
            } else {
                msg = system.getString("style_domain");
            }
        } catch (Exception e) {
            log.error("Key['style_domain'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getImageDomain() {
        String msg = null;
        try {
            msg = system.getString("image_domain");
        } catch (Exception e) {
            log.error("Key['image_domain'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getImageUploadPath() {
        String msg = null;
        try {
            msg = system.getString("image_upload_path");
        } catch (Exception e) {
            log.error("Key['image_upload_path'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getTaskKey() {
        String msg = null;
        try {
            msg = system.getString("task_key");
        } catch (Exception e) {
            log.error("Key['image_upload_path'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getCertificatePath() {
        String msg = null;
        try {
            msg = system.getString("ios_push_certificate_path");
        } catch (Exception e) {
            log.error("Key['ios_push_certificate_path'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getCertificatePassword() {
        String msg = null;
        try {
            msg = system.getString("ios_push_certificate_password");
        } catch (Exception e) {
            log.error("Key['ios_push_certificate_password'] Not Found in application.properties .");
        }
        return msg;
    }

    public static String getUrlWithVersion(String domain, String url) {
        String tagetUrl = "";
        try {
            if (url.contains("?")) {
                tagetUrl = domain + url + "&v=" + system.getString("style_version");
            } else {
                tagetUrl = domain + url + "?v=" + system.getString("style_version");
            }

        } catch (Exception e) {
            log.error("Key['style_version'] Not Found in application.properties .");
        }
        return tagetUrl;
    }
}
