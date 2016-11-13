/**   
* 文件名称: ActivityPrize.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午2:02:51<br/>
*/  
package com.mh.entity;

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
 * 
 * 活动奖品
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午2:02:51<br/>
 */

@Entity
@Table(name = "t_activity_prize")
public class ActivityPrize implements java.io.Serializable {

 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "prize_name")
	private String prizeName;
	
	@Column(name = "is_prize")
	private Integer isPrize;
	
	@Column(name = "images_url")
	private String imagesUrl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lock_begin_time")
	private Date lockBeginTime;//奖品锁定开始时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lock_end_time")
	private Date lockEndTime;//奖品锁定结束时间
	
	@Column(name = "winning_rate")
	private Double winningRate;
	
	//奖品数量
	@Column(name = "prize_nums")
	private Integer prizeNums;
	
	//奖品天发放数
	@Column(name = "day_nums")
	private Integer dayNums;
	
	//排序号
	@Column(name = "pxh")
	private Integer pxh;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;//创建时间
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;//修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public Integer getPxh() {
		return pxh;
	}

	public void setPxh(Integer pxh) {
		this.pxh = pxh;
	}

	public Integer getIsPrize() {
		return isPrize;
	}

	public void setIsPrize(Integer isPrize) {
		this.isPrize = isPrize;
	}

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}

	public Date getLockBeginTime() {
		return lockBeginTime;
	}

	public void setLockBeginTime(Date lockBeginTime) {
		this.lockBeginTime = lockBeginTime;
	}

	public Date getLockEndTime() {
		return lockEndTime;
	}

	public void setLockEndTime(Date lockEndTime) {
		this.lockEndTime = lockEndTime;
	}

	public Double getWinningRate() {
		return winningRate;
	}

	public void setWinningRate(Double winningRate) {
		this.winningRate = winningRate;
	}

	public Integer getDayNums() {
		return dayNums;
	}

	public void setDayNums(Integer dayNums) {
		this.dayNums = dayNums;
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

	public Integer getPrizeNums() {
		return prizeNums;
	}

	public void setPrizeNums(Integer prizeNums) {
		this.prizeNums = prizeNums;
	}
 
	
	
	
}
