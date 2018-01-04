package com.spider.service.impl;


import com.spider.SpiderUrl;
import com.spider.base.impl.BaseServiceImpl;
import com.spider.dao.SpiderUrlDAO;
import com.spider.service.SpiderUrlService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/27 14:23
 */
@Log4j
@Service("spiderUrlService")
public class SpiderUrlServiceImpl extends BaseServiceImpl<SpiderUrlDAO, SpiderUrl> implements SpiderUrlService {

    @Override
    public SpiderUrl selectByRand(Map<String, Object> params) {
        return  dao.selectByRand(params);
    }

    @Override
    public List<SpiderUrl> selectByRandList(Map<String, Object> params) {
        return dao.selectByRandList(params);
    }
}