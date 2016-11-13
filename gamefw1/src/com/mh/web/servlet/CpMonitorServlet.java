/**   
* 文件名称: CpMonitorServlet.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-31 上午11:26:33<br/>
*/  
package com.mh.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mh.commons.conf.CommonConstant;
import com.mh.web.job.ActivityClearDataJob;
import com.mh.web.job.LotteryDataJob;

/** 
 * 彩票监控Servlet
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-31 上午11:26:33<br/>
 */
public class CpMonitorServlet  extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	private static final Log logger = LogFactory.getLog(CpMonitorServlet.class);
	
	private Scheduler scheduler;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
			
			HibernateTemplate hibernateTemplate = (HibernateTemplate) wac.getBean("hibernateTemplate");
			JdbcTemplate jdbcTemplate = (JdbcTemplate) wac.getBean("jdbcTemplate");

			scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			//抽奖结算
			String time2 = CommonConstant.resCommMap.get(CommonConstant.ACTIVITY_BALACE_TIME);
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			JobDetail jobDetail2 = new JobDetail("LotteryDataJob", "GROUP2",LotteryDataJob.class);
//			Trigger trigger1 = new SimpleTrigger("LotteryDataJob",Scheduler.DEFAULT_GROUP, new Date(), null,SimpleTrigger.REPEAT_INDEFINITELY,2*60*1000);
			
			CronTrigger trigger2 = new CronTrigger("trigger2", "tgroup2");
			CronExpression cexp2 = new CronExpression(time2); 
			trigger2.setCronExpression(cexp2); 
			jobDetail2.getJobDataMap().put("hibernateTemplate",hibernateTemplate);
			jobDetail2.getJobDataMap().put("jdbcTemplate",jdbcTemplate);
			scheduler.scheduleJob(jobDetail2, trigger2);
			
			//抽奖数据清除
			String time3 = CommonConstant.resCommMap.get(CommonConstant.ACTIVITY_CLEAR_TIME);
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			JobDetail jobDetail3 = new JobDetail("ActivityClearDataJob", "GROUP3",ActivityClearDataJob.class);
//			Trigger trigger3= new SimpleTrigger("ActivityClearDataJob",Scheduler.DEFAULT_GROUP, new Date(), null,SimpleTrigger.REPEAT_INDEFINITELY,bbinGather*60*1000);
			
			CronTrigger trigger3 = new CronTrigger("trigger3", "tgroup3");
			CronExpression cexp3 = new CronExpression(time3); 
			trigger3.setCronExpression(cexp3); 
			jobDetail3.getJobDataMap().put("hibernateTemplate",hibernateTemplate);
			jobDetail3.getJobDataMap().put("jdbcTemplate",jdbcTemplate);
			scheduler.scheduleJob(jobDetail3, trigger3);
			
			
			// 启动任务
			if (!scheduler.isStarted()) {
				scheduler.start();
			}
			logger.info("Quartz Scheduler successful start.");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Quartz Scheduler start error", e);
		}
	}
	
	
	public void destroy() {
		try {
			// 关闭调度器
			if (scheduler != null && !scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			logger.error("Quartz Scheduler destroy error", e);
		}
		logger.info("Quartz Scheduler successful shutdown.");
	}

}
