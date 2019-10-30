package com.yh.kuangjia.util.Wx.Express;

import java.io.Serializable;

public class WxExpressInsured implements Serializable {

    /**
     * 是否保价，0 表示不保价，1 表示保价
     */
    private Integer use_insured;

    /**
     * 保价金额，单位是分，比如: 10000 表示 100 元
     */
    private Integer insured_value;

    public Integer getUse_insured() {
        return use_insured;
    }

    public void setUse_insured(Integer use_insured) {
        this.use_insured = use_insured;
    }

    public Integer getInsured_value() {
        return insured_value;
    }

    public void setInsured_value(Integer insured_value) {
        this.insured_value = insured_value;
    }
}
