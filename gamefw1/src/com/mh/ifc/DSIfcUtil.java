/**   
* 文件名称: DSUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-3 下午2:03:48<br/>
*/  
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
import com.mh.ifc.http.DsConts;
import com.mh.ifc.http.DsResResult;
import com.mh.ifc.util.StringHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:03:48<br/>
 */
public class DSIfcUtil {
	
	private static final Log logger = LogFactory.getLog(DSIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	
	
	
	
	/**
	 * DS登录
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * DsResResult
	 */
	public static DsResResult  login(Map<String, String> params) {
		DsResResult result = null;
		try {
			logger.info("DS【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/ds/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DsResResult.class);
			}
			logger.info("DS【登录账号】结束");
		} catch (Exception e) {
			logger.info("BB【I登录账号】失败",e);
		}
		return result;
	}

	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * DsResResult
	 */
	public static DsResResult register(Map<String, String> params){
		DsResResult result = null;
		try {
			logger.info("DS【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/ds/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DsResResult.class);
			}
			logger.info("DS【注册账号】结束");
		} catch (Exception e) {
			logger.info("BB【I注册账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * 查询余额
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * DsResResult
	 */
	public static DsResResult balance(Map<String, String> params) {
		DsResResult result = null;
		try {
			logger.info("DS【查詢余额】接口开始");
			String respJson = HttpClientUtil.post(url + "/ds/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DsResResult.class);
			}
			logger.info("DS【查詢余额】接口结束");
		} catch (Exception e) {
			logger.info("DS【查詢余额】接口失败",e);
		}
		return result;
	}
	
	/**
	 * 提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * DsResResult
	 */
	public static DsResResult withdrawal(Map<String, String> params) throws Exception {
		DsResResult result = null;
		try {
			logger.info("DS【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/ds/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DsResResult.class);
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new DsResResult();
				result.setErrorCode(Conts.RESULT_CREDIT_LACK);
				result.setErrorMessage(json.getString("message"));
			}
			logger.info("DS【提款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 存款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * DsResResult
	 */
	public static DsResResult deposit(Map<String, String> params) throws Exception {
		DsResResult result = null;
		try {
			logger.info("DS【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/ds/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DsResResult.class);
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new DsResResult();
				result.setErrorCode(Conts.RESULT_CREDIT_LACK);
				result.setErrorMessage(json.getString("message"));
			}
			logger.info("DS【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * DS账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registDsAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME, userName);
		if (userName.length() > 12) {
			userName = userName.substring(0, 10);// 截取前10位
		}
		String password = StringHelper.getCharAndNumr(32);// 随机字母与数字组合10位，小写
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号ds:" + userName);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(DsConts.USERNAME,  webUserFlag+userName);
		jsonObj.put(DsConts.PASSWORD, password);
		jsonObj.put(DsConts.CURRENCY, DsConts.CNY);
		jsonObj.put(DsConts.LANGUAGE, DsConts.LANG_CN);
		jsonObj.put(DsConts.LINE, 0);
		registerParams.put("params", jsonObj.toString());
		registerParams.put(Conts.WEB_USER_NAME, userName);
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT,  webUserFlat.getUserAgent());
		registerParams.put(DsConts.USERNAME, webUserFlag+userName);
		registerParams.put(DsConts.PASSWORD,password);
		
		DsResResult result =DSIfcUtil.register(registerParams);
		if (result != null) {
			if (result.getErrorCode() == DsConts.RES_CODE_6606 || result.getErrorCode() == DsConts.RES_CODE_6609 || result.getErrorCode() == DsConts.RES_CODE_6610) {// 帐号已经存在
				String tmpUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
				jsonObj.put(DsConts.USERNAME, webUserFlag+tmpUserName);
				registerParams.put("params", jsonObj.toString());
				registerParams.put(DsConts.USERNAME, webUserFlag+tmpUserName);
				result = DSIfcUtil.register(registerParams);
				if (result != null && result.getErrorCode() == DsConts.RES_CODE_0) {
					webUserFlat.setDsUserName(webUserFlag + tmpUserName);
					webUserFlat.setDsPassword(password);
					webUserFlat.setDsStatus(1);
				}
			} else if (result.getErrorCode() == DsConts.RES_CODE_0) {// 成功
				webUserFlat.setDsUserName(webUserFlag + userName);
				webUserFlat.setDsPassword(password);
				webUserFlat.setDsStatus(1);
			}
			webUserFlat.setReturnCode(result.getErrorCode());
		}
		return webUserFlat;
	}

}
