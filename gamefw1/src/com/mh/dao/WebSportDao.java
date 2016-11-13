/**   
* 文件名称: WebSportDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-14 下午7:40:53<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.orm.SharedBaseDao;
import com.mh.commons.utils.HttpClientUtil;
import com.mh.commons.utils.JackUtil;
import com.mh.entity.SportBet;
import com.mh.entity.TBkMatchP3;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchControl;
import com.mh.entity.TMatchRelate;
import com.mh.entity.TMatchSettled;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-14 下午7:40:53<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebSportDao extends SharedBaseDao{
	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param timeType大类型（今日赛事、早盘、滚球）
	 * @param rtype
	 * @return  
	 * TMatchControl
	 */
	
	public TMatchControl getMatchControl(String timeType,String rtype){
		TMatchControl control = null;
		
		/*final String sql = "select * from  t_match_control where time_type='"+timeType+"' and rtype = '"+rtype+"'";
		List<TMatchControl> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TMatchControl.class);
				return sqlQuery.list();
			}
		});
		if(list!=null && list.size()>0){
			control = list.get(0);
		}*/
		
		String key = SportConstant.CONTROL_KEY + timeType + "_" + rtype;
		Map<String,String> map = MemCachedUtil.getForMap(key);
		//Map<String,String> map = MemCachedUtil.getForMap(key);//获取版本号
		if(null == map){
			return null;
		}
		//没有值就是0 维护
		Integer version = map.get(SportConstant.DATA) == null ? 0 : Integer.parseInt((String)map.get(SportConstant.DATA));
		
		key = timeType + "_" + rtype + "_" + version;
		map = MemCachedUtil.getForMap(key);//获取control
		if(null == map){
			return null;
		}
		String value = map.get(SportConstant.DATA);
		if(StringUtils.isNotEmpty(value)){
			control = (TMatchControl) JackUtil.toBean(value, TMatchControl.class);
			control.setVersion(version);
			control.setTimeType(timeType);
			control.setRtype(rtype);
		}
		return control;
		
	}
	
	
	
	/**
	 * 获取联赛名称
	 * 方法描述: TODO</br> 
	 * @param tableName
	 * @param curTag
	 * @return  
	 * List<String>
	 */
	public List<String> getLeague(String tableName,String curTag){
		String sql = "SELECT league FROM  "+tableName+" WHERE curTag='"+curTag+"' GROUP BY league";
		List<Map<String,Object>> list = this.getJdbcTemplate_shared().queryForList(sql);
		List<String> valList = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			valList.add(map.get("league").toString());
		}
		return valList;
	}
	public List<String> getLeague(TMatchControl control){
		List<String> leagueList = new ArrayList<String>();
		//取出所有页数据
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(SportConstant.ft_rtype_r.equals(control.getRtype())){
				List<TFtMatchR> list = (List<TFtMatchR>) JackUtil.toList(value, TFtMatchR.class);
				for (TFtMatchR match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}else if(SportConstant.ft_rtype_pd.equals(control.getRtype()) || SportConstant.ft_rtype_hpd.equals(control.getRtype())){
				List<TFtMatchPD> list = (List<TFtMatchPD>) JackUtil.toList(value, TFtMatchPD.class);
				for (TFtMatchPD match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}else if(SportConstant.ft_rtype_t.equals(control.getRtype())){
				List<TFtMatchT> list = (List<TFtMatchT>) JackUtil.toList(value, TFtMatchT.class);
				for (TFtMatchT match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}else if(SportConstant.ft_rtype_f.equals(control.getRtype())){
				List<TFtMatchF> list = (List<TFtMatchF>) JackUtil.toList(value, TFtMatchF.class);
				for (TFtMatchF match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}else if(SportConstant.ft_rtype_p3.equals(control.getRtype())){
				List<TFtMatchP3> list = (List<TFtMatchP3>) JackUtil.toList(value, TFtMatchP3.class);
				for (TFtMatchP3 match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}else if(SportConstant.bk_rtype_rmain_cj.equals(control.getRtype())){
				List<TBkMatchRmain> list = (List<TBkMatchRmain>) JackUtil.toList(value, TBkMatchRmain.class);
				for (TBkMatchRmain match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}else if(SportConstant.bk_rtype_p3_cj.equals(control.getRtype())){
				List<TBkMatchP3> list = (List<TBkMatchP3>) JackUtil.toList(value, TBkMatchP3.class);
				for (TBkMatchP3 match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}else if(SportConstant.roll_rtype_re.equals(control.getRtype())){
				List<TRMatchRE> list = (List<TRMatchRE>) JackUtil.toList(value, TRMatchRE.class);
				for (TRMatchRE match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}else if(SportConstant.roll_rtype_remain.equals(control.getRtype())){
				List<TRMatchRemain> list = (List<TRMatchRemain>) JackUtil.toList(value, TRMatchRemain.class);
				for (TRMatchRemain match : list) {
					if(!leagueList.contains(match.getLeague())){
						leagueList.add(match.getLeague());
					}
				}
			}
		}
		return leagueList;
	}
	
	
	
	/**
	 * 
	 * 单式
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public List<TFtMatchR> getFtMatchR(String curTag,int curPage,String league){
		
		String sqlStr = "select * from t_ft_match_r where curTag='"+curTag+"'  ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		final String sql = sqlStr;
		List<TFtMatchR> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchR.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TFtMatchR> getFtMatchR(TMatchControl control,int curPage,String league){
		List<TFtMatchR> list = new ArrayList<TFtMatchR>();
		if(StringUtils.isNotEmpty(league)){
			List<TFtMatchR> listTemp = new ArrayList<TFtMatchR>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TFtMatchR>) getMatchInfoListForLeague(key, TFtMatchR.class));
			}
			String[] leas = league.split(",");
			for (TFtMatchR match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TFtMatchR>) getMatchInfoListForPage(control, curPage, TFtMatchR.class);
		}
		Collections.sort(list, new Comparator<TFtMatchR>() {
			public int compare(TFtMatchR o1, TFtMatchR o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	/**
	 * 分页获取赛事信息列表
	 * @param control
	 * @param curPage
	 * @param objClass
	 * @return
	 */
	private List<?> getMatchInfoListForPage(TMatchControl control,int curPage,Class<?> objClass){
		//没有采集到数据 或者 采集异常(curstatus==0采集异常)
		if(control.getCurcount() == 0 || control.getCurstatus() == 0){
			return new ArrayList();
		}
		String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + curPage;
		Map<String, String> map = MemCachedUtil.getForMap(key);
		String value = map.get(SportConstant.DATA);
		if(StringUtils.isNotEmpty(value)){
			return JackUtil.toList(value, objClass);
		}
		return new ArrayList();
	}
	
	private List<?> getMatchInfoListForLeague(String key,Class<?> objClass){
		Map<String, String> map = MemCachedUtil.getForMap(key);
		String value = map.get(SportConstant.DATA);
		if(StringUtils.isNotEmpty(value)){
			return JackUtil.toList(value, objClass);
		}
		return new ArrayList();
	}
	
	/**
	 * 
	 * 波胆
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public List<TFtMatchPD> getFtMatchPD(String curTag,int curPage,String league){
		
		String sqlStr = "select * from  t_ft_match_pd where curTag='"+curTag+"' ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		
		final String sql = sqlStr;
		List<TFtMatchPD> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchPD.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TFtMatchPD> getFtMatchPD(TMatchControl control,int curPage,String league){
		
		List<TFtMatchPD> list = new ArrayList<TFtMatchPD>();
		if(StringUtils.isNotEmpty(league)){
			List<TFtMatchPD> listTemp = new ArrayList<TFtMatchPD>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TFtMatchPD>) getMatchInfoListForLeague(key, TFtMatchPD.class));
			}
			String[] leas = league.split(",");
			for (TFtMatchPD match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TFtMatchPD>) getMatchInfoListForPage(control, curPage, TFtMatchPD.class);
		}
		Collections.sort(list, new Comparator<TFtMatchPD>() {
			public int compare(TFtMatchPD o1, TFtMatchPD o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	
	
	/**
	 * 
	 * 总入球
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public List<TFtMatchT> getFtMatchT(String curTag,int curPage,String league){
		
		String sqlStr = "select * from  t_ft_match_t where curTag='"+curTag+"' ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		final String sql = sqlStr;
		
		List<TFtMatchT> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchT.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TFtMatchT> getFtMatchT(TMatchControl control,int curPage,String league){
		List<TFtMatchT> list = new ArrayList<TFtMatchT>();
		if(StringUtils.isNotEmpty(league)){
			List<TFtMatchT> listTemp = new ArrayList<TFtMatchT>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TFtMatchT>) getMatchInfoListForLeague(key, TFtMatchT.class));
			}
			String[] leas = league.split(",");
			for (TFtMatchT match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TFtMatchT>) getMatchInfoListForPage(control, curPage, TFtMatchT.class);
		}
		Collections.sort(list, new Comparator<TFtMatchT>() {
			public int compare(TFtMatchT o1, TFtMatchT o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	
	
	/**
	 * 
	 * 总入球
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public List<TFtMatchF> getFtMatchF(String curTag,int curPage,String league){
		
		String sqlStr = "select * from  t_ft_match_f where curTag='"+curTag+"' ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		final String sql = sqlStr;
		List<TFtMatchF> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchF.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TFtMatchF> getFtMatchF(TMatchControl control,int curPage,String league){
		
		List<TFtMatchF> list = new ArrayList<TFtMatchF>();
		if(StringUtils.isNotEmpty(league)){
			List<TFtMatchF> listTemp = new ArrayList<TFtMatchF>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TFtMatchF>) getMatchInfoListForLeague(key, TFtMatchF.class));
			}
			String[] leas = league.split(",");
			for (TFtMatchF match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TFtMatchF>) getMatchInfoListForPage(control, curPage, TFtMatchF.class);
		}
		Collections.sort(list, new Comparator<TFtMatchF>() {
			public int compare(TFtMatchF o1, TFtMatchF o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	
	/**
	 * 
	 * 总和过关
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public List<TFtMatchP3> getFtMatchP3(String curTag,int curPage,String league){
		
		String sqlStr = "select * from  t_ft_match_p3 where curTag='"+curTag+"' ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		final String sql = sqlStr;
		
		
		List<TFtMatchP3> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchP3.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TFtMatchP3> getFtMatchP3(TMatchControl control,int curPage,String league){
		List<TFtMatchP3> list = new ArrayList<TFtMatchP3>();
		if(StringUtils.isNotEmpty(league)){
			List<TFtMatchP3> listTemp = new ArrayList<TFtMatchP3>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TFtMatchP3>) getMatchInfoListForLeague(key, TFtMatchP3.class));
			}
			String[] leas = league.split(",");
			for (TFtMatchP3 match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TFtMatchP3>) getMatchInfoListForPage(control, curPage, TFtMatchP3.class);
		}
		Collections.sort(list, new Comparator<TFtMatchP3>() {
			public int compare(TFtMatchP3 o1, TFtMatchP3 o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	
	/**
	 * 篮球 单式
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return  
	 * List<TBkMatchRmain>
	 */
	
	public List<TBkMatchRmain> getBkMatchRmain(String curTag,int curPage,String league){
		
		String sqlStr = "select * from  t_bk_match_rmain where curTag='"+curTag+"' ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		final String sql = sqlStr;
		List<TBkMatchRmain> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TBkMatchRmain.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TBkMatchRmain> getBkMatchRmain(TMatchControl control,int curPage,String league){
		List<TBkMatchRmain> list = new ArrayList<TBkMatchRmain>();
		if(StringUtils.isNotEmpty(league)){
			List<TBkMatchRmain> listTemp = new ArrayList<TBkMatchRmain>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TBkMatchRmain>) getMatchInfoListForLeague(key, TBkMatchRmain.class));
			}
			String[] leas = league.split(",");
			for (TBkMatchRmain match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TBkMatchRmain>) getMatchInfoListForPage(control, curPage, TBkMatchRmain.class);
		}
		Collections.sort(list, new Comparator<TBkMatchRmain>() {
			public int compare(TBkMatchRmain o1, TBkMatchRmain o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	
	/**
	 *  篮球 综合过关
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return  
	 * List<TBkMatchRmain>
	 */
	
	public List<TBkMatchP3> getBkMatchP3(String curTag,int curPage,String league){
		
		String sqlStr = "select * from  t_bk_match_p3 where curTag='"+curTag+"' ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		final String sql = sqlStr;
		List<TBkMatchP3> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TBkMatchP3.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TBkMatchP3> getBkMatchP3(TMatchControl control,int curPage,String league){
		List<TBkMatchP3> list = new ArrayList<TBkMatchP3>();
		if(StringUtils.isNotEmpty(league)){
			List<TBkMatchP3> listTemp = new ArrayList<TBkMatchP3>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TBkMatchP3>) getMatchInfoListForLeague(key, TBkMatchP3.class));
			}
			String[] leas = league.split(",");
			for (TBkMatchP3 match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TBkMatchP3>) getMatchInfoListForPage(control, curPage, TBkMatchP3.class);
		}
		Collections.sort(list, new Comparator<TBkMatchP3>() {
			public int compare(TBkMatchP3 o1, TBkMatchP3 o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	
	/**
	 *  滚球-足球
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return  
	 * List<TBkMatchRmain>
	 */
	
	public List<TRMatchRE> getRollMatchRE(String curTag,int curPage,String league){
		
		String sqlStr = "select * from  t_r_match_re where curTag='"+curTag+"' ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		final String sql = sqlStr;
		List<TRMatchRE> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TRMatchRE.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TRMatchRE> getRollMatchRE(TMatchControl control,int curPage,String league){
		List<TRMatchRE> list = new ArrayList<TRMatchRE>();
		if(StringUtils.isNotEmpty(league)){
			List<TRMatchRE> listTemp = new ArrayList<TRMatchRE>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TRMatchRE>) getMatchInfoListForLeague(key, TRMatchRE.class));
			}
			String[] leas = league.split(",");
			for (TRMatchRE match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TRMatchRE>) getMatchInfoListForPage(control, curPage, TRMatchRE.class);
		}
		Collections.sort(list, new Comparator<TRMatchRE>() {
			public int compare(TRMatchRE o1, TRMatchRE o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	
	
	
	/**
	 * 滚球-篮球
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return  
	 * List<TBkMatchRmain>
	 */
	
	public List<TRMatchRemain> getRollMatchRemain(String curTag,int curPage,String league){
		String sqlStr = "select * from  t_r_match_remain where curTag='"+curTag+"' ";
		if(!StringUtils.isEmpty(league)){
			sqlStr += " and FIND_IN_SET(league,'"+league+"')";
		}else{
			sqlStr += " and match_page='"+curPage+"' ";
		}
		sqlStr += "  order by match_index asc  ";
		final String sql = sqlStr;
		List<TRMatchRemain> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TRMatchRemain.class);
				return sqlQuery.list();
			}
		});
		
		return list;
	}
	
	public List<TRMatchRemain> getRollMatchRemain(TMatchControl control,int curPage,String league){
		List<TRMatchRemain> list = new ArrayList<TRMatchRemain>();
		if(StringUtils.isNotEmpty(league)){
			List<TRMatchRemain> listTemp = new ArrayList<TRMatchRemain>();
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				listTemp.addAll((List<TRMatchRemain>) getMatchInfoListForLeague(key, TRMatchRemain.class));
			}
			String[] leas = league.split(",");
			for (TRMatchRemain match : listTemp) {
				for (String lea : leas) {
					if(match.getLeague().equals(lea)){
						list.add(match);
					}
				}
			}
		}else{
			list = (List<TRMatchRemain>) getMatchInfoListForPage(control, curPage, TRMatchRemain.class);
		}
		Collections.sort(list, new Comparator<TRMatchRemain>() {
			public int compare(TRMatchRemain o1, TRMatchRemain o2) {
				return o1.getMatchIndex().compareTo(o2.getMatchIndex());
			}
		});
		return list;
	}
	
	//**************************************//
	
 
	
	
	
	/**
	 * 
	 * 单式
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public TFtMatchR getFtMatchR(String gid,String timeType){
		
		String sqlStr = "select * from t_ft_match_r where gid='"+gid+"' and time_type='"+timeType+"' ";
 
		final String sql = sqlStr;
		List<TFtMatchR> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchR.class);
				return sqlQuery.list();
			}
		});
		
		TFtMatchR obj = null;
		if(list!=null && list.size()>0){
			obj = list.get(0);
		}
		
		return obj;
		
	}
	
	public TFtMatchR getFtMatchR(TMatchControl control,SportBet bet){
		
		TFtMatchR re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TFtMatchR> list = (List<TFtMatchR>) JackUtil.toList(value, TFtMatchR.class);
				for (TFtMatchR match : list) {
					if(match.getGid().equals(bet.getGid())){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
		
	}
	
	/**
	 * 
	 * 波胆
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public TFtMatchPD getFtMatchPD(String gid,String timeType,String tag){
		
		String sqlStr = "select * from  t_ft_match_pd where gid='"+gid+"' and time_type='"+timeType+"' and tag="+tag;
	 
		
		final String sql = sqlStr;
		List<TFtMatchPD> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchPD.class);
				return sqlQuery.list();
			}
		});
		
		TFtMatchPD obj = null;
		if(list!=null && list.size()>0){
			obj = list.get(0);
		}
		
		return obj;
	}
	public TFtMatchPD getFtMatchPD(TMatchControl control,SportBet bet,String tag){
		TFtMatchPD re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TFtMatchPD> list = (List<TFtMatchPD>) JackUtil.toList(value, TFtMatchPD.class);
				for (TFtMatchPD match : list) {
					if(match.getGid().equals(bet.getGid()) && match.getTag() == Integer.parseInt(tag)){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
	}
	
	
	
	/**
	 * 
	 * 总入球
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public TFtMatchT getFtMatchT(String gid,String timeType){
		
		String sqlStr = "select * from t_ft_match_t where gid='"+gid+"' and time_type='"+timeType+"' ";
 
		final String sql = sqlStr;
		
		List<TFtMatchT> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchT.class);
				return sqlQuery.list();
			}
		});
		
		TFtMatchT obj = null;
		if(list!=null && list.size()>0){
			obj = list.get(0);
		}
		
		return obj;
		
	}
	
	public TFtMatchT getFtMatchT(TMatchControl control,SportBet bet){
		TFtMatchT re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TFtMatchT> list = (List<TFtMatchT>) JackUtil.toList(value, TFtMatchT.class);
				for (TFtMatchT match : list) {
					if(match.getGid().equals(bet.getGid())){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
	}
	
	
	
	/**
	 * 
	 * 半场/全场
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public TFtMatchF getFtMatchF(String gid,String timeType){
		String sqlStr = "select * from  t_ft_match_f where gid='"+gid+"' and time_type='"+timeType+"'";
	 
		final String sql = sqlStr;
		List<TFtMatchF> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchF.class);
				return sqlQuery.list();
			}
		});
		TFtMatchF obj = null;
		if(list!=null && list.size()>0){
			obj = list.get(0);
		}
		
		return obj;
		
	}
	
	public TFtMatchF getFtMatchF(TMatchControl control,SportBet bet){
		TFtMatchF re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TFtMatchF> list = (List<TFtMatchF>) JackUtil.toList(value, TFtMatchF.class);
				for (TFtMatchF match : list) {
					if(match.getGid().equals(bet.getGid())){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
		
	}
	
	/**
	 * 
	 * 总和过关
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @return  
	 * List<TFtMatchR>
	 */
	
	public TFtMatchP3 getFtMatchP3(String gid,String timeType){
		String sqlStr = "select * from  t_ft_match_p3 where gid='"+gid+"' and time_type='"+timeType+"' ";
 
		final String sql = sqlStr;
		
		List<TFtMatchP3> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TFtMatchP3.class);
				return sqlQuery.list();
			}
		});
		TFtMatchP3 obj = null;
		if(list!=null && list.size()>0){
			obj = list.get(0);
		}
		
		return obj;
		
	}
	
	public TFtMatchP3 getFtMatchP3(TMatchControl control,SportBet bet){
		TFtMatchP3 re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TFtMatchP3> list = (List<TFtMatchP3>) JackUtil.toList(value, TFtMatchP3.class);
				for (TFtMatchP3 match : list) {
					if(match.getGid().equals(bet.getGid())){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
		
	}
	
	
	/**
	 * 篮球 单式
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return  
	 * List<TBkMatchRmain>
	 */
	
	public TBkMatchRmain getBkMatchRmain(String gid,String timeType){
		String sqlStr = "select * from  t_bk_match_rmain where gid='"+gid+"' and time_type='"+timeType+"'";
 
		final String sql = sqlStr;
		List<TBkMatchRmain> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TBkMatchRmain.class);
				return sqlQuery.list();
			}
		});
		
		TBkMatchRmain obj = null;
		if(list!=null && list.size()>0){
			obj = list.get(0);
		}
		
		return obj;
	}
	
	public TBkMatchRmain getBkMatchRmain(TMatchControl control,SportBet bet){
		TBkMatchRmain re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TBkMatchRmain> list = (List<TBkMatchRmain>) JackUtil.toList(value, TBkMatchRmain.class);
				for (TBkMatchRmain match : list) {
					if(match.getGid().equals(bet.getGid())){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
	}
	
	
	/**
	 *  篮球 综合过关
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return  
	 * List<TBkMatchRmain>
	 */
	
	public TBkMatchP3 getBkMatchP3(String gid,String timeType){
		String sqlStr = "select * from  t_bk_match_p3 where gid='"+gid+"' and time_type='"+timeType+"'";
 
		final String sql = sqlStr;
		List<TBkMatchP3> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TBkMatchP3.class);
				return sqlQuery.list();
			}
		});
		TBkMatchP3 obj = null;
		if(list!=null && list.size()>0){
			obj = list.get(0);
		}
		
		return obj;
	}
	
	public TBkMatchP3 getBkMatchP3(TMatchControl control,SportBet bet){
		TBkMatchP3 re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TBkMatchP3> list = (List<TBkMatchP3>) JackUtil.toList(value, TBkMatchP3.class);
				for (TBkMatchP3 match : list) {
					if(match.getGid().equals(bet.getGid())){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
	}
	
	/**
	 *  滚球-足球
	 * 方法描述: TODO</br> 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return  
	 * List<TBkMatchRmain>
	 */
	
	public TRMatchRE getRollMatchRE(String gid,String timeType){
		String sqlStr = "select * from  t_r_match_re where gid='"+gid+"' ";
 
		final String sql = sqlStr;
		List<TRMatchRE> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TRMatchRE.class);
				return sqlQuery.list();
			}
		});
		TRMatchRE re = null;
		if(list!=null && list.size()>0){
			re = list.get(0);
		}
		
		return re;
	}
	
	public TRMatchRE getRollMatchRE(TMatchControl control,SportBet bet){
		TRMatchRE re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TRMatchRE> list = (List<TRMatchRE>) JackUtil.toList(value, TRMatchRE.class);
				for (TRMatchRE match : list) {
					if(match.getGid().equals(bet.getGid())){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
	}
	
	
	
	
	/**
	 * 滚球-篮球
	 * 方法描述: TODO</br> 
	 * @param gid
	 * @return  
	 * List<TBkMatchRmain>
	 */
	
	public TRMatchRemain getRollMatchRemain(String gid,String timeType){
		String sqlStr = "select * from  t_r_match_remain where gid='"+gid+"'  ";
 
		final String sql = sqlStr;
		List<TRMatchRemain> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TRMatchRemain.class);
				return sqlQuery.list();
			}
		});
		TRMatchRemain rmain = null;
		if(list!=null && list.size()>0){
			rmain = list.get(0);
		}
		
		return rmain;
	}
	
	public TRMatchRemain getRollMatchRemain(TMatchControl control,SportBet bet){
		TRMatchRemain re = null;
		boolean flag = false;
		for (int i = 1; i <= control.getCurpages(); i++) {
			String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
			Map<String, String> map = MemCachedUtil.getForMap(key);
			String value = map.get(SportConstant.DATA);
			if(StringUtils.isNotEmpty(value)){
				List<TRMatchRemain> list = (List<TRMatchRemain>) JackUtil.toList(value, TRMatchRemain.class);
				for (TRMatchRemain match : list) {
					if(match.getGid().equals(bet.getGid())){
						re = match;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return re;
	}
	
	
	
 
	
	/**
	 * 查询赛程
	 * 方法描述: TODO</br> 
	 * @param matchTime
	 * @param teamH
	 * @param teamC
	 * @return  
	 * TMatchRelate
	 */
	
	public TMatchRelate getTMatchRelate(String matchTime,String teamH,String teamC){
		String sqlStr = "select * from t_match_relate where match_time='"+matchTime+"' and team_h='"+teamH+"' and team_c='"+teamC+"' ";
 
		final String sql = sqlStr;
		List<TMatchRelate> list =this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TMatchRelate.class);
				return sqlQuery.list();
			}
		});
		TMatchRelate re = null;
		if(list!=null && list.size()>0){
			re = list.get(0);
		}
		
		return re;
	}
 
	/**
	 * 通过Gid查询球赛</br>
	 * 创建人: zoro<br/> 
	 * @param gid
	 * @return  
	 * TMatchRelate
	 */
	
	public TMatchRelate searchMatchByGid(String gid){
		TMatchRelate re = null;
		/*String sqlStr = "select * from t_match_relate where gid like '%," + gid + ",%'";
		final String sql = sqlStr;
		List<TMatchRelate> list = this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TMatchRelate.class);
				return sqlQuery.list();
			}
		});
		
		if(list!=null && list.size()>0){
			re = list.get(0);
		}*/
		String url = CommonConstant.resCommMap.get(CommonConstant.SPORT_INTERFACE_AUT_URL) + "/sport/agent/api/getMatchRelateByGid";
		Map<String, String> params = new HashMap<String, String>();
		params.put("gid", gid);
		String res = HttpClientUtil.post(url, params);
		JSONObject json = JSONObject.fromObject(res);
		if("000000".equals(json.get("code"))){
			JSONObject data = (JSONObject) json.get("data");
			re = (TMatchRelate) JSONObject.toBean(data, TMatchRelate.class);
		}
		return re;
	}
	
	/**
	 * 通过联盟名称+主队名+客队名+日期 询球赛</br>
	 * 创建人: zoro<br/> 
	 * @param league
	 * @param teamH
	 * @param teamC
	 * @param matchDate
	 * @return  
	 * List<TMatchRelate>
	 */
	
	public List<TMatchRelate> searchMatchForMulConditions(String league,
			String teamH, String teamC, String matchDate) {
		/*String sqlStr = "select * from t_match_relate where league='"+league+"' and team_h='"+teamH+"' and team_c='"+teamC+"' and match_date='"+matchDate+"' ";
		 
		final String sql = sqlStr;
		List<TMatchRelate> list = this.getHibernateTemplate_shared().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(TMatchRelate.class);
				return sqlQuery.list();
			}
		});*/
		List<TMatchRelate> list = null;
		String url = CommonConstant.resCommMap.get(CommonConstant.SPORT_INTERFACE_AUT_URL) + "/sport/agent/api/searchMatchForMulConditions";
		Map<String, String> params = new HashMap<String, String>();
		params.put("league", league);
		params.put("teamH", teamH);
		params.put("teamC", teamC);
		params.put("matchDate", matchDate);
		String res = HttpClientUtil.post(url, params);
		JSONObject json = JSONObject.fromObject(res);
		if("000000".equals(json.get("code"))){
			JSONArray data = (JSONArray) json.get("data");
			list = JSONArray.toList(data, TMatchRelate.class);
		}
		return list;
	}
	
	public String saveOrUpdateAll(List<TMatchSettled> list){
		//this.getHibernateTemplate_shared().saveOrUpdateAll(list);
		String url = CommonConstant.resCommMap.get(CommonConstant.SPORT_INTERFACE_AUT_URL) + "/sport/agent/api/getRelateResultSettlement";
		Map<String , String> map = new HashMap<String ,String>();
		JSONArray  jsonArray = JSONArray.fromObject(list);
		String	   jsonString = jsonArray.toString();
		map.put("json", jsonString);
		String res = HttpClientUtil.post(url, map);
		JSONObject jsonObject = JSONObject.fromObject(res);
		return jsonObject.getString("code");
	}
	

}
