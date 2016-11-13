package com.mh.ifc.http;

public class VgReqBean {
	private String userName;
	
	private Double amount;
	
	private String host;
	
	private String orderId;
	
	private String recordUserId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRecordUserId() {
		return recordUserId;
	}

	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}
}
