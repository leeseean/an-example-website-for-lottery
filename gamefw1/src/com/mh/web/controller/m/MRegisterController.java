package com.mh.web.controller.m;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.SystemConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.commons.utils.ValidStringUtil;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
import com.mh.web.login.UserContextAccessor;
import com.mh.web.servlet.MySessionContext;
import com.mh.web.util.CheckDeviceUtil;
@Controller
@RequestMapping("/m/register")
public class MRegisterController extends BaseController 
{
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired(required=false)
	private List<UserContextAccessor> accessores;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@RequestMapping("/checkParam")
	public void checkParam(HttpServletRequest request,HttpServletResponse response) 
	{
		UserContext uc = null;
		if(MySessionContext.getSession(request.getSession().getId())!=null)
			uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		//判断是否已经登录,已登录状态下不能注册 跳转到首页
		if(null != uc)
		{
			ServletUtils.outPrintFail(request, response, null);
			return;
		}
		String agentNo = (String) request.getSession().getAttribute("agentNo");// 代理号是放入session中
		String msg = "";
		if(StringUtils.isNotBlank(agentNo)){
			String agentName = WebSiteManagerConstants.ctxMap.get(agentNo+"_"+WebConstants.WEB_AGENT_STATUS_ON);
			if(null==agentName||StringUtils.isBlank(agentName)){
				msg = "无效的代理商，系统可以正常使用！ ";
				request.getSession().removeAttribute("agentNo");
			}
		}
		ServletUtils.outPrintSuccess(request, response, msg);
	}
	
	@RequestMapping("/goRegister")
	public ModelAndView goRegister(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView model = new ModelAndView("m//register");
		return model.addObject("birthday", sdf.format(new Date())).addObject("ctxMap", WebSiteManagerConstants.ctxMap);
	}
	
	/**
	 * 注册
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/doRegister")
	public void doRegister(HttpServletRequest request,HttpServletResponse response,WebUser webUser) {
		try {
			String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
			if (!validateUser(webUser)) {
				logger.info("注册信息有误，请重新填写。");
				ServletUtils.outPrintWithToken(request, response, "注册信息有误，请重新填写。");
				return;
			}
			WebUser twu = this.webUserService.findWebrUseByUserName(webUser.getUserName());
			if(twu!=null){
				logger.info(webUser.getUserName()+"已经存在,请换帐号。");
				ServletUtils.outPrintWithToken(request, response, webUser.getUserName()+"已经存在,请换帐号。");
				return;
			}
			WebUserFlat webUserFlat = this.webUserService.findWebUserFlatByUserName(webUser.getUserName());
			if(webUserFlat!=null){
				logger.info(webUser.getUserName()+"已经存在,请换帐号。");
				ServletUtils.outPrintWithToken(request, response, webUser.getUserName()+"已经存在,请换帐号。");
				return;
			}
			
			
			
			Date currDate = new Date();
			webUser.setUserStatus(1);
			webUser.setUserType(1);
			webUser.setUserLoginTimes(1);
			webUser.setCreateTime(currDate);
			webUser.setUserMoney(0D);
			webUser.setUserFlag(userFlag);// 用户标识
			webUser.setUserPsTimes(0);
			String cryptPassword= "";
			try {
				cryptPassword = DesUtil.encrypt(webUser.getUserPassword(), CommonConstant.resCommMap.get("app.client.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
 
			webUser.setUserPassword(cryptPassword);
			String cryptWithdrawPassword= "";
			try {
				cryptWithdrawPassword = DesUtil.encrypt(webUser.getUserWithdrawPassword(), CommonConstant.resCommMap.get("app.money.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			webUser.setUserWithdrawPassword(cryptWithdrawPassword);
			webUser.setUserLastLoginTime(currDate);
			webUser.setUserLastLoginIp(IPSeeker.getIpAddress(request));
			webUser.setRegistIp(IPSeeker.getIpAddress(request));
			webUser.setModifyTime(currDate);
			
			String agentName = WebSiteManagerConstants.ctxMap.get(webUser.getUserAgent()+"_"+WebConstants.WEB_AGENT_STATUS_ON);//内存中获取代理账号(代理编码_1)
			if(StringUtils.isEmpty(agentName)){//代理账号为空
				webUser.setUserAgent(getAgentNo(request));//使用session中的有效代理账号  
			}else{//不为空 有效的代理账号
				webUser.setUserAgent(agentName);
			}
			
			//webUser.setUserAgent(getAgentNo(request));// 代理帐号
			webUser.setUserSessionId(request.getSession().getId());
			
			webUser.setUserLastLoginDomain(this.getUserLoginDomain(request));
			
			webUser.setRegistDevice(CheckDeviceUtil.checkDevice(request));
			webUser.setLoginDevice(CheckDeviceUtil.checkDevice(request));
			this.webUserService.saveWebUser(request,webUser);
			
 
			bind(webUser,request);
			
			/** 触发通知 **/
			MemCachedUtil.setRegisterNotice(userFlag);
			String content = WebSiteManagerConstants.ctxMap.get("msg009");
			boolean needAlter = StringUtils.isNotEmpty(content)?true:false;
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", Boolean.toString(needAlter));
			map.put("content", content);
			map.put("msg", "註冊成功");
			ServletUtils.outPrintSuccess(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户注册异常: "+e.getMessage(), e);
			ServletUtils.outPrintWithToken(request, response, "用户注册异常！");
		}
	}
	
	/***
	 * 校验注册信息
	 * 
	 * @return
	 */
	public boolean validateUser(WebUser user) {
		if (ValidStringUtil.validUserName(user.getUserName()) && ValidStringUtil.validPassword(user.getUserPassword()) && ValidStringUtil.validWithdrawPw(user.getUserWithdrawPassword())
				&& ValidStringUtil.validName(user.getUserRealName())) {
			if (StringUtils.isNotBlank(user.getUserQq())) {
				if (!ValidStringUtil.validQQ(user.getUserQq())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/** 获取代理帐号 **/
	private String getAgentNo(HttpServletRequest request) {
		String agentNo = (String) request.getSession().getAttribute("agentNo");// 代理号是放入session中
		String agentName = WebSiteManagerConstants.ctxMap.get(agentNo+"_"+WebConstants.WEB_AGENT_STATUS_ON);
		if (StringUtils.isNotBlank(agentNo)&&StringUtils.isNotBlank(agentName)) {
			agentNo = agentName;
		} else {
			agentNo = SystemConstant.webDefaultAgentNo;
		}
		return agentNo;
	}
	
	/**
	 * 绑定用户上下文数据到session中
	 * @param user
	 * @param request
	 */
	private void bind(WebUser user, HttpServletRequest request){
		if(null == user)
			throw new RuntimeException("待载入缓存的用户对象为空错误！");
		logger.info("用户登入验证通过，开始绑定用户到Session中");
		
		UserContext uc = new UserContext();
		try {
			uc.setUserId(user.getId());
			uc.setUserName(user.getUserName());
			uc.setLastLogonTime(user.getUserLastLoginTime());
			uc.setIp(IPSeeker.getIpAddress(request));
			uc.setUserType(user.getUserType());
			uc.setUserMemberType(user.getUserType());
			uc.setUserMoney(user.getUserMoney());
	 
			//绑定用户权限到用户上下文
			this.bindCustomDatas(uc);
		} catch (RuntimeException e) {
			logger.error("绑定用户上下文数据出现异常：", e);
			throw new RuntimeException(e);
		}
		
		logger.info("绑定用户到Session中结束");
		uc.setSessionId(request.getSession().getId());
 
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			MySessionContext.getSession(request.getSession().getId()).setAttribute(CommonConstant.USER_CONTEXT_KEY, uc);
		}else{
			request.getSession().setAttribute(CommonConstant.USER_CONTEXT_KEY, uc);
			MySessionContext.addSession(request.getSession());
		}
		
	}
	
	/**
	 * 绑定jar包外部用户定义数据到Session
	 * @param uc
	 */
	private void bindCustomDatas(UserContext uc){
		if(this.accessores  != null && this.accessores.size() > 0){
			for(UserContextAccessor accessor : accessores){
				if(accessor != null)
					accessor.addCustomDatas(uc);
			}
		}
	}
}
