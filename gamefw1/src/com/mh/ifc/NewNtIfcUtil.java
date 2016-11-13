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
import com.mh.ifc.http.NewNtResBean;
import com.mh.ifc.util.StringHelper;

public class NewNtIfcUtil {
	private static final Log logger = LogFactory.getLog(NewNtIfcUtil.class);
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	/**
	 * 登录系统
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * NewNtResBean
	 */
	public static NewNtResBean  login(Map<String, String> params) throws Exception {
		NewNtResBean result = null;
		try {
			logger.info("新NT【登录账号】开始");
			String respJson = HttpClientUtil.post(url + "/newnt/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NewNtResBean.class);
			}
			logger.info("新NT【登录账号】结束");
		} catch (Exception e) {
			logger.info("新NT【登录账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * NewNtResBean
	 */
	public static NewNtResBean register(Map<String, String> params){
		NewNtResBean result = null;
		try {
			logger.info("新NT【注册账号】开始");
			String respJson = HttpClientUtil.post(url + "/newnt/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NewNtResBean.class);
			}
			logger.info("新NT【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("新NT【注册账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 新NT账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registNewNtAccout(WebUserFlat webUserFlat) throws Exception {
		int maxUserSize=15;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > maxUserSize - webUserFlag.length()) {
			userName = userName.substring(0, maxUserSize - webUserFlag.length());// 截取前八位
		}
		registerParams.put("userId", webUserFlag + userName);
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的新NT帐号:" + userName);
		NewNtResBean result = NewNtIfcUtil.register(registerParams);
		if (result != null) {
			if (StringUtils.isNotEmpty(result.getKey())) {// 成功
				webUserFlat.setNewNtUserName(webUserFlag + userName);
				webUserFlat.setNewNtStatus(1);
				webUserFlat.setNewNtKey(result.getKey());
			}else{
				String tmpObUserName = StringHelper.getCharAndNumr(maxUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put("userId", webUserFlag + tmpObUserName);
				result = NewNtIfcUtil.register(registerParams);
				if (StringUtils.isNotEmpty(result.getKey())) {
					webUserFlat.setNewNtUserName(webUserFlag + userName);
					webUserFlat.setNewNtStatus(1);
					webUserFlat.setNewNtKey(result.getKey());
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
	 * NewNtResBean
	 */
	public static NewNtResBean getBalance(Map<String, String> params) {
		NewNtResBean result = null;
		try {
			logger.info("查詢新NT余额接口开始");
			String respJson = HttpClientUtil.post(url + "/newnt/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NewNtResBean.class);
			}
			logger.info("查詢新NT余额接口结束");
		} catch (Exception e) {
			logger.info("查詢新NT余额接口失败",e);
		}
		return result;
	}
	/**
	 * 存款接口
	 * NewNtResBean
	 */
	public static NewNtResBean deposit(Map<String, String> params) throws Exception {
		NewNtResBean result = null;
		try {
			logger.info("新NT【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/newnt/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NewNtResBean.class);
			}
			logger.info("新NT【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * 提款接口
	 */
	public static NewNtResBean withdrawal(Map<String, String> params) throws Exception {
		NewNtResBean result = null;
		try {
			logger.info("新NT【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/newnt/agent/api/withdraw", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NewNtResBean.class);
			}
			logger.info("新NT【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}
