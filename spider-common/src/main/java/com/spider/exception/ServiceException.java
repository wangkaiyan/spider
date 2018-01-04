package com.spider.exception;

/**
 * Created by qd on 2016/1/12.
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = -3433662187706932470L;
    private ReturnInfo returnInfo;

    public ServiceException(int code, String message) {
        this.returnInfo = new ReturnInfo();
        this.returnInfo.setCode(code);
        this.returnInfo.setMessage(message);
    }

    public ServiceException(HttpStatus status) {
        this.returnInfo = new ReturnInfo(status);
    }

    public ReturnInfo getReturnInfo() {
        return this.returnInfo;
    }
}
