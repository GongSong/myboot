package com.yh.kuangjia.models.AdminDept;

import java.io.Serializable;

public class AdminDeptEdit implements Serializable {
    /**
     * 部门ID
     */
    private Integer dept_id ;
    /**
     * 部门名称
     */
    private String dept_name ;

    /**
     * 显示排序
     */
    private Integer sort ;

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
