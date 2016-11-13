/**   
* 文件名称: WebTestController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-26 下午1:29:25<br/>
*/  
package com.mh.web.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
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

import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.SystemConstant;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.WebTestUser;
import com.mh.entity.WebUser;
import com.mh.ifc.AGIfcUtil;
import com.mh.ifc.OSIfcUtil;
import com.mh.ifc.http.AgConts;
import com.mh.ifc.http.AgResResult;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.OsConts;
import com.mh.ifc.http.OsResResult;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.StringHelper;
import com.mh.service.WebTestUserService;
import com.mh.service.WebUserService;
import com.mh.web.login.UserContext;
import com.mh.web.servlet.MySessionContext;

/** 
 * 
 * 试用账号Controller
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-26 下午1:29:25<br/>
 */

@Controller
@RequestMapping("/test")
public class WebTestController extends BaseController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(WebTestController.class);
	
	@Autowired
	private WebTestUserService webTestUserService;
	
	@Autowired
	private WebUserService webUserService;
	
	private String basePath = "";
	
	/**
	 * 跳转到试用账号界面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView lottery(HttpServletRequest request,HttpServletResponse response) {
		WebUser webUser = null;
		if (MySessionContext.getSession(request.getSession().getId()) != null) {
			UserContext uc = this.getUserContext(request);
			if (uc != null) {
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}

		String userName = this.getRandUserName();
		request.setAttribute("dataList", WebSiteManagerConstants.WEBGONGGAO_LIST);
		return new ModelAndView("web/test")
			.addObject("userName", userName).addObject("webUser", webUser);
	}
	
	
	
	/**
	 * 试用账号登陆
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response){
	 
		try{

			Enumeration enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String key = enumeration.nextElement().toString();
				if (!isLegal(key)) {
					ServletUtils.outPrintWithToken(request, response, "包含非法字符");
					return;
				}
				String val = request.getParameter(key);
				if (!isLegal(val)) {
					ServletUtils.outPrintWithToken(request, response, "包含非法字符");
					return;
				}
			}

			if (!this.checkVerify(request, response)) {
				return;
			}
			String loginName = request.getParameter("testLoginUserName");
			String password = request.getParameter("testLoginPwd");

			if (StringUtils.isBlank(loginName)) {
				ServletUtils.outPrintWithToken(request, response, "请输入用户名");
				logger.info("用户名为空，返回登入界面");
				return;
			}
			if (StringUtils.isBlank(password)) {
				ServletUtils.outPrintWithToken(request, response, "请输入密码");
				logger.info("密码为空，返回登入界面");
				return;
			}
			try {
				password = DesUtil.encrypt(password,CommonConstant.resCommMap.get("app.client.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			WebTestUser testUser = this.webTestUserService.getWebTestUserByUserName(loginName);
			if(testUser==null){
				ServletUtils.outPrintWithToken(request, response, "帐号不存在!");
				return ;
			}
			
			if(!testUser.getUserPassword().equals(password)){
				ServletUtils.outPrintWithToken(request, response, "密码错误,请重新输入!");
				return ;
			}
			
			ServletUtils.outPrintSuccess(request, response,"登陆成功！");
			request.getSession().setAttribute(CommonConstant.TEST_USER_CONTEXT_KEY, testUser);
		}catch(Exception e){
			logger.error("试用账号登陆异常！", e);
			ServletUtils.outPrintFail(request, response, "试用账号登陆异常！");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 试用账号注册
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/register")
	public synchronized void register(HttpServletRequest request,HttpServletResponse response){
	 
		try{

			String loginName = request.getParameter("testUserName");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			
			if(StringUtils.isEmpty(loginName)){
				ServletUtils.outPrintWithToken(request, response, "测试账号为空!");
				return ;
			}
			
			WebTestUser testUser = this.webTestUserService.getWebTestUserByUserName(loginName);
			if(testUser!=null){
				ServletUtils.outPrintWithToken(request, response, "帐号已存在,请刷新页面!");
				return ;
			}
			
			if(!password1.equals(password2)){
				ServletUtils.outPrintWithToken(request, response, "二次输入密码不一致!");
				return ;
			}
			try {
				password1 = DesUtil.encrypt(password1,CommonConstant.resCommMap.get("app.client.des.key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			testUser = new WebTestUser();
			testUser.setUserName(loginName);
			testUser.setUserPassword(password1);
			Date currDate = new Date();
			testUser.setCreateTime(currDate);
			testUser.setModifyTime(currDate);
			testUser.setUserLastLoginTime(currDate);
			testUser.setUserLastLoginIp(IPSeeker.getIpAddress(request));
			
			this.registAgAccout(testUser);
			
			if(testUser.getFlatUserName()!=null && testUser.getFlatPassword()!=null){
				Map<String,Object> valMap = new HashMap<String,Object>();
				String userName = this.getRandUserName();
				valMap.put("userName", userName);
				
				this.webTestUserService.saveOrUpdateWebTestUser(testUser);
				logger.info("试用账号注册成功！");
				ServletUtils.outPrintSuccess(request, response,valMap);
				request.getSession().setAttribute(CommonConstant.TEST_USER_CONTEXT_KEY, testUser);
			}else{
				logger.info("试用账号注册失败！");
				ServletUtils.outPrintFail(request, response, "试用账号注册失败！");
			}
		}catch(Exception e){
			logger.error("试用账号注册异常！", e);
			ServletUtils.outPrintFail(request, response, "试用账号注册异常！");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * 注册AG试用账号
	 * 方法描述: TODO</br> 
	 * @param webTestUser
	 * @return
	 * @throws Exception  
	 * WebTestUser
	 */
	public synchronized  WebTestUser registAgAccout(WebTestUser webTestUser) throws Exception {
		String webUserFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String userName = webTestUser.getUserName();
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME, userName);
		registerParams.put(AgConts.LOGINNAME, webUserFlag + userName);// 游戏帐号(3-10),主帐号4-10位
		String password = StringHelper.getCharAndNumr(10);// 随机字母与数字组合10位，小写
		registerParams.put(AgConts.PASSWORD, password);// 2.游戏密码少于20個字母,主帐号4-10位
		registerParams.put(AgConts.ACTYPE, AgConts.ACTYPE_0);// 0试玩帐号 1真玩帐号
		registerParams.put(AgConts.ODDTYPE, AgConts.ODDTYPE_A);// 4
		registerParams.put(Conts.USER_AGENT, SystemConstant.webDefaultAgentNo);
		
		AgResResult result =AGIfcUtil.register(registerParams);
		if (result != null) {
			if (StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())) {// 成功
				webTestUser.setFlatUserName(webUserFlag+userName);
				webTestUser.setFlatPassword(password);
			} else {
				String tmpUserName = getRandUserName();//
				registerParams.put(AgConts.LOGINNAME, webUserFlag+tmpUserName);
				result =AGIfcUtil.register(registerParams);
				if (result != null && StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())) {
					webTestUser.setFlatUserName(webUserFlag+userName);
					webTestUser.setFlatPassword(password);
				}
			}

		}
		return webTestUser;
	}
	
	
	/**
	 * 检查验证码是否正确
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean checkVerify(HttpServletRequest request, HttpServletResponse response){
		//验证码
		String verifycode = request.getParameter("testverifycode");
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
	
	
	@RequestMapping("/forwardGame")
	public ModelAndView forwardGame(HttpServletRequest request, HttpServletResponse response) {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		String	backToUrl = "";
		String gameType = request.getParameter("gameType");
		if (StringUtils.endsWithIgnoreCase(gameType, "osElectronicFun")){
			backToUrl = this.osElectronicFun(request);
		
		} else if (StringUtils.equalsIgnoreCase(gameType, "ptElectronic")) {
			backToUrl = this.ptElectronicFun(request);
			return new ModelAndView("redirect:"+backToUrl);
		}else{
			if(request.getSession().getAttribute(CommonConstant.TEST_USER_CONTEXT_KEY)!=null){
				WebTestUser testUser = (WebTestUser)request.getSession().getAttribute(CommonConstant.TEST_USER_CONTEXT_KEY);
				backToUrl = this.ag(testUser);
			}
		}
		
		logger.info(backToUrl);

		return new ModelAndView("web/frameUrl").addObject("backToUrl", backToUrl);
	}
	
	/**
	 * OS 方法描述: TODO</br>
	 * 
	 * @param request
	 * @return String
	 */
	private String osElectronicFun(HttpServletRequest request) {
		try {
			String gameCode = request.getParameter("gameName");
			String registPath = "/register";
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+registPath;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(OsConts.PLAYMODEL, OsConts.MODEL_FUN);// 试玩模式
			params.put(OsConts.GAMECODE, gameCode);// 游戏code
			params.put(OsConts.WEBSITE, java.net.URLEncoder.encode(basePath, "utf-8"));//webSite
			OsResResult result = OSIfcUtil.login(params);
			if (result != null) {
				return result.getUrl();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * PT 方法描述: TODO</br>
	 * 
	 * @param request
	 * @return String
	 */
	private String ptElectronicFun(HttpServletRequest request) {
		try {
			String gameCode = request.getParameter("gameCode");
			String loginUrl = CommonConstant.resCommMap.get(CommonConstant.PT_LOGINAUT_URL)+"demoLogin?_p="+gameCode;
			return  loginUrl;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * AG 方法描述: TODO</br>
	 * 
	 * @param request
	 * @return String
	 */
	private String ag(WebTestUser testUser) {
		try {

			Map<String, String> params = new HashMap<String, String>();
			params.put(AgConts.LOGINNAME,testUser.getFlatUserName());// 游戏帐号
			params.put(AgConts.PASSWORD, testUser.getFlatPassword());// 密码
			params.put(AgConts.GAMETYPE, "ag");
			params.put(AgConts.ACTYPE,AgConts.ACTYPE_0);
			params.put(AgConts.DM, basePath);
			params.put(AgConts.ODDTYPE, CommonConstant.resCommMap.get(CommonConstant.AG_ODD_TYPE));//
			params.put(AgConts.LANG, AgConts.LANG_ZH_CN);//
			params.put(AgConts.SID, ComUtil.getAGBillno());//
			AgResResult result = AGIfcUtil.login(params);
			if (result != null) {
				return result.getInfo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * 获取试用账号
	 * 方法描述: TODO</br> 
	 * @return  
	 * String
	 */
	public synchronized String getRandUserName(){
//		MemCachedUtil.delete(CommonConstant.MENCHED_TEST_KEY);
//		String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		StringBuffer buff = new StringBuffer("");
		Object testKey = MemCachedUtil.get(CommonConstant.MENCHED_TEST_KEY);
		int userIndex;
		if(testKey==null){
			userIndex = this.webTestUserService.getMaxUserIndex();
			//userIndex += 100000;
		}else{
			userIndex = Integer.valueOf(testKey.toString().substring(2));
		}
		
		userIndex = userIndex+1;
//		buff.append(userFlag);
		buff.append(CommonConstant.TEST_PREFIX_KEY);
		buff.append(userIndex);
		
		MemCachedUtil.set(CommonConstant.MENCHED_TEST_KEY,buff.toString());
		
		return buff.toString();
	}
	
	@RequestMapping("/ptProgressiveJackPot")
	public ModelAndView ptProgressiveJackPot(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		String gameCode = request.getParameter("gameCode");
		
		return new ModelAndView("web/inc/ptJackPot").addObject("id", id).addObject("gameCode",gameCode);
	}

}
