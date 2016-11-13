/**   
* 文件名称: ActivityPrizeRule.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午2:24:56<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.ActivityRule;

/** 
 * 活动规则Dao接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午2:24:56<br/>
 */
@SuppressWarnings("all")
@Repository
public class ActivityPrizeRuleDao extends BaseDao<ActivityRule, Integer> {
	
	/**
	 * 查询活动规则
	 * @Description: TODO
	 * @param    
	 * @return List<ActivityRule>
	 * @author Andy
	 * @date 2015-9-18
	 */
	public List<ActivityRule> findRuleList(){
		List<ActivityRule> list=new ArrayList<ActivityRule>();
		StringBuffer hql=new StringBuffer(" from ActivityRule a where 1=1 ");
		hql.append(" order by a.id desc");
		list=this.getHibernateTemplate().find(hql.toString());
		return list;
	}
}
