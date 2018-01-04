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
public class SpiderUrlPage extends BaseModel {

	private static final long serialVersionUID = 5409185459234711691L;
	private String id;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private String urlPostion;
	/**
	 * 
	 */
	private Integer statusRecursion;
	/**
	 * 
	 */
	private String hostId;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private String typeA;
	/**
	 * 
	 */
	private String typeB;
	private String typeC;
	/**
	 *
	 */
	private String typeD;
	/**
	 * 
	 */
	private long runNumber;
	/**
	 * 
	 */
	private Long runNumberMax;


	private long createAt;
}