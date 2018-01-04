package com.spider;


import com.spider.base.BaseModel;
import lombok.Data;

/**
 * 
 * @author wangky
 * @version 1.0
 * @since 2016/03/21 17:51
 */
@Data
public class DzInfoData  extends BaseModel {

	private static final long serialVersionUID = 5409185459234711691L;
	private int id;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private int readpoint;
	/**
	 * 
	 */
	private String groupidsView;
	/**
	 * 
	 */
	private boolean paginationtype;
	/**
	 * 
	 */
	private int maxcharperpage;
	/**
	 * 
	 */
	private String template;
	/**
	 * 
	 */
	private boolean paytype;
	/**
	 * 
	 */
	private boolean allowComment;
	/**
	 * 
	 */
	private String relation;
	/**
	 * 
	 */
	private String metaltype;
	/**
	 * 
	 */
	private String templateUrl;
	/**
	 * 
	 */
	private String msgtype;


	public DzInfoData(){

	}

	public DzInfoData(int id,String content,String metaltype,String msgtype,String templateType){
		this.setId(id);
		this.setContent(content);   // ����
		this.setGroupidsView(""); //?
		this.setTemplate(templateType);
		this.setTemplateUrl(templateType);
		this.setRelation("");
		this.setMetaltype(metaltype);  // type1 �������  metaltype    �������ĸ����ࣨ�ֲģ�ͭ����������������ɫ��
		this.setMsgtype(msgtype);   // type2  msgtype ��Ѷ����    ��Ϊ���ࣨ����ֲ����飬�г����������ͳ�ƣ��۸���ܣ�

	}
}