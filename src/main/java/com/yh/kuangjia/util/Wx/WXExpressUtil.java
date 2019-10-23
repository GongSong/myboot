package com.yh.kuangjia.util.Wx;

import com.alibaba.fastjson.JSON;
import com.yh.kuangjia.util.HttpUtil;
import com.yh.kuangjia.util.JsonUtil;
import com.yh.kuangjia.util.MapObjUtil;
import com.yh.kuangjia.util.Wx.WxExpress.WxExpressAdd;
import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

public class WXExpressUtil {

    private final static WxConfig config = new WxConfig();

    /**
     * 获取签名
     *
     * @return
     * @throws SocketTimeoutException
     * @throws ConnectTimeoutException
     */
    public String getaccess_take() throws SocketTimeoutException, ConnectTimeoutException {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        requestUrl = requestUrl.replace("APPID", config.getAppID());
        requestUrl = requestUrl.replace("APPSECRET", config.getAppSecret());
        String s = HttpUtil.doGet(requestUrl);
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject.getString("access_token");
    }

    /**
     * 查询物流信息
     *
     * @param access_token
     * @return
     * @throws SocketTimeoutException
     * @throws ConnectTimeoutException
     */
    public Map<String, Object> getExpress(String access_token, String order_id, String openid, String delivery_id, String waybill_id) throws SocketTimeoutException, ConnectTimeoutException {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/express/business/path/get?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
        Map<String, String> data = new HashMap<String, String>();
        data.put("order_id", order_id);
        data.put("openid", openid);
        data.put("delivery_id", delivery_id);  //快递公司 ID
        data.put("waybill_id", waybill_id); //运单 ID
        String s = HttpUtil.doPost(requestUrl, data);
        return JsonUtil.json2Map(s);
    }

    /**
     * 获取支持的快递公司
     *
     * @param access_token
     * @return
     * @throws SocketTimeoutException
     * @throws ConnectTimeoutException
     */
    public Map<String, Object> getcompany(String access_token) throws SocketTimeoutException, ConnectTimeoutException {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/express/business/delivery/getall?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
        String s = HttpUtil.doGet(requestUrl);
        return JsonUtil.json2Map(s);
    }

    /**
     * 生成快递单
     *
     * @param access_token
     * @param dto          快递单信息
     * @return
     * @throws SocketTimeoutException
     * @throws ConnectTimeoutException
     */
    public Map<String, Object> expressAdd(String access_token, WxExpressAdd dto) throws SocketTimeoutException, ConnectTimeoutException {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/express/business/order/add?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
        String s = HttpUtil.doPost(requestUrl, MapObjUtil.String2Map(dto));
        return JsonUtil.json2Map(s);
    }

    /**
     * 取消生成的快递单
     *
     * @param access_token 签名
     * @param order_id     订单id
     * @param openid       用户openid
     * @param delivery_id  快递公司ID
     * @param waybill_id   运单ID
     * @return
     * @throws SocketTimeoutException
     * @throws ConnectTimeoutException
     */
    public Map<String, Object> expressCancel(String access_token, String order_id, String openid, String delivery_id, String waybill_id) throws SocketTimeoutException, ConnectTimeoutException {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/express/business/order/cancel?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
        Map<String, String> data = new HashMap<String, String>();
        data.put("order_id", order_id);
        data.put("openid", openid);
        data.put("delivery_id", delivery_id);  //快递公司 ID
        data.put("waybill_id", waybill_id); //运单 ID
        String s = HttpUtil.doPost(requestUrl, data);
        return JsonUtil.json2Map(s);
    }

    /**
     * 获取所有绑定的物流账号
     */
    public Map<String, Object> getall(String access_token) throws SocketTimeoutException, ConnectTimeoutException {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/express/business/account/getall?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
        String s = HttpUtil.doGet(requestUrl);
        return JsonUtil.json2Map(s);
    }
}
