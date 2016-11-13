package com.mh.ifc.http;

/**
 * 检测与创建帐号返回值
 * 
 * @author Administrator
 * 
 */
public class MGConts {
	public static final String ROWID = "";
	/** 请求参数名 **/
	public static String APIACCOUNT = "apiAccount";// api帐号
	public static String USERNAME = "userName";// 游戏帐号（3-10英文与数字组合）
	public static String PASSWORD = "password";// 游戏密码（3-10英文与数字组合）
	public static String MOBILEPHONE = "mobilePhone";// 手机号码
	public static String EMAIL = "email";// 邮箱
	public static String SESSIONGUID = "sessionGUID";// 认证码
	public static String CODE = "code";// 验证码
	public static String AMOUNT = "amount";// 金额
	public static String LANG = "lang";// 语言
	public static String GAMENAME = "gameName";// 电子游戏

	public static String DATEFROM = "dateFrom";// 起始時間，yyyy-MM-dd HH:mm:ss
	public static String DATETO = "dateTo";// 結束時間，yyyy-MM-dd HH:mm:ss
	public static String PAGEINDEX = "pageIndex";// 當前頁碼
	public static String PAGESIZE = "pageSize";// 每頁筆數
	public static String WEBFLAG = "webFlag";//系统标示
	
	/** 请求:sessionGUID **/
	public static String REQ_URL_SESSIONGUID = "/sessionguid.ashx";
	/** 请求:建立帐号 **/
	public static String REQ_URL_REGISTER = "/register.ashx";
	/** 请求:查询余额 **/
	public static String REQ_URL_BALANCE = "/balance.ashx";
	/** 请求:解锁 **/
	public static String REQ_URL_RESETLOGINATTEMPTS = "/resetloginattempts.ashx";
	/** 请求:存款 **/
	public static String REQ_URL_DEPOSIT = "/deposit.ashx";
	/** 请求:提款 **/
	public static String REQ_URL_WITHDRAWAL = "/withdrawal.ashx";
	/** 请求:登入真人 **/
	public static String REQ_URL_LOGINLIVE = "/loginlive.ashx";
	/** 请求:登入电子 **/
	public static String REQ_URL_LOGINELECTRONIC = "/loginelectronic.ashx";
	/** 请求:遊戲 記錄 **/
	public static String REQ_URL_BETRECORD = "/betrecord.ashx";

	/** 语言 **/
	public static String LANG_ZH_CN = "zh-CN";
	public static String LANG_ZH_TW = "zh-TW";
	public static String LANG_EN_US = "en-US";

	/** 返回结果 **/
	public static int RES_CODE_0 = 0;// 成功
	public static int RES_CODE_2 = 2;// 参数错误
	public static int RES_CODE_3 = 3;// api帐号错误
	public static int RES_CODE_4 = 4;// 游戏帐号错误
	public static int RES_CODE_5 = 5;// 验证码错误
	public static int RES_CODE_6 = 6;// sessionGUID过期
	public static int RES_CODE_7 = 7;// 帐号已存在
	public static int RES_CODE_9997 = 9997;// 维护

	/** 电子游戏名称 **/
	public static String GAME_NAME_THUNDERSTRUNCK = "Thunderstrunck";
	public static String GAME_NAME_TOMBRAIDER = "TombRaider";
	public static String GAME_NAME_ROULETTE = "Roulette";
	public static String GAME_NAME_OCEANS = "Oceans";
	public static String GAME_NAME_BACCARAT = "Baccarat";
	public static String GAME_NAME_BIGKAHUNA = "BigKahuna";
	public static String GAME_NAME_3CARDPOKER = "3CardPoker";
	public static String GAME_NAME_REDDOG = "RedDog";
	public static String GAME_NAME_SICBO = "SicBo";

}
