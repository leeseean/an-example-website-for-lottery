package com.mh.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mh.dao.WebSiteManagerDao;
import com.mh.entity.LinkWebPage;
import com.mh.entity.LinkWebPromosType;
import com.mh.entity.SysParameter;
import com.mh.entity.WebAgent;
import com.mh.entity.WebCarousel;
import com.mh.entity.WebGongGao;
import com.mh.entity.WebLineCk;
import com.mh.entity.WebPage;
import com.mh.entity.WebPanel;
import com.mh.entity.WebPromos;
import com.mh.entity.WebPromosType;
import com.mh.entity.WebResource;
import com.mh.entity.WebSite;
import com.mh.entity.WebSwitch;
import com.mh.entity.WebWeiHu;
import com.mh.service.WebSiteManagerService;

@Service
public class WebSiteManagerServiceImpl implements WebSiteManagerService {
	@Resource
	private WebSiteManagerDao webSiteManagerDao;
	
	public List<SysParameter> sysparameter_list(){
		
		return webSiteManagerDao.sysparameter_list();
	}
	
	public List<WebSite> webSite_list(){
		return webSiteManagerDao.webSite_list();
	}
	
	public List<LinkWebPage> linkwebpage_list() {
 
		return webSiteManagerDao.linkwebpage_list();
	}

	public List<LinkWebPromosType> linkwebpromostype_list() {
 
		return webSiteManagerDao.linkwebpromostype_list();
	}

	public List<WebCarousel> webcarousel_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webcarousel_list();
	}

	public List<WebGongGao> webgonggao_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webgonggao_list();
	}

	public List<WebLineCk> weblineck_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.weblineck_list();
	}

	public List<WebPage> webpage_list(WebPage p) {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webpage_list(p);
	}
	public Map<String,WebPage> webpage_map(WebPage entity){//
		Map<String,WebPage> map=new HashMap<String, WebPage>();
		List<WebPage> list=webSiteManagerDao.webpage_list(entity);
		for(WebPage p: list){
			map.put(p.getPgSn(), p);
		}
		return map;
	}
	public List<WebPage> webpage_listForChild(String pgSn){
		return webSiteManagerDao.webpage_listForChild(pgSn);
	}
	public List<WebPanel> webpanel_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webpanel_list();
	}

	public List<WebPromos> webpromos_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webpromos_list();
	}
	public List<WebPromos> webpromos_listForType(int typeId){
		return webSiteManagerDao.webpromos_listForType(typeId);
	}
	public List<WebPromosType> webpromostype_list(WebPromosType type) {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webpromostype_list(type);
	}

	public List<WebResource> webresource_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webresource_list();
	}

	public List<WebSwitch> webswitch_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webswitch_list();
	}

	public List<WebWeiHu> webweihu_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webweihu_list();
	}

	public List<WebAgent> webAgent_list() {
		// TODO Auto-generated method stub
		return webSiteManagerDao.webAgent_list();
	}
	
 
	
}
