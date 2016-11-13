/**   
* 文件名称: WebBankHuikuanDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 上午11:24:16<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.TWebThirdPay;
import com.mh.entity.WebBank;
import com.mh.entity.WebBankHuikuan;

/** 
 * 类描述: TODO<br/>汇款流水
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 上午11:24:16<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebBankHuikuanDao extends BaseDao<WebBankHuikuan,Integer>{
	
	
	/**
	 * 汇款流水列表
	 * 方法描述: TODO</br> 
	 * @param huikuan
	 * @return  
	 * List<WebBankHuikuan>
	 */
	public List<WebBankHuikuan> getWebBankHuikuanList(WebBankHuikuan huikuan){
		List<Object> list = new ArrayList<Object>();
		
		String hql = " from WebBankHuikuan where userName=? ";
		list.add(huikuan.getUserName());
		
		if(!StringUtils.isEmpty(huikuan.getBeginDateStr()) && !StringUtils.isEmpty(huikuan.getEndDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d')>= ? and STR_TO_DATE(createTime,'%Y-%m-%d') <= ?";
			list.add(huikuan.getBeginDateStr());
			list.add(huikuan.getEndDateStr());
		}else if(!StringUtils.isEmpty(huikuan.getBeginDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d')>= ?  ";
			list.add(huikuan.getBeginDateStr());
		}else if(!StringUtils.isEmpty(huikuan.getEndDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d') <= ?";
			list.add(huikuan.getEndDateStr());
		}
		if(huikuan.getHkModel() !=null && huikuan.getHkModel()!=0){
			hql += " and hkModel=? ";
			list.add(huikuan.getHkModel());
		}
		
		hql += " order by createTime desc ";
		
		return this.find(hql, list.toArray());
	}
	
	
	/**
	 * 获取银行列表
	 * 方法描述: TODO</br> 
	 * @param userTypeId
	 * @return  
	 * List<WebBank>
	 */
	public List<WebBank> getWebBankList(Integer userTypeId){
		final String sql = "SELECT b.* FROM t_web_bank b,t_link_web_user_bank lb  WHERE lb.user_type_id="+userTypeId.intValue()+" AND lb.user_bank_id=b.id AND b.bank_status = 1  ORDER BY b.id DESC";
		@SuppressWarnings("unchecked")
		List<WebBank> resultList =this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(WebBank.class);
				return sqlQuery.list();
			}
		});
		
		return resultList;
		
	}
	
	
	/**
	 * 获取第三方支付列表
	 * 方法描述: TODO</br> 
	 * @param userTypeId
	 * @return  
	 * List<WebBank>
	 */
	public List<TWebThirdPay> getWebThirdPayList(Integer userTypeId){
		final String sql = "SELECT b.* FROM t_web_third_pay b,t_link_web_user_third_pay lb  WHERE lb.user_type_id="+userTypeId.intValue()+" AND lb.user_third_pay_id=b.id AND b.third_status = 1  ORDER BY b.id DESC";
		List<TWebThirdPay> resultList =this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TWebThirdPay.class);
				return sqlQuery.list();
			}
		});
		
		return resultList;
		
	}
	
	/**
	 * 统计汇款
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param startTimeStr
	 * @param endTimeStr
	 * @return  
	 * int
	 */
	public Map<String, Integer> getWebBankHuikuanTj(String userName,String currDateStr){
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT MAX(total_times) AS totalTimes, MAX(day_times) AS dayTimes FROM t_web_bank_huikuan WHERE hk_model=1 and hk_status=1 ";
		if(!StringUtils.isEmpty(userName)){
			sql += " and user_name=? ";
			list.add(userName);
		}
		
		Map<String, Object> resultMap = this.getJdbcTemplate().queryForMap(sql, list.toArray());
		
		int totalTimes = 0;
		if(null!=resultMap && null != resultMap.get("totalTimes")){
			totalTimes  = (Integer)resultMap.get("totalTimes");
		}
		
		if(StringUtils.isNotEmpty(currDateStr)){
			sql += " and STR_TO_DATE(create_time,'%Y-%m-%d')= ? ";
			list.add(currDateStr);
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
