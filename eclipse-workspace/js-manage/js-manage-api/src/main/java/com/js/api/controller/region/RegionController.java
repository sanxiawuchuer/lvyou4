package com.js.api.controller.region;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.js.manage.service.region.RegionService;

@Controller
@RequestMapping("region")
public class RegionController {
	@Autowired
	private RegionService regionService;

	@RequestMapping("queryListByParentId")
	@ResponseBody
	public Map queryListByParentId(@RequestParam(defaultValue = "0") Long parentId) {
		return regionService.queryRegionByParentId(parentId);
	}
}
