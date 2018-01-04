package com.spider.base.controller;

import java.util.List;

/**
 * Created by WIN10 on 2016/4/9.
 */
public class DataGrid {
    private int total;
    private List<?> header;
    private List<?> rows;
    private List<?> footer;

    public DataGrid(int total, List<?> header, List<?> rows, List<?> footer) {
        this.total = total;
        this.header = header;
        this.rows = rows;
        this.footer = footer;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getHeader() {
        return header;
    }

    public void setHeader(List<?> header) {
        this.header = header;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public List<?> getFooter() {
        return footer;
    }

    public void setFooter(List<?> footer) {
        this.footer = footer;
    }
}
