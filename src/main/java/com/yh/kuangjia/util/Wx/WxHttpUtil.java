package com.yh.kuangjia.util.Wx;

public class WxHttpUtil {
    //服务器
    private final static String URL="https://api.benyuan-online.com";
    /**
     * 支付回调
     */
    public static String notify_url = URL+"/api/wx/notify";
    /**
     * 退款回调
     */
    public static String notify_url_refund = URL+"/api/wx/refund";

    /**
     * 充值回调
     */
    public static String recharge_url = URL+"/api/wx/recharge";

}
