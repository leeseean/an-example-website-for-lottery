/**   
* 文件名称: WebCommisionDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-9-1 下午7:33:08<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-9-1 下午7:33:08<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebCommisionDao  extends BaseDao{
	
	public Page searchReportList(Page page,String qs,String agentName){
		String sql = "SELECT * FROM t_agent_commision WHERE bet_qs LIKE '%"+qs+"%' ";
		if(StringUtils.isNotEmpty(agentName)){
			sql += " and bet_user_agent='"+agentName+"' ";
		}
		sql += " ORDER BY comms_total DESC, member_total DESC";
		return findPageBySql(page, sql);
	}
	
	
	public Map<String, Object> searchCommisionDetail(String agentName, String qs, String flat){
		List<String> list = new ArrayList<String>();
		
		String sql = "SELECT * FROM t_agent_commision_detail where 1=1 ";
		if(!StringUtils.isEmpty(agentName)){
			sql += " and bet_user_agent=? ";
			list.add(agentName);
		}
		
		if(!StringUtils.isEmpty(flat)){
			sql += " and bet_flat=? ";
			list.add(flat);
		}
		if(!StringUtils.isEmpty(qs)){
			sql += " and bet_qs  like ?";
			list.add("%"+qs+"%");
		}
		List<Map<String,Object>> valList = this.getJdbcTemplate().queryForList(sql, list.toArray());
		
		Map<String,Object> valMap = new HashMap<String,Object>();
		if(valList!=null&&valList.size()>0){
			valMap = valList.get(0);
		}
		
		
		return valMap;
	}
	
	
	public Page searchUserRecords(Page page, String agentName,  String flat, String begin, String end){
 		List<String> list = new ArrayList<String>();
		
		String sql = 
				"SELECT bet_user_name AS userName, ROUND(SUM(bet_in), 2) AS betIn, ROUND(SUM(bet_income), 2) AS betIncome," +
				" ROUND(SUM(bet_usr_win), 2) AS betUserWin, ROUND(SUM(bet_net_win), 2) AS betNetWin, SUM(bet_total) AS betTotal " +
				" FROM t_bet_day_report_user where bet_user_agent=? and bet_flat=?  " +
				" and STR_TO_DATE(bet_date,'%Y-%m-%d')>= ? and STR_TO_DATE(bet_date,'%Y-%m-%d') <= ?    GROUP BY userName";
		list.add(agentName);
		list.add(flat);
		list.add(begin);
		list.add(end);
		return this.findPageBySql(page, sql, list.toArray());
	}
	
	public Map<String, Object> reportUserRecordsTotal(String agentName,  String flat, String begin, String end){
		List<String> list = new ArrayList<String>();
		String sql =
				"SELECT ROUND(SUM(bet_in), 2) AS betIn, ROUND(SUM(bet_income), 2) AS betIncome,"+
				"ROUND(SUM(bet_usr_win), 2) AS betUserWin, ROUND(SUM(bet_net_win), 2) AS betNetWin, SUM(bet_total) AS betTotal "+
				" FROM t_bet_day_report_user WHERE bet_user_agent=? AND bet_flat=? "+
				" and STR_TO_DATE(bet_date,'%Y-%m-%d')>= ? and STR_TO_DATE(bet_date,'%Y-%m-%d') <= ? ";
		list.add(agentName);
		list.add(flat);
		list.add(begin);
		list.add(end);
		
		return this.getJdbcTemplate().queryForMap(sql, list.toArray());
	}
	
	
	public Map<String, Object> reportUserRecordsPage(Page page, String agentName,  String flat, String begin, String end){
		List<String> list = new ArrayList<String>();
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNo();
		int index = (pageNo-1)*pageSize;
		
		String sql =
				"SELECT ROUND(SUM(bet_in), 2) AS betIn, ROUND(SUM(bet_income), 2) AS betIncome,"+
				"ROUND(SUM(bet_usr_win), 2) AS betUserWin, ROUND(SUM(bet_net_win), 2) AS betNetWin, SUM(bet_total) AS betTotal "+
				" FROM (" +
				" SELECT ROUND(SUM(bet_in), 2) AS bet_in,  ROUND(SUM(bet_income), 2) AS bet_income, ROUND(SUM(bet_usr_win), 2) AS bet_usr_win," +
				" ROUND(SUM(bet_net_win), 2) AS bet_net_win, SUM(bet_total) AS bet_total " +
				" FROM t_bet_day_report_user WHERE bet_user_agent=? AND bet_flat=? "+
				" and STR_TO_DATE(bet_date,'%Y-%m-%d')>= ? and STR_TO_DATE(bet_date,'%Y-%m-%d') <= ? " +
				" GROUP BY bet_user_name LIMIT "+index+","+page.getPageSize()+")a";
		list.add(agentName);
		list.add(flat);
		list.add(begin);
		list.add(end);
		
		return this.getJdbcTemplate().queryForMap(sql, list.toArray());
	}


}
