/**   
* 文件名称: WebUserAgentLogonController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-28 上午4:00:07<br/>
*/  
package com.mh.web.login;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.SysParameter;
import com.mh.entity.WebAgent;
import com.mh.entity.WebUser;
import com.mh.service.SysParameterService;
import com.mh.service.WebAgentLogService;
import com.mh.service.WebAgentService;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.servlet.MySessionContext;

/** 
 * 
 * 用户代理登录Controller
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-28 上午4:00:07<br/>
 */

@SuppressWarnings("all")
@Controller
@RequestMapping("/agent")
public class WebUserAgentLogonController extends BaseController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(WebUserAgentLogonController.class);
	
	@Autowired
	private WebUserService webUserService;
	@Autowired
	private WebAgentService webAgentService;
	@Autowired
	private WebAgentLogService webAgentLogService;
	
	@Autowired
	private SysParameterService sysParameterService;
	
	
	
	/**
	 * 跳转到用户登录界面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping(value="/index")  
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView("agent/login");
	}
	
	/**
	 * 表达自动提交
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/submitLogin")  
	public ModelAndView submitLogin(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("authKey") String authKey){
		
		return new ModelAndView("agent/submitLogin")
		.addObject("authKey", authKey);
	}
	
	/**
	 * 用户鉴权
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/userAuth")  
	public void userAuth(HttpServletRequest request,HttpServletResponse response){
		try{
			String authIp = IPSeeker.getIpAddress(request);
			boolean reFlag = this.getAuthLoginIp(authIp);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "无权限访问");
				return;
			}
//			String userName = request.getParameter("userName");
//			if(StringUtils.isEmpty(userName)){
//				logger.info("参数userName不能为空！");
//				ServletUtils.outPrintFail(request, response, "参数userName不能为空！");
//			}
			Date currDate = new Date();
			String dateStr = DateUtil.format(currDate, "yyyy-MM-dd HH:mm:ss");
			StringBuffer buff = new StringBuffer("");
			buff.append(authIp);
			buff.append("|");
			buff.append(dateStr);
			String authKey="";
			try {
				authKey = DesUtil.encrypt(buff.toString(), CommonConstant.resCommMap.get("app.client.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("authKey", authKey);
			ServletUtils.outPrintSuccess(request, response,paramMap);
			
			
		}catch(Exception e){
			ServletUtils.outPrintFail(request, response, "登录鉴权失败");
			logger.error("登录鉴权失败",e);
		}
	}
	
	
	/**
	 * 后台登录鉴权
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/loginAuth")  
	public ModelAndView loginAuth(HttpServletRequest request,HttpServletResponse response){
		String authKey = request.getParameter("authKey");
		if(StringUtils.isEmpty(authKey)){
			logger.info("鉴权key为空！");
			ServletUtils.outPrintFail(request, response, "鉴权key为空");
			return null;
		}
		try {
			authKey = DesUtil.decrypt(authKey, CommonConstant.resCommMap.get("app.client.des.key"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("鉴权key解密失败！",e);
			ServletUtils.outPrintFail(request, response, "鉴权key解密失败");
			return null;
		}
		if(authKey.indexOf("|")==-1){
			logger.info("非法的鉴权key！");
			ServletUtils.outPrintFail(request, response, "非法的鉴权key");
			return null;
		}
		String authIp = authKey.substring(0, authKey.indexOf("|"));
		String dateStr =authKey.substring(authKey.indexOf("|")+1);
		
		
		boolean reFlag = this.getAuthLoginIp(authIp);
		if(!reFlag){
			logger.info("非法的IP地址！");
			ServletUtils.outPrintFail(request, response, "非法的IP地址");
			return null;
		}
		Date currDate = new Date();
		Date currDate2 = DateUtil.addMinute(currDate, -1);
		long times1= currDate2.getTime();
		long times2=0;
		try {
			Date authDate = DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
			times2 = authDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(times1>times2){
			logger.info("鉴权key失效！");
			ServletUtils.outPrintFail(request, response, "鉴权key失效！");
			return null;
		}
		
		
		
		UserContext uc = new UserContext();
		uc.setUserId(-1);
		uc.setUserName("admin");
		uc.setLastLogonTime(new Date());
		uc.setIp(IPSeeker.getIpAddress2(request));
		uc.setAdmin(true);
		MySessionContext.addSession(request.getSession());
		request.getSession().setAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY, uc);
		
		return new ModelAndView("agent/main");
	}
	
	
	/**
	 * 获取鉴权ip地址
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<String>
	 */
	public boolean getAuthLoginIp(String authIp){
		SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName(CommonConstant.WEB_USER_ONLINE_AUTH_IP);
		String userIp = "";
		if(sysParameter!=null){
			userIp = sysParameter.getParamValue();
		}
 
 
		String[] ipArr = userIp.split(",");
		for(int i=0;i<ipArr.length;i++){
			if(authIp.indexOf(ipArr[i])>0 || authIp.equals(ipArr[i])){
				return true;
			}
		}
		return false;
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
			
			logger.info("根据用户名={} 密码={} 获取用户对象",loginName,password);
			WebUser webUser = this.webUserService.findWebrUseByUserName(loginName);
			if(webUser==null){
				ServletUtils.outPrintWithToken(request, response, "帐号不存在!");
				return ;
			}
					
			if(!CommonConstant.STATUS_USABLE.equals(String.valueOf(webUser.getUserStatus()))){
				logger.info("用户 ID={} 的状态不可用",webUser.getUserStatus());
				ServletUtils.outPrintWithToken(request, response, "帐号已被冻结。请联系我们24小时在线客服！");
				return ;
			}
			
			WebAgent webAgent = this.webAgentService.findWebAgent(webUser.getUserName());
			if(webAgent==null){
				ServletUtils.outPrintWithToken(request, response, "帐号不存在!");
				return ;
			}
			
			if(!webUser.getUserPassword().equals(password)){
				logger.info("用户 userName={} 的密码不正确",webUser.getUserName());
				ServletUtils.outPrintWithToken(request, response, "密码不正确!");
				return ;
			}
			
			
			//已经登入不在绑定上下文
			UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
			if(uc != null && uc.getUserId() != null){
				ServletUtils.outPrintSuccess(request, response, "登入成功");
				clearVerify(request);
				logger.info("代理用户已经登入过，不在绑定用户信息，直接返回主页");
				return ;
			}
		 
			
			this.doLogon(request, webUser);
			this.updateUser(webUser, request,response);
			
			clearVerify(request);
			
 
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
	 * 退出
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/loginOut")
	public void loginOut(HttpServletRequest request,HttpServletResponse response) {
		try {
 
			UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
			if(uc != null && uc.getUserId() != null){
				logger.info("代理用户{} 退出登入",uc.getUserId());
			}
			request.getSession().invalidate();
			ServletUtils.outPrintSuccess(request, response);
			
		} catch (RuntimeException e) {
			logger.error("用户退出登入异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "用户退出登入出现异常");
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
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
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
		try {
			
			user.setUserLastLoginIp(IPSeeker.getIpAddress(request));// 本次登录IP登录下来，供下次查看
			user.setUserLastLoginDomain(this.getUserLoginDomain(request));// 本次登录的域名
			user.setUserLastLoginTime(new Date());// 本次登录时间记录下来，供下次查看
			user.setUserLoginTimes(user.getUserLoginTimes() + 1);
			
			this.webAgentLogService.updateWebAgentLog("登录", IPSeeker.getIpAddress(request), user.getUserName(), "用户登录");
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("更新用户登入信息失败: "+e.getMessage(), e);
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
			uc.setIp(user.getUserLastLoginIp());
			uc.setUserType(user.getUserType());
			uc.setUserMemberType(user.getUserType());
			uc.setUserMoney(user.getUserMoney());
	  
		} catch (RuntimeException e) {
			logger.error("绑定用户上下文数据出现异常：", e);
			throw new RuntimeException(e);
		}
		
		logger.info("绑定用户到Session中结束");
		uc.setSessionId(request.getSession().getId());
		request.getSession().setAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY, uc);
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
		if(request.getSession().getAttribute(CommonConstant.TEST_VERITY_CODE_KEY)!=null){
			code = request.getSession().getAttribute(CommonConstant.TEST_VERITY_CODE_KEY);
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

		request.getSession().removeAttribute(CommonConstant.TEST_VERITY_CODE_KEY);
	}

	/**
	 * 跳转到账户信息修改页面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goAccountUpdate")  
	public ModelAndView goPwd(HttpServletRequest request,HttpServletResponse response){
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		
		
		return new ModelAndView("agent/inc/account_update")
			.addObject("webUser", webUser);
	}
	
	
	
	/**
	 * 更新账户信息
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/doAccountUpdate")
	public void doAccountUpdate(HttpServletRequest request,HttpServletResponse response) {
		try {
//			String userByName = request.getParameter("userByName");
			String oldPassworld = request.getParameter("oldPassworld");
			String newPassworld1 = request.getParameter("newPassworld1");
			String newPassworld2 = request.getParameter("newPassworld2");
			String userMobile = request.getParameter("userMobile");
			String userMail = request.getParameter("userMail");
			if(StringUtils.isEmpty(oldPassworld)){
				ServletUtils.outPrintFail(request, response, "旧密码不能为空!");
				return;
			}
			if(StringUtils.isEmpty(newPassworld1)){
				ServletUtils.outPrintFail(request, response, "新密码不能为空!");
				return;
			}
			
			if(StringUtils.isEmpty(newPassworld2)){
				ServletUtils.outPrintFail(request, response, "确认新密码不能为空!");
				return;
			}
			if(!newPassworld1.equals(newPassworld2)){
				ServletUtils.outPrintFail(request, response, "二次输入密码不一致!");
				return;
			}
			
			
			UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
			
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			if(webUser==null){
				ServletUtils.outPrintFail(request, response, "更新账户不存在!");
				return;
			}
			try {
				oldPassworld = DesUtil.encrypt(oldPassworld, CommonConstant.resCommMap.get("app.client.des.key"));
				newPassworld1 = DesUtil.encrypt(newPassworld1, CommonConstant.resCommMap.get("app.client.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!oldPassworld.equals(webUser.getUserPassword())){
				ServletUtils.outPrintFail(request, response, "旧密码输入不正确!");
				return;
			}
//			webUser.setUserPassword(newPassworld1);
//			//webUser.setUserByName(userByName);
//			webUser.setUserMobile(userMobile);
//			webUser.setUserMail(userMail);
//			webUser.setModifyTime(new Date());
//			this.webUserService.updateWebUser(webUser);
			
			List<String> fieldList = new ArrayList<String>();
			List<Object> valList = new ArrayList<Object>();
			fieldList.add("user_password");
			fieldList.add("user_mobile");
			fieldList.add("user_mail");
			fieldList.add("modify_time");
			
			valList.add(newPassworld1);
			valList.add(userMobile);
			valList.add(userMail);
			valList.add(new Date());
 
			int rows = this.webUserService.updateWebUser(uc.getUserName(), fieldList, valList);
			if(rows<1){
				throw new RuntimeException("更新用户信息失败！");
			}
			
			
			this.webAgentLogService.updateWebAgentLog("修改", IPSeeker.getIpAddress(request), webUser.getUserName(), "账户信息");
			
			ServletUtils.outPrintSuccess(request, response,"更新成功!");
			
			request.getSession().removeAttribute(CommonConstant.USER_AGENT_CONTEXT_KEY);
		} catch (RuntimeException e) {
			logger.error("用户信息更新异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "用户信息更新异常");
		}
	}
	
}
