/**   
* 文件名称: ActivityUserServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-18 上午4:26:16<br/>
*/  
package com.mh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.ActivityUserDao;
import com.mh.service.ActivityUserService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-18 上午4:26:16<br/>
 */

@Service
public class ActivityUserServiceImpl implements ActivityUserService{

	@Autowired
	private ActivityUserDao activityUserDao;
	
	
	/**
	 * 获取抽奖次数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * int
	 */
	public int getLotteryNums(String userName){
		return this.activityUserDao.getLotteryNums(userName);
	}
	
	
	/**
	 * 根据用户名获取活动用户
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * ActivityUser
	 */
	public boolean isLotteryUser(String userName){
		return this.activityUserDao.isLotteryUser(userName);
	}
	
	
	/**
	 * 更新抽奖次数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * int
	 */
	public int updateActivityUser(String userName){
		return this.activityUserDao.updateActivityUser(userName);
	}
}
