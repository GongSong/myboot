package com.yh.kuangjia.util.Wx.WxExpress;

import java.io.Serializable;

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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getBiz_id() {
        return biz_id;
    }

    public void setBiz_id(String biz_id) {
        this.biz_id = biz_id;
    }

    public String getCustom_remark() {
        return custom_remark;
    }

    public void setCustom_remark(String custom_remark) {
        this.custom_remark = custom_remark;
    }

    public Object getSender() {
        return sender;
    }

    public void setSender(Object sender) {
        this.sender = sender;
    }

    public Object getReceiver() {
        return receiver;
    }

    public void setReceiver(Object receiver) {
        this.receiver = receiver;
    }

    public Object getCargo() {
        return cargo;
    }

    public void setCargo(Object cargo) {
        this.cargo = cargo;
    }

    public Object getShop() {
        return shop;
    }

    public void setShop(Object shop) {
        this.shop = shop;
    }

    public Object getInsured() {
        return insured;
    }

    public void setInsured(Object insured) {
        this.insured = insured;
    }

    public Object getService() {
        return service;
    }

    public void setService(Object service) {
        this.service = service;
    }
}
