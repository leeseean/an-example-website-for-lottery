package com.mh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
@Entity
@Table(name = "t_bet_record_mg")
public class TBetRecordMg implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "web_flag")
	private String webFlag;
	
	@Column(name = "web_remark")
	private String webRemark;
	
	@Column(name = "bet_user_name")
	private String betUserName;
	
	@Column(name = "bet_flat_account")
	private String betFlatAccount;
	
	@Column(name = "bet_game_content")
	private String betGameContent;
	
	@Column(name = "bet_game_result")
	private String betGameResult;
	
	@Column(name = "bet_content")
	private String betContent;
	
	@Column(name = "bet_game_hall")
	private String betGameHall;
	
	@Column(name = "bet_game_room")
	private String betGameRoom;
	
	@Column(name = "bet_game_table")
	private String betGameTable;
	
	@Column(name = "bet_game_code")
	private String betGameCode;
	
	@Column(name = "bet_Wagers_id")
	private String betWagersId;
	
	@Column(name = "bet_in")
	private Double betIn;
	
	@Column(name = "bet_income")
	private Double betIncome;
	
	@Column(name = "bet_usr_win")
	private Double betUsrWin;
	
	@Column(name = "bet_net_win")
	private Double betNetWin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bet_time")
	private Date betTime;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "back_water")
	private String backWater;
	
	@Column(name = "back_water_status")
	private Integer backWaterStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "back_water_time")
	private Date backWaterTime;
	
	@Column(name = "back_sys_user_name")
	private String backSysUserName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;
	
	@Column(name = "bet_login_ip")
	private String betLoginIp;
		
	@Column(name = "bet_user_agent")
	private String betUserAgent;
	@Transient
	private String beginTime;
	@Transient
	private String endTime;
	@Transient
	private Integer count;
	
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWebFlag() {
		return webFlag;
	}

	public void setWebFlag(String webFlag) {
		this.webFlag = webFlag;
	}

	public String getWebRemark() {
		return webRemark;
	}

	public void setWebRemark(String webRemark) {
		this.webRemark = webRemark;
	}

	public String getBetUserName() {
		return betUserName;
	}

	public void setBetUserName(String betUserName) {
		this.betUserName = betUserName;
	}

	public String getBetFlatAccount() {
		return betFlatAccount;
	}

	public void setBetFlatAccount(String betFlatAccount) {
		this.betFlatAccount = betFlatAccount;
	}

	public String getBetGameContent() {
		return betGameContent;
	}

	public void setBetGameContent(String betGameContent) {
		this.betGameContent = betGameContent;
	}

	public String getBetGameResult() {
		return betGameResult;
	}

	public void setBetGameResult(String betGameResult) {
		this.betGameResult = betGameResult;
	}

	public String getBetContent() {
		return betContent;
	}

	public void setBetContent(String betContent) {
		this.betContent = betContent;
	}

	public String getBetGameHall() {
		return betGameHall;
	}

	public void setBetGameHall(String betGameHall) {
		this.betGameHall = betGameHall;
	}

	public String getBetGameRoom() {
		return betGameRoom;
	}

	public void setBetGameRoom(String betGameRoom) {
		this.betGameRoom = betGameRoom;
	}

	public String getBetGameTable() {
		return betGameTable;
	}

	public void setBetGameTable(String betGameTable) {
		this.betGameTable = betGameTable;
	}

	public String getBetGameCode() {
		return betGameCode;
	}

	public void setBetGameCode(String betGameCode) {
		this.betGameCode = betGameCode;
	}

	public String getBetWagersId() {
		return betWagersId;
	}

	public void setBetWagersId(String betWagersId) {
		this.betWagersId = betWagersId;
	}

	public Double getBetIn() {
		return betIn;
	}

	public void setBetIn(Double betIn) {
		this.betIn = betIn;
	}

	public Double getBetIncome() {
		return betIncome;
	}

	public void setBetIncome(Double betIncome) {
		this.betIncome = betIncome;
	}

	public Double getBetUsrWin() {
		return betUsrWin;
	}

	public void setBetUsrWin(Double betUsrWin) {
		this.betUsrWin = betUsrWin;
	}

	public Double getBetNetWin() {
		return betNetWin;
	}

	public void setBetNetWin(Double betNetWin) {
		this.betNetWin = betNetWin;
	}

	public Date getBetTime() {
		return betTime;
	}

	public void setBetTime(Date betTime) {
		this.betTime = betTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBackWater() {
		return backWater;
	}

	public void setBackWater(String backWater) {
		this.backWater = backWater;
	}

	public Integer getBackWaterStatus() {
		return backWaterStatus;
	}

	public void setBackWaterStatus(Integer backWaterStatus) {
		this.backWaterStatus = backWaterStatus;
	}

	public Date getBackWaterTime() {
		return backWaterTime;
	}

	public void setBackWaterTime(Date backWaterTime) {
		this.backWaterTime = backWaterTime;
	}

	public String getBackSysUserName() {
		return backSysUserName;
	}

	public void setBackSysUserName(String backSysUserName) {
		this.backSysUserName = backSysUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getBetLoginIp() {
		return betLoginIp;
	}

	public void setBetLoginIp(String betLoginIp) {
		this.betLoginIp = betLoginIp;
	}

	public String getBetUserAgent() {
		return betUserAgent;
	}

	public void setBetUserAgent(String betUserAgent) {
		this.betUserAgent = betUserAgent;
	}
}
