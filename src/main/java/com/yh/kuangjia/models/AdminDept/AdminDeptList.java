package com.yh.kuangjia.models.AdminDept;

import java.io.Serializable;

public class AdminDeptList implements Serializable {

    /**
     * 部门ID
     */
    private Integer dept_id;

    /**
     * 部门名称
     */
    private String dept_name;

    /**
     *
     */
    private String parent_dept_name;

    /**
     *
     */
    private Integer dept_type;

    /**
     * 显示排序
     */
    private Integer sort;

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

    public String getParent_dept_name() {
        return parent_dept_name;
    }

    public void setParent_dept_name(String parent_dept_name) {
        this.parent_dept_name = parent_dept_name;
    }

    public Integer getDept_type() {
        return dept_type;
    }

    public void setDept_type(Integer dept_type) {
        this.dept_type = dept_type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
