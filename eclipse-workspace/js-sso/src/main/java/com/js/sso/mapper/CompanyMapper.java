package com.js.sso.mapper;

import com.js.sso.mapper.base.mapper.SysMapper;
import com.js.sso.pojo.Company;

public interface CompanyMapper extends SysMapper<Company>{

	Company findCompanyByMobilePhone(String mobilPhone);

}
