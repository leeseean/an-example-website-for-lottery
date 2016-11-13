/**   
* 文件名称: ActivityPrizeService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-18 上午3:24:56<br/>
*/  
package com.mh.service;

import java.util.List;

import com.mh.entity.ActivityPrize;

/** 
 * 
 * 奖品Service接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-18 上午3:24:56<br/>
 */
public interface ActivityPrizeService {

	/***
	 * 获取奖品列表
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<ActivityPrize>
	 */
	public List<ActivityPrize> getActivityPrize();
}
