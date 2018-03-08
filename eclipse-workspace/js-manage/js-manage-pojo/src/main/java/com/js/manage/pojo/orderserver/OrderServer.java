package com.js.manage.pojo.orderserver;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.js.manage.pojo.BasePojo;

@Table(name = "ly_order_server")
public class OrderServer extends BasePojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "tag_name")
	private String tagName;
	@Column(name = "travel_start_time")
	private Date travelStartTime;
	@Column(name = "travel_end_time")
	private Date travelEndTime;
	@Column(name = "adult_num")
	private Integer adultNum;
	@Column(name = "children_num")
	private Integer childrenNum;
	@Column(name = "need_tag_id")
	private Long needTagId;
	@Column(name = "other_need")
	private String otherNeed;
	@Column(name = "server_car_type_id")
	private Long serverCarTypeId;
	@Column(name="order_id")
    private Long orderId;
	@Column(name="region_id")
	private Long regionId;
	@Transient
	private Map<String,Object> destination;
	public Map<String, Object> getDestination() {
		return destination;
	}

	public void setDestination(Map<String, Object> destination) {
		this.destination = destination;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Date getTravelStartTime() {
		return travelStartTime;
	}

	public void setTravelStartTime(Date travelStartTime) {
		this.travelStartTime = travelStartTime;
	}

	public Date getTravelEndTime() {
		return travelEndTime;
	}

	public void setTravelEndTime(Date travelEndTime) {
		this.travelEndTime = travelEndTime;
	}

	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public Integer getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(Integer childrenNum) {
		this.childrenNum = childrenNum;
	}

	public Long getNeedTagId() {
		return needTagId;
	}

	public void setNeedTagId(Long needTagId) {
		this.needTagId = needTagId;
	}

	public String getOtherNeed() {
		return otherNeed;
	}

	public void setOtherNeed(String otherNeed) {
		this.otherNeed = otherNeed;
	}

	public Long getServerCarTypeId() {
		return serverCarTypeId;
	}

	public void setServerCarTypeId(Long serverCarTypeId) {
		this.serverCarTypeId = serverCarTypeId;
	}

}
