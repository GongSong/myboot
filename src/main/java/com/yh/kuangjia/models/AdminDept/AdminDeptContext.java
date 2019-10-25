package com.yh.kuangjia.models.AdminDept;

import java.io.Serializable;

public class AdminDeptContext implements Serializable {
    private Integer id;

    private String label;

    private Object children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }
}
