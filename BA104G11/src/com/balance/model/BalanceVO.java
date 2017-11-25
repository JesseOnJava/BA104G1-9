package com.balance.model;

import java.io.Serializable;
import java.sql.Date;

public class BalanceVO implements Serializable{

	private String topupNo;
	private String memNo;
	private Integer topupValue;
	private Date topupTime;
	private String status;
	private String topupWay;
	
	public String getTopupNo() {
		return topupNo;
	}
	public void setTopupNo(String topupNo) {
		this.topupNo = topupNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public Integer getTopuValue() {
		return topupValue;
	}
	public void setTopupValue(Integer topupValue) {
		this.topupValue = topupValue;
	}
	public Date getTopupTime() {
		return topupTime;
	}
	public void setTopupTime(Date topupTime) {
		this.topupTime = topupTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTopupWay() {
		return topupWay;
	}
	public void setTopupWay(String topupWay) {
		this.topupWay = topupWay;
	}
}
