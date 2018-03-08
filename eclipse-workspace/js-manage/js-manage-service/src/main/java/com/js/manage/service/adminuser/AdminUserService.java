package com.js.manage.service.adminuser;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.constant.ResponseCode;
import com.js.manage.pojo.adminUser.AdminUser;
import com.js.manage.pojo.adminuserexpand.AdminUserExpand;
import com.js.manage.service.BaseService;
import com.js.manage.service.adminuserexpand.AdminUserExpandService;

@Service
public class AdminUserService extends BaseService<AdminUser>{
    @Autowired
    private AdminUserExpandService amindUserExpandService;
	public Map editAdminUser(AdminUser adminUser) {
		Map map=null;
		this.updateSelective(adminUser);
		if(adminUser.getAdminUserExpand()!=null) {
			amindUserExpandService.updateSelective(adminUser.getAdminUserExpand());
			map=createMessage(ResponseCode.SUCCESS,"编辑成功");
		}
		return map;
	}
	public Map bindEmail(AdminUser adminUser) {
		AdminUser adminUserCheck=this.queryById(adminUser.getId());
		Map map=null;
		if(adminUserCheck==null) {
			 map=createMessage(ResponseCode.ACCOUNT_NOT_FOUND);
		}else {
			this.saveSelective(adminUser);
			map=createMessage(ResponseCode.SUCCESS);
		}
		return map;
	}
	public Map findAdminUserById(Long id) {
		Map map=null;
		AdminUser adminUser=this.queryById(id);
		if(adminUser!=null) {
			AdminUserExpand adminUserExpand=amindUserExpandService.queryById(id);
			if(adminUserExpand!=null) {
				adminUser.setAdminUserExpand(adminUserExpand);
				map=createMessage(ResponseCode.SUCCESS);
				map.put(adminUser, adminUser);
			}
		}else {
			map=createMessage(ResponseCode.ACCOUNT_NOT_FOUND);
		}
		return map;
	}

}
