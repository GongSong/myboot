package com.yh.kuangjia.controller.Api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.util.HttpUtil;
import com.yh.kuangjia.util.QRCodeUtil.ZXingCode;
import com.yh.kuangjia.util.Wx.WXAuthUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/douyin")
public class DouYinController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static final String douyinurl = "https://open.douyin.com";

    private static String UserUrl = "https://open.douyin.com/oauth/userinfo/";

    private static String client_key = "aw45j5lmfs5r0ajw";

    private static String client_secret = "0c8849a08d767885aa745ffdbaefae4b";

    private static String redirect_uri = "http://dy.redsay.net/api/douyin/redirect?uuid=" + 1;

    private static String access_token = "act.7db75fe0374745bab0da1749372307cc0SDngig9X3gmELqjU7wZoHv2hfzd";

    private static String open_id = "ffa22c86-831e-4a56-9008-91506b6cdd9a";

    private static String client_token = "clt.a66ba50909bb35d9279e3dbb44cfcb43Y0I0AFWKQfU7bK92vDoDWZ4CGt4z";

    private static String image_id = "tos-cn-i-0813/3722fd3a1108423b82185d6e69c36a29";

    private static String video_id = "v0200fdf0000bnrld54d1dravk5ihmj0";


    @IgnoreLogin
    @ApiOperation(value = "抖音登录授权")
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public void login(HttpServletResponse response) throws IOException {
        String url = douyinurl + "/platform/oauth/connect/?client_key=" + client_key
                + "&redirect_uri=" + URLEncoder.encode(redirect_uri, "UTF-8")
                + "&response_type=code"
                + "&scope=user_info,aweme.share,video.create,video.delete,video.data,video.list,video.comment,im,following.list,fans.list,fans.data"
                + "&state=3001";
        ZXingCode zXingCode = new ZXingCode();
        byte[] code = zXingCode.getLogoQRCode(url, "", null);
        response.setContentType("image/png");
        response.getOutputStream().write(code);
    }

    @IgnoreLogin
    @ApiOperation(value = "抖音回调接口")
    @RequestMapping(value = "redirect", method = RequestMethod.GET)
    public void redirect(@RequestParam("uuid") Integer uuid, HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");//拉去用户信息所需code值
        logger.info("id   " + uuid);
        logger.info("code  " + code);
        String state = req.getParameter("state");
        logger.info("state  " + state);
        String url = douyinurl + "/oauth/access_token/?client_key=" + client_key
                + "&client_secret=" + client_secret + "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = jsonObject.getString("data");
        Map<String, Object> map = JSON.parseObject(data, HashMap.class);
        logger.info("map转换的信息  " + map);
        String access_token = (String) map.get("access_token");
        String open_id = (String) map.get("open_id");
        Object expires_in = map.get("expires_in");
        String refresh_token = (String) map.get("refresh_token");
        String infoUrl = UserUrl + "?access_token=" + access_token + "&open_id=" + open_id;
        JSONObject UserInfo = null;
        try {
            UserInfo = WXAuthUtil.doGetJson(infoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String datainfo = UserInfo.getString("data");
        Map<String, Object> info = JSON.parseObject(datainfo, HashMap.class);
        logger.info("info " + info);
    }


    @IgnoreLogin
    @ApiOperation(value = "获取用户粉丝数据")
    @RequestMapping(value = "fansdata", method = RequestMethod.GET)
    public Result fansData() {
        String infoUrl = douyinurl + "/fans/data/?access_token=" + access_token + "&open_id=" + open_id;
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }


    @IgnoreLogin
    @ApiOperation(value = "获取关注列表")
    @RequestMapping(value = "follwinglist", method = RequestMethod.GET)
    public Result followingList() {
        String infoUrl = douyinurl + "/following/list/?access_token=" + access_token + "&open_id=" + open_id + "&cursor=" + 0 + "&count=" + 20;
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }

    @IgnoreLogin
    @ApiOperation(value = "粉丝列表")
    @RequestMapping(value = "fanslist", method = RequestMethod.GET)
    public Result fansList() {
        String infoUrl = douyinurl + "/fans/list/?access_token=" + access_token + "&open_id=" + open_id + "&cursor=" + 0 + "&count=" + 20;
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
            return Result.success(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }

    @IgnoreLogin
    @ApiOperation(value = "用户信息")
    @RequestMapping(value = "userinfo", method = RequestMethod.GET)
    public Result userInfo() {
        String infoUrl = douyinurl + "/oauth/userinfo/?access_token=" + access_token + "&open_id=" + open_id;
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
            return Result.success(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }

    @IgnoreLogin
    @ApiOperation(value = "获取意向用户详情")
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Result detail() {
        String user_id = "892455421218382";
        String infoUrl = douyinurl + "/enterprise/leads/user/detail/?access_token=" + access_token + "&open_id=" + open_id + "&user_id=" + user_id;
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }

    @IgnoreLogin
    @ApiOperation(value = "生成client_token")
    @RequestMapping(value = "clienttoken", method = RequestMethod.GET)
    public void clientToken() {
        String url = douyinurl + "/oauth/client_token/?client_key=" + client_key
                + "&client_secret=" + client_secret + "&grant_type=client_credential";
        try {
            JSONObject jsonObject = WXAuthUtil.doGetJson(url);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @IgnoreLogin
    @ApiOperation(value = "获取实时热点词")
    @RequestMapping(value = "sentences", method = RequestMethod.GET)
    public Result sentences() {
        String infoUrl = douyinurl + "/hotsearch/sentences/?access_token=" + client_token;
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }

    @IgnoreLogin
    @ApiOperation(value = "获取热点词聚合的视频")
    @RequestMapping(value = "videos", method = RequestMethod.GET)
    public Result videos() {
        String infoUrl = douyinurl + "/hotsearch/videos/?access_token=" + client_token + "&hot_sentence=" + "詹姆斯胯下妙传魔兽暴扣";
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }

    @IgnoreLogin
    @ApiOperation(value = "列出已发布的视频")
    @RequestMapping(value = "videoslist", method = RequestMethod.GET)
    public Result videoList() {
        String openId = "71d2ec82-4235-42f9-aaf7-75a58e7d1c1c";
        String accessToken = "act.46775a5a6fe53dd6965363f7eb35aec3Ykk9ZC0M3u3U2NvmtSZmGxNHRrlL";
        String infoUrl = douyinurl + "/video/list/?access_token=" + accessToken + "&open_id=" + openId + "&cursor=" + 0 + "&count=" + 20;
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }

    @IgnoreLogin
    @ApiOperation(value = "评论列表")
    @RequestMapping(value = "commentlist", method = RequestMethod.GET)
    public Result commentList() {
        String item_id = "@9VxQg/nHUMQ2LCKzMNk3V8780mDgOfGHOpN0oAKmKFUTZvf060zdRmYqig357zEBjmgmjRs2mauF6XFwFxJrcg==";
        String infoUrl = douyinurl + "/video/comment/list/?access_token=" + access_token + "&open_id=" + open_id + "&cursor=" + 0 + "&count=" + 10 + "&item_id=" + item_id;
        JSONObject jsonObject = null;
        try {
            jsonObject = WXAuthUtil.doGetJson(infoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(jsonObject);
    }

    @IgnoreLogin
    @ApiOperation(value = "给抖音用户发送消息")
    @RequestMapping(value = "send", method = RequestMethod.GET)
    public Result send() {
        String infoUrl = douyinurl + "/video/comment/list/?access_token=" + access_token + "&open_id=" + open_id;
        Map<String, Object> map = new HashMap<>();
        map.put("to_user_id", "71d2ec82-4235-42f9-aaf7-75a58e7d1c1c");
        map.put("message_type", "text");
        map.put("content", "这是一条文本消息");
        String s = null;
        try {
            s = HttpUtil.doPost(infoUrl, JSONObject.toJSONString(map));
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        return Result.success(s);
    }

    @IgnoreLogin
    @ApiOperation(value = "查询指定视频数据")
    @RequestMapping(value = "videodata", method = RequestMethod.GET)
    public Result videoData() {
        String infoUrl = douyinurl + "/video/comment/list/?access_token=" + access_token + "&open_id=" + open_id;
        List<String> list = new ArrayList<>();
        list.add("@9VxQg/nHUMQ2LCKzMNk3V8780mPhOvyDOJFxoAyvJ1Ubbvb060zdRmYqig357zEBLI+MvFV46QmqQzIWUoA6JQ==");
        String s = null;
        try {
            s = HttpUtil.doPost(infoUrl, JSONObject.toJSONString(list));
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        return Result.success(s);
    }


    public Result imageUpload() {
        return null;
    }

    @IgnoreLogin
    @ApiOperation(value = "视频上传")
    @RequestMapping(value = "video_upload", method = RequestMethod.POST)
    private Result sendVideo(MultipartFile file) throws Exception {
        String urlFormat = "https://open.douyin.com/video/upload/?open_id=%s&access_token=%s";
        String openId = "71d2ec82-4235-42f9-aaf7-75a58e7d1c1c";
        String accessToken = "act.46775a5a6fe53dd6965363f7eb35aec3Ykk9ZC0M3u3U2NvmtSZmGxNHRrlL";
        String urlStr = String.format(urlFormat, openId, accessToken);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postReq = new HttpPost(urlStr);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        byte[] data = file.getBytes();
        builder.addBinaryBody("video",
                new ByteArrayInputStream(data),
                ContentType.APPLICATION_OCTET_STREAM,
                file.getOriginalFilename());
        HttpEntity entity = builder.build();
        postReq.setEntity(entity);
        CloseableHttpResponse resp = httpClient.execute(postReq);
        HttpEntity respEntity = resp.getEntity();
        String sResponse = EntityUtils.toString(respEntity, "UTF-8");
        System.out.println("post result=" + sResponse);
        return Result.success(sResponse);
    }

    @IgnoreLogin
    @ApiOperation(value = "图片上传")
    @RequestMapping(value = "image_upload", method = RequestMethod.POST)
    private Result sendImage(MultipartFile file) throws Exception {
        String urlFormat = "https://open.douyin.com/image/upload/?open_id=%s&access_token=%s";
        String openId = "71d2ec82-4235-42f9-aaf7-75a58e7d1c1c";
        String accessToken = "act.46775a5a6fe53dd6965363f7eb35aec3Ykk9ZC0M3u3U2NvmtSZmGxNHRrlL";
        String urlStr = String.format(urlFormat, openId, accessToken);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postReq = new HttpPost(urlStr);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        byte[] data = file.getBytes();
        builder.addBinaryBody("image",
                new ByteArrayInputStream(data),
                ContentType.APPLICATION_OCTET_STREAM,
                file.getOriginalFilename());
        HttpEntity entity = builder.build();
        postReq.setEntity(entity);
        CloseableHttpResponse resp = httpClient.execute(postReq);
        HttpEntity respEntity = resp.getEntity();
        String sResponse = EntityUtils.toString(respEntity, "UTF-8");
        System.out.println("post result=" + sResponse);
        return Result.success(sResponse);
    }

    @IgnoreLogin
    @ApiOperation(value = "发布图片")
    @RequestMapping(value = "image_create", method = RequestMethod.GET)
    public Result imageCreate() {
        String openId = "71d2ec82-4235-42f9-aaf7-75a58e7d1c1c";
        String accessToken = "act.46775a5a6fe53dd6965363f7eb35aec3Ykk9ZC0M3u3U2NvmtSZmGxNHRrlL";
        String infoUrl = douyinurl + "/image/create/?access_token=" + accessToken + "&open_id=" + openId;
        String s = null;
        Map<String, Object> map = new HashMap<>();
        map.put("image_id", image_id);
        try {
            s = HttpUtil.doPost(infoUrl, JSONObject.toJSONString(map));
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        return Result.success(s);
    }

    @IgnoreLogin
    @ApiOperation(value = "发布视频")
    @RequestMapping(value = "video_create", method = RequestMethod.GET)
    public Result videoCreate() {
        String openId = "71d2ec82-4235-42f9-aaf7-75a58e7d1c1c";
        String accessToken = "act.46775a5a6fe53dd6965363f7eb35aec3Ykk9ZC0M3u3U2NvmtSZmGxNHRrlL";
        String infoUrl = douyinurl + "/video/create/?access_token=" + accessToken + "&open_id=" + openId;
        String s = null;
        Map<String, Object> map = new HashMap<>();
        map.put("video_id", video_id);
        try {
            s = HttpUtil.doPost(infoUrl, JSONObject.toJSONString(map));
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        return Result.success(s);
    }
}