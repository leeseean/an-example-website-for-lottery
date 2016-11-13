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
import com.mh.ifc.http.NBBinConts;
import com.mh.ifc.http.NBbinResResult;
import com.mh.ifc.util.StringHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-11-21 下午6:47:10<br/>
 */
public class NBBINIfcUtil {
	
private static final Log logger = LogFactory.getLog(NBBINIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);

	/**
	 * 登录系统
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * OsResResult
	 */
	public static NBbinResResult login(Map<String, String> params) throws Exception {
		NBbinResResult result = null;
		try {
			logger.info("NBBIN【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/nbbin/agent/api/login", params);
			JSONObject json = JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NBbinResResult.class);
			}
			logger.info("NBBIN【登录账号】结束");
		} catch (Exception e) {
			logger.info("NBBIN【登录账号】失败",e);
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
	public static NBbinResResult register(Map<String, String> params){
		NBbinResResult result = null;
		try {
			logger.info("NBBIN【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/nbbin/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NBbinResResult.class);
			}
			logger.info("NBBIN【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("NBBIN【注册账号】失败",e);
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
	public static NBbinResResult balance(Map<String, String> params) {
		NBbinResResult result = null;
		try {
			logger.info("查詢NBBIN余额接口开始");
			String respJson = HttpClientUtil.post(url + "/nbbin/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NBbinResResult.class);
			}
			logger.info("查詢NBBIN余额接口结束");
		} catch (Exception e) {
			logger.info("查詢NBBIN余额接口失败",e);
		}
		return result;
	}
	
	/**
	 * 存款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * NBbinResResult
	 */
	public static NBbinResResult transferMoney(Map<String, String> params) throws Exception {
		NBbinResResult result = null;
		try {
			logger.info("NBBIN【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/nbbin/agent/api/transfer", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), NBbinResResult.class);
			} else if(Conts.RESULT_9911000.equals(json.getString("code"))) {
				result = new NBbinResResult();
				result.setCode(Conts.RESULT_9911000);
				result.setMessage(json.getString("message"));
			}
			logger.info("NBBIN【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	/**
	 * NBBIN账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registNBBINAccout(WebUserFlat webUserFlat) throws Exception {
		int maxUserSize=16;
		String userName = webUserFlat.getUserName();
		String webUserFlag = webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > maxUserSize - webUserFlag.length()) {
			userName = userName.substring(0, maxUserSize - webUserFlag.length());// 截取前14位
		}
		registerParams.put(NBBinConts.USERNAME, NBBinConts.opPrefix + webUserFlag + userName);

		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号NBBIN:" + userName);
		NBbinResResult result = NBBINIfcUtil.register(registerParams);
		if (result != null) {
			if (result.getResult()) {// 成功
				webUserFlat.setBbinUserName(NBBinConts.opPrefix + webUserFlag + userName);
				webUserFlat.setBbinStatus(1);
			}else{
				String tmpObUserName = StringHelper.getCharAndNumr(10);// 留两位给网站标识
				registerParams.put(NBBinConts.USERNAME, NBBinConts.opPrefix +webUserFlag + tmpObUserName);
				result = NBBINIfcUtil.register(registerParams);
				if (result != null && result.getResult() ) {
					webUserFlat.setBbinUserName(NBBinConts.opPrefix +webUserFlag + tmpObUserName);
					webUserFlat.setBbinStatus(1);
				}
			}
		}
		return webUserFlat;
	}

	
}
