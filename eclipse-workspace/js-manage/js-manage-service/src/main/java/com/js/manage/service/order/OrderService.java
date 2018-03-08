package com.js.manage.service.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.utils.CollectionUtils;
import com.js.constant.ResponseCode;
import com.js.manage.pojo.order.Order;
import com.js.manage.pojo.orderserver.OrderServer;
import com.js.manage.service.BaseService;
import com.js.manage.service.orderserver.OrderServerService;

@Service
public class OrderService extends BaseService<Order> {
	private String[] orderShowArr = new String[] { "id", "orderNo", "totalPeopleNum", "totalDayNum", "totalFeeNum","orderServer"};
	
	@Autowired
	private OrderServerService orderServerService;
	public Map queryOrderListByOrderStatus(String orderStatus, Integer page, Integer rows) {
		Map map = null;
		Order order = new Order();
		order.setOrderStatus(orderStatus);
		PageHelper.startPage(page, rows);
		List<Order> orderList = this.queryListByWhere(order);
		if(CollectionUtils.isEmpty(orderList)) {
			return createMessage(ResponseCode.SUCCESS);
		}else {
			for(Order orderC:orderList) {
				Map<String, Object> orderServerMap=orderServerService.findOrderServerByOrderId(orderC);
				orderC.setOrderServer(orderServerMap);
			}
			PageInfo pageInfo = new PageInfo(orderList);
			// 内部拦截器拦截拼接分页条件
			List<Map<String, Object>> list = this.convertToMapList(orderList, orderShowArr);
			map = createMessageList(list, pageInfo.isIsLastPage() ? 1 : 0);
			return map;
		}
	}
}
