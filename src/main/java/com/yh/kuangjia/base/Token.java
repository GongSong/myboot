package com.yh.kuangjia.base;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {
    private Date expiredTime;

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
