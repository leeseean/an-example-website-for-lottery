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
import com.mh.ifc.NMGIfcUtil;
import com.mh.ifc.http.ComEduConts;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.NMGConts;
import com.mh.ifc.http.NMgResResult;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.DateUtil;
import com.mh.service.FlatInterfaceService;
import com.mh.web.login.UserContext;
/**
 * MG电子
 * @author Administrator
 *
 */
@Service("mgElectronicInterfaceServiceImpl")
public class MgElectronicInterfaceServiceImpl extends BaseFlatInfo implements FlatInterfaceService{
	
	public String login(HttpServletRequest request) {
		try {
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
			UserContext uc = getUserContext(request);
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getMgUserName())) {
				webUserFlat = NMGIfcUtil.registNMgAccout(webUserFlat);
				if (StringUtils.isNotEmpty(webUserFlat.getMgUserName()) || StringUtils.isNotEmpty(webUserFlat.getMgId())) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}
			String gameName = request.getParameter(NMGConts.GAMENAME);
			String gameCode = request.getParameter(NMGConts.GAMECODE);

			Map<String, String> params = new HashMap<String, String>();
			params.put(NMGConts.USERNAME, webUserFlat.getMgUserName());// 游戏帐号
			params.put(NMGConts.PASSWORD, webUserFlat.getMgPassword());// 游戏帐号密码
			params.put(NMGConts.GAMEID, gameName);// 游戏名称
			params.put(NMGConts.GAMECODE, gameCode);
			params.put(NMGConts.IP, IPSeeker.getIpAddress2(request));//IP
			
			params.put(NMGConts.LOBBY_URL, basePath);// 游戏帐号
			params.put(NMGConts.LOGOUTREDIRECT_URL, basePath);// 游戏帐号密码
			params.put(NMGConts.BANKING_URL, basePath);//IP

			NMgResResult result = NMGIfcUtil.loginelectronic(params);
			return result.getLoginUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public double searchUserBlance(WebUserFlat webUserFlat) throws Exception {
		double blance = 0;
		Map<String,String> params = new HashMap<String,String>();
		//注册
		if (StringUtils.isEmpty(webUserFlat.getMgUserName())) {
			webUserFlat = NMGIfcUtil.registNMgAccout(webUserFlat);
			if (StringUtils.isNotEmpty(webUserFlat.getMgUserName()) || StringUtils.isNotEmpty(webUserFlat.getMgId())) {
				webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
			}
		}
		//查余额
		params.put(NMGConts.USERID, webUserFlat.getMgId());
		NMgResResult result = NMGIfcUtil.balance(params);
		if (result != null && !StringUtils.isEmpty(result.getBlance())) {
			blance = Double.valueOf(result.getBlance());
		}
		return blance;
	}

	public String updateEdu(WebUserFlat webUserFlat, WebUser webUser,String flatName, String optType, int points, WebEdu webEdu) throws RuntimeException {
		String tsMsg = "";
		Integer eduForward = webEdu.getEduForward();
		String eduForwardRemark = webEdu.getEduForwardRemark();
		if(StringUtils.isEmpty(webUserFlat.getMgUserName()) || StringUtils.isEmpty(webUserFlat.getMgId())){
			 try {
				webUserFlat =  NMGIfcUtil.registNMgAccout(webUserFlat);
				if(StringUtils.isNotEmpty(webUserFlat.getMgUserName())&&StringUtils.isNotEmpty(webUserFlat.getMgId())){
					 webUserFlatDao.update(webUserFlat);
					 tsMsg = updateMgAccountPonit(webUser, flatName, webUserFlat.getMgUserName(), webUserFlat.getMgPassword(), optType, points, eduForward, eduForwardRemark);
				}else{
					 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }else{
			 tsMsg = updateMgAccountPonit(webUser, flatName, webUserFlat.getMgUserName(), webUserFlat.getMgPassword(), optType, points, eduForward, eduForwardRemark);
		 }
		return tsMsg;
	}
	
	private String updateMgAccountPonit(WebUser webUser,String flatName,String flatAccount, String password, String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = mgDepositOpt(webUser,flatName,optType, flatAccount, password, points,eduForward, eduForwardRemark);
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
		NMgResResult result = null;
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put(NMGConts.USERNAME, flatAccount);
			params.put(NMGConts.PASSWORD, password);
			params.put(NMGConts.AMOUNT, String.valueOf(points));
			params.put(NMGConts.REFNO, eduOrder);
			params.put(NMGConts.IP, "127.0.0.1");
			params.put(NMGConts.WEBFLAG, userflat);
			result = NMGIfcUtil.withdrawal(params);
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
			throw new RuntimeException( "MG帐号添加额度异常");// 异常
		}
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!=null && NMGConts.RES_CODE_0.equals(result.getStatusCode())){//成功
				int rows = webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				addAccountRecord(webUser, flatName, optType,-points, eduOrder, eduForwardRemark, gmt_4_date);;
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getStatusCode())){
					msg = result.getErrorMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
				
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("MG额度转换失败");// 异常
		}
		return msg;
	}
	
	
	private String mgDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, String password, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
		String logStrPrefix = eduForwardRemark+"额度操作主帐号" + webUser.getUserName() + " 转入金额" + points;
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		int eduPoints = points;
		String userflat = flatAccount.substring(0, 2);
		
		/**请求API 创建EDU记录**/
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(ComEduConts.EDUORDER, eduOrder);
		jsonObj.put(ComEduConts.USERNAME, webUser.getUserName());
		jsonObj.put(ComEduConts.EDUPOINTS, eduPoints+"");
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
			throw new RuntimeException("调用API创建edu记录失败");// 异常
		}

 
		// 减少用户余额
		double userMoney = webUser.getUserMoney().doubleValue() - points;
		webUser.setUserMoney(userMoney);
		int rows = webUserDao.updateWebUserForMoney(webUser.getUserName(),-points);
		if(rows<1){
			logger.error("更新用户额度失败！");
			throw new RuntimeException("更新用户额度失败");// 异常
		}
		try {
			
			//入款记录
			addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			addAccountRecord(webUser, flatName, optType,eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			NMgResResult result = null;
			Map<String, String> params = new HashMap<String, String>();
			params.put(NMGConts.USERNAME, flatAccount);
			params.put(NMGConts.AMOUNT, String.valueOf(points));
			params.put(NMGConts.PASSWORD, password);
			params.put(ComEduConts.EDUORDER, eduOrder);
			params.put(NMGConts.REFNO, eduOrder);
			params.put(NMGConts.WEBFLAG, userflat);
			params.put(NMGConts.IP, "127.0.0.1");

			result = NMGIfcUtil.deposit(params);
			
			if (result!= null && NMGConts.RES_CODE_0.equals(result.getStatusCode())) {
				webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				rows = webUserDao.updateWebUserForMoney(webUser.getUserName(),eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				
				if(result!=null && Conts.RESULT_9911000.equals(result.getStatusCode())){
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
			throw new RuntimeException("MG额度转化失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}

	public ModelAndView mobileLogin(HttpServletRequest request,HttpServletResponse response) {
		String domain = this.getWebDomain(request) + "m/main?code=slot_mg";
		NMgResResult result = null;
		try{
			UserContext uc = this.getUserContext(request);
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getMgUserName())) {
				webUserFlat = NMGIfcUtil.registNMgAccout(webUserFlat);
				if (StringUtils.isNotEmpty(webUserFlat.getMgUserName()) || StringUtils.isNotEmpty(webUserFlat.getMgId())) {
					this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				}else{
					sendErrorMsg(response,"【MG平台注册用户失败】",domain);
					return null;
				}
			}
			String gameName = request.getParameter(NMGConts.GAMENAME);
			String gameCode = request.getParameter(NMGConts.GAMECODE);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(NMGConts.USERNAME, webUserFlat.getMgUserName());// 游戏帐号
			params.put(NMGConts.PASSWORD, webUserFlat.getMgPassword());// 游戏帐号密码
			params.put(NMGConts.GAMEID, gameName);// 游戏名称
			params.put(NMGConts.IP, IPSeeker.getIpAddress2(request));//IP
			params.put(NMGConts.GAMECODE, gameCode);
			
			params.put(NMGConts.LOBBY_URL, domain);// 游戏帐号
			params.put(NMGConts.LOGOUTREDIRECT_URL, domain);// 游戏帐号密码
			params.put(NMGConts.BANKING_URL, domain);//IP
			
			result = NMGIfcUtil.loginelectronicH5(params);
			if(null == result){
				sendErrorMsg(response,"【MG】平台登录失败",domain);
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			sendErrorMsg(response,"【MG】平台登录失败",domain);
		}
		return new ModelAndView("m/mg/mg").addObject("url", result.getLoginUrl());
	}
}
