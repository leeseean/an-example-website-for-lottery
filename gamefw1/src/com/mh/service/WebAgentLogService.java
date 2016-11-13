/**   
* 文件名称: WebUserAgentLogService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-28 上午4:46:39<br/>
*/  
package com.mh.service;

import com.mh.commons.orm.Page;
import com.mh.entity.WebAgentLog;

/** 
 * 
 * 代理登录日志
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-28 上午4:46:39<br/>
 */
@SuppressWarnings("all")
public interface WebAgentLogService {
	
	
	/**
	 * 操作日志列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param webAgentLog
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, WebAgentLog webAgentLog);
	
	/**
	 * 保存更新代理登录日志
	 * 方法描述: TODO</br> 
	 * @param webUserAgentLog  
	 * void
	 */
	public void saveOrUpdateWebAgentLog(WebAgentLog webAgentLog);
	
	
	
	/**
	 * 更新代理操作日志
	 * 方法描述: TODO</br> 
	 * @param logName 日志类别名称（登录、添加等）
	 * @param optIp  操作ip
	 * @param optUser 操作者
	 * @param optTarget   操作对象
	 * void
	 */
	public void updateWebAgentLog(String logName,String optIp,String optUser,String optTarget);

}
