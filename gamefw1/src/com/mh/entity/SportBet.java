package com.mh.entity;

public class SportBet {
	
	
	String gid;
	String timeType;
	String matchType;// FT BK
	String rtype;
	String btype;
	String dtype;
	String selection;// 主队或客队
	String period;// 上半场或全场
	
	
	String rtypeName;
	
	

	String league;
	String team1;
	String team2;
	String vs;

	String odds;// 赔率
	String oddsName;// 盘口类型
	String oddsDes;// 盘口类型描述 ds-period

	String gidm;// 同一赛事标识

	/** 其他信息 **/
	String oddsMinus = "0";// 赔率减

	/** 限额 **/
	String limitBet;// 单注最高限额
	String limitMatch;// 单场最高限额
	String limitBetMin;// 单注最低金额
	
	

	
	

	public String getLimitBetMin() {
		return limitBetMin;
	}

	public void setLimitBetMin(String limitBetMin) {
		this.limitBetMin = limitBetMin;
	}

	public String getLimitBet() {
		return limitBet;
	}

	public void setLimitBet(String limitBet) {
		this.limitBet = limitBet;
	}

	public String getLimitMatch() {
		return limitMatch;
	}

	public void setLimitMatch(String limitMatch) {
		this.limitMatch = limitMatch;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getRtypeName() {
		return rtypeName;
	}

	public void setRtypeName(String rtypeName) {
		this.rtypeName = rtypeName;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public String getOdds() {
		return odds;
	}

	public void setOdds(String odds) {
		this.odds = odds;
	}

	public String getOddsName() {
		return oddsName;
	}
	

	public void setOddsName(String oddsName) {
		this.oddsName = oddsName;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getOddsDes() {
		return oddsDes;
	}

	public void setOddsDes(String oddsDes) {
		this.oddsDes = oddsDes;
	}

	public String getVs() {
		return vs;
	}

	public void setVs(String vs) {
		this.vs = vs;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public String getOddsMinus() {
		return oddsMinus;
	}

	public void setOddsMinus(String oddsMinus) {
		this.oddsMinus = oddsMinus;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getGidm() {
		return gidm;
	}

	public void setGidm(String gidm) {
		this.gidm = gidm;
	}


	
	
}
