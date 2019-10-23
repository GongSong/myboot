package com.yh.kuangjia.util;


public class PathUtil {

    /**
     * 获取url中的主机名
     * http://192.168.0.1/abc/a.jsp  ->  192.168.0.1
     * @param url
     * @return
     */
    public static String getBaseUrl(String url){
        url = url.replace("\\","/");
        url = url.replace("http://","");
        url = url.replace("https://","");
        if(url.indexOf("/") > -1){
            url = url.substring(0,url.indexOf("/"));
        }
        return url;
    }
}
