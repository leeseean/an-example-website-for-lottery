/**   
* 文件名称: WebRegisterController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-7 下午1:42:03<br/>
*/  
package com.mh.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestParam;
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
import com.mh.entity.WebGongGao;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.service.WebUserFlatService;
import com.mh.service.WebUserService;
import com.mh.web.login.UserContext;
import com.mh.web.login.UserContextAccessor;
import com.mh.web.servlet.MySessionContext;
import com.mh.web.util.CheckDeviceUtil;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-7 下午1:42:03<br/>
 */
@Controller
public class WebRegisterController  extends BaseController{
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WebUserFlatService webUserFlatService;
	
	@Autowired(required=false)
	private List<UserContextAccessor> accessores;

	/**
	 * 跳转到注册页面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response) {
		
		UserContext uc = null;
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		}
		
		String agentNo = (String) request.getSession().getAttribute("agentNo");// 代理号是放入session中
		String msg = "";
		
		if(StringUtils.isBlank(agentNo)){
			String agentName = WebSiteManagerConstants.ctxMap.get(agentNo+"_"+WebConstants.WEB_AGENT_STATUS_ON);
			if(null==agentName||StringUtils.isBlank(agentName)){
				msg = "无效的代理商，系统可以正常使用！ ";
				request.getSession().removeAttribute("agentNo");
				agentNo = "888";
			}else{
				agentNo = agentName;
			}
		}
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		//判断是否已经登录,已登录状态下不能注册 跳转到首页
		if(null != uc)
		{
			return new ModelAndView("main").addObject("webUser", uc);
		}
		return new ModelAndView("web/register")
			  .addObject("user", uc)
			  .addObject("msg",msg)
			  .addObject("agentNo", agentNo)
			  .addObject("dataList", dataList);
	}
	
	
	/**
	 * 皇冠体育注册
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/doHgRegister")
	public void doHgRegister(HttpServletRequest request,HttpServletResponse response) 
	{
		
		//初始化参数
	
		try 
		{
			String username = request.getParameter("userName");
			if(StringUtils.isEmpty(username)){
				logger.info("用户名为空!");
				outPut(request,response,"用户名为空!",1);
				return;
			}
			WebUser webUser = initParam(request);
			String password = webUser.getUserPassword();//密码 注册成功alert()使用
			
			String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
			if (!validateUser(webUser)) 
			{
				logger.info("注册信息有误，请重新填写。");
				outPut(request,response,"注册信息有误，请重新填写。",1);
				return;
			}
			WebUser twu = this.webUserService.findWebrUseByUserName(webUser.getUserName());
			if(twu!=null)
			{
				logger.info(webUser.getUserName()+"已经存在,请换帐号。");
				outPut(request,response,webUser.getUserName()+"已经存在,请换帐号。",1);
				return;
			}
			WebUserFlat webUserFlat = this.webUserService.findWebUserFlatByUserName(webUser.getUserName());
			if(webUserFlat!=null)
			{
				logger.info(webUser.getUserName()+"已经存在,请换帐号。");
				outPut(request,response,webUser.getUserName()+"已经存在,请换帐号。",1);
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
			outPut(request,response,"註冊成功!\\n" + "用户名:" + webUser.getUserName() + "\\n密码:" + password + "\\n名称:" + webUser.getUserRealName(),0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户注册异常: "+e.getMessage(), e);
			outPut(request,response,"用户注册异常!",1);
		}
	}
	
	/**
	 * 初始化注册参数
	 * @param request
	 * @return
	 */
	private WebUser initParam(HttpServletRequest request)
	{
		WebUser user = new WebUser();
		user.setUserName(request.getParameter("userName").toLowerCase());
		user.setUserPassword(request.getParameter("userPassword"));
		user.setUserRealName(request.getParameter("userRealName"));
		user.setUserMail(request.getParameter("userEmail"));
		user.setUserMobile(request.getParameter("userMobile"));
		user.setUserQq(request.getParameter("userQq"));
		//request.getParameter("birthday");
		String pwd1 = request.getParameter("pwd1");
		String pwd2 = request.getParameter("pwd2");
		String pwd3 = request.getParameter("pwd3");
		String pwd4 = request.getParameter("pwd4");
		user.setUserWithdrawPassword(pwd1+pwd2+pwd3+pwd4);
		return user;
	}
	
	/**
	 * 输出
	 * @param response
	 * @param msg
	 * @param status 0:成功 1:失败
	 */
	private void outPut(HttpServletRequest request,HttpServletResponse response,String msg,int status)
	{
		StringBuffer innerHtml = new StringBuffer("<script>alert('"+msg+"');</script>");//输出html
		if(status == 0)
		{
			String doMain = getWebDomain(request);
			innerHtml.setLength(0);
			innerHtml.append("<form id=\"goSport\" name=\"goSport\" method=\"POST\" action=\""+doMain+"sport/main\">");
			innerHtml.append("</form>");
			innerHtml.append("<script>alert('"+msg+"');");
			innerHtml.append("document.getElementById('goSport').submit();");
			innerHtml.append("</script>");
		}
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter print = null;
		try 
		{
			print = response.getWriter();
			print.write(innerHtml.toString());
		} 
		catch (IOException e) 
		{
			logger.error("服务器异常" + e);
		}
		finally
		{
			if(null != print)
			{
				print.flush();
				print.close();
			}
		}
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
			if(StringUtils.isEmpty(webUser.getUserName())){
				logger.info("用户名为空!");
				ServletUtils.outPrintWithToken(request, response,"用户名为空!");
				return;
			}
			webUser.setUserName(webUser.getUserName().toLowerCase());
			
			String agentName = WebSiteManagerConstants.ctxMap.get(webUser.getUserAgent()+"_"+WebConstants.WEB_AGENT_STATUS_ON);//内存中获取代理账号(代理编码_1)
			if(StringUtils.isEmpty(agentName)){//代理账号为空
				webUser.setUserAgent(getAgentNo(request));//使用session中的有效代理账号  
			}else{//不为空 有效的代理账号
				webUser.setUserAgent(agentName);
			}
			
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
	
	
	/**
	 * 推广注册
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/doPromoRegister")
	public void doPromoRegister(HttpServletRequest request,HttpServletResponse response,WebUser webUser) {
		try {
			
			if(!this.checkVerify(request, response)){
				return ;
			}
			
			String pwd = webUser.getUserPassword();
			if(StringUtils.isEmpty(webUser.getUserName())){
				logger.info("用户名为空!");
				ServletUtils.outPrintWithToken(request, response,"用户名为空!");
				return;
			}
			if(StringUtils.isEmpty(webUser.getUserPassword())){
				logger.info("密码为空!");
				ServletUtils.outPrintWithToken(request, response,"密码为空!");
				return;
			}
			
			webUser.setUserName(webUser.getUserName().toLowerCase());
			
			String agentName = WebSiteManagerConstants.ctxMap.get(webUser.getUserAgent()+"_"+WebConstants.WEB_AGENT_STATUS_ON);//内存中获取代理账号(代理编码_1)
			if(StringUtils.isEmpty(agentName)){//代理账号为空
				webUser.setUserAgent(getAgentNo(request));//使用session中的有效代理账号  
			}else{//不为空 有效的代理账号
				webUser.setUserAgent(agentName);
			}
			
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
			//webUser.setUserAgent(getAgentNo(request));// 代理帐号
			webUser.setUserSessionId(request.getSession().getId());
			
			webUser.setUserLastLoginDomain(this.getUserLoginDomain(request));
			webUser.setRegistDevice(CheckDeviceUtil.checkDevice(request));
			webUser.setLoginDevice(CheckDeviceUtil.checkDevice(request));
			this.webUserService.saveWebUser(request,webUser);
			
 
			bind(webUser,request);
			
			/** 触发通知 **/
			MemCachedUtil.setRegisterNotice(userFlag);
			Map<String, String> map = new HashMap<String, String>();
			map.put("userName", webUser.getUserName());
			map.put("pwd", pwd);
			ServletUtils.outPrintSuccess(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户注册异常: "+e.getMessage(), e);
			ServletUtils.outPrintWithToken(request, response, "用户注册异常！");
		}
	}
	
	
	/**
	 * 检查验证码是否正确
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean checkVerify(HttpServletRequest request, HttpServletResponse response){
		//验证码
		String verifycode = request.getParameter("verifycode");
		if(StringUtils.isBlank(verifycode)){
			ServletUtils.outPrintWithToken(request, response,  "请输入验证码");
			return false;
		}
		
		Object code = null;
		if(request.getSession().getAttribute(CommonConstant.REGISTER_VERITY_CODE_KEY)!=null){
			code = request.getSession().getAttribute(CommonConstant.REGISTER_VERITY_CODE_KEY);
		}
		
		if(code == null){
			ServletUtils.outPrintWithToken(request, response, "请重新获取验证码");
			logger.info("Session 中没有验证码信息 返回页面 重新获取验证码");
			return false;
		}
		
		if(!verifycode.equalsIgnoreCase(code.toString())){
			ServletUtils.outPrintWithToken(request, response, "验证码错误");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证用户名是否重复
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param userName  
	 * void
	 */
	@RequestMapping("/valid/registerUserName")
	public void validRegisterUserName(HttpServletRequest request,HttpServletResponse response,@RequestParam("userName") String userName) {
		try {
			 
			WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
			if(webUser!=null){
				logger.info(userName+"已经存在,请换帐号。");
				ServletUtils.outPrintWithToken(request, response, "已经存在,请换帐号。");
				return;
			}
			WebUserFlat webUserFlag = this.webUserFlatService.getWebUserFlat(userName);
			if(webUserFlag!=null){
				logger.info(userName+"已经存在,请换帐号。");
				ServletUtils.outPrintWithToken(request, response, "已经存在,请换帐号。");
				return;
			}
			
			ServletUtils.outPrintSuccess(request, response, "验证用户名成功");
		} catch (RuntimeException e) {
			logger.error("验证用户名异常: "+e.getMessage(), e);
			ServletUtils.outPrintWithToken(request, response, "验证用户名出现异常");
		}
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
	/**
	 * 跳转到帮助页面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/web/help")
	public ModelAndView help(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("/web/help");
	}
}
