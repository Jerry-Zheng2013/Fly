package com.ticketsystem.model;

public class DemoOrder {
	private String orderNo;
	private String status;
	
	
	public DemoOrder() {
	}
	public DemoOrder(String orderNo, String status) {
		this.orderNo = orderNo;
		this.status = status;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
