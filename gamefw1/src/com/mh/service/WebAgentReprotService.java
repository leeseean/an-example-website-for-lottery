/**   
* 文件名称: WebAgentReprotService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-29 上午6:45:17<br/>
*/  
package com.mh.service;

import com.mh.commons.orm.Page;
import com.mh.entity.WebAgent;

/** 
 * 
 * 代理报表Service接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-29 上午6:45:17<br/>
 */
@SuppressWarnings("all")
public interface WebAgentReprotService {
	
	
	/***
	 * 会员代理报表列表
	 * 方法描述: TODO</br> 
	 * @return  
	 * Map<List<String,Object>>
	 */
	public Page getUserReport(Page page,WebAgent webAgent);
	
	
	/**
	 * 获取代理报表
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<Map<String,Object>>
	 */
	public Page getAgentReport(Page page,WebAgent webAgent);

}
