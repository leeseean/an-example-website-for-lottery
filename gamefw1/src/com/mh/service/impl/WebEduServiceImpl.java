/**   
* 文件名称: WebEduServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 下午2:44:22<br/>
*/  
package com.mh.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mh.client.FlatClient;
import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.utils.HttpClientUtil;
import com.mh.dao.WebAccountsDao;
import com.mh.dao.WebEduDao;
import com.mh.dao.WebUserDao;
import com.mh.entity.WebAccounts;
import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.ABIfcUtil;
import com.mh.ifc.AGIfcUtil;
import com.mh.ifc.DJIfcUtil;
import com.mh.ifc.DSIfcUtil;
import com.mh.ifc.GDIfcUtil;
import com.mh.ifc.HGIfcUtil;
import com.mh.ifc.NBBINIfcUtil;
import com.mh.ifc.NMGIfcUtil;
import com.mh.ifc.NSBIfcUtil;
import com.mh.ifc.NTIfcUtil;
import com.mh.ifc.NewPTIfcUtil;
import com.mh.ifc.OGIfcUtil;
import com.mh.ifc.OSIfcUtil;
import com.mh.ifc.SBTIfcUtil;
import com.mh.ifc.TTGIfcUtil;
import com.mh.ifc.http.AbConts;
import com.mh.ifc.http.AbResResult;
import com.mh.ifc.http.AgConts;
import com.mh.ifc.http.AgResResult;
import com.mh.ifc.http.ComEduConts;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.DjConts;
import com.mh.ifc.http.DjResResult;
import com.mh.ifc.http.DsConts;
import com.mh.ifc.http.DsResResult;
import com.mh.ifc.http.GdConts;
import com.mh.ifc.http.GdResResult;
import com.mh.ifc.http.HgConts;
import com.mh.ifc.http.HgResResult;
import com.mh.ifc.http.NBBinConts;
import com.mh.ifc.http.NBbinResResult;
import com.mh.ifc.http.NMGConts;
import com.mh.ifc.http.NMgResResult;
import com.mh.ifc.http.NewPtResResult;
import com.mh.ifc.http.NsbConts;
import com.mh.ifc.http.NsbResResult;
import com.mh.ifc.http.NtConts;
import com.mh.ifc.http.NtResResult;
import com.mh.ifc.http.OgConts;
import com.mh.ifc.http.OgResResult;
import com.mh.ifc.http.OsConts;
import com.mh.ifc.http.OsResResult;
import com.mh.ifc.http.PtConts;
import com.mh.ifc.http.SbtApiConstants;
import com.mh.ifc.http.SbtResBean;
import com.mh.ifc.http.TTGConstant;
import com.mh.ifc.http.TTgResResult;
import com.mh.ifc.util.ComUtil;
import com.mh.ifc.util.DateUtil;
import com.mh.service.WebEduService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 下午2:44:22<br/>
 */

@Service
public class WebEduServiceImpl implements WebEduService{
	
	protected Logger logger = LoggerFactory.getLogger(WebEduServiceImpl.class);
	
	@Autowired
	private WebEduDao webEduDao;
	@Autowired
	private WebUserDao webUserDao;
	@Autowired
	private WebAccountsDao webAccountsDao;
	@Autowired
	private FlatClient flatClient;
	
	int flatUserSize = 10;
	
	/**
	 * 统计额度
	 * 方法描述: TODO</br> 
	 * @return  
	 * int
	 */
	public int getEduTotal(Integer eduType){
		return this.webEduDao.getEduTotal(eduType);
	}
	
	/**
	 * 额度转化记录流水列表
	 * 方法描述: TODO</br> 
	 * @param huikuan
	 * @return  
	 * List<WebBankHuikuan>
	 */
	public List<WebEdu> getWebBankHuikuanList(WebEdu webEdu){
		return this.webEduDao.getWebBankHuikuanList(webEdu);
	}
	
	
	/**
	 * 更新额度转换
	 * 方法描述: TODO</br> 
	 * @param webEdu
	 * @param webUserFlat
	 * @param flatName
	 * @param optType
	 * @param points
	 * @return  
	 * boolean
	 */
	public Map<String,Object> updateEdu(WebUserFlat webUserFlat,String flatName,String optType,int points,WebEdu webEdu){
		String tsMsg="额度转换成功！";
		boolean reFlag = true;
		Map<String,Object> reMap = new HashMap<String,Object>();
		try{
			
			WebUser webUser = this.webUserDao.findWebrUseByUserName(webUserFlat.getUserName());
			tsMsg = flatClient.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
			/*//BBIN
			if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_BBIN)) {
				//没有账户、注册
				 if(StringUtils.isEmpty(webUserFlat.getBbinUserName())){
					 webUserFlat =  NBBINIfcUtil.registNBBINAccout(webUserFlat);
					 if(webUserFlat.getBbinStatus()!=null && webUserFlat.getBbinStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateBbiAccountPonit(webUser, flatName, webUserFlat.getBbinUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg  = this.updateBbiAccountPonit(webUser, flatName, webUserFlat.getBbinUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			//MG
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_MG)) {
				 if(StringUtils.isEmpty(webUserFlat.getMgUserName()) || StringUtils.isEmpty(webUserFlat.getMgId())){
					 webUserFlat =  NMGIfcUtil.registNMgAccout(webUserFlat);
					 if(StringUtils.isNotEmpty(webUserFlat.getMgUserName())&&StringUtils.isNotEmpty(webUserFlat.getMgId())){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateMgAccountPonit(webUser, flatName, webUserFlat.getMgUserName(), webUserFlat.getMgPassword(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg = this.updateMgAccountPonit(webUser, flatName, webUserFlat.getMgUserName(), webUserFlat.getMgPassword(), optType, points, eduForward, eduForwardRemark);
				 }
			//AG
			}else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_AG)) {
				 if(StringUtils.isEmpty(webUserFlat.getAgUserName())){
					 webUserFlat =  AGIfcUtil.registAgAccout(webUserFlat);
					 if(webUserFlat.getAgStatus()!=null && webUserFlat.getAgStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg  = this.updateAgAccountPonit(webUser, flatName, webUserFlat.getAgUserName(),webUserFlat.getAgPassword(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg  =this.updateAgAccountPonit(webUser, flatName, webUserFlat.getAgUserName(),webUserFlat.getAgPassword(), optType, points, eduForward, eduForwardRemark);
				 }
				
			//DS
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_DS)) {
				 if(StringUtils.isEmpty(webUserFlat.getDsUserName())){
					 webUserFlat =  DSIfcUtil.registDsAccout(webUserFlat);
					 if(webUserFlat.getDsStatus()!=null && webUserFlat.getDsStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg  = this.updateDsAccountPonit(webUser, flatName, webUserFlat.getDsUserName(),webUserFlat.getDsPassword(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg  =this.updateDsAccountPonit(webUser, flatName, webUserFlat.getDsUserName(),webUserFlat.getDsPassword(), optType, points, eduForward, eduForwardRemark);
				 }
			//NT
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_NT)) {
				 if(StringUtils.isEmpty(webUserFlat.getNtUserName())){
					 webUserFlat =  NTIfcUtil.registNtAccout(webUserFlat);
					 if(webUserFlat.getNtStatus()!=null && webUserFlat.getNtStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg  =this.updateNtAccountPonit(webUser, flatName, webUserFlat.getNtUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg  = this.updateNtAccountPonit(webUser, flatName, webUserFlat.getNtUserName(), optType, points, eduForward, eduForwardRemark);
				 }
				 
			//PT
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_PT)) {
				 if(StringUtils.isEmpty(webUserFlat.getPtUserName())){
					 webUserFlat =  NewPTIfcUtil.registPtAccout(webUserFlat);
					 if(webUserFlat.getPtStatus()!= null && webUserFlat.getPtStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg  = this.updatePtAccountPonit(webUser, flatName, webUserFlat.getPtUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg  =this.updatePtAccountPonit(webUser, flatName, webUserFlat.getPtUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			//HG
			} else if (StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_HG)) {
				 if(StringUtils.isEmpty(webUserFlat.getHgUserName())){
					 webUserFlat =  HGIfcUtil.registHgAccout(webUserFlat);
					 if(webUserFlat.getHgStatus()!= null && webUserFlat.getHgStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateHgAccountPonit(webUser, flatName, webUserFlat.getHgUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg  =this.updateHgAccountPonit(webUser, flatName, webUserFlat.getHgUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			//斗鸡	 
			} else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_DJ)) {
				
				if(StringUtils.isEmpty(webUserFlat.getDjUserName())){
					 webUserFlat =  DJIfcUtil.registDJAccout(webUserFlat);
					 if(webUserFlat.getDjStatus()!= null && webUserFlat.getDjStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg  = this.updateDjAccountPonit(webUser, flatName, webUserFlat.getDjUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg  =this.updateDjAccountPonit(webUser, flatName, webUserFlat.getDjUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			//欧博
			} else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_AB)) {
				
				if(StringUtils.isEmpty(webUserFlat.getAbUserName())){
					 webUserFlat =  ABIfcUtil.registAbAccout(webUserFlat);
					 if(webUserFlat.getAbStatus()!= null && webUserFlat.getAbStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateAbAccountPonit(webUser, flatName, webUserFlat.getAbUserName(), webUserFlat.getAbPassword(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg = this.updateAbAccountPonit(webUser, flatName, webUserFlat.getAbUserName(), webUserFlat.getAbPassword(), optType, points, eduForward, eduForwardRemark);
				 }
			//OG
			} else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_OG)) {
				
				if(StringUtils.isEmpty(webUserFlat.getOgUserName())){
					 webUserFlat =  OGIfcUtil.registOgAccout(webUserFlat);
					 if(webUserFlat.getOgStatus()!= null && webUserFlat.getOgStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg  = this.updateOgAccountPonit(webUser, flatName, webUserFlat.getOgUserName(), webUserFlat.getOgPassword(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg = this.updateOgAccountPonit(webUser, flatName, webUserFlat.getOgUserName(), webUserFlat.getOgPassword(), optType, points, eduForward, eduForwardRemark);
				 }
			//OS
			}else if(StringUtils.equalsIgnoreCase(flatName, WebConstants.FLAT_NAME_OS)) {
				
				if(StringUtils.isEmpty(webUserFlat.getOsUserName())){
					 webUserFlat =  OSIfcUtil.registOsAccout(webUserFlat);
					 if(webUserFlat.getOsStatus()!= null && webUserFlat.getOsStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateOsAccountPonit(webUser, flatName, webUserFlat.getOsUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg = this.updateOsAccountPonit(webUser, flatName, webUserFlat.getOsUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			//SB
			}else if(StringUtils.endsWithIgnoreCase(flatName, WebConstants.FLAT_NAME_SB)) {
				if(StringUtils.isEmpty(webUserFlat.getSbUserName())){
					 webUserFlat =  NSBIfcUtil.registSbAccout(webUserFlat);
					 if(webUserFlat.getSbStatus()!= null && webUserFlat.getSbStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateSbAccountPonit(webUser, flatName, webUserFlat.getSbUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg = this.updateSbAccountPonit(webUser, flatName, webUserFlat.getSbUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			//GD
			}else if(StringUtils.endsWithIgnoreCase(flatName, WebConstants.FLAT_NAME_GD)) {
				if(StringUtils.isEmpty(webUserFlat.getGdUserName())){
					 webUserFlat =  GDIfcUtil.registGdAccout(webUserFlat);
					 if(webUserFlat.getGdStatus()!= null && webUserFlat.getGdStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateGdAccountPonit(webUser, flatName, webUserFlat.getGdUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg = this.updateGdAccountPonit(webUser, flatName, webUserFlat.getGdUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			//TTG
			}else if(StringUtils.endsWithIgnoreCase(flatName, WebConstants.FLAT_NAME_TTG)) {
				if(StringUtils.isEmpty(webUserFlat.getTtgUserName())){
					 webUserFlat =  TTGIfcUtil.registTTGAccout(webUserFlat);
					 if(webUserFlat.getTtgStatus()!= null && webUserFlat.getTtgStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateTtgAccountPonit(webUser, flatName, webUserFlat.getTtgUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg = this.updateTtgAccountPonit(webUser, flatName, webUserFlat.getTtgUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			}
			//SBT
			else if(StringUtils.endsWithIgnoreCase(flatName, WebConstants.FLAT_NAME_SBT)) {
				if(StringUtils.isEmpty(webUserFlat.getSbtUserName())){
					 webUserFlat = SBTIfcUtil.registSbtAccout(webUserFlat);
					 if(webUserFlat.getSbtStatus()!= null && webUserFlat.getSbtStatus().intValue()==1){
						 this.webUserFlatDao.update(webUserFlat);
						 tsMsg = this.updateSbtAccountPonit(webUser, flatName, webUserFlat.getSbtUserName(), optType, points, eduForward, eduForwardRemark);
					 }else{
						 tsMsg="额度转换失败，错误代码："+webUserFlat.getReturnCode();
					 }
				 }else{
					 tsMsg = this.updateSbtAccountPonit(webUser, flatName, webUserFlat.getSbtUserName(), optType, points, eduForward, eduForwardRemark);
				 }
			}*/
			
			if(tsMsg.indexOf("失败") >-1 || tsMsg.indexOf("异常") >-1 || tsMsg.indexOf("错误") >-1){
				reFlag = false;
			}
			
		}catch(Exception e){
			reFlag = false;
			logger.error("额度转换失败",e);
			tsMsg="额度转换失败！";
			throw new RuntimeException("额度转换失败!");// 异常
		}
		
		reMap.put("reFlag", reFlag);
		reMap.put("tsMsg", tsMsg);
		
		return reMap;
	}
	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateBbiAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false;	//异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(3, 5);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = bbinDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		NBbinResResult result = null;
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put(NBBinConts.USERNAME, flatAccount);
			params.put(NBBinConts.REMIT, String.valueOf(points));
			params.put(NBBinConts.REMITNO, eduOrder);
			params.put(NBBinConts.WEBFLAG, userflat);
			params.put(NBBinConts.ACTION, NBBinConts.WITHDRAW);
			result = NBBINIfcUtil.transferMoney(params);
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			flag = true;
			msg =  Conts.EDU_FAILURE;
			throw new RuntimeException( "BBIN帐号添加额度异常");// 抛出异常 事务回滚
		}
		
		if(flag){	//return error log
			return msg;
		}
		
		try{
			if(result!=null && result.getResult()){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else {
				if(result!= null && Conts.RESULT_9911000.equals(result.getCode())){
					msg = result.getMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}	
		}catch (Exception e) {
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("BBIN额度转换失败!"); 
		}
		
		return msg;
	}
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateAgAccountPonit(WebUser webUser,String flatName,String flatAccount,String flatPassword,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
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
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
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
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(StringUtils.equalsIgnoreCase(Conts.RESULT_9911000, result.getInfo())){
					msg = result.getMsg();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("AG额度转换失败");// 异常
		}
		return msg;
	}
	
	
	
 
	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateMgAccountPonit(WebUser webUser,String flatName,String flatAccount, String password, String optType
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
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
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
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType,-points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getStatusCode())){
					msg = result.getErrorMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
				
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("MG额度转换失败");// 异常
		}
		return msg;
	}
	
	

	
	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateDsAccountPonit(WebUser webUser,String flatName,String flatAccount,String flatPassword,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = dsDepositOpt(webUser,flatName,optType, flatAccount,flatPassword, points,eduForward, eduForwardRemark);
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
		DsResResult result = null;
		try{
			JSONObject jsonObj = new JSONObject();
			Map<String, String> params = new HashMap<String, String>();
			jsonObj.put(DsConts.USERNAME, flatAccount);
			jsonObj.put(DsConts.PASSWORD, flatPassword);
			jsonObj.put(DsConts.REF, eduOrder);
			jsonObj.put(DsConts.AMOUNT, String.valueOf(points));
			jsonObj.put(DsConts.WEBFLAG, userflat);
	
			params.put("params",jsonObj.toString());
			result = DSIfcUtil.withdrawal(params);
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException( "DS帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!=null && result.getErrorCode() == 0){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_CREDIT_LACK == result.getErrorCode()){
					msg = result.getErrorMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException( "DS额度转换失败");// 异常
		}
		return msg;
	}
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateNtAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = updateDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		
		//转出（游戏平-》台主帐号
		NtResResult result = null;
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put(NtConts.USER_ID, flatAccount);
			params.put(NtConts.EDUORDER, eduOrder);
			params.put(NtConts.AMOUNT, String.valueOf(-points*100));
			params.put(NtConts.WEBFLAG, userflat);
			
			result = NTIfcUtil.withdrawal(params);
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException("NT帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!=null && result.getErrorCode() == 0){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_CREDIT_LACK == result.getErrorCode()){
					msg = result.getErrorMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("NT额度转换失败",e);
			throw new RuntimeException("NT额度转换失败");// 异常
		}
		return msg;
	}
	
	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updatePtAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
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
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
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
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(Conts.RESULT_9911000.equals(ptResult.getCode())){
					msg = ptResult.getMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("PT额度转换失败");// 异常
		}
		return msg;
	}
	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateHgAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
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
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
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
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_CREDIT_LACK == result.getStatus()){
					msg = result.getErrdesc();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("HG额度转换失败");// 异常
		}
		return msg;
	}
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateDjAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = djDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		DjResResult result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put(DjConts.USERNAME,flatAccount);
			params.put(DjConts.AMOUNT, String.valueOf(points));
			params.put(DjConts.REFNO, eduOrder);
			params.put(DjConts.WEBFLAG, userflat);
			result = DJIfcUtil.withdrawal(params);
			
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException("DJ帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && DjConts.RES_CODE_00.equals(result.getStatus_code())){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号减少额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!= null && Conts.RESULT_9911000.equals(result.getStatus_code())){
					msg = Conts.FLAT_ACCOUNT_LACK;
				}else{
					msg = Conts.EDU_FAILURE;
				}
				
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("DJ额度转换失败");// 异常
		}
		return msg;
	}
	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateAbAccountPonit(WebUser webUser,String flatName,String flatAccount, String password, String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getNumr(13);// 订单编号 必须满13位
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = abDepositOpt(webUser,flatName,optType, flatAccount, password, points,eduForward, eduForwardRemark);
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
		AbResResult result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put(AbConts.USERNAME,flatAccount);
			params.put(AbConts.PASSWORD, password);
			params.put(AbConts.CREDIT, String.valueOf(points));
			params.put(AbConts.TRANSNO, eduOrder);
			params.put(AbConts.OPERFLAG, AbConts.OPER_TYPE_OUT);
			params.put(AbConts.WEBFLAG, userflat);
			result = ABIfcUtil.transferCredit(params);
			
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException("Ab帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && AbConts.RES_OK.equals(result.getError_code())){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号增加额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getError_code())){
					msg = result.getMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("欧博额度转换失败");// 异常
		}
		return msg;
	}
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateOgAccountPonit(WebUser webUser,String flatName,String flatAccount, String password, String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getNumr(10);// 订单编号 必须满10位
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = OgDepositOpt(webUser,flatName,optType, flatAccount, password, points,eduForward, eduForwardRemark);
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
		OgResResult result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put(OgConts.USERNAME,flatAccount);
			params.put(OgConts.PASSWORD, password);
			params.put(OgConts.CREDIT, String.valueOf(points));
			params.put(OgConts.BILLNO, eduOrder);
			params.put(OgConts.OPERTYPE, OgConts.OPER_TYPE_OUT);
			params.put(OgConts.WEBFLAG, userflat);
			
			result = OGIfcUtil.transferCredit(params);
			
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException("OG帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && OgConts.RES_OK.equals(result.getResultCode())){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号增加额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);;
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getResultCode())){
					msg = result.getResult();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("OG额度转换失败");// 异常
		}
		return msg;
	}
	
	/**
	 * OS
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateOsAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
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
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
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
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				//主帐号增加额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					msg = result.getErrorMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("OS额度转换失败");// 异常
		}
		return msg;
	}
	
	
	/**
	 * SB
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateSbAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = sbDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		NsbResResult result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put(NsbConts.DIRECTION, NsbConts.Withdraw);
			params.put(NsbConts.PLAYERNAME,flatAccount);
			params.put(NsbConts.AMOUNT, String.valueOf(points));
			params.put(NsbConts.OPTRANSID, eduOrder);
			params.put(NsbConts.WEBFLAG, userflat);
			
			result = NSBIfcUtil.transferMoney(params);
			
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException("SB帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && NsbConts.RES_CODE_0.equals(result.getError_code())){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号增加额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getError_code())){
					msg = result.getMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("沙巴体育额度转换失败");// 异常
		}
		return msg;
	}
	
	
	/**
	 * GD
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateGdAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = GdDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		GdResResult result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put(GdConts.USERID,flatAccount);
			params.put(GdConts.AMOUNT, String.valueOf(points));
			params.put(GdConts.TRANFID, eduOrder);
			params.put(GdConts.WEBFLAG, userflat);
			
			result = GDIfcUtil.withdrawal(params);
			
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
			msg = Conts.EDU_FAILURE;
			throw new RuntimeException("GD帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && GdConts.RES_CODE_0.equals(result.getErrorCode())){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号增加额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					msg = result.getErrorMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("GD额度转换失败");// 异常
		}
		return msg;
	}
	
	
	/**
	 * TTG
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateSbtAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = SbtDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		SbtResBean result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put(SbtApiConstants.USER_ID,flatAccount);
			params.put(SbtApiConstants.BALANCE, String.valueOf(points));
			params.put(SbtApiConstants.TRANSACTION, eduOrder);
			params.put(SbtApiConstants.WEBFLAG, userflat);
			
			result = SBTIfcUtil.withdrawal(params);
			
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
		    msg = Conts.EDU_FAILURE;
		    throw new RuntimeException("SBT帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && SbtApiConstants.RESP_SUCCESS_CODE.equals(result.getErrorCode())){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号增加额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					msg = result.getErrorMsg();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("SBT额度转换失败");// 异常
		}
		return msg;
	}
	
	/**
	 * TTG
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param flatAccount
	 * @param optType
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark  
	 * void
	 * @throws RuntimeException 
	 */
	public String updateTtgAccountPonit(WebUser webUser,String flatName,String flatAccount,String optType
			,int points,int eduForward, String eduForwardRemark) throws RuntimeException{
		boolean flag = false; //异常标识 false:无异常 true:异常
		String msg = "";
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		String userflat = flatAccount.substring(0, 2);
		
		if (StringUtils.equalsIgnoreCase(optType, WebConstants.EDU_TYPE_2)){// 转入 （主帐号-》游戏平台）
			msg = TtgDepositOpt(webUser,flatName,optType, flatAccount, points,eduForward, eduForwardRemark);
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
		TTgResResult result = null;
		try{
			//转出
			Map<String, String> params = new HashMap<String, String>();
			params.put(TTGConstant.USERID,flatAccount);
			params.put(TTGConstant.AMOUNT, String.valueOf(-points));
			params.put(TTGConstant.TRANSFERID, eduOrder);
			params.put(TTGConstant.WEBFLAG, userflat);
			
			result = TTGIfcUtil.withdrawal(params);
			
		}catch (Exception e) {
			this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_INIT, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			flag = true;
		    msg = Conts.EDU_FAILURE;
		    throw new RuntimeException("SBT帐号添加额度异常");// 异常
		}
		
		if(flag){ //return error log
		    return msg;
		}
		
		try{
			if(result!= null && TTGConstant.RES_SUCCESS.equals(result.getErrorCode())){//成功
				int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), points);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//主帐号增加额度
				double userMoney = webUser.getUserMoney().doubleValue() + points;
				webUser.setUserMoney(userMoney);
				//资金流水
				this.addAccountRecord(webUser, flatName, optType, -points, eduOrder, eduForwardRemark, gmt_4_date);
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_1, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
				msg = Conts.EDU_SUCCESS;
			}else{
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					msg = result.getErrorMessage();
				}else{
					msg = Conts.EDU_FAILURE;
				}
				this.addWebEduRecord(webUser,WebConstants.EDU_STATUS_0, eduOrder, -points, eduForward, eduForwardRemark, gmt_4_date, flatName);
			}
		}catch(Exception e){
			msg = Conts.EDU_FAILURE;
			logger.error("额度转换失败",e);
			throw new RuntimeException("TTG额度转换失败");// 异常
		}
		return msg;
	}
	
	/**
	 * BBin 入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String bbinDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
		String logStrPrefix = "NBBIN额度操作主帐号" + webUser.getUserName() + " 转入金额" + points;
		String eduOrder = ComUtil.getOrder();// 订单编号
		Date gmt_4_date = DateUtil.getGMT_4_Date();// 美东时间
		int eduPoints = points;
		String userflat = flatAccount.substring(3, 5);
		
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(),-points);
			if(rows<1){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			NBbinResResult result = null;
			Map<String, String> params = new HashMap<String, String>();
			params.put(NBBinConts.USERNAME, flatAccount);
			params.put(NBBinConts.REMIT, String.valueOf(points));
			params.put(NBBinConts.REMITNO, eduOrder);
			params.put(NBBinConts.WEBFLAG, userflat);
			params.put(NBBinConts.ACTION, NBBinConts.DEPOSIT);
			result = NBBINIfcUtil.transferMoney(params);
			
			if (result!= null && result.getResult()) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(),eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(result!= null && Conts.RESULT_9911000.equals(result.getCode())){
					return result.getMessage();
				}
			}
		}catch (Exception e) {
			logger.error(logStrPrefix + "异常!",e);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException("BBIN额度转换失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	
	/**
	 * MG入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
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

 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(),-points);
			if(rows<1){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType,eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
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
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(),eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				
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
	
	/**
	 * AG入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 */
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
			
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(),-points);
			if(rows<1){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType,eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			
			params.put(AgConts.FLAG, flag);
			result = AGIfcUtil.agAgentEduConfirm(params);// 确认转帐
			
			if (StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(),eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(StringUtils.equalsIgnoreCase(Conts.RESULT_9911000, result.getInfo())){
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
	
	
	
	/**
	 * DS入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String dsDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount,String flatPassword, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
			logger.info("调用API创建edu记录失败");
			throw new RuntimeException("调用API创建edu记录失败");// 异常
		}
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<1){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			DsResResult result = null;
			jsonObj = new JSONObject();
			Map<String, String> params = new HashMap<String, String>();
			jsonObj.put(DsConts.USERNAME, flatAccount);
			jsonObj.put(DsConts.PASSWORD, flatPassword);
			jsonObj.put(DsConts.REF, eduOrder);
			jsonObj.put(DsConts.AMOUNT, String.valueOf(points));
			jsonObj.put(DsConts.WEBFLAG, userflat);
			params.put("params", jsonObj.toJSONString());
			
			result = DSIfcUtil.deposit(params);
			
			if (result!= null && result.getErrorCode() == 0) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(result!=null && Conts.RESULT_CREDIT_LACK == result.getErrorCode()){
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
			throw new RuntimeException("DS额度转化失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	
	/**
	 * NT入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String updateDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<1){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			NtResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(NtConts.USER_ID, flatAccount);
			params.put(NtConts.EDUORDER, eduOrder);
			params.put(NtConts.AMOUNT, String.valueOf(points*100));
			params.put(NtConts.WEBFLAG, userflat);
			
			result = NTIfcUtil.deposit(params);
			
			
			if (result!= null && result.getErrorCode() == 0) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			} 
			
			/** 主帐号增加额度 **/
			userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
			webUser.setUserMoney(userMoney);
			rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
			if(rows<1){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
			this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
		
			if(result!=null && Conts.RESULT_CREDIT_LACK == result.getErrorCode()){
				return result.getErrorMessage();
			}
		}catch (Exception e) {
			logger.error(logStrPrefix + "异常!",e);
			try {
				MemCachedUtil.setEduNotice(flatName);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException("NT入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	
	/**
	 * HG入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<1){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
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
					this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
					return Conts.EDU_SUCCESS;
				}
				
			} 
			/** 主帐号增加额度 **/
			userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
			webUser.setUserMoney(userMoney);
			
			rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
			if(rows<1){
				logger.error("更新用户额度失败！");
				return Conts.EDU_FAILURE;
			}
			
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
			this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
		
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
	
	
	
	/**
	 * PT入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<1){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(PtConts.USER_ID, flatAccount);
			params.put(PtConts.AMOUNT, String.valueOf(points));
			params.put(PtConts.EDUORDER, eduOrder);
			params.put(PtConts.WEBFLAG, userflat);
			
			NewPtResResult ptResult = NewPTIfcUtil.deposit(params);
			
			if (PtConts.RES_SUUCESS.equals(ptResult.getCode())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(Conts.RESULT_9911000.equals(ptResult.getCode())){
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
	
	/**
	 * DJ入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String djDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<0){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			DjResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(DjConts.USERNAME,flatAccount);
			params.put(DjConts.AMOUNT, String.valueOf(points));
			params.put(DjConts.REFNO, eduOrder);
			params.put(OsConts.WEBFLAG, userflat);
			
			result = DJIfcUtil.deposit(params);
			
			if (result!= null && DjConts.RES_CODE_00.equals(result.getStatus_code())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(result!= null && Conts.RESULT_9911000.equals(result.getStatus_code())){
					return Conts.FLAT_ACCOUNT_LACK;
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
			throw new RuntimeException("DJ入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	
	/**
	 * OS入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<0){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			OsResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(OsConts.USERNAME,flatAccount);
			params.put(OsConts.AMOUNT, String.valueOf(points));
			params.put(OsConts.BILLNO, eduOrder);
			params.put(OsConts.WEBFLAG, userflat);
			result = OSIfcUtil.deposit(params);
			
			if (result!= null && OsConts.RES_OK.equals(result.getErrorCode())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					return result.getErrorMessage();
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
			throw new RuntimeException("DJ入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	/**
	 * 沙巴体育入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String sbDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<0){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			NsbResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(NsbConts.DIRECTION, NsbConts.Deposit);
			params.put(NsbConts.PLAYERNAME,flatAccount);
			params.put(NsbConts.AMOUNT, String.valueOf(points));
			params.put(NsbConts.OPTRANSID, eduOrder);
			params.put(NsbConts.WEBFLAG, userflat);
			result = NSBIfcUtil.transferMoney(params);
			
			if (result!= null && NsbConts.RES_CODE_0.equals(result.getError_code())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(result!=null && Conts.RESULT_9911000.equals(result.getError_code())){
					return result.getMessage();
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
			throw new RuntimeException("DJ入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	/**
	 * 欧博入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String abDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, String password, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
		String logStrPrefix = eduForwardRemark+"额度操作主帐号" + webUser.getUserName() + " 转入金额" + points;
		String eduOrder = ComUtil.getNumr(13);// 订单编号
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<0){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			AbResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(AbConts.USERNAME,flatAccount);
			params.put(AbConts.PASSWORD, password);
			params.put(AbConts.CREDIT, String.valueOf(points));
			params.put(AbConts.TRANSNO, eduOrder);
			params.put(AbConts.OPERFLAG, AbConts.OPER_TYPE_IN);
			params.put(AbConts.WEBFLAG, userflat);
			result = ABIfcUtil.transferCredit(params);
			
			if (result!= null && AbConts.RES_OK.equals(result.getError_code())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				if(result!=null && Conts.RESULT_9911000.equals(result.getError_code())){
					return result.getMessage();
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
			throw new RuntimeException("DJ入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
 
	/**
	 * OG入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String OgDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, String password, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
		String logStrPrefix = eduForwardRemark+"额度操作主帐号" + webUser.getUserName() + " 转入金额" + points;
		String eduOrder = ComUtil.getNumr(10);// 订单编号
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<0){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			OgResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(OgConts.USERNAME,flatAccount);
			params.put(OgConts.PASSWORD, password);
			params.put(OgConts.CREDIT, String.valueOf(points));
			params.put(OgConts.BILLNO, eduOrder);
			params.put(OgConts.OPERTYPE, OgConts.OPER_TYPE_IN);
			params.put(OgConts.WEBFLAG, userflat);
			
			result = OGIfcUtil.transferCredit(params);
			
			if (result!= null && OgConts.RES_OK.equals(result.getResultCode())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				
				if(result!=null && Conts.RESULT_9911000.equals(result.getResultCode())){
					return result.getResult();
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
			throw new RuntimeException("OG入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	
	/**
	 * OG入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String GdDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
		String logStrPrefix = eduForwardRemark+"额度操作主帐号" + webUser.getUserName() + " 转入金额" + points;
		String eduOrder = ComUtil.getNumr(10);// 订单编号
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<0){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			GdResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(GdConts.USERID,flatAccount);
			params.put(GdConts.AMOUNT, String.valueOf(points));
			params.put(GdConts.TRANFID, eduOrder);
			params.put(GdConts.WEBFLAG, userflat);
			
			result = GDIfcUtil.deposit(params);
			
			if (result!= null && GdConts.RES_CODE_0.equals(result.getErrorCode())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				
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
			throw new RuntimeException("OG入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	
	/**
	 * sbt入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String SbtDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<0){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			SbtResBean result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(SbtApiConstants.USER_ID,flatAccount);
			params.put(SbtApiConstants.BALANCE, String.valueOf(points));
			params.put(SbtApiConstants.TRANSACTION, eduOrder);
			params.put(SbtApiConstants.WEBFLAG, userflat);
			
			result = SBTIfcUtil.deposit(params);
			if (result!= null && SbtApiConstants.RESP_SUCCESS_CODE.equals(result.getErrorCode())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					return result.getErrorMsg();
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
			throw new RuntimeException("SBT入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	/**
	 * TTG入款
	 * @param optType
	 * @param userName
	 * @param flatAccount
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @return
	 * @throws RuntimeException 
	 * @throws RuntimeException
	 */
	private String TtgDepositOpt(WebUser webUser,String flatName,String optType, String flatAccount, int points, int eduForward, String eduForwardRemark) throws RuntimeException{
		
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
 
		try {
			// 减少用户余额
			double userMoney = webUser.getUserMoney().doubleValue() - points;
			webUser.setUserMoney(userMoney);
			int rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), -points);
			if(rows<0){
				logger.error("更新用户额度失败！");
				throw new RuntimeException("更新用户额度失败");// 异常
			}
			
			
			//入款记录
			this.addWebEduRecord(webUser, WebConstants.EDU_STATUS_INIT, eduOrder, eduPoints, eduForward, eduForwardRemark, gmt_4_date, flatName);
			//财务记录
			this.addAccountRecord(webUser, flatName, optType, eduPoints, eduOrder, eduForwardRemark, gmt_4_date);
		
			/** 平台帐号添加额度 **/
			TTgResResult result = null;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(TTGConstant.USERID,flatAccount);
			params.put(TTGConstant.AMOUNT, String.valueOf(points));
			params.put(TTGConstant.TRANSFERID, eduOrder);
			params.put(TTGConstant.WEBFLAG, userflat);
			
			result = TTGIfcUtil.deposit(params);
			
			if (result!= null && TTGConstant.RES_SUCCESS.equals(result.getErrorCode())) {
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款成功", WebConstants.EDU_STATUS_1);
				return Conts.EDU_SUCCESS;
			}else{
				/** 主帐号增加额度 **/
				userMoney = webUser.getUserMoney().doubleValue() +eduPoints;
				webUser.setUserMoney(userMoney);
				
				rows = this.webUserDao.updateWebUserForMoney(webUser.getUserName(), eduPoints);
				if(rows<1){
					logger.error("更新用户额度失败！");
					throw new RuntimeException("更新用户额度失败");// 异常
				}
				
				//财务记录
				this.addAccountRecord(webUser, flatName, optType, -eduPoints, eduOrder, "额度转化失败，返还金额", gmt_4_date);
				this.webEduDao.updateEduRecord(webUser.getUserName(), eduOrder, eduForwardRemark+"，入款失败", WebConstants.EDU_STATUS_0);
				
				if(result!=null && Conts.RESULT_9911000.equals(result.getErrorCode())){
					return result.getErrorMessage();
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
			throw new RuntimeException("TTG入款失败");// 异常
		} 
		return Conts.EDU_FAILURE;
	}
	
	
	/**
	 * 转换记录流水
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param statusType
	 * @param eduOrder
	 * @param points
	 * @param eduForward
	 * @param eduForwardRemark
	 * @param gmt_4_date
	 * @param flatName  
	 * void
	 */
	private void addWebEduRecord(WebUser webUser,int statusType, String eduOrder, int points, int eduForward, String eduForwardRemark, Date gmt_4_date, String flatName)  {
		 
		Date currDate = new Date();
		WebEdu edu = new WebEdu();
		edu.setEduOrder(eduOrder);
		edu.setUserName(webUser.getUserName());
		edu.setEduPoints(points);
		edu.setEduType(points > 0 ? 2 : 1);
		edu.setEduStatus(statusType);
		edu.setEduIp(webUser.getUserLastLoginIp());
		edu.setCreateTime(currDate);
		edu.setModifyTime(currDate);
		edu.setEduForward(eduForward);
		edu.setEduForwardRemark(eduForwardRemark);
		edu.setGmt4Time(gmt_4_date);
		edu.setFlatName(flatName);
		this.webEduDao.saveOrUpdate(edu);
			
	}
	
	
	
	/**
	 * 金额流水
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param flatName
	 * @param optType
	 * @param money
	 * @param eduOrder
	 * @param remark
	 * @param gmt_4_date  
	 * void
	 */
	private void addAccountRecord(WebUser webUser, String flatName, String optType, double money, String eduOrder, String remark, Date gmt_4_date) {
	
		optType = WebConstants.eduCodeMap.get(String.valueOf(optType));

		/** 财务记录 开始 **/
		WebAccounts account = new WebAccounts();
		account.setActOptMoney(-money);// 
		account.setActProType(flatName);// 取款
		account.setActOptType(optType);
		account.setActOrder(eduOrder);
		account.setActResultMoney(webUser.getUserMoney());
		account.setCreateTime(new Date());
		account.setModifyTime(new Date());
		account.setRemark(remark);
		account.setSysUserName(webUser.getUserName());
		account.setUserName(webUser.getUserName());
		account.setStatus(0);
		account.setGmt4Time(gmt_4_date);
		if(!StringUtils.isEmpty(webUser.getUserAgent())){
			account.setUserAgent(webUser.getUserAgent());
		}
	 
		webAccountsDao.saveOrUpdate(account);
 
	}
	
	
	
	public boolean optInterfaceEdu(Map<String, String> params){
		PostMethod method = null;
		try {
			String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
			try {
				String respJson = HttpClientUtil.post(url + "/com/agent/api/eduOption", params);
				JSONObject json =JSON.parseObject(respJson);
				if (StringUtils.equals("000000", json.getString("code"))) {
					return true;
				}
				return false;
			} catch (Exception e) {
				logger.info("校验接口失败",e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (method != null) {
				method.abort();
				method.releaseConnection();
			}
		}
		return false;

	}
	

}
