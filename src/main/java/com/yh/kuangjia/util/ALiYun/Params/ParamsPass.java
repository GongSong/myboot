package com.yh.kuangjia.util.ALiYun.Params;

public class ParamsPass extends ParamsBase {
    public ParamsPass() {
    }

    public ParamsPass( String phone, String pwd) {
        this.pwd = pwd;
        this.setPhone(phone);
    }
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
