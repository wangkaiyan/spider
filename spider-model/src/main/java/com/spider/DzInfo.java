package com.spider;


import com.spider.base.BaseModel;
import com.spider.util.MetalTypeUtil;
import lombok.Data;

/**
 *
 * @author wangky
 * @version 1.0
 * @since 2016/03/21 17:51
 */
@Data
public class DzInfo extends BaseModel {

        private static final long serialVersionUID = 5409185459234711691L;
        private int id;
        /**
         *
         */
        private int catid;
        /**
         *
         */
        private int typeid;
        /**
         *
         */
        private String title;
        /**
         *
         */
        private String style;
        /**
         *
         */
        private String thumb;
        /**
         *
         */
        private String keywords;
        /**
         *
         */
        private String description;
        /**
         *
         */
        private boolean posids;
        /**
         *
         */
        private String url;
        /**
         *
         */
        private int listorder;
        /**
         *
         */
        private int status;
        /**
         *
         */
        private boolean sysadd;
        /**
         *
         */
        private boolean islink;
        /**
         *
         */
        private String username;
        /**
         *
         */
        private int inputtime;
        /**
         *
         */
        private int updatetime;
        /**
         *
         */
        private String type1;
        /**
         *
         */
        private String type2;

        public DzInfo(){

        }

        public DzInfo(String title,String keywords,String description,String url,String type1,String type2,String type3,String type4){

                this.setCatid(Integer.parseInt(type4));
                this.setTypeid(MetalTypeUtil.METAL_TYPE.get(type1));  // ？
                this.setTitle(title);    // 标题
                this.setStyle("");
                this.setThumb("");
                this.setKeywords(keywords);   //关键字
                this.setDescription(description);  // 摘要
                this.setPosids(false);
                this.setUrl(url);
                this.setListorder(0);  // 排序 默认是0
                this.setStatus(99);  //  99 固定
                this.setSysadd(true);
                this.setIslink(true);
                this.setUsername("sys");
                int i = (int) (System.currentTimeMillis() / 1000);
                this.setInputtime((int) (System.currentTimeMillis() / 1000));
                this.setUpdatetime((int) (System.currentTimeMillis() / 1000));
                this.setType1(type1); //保留字段一 （如果是市场分析就包括，日报周报月报，如果是价格汇总就不分，如果是价格行情就可能是铜精矿，铜管，铜棒之类的）
                this.setType2(""); // 保留字段
        }



}