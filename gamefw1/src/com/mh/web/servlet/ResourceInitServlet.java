package com.mh.web.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.CpCommonConstant;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.SpringContextHolder;
import com.mh.service.WebSiteManagerService;
import com.mh.web.job.CodeDataHelper;
import com.mh.web.job.CpCacheHelper;
import com.mh.web.job.ResourceDataHelper;
import com.mh.web.job.TemplateHelper;
import com.mh.web.job.WebFlatSiteDateHelper;
import com.mh.web.job.WebResourceDataHelper;
import com.mh.web.util.WebInitData;

/**
 * 过滤器初始化参数Servlet 类描述: TODO<br/>
 * 修改人: alex<br/>
 * 修改时间: 2015-6-28 下午5:25:05<br/>
 */
public class ResourceInitServlet extends HttpServlet {

	private static final Log logger = LogFactory.getLog(ResourceInitServlet.class);

	private static final long serialVersionUID = 1L;

	public ResourceInitServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		initProperties(config);// app-base.properties
		initCoreProperties(config);// app-web.properties
		initLotConfigData(config);// cp:Cp_Type Cp_Category Cp_Game Cp_Config
		initSysCodeData(config);// t_sys_code
		initWebSitManagerDate(config);// slots
		initWebData(config);// 网站管理
		WebSiteManagerConstants.initCtxMap();// 常用数据
		initCpData(config);
		initWebFlatSitDate(config);//网站平台初始化数据
		initTemplate(config);
		//initCpData(config);
		initCpszHtml(config);
	}
	
	public void initTemplate(ServletConfig config){
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
//		HibernateTemplate hibernateTemplate_shared = (HibernateTemplate) wac.getBean("hibernateTemplate_shared");
		JdbcTemplate jdbcTemplate_shared = (JdbcTemplate) wac.getBean("jdbcTemplate_shared");
		TemplateHelper templateHelper = new TemplateHelper(jdbcTemplate_shared,config.getServletContext().getRealPath("/"));
		templateHelper.initData();
	}
	
	private void initCpszHtml(ServletConfig config){
		
		String path = config.getServletContext().getRealPath("/")+"commons/cp/template/fc3d/sz.html";
		StringBuffer sbuff = new StringBuffer();
		try {
			BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				sbuff.append(inputLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CpCommonConstant.buf.append(sbuff.toString().replace("\t", "")) ;
	}
	
	public void initCpData(ServletConfig config){
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		HibernateTemplate hibernateTemplate_shared = (HibernateTemplate) wac.getBean("hibernateTemplate_shared");
		JdbcTemplate jdbcTemplate_shared = (JdbcTemplate) wac.getBean("jdbcTemplate_shared");
		
		CpCacheHelper cp=new CpCacheHelper(jdbcTemplate_shared,hibernateTemplate_shared);
		cp.getBaseData();
	}

	private void initLotConfigData(ServletConfig config) {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		HibernateTemplate hibernateTemplate = (HibernateTemplate) wac.getBean("hibernateTemplate");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) wac.getBean("jdbcTemplate");
		ResourceDataHelper helper = new ResourceDataHelper(jdbcTemplate, hibernateTemplate);
		helper.initData();
	}

	public void initWebData(ServletConfig config) {
		// ApplicationContext ctx =
		// WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		WebSiteManagerService webSiteManagerService = (WebSiteManagerService) SpringContextHolder.getBean("webSiteManagerServiceImpl");
		// GenericXmlApplicationContext context = new
		// GenericXmlApplicationContext();
		try {
			// context.setValidating(false);
			// context.load("classpath*:conf/applicationContext.xml");
			// context.refresh();
			// //String
			// path=this.getServletConfig().getServletContext().getRealPath("");
			// WebSiteManagerService webSiteManagerService =
			// context.getBean(WebSiteManagerService.class);
			WebInitData wi = new WebInitData();
			wi.initDataList(webSiteManagerService, config);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("初始化网站数据异常!");
			e.printStackTrace();
		}

	}

	private void initSysCodeData(ServletConfig config) {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		HibernateTemplate hibernateTemplate = (HibernateTemplate) wac.getBean("hibernateTemplate");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) wac.getBean("jdbcTemplate");
		CodeDataHelper helper = new CodeDataHelper(jdbcTemplate, hibernateTemplate);
		helper.initData();
	}

	private void initWebSitManagerDate(ServletConfig config) {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		HibernateTemplate hibernateTemplate_shared = (HibernateTemplate) wac.getBean("hibernateTemplate_shared");
		JdbcTemplate jdbcTemplate_shared = (JdbcTemplate) wac.getBean("jdbcTemplate_shared");
		WebResourceDataHelper helper = new WebResourceDataHelper(jdbcTemplate_shared, hibernateTemplate_shared);
		helper.initData();
	}
	
	private void initWebFlatSitDate(ServletConfig config) {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		HibernateTemplate hibernateTemplate = (HibernateTemplate) wac.getBean("hibernateTemplate");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) wac.getBean("jdbcTemplate");
		WebFlatSiteDateHelper helper = new WebFlatSiteDateHelper(jdbcTemplate, hibernateTemplate);
		helper.initData();
	}

	private void initProperties(ServletConfig config) {
		ServletContext context = config.getServletContext();
		InputStream in = context.getResourceAsStream(config.getInitParameter(CommonConstant.RESOURCE_INIT_PATH));
		Properties property = new Properties();
		try {
			property.load(in);

			Object[] keyObjs = property.keySet().toArray();
			for (Object keyObj : keyObjs) {
				String key = (String) keyObj;
				CommonConstant.resCommMap.put(key, property.getProperty(key));
			}

			logger.info("配置文件初始化成功:" + CommonConstant.resCommMap);
		} catch (Exception e) {
			logger.error("配置文件初始化失败", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private void initCoreProperties(ServletConfig config) {
		ServletContext context = config.getServletContext();
		InputStream in = context.getResourceAsStream("/WEB-INF/classes/conf/app-web.properties");
		Properties property = new Properties();
		try {
			property.load(in);

			Object[] keyObjs = property.keySet().toArray();
			for (Object keyObj : keyObjs) {
				String key = (String) keyObj;
				CommonConstant.resCommMap.put(key, property.getProperty(key));
			}

			logger.info("配置文件初始化成功:" + CommonConstant.resCommMap);
		} catch (Exception e) {
			logger.error("配置文件初始化失败", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public void destroy() {
	}
}
