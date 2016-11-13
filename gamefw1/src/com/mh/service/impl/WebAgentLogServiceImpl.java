/**   
* 文件名称: WebUserAgentLogServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-28 上午4:47:27<br/>
*/  
package com.mh.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.dao.WebAgentLogDao;
import com.mh.entity.WebAgentLog;
import com.mh.service.WebAgentLogService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-28 上午4:47:27<br/>
 */
@SuppressWarnings("all")
@Service
public class WebAgentLogServiceImpl implements WebAgentLogService{
	
	@Autowired
	private WebAgentLogDao webAgentLogDao;
	
	
	/**
	 * 操作日志列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param webAgentLog
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, WebAgentLog webAgentLog){
		return this.webAgentLogDao.findPage(page, webAgentLog);
	}

	/**
	 * 保存更新代理登录日志
	 * 方法描述: TODO</br> 
	 * @param webUserAgentLog  
	 * void
	 */
	public void saveOrUpdateWebAgentLog(WebAgentLog webAgentLog){
		this.webAgentLogDao.saveOrUpdate(webAgentLog);
	}
	
	
	/**
	 * 更新代理操作日志
	 * 方法描述: TODO</br> 
	 * @param logName 日志类别名称（登录、添加等）
	 * @param optIp  操作ip
	 * @param optUser 操作者
	 * @param optTarget   操作对象
	 * void
	 */
	public void updateWebAgentLog(String logName,String optIp,String optUser,String optTarget){
		
		Date currDate = new Date();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd HH:mm:ss");
		//登录日志
		WebAgentLog webAgentLog = new WebAgentLog();
		webAgentLog.setLogType("1");
		webAgentLog.setLogName(logName);
		webAgentLog.setOptIp(optIp);
		webAgentLog.setOptUser(optUser);
		webAgentLog.setOptTime(new Date());
		webAgentLog.setOptTarget(optTarget);
		StringBuffer buff = new StringBuffer("");
		buff.append(optUser);
		buff.append("于");
		buff.append(currDateStr);
		buff.append("执行").append(logName).append("操作");
		webAgentLog.setLogContent(buff.toString());
		this.webAgentLogDao.saveOrUpdate(webAgentLog);
	}

}
