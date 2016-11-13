package com.mh.commons.conf;

import java.util.HashMap;
import java.util.Map;

import com.mh.web.job.CodeDataHelper;


public class SystemConstant  {
	
	public static String PROJECT_NAME_AG = "ag";
	public static String PROJECT_NAME_MG = "mg";
	public static String PROJECT_NAME_JBB = "jbb";
	public static String PROJECT_NAME_BBIN = "bbin";
	public static String PROJECT_NAME_DS = "ds";
	public static String PROJECT_NAME_KG = "kg";
	public static String PROJECT_NAME_HG = "hg";
	public static String PROJECT_NAME_NT = "nt";
	public static String PROJECT_NAME_PT = "pt";
	public static String PROJECT_NAME_HUANGGUANG = "huangguang";
	public static String PROJECT_NAME_CAIPIAO = "caipiao";
	public static String PROJECT_NAME_HUIKUAN = "huikuan";
	public static String PROJECT_NAME_WITHDRAW = "withdraw";
	public static String PROJECT_NAME_EDU = "edu";

 
	
	public static Map<String, String> flatCodeValueShowNameMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("flat");// 平台类型value与显示
	public static Map<String, String> huikuanCodeValueShowNameMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("huikuan");// 入款类型value与显示
	public static Map<String, String> withdrawCodeValueShowNameMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("withdraw");// 出款类型value与显示
	public static Map<String, String> eduCodeValueShowNameMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("edu");// 额度类型value与显示
	public static Map<String, String> betCodeValueShowNameMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("bet");// 收入与支出类型value与显示

	public static Map<String, String> projectCodeNameValueMap = CodeDataHelper.getCodeNameValueMapByCodeType("project");// 
	public static Map<String, String> projectCodeValueShowNameMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("project");
	public static String project_huikuan = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "huikuan");
	public static String project_withdraw = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "withdraw");
	public static String project_edu = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "edu");
	public static String project_ag = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "ag");
	public static String project_mg = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "mg");
	public static String project_jbb = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "jbb");
	public static String project_bbin = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "bbin");
	public static String project_kg = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "kg");
	public static String project_huangguan = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "huangguan");
	public static String project_caipiao = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("project", "caipiao");

	public static String huikuan_bank = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "bank");// 1
	public static String huikuan_online = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "online");// 2
	public static String huikuan_hongli = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "hongli");// 3
	public static String huikuan_manual = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "manual");// 4
	public static String huikuan_water = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "water");// 5
	public static String huikuan_caiji = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "caiji");// 6
	public static String huikuan_dama = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "dama");// 7
	public static String huikuan_back = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "back");// 8
	public static String huikuan_youhui = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("huikuan", "youhui");// 9

	public static String withdraw_hytk = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("withdraw", "hytk");// 11
	public static String withdraw_manual = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("withdraw", "manual");// 12

	public static String edu_out = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("edu", "out");// 21
	public static String edu_in = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("edu", "in");// 22

	public static String bet_out = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet", "out");// 31投注支出(皇冠和彩票)
	public static String bet_in = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet", "in");// 32投注收入(皇冠和彩票)

	public static String flat_account = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("flat", "account");//

	/** ############################### **/
	//public static String userPsTimes = getParamValueByName("user_ps_times");//
	public static String webDefaultAgentNo = "888";
	public static String webStaticJbbSportUrl = "http://sb.room88.net/zh-cn/sport/";
	//public static String webMatchBetLimitMin = getParamValueByName("web_match_bet_limit_min");
	//public static String webOptEduLimitTime = getParamValueByName("web_opt_edu_limit_time");

	/**************** 皇冠体育 盘口名称 ***************/
	public static String btype_dy = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "dy");//
	public static String btype_rq = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "rq");//
	public static String btype_dx = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "dx");//
	public static String btype_ds = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "ds");//
	public static String btype_pd = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "pd");//
	public static String btype_t = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "t");//
	public static String btype_f = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "f");//
	public static String btype_rf = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "rf");//
	public static String btype_jf = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("odds", "jf");//

	public static String team_H = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("team", "H");//
	public static String team_C = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("team", "C");//
	public static String team_N = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("team", "N");//

	/********************** bet_status *************************/
	public static String BET_STATUS_WIN_FULL = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "win_full");// 1赢
	public static String BET_STATUS_LOST_FULL = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "lost_full");// 2输
	public static String BET_STATUS_WIN_HALF = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "win_half");// 3赢一半
	public static String BET_STATUS_LOST_HALF = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "lost_half");// 4输一半
	public static String BET_STATUS_DRAW = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "draw");// 5和局退款
	public static String BET_STATUS_MATCH_CANCEL = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "match_cancel");// 6比赛取消
	public static String BET_STATUS_ODDS_ERROR = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "odds_error");// 7赔率错误
	public static String BET_STATUS_BET_ERROR = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "bet_error");// 8比分错误
	public static String BET_STATUS_HANDICAP_ERROR = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "handicap_error");// 9盘口错误
	public static String BET_STATUS_HC_ERROR = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "hc_error");// 10队名错误
	public static String BET_STATUS_HEJI_CANCEL = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "heji_cancel");// 11和局取消
	public static String BET_STATUS_MATCH_LATER = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "match_later");// 12赛事延赛
	public static String BET_STATUS_MATCH_DIE = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "match_die");// 13赛事腰斩
	public static String BET_STATUS_MATCH_JINQIU = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "match_jinqiu");// 14进球取消
	public static String BET_STATUS_BET_CONFIRM = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "bet_confirm");// 15正在确认
	public static String BET_STATUS_BET_UNACCEPT = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "bet_unaccept");// 16未接受注单
	public static String BET_STATUS_BET_UNSETTLE = CodeDataHelper.getCodeValueByCodeTypeAndCodeName("bet_status", "bet_unsettle");// 17未结算

	public static Map<String, String> betCancelCodeValueShowNameMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("bet_cancel");// 体育取消类型value与显示
	public static Map<String, String> betStatusCodeValueShowNameMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("bet_status");// 体育注单类型value与显示

	public static Map<String, String> betAgentTuiyongCodeValueMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("agent_tuiyong");// 体育取消类型value与显示
	public static Map<String, String> betAgentTuishuiCodeValueMap = CodeDataHelper.getCodeValueShowNameMapByCodeType("agent_tuishui");// 体育注单类型value与显示
	
	

	/** 常用参数名称 **/
	public static String param_username = "username";
	public static String param_passwd = "passwd";
	public static String param_mtype = "mtype";
	public static String param_uid = "uid";
	public static String param_langx = "langx";
	public static String param_rtype = "rtype";
	public static String param_page_no = "page_no";
	public static String param_league_id = "league_id";
	public static String param_hot_game = "hot_game";
	public static String param_g_date = "g_date";

	/** 常量 **/
	public static String value_g_date = "ALL";

	/** 登录Post，返回的状态意义 **/
	public static String login_statu_success = "200";// 成功登录
	public static String login_statu_safe = "104";// 安全设置
	public static String login_statu_chg_passwd = "106";// 修改密码

	/** HTML，参数名 **/
	public static String T_PAGE = "_.t_page";// 总页数标识
	public static String GAMOUNT = "_.gamount";// 当前页数据条数标识
	public static String PARENTGAMOUNT = "parent.gamount";// 当前页数据条数标识
	public static String TOP_TODAY_GMT = "top.today_gmt";// 当前美东时间

	/** 皇冠网址 **/
	public static String loginUrl = "/app/member/index.php";// 登录窗口
	public static String loginPostUrl = "/app/member/new_login.php";// 提交登录地址
	public static String loginSuccessUrl = "/app/member/FT_index.php";// 登录成功后默认请求这个地址，此时会分配doomain进入系统

	private static Map<String, String> rTypeNameMap;// 投注类型
	static {
		rTypeNameMap = new HashMap<String, String>();
		rTypeNameMap.put("ft_r", "单式");
		rTypeNameMap.put("ft_pd", "波胆");
		rTypeNameMap.put("ft_t", "总入球");
		rTypeNameMap.put("ft_f", "半场/全场");
		rTypeNameMap.put("ft_p3", "综合过关");
		rTypeNameMap.put("bk_r_main", "单式");
		rTypeNameMap.put("bk_p3", "综合过关");

		rTypeNameMap.put("roll_re", "足球");
		rTypeNameMap.put("roll_re_main", "篮球美式足球");
		rTypeNameMap.put("ft_re", "足球");
		rTypeNameMap.put("bk_re_main", "篮球美式足球");
	}

	public static String getRTypeName(String key) {
		return rTypeNameMap.get(key);
	}

	public static Map<String, String> timeTypeNameMap;
	static {
		timeTypeNameMap = new HashMap<String, String>();
		timeTypeNameMap.put("today", "今日");
		timeTypeNameMap.put("tom", "早盘");
		timeTypeNameMap.put("roll", "滚球");
	}

	public static String getTimeTypeName(String timeType) {
		return timeTypeNameMap.get(timeType);
	}

	public static Map<String, String> typeNameMap;
	static {
		typeNameMap = new HashMap<String, String>();
		typeNameMap.put("ft", "足球");
		typeNameMap.put("bk", "篮球美式足球");
		typeNameMap.put("roll", "");
	}

	public static String getTypeName(String timeType) {
		return typeNameMap.get(timeType);
	}

	/** 全场 **/
	public static Map<String, String> pdMapH;
	static {
		pdMapH = new HashMap<String, String>();
		pdMapH.put("1_0", "1_0");
		pdMapH.put("2_0", "2_0");
		pdMapH.put("2_1", "2_1");
		pdMapH.put("3_0", "3_0");
		pdMapH.put("3_1", "3_1");
		pdMapH.put("3_2", "3_2");
		pdMapH.put("4_0", "4_0");
		pdMapH.put("4_1", "4_1");
		pdMapH.put("4_2", "4_2");
		pdMapH.put("4_3", "4_3");
		pdMapH.put("0_0", "0_0");
		pdMapH.put("1_1", "1_1");
		pdMapH.put("2_2", "2_2");
		pdMapH.put("3_3", "3_3");
		pdMapH.put("4_4", "4_4");
		pdMapH.put("other", "other");
	}

	public static Map<String, String> pdMapC;
	static {
		pdMapC = new HashMap<String, String>();
		pdMapC.put("0_1", "0_1");
		pdMapC.put("0_2", "0_2");
		pdMapC.put("1_2", "1_2");
		pdMapC.put("0_3", "0_3");
		pdMapC.put("1_3", "1_3");
		pdMapC.put("2_3", "2_3");
		pdMapC.put("0_4", "0_4");
		pdMapC.put("1_4", "1_4");
		pdMapC.put("2_4", "2_4");
		pdMapC.put("3_4", "3_4");
		pdMapC.put("0_0", "0_0");
		pdMapC.put("1_1", "1_1");
		pdMapC.put("2_2", "2_2");
		pdMapC.put("3_3", "3_3");
		pdMapC.put("4_4", "4_4");
		pdMapC.put("other", "other");
	}

	/** 半场 **/
	public static Map<String, String> pdMapH_h;
	static {
		pdMapH_h = new HashMap<String, String>();
		pdMapH_h.put("1_0", "1_0");
		pdMapH_h.put("2_0", "2_0");
		pdMapH_h.put("2_1", "2_1");
		pdMapH_h.put("3_0", "3_0");
		pdMapH_h.put("3_1", "3_1");
		pdMapH_h.put("3_2", "3_2");
		pdMapH_h.put("0_0", "0_0");
		pdMapH_h.put("1_1", "1_1");
		pdMapH_h.put("2_2", "2_2");
		pdMapH_h.put("3_3", "3_3");
		pdMapH_h.put("other", "other");
	}

	public static Map<String, String> pdMapC_h;
	static {
		pdMapC_h = new HashMap<String, String>();
		pdMapC_h.put("0_1", "0_1");
		pdMapC_h.put("0_2", "0_2");
		pdMapC_h.put("1_2", "1_2");
		pdMapC_h.put("0_3", "0_3");
		pdMapC_h.put("1_3", "1_3");
		pdMapC_h.put("2_3", "2_3");
		pdMapC_h.put("0_0", "0_0");
		pdMapC_h.put("1_1", "1_1");
		pdMapC_h.put("2_2", "2_2");
		pdMapC_h.put("3_3", "3_3");
		pdMapC_h.put("other", "other");
	}

	public static String match_type_ft = "FT";
	public static String match_type_bk = "BK";

	/** 足球类型 **/
	public static String ft_rtype_r = "r";// 独赢&让球&大小&单/双
	public static String ft_rtype_pd = "pd";// 波胆
	public static String ft_rtype_t = "t";// 总入球
	public static String ft_rtype_f = "f";// 半场/全场
	public static String ft_rtype_p3 = "p3";// 综合过关
	public static String ft_rtype_hpd = "hpd";// 半场 波胆

	/** 篮球 **/
	public static String bk_rtype_rmain = "r_main";// 独赢盘&让分&大小
	public static String bk_rtype_p3 = "p3";// 综合过关

	public static String bk_rtype_rmain_cj = "bk_r_main";// 独赢盘&让分&大小
	public static String bk_rtype_p3_cj = "bk_p3";// 综合过关

	/** 滚球 **/
	public static String roll_rtype_re = "re";// 滚球 足球
	public static String roll_rtype_remain = "re_main";// 滚球 篮球

	public static String match_rtype_p3 = "p3";

	public static String TIME_TYPE_TODAY = "today";// 今日赛事
	public static String TIME_TYPE_TOM = "tom";// 早盘
	public static String TIME_TYPE_ROLL = "roll";// 滚球

	public static String TABLE_FT_MATCH_R = "t_ft_match_r";
	public static String TABLE_FT_MATCH_PD = "t_ft_match_pd";
	public static String TABLE_FT_MATCH_T = "t_ft_match_t";
	public static String TABLE_FT_MATCH_F = "t_ft_match_f";
	public static String TABLE_FT_MATCH_P3 = "t_ft_match_p3";

	public static String TABLE_BK_MATCH_RMAIN = "t_bk_match_rmain";
	public static String TABLE_BK_MATCH_P3 = "t_bk_match_p3";

	public static String TABLE_R_MATCH_RE = "t_r_match_re";
	public static String TABLE_R_MATCH_REMAIN = "t_r_match_remain";

	/** ######采水网址###### **/
	/** ##今日赛事-早盘## **/
	/** 足球数据地址 **/
	public static String today_ft = "/app/member/FT_browse/body_var.php";
	public static String tom_ft = "/app/member/FT_future/body_var.php";
	/** 籃球数据地址 **/
	public static String today_bk = "/app/member/BK_browse/body_var.php";
	public static String tom_bk = "/app/member/BK_future/body_var.php";

	/** 赛事结果 **/
	public static Integer RESULT_MATCH_STATUS_0 = new Integer("0");// 延赛\腰斩
	public static Integer RESULT_MATCH_STATUS_1 = new Integer("1");// 正常赛事

	public static Integer RESULT_STATUS_0 = new Integer("0");// 未处理
	public static Integer RESULT_STATUS_1 = new Integer("1");// 结算中
	public static Integer RESULT_STATUS_2 = new Integer("2");// 结算完成
	public static Integer RESULT_STATUS_3 = new Integer("3");// 结算失败
	public static Integer RESULT_STATUS_4 = new Integer("4");// 结算时，赛果异常

	public static Integer SETTLED_STATUS_0 = new Integer("0");// 初始状态
	public static Integer SETTLED_STATUS_1 = new Integer("1");// 过程中
	public static Integer SETTLED_STATUS_2 = new Integer("2");// 完成
	public static Integer SETTLED_STATUS_3 = new Integer("3");// 异常

	/** 结算 **/
	public static Integer RESULT_MATCH_SETTLED_0 = new Integer("0");// 未结
	public static Integer RESULT_MATCH_SETTLED_1 = new Integer("1");// 已结

	public static Integer PD_TAG_1 = new Integer("1");// 波胆 上半场
	public static Integer PD_TAG_2 = new Integer("2");// 波胆 全场

	public static String BK_RF_TEAM_BIG = "dx_big";// 球队让分，大
	public static String BK_RF_TEAM_SMALL = "dx_small";// 球队让分，小

	/** 下注相关 **/
	public static String BET_MATCH_RESULT_1 = "1";// 赢(客户)
	public static String BET_MATCH_RESULT_2 = "2";// 输(客户)

	public static Integer ORDER_STATUS_m2 = new Integer("-2");// 状态（滚球确认中-2）
	public static Integer ORDER_STATUS_m1 = new Integer("-1");// 状态（待确认订单-1）
	public static Integer ORDER_STATUS_0 = new Integer("0");// 状态（无效订单0）
	public static Integer ORDER_STATUS_1 = new Integer("1");// 状态（可结算订单1）
	public static Integer ORDER_STATUS_2 = new Integer("2");// 状态（已结算订单2）
	public static Integer ORDER_STATUS_3 = new Integer("3");// 状态（订单结算过程中）
	public static Integer ORDER_STATUS_4 = new Integer("4");// 状态（异常过程中）

	public static Integer BET_STATUS_0 = new Integer("0");// 初始入库状态
	public static Integer BET_STATUS_1 = new Integer("1");// 初始入库状态

	public static Integer BET_TYPE_1 = new Integer("1");// 下注类型：常规
	public static Integer BET_TYPE_2 = new Integer("2");// 下注类型：滚球

	/** SettleOddsBean结算常量 **/
	public static int SOB_SY_WIN = 1;// 赢
	public static int SOB_SY_LOST = 2;// 输
	public static int SOB_SY_WIN_HALF = 3;// 赢一半
	public static int SOB_SY_LOST_HALF = 4;// 输一半
	public static int SOB_SY_NONE = 0;// 不输赢

	public static double SOB_MULT_FULL = 1;// 全输赢
	public static double SOB_MULT_HALF = 0.5;// 输赢一半
	public static double SOB_MULT_O = 0;// 零
	public static int SOB_BEN_YES = 1;// 返本钱
	public static int SOB_BEN_NO = 0;// 不用返本钱

	/** 常用多维常量 **/
	public static String TAG_RTYPE = "rtype";
	public static String TAG_BTYPE = "btype";
	public static String TAG_DTYPE = "dtype";
	public static String TAG_SELECTION = "selection";
	public static String TAG_PERIOD = "period";

	public static String TAG_PERIOD_F = "f";// 全场
	public static String TAG_PERIOD_H = "h";// 半场

	public static String TAG_SELECTION_H = "H";// 主队
	public static String TAG_SELECTION_C = "C";// 客队
	public static String TAG_SELECTION_N = "N";// 中队

	public static String TAG_RQ_TEAM_H = "H";// 主队主球
	public static String TAG_RQ_TEAM_C = "C";// 客队让球

	/****************** 下注 赔率类型 *****************/
	public static String BTYPE_DY = "dy";
	public static String BTYPE_RQ = "rq";
	public static String BTYPE_RF = "rf";
	public static String BTYPE_JF = "jf";
	public static String BTYPE_DX = "dx";
	public static String BTYPE_DS = "ds";
	public static String BTYPE_PD = "pd";
	public static String BTYPE_T = "t";
	public static String BTYPE_F = "f";

	/** 让球让分 **/

	/** 波胆 **/
	public static String ODDS_pd_1_0 = "1_0";
	public static String ODDS_pd_2_0 = "2_0";
	public static String ODDS_pd_2_1 = "2_1";
	public static String ODDS_pd_3_0 = "3_0";
	public static String ODDS_pd_3_1 = "3_1";
	public static String ODDS_pd_3_2 = "3_2";
	public static String ODDS_pd_4_0 = "4_0";
	public static String ODDS_pd_4_1 = "4_1";
	public static String ODDS_pd_4_2 = "4_2";
	public static String ODDS_pd_4_3 = "4_3";
	public static String ODDS_pd_0_0 = "0_0";
	public static String ODDS_pd_1_1 = "1_1";
	public static String ODDS_pd_2_2 = "2_2";
	public static String ODDS_pd_3_3 = "3_3";
	public static String ODDS_pd_4_4 = "4_4";
	public static String ODDS_pd_other = "other";
	public static String ODDS_pd_0_1 = "0_1";
	public static String ODDS_pd_0_2 = "0_2";
	public static String ODDS_pd_1_2 = "1_2";
	public static String ODDS_pd_0_3 = "0_3";
	public static String ODDS_pd_1_3 = "1_3";
	public static String ODDS_pd_2_3 = "2_3";
	public static String ODDS_pd_0_4 = "0_4";
	public static String ODDS_pd_1_4 = "1_4";
	public static String ODDS_pd_2_4 = "2_4";
	public static String ODDS_pd_3_4 = "3_4";

	/** 总入球 **/
	public static String ODDS_t_0_1 = "0_1";
	public static String ODDS_t_2_3 = "2_3";
	public static String ODDS_t_4_6 = "4_6";
	public static String ODDS_t_7up = "7up";

	/** 半场/全场 **/
	public static String ODDS_f_H_H = "H_H";
	public static String ODDS_f_H_N = "H_N";
	public static String ODDS_f_H_C = "H_C";
	public static String ODDS_f_N_H = "N_H";
	public static String ODDS_f_N_N = "N_N";
	public static String ODDS_f_N_C = "N_C";
	public static String ODDS_f_C_H = "C_H";
	public static String ODDS_f_C_N = "C_N";
	public static String ODDS_f_C_C = "C_C";

	public static String MATCH_TYPE_CONS = "match_type";
	public static String MATCH_ODDS_CONS = "odds";

	public static Integer RESULT_MATCH_STATUS_10 = new Integer("10");// 正常完赛
	public static Integer K_RESULT_MATCH_STATUS_0 = new Integer("0");// 取消
	public static Integer K_RESULT_MATCH_STATUS_1 = new Integer("1");// 赛事延赛
	public static Integer K_RESULT_MATCH_STATUS_2 = new Integer("2");// 赛事无PK/加时
	public static Integer K_RESULT_MATCH_STATUS_3 = new Integer("3");// 队名错误
	public static Integer K_RESULT_MATCH_STATUS_4 = new Integer("4");// 赛事腰斩(下半场腰斩)
	public static Integer K_RESULT_MATCH_STATUS_5 = new Integer("5");// 赛事腰斩(全场腰斩)
 

	
}
