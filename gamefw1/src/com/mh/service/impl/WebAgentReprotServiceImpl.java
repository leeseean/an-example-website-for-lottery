/**   
* 文件名称: WebAgentReprotServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-29 上午6:44:42<br/>
*/  
package com.mh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.orm.Page;
import com.mh.dao.WebAgentReprotDao;
import com.mh.entity.WebAgent;
import com.mh.service.WebAgentReprotService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-29 上午6:44:42<br/>
 */
@SuppressWarnings("all")
@Service
public class WebAgentReprotServiceImpl implements WebAgentReprotService{
	
	@Autowired
	private WebAgentReprotDao webAgentReprotDao;
	
	/***
	 * 会员代理报表列表
	 * 方法描述: TODO</br> 
	 * @return  
	 * Map<List<String,Object>>
	 */
	public Page getUserReport(Page page,WebAgent webAgent){
		return this.webAgentReprotDao.getUserReport(page,webAgent);
	}
	
	
	/**
	 * 获取代理报表
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<Map<String,Object>>
	 */
	public Page getAgentReport(Page page,WebAgent webAgent){
		return this.webAgentReprotDao.getAgentReport(page,webAgent);
	}
	

}
