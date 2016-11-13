package com.mh.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TLinkWebThirdPayKj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_link_web_third_pay_kj")
public class TLinkWebThirdPayKj implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userTypeId;
	private Integer userPayType;
	private Integer userThirdPayKjId;
	private Integer status;
	private Date autoUpdateTime;

	// Constructors

	/** default constructor */
	public TLinkWebThirdPayKj() {
	}

	/** full constructor */
	public TLinkWebThirdPayKj(Integer userTypeId, Integer userPayType,
			Integer userThirdPayKjId, Integer status, Date autoUpdateTime) {
		this.userTypeId = userTypeId;
		this.userPayType = userPayType;
		this.userThirdPayKjId = userThirdPayKjId;
		this.status = status;
		this.autoUpdateTime = autoUpdateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_type_id")
	public Integer getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	@Column(name = "user_pay_type")
	public Integer getUserPayType() {
		return this.userPayType;
	}

	public void setUserPayType(Integer userPayType) {
		this.userPayType = userPayType;
	}

	@Column(name = "user_third_pay_kj_id")
	public Integer getUserThirdPayKjId() {
		return userThirdPayKjId;
	}

	public void setUserThirdPayKjId(Integer userThirdPayKjId) {
		this.userThirdPayKjId = userThirdPayKjId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auto_update_time", length = 19)
	public Date getAutoUpdateTime() {
		return this.autoUpdateTime;
	}

	public void setAutoUpdateTime(Date autoUpdateTime) {
		this.autoUpdateTime = autoUpdateTime;
	}

}