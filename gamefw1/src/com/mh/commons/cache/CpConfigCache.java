package com.mh.commons.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.xb.cmbase.model.CpBigType;
import app.xb.cmbase.model.CpCateGory;
import app.xb.cmbase.model.CpConfig;
import app.xb.cmbase.model.CpGame;
import app.xb.cmbase.model.CpType;

public class CpConfigCache {
	//基础信息 id为可以
	public static Map<Integer,CpGame> GAME_CACHE_MAP = new LinkedHashMap<Integer,CpGame>();
	
	public static Map<String,String> CP_GAME_MAP = new LinkedHashMap<String,String>();
	
	public static Map<String,String> GAME_CODES = new HashMap<String,String>();
	
	public static Map<Integer,CpBigType> BIGTYPE_CACHE_MAP = new HashMap<Integer,CpBigType>();
	public static Map<Integer,CpType> TYPE_CACHE_MAP = new HashMap<Integer,CpType>();
	public static Map<String,List<CpType>> TYPE_LIST_MAP = new HashMap<String,List<CpType>>();
	
	//对象
	public static Map<String,CpGame> GAME_OBJ_CACHE_MAP = new LinkedHashMap<String,CpGame>();
	public static Map<String,CpBigType> BIGTYPE_OBJ_CACHE_MAP = new HashMap<String,CpBigType>();
	public static Map<String,CpType> TYPE_OBJ_CACHE_MAP = new HashMap<String,CpType>();
	
	public static Map<String,CpCateGory> CATE_OBJ_CACHE_MAP = new HashMap<String,CpCateGory>();
	
	public static Map<String, CpType> CP_TYPE_MAP = new HashMap<String, CpType>();
	
	
	//赔率表缓存，UID为GAME_TYPE_CODE-UID 为Key
	public static Map<String,CpConfig> UID_CACHE_KEY=new HashMap<String, CpConfig>();
	
	public static Map<String,List<CpConfig>> RATE_CACHE_KEY = new HashMap<String,List<CpConfig>>();
  
	//一级菜单
	public static Map<String,String> CP_MENU_1 = new LinkedHashMap<String,String>();
	//二级菜单
	public static Map<String,List<CpGame>> CP_MENU_2 = new LinkedHashMap<String, List<CpGame>>();
	//三级菜单
	public static Map<String,List<CpType>> CP_MENU_3 = new LinkedHashMap<String, List<CpType>>();
}
