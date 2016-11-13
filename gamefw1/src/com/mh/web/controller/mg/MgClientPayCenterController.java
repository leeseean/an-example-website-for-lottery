package com.mh.web.controller.mg;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.constants.WebConstants;
import com.mh.commons.utils.AesUtil;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.entity.TWebBankHuikuan;
import com.mh.entity.TWebThirdPay;
import com.mh.entity.WebUser;
import com.mh.service.PayCenterService;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;

@Controller
@RequestMapping("/mg/client")
public class MgClientPayCenterController extends BaseController{
	@Resource
	private PayCenterService payCenterService;
	
	@Autowired
	private WebUserService webUserService;
	
	private Map<String,String> orderMap = new HashMap<String,String>();
	
	
	/**
	 * 
	 * @Description: 点击线上支付，根据会员类型选择第三方支付商家
	 * @param    
	 * @return ModelAndView  
	 * @throws
	 * @author Andy
	 * @date 2015-6-7
	 */
	@RequestMapping("/paySelect")
	public ModelAndView paySelect(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap){
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser webUser = this.webUserService.findWebrUseByUserName(userName);
		ModelAndView mv = new ModelAndView();
		List<TWebThirdPay> list=new ArrayList<TWebThirdPay>();
		TWebThirdPay pay=new TWebThirdPay();
		WebUser webuser = payCenterService.findWebrUseByUserName(webUser.getUserName());
		list=this.payCenterService.findTWebThirdPay(webuser.getUserType());
		if(list.size()>0){
			pay=list.get(0);
		}else{
			logger.error("用户名:"+webUser.getUserName()+"该用户类型下，没有绑定在线支付方式!");
			throw new NullPointerException("用户名:"+webUser.getUserName()+"该用户类型下，没有绑定在线支付方式!");
		}
		String billno=getOnliePayOrder();//订单编号
		
		orderMap.clear();
		orderMap.put("billno", billno);
		
		modelMap.put("thirdPay", pay);
		modelMap.put("billno", billno);
		modelMap.put("user", webUser);
		modelMap.put("webuser", webuser);
		mv.setViewName("mg/pay/"+pay.getThirdType());
		return mv;
	}
	/**
	 * 
	 * @Description: 点击充值，组装数据，订单生成，信息入库，跳转支付中心支付
	 * @param    
	 * @return ModelAndView  
	 * @throws
	 * @author Andy
	 * @date 2015-6-7
	 */
	@RequestMapping("/doPayCenterData")
	public ModelAndView doPayCenterData(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap){
		String userName = (String)request.getSession().getAttribute("userName");
		WebUser user = this.webUserService.findWebrUseByUserName(userName);
		ModelAndView mv = new ModelAndView();
		JSONObject json=new JSONObject();
		TWebBankHuikuan bankHk=new TWebBankHuikuan();
		Date now = new Date();
		DecimalFormat currentNumberFormat = new DecimalFormat("#0.00");
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pay_url="";
		String sendParams="";
		try {
			
			pay_url=request.getParameter("pay_url");
			String billno=request.getParameter("billno");//订单编号
			String money=request.getParameter("money");//订单金额
			
			if(orderMap.get("billno")==null){
				logger.error("请求异常,非法篡改订单编号!");
				throw new NullPointerException("请求异常,非法篡改订单编号!!");
			}
			
			if(orderMap.get("billno")!=null && !orderMap.get("billno").equals(billno)){
				logger.error("请求异常,订单编号不正确!");
				throw new NullPointerException("请求异常,订单编号不正确!");
			}
			
			
			
			String payType="";//支付商家区分标识
			//String billno=getOnliePayOrder();//订单编号
			String key="";//第三方支付对应key.
			String thirdCode="";//商户号
			String thirdSecode="";//附加商户code（附加商户唯一标识）
			Integer payId=Integer.parseInt(request.getParameter("payId"));
			if(null==payId){
				logger.error("异常请求，没有对应支付方式，支付ID为空!");
				throw new NullPointerException("没有对应支付方式，支付ID为空!");
			}
			List<TWebThirdPay> list=new ArrayList<TWebThirdPay>();

			TWebThirdPay pay=new TWebThirdPay();
			list=this.payCenterService.findTWebThirdPay(user.getUserType());
			if(list.size()>0){
				pay=list.get(0);
				if(Double.parseDouble(money)-pay.getThirdMinEdu()<0.0 || pay.getThirdMaxEdu()-Double.parseDouble(money)<0.0){
					logger.error("用户名:"+user.getUserName()+"非法支付方式，支付金额!");
					throw new NullPointerException("用户名:"+user.getUserName()+"非法支付方式，支付金额!");
				}
			}else{
				logger.error("用户名:"+user.getUserName()+"该用户类型下，没有绑定在线支付方式!");
				throw new NullPointerException("用户名:"+user.getUserName()+"该用户类型下，没有绑定在线支付方式!");
			}
			
			
			key=pay.getThirdKey();
			thirdCode=pay.getThirdCode();
			pay_url=pay.getThirdUrl()+pay_url;
			payType=pay.getThirdType();
			
			
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
			logger.info("basePath==="+basePath);
			String domain=basePath;//当前网站域名
			String amount = currentNumberFormat.format(Double.parseDouble(money));// 金额
			
			String remark = "订单[" + billno + "]金额" + amount + "提交时间" + f.format(now);// 备注
			String clientIp=IPSeeker.getIpAddress(request);
			/***组装订单信息入库 start***/
			bankHk=this.getBankHuikuanData(bankHk);
			bankHk.setHkOrder(billno);// 汇款编号和第三方付款一样
			bankHk.setUserName(user.getUserName());
			bankHk.setHkMoney(Double.valueOf(amount));
			bankHk.setHkIp(clientIp);
			bankHk.setRemark(remark);
			if(WebConstants.PAY_HUANXUN_TYPE.equals(payType)){//环讯支付
				bankHk.setHkCompanyBank("环讯支付");
				bankHk.setHkType("环讯支付");
				
			}else if(WebConstants.PAY_DINPAY_TYPE.equals(payType)){//快汇宝支付
				bankHk.setHkCompanyBank("快汇宝支付");
				bankHk.setHkType("快汇宝支付");
				String bank_code=request.getParameter("bank_code");
				json.put("bank_code", bank_code);
				
			}else if(WebConstants.PAY_MOBAO_TYPE.equals(payType)){//摩宝支付
				bankHk.setHkCompanyBank("摩宝支付");
				bankHk.setHkType("摩汇宝支付");
				String bank_code=request.getParameter("bank_code");
				json.put("bank_code", bank_code);
				
			}else if(WebConstants.PAY_BAOPAY_TYPE.equals(payType)){//宝付支付
				bankHk.setHkCompanyBank("宝付支付");
				bankHk.setHkType("宝付支付");
				String bank_code=request.getParameter("bank_code");
				json.put("bank_code", bank_code);
				thirdSecode=pay.getThirdSecode();
				json.put("thirdSecode", thirdSecode);
				
			}else if(WebConstants.PAY_YEEPAY_TYPE.equals(payType)){//宝付支付
				bankHk.setHkCompanyBank("易付支付");
				bankHk.setHkType("易付支付");
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
			
			
			json.put("attachJson", billno+"_"+user.getUserName()+"_"+payId);
			
			sendParams=AesUtil.encrypt(json.toString());
			
		} catch (Exception e) {
			logger.error("支付中心支付异常，用户名:"+user.getUserName());
			e.printStackTrace();
		}
		modelMap.put("pay_url", pay_url);
		modelMap.put("sendParams", sendParams);
		mv.setViewName("mg/pay/goPayCenter");	
		return mv;
	}
	/**
	 * 
	 * @Description: 环讯支付信息基本参数
	 * @param    
	 * @return TWebBankHuikuan  
	 * @throws
	 * @author Andy
	 * @date 2015-6-8
	 */
	public static String getHuanxunPayParams(){
		
		return "";
	}
	/**
	 * 
	 * @Description: 快汇宝支付信息基本参数
	 * @param    
	 * @return TWebBankHuikuan  
	 * @throws
	 * @author Andy
	 * @date 2015-6-8
	 */
	public static String getdinPayParams(){
		
		return "";
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
	//点击充值，生成订单号
	public static String getOnliePayOrder() {
		SimpleDateFormat f = new SimpleDateFormat("yyMMddHHmmssSSS");
//		Random random = new Random();
		// int rannum = (int) (random.nextDouble() * (99 - 10 + 1)) + 10;//
		// 获取2位随机数
		return f.format(new Date());
	}
	public PayCenterService getPayCenterService() {
		return payCenterService;
	}
	public void setPayCenterService(PayCenterService payCenterService) {
		this.payCenterService = payCenterService;
	}
	
	/**
	 * 
	 * 方法描述:查看支付结果</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/checkPayResult")
	public ModelAndView checkPayResult(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		String billno=request.getParameter("billno");//订单编号
		TWebBankHuikuan bankHuikuan = this.payCenterService.loadTWebBankHuikuanForBillno(billno,WebConstants.T_WEB_BANK_HUIKUAN_STATUS_1);
		if(null != bankHuikuan && WebConstants.T_WEB_BANK_HUIKUAN_STATUS_1.equals(bankHuikuan.getHkStatus()) 
					&& WebConstants.T_WEB_USER_WITHDRAW_CHECKED_STATUS_1.equals(bankHuikuan.getHkCheckStatus())){
			
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc
					.getUserName());
			view.addObject("user", webUser);
			view.addObject("msg","支付成功！");
			view.addObject("success","1");
		}else{
			view.addObject("msg","正在处理中，请稍等片刻！");
			view.addObject("success","0");
		}
		view.setViewName("mg/pay/payResult");
		return view;
	}
	
}
