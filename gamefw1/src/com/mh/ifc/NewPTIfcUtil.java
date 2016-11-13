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
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.utils.HttpClientUtil;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.http.Conts;
import com.mh.ifc.http.MGConts;
import com.mh.ifc.http.NewPtResResult;
import com.mh.ifc.http.PtConts;
import com.mh.ifc.util.StringHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:04:10<br/>
 */
public class NewPTIfcUtil {
	
	private static final Log logger = LogFactory.getLog(NewPTIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	
	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * PtResResult
	 */
	public static NewPtResResult register(Map<String, String> params){
		NewPtResResult ptResResult = null;
		try {
			logger.info("PT【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/newpt/agent/api/register", params);
			logger.info("PT【注册账号】返回报文："+respJson);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				ptResResult = JSON.parseObject(respJson, NewPtResResult.class);
			}
			logger.info("PT【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("PT【I注册账号】失败",e);
		}
		return ptResResult;
	}
	
	
	/**
	 * 
	 * 查询余额
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * PtResResult
	 */
	public static double balance(Map<String, String> params) {
		double amount =0;
		try {
			logger.info("PT【查詢余额】接口开始");
			String respJson = HttpClientUtil.post(url + "/newpt/agent/api/balance", params);
			logger.info("PT【查詢余额】返回报文："+respJson);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				amount = Double.valueOf(json.getString("data"));
			}
			logger.info("PT【查詢余额】接口结束");
		} catch (Exception e) {
			logger.info("PT【查詢余额】接口失败",e);
		}
		return amount;
	}
	
	/**
	 * 提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * PtResResult
	 */
	public static NewPtResResult withdrawal(Map<String, String> params) throws Exception {
		
		NewPtResResult result = new NewPtResResult();
		try {
			logger.info("PT【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/newpt/agent/api/withdrawal", params);
			logger.info("PT【提款】返回报文："+respJson);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				result.setCode("success");
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result.setCode(json.getString("code"));
				result.setMessage(json.getString("message"));
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
	public static NewPtResResult deposit(Map<String, String> params) throws Exception {
		NewPtResResult result = new NewPtResResult();
		try {
			logger.info("PT【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/newpt/agent/api/deposit", params);
			logger.info("PT【存款】返回报文："+respJson);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				result.setCode("success");
			}else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result.setCode(json.getString("code"));
				result.setMessage(json.getString("message"));
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
		String password = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
		registerParams.put(PtConts.USER_ID, webUserFlag+""+userName);// 游戏帐号(6-10),主帐号4-10位
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(PtConts.PASSWORD,password);
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号PT:" + userName);
		NewPtResResult result = NewPTIfcUtil.register(registerParams);
		if (result != null) {// 成功
			if ("000000".equals(result.getCode())) {// 成功
				webUserFlat.setPtUserName(webUserFlag + userName);
				webUserFlat.setPtStatus(1);
				webUserFlat.setPtPassword(password);
			} else{
				if ("19".equals(result.getCode())) {// 帐号已经存在
					String tmpMgUserName = StringHelper.getCharAndNumr(flatUserSize - webUserFlag.length());// 留两位给网站标识
					registerParams.put(MGConts.USERNAME, webUserFlag +tmpMgUserName);
					result = NewPTIfcUtil.register(registerParams);
					if (result != null && "000000".equals(result.getCode())) {
						webUserFlat.setPtUserName(webUserFlag + tmpMgUserName);
						webUserFlat.setPtStatus(1);
						webUserFlat.setPtPassword(password);
					}
				}
			}
			webUserFlat.setReturnCode(Integer.valueOf(result.getCode()));
		}
		return webUserFlat;
	}
	
}
