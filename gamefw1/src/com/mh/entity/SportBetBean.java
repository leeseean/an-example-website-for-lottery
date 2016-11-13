package com.mh.entity;

import java.util.List;

public class SportBetBean {
	String matchType;// 综合过关是足球还是篮球
	String tagP3;// 格式:time_matchType_p3
	String gold; //交易金额
	String betWagersId;//订单号
	
	List<SportBet> sportBetList;

	public String getTagP3() {
		return tagP3;
	}

	public void setTagP3(String tagP3) {
		this.tagP3 = tagP3;
	}

	public List<SportBet> getSportBetList() {
		return sportBetList;
	}

	public void setSportBetList(List<SportBet> sportBetList) {
		this.sportBetList = sportBetList;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	
	public String getBetWagersId() {
		return betWagersId;
	}

	public void setBetWagersId(String betWagersId) {
		this.betWagersId = betWagersId;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}


}
