/**   
* 文件名称: ActivityService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午4:08:21<br/>
*/  
package com.mh.service;

import java.util.List;

import com.mh.entity.ActivityPrize;
import com.mh.entity.ActivityRule;

/** 
 * 活动信息Service接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午4:08:21<br/>
 */
public interface ActivityService {
 

	
	
	/**
	 * 添加活动日志
	 * 方法描述: TODO</br> 
	 * @param activityPrize
	 * @param userName
	 * @param prizeIndex
	 * @param isWinning
	 * @param ip
	 * @param remark  
	 * void
	 */
	public void saveActivity(ActivityPrize activityPrize,String userName,Integer prizeIndex,Integer isWinning,String ip,String remark);
	
	/**
	 * 查询活动规则
	 * @Description: TODO
	 * @param    
	 * @return List<ActivityRule>
	 * @author Andy
	 * @date 2015-9-18
	 */
	public List<ActivityRule> findRuleList();
}
