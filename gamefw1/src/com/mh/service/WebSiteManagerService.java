package com.mh.service;

import java.util.List;
import java.util.Map;

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

public interface WebSiteManagerService {
	
	public List<SysParameter> sysparameter_list();
	
	public List<WebSite> webSite_list();
	
	public List<LinkWebPage> linkwebpage_list();
	public List<LinkWebPromosType> linkwebpromostype_list();
	public List<WebCarousel> webcarousel_list();
	public List<WebGongGao> webgonggao_list();
	public List<WebLineCk> weblineck_list();
	
	public List<WebPage> webpage_list(WebPage p);
	public Map<String,WebPage> webpage_map(WebPage p);
	public List<WebPage> webpage_listForChild(String pgSn);
	
	
	public List<WebPanel> webpanel_list();
	public List<WebPromos> webpromos_list();
	
	public List<WebPromosType> webpromostype_list(WebPromosType type);
	public List<WebPromos> webpromos_listForType(int typeId);
	
	public List<WebResource> webresource_list();
	public List<WebSwitch> webswitch_list();
	public List<WebWeiHu> webweihu_list();
	
	public List<WebAgent> webAgent_list();
	
 
}
