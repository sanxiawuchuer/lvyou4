package com.js.manage.service.region;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.js.constant.ResponseCode;
import com.js.manage.pojo.region.Region;
import com.js.manage.service.BaseService;
@Service
public class RegionService extends BaseService<Region>{
    private String[] regionShowArr=new String[] {"regionName"};
	public Map queryRegionByParentId(Long parentId) {
		Map map=null;
		Region region=new Region();
		region.setParentId(parentId);
		List<Region> regionList=this.queryListByWhere(region);		
		map=createMessage(ResponseCode.SUCCESS);
		map.put("regionList", regionList);
		return map;
	}

	public Map<String, Object> findRetionById(Long regionId) {
		Region region=this.queryById(regionId);
		if(region!=null) {
			Map<String,Object> map=this.convertToMap(region, regionShowArr);
			return map;
		}
		return null;
	}

}
