/**   
* 文件名称: AgentMainController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-8-28 下午1:51:55<br/>
*/  
package com.mh.web.controller;

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
import com.mh.commons.conf.SystemConstant;
import com.mh.commons.orm.Page;
import com.mh.dao.WebAccountsDao;
import com.mh.dao.WebUserDao;
import com.mh.service.WebAgentService;
import com.mh.web.login.UserContext;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-8-28 下午1:51:55<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/agent")
public class AgentMainController extends BaseController{
	
	@Autowired
	private WebAgentService agentService;
	
	@Autowired
	private WebAccountsDao webAccountsDao;
	
	@Autowired
	private WebUserDao webUserDao;
	
	
	/**
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("agent/main");
	}
	
	@RequestMapping("/head")
	public ModelAndView head(HttpServletRequest request, HttpServletResponse response){
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		return new ModelAndView("agent/head")
			.addObject("user", uc)
			.addObject("isAdmin", uc.isAdmin());
	}
	
	@RequestMapping("/goCenter")
	public ModelAndView goCenter(HttpServletRequest request, HttpServletResponse response, String code){
		return new ModelAndView("agent/inc/"+code);
	}
	
	/**
	 * 代理商会员列表</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/memberList")
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response){
		String agentName = request.getParameter("agentName");
		String userName = request.getParameter("username");
		 
		Page page = this.newPage(request);
		
		this.agentService.findWebAgentUsers(page,agentName,userName);
		
		page.setColspanNum(3);
		
		List<Map<String, Object>> list = page.getResult();
		for(Map<String, Object> object: list){
			String _userName = object.get("user_name").toString();
			Map<String, Object> inOut = UserMoneyInAndOut(_userName);
			object.putAll(inOut);
		}
		return  new ModelAndView("agent/inc/member_list")
		.addObject(CommonConstant.PAGE_KEY, page);
	}
	
	
	public Map<String, Object> UserMoneyInAndOut(String userName){
		/** 充值与提款 **/
		String inOptType = SystemConstant.huikuan_bank + "," + SystemConstant.huikuan_online +"," + SystemConstant.huikuan_manual;// 1公司入款	2在线支付	4后台入款
		String outOptType = SystemConstant.withdraw_hytk +"," + SystemConstant.huikuan_back;//11会员提款	8拒绝提款
		Map<String, Object> inOutMoney = this.webAccountsDao.reportUserInOut(userName, inOptType, outOptType);
		
		/** 手续、行政费用(统计取款表) **/
		Map<String, Object> proMoney = this.webAccountsDao.reportUserProcedure(userName);
		Double lMoney = inOutMoney.get("inMoney") != null ? Double.valueOf(inOutMoney.get("inMoney").toString()) : 0d;
		lMoney = lMoney + (proMoney.get("pcost") != null ? Double.valueOf(proMoney.get("pcost").toString()) : 0d) 
						+ ((proMoney.get("acost") != null ? Double.valueOf(proMoney.get("acost").toString()) : 0d));
		inOutMoney.put("outMoney", inOutMoney.get("outMoney") != null ? Double.valueOf(inOutMoney.get("outMoney").toString()) : 0.0);
		inOutMoney.put("inMoney", lMoney);
		return inOutMoney;
		
	}
	
	/**
	 * 代理商列表</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/agentlist")
	public ModelAndView agentlist(HttpServletRequest request, HttpServletResponse response){
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		String agentName = request.getParameter("agentName");
		Page page = this.newPage(request);
		if(!uc.isAdmin()&&StringUtils.isEmpty(agentName)){
			agentName = uc.getUserName();
			page = this.agentService.findWebAgentList(page, agentName);
		}else{
			page = this.agentService.findWebAgentListFuzzy(page, agentName);
		}
		Map<String,Integer> userMap = this.webUserDao.getUserAgentMap();
		return new ModelAndView("agent/inc/agent_list")
				.addObject(CommonConstant.PAGE_KEY, page)
				.addObject("userMap", userMap)
				.addObject("isAdmin", uc.isAdmin());
	}
	
	/**
	 * 代理类型</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/agentType")
	public ModelAndView agentType(HttpServletRequest request, HttpServletResponse response){
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		String agentName = uc.getUserName();
		Map<String, Object> agentType = this.agentService.findAgentType(agentName);
		Map<String, String> tuiyongMap = SystemConstant.betAgentTuiyongCodeValueMap;
		Map<String, String> tuiShuiMap = SystemConstant.betAgentTuishuiCodeValueMap;
		return new ModelAndView("agent/inc/agent_type" )
					.addObject("agentType",agentType)
					.addObject("tuiyongMap",tuiyongMap)
					.addObject("tuiShuiMap",tuiShuiMap);
	}
	
	
	/**
	 * 代理佣金阶梯比例</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param id
	 * @param type
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/agentOdds")
	public ModelAndView agentOdds(HttpServletRequest request, HttpServletResponse response, int id, String type){
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		
		List<?> list = null;
		if("tuishui".equals(type)){
			list = this.agentService.findWebAgentTuishui(id);
		}
		
		if("tuiyong".equals(type)){
			list = this.agentService.findWebAgentTuiyong(id);
		}
		
		return new ModelAndView("agent/inc/agent_odds_list")
			.addObject("list",list);
	}
}
