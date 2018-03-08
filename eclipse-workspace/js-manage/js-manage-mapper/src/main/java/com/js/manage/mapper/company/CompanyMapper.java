package com.js.manage.mapper.company;

import com.js.manage.mapper.base.mapper.SysMapper;
import com.js.manage.pojo.company.Company;

public interface CompanyMapper extends SysMapper<Company>{

	Company findCompanyByMobilePhone(String mobilPhone);

}
