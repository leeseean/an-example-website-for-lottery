/**   
* 文件名称: WebUserFlatService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-3 下午4:40:41<br/>
*/  
package com.mh.service;

import java.util.List;

import com.mh.entity.WebUserFlat;

/** 
 * 平台用户Service接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午4:40:41<br/>
 */
public interface WebUserFlatService {
	
	
	/**
	 * 添加平台用户信息
	 * 方法描述: TODO</br> 
	 * @param webUserFlat  
	 * void
	 */
	public  void saveOrUpdateWebUserFlat(WebUserFlat webUserFlat);
	
	
	/**
	 * 根据用户查询平台用户表
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebUserFlat
	 */
	public WebUserFlat getWebUserFlat(String userName);
	public List<WebUserFlat> getWebUserFlat(List<String> userName);
	
	
	public WebUserFlat selectUserFlatByMgUserName(String mgUserName);

}
