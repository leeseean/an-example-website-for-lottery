/**   
* 文件名称: ActivityInfo.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午4:03:22<br/>
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
 * 活动信息表
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午4:03:22<br/>
 */

@Entity
@Table(name = "t_activity_info")
public class ActivityInfo implements java.io.Serializable {
	

 
	private static final long serialVersionUID = 1L;


	// 流水号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "id", nullable = false)
	private Integer id;
	
	
	@Column(name = "activity_name")
	private String activityName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begin_time")
	private Date beginTime;//活动开始时间
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;//活动结束时间
	
	
	@Column(name = "init_nums")
	private Integer initNums;
	
	@Column(name = "growth_rate")
	private Double growthRate;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "message_url")
	private String messageUrl;
	
	@Column(name = "lott_condition")
	private String lottCondition;
	
	@Column(name = "lott_rule")
	private String lottRule;
	
	@Column(name = "logo_images")
	private String logoImages;
	
	@Column(name = "page_bg_images")
	private String pageBgimages;
	
	@Column(name = "roulette_images")
	private String rouletteImages;
	
	@Column(name = "web_url")
	private String webUrl;
	
	@Column(name = "prize_nums")
	private Integer prizeNums;
	
	
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


	public String getActivityName() {
		return activityName;
	}


	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}


	public Date getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Integer getInitNums() {
		return initNums;
	}


	public void setInitNums(Integer initNums) {
		this.initNums = initNums;
	}


	public Double getGrowthRate() {
		return growthRate;
	}


	public void setGrowthRate(Double growthRate) {
		this.growthRate = growthRate;
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


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getMessageUrl() {
		return messageUrl;
	}


	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}


	public String getLottCondition() {
		return lottCondition;
	}


	public void setLottCondition(String lottCondition) {
		this.lottCondition = lottCondition;
	}


	public String getLottRule() {
		return lottRule;
	}


	public void setLottRule(String lottRule) {
		this.lottRule = lottRule;
	}


	public String getPageBgimages() {
		return pageBgimages;
	}


	public void setPageBgimages(String pageBgimages) {
		this.pageBgimages = pageBgimages;
	}


	public String getRouletteImages() {
		return rouletteImages;
	}


	public void setRouletteImages(String rouletteImages) {
		this.rouletteImages = rouletteImages;
	}


	public String getWebUrl() {
		return webUrl;
	}


	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}


	public Integer getPrizeNums() {
		return prizeNums;
	}


	public void setPrizeNums(Integer prizeNums) {
		this.prizeNums = prizeNums;
	}


	public String getLogoImages() {
		return logoImages;
	}


	public void setLogoImages(String logoImages) {
		this.logoImages = logoImages;
	}
	
	
	
	

}
