package com.mh.ifc.http;

public class SaResBean {
	//RegUserInfo
	private String userName;
	private String errorMsgId;
	private String errorMsg;
	
	//LoginRequest
	private String loginUrl;
	
	//GetUserStatusDV
	private String balance;
	
	//GetAllBetDetailsForTimeIntervalDV
	private String betRecordXml;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getErrorMsgId() {
		return errorMsgId;
	}
	public void setErrorMsgId(String errorMsgId) {
		this.errorMsgId = errorMsgId;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getBetRecordXml() {
		return betRecordXml;
	}
	public void setBetRecordXml(String betRecordXml) {
		this.betRecordXml = betRecordXml;
	}
}
