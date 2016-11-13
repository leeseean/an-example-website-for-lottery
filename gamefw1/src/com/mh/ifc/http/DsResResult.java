package com.mh.ifc.http;


public class DsResResult {
	private String request;
	private int errorCode=-111;
	private String errorMessage;
	private long logId;
	private String params;
	
	/** 网站用户标识 **/
	String webUserFlat;
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
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
}
