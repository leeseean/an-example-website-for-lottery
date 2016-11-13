/**   
* 文件名称: ActivityInfoServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午4:29:21<br/>
*/  
package com.mh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.ActivityInfoDao;
import com.mh.entity.ActivityInfo;
import com.mh.service.ActivityInfoService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午4:29:21<br/>
 */
@Service
public class ActivityInfoServiceImpl implements ActivityInfoService{
	
	@Autowired
	private ActivityInfoDao activityInfoDao;
	
	
	/**
	 * 判断活动是否存在
	 * 方法描述: TODO</br> 
	 * @return  
	 * ActivityInfo
	 */
	public boolean isExistsActivity(Integer id){
		return this.activityInfoDao.isExistsActivity(id);
	}
	
	
	
	/**
	 * 获取活动信息
	 * 方法描述: TODO</br> 
	 * @return  
	 * ActivityInfo
	 */
	public ActivityInfo getActivityInfo(){
		List<ActivityInfo> list = this.activityInfoDao.loadAll();
		ActivityInfo activityInfo = null;
		if(list!=null && list.size()>0){
			if(list.size()==1){
				activityInfo = list.get(0);
			}
		}
		return activityInfo;
	}
	

}
