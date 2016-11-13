/**   
* 文件名称: ActivityLogDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午2:19:21<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.ActivityLog;

/** 
 * 活动日志
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午2:19:21<br/>
 */

@Repository
public class ActivityLogDao extends BaseDao<ActivityLog, Integer> {
	
	
	/**
	 * 获取已中奖奖品数
	 * 方法描述: TODO</br> 
	 * @param prizeId
	 * @return  
	 * int
	 */
	public int getActivityPrizeNum(Integer prizeId,String beginTimeStr,String endTimeStr){
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from t_activity_log where prize_id=? and is_winning=1 ";
		list.add(prizeId);
		/** 美东时间 **/
		if (StringUtils.isNotBlank(beginTimeStr) && StringUtils.isNotBlank(endTimeStr)) {
			sql += " and date_format(create_time,'%Y-%m-%d') >= ?";
			list.add(beginTimeStr);
			sql += " and date_format(create_time,'%Y-%m-%d') <= ?";
			list.add(endTimeStr);
		}else if (StringUtils.isNotBlank(beginTimeStr)) {
			sql += " and date_format(create_time,'%Y-%m-%d') >= ?";
			list.add(beginTimeStr);
		}else if (StringUtils.isNotBlank(endTimeStr)) {
			sql += " and date_format(create_time,'%Y-%m-%d') <= ?";
			list.add(endTimeStr);
		}
		
		return this.getJdbcTemplate().queryForInt(sql,list.toArray());
		
	}

}
