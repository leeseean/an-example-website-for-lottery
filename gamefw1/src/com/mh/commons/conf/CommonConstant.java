package com.mh.commons.conf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *整个应用通用的常量 
 *<br><b>类描述:</b>
 *<pre>|</pre>
 */
public final class CommonConstant{
	//配置文件常量
	public static Map<String, String> resCommMap = new HashMap<String, String>();
	
	//不拦截地址
	public static List<String> visitList = new ArrayList<String>();
	
	//拦截地址
	public static List<String> interceptorList = new ArrayList<String>();
	
	//提前开盘时间
	public static Map<String, String> openHourMap = new HashMap<String, String>();
	
	public static Map<String,Integer> inteMap = new HashMap<String,Integer>();

	public static Map<String,Integer> periodMap = new HashMap<String,Integer>();
	
	public static List<String> dxdsList = new ArrayList<String>();
	
	public static Map<String,List<String>> reverseMap = new LinkedHashMap<String,List<String>>();
	//逆向
	//大
	public static String[] bigReverseArr = new String[]{"小","小单","小双"};
	//小
	public static String[] smallReverseArr = new String[]{"大","大单","大双"};
	//单
	public static String[] oddReverseArr = new String[]{"双","大双","小双"};
	//双
	public static String[] evenReverseArr = new String[]{"单","大单","小单"};
	
	//大单
	public static String[] bigOddReverseArr = new String[]{"小","双","小双"};
	//大双
	public static String[] bigEvenReverseArr = new String[]{"小","单","小单"};
	//小单
	public static String[] smallOddReverseArr = new String[]{"大","双","大双"};
	//小双
	public static String[] smallEvenReverseArr = new String[]{"大","单","大单"};
	
	static{
		reverseMap.put("大", Arrays.asList(bigReverseArr));
		reverseMap.put("小", Arrays.asList(smallReverseArr));
		reverseMap.put("单", Arrays.asList(oddReverseArr));
		reverseMap.put("双", Arrays.asList(evenReverseArr));
		reverseMap.put("大单", Arrays.asList(bigOddReverseArr));
		reverseMap.put("大双", Arrays.asList(bigEvenReverseArr));
		reverseMap.put("小单", Arrays.asList(smallOddReverseArr));
		reverseMap.put("小双", Arrays.asList(smallEvenReverseArr));
		
		
		dxdsList.add("大");
		dxdsList.add("小");
		dxdsList.add("单");
		dxdsList.add("双");
		dxdsList.add("大单");
		dxdsList.add("小单");
		dxdsList.add("大双");
		dxdsList.add("小双");
		
		
		inteMap.put("CQSSC",10);
		inteMap.put("JXSSC",10);
		inteMap.put("TJSSC",10);
		inteMap.put("XJSSC",10);
		inteMap.put("TJKLSF",10);
		inteMap.put("GDKLSF",10);
		inteMap.put("BJPK10",5);
		
		periodMap.put("CQSSC",120);
		periodMap.put("JXSSC",84);
		periodMap.put("TJSSC",84);
		periodMap.put("XJSSC",96);
		periodMap.put("TJKLSF",84);
		periodMap.put("GDKLSF",84);
 
		
		//豁免地址
		visitList.add("/index");
		visitList.add("/goMobile");
		visitList.add("/login");
		visitList.add("/promologin");
		visitList.add("/loginRule");
		visitList.add("/yzc_electronic");
		
		visitList.add("/excel/read");
		
		visitList.add("/register");
		visitList.add("/doRegister");
		visitList.add("/doPromoRegister");
		visitList.add("/goPageCenter");
		visitList.add("/main");
		visitList.add("/valid/");
		visitList.add("/electronic");
		visitList.add("/uploadRoot");//文件访问
		visitList.add("/message/goGongGaoList");
		visitList.add("/getOnlineUser");
		visitList.add("/delOnlineUser");
		visitList.add("/sport/");
		visitList.add("/commons/web/ybb/upload/");
		visitList.add("/lineCheck");
		visitList.add("/test");
		visitList.add("/pg");
		visitList.add("/cp/main");
		visitList.add("/cp/LottoGetBalance");
		visitList.add("/cp/server");
		visitList.add("/cp/lottoGetGameInfo");
		visitList.add("/cp/allTemplate");
		visitList.add("/cp/szzhbetTemplate");
		visitList.add("/cp/betTemplate");
		visitList.add("/cp/LottoGetAnnouncement");
		visitList.add("/cpResult/goList");
		visitList.add("/cpResult/getNewsResult");
		//支付页面通知地址
		visitList.add("/payReturn/payReturnHuanxunPage");
		visitList.add("/payReturn/payReturnDinpayPage");
		visitList.add("/payReturn/payReturnBaopayPage");
		visitList.add("/payReturn/payReturnYeepayPage");
		//手机端访问
		visitList.add("/mobile/main/index");
		visitList.add("/mobile/main");
		visitList.add("/mobile/register/checkParam");
		visitList.add("/mobile/register/goRegister");
		visitList.add("/mobile/register/doRegister");
		
		visitList.add("/m/main");
		visitList.add("/m/help");
		visitList.add("/m/register/checkParam");
		visitList.add("/m/register/goRegister");
		visitList.add("/m/register/doRegister");
		visitList.add("/m/sport/list");
		visitList.add("/m/sport/goEventDetail");
		visitList.add("/m/sport/goOrder");
		visitList.add("/m/sport/goP3Order");
		visitList.add("/m/mobileCpMain/menu");
		visitList.add("/m/mobileCpAccount/findKjjgList");
		visitList.add("/m/mobileCpOrder/orderIndex");
		visitList.add("/m/mobileCpOrder/szdwOrderPage");
		visitList.add("/m/checkflatsatus");
		visitList.add("/m/game/list");
		visitList.add("/m/lottery/info");
		visitList.add("/m/wap");
		visitList.add("/m/wap/member");
		visitList.add("/m/results/history/");
		visitList.add("/m/results/current");
		visitList.add("/m/main/index");
		
		/**代理**/
		visitList.add("/agent");
		
		//活动
		visitList.add("/activity");
		
		/**皇冠体育登录注册**/
		visitList.add("/doHgRegister");
		visitList.add("/hglogin/index");
		visitList.add("/hglogin/doLogin");
		
		//拦截地址
		//interceptorList.add("/goSportCenter");
		interceptorList.add("/forwardGame");
		interceptorList.add("/lottery");
		
		/**MG客户端**/
		visitList.add("/mg/client");
		visitList.add("/clientInterface");
		 
		
		//低频提前一小时封盘 单位秒
		openHourMap.put("HK6",CommonConstant.CP_HK6_CLOSE_TIME);
		openHourMap.put("FC3D",CommonConstant.CP_FC3D_CLOSE_TIME);
		openHourMap.put("PL3",CommonConstant.CP_PL3_CLOSE_TIME);
		
		openHourMap.put("CQSSC",CommonConstant.CP_CQSSC_CLOSE_TIME);
		openHourMap.put("JXSSC",CommonConstant.CP_JXSSC_CLOSE_TIME);
		openHourMap.put("TJSSC",CommonConstant.CP_TJSSC_CLOSE_TIME);
		openHourMap.put("XJSSC",CommonConstant.CP_XJSSC_CLOSE_TIME);
		
		openHourMap.put("TJKLSF",CommonConstant.CP_TJKLSF_CLOSE_TIME);
		openHourMap.put("GDKLSF",CommonConstant.CP_GDKLSF_CLOSE_TIME);
		openHourMap.put("BJPK10",CommonConstant.CP_BJPK10_CLOSE_TIME);
	}
	
	
	
	
	//********************初始化数据****************/

	
	//参数初始化文件路径
	public static final String RESOURCE_INIT_PATH = "resInitPath"; 

	//------------------------------------ SESSION 信息 KEY----------------------------------	
   /**
    * 用户对象放到Session中的键名称
    */
   public static final String USER_CONTEXT_KEY = "USER_CONTEXT_KEY";
   
   //测试用户
   public static final String TEST_USER_CONTEXT_KEY = "TEST_USER_CONTEXT_KEY";
   
   //代理用户
   public static final String USER_AGENT_CONTEXT_KEY = "USER_AGENT_CONTEXT_KEY";
   

   //确认用户对象
   public static final String CONFIRM_USER_CONTEXT_KEY = "CONFIRM_USER_CONTEXT_KEY";
   
   
 
   public static final String MENCHED_TEST_KEY = "MENCHED_TEST_KEY";
   
   public static final String TEST_PREFIX_KEY = "ts";
   
   /** 登入验证码在Session 中的键名称*/
    public static final String VERITY_CODE_KEY = "VERITY_CODE";
    
    public static final String TEST_VERITY_CODE_KEY = "TEST_VERITY_CODE";
    
    public static final String REGISTER_VERITY_CODE_KEY = "REGISTER_VERITY_CODE";
   
	public static final String DEFAULT_SUCCESS_MSG = "成功";
	public static final String DEFAULT_FAIL_MSG = "失败";
	
	
	/** 用户名合法字符串，最大限度限制 */
	public static final String LEGAL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	//------------------------ 导出EXCEL方式 --------------------------
	/** 导出当前页EXCEL */
	public final static String EXCEL_EXPORT_TYPE_PAGE = "0"; 
	
	/** 导出符合检索条件EXCEL */
	public final static String EXCEL_EXPORT_TYPE_ALL = "1";
	
	/**excel标题头数据保存在上下文标识*/
	public final static String EXCEL_HEAD_LIST = "EXCEL_HEAD_LIST"; 
	
	/**excel内容体数据保存在上下文标识*/
	public final static String EXCEL_DATA_LIST = "EXCEL_DATA_LIST";
	
	//------------------------ 分页对像在上下文标识---------------------------------------------------------	
	public static final String PAGE_KEY = "page";
	
	//--------------------------------------------默认分页大小-----------------------------------------	
	/**默认分页大小*/
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	/**默认第几页*/
	public static final int DEFAULT_PAGE_NO = 1;
	
	public static final String PAGE_NO = "pageNo";
	
	public static final String PAGE_SIZE = "pageSize";
	
	public static final String ORDER_BY = "ORDER_BY";
	
	public static final String ORDER = "ORDER";
	
	
	/**
	 * 支付宝
	 */
	public static final int ALI_PAY = 2;
	/**
	 * 微信
	 */
	public static final int WX_PAY = 1;
	
	
	//------------------------ 逻辑状态 --------------------------
	/** 不可用 */
	public final static String STATUS_UNUSABLE = "0";
	
	/** 可用 */
	public final static String STATUS_USABLE = "1";

	//--------------------------------------------接口参数-----------------------------------------	
	public static final String INTERFACE_NEWS_URL = "INTERFACE_NEWS_URL";//最新开奖查询
	public static final String INTERFACE_NEXT_URL = "INTERFACE_NEXT_URL";//按下期查询
	public static final String INTERFACE_DAILY_URL = "INTERFACE_DAILY_URL";//按开奖日期查询
	public static final String IS_OPEN = "IS_OPEN";
	public static final String TONKEN = "TONKEN";
	public static final String RETURN_FORMAT = "RETURN_FORMAT";
	
	public static final String GATHER_DAY = "GATHER_DAY";
	//--------------------------------------------快速设置金额参数-----------------------------------------	
	public static final String FAST_SET_MONEY1="FAST_SET_MONEY1";//金额1
	public static final String FAST_SET_MONEY2="FAST_SET_MONEY2";//金额1
	public static final String FAST_SET_MONEY3="FAST_SET_MONEY3";//金额1
	public static final String FAST_SET_MONEY4="FAST_SET_MONEY4";//金额1
	public static final String FAST_SET_MONEY5="FAST_SET_MONEY5";//金额1
	
	//时间切换 0北京时间 1美东时间
	public static final String CP_TIME_IS_OPEN = "CP_TIME_IS_OPEN";
	
	public static final String CP_MONITOR_TIME = "CP_MONITOR_TIME";
	
	//高频
	public static final String CP_HK6_CLOSE_TIME="CP_HK6_CLOSE_TIME";
	public static final String CP_FC3D_CLOSE_TIME="CP_FC3D_CLOSE_TIME";
	public static final String CP_PL3_CLOSE_TIME="CP_PL3_CLOSE_TIME";
 

	public static final String	CP_CQSSC_CLOSE_TIME="CP_CQSSC_CLOSE_TIME";
	public static final String	CP_JXSSC_CLOSE_TIME="CP_JXSSC_CLOSE_TIME";
	public static final String	CP_TJSSC_CLOSE_TIME="CP_TJSSC_CLOSE_TIME";
	public static final String	CP_XJSSC_CLOSE_TIME="CP_XJSSC_CLOSE_TIME";
	public static final String	CP_TJKLSF_CLOSE_TIME="CP_TJKLSF_CLOSE_TIME";
	public static final String	CP_GDKLSF_CLOSE_TIME="CP_GDKLSF_CLOSE_TIME";
	public static final String	CP_BJPK10_CLOSE_TIME="CP_BJPK10_CLOSE_TIME";
	
	public static final String CP_DP_OPEN_HOUR="CP_DP_OPEN_HOUR";
	
	
	public static final String	WEB_USER_ONLINE_AUTH_IP="web_user_online_auth_ip";
	
	
	//彩票
	public static final String CP_ACT_PRO_TYPE = "caipiao";
	public static final String CP_BET_OUT = "31";//投注支出(体育与彩票)
	public static final String CP_BET_IN = "32";//投注收入（赢收入、注单取消收入、注单异常等收入）（体育与彩票）
	
	
	//--------------------------------------------彩票代码-----------------------------------------	
	
	//彩票代码值
	//六合彩
	public static final String HK_CODE_PARAM="HK6";
	//福彩3D
	public static final String FC3D_CODE_PARAM="FC3D";
	//排列3
	public static final String PL3_CODE_PARAM="PL3";
	//重庆时时彩
	public static final String CQSSC_CODE_PARAM="CQSSC";
	//江西时时彩
	public static final String JXSSC_CODE_PARAM="JXSSC";
	//天津时时彩
	public static final String	TJSSC_CODE_PARAM="TJSSC";
	//新疆时时彩
	public static final String XJSSC_CODE_PARAM="XJSSC";
	//天津快乐十分
	public static final String TJKLSF_CODE_PARAM="TJKLSF";
	//广东快乐十分
	public static final String GDKLSF_CODE_PARAM="GDKLSF";
	//北京pk拾
	public static final String BJPK10_CODE_PARAM="BJPK10";
	//幸运28
	public static final String BJKL8_CODE_PARAM="BJKL8";
	
	//加拿大28
	public static final String CAKENO_CODE_PARAM="CAKENO";
	//订单状态
	//未结算
	public static final String CP_ORDER_STATUS_WJS = "0";
	//已结算
	public static final String CP_ORDER_STATUS_YJS = "1";
	
	//状态  1表示下单 2表示结算
	public static final String CP_OPT_ORDER_STATUS_PR = "1";
	public static final String CP_OPT_ORDER_STATUS_BE = "2";
	
	//状态 0表示停用1表示启用
	public static final String CP_FAST_SET_STATUS_TY = "0";
	//已结算
	public static final String CP_FAST_SET_STATUS_QY = "1";
	
	

	
	public static Map<String, String> lxCode = new HashMap<String, String>();
	static{
		lxCode.put("0", HK_CODE_PARAM);//HK6
		lxCode.put("1", FC3D_CODE_PARAM);
		lxCode.put("2", PL3_CODE_PARAM);
		lxCode.put("3", CQSSC_CODE_PARAM);
		lxCode.put("5", JXSSC_CODE_PARAM);
		lxCode.put("7", TJSSC_CODE_PARAM);
		lxCode.put("10", XJSSC_CODE_PARAM);
		lxCode.put("11", GDKLSF_CODE_PARAM);
		lxCode.put("12", TJKLSF_CODE_PARAM);
		lxCode.put("13", BJPK10_CODE_PARAM);
		lxCode.put("14", BJKL8_CODE_PARAM);//北京快乐8
	}
	
	//香港六合彩 连码 
	 //组合类型  1复式 2 胆拖 3 生肖对碰 4 尾数对碰
	public static final String HK6_LM_ZHLX_FS="1";
	public static final String HK6_LM_ZHLX_TD="2";
	public static final String HK6_LM_ZHLX_SXDP="3";
	public static final String HK6_LM_ZHLX_WSDP="4";
	
	//类别  0 三全中 1三中二 2二全中 3二中特 4特串
	public static final String HK6_LM_LB_3QZ="0";
	public static final String HK6_LM_LB_3Q2="1";
	public static final String HK6_LM_LB_2QZ="2";
	public static final String HK6_LM_LB_2ZT="3";
	public static final String HK6_LM_LB_TC="4";
	
	//连码 配置id
	public static final String HK6_LM_CFGID_SQZ = "496";
	public static final String HK6_LM_CFGID_Z2 = "497";
	public static final String HK6_LM_CFGID_Z3 = "498";
	public static final String HK6_LM_CFGID_2QZ = "499";
	public static final String HK6_LM_CFGID_ZT = "500";
	public static final String HK6_LM_CFGID_TZ2 = "501";
	public static final String HK6_LM_CFGID_TC = "502";
	
	//接口
	public static final String SPORT_INTERFACE_AUT_URL = "SPORT_INTERFACE_AUT_URL";
	public static final String INTERFACE_AUT_URL = "INTERFACE_AUT_URL";
	public static final String INTERFACE_AUT_UID = "INTERFACE_AUT_UID";
	public static final String INTERFACE_AUT_UDD = "INTERFACE_AUT_UDD";
	public static final String INTERFACE_AUT_DES = "INTERFACE_AUT_DES";
	public static final String APIPLUS_IFC_URL = "APIPLUS_IFC_URL";//彩票时间备用接口的URL
	
	public static final String PT_LOGINAUT_URL = "PT_LOGINAUT_URL";
	public static final String PT_BRAND_CODE = "PT_BRAND_CODE";
	
	public static final String SB_SPORTBOOK_LOGIN_URL="SB_SPORTBOOK_LOGIN_URL";
	public static final String SB_MOBILE_LOGIN_URL="SB_MOBILE_LOGIN_URL";
	
	public static final String MG_ACCOUNT_MOBILE = "MG_ACCOUNT_MOBILE";
	public static final String MG_ACCOUNT_EMAIL = "MG_ACCOUNT_EMAIL";
	public static final String AG_ODD_TYPE = "AG_ODD_TYPE";
	
	public static final String WEB_USER_FLAG = "WEB_USER_FLAG";
	public static final String WEB_API_FLAG = "WEB_API_FLAG";
	public static final String TTG_LOGIN_URL = "TTG_LOGIN_URL";
	public static final String TTG_MOBILE_LOGIN_URL = "TTG_MOBILE_LOGIN_URL";
	public static final String SBT_LOGIN_URL = "SBT_LOGIN_URL";
	public static final String MARCHID = "MARCHID";
	public static final String MARCHPWD = "MARCHPWD";
	
	//平台代码
	public final static String PLAT_PARAM_AG = "ag";
	public final static String PLAT_PARAM_BBIN = "bbin";
	public final static String PLAT_PARAM_DS = "ds";
	public final static String PLAT_PARAM_HG = "hg";
	public final static String PLAT_PARAM_MG = "mg";
	public final static String PLAT_PARAM_NT = "nt";
	public final static String PLAT_PARAM_PT = "pt";
	public final static String PLAT_PARAM_SPORT = "sport";
	public final static String PLAT_PARAM_CAIPIAO = "caipiao";
	public final static String PLAT_PARAM_DOUJI = "douji";
	public final static String PLAT_PARAM_HUANGGUAN = "huangguan";
	public final static String PLAT_PARAM_AB = "ab";
	public final static String PLAT_PARAM_OG = "og";
	public final static String PLAT_PARAM_OS = "os";
	public final static String PLAT_PARAM_SB = "sb";
	public final static String PLAT_PARAM_GD = "gd";
	public final static String PLAT_PARAM_TTG = "ttg";
	public final static String PLAT_PARAM_SBT = "sbt";
	public final static String PLAT_PARAM_NEWNT = "newnt";//新NT
	public final static String PLAT_PARAM_AGFISH = "agfish";
	public final static String PLAT_PARAM_SB_SPORT = "sbSport";
	public final static String PLAT_PARAM_SB_CASION = "sbCasion";
	public final static String PLAT_PARAM_SA = "sa";
	public final static String PLAT_PARAM_VG = "vg";
	public final static String CP_PARM_ ="cp_fc3d";
	
	//抽奖
	public static final String  ACTIVITY_BALACE_TIME = "ACTIVITY_BALACE_TIME";
	public static final String  ACTIVITY_CLEAR_TIME = "ACTIVITY_CLEAR_TIME";	
//	public static final String  ACTIVITY_MAX_SUM_TIMES = "ACTIVITY_MAX_SUM_TIMES";	
//	public static final String  ACTIVITY_IS_DAI_RULE = "ACTIVITY_IS_DAI_RULE";
	public static final String  ACTIVITY_CLEAR_LOT_DAYS = "ACTIVITY_CLEAR_LOT_DAYS";
	public static final String  ACTIVITY_CLEAR_ACT_DAYS = "ACTIVITY_CLEAR_ACT_DAYS";
	
	public static Map<String,String> flatsBetRecord = new LinkedHashMap<String,String>();
	public static Map<String,String> flatsTransfRecord = new LinkedHashMap<String,String>();
	static{
		flatsTransfRecord.put(PLAT_PARAM_AG, "AG");
		flatsTransfRecord.put(PLAT_PARAM_BBIN,"BBIN");
		flatsTransfRecord.put(PLAT_PARAM_DS,"DS");
		flatsTransfRecord.put(PLAT_PARAM_HG,"HG");
		flatsTransfRecord.put(PLAT_PARAM_MG,"MG");
		//flatsTransfRecord.put(PLAT_PARAM_NT,"NT");
		flatsTransfRecord.put(PLAT_PARAM_PT,"PT");
		flatsTransfRecord.put(PLAT_PARAM_AB,"欧博");
		flatsTransfRecord.put(PLAT_PARAM_OG,"OG");
		flatsTransfRecord.put(PLAT_PARAM_OS,"OS");
		flatsTransfRecord.put(PLAT_PARAM_SB, "沙巴");
		flatsTransfRecord.put(PLAT_PARAM_GD, "GD");
		flatsTransfRecord.put(PLAT_PARAM_TTG, "TTG");
		flatsTransfRecord.put(PLAT_PARAM_SBT, "SBT");
		flatsTransfRecord.put(PLAT_PARAM_NEWNT, "NT");
		flatsTransfRecord.put(PLAT_PARAM_SA, "SA");
		flatsTransfRecord.put(PLAT_PARAM_VG, "VG");
		
		flatsBetRecord.put(PLAT_PARAM_AG, "AG");
		flatsBetRecord.put(PLAT_PARAM_BBIN,"BBIN");
		flatsBetRecord.put(PLAT_PARAM_DS,"DS");
		flatsBetRecord.put(PLAT_PARAM_HG,"HG");
		flatsBetRecord.put(PLAT_PARAM_MG,"MG");
		//flatsBetRecord.put(PLAT_PARAM_NT,"NT");
		flatsBetRecord.put(PLAT_PARAM_PT,"PT");
		flatsBetRecord.put(PLAT_PARAM_AB,"欧博");
		flatsBetRecord.put(PLAT_PARAM_OG,"OG");
		flatsBetRecord.put(PLAT_PARAM_OS,"OS");
		flatsBetRecord.put(PLAT_PARAM_SB_SPORT, "沙巴体育");
		flatsBetRecord.put(PLAT_PARAM_SB_CASION, "沙巴真人");
		flatsBetRecord.put(PLAT_PARAM_GD,"GD");
		flatsBetRecord.put(PLAT_PARAM_TTG,"TTG");
		flatsBetRecord.put(PLAT_PARAM_SBT,"SBT");
		flatsBetRecord.put(PLAT_PARAM_NEWNT,"NT");
		flatsBetRecord.put(PLAT_PARAM_SA,"SA");
		flatsBetRecord.put(PLAT_PARAM_VG, "VG");
	}
}
