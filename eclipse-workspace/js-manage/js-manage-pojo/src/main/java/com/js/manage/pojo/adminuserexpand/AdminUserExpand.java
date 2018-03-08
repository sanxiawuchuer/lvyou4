package com.js.manage.pojo.adminuserexpand;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.js.manage.pojo.BasePojo;

@Table(name = "ly_admin_user_expand")
public class AdminUserExpand extends BasePojo {
	@Id // 设置主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 设置自增，底层数据库支持
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "sex")
	private String sex;
	@Column(name = "community_invitation_code")
	private String communityInvitationCode;
	@Column(name = "admin_user_id")
	private Long adminUserId;
	@Column(name = "age")
	private Integer age;
	@Column(name = "signature")
	private String signature;
	@Column(name = "location_region_id")
	private Long locationRegionId;
	@Column(name = "home_region_id")
	private Long homeRegionId;
	@Column(name = "profession_id")
	private Long professionId;
	@Column(name = "contact_phone")
	private String contactPhone;
	@Column(name = "alternative_phone")
	private String alternativePhone;
	@Column(name = "we_chat")
	private String weChat;
	@Column(name = "facebook")
	private String facebook;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Long getLocationRegionId() {
		return locationRegionId;
	}

	public void setLocationRegionId(Long locationRegionId) {
		this.locationRegionId = locationRegionId;
	}

	public Long getHomeRegionId() {
		return homeRegionId;
	}

	public void setHomeRegionId(Long homeRegionId) {
		this.homeRegionId = homeRegionId;
	}

	public Long getProfessionId() {
		return professionId;
	}

	public void setProfessionId(Long professionId) {
		this.professionId = professionId;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getAlternativePhone() {
		return alternativePhone;
	}

	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCommunityInvitationCode() {
		return communityInvitationCode;
	}

	public void setCommunityInvitationCode(String communityInvitationCode) {
		this.communityInvitationCode = communityInvitationCode;
	}
	public Long getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Long adminUserId) {
		this.adminUserId = adminUserId;
	}

}
