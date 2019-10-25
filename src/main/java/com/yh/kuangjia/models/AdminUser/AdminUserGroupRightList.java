package com.yh.kuangjia.models.AdminUser;

public class AdminUserGroupRightList {
    private String path;
    private String icon;
    private String name;
    private String title;
    private String component;

    public AdminUserGroupRightList() {
    }

    public AdminUserGroupRightList(String path, String icon, String name, String title, String component) {
        this.path = path;
        this.icon = icon;
        this.name = name;
        this.title = title;
        this.component = component;
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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
