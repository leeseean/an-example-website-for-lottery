package com.mh.ifc.http;


public class NMgResResult {
	private String statusCode;
	private String errorMessage;
	private String loginUrl;
	private String blance;
	private String userId;
	private String params = new String();
	private String userToken;
	private String userName;
	/** 网站用户标识 **/
	String webUserFlat;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getBlance() {
		return blance;
	}
	public void setBlance(String blance) {
		this.blance = blance;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getWebUserFlat() {
		return webUserFlat;
	}
	public void setWebUserFlat(String webUserFlat) {
		this.webUserFlat = webUserFlat;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
