package com.spider.base.impl;

import com.spider.dao.BaseDAO;
import com.spider.base.BaseModel;
import com.spider.base.AppUser;
import com.spider.base.BaseService;
import com.spider.exception.ServiceException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j
public class BaseServiceImpl<T extends BaseDAO<E>, E extends BaseModel> implements BaseService<E> {

    @Autowired
    protected T dao;

    @Override
    public List<E> query(Map<String, Object> params) {
        return dao.selectByParams(params);
    }


    @Override
    public E findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<E> queryByIds(List<Long> ids) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        return dao.selectByParams(params);
    }

    @Override
    public List<E> page(Map<String, Object> params, int offset, int size) {
        params.put("offset", offset);
        params.put("size", size);
        return dao.selectByParams(params);
    }

    public List<E> page1_3_0(Map<String, Object> params, int offset, int size) {
        return null;
    }


    @Override
    public int count(Map<String, Object> params) {
        return dao.countByParams(params);
    }

    @Override
    public E findFirst(Map<String, Object> params) {
        return findByParams(params);
    }

    @Override
    public E findByParams(Map<String, Object> params) {
        params.put("offset", 0);
        params.put("size", 2);
        List<E> list = dao.selectByParams(params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int save(E entity) throws ParseException {
        return dao.insert(entity);
    }

    @Override
    public int batchSave(List<E> entityList) {
        return dao.insertBatch(entityList);
    }

    @Override
    public int saveSelective(E entity) {
        return dao.insertSelective(entity);
    }

    @Override
    public int modify(E entity) {
        return dao.updateByPrimaryKey(entity);
    }

    @Override
    public int modifySelective(E entity) {
        return dao.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int batchRemove(Map<String, Object> map) {
        return dao.deleteBatchByPrimaryKey(map);
    }
    
    /**
     * 设置创建人信息
     *
     * @param model   实体
     * @param appUser 操作者
     * @throws ServiceException
     */
    protected void handleCreateInfo(BaseModel model, AppUser appUser) throws ServiceException {

        //model.setCreateAt(System.currentTimeMillis());
        //model.setCreateBy(appUser.getId());
        //model.setCreateName(appUser.getRealname());
    }

    /**
     * 设置修改人信息
     *
     * @param model   实体
     * @param appUser 操作者
     * @throws ServiceException
     */
    protected void handleUpdateInfo(BaseModel model, AppUser appUser) throws ServiceException {

        //model.setUpdateAt(System.currentTimeMillis());
        //model.setUpdateBy(appUser.getId());
        //model.setUpdateName(appUser.getRealname());
    }
}