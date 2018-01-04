package com.spider.base.model;


import java.util.List;

/**
 * @author heyunxia.
 * @Description
 * @time 2015/6/21 14:23
 */
public class ListBaseResponse<T> extends BaseResponse {

    /**
     * 总的记录数
     */
    private int totalCount;

    /**
     * 类型
     */
    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
