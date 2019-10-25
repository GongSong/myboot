package com.yh.kuangjia.models.Enums;

public enum AdminDeptTypeEnum {
    总部(0, "总部"),
    管理员(1, "管理员"),
    销售(2, "销售"),
    行政(3, "行政"),
    财务(4, "财务"),
    研发(5, "研发");

    private int code;
    private String name;

    private AdminDeptTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public static AdminDeptTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0:
                return 总部;
            case 1:
                return 管理员;
            case 2:
                return 销售;
            case 3:
                return 行政;
            case 4:
                return 财务;
            case 5:
                return 研发;
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
