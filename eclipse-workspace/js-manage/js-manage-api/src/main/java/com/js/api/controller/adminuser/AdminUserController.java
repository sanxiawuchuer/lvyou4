package com.js.api.controller.adminuser;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.js.api.base.controller.BaseController;
import com.js.constant.ResponseCode;
import com.js.manage.pojo.adminUser.AdminUser;
import com.js.manage.service.adminuser.AdminUserService;
@Controller
@RequestMapping("adminUser")
public class AdminUserController extends BaseController{
	@Autowired
    private AdminUserService adminUserService;
	@RequestMapping("editAdminUser")
	@ResponseBody
	public Map editAdminUser(@RequestBody AdminUser adminUser) {
		Map map=null;
		if(adminUser==null||StringUtils.isNotEmpty(adminUser.getMobilePhone())||
			StringUtils.isNotBlank(adminUser.getMobilePhone())) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		}else {
			//map=adminUserService.editAdminUser(adminUser);
		}
		return null;
	}
	//绑定邮箱
	@RequestMapping("bindEmail")
	@ResponseBody
	public Map bindEmail(@RequestBody AdminUser adminUser) {
		Map map=null;
		if(adminUser==null) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		}else {
			map=adminUserService.bindEmail(adminUser);
		}
		return map;
	}
	//查找用户
	@RequestMapping("queryAdminUserById")
	@ResponseBody
	public Map queryAdminUserById(@RequestParam(value="id")Long id) {
		Map map=null;
		if(id==null) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		}else {
			map=adminUserService.findAdminUserById(id);
		}
		return map;
	}
}
