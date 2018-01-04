package com.spider.job.param;

import lombok.Data;

/**
 * Created by qd on 2016/1/12.
 */
@Data
public class LinkVo {

    public LinkVo(){}

    public LinkVo(String url, String category, String channel){
        this.setCategory(category);
        this.setUrl(url);
        this.setChannel(channel);
    }

    private String url;
    private String category;
    private String channel;

}
