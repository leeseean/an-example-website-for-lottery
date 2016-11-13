package com.mh.ifc.http;

/**
 * 检测与创建帐号返回值
 * 
 * @author Administrator
 * 
 */
public class KgConts {
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
	public static String TRANSSN = "transSN";// 交易編號(唯一)，10 ~ 19 個數字
	public static String PAGESITE = "pageSite";// 游戏类型
	public static String CLIENTIP = "ip";// 客户端登录IP
	public static String SESSIONID = "sessionID";//

	public static String ROUNDDATE = "roundDate";// 日期，yyyy-MM-dd
	public static String STARTDATE = "startDate";// 開始時間，yyyy-MM-ddTHH:mm:ss
	public static String ENDDATE = "endDate";// 開始時間，yyyy-MM-ddTHH:mm:ss
	public static String GAMEKIND = "gameKind";// 遊戲種類 (1:BB 體育 3:視訊 5:機率 12:彩票
	// 15:3D廳)
	public static String GAMETYPE = "gameType";// 遊戲代碼，註 2 (gameKind=12 時，須強制帶入)
	public static String PAGEINDEX = "pageIndex";// 當前頁碼
	public static String PAGESIZE = "pageSize";// 每頁筆數

	/** 请求:建立帐号 **/
	public static String REQ_URL_REGISTER = "/register.ashx";
	/** 请求:查询余额 **/
	public static String REQ_URL_BALANCE = "/balance.ashx";
	/** 请求:存款 **/
	public static String REQ_URL_DEPOSIT = "/deposit.ashx";
	/** 请求:提款 **/
	public static String REQ_URL_WITHDRAWAL = "/withdrawal.ashx";
	/** 请求:登入 **/
	public static String REQ_URL_LOGIN = "/login.ashx";
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
	public static int RES_CODE_8 = 8;// 交易编号重复
	public static int RES_CODE_9997 = 9997;// 维护

}
