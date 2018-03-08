package com.js.sso.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.js.constant.ResponseCode;
import com.js.sso.controller.base.controller.BaseController;
import com.js.sso.pojo.AdminUser;
import com.js.sso.service.AdminUserService;
import com.js.sso.service.SmsService;

@Controller
@RequestMapping("adminUser")
public class AdminUserController extends BaseController {
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private SmsService smsService;

	@RequestMapping("register")
	@ResponseBody
	public Map register(@RequestBody AdminUser adminUser) {
		Map map;
		if (adminUser == null || adminUser.getMobilePhone() == null || "".equals(adminUser.getMobilePhone())
				||StringUtils.isEmpty(adminUser.getDeviceId())||StringUtils.isBlank(adminUser.getDeviceId())||StringUtils.isEmpty(adminUser.getUserType())||StringUtils.isBlank(adminUser.getUserType())) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		} else {
			if (adminUser.getPassword() == null || "".equals(adminUser.getPassword())) {
				adminUser.setPassword("111111");
			}
			adminUser.setPassword(Hex.encodeHexString(DigestUtils.md5(adminUser.getPassword())));
			adminUser.setAddTime(new Date());
			adminUser.setDeleteFlag("0");
			map = adminUserService.register(adminUser);
		}
		return map;
	}

	@RequestMapping("sendValidate")
	@ResponseBody
	public Map sendValidate(@RequestParam(value = "mobilePhone", required = true) String mobilePhone,@RequestParam(value = "operationType", required = true) String operationType) {
		Map map = new HashMap();
		ResponseCode code = null;
		Boolean flag = true;
		if (StringUtils.isBlank(mobilePhone) || StringUtils.isEmpty(mobilePhone)||StringUtils.isEmpty(operationType)||StringUtils.isBlank(operationType)) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		} else {
			//如果是注册，需检查用户是否存在
			if("0".equals(operationType)) {
				AdminUser adminUser = new AdminUser();
				adminUser.setMobilePhone(mobilePhone);
				flag = adminUserService.check(adminUser);
				if (flag) {
					map=createMessage(ResponseCode.PHONE_EXIST);
				} 
				else {
					map = smsService.sendValidateCode(mobilePhone);
				}
			}else {
				map = smsService.sendValidateCode(mobilePhone);
			}
			
		}
		return map;
	}
	@RequestMapping("sendValidateByEmail")
	@ResponseBody
	public Map sendValidateByEmail(@RequestParam(value = "email", required = true) String email) {
		Map map = new HashMap();
		ResponseCode code = null;
		Boolean flag = true;
		if (email == null || "".equals(email)) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		} else {
			map = smsService.sendValidateCodeByEmail(email);
			
		}
		return map;
	}
    //手机号验证
	@RequestMapping("validateVcode")
	@ResponseBody
	public Map validateVcode(@RequestParam(value = "mobilePhone", required = true) String mobilePhone,
			@RequestParam(value = "vcode", required = true) String vcode,
			@RequestParam(value = "deviceId", required = true) String deviceId,@RequestParam(value = "operateType", required = true) String operateType) {
		Map map = null;
		ResponseCode code = null;
		if (StringUtils.isNotEmpty(mobilePhone) || StringUtils.isNotBlank(mobilePhone) || StringUtils.isNotEmpty(vcode)
				|| StringUtils.isNotBlank(vcode)|| StringUtils.isNotEmpty(deviceId)
				|| StringUtils.isNotBlank(deviceId)|| StringUtils.isNotEmpty(operateType)
				|| StringUtils.isNotBlank(operateType)) {
			map = createMessage(code.LOST_PARAMS);
		}
		if (smsService.validateVcode(mobilePhone, vcode,deviceId)) {
			map = createMessage(code.SUCCESS, "验证成功");
			//如果是登陆
			if("1".equals(operateType)) {
				AdminUser adminUser=new AdminUser();
				adminUser.setMobilePhone(mobilePhone);
				adminUser=adminUserService.queryByWhere(adminUser);
				map.put("adminUser", adminUser);
			}
			
		} else {
			map = createMessage(code.ERROR, "验证失败");
		}
		return map;
	}
	//邮箱验证
	@RequestMapping("validateVcodeByEmail")
	@ResponseBody
	public Map validateVcodeByEmail(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "vcode", required = true) String vcode,
			@RequestParam(value = "deviceId", required = true) String deviceId,@RequestParam(value = "operateType", required = true) String operateType) {
		Map map = null;
		ResponseCode code = null;
		if (StringUtils.isNotEmpty(email) || StringUtils.isNotBlank(email) || StringUtils.isNotEmpty(vcode)
				|| StringUtils.isNotBlank(vcode)|| StringUtils.isNotEmpty(deviceId)
				|| StringUtils.isNotBlank(deviceId)|| StringUtils.isNotEmpty(operateType)
				|| StringUtils.isNotBlank(operateType)) {
			map = createMessage(code.LOST_PARAMS);
		}
		if (smsService.validateVcodeWithEmail(email, vcode,deviceId)) {
			map = createMessage(code.SUCCESS, "验证成功");
			//如果是登陆
			if("1".equals(operateType)) {
				AdminUser adminUser=new AdminUser();
				adminUser.setMobilePhone(email);
				adminUser=adminUserService.queryByWhere(adminUser);
				map.put("adminUser", adminUser);
			}
			
		} else {
			map = createMessage(code.ERROR, "验证失败");
		}
		return map;
	}
	@RequestMapping("login")
	@ResponseBody
	public Map login(@RequestBody AdminUser adminUser) {
		Map map = null;
		if (adminUser == null || adminUser.getMobilePhone() == null || "".equals(adminUser.getMobilePhone())
				||StringUtils.isNotEmpty(adminUser.getDeviceId())||StringUtils.isNotBlank(adminUser.getDeviceId())) {
			map = createMessage(ResponseCode.LOST_PARAMS);
		}else {
			map=adminUserService.login(adminUser);
		}
		return map;
	}
	@RequestMapping("passwordResetWithEmail")
	@ResponseBody
	public Map passwordResetWithEmail(@RequestParam(value = "email")String email,@RequestParam(value = "password",required=true)String password) {
		Map map=null;
		if(StringUtils.isNotEmpty(password)&&StringUtils.isNotBlank(password)
				&&StringUtils.isNotEmpty(email)&&StringUtils.isNotBlank(email)) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		}else {
			map=adminUserService.passwordReset("",email,password);
		}
		return map;
	}
	@RequestMapping("passwordReset")
	@ResponseBody
	public Map passwordReset(@RequestParam(value = "mobilePhone")String mobilPhone,@RequestParam(value = "password",required=true)String password) {
		Map map=null;
		if(StringUtils.isNotEmpty(mobilPhone)&&StringUtils.isNotBlank(mobilPhone)
				&&StringUtils.isNotEmpty(password)&&StringUtils.isNotBlank(password)) {
			map=createMessage(ResponseCode.LOST_PARAMS);
		}else {
			map=adminUserService.passwordReset(mobilPhone,"",password);
		}
		return map;
	}
}
