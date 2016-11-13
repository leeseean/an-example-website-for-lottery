package com.mh.web.controller.m;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.constants.WebConstants;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.AesUtil;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.TWebBankHuikuan;
import com.mh.entity.TWebThirdPay;
import com.mh.entity.TWebThirdPayKj;
import com.mh.entity.WebBank;
import com.mh.entity.WebKjPay;
import com.mh.entity.WebMessage;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserType;
import com.mh.service.PayCenterService;
import com.mh.service.WebBankHuikuanService;
import com.mh.service.WebKjPayService;
import com.mh.service.WebMessageService;
import com.mh.service.WebRecordService;
import com.mh.service.WebUserService;
import com.mh.service.WebUserTypeService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
import com.mh.web.util.CheckDeviceUtil;
@SuppressWarnings("all")
@Controller
@RequestMapping("/memberinterface")
public class MemberInterfaceController extends BaseController {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private WebUserService webUserService;

	@Autowired
	private WebUserTypeService webUserTypeService;

	@Autowired
	private WebMessageService webMessageService;

	@Autowired
	private WebBankHuikuanService webBankHuikuanService;

	@Autowired
	private WebKjPayService webPayPicService;

	@Autowired
	private WebRecordService webRecordService;
	
	@Autowired
	private PayCenterService payCenterService;

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getUserInfo")
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserContext uc = this.getUserContext(request);

			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName()); // 获取会员信息
			WebUserType userType = webUserTypeService.getUserTypeById(webUser.getUserType());// 获取会员等级
			webUser.setUserTypeLevel(userType.getTypeLevel());
			webUser.setUserPassword(null);// 清空密码
			webUser.setUserWithdrawPassword(null);// 清空提款密码
			webUser.setId(null);
			parseUser(webUser);
			ServletUtils.outPrintSuccess(request, response, "", webUser);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取用户信息失败");
		}
	}

	/**
	 * 获取用户消息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getUserMessage")
	public void getUserMessage(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");// 1:系统消息 2:用户消息
		try {
			UserContext uc = this.getUserContext(request);

			WebMessage msg = new WebMessage();
			msg.setMsgType(Integer.parseInt(code));
			if (StringUtils.equals(code, "2")) {// 系统消息不需要用户名
				msg.setUserName(uc.getUserName());
			}
			List<WebMessage> list = this.webMessageService.getMessageList(msg);
			ServletUtils.outPrintSuccess(request, response, "", list);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取用户消息失败");
		}
	}

	/**
	 * 获取用户消息详情
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getUserMessageDetail")
	public void getUserMessageDetail(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");// 消息id
		try {
			WebMessage msg = this.webMessageService.findMessageContent(Integer.parseInt(id));
			if (null != msg) {
				this.webMessageService.updateUserMsgStatus(msg.getId());
				ServletUtils.outPrintSuccess(request, response, "", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取用户消息失败");
		}
	}

	/**
	 * 获取入款账户列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getBankList")
	public void getBankList(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			List<WebBank> bankList = this.webBankHuikuanService.getWebBankList(webUser.getUserType());
			ServletUtils.outPrintSuccess(request, response, "", bankList);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取入款账户列表失败");
		}
	}

	/**
	 * 第三方支付信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getThreePayInfo")
	public void getThreePayInfo(HttpServletRequest request, HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
		String payType = request.getParameter("payType");
		try {
			WebKjPay webKjPay = this.webPayPicService.getKjPay(Integer.parseInt(payType),webUser.getUserType());
			webKjPay.setImg(null);
			ServletUtils.outPrintSuccess(request, response, "", webKjPay);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取第三方支付信息失败");
		}
	}
	
	/**
	 * 在线支付
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/onlinekjPay")
	public void kjPayInfo(HttpServletRequest request, HttpServletResponse response) {
		UserContext userContext = getUserContext(request);
		WebUser webuser = payCenterService.findWebrUseByUserName(userContext.getUserName());
		String paytype = request.getParameter("payType");
		try {
			TWebThirdPayKj thirdPayKj = payCenterService.getTWebThirdPayKj(Integer.parseInt(paytype),webuser.getUserType());
			ServletUtils.outPrintSuccess(request, response, "", thirdPayKj);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取第三方支付信息失败");
		}
	}
	
	/**
	 * 在线支付详情
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/payInfo")
	public void payInfo(HttpServletRequest request, HttpServletResponse response) {
		UserContext userContext = getUserContext(request);
		WebUser webuser = payCenterService.findWebrUseByUserName(userContext.getUserName());
		String payType = request.getParameter("payType");
		List<TWebThirdPayKj> thirdPayKj = new ArrayList<TWebThirdPayKj>();
		try {
			//TWebThirdPay pay = this.payCenterService.findTWebThirdPayById(Integer.parseInt(payid));
			TWebThirdPayKj pay = this.payCenterService.getTWebThirdPayKj(Integer.parseInt(payType), webuser.getUserType());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("thirdMaxEdu", pay.getPayMaxEdu());
			map.put("thirdMinEdu", pay.getPayMinEdu());
			map.put("payid", pay.getId());
			ServletUtils.outPrintSuccess(request, response, "", map);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取第三方支付信息失败");
		}
	}

	/**
	 * 获取入款记录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getDepositRecord")
	public void getDepositRecord(HttpServletRequest request, HttpServletResponse response) {
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String hkType = request.getParameter("withdrawType");
		try {
			Date date = new Date();
			if (StringUtils.isBlank(beginTime)) {
				beginTime = sdf.format(date);
			}
			if (StringUtils.isBlank(endTime)) {
				endTime = sdf.format(date);
			}

			UserContext uc = this.getUserContext(request);
			WebRecords rd = new WebRecords();
			rd.setUserName(uc.getUserName());
			rd.setStartTime(beginTime);
			rd.setEndTime(endTime);
			rd.setHkModel(hkType);
			// rd.setCount(100);
			rd.setCode("payHistory");
			Page page = this.newPage(request);
			page = webRecordService.findFinancePage(page, rd);
			// page = webRecordService.getHuiKuan(page, rd);
			if (CollectionUtils.isNotEmpty(page.getResult())) {
				List result = page.getResult();
				for (Object object : result) {
					Map<Object, Object> map = (Map<Object, Object>) object;
					String company = (String) map.get("hk_company_bank");
					if (StringUtils.isNotEmpty(company)) {
						map.put("hk_company_bank", company.trim());
					}
				}
			}
			ServletUtils.outPrintSuccess(request, response, "", page);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取入款记录失败");
		}
	}

	/**
	 * 获取出款记录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getwithdrawRecord")
	public void getwithdrawRecord(HttpServletRequest request, HttpServletResponse response) {
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String withdrawType = request.getParameter("withdrawType");
		try {
			Date date = new Date();
			if (StringUtils.isBlank(beginTime)) {
				beginTime = sdf.format(date);
			}
			if (StringUtils.isBlank(endTime)) {
				endTime = sdf.format(date);
			}

			UserContext uc = this.getUserContext(request);
			WebRecords rd = new WebRecords();
			rd.setUserName(uc.getUserName());
			rd.setStartTime(beginTime);
			rd.setEndTime(endTime);
			rd.setWithdrawType(withdrawType);
			rd.setCode("withdrawHistory");
			// rd.setCount(100);
			Page page = this.newPage(request);
			page = webRecordService.findFinancePage(page, rd);
			// List<WebUserWithdraw> list =
			// webRecordService.getWithdrawList(rd);

			ServletUtils.outPrintSuccess(request, response, "", page);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取出款记录失败");
		}
	}

	/**
	 * 获取额度转换
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getEduRecord")
	public void getEduRecord(HttpServletRequest request, HttpServletResponse response) {
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String flatName = request.getParameter("flatName");
		String eduType = request.getParameter("eduType");
		try {
			Date date = new Date();
			if (StringUtils.isBlank(beginTime)) {
				beginTime = sdf.format(date);
			}
			if (StringUtils.isBlank(endTime)) {
				endTime = sdf.format(date);
			}

			UserContext uc = this.getUserContext(request);
			WebRecords record = new WebRecords();
			record.setUserName(uc.getUserName());
			record.setStartTime(beginTime);
			record.setEndTime(endTime);
			record.setFlatName(flatName);
			record.setEduType(eduType);
			record.setCode("eduHistory");

			Page page = this.newPage(request);
			page = webRecordService.findFinancePage(page, record);

			ServletUtils.outPrintSuccess(request, response, "", page);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取额度转换记录失败");
		}
	}

	/**
	 * 获取体育注单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getSportOrder")
	public void getSportOrder(HttpServletRequest request, HttpServletResponse response) {
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String sportType = request.getParameter("cateType");
		String status = request.getParameter("status");

		try {
			Date now = new Date();
			if (StringUtils.isBlank(beginTime)) {
				beginTime = sdf.format(now);
			}
			if (StringUtils.isBlank(endTime)) {
				endTime = sdf.format(now);
			}

			UserContext uc = this.getUserContext(request);
			WebRecords records = new WebRecords();
			records.setStartTime(beginTime);
			records.setEndTime(endTime);
			records.setUserName(uc.getUserName());
			records.setBetSportType(sportType);
			records.setStatus(status);

			Page page = this.newPage(request);
			page = this.webRecordService.findPageForSport(page, records);
			ServletUtils.outPrintSuccess(request, response, "", page);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取体育注单失败");
		}

	}

	/**
	 * 电子注单
	 */
	@RequestMapping("/getSlotOrder")
	public void getSlotOrder(HttpServletRequest request, HttpServletResponse response) {
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String slotType = request.getParameter("cateType") == null ? "ag" : request.getParameter("cateType");
		String status = request.getParameter("status");

		try {
			Date now = new Date();
			if (StringUtils.isBlank(beginTime)) {
				beginTime = sdf.format(now);
			}
			if (StringUtils.isBlank(endTime)) {
				endTime = sdf.format(now);
			}

			UserContext uc = this.getUserContext(request);
			WebRecords records = new WebRecords();
			records.setStartTime(beginTime);
			records.setEndTime(endTime);
			records.setUserName(uc.getUserName());
			records.setBetSportType(slotType);
			records.setBetStatus(status);
			Page page = this.newPage(request);
			page = this.webRecordService.findEleRecordPage(page, records);
			ServletUtils.outPrintSuccess(request, response, "", page);
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintFail(request, response, "获取电子注单失败");
		}
	}
	
	
	@RequestMapping("/doPayCenter")
	public void doPaykjCenterData(HttpServletRequest request, HttpServletResponse response){
		UserContext user=super.getUserContext(request);
		JSONObject json=new JSONObject();
		TWebBankHuikuan bankHk=new TWebBankHuikuan();
		Date now = new Date();
		DecimalFormat currentNumberFormat = new DecimalFormat("#0.00");
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pay_url="";
		String sendParams="";
		try {
			
			pay_url = "/payhc/payCenter_payHandleCenter.action";
			String billno = getOnliePayOrder();//订单编号
			String money=request.getParameter("money");//订单金额
			if(StringUtils.isBlank(money)){
				ServletUtils.outPrintFail(request, response, "无效的订单金额！");
				return;
			}
			
			String payType="";//支付商家区分标识
			String key="";//第三方支付对应key.
			String thirdCode="";//商户号
			String thirdSecode="";//附加商户code（附加商户唯一标识）
			String payId = request.getParameter("payid");
			if(StringUtils.isBlank(payId)){
				ServletUtils.outPrintFail(request, response, "无效的支付方式！");
				return;
			}
			WebUser webuser = payCenterService.findWebrUseByUserName(user.getUserName());
			TWebThirdPay pay=this.payCenterService.findTWebThirdPayById(Integer.parseInt(payId));
			
			TWebThirdPayKj kjpay = this.payCenterService.getTWebThirdPayKj(Integer.parseInt(request.getParameter("payType")), webuser.getUserType());
			if(kjpay != null){
				if(Double.parseDouble(money) - kjpay.getPayMinEdu() < 0.0 || kjpay.getPayMaxEdu() - Double.parseDouble(money) < 0.0){
					ServletUtils.outPrintFail(request, response, "用户名:"+user.getUserName()+"非法支付方式，支付金额!");
					return;
				}
			}else{
				ServletUtils.outPrintFail(request, response, "无效的支付方式！");
				return;
			}
			
			
			key=pay.getThirdKey();
			thirdCode=pay.getThirdCode();
			pay_url=pay.getThirdUrl() + pay_url;
			payType=pay.getThirdType();
			
			
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
			
			String domain=basePath;//当前网站域名
			String amount = currentNumberFormat.format(Double.parseDouble(money));// 金额
			
			String remark = "订单[" + billno + "]金额" + amount + "提交时间" + f.format(now);// 备注
			String clientIp=IPSeeker.getIpAddress(request);
			/***组装订单信息入库 start***/
			bankHk=this.getBankHuikuanData(bankHk);
			bankHk.setPayNo(pay.getPayNo()==null?"":pay.getPayNo());
			bankHk.setHkOrder(billno);// 汇款编号和第三方付款一样
			bankHk.setUserName(user.getUserName());
			bankHk.setHkMoney(Double.valueOf(amount));
			bankHk.setHkIp(clientIp);
			bankHk.setRemark(remark);
			if(WebConstants.PAY_HUANXUN_TYPE.equals(payType)){//环讯支付
				bankHk.setHkCompanyBank("环讯支付(M)");
				bankHk.setHkType("环讯支付(M)");
				
			}else if(WebConstants.PAY_HUANXUNV304_TYPE.equals(payType)){
				bankHk.setHkCompanyBank("环讯支付V0.3.4(M)");
				bankHk.setHkType("环讯支付V0.3.4(M)");
				thirdSecode=pay.getThirdSecode();
				json.put("thirdSecode", thirdSecode);
				
			}else if(WebConstants.PAY_DINPAY_TYPE.equals(payType)){//快汇宝支付
				bankHk.setHkCompanyBank("快汇宝支付(M)");
				bankHk.setHkType("快汇宝支付(M)");
				String bank_code=request.getParameter("bank_code");
				json.put("bank_code", bank_code);
			}else if(WebConstants.PAY_MOBAO_TYPE.equals(payType)){//摩宝支付
				bankHk.setHkCompanyBank("摩宝支付(M)");
				bankHk.setHkType("摩汇宝支付(M)");
				String bank_code=request.getParameter("bank_code");
				json.put("bank_code", bank_code);
			}else if(WebConstants.PAY_BAOPAY_TYPE.equals(payType)){//宝付支付
				bankHk.setHkCompanyBank("宝付支付(M)");
				bankHk.setHkType("宝付支付(M)");
				String bank_code=request.getParameter("bank_code");
				json.put("bank_code", bank_code);
				thirdSecode=pay.getThirdSecode();
				json.put("thirdSecode", thirdSecode);
				
			}else if(WebConstants.PAY_YEEPAY_TYPE.equals(payType)){//宝付支付
				bankHk.setHkCompanyBank("易付支付(M)");
				bankHk.setHkType("易付支付(M)");
				String bank_code=request.getParameter("bank_code");
				String pd_FrpId=request.getParameter("pd_FrpId");
				String pa7_cardAmt=request.getParameter("pa7_cardAmt");
				String pd_Frpa8_cardNopId=request.getParameter("pa8_cardNo");
				String pa9_cardPwd=request.getParameter("pa9_cardPwd");
				json.put("bank_code", bank_code);
				json.put("pd_FrpId", pd_FrpId);
				json.put("pa7_cardAmt", pa7_cardAmt);
				json.put("pa8_cardNo", pd_Frpa8_cardNopId);
				json.put("pa9_cardPwd", pa9_cardPwd);
				
			}
			this.payCenterService.saveBankHuikuan(bankHk);
			/***组装订单信息入库 end***/
			String webMark=CommonConstant.resCommMap.get("web_pay_mark");
			String webKey=CommonConstant.resCommMap.get("web_pay_key");
			StringBuffer sbf=new StringBuffer();
			sbf.append(webMark);
			sbf.append(webKey);
			sbf.append(billno);
			sbf.append(amount);
			
			logger.info("==="+sbf.toString());
			String sign=DigestUtils.md5Hex(sbf.toString());
			
			json.put("sign",sign);
			json.put("webMark",webMark);
			json.put("billno", billno);//订单编号
			json.put("thirdCode", thirdCode);
			json.put("key", key);//第三方支付对应key.
			json.put("domain", domain);//当前网站域名
			json.put("amount", amount);//订单金额
			json.put("payType", payType);//支付类型，标识字段，区分支付商家
			json.put("clientIp", clientIp);
			json.put("userName", user.getUserName());
			json.put("remark", pay.getRemark() == null ? "" : pay.getRemark());
			
			
			String clientType = CheckDeviceUtil.checkDevice(request);
			if(!StringUtils.equals("pc", clientType)){
				domain += "/m/wap/member";
			}
			
			json.put("attachJson", billno+"_"+user.getUserName()+"_"+payId+"_"+domain);
			
			String choosePayType=request.getParameter("payValue");
			if(StringUtils.isNotBlank(choosePayType)){
				net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(choosePayType);
				String address = CheckDeviceUtil.checkDevice(request);
				if(StringUtils.equals("pc", address)){
					choosePayType = obj.getString("pc");
				}else{
					choosePayType = obj.getString("m");
				}
				json.put("bank_code", choosePayType);
			}
			
			logger.info(json.toString());
			sendParams=AesUtil.encrypt(json.toString());
			Map<String, String> map = new HashMap<String, String>();
			map.put("pay_url", pay_url);
			map.put("sendParams", sendParams);
			ServletUtils.outPrintSuccess(request, response, "支付成功", map);
		} catch (Exception e) {
			ServletUtils.outPrintFail(request, response, "支付异常！");
			e.printStackTrace();
		}
	}
	
	public TWebBankHuikuan getBankHuikuanData(TWebBankHuikuan bankHk){
		Date now = new Date();
//		DecimalFormat currentNumberFormat = new DecimalFormat("#0.00");
//		String amount = currentNumberFormat.format(money);// 金额

//		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String attach = "订单[" + billno + "]金额" + amount + "提交时间" + f.format(now);// 备注

		bankHk.setCreateTime(now);
		bankHk.setHkTime(now);

		bankHk.setHkStatus(WebConstants.T_WEB_BANK_HUIKUAN_STATUS_0);// 状态设为已入帐
		bankHk.setModifyTime(now);

		bankHk.setHkCheckStatus(WebConstants.T_WEB_BANK_HUIKUAN_CHECKED_STATUS_0);

		bankHk.setHkModel(WebConstants.T_WEB_BANK_HUIKUAN_MODEL_2);// 在线支付
		
		
		bankHk.setGmt4Time(DateUtil.getGMT_4_Date());

		bankHk.setPayDama(0);// 打码再次入款标识
		return bankHk;
	}

	private void parseUser(WebUser webUser) {
		/*String qq = webUser.getUserQq();
		String mail = webUser.getUserMail();*/
		String mobile = webUser.getUserMobile();
		/*String bankCard = webUser.getUserBankCard();
		String realName = webUser.getUserRealName();
		if (StringUtils.isNotBlank(mail)) {
			String inner = "";
			String[] mails = mail.split("@");
			int length = mails[0].length();
			switch (length) {
			case 2:
			case 3:
				inner = "*" + mails[0].substring(mails[0].length() - 1, mails[0].length());
				break;
			case 4:
			case 5:
			case 6:
				inner = "**" + mails[0].substring(mails[0].length() - 2, mails[0].length());
				break;
			case 7:
				inner = "****" + mails[0].substring(mails[0].length() - 3, mails[0].length());
				break;
			default:
				inner = "*****" + mails[0].substring(mails[0].length() - 4, mails[0].length());
				break;
			}
			webUser.setUserMail(inner + mails[1]);
		}
		if (StringUtils.isNotBlank(qq)) {
			webUser.setUserQq("****" + qq.substring(qq.length() - 4, qq.length()));
		}*/
		if (StringUtils.isNotBlank(mobile)) {
			webUser.setUserMobile("********" + mobile.substring(mobile.length() - 3, mobile.length()));
		}
		/*if (StringUtils.isNotBlank(bankCard)) {
			webUser.setUserBankCard("********" + bankCard.substring(bankCard.length() - 3, bankCard.length()));
		}
		if (StringUtils.isNotBlank(realName)) {
			webUser.setUserRealName("*" + realName.substring(realName.length() - 1, realName.length()));
		}*/
	}
	
	
	//点击充值，生成订单号
	public static String getOnliePayOrder() {
		SimpleDateFormat f = new SimpleDateFormat("yyMMddHHmmssSSS");
		return f.format(new Date());
	}
}
