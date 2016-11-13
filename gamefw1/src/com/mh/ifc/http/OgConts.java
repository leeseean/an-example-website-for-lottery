/**   
* 文件名称: AbConts.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-11-21 下午7:08:12<br/>
*/  
package com.mh.ifc.http;

import java.util.HashMap;
import java.util.Map;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-11-21 下午7:08:12<br/>
 */
public class OgConts {
	
	public static String AGENT = "agent";//代理商用户名
	public static String USERNAME = "username";//客户名称
	public static String PASSWORD = "password";//客户密码
	
	public static String BILLNO = "billno";//交易流水号
	public static String CREDIT = "credit";// 转款额度
	public static String OPERTYPE = "type";//转账类型
	public static String WEBFLAG = "webFlag";// 系统标示
	
	public static String DOMAIN = "domain";
	public static String GAMETYPE = "gametype";
	
	//游戏记录
	public static String STARTTIME = "startTime";
	public static String ENDTIME = "endTime";
	public static String VENDORID = "vendorid";
	
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
	
	public static String RES_OK = "1";
	public static String RES_FAILURE = "0";
	public static String RES_AGENTNOEXIST = "10";
	
	/** 请求:注册 **/
	public static String METHOD_CACA = "caca";
	/** 查询余额**/
	public static String METHOD_GB = "gb";
	/** 转账(TransferCredit) **/
	public static String METHOD_PTC = "ptc";
	/** 查询转账状态(TransferCredit) **/
	public static String METHOD_CTC = "ctc";
	/** 请求:登入 **/
	public static String METHOD_TG = "tg";
	/** 请求:登出**/
	public static String METHOD_LG = "lg";
	/** 请求:遊戲 記錄 **/
	public static String REQ_URL_GBRBV = "gbrbv";
	
	/** 查询 转帐状态 **/
	public static String REQ_URL_TRANSFERSTATE = "/query_transfer_state";
	
	public static Map<Integer, String> gameType = new HashMap<Integer, String>();
	public static Map<Integer, String> betType = new HashMap<Integer, String>();
	
	public static Map<String, String> resultMap = new HashMap<String, String>();
	
	static{
		resultMap.put("key_error", "The key value is error.");
		resultMap.put("Network_error", "Network has some problem,lost information.");
		resultMap.put("Account_no_exist", "The username which you want to querycredit is not exist.");
		resultMap.put("account_no_exist", "The username which you want to querycredit is not exist.");
		resultMap.put("no_enough_credit", "Not enough money to withdraw.");
		resultMap.put("", "");
		resultMap.put("1", "successful.");
		resultMap.put("0", "Failure Maybe username already exist .");
		resultMap.put("2", "Password is invalid or does not match in the system.");
		resultMap.put("3", "Username is too long.");
		resultMap.put("10", "The agent not exist.");
		
		gameType.put(101, "普通百家乐");
		gameType.put(102, "VIP 百家乐");
		gameType.put(103, "急速百家乐");
		gameType.put(104, "竞咪百家乐");
		gameType.put(201, "骰宝");
		gameType.put(301, "龙虎");
		gameType.put(401, "轮盘");
		
		
		betType.put(1001, "庄");betType.put(1002, "闲");betType.put(1003, "和");betType.put(1004, "大");
		betType.put(1005, "小");betType.put(1006, "庄对");betType.put(1007, "闲对");
		
		betType.put(2001, "龙");betType.put(2002, "虎");betType.put(2003, "和");
		//骰宝
		betType.put(3001, "小");betType.put(3002, "单");betType.put(3003, "双");betType.put(3004, "大");
		betType.put(3005, "围一");betType.put(3006, "围二");betType.put(3007, "围三");betType.put(3008, "围四");
		betType.put(3009, "围五");betType.put(3010, "围六");betType.put(3011, "全围");
		betType.put(3012, "对1");betType.put(3013, "对2");betType.put(3014, "对3");betType.put(3015, "对4");
		betType.put(3016, "对5");betType.put(3017, "对6");
		betType.put(3018, "和值:4");betType.put(3019, "和值:5");betType.put(3020, "和值:6");betType.put(3021, "和值:7");
		betType.put(3022, "和值:8");betType.put(3023, "和值:9");betType.put(3024, "和值:10");betType.put(3025, "和值:11");
		betType.put(3026, "和值:12");betType.put(3027, "和值:13");betType.put(3028, "和值:14");betType.put(3029, "和值:15");
		betType.put(3030, "和值:16");betType.put(3031, "和值:17");betType.put(3032, "");
		betType.put(3033, "牌九式:12");betType.put(3034, "牌九式:13");betType.put(3035, "牌九式:14");betType.put(3036, "牌九式:15");betType.put(3037, "牌九式:16");
		betType.put(3038, "牌九式:23");betType.put(3039, "牌九式:24");betType.put(3040, "牌九式:25");betType.put(3041, "牌九式:26");
		betType.put(3042, "牌九式:34");betType.put(3043, "牌九式:35");betType.put(3044, "牌九式:36"); 
		betType.put(3045, "牌九式:45");betType.put(3046, "牌九式:46");betType.put(3047, "牌九式:56");
		betType.put(3048, "单骰:1");betType.put(3049, "单骰:2");betType.put(3050, "单骰:3");betType.put(3051, "单骰:4");betType.put(3052, "单骰:5");betType.put(3053, "单骰:6");
		//轮盘
		betType.put(4001, "小");betType.put(4002, "双");betType.put(4003, "红");betType.put(4004, "黑");betType.put(4005, "单");betType.put(4006, "大");
		betType.put(4007, "第一打");betType.put(4008, "第二打");betType.put(4009, "第三打");
		betType.put(4010, "第一列");betType.put(4011, "第二列");betType.put(4012, "第三列");
		
		for(int i=0; i<=36;i++){
			betType.put(4013+i, "直接注:(?)".replace("?", i+"") );
		}
		
		betType.put(4050, "三数:(0/1/2)");betType.put(4051, "三数:(0/2/3) ");betType.put(4052, "四数:(0/1/2/3)");
		
		for(int i=0; i<=36;i++){
			
		}
		//分注
		fenzhu(betType, 4053);
		//角注 街注 线注
		JiaoAndJieAndXianZhu(betType, 4113);
		betType.put(4155, "线注:(28~33)");
		betType.put(4156, "线注:(31~36)");
		betType.put(4157, "线注:(25~30)");
	}
	
	public static void fenzhu(Map<Integer, String> betType, int _index){
		int j=0,m=1,n=1, o=2,p=3;
		for(int i=1,index=_index; i<=60; i++, index++){
			if(j==0&&i<=3){
				String str = "分注:(" +j+"/"+i+")";
				betType.put(index, str);
				continue;
			}
			j=1;
			if(m<36){
				String str = "";
				if(i!=4&&(i-2)%2==0){
					str = "分注:(" +(++m)+"/"+(++m)+")";
				}else{
					str = "分注:(" +m+"/"+(++m)+")";
				}
				
				betType.put(index, str);
				continue;
			}
			if(n<32){
				String str = "分注:(" +n+"/";
				n+=3;
				str += n+")";
				betType.put(index, str);
				continue;
			}
			if(o<35){
				String str = "(" +o+"/";
				o+=3;
				str += o+")";
				betType.put(index, str);
				continue;
			}
			if(p<36){
				String str = "分注:(" +p+"/";
				p+=3;
				str += p+")";
				betType.put(index, str);
				continue;
			}
			
		}
		
	}
	
	public static void JiaoAndJieAndXianZhu(Map<Integer, String> betType, int _index){
		int index = _index;
		for(int i=1; i<=32;i++){
			if(i%3==0){
				continue;
			}
			String str = "角注:";
			if(i==19){
				str += "(" +(i-1)+"/";
			}else{
				str += "(" +i+"/";
			}
			
			str += i+4+")";
			betType.put(index++, str);
		}
		 
		int m =0;
		for(int i=1; i<=18;i++){
			if(i%3==0){
				continue;
			}
			String str = "街注:";
			if(m!=9){
				m+=1;
			}
			str += "(" +m+"~";
			if(m!=9){
				m+=2;
			}else{
				m+=3;
			}
			str += m+")";
			betType.put(index++, str);
		}
		
		for(int i=1; i<=22;i+=3){
			String str = "线注:";
			str += "("+ i +"~";
			str += i+5+")";
			betType.put(index++, str);
		}
	}

}
