/**   
* 文件名称: MatchSettled.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-13 下午4:12:31<br/>
*/
package com.mh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** 
 * 类描述: TODO<br/>球赛结算表	
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-13 下午4:12:31<br/>
 */
 

@Entity
@Table(name = "t_match_settled")
public class TMatchSettled implements Serializable {
	private static final long serialVersionUID = 1L;

 
	@Id
	@Column(name = "match_id",nullable = false)
	private String matchId;//球赛编号

 
	@Column(name = "status")
	private Integer status;//0未结算，1结算完成
	
 
	@Column(name = "betCount")
	private Integer betCount;//统计注单数

 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt4_time")
	private Date gmt4Time;//美东时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;//修改时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;//创建时间
	
	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBetCount() {
		return betCount;
	}

	public void setBetCount(Integer betCount) {
		this.betCount = betCount;
	}

	

	public Date getGmt4Time() {
		return gmt4Time;
	}

	public void setGmt4Time(Date gmt4Time) {
		this.gmt4Time = gmt4Time;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
