package com.yh.kuangjia.models.AdminUser;

import com.yh.kuangjia.base.PageInfo;

import java.io.Serializable;

public class AdminUserFilter extends PageInfo implements Serializable {

    private Integer dept_id;

    private Integer role_id;

    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }


}
