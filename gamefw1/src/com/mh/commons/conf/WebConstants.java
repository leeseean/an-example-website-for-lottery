package com.mh.commons.conf;

import java.util.HashMap;
import java.util.Map;

/**
 * 前端与会员中心
 * 
 * @author Administrator
 * 
 */
public class WebConstants {
	
	public static String webDefaultAgentNo = "888";
	
	
	/** 服务层请求响应状态码 **/
	public static String LOG_RES_CODE_SUCCESS = "00";// 成功
	public static String LOG_RES_CODE_EXCEPTION = "-1";// 异常
	public static String LOG_RES_CODE_BLANK = "01";// 空值
	public static String LOG_RES_CODE_RESULT_NULL = "04";// 结果空值

	public static String LOG_RES_CODE_UPDATE_FAIL = "03";// 更新失败

	/** 登录相关会话名 **/
	public static String SESSION_VERIFY_CODE = "web.verify.code";// 登录验证码会话名
	public static String SESSION_WEBUSER_ENTITY = "webUser";// 登录帐号对象实体
	public static String IS_LETOUT = "isLetOut";// 是否被登出
	public static String SESSION_WEBUSER_ENTITY_FLAT = "webUserFlat";// 平台帐号信息
	public static String APPLICATION_USERS = "application.users";// 全局web用户信息

	public static String USER_ACCESS_DOMAIN = "user.access.domain";

	public static Integer T_WEB_BANK_HUIKUAN_MODEL_1 = Integer.valueOf("1");// 银行卡划款
	public static Integer T_WEB_BANK_HUIKUAN_MODEL_2 = Integer.valueOf("2");// 在线支付
	public static Integer T_WEB_BANK_HUIKUAN_MODEL_3 = Integer.valueOf("3");// 红利赠送
	public static Integer T_WEB_BANK_HUIKUAN_MODEL_4 = Integer.valueOf("4");// 后台入款
	public static Integer T_WEB_BANK_HUIKUAN_MODEL_5 = Integer.valueOf("5");// 投注返水
	public static Integer T_WEB_BANK_HUIKUAN_MODEL_6 = Integer.valueOf("6");// 免费彩金

	public static Integer T_WEB_BANK_HUIKUAN_MODEL_7 = Integer.valueOf("7");// 存款赠送
	public static Integer T_WEB_BANK_HUIKUAN_MODEL_9 = Integer.valueOf("9");// 存款优惠

	public static Integer T_WEB_BANK_HUIKUAN_STATUS_0 = Integer.valueOf("0");// 待审核存款
	public static Integer T_WEB_BANK_HUIKUAN_STATUS_1 = Integer.valueOf("1");// 已审核
	public static Integer T_WEB_BANK_HUIKUAN_CHECKED_STATUS_2 = Integer.valueOf("2");// 审核不通过
	public static Integer T_WEB_BANK_HUIKUAN_CHECKED_STATUS_1 = Integer.valueOf("1");// 审核通过
	public static Integer T_WEB_BANK_HUIKUAN_CHECKED_STATUS_0 = Integer.valueOf("0");// 初始状态

	public static Integer T_WEB_USER_WITHDRAW_STATUS_0 = Integer.valueOf("0");// 待审核
	public static Integer T_WEB_USER_WITHDRAW_STATUS_1 = Integer.valueOf("1");// 已审核
	public static Integer T_WEB_USER_WITHDRAW_CHECKED_STATUS_2 = Integer.valueOf("2");// 审核不通过
	public static Integer T_WEB_USER_WITHDRAW_CHECKED_STATUS_1 = Integer.valueOf("1");// 审核通过
	public static Integer T_WEB_USER_WITHDRAW_CHECKED_STATUS_0 = Integer.valueOf("0");// 初始状态

	public static Integer T_WEB_MANUAL_ACCOUNT_CHECKED_STATUS_1 = Integer.valueOf("1");// 审核通过
	public static Integer T_WEB_MANUAL_ACCOUNT_CHECKED_STATUS_0 = Integer.valueOf("0");// 审核不通过
	public static Integer T_WEB_MA_TYPE_11 = Integer.valueOf("11");// 11会员提款
	public static Integer T_WEB_MA_TYPE_12 = Integer.valueOf("12");// 12系统扣款

	public static Integer T_WEB_MA_CLASS_1 = Integer.valueOf("1");// 存款
	public static Integer T_WEB_MA_CLASS_2 = Integer.valueOf("2");// 入款

	public static Integer T_WEB_MESSAGE_TYPE_1 = Integer.valueOf("1");// 系统消息
	public static Integer T_WEB_MESSAGE_TYPE_2 = Integer.valueOf("2");// 会员消息
	public static Integer T_WEB_MESSAGE_TYPE_3 = Integer.valueOf("3");// 代理消息
	public static Integer T_WEB_MESSAGE_STATUS_0 = Integer.valueOf("0");// 消息状态有效
	public static Integer T_WEB_MESSAGE_STATUS_1 = Integer.valueOf("1");// 消息状态无效

	/** 额度互转类型 **/
	public static String EDU_TYPE_1 = "1";// 转出（游戏平-》台主帐号）
	public static String EDU_TYPE_2 = "2";// 转入 （主帐号-》游戏平台）
	
	public static Map<String,String> eduCodeMap = new HashMap<String,String>();
	static{
		eduCodeMap.put(EDU_TYPE_1, "21");
		eduCodeMap.put(EDU_TYPE_2, "22");
	}
	//转换状态(1-成功,0-失败)
	/** 作废 **/
	public static int EDU_STATUS_2 = 2;
	/** 成功 **/
	public static int EDU_STATUS_1 = 1;
	/** 失败 **/
	public static int EDU_STATUS_0 = 0;
	/** 初始化 **/
	public static int EDU_STATUS_INIT = -1;

	/** 平台名称 **/
	public static String FLAT_NAME_ACCOUNT = "account";
	public static String FLAT_NAME_BBIN = "bbin";
	public static String FLAT_NAME_MG = "mg";
	public static String FLAT_NAME_TTG = "ttg";
	public static String FLAT_NAME_SBT = "sbt";
	public static String FLAT_NAME_AG = "ag";
//	public static String FLAT_NAME_KG = "kg";
	public static String FLAT_NAME_HG = "hg";
//	public static String FLAT_NAME_JBB = "jbb";
	public static String FLAT_NAME_SPORT = "sport";
	public static String FLAT_NAME_DS = "ds";
	public static String FLAT_NAME_NT = "nt";
	public static String FLAT_NAME_PT = "pt";
	public static String FLAT_NAME_CP ="caipiao";
	public static String FLAT_NAME_DJ = "douji";
	public static String FLAT_NAME_AB = "ab";
	public static String FLAT_NAME_OG = "og";
	public static String FLAT_NAME_OS = "os";
	public static String FLAT_NAME_SB = "sb";
	public static String PLAT_PARAM_SB_SPORT = "sbSport";
	public static String PLAT_PARAM_SB_CASION = "sbCasion";
	public static String FLAT_NAME_GD = "gd";
	public static String FLAT_NAME_NEWNT = "newnt";
	public static String FLAT_NAME_AGFISH = "agfish";
	public static String FLAT_NAME_SA = "sa";
	public static String FLAT_NAME_VG = "vg";
	
	public final static String OS_ELECTRONIC_REAL = "osElectronicReal"; 
	public final static String MG_LIVE = "mgLive";
	public final static String MG_ELECTRONIC = "mgElectronic";
	public final static String NT_ELECTRONIC = "ntElectronic";
	public final static String PT_ELECTRONIC = "ptElectronic";
	
	public static String SB_SPORT = "Sport";
	public static String SB_CASION = "Casion";
	/** 额度转化方向 **/
	public static int EDU_FORWARD_BBIN_2 = 21;//
	public static int EDU_FORWARD_MG_2 = 22;//
	public static int EDU_FORWARD_AG_2 = 23;//
	public static int EDU_FORWARD_DJ_2 = 24;//
	public static int EDU_FORWARD_KG_2 = 25;//
	public static int EDU_FORWARD_DS_2 = 26;//
	public static int EDU_FORWARD_HG_2 = 27;//
	public static int EDU_FORWARD_NT_2 = 28;//
	public static int EDU_FORWARD_PT_2 = 29;//
	
	public static int EDU_FORWARD_AB_2 = 61;//
	public static int EDU_FORWARD_OG_2 = 62;//
	public static int EDU_FORWARD_OS_2 = 63;//
	public static int EDU_FORWARD_SB_2 = 64;//
	public static int EDU_FORWARD_GD_2 = 65;//
	public static int EDU_FORWARD_TTG_2 = 66;//
	public static int EDU_FORWARD_SBT_2 = 67;//
	public static int EDU_FORWARD_NEWNT_2 = 68;//
	public static int EDU_FORWARD_SA_2 = 69;//
	public static int EDU_FORWARD_VG_2 = 70;//
	/** 额度转化方向备注 **/
	public static String EDU_FORWARD_REMARK_BBIN_2 = "主帐号->BBIN";//
	public static String EDU_FORWARD_REMARK_MG_2 = "主帐号->MG";//
	public static String EDU_FORWARD_REMARK_AG_2 = "主帐号->AG";//
	public static String EDU_FORWARD_REMARK_DJ_2 = "主帐号->斗鸡";//
	public static String EDU_FORWARD_REMARK_KG_2 = "主帐号->KG彩票";//
	public static String EDU_FORWARD_REMARK_DS_2 = "主帐号->DS";//
	public static String EDU_FORWARD_REMARK_HG_2 = "主帐号->HG";//
	public static String EDU_FORWARD_REMARK_NT_2 = "主帐号->NT";//
	public static String EDU_FORWARD_REMARK_PT_2 = "主帐号->PT";//
	public static String EDU_FORWARD_REMARK_AB_2 = "主帐号->AB";//
	public static String EDU_FORWARD_REMARK_OG_2 = "主帐号->OG";//
	public static String EDU_FORWARD_REMARK_OS_2 = "主帐号->OS";//
	public static String EDU_FORWARD_REMARK_SB_2 = "主帐号->SB";//
	public static String EDU_FORWARD_REMARK_GD_2 = "主帐号->GD";//
	public static String EDU_FORWARD_REMARK_TTG_2 = "主帐号->TTG";//
	public static String EDU_FORWARD_REMARK_SBT_2 = "主帐号->SBT";//
	public static String EDU_FORWARD_REMARK_NEWNT_2 = "主帐号->新NT";//
	public static String EDU_FORWARD_REMARK_SA_2 = "主帐号->SA";//
	public static String EDU_FORWARD_REMARK_VG_2 = "主帐号->VG";//

	/** 额度转化方向 **/
	public static int EDU_FORWARD_BBIN_1 = 11;//
	public static int EDU_FORWARD_MG_1 = 12;//
	public static int EDU_FORWARD_AG_1 = 13;//
	public static int EDU_FORWARD_DJ_1 = 14;//
	public static int EDU_FORWARD_KG_1 = 15;//
	public static int EDU_FORWARD_DS_1 = 16;//
	public static int EDU_FORWARD_HG_1 = 17;//
	public static int EDU_FORWARD_NT_1 = 18;//
	public static int EDU_FORWARD_PT_1 = 19;//
	
	public static int EDU_FORWARD_AB_1 = 51;//
	public static int EDU_FORWARD_OG_1 = 52;//
	public static int EDU_FORWARD_OS_1 = 53;//
	public static int EDU_FORWARD_SB_1 = 54;//
	public static int EDU_FORWARD_GD_1 = 55;//
	public static int EDU_FORWARD_TTG_1 = 56;//
	public static int EDU_FORWARD_SBT_1 = 57;//
	public static int EDU_FORWARD_NEWNT_1 = 58;//
	public static int EDU_FORWARD_SA_1 = 59;//
	public static int EDU_FORWARD_VG_1 = 60;//
	/** 额度转化方向备注 **/
	public static String EDU_FORWARD_REMARK_BBIN_1 = "BBIN->主帐号";//
	public static String EDU_FORWARD_REMARK_MG_1 = "MG->主帐号";//
	public static String EDU_FORWARD_REMARK_AG_1 = "AG->主帐号";//
	public static String EDU_FORWARD_REMARK_DJ_1 = "斗鸡->主帐号";//
	public static String EDU_FORWARD_REMARK_KG_1 = "KG->主帐号";//
	public static String EDU_FORWARD_REMARK_DS_1 = "DS->主帐号";//
	public static String EDU_FORWARD_REMARK_HG_1 = "HG->主帐号";//
	public static String EDU_FORWARD_REMARK_NT_1 = "NT->主帐号";//
	public static String EDU_FORWARD_REMARK_PT_1 = "PT->主帐号";//
	public static String EDU_FORWARD_REMARK_AB_1 = "AB->主帐号";//
	public static String EDU_FORWARD_REMARK_OG_1 = "OG->主帐号";//
	public static String EDU_FORWARD_REMARK_OS_1 = "OS->主帐号";//
	public static String EDU_FORWARD_REMARK_SB_1 = "SB->主帐号";//
	public static String EDU_FORWARD_REMARK_GD_1 = "GD->主帐号";//
	public static String EDU_FORWARD_REMARK_TTG_1 = "TTG->主帐号";//
	public static String EDU_FORWARD_REMARK_SBT_1 = "SBT->主帐号";//
	public static String EDU_FORWARD_REMARK_NEWNT_1 = "新NT->主帐号";//
	public static String EDU_FORWARD_REMARK_SA_1 = "SA->主帐号";//
	public static String EDU_FORWARD_REMARK_VG_1 = "VG->主帐号";//

	public static Integer T_BET_RECORD_BACK_WATER_STATUS_0 = new Integer("0");// 未返水状态
	public static Integer T_BET_RECORD_BACK_WATER_STATUS_1 = new Integer("1");// 已返水状态
	public static Integer T_BET_RECORD_BACK_WATER_STATUS_2 = new Integer("2");// 不返水状态

	public static Integer T_BET_RECORD_STATUS_0 = new Integer("0");// 未结算
	public static Integer T_BET_RECORD_STATUS_1 = new Integer("1");// 已结算

	public static Double BACK_WATER_RATIO = new Double("0.01");// 返水*这个比率
	public static Double RATIO = new Double("0.01");// *比率

	public static Integer T_WEB_DAMA_STATUS_1 = Integer.valueOf("1");// 打码优惠有效
	public static Integer T_WEB_DAMA_STATUS_0 = Integer.valueOf("0");// 打码优惠失效

	public static Integer T_WEB_HK_YOUHUI_0 = Integer.valueOf("0");// 汇款不送优惠
	public static Integer T_WEB_HK_YOUHUI_1 = Integer.valueOf("1");// 汇款送优惠

	public static Integer T_WEB_WITHDRAW_COST_0 = Integer.valueOf("0");// 取款手续费
																		// 不用
	public static Integer T_WEB_WITHDRAW_COST_1 = Integer.valueOf("1");// 取款手续费
																		// 要
	public static Integer WEB_AGENT_STATUS_ON = Integer.valueOf("1");//代理有效
	public static Integer WEB_AGENT_STATUS_OFF = Integer.valueOf("0");//代理无效
	
	public static Integer WEB_WEIHU_STATUS_ON = Integer.valueOf("1");//正常
	public static Integer WEB_WEIHU_STATUS_OFF = Integer.valueOf("0");//维护
	
	public static String SBT_MSG_KEY = "web_sport_sbt_msg";	//sbt提示信息key
}
