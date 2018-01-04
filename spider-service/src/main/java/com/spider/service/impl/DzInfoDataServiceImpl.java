package com.spider.service.impl;

import com.spider.DzInfoData;
import com.spider.base.impl.BaseServiceImpl;
import com.spider.dao.DzInfoDataDAO;
import com.spider.service.DzInfoDataService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/21 17:51
 */
@Log4j
@Service("dzInfoDataService")
public class DzInfoDataServiceImpl extends BaseServiceImpl<DzInfoDataDAO, DzInfoData> implements DzInfoDataService {

}