package com.spider.job.param;

import lombok.Data;
import org.jsoup.nodes.Element;

/**
 * Created by qd on 2016/3/25.
 */
@Data
public class ParseVo {

    private Element element;

    private String title;
}
