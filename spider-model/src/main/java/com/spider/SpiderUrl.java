package com.spider;


import com.spider.base.BaseModel;
import lombok.Data;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/27 14:23
 */
@Data
public class SpiderUrl extends BaseModel {

	private static final long serialVersionUID = 5409185459234711691L;
	private String id;

	/**
	 * 
	 */
	private String titile;
	/**
	 * 
	 */
	private String url;
	private String showUrl;
	/**
	 * 
	 */
	private String hostId;
	/**
	 * 
	 */
	private int status;
	/**
	 * 
	 */
	private String type1;
	/**
	 * 
	 */
	private String type2;
	private String type3;
	/**
	 *
	 */
	private String type4;

	private long createAt;
}