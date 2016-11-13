package com.mh.ifc.http;

import java.util.HashMap;
import java.util.Map;


public class SbtApiConstants {
	/**盘口**/
	public static final String ODDS_STYLE = "3";
	/**接口成功响应码**/
	public static final String SUCCESS_CODE = "000";
	/**api响应成功**/
	public static final String RESP_SUCCESS_CODE = "0";
	/**响应失败**/
	public static final String RESP_FAILED_CODE = "03";
	/**货币**/
	public static final String CURRENCY_CODE = "RMB";
	/**语言**/
	public static final String LANGUAGE_CODE = "240";
	/**身份识别 false**/
	public static final String TEST_CUST = "false";
	/**国家代码**/
	public static final String COUNTRY = "86";
	/**用户id**/
	public static final String USER_ID = "userName";
	/**交易订单号**/
	public static final String TRANSACTION = "transaction";
	/**金额**/
	public static final String BALANCE = "balance";
	/**出款**/
	public static final String DEBIT = "debit";
	/**存款**/
	public static final String CREDIT = "credit";
	/**平台编码**/
	public static final String WEBFLAG = "webFlag";
	/**注单获取起始时间(已完成注单)**/
	public static final String DATE_FROM = "datefrom";
	/**注单获取结束时间(已完成注单)**/
	public static final String DATE_TO = "dateto";
	/**注单获取起始时间(未完成注单)**/
	public static final String FROM_DATE = "FromDate";
	/**注单获取起始时间(未完成注单)**/
	public static final String ToDate = "ToDate";
	/**用户id**/
	public static final String CUSTOMER_ID = "customerId";
	/**注单获取成功标识**/
	public static final String ERROR_CODE = "NoError";
	/**已完成的注单**/
	public static final int FINSH_BET = 1;
	/**未完成的注单**/
	public static final int NO_FINAL_BET = 0;
	/**普通注单**/
	public static final String SINGLE = "1";
	/**串关注单**/
	public static final String COMBO = "2";
	/**串关主订单标识**/
	public static final String PARENT_BET = "-1"; 
	/**注单状态**/
	public static final Map<String, String> RESULE_MAP = new HashMap<String, String>();
	static {
		RESULE_MAP.put("Opened", "开");
		RESULE_MAP.put("Won", "赢");
		RESULE_MAP.put("Lost", "输");
		RESULE_MAP.put("Draw", "Draw");
		RESULE_MAP.put("Canceled", "取消");
	}
}
