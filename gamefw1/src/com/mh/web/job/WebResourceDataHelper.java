/**   
* 文件名称: WebSitDataHelper.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-7-25 下午3:22:59<br/>
*/  
package com.mh.web.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.ResourceConstant;
import com.mh.entity.WebGdElectronic;
import com.mh.entity.WebMgElectronic;
import com.mh.entity.WebNewNtElectronic;
import com.mh.entity.WebNtElectronic;
import com.mh.entity.WebOsElectronic;
import com.mh.entity.WebPtElectronic;
import com.mh.entity.WebTtgElectronic;
import com.mh.web.util.FastUtil;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-7-25 下午3:22:59<br/>
 */
@SuppressWarnings("all")
public class WebResourceDataHelper {
	private static final Logger logger = LoggerFactory.getLogger(WebResourceDataHelper.class);
	
	private JdbcTemplate jdbcTemplate;
	
	private HibernateTemplate hibernateTemplate;
	
	public  synchronized void initData(){
		logger.info("***********初始化web静态数据开始***********");
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
	public synchronized void getBaseData(){
		List<WebMgElectronic> mgElects = this.getMgElectronicDataList();
		Map<String,List<WebMgElectronic>> typeMap = new HashMap<String,List<WebMgElectronic>>();
		for(int i =0; i<mgElects.size(); i++){
			WebMgElectronic mg = mgElects.get(i);
			String gameType = mg.getEleGameType1();
			List<WebMgElectronic> list = null;
			if(!typeMap.containsKey(gameType)){
				list = new ArrayList<WebMgElectronic>();
			}else{
				list = typeMap.get(gameType);
			}
			list.add(mg);
			typeMap.put(gameType, list);
		}
		typeMap.put("all", mgElects);
		
		Map<String,List<WebTtgElectronic>> ttgMap = new HashMap<String,List<WebTtgElectronic>>();
		List<WebTtgElectronic> ttgElects = this.getTtgElectronicDataList();
		for(int i =0; i<ttgElects.size(); i++){
			WebTtgElectronic ttg = ttgElects.get(i);
			String gameType = ttg.getEleGameType1();
			List<WebTtgElectronic> list = null;
			if(!ttgMap.containsKey(gameType)){
				list = new ArrayList<WebTtgElectronic>();
			}else{
				list = ttgMap.get(gameType);
			}
			list.add(ttg);
			ttgMap.put(gameType, list);
		}
		ttgMap.put("all", ttgElects);
		
		
		Map<String, String> ntCodeMap = new HashMap<String, String>();
		List<WebNtElectronic> ntDatas = this.getNtElectronicDataList();
		for(WebNtElectronic nt : ntDatas){
			ntCodeMap.put(nt.getEleGameIndex(), nt.getEleGameCnname());
		}
		
		Map<String, String> ptCodeMap = new HashMap<String, String>();
		
		
		Map<String,List<WebPtElectronic>> ptTypeMap = new HashMap<String,List<WebPtElectronic>>();
		List<WebPtElectronic> ptDatas = this.getPtElectronicDataList();
		for(WebPtElectronic pt : ptDatas){
			ptCodeMap.put(pt.getEleGameIndex(), pt.getEleGameCnname());
			String gameType = pt.getEleGameType1();
			List<WebPtElectronic> list = null;
			if(!ptTypeMap.containsKey(gameType)){
				list = new ArrayList<WebPtElectronic>();
			}else{
				list = ptTypeMap.get(gameType);
			}
			list.add(pt);
			ptTypeMap.put(gameType, list);
		}
		ptTypeMap.put("all", ptDatas);
		ptTypeMap.put("support", this.getPtElectronicDataListBySupport(1));
		
		/**OS 电子*/
		List<WebOsElectronic> osElects = this.getOsElectronicDataList();
		
		Map<String,List<WebOsElectronic>> osTypeMap = new HashMap<String,List<WebOsElectronic>>();
		
		for(int i =0; i<osElects.size(); i++){
			WebOsElectronic os = osElects.get(i);
			String gameType = os.getEleGameType1();
			List<WebOsElectronic> list = null;
			if(!osTypeMap.containsKey(gameType)){
				list = new ArrayList<WebOsElectronic>();
			}else{
				list = osTypeMap.get(gameType);
			}
			list.add(os);
			osTypeMap.put(gameType, list);
		}
		osTypeMap.put("all", osElects);
		
		Map<String,List<WebNewNtElectronic>> newNtMap = new HashMap<String,List<WebNewNtElectronic>>();
		List<WebNewNtElectronic> newNtElects = this.getNewNtElectronicDataList();
		for(int i =0; i<newNtElects.size(); i++){
			WebNewNtElectronic newNt = newNtElects.get(i);
			String gameType = newNt.getEleGameType1();
			List<WebNewNtElectronic> list = null;
			if(!newNtMap.containsKey(gameType)){
				list = new ArrayList<WebNewNtElectronic>();
			}else{
				list = newNtMap.get(gameType);
			}
			list.add(newNt);
			newNtMap.put(gameType, list);
		}
		newNtMap.put("all", newNtElects);
		
		Map<String,List<WebGdElectronic>> gdMap = new HashMap<String,List<WebGdElectronic>>();
		List<WebGdElectronic> gdElects = this.getGdElectronicDataList();
		for(int i =0; i<gdElects.size(); i++){
			WebGdElectronic gd = gdElects.get(i);
			String gameType = gd.getEleGameType1();
			List<WebGdElectronic> list = null;
			if(!gdMap.containsKey(gameType)){
				list = new ArrayList<WebGdElectronic>();
			}else{
				list = gdMap.get(gameType);
			}
			list.add(gd);
			gdMap.put(gameType, list);
		}
		gdMap.put("all", gdElects);
		
		
		ResourceConstant.MOBILE_MG_ELECTRONIC_LIST = this.getMobileMgElectronicDataList();
		ResourceConstant.MOBILE_PT_ELECTRONIC_LIST = this.getMobilePtElectronicDataList();
		ResourceConstant.MOBILE_OS_ELECTRONIC_LIST = this.getMobileOsElectronicDataList();
		ResourceConstant.MOBILE_TTG_ELECTRONIC_LIST = this.getMobileTtgElectronicDataList();
		
		ResourceConstant.GD_ELECTRONIC_LIST = gdMap;
		ResourceConstant.NEWNT_ELECTRONIC_LIST = newNtMap;
		
		ResourceConstant.MG_ELECTRONIC_LIST = typeMap;
		ResourceConstant.PT_ELECTRONIC_LIST = ptTypeMap;
		ResourceConstant.OS_ELECTRONIC_LIST = osTypeMap;
		ResourceConstant.TTG_ELECTRONIC_LIST = ttgMap;
		
		ResourceConstant.NT_ELECTRONIC_LIST = ntDatas;
		ResourceConstant.ntElectronic = ntCodeMap;
		ResourceConstant.ptElectronic = ptCodeMap;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<WebMgElectronic> getMobileMgElectronicDataList() {
		//从缓存读取
		Class<WebMgElectronic> webMgElectronic = WebMgElectronic.class;
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("mg") == null){
			return null;
		}
		String mobileMg = map.get("mg");
		List<WebMgElectronic> list = (List<WebMgElectronic>) FastUtil.parseArray(mobileMg, webMgElectronic);
		List<WebMgElectronic> res = new ArrayList<WebMgElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if(("1".equals(list.get(i).getStatus())) && ("HTML5".equals(list.get(i).getEleGameName())) && (list.get(i).getEleGamePic() != null)){
				res.add((list.get(i)));
			}
		}
		return res;
		//String hql = "from WebMgElectronic WHERE status=1 and eleGameName = 'HTML5' and eleGamePic is not null ORDER BY eleSortIndex DESC, id";
		//return this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * pt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WebPtElectronic> getMobilePtElectronicDataList() {
		//从缓存读取
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("mobilePt") == null){
			return null;
		}
		String pt = map.get("mobilePt");
		Class<WebPtElectronic> webPtElectronic = WebPtElectronic.class;
		List<WebPtElectronic> list = (List<WebPtElectronic>) FastUtil.parseArray(pt, webPtElectronic);
		List<WebPtElectronic> res = new ArrayList<WebPtElectronic>();
		for(int i =0 ; i < list.size() ; i++){
			if(("1".equals(list.get(i).getStatus())) && ("1".equals(list.get(i).getPlatform().toString()))){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebPtElectronic WHERE status=1 and platform=1 ORDER BY eleSortIndex DESC, id";
		//return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<WebOsElectronic> getMobileOsElectronicDataList() {
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("os") == null){
			return null;
		}
		String mobileOs = map.get("os");
		Class<WebOsElectronic> webOsElectronic = WebOsElectronic.class;
		List<WebOsElectronic> list = (List<WebOsElectronic>) FastUtil.parseArray(mobileOs, webOsElectronic);
		List<WebOsElectronic> res = new ArrayList<WebOsElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if(("1".equals(list.get(i).getStatus())) && ("Mobile".equals(list.get(i).getEleGameName()))){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebOsElectronic WHERE status=1 and eleGameName = 'Mobile' ORDER BY eleSortIndex DESC, id";
		//return this.getHibernateTemplate().find(hql);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<WebMgElectronic> getMgElectronicDataList() {
		Map<String , String> map = (Map<String, String>)MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("mg") == null){
			return null;
		}
		String mgString = map.get("mg");
		Class<WebMgElectronic> webMgElectronic = WebMgElectronic.class;
		List<WebMgElectronic> list = (List<WebMgElectronic>)FastUtil.parseArray(mgString, webMgElectronic);
		List<WebMgElectronic> res = new ArrayList<WebMgElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if("1".equals(list.get(i).getStatus()) && !("HTML5".equals(list.get(i).getEleGameName()))){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebMgElectronic WHERE status=1 and eleGameName !='HTML5' ORDER BY eleSortIndex DESC, ID";
		///return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<WebTtgElectronic> getTtgElectronicDataList() {
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("ttg") == null){
			return null;
		}
		String ttg = map.get("ttg");
		Class<WebTtgElectronic> webTtgElectronic = WebTtgElectronic.class;
		List<WebTtgElectronic> list = (List<WebTtgElectronic>) FastUtil.parseArray(ttg, webTtgElectronic);
		List<WebTtgElectronic> res = new ArrayList<WebTtgElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if("1".equals(list.get(i).getStatus()) && !("Mobile".equals(list.get(i).getEleGameType1()))){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebTtgElectronic WHERE status=1 ORDER BY ID DESC";
		//return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<WebOsElectronic> getOsElectronicDataList() {
		List<WebOsElectronic> res = new ArrayList<WebOsElectronic>();
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("os") == null){
			return null;
		}
		String os = map.get("os");
		Class<WebOsElectronic> webOsElectronic = WebOsElectronic.class;
		List<WebOsElectronic> list = (List<WebOsElectronic>) FastUtil.parseArray(os, webOsElectronic);
		for(int i = 0 ; i < list.size() ; i++){
			if("1".equals(list.get(i).getStatus()) && "Casino".equals(list.get(i).getEleGameName())){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebOsElectronic WHERE status=1 and eleGameName='Casino' ORDER BY eleSortIndex DESC, ID";
		//return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<WebNtElectronic> getNtElectronicDataList() {
		String hql = "from WebNtElectronic WHERE status=1 ORDER BY eleSortIndex DESC, eleGameIndex";
		return this.getHibernateTemplate().find(hql);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<WebGdElectronic> getGdElectronicDataList() {
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("gd") == null){
			return null;
		}
		String gd = map.get("gd");
		Class<WebGdElectronic> webGdElectronic = WebGdElectronic.class;
		List<WebGdElectronic> list = (List<WebGdElectronic>) FastUtil.parseArray(gd, webGdElectronic);
		List<WebGdElectronic> res = new ArrayList<WebGdElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if("1".equals(list.get(i).getStatus())){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebGdElectronic WHERE status=1 ORDER BY ID";
		//return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<WebNewNtElectronic> getNewNtElectronicDataList() {
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("newNt") == null){
			return null;
		}
		String newNt = map.get("newNt");
		Class<WebNewNtElectronic> webNewNtElectronic = WebNewNtElectronic.class;
		List<WebNewNtElectronic> list = (List<WebNewNtElectronic>) FastUtil.parseArray(newNt, webNewNtElectronic);
		List<WebNewNtElectronic> res = new ArrayList<WebNewNtElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if("1".equals(list.get(i).getStatus().toString())){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebNewNtElectronic WHERE status=1 ORDER BY eleIndex";
		//return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<WebPtElectronic> getPtElectronicDataList() {
		Map<String ,String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("pt") == null){
			return null;
		}
		String pt = map.get("pt");
		Class<WebPtElectronic> webPtElectronic = WebPtElectronic.class;
		List<WebPtElectronic> list = (List<WebPtElectronic>) FastUtil.parseArray(pt, webPtElectronic);
		List<WebPtElectronic> res = new ArrayList<WebPtElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if("1".equals(list.get(i).getStatus()) && "0".equals(list.get(i).getPlatform().toString())){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebPtElectronic WHERE status=1 and platform=0 ORDER BY eleSortIndex DESC, eleGameIndex";
		//return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<WebPtElectronic> getPtElectronicDataListBySupport(int isSupport) {
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("pt") == null){
			return null;
		}
		String ptBySuport = map.get("pt");
		Class<WebPtElectronic> webPtElectronic = WebPtElectronic.class;
		List<WebPtElectronic> list = (List<WebPtElectronic>) FastUtil.parseArray(ptBySuport, webPtElectronic);
		List<WebPtElectronic> res = new ArrayList<WebPtElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if(list.get(i).getIsSupport() != null){
				if("1".equals(list.get(i).getStatus()) && "0".equals(list.get(i).getPlatform().toString()) && list.get(i).getIsSupport() == isSupport){
					res.add(list.get(i));
				}
			}
		}
		return res;
		//String hql = "from WebPtElectronic WHERE status=1 and platform=0 and isSupport=? ORDER BY eleSortIndex DESC, eleGameIndex";
		//return this.getHibernateTemplate().find(hql,new Object[]{isSupport});
	}
	
	public List<WebTtgElectronic> getMobileTtgElectronicDataList() {
		Map<String , String> map = (Map<String, String>) MemCachedUtil.get("ybslot_result");
		if(map == null || map.get("mobileTtg") == null){
			return null;
		}
		String MobileTtg = map.get("mobileTtg");
		Class<WebTtgElectronic> webTtgElectronic = WebTtgElectronic.class;
		List<WebTtgElectronic> list = (List<WebTtgElectronic>) FastUtil.parseArray(MobileTtg, webTtgElectronic);
		List<WebTtgElectronic> res = new ArrayList<WebTtgElectronic>();
		for(int i = 0 ; i < list.size() ; i++){
			if("1".equals(list.get(i).getStatus()) && "Mobile".equals(list.get(i).getEleGameType1()) ){
				res.add(list.get(i));
			}
		}
		return res;
		//String hql = "from WebTtgElectronic WHERE status=1 and eleGameType1 = 'Mobile' ORDER BY id";
		//return this.getHibernateTemplate().find(hql);
	}
	
	public WebResourceDataHelper(JdbcTemplate jdbcTemplate,
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
}
