/**   
 * 文件名称: WebMatchBetDao.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-7-17 下午3:02:54<br/>
 */
package com.mh.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.TWebMatchDetail;
import com.mh.entity.WebRecords;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-17 下午3:02:54<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebMatchBetDao extends BaseDao<TWebMatchBet, Integer> {

	/**
	 * 获取注单列表 方法描述: TODO</br>
	 * 
	 * @param betUserName
	 * @return List<TWebMatchBet>
	 */
	public List<TWebMatchBet> getWebMatchBetListByBetName(final WebRecords records) {

		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("select * from  T_WEB_MATCH_BET where 1=1");
		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='" + records.getStartTime() + "' and STR_TO_DATE(order_time,'%Y-%m-%d') <='" + records.getEndTime() + "' ");
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='" + records.getStartTime() + "' ");
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d') <='" + records.getEndTime() + "' ");
		}
		if (!StringUtils.isEmpty(records.getUserName())) {
			sqlBuff.append(" and bet_user_name='" + records.getUserName() + "' ");
		}
		if (!StringUtils.isEmpty(records.getBetStatus())) {
			sqlBuff.append(" and FIND_IN_SET(status, '" + records.getBetStatus() + "') ");
		}

		sqlBuff.append("  order by id desc ");
		final String sql = sqlBuff.toString();
		List<TWebMatchBet> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(sql).addEntity(TWebMatchBet.class);
				return sqlQuery.list();
			}
		});
		return list;
	}
	
	/**
	 * 体育注单查询
	 * 
	 * @param page
	 * @param records
	 * @return
	 */
	public Page findSportRecordPage(Page page, WebRecords records) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("select * from  T_WEB_MATCH_BET where 1=1 ");
		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>=? and STR_TO_DATE(order_time,'%Y-%m-%d') <=? ");
			list.add(records.getStartTime());
			list.add(records.getEndTime());
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='" + records.getStartTime() + "' ");
			list.add(records.getStartTime());
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d') <='" + records.getEndTime() + "' ");
			list.add(records.getEndTime());
		}
		if (!StringUtils.isEmpty(records.getUserName())) {
			sqlBuff.append(" and bet_user_name = ? ");
			list.add(records.getUserName());

		}
		if (!StringUtils.isEmpty(records.getBetStatus())) {
			sqlBuff.append(" and FIND_IN_SET(status, ?) ");
			list.add(records.getBetStatus());
		}

		sqlBuff.append("  order by id desc ");
		page = this.findPageBySql(page, sqlBuff.toString(), list.toArray());
		List<TWebMatchBet> betList = page.getResult();
		for(int i=0;i<betList.size();i++){
			TWebMatchBet bet = betList.get(i);
			List<TWebMatchDetail> detailList = this.getWebMatchBetDetailByBetWagersId(bet.getBetWagersId());
			bet.setDetails(detailList);
		}		
				
		return page;
	}

	public List<TWebMatchDetail> getWebMatchBetDetailByBetWagersId(String betWagersId) {
		String hql = "from TWebMatchDetail where betWagersId=? ";
		return this.find(hql, new Object[] { betWagersId });

	}
}
