package com.mh.web.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import app.xb.cmbase.model.CpBigType;
import app.xb.cmbase.model.CpCateGory;
import app.xb.cmbase.model.CpConfig;
import app.xb.cmbase.model.CpGame;
import app.xb.cmbase.model.CpGameLinkType;
import app.xb.cmbase.model.CpType;

import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.ResourceConstant;
public class CpCacheHelper {

	/**
	 * 类描述: TODO<br/>
	 * 创建人: TODO alex<br/>
	 * 创建时间: 2015-5-26 下午7:03:06<br/>
	 */

	private static final Logger logger = LoggerFactory.getLogger(CpCacheHelper.class);

	private JdbcTemplate jdbcTemplate;

	private HibernateTemplate hibernateTemplate;

	/**
	 * 初始化 方法描述: TODO</br> void
	 */
	public synchronized void getBaseData() {
		clearMap();
		List<CpGame> gameList = this.getCpGameList();
		//游戏表
		
		Map<String,String> gameCodeMap = new HashMap<String,String>();
		for(int i=0;i<gameList.size();i++){
			CpGame cpGame = gameList.get(i);
			CpConfigCache.GAME_CACHE_MAP.put(cpGame.getId(), cpGame);
			CpConfigCache.GAME_OBJ_CACHE_MAP.put(cpGame.getGameTypeCode(), cpGame);
			StringBuffer codes = new StringBuffer("");
			if(gameCodeMap.containsKey(cpGame.getBigtypeCode())){
				codes.append(gameCodeMap.get(cpGame.getBigtypeCode())).append(",").append(cpGame.getGameTypeCode());
			}else{
				codes.append(cpGame.getGameTypeCode());
			}
			gameCodeMap.put(cpGame.getBigtypeCode(), codes.toString());
			CpConfigCache.CP_GAME_MAP.put(cpGame.getGameTypeCode(), cpGame.getGameTypeName());
			
			CpConfigCache.CP_MENU_1.put(cpGame.getHandicapCode(), cpGame.getHandicapName());//一级菜单
		}
		
		for (Entry<String, String> entry : CpConfigCache.CP_MENU_1.entrySet()) {//二级菜单
			List<CpGame> list = new ArrayList<CpGame>();
			for (CpGame cpGame : gameList) {
				if(StringUtils.equals(entry.getKey(), cpGame.getHandicapCode())){
					list.add(cpGame);
				}
			}
			CpConfigCache.CP_MENU_2.put(entry.getKey(), list);
		}
		
		CpConfigCache.GAME_CODES.putAll(gameCodeMap);
		logger.info("CPGAME初始化完成...");
		//大类
		List<CpBigType> bigtypeList = this.getCpBigtypeList();
		for(int i=0;i<bigtypeList.size();i++){
			CpBigType cpBigtype = bigtypeList.get(i);
			CpConfigCache.BIGTYPE_CACHE_MAP.put(cpBigtype.getId(), cpBigtype);
			CpConfigCache.BIGTYPE_OBJ_CACHE_MAP.put(cpBigtype.getBigtypeCode(), cpBigtype);
		}
		logger.info("CpBigType初始化完成...");
		
		//类型表
		Map<String,List<CpType>> typeListMap = new HashMap<String,List<CpType>>();
		List<CpType> typeList = this.getCpTypeList();
		for(int i=0;i<typeList.size();i++){
			CpType cptype = typeList.get(i);
			CpConfigCache.TYPE_CACHE_MAP.put(cptype.getId(), cptype);
			CpConfigCache.TYPE_OBJ_CACHE_MAP.put(cptype.getBigtypeCode()+"_"+cptype.getCpTypeCode(), cptype);
			
			
			List<CpType> valList =null;
			if(typeListMap.containsKey(cptype.getBigtypeCode())){
				valList = typeListMap.get(cptype.getBigtypeCode());
			}else{
				valList = new ArrayList<CpType>();
			}
			valList.add(cptype);
			typeListMap.put(cptype.getBigtypeCode(),valList);
		}
		CpConfigCache.TYPE_LIST_MAP.putAll(typeListMap);
		
		//cp_type
		List<CpType> cp_typeList = this.getCpTypeList();		
		Map<String,CpType> cp_typeMap = new HashMap<String,CpType>();
		for(CpType type : cp_typeList){
			if(ResourceConstant.SSC.equals(type.getBigtypeCode())){
				for (String sscType : ResourceConstant.SSC_TYPE) {
					 cp_typeMap.put(sscType+"_"+type.getCpTypeCode(), type);
				}
			}else if(ResourceConstant.FTC.equals(type.getBigtypeCode())){
				for (String ftcType : ResourceConstant.FTC_TYPE) {
					 cp_typeMap.put(ftcType+"_"+type.getCpTypeCode(), type);
				}
			}else if(ResourceConstant.PK10.equals(type.getBigtypeCode())){
				for (String pk10Type : ResourceConstant.PK10_TYPE) {
					 cp_typeMap.put(pk10Type+"_"+type.getCpTypeCode(), type);
				}
			}else if(ResourceConstant.KLSF.equals(type.getBigtypeCode())){
				for (String klsfType : ResourceConstant.KLSF_TYPE) {
					 cp_typeMap.put(klsfType+"_"+type.getCpTypeCode(), type);
				}
			}else if(ResourceConstant.KL8.equals(type.getBigtypeCode())){
				for (String xy28Type : ResourceConstant.BJKL8_TYPE) {
					 cp_typeMap.put(xy28Type+"_"+type.getCpTypeCode(), type);
				}
			}else{
				cp_typeMap.put(type.getBigtypeCode()+"_"+type.getCpTypeCode(), type);
			}
		}
		CpConfigCache.CP_TYPE_MAP.putAll(cp_typeMap);

		
		//赔率表
		List<CpConfig> configList = this.getConfigDataList();
		
		Map<String,List<CpConfig>> rateCacheMap = new HashMap<String,List<CpConfig>>();
		for(int i=0;i<configList.size();i++){
			CpConfig cpConfig = configList.get(i);
			if(!StringUtils.isEmpty(cpConfig.getUid())){
				String uidKey = cpConfig.getGameTypeCode()+"-"+cpConfig.getUid();
				CpConfigCache.UID_CACHE_KEY.put(uidKey, cpConfig);
			}
			String cacheKey = cpConfig.getGameTypeCode()+"-"+cpConfig.getCpTypeCode();
			List<CpConfig> valList = null;
			if(rateCacheMap.containsKey(cacheKey)){
				valList = rateCacheMap.get(cacheKey);
			}else{
				valList = new ArrayList<CpConfig>();
			}
			valList.add(cpConfig);
			rateCacheMap.put(cacheKey, valList);
		}
		CpConfigCache.RATE_CACHE_KEY.putAll(rateCacheMap);
		logger.info("CpConfig初始化完成...");
		//类别表
		 List<CpCateGory> cateList = this.getCpCategoryList();
		 for(int i=0;i<cateList.size();i++){
			 CpCateGory cate = cateList.get(i);
			 String cateKey = cate.getGameTypeCode()+"_"+cate.getCpTypeCode()+"_"+cate.getCpCateCode();
			 CpConfigCache.CATE_OBJ_CACHE_MAP.put(cateKey, cate);
			 
		 }
		 
		 List<CpGameLinkType> linkType = this.getCpGameLinkType();
		 for (CpGame game : gameList) {//game表
			List<CpType> type = new ArrayList<CpType>();
			for (CpGameLinkType link : linkType) {//link表
				if((int)link.getIsEnable() == 1){
					if((int)game.getId() == (int)link.getGameId()){
						for (CpType cpType : typeList) {//type表
							if((int)cpType.getId() == (int)link.getItemId()){
								for (CpBigType big : bigtypeList) {//bigtype
									if(StringUtils.equals(big.getBigtypeCode(), cpType.getBigtypeCode())){
										cpType.setP(big.getId() + "_" + cpType.getId());
									}
								}
								type.add(cpType);
							}
						}
					}
				}
			}
			CpConfigCache.CP_MENU_3.put(game.getGameTypeCode(), type);//三级菜单
		}
		logger.info("cpGameLinkType初始化完成！");
		logger.info("初始化彩票基础数据完成！");
	}
	
	
	public void clearMap(){
		CpConfigCache.GAME_CACHE_MAP.clear();
		CpConfigCache.CP_GAME_MAP.clear();
		CpConfigCache.GAME_OBJ_CACHE_MAP.clear();
		CpConfigCache.GAME_CODES.clear();
		CpConfigCache.BIGTYPE_CACHE_MAP.clear();
		CpConfigCache.BIGTYPE_OBJ_CACHE_MAP.clear();
		CpConfigCache.TYPE_CACHE_MAP.clear();
		CpConfigCache.TYPE_OBJ_CACHE_MAP.clear();
		CpConfigCache.TYPE_LIST_MAP.clear();
		CpConfigCache.CP_TYPE_MAP.clear();
		CpConfigCache.UID_CACHE_KEY.clear();
		CpConfigCache.RATE_CACHE_KEY.clear();
		CpConfigCache.CATE_OBJ_CACHE_MAP.clear();
	}
	

	@SuppressWarnings("unchecked")
	public List<CpConfig> getConfigDataList() {
//		String hql = "from CpConfig order by cpTypeCode,cpCateCode,pxh";
//		return this.getHibernateTemplate().find(hql);
		List<CpConfig> cpList = new ArrayList<CpConfig>();
		Integer pageNum = (Integer)MemCachedUtil.get("_pageNum");
		for (int i = 1; i <= pageNum; i++) {
			List<CpConfig> list = (List<CpConfig>) MemCachedUtil.get("config_" + i);
			cpList.addAll(list);
		}
		return cpList;
	}
	
	@SuppressWarnings("unchecked")
	public  List<CpBigType> getCpBigtypeList() {
		//String hql = "from CpBigtype  order by  pxh ";
		//return this.getHibernateTemplate().find(hql);
		
		if(null == MemCachedUtil.get("_bigType")){
			throw new NullPointerException("CpBigtype缓存初始化失败");
		}
		return (List<CpBigType>)MemCachedUtil.get("_bigType");

	}

	@SuppressWarnings("unchecked")
	public List<CpGame> getCpGameList() {
//		String hql = "from CpGame  order by  pxh ";
//		return this.getHibernateTemplate().find(hql);
		
		if(null == MemCachedUtil.get("_game")){
			throw new NullPointerException("CpGame缓存初始化失败");
		}
		return (List<CpGame>)MemCachedUtil.get("_game");

	}

	@SuppressWarnings("unchecked")
	public List<CpType> getCpTypeList() {
		//String hql = "from CpType order by bigtypeCode,pxh ";
		//return this.getHibernateTemplate().find(hql);
		if(null == MemCachedUtil.get("_type")){
			throw new NullPointerException("CpType缓存初始化失败");
		}
		return (List<CpType>)MemCachedUtil.get("_type");
	}

	@SuppressWarnings("unchecked")
	public List<CpCateGory> getCpCategoryList() {
		//String hql = "from CpCategory order by bigtypeCode,pxh ";
		//return this.getHibernateTemplate().find(hql);
		if(null == MemCachedUtil.get("_cate")){
			throw new NullPointerException("CpCategory缓存初始化失败");
		}
		return (List<CpCateGory>)MemCachedUtil.get("_cate");
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CpGameLinkType> getCpGameLinkType() {
		if(null == MemCachedUtil.get("_linkType")){
			throw new NullPointerException("CpGameLinkType缓存初始化失败");
		}
		return (List<CpGameLinkType>)MemCachedUtil.get("_linkType");
	}
	
	public CpCacheHelper() {
		// TODO Auto-generated constructor stub
	}

	public CpCacheHelper(JdbcTemplate jdbcTemplate,
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
