package com.mh.ifc;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.utils.HttpClientUtil;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.SbtApiConstants;
import com.mh.ifc.http.SbtResBean;
import com.mh.ifc.util.StringHelper;

public class SBTIfcUtil {
	private static final Log logger = LogFactory.getLog(SBTIfcUtil.class);
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	/**
	 * 登录系统
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * SbtResBean
	 */
	public static SbtResBean  login(Map<String, String> params) throws Exception {
		SbtResBean result = null;
		try {
			logger.info("SBT【登录账号】开始");
			String respJson = HttpClientUtil.post(url + "/sbt/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), SbtResBean.class);
			}
			logger.info("SBT【登录账号】结束");
		} catch (Exception e) {
			logger.info("SBT【登录账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * SbtResBean
	 */
	public static SbtResBean register(Map<String, String> params){
		SbtResBean result = null;
		try {
			logger.info("SBT【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/sbt/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), SbtResBean.class);
			}
			logger.info("SBT【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("SBT【注册账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * SBT账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registSbtAccout(WebUserFlat webUserFlat) throws Exception {
		int maxUserSize=15;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > maxUserSize - webUserFlag.length()) {
			userName = userName.substring(0, maxUserSize - webUserFlag.length());// 截取前八位
		}
		registerParams.put(SbtApiConstants.USER_ID, webUserFlag + userName);
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号SBT:" + userName);
		SbtResBean result = SBTIfcUtil.register(registerParams);
		if (result != null) {
			if (SbtApiConstants.RESP_SUCCESS_CODE.equals(result.getErrorCode()) ) {// 成功
				webUserFlat.setSbtUserName(webUserFlag + userName);
				webUserFlat.setSbtStatus(1);
			}else{
				String tmpObUserName = StringHelper.getCharAndNumr(maxUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(SbtApiConstants.USER_ID, webUserFlag + tmpObUserName);
				result = SBTIfcUtil.register(registerParams);
				if (result != null && !SbtApiConstants.RESP_SUCCESS_CODE.equals(result.getErrorCode()) ) {
					webUserFlat.setSbtUserName(webUserFlag + userName);
					webUserFlat.setSbtStatus(1);
				}
			}
		}
		return webUserFlat;
	}
	
	
	/**
	 * 
	 * 查询余额
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * SbtResBean
	 */
	public static SbtResBean balance(Map<String, String> params) {
		SbtResBean result = null;
		try {
			logger.info("查詢SBT余额接口开始");
			String respJson = HttpClientUtil.post(url + "/sbt/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), SbtResBean.class);
			}
			logger.info("查詢SBT余额接口结束");
		} catch (Exception e) {
			logger.info("查詢SBT余额接口失败",e);
		}
		return result;
	}
	
	
	/**
	 * 存款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * SbtResBean
	 */
	public static SbtResBean deposit(Map<String, String> params) throws Exception {
		SbtResBean result = null;
		try {
			logger.info("SBT【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/sbt/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), SbtResBean.class);
			} else{
				result = new SbtResBean();
				result.setErrorCode(json.getString("code"));
				result.setErrorMsg(json.getString("message"));
			}
			logger.info("SBT【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * 提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * SbtResBean
	 */
	public static SbtResBean withdrawal(Map<String, String> params) throws Exception {
		SbtResBean result = null;
		try {
			logger.info("SBT【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/sbt/agent/api/withdraw", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), SbtResBean.class);
			} else{
				result = new SbtResBean();
				result.setErrorCode(json.getString("code"));
				result.setErrorMsg(json.getString("message"));
			}
			logger.info("SBT【提款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}
