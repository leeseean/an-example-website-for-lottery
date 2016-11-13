/**   
* 文件名称: AgentReprotDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-29 上午6:43:18<br/>
*/  
package com.mh.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.WebAgent;

/** 
 * 
 * 代理报表
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-29 上午6:43:18<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebAgentReprotDao  extends BaseDao<String, Integer> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/***
	 * 会员代理报表列表
	 * 方法描述: TODO</br> 
	 * @return  
	 * Map<List<String,Object>>
	 */
	public Page getUserReport(Page page,WebAgent webAgent){
		List<Object> list = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT userName, ROUND(SUM(bet_in),2) AS bet_in, ROUND(SUM(bet_income),2) AS bet_income, ");
		sqlBuffer.append("ROUND(SUM(bet_usr_win),2) AS bet_usr_win ,ROUND(SUM(bet_net_win),2) AS bet_net_win, SUM(bet_total) AS bet_total FROM ");
		sqlBuffer.append("(");
		sqlBuffer.append("\n");
		
		sqlBuffer.append("	SELECT bet_user_name AS userName, ROUND(SUM(bet_in),2) AS bet_in, ROUND(SUM(bet_income),2) AS bet_income, ");
		sqlBuffer.append("	ROUND(SUM(bet_usr_win),2) AS bet_usr_win ,ROUND(SUM(bet_net_win),2) AS bet_net_win ,SUM(bet_total) AS bet_total FROM t_bet_day_report_user  where 1=1 ");
		sqlBuffer.append("\n");
		/**where条件开始**/
		
		Date currDate=new Date();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		try {
			currDate = DateUtil.parse(currDateStr, "yyyy-MM-dd");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long times = currDate.getTime();
		boolean reFlag = false;
		if(StringUtils.isNotBlank(webAgent.getPlatName())){
			sqlBuffer.append(" AND bet_flat = '"+webAgent.getPlatName()+"' ");
		}
		if(StringUtils.isNotBlank(webAgent.getUserName())){
			sqlBuffer.append(" AND bet_user_agent = '"+webAgent.getUserName()+"' ");
		}
		
		if (StringUtils.isNotBlank(webAgent.getBeginTimeStr()) && StringUtils.isNotBlank(webAgent.getEndTimeStr())) {
			Date date1=null;
			Date date2=null;
			try {
				date1 = DateUtil.parse(webAgent.getBeginTimeStr(), "yyyy-MM-dd");
				date2 = DateUtil.parse(webAgent.getEndTimeStr(), "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long times1 = date1.getTime();
			long times2 = date2.getTime();
			if(times>=times1&&times<=times2){
				reFlag = true;
			}
			sqlBuffer.append( " and date_format(bet_date,'%Y-%m-%d') >= '"+webAgent.getBeginTimeStr()+"' ");
			sqlBuffer.append(" and date_format(bet_date,'%Y-%m-%d') <= '"+webAgent.getEndTimeStr()+"' ");
		}else if (StringUtils.isNotBlank(webAgent.getBeginTimeStr())) {
			sqlBuffer.append( " and date_format(bet_date,'%Y-%m-%d') >= '"+webAgent.getBeginTimeStr()+"' ");
			Date date1=null;
			try {
				date1 = DateUtil.parse(webAgent.getBeginTimeStr(), "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long times1 = date1.getTime();
			if(times>=times1){
				reFlag = true;
			}
		}else if (StringUtils.isNotBlank(webAgent.getEndTimeStr())) {
			sqlBuffer.append(" and date_format(bet_date,'%Y-%m-%d') <= '"+webAgent.getEndTimeStr()+"' ");
			Date date2=null;
			try {
				date2 = DateUtil.parse(webAgent.getEndTimeStr(), "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long times2 = date2.getTime();
			if(times<=times2){
				reFlag = true;
			}
		}

		
		/**where条件结束**/
		sqlBuffer.append(" GROUP BY bet_user_name  ");
		/**union条件开始**/
		if(reFlag){
			String sql2 = this.getUserBetReportSql(webAgent.getPlatName(),webAgent.getUserName());
			sqlBuffer.append("\n");
			sqlBuffer.append(" UNION ");
			sqlBuffer.append("\n");
			sqlBuffer.append(sql2);
		}
		
		/**union条件结束**/
		sqlBuffer.append("\n");
		sqlBuffer.append(")c GROUP BY userName");
		
		logger.debug("*************"+sqlBuffer.toString());
		
		return findPageBySql(page, sqlBuffer.toString(), list.toArray());
	}
	
	
	
	
	/**
	 * 获取代理报表
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<Map<String,Object>>
	 */
	public Page getAgentReport(Page page,WebAgent webAgent){
		List<Object> list = new ArrayList<Object>();
		String sql1 = "select bet_user_agent,date_format(bet_date,'%Y-%m-%d') as bet_date,bet_flat,bet_in,bet_income,bet_usr_win,bet_net_win,bet_total FROM t_bet_day_report_agent where 1=1 ";
		Date currDate=new Date();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		try {
			
			currDate = DateUtil.parse(currDateStr, "yyyy-MM-dd");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long times = currDate.getTime();
		boolean reFlag = false;
		if(StringUtils.isNotBlank(webAgent.getPlatName())){
			sql1 += " and bet_flat = '"+webAgent.getPlatName()+"' ";
		}
		if(StringUtils.isNotBlank(webAgent.getAgentNo())){
			sql1 += " and bet_user_agent = '"+webAgent.getAgentNo()+"' ";
		}
		
		if (StringUtils.isNotBlank(webAgent.getBeginTimeStr()) && StringUtils.isNotBlank(webAgent.getEndTimeStr())) {
			Date date1=null;
			Date date2=null;
			try {
				date1 = DateUtil.parse(webAgent.getBeginTimeStr(), "yyyy-MM-dd");
				date2 = DateUtil.parse(webAgent.getEndTimeStr(), "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long times1 = date1.getTime();
			long times2 = date2.getTime();
			if(times>=times1&&times<=times2){
				reFlag = true;
			}
			sql1 += " and date_format(bet_date,'%Y-%m-%d') >= '"+webAgent.getBeginTimeStr()+"' ";
			sql1 += " and date_format(bet_date,'%Y-%m-%d') <= '"+webAgent.getEndTimeStr()+"' ";
		}else if (StringUtils.isNotBlank(webAgent.getBeginTimeStr())) {
			sql1 += " and date_format(bet_date,'%Y-%m-%d') >= '"+webAgent.getBeginTimeStr()+"' ";
			Date date1=null;
			try {
				date1 = DateUtil.parse(webAgent.getBeginTimeStr(), "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long times1 = date1.getTime();
			if(times>=times1){
				reFlag = true;
			}
		}else if (StringUtils.isNotBlank(webAgent.getEndTimeStr())) {
			sql1 += " and date_format(bet_date,'%Y-%m-%d') <= '"+webAgent.getEndTimeStr()+"' ";
			Date date2=null;
			try {
				date2 = DateUtil.parse(webAgent.getEndTimeStr(), "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long times2 = date2.getTime();
			if(times<=times2){
				reFlag = true;
			}
		}
		
		
		StringBuffer buff = new StringBuffer("");
		buff.append("select t.* from (");
		buff.append(sql1);
		String sql2="";
		if(reFlag){
			sql2 = this.getAgentBetReportSql(webAgent.getPlatName(),webAgent.getAgentNo());
			buff.append("union all ");
			buff.append(sql2);
		}
		
		buff.append(") t");
		
		buff.append(" order by t.bet_date desc ");
		
		logger.debug("*************"+buff.toString());
		
		return findPageBySql(page, buff.toString(), list.toArray());
	}
	
	
	
	/**
	 * 查询用户报表
	 * 方法描述: TODO</br> 
	 * @param platFlag
	 * @param dateStr
	 * @return  
	 * List<Map<String,Object>>
	 */
	public String getUserBetReportSql(String platFlag,String agentName){
		String webFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String sql = "";
		String dateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		if(CommonConstant.PLAT_PARAM_SPORT.equals(platFlag)){
			sql = "SELECT bet_user_name AS userName, ROUND(SUM(bet_in),2) AS bet_in, ROUND(SUM(bet_income),2) AS bet_income,ROUND(SUM(bet_usr_win),2) AS bet_usr_win ,ROUND(SUM(bet_net_win),2) AS bet_net_win,COUNT(1) AS bet_total " +
				"FROM t_web_match_bet  WHERE STATUS=2   and date_format(bet_settled_time,'%Y-%m-%d') ='"+dateStr+"'  and bet_user_agent='" +agentName+"' "+
				"GROUP BY bet_user_name ";
		}else if(CommonConstant.PLAT_PARAM_CAIPIAO.equals(platFlag)){
			sql = "SELECT USER_NAME AS userName, ROUND(SUM(XZJE),2) AS bet_in, ROUND(SUM(YXTZ),2) AS bet_income,ROUND(SUM(HYSF),2) AS bet_usr_win,ROUND(SUM(HYSF),2)*(-1) AS bet_net_win,COUNT(1) AS bet_total " +
				"FROM cp_order  WHERE ORDER_STATUS='2' and date_format(XZSJ,'%Y-%m-%d') ='"+dateStr+"'  and bet_user_agent='" +agentName+"' "+
				"GROUP BY USER_NAME ";
		}else if(CommonConstant.PLAT_PARAM_DOUJI.equals(platFlag)){
			
		}else {
			sql = "SELECT bet_user_name AS userName, ROUND(SUM(bet_in),2) AS bet_in, ROUND(SUM(bet_income),2) AS bet_income,ROUND(SUM(bet_usr_win),2) AS bet_usr_win ,ROUND(SUM(bet_net_win),2) AS bet_net_win,COUNT(1) AS bet_total " +
				"FROM t_bet_record_"+platFlag+"  WHERE STATUS=1 AND web_flag='"+webFlag+"'  and date_format(bet_time,'%Y-%m-%d') ='"+dateStr+"'  and bet_user_agent='" +agentName+"' "+
				"GROUP BY bet_user_name ";
		}
		logger.info("【"+platFlag+"】【"+dateStr+"】"+sql);
		
		return sql;
		
	}
	
	/**
	 * 查询代理报表
	 * 方法描述: TODO</br> 
	 * @param platFlag
	 * @param dateStr
	 * @return  
	 * List<Map<String,Object>>
	 */
	public String getAgentBetReportSql(String platFlag,String agentNo){
		String webFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String sql = "";
		String dateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		if(CommonConstant.PLAT_PARAM_SPORT.equals(platFlag)){
			sql = "SELECT bet_user_agent AS bet_user_agent,'"+dateStr+"' as bet_date,'"+platFlag+"' as bet_flat, ROUND(SUM(bet_in),2) AS bet_in, ROUND(SUM(bet_income),2) AS bet_income,ROUND(SUM(bet_usr_win),2) AS bet_usr_win ,ROUND(SUM(bet_net_win),2) AS bet_net_win,COUNT(1) AS bet_total " +
				"FROM t_web_match_bet  WHERE STATUS=2   and date_format(bet_settled_time,'%Y-%m-%d') ='"+dateStr+"' and bet_user_agent='" +agentNo+"' "+
				"GROUP BY bet_user_agent ";
		}else if(CommonConstant.PLAT_PARAM_CAIPIAO.equals(platFlag)){
			sql = "SELECT BET_USER_AGENT AS bet_user_agent,'"+dateStr+"' as bet_date,'"+platFlag+"' as bet_flat, ROUND(SUM(XZJE),2) AS bet_in, ROUND(SUM(XZJE),2) AS bet_income,ROUND(SUM(HYSF),2) AS bet_usr_win,ROUND(SUM(HYSF),2)*(-1) AS bet_net_win,COUNT(1) AS bet_total " +
				"FROM cp_order  WHERE ORDER_STATUS='2' and date_format(XZSJ,'%Y-%m-%d') ='"+dateStr+"'  and bet_user_agent='" +agentNo+"' "+
				"GROUP BY bet_user_agent ";
		}else if(CommonConstant.PLAT_PARAM_DOUJI.equals(platFlag)){
			
		}else{
			sql = "SELECT bet_user_agent AS bet_user_agent,'"+dateStr+"' as bet_date,'"+platFlag+"' as bet_flat, ROUND(SUM(bet_in),2) AS bet_in, ROUND(SUM(bet_income),2) AS bet_income,ROUND(SUM(bet_usr_win),2) AS bet_usr_win ,ROUND(SUM(bet_net_win),2) AS bet_net_win,COUNT(1) AS bet_total " +
				"FROM t_bet_record_"+platFlag+"  WHERE STATUS=1 AND web_flag='"+webFlag+"'  and date_format(bet_time,'%Y-%m-%d') ='"+dateStr+"'  and bet_user_agent='" +agentNo+"' "+
				"GROUP BY bet_user_agent ";
		}
		
		logger.info("【"+platFlag+"】【"+dateStr+"】"+sql);
		return sql;
	}
}
