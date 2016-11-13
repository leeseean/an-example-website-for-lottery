/**   
* 文件名称: ActivityPrizeDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午2:23:59<br/>
*/  
package com.mh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.ActivityPrize;

/** 
 * 奖品Dao接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午2:23:59<br/>
 */
@SuppressWarnings("all")
@Repository
public class ActivityPrizeDao  extends BaseDao<ActivityPrize, Integer> {
	
	
	/***
	 * 获取奖品列表
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<ActivityPrize>
	 */
	public List<ActivityPrize> getActivityPrize(){
		String hql = "from ActivityPrize order by pxh";
		return this.getHibernateTemplate().find(hql);
	}

}
