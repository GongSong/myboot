package com.yh.kuangjia.util.Wx;


import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.HttpUtil;
import com.yh.kuangjia.util.IPUtil;
import com.yh.kuangjia.util.JsonUtil;
import com.yh.kuangjia.util.Security.AESPKCS7Util;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class WxOssUtil {

    private final static String URL = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";

    private final static WxConfig config = new WxConfig();


    /**
     * 微信登录
     *
     * @param code
     * @return
     * @throws Exception
     */
    public static WxSession WxLogin(String code) throws Exception {
        return JsonUtil.json2Object(HttpUtil.doGet(MessageFormat.format(URL, config.getAppID(), config.getAppSecret(), code)), WxSession.class);
    }


    /**
     * +
     * 小程序支付
     *
     * @param openID
     * @param price
     * @param out_trade_no
     * @param notify_url
     * @param product_id
     * @return
     * @throws Exception
     */
    public static Map<String, String> CreateMiniPay(String openID, double price, String out_trade_no, String notify_url, int product_id) throws Exception {

        Map<String, String> result = CreateJSAPIPay(openID, price, out_trade_no, notify_url, product_id, "");
        if (result.containsKey("return_msg")) {
            throw new Exception("统一下单失败:" + result.get("return_msg"));
        }
        if (!result.containsKey("prepay_id")) {
            throw new Exception("统一下单失败:" + result.get("err_code_des"));
        }
        return CreatePayObj(result.get("prepay_id"));
    }

    /**
     * 异步回调
     *
     * @param request
     * @param func    out_trade_no,transaction_id,total_fee
     * @return
     */
    public static boolean Notify(HttpServletRequest request, Consumer<Map<String, String>> func) {
        WXPay wxpay = null;
        Map<String, String> data = null;
        try {
            wxpay = new WXPay(config);
            String xml = IOUtils.toString(request.getInputStream(), "UTF-8");
            ServletInputStream stream = request.getInputStream();
            data = WXPayUtil.xmlToMap(xml);
            if (wxpay.isPayResultNotifySignatureValid(data)) {
                Map<String, String> query = new HashMap<String, String>();
                query.put("out_trade_no", data.get("out_trade_no"));
                Map<String, String> resp = wxpay.orderQuery(query);
                if (!resp.containsKey("out_trade_no") || !resp.containsKey("transaction_id") || !resp.containsKey("total_fee")) {
                    return false;
                }
                func.accept(resp);
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public static Map<String, String> CreateJSAPIPay(String openID, double price, String out_trade_no, String notify_url, int product_id) throws Exception {
        return CreateJSAPIPay(openID, price, out_trade_no, notify_url, product_id, "订单支付");
    }

    public static Map<String, String> CreateJSAPIPay(String openID, double price, String out_trade_no, String notify_url, int product_id, String body) throws Exception {
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", String.valueOf(new Double(price * 100).intValue()));
        data.put("spbill_create_ip", IPUtil.getIpAddr());
        data.put("notify_url", notify_url);
        data.put("trade_type", "JSAPI");
        data.put("openid", openID);
        data.put("product_id", String.valueOf(product_id));
        return wxpay.unifiedOrder(data);
    }

    /// <summary>
    /// 创建小程序支付包
    /// </summary>
    /// <param name="prepay_id"></param>
    /// <returns></returns>
    public static Map<String, String> CreatePayObj(String prepay_id) throws Exception {
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("appId", config.getAppID());
        data.put("nonceStr", WXPayUtil.generateNonceStr());
        data.put("package", "prepay_id=" + prepay_id);
        data.put("signType", "MD5");
        data.put("timeStamp", String.valueOf(DateUtil.GetCurrentTimeMillis() / 1000));
        data.put("paySign", WXPayUtil.generateSignature(data, config.getKey(), WXPayConstants.SignType.MD5));
        data.remove("appId");
        return data;
    }


    /**
     * 申请退款
     *
     * @param openID
     * @param price
     * @param transaction_id
     * @param notify_url
     * @return
     * @throws Exception
     */
    public static Map<String, String> CreateRefundMoney(String openID, double price, double refundprice, String transaction_id, String refundid, String notify_url, String refund_desc) throws Exception {
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("appid", config.getAppID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        data.put("transaction_id", transaction_id);
        data.put("refund_fee_type", "CNY");
        data.put("out_refund_no", refundid);
        data.put("notify_url", notify_url);
        data.put("refund_desc", refund_desc);
        data.put("total_fee", String.valueOf(new Double(price * 100).intValue()));
        data.put("refund_fee", String.valueOf(new Double(refundprice * 100).intValue()));
        data.put("sign_type", "MD5");
        data.put("mch_id", "1313100401");
        data.put("sign", WXPayUtil.generateSignature(data, config.getKey(), WXPayConstants.SignType.MD5));
        return wxpay.refund(data);
    }

    /**
     * 微信提现
     * @param partner_trade_no  商户订单号
     * @param price 金额
     * @param desc 备注
     * @param openid 用户openid
     * @return
     * @throws Exception
     */
    public static Map<String, String> AmountWithdrawal(String partner_trade_no,double price,String desc,String openid) throws Exception {
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("mch_appid", config.getAppID());
        data.put("mchid", config.getMchID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        data.put("partner_trade_no", partner_trade_no);//商户订单号
        data.put("openid", openid);//用户openid
        data.put("check_name", "NO_CHECK");
        data.put("amount", String.valueOf(new Double(price * 100).intValue()));
        data.put("desc",desc);
        data.put("device_info","013467007045764");
//        data.put("re_user_name","杨洁");
        data.put("spbill_create_ip",IPUtil.getUserIpAddr());
        data.put("sign", WXPayUtil.generateSignature(data, config.getKey(), WXPayConstants.SignType.MD5));
        return wxpay.withdrawal(data);
    }

    /**
     * 获取微信公钥
     * @return
     * @throws Exception
     */
    public static Map<String, String> getPublicKey() throws Exception {
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("mch_id", config.getMchID());
        data.put("nonceStr", WXPayUtil.generateNonceStr());
        data.put("sign", WXPayUtil.generateSignature(data, config.getKey(), WXPayConstants.SignType.MD5));
        data.put("sign_type","MD5");
        return wxpay.getPublicKey(data);
    }


    public static Map<String, String> ResfundNotify(HttpServletRequest request) throws Exception {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
        Map<String, String> m = null;
        try {
            //将xml格式转换为map格式
            m = WXPayUtil.xmlToMap(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (m.containsKey("return_code")&&m.get("return_code").equals("SUCCESS")) {
            //获取加密信息
            String a = m.get("req_info").toString();//第二部 接下来解码加密信息
            String s = AESPKCS7Util.decryptData(a);
            Map<String, String> m1 = null;
            m1 = WXPayUtil.xmlToMap(s);
            if (m1.containsKey("refund_status")&&m1.get("refund_status").equals("SUCCESS")) {
                return m1;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
