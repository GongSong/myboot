package com.yh.kuangjia.models.AdminUser;

import java.util.List;

public class AdminUserGroupList {
    private String path;
    private String icon;
    private String name;
    private String title;
    private List<AdminUserGroupRightList> children;

    public AdminUserGroupList(String path, String icon, String name, String title, List<AdminUserGroupRightList> children) {
        this.path = path;
        this.icon = icon;
        this.name = name;
        this.title = title;
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AdminUserGroupRightList> getChildren() {
        return children;
    }

    public void setChildren(List<AdminUserGroupRightList> children) {
        this.children = children;
    }
}
