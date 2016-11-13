package com.mh.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
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
@SuppressWarnings("all")
@Repository
public class WebSiteManagerDao extends BaseDao{

	public List<SysParameter> sysparameter_list() {
		List<SysParameter> list = new ArrayList<SysParameter>();
		String hql = "from SysParameter t where 1=1 ";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	// 网站基本信息
	public List<WebSite> webSite_list() {
		List<WebSite> list = new ArrayList<WebSite>();
		String hql = "from WebSite t where 1=1 order by t.id desc";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<LinkWebPromosType> linkwebpromostype_list() {
		List<LinkWebPromosType> list = new ArrayList<LinkWebPromosType>();
		String hql = "from LinkWebPromosType t";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebCarousel> webcarousel_list() {
		List<WebCarousel> list = new ArrayList<WebCarousel>();
		String hql = "from WebCarousel t where 1=1 and t.status=1";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebGongGao> webgonggao_list() {
		List<WebGongGao> list = new ArrayList<WebGongGao>();
		String hql = "from WebGongGao t where 1=1 and t.ggStatus=1 order by t.createTime desc ";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebLineCk> weblineck_list() {
		List<WebLineCk> list = new ArrayList<WebLineCk>();
		String hql = "from WebLineCk t where 1=1 order by t.id desc";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<LinkWebPage> linkwebpage_list() {
		List<LinkWebPage> list = new ArrayList<LinkWebPage>();
		String hql = "from LinkWebPage t";
		list = this.getHibernateTemplate().find(hql);

		return list;
	}

	public List<WebPage> webpage_list(WebPage p) {
		List<WebPage> list = new ArrayList<WebPage>();
		String hql = "from WebPage t where 1=1 " + getWebPagestr(p);
		
		hql+="order by t.pgSequence desc";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebPage> webpage_listForChild(String pgSn) {
		List<WebPage> list = new ArrayList<WebPage>();
		String hql = "from WebPage t where 1=1 and t.pgSn in(select p.childPgSn from LinkWebPage p where p.pgSn='" + pgSn + "') and t.status=1";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	// 页面查询条件
	public String getWebPagestr(WebPage p) {
		StringBuffer sb = new StringBuffer();
		if (null != p.getPgType()) {
			sb.append("and t.pgType=" + p.getPgType());
		}

		if (null != p.getStatus()) {
			sb.append("and t.status=" + p.getStatus());
		}
		return sb.toString();
	}

	public List<WebPanel> webpanel_list() {
		List<WebPanel> list = new ArrayList<WebPanel>();
		String hql = "from WebPanel t where 1=1 and t.status=1";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebPromos> webpromos_list() {
		List<WebPromos> list = new ArrayList<WebPromos>();
		String hql = "from WebPromos t where 1=1 and t.status=1";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	// 获取活动类型下所有活动
	public List<WebPromos> webpromos_listForType(int typeId) {
		List<WebPromos> list = new ArrayList<WebPromos>();
		List<Object> params = new ArrayList<Object>();
		String hql = "from WebPromos t where 1=1 and t.pmsName in(select p.pmsName from LinkWebPromosType p where p.pmsTypeId=" + typeId + ") ";
		hql += " and t.status=1 ";
		hql += " order by t.pmsIndex desc";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebPromosType> webpromostype_list(WebPromosType type) {
		List<WebPromosType> list = new ArrayList<WebPromosType>();
		StringBuffer hql = new StringBuffer("from WebPromosType t where 1=1 ");
		if (null != type && null != type.getStatus()) {
			hql.append(" and t.status=" + type.getStatus());
		}
		list = this.getHibernateTemplate().find(hql.toString());
		return list;
	}

	public List<WebResource> webresource_list() {
		List<WebResource> list = new ArrayList<WebResource>();
		String hql = "from WebResource t where 1=1 and t.status=1";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebSwitch> webswitch_list() {
		List<WebSwitch> list = new ArrayList<WebSwitch>();
		String hql = "from WebSwitch t where 1=1 and t.status=1";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebWeiHu> webweihu_list() {
		List<WebWeiHu> list = new ArrayList<WebWeiHu>();
		String hql = "from WebWeiHu t where 1=1 ";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<WebAgent> webAgent_list() {
		List<WebAgent> list = new ArrayList<WebAgent>();
		String hql = "from WebAgent t where 1=1 ";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}
 
}
