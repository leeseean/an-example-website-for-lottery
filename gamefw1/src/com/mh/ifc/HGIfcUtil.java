/**   
* 文件名称: HgIfcUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-3 下午2:03:00<br/>
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
import com.mh.ifc.http.HgConts;
import com.mh.ifc.http.HgResResult;
import com.mh.ifc.http.MGConts;
import com.mh.ifc.util.StringHelper;

/** 
 * HG接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:03:00<br/>
 */
public class HGIfcUtil {
	

	private static final Log logger = LogFactory.getLog(HGIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	
	
	public static HgResResult  login(Map<String, String> params) throws Exception {
		HgResResult result = null;
		try {
			logger.info("HG【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/hg/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), HgResResult.class);
			}
			logger.info("HG【登录账号】结束");
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
	 * HgResResult
	 */
	public static HgResResult register(Map<String, String> params){
		HgResResult result = null;
		try {
			logger.info("HG【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/hg/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), HgResResult.class);
			}
			logger.info("HG【注册账号】结束");
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
	 * HgResResult
	 */
	public static HgResResult balance(Map<String, String> params) {
		HgResResult result = null;
		try {
			logger.info("HG【查詢余额】接口开始");
			String respJson = HttpClientUtil.post(url + "/hg/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), HgResResult.class);
			}
			logger.info("HG【查詢余额】接口结束");
		} catch (Exception e) {
			logger.info("HG【查詢余额】接口失败",e);
		}
		return result;
	}
	
	/**
	 * 提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * HgResResult
	 */
	public static HgResResult withdrawal(Map<String, String> params) throws Exception {
		HgResResult result = null;
		try {
			logger.info("HG【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/hg/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), HgResResult.class);
			}
			logger.info("HG【提款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 预备转帐接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * HgResResult
	 */
	public static HgResResult deposit(Map<String, String> params) throws Exception {
		HgResResult result = null;
		try {
			logger.info("HG【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/hg/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), HgResResult.class);
			}  
			logger.info("HG【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * 确认转帐接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * HgResResult
	 */
	public static HgResResult depositConfirm(Map<String, String> params) throws Exception {
		HgResResult result = null;
		try {
			logger.info("HG【确认转账】接口开始");
			String respJson = HttpClientUtil.post(url + "/hg/agent/api/depositConfirm", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), HgResResult.class);
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new HgResResult();
				result.setStatus(Conts.RESULT_CREDIT_LACK);
				result.setErrdesc(json.getString("message"));
			}
			logger.info("HG【确认转账】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	

	/**
	 * 确定存款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * HgResResult
	 */
	public static HgResResult withdrawalConfirml(Map<String, String> params) throws Exception {
		HgResResult result = null;
		try {
			logger.info("HG【确定存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/hg/agent/api/withdrawalConfirml", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), HgResResult.class);
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new HgResResult();
				result.setStatus(Conts.RESULT_CREDIT_LACK);
				result.setErrdesc(json.getString("message"));
			}
			logger.info("HG【确定存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * HG账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registHgAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > flatUserSize - webUserFlag.length()) {
			userName = userName.substring(0, flatUserSize - webUserFlag.length());// 截取前八位
		}
 
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(HgConts.USERNAME, webUserFlag + userName);
		jsonObj.put(HgConts.MODE, HgConts.LOGIN_MODE);
		jsonObj.put(HgConts.FIRSTNAME,webUserFlag + userName);
		jsonObj.put(HgConts.LASTNAME, webUserFlag);
		jsonObj.put(HgConts.CURRENCYID, HgConts.CURRENCY_RMB);

		registerParams.put("params", jsonObj.toString());

		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		registerParams.put(HgConts.USERNAME,webUserFlag + userName);
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号Hg:" + userName);
		HgResResult result = HGIfcUtil.register(registerParams);
		if (result != null) {
			if (result.getStatus() == HgConts.RES_CODE_0) {// 成功
				webUserFlat.setHgUserName(webUserFlag + userName);
				webUserFlat.setHgStatus(1);
			}else{
				String tmpMgUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(MGConts.USERNAME, webUserFlag+tmpMgUserName);
				jsonObj.put(HgConts.USERNAME, webUserFlag + tmpMgUserName);
				jsonObj.put(HgConts.FIRSTNAME,webUserFlag + tmpMgUserName);
				registerParams.put("params", jsonObj.toString());
				result = HGIfcUtil.register(registerParams);
				if (result != null && result.getStatus() == HgConts.RES_CODE_0) {
					webUserFlat.setHgUserName(webUserFlag + tmpMgUserName);
					webUserFlat.setHgStatus(1);
				}
			}
			webUserFlat.setReturnCode(result.getStatus());
		}
		return webUserFlat;
	}

}
