package com.yh.kuangjia.models.AdminRight;

import java.io.Serializable;
import java.util.List;

public class AdminRightGroupList<T> implements Serializable {

    private String title;

    private Integer group_id;

    private List<T> children;

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

}
