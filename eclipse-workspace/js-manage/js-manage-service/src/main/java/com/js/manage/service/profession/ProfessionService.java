package com.js.manage.service.profession;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.js.constant.ResponseCode;
import com.js.manage.pojo.profession.Profession;
import com.js.manage.service.BaseService;
@Service
public class ProfessionService extends BaseService<Profession>{

	public Map queryProfessionListByParentId(Long parentId) {
		Map map=null;
		Profession profession=new Profession();
		profession.setParentId(parentId);
		List<Profession> professionList=this.queryListByWhere(profession);
		map=createMessage(ResponseCode.SUCCESS);
		map.put("professionList", professionList);
		return map;
	}

}
