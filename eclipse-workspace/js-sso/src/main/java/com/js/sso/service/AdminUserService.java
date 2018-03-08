package com.js.sso.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.common.service.RedisService;
import com.js.constant.ResponseCode;
import com.js.sso.mapper.AdminUserCredentialsMapper;
import com.js.sso.mapper.AdminUserExpandMapper;
import com.js.sso.mapper.AdminUserMapper;
import com.js.sso.mapper.CompanyMapper;
import com.js.sso.mapper.FileMapper;
import com.js.sso.mapper.GuideMapper;
import com.js.sso.pojo.AdminUser;
import com.js.sso.pojo.AdminUserCredentials;
import com.js.sso.pojo.AdminUserExpand;
import com.js.sso.pojo.Company;
import com.js.sso.pojo.File;
import com.js.sso.pojo.Guide;
@Service
public class AdminUserService extends BaseService<AdminUser>{
	@Autowired
    private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminUserExpandMapper adminUserExpandMapper;
	@Autowired
	private AdminUserCredentialsMapper adminUserCredentialsMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private FileMapper fileMapper;
	@Autowired
	private RedisService redisService;
	@Autowired
	private GuideMapper guideMapper;
	private  ObjectMapper MAPPER=new ObjectMapper();
	public Map register(AdminUser adminUser) {
		Map map=null;
		try {
			//检出用户是否存在
			Boolean flag=false;
			flag=check(adminUser);
			if(flag) {
				map=createMessage(ResponseCode.PHONE_EXIST);
			}else {
				adminUser.setAuthentication("0");
				this.saveSelective(adminUser);
				String ticket=DigestUtils.md5Hex("TICKET"+System.currentTimeMillis()+adminUser.getMobilePhone());
				if(adminUser.getAdminUserExpand()!=null) {
				 AdminUserExpand adminUseExpand=adminUser.getAdminUserExpand();
				 adminUseExpand.setAddUserId(adminUser.getId());
				 adminUseExpand.setAddTime(new Date());
				 adminUseExpand.setDeleteFlag("0");
				 adminUserExpandMapper.insertSelective(adminUseExpand);
				}
				if(adminUser.getAdminUserCredentialsList()!=null&&adminUser.getAdminUserCredentialsList().size()>0) {
					List<AdminUserCredentials> adminUserCredentialsList=adminUser.getAdminUserCredentialsList();
					for(AdminUserCredentials adminUserCredentials:adminUserCredentialsList) {
						adminUserCredentials.setAddUserId(adminUser.getId());
						adminUserCredentials.setAddTime(new Date());
						adminUserCredentials.setDeleteFlag("0");
						adminUserCredentialsMapper.insertSelective(adminUserCredentials);
					}
				}
				if(adminUser.getCompany()!=null) {
					Company company=adminUser.getCompany();
					company.setMobilePhone(adminUser.getMobilePhone());
					company.setPassword(adminUser.getPassword());
					company.setAddTime(new Date());
					company.setAdminUserId(adminUser.getId());
					company.setDeleteFlag("0");
					companyMapper.insertSelective(company);
				}
				if(adminUser.getFileList()!=null&&adminUser.getFileList().size()>0) {
					for(File file:adminUser.getFileList()) {
						file.setAddTime(new Date());
						file.setAdminUserId(adminUser.getId());
						fileMapper.insertSelective(file);
					}
				}
				if("0".equals(adminUser.getUserType())||"1".equals(adminUser.getUserType())) {
					Guide guide=new Guide();
					if(StringUtils.isNotEmpty(adminUser.getAdminUserExpand().getName())||StringUtils.isNotBlank(adminUser.getAdminUserExpand().getName())) {
						guide.setGuideName(adminUser.getAdminUserExpand().getName());
					}
					guide.setAdminUserId(adminUser.getId());
					guideMapper.insertSelective(guide);
				}
				map=createMessage(ResponseCode.SUCCESS,"注册成功");
				map.put("adminUser", adminUser);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map=createMessage(ResponseCode.ERROR,"注册失败");
		}
		return map;
	}

	public  Boolean check(AdminUser adminUser) {
		AdminUser checkAdminUser=adminUserMapper.findAdinUserByMobilePhone(adminUser.getMobilePhone());
		if(checkAdminUser==null) {
			return false;
		}
		return true;
	}

	public Map login(AdminUser adminUser) {
		Map map=null;
		try {
			if(check(adminUser)) {
				//检查是否更换第三方设备
				Boolean flag=checkDeviceId(adminUser.getDeviceId());
				if(flag) {
					//检查密码是否正确;
					AdminUser checkAdminUser=adminUserMapper.findAdinUserByMobilePhone(adminUser.getMobilePhone());
				    if(adminUser.getPassword().equals(checkAdminUser.getPassword())) {
				    	map=createMessage(ResponseCode.SUCCESS,"登陆成功");
				    	map.put("adminUser", adminUser);
				    }else {
				    	map=createMessage(ResponseCode.ACCOUNT_PASSWORD_DIF);
				    }
				} else {
					map=createMessage(ResponseCode.DEVICE_ID_DIF);
				}
			}else {
				map=createMessage(ResponseCode.ACCOUNT_NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}

	private Boolean checkDeviceId(String deviceId) {
		AdminUser adminUser=adminUserMapper.findAdminUserByDeviceId(deviceId);
		if(adminUser!=null) {
			return true;
		}
		return false;
	}

	public Map passwordReset(String mobilPhone, String email, String password) {
		AdminUser adminUser=null;
		Map map=null;
		//手机号重置密码
		if(StringUtils.isNotEmpty(mobilPhone)&&StringUtils.isNotBlank(mobilPhone)) {
			adminUser=adminUserMapper.findAdinUserByMobilePhone(mobilPhone);
		}
		//邮箱重置密码
		if(StringUtils.isNotEmpty(email)&&StringUtils.isNotBlank(email)) {
			adminUser=adminUserMapper.findAdminUserByEmail(email);
		}
		if(adminUser!=null) {
			adminUser.setPassword(password);
			adminUserMapper.insertSelective(adminUser);
			Company company=companyMapper.findCompanyByMobilePhone(adminUser.getMobilePhone());
			if(company!=null) {
				company.setPassword(password);
				companyMapper.insertSelective(company);
			}
			map=createMessage(ResponseCode.SUCCESS,"修改成功");
		}
		if(adminUser!=null) {
			map=createMessage(ResponseCode.ACCOUNT_NOT_FOUND);
		}
		return map;
	}

}
