/**   
* 文件名称: OrderDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-5 下午1:16:09<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.CpOrder;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-5 下午1:16:09<br/>
 */
@SuppressWarnings("all")
@Repository
public class CpOrderDao extends BaseDao<CpOrder,Integer>{
	
	
	/**
	 * 批量更新
	 * 方法描述: TODO</br> 
	 * @param list  
	 * void
	 */
	public void saveOrUpdateAllOrder(List<CpOrder> list){
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}
	
 
	
	/**
	 * 获取最新的默认8条记录
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<CpOrder>
	 */
	public List<Map<String,Object>> getOrderResult8List(String gameTypeCode,String userName){
		String sql = "select QS,GAME_TYPE_NAME,CP_TYPE_NAME,NUMBER,PL,XZJE,CONTENT,BZ from cp_order  where game_type_code='"+gameTypeCode+"' and sfjs ='"+CommonConstant.CP_ORDER_STATUS_WJS+"' ";
		String currDateStr = DateUtil.getGMT_4_YYYMMDD();
		sql +=" and DATE_FORMAT(xzsj,'%Y%m%d')= '"+currDateStr+"' and user_name='"+userName+"' ";
		sql +=" order by xzsj desc limit 8 ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 
	 * 方法描述:得到注单集合</br>
	 * 创建人: zoro<br/> 
	 * @param bean
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getOrderList(CpOrder bean){
		List<Object> list = new ArrayList<Object>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("from CpOrder where  1=1 ");
		if(StringUtils.isNotEmpty(bean.getGameTypeCode())){
			buffer.append(" and gameTypeCode =? ");
			list.add(bean.getGameTypeCode());
		}
		if(StringUtils.isNotEmpty(bean.getQs())){
			buffer.append(" and qs=? ");
			list.add(bean.getQs());
		}
		if(StringUtils.isNotEmpty(bean.getUserName())){
			buffer.append(" and userName=? ");
			list.add(bean.getUserName());
		}
		if(StringUtils.isNotEmpty(bean.getStartTimeStr()) && StringUtils.isNotEmpty(bean.getEndTimeStr())){
			buffer.append(" AND STR_TO_DATE(xzsj,'%Y-%m-%d')>=? and STR_TO_DATE(xzsj,'%Y-%m-%d') <=? ");
			list.add(bean.getStartTimeStr());
			list.add(bean.getEndTimeStr());
		}else if(StringUtils.isNotEmpty(bean.getStartTimeStr())){
			buffer.append(" AND STR_TO_DATE(xzsj,'%Y-%m-%d')>=? ");
			list.add(bean.getStartTimeStr());
		}else if(StringUtils.isNotEmpty(bean.getEndTimeStr())){
			buffer.append(" AND STR_TO_DATE(xzsj,'%Y-%m-%d') <=? ");
			list.add(bean.getEndTimeStr());
		}
		if(StringUtils.isNotBlank(bean.getOrderStatus())){
			buffer.append(" and  sfjs=? ");
			list.add(bean.getOrderStatus());
		}
		
		buffer.append(" ORDER BY sfjs DESC, qs DESC ");
		return this.find(buffer.toString(), list.toArray());
	}
	
	
	/**
	 * 订单列表
	 * 方法描述: TODO</br> 
	 * @param bean
	 * @return  
	 * List<Map<String,Object>>
	 */
	public Page getReportOrderList(Page page,CpOrder bean){
		
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from cp_order where 1=1 ";
		if(!StringUtils.isEmpty(bean.getGameTypeCode())){
			sql += " and GAME_TYPE_CODE = ?";
			list.add(bean.getGameTypeCode());
		}
		
		if(!StringUtils.isEmpty(bean.getUserName())){
			sql += " and USER_NAME = ?";
			list.add(bean.getUserName());
		}
		
		if(!StringUtils.isEmpty(bean.getQs())){
			sql += " and QS = ?";
			list.add(bean.getQs());
		}
		if(!StringUtils.isEmpty(bean.getSfjs())){
			sql += " and SFJS = ?";
			list.add(bean.getSfjs());
		}
		
		if (!StringUtils.isEmpty(bean.getStartTimeStr()) && !StringUtils.isEmpty(bean.getEndTimeStr())) {
			sql += " and date_format(XZSJ,'%Y-%m-%d') >= ?";
			list.add(bean.getStartTimeStr());
			sql += " and date_format(XZSJ,'%Y-%m-%d') <= ?";
			list.add(bean.getEndTimeStr());
		}else if (!StringUtils.isEmpty(bean.getStartTimeStr())) {
			sql += " and date_format(XZSJ,'%Y-%m-%d') >= ?";
			list.add(bean.getStartTimeStr());
		}else if (!StringUtils.isEmpty(bean.getEndTimeStr())) {
			sql += " and date_format(XZSJ,'%Y-%m-%d') <= ?";
			list.add(bean.getEndTimeStr());
		}
 
		sql += " order by XZSJ desc ";
		
		return findPageBySql(page, sql, list.toArray());
 
	}
	
	/***
	 * 方法描述: 统计注单内容</br>
	 * 创建人: zoro<br/> 
	 * @param gameCode
	 * @param qs
	 * @param userName
	 * @param status
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Map<String,Object>> selectCountOrder(CpOrder bean){
		List<Object> list = new ArrayList<Object>();
		String sql = "select date_format(XZSJ,'%Y-%m-%d') as xzsj,count(1) as count, ROUND(SUM(IFNULL(XZJE,0)),3) as xzje,ROUND(SUM(IFNULL(YJ,0)),3) as yj,ROUND(SUM(IFNULL(YXTZ,0)),3) as yxtz, ROUND(SUM(IFNULL(KYJE,0)),3) as kyje, ROUND(SUM(IFNULL(HYSF,0)),3) as hysf " +
				", ROUND(SUM(IFNULL(BACK_WATER_MONEY,0)),3) as backWaterMoney, ROUND(SUM(IFNULL(WIN_MONEY,0)),3) as winMoney, ROUND(SUM(IFNULL(BET_USR_WIN,0)),3) as betUsrWin " +
				"" +
				"from cp_order where SFJS='1' and FIND_IN_SET(ORDER_STATUS,'输,赢') ";
		if(!StringUtils.isEmpty(bean.getGameTypeCode())){
			sql += " and GAME_TYPE_CODE = ?";
			list.add(bean.getGameTypeCode());
		}
		
		if(!StringUtils.isEmpty(bean.getUserName())){
			sql += " and USER_NAME = ?";
			list.add(bean.getUserName());
		}
		
		if(!StringUtils.isEmpty(bean.getQs())){
			sql += " and QS = ?";
			list.add(bean.getQs());
		}
	 
		
		if (!StringUtils.isEmpty(bean.getStartTimeStr()) && !StringUtils.isEmpty(bean.getEndTimeStr())) {
			sql += " and date_format(XZSJ,'%Y-%m-%d') >= ?";
			list.add(bean.getStartTimeStr());
			sql += " and date_format(XZSJ,'%Y-%m-%d') <= ?";
			list.add(bean.getEndTimeStr());
		}else if (!StringUtils.isEmpty(bean.getStartTimeStr())) {
			sql += " and date_format(XZSJ,'%Y-%m-%d') >= ?";
			list.add(bean.getStartTimeStr());
		}else if (!StringUtils.isEmpty(bean.getEndTimeStr())) {
			sql += " and date_format(XZSJ,'%Y-%m-%d') <= ?";
			list.add(bean.getEndTimeStr());
		}
		sql += " group by date_format(XZSJ,'%Y-%m-%d') ";
		sql += " order by date_format(XZSJ,'%Y-%m-%d') ";
		
		List<Map<String,Object>> valList = this.getJdbcTemplate().queryForList(sql, list.toArray());
		Map<String,Map<String,Object>> dataMap = new LinkedHashMap<String,Map<String,Object>>();
		for(int i=0;i<valList.size();i++){
			Map<String,Object> map = valList.get(i);
			Map<String,Object> valMap = new LinkedHashMap<String,Object>();
			valMap.put("xzsj", map.get("xzsj"));
			valMap.put("xzje", map.get("xzje"));
			valMap.put("count", map.get("count"));
			valMap.put("kyje", map.get("kyje"));
			valMap.put("hysf", map.get("hysf"));
			valMap.put("backWaterMoney", (map.get("backWaterMoney")==null?0:map.get("backWaterMoney")));
			valMap.put("winMoney", (map.get("winMoney")==null?0:map.get("winMoney")));
			valMap.put("betUsrWin", map.get("betUsrWin")==null?0:map.get("betUsrWin"));
			dataMap.put(String.valueOf(map.get("xzsj")), valMap);
			
		}
		
		return dataMap;
	}
	
	
	/**
	 * 今日订单统计
	 * 方法描述: TODO</br> 
	 * @param gameTypeCode
	 * @param cpTypeCode
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Object> getTodayOrderTj(String gameTypeCode,String cpTypeCode,String userName){
		Date currDate = new Date();
		String dateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		
		String sql = "SELECT ROUND(SUM(BET_USR_WIN),3) AS betUsrWin FROM cp_order WHERE sfjs='1' AND FIND_IN_SET(ORDER_STATUS,'赢,输') AND DATE_FORMAT(xzsj,'%Y-%m-%d')='"+dateStr+"'" +
				" and GAME_TYPE_CODE='"+gameTypeCode+"' and CP_TYPE_CODE='"+cpTypeCode+"' AND USER_NAME = '"+userName+"'";
		
		
		Map<String,Object> valMap= this.getJdbcTemplate().queryForMap(sql);
		if(valMap!=null){
			return valMap;
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * 方法描述:得到注单集合</br>
	 * 创建人: zoro<br/> 
	 * @param bean
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getOrderRecordList(CpOrder bean,int start,int pageSize){
		List<Object> list = new ArrayList<Object>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("from CpOrder where  1=1 ");
		if(StringUtils.isNotEmpty(bean.getGameTypeCode())){
			buffer.append(" and gameTypeCode =? ");
			list.add(bean.getGameTypeCode());
		}
		if(StringUtils.isNotEmpty(bean.getQs())){
			buffer.append(" and qs=? ");
			list.add(bean.getQs());
		}
		if(StringUtils.isNotEmpty(bean.getUserName())){
			buffer.append(" and userName=? ");
			list.add(bean.getUserName());
		}
		if(StringUtils.isNotEmpty(bean.getStartTimeStr()) && StringUtils.isNotEmpty(bean.getEndTimeStr())){
			buffer.append(" AND STR_TO_DATE(xzsj,'%Y-%m-%d')>=? and STR_TO_DATE(xzsj,'%Y-%m-%d') <=? ");
			list.add(bean.getStartTimeStr());
			list.add(bean.getEndTimeStr());
		}else if(StringUtils.isNotEmpty(bean.getStartTimeStr())){
			buffer.append(" AND STR_TO_DATE(xzsj,'%Y-%m-%d')>=? ");
			list.add(bean.getStartTimeStr());
		}else if(StringUtils.isNotEmpty(bean.getEndTimeStr())){
			buffer.append(" AND STR_TO_DATE(xzsj,'%Y-%m-%d') <=? ");
			list.add(bean.getEndTimeStr());
		}
		if(StringUtils.isNotBlank(bean.getOrderStatus())){
			buffer.append(" and  orderStatus=? ");
			list.add(bean.getOrderStatus());
		}
		
		Page<CpOrder> page=new Page<CpOrder>();
		page.setPageNo(start+1);
		page.setPageSize(pageSize);
		page=this.findPage(page, buffer.toString(), list.toArray());
		return page.getResult();
		//return this.find(buffer.toString(), list.toArray());
	}
	
	/**
	 * 当期盘口限额统计
	 * @param order
	 * @return
	 */
	public Map<String, Object> getUserSingleCredit(CpOrder order){
		if(StringUtils.isEmpty(order.getUserName())){
			return null;
		}
		StringBuffer sql = new StringBuffer("SELECT SUM(XZJE) AS XZJE FROM CP_ORDER WHERE 1 = 1 ");
		List<Object> list = new ArrayList<Object>();
		sql.append(" AND USER_NAME = ?");
		list.add(order.getUserName());
		sql.append(" AND SFJS = ?");
		list.add(0);
		if(StringUtils.isNotEmpty(order.getQs())){
			sql.append(" AND QS = ?");
			list.add(order.getQs());
		}
		if(StringUtils.isNotEmpty(order.getGameTypeCode())){
			sql.append(" AND GAME_TYPE_CODE = ?");
			list.add(order.getGameTypeCode());
		}
		if(StringUtils.isNotEmpty(order.getCpTypeCode())){
			sql.append(" AND CP_TYPE_CODE = ?");
			list.add(order.getCpTypeCode());
		}
		Map<String, Object> valMap = this.getJdbcTemplate().queryForMap(sql.toString() , list.toArray());
		return valMap;
	}
	
	/**
	 * 用户号码限额统计
	 * @param order
	 * @return
	 */
	/*public List<Map<String,Object>> getUserSingleCreditForNumber(CpOrder order){
		if(StringUtils.isEmpty(order.getUserName())){
			return null;
		}
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("SELECT NUMBER,SUM(XZJE) AS XZJE FROM CP_ORDER WHERE 1 = 1 ");
		sql.append(" AND USER_NAME = ?");
		list.add(order.getUserName());
		sql.append(" AND SFJS = ?");
		list.add(0);
		if(StringUtils.isNotEmpty(order.getQs())){
			sql.append(" AND QS = ?");
			list.add(order.getQs());
		}
		if(StringUtils.isNotEmpty(order.getGameTypeCode())){
			sql.append(" AND GAME_TYPE_CODE = ?");
			list.add(order.getGameTypeCode());
		}
		if(StringUtils.isNotEmpty(order.getCpTypeCode())){
			sql.append(" AND CP_TYPE_CODE = ?");
			list.add(order.getCpTypeCode());
		}
		if(StringUtils.isNotEmpty(order.getCpCateCode())){
			sql.append(" AND CP_CATE_CODE = ?");
			list.add(order.getCpCateCode());
		}
		if(StringUtils.isNotEmpty(order.getXzlxCode())){
			sql.append(" AND XZLX_CODE = ?");
			list.add(order.getXzlxCode());
		}
		if(StringUtils.isNotEmpty(order.getXzzuCode())){
			sql.append(" AND XZZU_CODE = ?");
			list.add(order.getXzzuCode());
		}
		sql.append(" GROUP BY NUMBER");
		List<Map<String,Object>> valList = this.getJdbcTemplate().queryForList(sql.toString(), list.toArray());
		return valList;
	}*/
	
	/**
	 * 用户号码限额统计
	 * @param order
	 * @return
	 */
	public List<CpOrder> getUserSingleCreditForNumber(CpOrder order){
		if(StringUtils.isEmpty(order.getUserName())){
			return null;
		}
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from CpOrder where 1 = 1");
		hql.append(" AND userName = ?");
		list.add(order.getUserName());
		hql.append(" AND sfjs = ?");
		list.add(String.valueOf(0));
		if(StringUtils.isNotEmpty(order.getQs())){
			hql.append(" AND qs = ?");
			list.add(order.getQs());
		}
		if(StringUtils.isNotEmpty(order.getGameTypeCode())){
			hql.append(" AND gameTypeCode = ?");
			list.add(order.getGameTypeCode());
		}
		if(StringUtils.isNotEmpty(order.getCpTypeCode())){
			hql.append(" AND cpTypeCode = ?");
			list.add(order.getCpTypeCode());
		}
		if(StringUtils.isNotEmpty(order.getCpCateCode())){
			hql.append(" AND cpCateCode = ?");
			list.add(order.getCpCateCode());
		}
		if(StringUtils.isNotEmpty(order.getXzlxCode())){
			hql.append(" AND xzlxCode = ?");
			list.add(order.getXzlxCode());
		}
		if(StringUtils.isNotEmpty(order.getXzzuCode())){
			hql.append(" AND xzzuCode = ?");
			list.add(order.getXzzuCode());
		}
		if(StringUtils.isNotEmpty(order.getNumber())){
			hql.append(" AND NUMBER = ?");
			list.add(order.getNumber());
		}
		List<CpOrder> resOrder = this.getHibernateTemplate().find(hql.toString(), list.toArray());
		return resOrder;
	}
	
	/**
	 * 获取结算订单前几条
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getCpOrderLotteryUser(int limits){
		String hql = "from CpOrder where sfjs='1' and hysf>0 order by createTime desc limit "+limits;
		return this.getHibernateTemplate().find(hql);
		
	}
}
