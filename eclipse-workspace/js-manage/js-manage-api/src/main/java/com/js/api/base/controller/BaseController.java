package com.js.api.base.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.js.constant.ResponseCode;

public class BaseController {
  public Map createMessage(ResponseCode responseCode) {
	  return createMessage(responseCode,"");
  }

public Map createMessage(ResponseCode responseCode, String msg) {
	Map map=Maps.newHashMap();
	map.put("code",responseCode.getCode());
	if(StringUtils.isEmpty(msg)) {
		map.put("msg",responseCode.getDesc());
	}else {
		map.put("msg", msg);
	}
	return map;
}
}
