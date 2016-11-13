/**   
 * 文件名称: WebAgentController.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: Channel<br/>  
 * 创建时间 : 2015-7-2 下午9:05:58<br/>
 */
package com.mh.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.SysParameter;
import com.mh.entity.WebAgent;
import com.mh.entity.WebUser;
import com.mh.service.SysParameterService;
import com.mh.service.WebAgentService;
import com.mh.service.WebUserService;
import com.mh.web.login.UserContext;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-2 下午9:05:58<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/member")
public class WebAgentController extends BaseController {
	@Autowired
	private WebUserService webUserService;
	@Autowired
	private WebAgentService webAgentService;
	@Autowired
	private SysParameterService sysParameterService;

	/**
	 * 跳转到代理中心的代理信息 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/agentDetail")
	public ModelAndView agentInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		WebAgent webAgent = this.webAgentService.findWebAgent(uc.getUserName());
		if(null != webAgent){
			int total = this.webUserService.getUserAgentCont(uc.getUserName());
			webAgent.setCount(total);
			view.setViewName("member/agent/agentDetail");
			view.addObject("webAgent", webAgent);
		}else{
			view.setViewName("member/agent/agentApply");
		}
		view.addObject("webUser", webUser);
		return view;
	}
	/**
	 * 添加代理会员 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/insertApply")
	public void insertinto(HttpServletRequest request,
			HttpServletResponse response) {
		WebAgent webAgent=new WebAgent();
		Date currDate = DateUtil.currentDate();
		String agentMail=request.getParameter("agentMail");
		String content=request.getParameter("content");
		String agentType=request.getParameter("agentType");
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		webAgent.setContent(content);
		webAgent.setGmt4Time(DateUtil.getGMT_4_Date());// 美东当前时间
		webAgent.setUserName(uc.getUserName());
		webAgent.setUserRealName(webUser.getUserRealName());
		webAgent.setAgentMail(agentMail);	
		webAgent.setStatus(webUser.getUserStatus());
		webAgent.setStatus(-1);
		webAgent.setCount(0);
		webAgent.setAgentType(Integer.parseInt(agentType));
		webAgent.setCreateTime(currDate);
		webAgent.setModifyTime(currDate);
		String domain = webUser.getUserLastLoginDomain();
		webAgent.setAgentUrl(domain);
		
		this.webAgentService.insertWebAgent(webAgent);
		ServletUtils.outPrintSuccess(request, response);
	}	
	
	/**
	 * 跳转到代理中心的旗下会员 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/agentForUsers")
	public ModelAndView agentForUsers(HttpServletRequest request,
			HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		WebAgent webAgent = this.webAgentService.findWebAgent(uc.getUserName());
		if(null == webAgent){
			return new ModelAndView("member/agent/agentDetail")
				.addObject("webUser", webUser);
		}
		Page page = this.newPage(request);
		this.webAgentService.findWebAgentUsers(page,webUser.getUserName(),"");
		page.setColspanNum(3);
		return  new ModelAndView("member/agent/agentForUsers")
		.addObject("webAgent", webAgent)
		.addObject(CommonConstant.PAGE_KEY, page);
	}
	
	/***
	 * 代理入口</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/agentEnterInfor")
	public ModelAndView agentEnterInfor(HttpServletRequest request, HttpServletResponse response){
		SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName("web_agent_login_address");
		String enterUrl = sysParameter.getParamValue();
		
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		WebAgent webAgent = this.webAgentService.findWebAgent(uc.getUserName());
		if(null == webAgent){
			return new ModelAndView("member/agent/agentDetail")
				.addObject("webUser", webUser);
		}
		return new ModelAndView("member/agent/agentEnterInfor")
					.addObject("enterUrl", enterUrl)
					.addObject("webAgent", webAgent);
	}

}
