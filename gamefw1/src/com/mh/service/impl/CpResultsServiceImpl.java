/**   
* 文件名称: ResultsServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-4 上午10:42:21<br/>
*/  
package com.mh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.orm.Page;
import com.mh.dao.CpResultsDao;
import com.mh.entity.CpHistoryResults;
import com.mh.entity.CpResults;
import com.mh.entity.CpTomResults;
import com.mh.service.CpResultsService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-4 上午10:42:21<br/>
 */
@Service
public class CpResultsServiceImpl implements CpResultsService{

	@Autowired
	private CpResultsDao cpResultsDao;
	
	/**
	 * 根据code查询下一期开奖结果
	 * @param results
	 * @param code
	 * @return
	 */
	public CpTomResults getNextResults(String code) {
		return this.cpResultsDao.getNextResults(code);
	}
 
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public CpResults findResultByCode(String code) throws Exception{
		return this.cpResultsDao.findResultByCode(code);
	}
 
	
	/**
	 * 开奖结果列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param results
	 * @return  
	 * Page
	 * @throws Throwable 
	 */
	@SuppressWarnings("rawtypes")
	public Page findPage(Page page, CpResults results) {
		return this.cpResultsDao.findPage(page, results);
	}

	
	/**
	 * 获取历史开奖结果
	 * 方法描述: TODO</br> 
	 * @param gameCode
	 * @param rows
	 * @return  
	 * Map<String,String>
	 */
	public List<CpHistoryResults> findHistoryResultsMap(String gameCode,String rows){
		return this.cpResultsDao.findHistoryResultsMap(gameCode, rows);
	}
	
	
 

}
