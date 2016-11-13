/**   
* 文件名称: LogonController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-5 下午1:38:51<br/>
*/  
package com.mh.web.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.constants.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.SysParameter;
import com.mh.entity.WebGongGao;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserLog;
import com.mh.service.SysParameterService;
import com.mh.service.WebMessageService;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.servlet.MySessionContext;
import com.mh.web.servlet.MyTokenSessionContext;
import com.mh.web.util.CheckDeviceUtil;

/** 
 * 类描述: TODO<br/>用户登入验证Controller
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-5 下午1:38:51<br/>
 */
@SuppressWarnings("all")
@Controller
public class LogonController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(LogonController.class);

	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WebMessageService webMessageService;
	
	@Autowired
	private SysParameterService sysParameterService;
	
	@Autowired(required=false)
	private List<UserContextAccessor> accessores;
	
 
	
	
	/**
	 * 首页跳转地址
	 * 方法描述: TODO</br> 
	 * @param backToUrl
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping(value="/main/{backToUrl}",method=RequestMethod.GET)  
	public ModelAndView goBackPage(@PathVariable("backToUrl") String backToUrl,HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView("web/loginFrame")
			.addObject("backToUrl", backToUrl);
	}

	
	/**
	 * 跳转到首页
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response) {
		WebUser webUser = null;
		 
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			String reFlag = (String)request.getSession().getAttribute(CommonConstant.CONFIRM_USER_CONTEXT_KEY);
			if("ok".equals(reFlag)){
				MySessionContext.getSession(request.getSession().getId()).removeAttribute(CommonConstant.USER_CONTEXT_KEY);
				MySessionContext.getSession(request.getSession().getId()).removeAttribute(CommonConstant.VERITY_CODE_KEY);
			}else{
				UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
				if(uc!=null && uc.getUserId()!=null){
					webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
				}
			}
		}
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		
		return new ModelAndView("main")
			.addObject("webUser", webUser)
			.addObject("dataList", dataList)
			.addObject("linkList",WebSiteManagerConstants.WEBPAGE_LIST);
	}
	
	
	/**
	 * 跳转到规则页面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/loginRule")
	public ModelAndView loginRule(HttpServletRequest request,HttpServletResponse response) {
		//防止直接访问
		String referer =request.getHeader("Referer");
		if(StringUtils.isEmpty(referer)){
			String loginUrl = this.getWebDomain(request);
			response.setHeader("Location",loginUrl);
			try {
				response.sendRedirect(loginUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		MySessionContext.getSession(request.getSession().getId()).setAttribute(CommonConstant.CONFIRM_USER_CONTEXT_KEY, "ok");
		
		return new ModelAndView("web/rule");
	}
	
	
	
	@RequestMapping("/loginComfire")
	public void loginComfire(HttpServletRequest request,HttpServletResponse response) {
		try {
			if(MySessionContext.getSession(request.getSession().getId())!=null){
				MySessionContext.getSession(request.getSession().getId()).removeAttribute(CommonConstant.CONFIRM_USER_CONTEXT_KEY);
			}
			
			ServletUtils.outPrintSuccess(request, response,"登录验证成功！");
		} catch (RuntimeException e) {
			logger.error("登录验证异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "登录验证异常");
		}
	}
	
	
	/**
	 * 用户登入
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) {
		Enumeration enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String key = enumeration.nextElement().toString();
			if(!isLegal(key)){
				ServletUtils.outPrintWithToken(request, response, "包含非法字符");
				return ;
			}
			String val = request.getParameter(key);
			if(!isLegal(val)){
				ServletUtils.outPrintWithToken(request, response, "包含非法字符");
				return ;
			}
		}
		
		String mobileUrl = CommonConstant.resCommMap.get("MOBILE_LOGIN_URL");
		if(StringUtils.isBlank(mobileUrl))
			mobileUrl = "/mobile/main/index";
		//logger.info("获取用户登录信息并进行判断");
		try {
			if(!this.checkVerify(request, response)){
				return ;
			}
			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");
			
			if(StringUtils.isBlank(loginName)){
				ServletUtils.outPrintWithToken(request, response, "请输入用户名");
				logger.info("用户名为空，返回登入界面");
				return ;
			}
			if(StringUtils.isBlank(password)){
				ServletUtils.outPrintWithToken(request, response, "请输入密码");
				logger.info("密码为空，返回登入界面");
				return ;
			}
			try {
				password = DesUtil.encrypt(password, CommonConstant.resCommMap.get("app.client.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int userPsTimes = NumberUtils.toInt(WebSiteManagerConstants.SYSPARAMMAP.get("user_ps_times").toString(), 5);
			
			logger.info("根据用户名={} 密码={} 获取用户对象",loginName,password);
//			WebUser webUser = this.webUserService.findWebrUser(loginName, password);
			WebUser webUser = this.webUserService.findWebrUseByUserName(loginName);
	 
			
			List<String> fieldList = new ArrayList<String>();
			List<Object> valList = new ArrayList<Object>();
			Date currDate = new Date();
			if(webUser==null){
				ServletUtils.outPrintWithToken(request, response, "帐号不存在!");
				return ;
			}else{
				if(!CommonConstant.STATUS_USABLE.equals(String.valueOf(webUser.getUserStatus()))){
					logger.info("用户 ID={} 的状态不可用",webUser.getUserStatus());
					ServletUtils.outPrintWithToken(request, response, "帐号已被冻结。请联系我们24小时在线客服！");
					return ;
				}
				if(!webUser.getUserPassword().equals(password)){//密码错误
					String warn = "";
					
					String lastTimeStr =DateUtil.format(webUser.getModifyTime(), "yyyy-MM-dd");
					String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
					if(!currDateStr.equals(lastTimeStr)){
						webUser.setUserPsTimes(0);
					}
					
					int userHasPsTimes = 0;//密码输入错误的次数
					if(webUser.getUserPsTimes()!=null){
						userHasPsTimes =webUser.getUserPsTimes().intValue();
					}
					userHasPsTimes++;
					if (userHasPsTimes >= userPsTimes) {// 冻结帐号
						webUser.setUserStatus(0);
						warn = "密码输入错误超过限制，冻结被帐号。请联系我们24小时在线客服！";
					} else {
						warn = "密码输入错误，超过" + userPsTimes + "次将被冻结。目前第" + (userHasPsTimes) + "次";
					}
					fieldList.add("user_login_times");
					fieldList.add("user_status");
					fieldList.add("modify_time");
					fieldList.add("user_ps_times");
					
					valList.add(userHasPsTimes);
					valList.add(webUser.getUserStatus());
					valList.add(currDate);
					valList.add(userHasPsTimes);
					
					int rows = this.webUserService.updateWebUser(webUser.getUserName(), fieldList, valList);
					if(rows<1){
						throw new RuntimeException("用户信息更新失败!");
					}
					
//					webUser.setUserPsTimes(userHasPsTimes);
//					webUser.setModifyTime(new Date());
//					this.webUserService.updateWebUser(webUser);
					ServletUtils.outPrintWithToken(request, response,warn);
					logger.info(warn);
					return ;
				}else{
					int userHasPsTimes = 0;//密码输入错误的次数
					if(webUser.getUserPsTimes()!=null){
						userHasPsTimes =webUser.getUserPsTimes().intValue();
					}
					if(userHasPsTimes != 0){//清空密码输入错误次数
						fieldList.add("user_ps_times");
						valList.add(0);
						int rows = this.webUserService.updateWebUser(webUser.getUserName(), fieldList, valList);
						if(rows<1){
							throw new RuntimeException("用户信息更新失败!");
						}
					}
				}
			}
			
			//已经登入不在绑定上下文
			if(MySessionContext.getSession(request.getSession().getId())!=null){
				UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
				if(uc != null && uc.getUserId() != null){
					ServletUtils.outPrintSuccess(request, response, "登入成功",mobileUrl);
					clearVerify(request);
					logger.info("用户已经登入过，不在绑定用户信息，直接返回主页");
					return ;
				}
			}
			
			if(MySessionContext.getSession(webUser.getUserSessionId())!=null){
				HttpSession oldSession = MySessionContext.getSession(webUser.getUserSessionId());
				try{
					if(oldSession.getAttribute(CommonConstant.USER_CONTEXT_KEY)!=null){
						//移除session
						//TODO
						oldSession.removeAttribute(CommonConstant.USER_CONTEXT_KEY);
						//oldSession.removeAttribute(CommonConstant.VERITY_CODE_KEY);
						MySessionContext.delSession(oldSession);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			//新增myTokenSession容器记录
			if(MyTokenSessionContext.hashUserSession(loginName)){
				MyTokenSessionContext.delMySession(loginName);
			}
			if(MyTokenSessionContext.hasUserClientTokenKey(loginName)){
				MyTokenSessionContext.delMyClientToken(loginName);
			}
			if(MyTokenSessionContext.sessionUser.containsKey(loginName)){
				MyTokenSessionContext.sessionUser.remove(loginName);
			}
			
			try{
				request.getSession().invalidate();//清除之前的session创建新的
			}catch (Exception e) {
			}
			
			//限制同时只能一个登陆
			MySessionContext.addSession(request.getSession());
			MyTokenSessionContext.addMySession(loginName, request.getSession());
			
			this.doLogon(request, webUser);
			this.updateUser(webUser, request,response);
			
//			clearVerify(request);
			
 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户登入出现异常: "+e.getMessage(), e);
			ServletUtils.outPrintWithToken(request, response, "用户登入出现异常!");
			return ;
		}
		ServletUtils.outPrintSuccess(request, response, "用户登入成功",mobileUrl);
		return ;
	}	
	
	/**
	 * 推广 用户登入
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/promologin")
	public void promologin(HttpServletRequest request,HttpServletResponse response) {
		Enumeration enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String key = enumeration.nextElement().toString();
			if(!isLegal(key)){
				ServletUtils.outPrintWithToken(request, response, "包含非法字符");
				return ;
			}
			String val = request.getParameter(key);
			if(!isLegal(val)){
				ServletUtils.outPrintWithToken(request, response, "包含非法字符");
				return ;
			}
		}
		
		//logger.info("获取用户登录信息并进行判断");
		try {
			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");
			
			if(StringUtils.isBlank(loginName)){
				ServletUtils.outPrintWithToken(request, response, "请输入用户名");
				logger.info("用户名为空，返回登入界面");
				return ;
			}
			if(StringUtils.isBlank(password)){
				ServletUtils.outPrintWithToken(request, response, "请输入密码");
				logger.info("密码为空，返回登入界面");
				return ;
			}
			try {
				password = DesUtil.encrypt(password, CommonConstant.resCommMap.get("app.client.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int userPsTimes = NumberUtils.toInt(WebSiteManagerConstants.SYSPARAMMAP.get("user_ps_times").toString(), 5);
			
			logger.info("根据用户名={} 密码={} 获取用户对象",loginName,password);
//			WebUser webUser = this.webUserService.findWebrUser(loginName, password);
			WebUser webUser = this.webUserService.findWebrUseByUserName(loginName);
	 
			
			List<String> fieldList = new ArrayList<String>();
			List<Object> valList = new ArrayList<Object>();
			Date currDate = new Date();
			if(webUser==null){
				ServletUtils.outPrintWithToken(request, response, "帐号不存在!");
				return ;
			}else{
				if(!CommonConstant.STATUS_USABLE.equals(String.valueOf(webUser.getUserStatus()))){
					logger.info("用户 ID={} 的状态不可用",webUser.getUserStatus());
					ServletUtils.outPrintWithToken(request, response, "帐号已被冻结。请联系我们24小时在线客服！");
					return ;
				}
				if(!webUser.getUserPassword().equals(password)){//密码错误
					String warn = "";
					
					String lastTimeStr =DateUtil.format(webUser.getModifyTime(), "yyyy-MM-dd");
					String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
					if(!currDateStr.equals(lastTimeStr)){
						webUser.setUserPsTimes(0);
					}
					
					int userHasPsTimes = 0;//密码输入错误的次数
					if(webUser.getUserPsTimes()!=null){
						userHasPsTimes =webUser.getUserPsTimes().intValue();
					}
					userHasPsTimes++;
					if (userHasPsTimes >= userPsTimes) {// 冻结帐号
						webUser.setUserStatus(0);
						warn = "密码输入错误超过限制，冻结被帐号。请联系我们24小时在线客服！";
					} else {
						warn = "密码输入错误，超过" + userPsTimes + "次将被冻结。目前第" + (userHasPsTimes) + "次";
					}
					fieldList.add("user_login_times");
					fieldList.add("user_status");
					fieldList.add("modify_time");
					fieldList.add("user_ps_times");
					
					valList.add(userHasPsTimes);
					valList.add(webUser.getUserStatus());
					valList.add(currDate);
					valList.add(userHasPsTimes);
					
					int rows = this.webUserService.updateWebUser(webUser.getUserName(), fieldList, valList);
					if(rows<1){
						throw new RuntimeException("用户信息更新失败!");
					}
					
//					webUser.setUserPsTimes(userHasPsTimes);
//					webUser.setModifyTime(new Date());
//					this.webUserService.updateWebUser(webUser);
					ServletUtils.outPrintWithToken(request, response,warn);
					logger.info(warn);
					return ;
				}else{
					int userHasPsTimes = 0;//密码输入错误的次数
					if(webUser.getUserPsTimes()!=null){
						userHasPsTimes =webUser.getUserPsTimes().intValue();
					}
					if(userHasPsTimes != 0){//清空密码输入错误次数
						fieldList.add("user_ps_times");
						valList.add(0);
						int rows = this.webUserService.updateWebUser(webUser.getUserName(), fieldList, valList);
						if(rows<1){
							throw new RuntimeException("用户信息更新失败!");
						}
					}
				}
			}
			
			//已经登入不在绑定上下文
			if(MySessionContext.getSession(request.getSession().getId())!=null){
				UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
				if(uc != null && uc.getUserId() != null){
					ServletUtils.outPrintSuccess(request, response, "登入成功");
					clearVerify(request);
					logger.info("用户已经登入过，不在绑定用户信息，直接返回主页");
					return ;
				}
			}
			
			if(MySessionContext.getSession(webUser.getUserSessionId())!=null){
				HttpSession oldSession = MySessionContext.getSession(webUser.getUserSessionId());
				try{
					if(oldSession.getAttribute(CommonConstant.USER_CONTEXT_KEY)!=null){
						//移除session
						//TODO
						oldSession.removeAttribute(CommonConstant.USER_CONTEXT_KEY);
						//oldSession.removeAttribute(CommonConstant.VERITY_CODE_KEY);
						MySessionContext.delSession(oldSession);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			//新增myTokenSession容器记录
			if(MyTokenSessionContext.hashUserSession(loginName)){
				MyTokenSessionContext.delMySession(loginName);
			}
			if(MyTokenSessionContext.hasUserClientTokenKey(loginName)){
				MyTokenSessionContext.delMyClientToken(loginName);
			}
			if(MyTokenSessionContext.sessionUser.containsKey(loginName)){
				MyTokenSessionContext.sessionUser.remove(loginName);
			}
			
			try{
				request.getSession().invalidate();//清除之前的session创建新的
			}catch (Exception e) {
			}
			
			//限制同时只能一个登陆
			MySessionContext.addSession(request.getSession());
			MyTokenSessionContext.addMySession(loginName, request.getSession());
			
			this.doLogon(request, webUser);
			this.updateUser(webUser, request,response);
			
//			clearVerify(request);
			
 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户登入出现异常: "+e.getMessage(), e);
			ServletUtils.outPrintWithToken(request, response, "用户登入出现异常!");
			return ;
		}
		ServletUtils.outPrintSuccess(request, response, "用户登入成功");
		return ;
	}	
	
	/**
	 * 退出登入
	 * @param request
	 * @param response
	 */
	@RequestMapping("/loginOut")
	public void loginOut(HttpServletRequest request,HttpServletResponse response) {
		try {
 
			if(MySessionContext.getSession(request.getSession().getId())!=null){
				UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
				if(uc != null && uc.getUserId() != null){
					logger.info("用户{} 退出登入",uc.getUserId());
				}
				MySessionContext.getSession(request.getSession().getId()).removeAttribute(CommonConstant.USER_CONTEXT_KEY);
				MySessionContext.getSession(request.getSession().getId()).removeAttribute(CommonConstant.VERITY_CODE_KEY);
			}
			MySessionContext.delSession(request.getSession());
			request.getSession().invalidate();
			ServletUtils.outPrintSuccess(request, response);
			
		} catch (RuntimeException e) {
			logger.error("用户退出登入异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "用户退出登入出现异常");
		}
	}
	
	/**
	 * 刷新金额
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/valid/refreshUserMoney")
	public void refreshUserMoney(HttpServletRequest request,HttpServletResponse response) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			int total=0;
			double userMoney = 0;
			if(MySessionContext.getSession(request.getSession().getId())!=null){
				UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
				if(uc != null && uc.getUserId() != null){
					WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
					total = this.webMessageService.getMessageNum(uc.getUserName(), WebConstants.T_WEB_MESSAGE_TYPE_1+","+WebConstants.T_WEB_MESSAGE_TYPE_2, 
							WebConstants.T_WEB_MESSAGE_STATUS_1);
					
					if(webUser!= null && webUser.getUserMoney()!=null){
						userMoney= webUser.getUserMoney().doubleValue();
					}
				} 
			}
//			List<WebGongGao> gongGaoList = this.webGongGaoService.getWebGongGao();
			
			map.put("userMoney",userMoney);
			map.put("msgTotal", total);
//			map.put("gongGaoList", gongGaoList);
			ServletUtils.outPrintSuccess(request, response,map);
		} catch (RuntimeException e) {
			logger.error("用户退出登入异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "用户退出登入出现异常");
		}
	}
	
	
	/**
	 * 验证用户是否登录
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/valid/checkUserLogin")
	public void checkUserLogin(HttpServletRequest request,HttpServletResponse response) {
		try {
			if(MySessionContext.getSession(request.getSession().getId())!=null){
				UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
				if(uc != null && uc.getUserId() != null){
					ServletUtils.outPrintSuccess(request, response,"用户已登录");
					return;
				}
			}
			ServletUtils.outPrintFail(request, response, "用户未登陆");
		} catch (RuntimeException e) {
			logger.error("用户退出登入异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "验证登录异常");
		}
	}
	
	

	
	
	/**
	 * 
	 *功能说明: 登入操作
	 *@param request
	 *@param userId void
	 *
	 */
	public void doLogon(HttpServletRequest request, WebUser user){
		if(user == null) throw new IllegalArgumentException("用户不能为空");
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		if(uc != null && uc.getUserId().equals(user.getId())) return ;
		bind(user,request);
	}
	
	/**
	 * 更新用户登入信息
	 * @param user
	 * @param request
	 */
	private void updateUser(WebUser user, HttpServletRequest request,HttpServletResponse response){
		logger.info("更新用户登入信息");
		 
			
		
//			user.setUserLastLoginIp(IPSeeker.getIpAddress(request));// 本次登录IP登录下来，供下次查看
//			user.setUserLastLoginDomain(this.getUserLoginDomain(request));// 本次登录的域名
//			user.setUserLastLoginTime(new Date());// 本次登录时间记录下来，供下次查看
//			user.setUserLoginTimes(user.getUserLoginTimes() + 1);
//			user.setModifyTime(new Date());
//			user.setUserPsTimes(0);
//			user.setUserSessionId(request.getSession().getId());
//			user.setUserLoginStatus("1");
//			user.setUserLogonTime(new Date());
		
		List<String> fieldList = new ArrayList<String>();
		List<Object> valList = new ArrayList<Object>();
		Date currDate = new Date();
		fieldList.add("user_last_login_ip");
		fieldList.add("user_last_login_domain");
		fieldList.add("user_last_login_time");
		fieldList.add("user_login_times");
		fieldList.add("modify_time");
		fieldList.add("user_ps_times");
		fieldList.add("user_session_id");
		fieldList.add("login_device");
		
		valList.add(IPSeeker.getIpAddress(request));
		valList.add(this.getUserLoginDomain(request));
		valList.add(currDate);
		valList.add(user.getUserLoginTimes() + 1);
		valList.add(currDate);
		valList.add(0);
		valList.add(request.getSession().getId());
		valList.add(CheckDeviceUtil.checkDevice(request));
		int rows = this.webUserService.updateWebUser(user.getUserName(), fieldList, valList);
		if(rows<1){
			throw new RuntimeException("更新用户信息失败！");
		}
		
		//登录日志
		WebUserLog webUserLog = new WebUserLog();
		webUserLog.setCreateTime(new Date());
		webUserLog.setLogWebFlag(user.getUserFlag());// 网站标识
		webUserLog.setLogWebUserName(user.getUserName());// 帐号
		webUserLog.setLogAddress(IPSeeker.getIpAddress(request));// 地址
		webUserLog.setLogWebName(WebSiteManagerConstants.ctxMap.get("siteName"));// 网站名称
		webUserLog.setLogWebDomain(WebSiteManagerConstants.ctxMap.get("siteDomain"));
		
		this.webUserService.updateWebUser(null,webUserLog);
	 
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
			uc.setIp(user.getUserLastLoginIp());
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
		MySessionContext.getSession(request.getSession().getId()).setAttribute(CommonConstant.USER_CONTEXT_KEY, uc);
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
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			code = MySessionContext.getSession(request.getSession().getId()).getAttribute(CommonConstant.VERITY_CODE_KEY);
		}
		
		if(code == null){
			ServletUtils.outPrintWithToken(request, response, "登入超时，请重新登入");
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
	 * 清除验证码
	 * @param request
	 */
	private void clearVerify(HttpServletRequest request){
		logger.info("清除Session中验证码");

		MySessionContext.getSession(request.getSession().getId()).removeAttribute(CommonConstant.VERITY_CODE_KEY);
		request.getSession().removeAttribute(CommonConstant.VERITY_CODE_KEY);
	}
	
	
	
	
	
	/**
	 * 获取在线用户
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/getOnlineUser")
	public void getOnlineUser(HttpServletRequest request,HttpServletResponse response) {
		try {
 
			boolean reFlag = this.getAuthOnlineIp(request);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "无权限访问");
			}
			
			Collection<HttpSession> sessioinCol = MySessionContext.getAllHttpSessions();
			Iterator<HttpSession> iterator = null;
			iterator = sessioinCol.iterator();
			Set<String> set = new HashSet<String>();
			while (iterator.hasNext()) {
				try{
					HttpSession session = iterator.next();
					UserContext uc = (UserContext)session.getAttribute(CommonConstant.USER_CONTEXT_KEY);
					if(uc!=null){
						set.add(uc.getUserName());
					}
				}catch(Exception e){}	
			}
			List<String> list = new ArrayList<String>(set);
			Map<String,Object> map = new HashMap<String,Object>();
			StringBuffer buff = new StringBuffer("");
			for(int i=0;i<list.size();i++){
				if(i>0){
					buff.append(",");
				}
				buff.append(list.get(i));
			}
			
			map.put("onlineUser",buff.toString());
		 
			ServletUtils.outPrintSuccess(request, response, map);
		} catch (RuntimeException e) {
			logger.error("用户退出登入异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "验证登录异常");
		}
	}
	
	
	/**
	 * 强制退出
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/delOnlineUser")
	public void delOnlineUser(HttpServletRequest request,HttpServletResponse response) {
		try {
			boolean reFlag = this.getAuthOnlineIp(request);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "无权限访问");
			}
			
			String userAccounts = request.getParameter("userAccounts");
			if(StringUtils.isEmpty(userAccounts)){
				ServletUtils.outPrintFail(request, response, "请求参数userAccounts为空");
				return ;
			}
			String[] userArr= userAccounts.split(",");
			List<String> userList = Arrays.asList(userArr);
			
			Collection<HttpSession> sessioinCol = MySessionContext.getAllHttpSessions();
			Iterator<HttpSession> iterator = null;
			iterator = sessioinCol.iterator();
			while (iterator.hasNext()) {
				HttpSession session = iterator.next();
				try{
					UserContext uc = (UserContext)session.getAttribute(CommonConstant.USER_CONTEXT_KEY);
					if(uc!=null){
						String userName = uc.getUserName();
							if(userList.contains(userName)){
								session.removeAttribute(CommonConstant.USER_CONTEXT_KEY);
								session.removeAttribute(CommonConstant.VERITY_CODE_KEY);
								MySessionContext.delSession(session);
								session.invalidate();
							}
					}
				}catch (Exception e) {}
			}
			ServletUtils.outPrintSuccess(request, response, "强制退出成功");
		} catch (RuntimeException e) {
			logger.error("用户退出登入异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "验证登录异常");
		}
	}
	
	
	/**
	 * 获取鉴权ip地址
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<String>
	 */
	public boolean getAuthOnlineIp(HttpServletRequest request){
		SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName(CommonConstant.WEB_USER_ONLINE_AUTH_IP);
		String userIp = "";
		if(sysParameter!=null){
			userIp = sysParameter.getParamValue();
		}
		String authIp = IPSeeker.getIpAddress(request);
 
 
		String[] ipArr = userIp.split(",");
		for(int i=0;i<ipArr.length;i++){
			if(authIp.indexOf(ipArr[i])>0 || authIp.equals(ipArr[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 方法描述: 线路检测</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/lineCheck")
	public ModelAndView lineCheck(HttpServletRequest request, HttpServletResponse response){
		String ip = IPSeeker.getIpAddress(request);
		return new ModelAndView("web/line").addObject("localIp", ip);
	}
	
	
	/**
	 * mobile用户登入
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/loginForMobile")
	public void loginForMobile(HttpServletRequest request,HttpServletResponse response) {
		Enumeration enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String key = enumeration.nextElement().toString();
			if(!isLegal(key)){
				ServletUtils.outPrintWithToken(request, response, "包含非法字符");
				return ;
			}
			String val = request.getParameter(key);
			if(!isLegal(val)){
				ServletUtils.outPrintWithToken(request, response, "包含非法字符");
				return ;
			}
		}
		
		//logger.info("获取用户登录信息并进行判断");
		try {
			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");
			
			if(StringUtils.isBlank(loginName)){
				ServletUtils.outPrintWithToken(request, response, "请输入用户名");
				logger.info("用户名为空，返回登入界面");
				return ;
			}
			if(StringUtils.isBlank(password)){
				ServletUtils.outPrintWithToken(request, response, "请输入密码");
				logger.info("密码为空，返回登入界面");
				return ;
			}
			try {
				password = DesUtil.encrypt(password, CommonConstant.resCommMap.get("app.client.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int userPsTimes = NumberUtils.toInt(WebSiteManagerConstants.SYSPARAMMAP.get("user_ps_times").toString(), 5);
			
			logger.info("根据用户名={} 密码={} 获取用户对象",loginName,password);
//			WebUser webUser = this.webUserService.findWebrUser(loginName, password);
			WebUser webUser = this.webUserService.findWebrUseByUserName(loginName);
			List<String> fieldList = new ArrayList<String>();
			List<Object> valList = new ArrayList<Object>();
			Date currDate = new Date();
			if(webUser==null){
				ServletUtils.outPrintWithToken(request, response, "帐号不存在!");
				return ;
			}else{
				if(!webUser.getUserPassword().equals(password)){
					String warn = "";
					
					String lastTimeStr =DateUtil.format(webUser.getModifyTime(), "yyyy-MM-dd");
					String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
					if(!currDateStr.equals(lastTimeStr)){
						webUser.setUserPsTimes(0);
					}
					
					int userHasPsTimes = 0;
					if(webUser.getUserPsTimes()!=null){
						userHasPsTimes =webUser.getUserPsTimes().intValue();
					}
					userHasPsTimes++;
					if (userHasPsTimes >= userPsTimes) {// 冻结帐号
						webUser.setUserStatus(0);
						warn = "密码输入错误超过限制，冻结被帐号。请联系我们24小时在线客服！";
					} else {
						warn = "密码输入错误，超过" + userPsTimes + "次将被冻结。目前第" + (userHasPsTimes) + "次";
					}
					fieldList.add("user_login_times");
					fieldList.add("user_status");
					fieldList.add("modify_time");
					
					valList.add(userHasPsTimes);
					valList.add(webUser.getUserStatus());
					valList.add(currDate);
					
					int rows = this.webUserService.updateWebUser(webUser.getUserName(), fieldList, valList);
					if(rows<1){
						throw new RuntimeException("用户信息更新失败!");
					}
					
//					webUser.setUserPsTimes(userHasPsTimes);
//					webUser.setModifyTime(new Date());
//					this.webUserService.updateWebUser(webUser);
					ServletUtils.outPrintWithToken(request, response,warn);
					logger.info(warn);
					return ;
				}
			}
					
			if(!CommonConstant.STATUS_USABLE.equals(String.valueOf(webUser.getUserStatus()))){
				logger.info("用户 ID={} 的状态不可用",webUser.getUserStatus());
				ServletUtils.outPrintWithToken(request, response, "帐号已被冻结。请联系我们24小时在线客服！");
				return ;
			}
			
			//已经登入不在绑定上下文
			if(MySessionContext.getSession(request.getSession().getId())!=null){
				UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
				if(uc != null && uc.getUserId() != null){
					ServletUtils.outPrintSuccess(request, response, "登入成功");
					clearVerify(request);
					logger.info("用户已经登入过，不在绑定用户信息，直接返回主页");
					return ;
				}
			}
			
			if(MySessionContext.getSession(webUser.getUserSessionId())!=null){
				HttpSession oldSession = MySessionContext.getSession(webUser.getUserSessionId());
				//移除session
				oldSession.removeAttribute(CommonConstant.USER_CONTEXT_KEY);
				oldSession.removeAttribute(CommonConstant.VERITY_CODE_KEY);
				MySessionContext.delSession(oldSession);
			}
			
			try{
				request.getSession().invalidate();//清除之前的session创建新的
			}catch (Exception e) {
			}
			
			//限制同时只能一个登陆
			MySessionContext.addSession(request.getSession());
			
			this.doLogon(request, webUser);
			this.updateUser(webUser, request,response);
			
//			clearVerify(request);
			
 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户登入出现异常: "+e.getMessage(), e);
			ServletUtils.outPrintWithToken(request, response, "用户登入出现异常!");
			return ;
		}
		ServletUtils.outPrintSuccess(request, response, "用户登入成功");
		return ;
	}
	
	
}
