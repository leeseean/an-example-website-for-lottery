package com.mh.ifc;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.utils.HttpClientUtil;

public class NNTIfcUtil {
	private static final Log logger = LogFactory.getLog(NNTIfcUtil.class);
	private static String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
	
	/**
	 * 创建分组
	 * 方法描述: TODO</br> 
	 * @param params
	 * @return  
	 * SbtResBean
	 */
	public static String  createGroup(Map<String, String> params){
		try {
			logger.info("新NT【创建分组】开始");
			String respJson = HttpClientUtil.post(url + "/nnt/agent/api/createGroup", params);
			JSONObject json =JSON.parseObject(respJson);
			logger.info("新NT【创建分组】结束");
			return json.getString("code");
		} catch (Exception e) {
			logger.info("新NT【创建分组】失败",e);
		}
		return null;
	}
}
