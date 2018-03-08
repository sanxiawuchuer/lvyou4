package com.js.api.controller.itemcat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.js.manage.pojo.itemcat.ItemCat;
import com.js.manage.service.itemcat.ItemCatService;

@Controller
@RequestMapping("/item")
public class ItemCatController {
	@Autowired
private ItemCatService itemCatService;
	@RequestMapping("/queryList")
	@ResponseBody		//返回json格式串
	public List<ItemCat> queryList(@RequestParam(defaultValue="0")Integer id){
		return itemCatService.queryItemCatList(id);
	}
}
