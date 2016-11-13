/**   
* 文件名称: WebUserWithdrawDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 下午2:06:48<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.constants.WebConstants;
import com.mh.commons.orm.BaseDao;
import com.mh.entity.WebUserWithdraw;

/** 
 * 类描述: TODO<br/>出款记录Dao
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 下午2:06:48<br/>
 */

@SuppressWarnings("all")
@Repository
public class WebUserWithdrawDao  extends BaseDao<WebUserWithdraw,Integer>{

	
	/**
	 * 获取出款流水列表
	 * 方法描述: TODO</br> 
	 * @param huikuan
	 * @return  
	 * List<WebUserWithdraw>
	 */
	public List<WebUserWithdraw> getWebUserWithdrawList(WebUserWithdraw userWithdraw){
		List<Object> list = new ArrayList<Object>();
		
		String hql = " from WebUserWithdraw where userName=? ";
		list.add(userWithdraw.getUserName());
		
		if(!StringUtils.isEmpty(userWithdraw.getBeginDateStr()) && !StringUtils.isEmpty(userWithdraw.getEndDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d')>= ? and STR_TO_DATE(createTime,'%Y-%m-%d') <= ?";
			list.add(userWithdraw.getBeginDateStr());
			list.add(userWithdraw.getEndDateStr());
		}else if(!StringUtils.isEmpty(userWithdraw.getBeginDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d')>= ?  ";
			list.add(userWithdraw.getBeginDateStr());
		}else if(!StringUtils.isEmpty(userWithdraw.getEndDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d') <= ?";
			list.add(userWithdraw.getEndDateStr());
		}
		if(userWithdraw.getWithdrawType() !=null && userWithdraw.getWithdrawType()!=0){
			hql += " and withdrawType=? ";
			list.add(userWithdraw.getWithdrawType());
		}
		hql += " order by createTime desc ";
		
		return this.find(hql, list.toArray());
		
		
	}
	
	public Map<String, Integer> countWithdrawSuccessTimes(WebUserWithdraw userWithdraw){
		List<Object> list = new ArrayList<Object>();
		String sql = " SELECT  MAX(total_times) AS totalTimes, MAX(day_times) AS dayTimes FROM t_web_user_withdraw  WHERE 1 = 1 ";
		if(!StringUtils.isEmpty(userWithdraw.getUserName())){
			sql +=" and user_name=? ";
			list.add(userWithdraw.getUserName());
		}
		sql +=" and withdraw_type=? ";
		list.add(WebConstants.T_WEB_MA_TYPE_11);// 会员提款
		
		sql +=" and check_status=? ";
		list.add(WebConstants.T_WEB_USER_WITHDRAW_CHECKED_STATUS_1);// 审核通过
		
		Map<String, Object> resultMap = this.getJdbcTemplate().queryForMap(sql, list.toArray());
		
		int totalTimes = 0;
		if(null!=resultMap && null != resultMap.get("totalTimes")){
			totalTimes  = (Integer)resultMap.get("totalTimes");
		}
		
		if(StringUtils.isNotEmpty(userWithdraw.getBeginDateStr())){
			sql += " and STR_TO_DATE(create_time,'%Y-%m-%d')=? ";
			list.add(userWithdraw.getBeginDateStr());
		}
		
		resultMap = this.getJdbcTemplate().queryForMap(sql, list.toArray());
		
		int dayTimes = 0;
		if(null!=resultMap && null != resultMap.get("dayTimes")){
			dayTimes  = (Integer)resultMap.get("dayTimes");
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("totalTimes", totalTimes);
		map.put("dayTimes", dayTimes);
		
		return map;
	}
}
