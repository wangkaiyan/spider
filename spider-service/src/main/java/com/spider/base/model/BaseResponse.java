package com.spider.base.model;

import com.spider.exception.ReturnInfo;

import java.io.Serializable;

/**
 * Created by qd on 2016/1/12.
 */
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = -1653496174565882159L;
    private ReturnInfo returnInfo;

    public BaseResponse() {
    }

    public ReturnInfo getReturnInfo() {
        return this.returnInfo;
    }

    public void setReturnInfo(ReturnInfo returnInfo) {
        this.returnInfo = returnInfo;
    }

    public String toString() {
        return "BaseResponse [returnInfo=" + this.returnInfo + "]";
    }
}
