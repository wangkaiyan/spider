package com.spider.service.impl;

import com.spider.SpiderHost;
import com.spider.base.impl.BaseServiceImpl;
import com.spider.dao.SpiderHostDAO;
import com.spider.service.SpiderHostService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/27 14:23
 */
@Log4j
@Service("spiderHostService")
public class SpiderHostServiceImpl extends BaseServiceImpl<SpiderHostDAO, SpiderHost> implements SpiderHostService {

}