package com.yh.kuangjia.util.Wx.WxExpress;

import java.io.Serializable;

public class WxExpressCargoDeteilList implements Serializable {

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品数量
     */
    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
