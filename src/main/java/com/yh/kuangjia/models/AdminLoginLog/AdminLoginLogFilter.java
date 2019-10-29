package com.yh.kuangjia.models.AdminLoginLog;

import com.yh.kuangjia.base.PageInfo;

import java.io.Serializable;

public class AdminLoginLogFilter  extends PageInfo implements Serializable {
    private String user_name ;
    private Integer is_succeed ;
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getIs_succeed() {
        return is_succeed;
    }

    public void setIs_succeed(Integer is_succeed) {
        this.is_succeed = is_succeed;
    }
}
