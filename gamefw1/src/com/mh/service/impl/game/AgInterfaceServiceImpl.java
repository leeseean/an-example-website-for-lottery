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
import com.mh.ifc.AGIfcUtil;
import com.mh.ifc.http.AgConts;
import com.mh.ifc.http.AgResResult;
import com.mh.ifc.http.ComEduConts;
import com.mh.ifc.http.Conts;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.DateUtil;
import com.mh.service.FlatInterfaceService;
import com.mh.web.login.UserContext;
/**
 * AG
 * @author Administrator
 *
 */
@Service("agInterfaceServiceImpl")
public class AgInterfaceServiceImpl extends BaseFlatInfo implements FlatInterfaceService {
	
	public String login(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		try {
			UserContext uc = getUserContext(request);
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());

			if (StringUtils.isEmpty(webUserFlat.getAgUserName())) {
				webUserFlat = AGIfcUtil.registAgAccout(webUserFlat);
				if (webUserFlat.getAgStatus() != null && webUserFlat.getAgStatus().intValue() == 1) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}
			String agGameType = request.getParameter("agGameType");
			String actype = request.getParameter(AgConts.ACTYPE);
			if (StringUtils.isBlank(actype)) {
				actype = AgConts.ACTYPE_1;
			}

			Map<String, String> params = new HashMap<String, String>();
			params.put(AgConts.LOGINNAME, webUserFlat.getAgUserName());// 游戏帐号
			params.put(AgConts.PASSWORD, webUserFlat.getAgPassword());// 密码
			params.put(AgConts.GAMETYPE, agGameType);
			params.put(AgConts.ACTYPE, actype);
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
	
	public double searchUserBlance(WebUserFlat webUserFlat) throws Exception {
		double blance = 0;
		Map<String,String> params = new HashMap<String,String>();
		//注册
		if (StringUtils.isEmpty(webUserFlat.getAgUserName())) {
			webUserFlat = AGIfcUtil.registAgAccout(webUserFlat);
			if (webUserFlat.getAgStatus() != null && webUserFlat.getAgStatus().intValue() == 1) {
				webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
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
		return blance;
	}

	public String updateEdu(WebUserFlat webUserFlat, WebUser webUser,String flatName, String optType, int points, WebEdu webEdu) throws RuntimeException {
		String tsMsg = "";
		Integer eduForward = webEdu.getEduForward();
		String eduForwardRemark = webEdu.getEduForwardRemark();
		if(StringUtils.isEmpty(webUserFlat.getAgUserName())){
			 try {
				webUserFlat =  AGIfcUtil.registAgAccout(webUserFlat);
				if(webUserFlat.getAgStatus()!=null && webUserFlat.getAgStatus().intValue()==1){
					 webUserFlatDao.update(webUserFlat);
					 tsMsg  = updateAgAccountPonit(webUser, flatName, webUserFlat.getAgUserName(),webUserFlat.getAgPassword(), optType, points, eduForward, eduForwardRemark);
				 }else{
					 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
				 }
			 } catch (Exception e) {
				e.printStackTrace();
			 }
		 }else{
			 tsMsg  =updateAgAccountPonit(webUser, flatName, webUserFlat.getAgUserName(),webUserFlat.getAgPassword(), optType, points, eduForward, eduForwardRemark);
		 }
		return tsMsg;
	}
	
	private String updateAgAccountPonit(WebUser webUser,String flatName,String flatAccount,String flatPassword,String optType,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean ag_flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = agDepositOpt(webUser,flatName,optType, flatAccount,flatPassword, points,eduForward, eduForwardRemark);
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
		AgResResult result = null;
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put(AgConts.LOGINNAME, flatAccount);
			params.put(AgConts.PASSWORD, flatPassword);
			params.put(AgConts.ACTYPE, AgConts.ACTYPE_1);// 真钱帐号
			params.put(AgConts.TYPE, StringUtils.equals(optType, WebConstants.EDU_TYPE_2) ? AgConts.TYPE_IN : AgConts.TYPE_OUT);
			params.put(AgConts.BILLNO, eduOrder);
			params.put(AgConts.WEBFLAG, CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
			params.put(AgConts.CREDIT, String.valueOf(points));
			
			result = AGIfcUtil.agAgentEdu(params);// 预备转帐
			
			String flag = AgConts.FLAG_0;
			if (!StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())) {
				return Conts.EDU_FAILURE;
			}
		 
			flag = AgConts.FLAG_1;
			params.put(AgConts.FLAG, flag);
			params.put(AgConts.WEBFLAG, userflat);
			result = AGIfcUtil.agAgentEduConfirm(params);// 确认转帐
		}catch (Exception e) {
			addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ag_flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException( "AG帐号添加额度异常");// 异常
		}
		
		if(ag_flag){ //return error log
		     return msg;
		}
		
		try{
			if(StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())){//成功
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
				if(StringUtils.equalsIgnoreCase(Conts.RESULT_9911000, result.getInfo())){
					msg = result.getMsg();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("AG额度转换失败");// 异常
		}
		return msg;
	}
	
	private String agDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount,String flatPassword, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
		String logStrPrefix = eduForwardRemark+"额度操作主帐号" + webUser.getUserName() + " 转入金额" + points;
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		int eduPoints = points;
		String userflat = flatAccount.substring(0, 2);
		
		AgResResult result = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put(AgConts.LOGINNAME, flatAccount);
		params.put(AgConts.PASSWORD, flatPassword);
		params.put(AgConts.ACTYPE, AgConts.ACTYPE_1);// 真钱帐号
		params.put(AgConts.TYPE, AgConts.TYPE_IN);
		params.put(AgConts.BILLNO, eduOrder);

		params.put(AgConts.CREDIT, String.valueOf(points));
		params.put(AgConts.WEBFLAG, userflat);
		String flag = AgConts.FLAG_0;
	 
		result = AGIfcUtil.agAgentEdu(params);// 预备转帐
		if (!StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())) {
			return Conts.EDU_FAILURE;
		}
	 
		flag = AgConts.FLAG_1;
		
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
			
			params.put(AgConts.FLAG, flag);
			result = AGIfcUtil.agAgentEduConfirm(params);// 确认转帐
			
			if (result!= null && StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())) {
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
				if(result!=null && StringUtils.equalsIgnoreCase(Conts.RESULT_9911000, result.getInfo())){
					return result.getMsg();
				}
			}
		}catch (Exception e) {
			logger.error(logStrPrefix + "异常!",e);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException("AG额度转化失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}

	public ModelAndView mobileLogin(HttpServletRequest request,HttpServletResponse response) {
		AgResResult result = null;
		String returnUrl = this.getWebDomain(request) + "m/main?code=slot_ag";
		try{
			UserContext uc = this.getUserContext(request);
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getAgUserName())) {
				webUserFlat = AGIfcUtil.registAgAccout(webUserFlat);
				if (webUserFlat.getAgStatus() != null && webUserFlat.getAgStatus().intValue() == 1) {
					this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				}else{
					sendErrorMsg(response,"【AG平台注册用户失败】",returnUrl);
					return null;
				}
			}
			String agGameType = request.getParameter("agGameType");
			String actype = request.getParameter(AgConts.ACTYPE);
			if (StringUtils.isBlank(actype)){
				actype = AgConts.ACTYPE_1;
			} 

			Map<String, String> params = new HashMap<String, String>();
			params.put(AgConts.LOGINNAME, webUserFlat.getAgUserName());// 游戏帐号
			params.put(AgConts.PASSWORD, webUserFlat.getAgPassword());// 密码
			params.put(AgConts.GAMETYPE, agGameType);
			params.put(AgConts.ACTYPE, actype);
			params.put(AgConts.DM, basePath);
			params.put(AgConts.ODDTYPE, CommonConstant.resCommMap.get(CommonConstant.AG_ODD_TYPE));//
			params.put(AgConts.LANG, AgConts.LANG_ZH_CN);//
			params.put(AgConts.SID, ComUtil.getAGBillno());//
			result = AGIfcUtil.login(params);
			if(null == result){
				sendErrorMsg(response,"【AG】平台登录失败",returnUrl);
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			sendErrorMsg(response,"【用户登录失败】",returnUrl);
		}
		return new ModelAndView("/m/ag/ag").addObject("url", result.getInfo());
	}
}
