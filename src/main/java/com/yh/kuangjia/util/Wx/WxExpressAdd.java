package com.yh.kuangjia.util.Wx;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxExpressAdd implements Serializable {

//    /**
//     * 接口凭证
//     */
//    private String access_token;

    /**
     * 订单号
     */
    private String order_id;

    /**
     * 用户openid
     */
    private String openid;

    /**
     * 快递公司ID
     */
    private String delivery_id;

    /**
     * 快递客户编码或者现付编码
     */
    private String biz_id;

    /**
     * 快递备注信息，比如"易碎物品"，
     */
    private String custom_remark;

    /**
     * 发件人信息
     */
    private Object sender;

    /**
     * 收件人信息
     */
    private Object receiver;

    /**
     * 包裹信息
     */
    private Object cargo;

    /**
     * 商品信息
     */
    private Object shop;

    /**
     * 保价信息
     */
    private Object insured;

    /**
     * 服务类型
     */
    private Object service;


}
