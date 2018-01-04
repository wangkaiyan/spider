package com.spider.msg.service.impl;

import com.spider.Msg;
import com.spider.dao.MsgDao;
import com.spider.base.impl.BaseServiceImpl;
import com.spider.msg.service.MsgService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * Created by qd on 2016/1/12.
 */

@Log4j
@Service("msgService")
public class MsgServiceImpl extends BaseServiceImpl<MsgDao, Msg> implements MsgService {

}
