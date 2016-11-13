/**   
 * 文件名称: WebAgentService.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: Channel<br/>  
 * 创建时间 : 2015-7-2 上午10:59:07<br/>
 */
package com.mh.service;

import java.util.List;
import java.util.Map;

import com.mh.commons.orm.Page;
import com.mh.entity.WebAgent;
import com.mh.entity.WebAgentTuishui;
import com.mh.entity.WebAgentTuiyong;

/**
 * 类描述: TODO<br/>
 * 代理中心service 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-2 上午10:59:07<br/>
 */
@SuppressWarnings("all")
public interface WebAgentService {
	
	/**
	 * 代理列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param webAgent
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, WebAgent webAgent);
	
	/**
	 * 
	 * @Description: 根据名字查询代理人的信息
	 * @param
	 * @return List<WebAgent>
	 * @throws
	 * @author Channel
	 * @date 2015-7-2
	 */
	public WebAgent findWebAgent(String userName);
	/**
	 * 
	 * @Description: 添加为代理人
	 * @param
	 * @return List<WebAgent>
	 * @throws
	 * @author Channel
	 * @date 2015-7-21
	 */
	public void insertWebAgent(WebAgent webAgent);

	/**
	 * 查询代理人旗下会员的信息</br>
	 * 创建人: zoro<br/> 
	 * @param page
	 * @param agentName
	 * @return  
	 * Page
	 */
	public Page findWebAgentUsers(Page page, String agentName, String userName);

	/**
	 * 
	 * @Description: 代理人信息
	 * @param
	 * @return List<WebAgent>
	 * @throws
	 * @author Channel
	 * @date 2015-7-2
	 */
	public List<WebAgent> findWebAgentReport(WebAgent webAgent);

	/***
	 * 根据代理no 查询代理信息 方法描述: TODO</br>
	 * 
	 * @param angetNo
	 * @return
	 * @author alex WebAgent
	 */
	public WebAgent getWebAgentByAngetNo(String angetNo);
	
	public Page findWebAgentList(Page page, String agentName);
	
	public Page findWebAgentListFuzzy(Page page, String agentName);
	
	public Map<String, Object> findAgentType(String agentName);
	
	public List<WebAgentTuiyong> findWebAgentTuiyong(int agentType);
	
	public List<WebAgentTuishui> findWebAgentTuishui(int agentType);
}
