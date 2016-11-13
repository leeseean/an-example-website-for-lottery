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
import com.mh.ifc.http.NsbConts;
import com.mh.ifc.http.NsbResResult;
import com.mh.ifc.util.StringHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-11-21 下午6:47:10<br/>
 */
public class NSBIfcUtil {
	
private static final Log logger = LogFactory.getLog(NSBIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);

	/**
	 * 登录系统
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * OsResResult
	 */
	public static NsbResResult login(Map<String, String> params) throws Exception {
		NsbResResult result = null;
		try {
			logger.info("沙巴【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/nsb/agent/api/login", params);
			JSONObject json = JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NsbResResult.class);
			}
			logger.info("沙巴【登录账号】结束");
		} catch (Exception e) {
			logger.info("沙巴【登录账号】失败",e);
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
	public static NsbResResult register(Map<String, String> params){
		NsbResResult result = null;
		try {
			logger.info("沙巴【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/nsb/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NsbResResult.class);
			}
			logger.info("沙巴【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("沙巴【注册账号】失败",e);
		}
		return result;
	}
	
	/**
	 * 
	 * 查询余额
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * OsResResult
	 */
	public static NsbResResult balance(Map<String, String> params) {
		NsbResResult result = null;
		try {
			logger.info("查詢沙巴余额接口开始");
			String respJson = HttpClientUtil.post(url + "/nsb/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NsbResResult.class);
			}
			logger.info("查詢沙巴余额接口结束");
		} catch (Exception e) {
			logger.info("查詢沙巴余额接口失败",e);
		}
		return result;
	}
	
	/**
	 * 存款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * OsResResult
	 */
	public static NsbResResult transferMoney(Map<String, String> params) throws Exception {
		NsbResResult result = null;
		try {
			logger.info("沙巴【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/nsb/agent/api/transferMoney", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NsbResResult.class);
			} else if(Conts.RESULT_9911000.equals(json.getString("code"))) {
				result = new NsbResResult();
				result.setError_code(Conts.RESULT_9911000);
				result.setMessage(json.getString("message"));
			}
			logger.info("沙巴【存款】接口结束");
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
	public static WebUserFlat registSbAccout(WebUserFlat webUserFlat) throws Exception {
		int maxUserSize=20;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > maxUserSize - webUserFlag.length()) {
			userName = userName.substring(0, maxUserSize - webUserFlag.length());// 截取前八位
		}
		registerParams.put(NsbConts.PLAYERNAME, webUserFlag + userName);

		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号沙巴:" + userName);
		NsbResResult result = NSBIfcUtil.register(registerParams);
		if (result != null) {
			if (NsbConts.RES_CODE_0.equals(result.getError_code()) ) {// 成功
				webUserFlat.setSbUserName(webUserFlag + userName);
				webUserFlat.setSbStatus(1);
			}else{
				String tmpObUserName = StringHelper.getCharAndNumr(maxUserSize - webUserFlag.length());// 留两位给网站标识
				registerParams.put(NsbConts.PLAYERNAME, webUserFlag + tmpObUserName);
				result = NSBIfcUtil.register(registerParams);
				if (result != null && NsbConts.RES_CODE_0.equals(result.getError_code()) ) {
					webUserFlat.setSbUserName(webUserFlag + tmpObUserName);
					webUserFlat.setSbStatus(1);
				}
			}
		}
		return webUserFlat;
	}

	
}
