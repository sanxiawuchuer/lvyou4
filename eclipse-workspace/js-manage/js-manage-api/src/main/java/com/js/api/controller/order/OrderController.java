package com.js.api.controller.order;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.js.api.base.controller.BaseController;
import com.js.constant.ResponseCode;
import com.js.manage.service.order.OrderService;

@Controller
@RequestMapping("order")
public class OrderController extends BaseController{
	@Autowired
 private OrderService orderService;
	@RequestMapping("queryOrderListByOrderStatus")
	@ResponseBody
 public Map queryOrderListByOrderStatus(@RequestParam(value="orderStatus") String orderStatus,@RequestParam(value="page") Integer page,@RequestParam(value="rows") Integer rows) {
		Map map=null;
		if(StringUtils.isEmpty(orderStatus)||StringUtils.isBlank(orderStatus)) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		}else {
			if(page==null||page<0) {
				page=1;
			}
			if(rows==null||rows<0) {
				rows=1;
			}
			map=orderService.queryOrderListByOrderStatus(orderStatus,page,rows);
		}
		return map;
	}
}
