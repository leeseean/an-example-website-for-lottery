package com.mh.ifc.http;

/**
 * 检测与创建帐号返回值
 * 
 * @author Administrator
 * 
 */
public class AgConts {
	/** 请求参数名 **/
	public static String CAGENT = "cagent";// api帐号
	public static String LOGINNAME = "loginname";// 会员名
	public static String PASSWORD = "password";// 密码
	public static String ACTYPE = "actype";// 会员类型
	public static String ODDTYPE = "oddtype";// 盘口
	public static String METHOD = "method";// 方法
	public static String CUR = "cur";// 币种
	public static String BILLNO = "billno";// billno = (cagent+序列),
	// 序列是唯一的13~16位数
	public static String TYPE = "type";// 转入或转出 IN\OUT
	public static String CREDIT = "credit";// 转款额度
	public static String FLAG = "flag";// 预备转帐
	public static String DM = "dm";// 网站域名
	public static String SID = "sid";
	public static String LANG = "lang";
	public static String GAMETYPE = "gameType";
	public static String CALLBACK_ID = "id";
	public static String CALLBACK_TYPE = "type";
	public static String CALLBACK_STAMP = "stamp";
	public static String CALLBACK_FEATURE = "feature";
	public static String WEBFLAG = "webFlag";//系统标示

	/** 请求常量 **/
	public static String METHOD_LG = "lg";// 创建帐号
	public static String METHOD_GB = "gb";// 查询余额
	public static String METHOD_TC = "tc";// 预备转帐
	public static String METHOD_TCC = "tcc";// 预备转帐
	public static String METHOD_QOS = "qos";// 查询订单状态

	public static String TYPE_IN = "IN";// 额度转入
	public static String TYPE_OUT = "OUT";// 额度转出

	public static String ACTYPE_0 = "0";// 试玩帐号
	public static String ACTYPE_1 = "1";// 真玩帐号

	public static String FLAG_0 = "0";
	public static String FLAG_1 = "1";

	public static String CUR_RMB = "CNY";// 人民币

	public static String LANG_ZH_CN = "1";
	public static String LANG_ZH_TW = "2";
	public static String LANG_EN_US = "3";

	public static String GAMETYPE_0 = "0";// 大厅
	public static String GAMETYPE_1 = "1";// AGQ 厅
	public static String GAMETYPE_2 = "2";// DSP 厅
	public static String GAMETYPE_3 = "3";// 自选多台
	public static String GAMETYPE_4 = "4";// VIP 厅

	/** 盘口 **/
	public static String ODDTYPE_A = "A";
	public static String ODDTYPE_B = "B";
	public static String ODDTYPE_C = "C";
	public static String ODDTYPE_D = "D";
	public static String ODDTYPE_E = "E";
	public static String ODDTYPE_F = "F";
	public static String ODDTYPE_G = "G";
	public static String ODDTYPE_H = "H";
	public static String ODDTYPE_I = "I";
	public static String ODDTYPE_N = "N";

	/** 请求:params **/
	public static String REQ_URL_PARAMS = "/doBusiness.do?/params=";

	/** 返回结果 **/
	public static String SUCCESS = "0";// 成功
	public static String KEY_ERROR = "key_error";// Key错误
	public static String NETWORK_ERROR = "network_error";// 网络异常
	public static String ACCOUNT_ADD_FAIL = "account_add_fail";// 创建帐号失败（密码不正确或帐号存在）
	public static String ERROR = "error";// 其他错误信息
}
