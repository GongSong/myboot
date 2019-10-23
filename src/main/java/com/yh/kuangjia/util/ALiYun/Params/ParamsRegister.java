package com.yh.kuangjia.util.ALiYun.Params;

public class ParamsRegister extends ParamsBase {
    public ParamsRegister() {
    }

    public ParamsRegister(String code, String phone) {
        this.code = code;
        this.setPhone(phone);
    }

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
