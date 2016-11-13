/**   
* 文件名称: ActivityInfoService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午4:28:39<br/>
*/  
package com.mh.service;

import com.mh.entity.ActivityInfo;

/** 
 * 
 * 活动信息Service接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午4:28:39<br/>
 */
public interface ActivityInfoService {
	
	/**
	 * 判断活动是否存在
	 * 方法描述: TODO</br> 
	 * @return  
	 * ActivityInfo
	 */
	public boolean isExistsActivity(Integer id);
	
	/**
	 * 获取活动信息
	 * 方法描述: TODO</br> 
	 * @return  
	 * ActivityInfo
	 */
	public ActivityInfo getActivityInfo();

}
