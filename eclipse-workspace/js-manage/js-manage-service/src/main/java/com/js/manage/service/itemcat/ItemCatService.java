package com.js.manage.service.itemcat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.manage.mapper.itemcat.ItemCatMapper;
import com.js.manage.pojo.itemcat.ItemCat;
import com.js.manage.service.BaseService;

@Service
public class ItemCatService extends BaseService<ItemCat>{
	//注入接口
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	//查询某个分类的列表
	public List<ItemCat> queryItemCatList(Integer parentId){
		return itemCatMapper.queryItemCatListByParentId(parentId);
	}
}
