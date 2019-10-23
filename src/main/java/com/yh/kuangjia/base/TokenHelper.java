package com.yh.kuangjia.base;



import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.JsonUtil;
import com.yh.kuangjia.util.security.AESUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenHelper {

    /**
     * 获取后台登录用户Token
     *
     * @param token
     * @return
     */
    public static String GetAccessTokenAdmin(TokenAdmin token) {
        token.setExpiredTime(DateUtil.AddDays(new Date(), 1));
        return AESUtil.encrypt(JsonUtil.object2Json(token));
    }

    /**
     * 获取登录用户Token
     *
     * @param token
     * @return
     */
    public static String GetAccessTokenUser(TokenUser token) {
        token.setExpiredTime(DateUtil.AddDays(new Date(), 1));
        return AESUtil.encrypt(JsonUtil.object2Json(token));
    }

    /**
     * 获取Access_Token
     *
     * @param
     * @return
     */
    public static String GetAccessToken(HttpServletRequest request) {
        String token = request.getHeader("Access-Token");  // 从 http 请求头中取出 token
        return AESUtil.decrypt(token);
    }

    /**
     * @return
     */
    public static TokenUser GetTokenUser(HttpServletRequest request) {
        return JsonUtil.json2Object(GetAccessToken(request), TokenUser.class);
    }

    /**
     * @return
     */
    public static TokenAdmin GetTokenAdmin(HttpServletRequest request) {
        return JsonUtil.json2Object(GetAccessToken(request), TokenAdmin.class);
    }
}
