package com.android.mealOrderDetail.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MealOrderDetailVO implements Serializable{
	private String moDetailNo;
	private String moNo;
	private Date deliverDate;
	private String deliverTime;
	private Integer smNo;
	private Integer orderQty;
	
	public MealOrderDetailVO(){
		
	}
	
	public MealOrderDetailVO(String moDetailNo, String moNo, Date deliverDate, String deliverTime, Integer smNo,
			Integer orderQty) {
		super();
		this.moDetailNo = moDetailNo;
		this.moNo = moNo;
		this.deliverDate = deliverDate;
		this.deliverTime = deliverTime;
		this.smNo = smNo;
		this.orderQty = orderQty;
	}

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

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
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
	
	
}
