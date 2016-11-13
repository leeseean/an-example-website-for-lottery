/**   
* 文件名称: WebAgentLogController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-29 上午3:06:31<br/>
*/  
package com.mh.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.WebAgentLog;
import com.mh.service.WebAgentLogService;
import com.mh.web.login.UserContext;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-29 上午3:06:31<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/agent")
public class WebAgentLogController extends BaseController {
	
	@Autowired
	private WebAgentLogService webAgentLogService;
	
	
	/**
	 * 代理日志
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param webAgentLog
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goAgentLogList")
	public ModelAndView goList(HttpServletRequest request,
			HttpServletResponse response,WebAgentLog webAgentLog) {
		Page page = newPage(request);
		String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		if(StringUtils.isEmpty(webAgentLog.getBeginTimeStr())){
			webAgentLog.setBeginTimeStr(currDateStr+" 00:00:00");
		}
		if(StringUtils.isEmpty(webAgentLog.getEndTimeStr())){
			webAgentLog.setEndTimeStr(currDateStr+" 23:59:59");
		}
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		webAgentLog.setOptUser(uc.getUserName());
		
		
		this.webAgentLogService.findPage(page, webAgentLog);

		return new ModelAndView("agent/inc/agent_log_list")
			.addObject("page", page)
			.addObject("webAgentLog", webAgentLog);
	}
	

}
