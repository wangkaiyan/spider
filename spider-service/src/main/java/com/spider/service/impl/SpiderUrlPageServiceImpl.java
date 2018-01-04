package com.spider.service.impl;


import com.spider.SpiderUrlPage;
import com.spider.base.impl.BaseServiceImpl;
import com.spider.dao.SpiderUrlPageDAO;
import com.spider.service.SpiderUrlPageService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/27 14:23
 */
@Log4j
@Service("spiderUrlPageService")
public class SpiderUrlPageServiceImpl extends BaseServiceImpl<SpiderUrlPageDAO, SpiderUrlPage> implements SpiderUrlPageService {

}