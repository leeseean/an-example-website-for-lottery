package com.mh.ifc.http;

public class AbResResult {
	String error_code;
	String message;
	String client;
	Double balance;
	String currency;
	String gameLoginUrl;
	String page;
	String histories;
	String startTime;
	String endTime;
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
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getGameLoginUrl() {
		return gameLoginUrl;
	}
	public void setGameLoginUrl(String gameLoginUrl) {
		this.gameLoginUrl = gameLoginUrl;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getHistories() {
		return histories;
	}
	public void setHistories(String histories) {
		this.histories = histories;
	}
}
