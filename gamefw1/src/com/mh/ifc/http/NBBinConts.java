/**   
* 文件名称: NBBinConts.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2016-1-19 下午6:48:19<br/>
*/  
package com.mh.ifc.http;

import com.mh.commons.conf.CommonConstant;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2016-1-19 下午6:48:19<br/>
 */
public class NBBinConts {
	public static String WEBSITE = "website";
	public static String USERNAME = "username";
	public static String UPPERNAME = "uppername";
	public static String PASSWORD = "password";
	
	public static String REMITNO = "remitno";//订单号
	public static String REMIT = "remit";
	public static String ACTION = "action";
	public static String DEPOSIT = "IN";// 额度转入
	public static String WITHDRAW = "OUT";// 额度转出
	
	public static String LANG = "lang";
	public static String PAGESITE = "page_site";
	public static String ZH_CN = "zh-cn";
	public static String EN_US = "en-us";
	public static String WEBFLAG = "webFlag";//系统标示
	public static String OP = "Op";
	public static String AUTH = "auth";
	public static String FRDATE = "frDate";
	public static String TODATE = "toDate";
	
	public static String opPrefix=CommonConstant.resCommMap.get("NBBIN_USRNAME_PREFIX");
	
	public static String CREATMEMBER_SUCCESS = "21100";
	public static String LOGINMEMBER_SUCCESS = "99999";
	public static String LOGINOUT_SUCCESS = "22001";
	public static String TRANSFER_SUCCESS = "11100";
}
