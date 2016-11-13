package com.mh.service.impl.game;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.mh.client.BaseFlatInfo;
import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.HGIfcUtil;
import com.mh.ifc.http.ComEduConts;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.HgConts;
import com.mh.ifc.http.HgResResult;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.DateUtil;
import com.mh.service.FlatInterfaceService;
import com.mh.web.login.UserContext;
/**
 * HG
 * @author Administrator
 *
 */
@Service("hgInterfaceServiceImpl")
public class HgInterfaceServiceImpl extends BaseFlatInfo implements FlatInterfaceService {
	
	public String login(HttpServletRequest request) {
		try {

			UserContext uc = getUserContext(request);
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getHgUserName())) {
				webUserFlat = HGIfcUtil.registHgAccout(webUserFlat);
				if (webUserFlat.getHgStatus() != null && webUserFlat.getHgStatus().intValue() == 1) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}

			Map<String, String> params = new HashMap<String, String>();
			JSONObject jsonObj = new JSONObject();

			jsonObj.put(HgConts.USERNAME, webUserFlat.getHgUserName());// 游戏帐号
			jsonObj.put(HgConts.MODE, HgConts.LOGIN_MODE);
			jsonObj.put(HgConts.FIRSTNAME, webUserFlat.getUserName());
			jsonObj.put(HgConts.LASTNAME, webUserFlat.getHgUserName().substring(0, 2));
			jsonObj.put(HgConts.CURRENCYID, HgConts.CURRENCY_RMB);

			params.put("params", jsonObj.toString());
			HgResResult result = HGIfcUtil.login(params);
			if (result != null) {
				logger.info(result.getUrl());
				return result.getUrl();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public double searchUserBlance(WebUserFlat webUserFlat) throws Exception {
		double blance = 0;
		Map<String,String> params = new HashMap<String,String>();
		//注册
		if (StringUtils.isEmpty(webUserFlat.getHgUserName())) {
			webUserFlat = HGIfcUtil.registHgAccout(webUserFlat);
			if (webUserFlat.getHgStatus() != null && webUserFlat.getHgStatus().intValue() == 1) {
				webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
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
		return blance;
	}

	public String updateEdu(WebUserFlat webUserFlat, WebUser webUser,String flatName, String optType, int points, WebEdu webEdu) throws RuntimeException {
		String tsMsg = "";
		Integer eduForward = webEdu.getEduForward();
		String eduForwardRemark = webEdu.getEduForwardRemark();
		if(StringUtils.isEmpty(webUserFlat.getHgUserName())){
			 try {
				webUserFlat =  HGIfcUtil.registHgAccout(webUserFlat);
				if(webUserFlat.getHgStatus()!= null && webUserFlat.getHgStatus().intValue()==1){
					 webUserFlatDao.update(webUserFlat);
					 tsMsg = updateHgAccountPonit(webUser, flatName, webUserFlat.getHgUserName(), optType, points, eduForward, eduForwardRemark);
				 }else{
					 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }else{
			 tsMsg  =updateHgAccountPonit(webUser, flatName, webUserFlat.getHgUserName(), optType, points, eduForward, eduForwardRemark);
		 }
		return tsMsg;
	}
	
	private String updateHgAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = hgDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
			return msg;
		}
		
		/**请求API 创建EDU记录**/
		JSONObject eduObj = new JSONObject();
		eduObj.put(ComEduConts.EDUORDER, eduOrder);
		eduObj.put(ComEduConts.USERNAME, webUser.getUserName());
		eduObj.put(ComEduConts.EDUPOINTS, -points+"");
		eduObj.put(ComEduConts.EDUTYPE, WebConstants.EDU_TYPE_1);
		eduObj.put(ComEduConts.EDUSTATUS, "-1");
		eduObj.put(ComEduConts.EDUIP, "0.0.0.0");
		eduObj.put(ComEduConts.EDUFORWARD, eduForward+"");
		eduObj.put(ComEduConts.EDUFORWARDREMARK, eduForwardRemark);
		eduObj.put(ComEduConts.WEBFLAG, userflat);
		eduObj.put(ComEduConts.FLATNAME,flatName);
		eduObj.put(ComEduConts.FLATUSERNAME,flatAccount);
		
		Map<String,String> paramMap= new HashMap<String,String>();
		paramMap.put("params", eduObj.toString());
		boolean eduResult = optInterfaceEdu(paramMap);
		if(!eduResult){
			throw new RuntimeException("调用API创建edu记录失败");// 异常
		}
		
		//转出（游戏平-》台主帐号）
		HgResResult result = null;
		try{
			//存款预热
			String webUserFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
			Map<String, String> params = new HashMap<String, String>();
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(HgConts.USERNAME,flatAccount);
			jsonObj.put(HgConts.MODE, HgConts.LOGIN_MODE);
			jsonObj.put(HgConts.CURRENCYID, HgConts.CURRENCY_RMB);
			jsonObj.put(HgConts.AMOUNT, String.valueOf(points));
			jsonObj.put(HgConts.REFNO, eduOrder);
			jsonObj.put(HgConts.WEBFLAG, webUserFlag);
			params.put("params", jsonObj.toString());
			result = HGIfcUtil.withdrawal(params);
			
			if(result!= null && result.getStatus() ==HgConts.SUCCESS){//成功
				//存款确认
				jsonObj = new JSONObject();
				jsonObj.put(HgConts.STATUS,result.getStatus());
				jsonObj.put(HgConts.PAYMENTID, result.getPaymentid());
				jsonObj.put(HgConts.ERRDESC,result.getErrdesc());
				jsonObj.put(HgConts.USERNAME, flatAccount);
				jsonObj.put(HgConts.AMOUNT, String.valueOf(points));
				jsonObj.put(HgConts.REFNO,eduOrder);
				jsonObj.put(HgConts.WEBFLAG,webUserFlag);
				params.put("params", jsonObj.toString());
				result = HGIfcUtil.withdrawalConfirml(params);
			}
		}catch (Exception e) {
			addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg  = Conts.EDU_FAILURE;
			throw new RuntimeException("HG帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && result.getStatus() ==HgConts.SUCCESS){//成功
				int rows = webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_CREDIT_LACK == result.getStatus()){
					msg = result.getErrdesc();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("HG额度转换失败");// 异常
		}
		return msg;
	}
	
	private String hgDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
		String logStrPrefix = eduForwardRemark+"额度操作主帐号" + webUser.getUserName() + " 转入金额" + points;
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		int eduPoints = points;
		String userflat = flatAccount.substring(0, 2);
		
		/**请求API 创建EDU记录**/
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(ComEduConts.EDUORDER, eduOrder);
		jsonObj.put(ComEduConts.USERNAME, webUser.getUserName());
		jsonObj.put(ComEduConts.EDUPOINTS, eduPoints);
		jsonObj.put(ComEduConts.EDUTYPE, "2");
		jsonObj.put(ComEduConts.EDUSTATUS, "-1");
		jsonObj.put(ComEduConts.EDUIP, "0.0.0.0");
		jsonObj.put(ComEduConts.EDUFORWARD, eduForward+"");
		jsonObj.put(ComEduConts.EDUFORWARDREMARK, eduForwardRemark);
		jsonObj.put(ComEduConts.WEBFLAG, userflat);
		jsonObj.put(ComEduConts.FLATNAME,flatName);
		jsonObj.put(ComEduConts.FLATUSERNAME,flatAccount);
		
		Map<String,String> paramMap= new HashMap<String,String>();
		paramMap.put("params", jsonObj.toString());
		boolean eduResult = optInterfaceEdu(paramMap);
		if(!eduResult){
			logger.info("调用API创建edu记录失败");
			throw new RuntimeException("调用API创建edu记录失败");// 异常
		}
 
		// 减少用户余额
		double userMoney = webUser.getUserMoney().doubleValue() - points;
		webUser.setUserMoney(userMoney);
		int rows = webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
		if(rows<1){
			logger.error("更新用户额度失败！");
			throw new RuntimeException("更新用户额度失败");// 异常
		}
		try {
			
			//入款记录
			addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			HgResResult result = null;
			// 预备转帐
			Map<String, String> params = new HashMap<String, String>();
			jsonObj = new JSONObject();
			jsonObj.put(HgConts.USERNAME, flatAccount);
			jsonObj.put(HgConts.MODE, HgConts.LOGIN_MODE);
			jsonObj.put(HgConts.CURRENCYID, HgConts.CURRENCY_RMB);
			jsonObj.put(HgConts.AMOUNT, String.valueOf(points));
			jsonObj.put(HgConts.REFNO, eduOrder);
			jsonObj.put(HgConts.WEBFLAG,userflat);
			params.put("params", jsonObj.toString());
			result = HGIfcUtil.deposit(params);
			if (result!= null && result.getStatus() == HgConts.RES_CODE_0) {
				//确认转账
				jsonObj = new JSONObject();
				jsonObj.put(HgConts.STATUS,result.getStatus());
				jsonObj.put(HgConts.PAYMENTID, result.getPaymentid());
				jsonObj.put(HgConts.ERRDESC,result.getErrdesc());
				jsonObj.put(HgConts.USERNAME, flatAccount);
				jsonObj.put(HgConts.AMOUNT, String.valueOf(points));
				jsonObj.put(HgConts.REFNO,eduOrder);
				jsonObj.put(HgConts.WEBFLAG, userflat);
				params.put("params", jsonObj.toString());
				
				result = HGIfcUtil.depositConfirm(params);
				
				if (result!= null && result.getStatus() == HgConts.RES_CODE_0) {
					webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
					return Conts.EDU_SUCCESS;
				}
				
			} 
			/** 主帐号增加额度 **/
			userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
			webUser.setUserMoney(userMoney);
			
			rows = webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
			if(rows<1){
				logger.error("更新用户额度失败！");
				return Conts.EDU_FAILURE;
			}
			
			//财务记录
			addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
			webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
		
			if(result!=null && Conts.RESULT_CREDIT_LACK == result.getStatus()){
				return result.getErrdesc();
			}
		}catch (Exception e) {
			logger.error(logStrPrefix + "异常!",e);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException("HG入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}

	public ModelAndView mobileLogin(HttpServletRequest request, HttpServletResponse response) {
		String domain = this.getWebDomain(request) + "m/main";
		try {

			UserContext uc = getUserContext(request);
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getHgUserName())) {
				webUserFlat = HGIfcUtil.registHgAccout(webUserFlat);
				if (webUserFlat.getHgStatus() != null && webUserFlat.getHgStatus().intValue() == 1) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					sendErrorMsg(response,"【HG平台注册用户失败】",domain);
					return null;
				}
			}

			Map<String, String> params = new HashMap<String, String>();
			JSONObject jsonObj = new JSONObject();

			jsonObj.put(HgConts.USERNAME, webUserFlat.getHgUserName());// 游戏帐号
			jsonObj.put(HgConts.MODE, HgConts.LOGIN_MODE);
			jsonObj.put(HgConts.FIRSTNAME, webUserFlat.getUserName());
			jsonObj.put(HgConts.LASTNAME, webUserFlat.getHgUserName().substring(0, 2));
			jsonObj.put(HgConts.CURRENCYID, HgConts.CURRENCY_RMB);

			params.put("params", jsonObj.toString());
			params.put(HgConts.ISMOBILE, "mobile");
			HgResResult result = HGIfcUtil.login(params);
			if (result != null) {
				logger.info(result.getUrl());
				return new ModelAndView("m/mg/mg").addObject("url", result.getUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			sendErrorMsg(response,"【HG】平台登录失败",domain);
		}
		return null;
	}
}
