package com.spider.util;


import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ConstantsUtil {


	/**
	 * 未知模板类型
	 */
	public static final String UN_KNOWN_TEMPLATE 				= "-1";


	/**
	 * 有效状态
	 */
	public static final int STATUS_VALID 				= 1;
	
	/**
	 * 重置密码
	 */
	public static final String RESET_PWD 				= "123456";
	
	/**
	 * 无效状态
	 */
	public static final int STATUS_INVALID 				= 0;

	/**
	 * 修改DB影响行数 1
	 */
	public static final int AFFECTED_ZERO				=  0;
	/**
	 * 修改DB影响行数 0
	 */
	public static final int AFFECTED_ONE				=  1;

	/**
	 * 数据库列排序 升序
	 */
	public static final String ORDER_ASC					= "ASC";

	/**
	 * 数据库列排序 降序
	 */
	public static final String ORDER_DESC					= "DESC";

	/**
	 * 未发货状态
	 */
	public static final Integer GOOD_STATUS 				= 100;


	/**
	 * 是否为补充价格
	 */
	public static final Integer EXTRA_PRICE 				= 1;

	public class QDResponse {
		/**
		 * 请求成功
		 */
		public static final int CODE_200 = 200;
		
		/**
		 * 请求失败
		 */
		public static final int CODE_500 = 500;

		/**
		 * 成功的描述
		 */
		public static final String MESSAGE_200 = "操作成功";

		/**
		 * 成功的描述
		 */
		public static final String MESSAGE_500 = "操作失败";
		
	}

	public static final String SYSTEM_MESSAGE = "system message";
	
	public static final String LIST = "List";
	/**
	 * utf8编码
	 */
	public static final String CODE_UTF8 				= "UTF-8";
	
	/**
	 * MD5编码
	 */
	public static final String CODE_MD5 				= "MD5";
	
	/**
	 * 竖线分隔符
	 */
	public static final String VERTICALLINE 			= "|";
	
	/**
	 * 逗号分隔符
	 */
	public static final String COMMA 					= ",";
	/**
	 * 竖线分隔符的正则表达式
	 */
	public static final String VERTICALLINE_REGEX 		= "\\|";
	
	/**
	 * 短信服务
	 */
	public class SmsConfig {
		public static final String REGISTER_VERIFY_SMS = "注册验证码 ：{code}】";
		public static final String FORGET_VERIFY_SMS = "";
		public static final String URL = "http://api.taovip.com/v1/sms/send.json";
		public static final String KEY = "781179490632feaf13ab0648d9a81faf"; 
	}

	/**
	 * 既提供键到值的映射，也提供值到键的映射，所以它是双向Map
	 * PASSENGERTYPE.get("key");
	 * PASSENGERTYPE.inverse().get("value");
	 */
	public static final BiMap<Integer, String> PSG_TYPE_REL = HashBiMap.create();
	static {
		PSG_TYPE_REL.put(0, "ADULT"); //成人
	}

	public static final BiMap<String, String> PSG_TYPE_ORG = HashBiMap.create();
	static {
		PSG_TYPE_ORG.put("ADULT", "成人"); //成人
	}


	public static final String CACHE_PRE = "qd_travel_";

	// 缓存10分钟
	public static int CACHE_TRAVEL_TIME_MINUTE = 1000 * 60 * 10;
	// 缓存1小时
	public static int CACHE_TRAVEL_TIME_HOUR = 1000 * 60 * 60;
	// 缓存1天
	public static int CACHE_TRAVEL_TIME_DAY = 1000 * 60 * 60 * 24;

	public static int DEFAULT_EXPIRE_TIME = CACHE_TRAVEL_TIME_MINUTE;


}
