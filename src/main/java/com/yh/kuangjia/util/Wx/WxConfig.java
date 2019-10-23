package com.yh.kuangjia.util.Wx;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class WxConfig extends WXPayConfig {
    private static final String appId = "";
    private static final String appsecret = "";

    private static final String mchID = "";

    private static final String key = "";

    private static byte[] certData;


    @Override
    public String getAppSecret() {
        return appsecret;
    }

    @Override
    public String getAppID() {
        return appId;
    }

    /**
     * 商户ID
     *
     * @return
     */
    @Override
    String getMchID() {
        return mchID;
    }

    /**
     * @return API密钥
     */
    @Override
    String getKey() {
        return key;
    }

    /**
     * 获取商户证书内容(退款)
     *
     * @return 商户证书内容
     */
    @Override
    InputStream getCertStream() throws FileNotFoundException {


        InputStream in = null;
        String path = null;
        //线下
//            path = ResourceUtils.getURL("classpath:").getPath();
//            File file = new File(path+"configs/apiclient_cert.p12");
        //服务器
//            File file = new File("D:/cert/apiclient_cert.p12");
//            in = new FileInputStream(file);
        return in;
    }

    /**
     * 获取微信支付域名
     *
     * @return
     */
    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }
}
