package com.yh.kuangjia.util.Wx;

 public  class WxSession {
    private String openid;

    private String session_key;

    private String unionid;

    private int errcode;

    private String errmsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public WxSession() {


    }


    public WxSession(String openid, String session_key, String unionid, Integer errcode, String errmsg) {
        this.openid = openid;
        this.session_key = session_key;
        this.unionid = unionid;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }
}
