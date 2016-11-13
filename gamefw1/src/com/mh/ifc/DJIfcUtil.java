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
import com.mh.ifc.http.DjConts;
import com.mh.ifc.http.DjResResult;
import com.mh.ifc.util.StringHelper;

/** 
 * Douji接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:03:00<br/>
 */
public class DJIfcUtil {
	

	private static final Log logger = LogFactory.getLog(DJIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	
	
	public static DjResResult login(Map<String, String> params) throws Exception {
		DjResResult result = null;
		try {
			logger.info("Douji【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/douji/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DjResResult.class);
			}
			logger.info("Douji【登录账号】结束");
		} catch (Exception e) {
			logger.info("Douji【登录账号】失败",e);
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
	public static DjResResult register(Map<String, String> params){
		DjResResult result = null;
		try {
			logger.info("Douji【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/douji/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DjResResult.class);
			}
			logger.info("Douji【注册账号】结束");
		} catch (Exception e) {
			logger.info("Douji【I注册账号】失败",e);
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
	public static DjResResult balance(Map<String, String> params) {
		DjResResult result = null;
		try {
			logger.info("Douji【查詢余额】接口开始");
			String respJson = HttpClientUtil.post(url + "/douji/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DjResResult.class);
			}
			logger.info("Douji【查詢余额】接口结束");
		} catch (Exception e) {
			logger.info("Douji【查詢余额】接口失败",e);
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
	public static DjResResult withdrawal(Map<String, String> params) throws Exception {
		DjResResult result = null;
		try {
			logger.info("Douji【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/douji/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DjResResult.class);
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new DjResResult();
				result.setStatus_code(Conts.RESULT_9911000);
			}
			logger.info("Douji【提款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 转帐接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * HgResResult
	 */
	public static DjResResult deposit(Map<String, String> params) throws Exception {
		DjResResult result = null;
		try {
			logger.info("Douji【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/douji/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), DjResResult.class);
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new DjResResult();
				result.setStatus_code(Conts.RESULT_9911000);
			}
			logger.info("Douji【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	/**
	 * Douji账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registDJAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > flatUserSize - webUserFlag.length()) {
			userName = userName.substring(0, flatUserSize - webUserFlag.length());// 截取前八位
		}
		String password = StringHelper.getCharAndNumr(20);// 随机字母与数字组合20位，小写
		registerParams.put(DjConts.USERNAME, webUserFlag + userName);
		registerParams.put(DjConts.PASSWORD, password);

		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号Douji:" + userName);
		DjResResult result = DJIfcUtil.register(registerParams);
		if (result != null) {
			if (DjConts.RES_CODE_00.equals(result.getStatus_code()) ) {// 成功
				webUserFlat.setDjUserName(webUserFlag + userName);
				webUserFlat.setDjStatus(1);
				webUserFlat.setDjPassword(password);
			}else{
				String tmpDsUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(DjConts.USERNAME, webUserFlag + tmpDsUserName);
				registerParams.put(DjConts.PASSWORD, password);
				result = DJIfcUtil.register(registerParams);
				if (result != null && DjConts.RES_CODE_00.equals(result.getStatus_code())) {
					webUserFlat.setDjUserName(webUserFlag + tmpDsUserName);
					webUserFlat.setDjStatus(1);
					webUserFlat.setDjPassword(password);
				}
			}
		}
		return webUserFlat;
	}

}
