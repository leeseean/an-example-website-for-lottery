/**   
* 文件名称: MatchFtLimit.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-13 下午5:30:31<br/>
*/
package com.mh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * 类描述: TODO<br/>滚球表	
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-13 下午5:30:31<br/>
 */
@Entity
@Table(name = "t_match_ft_limit")
public class TMatchFtLimit implements Serializable {
	private static final long serialVersionUID = 1L;

	// 流水号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "dy_bet")
	private String dyBet;//独赢
	
	@Column(name = "dy_match")
	private String dyMatch;
	
	@Column(name = "rq_bet")
	private String rqBet;//让球
	
	@Column(name = "rq_match")
	private String rqMatch;
	
	@Column(name = "dx_bet")
	private String dxBet;//大小
	
	@Column(name = "dx_match")
	private String dxMatch;
	
	@Column(name = "ds_bet")
	private String dsBet;//单双
	
	@Column(name = "ds_match")
	private String dsMatch;
	
	@Column(name = "dy_h_bet")
	private String dyHBet;//上半独赢
	
	@Column(name = "dy_h_match")
	private String dHMatch;
	
	@Column(name = "rq_h_bet")
	private String rqHBet;//上半让球
	
	@Column(name = "rq_h_match")
	private String rqHMatch;

	@Column(name = "dx_h_bet")
	private String dxHBet;//上半大小
	
	@Column(name = "dx_h_match")
	private String dxHMatch;
	
	@Column(name = "roll_dy_bet")
	private String rollDyBet;//滚球独赢
	
	@Column(name = "roll_dy_match")
	private String rollDyMatch;
	
	@Column(name = "roll_rq_bet")
	private String rollRqBet;//滚球让球
	
	@Column(name = "roll_rq_match")
	private String rollRqMatch;
	
	@Column(name = "roll_dx_bet")
	private String rollDxBet;//滚球大小
	
	@Column(name = "roll_dx_match")
	private String rollDxMatch;
	
	@Column(name = "roll_ds_bet")
	private String rollDsBet;//滚球单双
	
	@Column(name = "roll_ds_match")
	private String rollDsMatch;
	
	@Column(name = "roll_dy_h_bet")
	private String rollDyHBet;//滚球上半独赢
	
	@Column(name = "roll_dy_h_match")
	private String rollDyHMatch;
	
	@Column(name = "roll_rq_h_bet")
	private String rollRqHBet;//滚球上半让球
	
	@Column(name = "roll_rq_h_match")
	private String rollRqHMatch;

	@Column(name = "roll_dx_h_bet")
	private String rollDxHBet;//滚球上半大小
	
	@Column(name = "roll_dx_h_match")
	private String rollDxHMatch;
	
	@Column(name = "pd_bet")
	private String pdBet;//波胆

	@Column(name = "pd_match")
	private String pdMatch;
	
	@Column(name = "pd_h_bet")
	private String pdHBet;	//上半波胆
	
	@Column(name = "pd_h_match")
	private String pdHMatch;
	
	@Column(name = "t_bet")
	private String tBet;//总入球
	
	@Column(name = "t_match")
	private String tMatch;
	
	@Column(name = "f_bet")
	private String fBet;//全场/半场
	
	@Column(name = "f_match")
	private String fMatch;

	@Column(name = "p3_bet")
	private String p3Bet;//综合过关
	
	@Column(name = "p3_match")
	private String p3Match;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDyBet() {
		return dyBet;
	}

	public void setDyBet(String dyBet) {
		this.dyBet = dyBet;
	}

	public String getDyMatch() {
		return dyMatch;
	}

	public void setDyMatch(String dyMatch) {
		this.dyMatch = dyMatch;
	}

	public String getRqBet() {
		return rqBet;
	}

	public void setRqBet(String rqBet) {
		this.rqBet = rqBet;
	}

	public String getRqMatch() {
		return rqMatch;
	}

	public void setRqMatch(String rqMatch) {
		this.rqMatch = rqMatch;
	}

	public String getDxBet() {
		return dxBet;
	}

	public void setDxBet(String dxBet) {
		this.dxBet = dxBet;
	}

	public String getDxMatch() {
		return dxMatch;
	}

	public void setDxMatch(String dxMatch) {
		this.dxMatch = dxMatch;
	}

	public String getDsBet() {
		return dsBet;
	}

	public void setDsBet(String dsBet) {
		this.dsBet = dsBet;
	}

	public String getDsMatch() {
		return dsMatch;
	}

	public void setDsMatch(String dsMatch) {
		this.dsMatch = dsMatch;
	}

	public String getDyHBet() {
		return dyHBet;
	}

	public void setDyHBet(String dyHBet) {
		this.dyHBet = dyHBet;
	}

	public String getdHMatch() {
		return dHMatch;
	}

	public void setdHMatch(String dHMatch) {
		this.dHMatch = dHMatch;
	}

	public String getRqHBet() {
		return rqHBet;
	}

	public void setRqHBet(String rqHBet) {
		this.rqHBet = rqHBet;
	}

	public String getRqHMatch() {
		return rqHMatch;
	}

	public void setRqHMatch(String rqHMatch) {
		this.rqHMatch = rqHMatch;
	}

	public String getDxHBet() {
		return dxHBet;
	}

	public void setDxHBet(String dxHBet) {
		this.dxHBet = dxHBet;
	}

	public String getDxHMatch() {
		return dxHMatch;
	}

	public void setDxHMatch(String dxHMatch) {
		this.dxHMatch = dxHMatch;
	}

	public String getRollDyBet() {
		return rollDyBet;
	}

	public void setRollDyBet(String rollDyBet) {
		this.rollDyBet = rollDyBet;
	}

	public String getRollDyMatch() {
		return rollDyMatch;
	}

	public void setRollDyMatch(String rollDyMatch) {
		this.rollDyMatch = rollDyMatch;
	}

	public String getRollRqBet() {
		return rollRqBet;
	}

	public void setRollRqBet(String rollRqBet) {
		this.rollRqBet = rollRqBet;
	}

	public String getRollRqMatch() {
		return rollRqMatch;
	}

	public void setRollRqMatch(String rollRqMatch) {
		this.rollRqMatch = rollRqMatch;
	}

	public String getRollDxBet() {
		return rollDxBet;
	}

	public void setRollDxBet(String rollDxBet) {
		this.rollDxBet = rollDxBet;
	}

	public String getRollDxMatch() {
		return rollDxMatch;
	}

	public void setRollDxMatch(String rollDxMatch) {
		this.rollDxMatch = rollDxMatch;
	}

	public String getRollDsBet() {
		return rollDsBet;
	}

	public void setRollDsBet(String rollDsBet) {
		this.rollDsBet = rollDsBet;
	}

	public String getRollDsMatch() {
		return rollDsMatch;
	}

	public void setRollDsMatch(String rollDsMatch) {
		this.rollDsMatch = rollDsMatch;
	}

	public String getRollDyHBet() {
		return rollDyHBet;
	}

	public void setRollDyHBet(String rollDyHBet) {
		this.rollDyHBet = rollDyHBet;
	}

	public String getRollDyHMatch() {
		return rollDyHMatch;
	}

	public void setRollDyHMatch(String rollDyHMatch) {
		this.rollDyHMatch = rollDyHMatch;
	}

	public String getRollRqHBet() {
		return rollRqHBet;
	}

	public void setRollRqHBet(String rollRqHBet) {
		this.rollRqHBet = rollRqHBet;
	}

	public String getRollRqHMatch() {
		return rollRqHMatch;
	}

	public void setRollRqHMatch(String rollRqHMatch) {
		this.rollRqHMatch = rollRqHMatch;
	}

	public String getRollDxHBet() {
		return rollDxHBet;
	}

	public void setRollDxHBet(String rollDxHBet) {
		this.rollDxHBet = rollDxHBet;
	}

	public String getRollDxHMatch() {
		return rollDxHMatch;
	}

	public void setRollDxHMatch(String rollDxHMatch) {
		this.rollDxHMatch = rollDxHMatch;
	}

	public String getPdBet() {
		return pdBet;
	}

	public void setPdBet(String pdBet) {
		this.pdBet = pdBet;
	}

	public String getPdMatch() {
		return pdMatch;
	}

	public void setPdMatch(String pdMatch) {
		this.pdMatch = pdMatch;
	}

	public String getPdHBet() {
		return pdHBet;
	}

	public void setPdHBet(String pdHBet) {
		this.pdHBet = pdHBet;
	}

	public String getPdHMatch() {
		return pdHMatch;
	}

	public void setPdHMatch(String pdHMatch) {
		this.pdHMatch = pdHMatch;
	}

	public String gettBet() {
		return tBet;
	}

	public void settBet(String tBet) {
		this.tBet = tBet;
	}

	public String gettMatch() {
		return tMatch;
	}

	public void settMatch(String tMatch) {
		this.tMatch = tMatch;
	}

	public String getfBet() {
		return fBet;
	}

	public void setfBet(String fBet) {
		this.fBet = fBet;
	}

	public String getfMatch() {
		return fMatch;
	}

	public void setfMatch(String fMatch) {
		this.fMatch = fMatch;
	}

	public String getP3Bet() {
		return p3Bet;
	}

	public void setP3Bet(String p3Bet) {
		this.p3Bet = p3Bet;
	}

	public String getP3Match() {
		return p3Match;
	}

	public void setP3Match(String p3Match) {
		this.p3Match = p3Match;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
