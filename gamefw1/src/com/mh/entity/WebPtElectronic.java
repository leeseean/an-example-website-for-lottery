/**   
* 文件名称: WebPtElectronic.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-6-30 下午2:17:09<br/>
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
 * 类描述: TODO<br/>pt电子游戏记录表
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-6-30 下午2:17:09<br/>
 */
@Entity
@Table(name = "t_web_pt_electronic")
public class WebPtElectronic implements   Serializable{

	/** 
	 * @Fields serialVersionUID: TODO 
	 */ 
	private static final long serialVersionUID = 1L;
	// 流水号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "ID", nullable = false)
	private Integer id;//数据库主键
	
	@Column(name = "ele_game_code")
	private String eleGameCode;//游戏CODE

	@Column(name = "ele_game_enname")
	private String eleGameEnname;//游戏英文名称
	
	@Column(name = "ele_game_cnname")
	private String eleGameCnname;//游戏中文名称
	
	@Column(name = "ele_game_pic")
	private String eleGamePic;//游戏图标
	
	@Column(name = "ele_game_index")
	private String eleGameIndex;//游戏编号
	
	@Column(name = "ele_game_type1")
	private String eleGameType1;//游戏类型
	
	@Column(name = "ele_game_type2")
	private String eleGameType2;//游戏分组
	
	@Column(name = "remark")
	private String remark;//说明
	
	@Column(name = "status")
	private String status;//是否有效
	
	
	
	@Column(name = "is_support")
	private Integer isSupport;//是否支持奖池0否1是
	
	@Column(name = "progressive_game_code")
	private String progressiveGameCode;//累积奖池游戏代码
	
	@Column(name = "platform")
	private Integer platform;//平台标示0表示web 1表示html5

	@Column(name="ele_sort_index")
	private Integer eleSortIndex;
	
	@Column(name="ele_hot_num")
	private Integer eleHotNum;
	
	/**
	 * 是否需要返水(0:否 1:是)
	 */
	@Column(name="is_water")
	private Integer isWater;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEleGameCode() {
		return eleGameCode;
	}

	public void setEleGameCode(String eleGameCode) {
		this.eleGameCode = eleGameCode;
	}

	public String getEleGameEnname() {
		return eleGameEnname;
	}

	public void setEleGameEnname(String eleGameEnname) {
		this.eleGameEnname = eleGameEnname;
	}

	public String getEleGameCnname() {
		return eleGameCnname;
	}

	public void setEleGameCnname(String eleGameCnname) {
		this.eleGameCnname = eleGameCnname;
	}

	public String getEleGamePic() {
		return eleGamePic;
	}

	public void setEleGamePic(String eleGamePic) {
		this.eleGamePic = eleGamePic;
	}

	public String getEleGameIndex() {
		return eleGameIndex;
	}

	public void setEleGameIndex(String eleGameIndex) {
		this.eleGameIndex = eleGameIndex;
	}

 
 
	public String getEleGameType1() {
		return eleGameType1;
	}

	public void setEleGameType1(String eleGameType1) {
		this.eleGameType1 = eleGameType1;
	}

	public String getEleGameType2() {
		return eleGameType2;
	}

	public void setEleGameType2(String eleGameType2) {
		this.eleGameType2 = eleGameType2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIsSupport() {
		return isSupport;
	}

	public void setIsSupport(Integer isSupport) {
		this.isSupport = isSupport;
	}

	public String getProgressiveGameCode() {
		return progressiveGameCode;
	}

	public void setProgressiveGameCode(String progressiveGameCode) {
		this.progressiveGameCode = progressiveGameCode;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getEleSortIndex() {
		return eleSortIndex;
	}

	public void setEleSortIndex(Integer eleSortIndex) {
		this.eleSortIndex = eleSortIndex;
	}

	public Integer getEleHotNum() {
		return eleHotNum;
	}

	public void setEleHotNum(Integer eleHotNum) {
		this.eleHotNum = eleHotNum;
	}

	public Integer getIsWater() {
		return isWater;
	}

	public void setIsWater(Integer isWater) {
		this.isWater = isWater;
	}

	
}
