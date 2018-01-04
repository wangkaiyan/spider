package com.spider.util;

import org.jsoup.nodes.Document;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

/**
 * Created by qd on 2016/3/25.
 */
public class CleanerUtil {

    public static Cleaner cleaner  = new Cleaner(Whitelist.relaxed().addTags("div").addAttributes(":all",  "id", "name","href","target","border"));

    public static Document baseClean(Document doc){
        //Whitelist whitelist = Whitelist.relaxed();//创建relaxed的过滤器
        //
        //
        //Cleaner cleaner = new Cleaner(whitelist);
        //whitelist.addTags("div");//添加relaxed里面没有的标签
        ////whitelist.removeTags("script");
        //whitelist.addAttributes(":all",  "id", "name","href","target","border");
        return cleaner.clean(doc);//按照过滤器清除
    }

}
