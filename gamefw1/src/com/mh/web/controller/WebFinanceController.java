/**   
* 文件名称: WebFinanceController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 上午10:08:05<br/>
*/  
package com.mh.web.controller;

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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mh.client.FlatClient;
import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.OrderNoUtils;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.TLinkWebKjPay;
import com.mh.entity.TWebThirdPay;
import com.mh.entity.WebBank;
import com.mh.entity.WebBankHuikuan;
import com.mh.entity.WebConfig;
import com.mh.entity.WebEdu;
import com.mh.entity.WebKjPay;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.entity.WebUserType;
import com.mh.entity.WebUserWithdraw;
import com.mh.ifc.ABIfcUtil;
import com.mh.ifc.AGIfcUtil;
import com.mh.ifc.DJIfcUtil;
import com.mh.ifc.DSIfcUtil;
import com.mh.ifc.GDIfcUtil;
import com.mh.ifc.HGIfcUtil;
import com.mh.ifc.MGIfcUtil;
import com.mh.ifc.NBBINIfcUtil;
import com.mh.ifc.NMGIfcUtil;
import com.mh.ifc.NSBIfcUtil;
import com.mh.ifc.NTIfcUtil;
import com.mh.ifc.NewPTIfcUtil;
import com.mh.ifc.OGIfcUtil;
import com.mh.ifc.OSIfcUtil;
import com.mh.ifc.PTIfcUtil;
import com.mh.ifc.SBTIfcUtil;
import com.mh.ifc.TTGIfcUtil;
import com.mh.ifc.http.AbConts;
import com.mh.ifc.http.AbResResult;
import com.mh.ifc.http.AgConts;
import com.mh.ifc.http.AgResResult;
import com.mh.ifc.http.DjConts;
import com.mh.ifc.http.DjResResult;
import com.mh.ifc.http.DsConts;
import com.mh.ifc.http.DsResResult;
import com.mh.ifc.http.GdConts;
import com.mh.ifc.http.GdResResult;
import com.mh.ifc.http.HgConts;
import com.mh.ifc.http.HgResResult;
import com.mh.ifc.http.MGConts;
import com.mh.ifc.http.MgResResult;
import com.mh.ifc.http.NBBinConts;
import com.mh.ifc.http.NBbinResResult;
import com.mh.ifc.http.NMGConts;
import com.mh.ifc.http.NMgResResult;
import com.mh.ifc.http.NsbConts;
import com.mh.ifc.http.NsbResResult;
import com.mh.ifc.http.NtConts;
import com.mh.ifc.http.NtResResult;
import com.mh.ifc.http.OgConts;
import com.mh.ifc.http.OgResResult;
import com.mh.ifc.http.OsConts;
import com.mh.ifc.http.OsResResult;
import com.mh.ifc.http.PtResResult;
import com.mh.ifc.http.SbtApiConstants;
import com.mh.ifc.http.SbtResBean;
import com.mh.ifc.http.TTGConstant;
import com.mh.ifc.http.TTgResResult;
import com.mh.ifc.util.ComUtil;
import com.mh.service.PayCenterService;
import com.mh.service.WebBankHuikuanService;
import com.mh.service.WebConfigService;
import com.mh.service.WebEduService;
import com.mh.service.WebKjPayService;
import com.mh.service.WebRecordService;
import com.mh.service.WebUserFlatService;
import com.mh.service.WebUserService;
import com.mh.service.WebUserTypeService;
import com.mh.service.WebUserWithdrawService;
import com.mh.web.login.UserContext;

/** 
 * 类描述: TODO<br/>财务管理
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 上午10:08:05<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/member")
public class WebFinanceController extends BaseController{
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WebBankHuikuanService webBankHuikuanService;
	
	@Autowired
	private WebUserWithdrawService webUserWithdrawService;
	
	@Autowired
	private WebEduService webEduService;
	
	@Autowired
	private WebUserFlatService webUserFlatService;
	
	@Autowired
	private WebConfigService webConfigService;
	
	@Autowired
	private WebRecordService webRecordService;
	
	@Autowired
	private WebKjPayService webPayPicService;
	
	@Autowired
	private WebUserTypeService webUserTypeService;
	
	@Autowired
	private FlatClient flatClient;
	
	@Autowired
	private PayCenterService payCenterService;
	
	
	/**
	 * 查询财务记录
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param records
	 * @return  
	 * ModelAndView
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/goList")
	public ModelAndView goList(HttpServletRequest request, HttpServletResponse response, WebRecords records){
		String code = request.getParameter("code");
		
		String flatName = request.getParameter("flatName");
		if(!StringUtils.isEmpty(code)){
			records.setCode(code);
		}else{
			records.setCode("withdrawHistory");
		}
		UserContext uc = this.getUserContext(request);
		records.setUserName(uc.getUserName());
		Date currDate = DateUtil.currentDate();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		if(StringUtils.isEmpty(records.getStartTime()) ){
			Date nextDate = DateUtil.addDay(currDate, -30);
			String nextDateStr = DateUtil.format(nextDate, "yyyy-MM-dd");
			records.setStartTime(nextDateStr);
		}
		
		if(StringUtils.isEmpty(records.getEndTime())){
			records.setEndTime(currDateStr);
		}
		 
		Page page = this.newPage(request);
 
		webRecordService.findFinancePage(page, records);
	 
		return new ModelAndView("member/account/" + code)
			.addObject(CommonConstant.PAGE_KEY, page)
			.addObject("records", records)
			.addObject("flatParms", CommonConstant.flatsTransfRecord).addObject("flatName", flatName);
	}
	
	

	
	/**
	 * 跳转到账户充值页面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/payInfo")
	public ModelAndView payInfo(HttpServletRequest request,HttpServletResponse response) {
		
		
		return new ModelAndView("member/account/pay_info");
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
		return new ModelAndView("member/account/pay_online");
	}
	
	@RequestMapping("/paykjOnline")
	public ModelAndView paykjOnline(HttpServletRequest request, HttpServletResponse response) {
		String paytype = request.getParameter("paytype");
		String payid = request.getParameter("payid");
		return new ModelAndView("member/account/paykj_online").addObject("paytype", paytype).addObject("payid", payid);
	}
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goPayBank")
	public ModelAndView goPayBank(HttpServletRequest request,HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		
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
		/**银行卡账户编号**/
		Map<String,String> bankMap=new HashMap<String, String>();
		for(WebBank w : bankList){
			bankMap.put("", "");
		}
		
		return new ModelAndView("member/account/pay_bank")
			.addObject("currDateStr", currDateStr)
			.addObject("webUser", webUser)
			.addObject("webConfig", webConfig)
			.addObject("bankList", bankList)
			.addObject("thirdPay", thirdPay);
	}
	
	
	/**
	 * 支付
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/doBankPay")
	public void doBankPay(HttpServletRequest request,HttpServletResponse response,WebBankHuikuan bankHk) {
		try {
 
			if(bankHk.getHkMoney()==null){
				ServletUtils.outPrintFail(request, response, "请输入充值金额");
				return;
			}
			if(StringUtils.isBlank(bankHk.getHkUserName().replaceAll(" ", ""))){
				ServletUtils.outPrintFail(request, response, "请输入汇款人姓名");
				return;
			}
			if(StringUtils.isBlank(bankHk.getHkType())){
				ServletUtils.outPrintFail(request, response, "请选择汇入方式");
				return;
			}
			if(StringUtils.isBlank(bankHk.getPayNo())){
				ServletUtils.outPrintFail(request, response, "请选择汇入银行");
				return;
			}
			
			/**最低入款判断**/
			List<WebConfig> configList = this.webConfigService.getWebConfigList();
			String mobile = request.getParameter("mobile");
			if(StringUtils.isNotEmpty(mobile)){
				bankHk.setHkCompanyBank(bankHk.getHkCompanyBank() + "(M)");
			}
			WebConfig webConfig = null;
			if(configList!=null &configList.size()>0){
				webConfig = configList.get(0);
				Double hkMoney = bankHk.getHkMoney();
				if(hkMoney == null || hkMoney <= 0){
					ServletUtils.outPrintFail(request, response, "请正确输入金额");
					return;
				}
				double minPay = webConfig.getCompanyMinPay();
				if(hkMoney<minPay){
					ServletUtils.outPrintFail(request, response, "公司入款最低"+minPay+"元！");
					return;
				}
			}
			
			if(StringUtils.isEmpty(bankHk.getHkCompanyBank())|| StringUtils.isEmpty(bankHk.getHkType()) 
					|| StringUtils.isEmpty(bankHk.getHkUserName())|| StringUtils.isEmpty(bankHk.getHkUserName().trim())){
				ServletUtils.outPrintFail(request, response, "请完善您入款的信息！");
				return;
			}
			
			UserContext uc = this.getUserContext(request);
			WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
			Date currDate = DateUtil.currentDate();
			String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
			Map<String, Integer> map = this.webBankHuikuanService.getWebBankHuikuanTj(uc.getUserName(), currDateStr);
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
			bankHk.setUserName(uc.getUserName());
			bankHk.setHkIp(IPSeeker.getIpAddress(request));
			bankHk.setHkModel(WebConstants.T_WEB_BANK_HUIKUAN_MODEL_1);// 银行卡划款
			bankHk.setHkCompanyBank(StringUtils.trim(bankHk.getHkCompanyBank()));
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
	
	@RequestMapping("/doKjPay")
	public void doKjPay(HttpServletRequest request, HttpServletResponse response,WebBankHuikuan bankHk) throws SQLException, IOException
	{
		try 
		{
			String payType = request.getParameter("payType");
			String companyBank = "快捷支付";
			if("1".equals(payType)){
				companyBank ="微信支付";
			}else if("2".equals(payType)){
				companyBank ="支付宝支付";
			}
			if(StringUtils.isBlank(bankHk.getHkUserName())){
				ServletUtils.outPrintFail(request, response, "请输入您微信昵称");
				return;
			}
			if(bankHk.getHkMoney()==null){
				ServletUtils.outPrintFail(request, response, "请输入充值金额");
				return;
			}
			String _hkUserName = bankHk.getHkUserName().replaceAll(" ", "").trim();
			if(StringUtils.isBlank(_hkUserName)){
				ServletUtils.outPrintFail(request, response, "请输入您微信昵称");
				return;
			}
			if(bankHk.getHkMoney()<=0){
				ServletUtils.outPrintFail(request, response, "充值金额大于0");
				return;
			}
			
			
			UserContext uc = this.getUserContext(request);
			WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
			
			if(StringUtils.isNotBlank(payType) && StringUtils.equals(payType, "1") || StringUtils.equals(payType, "2")){
				List<TLinkWebKjPay> kjPay = payCenterService.getTWebKjPay(user.getUserType(),Integer.parseInt(payType));
				if(CollectionUtils.isEmpty(kjPay)){
					ServletUtils.outPrintFail(request, response, "该会员类型未绑定快捷支付或无效的快捷支付类型");
					return;
				}
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
	 * 跳转至微信转账页面
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@RequestMapping("/goWxPay")
	public ModelAndView goWxPay(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="paytype",required=false) String paytype){
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		
		ModelAndView model = new ModelAndView();
		//图片类型
		if(StringUtils.isBlank(paytype)){
			paytype = "1";
		}
		
		if(paytype.equals("1")){
			model.setViewName("member/account/wx_pay");
		}else{
			model.setViewName("member/account/ali_pay");
		}
		
		//获取数据
		WebKjPay webKjPay = this.webPayPicService.getKjPay(Integer.parseInt(paytype),webUser.getUserType());
		
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
	 * 二维码写入页面
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping("/getPayImg")
	public void getPayImg(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
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
	 * 跳转至用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@RequestMapping("/goUserInfo")
	public ModelAndView goUserInfo(HttpServletRequest request, HttpServletResponse response){
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		String qq = webUser.getUserQq();
		String mail = webUser.getUserMail();
		String mobile = webUser.getUserMobile();
		if(StringUtils.isNotBlank(mail)){
			String inner = "";
			String[] mails = mail.split("@");
			int length = mails[0].length();
			switch (length) {
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
		if(StringUtils.isNotBlank(qq)){
			webUser.setUserQq("****" + qq.substring(qq.length() - 4, qq.length()));
		}
		if(StringUtils.isNotBlank(mobile)){
			webUser.setUserMobile("********" + mobile.substring(mobile.length() - 3, mobile.length()));
		}
		WebUserType userType = webUserTypeService.getUserTypeById(webUser.getUserType());
		ModelAndView model = new ModelAndView("member/account/userinfo");
		return model.addObject("webUser", webUser).addObject("userType", userType);
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
		String url = "member/account/withdraw";
		UserContext uc = this.getUserContext(request);
		WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
		//判断出款银行卡是否设置
		if(null != user && StringUtils.isEmpty(user.getUserBankCard())){
			url = "member/account/bank_info";
		}else{
			//判断打码量
			/*TWebDama webDama = new TWebDama();
			webDama.setUserName(uc.getUserName());
			webDama = this.webRecordService.findWebDama(webDama);
			if(null !=webDama){
				WebRecords records = new WebRecords();
				records.setUserName(uc.getUserName());
				List<Map<String, Object>> list = webRecordService.btReport(records);
				double total = 0.0;
				for(int i=0;i<list.size();i++){
					total = total + NumberUtils.toDouble(String.valueOf(list.get(i).get("betIncome")), 0d);
				}
				webDama.setCurUserDama(total);
				if (total < webDama.getDmReqDm().doubleValue()) {// 打码量不够
					url = "member/account/dama";
					view.addObject("dama", webDama);
				}
			}*/
		}
		
		view.addObject("webUser",user);
		view.setViewName(url);
		return view;
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
			UserContext uc = this.getUserContext(request);
			WebUser ruser = this.webUserService.findWebrUseByUserName(uc.getUserName());
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
				this.webUserService.updateWebUser(uc.getUserName(), fieldList, valList);
				
//				this.webUserService.updateWebUser(ruser);
				ServletUtils.outPrintSuccess(request, response,"绑定成功!", optInfo);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("绑定银行卡失败",e);
			ServletUtils.outPrintFail(request, response, "收款银行信息设置失败.请稍候再试或联系我们客服更新！");
		}
	}
	
	
	@RequestMapping("/checkEdu")
	public void checkEdu(HttpServletRequest request, HttpServletResponse response) {
		/*try {
			String wPoints = request.getParameter("wPoints");
			String fromFlatName = request.getParameter("fromFlatName");
//			String toFlatName = request.getParameter("toFlatName");
			if("account".equals(fromFlatName)){
				int totalEdu2 = this.webEduService.getEduTotal(2);
				int totalEdu1 = this.webEduService.getEduTotal(1);
				if(totalEdu2<0){
					totalEdu2 =-totalEdu2;
				}
			 
				String maxEdeStr = WebSiteManagerConstants.SYSPARAMMAP.get("web_edu_month_max").toString();
				double maxEdu = 500000;
				if(!StringUtils.isEmpty(maxEdeStr)){
					maxEdu = Double.valueOf(maxEdeStr);
				}
				double wp = Double.valueOf(wPoints);
				double total = wp+totalEdu2-totalEdu1;
				if(total>maxEdu){
					double mnu = maxEdu - (totalEdu2-totalEdu1);
					ServletUtils.outPrintFail(request, response, "额度转换超过限额，请联系在线客服处理。");
					return;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			//logger.error("校验额度失败",e);
		}*/
		ServletUtils.outPrintSuccess(request, response, "校验额度成功!");
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
			UserContext uc = this.getUserContext(request);
			user = this.webUserService.findWebrUseByUserName(uc.getUserName());
			double userMoney = 0;
			if(user!=null && user.getUserMoney()!=null){
				userMoney =  user.getUserMoney().doubleValue();
			}
			
			if(StringUtils.isEmpty(user.getUserBankCard())){
				ServletUtils.outPrintFail(request, response, "请绑定银行卡!");
				return;
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
	 * 跳转到额度转换页面
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goEdu")
	public ModelAndView goEdu(HttpServletRequest request,HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
		
		return new ModelAndView("member/account/edu")
			.addObject("user",user);
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
			UserContext uc = this.getUserContext(request);
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(uc.getUserName());
			double blance = flatClient.searchUserBlance(webUserFlat, uc, flatName);
			/*double blance = 0;
			Map<String,String> params = new HashMap<String,String>();
			if (StringUtils.equalsIgnoreCase(flatName, "bbin")) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getBbinUserName())) {
					webUserFlat = NBBINIfcUtil.registNBBINAccout(webUserFlat);
					if (webUserFlat.getBbinStatus() != null && webUserFlat.getBbinStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(NBBinConts.USERNAME, webUserFlat.getBbinUserName());// 游戏帐号
				NBbinResResult result = NBBINIfcUtil.balance(params);
				if (result != null && result.getResult()) {
					JSONArray _arr = JSON.parseArray(result.getData());
					JSONObject json = _arr.getJSONObject(0);
					if(json!=null&&json.containsKey("Balance")&& json.get("Balance")!=null){
						blance = json.getDoubleValue("Balance");
					}
				}
			} else if (StringUtils.equalsIgnoreCase(flatName, "mg")) {

				params.put(MGConts.USERNAME, webUserFlat.getMgUserName());// 游戏帐号
				params.put(MGConts.PASSWORD, webUserFlat.getMgPassword());// 游戏帐号
				params.put(AgConts.ACTYPE, AgConts.ACTYPE_1);// 游戏帐号
				MgResResult result = MGIfcUtil.balance(params);
				if (result != null && !StringUtils.isEmpty(result.getData())) {
					blance = Double.valueOf(result.getData());
				}

			} else if (StringUtils.endsWithIgnoreCase(flatName, "mg")){
				//注册
				if (StringUtils.isEmpty(webUserFlat.getMgUserName())) {
					webUserFlat = NMGIfcUtil.registNMgAccout(webUserFlat);
					if (StringUtils.isNotEmpty(webUserFlat.getMgUserName()) || StringUtils.isNotEmpty(webUserFlat.getMgId())) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				//params.put(NMGConts.USERNAME, webUserFlat.getMgUserName());// 游戏帐号
				//params.put(NMGConts.PASSWORD, webUserFlat.getMgPassword());// 游戏帐号
				params.put(NMGConts.USERID, webUserFlat.getMgId());
				NMgResResult result = NMGIfcUtil.balance(params);
				if (result != null && !StringUtils.isEmpty(result.getBlance())) {
					blance = Double.valueOf(result.getBlance());
				}
			} else if (StringUtils.equalsIgnoreCase(flatName, "ag")) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getAgUserName())) {
					webUserFlat = AGIfcUtil.registAgAccout(webUserFlat);
					if (webUserFlat.getAgStatus() != null && webUserFlat.getAgStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(AgConts.LOGINNAME, webUserFlat.getAgUserName());// 游戏帐号
				params.put(AgConts.PASSWORD, webUserFlat.getAgPassword());// 游戏帐号
				params.put(AgConts.ACTYPE, AgConts.ACTYPE_1);// 游戏帐号
				AgResResult result = AGIfcUtil.balance(params);
				if (result != null && !StringUtils.isEmpty(result.getInfo())) {
					blance = Double.valueOf(result.getInfo());
				}

			} else if (StringUtils.equalsIgnoreCase(flatName, "ds")) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getDsUserName())) {
					webUserFlat = DSIfcUtil.registDsAccout(webUserFlat);
					if (webUserFlat.getDsStatus() != null && webUserFlat.getDsStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(DsConts.USERNAME, webUserFlat.getDsUserName());
				jsonObj.put(DsConts.PASSWORD, webUserFlat.getDsPassword());
				params.put("params", jsonObj.toString());

				DsResResult result = DSIfcUtil.balance(params);
				if (result != null) {
					JSONObject json = JSON.parseObject(result.getParams());
					if(json.containsKey("balance")&& json.get("balance")!=null){
						blance = Double.valueOf(json.get("balance").toString());
					}
				}
			} else if (StringUtils.equalsIgnoreCase(flatName, "nt")) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getNtUserName())) {
					webUserFlat = NTIfcUtil.registNtAccout(webUserFlat);
					if (webUserFlat.getNtStatus() != null && webUserFlat.getNtStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(NtConts.USER_ID, webUserFlat.getNtUserName());// 游戏帐号
				NtResResult result = NTIfcUtil.balance(params);
				if (result != null) {
					blance = result.getBalance() / 100;
				}
			} else if (StringUtils.equalsIgnoreCase(flatName, "pt")) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getPtUserName())) {
					webUserFlat = NewPTIfcUtil.registPtAccout(webUserFlat);
					if (webUserFlat.getPtStatus() != null && webUserFlat.getPtStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(NtConts.USER_ID, webUserFlat.getPtUserName());// 游戏帐号
				blance= NewPTIfcUtil.balance(params);
			}
			 else if (StringUtils.equalsIgnoreCase(flatName, "pt")) {
				params.put(NtConts.USER_ID, webUserFlat.getPtUserName());// 游戏帐号
				PtResResult result = PTIfcUtil.balance(params);
				if (result != null) {
					blance = result.getBalance() / 100;
				}
			} 
			else if (StringUtils.equalsIgnoreCase(flatName, "hg")) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getHgUserName())) {
					webUserFlat = HGIfcUtil.registHgAccout(webUserFlat);
					if (webUserFlat.getHgStatus() != null && webUserFlat.getHgStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(HgConts.MODE, HgConts.LOGIN_MODE);
				jsonObj.put(HgConts.USERNAME, webUserFlat.getHgUserName());
				params.put("params", jsonObj.toString());

				HgResResult result = HGIfcUtil.balance(params);
				if (result != null && result.getBalance() != null) {
					blance = result.getBalance();
				}
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_DJ)) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getDjUserName())) {
					webUserFlat = DJIfcUtil.registDJAccout(webUserFlat);
					if (webUserFlat.getDjStatus() != null && webUserFlat.getDjStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(DjConts.USERNAME, webUserFlat.getDjUserName());// 游戏帐号
				DjResResult result = DJIfcUtil.balance(params);
				if (result != null && result.getBalance() != null) {
					blance = result.getBalance();
				}
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_AB)) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getAbUserName())) {
					webUserFlat = ABIfcUtil.registAbAccout(webUserFlat);
					if (webUserFlat.getAbStatus() != null && webUserFlat.getAbStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(AbConts.USERNAME, webUserFlat.getAbUserName());
				params.put(AbConts.PASSWORD, webUserFlat.getAbPassword());
				AbResResult result = ABIfcUtil.balance(params);
				if (result != null && result.getBalance() != null) {
					blance = result.getBalance();
				}
				
			} else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_OG)) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getOgUserName())) {
					webUserFlat = OGIfcUtil.registOgAccout(webUserFlat);
					if (webUserFlat.getOgStatus() != null && webUserFlat.getOgStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(OgConts.USERNAME, webUserFlat.getOgUserName());
				params.put(OgConts.PASSWORD, webUserFlat.getOgPassword());
				OgResResult result = OGIfcUtil.balance(params);
				if (result != null && OgConts.RES_OK.equals(result.getResultCode())) {
					blance =  Double.valueOf(result.getResult());
				}
			} else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_OS)) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getOsUserName())) {
					webUserFlat = OSIfcUtil.registOsAccout(webUserFlat);
					if (webUserFlat.getOsStatus() != null && webUserFlat.getOsStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(OsConts.USERNAME, webUserFlat.getOsUserName());
				OsResResult result = OSIfcUtil.balance(params);
				if (result != null && OsConts.RES_OK.equals(result.getErrorCode())) {
					blance =  result.getBalance().doubleValue();
				}
			} else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_SB)) {
				//注册
				if (StringUtils.isEmpty(webUserFlat.getSbUserName())) {
					webUserFlat = NSBIfcUtil.registSbAccout(webUserFlat);
					if (webUserFlat.getSbStatus() != null && webUserFlat.getSbStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(NsbConts.PLAYERNAME, webUserFlat.getSbUserName());
				NsbResResult result = NSBIfcUtil.balance(params);
				if (result != null && NsbConts.RES_CODE_0.equals(result.getError_code())) {
					String data = result.getData();
					JSONArray array = JSONArray.parseArray(data);
					String _blance = array.getJSONObject(0).get("balance").toString();
					blance = Double.valueOf(_blance);
				}
			} else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_GD)){
				//注册
				if (StringUtils.isEmpty(webUserFlat.getGdUserName())) {
					webUserFlat = GDIfcUtil.registGdAccout(webUserFlat);
					if (webUserFlat.getGdStatus() != null && webUserFlat.getGdStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(GdConts.USERID, webUserFlat.getGdUserName());
				GdResResult result = GDIfcUtil.balance(params);
				if(result!=null && GdConts.RES_CODE_0.equals(result.getErrorCode())){
					blance = Double.valueOf(result.getBalance());
				}
			}else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_TTG)){
				//注册
				if (StringUtils.isEmpty(webUserFlat.getTtgUserName())) {
					webUserFlat = TTGIfcUtil.registTTGAccout(webUserFlat);
					if (webUserFlat.getTtgStatus() != null && webUserFlat.getTtgStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(TTGConstant.USERID, webUserFlat.getTtgUserName());
				TTgResResult result = TTGIfcUtil.balance(params);
				if(result!=null && TTGConstant.RES_SUCCESS.equals(result.getErrorCode()) && StringUtils.isNotEmpty(result.getBalance())){
					blance = Double.valueOf(result.getBalance());
				}
			}else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_SBT)){
				//注册
				if (StringUtils.isEmpty(webUserFlat.getSbtUserName())) {
					webUserFlat = SBTIfcUtil.registSbtAccout(webUserFlat);
					if (webUserFlat.getSbtStatus() != null && webUserFlat.getSbtStatus().intValue() == 1) {
						this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
					}
				}
				//查余额
				params.put(SbtApiConstants.USER_ID, webUserFlat.getSbtUserName());
				SbtResBean result = SBTIfcUtil.balance(params);
				if(result!=null && SbtApiConstants.RESP_SUCCESS_CODE.equals(result.getErrorCode())){
					blance = Double.valueOf(result.getBalance());
				}
			}
			else if (StringUtils.equalsIgnoreCase(flatName, "account")) {
				WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
				blance = user.getUserMoney().doubleValue();
			}*/
			WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
			Map<String,Double> dataMap = new HashMap<String,Double>();
			dataMap.put("flatMoney", blance);
			dataMap.put("accountMoney", user.getUserMoney());
			ServletUtils.outPrintSuccess(request, response,dataMap);
		} catch (Exception e) {
			logger.error("用户额度获取异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "用户额度获取异常");
		}
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
	public synchronized void doEdu(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("fromFlatName") String fromFlatName,
			@RequestParam("toFlatName") String toFlatName,
			@RequestParam("wPoints") Integer wPoints) {
		try {
			String optType = "";
			String payType = "";// 转换类型
			String flatName ="";
			UserContext uc = this.getUserContext(request);
			//WebUser webUser = this.webUserService.findWebUserByUserNameForUpdate(uc.getUserName());
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			if (StringUtils.equals(fromFlatName, WebConstants.FLAT_NAME_ACCOUNT)) {// 从总账户转出
				if (webUser!= null && wPoints > webUser.getUserMoney()) {
					logger.info("总帐户余额不足!");
					ServletUtils.outPrintFail(request, response, "总帐户余额不足!");
					//this.webUserService.updateWebUser(webUser);
					return;
				}
				optType = WebConstants.EDU_TYPE_2;// 2:（主帐号-》游戏平台）
				payType = WebConstants.EDU_TYPE_2;
				flatName = toFlatName;
			} else {
				optType = WebConstants.EDU_TYPE_1;// 1:（游戏平台-》主帐号）
				payType = WebConstants.EDU_TYPE_1;
				flatName = fromFlatName;
			}
			WebEdu webEdu = getEduForward(payType, flatName);
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(uc.getUserName());
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
		if (StringUtils.equalsIgnoreCase(WebConstants.EDU_TYPE_2, payType)) {//从主账号转出
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
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_AB)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_AB_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_AB_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_OG)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_OG_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_OG_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_OS)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_OS_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_OS_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_SB)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_SB_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_SB_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_GD)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_GD_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_GD_2);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_TTG)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_TTG_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_TTG_2);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_SBT)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_SBT_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_SBT_2);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_NEWNT)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_NEWNT_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_NEWNT_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_SA)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_SA_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_SA_2);
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_VG)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_VG_2);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_VG_2);
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
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_OS)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_OS_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_OS_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_SB)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_SB_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_SB_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_GD)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_GD_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_GD_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_TTG)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_TTG_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_TTG_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_SBT)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_SBT_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_SBT_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_NEWNT)) {
				tmp.setEduForward(WebConstants.EDU_FORWARD_NEWNT_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_NEWNT_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_SA)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_SA_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_SA_1);
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_VG)){
				tmp.setEduForward(WebConstants.EDU_FORWARD_VG_1);
				tmp.setEduForwardRemark(WebConstants.EDU_FORWARD_REMARK_VG_1);
			}
		}
		return tmp;
	}
	

}
