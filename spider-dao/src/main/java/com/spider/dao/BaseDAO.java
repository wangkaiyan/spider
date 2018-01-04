package com.spider.dao;


import com.spider.base.BaseModel;

import java.util.List;
import java.util.Map;


public interface BaseDAO<E extends BaseModel> {

	/**
	 * 插入
	 */
	public int insert(E entity);

	/**
	 * 批量插入
	 */
	public int insertBatch(List<E> entityList);

	/**
	 * 选择插入
	 */
	public int insertSelective(E entity);

	/**
	 * 按主键ID查询
	 */
	public E selectByPrimaryKey(String id);

	/**
	 * 按主键ID选择更新
	 */
	public int updateByPrimaryKeySelective(E entity);

	/**
	 * 按主键ID更新
	 */
	public int updateByPrimaryKey(E entity);

	/**
	 * 按参数查询
	 */
	public List<E> selectByParams(Map<String, Object> params);

	/**
	 * 按参数查询数量
	 */
    public int countByParams(Map<String, Object> params);

	/**
	 * 按主键ID删除
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 按主键ID批量删除
	 */
	public int deleteBatchByPrimaryKey(Map<String, Object> map);
}