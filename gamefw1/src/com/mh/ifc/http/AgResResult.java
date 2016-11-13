package com.mh.ifc.http;

public class AgResResult {
	String info;
	String msg;

	String webUserFlat;

	public String getInfo() {
		return "null".equals(info)?"":info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getWebUserFlat() {
		return webUserFlat;
	}

	public void setWebUserFlat(String webUserFlat) {
		this.webUserFlat = webUserFlat;
	}

}
