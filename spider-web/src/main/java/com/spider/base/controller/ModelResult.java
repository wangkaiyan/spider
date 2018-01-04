package com.spider.base.controller;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * ModelResult
 * @author taomk
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
public final class ModelResult {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    public static final int CODE_200 = 200;
    public static final int CODE_500 = 500;

    public static final int CODE_401 = 401;
    public static final int CODE_406 = 406;

    private int code;

    private final Map<String,Object> data = Maps.newHashMap();

    public ModelResult(int code) {
        this.code = code;
    }

    public ModelResult setResultPage(ResultPage resultPage){
        data.put("total",resultPage.getTotalCount());
        data.put("page",resultPage.getPageCount());
        data.put("size",resultPage.getPageSize());
        data.put("list",resultPage.getItems());

        data.put("totalCount",resultPage.getTotalCount());
        data.put("recordCount",resultPage.getItems().size());
        return  this;
    }

    public void setMessage(String message){
        data.put("message", message);
    }

    public void setList(List list){
        data.put("list", list);
    }

    public void setEntity(Object entity){
        data.put("entity", entity);
    }
}
