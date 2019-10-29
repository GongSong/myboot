package com.yh.kuangjia.models.Enums;

public enum LogTypeEnum {
    /**
     * 部门
     */
    Dept(1, "部门"),
    /**
     * 系统角色
     */
    Role(2, "系统角色"),
    /**
     * 系统权限
     */
    Right(3, "系统权限"),
    /**
     * 权限组
     */
    RightGroup(4, "权限组"),
    /**
     * 用户
     */
    User(5, "用户"),
    /**
     * 系统配置
     */
    Config(6, "系统配置"),
    /**
     * 数据字典
     */
    Dict(7, "数据字典"),
    /**
     * 账户
     */
    Wealth(8, "账户"),
    /**
     * 文章
     */
    Article(9, "文章"),
    /**
     * 地区
     */
    Region(10, "地区"),
    /**
     * 地区
     */
    Banner(11, "轮播"),
    /**
     * 地区
     */
    Rules(12, "分销规则")    ,
    /**
     * C端客户
     */
    C_User(13, "C端客户"),
    /**
     * 订单
     */
    Order(14, "订单");
    private int code;
    private String name;

    private LogTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static LogTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 1:
                return Dept;
            case 2:
                return Role;
            case 3:
                return Right;
            case 4:
                return RightGroup;
            case 5:
                return User;
            case 6:
                return Config;
            case 7:
                return Dict;
            case 8:
                return Wealth;
           case 9:
                return Article;
            case 10:
                return Region;
            case 11:
                return Banner;
            case 12:
                return Rules;
            case 13:
                return C_User;
            case 14:
                return Order;

            default:
                return null;
        }
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }


}
