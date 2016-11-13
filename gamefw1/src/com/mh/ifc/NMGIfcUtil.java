/**   
 * 文件名称: MGUtil.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-7-3 下午2:03:38<br/>
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
import com.mh.ifc.http.NMGConts;
import com.mh.ifc.http.NMgResResult;
import com.mh.ifc.util.StringHelper;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:03:38<br/>
 */
public class NMGIfcUtil {

	private static final Log logger = LogFactory.getLog(NMGIfcUtil.class);

	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);

	private static String webUserFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);

	
	
	/**
	 * MG游戏登陆
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * MgResResult
	 */
	public static NMgResResult  loginelectronic(Map<String, String> params) throws Exception {
		NMgResResult result = null;
		try {
			logger.info("【MG电子登录】开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/loginelectronic", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
			}
			logger.info("【MG电子登录】结束");
		} catch (Exception e) {
			logger.info("【MG电子登录】失败",e);
		}
		return result;
	}
	
	/**
	 * MG游戏登陆
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * MgResResult
	 */
	public static NMgResResult  loginelectronicH5(Map<String, String> params) throws Exception {
		NMgResResult result = null;
		try {
			logger.info("【MG电子登录】开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/loginhtml5", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
			}
			logger.info("【MG电子登录】结束");
		} catch (Exception e) {
			logger.info("【MG电子登录】失败",e);
		}
		return result;
	}
	
	/**
	 * MG游戏登陆
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * MgResResult
	 */
	public static NMgResResult  loginelectronicHtml5(Map<String, String> params) throws Exception {
		NMgResResult result = null;
		try {
			logger.info("【MG电子登录】开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/loginelectronic", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
			}
			logger.info("【MG电子登录】结束");
		} catch (Exception e) {
			logger.info("【MG电子登录】失败",e);
		}
		return result;
	}
	
	
	/**
	 * MG真人登录
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * MgResResult
	 */
	public static NMgResResult  loginlive(Map<String, String> params) throws Exception {
		NMgResResult result = null;
		try {
			logger.info("【MG真人登录】开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/loginlive", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
			}
			logger.info("【MG真人登录】结束");
		} catch (Exception e) {
			logger.info("【MG真人登录】失败",e);
		}
		return result;
	}
	
	
	
	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * BbinResResult
	 */
	public static NMgResResult register(Map<String, String> params){
		NMgResResult result = null;
		try {
			logger.info("MG【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
			}
			logger.info("MG【注册账号】结束");
		} catch (Exception e) {
			logger.info("MG【I注册账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * 查询余额 方法描述: TODO</br>
	 * 
	 * @param params
	 * @return AgResResult
	 */
	public static NMgResResult balance(Map<String, String> params) {
		NMgResResult result = null;
		try {
			logger.info("查詢MG余额接口开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/balance", params);
			JSONObject json = JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
				result.setWebUserFlat(webUserFlag);// 返回网站标识信息
			}
			logger.info("查詢MG余额接口结束");
		} catch (Exception e) {
			logger.info("查詢MG餘額接口失败", e);
		}
		return result;
	}
	
	
	/**
	 * 提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * MgResResult
	 */
	public static NMgResResult withdrawal(Map<String, String> params) throws Exception {
		NMgResResult result = null;
		try {
			logger.info("MG【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
			} else if(Conts.RESULT_9911000.equals(json.getString("code"))) {
				result = new NMgResResult();
				result.setStatusCode(Conts.RESULT_9911000);
				result.setErrorMessage(json.getString("message"));
			}
			logger.info("MG【提款】接口结束");
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
	 * MgResResult
	 */
	public static NMgResResult deposit(Map<String, String> params) throws Exception {
		NMgResResult result = null;
		try {
			logger.info("MG【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
			} else if(Conts.RESULT_9911000.equals(json.getString("code"))) {
				result = new NMgResResult();
				result.setStatusCode(Conts.RESULT_9911000);
				result.setErrorMessage(json.getString("message"));
			}
			logger.info("MG【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	/**
	 * MG账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registNMgAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > flatUserSize - webUserFlag.length()) {
			userName = userName.substring(0, flatUserSize - webUserFlag.length());// 截取前八位
		}
		String password = StringHelper.getCharAndNumr(20);// 随机字母与数字组合20位，小写
		registerParams.put(NMGConts.USERNAME, webUserFlag + userName);
		registerParams.put(NMGConts.PASSWORD, password);

		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号MG:" + userName);
		NMgResResult result = NMGIfcUtil.register(registerParams);
		if (result != null) {
			if (NMGConts.RES_CODE_0.equals(result.getStatusCode()) ) {// 成功
				webUserFlat.setMgUserName(webUserFlag + userName);
				webUserFlat.setMgId(result.getUserId());
				webUserFlat.setMgStatus(1);
				webUserFlat.setMgPassword(password);
			}else{
				String tmpMgUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(NMGConts.USERNAME, webUserFlag + tmpMgUserName);
				registerParams.put(NMGConts.PASSWORD, password);
				result = NMGIfcUtil.register(registerParams);
				if (result != null && NMGConts.RES_CODE_0.equals(result.getStatusCode())) {
					webUserFlat.setMgUserName(webUserFlag + tmpMgUserName);
					webUserFlat.setMgId(result.getUserId());
					webUserFlat.setMgStatus(1);
					webUserFlat.setMgPassword(password);
				}
			}
		}
		return webUserFlat;
	}
	
	
	public static NMgResResult getUserInfo(Map<String, String> params) throws Exception{
		NMgResResult result = null;
		try {
			logger.info("MG【获取用户信息】接口开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/getPlayerDetail", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
			}
			logger.info("MG【获取用户信息】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * 
	 * 查询余额 方法描述: TODO</br>
	 * 
	 * @param params
	 * @return AgResResult
	 */
	public static NMgResResult getUserToken(Map<String, String> params) {
		NMgResResult result = null;
		try {
			logger.info("查詢MG用户信息token接口开始");
			String respJson = HttpClientUtil.post(url + "/nmg/agent/api/getPlayerToken", params);
			JSONObject json = JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NMgResResult.class);
				result.setWebUserFlat(webUserFlag);// 返回网站标识信息
			}
			logger.info("查詢MG用户信息token接口结束");
		} catch (Exception e) {
			logger.info("查詢MG用户信息token接口失败", e);
		}
		return result;
	}
	
 
}
