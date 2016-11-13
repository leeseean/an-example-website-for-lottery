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
import com.mh.commons.conf.WebConstants;
import com.mh.commons.utils.IPSeeker;
import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.OSIfcUtil;
import com.mh.ifc.http.ComEduConts;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.OsConts;
import com.mh.ifc.http.OsResResult;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.DateUtil;
import com.mh.service.FlatInterfaceService;
import com.mh.web.login.UserContext;
/**
 * OS
 * @author Administrator
 *
 */
@Service("osInterfaceServiceImpl")
public class OsInterfaceServiceImpl extends BaseFlatInfo implements FlatInterfaceService {

	public String login(HttpServletRequest request) {
		try {

			UserContext uc = getUserContext(request);
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());

			if (StringUtils.isEmpty(webUserFlat.getOsUserName())) {
				webUserFlat = OSIfcUtil.registOsAccout(webUserFlat);
				if (webUserFlat.getOsStatus() != null && webUserFlat.getOsStatus().intValue() == 1) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}
			String gameCode = request.getParameter("gameName");
			Map<String, String> params = new HashMap<String, String>();
			params.put(OsConts.USERNAME, webUserFlat.getOsUserName());// 游戏帐号
			params.put(OsConts.IPADRESS, IPSeeker.getIpAddress(request));// 密码
			params.put(OsConts.GAMECODE, gameCode);
			params.put(OsConts.PLAYMODEL, OsConts.MODEL_REAL);//真实模式
			OsResResult result = OSIfcUtil.login(params);
			if (result != null) {
				return result.getUrl();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public double searchUserBlance(WebUserFlat webUserFlat) throws Exception {
		double blance = 0;
		Map<String,String> params = new HashMap<String,String>();
		//注册
		if (StringUtils.isEmpty(webUserFlat.getOsUserName())) {
			webUserFlat = OSIfcUtil.registOsAccout(webUserFlat);
			if (webUserFlat.getOsStatus() != null && webUserFlat.getOsStatus().intValue() == 1) {
				webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
			}
		}
		//查余额
		params.put(OsConts.USERNAME, webUserFlat.getOsUserName());
		OsResResult result = OSIfcUtil.balance(params);
		if (result != null && OsConts.RES_OK.equals(result.getErrorCode())) {
			blance =  result.getBalance().doubleValue();
		}
	
		return blance;
	}

	public String updateEdu(WebUserFlat webUserFlat, WebUser webUser,String flatName, String optType, int points, WebEdu webEdu) throws RuntimeException {
		String tsMsg = "";
		Integer eduForward = webEdu.getEduForward();
		String eduForwardRemark = webEdu.getEduForwardRemark();
		if(StringUtils.isEmpty(webUserFlat.getOsUserName())){
			 try {
				webUserFlat =  OSIfcUtil.registOsAccout(webUserFlat);
				if(webUserFlat.getOsStatus()!= null && webUserFlat.getOsStatus().intValue()==1){
					 webUserFlatDao.update(webUserFlat);
					 tsMsg = updateOsAccountPonit(webUser, flatName, webUserFlat.getOsUserName(), optType, points, eduForward, eduForwardRemark);
				 }else{
					 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 tsMsg = updateOsAccountPonit(webUser, flatName, webUserFlat.getOsUserName(), optType, points, eduForward, eduForwardRemark);
		 }
		return tsMsg;
	}
	
	private String updateOsAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = osDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
			return msg;
		}
		
		/**请求API 创建EDU记录**/
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(ComEduConts.EDUORDER, eduOrder);
		jsonObj.put(ComEduConts.USERNAME, webUser.getUserName());
		jsonObj.put(ComEduConts.EDUPOINTS, -points+"");
		jsonObj.put(ComEduConts.EDUTYPE, WebConstants.EDU_TYPE_1);
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
			throw new RuntimeException("调用API创建edu记录失败");// 异常
		}
		
		//转出（游戏平-》台主帐号）
		OsResResult result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put(OsConts.USERNAME,flatAccount);
			params.put(OsConts.AMOUNT, String.valueOf(points));
			params.put(OsConts.BILLNO, eduOrder);
			params.put(OsConts.WEBFLAG, userflat);
			
			result = OSIfcUtil.withdrawal(params);
			
		}catch (Exception e) {
			addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException("OS帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && OsConts.RES_OK.equals(result.getErrorCode())){//成功
				int rows = webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				//主帐号增加额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					msg = result.getErrorMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("OS额度转换失败");// 异常
		}
		return msg;
	}
	
private String osDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
		if(rows<0){
			logger.error("更新用户额度失败！");
			throw new RuntimeException("更新用户额度失败");// 异常
		}
		try {
			
			
			//入款记录
			addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			OsResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(OsConts.USERNAME,flatAccount);
			params.put(OsConts.AMOUNT, String.valueOf(points));
			params.put(OsConts.BILLNO, eduOrder);
			params.put(OsConts.WEBFLAG, userflat);
			result = OSIfcUtil.deposit(params);
			
			if (result!= null && OsConts.RES_OK.equals(result.getErrorCode())) {
				webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					return result.getErrorMessage();
				}
			}
		}catch (Exception e) {
			logger.error(logStrPrefix + "异常!",e);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException("DJ入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}

	public ModelAndView mobileLogin(HttpServletRequest request,HttpServletResponse response) {
		OsResResult result = null;
		String domain = this.getWebDomain(request) + "m/main?code=slot_os";
		try {
			UserContext uc = this.getUserContext(request);
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getOsUserName())) {
				webUserFlat = OSIfcUtil.registOsAccout(webUserFlat);
				if (webUserFlat.getOsStatus() != null && webUserFlat.getOsStatus().intValue() == 1) {
					this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					sendErrorMsg(response,"【OS平台注册用户失败】",domain);
					return null;
				}
			}
			String gameCode = request.getParameter("gameName");
			Map<String, String> params = new HashMap<String, String>();
			params.put(OsConts.USERNAME, webUserFlat.getOsUserName());// 游戏帐号
			params.put(OsConts.IPADRESS, IPSeeker.getIpAddress(request));// 密码
			params.put(OsConts.GAMECODE, gameCode);
			params.put(OsConts.PLAYMODEL, OsConts.MODEL_REAL);//真实模式
			
			params.put(OsConts.LOGIN_URL, this.getWebDomain(request) + "m/main");
			params.put(OsConts.ERROR_URL, this.getWebDomain(request) + "m/main");
			params.put(OsConts.BACK_URL, domain);
 
			if("xc_mrmonkeyslots".equals(gameCode)){
				params = new HashMap<String, String>();
				params.put(OsConts.USERNAME, webUserFlat.getOsUserName());// 游戏帐号
				params.put(OsConts.IPADRESS, IPSeeker.getIpAddress(request));// 密码
				params.put(OsConts.GAMECODE, gameCode);
				params.put(OsConts.PLAYMODEL, OsConts.MODEL_REAL);//真实模式
				result = OSIfcUtil.login(params);
			}else{
				result = OSIfcUtil.login_h5(params);
			}
			if (result == null) {
				sendErrorMsg(response,"【OS】平台登录失败",domain);
				return null;
			}
 
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorMsg(response,"【OS】平台登录失败",domain);
		}
		return new ModelAndView("redirect:"+result.getUrl());
	}
}
