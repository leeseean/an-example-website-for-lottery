package com.mh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_web_match_detail")
public class TWebMatchDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "bet_wagers_id")
	private String betWagersId;

	@Column(name = "gid")
	private String gid;

	@Column(name = "league")
	private String league;

	@Column(name = "team_h")
	private String teamH;

	@Column(name = "team_c")
	private String teamC;

	@Column(name = "rtype_name")
	private String rtypeName;

	@Column(name = "time_type")
	private String timeType;

	@Column(name = "match_type")
	private String matchType;

	@Column(name = "rtype")
	private String rtype;

	@Column(name = "btype")
	private String btype;

	@Column(name = "dtype")
	private String dtype;

	@Column(name = "selection")
	private String selection;

	@Column(name = "period")
	private String period;

	@Column(name = "bet_index")
	private String betIndex;

	@Column(name = "leg")
	private String leg;

	@Column(name = "bet_odds_des")
	private String betOddsDes;

	@Column(name = "bet_odds")
	private String betOdds;

	@Column(name = "bet_odds_minus")
	private Integer betOddsMinus;

	@Column(name = "bet_odds_name")
	private String betOddsName;

	@Column(name = "bet_rq_team")
	private String betRqTeam;

	@Column(name = "bet_rq")
	private String betRq;

	@Column(name = "bet_rq_team_h")
	private String betRqTeamH;

	@Column(name = "bet_rq_h")
	private String betRqH;

	@Column(name = "bet_dx")
	private String betDx;

	@Column(name = "bet_dx_h")
	private String betDxH;

	@Column(name = "bet_score_h")
	private String betScoreH;

	@Column(name = "bet_score_c")
	private String betScoreC;

	@Column(name = "bet_score_h_cur")
	private String betScoreHCur;

	@Column(name = "bet_score_c_cur")
	private String betScoreCCur;

	@Column(name = "bet_time")
	private Date betTime;

	@Column(name = "match_time")
	private Date matchTime;

	@Column(name = "status")
	private Integer status;

	@Column(name = "bet_status")
	private Integer betStatus;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "modify_time")
	private Date modifyTime;

	@Column(name = "tmp1")
	private String tmp1;

	@Column(name = "tmp2")
	private String tmp2;

	@Column(name = "tmp3")
	private String tmp3;

	@Column(name = "bet_vs")
	private String betVs;

	@Column(name = "match_id")
	private String matchId;
	
	@Column(name = "bet_uuid")
	private String betUuid;

	// 多对一，@JoinColumn与@column类似，指定映射的数据库字段
	// @ManyToOne(targetEntity = TWebMatchBet.class)
	// @JoinColumn(name="betWagersId",updatable=false)
	// private TWebMatchBet matchBet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBetWagersId() {
		return betWagersId;
	}

	public void setBetWagersId(String betWagersId) {
		this.betWagersId = betWagersId;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
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

	public String getRtypeName() {
		return rtypeName;
	}

	public void setRtypeName(String rtypeName) {
		this.rtypeName = rtypeName;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getBetIndex() {
		return betIndex;
	}

	public void setBetIndex(String betIndex) {
		this.betIndex = betIndex;
	}

	public String getLeg() {
		return leg;
	}

	public void setLeg(String leg) {
		this.leg = leg;
	}

	public String getBetOddsDes() {
		return betOddsDes;
	}

	public void setBetOddsDes(String betOddsDes) {
		this.betOddsDes = betOddsDes;
	}

	public String getBetOdds() {
		return betOdds;
	}

	public void setBetOdds(String betOdds) {
		this.betOdds = betOdds;
	}

	public Integer getBetOddsMinus() {
		return betOddsMinus;
	}

	public void setBetOddsMinus(Integer betOddsMinus) {
		this.betOddsMinus = betOddsMinus;
	}

	public String getBetOddsName() {
		return betOddsName;
	}

	public void setBetOddsName(String betOddsName) {
		this.betOddsName = betOddsName;
	}

	public String getBetRqTeam() {
		return betRqTeam;
	}

	public void setBetRqTeam(String betRqTeam) {
		this.betRqTeam = betRqTeam;
	}

	public String getBetRq() {
		return betRq;
	}

	public void setBetRq(String betRq) {
		this.betRq = betRq;
	}

	public String getBetRqTeamH() {
		return betRqTeamH;
	}

	public void setBetRqTeamH(String betRqTeamH) {
		this.betRqTeamH = betRqTeamH;
	}

	public String getBetRqH() {
		return betRqH;
	}

	public void setBetRqH(String betRqH) {
		this.betRqH = betRqH;
	}

	public String getBetDx() {
		return betDx;
	}

	public void setBetDx(String betDx) {
		this.betDx = betDx;
	}

	public String getBetDxH() {
		return betDxH;
	}

	public void setBetDxH(String betDxH) {
		this.betDxH = betDxH;
	}

	public String getBetScoreH() {
		return betScoreH;
	}

	public void setBetScoreH(String betScoreH) {
		this.betScoreH = betScoreH;
	}

	public String getBetScoreC() {
		return betScoreC;
	}

	public void setBetScoreC(String betScoreC) {
		this.betScoreC = betScoreC;
	}

	public String getBetScoreHCur() {
		return betScoreHCur;
	}

	public void setBetScoreHCur(String betScoreHCur) {
		this.betScoreHCur = betScoreHCur;
	}

	public String getBetScoreCCur() {
		return betScoreCCur;
	}

	public void setBetScoreCCur(String betScoreCCur) {
		this.betScoreCCur = betScoreCCur;
	}

	public Date getBetTime() {
		return betTime;
	}

	public void setBetTime(Date betTime) {
		this.betTime = betTime;
	}

	public Date getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
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

	public String getTmp1() {
		return tmp1;
	}

	public void setTmp1(String tmp1) {
		this.tmp1 = tmp1;
	}

	public String getTmp2() {
		return tmp2;
	}

	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
	}

	public String getTmp3() {
		return tmp3;
	}

	public void setTmp3(String tmp3) {
		this.tmp3 = tmp3;
	}

	public String getBetVs() {
		return betVs;
	}

	public void setBetVs(String betVs) {
		this.betVs = betVs;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	// public TWebMatchBet getMatchBet() {
	// return matchBet;
	// }
	//
	// public void setMatchBet(TWebMatchBet matchBet) {
	// this.matchBet = matchBet;
	// }
	@Transient
	private SportScore score;

	public SportScore getScore() {
		return score;
	}

	public void setScore(SportScore score) {
		this.score = score;
	}

	public String getBetUuid() {
		return betUuid;
	}

	public void setBetUuid(String betUuid) {
		this.betUuid = betUuid;
	}
}