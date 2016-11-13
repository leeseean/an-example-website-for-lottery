/**   
* 文件名称: MatchBkLimit.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-13 下午7:17:31<br/>
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
 * 创建时间: 2015-7-13 下午7:17:31<br/>
 */
@Entity
@Table(name = "t_match_bk_limit")
public class TMatchBkLimit implements Serializable {

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
	
	@Column(name = "jf_bet")
	private String jfBet;//积分
	
	@Column(name = "jf_match")
	private String jfMatch;
	
	@Column(name = "p3_bet")
	private String p3Bet;//综合过关
	
	@Column(name = "p3_match")
	private String p3Match;
	
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
	
	@Column(name = "roll_jf_bet")
	private String rollJfBet;//滚球积分
	
	@Column(name = "roll_jf_match")
	private String rollJfMatch;

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

	public String getJfBet() {
		return jfBet;
	}

	public void setJfBet(String jfBet) {
		this.jfBet = jfBet;
	}

	public String getJfMatch() {
		return jfMatch;
	}

	public void setJfMatch(String jfMatch) {
		this.jfMatch = jfMatch;
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

	public String getRollJfBet() {
		return rollJfBet;
	}

	public void setRollJfBet(String rollJfBet) {
		this.rollJfBet = rollJfBet;
	}

	public String getRollJfMatch() {
		return rollJfMatch;
	}

	public void setRollJfMatch(String rollJfMatch) {
		this.rollJfMatch = rollJfMatch;
	}
	
}
