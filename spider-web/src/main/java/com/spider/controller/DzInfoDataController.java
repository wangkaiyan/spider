package com.spider.controller;


import com.google.common.collect.Maps;
import com.spider.DzInfoData;
import com.spider.base.controller.BaseController;
import com.spider.base.controller.DataGrid;
import com.spider.service.DzInfoDataService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Log4j
@Controller
@RequestMapping("/dzinfodata")
public class DzInfoDataController extends BaseController<DzInfoDataService, DzInfoData> {

    @Override
    public Class<DzInfoData> getEntityClass() {
        return DzInfoData.class;
    }

	@RequestMapping(value = "page2")
	@ResponseBody
	public DataGrid page2() throws Exception  {
		Map<String,Object> map = Maps.newConcurrentMap();
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		int totalCount = service.count(map);
		List<DzInfoData> items = service.page(map, Integer.parseInt(pageNo)*Integer.parseInt(pageSize), Integer.parseInt(pageSize));
		//(int total, List<?> header, List<?> rows, List<?> footer) {
		DataGrid dataGrid =new DataGrid(totalCount,null,items,null);
		return  dataGrid;
		//request.setAttribute("result", dataGrid);
		//responsePage(ModelResult.CODE_200, ModelResult.SUCCESS, totalCount, items);
	}

}