package com.yh.kuangjia.util.ALiYun.Params;

public class ParamRefund extends ParamsBase {

    public ParamRefund() {
    }

    public ParamRefund(Integer code, String phone) {
        this.code = code;
        this.setPhone(phone);
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
