package com.mh.web.controller.m;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.TUserPwdLog;
import com.mh.entity.TWebThirdPay;
import com.mh.entity.WebBank;
import com.mh.entity.WebBankHuikuan;
import com.mh.entity.WebConfig;
import com.mh.entity.WebKjPay;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserType;
import com.mh.ifc.util.ComUtil;
import com.mh.service.WebBankHuikuanService;
import com.mh.service.WebConfigService;
import com.mh.service.WebKjPayService;
import com.mh.service.WebUserService;
import com.mh.service.WebUserTypeService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
@Controller
@RequestMapping("/m/member")
public class MMemberController extends BaseController 
{
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WebBankHuikuanService webBankHuikuanService;
	
	@Autowired
	private WebConfigService webConfigService;
	
	
	@Autowired
	private WebUserTypeService webUserTypeService;
	
	@Autowired
	private WebKjPayService webPayPicService;
	
	/**
	 * 公司入款银行卡页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goAccounts")
	public ModelAndView goAccounts(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("m/member/deposit_accounts");
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		List<WebBank> bankList = this.webBankHuikuanService.getWebBankList(webUser.getUserType());
		
		List<TWebThirdPay> thirdPayList = this.webBankHuikuanService.getWebThirdPayList(webUser.getUserType());
		TWebThirdPay thirdPay = null;
		if(thirdPayList!= null && thirdPayList.size()>0)
			thirdPay =thirdPayList.get(0); 
		
		
		List<WebConfig> configList = this.webConfigService.getWebConfigList();
		WebConfig webConfig = null;
		if(configList!=null &configList.size()>0)
			webConfig = configList.get(0);
		
		
		String currDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		
		return model.addObject("currDateStr", currDateStr)
				.addObject("webUser", webUser)
				.addObject("webConfig", webConfig)
				.addObject("bankList", bankList)
				.addObject("thirdPay", thirdPay);
	}
	
	
	private String getNewString(String string)
	{
		String result = null;
		try 
		{
			result = new String(string.getBytes("ISO-8859-1"),"utf-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/dialog")
	public ModelAndView dialog(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView model = new ModelAndView("m/member/deposit_success").addObject("msg", "修改密码成功");
		return model;
	}
	
	
	@RequestMapping("/goWithdraw")
	public ModelAndView goWithdraw(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView model = new ModelAndView("m/member/withdrawal_accounts");
		UserContext uc = this.getUserContext(request);
		WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
		if(StringUtils.isBlank(user.getUserBankCard()))
			model.setViewName("m/member/bind_bank");
		model.addObject("webUser",user);
		return model;
	}
	
	
	@RequestMapping("/goPayHistory")
	public ModelAndView goPayHistory(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView model = new ModelAndView();
		String code = request.getParameter("code");
		if(code.equals("payHistory"))
			model.setViewName("m/member/deposit_rec");
		else
			model.setViewName("m/member/withdrawal_rec");
		
		Date currDate = DateUtil.currentDate();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		Date nextDate = DateUtil.addDay(currDate, -30);
		String nextDateStr = DateUtil.format(nextDate, "yyyy-MM-dd");
		return model.addObject("currDateStr", currDateStr).addObject("nextDateStr", nextDateStr);
	}
	
	@RequestMapping("/doPayHistory")
	public ModelAndView doPayHistory(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView model = new ModelAndView("m/member/");
		String startTime = request.getParameter("bet-order-filter-11");
		String endTime = request.getParameter("bet-order-filter-12");
		logger.info(startTime);
		logger.info(endTime);
		return model;
	}
	
	
	/**
	 * 用户信息
	 * @return
	 */
	@RequestMapping("/userinfo")
	public ModelAndView userinfo(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("m/member/user_info");
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
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
			webUser.setUserQq("****" + qq.substring(qq.length() - 4, qq.length()));
		if(StringUtils.isNotBlank(mobile))
			webUser.setUserMobile("********" + mobile.substring(mobile.length() - 3, mobile.length()));
		WebUserType userType = webUserTypeService.getUserTypeById(webUser.getUserType());
		return model.addObject("webUser", webUser).addObject("userType", userType);
	}
	
	/**
	 * 用户信息
	 * @return
	 */
	@RequestMapping("/updpassword")
	public ModelAndView updpassword(HttpServletRequest request,HttpServletResponse response)
	{
		String page = request.getParameter("page");
		ModelAndView model = new ModelAndView("m/member/" + page);
		return model;
	}
	
	/**
	 * 修改用户密码
	 */
	@RequestMapping("/doLoginPwd")
	public void doLoginPwd(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		String cryptOldPassword = DesUtil.encrypt(oldPwd,CommonConstant.resCommMap.get("app.client.des.key"));
		try 
		{
			if (!cryptOldPassword.equals(webUser.getUserPassword())) 
			{
				ServletUtils.outPrintFail(request, response, "原登录密码错误.");
			} 
			else 
			{
				String cryptPassword = DesUtil.encrypt(newPwd,CommonConstant.resCommMap.get("app.client.des.key"));
				if (!cryptPassword.equals(cryptOldPassword)) 
				{
					this.webUserService.updatePassword(cryptPassword,webUser.getUserName());
					this.insertModifyPwdLog(request, cryptOldPassword, cryptPassword, "登录密码修改");
					ServletUtils.outPrintSuccess(request, response, "密码修改成功.");
				} 
				else 
				{
					ServletUtils.outPrintFail(request, response,"密码修改失败.请稍候再试或联系我们客服.");
				}
			}
		} 
		catch (RuntimeException e) 
		{
			logger.error("登录密码修改异常: " + e.getMessage(), e);
			ServletUtils.outPrintFail(request, response,"登录密码修改异常.请稍候再试或联系我们客服.");
		}
	}
	
	private void insertModifyPwdLog(HttpServletRequest request, String oldPwd, String newPwd, String remark)
	{
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
	
	
	@RequestMapping("/doWithdrawPwd")
	public void doWithdrawPwd(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		String cryptOldPassword = DesUtil.encrypt(oldPwd,CommonConstant.resCommMap.get("app.money.des.key"));
		try 
		{
			if (!cryptOldPassword.equals(webUser.getUserWithdrawPassword())) 
			{
				ServletUtils.outPrintFail(request, response, "原资金密码错误.");
			}
			else 
			{
				String cryptPassword = DesUtil.encrypt(newPwd,CommonConstant.resCommMap.get("app.money.des.key"));
				if (!cryptPassword.equals(cryptOldPassword)) 
				{
					this.webUserService.updateMoneyPassword(cryptPassword,webUser.getUserName());
					this.insertModifyPwdLog(request, cryptOldPassword, cryptPassword, "资金密码修改");
					ServletUtils.outPrintSuccess(request, response, "资金密码修改成功.");
				}
				else 
				{
					ServletUtils.outPrintFail(request, response,"资金密码修改失败.请稍候再试或联系我们客服.");
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("修改资金密码异常",e);
			ServletUtils.outPrintFail(request, response,"资金密码修改失败.请稍候再试或联系我们客服.");
		}

	}
	
	
	@RequestMapping("/saveBackInfo")
	public ModelAndView saveBackInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("m/member/deposit_success");
		try 
		{
			String userBankCard = request.getParameter("userBankCard");
			String userBankAddress = request.getParameter("userBankAddress");
			String userBankType = request.getParameter("userBankType");
			String userWithdrawPassword = request.getParameter("userWithdrawPassword");
			
			if(StringUtils.isNotBlank(userBankAddress))
				userBankAddress = getNewString(userBankAddress);
			
			if(StringUtils.isNotBlank(userBankType))
				userBankType = getNewString(userBankType);
			
			WebUser webUser = new WebUser();
			webUser.setUserBankCard(userBankCard);
			webUser.setUserBankAddress(userBankAddress);
			webUser.setUserBankType(userBankType);
			webUser.setUserWithdrawPassword(userWithdrawPassword);
			UserContext uc = this.getUserContext(request);
			WebUser ruser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			if(ruser!=null && ruser.getUserBankCard()!=null && !"".equals(ruser.getUserBankCard()))
				return model.addObject("msg", "绑定失败，请完善信息!");
			String cryptPassword = DesUtil.encrypt(webUser.getUserWithdrawPassword(), CommonConstant.resCommMap.get("app.money.des.key"));
			if (!StringUtils.equalsIgnoreCase(cryptPassword, ruser.getUserWithdrawPassword())) 
			{
				return model.addObject("msg", "资金密码错误!");
			} 
			else 
			{
				List<String> fieldList = new ArrayList<String>();
				List<Object> valList = new ArrayList<Object>();
				fieldList.add("user_bank_type");
				fieldList.add("user_bank_address");
				fieldList.add("user_bank_card");
				valList.add(webUser.getUserBankType());
				valList.add(webUser.getUserBankAddress());
				valList.add(webUser.getUserBankCard());
				this.webUserService.updateWebUser(uc.getUserName(), fieldList, valList);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("绑定银行卡失败",e);
			model.addObject("msg", "收款银行信息设置失败.请稍候再试或联系我们客服更新！");
		}
		return model.addObject("msg", "绑定成功");
	}
	
	/**
	 * 微信支付
	 * @throws IOException 
	 */
	@RequestMapping("/gowxpay")
	public ModelAndView gowxpay(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("m/member/wx_accounts");
		
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		//图片类型
		Integer paytype = Integer.parseInt(request.getParameter("paytype"));
		if(paytype == null)
			paytype = 1;
		
		int count = this.webPayPicService.getPayCount(paytype);
		if(count > 0)
		{
			//获取数据
			WebKjPay webKjPay = this.webPayPicService.getKjPay(paytype,webUser.getUserType());
			model.addObject("webKjPay", webKjPay);
			
			if(paytype == 1)
				model.setViewName("m/member/wx_accounts");
			else
				model.setViewName("m/member/ali_accounts");
		}
		else
		{
			model.setViewName("m/member/pay_error");
			model.addObject("paytype", paytype);
		}
		
		List<WebConfig> configList = this.webConfigService.getWebConfigList();
		WebConfig webConfig = null;
		if(configList!=null &configList.size()>0)
			webConfig = configList.get(0);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return model.addObject("webConfig", webConfig).addObject("webUser", webUser).addObject("currDateStr", sdf.format(new Date()));
	}
	
	
	@RequestMapping("/doKjPay")
	public void doKjPay(HttpServletRequest request, HttpServletResponse response,WebBankHuikuan bankHk) throws SQLException, IOException
	{
		try 
		{
			UserContext uc = this.getUserContext(request);
			WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
			
			String payType = request.getParameter("payType");
			String payNo = request.getParameter("payNo");
			String companyBank = "快捷支付";
			if("1".equals(payType)){
				companyBank ="微信支付(M)";
			}else if("2".equals(payType)){
				companyBank ="支付宝支付(M)";
			}
			
			Date currDate = DateUtil.currentDate();
			String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
			Map<String, Integer> map = this.webBankHuikuanService.getWebBankHuikuanTj(uc.getUserName(), currDateStr);
			int totalTimes = map.get("totalTimes")+1;
			int dayTimes = map.get("dayTimes")+1;
			
			bankHk.setHkType("快捷支付");
			bankHk.setHkCompanyBank(companyBank);
			bankHk.setGmt4Time(DateUtil.getGMT_4_Date());// 美东当前时间
			bankHk.setTotalTimes(totalTimes);// 总次数
			bankHk.setDayTimes(dayTimes);// 日次数
			bankHk.setHkOrder(ComUtil.getOnliePayOrder());
			bankHk.setHkStatus(WebConstants.T_WEB_BANK_HUIKUAN_STATUS_0);// 订单状态为：未审核
			bankHk.setHkCheckStatus(WebConstants.T_WEB_BANK_HUIKUAN_CHECKED_STATUS_0);// 通过状态：初始状态
			bankHk.setCreateTime(currDate);
			bankHk.setModifyTime(currDate);
			bankHk.setUserName(uc.getUserName());
			bankHk.setHkIp(IPSeeker.getIpAddress(request));
			bankHk.setHkModel(WebConstants.T_WEB_BANK_HUIKUAN_MODEL_1);// 在线支付
			bankHk.setPayNo(payNo);
			bankHk.setRemark(companyBank);
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
	
}
