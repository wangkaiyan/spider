package com.spider.job;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qd on 2016/4/6.
 */
@Service("jobT")
@Log4j
public class JobT  implements Runnable {

    @Autowired
    private GainPageUrlJob gainPageUrlJob;

        @Override
        public void run() {
            log.info("gain-sub-url开始:");
            gainPageUrlJob.startRun();
            log.info("gain-sub-url结束:" );
        }

}
