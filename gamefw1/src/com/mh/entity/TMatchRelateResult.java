/**   
* 文件名称: MatchRelateResult.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-13 下午4:39:25<br/>
*/
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

/** 
 * 类描述: TODO<br/>赛程表	
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-13 下午4:39:25<br/>
 */
@Entity
@Table(name = "t_match_relate_result")
public class TMatchRelateResult implements Serializable {
	private static final long serialVersionUID = 1L;

	// 流水号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "match_id")
	private String matchId;//t_match_relate表的id值

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "match_time")
	private Date matchTime;//球赛时间
	
	@Column(name = "match_type")
	private String matchType;//FT足球 BK篮球
	
	@Column(name = "league")
	private String league;//联盟名称[比赛所属联赛名称]
	
	@Column(name = "team_h")
	private String teamH;//主队名称
	
	@Column(name = "team_c")
	private String teamC;//客队名称
	
	@Column(name = "match_no")
	private String matchNo;//球赛标识
	
	@Column(name = "gid")
	private String gid;//赛事标识

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt4_time")
	private Date gmt4time;//美东时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;//修改时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;//创建时间

	@Column(name = "hr_score_c")
	private Integer hrScoreC;//半场客队球数
	
	@Column(name = "hr_score_h")
	private Integer hrScoreH;//半场主队球数
	
	@Column(name = "fl_score_c")
	private Integer FLScoreC;//全场客队球数
	
	@Column(name = "fl_score_h")
	private Integer FLScoreh;//全场主队球数
	
	@Column(name = "hr_score_c_cal")
	private String hrScoreCCal;//非正常比赛
	
	@Column(name = "hr_score_h_cal")
	private String hrScoreHCal;//非正常比赛
	
	@Column(name = "fl_score_c_cal")
	private String flScore_CCal;//非正常比赛
	
	@Column(name = "fl_score_h_cal")
	private String flCcoreHCal;//非正常比赛
	
	@Column(name = "yaozhe_hr_score_c")
	private Integer yaozheHrScoreC;//腰斩客主球数
	
	@Column(name = "yaozhe_hr_score_h")
	private Integer yaozheHrScoreH;//腰斩客主球数
	
	@Column(name = "yaozhe_fl_score_c")
	private Integer yaozheF1ScoreC;//腰斩客主球数
	
	@Column(name = "yaozhe_fl_score_h")
	private Integer yaozheF1ScoreH;//腰斩客主球数
	
	@Column(name = "stage_c_1")
	private Integer stageC1;//第一节（客）
	
	@Column(name = "stage_c_2")
	private Integer stageC2;//第二节（客）
	
	@Column(name = "stage_c_3")
	private Integer stageC3;//第三节（客）
	
	@Column(name = "stage_c_4")
	private Integer stageC4;//第四节（客）
	
	@Column(name = "stage_c_s")
	private Integer stageCS;//上半场（客）	
	
	@Column(name = "stage_c_x")
	private Integer stageCX;//下半场（客）	
	
	@Column(name = "stage_c_f")
	private Integer stageCF;//全场（客）	
	
	@Column(name = "stage_c_a")
	private Integer stageCA;//加时赛（客）
	
	@Column(name = "stage_h_1")
	private Integer stageH1;//第一节（主）
	
	@Column(name = "stage_h_2")
	private Integer stageH2;//第二节（主）
	
	@Column(name = "stage_h_3")
	private Integer stageH3;//第三节（主）
	
	@Column(name = "stage_h_4")
	private Integer stageH4;//第四节（主）
	
	@Column(name = "stage_h_s")
	private Integer stageHS;//上半场（主）
	
	@Column(name = "stage_h_x")
	private Integer stageHX;//下半场（主）
	
	@Column(name = "stage_h_f")
	private Integer stageHF;//全场（主）
	
	@Column(name = "stage_h_a")
	private Integer stageHA;//加时赛（主）
	
	@Column(name = "match_status")
	private Integer matchStatus;//赛事是否有效（1有效，0无效）
	
	@Column(name = "status")
	private Integer status;//结算处理状态（0未处理，1结算中，2结算完成，4结算失败，主要是针对非P3结算）
	
	@Column(name = "remark")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public Date getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
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

	public String getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Date getGmt4time() {
		return gmt4time;
	}

	public void setGmt4time(Date gmt4time) {
		this.gmt4time = gmt4time;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getHrScoreC() {
		return hrScoreC;
	}

	public void setHrScoreC(Integer hrScoreC) {
		this.hrScoreC = hrScoreC;
	}

	public Integer getHrScoreH() {
		return hrScoreH;
	}

	public void setHrScoreH(Integer hrScoreH) {
		this.hrScoreH = hrScoreH;
	}

	public Integer getFLScoreC() {
		return FLScoreC;
	}

	public void setFLScoreC(Integer fLScoreC) {
		FLScoreC = fLScoreC;
	}

	public Integer getFLScoreh() {
		return FLScoreh;
	}

	public void setFLScoreh(Integer fLScoreh) {
		FLScoreh = fLScoreh;
	}

	public String getHrScoreCCal() {
		return hrScoreCCal;
	}

	public void setHrScoreCCal(String hrScoreCCal) {
		this.hrScoreCCal = hrScoreCCal;
	}

	public String getHrScoreHCal() {
		return hrScoreHCal;
	}

	public void setHrScoreHCal(String hrScoreHCal) {
		this.hrScoreHCal = hrScoreHCal;
	}

	public String getFlScore_CCal() {
		return flScore_CCal;
	}

	public void setFlScore_CCal(String flScore_CCal) {
		this.flScore_CCal = flScore_CCal;
	}

	public String getFlCcoreHCal() {
		return flCcoreHCal;
	}

	public void setFlCcoreHCal(String flCcoreHCal) {
		this.flCcoreHCal = flCcoreHCal;
	}

	public Integer getYaozheHrScoreC() {
		return yaozheHrScoreC;
	}

	public void setYaozheHrScoreC(Integer yaozheHrScoreC) {
		this.yaozheHrScoreC = yaozheHrScoreC;
	}

	public Integer getYaozheHrScoreH() {
		return yaozheHrScoreH;
	}

	public void setYaozheHrScoreH(Integer yaozheHrScoreH) {
		this.yaozheHrScoreH = yaozheHrScoreH;
	}

	public Integer getYaozheF1ScoreC() {
		return yaozheF1ScoreC;
	}

	public void setYaozheF1ScoreC(Integer yaozheF1ScoreC) {
		this.yaozheF1ScoreC = yaozheF1ScoreC;
	}

	public Integer getYaozheF1ScoreH() {
		return yaozheF1ScoreH;
	}

	public void setYaozheF1ScoreH(Integer yaozheF1ScoreH) {
		this.yaozheF1ScoreH = yaozheF1ScoreH;
	}

	public Integer getStageC1() {
		return stageC1;
	}

	public void setStageC1(Integer stageC1) {
		this.stageC1 = stageC1;
	}

	public Integer getStageC2() {
		return stageC2;
	}

	public void setStageC2(Integer stageC2) {
		this.stageC2 = stageC2;
	}

	public Integer getStageC3() {
		return stageC3;
	}

	public void setStageC3(Integer stageC3) {
		this.stageC3 = stageC3;
	}

	public Integer getStageC4() {
		return stageC4;
	}

	public void setStageC4(Integer stageC4) {
		this.stageC4 = stageC4;
	}

	public Integer getStageCS() {
		return stageCS;
	}

	public void setStageCS(Integer stageCS) {
		this.stageCS = stageCS;
	}

	public Integer getStageCX() {
		return stageCX;
	}

	public void setStageCX(Integer stageCX) {
		this.stageCX = stageCX;
	}

	public Integer getStageCF() {
		return stageCF;
	}

	public void setStageCF(Integer stageCF) {
		this.stageCF = stageCF;
	}

	public Integer getStageCA() {
		return stageCA;
	}

	public void setStageCA(Integer stageCA) {
		this.stageCA = stageCA;
	}

	public Integer getStageH1() {
		return stageH1;
	}

	public void setStageH1(Integer stageH1) {
		this.stageH1 = stageH1;
	}

	public Integer getStageH2() {
		return stageH2;
	}

	public void setStageH2(Integer stageH2) {
		this.stageH2 = stageH2;
	}

	public Integer getStageH3() {
		return stageH3;
	}

	public void setStageH3(Integer stageH3) {
		this.stageH3 = stageH3;
	}

	public Integer getStageH4() {
		return stageH4;
	}

	public void setStageH4(Integer stageH4) {
		this.stageH4 = stageH4;
	}

	public Integer getStageHS() {
		return stageHS;
	}

	public void setStageHS(Integer stageHS) {
		this.stageHS = stageHS;
	}

	public Integer getStageHX() {
		return stageHX;
	}

	public void setStageHX(Integer stageHX) {
		this.stageHX = stageHX;
	}

	public Integer getStageHF() {
		return stageHF;
	}

	public void setStageHF(Integer stageHF) {
		this.stageHF = stageHF;
	}

	public Integer getStageHA() {
		return stageHA;
	}

	public void setStageHA(Integer stageHA) {
		this.stageHA = stageHA;
	}

	public Integer getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(Integer matchStatus) {
		this.matchStatus = matchStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
 
	
}
