package com.spider.base.model;



import java.util.List;

/**
 * @author heyunxia.
 * @Description
 * @time 2015/6/21 14:21
 */
public class PageBaseResponse<T> extends BaseResponse {

    private List<T> list;

    private Integer totalCount;//总记录录

    private Integer pageNo;//第几页

    private Integer pageSize;//总页面

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
