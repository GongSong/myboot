package com.yh.kuangjia.models.AdminUser;

public class AdminUserRightList {
    private String path;
    private String icon;
    private String name;
    private String title;
    private String component;
    private Integer right_id;
    private Integer group_id;

    public AdminUserRightList() {
    }

    public AdminUserRightList(String path, String icon, String name, String title, String component, Integer right_id, Integer group_id) {
        this.path = path;
        this.icon = icon;
        this.name = name;
        this.title = title;
        this.component = component;
        this.right_id = right_id;
        this.group_id = group_id;
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

    public Integer getRight_id() {
        return right_id;
    }

    public void setRight_id(Integer right_id) {
        this.right_id = right_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

}
