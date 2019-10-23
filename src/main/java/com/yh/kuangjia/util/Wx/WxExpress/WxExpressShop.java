package com.yh.kuangjia.util.Wx.WxExpress;

import java.io.Serializable;

public class WxExpressShop implements Serializable {

    /**
     * 商家小程序的路径，建议为订单页面
     */
    private String wxa_path;

    /**
     * 商品缩略图 url
     */
    private String img_url;

    /**
     * 商品名称
     */
    private String goods_name;

    /**
     * 商品数量
     */
    private Integer goods_count;

    public String getWxa_path() {
        return wxa_path;
    }

    public void setWxa_path(String wxa_path) {
        this.wxa_path = wxa_path;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(Integer goods_count) {
        this.goods_count = goods_count;
    }
}
