/**   
* 文件名称: ActivityUserDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-18 上午4:02:20<br/>
*/  
package com.mh.dao;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.ActivityUser;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-18 上午4:02:20<br/>
 */

@Repository
public class ActivityUserDao extends BaseDao<ActivityUser, Integer> {
	
	/**
	 * 根据用户名获取活动用户
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * ActivityUser
	 */
	public boolean isLotteryUser(String userName){
		String sql = "select count(*) from t_activity_user where user_name=? ";
		int total = this.getJdbcTemplate().queryForInt(sql, new Object[]{userName});
		if(total>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取抽奖次数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * int
	 */
	public int getLotteryNums(String userName){
		String sql = "select lott_times from t_activity_user where user_name=? ";
		return this.getJdbcTemplate().queryForInt(sql,new Object[]{userName});
	}
	
	
	
	/**
	 * 更新抽奖次数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * int
	 */
	public int updateActivityUser(String userName){
		String sql = " update t_activity_user set lott_times=lott_times-1 where  user_name=? and lott_times>0 ";
		return this.getJdbcTemplate().update(sql, new Object[]{userName});
	}
	

}
