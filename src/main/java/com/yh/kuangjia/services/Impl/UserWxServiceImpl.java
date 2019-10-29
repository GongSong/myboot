package com.yh.kuangjia.services.Impl;

import com.alibaba.fastjson.JSONObject;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.models.User.UserLogin;
import com.yh.kuangjia.services.UserWxService;
import com.yh.kuangjia.util.ALiYun.OssUtil;
import com.yh.kuangjia.util.CacheUtil;
import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.EhCacheSpaces;
import com.yh.kuangjia.util.Wx.TokenUtil.AccessToken;
import com.yh.kuangjia.util.Wx.WXAuthUtil;
import com.yh.kuangjia.util.Wx.WXBiz.CheckUtil;
import com.yh.kuangjia.util.Wx.WXPayUtil;
import com.yh.kuangjia.util.Wx.WxConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service("WxUserService")
public class UserWxServiceImpl implements UserWxService {
    public final static WxConfig config = new WxConfig();

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private String _Prex = "GLASSESHOME";

    private String _key = "AccessToken";

    private String _ticket_key = "Ticket";

    @Autowired
    CacheUtil ehcacheUtil;

    @Autowired
    private OssUtil ossUtil;

//    @Override
//    public Boolean InsertOrUpdate(Integer userID, String openID, String sessionKey, UserLogin login) {
//        UserWxInfo info = GetUserInfo(userID);
//        boolean isInsert = info == null;
//        if (isInsert) {
//            info = new UserWxInfo();
////            info.setAvatar_url(login.getAvatarUrl());
//            info.setOpen_id(openID);
//        }
//        info.setSession_key(sessionKey);
//        info.setN_user_id(userID);
//        info.setIs_admin(false);
//        info.setExpires_time(new Long(DateUtil.GetCurrentTimeMillis() / 1000 + 3600).intValue());
//        if (isInsert) {
////            String key=_Prex + "/USER/" + DateUtil.GetDateInt() + "/" + RandomUtil.getRandomString(16)+".png";
////            ossUtil.fileUp(key,info.getAvatar_url());
////            info.setAvatar_url(key);
//            return userWxInfoMapper.insert(info) > 0;
//        } else {
//            return userWxInfoMapper.updateByPrimaryKey(info) > 0;
//        }
//    }

//    @Override
//    public String GetOpenID(Integer userID) {
//        UserWxInfo info = userWxInfoMapper.selectByUser_ID(userID);
//        if (info == null || (info.getOpen_id() == null || info.getOpen_id().isEmpty())) {
//            return null;
//        }
//        return info.getOpen_id();
//    }

    @Override
    public String GetOpenID(Integer userID) {
        return null;
    }

    @Override
    public String getAccessToken() {
        return getToken();
    }

    @Override
    public Result ticket(String referer) {
        String ticket = getTicket();
        logger.info("ticket" + ticket);
        Map<String, String> data = new HashMap<String, String>();
        data.put("nonceStr", WXPayUtil.generateNonceStr());
        data.put("jsapi_ticket", ticket);
        data.put("timestamp", String.valueOf(DateUtil.GetCurrentTimeMillis() / 1000));
        data.put("url", referer);
        String ticket1 = WXPayUtil.getTicket(data);
        logger.info(ticket1);
        String signature = CheckUtil.getSha1(ticket1);
        data.put("signature", signature);
        data.put("appId", config.getAppID());
        data.remove("jsapi_ticket");
        data.put("debug","");
        return Result.success(data);
    }

    @Override
    public String checkIsFocus(Integer user_id) {
        //获取用的openid
        String openid = "";
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+getToken()+"&openid="+openid+"&lang=zh_CN";
        try {
            JSONObject jsonObject = WXAuthUtil.doGetJson(url);
            String subscribe = jsonObject.getString("subscribe");
            String errcode = jsonObject.getString("errcode");
            if (null!=errcode&&errcode.equals("40003")) {
                return "401";
            }
            return null==subscribe?"0":subscribe;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

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

    public String getTicket() {
        String ticket;
        ticket = ehcacheUtil.get(EhCacheSpaces.AccessToken, _ticket_key);
        if (ticket == null) {
            synchronized (getClass()) {
                ticket = ehcacheUtil.get(EhCacheSpaces.AccessToken, _ticket_key);
                if (ticket == null) {
                    ticket = Ticket();
                    ehcacheUtil.put(EhCacheSpaces.AccessToken, _ticket_key, ticket);
                }
            }
        }
        return ticket;
    }

    public String Ticket() {
        String ticket = null;
        try {
            JSONObject jsonObject = WXAuthUtil.doGetJson("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getToken() + "&type=jsapi");
            logger.info("jsonObjectTicket  " + jsonObject);
            if (jsonObject.getString("errcode").equals("0")) {
                ticket = jsonObject.getString("ticket");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticket;
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
