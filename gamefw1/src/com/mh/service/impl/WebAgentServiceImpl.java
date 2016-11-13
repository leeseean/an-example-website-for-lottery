/**   
* 文件名称: WebAgentServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-2 上午11:10:31<br/>
*/  
package com.mh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.orm.Page;
import com.mh.dao.WebAgentDao;
import com.mh.entity.WebAgent;
import com.mh.entity.WebAgentTuishui;
import com.mh.entity.WebAgentTuiyong;
import com.mh.service.WebAgentService;

/** 
 * 类描述: TODO<br/>代理中心
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-2 上午11:10:31<br/>
 */
@SuppressWarnings("all")
@Service
public class WebAgentServiceImpl implements WebAgentService {
   
	@Autowired
	private WebAgentDao webAgentDao;
	
	/**
	 * 代理列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param webAgent
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, WebAgent webAgent){
		return this.webAgentDao.findPage(page, webAgent);
	}
	
	
	
	/**
	 * 
	 * @Description: 查询代理人的信息
	 * @param    
	 * @return List<WebAgent>  
	 * @throws
	 * @author Channel
	 * @date 2015-7-2
	 */
	public WebAgent findWebAgent(String userName) {
		// TODO Auto-generated method stub
		return this.webAgentDao.findWebAgent(userName);
	}
	
	/**
	 * 
	 * @Description: 根据名字查询代理人的信息如果不是代理人,添加为代理人
	 * @param
	 * @return List<WebAgent>
	 * @throws
	 * @author Channel
	 * @date 2015-7-21
	 */
	public void insertWebAgent(WebAgent webAgent){
		  this.webAgentDao.save(webAgent);
	}
	
	/**
	 * 查询代理人旗下会员的信息</br>
	 * 创建人: zoro<br/> 
	 * @param page
	 * @param agentName
	 * @return  
	 * Page
	 */
	public Page findWebAgentUsers(Page page, String agentName, String userName) {
		return this.webAgentDao.findWebAgentUsers(page,agentName, userName);
	}
	/**
	 * 
	 * @Description: 代理人信息
	 * @param    
	 * @return List<WebAgent>  
	 * @throws
	 * @author Channel
	 * @date 2015-7-2
	 */
	public List<WebAgent> findWebAgentReport(WebAgent webAgent) {
		// TODO Auto-generated method stub
		return  this.webAgentDao.findWebAgentReport(webAgent);
	}
	
	
	/***
	 * 根据代理no 查询代理信息
	 * 方法描述: TODO</br> 
	 * @param angetNo
	 * @return  
	 * @author alex
	 * WebAgent
	 */
	public WebAgent getWebAgentByAngetNo(String angetNo){
		return this.webAgentDao.getWebAgentByAngetNo(angetNo);
	}

	public Page findWebAgentList(Page page, String agentName) {
		return this.webAgentDao.findWebAgentList(page, agentName);
	}

	public Page findWebAgentListFuzzy(Page page, String agentName) {
		return this.webAgentDao.findWebAgentListFuzzy(page, agentName);
	}
	
	public Map<String, Object> findAgentType(String agentName){
		return this.webAgentDao.findAgentType(agentName);
	}

	public List<WebAgentTuiyong> findWebAgentTuiyong(int agentType) {
		return this.webAgentDao.findWebAgentTuiyong(agentType);
	}

	public List<WebAgentTuishui> findWebAgentTuishui(int agentType) {
		return this.webAgentDao.findWebAgentTuishui(agentType);
	}

}
