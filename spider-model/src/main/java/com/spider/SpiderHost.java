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
public class SpiderHost extends BaseModel {

	private static final long serialVersionUID = 5409185459234711691L;
	private String id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String homeUrl;
	/**
	 * 
	 */
	private String loginUrl;
	private String verifyUrl;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String cookieStr;
	/**
	 * 
	 */
	private int isEffect;

	private int isDel;
}