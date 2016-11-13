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
 * 类描述: OS电子游戏表<br/> 
 * 修改人: zoro<br/>
 * 修改时间: 2015-12-22 下午2:37:20<br/>
 */
@Entity
@Table(name = "t_web_os_electronic")
public class WebOsElectronic implements Serializable,Comparable<WebOsElectronic> {

	/**
	 * @Fields serialVersionUID: TODO
	 */
	private static final long serialVersionUID = 1L;
	// 流水号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 自增长
	@Column(name = "ID", nullable = false)
	private Integer id;// 数据库主键

	@Column(name = "ele_game_id")
	private String eleGameId;// 游戏ID

	@Column(name="ele_game_cnname")
	private String eleGameCnname;
	
	@Column(name="ele_game_enname")
	private String eleGameEnname;
	
	@Column(name = "ele_game_pic")
	private String eleGamePic;// 游戏图标

	@Column(name = "ele_game_type1")
	private String eleGameType1;// 游戏1级分类

	@Column(name = "ele_game_type2")
	private String eleGameType2;// 游戏2级分类

	@Column(name = "remark")
	private String remark;// 说明
	
	@Column(name = "status")
	private String status;//是否有效
	
	@Column(name = "ele_game_code")
	private String eleGameCode;
	
	@Column(name="ele_game_name")
	private String eleGameName;
	
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

	public String getEleGameId() {
		return eleGameId;
	}

	public void setEleGameId(String eleGameId) {
		this.eleGameId = eleGameId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getEleGameCode() {
		return eleGameCode;
	}

	public void setEleGameCode(String eleGameCode) {
		this.eleGameCode = eleGameCode;
	}

	public String getEleGameName() {
		return eleGameName;
	}

	public void setEleGameName(String eleGameName) {
		this.eleGameName = eleGameName;
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

	@Override
	public int compareTo(WebOsElectronic o) {
		o.getEleSortIndex().compareTo(this.getEleSortIndex());
		return this.getId().compareTo(o.getId());
	}


}
