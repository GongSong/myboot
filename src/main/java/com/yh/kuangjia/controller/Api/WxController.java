package com.yh.kuangjia.controller.Api;

import com.alibaba.fastjson.JSONObject;
import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.util.Wx.WxConfig;
import com.yh.kuangjia.util.Wx.WxOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
//@Api("微信回调相关")
public class WxController extends BaseController {
    public final static WxConfig config = new WxConfig();
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @IgnoreLogin
    @ApiOperation(value = "回调：订单支付")
    @RequestMapping(value = "notify", method = RequestMethod.POST)
    public String Notify() {
        logger.error("Notify");
        if (WxOssUtil.Notify(this.request(), map -> {  //这里都验证查询了
            String out_trade_no = map.get("out_trade_no");
            String transaction_id = map.get("transaction_id");
            String total_fee = map.get("total_fee");
            logger.info("out_trade_no " + out_trade_no);
            logger.info("transaction_id " + transaction_id);
            logger.info("回调数据" + map);
            //查询订单是否存在
//            Order order = mapper.selectByPrimaryKey(Integer.parseInt(out_trade_no));
//            if (order == null) {
//                logger.error("订单：" + out_trade_no + "，为空");
//            }
            //回调业务逻辑
//            Result result = orderService.PayOrder(order.getUser_id(), Integer.parseInt(out_trade_no), transaction_id);
//            if (result.getCode() > 0) {
//                logger.error("订单：" + out_trade_no + "，支付状态更新失败" + result.getMsg());
//            }

        })) {
            //这里要返回xml数据
            return "<xml>" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>" +
                    "  <return_msg><![CDATA[OK]]></return_msg>" +
                    "</xml>";
        }
        return "<xml>" +
                "  <return_code><![CDATA[FAIL]]></return_code>" +
                "  <return_msg><![CDATA[OK]]></return_msg>" +
                "</xml>";
    }

    @IgnoreLogin
    @ApiOperation(value = "回调：充值支付")
    @RequestMapping(value = "recharge", method = RequestMethod.POST)
    public String Recharge() {
        logger.error("Recharge");
        if (WxOssUtil.Notify(this.request(), map -> {  //这里都验证查询了
            String out_trade_no = map.get("out_trade_no");
            String transaction_id = map.get("transaction_id");
            String total_fee = map.get("total_fee");
            logger.error("out_trade_no");
            //回调业务逻辑
            //wealthRechargeService.Recharge(Integer.parseInt(out_trade_no), transaction_id);
        })) {
            //这里要返回xml数据
            return "<xml>" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>" +
                    "  <return_msg><![CDATA[OK]]></return_msg>" +
                    "</xml>";
        }
        return "<xml>" +
                "  <return_code><![CDATA[FAIL]]></return_code>" +
                "  <return_msg><![CDATA[OK]]></return_msg>" +
                "</xml>";
    }

    @IgnoreLogin
    @ApiOperation(value = "回调：微信退款")
    @RequestMapping(value = "refund", method = RequestMethod.POST)
    public String Refund() {
        logger.error("Refund");
        Map<String, String> map1 = null;
        try {
            map1 = WxOssUtil.ResfundNotify(this.request());

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(map1.toString());
        if (map1 != null) {
            String out_refund_no = map1.get("out_refund_no");
            String out_trade_no = map1.get("out_trade_no").substring(1, map1.get("out_trade_no").length());
            String refund_id = map1.get("refund_id");
            String total_fee = map1.get("total_fee");
            String refund_fee = map1.get("refund_fee");
            logger.error(out_refund_no);
            logger.error(out_trade_no);
            //回到业务逻辑
//            Result result = orderService.refundOrder(Integer.parseInt(out_trade_no), out_refund_no, refund_id, refund_fee);
//            if (result.getCode() > 0) {
//                logger.error("售后订单：" + out_refund_no + "，支付状态更新失败" + result.getMsg());
//                return "<xml>" +
//                        "  <return_code><![CDATA[FAIL]]></return_code>" +
//                        "  <return_msg><![CDATA[OK]]></return_msg>" +
//                        "</xml>";
//            }
            return "<xml>" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>" +
                    "  <return_msg><![CDATA[OK]]></return_msg>" +
                    "</xml>";
        } else {
            return "<xml>" +
                    "  <return_code><![CDATA[FAIL]]></return_code>" +
                    "  <return_msg><![CDATA[OK]]></return_msg>" +
                    "</xml>";
        }
    }

}
