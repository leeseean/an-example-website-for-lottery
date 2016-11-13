/**   
* 文件名称: ActivityPrizeServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-18 上午3:25:34<br/>
*/  
package com.mh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.ActivityPrizeDao;
import com.mh.entity.ActivityPrize;
import com.mh.service.ActivityPrizeService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-18 上午3:25:34<br/>
 */

@Service
public class ActivityPrizeServiceImpl implements ActivityPrizeService{
	
	@Autowired
	private ActivityPrizeDao activityPrizeDao;
	
	/***
	 * 获取奖品列表
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<ActivityPrize>
	 */
	public List<ActivityPrize> getActivityPrize(){
		return this.activityPrizeDao.getActivityPrize();
	}


}
