package com.js.manage.mapper.itemcat;

import java.util.List;

import com.js.manage.mapper.base.mapper.SysMapper;
import com.js.manage.pojo.itemcat.ItemCat;

public interface ItemCatMapper extends SysMapper<ItemCat>{
	//扩展方法
	public List<ItemCat> queryItemCatListByParentId(Integer pid);
}
