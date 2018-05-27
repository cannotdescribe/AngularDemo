package com.insigma.sr.bean;


public class ResultEndBean {
    private Integer code;
    private String msg;
    private boolean success;

    private Object data;

    public ResultEndBean(String msg, boolean success) {
        this(success);
        this.msg = msg;
    }

    public ResultEndBean(boolean success) {
        this.success = success;
        if(success){
            this.code = 0;
        }
    }

    public ResultEndBean(Object data) {
        this();
        setData(data);
    }

    public ResultEndBean() {
        this(true);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
