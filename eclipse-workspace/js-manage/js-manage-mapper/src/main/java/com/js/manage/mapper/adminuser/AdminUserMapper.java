package com.js.manage.mapper.adminuser;

import com.js.manage.mapper.base.mapper.SysMapper;
import com.js.manage.pojo.adminUser.AdminUser;

public interface AdminUserMapper extends SysMapper<AdminUser>{

	

	AdminUser findAdinUserByMobilePhone(String mobilePhone);

	AdminUser findAdminUserByDeviceId(String deviceId);

	AdminUser findAdminUserByEmail(String email);

	

}
