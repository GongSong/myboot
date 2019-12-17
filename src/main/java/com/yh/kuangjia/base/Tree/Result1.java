package com.yh.kuangjia.base.Tree;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.ResultCode;

public class Result1<T> {

    private static final long serialVersionUID = -5372450875750675775L;


    /**
     * 编码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;


    public Result1() {
    }

    public Result1(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.message = msg;
    }


    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }


    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    protected Result1<Object> ok(T data) {
       Result1<Object> obj = new Result1<>();
       return obj;
    }

    public static Result failure() {
        Result result = new Result();
        result.setResultCode(ResultCode.FAILURE);
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }


    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }


    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
        this.message = code.message();;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }
}
