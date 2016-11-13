package com.mh.entity;

import java.io.Serializable;

public class SportBetDetail extends SportBet implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订单号
	 */
	private String orderNO;
	
	/**
	 * 主队
	 */
	private String teamH;
	
	/**
	 * 客队
	 */
	private String teamC;
	
	/**
	 * 是否是滚球赛事
	 */
	private boolean roll;
	/**
	 * 
	 */
	private String btypeName;
	
	private String periodName;
	
	private String teamName;
	
	/**
	 * 下注金额
	 */
	private double money;
	/**
	 * 返回信息
	 */
	private String msg;
	/**
	 * 标识是否下注成功
	 */
	private boolean flag;
	
	/**
	 * 下单成功后返回url
	 */
	private String url;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBtypeName() {
		return btypeName;
	}

	public void setBtypeName(String btypeName) {
		this.btypeName = btypeName;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isRoll() {
		return roll;
	}

	public void setRoll(boolean roll) {
		this.roll = roll;
	}

	public String getTeamH() {
		return teamH;
	}

	public void setTeamH(String teamH) {
		this.teamH = teamH;
	}

	public String getTeamC() {
		return teamC;
	}

	public void setTeamC(String teamC) {
		this.teamC = teamC;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}
	
	
}
