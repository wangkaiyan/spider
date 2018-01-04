package com.spider.service.impl;

import com.spider.DzInfo;
import com.spider.base.impl.BaseServiceImpl;
import com.spider.dao.DzInfoDAO;
import com.spider.service.DzInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/21 17:51
 */
@Log4j
@Service("dzInfoService")
public class DzInfoServiceImpl extends BaseServiceImpl<DzInfoDAO, DzInfo> implements DzInfoService {

}