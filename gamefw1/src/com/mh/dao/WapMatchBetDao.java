package com.mh.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.TWebMatchDetail;
import com.mh.entity.WebRecords;
@SuppressWarnings("rawtypes")
@Repository
public class WapMatchBetDao extends BaseDao 
{
	/**
	 * 获取注单列表
	 * 方法描述: TODO</br> 
	 * @param betUserName
	 * @return  
	 * List<TWebMatchBet>
	 */
	@SuppressWarnings("unchecked")
	public List<TWebMatchBet> getWebMatchBetListByBetName(WebRecords records)
	{
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("select * from  T_WEB_MATCH_BET where 1=1");
		if(!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime()))
		{
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='"+records.getStartTime()+"' and STR_TO_DATE(order_time,'%Y-%m-%d') <='"+records.getEndTime()+"' ");
		}
		else if(!StringUtils.isEmpty(records.getStartTime()))
		{
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='"+records.getStartTime()+"' ");
		}
		else if(!StringUtils.isEmpty(records.getEndTime()))
		{
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d') <='"+records.getEndTime()+"' ");
		}
		if(!StringUtils.isEmpty(records.getUserName()))
		{
			sqlBuff.append(" and bet_user_name='" + records.getUserName() + "' ");
		}
		if(!StringUtils.isEmpty(records.getBetStatus()))
		{
			sqlBuff.append(" and FIND_IN_SET(status, '"+records.getBetStatus()+"') ");
		}
		if(!StringUtils.isEmpty(records.getBetSportType()))
		{
			sqlBuff.append(" and bet_sport_type='"+records.getBetSportType().toUpperCase()+"' and bet_sport_type='"+records.getBetSportType().toLowerCase()+"' ");
		}
		
		sqlBuff.append("  order by id desc ");
		final String sql = sqlBuff.toString();
		List<TWebMatchBet> list =this.getHibernateTemplate().executeFind(new HibernateCallback() 
		{
			public Object doInHibernate(Session session)
			{
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TWebMatchBet.class);
				return sqlQuery.list();
			}
		});
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<TWebMatchBet> getSportOrder(final WebRecords records)
	{
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("select * from  T_WEB_MATCH_BET where 1=1");
		if(!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime()))
		{
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='"+records.getStartTime()+"' and STR_TO_DATE(order_time,'%Y-%m-%d') <='"+records.getEndTime()+"' ");
		}
		else if(!StringUtils.isEmpty(records.getStartTime()))
		{
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='"+records.getStartTime()+"' ");
		}
		else if(!StringUtils.isEmpty(records.getEndTime()))
		{
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d') <='"+records.getEndTime()+"' ");
		}
		if(!StringUtils.isEmpty(records.getUserName()))
		{
			sqlBuff.append(" and bet_user_name='" + records.getUserName() + "' ");
		}
		if(!StringUtils.isEmpty(records.getBetStatus()))
		{
			sqlBuff.append(" and FIND_IN_SET(status, '"+records.getBetStatus()+"') ");
		}
		if(!StringUtils.isEmpty(records.getBetSportType()))
		{
			sqlBuff.append(" and bet_sport_type='"+records.getBetSportType().toUpperCase()+"' and bet_sport_type='"+records.getBetSportType().toLowerCase()+"' ");
		}
		
		sqlBuff.append("  order by id desc ");
		final String sql = sqlBuff.toString();
		List<TWebMatchBet> list =this.getHibernateTemplate().executeFind(new HibernateCallback() 
		{
			public Object doInHibernate(Session session)
			{
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TWebMatchBet.class);
				sqlQuery.setFirstResult(0);
				sqlQuery.setMaxResults(records.getCount());
				return sqlQuery.list();
			}
		});
		return list;
	}

	
	@SuppressWarnings("unchecked")
	public List<TWebMatchDetail> getWebMatchBetDetailByBetWagersId(String betWagersId)
	{
		String hql = "from TWebMatchDetail where betWagersId=? ";		
		return this.find(hql, new Object[]{betWagersId});
	}
}
