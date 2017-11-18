package com.mealorderdetail.model;

import java.sql.Date;

public class MealOrderDetailVO implements java.io.Serializable {
	private String moDetailNo;
	private String moNo;
	private Date deliveryDate;
	private String mealTime;
	private Integer smNo;
	private Integer orderQty;

	public String getMoDetailNo() {
		return moDetailNo;
	}

	public void setMoDetailNo(String moDetailNo) {
		this.moDetailNo = moDetailNo;
	}

	public String getMoNo() {
		return moNo;
	}

	public void setMoNo(String moNo) {
		this.moNo = moNo;
	}

	public Integer getSmNo() {
		return smNo;
	}

	public void setSmNo(Integer smNo) {
		this.smNo = smNo;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getMealTime() {
		return mealTime;
	}

	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}

}
