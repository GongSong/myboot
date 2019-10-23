package com.yh.kuangjia.util.ALiYun;

public class OssModel {
    public OssModel() {
    }

    public OssModel(String key, String url) {
        this.Key = key;
        this.Url = url;
    }

    private String Key;
    private String Url;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        this.Key = key;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
