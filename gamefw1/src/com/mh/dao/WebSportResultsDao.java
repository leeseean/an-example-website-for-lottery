/**   
* 文件名称: WebSportResultsDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-16 下午1:40:53<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.SharedBaseDao;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.HttpClientUtil;
import com.mh.entity.TResultMatchBk;
import com.mh.entity.TResultMatchFt;


/** 
 * 类描述: TODO<br/>
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-16 下午1:40:53<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebSportResultsDao extends SharedBaseDao<String, Integer> {
	/**
	 * 得到篮球赛果
	 * 方法描述: TODO</br> 
	 * @param tableName
	 * @param curTag
	 * @return  
	 * TResultMatchBk
	 */
    public List<TResultMatchBk> getResultBkSport(String serachDate){
 
    	String url = CommonConstant.resCommMap.get(CommonConstant.SPORT_INTERFACE_AUT_URL) + "/sport/agent/api/getRelateResultAmidithion";
    	Map<String , String > map = new HashMap<String , String>();
    	map.put("gameName", "bk");
    	map.put("date", serachDate);
    	String jsonString = HttpClientUtil.post(url, map);
    	logger.info("返回报文:" + jsonString);
    	JSONObject jsonObject = JSONObject.fromObject(jsonString);
    	List<TResultMatchBk> resultList = new ArrayList<TResultMatchBk>();
    	if(jsonObject.getString("code").equals("000000") && !StringUtils.equals("null", jsonObject.getString("data"))){
			JSONArray array = jsonObject.getJSONArray("data");
    	    resultList = JSONArray.toList(array,TResultMatchBk.class);
    	}
    	return resultList;
    	
    	
    	/*String sqlStr = "select * from  t_result_match_bk where 1=1";
    	if(!StringUtils.isEmpty(serachDate)){
			sqlStr += " and STR_TO_DATE(search_time,'%Y-%m-%d')= '"+serachDate+"'";
		}
    	final String sql = sqlStr;
		List<TResultMatchBk> resultList =this.getHibernateTemplate_shared().executeFind( new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TResultMatchBk.class);
				return sqlQuery.list();
			}
		});
		return resultList;*/
    } 
	/**
	 * 得到足球赛果
	 * 方法描述: TODO</br> 
	 * @param tableName
	 * @param curTag
	 * @return  
	 * TResultMatchFt
	 */
    public List<TResultMatchFt> getResultFtSport(String serachDate){
    	String url = CommonConstant.resCommMap.get(CommonConstant.SPORT_INTERFACE_AUT_URL) + "/sport/agent/api/getRelateResultAmidithion";
    	Map<String , String > map = new HashMap<String , String>();
    	map.put("gameName", "ft");
    	map.put("date", serachDate);
    	String jsonString = HttpClientUtil.post(url, map);
    	logger.info("返回报文:" + jsonString);
    	JSONObject jsonObject = JSONObject.fromObject(jsonString);
    	List<TResultMatchFt> resultList = new ArrayList<TResultMatchFt>() ;
    	if(jsonObject.getString("code").equals("000000") && !StringUtils.equals("null", jsonObject.getString("data"))){
    		JSONArray array = jsonObject.getJSONArray("data");
    		resultList = JSONArray.toList(array,TResultMatchFt.class);
    	}
    	return resultList;
    	
    	/*String sqlStr = "select * from  t_result_match_ft where 1=1";
    	if(!StringUtils.isEmpty(serachDate)){
			sqlStr += " and STR_TO_DATE(search_time,'%Y-%m-%d')= '"+serachDate+"'";
		}
    	final String sql = sqlStr;
		List<TResultMatchFt> resultList =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TResultMatchFt.class);
				return sqlQuery.list();
			}
		});
		return resultList;*/
    }
    
    /**
     * 消息列表
     * 方法描述: TODO</br> 
     * @param dateStr
     * @return  
     * List<Map<String,Object>>
     */
    public List<Map<String,Object>> getMessageList(String select_date,String fField){
    	String sql = "SELECT * FROM  t_match_message where 1=1";
    	Date currDate = new Date();
    	String dateStr = "";
		if("0".equals(select_date)){//今日
			dateStr = DateUtil.format(currDate, "yyyy-MM-dd");
			sql += " and date_format(date,'%Y-%m-%d')= '"+dateStr+"'";
		}else if("-1".equals(select_date)){//昨日
			Date lastDate = DateUtil.addDay(currDate, -1);
			dateStr = DateUtil.format(lastDate, "yyyy-MM-dd");
			sql += " and date_format(date,'%Y-%m-%d')= '"+dateStr+"'";
		}else if("-2".equals(select_date)){//昨日之前
			Date lastDate = DateUtil.addDay(currDate, -1);
			dateStr = DateUtil.format(lastDate, "yyyy-MM-dd");
			sql += " and date_format(date,'%Y-%m-%d') <= '"+dateStr+"'";
		}
    	
    	if(!StringUtils.isEmpty(fField)){
    		sql += " and content like '%"+fField+"%'";
    	}
    	
    	
    	sql += " order by date desc ";
    	
    	return this.getJdbcTemplate_shared().queryForList(sql);
    }
    
    
    /**
     * 获取公告前几条列表
     * 方法描述: TODO</br> 
     * @return  
     * List<String>
     */
    public List<String> getSportMessageLimit(){
    	List<String> valList = new ArrayList<String>();
    	String sql = "SELECT * FROM  t_match_message ORDER BY DATE DESC LIMIT 5 ";
    	List<Map<String,Object>> list = this.getJdbcTemplate_shared().queryForList(sql);
    	for(int i=0;i<list.size();i++){
    		Map<String,Object> map = list.get(i);
    		if(map.get("content")!=null){
    			valList.add(map.get("content").toString());
    		}
    	}
    	return valList;
    }
    
    
}
