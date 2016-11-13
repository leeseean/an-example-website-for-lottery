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
import com.mh.commons.utils.SecurityEncode;
import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.NewPTIfcUtil;
import com.mh.ifc.http.ComEduConts;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.NewPtResResult;
import com.mh.ifc.http.NtConts;
import com.mh.ifc.http.PtConts;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.DateUtil;
import com.mh.service.FlatInterfaceService;
import com.mh.web.login.UserContext;
/**
 * PT
 * @author Administrator
 *
 */
@Service("ptInterfaceServiceImpl")
public class PtInterfaceServiceImpl extends BaseFlatInfo implements FlatInterfaceService {
	
	public String login(HttpServletRequest request) {
		StringBuffer buff = new StringBuffer("");
		String _p="";
		try {
			UserContext uc = getUserContext(request);
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getPtUserName())) {
				webUserFlat = NewPTIfcUtil.registPtAccout(webUserFlat);
				if (webUserFlat.getPtStatus() != null && webUserFlat.getPtStatus().intValue() == 1) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				}
			}
			String gameCode = request.getParameter("game");
			String ptUserName =  CommonConstant.resCommMap.get(CommonConstant.PT_BRAND_CODE)+"_"+webUserFlat.getPtUserName().toUpperCase();
			String ptPassword = webUserFlat.getPtPassword();
			buff.append(ptUserName);
			buff.append("|");
			buff.append(ptPassword);
			buff.append("|");
			buff.append(gameCode);
			try {
				logger.info("PT加密参数:"+buff.toString());
				_p = SecurityEncode.encoderByDES(buff.toString(),SecurityEncode.key);
				logger.info("PT加密后参数:"+_p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String loginUrl = CommonConstant.resCommMap.get(CommonConstant.PT_LOGINAUT_URL)+"login?_p="+_p;
		logger.info(loginUrl);
		return  loginUrl;
	}

	public double searchUserBlance(WebUserFlat webUserFlat) throws Exception {
		double blance = 0;
		Map<String,String> params = new HashMap<String,String>();
		//注册
		if (StringUtils.isEmpty(webUserFlat.getPtUserName())) {
			webUserFlat = NewPTIfcUtil.registPtAccout(webUserFlat);
			if (webUserFlat.getPtStatus() != null && webUserFlat.getPtStatus().intValue() == 1) {
				webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
			}
		}
		//查余额
		params.put(NtConts.USER_ID, webUserFlat.getPtUserName());// 游戏帐号
		blance= NewPTIfcUtil.balance(params);
		return blance;
	}

	public String updateEdu(WebUserFlat webUserFlat, WebUser webUser,String flatName, String optType, int points, WebEdu webEdu) throws RuntimeException {
		String tsMsg = "";
		Integer eduForward = webEdu.getEduForward();
		String eduForwardRemark = webEdu.getEduForwardRemark();
		if(StringUtils.isEmpty(webUserFlat.getPtUserName())){
			 try {
				webUserFlat =  NewPTIfcUtil.registPtAccout(webUserFlat);
				if(webUserFlat.getPtStatus()!= null && webUserFlat.getPtStatus().intValue()==1){
					 webUserFlatDao.update(webUserFlat);
					 tsMsg  = updatePtAccountPonit(webUser, flatName, webUserFlat.getPtUserName(), optType, points, eduForward, eduForwardRemark);
				 }else{
					 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 tsMsg  =updatePtAccountPonit(webUser, flatName, webUserFlat.getPtUserName(), optType, points, eduForward, eduForwardRemark);
		 }
		return tsMsg;
	}
	
	private String updatePtAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = ptDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		NewPtResResult ptResult = null;
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put(PtConts.USER_ID, flatAccount);
			params.put(PtConts.EDUORDER, eduOrder);
			params.put(PtConts.AMOUNT, String.valueOf(points));
			params.put(PtConts.WEBFLAG, userflat);
			
			ptResult = NewPTIfcUtil.withdrawal(params);
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
			throw new RuntimeException( "PT帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(PtConts.RES_SUUCESS.equals(ptResult.getCode())){//成功
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
				if(Conts.RESULT_9911000.equals(ptResult.getCode())){
					msg = ptResult.getMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("PT额度转换失败");// 异常
		}
		return msg;
	}
	
	private String ptDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(PtConts.USER_ID, flatAccount);
			params.put(PtConts.AMOUNT, String.valueOf(points));
			params.put(PtConts.EDUORDER, eduOrder);
			params.put(PtConts.WEBFLAG, userflat);
			
			NewPtResResult ptResult = NewPTIfcUtil.deposit(params);
			
			if (ptResult!=null && PtConts.RES_SUUCESS.equals(ptResult.getCode())) {
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
				if(ptResult!= null && Conts.RESULT_9911000.equals(ptResult.getCode())){
					return ptResult.getMessage();
				}
			}
		}catch (Exception e) {
			logger.error(logStrPrefix + "异常!",e);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException("PT入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}

	public ModelAndView mobileLogin(HttpServletRequest request,HttpServletResponse response) {
		String gameName = request.getParameter("gameName");
		String domain = this.getWebDomain(request) + "m/main?code=slot_pt";
		StringBuffer buff = new StringBuffer("");
		String _p = "";
		try{
			UserContext uc = this.getUserContext(request);
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getPtUserName())) {
				webUserFlat = NewPTIfcUtil.registPtAccout(webUserFlat);
				if (webUserFlat.getPtStatus() != null && webUserFlat.getPtStatus().intValue() == 1) {
					this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				}else{
					sendErrorMsg(response,"【PT平台注册用户失败】",domain);
					return null;
				}
			}
			String ptUserName = CommonConstant.resCommMap.get(CommonConstant.PT_BRAND_CODE) + "_"
					+ webUserFlat.getPtUserName().toUpperCase();
			String ptPassword = webUserFlat.getPtPassword();
			buff.append(ptUserName);
			buff.append("|");
			buff.append(ptPassword);
			buff.append("|");
			buff.append(gameName);
			logger.info("PT加密参数:" + buff.toString());
			try {
				_p = SecurityEncode.encoderByDES(buff.toString(),SecurityEncode.key);
				logger.info("PT加密后参数:" + _p);
			} catch (Exception e) {
				e.printStackTrace();
				sendErrorMsg(response,"【PT平台用户登录失败】",domain);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorMsg(response,"【PT平台用户登录失败】",domain);
		}
		String url = this.getRootWebDomain_(request) + "/m/main/";
		String loginUrl = CommonConstant.resCommMap.get(CommonConstant.PT_LOGINAUT_URL) + "login_h5?_p=" + _p + "&url="+url;
		return new ModelAndView("redirect:" + loginUrl);
	
	}
}
