/**   
* 文件名称: ActivityLogServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-18 上午5:03:01<br/>
*/  
package com.mh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.ActivityLogDao;
import com.mh.service.ActivityLogService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-18 上午5:03:01<br/>
 */
@Service
public class ActivityLogServiceImpl implements ActivityLogService{
	
	@Autowired
	private ActivityLogDao activityLogDao;
	
	
	
	/**
	 * 获取已中奖奖品数
	 * 方法描述: TODO</br> 
	 * @param prizeId
	 * @return  
	 * int
	 */
	public int getActivityPrizeNum(Integer prizeId,String beginTimeStr,String endTimeStr){
		return this.activityLogDao.getActivityPrizeNum(prizeId, beginTimeStr, endTimeStr);
	}

}
