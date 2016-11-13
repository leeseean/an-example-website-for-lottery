/**   
* 文件名称: ActivityRule.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午2:08:57<br/>
*/  
package com.mh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * 活动规则
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午2:08:57<br/>
 */


@Entity
@Table(name = "t_activity_rule")
public class ActivityRule implements java.io.Serializable {
	
 
	private static final long serialVersionUID = 1L;


	// 流水号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "id", nullable = false)
	private Integer id;
	
	
	@Column(name = "begin_deposit")
	private Double beginDeposit;
	
	@Column(name = "end_deposit")
	private Double endDeposit;
	
	@Column(name = "bs")
	private Integer bs;
	
	@Column(name = "lott_times")
	private Integer lottTimes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBeginDeposit() {
		return beginDeposit;
	}

	public void setBeginDeposit(Double beginDeposit) {
		this.beginDeposit = beginDeposit;
	}

	public Double getEndDeposit() {
		return endDeposit;
	}

	public void setEndDeposit(Double endDeposit) {
		this.endDeposit = endDeposit;
	}

	public Integer getBs() {
		return bs;
	}

	public void setBs(Integer bs) {
		this.bs = bs;
	}

	public Integer getLottTimes() {
		return lottTimes;
	}

	public void setLottTimes(Integer lottTimes) {
		this.lottTimes = lottTimes;
	}
	
	
	

}
