/**   
* 文件名称: ConfigDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-4 下午2:54:51<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import app.xb.cmbase.model.CpConfig;

import com.mh.commons.cache.CpConfigCache;

/** 
 * 类描述: TODO<br/>配置表
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-4 下午2:54:51<br/>
 */
@Repository
public class CpConfigDao{
	/***
	 * 根据各类别查询所需配置信息
	 * @Description: TODO
	 * @param    
	 * @return List<CpConfig>
	 * @author Andy
	 * @date 2015-8-10
	 */
	public List<CpConfig> getConfigListForCode(String gameTypeCode,String cpTypeCode,String cpCateCode,String xzlxCode) {

		Map<String,List<CpConfig>> configMap = CpConfigCache.RATE_CACHE_KEY;
		
		List<CpConfig> list = new ArrayList<CpConfig>(configMap.get(gameTypeCode+"-"+cpTypeCode));
		if(StringUtils.isEmpty(cpCateCode) && StringUtils.isEmpty(xzlxCode)){
			Collections.sort(list, new Comparator<CpConfig>() {
				public int compare(CpConfig o1, CpConfig o2) {
					return o1.getPxh().compareTo(o2.getPxh());
				}
			});
			return list;
		}
		
		List<CpConfig> resList = new ArrayList<CpConfig>();
		Iterator<CpConfig> iterator = list.iterator();
		while (iterator.hasNext()) {
			CpConfig cpConfig = iterator.next();
			if(cpConfig.getCpCateCode().equals(cpCateCode) && !resList.contains(cpConfig)){
				resList.add(cpConfig);
			}
			if(cpConfig.getXzlxCode().equals(xzlxCode) && !resList.contains(cpConfig)){
				resList.add(cpConfig);
			}
		}
		Collections.sort(resList, new Comparator<CpConfig>() {
			public int compare(CpConfig o1, CpConfig o2) {
				return o1.getPxh().compareTo(o2.getPxh());
			}
		});
		return resList;
	}
	
	
	
	
	public Map<String,CpConfig> getConfigTypeMap(String gameTypeCode,String cpTypeCode,String cpCateCode){
		Map<String,CpConfig> map = new HashMap<String,CpConfig>();
		Map<String,List<CpConfig>> configMap = CpConfigCache.RATE_CACHE_KEY;
		List<CpConfig> list_temp = new ArrayList<CpConfig>();
		
		List<CpConfig> list = new ArrayList<CpConfig>(configMap.get(gameTypeCode+"-"+cpTypeCode));
		if(StringUtils.isNotEmpty(cpCateCode)){
			Iterator<CpConfig> iterator = list.iterator();
			while (iterator.hasNext()) {
				CpConfig cpConfig = iterator.next();
				if(cpConfig.getCpCateCode().equals(cpCateCode) && !list_temp.contains(cpConfig)){
					list_temp.add(cpConfig);
				}
			}
		}else{
			list_temp.addAll(list);
		}
		for(CpConfig config:list_temp){
			map.put(String.valueOf(config.getId()), config);
		}
		return map;
	}
	
	
 
	/**
	 * 根据id数组获取所有配置信息
	 */
	public Map<String, CpConfig> getCpConfigAllByIds(String ids,String gameTypeCode,String cpTypeCode){
		List<CpConfig> valList = new ArrayList<CpConfig>();
		
		String[] id = ids.split(",");
		List<CpConfig> list = CpConfigCache.RATE_CACHE_KEY.get(gameTypeCode+"-"+cpTypeCode);
		for (CpConfig cpConfig : list) {
			for (int i = 0; i < id.length; i++) {
				if((int)cpConfig.getId() == (int)Integer.parseInt(id[i])){
					valList.add(cpConfig);
				}
			}
		}
		Map<String,CpConfig> map = new HashMap<String,CpConfig>();
		for(CpConfig config:valList){
			map.put(String.valueOf(config.getId()), config);
		}
		return map;
	}
	
	/***
	 * 根据各类别下注号码查询所需配置信息
	 * @Description: TODO
	 * @param    
	 * @return List<CpConfig>
	 * @author Andy
	 * @date 2015-8-10
	 */
	public List<CpConfig> getConfigListForNumber(String gameTypeCode,String cpTypeCode,String cpCateCode,String xzlxCode,String number) {
		Map<String,List<CpConfig>> configMap = CpConfigCache.RATE_CACHE_KEY;
		
		List<CpConfig> list = new ArrayList<CpConfig>(configMap.get(gameTypeCode+"-"+cpTypeCode));
		if(StringUtils.isEmpty(cpCateCode) && StringUtils.isEmpty(xzlxCode) && StringUtils.isEmpty(number)){
			Collections.sort(list, new Comparator<CpConfig>() {
				public int compare(CpConfig o1, CpConfig o2) {
					return o1.getPxh().compareTo(o2.getPxh());
				}
			});
			return list;
		}
		
		List<CpConfig> resList = new ArrayList<CpConfig>();
		Iterator<CpConfig> iterator = list.iterator();
		while (iterator.hasNext()) {
			CpConfig cpConfig = iterator.next();
			if(cpConfig.getCpCateCode().equals(cpCateCode) && !resList.contains(cpConfig)){
				resList.add(cpConfig);
			}
			if(cpConfig.getXzlxCode().equals(xzlxCode) && !resList.contains(cpConfig)){
				resList.add(cpConfig);
			}
			if(cpConfig.getNumber().equals(number) && !resList.contains(cpConfig)){
				resList.add(cpConfig);
			}
		}
		Collections.sort(resList, new Comparator<CpConfig>() {
			public int compare(CpConfig o1, CpConfig o2) {
				return o1.getPxh().compareTo(o2.getPxh());
			}
		});
		return resList;
	}
	
	
}
