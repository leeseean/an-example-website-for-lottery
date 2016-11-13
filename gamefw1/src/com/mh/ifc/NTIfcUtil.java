/**   
* 文件名称: NTUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-3 下午2:03:57<br/>
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
import com.mh.ifc.http.NtConts;
import com.mh.ifc.http.NtResResult;
import com.mh.ifc.util.StringHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:03:57<br/>
 */
public class NTIfcUtil {
	private static final Log logger = LogFactory.getLog(NTIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	
	
	/**
	 * NT登录
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * NtResResult
	 */
	public static NtResResult  login(Map<String, String> params) throws Exception {
		NtResResult result = null;
		try {
			logger.info("NT【真人登录】开始");
			String respJson = HttpClientUtil.post(url + "/nt/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NtResResult.class);
			}
			logger.info("NT【真人登录】结束");
		} catch (Exception e) {
			logger.info("NT【真人登录】失败",e);
		}
		return result;
	}
	

	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * NtResResult
	 */
	public static NtResResult register(Map<String, String> params){
		NtResResult result = null;
		try {
			logger.info("NT【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/nt/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NtResResult.class);
			}
			logger.info("NT【注册账号】结束");
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
	 * NtResResult
	 */
	public static NtResResult balance(Map<String, String> params) {
		NtResResult result = null;
		try {
			logger.info("NT【查詢余额】接口开始");
			String respJson = HttpClientUtil.post(url + "/nt/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NtResResult.class);
			}
			logger.info("NT【查詢余额】接口结束");
		} catch (Exception e) {
			logger.info("NT【查詢余额】接口失败",e);
		}
		return result;
	}
	
	/**
	 * 提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * NtResResult
	 */
	public static NtResResult withdrawal(Map<String, String> params) throws Exception {
		NtResResult result = null;
		try {
			logger.info("NT【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/nt/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NtResResult.class);
			} else if(Conts.RESULT_9911000.equals(json.getString("code"))) {
				result = new NtResResult();
				result.setErrorCode(Conts.RESULT_CREDIT_LACK);
				result.setErrorMessage(json.getString("message"));
			}
			logger.info("NT【提款】接口结束");
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
	 * NtResResult
	 */
	public static NtResResult deposit(Map<String, String> params) throws Exception {
		NtResResult result = null;
		try {
			logger.info("NT【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/nt/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NtResResult.class);
			} else if(Conts.RESULT_9911000.equals(json.getString("code"))) {
				result = new NtResResult();
				result.setErrorCode(Conts.RESULT_CREDIT_LACK);
				result.setErrorMessage(json.getString("message"));
			}
			logger.info("NT【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * NT账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registNtAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME, userName);
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		if (userName.length() > flatUserSize - webUserFlag.length()) {
			userName = userName.substring(0, flatUserSize - webUserFlag.length());// 截取前八位
		}
 
		registerParams.put(NtConts.USER_ID, webUserFlag + userName);// 游戏帐号(6-10),主帐号4-10位
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号NT:" + userName);
		NtResResult result = NTIfcUtil.register(registerParams);
		if (result != null) {
				
			if (result != null && result.getErrorCode() == NtConts.RES_CODE_0)  {// 成功
				webUserFlat.setNtUserName(webUserFlag + userName);
				webUserFlat.setNtStatus(1);
			}else{
				String tmpMgUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(NtConts.USER_ID, webUserFlag +tmpMgUserName);
				result = NTIfcUtil.register(registerParams);
				if (result != null && result.getErrorCode() == NtConts.RES_CODE_0) {
					webUserFlat.setNtUserName(webUserFlag + tmpMgUserName);
					webUserFlat.setNtStatus(1);
				}
			}
			webUserFlat.setReturnCode(result.getErrorCode());
		}
		return webUserFlat;
	}

}
