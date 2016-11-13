package com.mh.ifc.http;


public class NsbResResult {
	private String error_code;
	private String message;
	private String sessionToken;
	private String Data;
	private int LastVersionKey;
	private int TotalRecord;
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public int getLastVersionKey() {
		return LastVersionKey;
	}
	public void setLastVersionKey(int lastVersionKey) {
		LastVersionKey = lastVersionKey;
	}
	public int getTotalRecord() {
		return TotalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		TotalRecord = totalRecord;
	}
	
}
