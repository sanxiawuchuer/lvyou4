package com.js.manage.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public abstract class BasePojo implements Serializable{
	@Column(name = "add_time")
    private Date addTime;
	@Column(name = "add_user_id")
    private Long addUserId;
	@Column(name = "update_time")
    private Date updateTime;
	@Column(name = "update_user_id")
    private Long updateUserId;
	@Column(name = "delete_flag")
    private String deleteFlag;
	
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Long getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(Long addUserId) {
		this.addUserId = addUserId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
    
    

}
