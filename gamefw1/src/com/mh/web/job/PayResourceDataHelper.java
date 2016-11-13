package com.mh.web.job;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.mh.commons.conf.CommonConstant;

public class PayResourceDataHelper {
private static final Logger logger = LoggerFactory.getLogger(WebResourceDataHelper.class);
	
	private HibernateTemplate hibernateTemplate;
	
	public synchronized void initData(WebApplicationContext wac){
		logger.info("***********初始化web快捷支付信息***********");
		try
		{
			ServletContext servletContext = wac.getServletContext();
			int wx_count = this.getPayCount(CommonConstant.WX_PAY);
			int ali_count = this.getPayCount(CommonConstant.ALI_PAY);
			servletContext.setAttribute("wx_count", wx_count);
			servletContext.setAttribute("ali_count", ali_count);
		}
		catch(Exception e)
		{
			logger.info("初始化web快捷支付信息数据出错...");
			e.printStackTrace();
		}
		logger.info("***********初始化web快捷支付信息结束***********");
	}
	
	public int getPayCount(Integer type)
	{
		String hql = " from WebKjPay where status = 1 and payType = ?";
		return this.getHibernateTemplate().find(hql,new Object[]{type}).size();
	}

	public PayResourceDataHelper(HibernateTemplate hibernateTemplate) {
		super();
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
