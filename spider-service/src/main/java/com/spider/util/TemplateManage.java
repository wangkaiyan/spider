package com.spider.util;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2016/3/25.
 */
@Log4j
@Data
public class TemplateManage {
    private    Map<String, List<Condition>> templateType;
    private   Map<String, Map<String,String>> template;


   /* public void setTemplate(Map<String, Map<String, String>> template) {
        this.template = template;
    }



    public void setTemplateType(Map<String, String> templateType) {
        this.templateType = templateType;
    }*/

    // 猜测模板类型  -1
    public  String guessTemplateType(Document doc){

        for (Map.Entry<String, List<Condition>> entry : templateType.entrySet()) {

            List<Condition>  conditions =  entry.getValue();
            int i = 0;
            for(Condition condition:conditions){
                if(doc.select(condition.getRule()).size()>0 && condition.getMatch().booleanValue() == true){
                    i++;
                }else if (doc.select(condition.getRule()).size()==0 && condition.getMatch().booleanValue() == false){
                    i++;
                }else{
                    break;
                }
            }
            if(conditions.size()  == i){
                return  entry.getKey();
            }

        }
        return ConstantsUtil.UN_KNOWN_TEMPLATE;
    }
}
