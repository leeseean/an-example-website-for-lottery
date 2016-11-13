/**   
* 文件名称: ABIfcUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-11-21 下午6:47:10<br/>
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
import com.mh.ifc.http.GdConts;
import com.mh.ifc.http.GdResResult;
import com.mh.ifc.util.StringHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-11-21 下午6:47:10<br/>
 */
public class GDIfcUtil {
	
private static final Log logger = LogFactory.getLog(GDIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	/**
	 * 登录系统
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * GdResResult
	 */
	public static GdResResult  login(Map<String, String> params) throws Exception {
		GdResResult result = null;
		try {
			logger.info("GD【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/gd/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), GdResResult.class);
			}
			logger.info("GD【登录账号】结束");
		} catch (Exception e) {
			logger.info("GD【登录账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * OsResResult
	 */
	public static GdResResult register(Map<String, String> params){
		GdResResult result = null;
		try {
			logger.info("GD【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/gd/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), GdResResult.class);
			}
			logger.info("GD【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("GD【注册账号】失败",e);
		}
		return result;
	}
	
	/**
	 * 
	 * 查询余额
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * GdResResult
	 */
	public static GdResResult balance(Map<String, String> params) {
		GdResResult result = null;
		try {
			logger.info("查詢GD余额接口开始");
			String respJson = HttpClientUtil.post(url + "/gd/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), GdResResult.class);
				//result.setWebUserFlat(webUserFlag);// 返回网站标识信息
			}
			logger.info("查詢GD余额接口结束");
		} catch (Exception e) {
			logger.info("查詢GD余额接口失败",e);
		}
		return result;
	}
	
	/**
	 * 存款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * GdResResult
	 */
	public static GdResResult deposit(Map<String, String> params) throws Exception {
		GdResResult result = null;
		try {
			logger.info("GD【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/gd/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), GdResResult.class);
			} else if(Conts.RESULT_9911000.equals(json.getString("code"))) {
				result = new GdResResult();
				result.setErrorCode(Conts.RESULT_9911000);
				result.setErrorMessage(json.getString("message"));
			}
			logger.info("GD【存款】接口结束");
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
	 * GdResResult
	 */
	public static GdResResult withdrawal(Map<String, String> params) throws Exception {
		GdResResult result = null;
		try {
			logger.info("GD【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/gd/agent/api/withdrawal", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), GdResResult.class);
			} else if(Conts.RESULT_9911000.equals(json.getString("code"))) {
				result = new GdResResult();
				result.setErrorCode(Conts.RESULT_9911000);
				result.setErrorMessage(json.getString("message"));
			}
			logger.info("GD【提款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	
	
	
	/**
	 * OG账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registGdAccout(WebUserFlat webUserFlat) throws Exception {
		int maxUserSize=15;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > maxUserSize - webUserFlag.length()) {
			userName = userName.substring(0, maxUserSize - webUserFlag.length());// 截取前八位
		}
		registerParams.put(GdConts.USERID, webUserFlag + userName);
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号GD:" + userName);
		GdResResult result = GDIfcUtil.register(registerParams);
		if (result != null) {
			if (GdConts.RES_CODE_0.equals(result.getErrorCode()) ) {// 成功
				webUserFlat.setGdUserName(webUserFlag + userName);
				webUserFlat.setGdStatus(1);
			}else{
				String tmpObUserName = StringHelper.getCharAndNumr(maxUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(GdConts.USERID, webUserFlag + tmpObUserName);
				result = GDIfcUtil.register(registerParams);
				if (result != null && GdConts.RES_CODE_0.equals(result.getErrorCode()) ) {
					webUserFlat.setGdUserName(webUserFlag + tmpObUserName);
					webUserFlat.setGdStatus(1);
				}
			}
		}
		return webUserFlat;
	}

	
}
