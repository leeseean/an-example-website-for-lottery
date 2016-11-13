/**   
 * 文件名称: WebMemberController.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-7-1 下午7:08:08<br/>
 */
package com.mh.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.constants.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.TUserPwdLog;
import com.mh.entity.TWebThirdPayKj;
import com.mh.entity.WebAgent;
import com.mh.entity.WebGongGao;
import com.mh.entity.WebKjPay;
import com.mh.entity.WebPromos;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserType;
import com.mh.service.PayCenterService;
import com.mh.service.WebAgentService;
import com.mh.service.WebKjPayService;
import com.mh.service.WebMessageService;
import com.mh.service.WebUserService;
import com.mh.service.WebUserTypeService;
import com.mh.web.login.UserContext;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-1 下午7:08:08<br/>
 */

@Controller
@RequestMapping("/member")
public class WebMemberController extends BaseController {

	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WebMessageService webMessageService;
 
	@Autowired
	private WebAgentService webAgentService;
	
	@Autowired
	private WebUserTypeService webUserTypeService;
	
	@Autowired
	private PayCenterService payCenterService;
	
	@Autowired
	private WebKjPayService webPayPicService;
	
	/**
	 * 个人中心入口 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param Args1
	 * @param Args2
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request,HttpServletResponse response) {
		String type = request.getParameter("type");
		
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		String backUrl = "";
		if(StringUtils.isEmpty(type)){
			type="0";
		}
		
		if("0".equals(type)){
			backUrl = "member/accountInfo";
		}else if("1".equals(type)){
			backUrl = "member/goPayBank";
		}else if("2".equals(type)){
			backUrl = "member/goWithdraw";
		}else if("3".equals(type)){
			backUrl = "member/goEdu";
		}else if("4".equals(type)){
			backUrl = "records/goList?code=ag";
		}else if("5".equals(type)){
			backUrl = "message/listForUser";
		}
		int msgTotal = this.webMessageService.getMessageNum(uc.getUserName(), WebConstants.T_WEB_MESSAGE_TYPE_1+","+WebConstants.T_WEB_MESSAGE_TYPE_2,WebConstants.T_WEB_MESSAGE_STATUS_1);
		
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		WebAgent webAgent = this.webAgentService.findWebAgent(uc.getUserName());
		List<WebPromos> lbList = WebSiteManagerConstants.LbWEBPROMOS_LIST;
		
		TWebThirdPayKj wxthird = payCenterService.getTWebThirdPayKj(1,webUser.getUserType());
		TWebThirdPayKj alithird = payCenterService.getTWebThirdPayKj(2,webUser.getUserType());
		
		WebKjPay wxkjPay = webPayPicService.getKjPay(1, webUser.getUserType());
		WebKjPay alikjPay = webPayPicService.getKjPay(2, webUser.getUserType());
		
		WebUserType userType = webUserTypeService.getUserTypeById(webUser.getUserType());
		return new ModelAndView("member/member_main")
			.addObject("webUser",webUser)
			.addObject("uc", uc)
			.addObject("webAgent", webAgent)
			.addObject("type", type)
			.addObject("msgTotal", msgTotal)
			.addObject("gongGaoList", dataList)
			.addObject("userType", userType)
			.addObject("wxthird", wxthird)
			.addObject("alithird", alithird)
			.addObject("wxkjPay", wxkjPay)
			.addObject("alikjPay", alikjPay)
			.addObject("backUrl", backUrl).addObject("lbPromoList",lbList);
	}
	
	/**
	 * 会员等级图片写入页面
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping("/getUserTypeImg")
	public void getUserTypeImg(HttpServletRequest request, HttpServletResponse response){
		//图片类型
		Integer id = Integer.parseInt(request.getParameter("id"));
		//获取数据
		WebUserType userType = this.webUserTypeService.getUserTypeById(id);
		try {
			response.setContentType("iamge/jpg");
			//缓存设置
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			ServletOutputStream sos = response.getOutputStream();
			//写入响应
			sos.write(userType.getTypePic());
			sos.flush();
			sos.close();
		} catch (IOException e) {
			logger.info("会员图片写入异常...");
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到个人银行卡页面 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/goBankInfo")
	public ModelAndView goBankInfo(HttpServletRequest request,HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		return new ModelAndView("member/account/bank_info").addObject("webUser", webUser);
	}

	/**
	 * 账户信息 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/accountInfo")
	public ModelAndView accountInfo(HttpServletRequest request,HttpServletResponse response) {

		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		return new ModelAndView("member/account/account_info").addObject("webUser", webUser);
	}

	/**
	 * 进入密码页面 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/goPwdMain")
	public ModelAndView goPwdMain(HttpServletRequest request,HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		return new ModelAndView("member/account/pwd_main").addObject("webUser",webUser);
	}

	/**
	 * 跳转到资金密码修改页面 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/goWithdrawPwd")
	public ModelAndView goWithdrawPwd(HttpServletRequest request,HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		return new ModelAndView("member/account/withdraw_pwd").addObject("webUser", webUser);
	}

	/**
	 * 跳转到登录密码修改页面 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/goLoginPwd")
	public ModelAndView goLoginPwd(HttpServletRequest request,HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		return new ModelAndView("member/account/login_pwd").addObject("webUser", webUser);
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
	public void doLoginPwd(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		UserContext uc = this.getUserContext(request);
		
		if(StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd)){
			ServletUtils.outPrintFail(request, response, "请输入密码!");
			return;
		}
		
		if(newPwd.length() < 6){
			ServletUtils.outPrintFail(request, response, "请输入6位长度密码!");
			return;
		}
		
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
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
	public void doWithdrawPwd(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		if(StringUtils.isBlank(newPwd) || !newPwd.matches("[0-9]{4}")){
			ServletUtils.outPrintFail(request, response, "请输入4位数字资金密码.");
			return;
		}
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
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
		return new ModelAndView("member/contact/panel-contact");
	}
	
	
	private void insertModifyPwdLog(HttpServletRequest request, String oldPwd, String newPwd, String remark){
		UserContext uc = this.getUserContext(request);
		
		TUserPwdLog log = new TUserPwdLog();
		log.setUserName(uc.getUserName());
		log.setOldPassword(oldPwd);
		log.setNewPassword(newPwd);
		log.setRemark(remark);
		log.setUpdateTime(new Date());
		log.setIp(IPSeeker.getIpAddress(request));
		this.webUserService.insertTuserPwdLog(log);
	}

}
