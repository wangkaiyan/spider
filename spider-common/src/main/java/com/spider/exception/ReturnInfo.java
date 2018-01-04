package com.spider.exception;

import java.io.Serializable;

/**
 * Created by qd on 2016/1/12.
 */
public class ReturnInfo implements Serializable {
    private static final long serialVersionUID = 6333694033173649027L;
    private int code;
    private String message;

    public ReturnInfo() {
    }

    public ReturnInfo(HttpStatus status) {
        this.code = status.getStatusCode();
        this.message = status.toString();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "ReturnInfo [code=" + this.code + ", message=" + this.message + "]";
    }
}