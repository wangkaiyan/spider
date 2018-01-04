package com.spider.msg;


import com.google.common.collect.Maps;
import com.spider.Msg;
import com.spider.base.controller.BaseController;
import com.spider.base.controller.DataGrid;
import com.spider.base.controller.ModelResult;
import com.spider.msg.service.MsgService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Log4j
@Controller
@RequestMapping("/msg")
public class MsgController extends BaseController<MsgService, Msg> {

    @Override
    public Class<Msg> getEntityClass() {
        return Msg.class;
    }

	@RequestMapping(value = "page2")
	@ResponseBody
	public DataGrid page2() throws Exception  {
		Map<String,Object> map = Maps.newConcurrentMap();
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		int totalCount = service.count(map);
		List<Msg> items = service.page(map, Integer.parseInt(pageNo)*Integer.parseInt(pageSize), Integer.parseInt(pageSize));
		//(int total, List<?> header, List<?> rows, List<?> footer) {
		DataGrid dataGrid =new DataGrid(totalCount,null,items,null);
		return  dataGrid;
		//request.setAttribute("result", dataGrid);
		//responsePage(ModelResult.CODE_200, ModelResult.SUCCESS, totalCount, items);
	}
    
    @RequestMapping(value = "save")
    @ResponseBody
    @Override
    public void save() throws Exception  {
    	// 一个方案只能有一个优惠券
    	Map<String,Object> params=getParams();
    	
    	Map<String,Object> map= Maps.newConcurrentMap();
    	map.put("planId", params.get("planId"));
    	map.put("meijuProviderId",  params.get("meijuProviderId"));
    	map.put("isDel",  0);
    	List<Msg>  Msgs=service.query(map);
    	if(Msgs==null || Msgs.size()==0){
    		int result = service.save(handleUpdateInfo(handleCreateInfo(getParamsEntity())));
    		responseMessage(result == 1 ? ModelResult.CODE_200 : ModelResult.CODE_500, result == 1 ? ModelResult.SUCCESS : ModelResult.FAIL);
    	}else{
    		responseMessage(ModelResult.CODE_500, "此方案已经存在优惠券");
    	}
    }
}