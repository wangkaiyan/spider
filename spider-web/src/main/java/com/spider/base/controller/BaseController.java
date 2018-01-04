package com.spider.base.controller;

import com.spider.base.BaseModel;
import com.spider.base.BaseService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log4j
public abstract class BaseController<T extends BaseService<E>, E extends BaseModel> extends HttpSupport<E> {

    @Autowired
    protected T service;

    @RequestMapping(value = "list2")
    @ResponseBody
    public void list2() throws Exception  {
        List<E> items = service.query(getParams());
        responseList(ModelResult.CODE_200, ModelResult.SUCCESS, items);
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public ModelResult list() throws Exception  {
        List<E> items = service.query(getParams());
        ModelResult modelResult=responseListTest(ModelResult.CODE_200, ModelResult.SUCCESS, items);
        return  modelResult;
    }

    @RequestMapping(value = "page")
    @ResponseBody
    public void page() throws Exception  {
        int totalCount = service.count(getParams());
        List<E> items = service.page(getParams(), getOffset(), getPageSize());
        responsePage(ModelResult.CODE_200, ModelResult.SUCCESS, totalCount, items);
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public void save() throws Exception {
        int result = service.save(handleUpdateInfo(handleCreateInfo(getParamsEntity())));
        responseMessage(result == 1 ? ModelResult.CODE_200 : ModelResult.CODE_500, result == 1 ? ModelResult.SUCCESS : ModelResult.FAIL);
    }

    @RequestMapping(value = "detail")
    @ResponseBody
    public ModelResult detail() throws Exception {
        E entity = service.findById(getParamStr("id"));
        ModelResult modelResult=responseEntity(ModelResult.CODE_200, ModelResult.SUCCESS, entity);
        return  modelResult;
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public void update() throws Exception {
        int result = service.modify(handleUpdateInfo(getParamsEntity()));
        responseMessage(result == 1 ? ModelResult.CODE_200 : ModelResult.CODE_500, result == 1 ? ModelResult.SUCCESS : ModelResult.FAIL);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public void delete() {
        int result = service.remove(getParamStr("id"));
        responseMessage(result == 1 ? ModelResult.CODE_200 : ModelResult.CODE_500, result == 1 ? ModelResult.SUCCESS : ModelResult.FAIL);
    }
    





    /**
     * 处理创建信息
     * @param entity
     * @return
     */
    protected E handleCreateInfo(E entity) {
        /*entity.setCreateAt(System.currentTimeMillis());

        // APP UserToken
        if (getUserToken() != null) {
            if (StringUtils.isBlank(entity.getCreateBy())) {
                entity.setCreateBy(getUserToken().getMemberId());
            }

            if (StringUtils.isBlank(entity.getCreateName())) {
                entity.setCreateName(getUserToken().getName());
            }
        } else if (getAppUser() != null) {
            // PC User
            if (StringUtils.isBlank(entity.getCreateBy())) {
                entity.setCreateBy(String.valueOf(getAppUser().getId()));
            }

            if (StringUtils.isBlank(entity.getCreateName())) {
                entity.setCreateName(getAppUser().getUsername());
            }
        }*/
        return entity;
    }

    /**
     * 处理更新信息
     * @param entity
     * @return
     */
    protected E handleUpdateInfo(E entity) {
       /* entity.setUpdateAt(System.currentTimeMillis());

        // APP UserToken
        if (getUserToken() != null) {
            if (StringUtils.isBlank(entity.getUpdateBy())) {
                entity.setUpdateBy(getUserToken().getMemberId());
            }

            if (StringUtils.isBlank(entity.getUpdateName())) {
                entity.setUpdateName(getUserToken().getName());
            }
        } else if (getAppUser() != null) {
            // PC User
            if (StringUtils.isBlank(entity.getUpdateBy())) {
                entity.setUpdateBy(String.valueOf(getAppUser().getId()));
            }

            if (StringUtils.isBlank(entity.getUpdateName())) {
                entity.setUpdateName(getAppUser().getUsername());
            }
        }*/
        return entity;
    }
}
