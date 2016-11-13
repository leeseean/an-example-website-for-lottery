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
import com.mh.ifc.http.VgConts;
import com.mh.ifc.http.VgResBean;
import com.mh.ifc.util.StringHelper;
public class VgIfcUtil {
	
	private static final Log logger = LogFactory.getLog(VgIfcUtil.class);
	
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);

	/**
	 * 登录系统
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * OsResResult
	 */
	public static VgResBean login(Map<String, String> params) throws Exception {
		VgResBean result = null;
		try {
			logger.info("VG【登录账号】开始");
			
			String respJson = HttpClientUtil.post(url + "/vg/agent/api/login", params);
			JSONObject json = JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), VgResBean.class);
			}
			logger.info("VG【登录账号】结束");
		} catch (Exception e) {
			logger.info("VG【登录账号】失败",e);
		}
		return result;
	}
	
	/**
	 * 注册账号
	 */
	public static VgResBean register(Map<String, String> params){
		VgResBean result = null;
		try {
			logger.info("VG【注册账号】开始");
			String respJson = HttpClientUtil.post(url + "/vg/agent/api/register", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), VgResBean.class);
			}
			logger.info("VG【注册账号】结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("VG【注册账号】失败",e);
		}
		return result;
	}
	
	/**
	 * 查询余额
	 */
	public static VgResBean balance(Map<String, String> params) {
		VgResBean result = null;
		try {
			logger.info("查詢VG余额接口开始");
			String respJson = HttpClientUtil.post(url + "/vg/agent/api/balance", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), VgResBean.class);
			}
			logger.info("查詢VG余额接口结束");
		} catch (Exception e) {
			logger.info("查詢VG余额接口失败",e);
		}
		return result;
	}
	
	/**
	 * 存款
	 */
	public static VgResBean deposit(Map<String, String> params) throws Exception {
		VgResBean result = null;
		try {
			logger.info("VG【存款】接口开始");
			String respJson = HttpClientUtil.post(url + "/vg/agent/api/deposit", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), VgResBean.class);
			}
			logger.info("VG【存款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	/**
	 * 取款
	 */
	public static VgResBean withdraw(Map<String, String> params) throws Exception {
		VgResBean result = null;
		try {
			logger.info("VG【取款】接口开始");
			String respJson = HttpClientUtil.post(url + "/vg/agent/api/withdraw", params);
			JSONObject json =JSON.parseObject(respJson);
			if (StringUtils.equals("000000", json.getString("code"))) {
				JSONObject msInfo = json.getJSONObject("msInfo");
				result = JSON.parseObject(msInfo.toString(), VgResBean.class);
			}
			logger.info("VG【取款】接口结束");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	/**
	 * VG账户注册
	 */
	public static WebUserFlat registVgAccout(WebUserFlat webUserFlat) throws Exception {
		int maxUserSize=16;
		String userName = webUserFlat.getUserName();
		String webUserFlag = webUserFlat.getUserFlag();
		
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put(Conts.WEB_USER_NAME,userName);
		if (userName.length() > maxUserSize - webUserFlag.length()) {
			userName = userName.substring(0, maxUserSize - webUserFlag.length());// 截取前14位
		}
		registerParams.put(VgConts.USER_ID, webUserFlag + userName);

		registerParams.put(Conts.WEB_USER_FLAG, webUserFlag);
		registerParams.put(Conts.USER_AGENT, webUserFlat.getUserAgent());
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>要注册的帐号VG:" + userName);
		VgResBean result = VgIfcUtil.register(registerParams);
		if (result != null && StringUtils.equals(result.getErrCode(), VgConts.RES_SUCCESS)) {
			webUserFlat.setVgStatus(1);
			webUserFlat.setVgUserName(webUserFlag + userName);
		}else{
			String tmpObUserName = StringHelper.getCharAndNumr(10);// 留两位给网站标识
			registerParams.put(VgConts.USER_ID, webUserFlag + tmpObUserName);
			result = VgIfcUtil.register(registerParams);
			if (result != null && StringUtils.equals(result.getErrCode(), VgConts.RES_SUCCESS)) {
				webUserFlat.setVgStatus(1);
				webUserFlat.setVgUserName(webUserFlag + tmpObUserName);
			}
		}
		return webUserFlat;
	}
}
