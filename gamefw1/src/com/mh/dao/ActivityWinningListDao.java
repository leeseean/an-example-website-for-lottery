/**   
* 文件名称: ActivityWinningListDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午2:26:12<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.entity.ActivityWinningList;

/** 
 * 中奖信息DAO
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午2:26:12<br/>
 */
@SuppressWarnings("all")
@Repository
public class ActivityWinningListDao extends BaseDao<ActivityWinningList, Integer> {
	
	/**
	 * 获取中奖名单列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param userName
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, String userName){
		List<Object> list = new ArrayList<Object>();

		String sql = "SELECT t.prize_name,DATE_FORMAT(t.create_time,'%Y-%m-%d') AS lott_time  FROM t_activity_winninglist t where 1=1  ";
		if(!StringUtils.isEmpty(userName)){
			sql += " and t.user_name=? ";
			list.add(userName);
		}
	 
		sql+=" order by t.create_time desc ";
		
		return findPageBySql(page, sql, list.toArray());
	}
	
 
	/**
	 * 获取中奖名单列表
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getWinningList(String userName){
		List<Object> list = new ArrayList<Object>();
		String sql = "select CONCAT(SUBSTRING_INDEX(t.user_name,RIGHT(t.user_name,2),1),'**') AS user_name,t.prize_name,DATE_FORMAT(t.create_time,'%m-%d') AS lott_time from t_activity_winninglist t where 1=1 ";
		if(StringUtils.isEmpty(userName)){
			sql += " order by t.create_time desc limit 50";
		}else{
			sql += " and t.user_name =?";
			list.add(userName);
		}
		
		return this.getJdbcTemplate().queryForList(sql,list.toArray());
	 
	}
	
	
}
