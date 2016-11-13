/**   
* 文件名称: ActivityWinningListService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-19 上午2:35:58<br/>
*/  
package com.mh.service;

import java.util.List;
import java.util.Map;

import com.mh.commons.orm.Page;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-19 上午2:35:58<br/>
 */
@SuppressWarnings("all")
public interface ActivityWinningListService {
	
	/**
	 * 获取中奖名单列表
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getWinningList(String userName);
	
	
	
	/**
	 * 获取中奖名单列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param userName
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, String userName);

}
