package com.spider.util;

import com.google.common.collect.Maps;
import com.spider.SpiderUrlPage;
import com.spider.service.SpiderUrlPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Map;

/**
 * Created by qd on 2016/3/25.
 */
@Service("handlePageUrl")
public class HandlePageUrl {

    @Autowired
    private SpiderUrlPageService spiderUrlPageService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void handleUrl(SpiderUrlPage hook ,String nextUrl) throws ParseException {
        // 保证同一条记录只有一个线程处理
        synchronized (hook.getId() + "".intern()) {
            Map<String, Object> map = Maps.newConcurrentMap();
            map.put("url", nextUrl);
            SpiderUrlPage urlPage = new SpiderUrlPage();
            urlPage.setUrl(nextUrl);

                //  不存在 and 钩子url状态为未处理
                SpiderUrlPage u = spiderUrlPageService.findByParams(map);
                hook = spiderUrlPageService.findById(hook.getId());
                if (u == null && hook.getStatusRecursion() == 0) {
                    spiderUrlPageService.save(urlPage);
                }
                hook.setStatusRecursion(1);
                spiderUrlPageService.modify(hook);

        }

    }

}
