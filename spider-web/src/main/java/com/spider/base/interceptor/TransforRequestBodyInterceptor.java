package com.spider.base.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.spider.util.GsonUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

@Log4j
public class TransforRequestBodyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return transferRequest(request, handler);
    }

    private boolean transferRequest(HttpServletRequest request, Object handler) throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        String body = request.getParameter("body");
        if(StringUtils.isNotEmpty(body)) {
	    	JSONObject jsonObj = GsonUtil.stringToJSONOBject(body);
	    	Set<String> keySet = jsonObj.keySet();
	         for (String key : keySet) {
	             params.put(key, jsonObj.get(key));
	         }
	    	request.setAttribute("params", params);
        }
        return true;
    }
}