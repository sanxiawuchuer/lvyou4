package com.js.parent.js_parent;

import java.util.ArrayList;

import org.junit.Test;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

public class SmsDemo {
	public static void main(String[] args) {
		int appid = 1400035861;
		String appkey = "fcd2996ba564802430523060761389ae";

		String phoneNumber1 = "13483347386";
		String phoneNumber2 = "13576666666";
		String phoneNumber3 = "13576666666";
		int tmplId = 80243;

		try {
			//初始化单发
            SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult singleSenderResult;
			 ArrayList<String> params = new ArrayList<String>();
	            params.add("111111");
	            singleSenderResult = singleSender.sendWithParam("86", phoneNumber1, tmplId, params, "", "", "");
	            System.out.println(singleSenderResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test() {
		int appid = 1400035861;
		String appkey = "fcd2996ba564802430523060761389ae";

		String phoneNumber1 = "13483347386";
		String phoneNumber2 = "13576666666";
		String phoneNumber3 = "13576666666";
		int tmplId = 80243;

		try {
			//初始化单发
            SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult singleSenderResult;
			 ArrayList<String> params = new ArrayList<String>();
	            params.add("111111");
	            singleSenderResult = singleSender.sendWithParam("86", phoneNumber1, tmplId, params, "", "", "");
	            System.out.println(singleSenderResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
