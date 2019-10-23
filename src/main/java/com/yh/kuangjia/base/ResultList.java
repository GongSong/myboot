package com.yh.kuangjia.base;

import java.io.Serializable;

/**
 * 返回数据封装类
 */
public class ResultList implements Serializable {
    private static final long serialVersionUID = -5372450875750675775L;
    /**
     * 数量
     */
    private Long count;
    /**
     * 编码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;


    public ResultList() {
    }

    public ResultList(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultList success(Object data, Long count) {
        ResultList result = new ResultList();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        result.setCount(count);
        return result;
    }

    public static ResultList success(Object data) {
        ResultList result = new ResultList();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ResultList failure() {
        ResultList result = new ResultList();
        result.setResultCode(ResultCode.FAILURE);
        return result;
    }

    public static ResultList failure(ResultCode resultCode) {
        ResultList result = new ResultList();
        result.setResultCode(resultCode);
        return result;
    }


    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
