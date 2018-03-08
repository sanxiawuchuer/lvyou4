package com.js.sso.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.js.common.service.RedisService;
import com.js.common.spring.exetend.PropertyConfig;
import com.js.constant.ResponseCode;
import com.js.sso.mapper.AdminUserMapper;
import com.js.sso.pojo.AdminUser;

@Service
public class SmsService extends BaseService{
	@PropertyConfig
    private Integer APP_ID;
	@PropertyConfig
    private String APP_KEY;
	@PropertyConfig
	private Integer TMPL_ID;
	@Autowired
	private RedisService redisService;
	@Autowired
	private AdminUserMapper adminUserMapper;
	public Map sendValidateCode(String mobilePhone) {
		Map map;
		String vcode=createVcode();
		//初始化单发
		try {
			SmsSingleSender singleSender=new SmsSingleSender(APP_ID,APP_KEY);
			SmsSingleSenderResult singleSenderReuslt;
			ArrayList<String> params=new ArrayList<String>();
			//String str="儿子，你再干啥呢？弹唱";
			params.add(vcode);
			singleSenderReuslt=singleSender.sendWithParam("86", mobilePhone, TMPL_ID, params, "", "", "");
			System.out.println(singleSenderReuslt);
			//将验证码放进缓冲中，等待用户输入的时候验证
			redisService.set(mobilePhone, vcode,60);
			 map=createMessage(ResponseCode.SEND_SMS_CODE_SUCCUSS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map=createMessage(ResponseCode.SEND_SMS_CODE_ERROR);
		}
		
		return map;
	}
    private String createVcode() {
    	String vcode="";
		for(int i=0;i<6;i++) {
			vcode=vcode+(int)(Math.random()*9);
		}
		return vcode;
    }
	public boolean validateVcode(String mobilePhone, String vcode,String deviceId) {
		String saveCode=redisService.get(mobilePhone);
		if(StringUtils.isNotBlank(saveCode)&&saveCode.equals(vcode)) {
			redisService.del(mobilePhone);
			AdminUser adminUser=adminUserMapper.findAdinUserByMobilePhone(mobilePhone);
			if(adminUser!=null) {
				adminUser.setDeviceId(deviceId);
				adminUserMapper.updateByPrimaryKeySelective(adminUser);
			}
			return true;
		}
		return false;
	}
	public Map sendValidateCodeByEmail(String email) {
		try {
			final Properties props = new Properties();
			  // 表示SMTP发送邮件，必须进行身份验证
			  props.put("mail.smtp.auth", "true");
			  //此处填写SMTP服务器
			  props.put("mail.smtp.host", "smtp.qq.com");
			  //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
			  props.put("mail.smtp.port", "587");
			  // 此处填写你的账号
			  props.put("mail.user", "531394524@qq.com");
			  // 此处的密码就是前面说的16位STMP口令
			  props.put("mail.password", "eoewbltjdhwzcacg");

			  // 构建授权信息，用于进行SMTP进行身份验证
			  Authenticator authenticator = new Authenticator() {

			      protected PasswordAuthentication getPasswordAuthentication() {
			          // 用户名、密码
			          String userName = props.getProperty("mail.user");
			          String password = props.getProperty("mail.password");
			          return new PasswordAuthentication(userName, password);
			      }
			  };
			  // 使用环境属性和授权信息，创建邮件会话
			  Session mailSession = Session.getInstance(props, authenticator);
			  // 创建邮件消息
			  MimeMessage message = new MimeMessage(mailSession);
			  // 设置发件人
			  InternetAddress form = new InternetAddress(
			          props.getProperty("mail.user"));
			  message.setFrom(form);

			  // 设置收件人的邮箱
			  InternetAddress to = new InternetAddress(email);
			  message.setRecipient(RecipientType.TO, to);

			  // 设置邮件标题
			  message.setSubject("井石旅游邮件");
              String vcode=createVcode();
              String content="井石：井石旅游给您发了一个验证码"+vcode+"，打死不能告诉别人";
			  // 设置邮件的内容体
			  message.setContent(content, "text/html;charset=UTF-8");

			  // 最后当然就是发送邮件啦
			  Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean validateVcodeWithEmail(String email, String vcode, String deviceId) {
		String saveCode=redisService.get(email);
		if(StringUtils.isNotBlank(saveCode)&&saveCode.equals(vcode)) {
			redisService.del(email);
			AdminUser adminUser=adminUserMapper.findAdminUserByEmail(email);
			if(adminUser!=null) {
				adminUser.setDeviceId(deviceId);
				adminUserMapper.updateByPrimaryKeySelective(adminUser);
			}
			return true;
		}
		return false;
	}
}
















