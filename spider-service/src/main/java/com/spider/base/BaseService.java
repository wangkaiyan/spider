package com.spider.base;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * BaseService
 * @param <E>
 *     Model
 *
 * @author taomk
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public interface BaseService<E extends BaseModel> {

	/**
	 * 查询
	 */
	public List<E> query(Map<String, Object> params);

	/**
     * ID查询
     */
	public E findById(String id);

	/**
	 * ID批量查询
	 */
	public List<E> queryByIds(List<Long> ids);

	/**
	 * 参数分页查询
	 */
	public List<E> page(Map<String, Object> params, int offset, int size);

	/**
	 * 参数分页查询
	 * @Version 1.3.0
	 */
	public List<E> page1_3_0(Map<String, Object> params, int offset, int size);

	/**
	 * 参数查询总数
	 */
	public int count(Map<String, Object> params);

	/**
	 * First查询
	 */
	public E findFirst(Map<String, Object> params);

	/**
	 * 参数查询
	 */
	E findByParams(Map<String, Object> params);

	/**
	 * 保存
	 */
    public int save(E entity) throws ParseException;

	/**
	 * 批量保存
	 */
	public int batchSave(List<E> entityList);

    /**
     * 选择保存
     */
    public int saveSelective(E entity);

	/**
	 * 修改
	 */
	public int modify(E entity);

    /**
     * 选择修改
     */
    public int modifySelective(E entity);

	/**
	 * 删除
	 */
    public int remove(String id);

	/**
	 * 批量删除
	 */
	public int batchRemove(Map<String, Object> map);
}
