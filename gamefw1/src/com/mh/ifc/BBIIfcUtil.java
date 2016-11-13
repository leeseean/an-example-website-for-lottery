/**   
* 文件名称: BbiUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-3 下午2:01:58<br/>
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
import com.mh.ifc.http.BbinConts;
import com.mh.ifc.http.BbinResResult;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.MGConts;
import com.mh.ifc.util.StringHelper;

/** 
 * BBI接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:01:58<br/>
 */
public class BBIIfcUtil {
	
	private static final Log logger = LogFactory.getLog(BBIIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);

	
	
	public static BbinResResult  login(Map<String, String> params) throws Exception {
		BbinResResult result = null;
		try {
			logger.info("BBI【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/bbin/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), BbinResResult.class);
			}
			logger.info("BBI【登录账号】结束");
		} catch (Exception e) {
			logger.info("BB【I登录账号】失败",e);
		}
		return result;
	}
 
	
	
	/**
	 * 登录账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * BbinResResult
	 */
	public static BbinResResult register(Map<String, String> params){
		BbinResResult result = null;
		try {
			logger.info("BBI【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/bbin/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), BbinResResult.class);
			}
			logger.info("BBI【登录账号】结束");
		} catch (Exception e) {
			logger.info("BB【I登录账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * 查询余额
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * BbinResResult
	 */
	public static BbinResResult balance(Map<String, String> params) {
		BbinResResult result = null;
		try {
			logger.info("BBI【查詢余额】接口开始");
			String respJson = HttpClientUtil.post(url + "/bbin/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), BbinResResult.class);
			}
			logger.info("BBI【查詢余额】接口结束");
		} catch (Exception e) {
			logger.info("BBI【查詢余额】接口失败",e);
		}
		return result;
	}
	
	/**
	 * 提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * BbinResResult
	 */
	public static BbinResResult withdrawal(Map<String, String> params) throws Exception {
		BbinResResult result = null;
		try {
			logger.info("BBI【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/bbin/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), BbinResResult.class);
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new BbinResResult();
				result.setCode(json.getIntValue("code"));
				result.setMessage(json.getString("message"));
			}
			logger.info("BBI【提款】接口结束");
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
	 * BbinResResult
	 */
	public static BbinResResult deposit(Map<String, String> params) throws Exception {
		BbinResResult result = null;
		try {
			logger.info("BBI【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/bbin/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), BbinResResult.class);
			}else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new BbinResResult();
				result.setCode(json.getIntValue("code"));
				result.setMessage(json.getString("message"));
			}
			logger.info("BBI【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * BBI账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registBbinAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();

		registerParams.put(Conts.WEB_USER_NAME, userName);
		
		if (userName.length() > flatUserSize - webUserFlag.length()) {
			userName = userName.substring(0, flatUserSize - webUserFlag.length());// 截取前八位
		}
		registerParams.put(BbinConts.USERNAME, webUserFlag + userName);// 游戏帐号(3-10),主帐号4-10位
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号bbin:" + userName);
		BbinResResult result = BBIIfcUtil.register(registerParams);
		if (result != null) {
			if (result.getCode() == MGConts.RES_CODE_7) {// 帐号已经存在
				String tmpMgUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(MGConts.USERNAME, webUserFlag +tmpMgUserName);
				result = BBIIfcUtil.register(registerParams);
				if (result != null && result.getCode() == MGConts.RES_CODE_0) {
					webUserFlat.setBbinUserName(webUserFlag + tmpMgUserName);
					webUserFlat.setBbinStatus(1);
				}
			} else if (result.getCode() == MGConts.RES_CODE_0) {// 成功
				webUserFlat.setBbinUserName(webUserFlag + userName);
				webUserFlat.setBbinStatus(1);
			}
			webUserFlat.setReturnCode(result.getCode());
		}
		return webUserFlat;
	}
	
	
}
