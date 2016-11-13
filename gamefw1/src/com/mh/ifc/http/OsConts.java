/**   
* 文件名称: AbConts.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-11-21 下午7:08:12<br/>
*/  
package com.mh.ifc.http;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-11-21 下午7:08:12<br/>
 */
public class OsConts {
	
	public static String AGENT = "agent";//代理商用户名
	public static String USERNAME = "username";//客户名称
	public static String PASSWORD = "password";//客户密码
	
	public static String BILLNO = "billno";//交易流水号
	public static String OPERTYPE = "type";//转账类型
	public static String WEBFLAG = "webFlag";// 系统标示
	public static String AMOUNT = "amount";// 转款额度
	public static String DOMAIN = "domain";
	public static String GAMETYPE = "gametype";
	public static String IPADRESS = "IPAddress";
	
	//游戏记录
	public static String STARTTIME = "startTime";
	public static String ENDTIME = "endTime";
	public static String VENDORID = "vendorid";
	
	public static String PLAYMODEL = "playmode";
	public static String MODEL_FUN = "fun";
	public static String MODEL_REAL = "real";
	public static String LOGIN_URL = "loginUrl";
	public static String BACK_URL = "backUrl";
	public static String ERROR_URL = "errorUrl";
	
	
	public static String GAMECODE = "game_code";
	public static String WEBSITE = "website";
	
	/** 语言 **/
	public static String LANG_ZH_CN = "zh";
	public static String LANG_EN_EN = "en";
	
	public static String CUR_RMB = "RMB";// 人民币
	public static String CUR_USD = "USD";//美元
	
	public static String OPER_TYPE_IN = "IN";// 额度转入
	public static String OPER_TYPE_OUT = "OUT";// 额度转出
	
	public static int HANDICAP_GENERAL = 0;//普通盘口
	public static int HANDICAP_VIP = 1;//VIP 盘口
	
	public static String TRANSFER_STATE_INIT = "0";
	public static String TRANSFER_STATE_SUCCESS = "1";
	public static String TRANSFER_STATE_FAILURE = "2";
	
	public static String RES_OK = "0";
	
	public static Map<BigInteger, String> errorCodeMap = new HashMap<BigInteger, String>();
	static{
		//errorCodeMap.put(0, "客户已注册");
		errorCodeMap.put(BigInteger.valueOf(0), "没有错误");
		errorCodeMap.put(BigInteger.valueOf(1), "客户已注册");
		errorCodeMap.put(BigInteger.valueOf(2), "客户未注册");
		errorCodeMap.put(BigInteger.valueOf(-1), "内部服务器错误");
		errorCodeMap.put(BigInteger.valueOf(-5), "未找到商家");
		errorCodeMap.put(BigInteger.valueOf(-7), "客户已被封");
		errorCodeMap.put(BigInteger.valueOf(-8), "币种未注册");
		errorCodeMap.put(BigInteger.valueOf(-9), "语种未注册");
		errorCodeMap.put(BigInteger.valueOf(-223), "不支持商家集成类型");
		errorCodeMap.put(BigInteger.valueOf(-224), "该集成类型不允许此服务");
		errorCodeMap.put(BigInteger.valueOf(-225), "商家授权失败");
		
	}
	
}
