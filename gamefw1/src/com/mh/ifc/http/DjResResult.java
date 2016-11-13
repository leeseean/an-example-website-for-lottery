package com.mh.ifc.http;

public class DjResResult {

	private String status_code;
	private String status_text;
	private String user_id;
	private String trans_id;
	private String trans;
	private String balance_open;
	private String balance_close;
	
	private Double balance;
	
	private String userToken;
	private String statusCode;
	private String messageCode;
	private String url;
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getStatus_text() {
		return status_text;
	}
	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getBalance_open() {
		return balance_open;
	}
	public void setBalance_open(String balance_open) {
		this.balance_open = balance_open;
	}
	public String getBalance_close() {
		return balance_close;
	}
	public void setBalance_close(String balance_close) {
		this.balance_close = balance_close;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
