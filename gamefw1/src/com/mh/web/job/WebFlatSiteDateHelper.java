/**   
* 文件名称: WebFlatSitDateHelp.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2016-3-15 上午10:14:09<br/>
*/  
package com.mh.web.job;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mh.commons.conf.ResourceConstant;
import com.mh.entity.TWebFlatInfo;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2016-3-15 上午10:14:09<br/>
 */
public class WebFlatSiteDateHelper {
private static final Logger logger = LoggerFactory.getLogger(WebFlatSiteDateHelper.class);
	
	private JdbcTemplate jdbcTemplate;
	
	private HibernateTemplate hibernateTemplate;
	public WebFlatSiteDateHelper(JdbcTemplate jdbcTemplate,
			HibernateTemplate hibernateTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.hibernateTemplate = hibernateTemplate;
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
	
	
	public  synchronized void initData(){
		logger.info("***********初始化配置表开始***********");
		try{
			getBaseData();
		}catch(Exception e){
			logger.info("初始化数据出错...");
			e.printStackTrace();
		}
		logger.info("***********初始化配置表结束***********");
	}
	
	
	/**
	 * 初始化
	 * 方法描述: TODO</br>   
	 * void
	 */
	public  synchronized void getBaseData(){
		List<TWebFlatInfo> liveSite = new ArrayList<TWebFlatInfo>();
		List<TWebFlatInfo> slotSite = new ArrayList<TWebFlatInfo>();
		List<TWebFlatInfo> sportSite = new ArrayList<TWebFlatInfo>();
		List<TWebFlatInfo> cpSite = new ArrayList<TWebFlatInfo>();
		List<TWebFlatInfo> list =this.getWebFlatInfoList();
		for(TWebFlatInfo info : list){
			String type = info.getFlatType();
			if("live".equals(type)){
				liveSite.add(info);
			}else if("sport".equals(type)){
				sportSite.add(info);
			}else if("slot".equals(type)){
				slotSite.add(info);
			}else if("caipiao".equals(type)){
				cpSite.add(info);
			}
		}
		
		ResourceConstant.liveSite = liveSite;
		ResourceConstant.slotSite = slotSite;
		ResourceConstant.sportSite = sportSite;
		ResourceConstant.cpSite = cpSite;
	}
	
	@SuppressWarnings("unchecked")
	public List<TWebFlatInfo> getWebFlatInfoList() {
		String hql = "FROM TWebFlatInfo WHERE flatStatus=1 ORDER BY flatIndex DESC";
		return this.getHibernateTemplate().find(hql);
	}

}
