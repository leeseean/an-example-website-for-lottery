/**   
* 文件名称: ActivityWinningListServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-19 上午5:10:04<br/>
*/  
package com.mh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.orm.Page;
import com.mh.dao.ActivityWinningListDao;
import com.mh.service.ActivityWinningListService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-19 上午5:10:04<br/>
 */
@SuppressWarnings("all")
@Service
public class ActivityWinningListServiceImpl implements ActivityWinningListService{
	
	@Autowired
	private ActivityWinningListDao activityWinningListDao;
	
	
	/**
	 * 获取中奖名单列表
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getWinningList(String userName){
		return this.activityWinningListDao.getWinningList(userName);
	}
	
	
	
	/**
	 * 获取中奖名单列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param userName
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, String userName){
		return this.activityWinningListDao.findPage(page, userName);
	}

}
