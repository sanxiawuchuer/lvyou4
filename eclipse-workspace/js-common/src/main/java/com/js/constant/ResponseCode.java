package com.js.constant;


public enum ResponseCode {
	SUCCESS("200", "操作成功"),
    AUTH_FAILED("403", "验签失败"),
    ERROR("500", "服务器端错误"),
    LOST_PARAMS("600", "缺少参数"),
    ACCOUNT_NOT_FOUND("601", "账号不存在"),
    PASSWORD_ERROR("602", "密码有误"),
    ACCOUNT_PASSWORD_DIF("613","账号或密码错误"),
    DEVICE_ID_DIF("612","更换第三方设备"),
    ACCOUNT_LOCKED("611", "账号已被锁定"),
    DATA_NOT_FOUND("604", "查询信息不存在"),
    ERROR_SMS_CODE("605", "验证码有误"),
    PHONE_EXIST("606", "手机号已注册"),
    SEND_SMS_CODE_ERROR("607", "验证码发送失败"),
    SEND_SMS_CODE_SUCCUSS("608", "验证码发送成功"),
    ILLEGAL_USER("609", "非法用户"),
    ILLEGAL_COMPANY("610", "非法公司"),
    ERROR_PARAM_VAL("615", "参数数值错误"),
    SHOP_NOLL("616", "商铺不存在"),
    PHONE_NOLL("617", "未绑定手机号"),
    ERROR_MOUNT("618", "余额不足"),
    FEED_NOT_FOUND("619", "动态不存在"),
    SECURITY_PHONE_NOT_FOUND("620", "未设置安全手机号码"),
    DEBT_NUM_OUTOF_RANGE("621", "赊账还款数量不能大于总剩余数量"),
    DEBT_AMOUNT_OUTOF_RANGE("622", "赊账还款金额不能大于总剩余金额"),
    DEPOSIT_NUM_OUTOF_RANGE("623", "还款数量不能大于总剩余数量"),
    DEPOSIT_AMOUNT_OUTOF_RANGE("624", "还款金额不能大于总剩余金额"),
    DEPOSIT_GOODS_NULL("625", "押金商品不存在"),
    UN_BIND_ACCOUNT("626", "未绑定账号"),
    NULL_USER_ID("627","缺少操作人"),
    NU_ERROR("800", "其他错误"),
    LOGIN_ERROR("801", "账号异常"),  //app端会重新登录
    CREDIT_ORDER_ERROR("802", "有未支付订单，不允许继续下单");  //app端会禁止下单
	private String code;
	private String desc;
	ResponseCode(){
		
	}
	private ResponseCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	/*public static ResponseCode fromCode(String code) {
		ResponseCode[] values=ResponseCode.values();
		for(int i=0;i<values.length;i++) {
			if(code.equals(values[i].code)) {
				return values[i];
			}
		}
		return null;
	}
	public static ResponseCode fromCodeOrDesc(String code) {
		ResponseCode[] values=ResponseCode.values();
		for(int i=0;i<values.length;i++) {
			if(code.equals(values[i].code)||code.equals(values[i].desc)) {
				return values[i];
			}
		}
		return null;
	}*/
}
