package com.mh.web.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.enums.WebTableEnum;
import com.mh.entity.SysParameter;
import com.mh.entity.WebLineCk;
import com.mh.entity.WebPage;
import com.mh.entity.WebSite;
import com.mh.service.WebSiteManagerService;
import com.mh.web.job.WebResourceDataHelper;
import com.mh.web.util.FreeMarkerUtil;
import com.mh.web.util.WebInitData;
import com.mh.web.utilBean.FreeMarkerBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
@SuppressWarnings("all")
@Controller
@RequestMapping("/initWeb")
public class NewWebDataAndHtmlController extends BaseController {
	@Resource
	private WebSiteManagerService webSiteManagerService;
	
	private HttpServletRequest req;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/webData")
	public void initWebData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		logger.info("初始化进入!");
		String dataTypeJson = request.getParameter("dataType");
		logger.info("项目：" + dataTypeJson);
		req = request;
		if (StringUtils.isNotBlank(dataTypeJson)) {
			json = JSONObject.parseObject(dataTypeJson);
			String dataType = json.getString("dataType");
			String[] datas = dataType.split(",");

			for (int i = 0; i < datas.length; i++) {
				String data = datas[i];
				this.initDataList(data);
				logger.info("刷新" + data + "：OK");
			}
			WebSiteManagerConstants.initCtxMap();// 常用数据
			logger.info("刷新CTX：" + "OK");
		}
		logger.info("初始化完成!");
	}

	/**
	 * @Description: TODO
	 * @param
	 * @return void
	 * @author Andy
	 * @throws Exception
	 * @date 2015-7-21
	 */
	public void initDataList(String table) throws Exception {
		if (table.equals(WebTableEnum.SYSPARAM.tname)) {
			List<SysParameter> list = new ArrayList<SysParameter>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			list=webSiteManagerService.sysparameter_list();
			for (SysParameter sp : list) {
				paramMap.put(sp.getParamName(), sp.getParamValue());
			}
			WebSiteManagerConstants.SYSPARAMMAP=paramMap;//sysparamter参数表
		} else if (table.equals(WebTableEnum.WEBSITE.tname)) {
			List<WebSite> websiteList = webSiteManagerService.webSite_list();
			if (websiteList.size() > 0) {
				WebSiteManagerConstants.WEBSITE_INFO = websiteList.get(0);
			}
		} else if (table.equals(WebTableEnum.LINKWEBPAGE.tname)) {
			WebSiteManagerConstants.LINKWEBPAGE_LIST = webSiteManagerService.linkwebpage_list();
		} else if (table.equals(WebTableEnum.LINKWEBPROMOSTYPE.tname)) {
			WebSiteManagerConstants.LINKWEBPROMOSTYPE_LIST = webSiteManagerService.linkwebpromostype_list();
		} else if (table.equals(WebTableEnum.WEBCAROUSEL.tname)) {
			WebSiteManagerConstants.WEBCAROUSEL_LIST = webSiteManagerService.webcarousel_list();
		} else if (table.equals(WebTableEnum.WEBGONGGAO.tname)) {
			WebSiteManagerConstants.WEBGONGGAO_LIST = webSiteManagerService.webgonggao_list();
		} else if (table.equals(WebTableEnum.WEBLINECK.tname)) {
			List<WebLineCk> ckList = webSiteManagerService.weblineck_list();
			if (null != ckList && ckList.size() > 0) {
				WebSiteManagerConstants.WEBLINECK_INFO = ckList.get(0);
			}
		} else if (table.equals(WebTableEnum.WEBPAGE.tname)) {// 生成帮助页面
			// WebSiteManagerConstants.WEBPAGE_LIST=webSiteManagerService.webpage_list();
			String ftlPath = req.getSession().getServletContext().getRealPath(WebSiteManagerConstants.FTL_PATH);
			String savePath = req.getSession().getServletContext().getRealPath(WebSiteManagerConstants.HELP_HTML_PATH) + "/";// html存放文件夹路径
			new WebInitData().initWebPageData(ftlPath, savePath, webSiteManagerService);
		} else if (table.equals(WebTableEnum.WEBPANEL.tname)) {
			WebSiteManagerConstants.WEBPANEL_LIST = webSiteManagerService.webpanel_list();
		} else if (table.equals(WebTableEnum.WEBPROMOS.tname)) {// 生成活动页面
			String ftlPath = req.getSession().getServletContext().getRealPath(WebSiteManagerConstants.FTL_PATH);
			String savePath = req.getSession().getServletContext().getRealPath(WebSiteManagerConstants.PROMOS_HTML_PATH) + "/";// html存放文件夹路径
			new WebInitData().initWebPromosData(ftlPath, savePath, webSiteManagerService);
		} else if (table.equals(WebTableEnum.WEBPROMOSTYPE.tname)) {
			// WebSiteManagerConstants.WEBPROMOSTYPE_LIST=webSiteManagerService.webpromostype_list();
		} else if (table.equals(WebTableEnum.WEBRESOURCE.tname)) {
			WebSiteManagerConstants.WEBRESOURCE_LIST = webSiteManagerService.webresource_list();
		} else if (table.equals(WebTableEnum.WEBSWITCH.tname)) {
			WebSiteManagerConstants.WEBSWITCH_LIST = webSiteManagerService.webswitch_list();
		} else if (table.equals(WebTableEnum.WEBWEIHU.tname)) {
			WebSiteManagerConstants.WEBWEIHU_LIST = webSiteManagerService.webweihu_list();
		} else if (table.equals(WebTableEnum.WEBAGENT.tname)) {
			WebSiteManagerConstants.WEBAGENT_LIST = webSiteManagerService.webAgent_list();
		} else if(table.equals(WebTableEnum.CPCONFIG.tname)){
		} else if (table.equals(WebTableEnum.SLOTCONFIG.tname)) {
			WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
			HibernateTemplate hibernateTemplate_shared = (HibernateTemplate) wac.getBean("hibernateTemplate_shared");
			JdbcTemplate jdbcTemplate_shared = (JdbcTemplate) wac.getBean("jdbcTemplate_shared");
			WebResourceDataHelper helper = new WebResourceDataHelper(jdbcTemplate_shared, hibernateTemplate_shared);
			helper.initData();
		}

	}

	public void resolution(String ftlPath, String ftlName, String savePath, String fileName) throws Exception {
		Writer out = null;
		String charset = "UTF-8";
		/**
		 * 创建Configuration对象 设置模板文件的基路径 设置读取模板的编码方式
		 */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(ftlPath));
		cfg.setDefaultEncoding(charset);
		/**
		 * 创建FreeMarker的数据模型
		 */
		Map<String, String> root = new HashMap<String, String>();
		root.put("name", "各种逗比");
		root.put("address", "然而并没有什么卵用");
		root.put("age", "23");
		/**
		 * 设置生成的模板的位置 合并数据模型与模板 生成最终的html页面
		 */
		try {
			Template template = cfg.getTemplate(ftlName, charset);
			// template.setEncoding("GBK");
			String path = "savePath";
			File file = new File(path + fileName);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
			template.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// new
		// NewWebDataAndHtmlController().resolution("F://ftls/","test.ftl","F://","example.html");

		// ApplicationContext ac = new
		// ClassPathXmlApplicationContext("conf/applicationContext.xml");
		// WebSiteManagerService webSiteManagerService = (WebSiteManagerService)
		// ac.getBean("WebSiteManagerService");

		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:conf/applicationContext.xml");
		context.refresh();
		WebSiteManagerService webSiteManagerService = context.getBean(WebSiteManagerService.class);

		FreeMarkerUtil fmu = new FreeMarkerUtil();
		FreeMarkerBean fb = new FreeMarkerBean();
		Map<String, Object> ftlMap = new HashMap<String, Object>();// 模板文件MAP
		String ftlPath = "F://ftls/";
		String ftlName = "assitParent.ftl";// 模板文件名
		String savePath = "F://platform/";// html存放文件夹路径
		String fileName = "assitParent.html";// html文件名

		fb.setFileName(fileName);
		fb.setFtlName(ftlName);
		fb.setFtlPath(ftlPath);
		fb.setSavePath(savePath);

		// 获取父页面集合
		WebPage p = new WebPage();
		p.setStatus(1);
		p.setPgType(1);
		List<WebPage> plist = webSiteManagerService.webpage_list(p);
		for (WebPage w : plist) {
			FreeMarkerBean wf = new FreeMarkerBean();
			fb.setFileName(w.getPgSn() + ".html");
			fb.setFtlName("assitParent.ftl");
			fb.setFtlPath(ftlPath);
			fb.setSavePath(savePath);
			Map<String, Object> wm = new HashMap<String, Object>();// 模板文件MAP
			wm.put("wm", w);
			List<WebPage> clist = new ArrayList<WebPage>();// 子页面集合
			if (w.getPgCtype() == 0) {// 如果只显示文本，没有子页面

			} else {// 连接到子页面，会有多个子页面
					// 查找属于自己的子页面，生成html
					// 获取子页面集合
				clist = webSiteManagerService.webpage_listForChild(w.getPgSn());
			}
			wm.put("wpList", clist);
			fb.setMap(wm);
			fmu.resolution(fb);
		}
		/*
		 * for(WebPage w : plist){ FreeMarkerBean wf=new FreeMarkerBean();
		 * fb.setFileName("help_"+w.getPgSn()+".html");
		 * fb.setFtlName("assitParent.ftl"); fb.setFtlPath(ftlPath);
		 * fb.setSavePath(savePath); Map<String,Object> wm =new HashMap<String,
		 * Object>();//模板文件MAP wm.put("wp", w);
		 * if(w.getPgCtype()==0){//如果只显示文本，没有子页面
		 * 
		 * //生成父页面html代码 }else{//连接到子页面，会有多个子页面 //查找属于自己的子页面，生成html //获取子页面集合
		 * List<String> cst=new ArrayList<String>();//子页面名称集合 List<WebPage>
		 * clist=webSiteManagerService.webpage_listForChild(w.getPgSn());
		 * for(WebPage c : clist){ FreeMarkerBean cf=new FreeMarkerBean();
		 * String fname="help_"+c.getPgSn()+".html"; cf.setFileName(fname);
		 * cf.setFtlName("assitParent.ftl"); cf.setFtlPath(ftlPath);
		 * cf.setSavePath(savePath); Map<String,Object> cm =new HashMap<String,
		 * Object>();//模板文件MAP cm.put("cp", c); cst.add(fname); } } }
		 */

		// ftlMap.put("plist", plist);

	}
}
