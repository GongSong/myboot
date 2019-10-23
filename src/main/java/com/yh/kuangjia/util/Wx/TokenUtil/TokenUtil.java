package com.yh.kuangjia.util.Wx.TokenUtil;

import com.alibaba.fastjson.JSONObject;
import com.yh.kuangjia.util.CacheUtil;
import com.yh.kuangjia.util.EhCacheSpaces;
import com.yh.kuangjia.util.Wx.WxConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class TokenUtil {

    private static  String _key = "AccessToken";
    private final static WxConfig config = new WxConfig();

    @Autowired
    CacheUtil ehcacheUtil;

    public String getToken() {
        String token;
        token = ehcacheUtil.get(EhCacheSpaces.AccessToken, _key);
        if (token == null) {
            synchronized (getClass()) {
                token = ehcacheUtil.get(EhCacheSpaces.AccessToken, _key);
                if (token == null) {
                    token = getHttpToken();
                    ehcacheUtil.put(EhCacheSpaces.AccessToken, _key, token);
                }
            }
        }
        return token;
    }

    public String getHttpToken() {
        String tmpUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + config.getAppID()
                + "&secret=" + config.getAppSecret();
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(tmpUrl);
        try {
            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String entity = EntityUtils.toString(httpResponse.getEntity());
                AccessToken accessToken = JSONObject.parseObject(entity, AccessToken.class);
                return accessToken.getAccess_token();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                httpCilent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
