/**   
* 文件名称: PTUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-3 下午2:04:10<br/>
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
import com.mh.ifc.http.MGConts;
import com.mh.ifc.http.PtConts;
import com.mh.ifc.http.PtResResult;
import com.mh.ifc.util.StringHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:04:10<br/>
 */
public class PTIfcUtil {
	private static final Log logger = LogFactory.getLog(PTIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);

	/**
	 * PT登录
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * NtResResult
	 */
	public static PtResResult  login(Map<String, String> params) throws Exception {
		PtResResult result = null;
		try {
			logger.info("PT【真人登录】开始");
			String respJson = HttpClientUtil.post(url + "/pt/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), PtResResult.class);
			}
			logger.info("PT【真人登录】结束");
		} catch (Exception e) {
			logger.info("PT【真人登录】失败",e);
		}
		return result;
	}
 
	
	
	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * PtResResult
	 */
	public static PtResResult register(Map<String, String> params){
		PtResResult result = null;
		try {
			logger.info("PT【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/pt/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), PtResResult.class);
			}
			logger.info("PT【注册账号】结束");
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
	 * PtResResult
	 */
	public static PtResResult balance(Map<String, String> params) {
		PtResResult result = null;
		try {
			logger.info("PT【查詢余额】接口开始");
			String respJson = HttpClientUtil.post(url + "/pt/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), PtResResult.class);
			}
			logger.info("PT【查詢余额】接口结束");
		} catch (Exception e) {
			logger.info("PT【查詢余额】接口失败",e);
		}
		return result;
	}
	
	/**
	 * 提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * PtResResult
	 */
	public static PtResResult withdrawal(Map<String, String> params) throws Exception {
		PtResResult result = null;
		try {
			logger.info("PT【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/pt/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), PtResResult.class);
			}
			logger.info("PT【提款】接口结束");
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
	 * PtResResult
	 */
	public static PtResResult deposit(Map<String, String> params) throws Exception {
		PtResResult result = null;
		try {
			logger.info("PT【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/pt/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), PtResResult.class);
			}
			logger.info("PT【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * PT账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registPtAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
 

		registerParams.put(Conts.WEB_USER_NAME, userName);
		if (userName.length() > flatUserSize - webUserFlag.length()) {
			userName = userName.substring(0, flatUserSize - webUserFlag.length());// 截取前八位
		}
		registerParams.put(PtConts.USER_ID, webUserFlag+""+userName);// 游戏帐号(6-10),主帐号4-10位
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号PT:" + userName);
		PtResResult result = PTIfcUtil.register(registerParams);
		if (result != null) {
			if ( result.getErrorCode() == PtConts.RES_CODE_0) {// 帐号已经存在
				webUserFlat.setPtUserName(webUserFlag + userName);
				webUserFlat.setPtStatus(1);
			} else{// 成功
				String tmpMgUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(MGConts.USERNAME, webUserFlag +tmpMgUserName);
				result = PTIfcUtil.register(registerParams);
				if (result != null && result.getErrorCode() == PtConts.RES_CODE_0) {
					webUserFlat.setPtUserName(webUserFlag + tmpMgUserName);
					webUserFlat.setPtStatus(1);
				}
			}
			webUserFlat.setReturnCode(result.getErrorCode());
		}
		return webUserFlat;
	}
	
}
