/**   
* 文件名称: AgentReprotController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-29 上午6:42:03<br/>
*/  
package com.mh.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.mh.dao.WebCommisionDao;
import com.mh.entity.WebAgent;
import com.mh.service.WebAgentPoundageServive;
import com.mh.service.WebAgentReprotService;
import com.mh.web.login.UserContext;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-29 上午6:42:03<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/agent")
public class WebAgentReprotController extends BaseController{

	@Autowired
	private WebAgentReprotService webAgentReprotService;
	
	@Autowired
	private WebCommisionDao webCommisionDao;
	
	@Autowired
	private WebAgentPoundageServive webAgentPoundageServive;
	
	
	/**
	 * 会员报表列表
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param webAgent
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goUserReport")
	public ModelAndView goUserReport(HttpServletRequest request,
			HttpServletResponse response,WebAgent webAgent) {
		initSearchDateTime(request);
		
		Page page = newPage(request);
		String yesterDateStr = DateUtil.yesterdayBegin().substring(0,10);
		//String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		if(StringUtils.isEmpty(webAgent.getBeginTimeStr())){
			webAgent.setBeginTimeStr(yesterDateStr);
		}
		if(StringUtils.isEmpty(webAgent.getEndTimeStr())){
			webAgent.setEndTimeStr(yesterDateStr);
		}
		if(StringUtils.isEmpty(webAgent.getPlatName())){
			webAgent.setPlatName(CommonConstant.PLAT_PARAM_AG);
		}
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		webAgent.setUserName(uc.getUserName());
		
		this.webAgentReprotService.getUserReport(page,webAgent);

		return new ModelAndView("agent/inc/user_report")
			.addObject("page", page)
			.addObject("platMap", getPlatMap())
			.addObject("webAgent", webAgent);
	}
	
	
	/**
	 * 代理报表列表
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param webAgent
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goAgentReport")
	public ModelAndView goAgentReport(HttpServletRequest request,
			HttpServletResponse response,WebAgent webAgent) {
		initSearchDateTime(request);
		Page page = newPage(request);
		String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		if(StringUtils.isEmpty(webAgent.getBeginTimeStr())){
			webAgent.setBeginTimeStr(currDateStr);
		}
		if(StringUtils.isEmpty(webAgent.getEndTimeStr())){
			webAgent.setEndTimeStr(currDateStr);
		}
		if(StringUtils.isEmpty(webAgent.getPlatName())){
			webAgent.setPlatName(CommonConstant.PLAT_PARAM_AG);
		}
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		webAgent.setAgentNo(uc.getUserName());
		this.webAgentReprotService.getAgentReport(page,webAgent);

		return new ModelAndView("agent/inc/agent_report")
			.addObject("page", page)
			.addObject("platMap", getPlatMap())
			.addObject("webAgent", webAgent);
	}
	
	
	/**
	 * 获取列表
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<String>
	 */
	public Map<String,String> getPlatMap() {
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("ag","AG");
		map.put("bbin","BBIN");
		map.put("ds","DS");
		map.put("hg","HG");
		map.put("mg","MG");
		map.put("nt","NT");
		map.put("pt","PT");;
		map.put("sport","体育");
		map.put("caipiao","彩票");
		map.put("douji","斗鸡");
		map.put("ab","AB");
		map.put("og","OG");
		map.put("os","OS");
		map.put("sb","SB");
		map.put("gd","GD");
		map.put("ttg","TTG");
		map.put("sbt","SBT");
		map.put("sa","SA");
		map.put("vg","VG");
		
		return map;
	}
	
	/**
	 * 佣金代理报表</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param code
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/agentReport")
	public ModelAndView agentReport(HttpServletRequest request, HttpServletResponse response, String code){
		ModelAndView view = new ModelAndView();
		String qs = request.getParameter("betQs");
		String agentName = request.getParameter("agentName");
		String currentDate = DateUtil.format(new Date(), "yyyyMM");
				
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		if(!uc.isAdmin()&&StringUtils.isEmpty(agentName)){
			agentName = uc.getUserName();
		}
		
		if("1".equals(code)){
			qs = StringUtils.defaultString(qs, currentDate);
			view.setViewName("agent/inc/agent_report");
		}else{
			Date lastMonth = DateUtil.addMonth(new Date(), -1);
			qs = StringUtils.defaultString(qs,DateUtil.format(lastMonth, "yyyyMM"));
			view.setViewName("agent/inc/agent_report_his");
		}
		Page page = newPage(request);
		page = this.webCommisionDao.searchReportList(page, qs, agentName);
		return view
				.addObject(CommonConstant.PAGE_KEY,page)
				.addObject("betQs",qsList())
				.addObject("code", code)
				.addObject("qs", qs)
				.addObject("isAdmin", uc.isAdmin());
	}
	
	/**
	 * 跳转佣金详细画面</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/gotoDetail")
	public ModelAndView gotoDetail(HttpServletRequest request, HttpServletResponse response){
		String qs = request.getParameter("qs");
		String agentName = request.getParameter("agentName");
		String betFlat = request.getParameter("betFlat");
		Map<String, Object> map = this.webCommisionDao.searchCommisionDetail(agentName, qs, betFlat);
		//行政费用
		String xzfy = this.webAgentPoundageServive.getAgentPoundage();
		return new ModelAndView("agent/inc/comm_detail")
				.addObject("detail",map)
				.addObject("betFlat",betFlat)
				.addObject("xzfy",xzfy)
				;
	}
	
	/**
	 * 会员记录列表</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/gotoUserRecords")
	public ModelAndView gotoUserRecords(HttpServletRequest request, HttpServletResponse response){
		String qs = request.getParameter("qs");
		
		String agentName = request.getParameter("agentName");
		String betFlat = request.getParameter("betFlat");
		String begin = request.getParameter("beginTimeStr");
		String end = request.getParameter("endTimeStr");
		if(StringUtils.isEmpty(begin)&&StringUtils.isEmpty(end)){
			String year = qs.substring(0,4);
			String month = qs.substring(4,6);
			begin = DateUtil.getGMT_4_BeginString(year, month).substring(0,10);
			end = DateUtil.getGMT_4_EndString(year, month).substring(0,10);
		}
		
		Map<String, Object> totalReport = new HashMap<String, Object>();
		Map<String, Object> pageReport = new HashMap<String, Object>();
		Page page = newPage(request);	
	    this.webCommisionDao.searchUserRecords(page, agentName, betFlat, begin, end);
	    if(page.getResult().size()>0){
	    	totalReport = this.webCommisionDao.reportUserRecordsTotal(agentName, betFlat, begin, end);
	  		pageReport = this.webCommisionDao.reportUserRecordsPage(page,agentName, betFlat, begin, end);
	    }
	  
		return new ModelAndView("agent/inc/agent_user_records")
			.addObject(CommonConstant.PAGE_KEY, page)
			.addObject("betFlat", betFlat)
			.addObject("platMap", getPlatMap())
			.addObject("beginTimeStr", begin)
			.addObject("endTimeStr", end)
			.addObject("agentName", agentName)
			.addObject("totalReport",totalReport)
			.addObject("pageReport", pageReport);
	}
	
	/**
	 * 历史期数</br>
	 * 创建人: zoro<br/> 
	 * @return  
	 * List<String>
	 */
	public List<String> qsList(){
		List<String> list = new ArrayList<String>();
		for(int i=1; i<=3;i++){
			Date d = DateUtil.addMonth(new Date(), -i);
			String str = DateUtil.format(d, "yyyyMM");
			list.add(str);
		}
		return list;
	}
	
}
