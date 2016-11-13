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
import com.mh.ifc.http.AgConts;
import com.mh.ifc.http.AgResResult;
import com.mh.ifc.http.Conts;
import com.mh.ifc.util.StringHelper;

/** 
 * AG接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午2:01:58<br/>
 */
public class AGIfcUtil {
	
	private static final Log logger = LogFactory.getLog(AGIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);

	private static String webUserFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
	
  
	
	public static AgResResult  login(Map<String, String> params) throws Exception {
		AgResResult result = null;
		try {
			logger.info("AG【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/ag/agent/api/forwardGame", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AgResResult.class);
			}
			logger.info("AG【登录账号】结束");
		} catch (Exception e) {
			logger.info("BB【I登录账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 注册账号
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * AgResResult
	 */
	public static AgResResult register(Map<String, String> params){
		AgResResult result = null;
		try {
			logger.info("AG【注册账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/ag/agent/api/checkOrCreateGameAccount", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AgResResult.class);
			}
			logger.info("AG【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("AG【I注册账号】失败",e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * 查询余额
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * AgResResult
	 */
	public static AgResResult balance(Map<String, String> params) {
		AgResResult result = null;
		try {
			logger.info("查詢AG余额接口开始");
			String respJson = HttpClientUtil.post(url + "/ag/agent/api/getBalance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AgResResult.class);
				result.setWebUserFlat(webUserFlag);// 返回网站标识信息
			}
			logger.info("查詢AG余额接口结束");
		} catch (Exception e) {
			logger.info("查詢AG余额接口失败",e);
		}
		return result;
	}
	
	
	
	/**
	 * 预备提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * AgResResult
	 */
	public static AgResResult agAgentEdu(Map<String, String> params) {
		AgResResult result = null;
		try {
			logger.info("AG【提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/ag/agent/api/prepareTransferCredit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AgResResult.class);
			}
			logger.info("AG【提款】接口结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 确认提款接口
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return
	 * @throws Exception  
	 * AgResResult
	 */
	public static AgResResult agAgentEduConfirm(Map<String, String> params) throws Exception {
		AgResResult result = null;
		try {
			logger.info("AG【确认提款】接口开始");
			String respJson = HttpClientUtil.post(url + "/ag/agent/api/transferCreditConfirm", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AgResResult.class);
			} else if(StringUtils.equals(Conts.RESULT_9911000, json.getString("code"))){
				result = new AgResResult();
				result.setInfo(json.getString("code"));
				result.setMsg(json.getString("message"));
			}
			logger.info("AG【确认提款】接口结束");
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
	 * AgResResult
	 */
	public static AgResResult deposit(Map<String, String> params) throws Exception {
		AgResResult result = null;
		try {
			logger.info("AG【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/bbin/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), AgResResult.class);
			}  
			logger.info("AG【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	
	/**
	 * AG账户注册
	 * 方法描述: TODO</br> 
	 * @param webUserFlat
	 * @param userName
	 * @param userAgent
	 * @return
	 * @throws Exception  
	 * WebUserFlat
	 */
	public static WebUserFlat registAgAccout(WebUserFlat webUserFlat) throws Exception {
		int flatUserSize=10;
		String userName = webUserFlat.getUserName();
		String webUserFlag =webUserFlat.getUserFlag();
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME, userName);
		if (userName.length() > flatUserSize - webUserFlag.length()) {
			userName = userName.substring(0, flatUserSize - webUserFlag.length());// 截取前八位
		}
		registerParams.put(AgConts.LOGINNAME, webUserFlag + userName);// 游戏帐号(3-10),主帐号4-10位
		String password = StringHelper.getCharAndNumr(10);// 随机字母与数字组合10位，小写
		registerParams.put(AgConts.PASSWORD, password);// 2.游戏密码少于20個字母,主帐号4-10位
		registerParams.put(AgConts.ACTYPE, AgConts.ACTYPE_1);// 0试玩帐号 1真玩帐号
		registerParams.put(AgConts.ODDTYPE, AgConts.ODDTYPE_A);// 4
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		AgResResult result =AGIfcUtil.register(registerParams);
		if (result != null) {
			if (StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())) {// 成功
				webUserFlat.setAgUserName(webUserFlag + userName);
				webUserFlat.setAgPassword(password);
				webUserFlat.setAgStatus(1);
			} else {
				String tmpUserName = StringHelper.getCharAndNumr(10);//
				registerParams.put(AgConts.LOGINNAME, webUserFlag+tmpUserName);
				result =AGIfcUtil.register(registerParams);
				if (result != null && StringUtils.equalsIgnoreCase(AgConts.SUCCESS, result.getInfo())) {
					webUserFlat.setAgUserName(webUserFlag + tmpUserName);
					webUserFlat.setAgPassword(password);
					webUserFlat.setAgStatus(1);
				}
			}

		}
		return webUserFlat;
	}

	
	
}
