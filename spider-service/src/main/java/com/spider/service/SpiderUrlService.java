package com.spider.service;


import com.spider.SpiderUrl;
import com.spider.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/27 14:23
 */
public interface SpiderUrlService extends BaseService<SpiderUrl> {

    public SpiderUrl selectByRand(Map<String, Object> params);;

    public List<SpiderUrl> selectByRandList(Map<String, Object> params);



}
