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
import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.VgIfcUtil;
import com.mh.ifc.http.ComEduConts;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.VgConts;
import com.mh.ifc.http.VgResBean;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.DateUtil;
import com.mh.service.FlatInterfaceService;
import com.mh.web.login.UserContext;
@Service("vgInterfaceServiceImpl")
public class VgInterfaceServiceImpl extends BaseFlatInfo implements FlatInterfaceService {

	
	public String login(HttpServletRequest request) {
		try {
			
			String domain = this.getWebDomain(request);
			
			UserContext uc = getUserContext(request);
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());

			if (StringUtils.isEmpty(webUserFlat.getVgUserName())) {
				webUserFlat = VgIfcUtil.registVgAccout(webUserFlat);
				if (webUserFlat.getVgStatus() != null && webUserFlat.getVgStatus().intValue() == 1) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put(VgConts.USER_ID, webUserFlat.getVgUserName());// 游戏帐号
			params.put(VgConts.HOST, domain);
			VgResBean result = VgIfcUtil.login(params);
			if (result != null) {
				return result.getUrl();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public ModelAndView mobileLogin(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public double searchUserBlance(WebUserFlat webUserFlat) throws Exception {
		double blance = 0;
		Map<String,String> params = new HashMap<String,String>();
		//注册
		if (StringUtils.isEmpty(webUserFlat.getVgUserName())) {
			webUserFlat = VgIfcUtil.registVgAccout(webUserFlat);
			if (webUserFlat.getVgStatus() != null && webUserFlat.getVgStatus().intValue() == 1) {
				webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
			}
		}
		//查余额
		params.put(VgConts.USER_ID, webUserFlat.getVgUserName());// 游戏帐号
		VgResBean result = VgIfcUtil.balance(params);
		if (result != null && StringUtils.equals(result.getErrCode(), VgConts.RES_SUCCESS)) {
			blance = result.getBalance();
		}
		return blance;
	}

	
	public String updateEdu(WebUserFlat webUserFlat, WebUser webUser,String flatName, String optType, int points, WebEdu webEdu) throws RuntimeException {

		Integer eduForward = webEdu.getEduForward();
		String eduForwardRemark = webEdu.getEduForwardRemark();
		String tsMsg = "";
		// 没有账户、注册
		if (StringUtils.isEmpty(webUserFlat.getVgUserName())) {
			try {
				webUserFlat = VgIfcUtil.registVgAccout(webUserFlat);
				if (webUserFlat.getVgStatus() != null && webUserFlat.getVgStatus().intValue() == 1) {
					webUserFlatDao.update(webUserFlat);
					tsMsg = updateVgAccountPonit(webUser, flatName,webUserFlat.getVgUserName(), optType, points,eduForward, eduForwardRemark);
				} else {
					tsMsg = "额度转换失败，错误代码：" + webUserFlat.getReturnCode();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			tsMsg = updateVgAccountPonit(webUser, flatName,webUserFlat.getVgUserName(), optType, points, eduForward,eduForwardRemark);
		}
		return tsMsg;
	
	}

	
	private String updateVgAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false;	//异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(3, 5);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = vgDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		VgResBean result = null;
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put(VgConts.USER_ID, flatAccount);
			params.put(VgConts.AMOUNT, String.valueOf(points));
			params.put(VgConts.ORDER_ID, eduOrder);
			params.put(VgConts.WEBFLAG, userflat);
			result = VgIfcUtil.withdraw(params);
		}catch (Exception e) {
			addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			flag = true;
			msg =  Conts.EDU_FAILURE;
			throw new RuntimeException( "VG帐号添加额度异常");// 抛出异常 事务回滚
		}
		
		if(flag){	//return error log
			return msg;
		}
		
		try{
			if(result != null && StringUtils.equals(result.getErrCode(), VgConts.RES_SUCCESS)){//成功
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
			}else {
				msg = Conts.EDU_FAILURE;
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}	
		}catch (Exception e) {
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("VG额度转换失败!"); 
		}
		
		return msg;
	}
	
	
	private String vgDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
		String logStrPrefix = "VG额度操作主帐号" + webUser.getUserName() + " 转入金额" + points;
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
			addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			VgResBean result = null;
			Map<String, String> params = new HashMap<String, String>();
			params.put(VgConts.USER_ID, flatAccount);
			params.put(VgConts.AMOUNT, String.valueOf(points));
			params.put(VgConts.ORDER_ID, eduOrder);
			params.put(VgConts.WEBFLAG, userflat);
			result = VgIfcUtil.deposit(params);
			
			if (result!= null && StringUtils.equals(result.getErrCode(), VgConts.RES_SUCCESS)) {
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
			}
		}catch (Exception e) {
			logger.error(logStrPrefix + "异常!",e);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException("VG额度转换失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
}
