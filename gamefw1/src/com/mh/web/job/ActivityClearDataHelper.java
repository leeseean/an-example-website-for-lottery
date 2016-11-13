/**   
* 文件名称: ClearDataHelper.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-18 上午3:00:28<br/>
*/  
package com.mh.web.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.utils.DateUtil;

/** 
 * 清数据
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-18 上午3:00:28<br/>
 */
public class ActivityClearDataHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityClearDataHelper.class);
	
	private JdbcTemplate jdbcTemplate;

	private HibernateTemplate hibernateTemplate;
	
	
	
	public synchronized  void init(){
		int actDays = Integer.valueOf(CommonConstant.resCommMap.get(CommonConstant.ACTIVITY_CLEAR_ACT_DAYS));
		int lotDays = Integer.valueOf(CommonConstant.resCommMap.get(CommonConstant.ACTIVITY_CLEAR_LOT_DAYS));
		Date currDate = new Date();
		Date actDate = DateUtil.addDay(currDate, actDays);
		Date lotDate = DateUtil.addDay(currDate, lotDays);
		
		String actDateStr = DateUtil.formatDate(actDate, "yyyy-MM-dd");
		String lotDateStr = DateUtil.formatDate(lotDate, "yyyy-MM-dd");
		
		logger.info("清除数据开始....");
		String sql1 = " update t_activity_user set lott_times=0,add_times=0,modify_time=SYSDATE() ";
		int rows1= this.getJdbcTemplate().update(sql1);
		logger.info("抽奖用户表【"+rows1+"】条清除成功！");
		String sql2= "delete from t_activity_user_log where date_format(update_time,'%Y-%m-%d') <='"+actDateStr+"'";
		int rows2= this.getJdbcTemplate().update(sql2);
		logger.info("用户活动日志【"+rows2+"】条清除成功！");
		
		String sql3= "delete from t_activity_log where date_format(create_time,'%Y-%m-%d') <='"+lotDateStr+"'";
		int rows3= this.getJdbcTemplate().update(sql3);
		logger.info("用户抽奖日志【"+rows3+"】条清除成功！");
		
		logger.info("清除数据结算。");
		
	}
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public ActivityClearDataHelper(JdbcTemplate jdbcTemplate,
			HibernateTemplate hibernateTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
