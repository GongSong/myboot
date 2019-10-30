package com.yh.kuangjia.controller.Api;

import com.alibaba.fastjson.JSONObject;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.TokenUser;
import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.services.UserWxService;
import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.JsonUtil;
import com.yh.kuangjia.util.Wx.WXAuthUtil;
import com.yh.kuangjia.util.Wx.WXBiz.AesException;
import com.yh.kuangjia.util.Wx.WXBiz.MessageUtil;
import com.yh.kuangjia.util.Wx.WXBiz.ReturnMan;
import com.yh.kuangjia.util.Wx.WXBiz.WXBizMsgCrypt;
import com.yh.kuangjia.util.Wx.WXPayUtil;
import com.yh.kuangjia.util.Wx.WxConfig;
import com.yh.kuangjia.util.Security.AESPKCS5Util;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("api/wx")
//@Api("微信公众号相关")
public class WxPublicController extends BaseController {
    public final static WxConfig config = new WxConfig();
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static String encodingAesKey = "09nJcZMiRVsMGvXDSNyWHRPcHr5ilbAlL9pliYnACLe";
    private static String token = "duobaoyu";

    @Autowired
    UserWxService userWxService;

    @IgnoreLogin
    @ApiOperation(value = "公众号微信登录授权")
    @RequestMapping(value = "wxLogin", method = RequestMethod.GET)
    public void wxLogin(HttpServletRequest request,
                        HttpServletResponse response) {
        String referer = request.getParameter("referer");
        //加密
        String encrypt = AESPKCS5Util.encrypt(referer);
        //回调地址
        String backUrl = "https://classwx.91duobaoyu.com/api/wx/callBack";
        try {
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + config.getAppID()
                    + "&redirect_uri=" + URLEncoder.encode(backUrl, "UTF-8")
                    + "&response_type=code"
                    + "&scope=snsapi_userinfo"
                    + "&state=" + encrypt + "#wechat_redirect";
            logger.info("forward重定向地址{" + url + "}");
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @IgnoreLogin
    @ApiOperation(value = "回调")
    @RequestMapping(value = "callBack", method = RequestMethod.GET)
    public String callBack(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String code = req.getParameter("code");//拉去用户信息所需code值
        String state = req.getParameter("state");
        logger.info("state" + state);
        TokenUser token = new TokenUser();
        token.setExpiredTime(DateUtil.AddDays(new Date(), 10));
        String replace = state.replace("#wechat_redirect", "");
        logger.info("replace" + replace);
        //解密
        String referer = AESPKCS5Util.decrypt(replace.replace(" ", "+"));
        logger.info("referer" + referer);
        //通过code换取网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + config.getAppID()
                + "&secret=" + config.getAppSecret()
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = WXAuthUtil.doGetJson(url);
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        String refresh_token = jsonObject.getString("refresh_token");
        // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token
                + "&openid=" + openid
                + "&lang=zh_CN";
        JSONObject jsonObject1 = WXAuthUtil.doGetJson(infoUrl);
        logger.info(jsonObject1.toString());
        //获取微信用户基本信息 保存信息
//        Integer integer = userService.loginAdd(jsonObject1, userId, n_course_id);
        token.setUserId(0);
        String encrypt = AESPKCS5Util.encrypt(JsonUtil.object2Json(token));
        //重定向地址
        if (true) {
            resp.sendRedirect("http://class.91duobaoyu.com/web/?token=" + encrypt + "#/course/follow");
            return "0";
        } else {
            resp.sendRedirect(referer);
            return "0";
        }
    }

    @IgnoreLogin
    @ApiOperation(value = "校验服务器请求")
    @RequestMapping(value = "checksignature")
    public String checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException, AesException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean isGet = request.getMethod().toLowerCase().equals("get");
        String signature = request.getParameter("signature");// 微信加密签名
        logger.info("signature " + signature);
        String timestamp = request.getParameter("timestamp");// 时间戳
        logger.info("timestamp " + timestamp);
        String nonce = request.getParameter("nonce");// 随机数
        logger.info("nonce " + nonce);
        String echostr = request.getParameter("echostr");// 随机字符串
        if (isGet) {
            String s = checkSignature(signature, timestamp, nonce, echostr);
            if (null != s) {
                return s;
            }
        } else {
            try {
                PrintWriter out = response.getWriter();
                String xml = IOUtils.toString(request.getInputStream(), "UTF-8");
                String message = acceptMessage(signature, timestamp, encodingAesKey, nonce, xml);
                out.print(null != message ? message : "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @ApiOperation(value = "校验用户是否关注公众号")
    @RequestMapping(value = "checkisfocus", method = RequestMethod.GET)
    public Result checkIsFocus(HttpServletResponse response) throws IOException {
        String s = userWxService.checkIsFocus(this.GetTokenUser().getUserId());
        if (s.equals("401")) return new Result(401, "");
        return Result.success(s);
    }


    @IgnoreLogin
    @ApiOperation(value = "菜单")
    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public Result menu() {
        try {
            JSONObject jsonObject = WXAuthUtil.doGetJson("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + userWxService.getAccessToken());
            logger.info(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @IgnoreLogin
    @ApiOperation(value = "权限签名")
    @RequestMapping(value = "ticket", method = RequestMethod.GET)
    public Result ticket(HttpServletRequest request, HttpServletResponse response) throws SocketTimeoutException, ConnectTimeoutException {
        String referer = request.getHeader("referer");
        Result ticket = userWxService.ticket(referer);
        return ticket;
    }

    private String checkSignature(String signature, String timestamp, String nonce, String echostr) throws IOException, AesException {
        if (null != echostr) {
            return echostr;
        }
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token,
                encodingAesKey, config.getAppID());
        String url = wxcpt.verifyUrl(signature, timestamp, nonce, echostr);
        return url;
    }

    private String acceptMessage(String signature, String timestamp, String encodingAesKey, String nonce, String xml) throws IOException {
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token,
                    encodingAesKey, config.getAppID());
            String s = wxcpt.decryptMsg(signature, timestamp, nonce, xml);
            Map<String, String> map = WXPayUtil.xmlToMap(s);
            ReturnMan adapter = getReturnMan(map);
            String message = null;
            switch (adapter.getMsgType()) {
                case "event":
                    String eventType = map.get("Event");
                    if (MessageUtil.EVENT_SUB.equals(eventType)) {
                        message = TextMsg(adapter, "欢迎关注公众号");
                    }
                    break;
                case "text":
                    message = TextMsg(adapter, "敬请期待");
                    break;
                case "VIEW":
                    message = TextMsg(adapter, "敬请期待");
                    break;
                case "image":
                    message = TextMsg(adapter, "敬请期待");
                    break;
                case "voice":
                    message = TextMsg(adapter, "敬请期待");
                    break;
                case "video":
                    message = TextMsg(adapter, "敬请期待");
                    break;
                case "location":
                    message = TextMsg(adapter, "敬请期待");
                    break;
                default:
                    break;
            }
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 推送关注消息
     *
     * @param n_course_id
     * @param vc_course_name
     * @param nick_name
     * @param openid
     */
    private void sengMSG(Integer n_course_id, String vc_course_name, String nick_name, String openid) {
//        SendMSGAdd sendMSGAdd = new SendMSGAdd();
//        sendMSGAdd.setFirst(new SendMSGContext("你已成功加入训练营 【" + vc_course_name + "】", "#3385ff"));
//        sendMSGAdd.setKeyword1(new SendMSGContext(nick_name, ""));
//        sendMSGAdd.setKeyword2(new SendMSGContext(DateUtil.DateFormat(DateUtil.GetDate(), "yyyy-MM-dd HH:mm:ss"), ""));
//        sendMSGAdd.setRemark(new SendMSGContext("点击这里，领取课程>>", "#3385ff"));
//        SendMSGUtil.sendMsg(template_id, openid, url + n_course_id, sendMSGAdd, userWxService.getAccessToken());
    }

    public static String TextMsg(ReturnMan adapter, String content) throws Exception {
        String s = "<xml><ToUserName><![CDATA[toUserName]]></ToUserName><FromUserName><![CDATA[fromUserName]]></FromUserName><CreateTime>createTime</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[content]]></Content></xml>";
        String replace = s.replace("toUserName", adapter.getToUserName()).replace("fromUserName", adapter.getFromUserName()).replace("createTime", adapter.getCreateTime()).replace("content", content);
        return replace;
    }

    public static ReturnMan getReturnMan(Map<String, String> map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String createTime = map.get("CreateTime");
        ReturnMan returnMan = new ReturnMan();
        returnMan.setCreateTime(createTime);
        returnMan.setMsgType(msgType);
        returnMan.setToUserName(fromUserName);
        returnMan.setFromUserName(toUserName);
        return returnMan;
    }

    /**
     * 推送消息
     *
     * @param url
     * @param MSG
     * @return
     */
    public static JSONObject SendMSG(String url, String MSG) {
        URL urlGet = null;
        JSONObject object = null;
        try {
            urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); //必须get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            OutputStream os = null;
            if (!MSG.equals("")) { //写消息数据
                os = http.getOutputStream();
                os.write(MSG.getBytes("UTF-8")); //传入参数
            }
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            object = JSONObject.parseObject(message);
            //关闭流
            if (!MSG.equals("")) {
                os.flush();
                os.close();
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
