package com.js.manage.pojo.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.js.manage.pojo.BasePojo;

@Table(name = "ly_order")
public class Order extends BasePojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "order_no")
	private String orderNo;
	@Column(name = "order_create_time")
	private Date orderCreateTime;
	@Column(name = "order_status")
	private String orderStatus;
	@Column(name = "total_people_num")
	private Integer totalPeopleNum;
	@Column(name = "guide_level_id")
	private Long guideLevelId;
	@Column(name = "total_day_num")
	private Integer totalDayNum;
	@Column(name = "total_fee_num")
	private Double totalFeeNum;
	@Column(name = "order_type")
	private String orderType;
	@Column(name = "order_region")
	private String orderRegion;
	@Column(name = "pay_time")
	private Date payTime;
	@Column(name = "refund_time")
	private Date refundTime;
	@Transient
	private Map<String,Object> orderServer;
	
	
	public Map<String, Object> getOrderServer() {
		return orderServer;
	}

	public void setOrderServer(Map<String, Object> orderServer) {
		this.orderServer = orderServer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getTotalPeopleNum() {
		return totalPeopleNum;
	}

	public void setTotalPeopleNum(Integer totalPeopleNum) {
		this.totalPeopleNum = totalPeopleNum;
	}

	public Long getGuideLevelId() {
		return guideLevelId;
	}

	public void setGuideLevelId(Long guideLevelId) {
		this.guideLevelId = guideLevelId;
	}

	public Integer getTotalDayNum() {
		return totalDayNum;
	}

	public void setTotalDayNum(Integer totalDayNum) {
		this.totalDayNum = totalDayNum;
	}

	public Double getTotalFeeNum() {
		return totalFeeNum;
	}

	public void setTotalFeeNum(Double totalFeeNum) {
		this.totalFeeNum = totalFeeNum;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderRegion() {
		return orderRegion;
	}

	public void setOrderRegion(String orderRegion) {
		this.orderRegion = orderRegion;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

}
