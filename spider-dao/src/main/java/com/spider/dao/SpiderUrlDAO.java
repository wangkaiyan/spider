package com.spider.dao;


import com.spider.SpiderUrl;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/27 14:23
 */
public interface SpiderUrlDAO extends BaseDAO<SpiderUrl>{
    //order by rand() limit 1
    public SpiderUrl selectByRand(Map<String, Object> params);;

    public List<SpiderUrl> selectByRandList(Map<String, Object> params);
    //处理上架
}