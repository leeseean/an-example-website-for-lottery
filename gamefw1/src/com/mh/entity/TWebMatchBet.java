package com.mh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
 

@Entity
@Table(name = "T_WEB_MATCH_BET")
public class TWebMatchBet implements Serializable{

 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//自增长
	@Column(name = "ID",nullable = false)
    private Integer id;

	@Column(name = "web_flat")
    private String webFlat;

	@Column(name = "web_remark")
    private String webRemark;

	@Column(name = "match_rtype")
    private String matchRtype;

	@Column(name = "bet_type")
    private Integer betType;

	@Column(name = "bet_user_name")
    private String betUserName;

	@Column(name = "bet_match_content")
    private String betMatchContent;

	@Column(name = "bet_match_result")
    private Integer betMatchResult;

	@Column(name = "bet_wagers_id")
    private String betWagersId;

	@Column(name = "bet_in")
    private Double betIn;

	@Column(name = "bet_income")
    private Double betIncome;
	
	@Column(name = "bet_can_win")
	private Double betCanWin;

	@Column(name = "bet_usr_win")
    private Double betUsrWin;

	@Column(name = "bet_net_win")
    private Double betNetWin;

	@Column(name = "bet_time")
    private Date betTime;

	@Column(name = "status")
    private Integer status;

	@Column(name = "bet_status")
    private Integer betStatus;

	@Column(name = "bet_void_reason")
    private String betVoidReason;

	@Column(name = "time_type")
    private String timeType;

	@Column(name = "bet_sport_type")
    private String betSportType;

	@Column(name = "bet_sport_name")
    private String betSportName;

	@Column(name = "bet_match_ids")
    private String betMatchIds;

	@Column(name = "bet_settled_time")
    private Date betSettledTime;

	@Column(name = "remark")
    private String remark;

	@Column(name = "bet_member_ip_address")
    private String betMemberIpAddress;

	@Column(name = "order_time")
    private Date orderTime;

	@Column(name = "create_time")
    private Date createTime;

	@Column(name = "confirm_time")
    private Date confirmTime;

	@Column(name = "modify_time")
    private Date modifyTime;

	@Column(name = "bet_user_agent")
    private String betUserAgent;

	@Column(name = "back_water")
    private String backWater;

	@Column(name = "back_water_status")
    private Integer backWaterStatus;

	@Column(name = "back_water_time")
    private Date backWaterTime;

	@Column(name = "back_sys_user_name")
    private String backSysUserName;
	
//	@OneToMany(targetEntity=TWebMatchDetail.class,cascade=CascadeType.ALL)
//	@Fetch(FetchMode.JOIN)
//	//updatable=false很关键，如果没有它，在级联删除的时候就会报错(反转的问题)
//	@JoinColumn(name="betWagersId",updatable=false)
//	private List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
	
	@Transient
	private List<TWebMatchDetail> details;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWebFlat() {
		return webFlat;
	}

	public void setWebFlat(String webFlat) {
		this.webFlat = webFlat;
	}

	public String getWebRemark() {
		return webRemark;
	}

	public void setWebRemark(String webRemark) {
		this.webRemark = webRemark;
	}

	public String getMatchRtype() {
		return matchRtype;
	}

	public void setMatchRtype(String matchRtype) {
		this.matchRtype = matchRtype;
	}

	public Integer getBetType() {
		return betType;
	}

	public void setBetType(Integer betType) {
		this.betType = betType;
	}

	public String getBetUserName() {
		return betUserName;
	}

	public void setBetUserName(String betUserName) {
		this.betUserName = betUserName;
	}

	public String getBetMatchContent() {
		return betMatchContent;
	}

	public void setBetMatchContent(String betMatchContent) {
		this.betMatchContent = betMatchContent;
	}

	public Integer getBetMatchResult() {
		return betMatchResult;
	}

	public void setBetMatchResult(Integer betMatchResult) {
		this.betMatchResult = betMatchResult;
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
	
	public Double getBetCanWin() {
		return betCanWin;
	}

	public void setBetCanWin(Double betCanWin) {
		this.betCanWin = betCanWin;
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

	public Integer getBetStatus() {
		return betStatus;
	}

	public void setBetStatus(Integer betStatus) {
		this.betStatus = betStatus;
	}

	public String getBetVoidReason() {
		return betVoidReason;
	}

	public void setBetVoidReason(String betVoidReason) {
		this.betVoidReason = betVoidReason;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getBetSportType() {
		return betSportType;
	}

	public void setBetSportType(String betSportType) {
		this.betSportType = betSportType;
	}

	public String getBetSportName() {
		return betSportName;
	}

	public void setBetSportName(String betSportName) {
		this.betSportName = betSportName;
	}

	public String getBetMatchIds() {
		return betMatchIds;
	}

	public void setBetMatchIds(String betMatchIds) {
		this.betMatchIds = betMatchIds;
	}

	public Date getBetSettledTime() {
		return betSettledTime;
	}

	public void setBetSettledTime(Date betSettledTime) {
		this.betSettledTime = betSettledTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBetMemberIpAddress() {
		return betMemberIpAddress;
	}

	public void setBetMemberIpAddress(String betMemberIpAddress) {
		this.betMemberIpAddress = betMemberIpAddress;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getBetUserAgent() {
		return betUserAgent;
	}

	public void setBetUserAgent(String betUserAgent) {
		this.betUserAgent = betUserAgent;
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
	
	public List<TWebMatchDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TWebMatchDetail> details) {
		this.details = details;
	}
	
}