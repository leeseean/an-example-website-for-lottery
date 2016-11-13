/**   
* 文件名称: WebSportResultsService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-16 下午1:36:27<br/>
*/  
package com.mh.service;

import java.util.List;
import java.util.Map;

import com.mh.entity.TResultMatchBk;
import com.mh.entity.TResultMatchFt;


/** 
 * 结果Dao
 * 类描述: TODO<br/>
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-16 下午1:36:27<br/>
 */
public interface WebSportResultsService {
	/**
	 * 得到篮球赛果
	 * 方法描述: TODO</br> 
	 * @param tableName
	 * @param curTag
	 * @return  
	 * TResultMatchBk
	 */
	public List<TResultMatchBk> getResultBkSport(String serachDate);
	/**
	 * 得到足球赛果
	 * 方法描述: TODO</br> 
	 * @param tableName
	 * @param curTag
	 * @return  
	 * TResultMatchBk
	 */
	public List<TResultMatchFt> getResultFtSport(String serachDate);
	
	
	/**
     * 消息列表
     * 方法描述: TODO</br> 
     * @param dateStr
     * @return  
     * List<Map<String,Object>>
     */
    public List<Map<String,Object>> getMessageList(String dateStr,String fField);
    
    
    /**
     * 获取公告前几条列表
     * 方法描述: TODO</br> 
     * @return  
     * List<String>
     */
    public List<String> getSportMessageLimit();
}
