package com.yh.kuangjia.base;

import java.io.Serializable;

public class TokenAdmin extends Token implements Serializable {
    private int adminId;
    private String roleIDs;
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getRoleIDs() {
        return roleIDs;
    }

    public void setRoleIDs(String roleIDs) {
        this.roleIDs = roleIDs;
    }
}
