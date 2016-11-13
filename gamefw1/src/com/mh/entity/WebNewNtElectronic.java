/**   
* 文件名称: WebNtElectronic.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-6-30 下午2:11:36<br/>
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
 * 类描述: TODO<br/>新nt电子游戏记录表
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-6-30 下午2:11:36<br/>
 */
@Entity
@Table(name = "t_web_newnt_electronic")
public class WebNewNtElectronic implements Serializable,Comparable<WebNewNtElectronic> {

	/** 
	 * @Fields serialVersionUID: TODO 
	 */ 
	private static final long serialVersionUID = 1L;
	// 流水号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "id", nullable = false)
	private Integer id;//数据库主键
	
	@Column(name = "ele_game_id")
	private String eleGameId;//唯一标识
	
	@Column(name = "ele_game_code")
	private String eleGameCode;//游戏编码
	
	@Column(name = "ele_game_cnname")
	private String eleGameCnname;//游戏中文名
	
	@Column(name = "ele_game_enname")
	private String eleGameEnname;//游戏英文名
	
	@Column(name = "ele_game_pic")
	private String eleGamePic;//游戏图片
	
	@Column(name = "ele_game_type1")
	private String eleGameType1;//游戏类型1
	
	@Column(name = "ele_game_type2")
	private String eleGameType2;//游戏类型2
	
	@Column(name = "ele_game_active")
	private Integer eleGameActive;//游戏活动
	
	@Column(name = "ele_index")
	private Integer eleIndex;//排序
	
	@Column(name = "ele_item2")
	private String eleItem2;//备用字段2
	
	@Column(name = "is_water")
	private Integer isWater;//是否需要返水
	
	@Column(name = "status")
	private Integer status;//游戏状态
	
	@Column(name = "remark")
	private String remark;//游戏备注

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

	public String getEleGameCnname() {
		return eleGameCnname;
	}

	public void setEleGameCnname(String eleGameCnname) {
		this.eleGameCnname = eleGameCnname;
	}

	public String getEleGameEnname() {
		return eleGameEnname;
	}

	public void setEleGameEnname(String eleGameEnname) {
		this.eleGameEnname = eleGameEnname;
	}

	public String getEleGamePic() {
		return eleGamePic;
	}

	public void setEleGamePic(String eleGamePic) {
		this.eleGamePic = eleGamePic;
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

	public Integer getEleGameActive() {
		return eleGameActive;
	}

	public void setEleGameActive(Integer eleGameActive) {
		this.eleGameActive = eleGameActive;
	}

	public Integer getEleIndex() {
		return eleIndex;
	}

	public void setEleIndex(Integer eleIndex) {
		this.eleIndex = eleIndex;
	}

	public String getEleItem2() {
		return eleItem2;
	}

	public void setEleItem2(String eleItem2) {
		this.eleItem2 = eleItem2;
	}

	public Integer getIsWater() {
		return isWater;
	}

	public void setIsWater(Integer isWater) {
		this.isWater = isWater;
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

	public String getEleGameId() {
		return eleGameId;
	}

	public void setEleGameId(String eleGameId) {
		this.eleGameId = eleGameId;
	}

	@Override
	public int compareTo(WebNewNtElectronic o) {
		return this.getEleIndex().compareTo(o.getEleIndex());
	}
}
