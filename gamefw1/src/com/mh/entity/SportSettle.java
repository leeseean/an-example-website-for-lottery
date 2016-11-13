package com.mh.entity;

public class SportSettle {
	double optMoney = 0;// 操作金额
	String betWagersId;// 注单号
	String remark;// 备注
	double in = 0;// 投注额
	double income = 0;// 有效投注
	double usrWin;// 用户输赢（正赢，负输）
	double netWin;// 平台输赢（正赢，负输）

	double optMoneyOdds = 0;// 综合过关有效赔率

	public double getUsrWin() {
		return usrWin;
	}

	public void setUsrWin(double usrWin) {
		this.usrWin = usrWin;
	}

	public double getNetWin() {
		return netWin;
	}

	public void setNetWin(double netWin) {
		this.netWin = netWin;
	}

	public double getOptMoney() {
		return optMoney;
	}

	public void setOptMoney(double optMoney) {
		this.optMoney = optMoney;
	}

	public String getBetWagersId() {
		return betWagersId;
	}

	public void setBetWagersId(String betWagersId) {
		this.betWagersId = betWagersId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getIn() {
		return in;
	}

	public void setIn(double in) {
		this.in = in;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getOptMoneyOdds() {
		return optMoneyOdds;
	}

	public void setOptMoneyOdds(double optMoneyOdds) {
		this.optMoneyOdds = optMoneyOdds;
	}

}
