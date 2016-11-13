/**   
* 文件名称: WebRecordController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-7-2 下午2:32:00<br/>
*/  
package com.mh.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.WebAgent;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUser;
import com.mh.service.WebAgentService;
import com.mh.service.WebRecordService;
import com.mh.service.WebUserService;
import com.mh.web.login.UserContext;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-7-2 下午2:32:00<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/records")
public class WebRecordController extends BaseController{
	protected Logger logger = LoggerFactory.getLogger(WebRecordController.class);
	
	@Autowired
	private WebRecordService webRecordService;
	@Autowired
	private WebAgentService webAgentService;
	@Autowired
	private WebUserService webUserService;
	/**
	 * 游戏记录数据
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param records
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goList")
	public ModelAndView goList(HttpServletRequest request, HttpServletResponse response, WebRecords records){
		String code = request.getParameter("code");
		if(!StringUtils.isEmpty(code)){
			records.setCode(code);
		}
		UserContext uc = this.getUserContext(request);
		records.setUserName(uc.getUserName());
		initSearchParam(records);
		logger.info("游戏记录页面");
		Page page = this.newPage(request);
		String pageView = code.substring(0,1).toUpperCase() + code.substring(1);
		ModelAndView view = new ModelAndView("member/bet/bet" + pageView);
		/*if("sport".equals(code)){
			webRecordService.findPageForSport(page, records);
			Map<String, String> betSportMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("bet_status");
			view.addObject("betSportMap", betSportMap);
		}else{
			if(WebConstants.FLAT_NAME_NT.equals(code)){
				view.addObject("codeMap", ResourceConstant.ntElectronic);
			}else if(WebConstants.FLAT_NAME_PT.equals(code)){
				view.addObject("codeMap", ResourceConstant.ptElectronic);
			}
		}*/
 
		webRecordService.findPage(page, records);
		return view.addObject(CommonConstant.PAGE_KEY, page).addObject("records", records)
					.addObject("flatParms", CommonConstant.flatsBetRecord);
	}
	
 
	
	/**
	 * 用户游戏记录报表
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param records
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/betReport")
	public ModelAndView betReport(HttpServletRequest request, HttpServletResponse response, WebRecords records){
		UserContext uc = this.getUserContext(request);
		records.setUserName(uc.getUserName());
		initSearchParamReport(records);
		List<Map<String, Object>> list = this.webRecordService.btReportUser(records);
		return new ModelAndView("member/bet/betReport")
		.addObject("reports", list)
		.addObject("records", records);
	}
	
	/**
	 * 代理用户报表
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param records
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/betAgentReport")
	public ModelAndView betAgentReport(HttpServletRequest request, HttpServletResponse response, WebRecords records){
		
		Date currDate = DateUtil.currentDate();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		if(StringUtils.isEmpty(records.getStartTime()) ){
			String monthBegin = DateUtil.monthBegin().substring(0,10);
			records.setStartTime(monthBegin);
		}
		
		if(StringUtils.isEmpty(records.getEndTime())){
			records.setEndTime(currDateStr);
		}
		UserContext uc = this.getUserContext(request);
		records.setUserAgent(uc.getUserName());
		WebAgent webAgent = this.webAgentService.findWebAgent(uc.getUserName());
		if(null == webAgent){
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			return new ModelAndView("member/agent/agentDetail")
				.addObject("webUser", webUser);
		}
		List<Map<String, Object>> list = this.webRecordService.btReport(records);
		return new ModelAndView("member/agent/agentForCount")
				.addObject("dataList", list)
				.addObject("records", records)
				.addObject("webAgent", webAgent);
	}
	
	
	private void initSearchParam(WebRecords records){
		Date currDate = new Date();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		if(StringUtils.isEmpty(records.getStartTime()) ){
			Date nextDate = DateUtil.addDay(currDate, -30);
			String nextDateStr = DateUtil.format(nextDate, "yyyy-MM-dd");
			records.setStartTime(nextDateStr);
		}
		
		if(StringUtils.isEmpty(records.getEndTime())){
			records.setEndTime(currDateStr);
		}
	}
	
	private void initSearchParamReport(WebRecords records){
		Date currDate = DateUtil.addDay(new Date(), -1);
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		if(StringUtils.isEmpty(records.getStartTime()) ){
			Date nextDate = DateUtil.addDay(currDate, -30);
			String nextDateStr = DateUtil.format(nextDate, "yyyy-MM-dd");
			records.setStartTime(nextDateStr);
		}
		
		if(StringUtils.isEmpty(records.getEndTime())){
			records.setEndTime(currDateStr);
		}
	}
}
