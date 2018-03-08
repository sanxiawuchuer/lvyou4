package com.js.sso.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ly_company")
public class Company extends BasePojo {
	@Id // 设置主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 设置自增，底层数据库支持
	private Long id;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "mobile_phone")
	private String mobilePhone;
	private String password;
	@Column(name = "region_id")
	private Long regionId;
	@Column(name = "admin_user_id")
	private Long adminUserId;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Long adminUserId) {
		this.adminUserId = adminUserId;
	}

}
