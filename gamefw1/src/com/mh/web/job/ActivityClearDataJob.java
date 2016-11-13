/**   
* 文件名称: LotteryDataJob.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-17 上午4:54:16<br/>
*/  
package com.mh.web.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/** 
 * 抽奖结算
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-17 上午4:54:16<br/>
 */
public class ActivityClearDataJob  implements  Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) arg0.getJobDetail().getJobDataMap().get("jdbcTemplate");
		HibernateTemplate hibernateTemplate = (HibernateTemplate) arg0.getJobDetail().getJobDataMap().get("hibernateTemplate");
		ActivityClearDataHelper helper = new ActivityClearDataHelper(jdbcTemplate,hibernateTemplate);
		helper.init();
		
	}

}
