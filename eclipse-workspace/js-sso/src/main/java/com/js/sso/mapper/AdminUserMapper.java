package com.js.sso.mapper;

import com.js.sso.mapper.base.mapper.SysMapper;
import com.js.sso.pojo.AdminUser;

public interface AdminUserMapper extends SysMapper<AdminUser>{

	

	AdminUser findAdinUserByMobilePhone(String mobilePhone);

	AdminUser findAdminUserByDeviceId(String deviceId);

	AdminUser findAdminUserByEmail(String email);

	

}
