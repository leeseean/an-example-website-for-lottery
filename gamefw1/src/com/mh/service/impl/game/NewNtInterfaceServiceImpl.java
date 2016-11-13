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
import com.mh.ifc.NewNtIfcUtil;
import com.mh.ifc.http.ComEduConts;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.NewNtResBean;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.DateUtil;
import com.mh.service.FlatInterfaceService;
import com.mh.web.login.UserContext;
/**
 * 新NT
 * 
 * @author Administrator
 * 
 */
@Service("newNtInterfaceServiceImpl")
public class NewNtInterfaceServiceImpl extends BaseFlatInfo implements FlatInterfaceService {

	public String login(HttpServletRequest request) {
		UserContext uc = getUserContext(request);
		try {
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getNewNtUserName())) {
				webUserFlat = NewNtIfcUtil.registNewNtAccout(webUserFlat);
				if (webUserFlat.getNewNtUserName() != null && webUserFlat.getNewNtStatus().intValue() == 1) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId", webUserFlat.getNewNtUserName());// 游戏帐号
			params.put("gameName", request.getParameter("game"));
			params.put(Conts.WEB_USER_FLAG, webUserFlat.getUserFlag());
			params.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
			params.put(Conts.WEB_USER_NAME,webUserFlat.getUserName());
			NewNtResBean result = NewNtIfcUtil.login(params);
			if (result != null) {
				return result.getLoginUrl();
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
		if (StringUtils.isEmpty(webUserFlat.getNewNtUserName())) {
			webUserFlat = NewNtIfcUtil.registNewNtAccout(webUserFlat);
			if (webUserFlat.getNewNtUserName() != null && webUserFlat.getNewNtStatus().intValue() == 1) {
				webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
			}
		}
		//查余额
		params.put("userId", webUserFlat.getNewNtUserName());
		NewNtResBean result = NewNtIfcUtil.getBalance(params);
		if(result!=null){
			blance = Double.valueOf(result.getBalance());
		}
		return blance;
	}

	public String updateEdu(WebUserFlat webUserFlat, WebUser webUser,String flatName, String optType, int points, WebEdu webEdu)throws RuntimeException {
		String tsMsg = "";
		Integer eduForward = webEdu.getEduForward();
		String eduForwardRemark = webEdu.getEduForwardRemark();
		if(StringUtils.isEmpty(webUserFlat.getNewNtUserName())){
			 try {
				webUserFlat = NewNtIfcUtil.registNewNtAccout(webUserFlat);
				if(webUserFlat.getNewNtUserName()!= null && webUserFlat.getNewNtStatus().intValue()==1){
					 webUserFlatDao.update(webUserFlat);
					 tsMsg = updateNewNtAccountPonit(webUser, flatName, webUserFlat.getNewNtUserName(), optType, points, eduForward, eduForwardRemark);
				 }else{
					 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }else{
			 tsMsg = updateNewNtAccountPonit(webUser, flatName, webUserFlat.getNewNtUserName(), optType, points, eduForward, eduForwardRemark);
		 }
		return tsMsg;
	}

	
	private String updateNewNtAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = newNtDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
			return msg;
		}
		
		/**请求API 创建EDU记录**/
		JSONObject eduObj = new JSONObject();
		eduObj.put(ComEduConts.EDUORDER, eduOrder);
		eduObj.put(ComEduConts.USERNAME, webUser.getUserName());
		eduObj.put(ComEduConts.EDUPOINTS, points+"");
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
		NewNtResBean result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId",flatAccount);
			params.put("amount", String.valueOf(-points));
			params.put("transaction", eduOrder);
			params.put("webFlag", userflat);
			
			result = NewNtIfcUtil.withdrawal(params);
			
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
		    throw new RuntimeException("新NT帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null){//成功
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
				msg = Conts.EDU_FAILURE;
				addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("新NT额度转换失败");// 异常
		}
		return msg;
	}
	
	private String newNtDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
		jsonObj.put(ComEduConts.EDUTYPE,  WebConstants.EDU_TYPE_2);
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
			NewNtResBean result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId",flatAccount);
			params.put("amount", String.valueOf(points));
			params.put("transaction", eduOrder);
			params.put("webFlag", userflat);
			
			result = NewNtIfcUtil.deposit(params);
			if (result!= null) {
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
			}
		}catch (Exception e) {
			logger.error(logStrPrefix + "异常!",e);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException("新NT入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
}
