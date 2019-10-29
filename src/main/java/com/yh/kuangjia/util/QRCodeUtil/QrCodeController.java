package com.yh.kuangjia.util.QRCodeUtil;

import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.util.HttpUtil;
import com.yh.kuangjia.util.JsonUtil;
import com.yh.kuangjia.util.Wx.QrCodeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("api/qrcode")
@Api("小程序码")
public class QrCodeController extends BaseController {

    @IgnoreLogin
    @ApiOperation(value = "生成邀请二维码")
    @RequestMapping(value = "code", method = RequestMethod.GET)
    public void madeQrCode(@RequestParam("user_id") int user_id) throws Exception {
        byte[] bytes = QrCodeController.getminiqrQr(QrCodeController.getAccessToken(), user_id + "");
        this.response().setContentType("image/png");
        this.response().getOutputStream().write(bytes);
    }


    @IgnoreLogin
    @ApiOperation(value = "生成课程小程序码")
    @RequestMapping(value = "moddlecode", method = RequestMethod.GET)
    public void moddlecode(@RequestParam("course_id") Integer course_id) throws Exception {
        byte[] bytes = QrCodeController.getminiqrmoddleQr(QrCodeController.getAccessToken(),course_id+"");
        this.response().setContentType("image/png");
        this.response().getOutputStream().write(bytes);
    }

    private static final String APPID = "wxda251596b7bcdf4c";
    private static final String APPSECRET = "e39f65898115169f69da47091d4a91d6";

    public static String getAccessToken() throws Exception {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + APPID + "&secret=" + APPSECRET;
//        System.out.println("URL for getting accessToken accessTokenUrl=" + accessTokenUrl);

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
            result.setPage("pages/home/main");
            result.setWidth(600);
            result.setScene(userId);
            String b = JsonUtil.object2Json(result);
            return HttpUtil.doPostBytes("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getminiqrmoddleQr(String accessToken,String course_id) {
        InputStream in = null;
        try {
            org.json.JSONObject paramJson = new org.json.JSONObject();
            QrCodeResult result = new QrCodeResult();
            result.setPage("pages/courseDetails/main");
            result.setWidth(600);
            result.setScene(course_id);
            String b = JsonUtil.object2Json(result);
            return HttpUtil.doPostBytes("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
