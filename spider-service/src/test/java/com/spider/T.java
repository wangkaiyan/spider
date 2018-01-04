package com.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

/**
 * Created by qd on 2016/3/22.
 */
public class T {

    public static void main(String[] args) {
        //String html = "<div id=\"sss\"><script></script><div id=\"sss\">xxx</div><span>ss</span><img src=\"http://a.mysteelcdn.com/common/3.0/logo12560.png\" width=\"125\" height=\"60\" border=\"0\" /><a href=\"http://member1.mysteel.com/\" target=\"_blank\" >sss</a><div id=\"specDiv\" class=\"open_select_box\">xxx</div></div>";
        String html = " <div> <p><strong>【趋势分析与建议】</strong></p> <br>（Mysteel.com钢材部编辑，未经许可，请勿转载） <div> ";
        //Whitelist whitelist = Whitelist.relaxed();//创建relaxed的过滤器
        Whitelist whitelist =Whitelist.relaxed();
        Document doc = Jsoup.parseBodyFragment(html);
        whitelist.addTags("div");//添加relaxed里面没有的标签
        whitelist.removeTags("br");
        whitelist.addAttributes(":all",  "id", "name","href","target","border");
        //whitelist.addAttributes("a", "href", "class");
        Cleaner cleaner = new Cleaner(whitelist);
        doc = cleaner.clean(doc);//按照过滤器清除
//doc.outputSettings().prettyPrint(false);//是否格式化
        Element body = doc.body();
        System.out.println(body);//完整的html字符串
    }
}
