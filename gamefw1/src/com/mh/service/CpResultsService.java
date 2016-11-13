/**   
* 文件名称: ResultsService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-4 上午10:41:47<br/>
*/  
package com.mh.service;

import java.util.List;
import java.util.Map;

import com.mh.commons.orm.Page;
import com.mh.entity.CpHistoryResults;
import com.mh.entity.CpResults;
import com.mh.entity.CpTomResults;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-4 上午10:41:47<br/>
 */
@SuppressWarnings("all")
public interface CpResultsService {
 
	
	/**
	 * 根据code查询下一期开奖结果
	 * @param results
	 * @param code
	 * @return
	 */
	public CpTomResults getNextResults(String code);
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public CpResults findResultByCode(String code) throws Exception;
	 
	
 
	
	/**
	 * 开奖结果列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param results
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, CpResults results);
	
	
	/**
	 * 获取历史开奖结果
	 * 方法描述: TODO</br> 
	 * @param gameCode
	 * @param rows
	 * @return  
	 * Map<String,String>
	 */
	public List<CpHistoryResults> findHistoryResultsMap(String gameCode,String rows);
	
}
