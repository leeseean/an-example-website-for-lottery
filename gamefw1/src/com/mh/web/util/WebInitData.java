package com.mh.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.entity.SysParameter;
import com.mh.entity.WebLineCk;
import com.mh.entity.WebPage;
import com.mh.entity.WebPromos;
import com.mh.entity.WebPromosType;
import com.mh.entity.WebSite;
import com.mh.service.WebSiteManagerService;
import com.mh.web.utilBean.FreeMarkerBean;

public class WebInitData {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Description: TODO
	 * @param
	 * @return void
	 * @author Andy
	 * @throws Exception
	 * @date 2015-7-21
	 */
	public void initDataList(WebSiteManagerService webSiteManagerService,ServletConfig config) throws Exception {

		List<SysParameter> list = new ArrayList<SysParameter>();
		list = webSiteManagerService.sysparameter_list();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (SysParameter sp : list) {
			paramMap.put(sp.getParamName(), sp.getParamValue());
		}
		WebSiteManagerConstants.SYSPARAMMAP = paramMap;// sysparamter参数表
		List<WebSite> websiteList = webSiteManagerService.webSite_list();
		if (websiteList.size() > 0) {
			WebSiteManagerConstants.WEBSITE_INFO = websiteList.get(0);
		}
		WebSiteManagerConstants.WEBCAROUSEL_LIST = webSiteManagerService.webcarousel_list();
		WebSiteManagerConstants.WEBGONGGAO_LIST = webSiteManagerService.webgonggao_list();

		List<WebLineCk> ckList = webSiteManagerService.weblineck_list();
		if (null != ckList && ckList.size() > 0) {
			WebSiteManagerConstants.WEBLINECK_INFO = ckList.get(0);
		}

		WebSiteManagerConstants.WEBPANEL_LIST = webSiteManagerService.webpanel_list();

		String ftlPath = config.getServletContext().getRealPath(WebSiteManagerConstants.FTL_PATH);
		String promosHtml = config.getServletContext().getRealPath(WebSiteManagerConstants.PROMOS_HTML_PATH)+ "/";
		String helpHtml = config.getServletContext().getRealPath(WebSiteManagerConstants.HELP_HTML_PATH)+ "/";

		initWebPageData(ftlPath, helpHtml, webSiteManagerService);// 生成帮助页面
		initWebPromosData(ftlPath, promosHtml, webSiteManagerService);// 生成优惠活动页面

		WebSiteManagerConstants.WEBRESOURCE_LIST = webSiteManagerService.webresource_list();
		WebSiteManagerConstants.WEBSWITCH_LIST = webSiteManagerService.webswitch_list();
		WebSiteManagerConstants.WEBWEIHU_LIST = webSiteManagerService.webweihu_list();
		WebSiteManagerConstants.WEBAGENT_LIST = webSiteManagerService.webAgent_list();

		WebPage page = new WebPage();
		page.setPgType(2);
		page.setStatus(1);
		WebSiteManagerConstants.WEBPAGE_ALONE_LIST = webSiteManagerService.webpage_list(page);
	}

	/**
	 * 初始化活动数据
	 * 
	 * @Description: TODO
	 * @param
	 * @return void
	 * @author Andy
	 * @throws Exception
	 * @date 2015-7-20
	 */
	public void initWebPromosData(String ftlPath, String savePath,
			WebSiteManagerService webSiteManagerService) throws Exception {

		Map<String, Object> ftlMap = new HashMap<String, Object>();// 模板文件MAP

		// 获取活动类型
		WebPromosType type = new WebPromosType();
		type.setStatus(1);
		List<WebPromosType> ptList = new ArrayList<WebPromosType>();

		ptList = webSiteManagerService.webpromostype_list(type);// 所有可用活动类型

		WebSiteManagerConstants.WEBPROMOSTYPE_LIST = ptList;
		List<WebPromos> pdAllList = new ArrayList<WebPromos>();

		Map<String, String> tempMap = new HashMap<String, String>();
		Map<String, Object> ftlMap1 = new HashMap<String, Object>();// 活动文件MAP
		for (WebPromosType w : ptList) {// 属于该活动类型下所有活动
			List<WebPromos> plist = new ArrayList<WebPromos>();
			plist = webSiteManagerService.webpromos_listForType(w.getId());

			for (WebPromos p : plist) {// 删除重复活动
				if (tempMap.containsKey(p.getId().toString())) {

				} else {
					tempMap.put(p.getId().toString(), "1");
					pdAllList.add(p);
				}
			}
			Map<String, Object> cmap = new HashMap<String, Object>();// 活动类型父页面
			cmap.put("plist", plist);
			cmap.put("typeName", w.getPmsTypeName());
			cmap.put("tid", w.getId());
			typeChild(w.getId(), cmap, ftlPath, savePath);
		}

		List<WebPromos> topList = new ArrayList<WebPromos>();
		for (WebPromos p : pdAllList) {
			if (p.getIsTop() == 1) {
				topList.add(p);
			}
		}
		WebSiteManagerConstants.LbWEBPROMOS_LIST = topList;
		WebSiteManagerConstants.WEBPROMOS_LIST = pdAllList;

		// 主页面

		ftlMap.put("ptList", ptList);

		FreeMarkerUtil fmu = new FreeMarkerUtil();
		// 全部优惠
		Map<String, Object> allMap = new HashMap<String, Object>();// 轮播文件MAP
		allMap.put("plist", pdAllList);
		FreeMarkerBean allfb = new FreeMarkerBean();

		allfb.setMap(allMap);
		allfb.setFileName("promosAll.html");
		allfb.setFtlName("promosAll.ftl");
		allfb.setFtlPath(ftlPath);
		allfb.setSavePath(savePath);
		fmu.resolution(allfb);
		// 简单主页面
		FreeMarkerBean fb1 = new FreeMarkerBean();

		ftlMap1.put("pdAllList", pdAllList);
		fb1.setMap(ftlMap1);
		fb1.setFileName("promosEasy.html");
		fb1.setFtlName("promosEasy.ftl");
		fb1.setFtlPath(ftlPath);
		fb1.setSavePath(savePath);
		fmu.resolution(fb1);

	}

	// 活动子页
	public static void typeChild(int id, Map<String, Object> map,
			String ftlPath, String savePath) {
		String ftlName = "promosComplex.ftl";// 模板文件名
		String fileName = id + ".html";// html文件名
		FreeMarkerBean fb = new FreeMarkerBean();
		FreeMarkerUtil fmu = new FreeMarkerUtil();
		try {
			fb.setFileName(fileName);
			fb.setFtlName(ftlName);
			fb.setFtlPath(ftlPath);
			fb.setSavePath(savePath);
			fb.setMap(map);
			fmu.resolution(fb);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 初始化页面管理数据
	 * 
	 * @Description: TODO
	 * @param
	 * @return void
	 * @author Andy
	 * @throws Exception
	 * @date 2015-7-20
	 */
	public void initWebPageData(String ftlPath, String savePath,
			WebSiteManagerService webSiteManagerService) throws Exception {
		String ftlName = "help.ftl";// 模板文件名
		String fileName = "help.html";// html文件名

		FreeMarkerUtil fmu = new FreeMarkerUtil();
		// 获取父页面集合
		WebPage p = new WebPage();
		p.setStatus(1);
		p.setPgType(1);
		List<WebPage> plist = webSiteManagerService.webpage_list(p);
		WebSiteManagerConstants.WEBPAGE_LIST = plist;
		for (WebPage w : plist) {
			FreeMarkerBean fb = new FreeMarkerBean();

			fb.setFileName(fileName);
			fb.setFtlName(ftlName);
			fb.setFtlPath(ftlPath);
			fb.setSavePath(savePath);
			fb.setFileName(w.getPgSn() + ".html");
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

		WebPage page = new WebPage();
		page.setPgType(2);
		page.setStatus(1);
		// 独立页面
		WebSiteManagerConstants.WEBPAGE_ALONE_MAP = webSiteManagerService.webpage_map(page);

		WebPage mpage = new WebPage();
		mpage.setPgType(3);
		mpage.setStatus(1);
		// 独立页面
		WebSiteManagerConstants.WEBPAGE_MOBILE_MAP = webSiteManagerService.webpage_map(mpage);

		if (null != WebSiteManagerConstants.WEBPAGE_ALONE_MAP && WebSiteManagerConstants.WEBPAGE_ALONE_MAP.size() < 1) {
			logger.info("要郁闷了，刷新缓存无数据：" + WebSiteManagerConstants.WEBPAGE_ALONE_MAP);
		}
	}

	// 活动主页
	public void promosMain() throws Exception {

		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:conf/applicationContext.xml");
		context.refresh();

		FreeMarkerBean fb = new FreeMarkerBean();
		String ftlPath = "F://ftls/";
		String ftlName = "discount.ftl";// 模板文件名
		String savePath = "F://platform/";// html存放文件夹路径
		String fileName = "discount.html";// html文件名

		fb.setFileName(fileName);
		fb.setFtlName(ftlName);
		fb.setFtlPath(ftlPath);
		fb.setSavePath(savePath);

		// 获取活动类型
		WebPromosType type = new WebPromosType();
		type.setStatus(1);
	}

}
