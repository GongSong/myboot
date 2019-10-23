package com.yh.kuangjia.base;

public enum ResultCode {

    //region 返回信息

    SUCCESS(0, "成功"),

    FAILURE(1, "失败"),

    FAILURE_TOKEN(401, "无效的TOKEN"),

    FAILURE_PARAM(101, "参数不合规"),
    //endregion
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),

    NOT_FOUND(404, "未找到该资源!"),

    SERVER_BUSY(503,"服务器正忙，请稍后再试!"),

    NETWORK_AUTHENTICATION_REQUIRED(511, "需要网络认证"),

    FAILURE_CODE500(500, "异常");



    private int count;
    /**
     * 编码
     */
    private Integer code;


    /**
     * 消息
     */
    private String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer code() {
        return this.code;
    }


    public String message() {
        return this.message;
    }


    public static Integer getCode(String name) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.name().equals(name)) {
                return resultCode.code;
            }
        }
        return null;
    }


    public static String getMessage(String name) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.name().equals(name)) {
                return resultCode.message;
            }
        }
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
