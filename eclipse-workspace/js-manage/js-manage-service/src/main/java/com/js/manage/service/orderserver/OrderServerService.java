package com.js.manage.service.orderserver;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.manage.pojo.order.Order;
import com.js.manage.pojo.orderserver.OrderServer;
import com.js.manage.service.BaseService;
import com.js.manage.service.region.RegionService;
@Service
public class OrderServerService extends BaseService<OrderServer>{
	private String[] orderServiceShowArr = new String[] { "travelStartTime", "travelEndTime", "adultNum",
	"childrenNum","destination" };
	@Autowired
    private RegionService regionService;
	public Map<String,Object> findOrderServerByOrderId(Order orderC) {
		OrderServer orderServer=new OrderServer();
		orderServer.setOrderId(orderC.getId());
		orderServer=this.queryByWhere(orderServer);
		Map<String, Object> map=null;
		if(orderServer!=null) {
			Map<String,Object> destination=regionService.findRetionById(orderServer.getRegionId());
			 if(destination!=null) {
				 orderServer.setDestination(destination);
			 }
			 map=this.convertToMap(orderServer, orderServiceShowArr);
		}
		return map;
	}

}
