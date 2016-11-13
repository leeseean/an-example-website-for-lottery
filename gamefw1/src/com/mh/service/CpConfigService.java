/**   
* 文件名称: ConfigService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-4 下午2:56:16<br/>
*/  
package com.mh.service;

import java.util.List;
import java.util.Map;

import app.xb.cmbase.model.CpConfig;

/** 
 * 类描述: TODO<br/>配置Service
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-4 下午2:56:16<br/>
 */
public interface CpConfigService {
	
	/***
	 * 根据各类别查询所需配置信息
	 * 
	 * @Description: TODO
	 * @param
	 * @return List<CpConfig>
	 * @author Andy
	 * @date 2015-8-10
	 */
	public List<CpConfig> getConfigListForCode(
			String gameTypeCode, String cpTypeCode, String cpCateCode,
			String xzlxCode);
	/**
	 * 根据id数组获取所有配置信息
	 */
	public Map<String, CpConfig> getCpConfigAllByIds(String ids,String gameTypeCode,String cpTypeCode);
	
	/***
	 * 根据各类别下注号码查询所需配置信息
	 * @Description: TODO
	 * @param    
	 * @return List<CpConfig>
	 * @author Andy
	 * @date 2015-8-10
	 */
	public List<CpConfig> getConfigListForNumber(String gameTypeCode,String cpTypeCode,String cpCateCode,String xzlxCode,String number) ;
	
	
}
