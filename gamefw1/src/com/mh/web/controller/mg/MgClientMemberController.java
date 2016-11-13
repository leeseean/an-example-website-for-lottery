/**   
 * 文件名称: WebTestController.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-8-26 下午1:29:25<br/>
 */
package com.mh.web.controller.mg;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.IpUtil;
import com.mh.commons.utils.OrderNoUtils;
import com.mh.commons.utils.ServletUtils;
import com.mh.dao.WebKjPayDao;
import com.mh.dao.WebUserFlatDao;
import com.mh.entity.TUserPwdLog;
import com.mh.entity.TWebThirdPay;
import com.mh.entity.WebAgent;
import com.mh.entity.WebBank;
import com.mh.entity.WebBankHuikuan;
import com.mh.entity.WebConfig;
import com.mh.entity.WebEdu;
import com.mh.entity.WebGongGao;
import com.mh.entity.WebKjPay;
import com.mh.entity.WebMessage;
import com.mh.entity.WebPromos;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.entity.WebUserType;
import com.mh.entity.WebUserWithdraw;
import com.mh.ifc.NMGIfcUtil;
import com.mh.ifc.http.NMGConts;
import com.mh.ifc.http.NMgResResult;
import com.mh.ifc.util.ComUtil;
import com.mh.service.WebAgentService;
import com.mh.service.WebBankHuikuanService;
import com.mh.service.WebConfigService;
import com.mh.service.WebEduService;
import com.mh.service.WebKjPayService;
import com.mh.service.WebMessageService;
import com.mh.service.WebUserFlatService;
import com.mh.service.WebUserService;
import com.mh.service.WebUserTypeService;
import com.mh.service.WebUserWithdrawService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
import com.mh.web.servlet.MySessionContext;
import com.mh.web.servlet.MyTokenSessionContext;
@SuppressWarnings("all")
@Controller
@RequestMapping("/mg/client")
public class MgClientMemberController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(MgClientMemberController.class);
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WebUserFlatService webUserFlatService;
	
	@Autowired
	private WebUserFlatDao webUserFlatDao;
	
	@Autowired
	private WebUserTypeService webUserTypeService;
	
	@Autowired
	private WebMessageService webMessageService;
	
	@Autowired
	private WebEduService webEduService;
	
	@Autowired
	private WebConfigService webConfigService;
	
	@Autowired
	private WebKjPayService webPayPicService;
	
	@Autowired
	private WebBankHuikuanService webBankHuikuanService;
	
	@Autowired
	private WebUserWithdrawService webUserWithdrawService;
	
	@Autowired
	private WebAgentService webAgentService;
	
	@Autowired
	private WebKjPayDao webKjPayDao;

	/**
	 * 个人中心入口 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param Args1
	 * @param Args2
	 * @return ModelAndView
	 */
	@RequestMapping("/account")
	public ModelAndView main(HttpServletRequest request,
			HttpServletResponse response) {
		String type = request.getParameter("type");
		String _token = request.getParameter("token");
		if(StringUtils.isEmpty(_token)){
			//拦截
			logger.info("token为空");
			return new ModelAndView("redirect:login");
		}
		Map<String,String> params = new HashMap<String, String>();
		params.put("token", _token);
		NMgResResult result = NMGIfcUtil.getUserToken(params);
		logger.info("接口返回用户名  " + result.getUserName());
		String _mgName = result.getUserName();
		WebUserFlat userFlat = this.webUserFlatDao.selectUserFlatByMgUserName(_mgName);
		String userName = userFlat.getUserName();
		logger.info("用户名："+ userName);
		request.getSession().setAttribute("userName", userName);//将用户名存到session中
		MyTokenSessionContext.sessionUser.put(userName, userName);
		
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		String backUrl = "";
		if(StringUtils.isEmpty(type)){
			type="1";
		}
		
		if("1".equals(type)){
			backUrl = "mg/client/goUserInfo";
		}
		int msgTotal = this.webMessageService.getMessageNum(userName, WebConstants.T_WEB_MESSAGE_TYPE_1+","+WebConstants.T_WEB_MESSAGE_TYPE_2, 
				WebConstants.T_WEB_MESSAGE_STATUS_1);
		
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		 WebAgent webAgent = this.webAgentService.findWebAgent(userName);
		 List<WebPromos> lbList=WebSiteManagerConstants.LbWEBPROMOS_LIST;
		 
		 //判断是否拥有启用的微信二维码  如果没有 ,关闭微信支付入口
		int payCount = this.webKjPayDao.getPayCount(1);
		
		WebUserType userType = webUserTypeService.getUserTypeById(webUser.getUserType());
		return new ModelAndView("mg/account_main")
			.addObject("webUser",webUser)
			.addObject("webAgent", webAgent)
			.addObject("type", type)
			.addObject("msgTotal", msgTotal)
			.addObject("gongGaoList", dataList)
			.addObject("payCount", payCount)
			.addObject("userType", userType)
			.addObject("backUrl", backUrl).addObject("lbPromoList",lbList);
	}
	
	
	@RequestMapping("/install")
	public ModelAndView forwardGame1(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("mg/install");
	}

	@RequestMapping("/contact")
	public ModelAndView forwardGame3(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("mg/contact");
	}

	@RequestMapping("/bank")
	public ModelAndView bank(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String _token = request.getParameter("token");
		if(StringUtils.isEmpty(_token)){
			//拦截
			logger.info("token为空");
			return new ModelAndView("redirect:login");
		}
		Map<String,String> params = new HashMap<String, String>();
		params.put("token", _token);
		NMgResResult result = NMGIfcUtil.getUserToken(params);
		logger.info("接口返回用户名  " + result.getUserName());
		String _mgName = result.getUserName();
		WebUserFlat userFlat = this.webUserFlatDao.selectUserFlatByMgUserName(_mgName);
		String userName = userFlat.getUserName();
		logger.info("用户名："+ userName);
		request.getSession().setAttribute("userName", userName);//将用户名存到session中
		MyTokenSessionContext.sessionUser.put(userName, userName);
		
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		String backUrl = "";
		if(StringUtils.isEmpty(type)){
			type="1";
		}
		
		if("1".equals(type)){
			backUrl = "mg/client/goPayBank";
		}else if("2".equals(type)){
			backUrl = "mg/client/goWithdraw";
		}else if("3".equals(type)){
			backUrl = "mg/client/goEdu";
		}
		int msgTotal = this.webMessageService.getMessageNum(userName, WebConstants.T_WEB_MESSAGE_TYPE_1+","+WebConstants.T_WEB_MESSAGE_TYPE_2, 
				WebConstants.T_WEB_MESSAGE_STATUS_1);
		
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		 WebAgent webAgent = this.webAgentService.findWebAgent(userName);
		 List<WebPromos> lbList=WebSiteManagerConstants.LbWEBPROMOS_LIST;
		 
		 //判断是否拥有启用的微信二维码  如果没有 ,关闭微信支付入口
		int payCount = this.webKjPayDao.getPayCount(1);
		
		WebUserType userType = webUserTypeService.getUserTypeById(webUser.getUserType());
		return new ModelAndView("mg/bank_main")
			.addObject("webUser",webUser)
			.addObject("webAgent", webAgent)
			.addObject("type", type)
			.addObject("msgTotal", msgTotal)
			.addObject("gongGaoList", dataList)
			.addObject("payCount", payCount)
			.addObject("userType", userType)
			.addObject("backUrl", backUrl).addObject("lbPromoList",lbList);
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("mg/login");
	}
	
	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!this.checkVerify(request, response)){
			return ;
		}
		
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		
		if(StringUtils.isBlank(loginName)){
			ServletUtils.outPrintWithToken(request, response, "请输入用户名");
			logger.info("用户名为空，返回登入界面");
			return;
		}
		if(StringUtils.isBlank(password)){
			ServletUtils.outPrintWithToken(request, response, "请输入密码");
			logger.info("密码为空，返回登入界面");
			return;
		}
		try {
			password = DesUtil.encrypt(password, CommonConstant.resCommMap.get("app.client.des.key"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		int userPsTimes = NumberUtils.toInt(WebSiteManagerConstants.SYSPARAMMAP.get("user_ps_times").toString(), 5);
		
		logger.info("根据用户名={} 密码={} 获取用户对象",loginName,password);
//		WebUser webUser = this.webUserService.findWebrUser(loginName, password);
		WebUser webUser = this.webUserService.findWebrUseByUserName(loginName);
		List<String> fieldList = new ArrayList<String>();
		List<Object> valList = new ArrayList<Object>();
		Date currDate = new Date();
		if(webUser==null){
			ServletUtils.outPrintWithToken(request, response, "帐号不存在!");
			return;
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
				
//				webUser.setUserPsTimes(userHasPsTimes);
//				webUser.setModifyTime(new Date());
//				this.webUserService.updateWebUser(webUser);
				ServletUtils.outPrintWithToken(request, response,warn);
				logger.info(warn);
				return;
			}
		}
				
		if(!CommonConstant.STATUS_USABLE.equals(String.valueOf(webUser.getUserStatus()))){
			logger.info("用户 ID={} 的状态不可用",webUser.getUserStatus());
			ServletUtils.outPrintWithToken(request, response, "帐号已被冻结。请联系我们24小时在线客服！");
			return;
		}
		
		WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(loginName);
		if(StringUtils.isEmpty(webUserFlat.getMgUserName()) || StringUtils.isEmpty(webUserFlat.getMgId())){
			webUserFlat =  NMGIfcUtil.registNMgAccout(webUserFlat);
			 if(StringUtils.isNotEmpty(webUserFlat.getMgUserName())&&StringUtils.isNotEmpty(webUserFlat.getMgId())){
				 this.webUserFlatDao.update(webUserFlat);
			 }else{
				String errMsg="创建MG用户失败，错误代码："+webUserFlat.getReturnCode();
				logger.info(errMsg);
				ServletUtils.outPrintWithToken(request, response, errMsg);
				return;
			 }
		}
		Map<String,String> params = new HashMap<String,String>();
		params.put(NMGConts.USERNAME, webUserFlat.getMgUserName());// 游戏帐号
		params.put(NMGConts.PASSWORD, webUserFlat.getMgPassword());// 游戏帐号
		params.put(NMGConts.IP, IpUtil.getClientIpAddr(request));//IP
		NMgResResult result = NMGIfcUtil.loginlive(params);
		//请求API 获取token
		if(null==result || StringUtils.isEmpty(result.getUserToken())){
			logger.info("用户{} 的MGtoken为空",webUser.getUserName());
			ServletUtils.outPrintWithToken(request, response, "用户MGtoken为空");
			return;
		}
		String token = result.getUserToken();
		//MyTokenSessionContext.addMyClientToken(loginName, token);//添加token记录
		MyTokenSessionContext.sessionUser.put(loginName, loginName);
		try{
			if(MyTokenSessionContext.hashUserSession(loginName)){
				HttpSession session = MyTokenSessionContext.getMySession(loginName);
				if(MySessionContext.getSession(session.getId())!=null){
					HttpSession oldSession = MySessionContext.getSession(session.getId());
					//移除session
					oldSession.removeAttribute(CommonConstant.USER_CONTEXT_KEY);
					oldSession.removeAttribute(CommonConstant.VERITY_CODE_KEY);
					MySessionContext.delSession(oldSession);
				}
				MyTokenSessionContext.delMySession(loginName);
			}
		}catch (Exception e) {
		}
		 
		logger.info("用户登入成功, token="+token);
		ServletUtils.outPrintSuccess(request, response, "登录成功！", token);
	}
	
	
	@RequestMapping("/doUserLogin")
	public void doUserLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!this.checkVerify(request, response)){
			return ;
		}
		
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		
		if(StringUtils.isBlank(loginName)){
			ServletUtils.outPrintWithToken(request, response, "请输入用户名");
			logger.info("用户名为空，返回登入界面");
			return;
		}
		if(StringUtils.isBlank(password)){
			ServletUtils.outPrintWithToken(request, response, "请输入密码");
			logger.info("密码为空，返回登入界面");
			return;
		}
		try {
			password = DesUtil.encrypt(password, CommonConstant.resCommMap.get("app.client.des.key"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		int userPsTimes = NumberUtils.toInt(WebSiteManagerConstants.SYSPARAMMAP.get("user_ps_times").toString(), 5);
		
		logger.info("根据用户名={} 密码={} 获取用户对象",loginName,password);
//		WebUser webUser = this.webUserService.findWebrUser(loginName, password);
		WebUser webUser = this.webUserService.findWebrUseByUserName(loginName);
		List<String> fieldList = new ArrayList<String>();
		List<Object> valList = new ArrayList<Object>();
		Date currDate = new Date();
		if(webUser==null){
			ServletUtils.outPrintWithToken(request, response, "帐号不存在!");
			return;
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
				
//				webUser.setUserPsTimes(userHasPsTimes);
//				webUser.setModifyTime(new Date());
//				this.webUserService.updateWebUser(webUser);
				ServletUtils.outPrintWithToken(request, response,warn);
				logger.info(warn);
				return;
			}
		}
				
		if(!CommonConstant.STATUS_USABLE.equals(String.valueOf(webUser.getUserStatus()))){
			logger.info("用户 ID={} 的状态不可用",webUser.getUserStatus());
			ServletUtils.outPrintWithToken(request, response, "帐号已被冻结。请联系我们24小时在线客服！");
			return;
		}
		
		WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(loginName);
		if(StringUtils.isEmpty(webUserFlat.getMgUserName()) || StringUtils.isEmpty(webUserFlat.getMgId())){
			webUserFlat =  NMGIfcUtil.registNMgAccout(webUserFlat);
			 if(StringUtils.isNotEmpty(webUserFlat.getMgUserName())&&StringUtils.isNotEmpty(webUserFlat.getMgId())){
				 this.webUserFlatDao.update(webUserFlat);
			 }else{
				String errMsg="创建MG用户失败，错误代码："+webUserFlat.getReturnCode();
				logger.info(errMsg);
				ServletUtils.outPrintWithToken(request, response, errMsg);
				return;
			 }
		}
		/*Map<String,String> params = new HashMap<String,String>();
		params.put(NMGConts.USERNAME, webUserFlat.getMgUserName());// 游戏帐号
		params.put(NMGConts.PASSWORD, webUserFlat.getMgPassword());// 游戏帐号
		params.put(NMGConts.IP, IpUtil.getClientIpAddr(request));//IP
		NMgResResult result = NMGIfcUtil.loginlive(params);
		//请求API 获取token
		if(null==result || StringUtils.isEmpty(result.getUserToken())){
			logger.info("用户{} 的MGtoken为空",webUser.getUserName());
			ServletUtils.outPrintWithToken(request, response, "用户MGtoken为空");
			return;
		}
		String token = result.getUserToken();*/
		//MyTokenSessionContext.addMyClientToken(loginName, token);//添加token记录
		
		logger.info("用户名："+ loginName);
		request.getSession().setAttribute("userName", loginName);//将用户名存到session中
		MyTokenSessionContext.sessionUser.put(loginName, loginName);
		try{
			if(MyTokenSessionContext.hashUserSession(loginName)){
				HttpSession session = MyTokenSessionContext.getMySession(loginName);
				if(MySessionContext.getSession(session.getId())!=null){
					HttpSession oldSession = MySessionContext.getSession(session.getId());
					//移除session
					oldSession.removeAttribute(CommonConstant.USER_CONTEXT_KEY);
					oldSession.removeAttribute(CommonConstant.VERITY_CODE_KEY);
					MySessionContext.delSession(oldSession);
				}
				MyTokenSessionContext.delMySession(loginName);
			}
		}catch (Exception e) {
		}
		 
		logger.info("用户登入成功");
		ServletUtils.outPrintSuccess(request, response, "登录成功！");
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
		
		Object code = request.getSession().getAttribute(CommonConstant.TEST_VERITY_CODE_KEY);;
		
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
	
	@RequestMapping("/login2")
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("mg/login2");
	}
	
	@RequestMapping("/loginSuccess")
	public ModelAndView loginSuccess(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("mg/login_success");
	}
	
	@RequestMapping("/loginFailure")
	public ModelAndView loginFailure(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("mg/login_failure");
	}
	
	@RequestMapping("/loginhelp")
	public ModelAndView forwardGame7(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("mg/loginhelp");
	}

	@RequestMapping("/active")
	public ModelAndView forwardGame8(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("mg/active");
	}
	
	
	/**
	 * 跳转至用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@RequestMapping("/goUserInfo")
	public ModelAndView goUserInfo(HttpServletRequest request, HttpServletResponse response)
	{
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		String qq = webUser.getUserQq();
		String mail = webUser.getUserMail();
		String mobile = webUser.getUserMobile();
		if(StringUtils.isNotBlank(mail))
		{
			String inner = "";
			String[] mails = mail.split("@");
			int length = mails[0].length();
			switch (length) 
			{
				case 2:
				case 3:
					inner = "*" + mails[0].substring(mails[0].length() -1 , mails[0].length());
					break;
				case 4:
				case 5:
				case 6:
					inner = "**" + mails[0].substring(mails[0].length() -2 , mails[0].length());
					break;
				case 7:
					inner = "****" + mails[0].substring(mails[0].length() -3 , mails[0].length());
					break;
				default:
					inner = "*****" + mails[0].substring(mails[0].length() -4 , mails[0].length());
					break;
			}
			webUser.setUserMail(inner + mails[1]);
		}
		if(StringUtils.isNotBlank(qq))
		{
			webUser.setUserQq("****" + qq.substring(qq.length() - 4, qq.length()));
		}
		if(StringUtils.isNotBlank(mobile))
		{
			webUser.setUserMobile("********" + mobile.substring(mobile.length() - 3, mobile.length()));
		}
		WebUserType userType = webUserTypeService.getUserTypeById(webUser.getUserType());
		ModelAndView model = new ModelAndView("mg/account/userinfo");
		return model.addObject("webUser", webUser).addObject("userType", userType);
	}
	
	
	/**
	 * 跳转到信息中心的站內消息 方法描述</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/listForUser")
	public ModelAndView getMessageNum(HttpServletRequest request, HttpServletResponse response) {
		String userName = (String)request.getSession().getAttribute("userName");
		WebMessage webMessage = new WebMessage();
		webMessage.setUserName(userName);
		Page page = this.newPage(request);
		this.webMessageService.getMessageUserName(page, webMessage);
		page.setColspanNum(6);
		return new ModelAndView("mg/message/listForUser")
			.addObject("webMessage", webMessage)
			.addObject(CommonConstant.PAGE_KEY, page);

	}
	
	
	/**
	 * 额度转换
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param fromFlatName
	 * @param toFlatName
	 * @param wPoints  
	 * void
	 */
	@RequestMapping("/doEdu")
	public synchronized void doEdu(HttpServletRequest request,HttpServletResponse response,@RequestParam("fromFlatName") String fromFlatName,
			@RequestParam("toFlatName") String toFlatName,@RequestParam("wPoints") Integer wPoints) {
		try {
			String optType = "";
			String payType = "";// 转换类型
			String flatName ="";
			String userName = (String)request.getSession().getAttribute("userName");
			//WebUser webUser = this.webUserService.findWebUserByUserNameForUpdate(uc.getUserName());
			WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
			if (StringUtils.equals(fromFlatName, WebConstants.FLAT_NAME_ACCOUNT)) {// 转入
				if (webUser!= null && wPoints > webUser.getUserMoney()) {
					logger.info("总帐户余额不足!");
					ServletUtils.outPrintFail(request, response, "总帐户余额不足!");
					//this.webUserService.updateWebUser(webUser);
					return;
				}
				optType = WebConstants.EDU_TYPE_2;
				payType = WebConstants.EDU_TYPE_2;
				flatName = toFlatName;
			} else {
				optType = WebConstants.EDU_TYPE_1;// 转出
				payType = WebConstants.EDU_TYPE_1;
				flatName = fromFlatName;
			}
			WebEdu webEdu = getEduForward(payType, flatName);
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(userName);
			Map<String,Object> reMap = this.webEduService.updateEdu(webUserFlat, flatName, optType, wPoints,webEdu);
			boolean reFlag = (Boolean) reMap.get("reFlag");
			String reMsg = reMap.get("tsMsg").toString();
 
			if(reFlag){
				ServletUtils.outPrintSuccess(request, response,reMsg);
			}else{
				ServletUtils.outPrintFail(request, response,reMsg);
			}
		} catch (RuntimeException e) {
			logger.error("额度转换异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "额度转换异常");
		}
	}
	
	
	private WebEdu getEduForward(String payType, String flatName) {
		WebEdu tmp = new WebEdu();
		if (StringUtils.equalsIgnoreCase(WebConstants.EDU_TYPE_2, payType)) {
			if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_BBIN)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_BBIN_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_BBIN_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_MG)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_MG_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_MG_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_AG)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_AG_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_AG_2);
//			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_JBB)) {
//				tmp.setEduForward(WebConstants.EDU_FORWARD_188_2);
//				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_188_2);
//			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_KG)) {
//				tmp.setEduForward(WebConstants.EDU_FORWARD_KG_2);
//				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_KG_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_DS)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_DS_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_DS_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_HG)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_HG_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_HG_2);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_NT)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_NT_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_NT_2);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_PT)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_PT_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_PT_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_DJ)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_DJ_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_DJ_2);
			} else if (StringUtils.endsWithIgnoreCase(flatName, WebConstants.FLAT_NAME_AB)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_AB_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_AB_2);
			} else if (StringUtils.endsWithIgnoreCase(flatName, WebConstants.FLAT_NAME_OG)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_OG_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_OG_2);
			}

		} else if (StringUtils.equalsIgnoreCase(WebConstants.EDU_TYPE_1, payType)) {
			if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_BBIN)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_BBIN_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_BBIN_1);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_MG)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_MG_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_MG_1);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_AG)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_AG_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_AG_1);
//			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_JBB)) {
//				tmp.setEduForward(WebConstants.EDU_FORWARD_188_1);
//				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_188_1);
//			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_KG)) {
//				tmp.setEduForward(WebConstants.EDU_FORWARD_KG_1);
//				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_KG_1);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_DS)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_DS_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_DS_1);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_HG)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_HG_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_HG_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_NT)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_NT_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_NT_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_PT)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_PT_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_PT_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_DJ)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_DJ_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_DJ_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_AB)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_AB_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_AB_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_OG)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_OG_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_OG_1);
			}
		}
		return tmp;
	}
	
	
	/**
	 * 跳转到申请提款页面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goWithdraw")
	public ModelAndView goWithdraw(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		String url = "mg/account/withdraw";
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser user = this.webUserService.findWebrUseByUserName(userName);
		//判断出款银行卡是否设置
		if(null != user && StringUtils.isEmpty(user.getUserBankCard())){
			url = "mg/account/bank_info";
		}
		
		view.addObject("webUser",user);
		view.setViewName(url);
		return view;
	}
	
	
	/**
	 * 提款
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param code  
	 * void
	 */
	@RequestMapping("/saveWithdraw")
	public synchronized void saveWithdraw(HttpServletRequest request,HttpServletResponse response,@RequestParam("money") Double money,@RequestParam("userWithdrawPassword") String userWithdrawPassword){
		WebUser user = null;
		try {
			String userName = (String)request.getSession().getAttribute("userName");
			user = this.webUserService.findWebrUseByUserName(userName);
			double userMoney = 0;
			if(user!=null && user.getUserMoney()!=null){
				userMoney =  user.getUserMoney().doubleValue();
			}

			/** 资金密码判断 **/
			String cryptPassword = DesUtil.encrypt(userWithdrawPassword, CommonConstant.resCommMap.get("app.money.des.key"));
			if (!StringUtils.equalsIgnoreCase(cryptPassword, user.getUserWithdrawPassword())) {
				ServletUtils.outPrintFail(request, response, "资金密码错误!");
				return;
			}

			/** 判断取款金额是否超过主帐号余额 **/
			if (money.doubleValue() > userMoney) {
				ServletUtils.outPrintFail(request, response, "提款金额不能大于总帐户余额！");
				return;
			}
			
			int totalTimes = 1;
			int dayTimes = 1;
			
			WebUserWithdraw withdraw = new WebUserWithdraw();
			withdraw.setUserName(user.getUserName());
			withdraw.setBeginDateStr(DateUtil.todayBegin());// 美东时间(今天)

			Map<String, Integer> map = this.webUserWithdrawService.countWithdrawSuccessTimes(withdraw);
			
			if (null!=map.get("totalTimes")&&map.get("totalTimes") > 0) {
				totalTimes = map.get("totalTimes") + 1;
			}
			if (null!=map.get("dayTimes")&&map.get("dayTimes") > 0) {
				dayTimes = map.get("dayTimes") + 1;
			}

			/** 保存取款申請記錄及扣减主帐号金额 **/
			withdraw.setGmt4Time(DateUtil.getGMT_4_Date());
			withdraw.setTotalTimes(totalTimes);
			withdraw.setDayTimes(dayTimes);

			withdraw.setUserRealName(user.getUserRealName());
			withdraw.setUserBankInfo(user.getUserBankType() + "|" + user.getUserBankCard() + "|" + user.getUserBankAddress());
			withdraw.setUserWithdrawMoney(money);
			withdraw.setStatus(WebConstants.T_WEB_USER_WITHDRAW_STATUS_0);
			withdraw.setCheckStatus(WebConstants.T_WEB_USER_WITHDRAW_CHECKED_STATUS_0);

			Date now = new Date();
			withdraw.setModifyTime(now);
			withdraw.setCreateTime(now);
			withdraw.setUserOrder(OrderNoUtils.getOrderNo(""));
			withdraw.setWithdrawType(WebConstants.T_WEB_MA_TYPE_11);// 会员提款
			withdraw.setGmt4Time(DateUtil.getGMT_4_Date());

			boolean reFlag = this.webUserWithdrawService.saveWebUserWithdraw(withdraw);
			if(reFlag){
				/** 触发通知 **/
				MemCachedUtil.setWithdrawNotice(user.getUserFlag());
				String optInfo = "提款申请成功！财务部门将在审核过后，将您的提款金额存入您的提款账号中！您也可以到会员中心【提款记录】里查询您的提款状态！";
				ServletUtils.outPrintSuccess(request, response, optInfo);
			}else{
				ServletUtils.outPrintFail(request, response, "您好,提款申请功能维护中，请稍候再试或联系我们客服处理！");
			}
		}catch (Exception e) {
			try {
				MemCachedUtil.setWithdrawNotice(user.getUserFlag());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			logger.error("提款异常",e);
			ServletUtils.outPrintFail(request, response, "您好,提款申请功能维护中，请稍候再试或联系我们客服处理！");
		}
	}
	
	/**
	 * 跳转至微信转账页面
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@RequestMapping("/goWxPay")
	public ModelAndView goWxPay(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		
		ModelAndView model = new ModelAndView("mg/account/wx_pay");
		//图片类型
		Integer paytype = Integer.parseInt(request.getParameter("paytype"));
		//获取数据
		
		WebKjPay webKjPay = this.webPayPicService.getKjPay(paytype,webUser.getUserType());
		
		List<WebConfig> configList = this.webConfigService.getWebConfigList();
		WebConfig webConfig = null;
		if(configList!=null &configList.size()>0)
		{
			webConfig = configList.get(0);
		}
		
		//当前日期
		String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		
		return model.addObject("webKjPay", webKjPay)
				.addObject("currDateStr",currDateStr)
				.addObject("webUser", webUser)
				.addObject("webConfig", webConfig);
	}
	
	/**
	 * 
	 * 微信支付</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param bankHk
	 * @throws SQLException
	 * @throws IOException  
	 * void
	 */
	@RequestMapping("/doKjPay")
	public void doKjPay(HttpServletRequest request, HttpServletResponse response,WebBankHuikuan bankHk) throws SQLException, IOException
	{
		try 
		{
			String userName = (String)request.getSession().getAttribute("userName");
			logger.info("用户名:"+userName);
			WebUser user = this.webUserService.findWebrUseByUserName(userName);
			
			Date currDate = DateUtil.currentDate();
			String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
			Map<String, Integer> map = this.webBankHuikuanService.getWebBankHuikuanTj(userName, currDateStr);
			int totalTimes = map.get("totalTimes")+1;
			int dayTimes = map.get("dayTimes")+1;
			
			bankHk.setHkType("快捷支付");
			bankHk.setHkCompanyBank("快捷支付");
			bankHk.setGmt4Time(DateUtil.getGMT_4_Date());// 美东当前时间
			bankHk.setTotalTimes(totalTimes);// 总次数
			bankHk.setDayTimes(dayTimes);// 日次数
			bankHk.setHkOrder(ComUtil.getOnliePayOrder());
			bankHk.setHkStatus(WebConstants.T_WEB_BANK_HUIKUAN_STATUS_0);// 订单状态为：未审核
			bankHk.setHkCheckStatus(WebConstants.T_WEB_BANK_HUIKUAN_CHECKED_STATUS_0);// 通过状态：初始状态
			bankHk.setCreateTime(currDate);
			bankHk.setModifyTime(currDate);
			bankHk.setUserName(userName);
			bankHk.setHkIp(IPSeeker.getIpAddress(request));
			bankHk.setHkModel(WebConstants.T_WEB_BANK_HUIKUAN_MODEL_1);// 在线支付
			Date now = new Date();
			try 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				bankHk.setHkTime(sdf.parse(bankHk.getTime() + " " + bankHk.getTimeHour() + ":" + bankHk.getTimeMinute() + ":00"));
			} 
			catch (Exception e) 
			{
				bankHk.setHkTime(now);
			}
			
			this.webBankHuikuanService.saveWebBankHuikuan(bankHk);
			
			/** 触发通知 **/
			MemCachedUtil.setDepositNotice(user.getUserFlag());
			
			ServletUtils.outPrintSuccess(request, response,"您的汇款订单已提交，请等待我们的审核，谢谢！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("汇款订单提交异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "您的汇款订单提交出了点状况，请稍候再提交或者联系我们客服帮助！");
		}
	}
	
	
	/**
	 * 
	 * 公司入款</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goPayBank")
	public ModelAndView goPayBank(HttpServletRequest request,HttpServletResponse response) {
		String userName = (String)request.getSession().getAttribute("userName");
		logger.info("用户名:"+userName);
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		
		List<WebBank> bankList = this.webBankHuikuanService.getWebBankList(webUser.getUserType());
		List<TWebThirdPay> thirdPayList = this.webBankHuikuanService.getWebThirdPayList(webUser.getUserType());
		TWebThirdPay thirdPay = null;
		if(thirdPayList!= null && thirdPayList.size()>0){
			thirdPay =thirdPayList.get(0); 
		}
		
		List<WebConfig> configList = this.webConfigService.getWebConfigList();
		WebConfig webConfig = null;
		if(configList!=null &configList.size()>0){
			webConfig = configList.get(0);
		}
		
		String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		
		return new ModelAndView("mg/account/pay_bank")
			.addObject("currDateStr", currDateStr)
			.addObject("webUser", webUser)
			.addObject("webConfig", webConfig)
			.addObject("bankList", bankList)
			.addObject("thirdPay", thirdPay);
	}
	
	/**
	 * 公司入款
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/doBankPay")
	public void doBankPay(HttpServletRequest request,HttpServletResponse response,WebBankHuikuan bankHk) {
		try {
			/**最低入款判断**/
			List<WebConfig> configList = this.webConfigService.getWebConfigList();
			WebConfig webConfig = null;
			if(configList!=null &configList.size()>0){
				webConfig = configList.get(0);
				double hkMoney = bankHk.getHkMoney();
				double minPay = webConfig.getCompanyMinPay();
				if(hkMoney<minPay){
					ServletUtils.outPrintFail(request, response, "公司入款最低"+minPay+"元！");
				}
			}
			
			if(StringUtils.isEmpty(bankHk.getHkCompanyBank())|| StringUtils.isEmpty(bankHk.getHkType()) 
					|| StringUtils.isEmpty(bankHk.getHkUserName())|| StringUtils.isEmpty(bankHk.getHkUserName().trim())){
				ServletUtils.outPrintFail(request, response, "请完善您入款的信息！");
			}
			
			String userName = (String)request.getSession().getAttribute("userName");
			logger.info("用户名:"+userName);
			WebUser user = this.webUserService.findWebrUseByUserName(userName);
			Date currDate = DateUtil.currentDate();
			String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
			Map<String, Integer> map = this.webBankHuikuanService.getWebBankHuikuanTj(userName, currDateStr);
			int totalTimes = map.get("totalTimes")+1;
			int dayTimes = map.get("dayTimes")+1;
			bankHk.setGmt4Time(DateUtil.getGMT_4_Date());// 美东当前时间
			bankHk.setTotalTimes(totalTimes);// 总次数
			bankHk.setDayTimes(dayTimes);// 日次数
			bankHk.setHkOrder(ComUtil.getOnliePayOrder());
			bankHk.setHkStatus(WebConstants.T_WEB_BANK_HUIKUAN_STATUS_0);// 订单状态为：未审核
			bankHk.setHkCheckStatus(WebConstants.T_WEB_BANK_HUIKUAN_CHECKED_STATUS_0);// 通过状态：初始状态
			bankHk.setCreateTime(currDate);
			bankHk.setModifyTime(currDate);
			bankHk.setUserName(userName);
			bankHk.setHkIp(IPSeeker.getIpAddress(request));
			bankHk.setHkModel(WebConstants.T_WEB_BANK_HUIKUAN_MODEL_1);// 银行卡划款
			Date now = new Date();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				bankHk.setHkTime(sdf.parse(bankHk.getTime() + " " + bankHk.getTimeHour() + ":" + bankHk.getTimeMinute() + ":00"));
			} catch (Exception e) {
				bankHk.setHkTime(now);
			}

			this.webBankHuikuanService.saveWebBankHuikuan(bankHk);
//			bankHk.setPayDama(NumberUtils.toInt(getParameter("key"), 0));// 打码再次入款标识

			/** 触发通知 **/
			MemCachedUtil.setDepositNotice(user.getUserFlag());
			
			ServletUtils.outPrintSuccess(request, response,"您的汇款订单已提交，请等待我们的审核，谢谢！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("汇款订单提交异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "您的汇款订单提交出了点状况，请稍候再提交或者联系我们客服帮助！");
		}
	}
	
	/**
	 * 账户信息 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/accountInfo")
	public ModelAndView accountInfo(HttpServletRequest request,
			HttpServletResponse response) {

		String userName = (String)request.getSession().getAttribute("userName");
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);

		return new ModelAndView("mg/account/account_info").addObject(
				"webUser", webUser);
	}

	
	/***
	 * 保存银行卡信息
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param webUser
	 * @throws Exception  
	 * void
	 */
	@RequestMapping("/saveBackInfo")
	public void saveBackInfo(HttpServletRequest request, HttpServletResponse response, WebUser webUser) {
		try {
			String userName = (String)request.getSession().getAttribute("userName");
			WebUser ruser = this.webUserService.findWebrUseByUserName(userName);
			if(ruser!=null && ruser.getUserBankCard()!=null && !"".equals(ruser.getUserBankCard())){
				ServletUtils.outPrintFail(request, response, "绑定失败，请联系客服!");
				return;
			}
			
			
			String cryptPassword = DesUtil.encrypt(webUser.getUserWithdrawPassword(), CommonConstant.resCommMap.get("app.money.des.key"));
			String optInfo = "";
			if (!StringUtils.equalsIgnoreCase(cryptPassword, ruser.getUserWithdrawPassword())) {
				ServletUtils.outPrintFail(request, response, "资金密码错误!");
				return;
			} else {
//				ruser.setUserBankType(webUser.getUserBankType());
//				ruser.setUserBankAddress(webUser.getUserBankAddress());
//				ruser.setUserBankCard(webUser.getUserBankCard());
//				
				
				List<String> fieldList = new ArrayList<String>();
				List<Object> valList = new ArrayList<Object>();
				fieldList.add("user_bank_type");
				fieldList.add("user_bank_address");
				fieldList.add("user_bank_card");
				valList.add(webUser.getUserBankType());
				valList.add(webUser.getUserBankAddress());
				valList.add(webUser.getUserBankCard());
				this.webUserService.updateWebUser(userName, fieldList, valList);
				
//				this.webUserService.updateWebUser(ruser);
				ServletUtils.outPrintSuccess(request, response, optInfo);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("绑定银行卡失败",e);
			ServletUtils.outPrintFail(request, response, "收款银行信息设置失败.请稍候再试或联系我们客服更新！");
		}
	}
	
	/**
	 * 二维码写入页面
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping("/getPayImg")
	public void getPayImg(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		//图片类型
		Integer paytype = Integer.parseInt(request.getParameter("paytype"));
		//获取数据
		WebKjPay webKjPay = this.webPayPicService.getKjPay(paytype,webUser.getUserType());
		response.setContentType("iamge/jpg");
		//缓存设置
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		ServletOutputStream sos = response.getOutputStream();
		//写入响应
		sos.write(webKjPay.getImg());
		sos.flush();
		sos.close();
	}
	
	
	/**
	 * 方法描述: 在线支付</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/payOnline")
	public ModelAndView payOnline(HttpServletRequest request, HttpServletResponse response) {
		
		return new ModelAndView("mg/account/pay_online");
	}
	
	/**
	 * 跳转到额度转换页面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goEdu")
	public ModelAndView goEdu(HttpServletRequest request,HttpServletResponse response) {
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser user = this.webUserService.findWebrUseByUserName(userName);
		
		return new ModelAndView("mg/account/edu")
			.addObject("user",user);
	}
	
	/**
	 * 进入密码页面 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/goPwdMain")
	public ModelAndView goPwdMain(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		return new ModelAndView("mg/account/pwd_main").addObject("webUser",
				webUser);
	}

	/**
	 * 跳转到资金密码修改页面 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/goWithdrawPwd")
	public ModelAndView goWithdrawPwd(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		return new ModelAndView("mg/account/withdraw_pwd").addObject(
				"webUser", webUser);
	}
	
	
	/**
	 * 跳转到登录密码修改页面 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/goLoginPwd")
	public ModelAndView goLoginPwd(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		return new ModelAndView("mg/account/login_pwd").addObject(
				"webUser", webUser);
	}

	/**
	 * 修改登录密码 方法描述: TODO</br>
	 * 
	 * @param loginName
	 * @param NewPassword
	 * @return WebUser
	 * @throws Exception
	 */
	@RequestMapping("/doLoginPwd")
	public void doLoginPwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String userName = (String)request.getSession().getAttribute("userName");
		
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		String cryptOldPassword = DesUtil.encrypt(oldPwd,CommonConstant.resCommMap.get("app.client.des.key"));
		try {
			if (!cryptOldPassword.equals(webUser.getUserPassword())) {
				ServletUtils.outPrintFail(request, response, "原登录密码错误.");
			} else {
				String cryptPassword = DesUtil.encrypt(newPwd,CommonConstant.resCommMap.get("app.client.des.key"));
				if (!cryptPassword.equals(cryptOldPassword)) {
					this.webUserService.updatePassword(cryptPassword,webUser.getUserName());
					this.insertModifyPwdLog(request, cryptOldPassword, cryptPassword, "登录密码修改");
					ServletUtils.outPrintSuccess(request, response, "密码修改成功.");
				} else {
					ServletUtils.outPrintFail(request, response,"密码修改失败.请稍候再试或联系我们客服.");
				}
			}
		} catch (RuntimeException e) {
			logger.error("登录密码修改异常: " + e.getMessage(), e);
			ServletUtils.outPrintFail(request, response,"登录密码修改异常.请稍候再试或联系我们客服.");
		}

	}

	/**
	 * 修改资金密码 方法描述: TODO</br>
	 * 
	 * @param response
	 * @param request
	 * @return WebUser
	 * @throws Exception
	 */
	@RequestMapping("/doWithdrawPwd")
	public void doWithdrawPwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		String cryptOldPassword = DesUtil.encrypt(oldPwd,
				CommonConstant.resCommMap.get("app.money.des.key"));
		try {
			if (!cryptOldPassword.equals(webUser.getUserWithdrawPassword())) {
				ServletUtils.outPrintFail(request, response, "原资金密码错误.");
			} else {
				String cryptPassword = DesUtil.encrypt(newPwd,CommonConstant.resCommMap.get("app.money.des.key"));
				if (!cryptPassword.equals(cryptOldPassword)) {
					this.webUserService.updateMoneyPassword(cryptPassword,webUser.getUserName());
					this.insertModifyPwdLog(request, cryptOldPassword, cryptPassword, "资金密码修改");
					ServletUtils.outPrintSuccess(request, response, "资金密码修改成功.");
				} else {
					ServletUtils.outPrintFail(request, response,"资金密码修改失败.请稍候再试或联系我们客服.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改资金密码异常",e);
			ServletUtils.outPrintFail(request, response,"资金密码修改失败.请稍候再试或联系我们客服.");
		}

	}
	
	/***
	 * 
	 * 方法描述: 在线客服</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/gotoOnlineContact")
	public ModelAndView gotoOnlineContact(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("mg/contact/panel-contact");
	}

	private void insertModifyPwdLog(HttpServletRequest request, String oldPwd, String newPwd, String remark){
		String userName = (String)request.getSession().getAttribute("userName");
		
		TUserPwdLog log = new TUserPwdLog();
		log.setUserName(userName);
		log.setOldPassword(oldPwd);
		log.setNewPassword(newPwd);
		log.setRemark(remark);
		log.setUpdateTime(new Date());
		log.setIp(IPSeeker.getIpAddress(request));
		this.webUserService.insertTuserPwdLog(log);
	}
	
	
	/**
	 * 查询平台余额
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/getBlanceMoney")
	public void getBlanceMoney(HttpServletRequest request,HttpServletResponse response,@RequestParam("flatName") String flatName) {
		try {
			String userName = (String)request.getSession().getAttribute("userName");
			logger.info("getBlanceMoney----------" + userName);
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(userName);
			double blance = 0;
			Map<String,String> params = new HashMap<String,String>();
			if (StringUtils.endsWithIgnoreCase(flatName, "mg")){
				logger.info("mg 查询金额");
				//params.put(NMGConts.USERNAME, webUserFlat.getMgUserName());// 游戏帐号
				//params.put(NMGConts.PASSWORD, webUserFlat.getMgPassword());// 游戏帐号
				params.put(NMGConts.USERID, webUserFlat.getMgId());
				NMgResResult result = NMGIfcUtil.balance(params);
				if (result != null && !StringUtils.isEmpty(result.getBlance())) {
					blance = Double.valueOf(result.getBlance());
				}
				logger.info(blance + "");
			} else if (StringUtils.equalsIgnoreCase(flatName, "account")) {
				WebUser user = this.webUserService.findWebrUseByUserName(userName);
				blance = user.getUserMoney().doubleValue();
			}
			Map<String,Double> dataMap = new HashMap<String,Double>();
			dataMap.put("flatMoney", blance);
			ServletUtils.outPrintSuccess(request, response,dataMap);
		} catch (RuntimeException e) {
			logger.error("查询用户金额异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "查询用户金额异常");
		}
	}
	
	
	/**
	 * 查询系统消息: 方法描述:TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/listForSys")
	public ModelAndView adminMessage(HttpServletRequest request, HttpServletResponse response) {
		String userName = (String)request.getSession().getAttribute("userName");
		WebMessage webMessage = new WebMessage();
		webMessage.setUserName(userName);
		webMessage.setMsgType(1);
		Page page = this.newPage(request);
		this.webMessageService.getMessageByAdmin(page, webMessage);
		page.setColspanNum(6);
		return new ModelAndView("mg/message/listForSys")
			.addObject("webMessage", webMessage)
			.addObject(CommonConstant.PAGE_KEY, page);
	}

	/**
	 * 查詢消息內容 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param webMessage
	 * @return ModelAndView
	 */
	@RequestMapping("/showMessage")
	public ModelAndView showMessage(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		WebMessage webMessage = this.webMessageService.findMessageContent(id);
		this.webMessageService.updateUserMsgStatus(id);
		return new ModelAndView("mg/message/showMessage")
			.addObject("webMessage", webMessage);

	}

	/**
	 * 删除信息 方法描述：TODO</br>
	 * 
	 * @param webMessage
	 * @return ModelAndView
	 * 
	 * */
	@RequestMapping("/deleteMessage")
	public ModelAndView deleteMessage(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String userName = (String)request.getSession().getAttribute("userName");
		this.webMessageService.deleteUserMsg(id);
		WebMessage webMessage = new WebMessage();
		webMessage.setUserName(userName);
		Page page = this.newPage(request);
		this.webMessageService.getMessageUserName(page, webMessage);
		page.setColspanNum(6);
		return new ModelAndView("mg/message/listForUser")
			.addObject("webMessage", webMessage)
			.addObject(CommonConstant.PAGE_KEY, page);
	}
	
	@RequestMapping("/checkEdu")
	public void checkEdu(HttpServletRequest request, HttpServletResponse response) {
		ServletUtils.outPrintSuccess(request, response, "校验额度成功!");
	}

	
	@RequestMapping("/valid/checkflatsatus")
	public void checkFlatSatus(HttpServletRequest request, HttpServletResponse response){
		//Map<String,String> ctxMap = WebSiteManager;Constants.ctxMap;
		logger.info("进入检测平台是否维护-----------");
		String type = request.getParameter("code");
		String msg = "";
		boolean status = false;
		msg = WebSiteManagerConstants.ctxMap.get(type+"_"+WebConstants.WEB_WEIHU_STATUS_OFF);
		if(StringUtils.isNotBlank(msg)){
			status = true;
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("status", status);
		dataMap.put("msg", msg);
		
		//判断用户
		if(!status){
			List<String> list = new ArrayList<String>();
			list.add(CommonConstant.PLAT_PARAM_AG);
			list.add(CommonConstant.PLAT_PARAM_BBIN);
			list.add(CommonConstant.PLAT_PARAM_DS);
			list.add(CommonConstant.PLAT_PARAM_HG);
			list.add(CommonConstant.PLAT_PARAM_MG);
			list.add(CommonConstant.PLAT_PARAM_NT);
			list.add(CommonConstant.PLAT_PARAM_PT);
			list.add(CommonConstant.PLAT_PARAM_HUANGGUAN);
			list.add(CommonConstant.PLAT_PARAM_CAIPIAO);
			list.add(CommonConstant.PLAT_PARAM_DOUJI);
			list.add(CommonConstant.PLAT_PARAM_AB);
			list.add(CommonConstant.PLAT_PARAM_OG);
			if(list.contains(type)){
				String userName = (String)request.getSession().getAttribute("userName");
				WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
				if(webUser!=null && webUser.getBetFlat()!=null && !"".equals(webUser.getBetFlat())){
					String betFlat = webUser.getBetFlat();
					if(betFlat.indexOf(type)!=-1){
						dataMap.put("status", true);
						dataMap.put("msg", "无权限访问该平台，请跟管理员联系！");
					}
				}
			}
			
		}
		
		ServletUtils.outPrintSuccess(request, response,dataMap);
	}
	
	/**
	 * 公告消息列表 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/news")
	public ModelAndView news(HttpServletRequest request, HttpServletResponse respose){
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		return new ModelAndView("mg/message").addObject("dataList", dataList);
	}
	
}
