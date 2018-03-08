package com.js.manage.pojo.guide;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.js.manage.pojo.BasePojo;

@Table(name = "ly_guide")
public class Guide extends BasePojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "guide_name")
	private String guideName;
	@Column(name = "guide_unit")
	private double guideUnit;
	@Column(name = "admin_user_id")
    private Long adminUserId;
	@Column(name = "guide_level")
	private String guideLevel;
	
	public String getGuideLevel() {
		return guideLevel;
	}

	public void setGuideLevel(String guideLevel) {
		this.guideLevel = guideLevel;
	}

	public Long getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Long adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public double getGuideUnit() {
		return guideUnit;
	}

	public void setGuideUnit(double guideUnit) {
		this.guideUnit = guideUnit;
	}

}
