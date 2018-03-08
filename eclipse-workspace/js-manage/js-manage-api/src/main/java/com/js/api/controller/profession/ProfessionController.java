package com.js.api.controller.profession;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.js.api.base.controller.BaseController;
import com.js.manage.service.profession.ProfessionService;
@Controller
@RequestMapping("profession")
public class ProfessionController extends BaseController{
	@Autowired
  private ProfessionService professionService;
	@RequestMapping("queryListByParentId")
	@ResponseBody
	public Map queryListByParentId(@RequestParam(defaultValue="0")Long parentId) {
		return professionService.queryProfessionListByParentId(parentId);
	}
}
