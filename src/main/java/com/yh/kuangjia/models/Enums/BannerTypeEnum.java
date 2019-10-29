package com.yh.kuangjia.models.Enums;

/**
 * 轮播类别
 */
public enum BannerTypeEnum {
    /**
     * 主页轮播
     */
    主页轮播(1, "主页轮播"),
    /**
     * 主页广告
     */
    主页广告(2, "主页广告");

    private int code;
    private String name;

    private BannerTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BannerTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 1:
                return 主页轮播;
            case 2:
                return 主页广告;
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
