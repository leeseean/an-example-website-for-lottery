/**   
* 文件名称: ActivityInfoDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午4:07:28<br/>
*/  
package com.mh.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.ActivityInfo;

/** 
 * 
 * 活动信息Dao接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午4:07:28<br/>
 */

@Repository
public class ActivityInfoDao  extends BaseDao<ActivityInfo, Integer> {
	
 
	
	
	/**
	 * 获取活动信息
	 * 方法描述: TODO</br> 
	 * @return  
	 * ActivityInfo
	 */
	public boolean isExistsActivity(Integer id){
		Date currDate = new Date();
		String currDateStr = DateUtil.format(currDate, DateUtil.YMDHMS_PATTERN);
		String sql = "select t.* from t_activity_info t where t.begin_time>='"+currDateStr+"' and t.end_time<='"+currDateStr+"' and t.id="+id;
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list!=null && list.size()>0){
			return true;
		}
	 
		return false;
		
	}
	

}
