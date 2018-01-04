package com.spider.job;

import com.spider.exception.ServiceException;
import com.spider.job.param.GetUrlPar;
import com.spider.job.param.JobPar;
import com.spider.job.param.ScheduleResponse;

import java.text.ParseException;
import java.util.List;

/**
 * Created by qd on 2016/3/21.
 */
public interface BaseJob {

    public List<String> getUrl(GetUrlPar par) throws ServiceException, ParseException, InterruptedException;

    public List<String> analyzePage(GetUrlPar par) throws ServiceException;


    public ScheduleResponse doJob(JobPar jobPar) throws ServiceException, InterruptedException;
}
