package com.yh.kuangjia.base;

import java.io.Serializable;

public class TokenUser extends Token implements Serializable {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
