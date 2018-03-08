package com.js.manage.pojo.servercartype;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.js.manage.pojo.BasePojo;

@Table(name = "ly_server_car_type")
public class ServerCarType extends BasePojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "car_type_name")
	private String carTypeName;
	@Column(name = "car_type_unit")
	private Double carTypeUnit;
	@Column(name = "seat_num")
	private Integer seatNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public Double getCarTypeUnit() {
		return carTypeUnit;
	}

	public void setCarTypeUnit(Double carTypeUnit) {
		this.carTypeUnit = carTypeUnit;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

}
