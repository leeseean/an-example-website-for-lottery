/**   
* 文件名称: TMatchRelate.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-17 下午2:33:01<br/>
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
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-17 下午2:33:01<br/>
 */

@Entity
@Table(name = "t_match_relate")
public class TMatchRelate  implements Serializable {

 
	private static final long serialVersionUID = 1L;


	// 流水号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "ID", nullable = false)
    private Integer id;

	
	@Column(name = "match_id")
    private String matchId;

 
	@Column(name = "match_time")
    private String matchTime;

	@Column(name = "match_type")
    private String matchType;

	@Column(name = "league")
    private String league;

	@Column(name = "team_h")
    private String teamH;

	//客队名称
	@Column(name = "team_c")
    private String teamC;

	//球赛标识
	@Column(name = "match_no")
    private String matchNo;

	//赛事标识,所有gid
	@Column(name = "gid")
    private String gid;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
    private Date createTime;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
    private Date modifyTime;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt_4_time")
    private Date gmt4Time;

	//半场客队球数
	@Column(name = "hr_score_c")
    private Integer hrScoreC;

	//半场主队球数
	@Column(name = "hr_score_h")
    private Integer hrScoreH;

	//全场客队球数
	@Column(name = "fl_score_c")
    private Integer flScoreC;

	//全场主队球数
	@Column(name = "fl_score_h")
    private Integer flScoreH;

	//非正常比赛
	@Column(name = "hr_score_c_cal")
    private String hrScoreCCal;

	@Column(name = "hr_score_h_cal")
    private String hrScoreHCal;

	
	
	@Column(name = "fl_score_c_cal")
    private String flScoreCCal;

	@Column(name = "fl_score_h_cal")
    private String flScoreHCal;

	@Column(name = "yaozhe_hr_score_c")
    private Integer yaozheHrScoreC;

	@Column(name = "yaozhe_hr_score_h")
    private Integer yaozheHrScoreH;

	@Column(name = "yaozhe_fl_score_c")
    private Integer yaozheFlScoreC;

	@Column(name = "yaozhe_fl_score_h")
    private Integer yaozheFlScoreH;

	@Column(name = "stage_c_1")
    private Integer stageC1;

	@Column(name = "stage_c_2")
    private Integer stageC2;

	@Column(name = "stage_c_3")
    private Integer stageC3;

	@Column(name = "stage_c_4")
    private Integer stageC4;

	@Column(name = "stage_c_s")
    private Integer stageCS;

	@Column(name = "stage_c_x")
    private Integer stageCX;

	@Column(name = "stage_c_f")
    private Integer stageCF;

	@Column(name = "stage_c_a")
    private Integer stageCA;

	@Column(name = "stage_h_1")
    private Integer stageH1;

	@Column(name = "stage_h_2")
    private Integer stageH2;

	@Column(name = "stage_h_3")
    private Integer stageH3;

	@Column(name = "stage_h_4")
    private Integer stageH4;

	@Column(name = "stage_h_s")
    private Integer stageHS;

	@Column(name = "stage_h_x")
    private Integer stageHX;

   
	//全场（主）
	@Column(name = "stage_h_f")
    private Integer stageHF;

	//加时赛（主）
	@Column(name = "stage_h_a")
    private Integer stageHA;

	//赛事是否有效（-1未完赛、1有效、0无效）
	@Column(name = "match_status")
    private Integer matchStatus;

	//比赛日期
	@Column(name = "match_date")
    private String matchDate;

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

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
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

	public Date getGmt4Time() {
		return gmt4Time;
	}

	public void setGmt4Time(Date gmt4Time) {
		this.gmt4Time = gmt4Time;
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

	public Integer getFlScoreC() {
		return flScoreC;
	}

	public void setFlScoreC(Integer flScoreC) {
		this.flScoreC = flScoreC;
	}

	public Integer getFlScoreH() {
		return flScoreH;
	}

	public void setFlScoreH(Integer flScoreH) {
		this.flScoreH = flScoreH;
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

	public String getFlScoreCCal() {
		return flScoreCCal;
	}

	public void setFlScoreCCal(String flScoreCCal) {
		this.flScoreCCal = flScoreCCal;
	}

	public String getFlScoreHCal() {
		return flScoreHCal;
	}

	public void setFlScoreHCal(String flScoreHCal) {
		this.flScoreHCal = flScoreHCal;
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

	public Integer getYaozheFlScoreC() {
		return yaozheFlScoreC;
	}

	public void setYaozheFlScoreC(Integer yaozheFlScoreC) {
		this.yaozheFlScoreC = yaozheFlScoreC;
	}

	public Integer getYaozheFlScoreH() {
		return yaozheFlScoreH;
	}

	public void setYaozheFlScoreH(Integer yaozheFlScoreH) {
		this.yaozheFlScoreH = yaozheFlScoreH;
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

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	
	
	
	
}
