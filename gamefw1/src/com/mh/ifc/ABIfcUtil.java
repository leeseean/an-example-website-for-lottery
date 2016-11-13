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
import com.mh.ifc.http.AbConts;
import com.mh.ifc.http.AbResResult;
import com.mh.ifc.http.Conts;
import com.mh.ifc.util.StringHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-11-21 下午6:47:10<br/>
 */
public class ABIfcUtil {
	
private static final Log logger = LogFactory.getLog(ABIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	/**
	 * 登录系统
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * AbResResult
	 */
	public static AbResResult  login(Map<String, String> params) throws Exception {
		AbResResult result = null;
		try {
			logger.info("AB【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/ab/agent/api/login", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AbResResult.class);
			}
			logger.info("AB【登录账号】结束");
		} catch (Exception e) {
			logger.info("AB【登录账号】失败",e);
		}
		return result;
	}
	
	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * AbResResult
	 */
	public static AbResResult register(Map<String, String> params){
		AbResResult result = null;
		try {
			logger.info("AB【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/ab/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AbResResult.class);
			}
			logger.info("AB【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("AB【注册账号】失败",e);
		}
		return result;
	}
	
	/**
	 * 
	 * 查询余额
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * AbResResult
	 */
	public static AbResResult balance(Map<String, String> params) {
		AbResResult result = null;
		try {
			logger.info("查詢AB余额接口开始");
			String respJson = HttpClientUtil.post(url + "/ab/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AbResResult.class);
				//result.setWebUserFlat(webUserFlag);// 返回网站标识信息
			}
			logger.info("查詢AB余额接口结束");
		} catch (Exception e) {
			logger.info("查詢AB余额接口失败",e);
		}
		return result;
	}
	
	
	
	/**
	 * 存款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * AbResResult
	 */
	public static AbResResult transferCredit(Map<String, String> params) throws Exception {
		AbResResult result = null;
		try {
			logger.info("AB【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/ab/agent/api/transferCredit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AbResResult.class);
			} else if(StringUtils.equals("9911000", json.getString("code"))){
				result = new AbResResult();
				result.setError_code(json.getString("code"));
				result.setMessage(json.getString("message"));
			}
			logger.info("AB【存款】接口结束");
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
	public static WebUserFlat registAbAccout(WebUserFlat webUserFlat) throws Exception {
		int maxUserSize=12;
		int minUserSize = 5;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > maxUserSize - webUserFlag.length()) {
			userName = userName.substring(0, maxUserSize - webUserFlag.length());// 截取前八位
		}
		
		if(userName.length() < minUserSize - webUserFlag.length()){
			userName = userName + "___";
		}
		String password = StringHelper.getCharAndNumr(10);// 随机字母与数字组合20位，小写
		registerParams.put(AbConts.USERNAME, webUserFlag + userName);
		registerParams.put(AbConts.PASSWORD, password);

		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号AB:" + userName);
		AbResResult result = ABIfcUtil.register(registerParams);
		if (result != null) {
			if (AbConts.RES_OK.equals(result.getError_code()) ) {// 成功
				webUserFlat.setAbUserName(webUserFlag + userName);
				webUserFlat.setAbStatus(1);
				webUserFlat.setAbPassword(password);
			}else{
				String tmpMgUserName = StringHelper.getCharAndNumr(10 - webUserFlag.length());// 留两位给网站标识
				registerParams.put(AbConts.USERNAME, webUserFlag + tmpMgUserName);
				registerParams.put(AbConts.PASSWORD, password);
				result = ABIfcUtil.register(registerParams);
				if (result != null && AbConts.RES_OK.equals(result.getError_code()) ) {
					webUserFlat.setAbUserName(webUserFlag + tmpMgUserName);
					webUserFlat.setAbStatus(1);
					webUserFlat.setAbPassword(password);
				}
			}
		}
		return webUserFlat;
	}
	
	
	

}
