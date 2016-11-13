package com.mh.ifc.http;

public class MgResResult {
	String data;
	int code;

	int totalCount;
	int pageIndex;
	int pageCount;
	int pageSize;

	boolean success;
	String message;
	String exception;

	/** 网站用户标识 **/
	String webUserFlat;

	public String getData() {
		return "null".equals(data)?"":data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getWebUserFlat() {
		return webUserFlat;
	}

	public void setWebUserFlat(String webUserFlat) {
		this.webUserFlat = webUserFlat;
	}

}
