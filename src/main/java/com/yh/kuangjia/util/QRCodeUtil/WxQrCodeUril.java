package com.yh.kuangjia.util.QRCodeUtil;


import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.util.HttpUtil;
import com.yh.kuangjia.util.JsonUtil;
import com.yh.kuangjia.util.Wx.QrCodeResult;
import com.yh.kuangjia.util.Wx.WxConfig;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 微信小程序二维码
 */
public class WxQrCodeUril extends BaseController {

    private final static WxConfig config = new WxConfig();

    @IgnoreLogin
    @ApiOperation(value = "生成二维码")
    @RequestMapping(value = "code", method = RequestMethod.GET)
    public void madeQrCode(@RequestParam("user_id") int user_id) throws Exception {
        byte[] bytes = WxQrCodeUril.getminiqrQr(WxQrCodeUril.getAccessToken(), user_id + "");
        this.response().setContentType("image/png");
        this.response().getOutputStream().write(bytes);
    }


    @IgnoreLogin
    @ApiOperation(value = "生成小程序码")
    @RequestMapping(value = "moddlecode", method = RequestMethod.GET)
    public void madeQrCode() throws Exception {
        byte[] bytes = WxQrCodeUril.getminiqrmoddleQr(WxQrCodeUril.getAccessToken());
        this.response().setContentType("image/png");
        this.response().getOutputStream().write(bytes);
    }

    public static String getAccessToken() throws Exception {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + config.getAppID() + "&secret=" + config.getAppSecret();

        URL url = new URL(accessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size = inputStream.available();
        byte[] bs = new byte[size];
        inputStream.read(bs);
        String message = new String(bs, "UTF-8");

        //获取access_token
        JSONObject jsonObject = JSONObject.fromObject(message);
        String accessToken = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
        return accessToken;
    }

    public static byte[] getminiqrQr(String accessToken, String userId) {
        InputStream in = null;
        try {
            org.json.JSONObject paramJson = new org.json.JSONObject();
            QrCodeResult result = new QrCodeResult();
            result.setPage("pages/worksStore/worksStore");
            result.setWidth(600);
            result.setScene(userId);
            String b = JsonUtil.object2Json(result);
            return HttpUtil.doPostBytes("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getminiqrmoddleQr(String accessToken) {
        InputStream in = null;
        try {
            org.json.JSONObject paramJson = new org.json.JSONObject();
            QrCodeResult result = new QrCodeResult();
            result.setPage("pages/login/register");
            result.setWidth(600);
            result.setScene("1");
            String b = JsonUtil.object2Json(result);
            return HttpUtil.doPostBytes("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
