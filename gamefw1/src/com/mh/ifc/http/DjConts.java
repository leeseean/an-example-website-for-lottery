package com.mh.ifc.http;

/**
 * 检测与创建帐号返回值
 * 
 * @author Administrator
 * 
 */
public class DjConts {
	/** 请求参数名 **/
	public static String APIKEY = "api_key";// api帐号
	public static String USERNAME = "username";// 游戏帐号（3-10英文与数字组合）
	public static String PASSWORD = "password";// 游戏密码（3-10英文与数字组合）
	public static String AMOUNT = "amount";// 金额
	public static String REFNO = "ref_no";//唯一交易号
	//public static String LANGUAGE = "language";// 语言
	public static String STARTTIME = "start_time";//系统标示
	public static String WEBFLAG = "webFlag";// 系统标示
	
	/** 语言 **/
	public static String LANG_ZH_CN = "CN";
	public static String LANG_ZH_TW = "TW";
	public static String LANG_EN_US = "EN";

	/** 游戏类型 **/
	public static String PAGESITE_BACCARAT = "BACCARAT";//百家乐
	public static String PAGESITE＿DRAGON_TIGER = "DRAGON_TIGER";//龙虎
	public static String PAGESITE_ROULETTE = "ROULETTE";//轮盘
	public static String PAGESITE_BACCARAT_INSURANCE = "BACCARAT_INSURANCE";//保险百家乐
	public static String PAGESITE_SICBO = "SICBO";//骰宝
	public static String PAGESITE_XOC_DIA = "XOC_DIA";//色碟
	
	
	/** 返回结果 **/
	public static String RES_CODE_00 = "00";// 成功
	public static String RES_CODE_6101 = "61.01";//参考编号不存在 (Ref No. existed)
	public static String RES_CODE_6103 = "61.03";//用户名不存在(Username not found)
	public static String RES_CODE_6105 = "61.05";//超额，额度不够(Withdraw amount exceed balance)
	public static String RES_CODE_6199 = "61.99";//重复提交(Mutliple submission)
	public static String RES_CODE_99 = "99";//参考编号不存在 (Ref No. existed)

}
