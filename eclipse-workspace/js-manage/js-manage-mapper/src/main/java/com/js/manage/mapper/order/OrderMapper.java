package com.js.manage.mapper.order;

import java.util.List;

import com.js.manage.mapper.base.mapper.SysMapper;
import com.js.manage.pojo.order.Order;

public interface OrderMapper extends SysMapper<Order>{

	List<Order> queryListBStatus(String orderStatus);

}
