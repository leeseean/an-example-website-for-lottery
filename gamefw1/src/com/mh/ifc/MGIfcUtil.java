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
import com.mh.ifc.http.MGConts;
import com.mh.ifc.http.MgResResult;
import com.mh.ifc.util.StringHelper;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:03:38<br/>
 */
public class MGIfcUtil {

	private static final Log logger = LogFactory.getLog(MGIfcUtil.class);

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
	public static MgResResult  loginelectronic(Map<String, String> params) throws Exception {
		MgResResult result = null;
		try {
			logger.info("MG【MG真人登录】开始");
			String respJson = HttpClientUtil.post(url + "/mg/agent/api/loginelectronic", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), MgResResult.class);
			}
			logger.info("MG【MG真人登录】结束");
		} catch (Exception e) {
			logger.info("MG【MG真人登录】失败",e);
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
	public static MgResResult  loginlive(Map<String, String> params) throws Exception {
		MgResResult result = null;
		try {
			logger.info("MG【MG真人登录】开始");
			String respJson = HttpClientUtil.post(url + "/mg/agent/api/loginlive", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), MgResResult.class);
			}
			logger.info("MG【MG真人登录】结束");
		} catch (Exception e) {
			logger.info("MG【MG真人登录】失败",e);
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
	public static MgResResult register(Map<String, String> params){
		MgResResult result = null;
		try {
			logger.info("MG【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/mg/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), MgResResult.class);
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
	public static MgResResult balance(Map<String, String> params) {
		MgResResult result = null;
		try {
			logger.info("查詢MG余额接口开始");
			params.put(MGConts.LANG, MGConts.LANG_ZH_CN);
			String respJson = HttpClientUtil.post(url + "/mg/agent/api/balance", params);
			JSONObject json = JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), MgResResult.class);
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
	public static MgResResult withdrawal(Map<String, String> params) throws Exception {
		MgResResult result = null;
		try {
			logger.info("MG【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/mg/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), MgResResult.class);
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
	public static MgResResult deposit(Map<String, String> params) throws Exception {
		MgResResult result = null;
		try {
			logger.info("MG【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/mg/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), MgResResult.class);
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
	public static WebUserFlat registMgAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME, userName);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		if (userName.length() > flatUserSize - webUserFlag.length()) {
			userName = userName.substring(0, flatUserSize - webUserFlag.length());// 截取前八位
		}

		registerParams.put(MGConts.USERNAME,webUserFlag +userName);// 游戏帐号(3-10),主帐号4-10位
		String password = StringHelper.getCharAndNumr(10);// 随机字母与数字组合10位，小写
		registerParams.put(MGConts.PASSWORD, password);// 游戏密码(3-12)
		
		
		registerParams.put(MGConts.LANG, MGConts.LANG_ZH_CN);// 语言
		registerParams.put(MGConts.MOBILEPHONE,CommonConstant.resCommMap.get(CommonConstant.MG_ACCOUNT_MOBILE));// 手机号码
		registerParams.put(MGConts.EMAIL, CommonConstant.resCommMap.get(CommonConstant.MG_ACCOUNT_EMAIL));// 手机号码

		registerParams.put(Conts.WEB_USER_FLAG, webUserFlat.getUserFlag());// 网站标识
		
		

		MgResResult result = MGIfcUtil.register(registerParams);
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号MG:" + userName+"密码为："+password);
 
		if (result != null) {
			if (result.getCode() == MGConts.RES_CODE_7) {// 帐号已经存在
				String tmpMgUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(MGConts.USERNAME, webUserFlag +tmpMgUserName);
				result = MGIfcUtil.register(registerParams);
				if (result != null && result.getCode() == MGConts.RES_CODE_0) {
					webUserFlat.setMgUserName(webUserFlag + tmpMgUserName);
					webUserFlat.setMgPassword(password);
					webUserFlat.setMgStatus(1);
				}
			} else if (result.getCode() == MGConts.RES_CODE_0) {// 成功
				webUserFlat.setMgUserName(webUserFlag + userName);
				webUserFlat.setMgPassword(password);
				webUserFlat.setMgStatus(1);
			}
			webUserFlat.setReturnCode(result.getCode());
		}
		return webUserFlat;
	}
	
 
}
