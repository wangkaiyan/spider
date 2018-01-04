package com.spider.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.spider.base.AppUser;
import com.spider.util.GsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * HttpSupport
 *
 * @param <E> Model
 * @author chengbo
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public abstract class HttpSupport<E> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    //@Autowired
    //protected DozerUtil dozerUtil;

    /**
     * 获取当前页码
     *
     * @return
     */
    protected final int getPageNo() {
        int defaultPage = 1;
        int page = NumberUtils.toInt(getParamStr("pageNo"), defaultPage);
        return page > 0 ? page : defaultPage;
    }

    /**
     * 获取每页大小
     *
     * @return
     */
    protected final int getPageSize() {
        int defaultPageSize = 5;
        int pageSize = NumberUtils.toInt(getParamStr("pageSize"), defaultPageSize);
        return pageSize > 0 ? pageSize : defaultPageSize;
    }

    /**
     * 获取分页偏移量
     *
     * @return
     */
    protected final int getOffset() {
        return (getPageNo() - 1) * getPageSize();
    }

    /**
     * 获取请求中的参数
     *
     * @return
     */
    protected final Map<String, Object> getParams() {
    	Map<String, Object> m=(Map<String, Object>) request.getAttribute("params");
        return (Map<String, Object>) request.getAttribute("params");
    }

    /**
     * 获取请求中的参数，并转换为当前实体对象 目前仅支持一级
     *
     * @return
     * @throws Exception
     */
    protected final E getParamsEntity() throws Exception {
        return getParamsObj(getEntityClass());
    }

    /**
     * 获取请求中的参数，并转换为指定实体对象
     *
     * @param clazz
     * @param <M>
     * @return
     * @throws Exception
     */
    protected final <M> M getParamsObj(Class<M> clazz) throws Exception {
    	 M m = JSON.parseObject(GsonUtil.toJsonSerializeNulls(getParams()), clazz);
//        M m = GsonUtil.fromJson(GsonUtil.toJsonSerializeNulls(getParams()), clazz);
       
        //SmartValidate.validate(m);
        return m;
        //return (M) MapExUtils.toBean(clazz, getParams());
    }

    /**
     * 根据name，获取请求中的参数：字符串 仅支持一级
     *
     * @param name
     * @return
     */
    protected final String getParamStr(String name) {
        return getParams().containsKey(name) ? String.valueOf(getParams().get(name)) : StringUtils.EMPTY;
    }

    /**
     * 根据name，获取请求中的参数：字符串 仅支持一级
     *
     * @param name
     * @return
     */
    protected final <T> T getParamT(String name) {
        if (getParams().containsKey(name)) {
            return (T) getParams().get(name);
        }
        return null;
    }

    /**
     * 根据name，获取请求中的参数：整型 仅支持一级
     *
     * @param name
     * @return
     */
    protected final int getParamInt(String name) {
        return getParams().containsKey(name) ? Integer.valueOf(String.valueOf(getParams().get(name))) : 0;
    }

    /**
     * 根据name，获取请求中的参数：长整型 仅支持一级
     *
     * @param name
     * @return
     */
    protected final long getParamLong(String name) {
        return getParams().containsKey(name) ? Long.valueOf(String.valueOf(getParams().get(name))) : 0l;
    }

    /**
     * 根据name，获取请求中的参数：Double型 仅支持一级
     *
     * @param name
     * @return
     */
    protected final double getParamDouble(String name) {
        return getParams().containsKey(name) ? Double.valueOf(String.valueOf(getParams().get(name))) : 0.0f;
    }

    /**
     * 根据name，获取请求中的参数：Float型 仅支持一级
     *
     * @param name
     * @return
     */
    protected final double getParamFloat(String name) {
        return getParams().containsKey(name) ? Float.valueOf(String.valueOf(getParams().get(name))) : 0.0f;
    }

    /**
     * 根据name，获取请求中的参数：T 数组 仅支持一级
     *
     * @param name
     * @return
     */
    protected final <T> T[] getParamTArray(String name, Class<T> clazz) {
        if (getParams().containsKey(name)) {

            JSONArray array = (JSONArray) getParams().get(name);
            T[] result = (T[]) Array.newInstance(clazz, array.size());
            for (int i = 0; i < array.size(); i++) {
                result[i] = (T) array.get(i);
            }
            return result;
        }
        return null;
    }

    /**
     * 根据name，获取请求中的参数：Long数组 仅支持一级
     *
     * @param name
     * @return
     */
    protected final long[] getParamLongArray(String name) {
        if (getParams().containsKey(name)) {
            JSONArray array = (JSONArray) getParams().get(name);
            long[] result = new long[array.size()];
            for (int i = 0; i < array.size(); i++) {
                result[i] = array.getLongValue(i);
            }
            return result;
        }
        return null;
    }

    /**
     * 根据name，获取请求中的参数：Integer数组 仅支持一级
     *
     * @param name
     * @return
     */
    protected final int[] getParamIntArray(String name) {
        if (getParams().containsKey(name)) {
            JSONArray array = (JSONArray) getParams().get(name);
            int[] result = new int[array.size()];
            for (int i = 0; i < array.size(); i++) {
                result[i] = array.getIntValue(i);
            }
            return result;
        }
        return null;
    }

    /**
     * 根据name，获取请求中的参数：String数组 仅支持一级
     *
     * @param name
     * @return
     */
    protected final String[] getParamStrArray(String name) {
        if (getParams().containsKey(name)) {
            JSONArray array = (JSONArray) getParams().get(name);
            String[] result = new String[array.size()];
            for (int i = 0; i < array.size(); i++) {
                result[i] = array.getString(i);
            }
            return result;
        }
        return null;
    }

    /**
     * 获取key=entity请求中的参数，默认转换为当前实体对象
     *
     * @return
     */
    protected final E getParamEntity() {
        return getParamEntity("entity");
    }

    /**
     * 根据name, 获取请求中的参数，默认转换为当前实体对象
     *
     * @return
     */
    protected final E getParamEntity(String name) {
        return getParams().containsKey(name) ? GsonUtil.fromJson(String.valueOf(getParams().get(name)), getEntityClass()) : null;
    }

    /**
     * 获取key=entity请求中的参数，默认转换为指定实体对象
     *
     * @return
     */
    protected final <M> M getParamObj(Class<M> clazz) {
        return getParamObj("entity", clazz);
    }

    /**
     * 根据name, 获取请求中的参数，默认转换为指定实体对象
     *
     * @return
     */
    protected final <M> M getParamObj(String name, Class<M> clazz) {
        return getParams().containsKey(name) ? GsonUtil.fromJson(String.valueOf(getParams().get(name)), clazz) : null;
    }

    /**
     * 获取请求APP端设备信息
     *
     * @return
     */
  /*  protected final AppDevice getAppDevice() {
        return (AppDevice) request.getAttribute("appDevice");
    }*/

    /**
     * 获取请求PC端用户信息
     *
     * @return
     */
    protected final AppUser getAppUser() {
       /* com.qding.framework.common.AppUser commonUser = MangerRequestUtil.getCurrentUser();
        AppUser appUser = dozerUtil.transfor(AppUser.class, commonUser);
        ThreadLocalUtils.putUser(appUser);*/
        AppUser appUser = new AppUser();
        return appUser;
    }

    /**
     * 获取用户所属社区信息
     * @return
     */
    protected final List<Long> getAppUserProjects() {
        AppUser appUser = getAppUser();
        List<Long> projectIds = null;
        //若有所有社区权限自动忽略
        if (!appUser.isAllProjectVisable()) {
            projectIds = appUser.getProjectIds();
            if (CollectionUtils.isEmpty(projectIds)) {
                projectIds = Lists.newArrayList();
                projectIds.add(-1l);
            }
        }
        return projectIds;
    }

    /**
     * 获取请求APP端UserToken信息
     *
     * @return
     */
   /* protected final UserToken getUserToken() {
        UserToken userToken = (UserToken) request.getAttribute("userToken");
        AppUser appUser = dozerUtil.transfor(AppUser.class, userToken);
        ThreadLocalUtils.putUser(appUser);
        return userToken;
    }*/

    /**
     * 处理响应信息
     *
     * @param code
     * @param message
     * @return
     */
    protected final void responseMessage(int code, String message) {
        ModelResult modelResult = new ModelResult(code);
        modelResult.setMessage(message);

        request.setAttribute("result", modelResult);
    }

    /**
     * 处理响应信息
     *
     * @param success
     * @return
     */
    protected final void responseMessage(boolean success) {
        ModelResult modelResult = new ModelResult(ModelResult.CODE_200);
        modelResult.setMessage(ModelResult.SUCCESS);

        if (!success) {
            modelResult.setCode(ModelResult.CODE_500);
            modelResult.setMessage(ModelResult.FAIL);
        }

        request.setAttribute("result", modelResult);
    }

    /**
     * 处理响应单个实体
     *
     * @param code
     * @param message
     * @param entity
     * @return
     */
    protected final void responseEntityTest(int code, String message, Object entity) {
        ModelResult modelResult = new ModelResult(code);
        modelResult.setMessage(message);
        modelResult.setEntity(entity);

        request.setAttribute("result", modelResult);
    }

    protected final ModelResult responseEntity(int code, String message, Object entity) {
        ModelResult modelResult = new ModelResult(code);
        modelResult.setMessage(message);
        modelResult.setEntity(entity);

        return modelResult;
    }

    /**
     * 处理响应list
     *
     * @param code
     * @param message
     * @param list
     * @return
     */
    protected final void responseList(int code, String message, List list) {
        ModelResult modelResult = new ModelResult(code);
        modelResult.setMessage(message);
        modelResult.setList(list);

        request.setAttribute("result", modelResult);
    }

    /**
     * 处理响应list
     *
     * @param code
     * @param message
     * @param list
     * @return
     */
    protected final ModelResult responseListTest(int code, String message, List list) {
        ModelResult modelResult = new ModelResult(code);
        modelResult.setMessage(message);
        modelResult.setList(list);

        return modelResult;
    }

    /**
     * 处理响应page
     *
     * @param code
     * @param message
     * @param totalCount
     * @param items
     * @return
     */
    protected final void responsePage(int code, String message, int totalCount, List items) {
        ModelResult modelResult = new ModelResult(code);
        modelResult.setMessage(message);
        modelResult.setResultPage(new ResultPage(totalCount, getPageSize(), getPageNo(), items));

        request.setAttribute("result", modelResult);
    }

    protected final String responseRedirect(String url) {
        return "redirect:" + url;
    }

    /**
     * 获取当前实体类对象
     *
     * @return
     */
    public abstract Class<E> getEntityClass();

    protected final AppUser getOptUser() {

        AppUser appUser = getAppUser();
        //UserToken userToken = getUserToken();
        //
        //if (appUser == null) appUser = dozerUtil.transfor(AppUser.class, userToken);
        return appUser;
    }
}