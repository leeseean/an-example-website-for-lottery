/**   
* 文件名称: ResourceConstant.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-5-29 下午4:24:54<br/>
*/  
package com.mh.commons.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mh.entity.TMatchBkLimit;
import com.mh.entity.TMatchFtLimit;
import com.mh.entity.TWebFlatInfo;
import com.mh.entity.WebGdElectronic;
import com.mh.entity.WebMgElectronic;
import com.mh.entity.WebNewNtElectronic;
import com.mh.entity.WebNtElectronic;
import com.mh.entity.WebOsElectronic;
import com.mh.entity.WebPtElectronic;
import com.mh.entity.WebTtgElectronic;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-5-29 下午4:24:54<br/>
 */
public class ResourceConstant {

 
	
	public static List<WebMgElectronic> MOBILE_MG_ELECTRONIC_LIST;
	public static List<WebPtElectronic> MOBILE_PT_ELECTRONIC_LIST;
	public static List<WebOsElectronic> MOBILE_OS_ELECTRONIC_LIST;
	public static List<WebTtgElectronic> MOBILE_TTG_ELECTRONIC_LIST;
	public static Map<String,List<WebMgElectronic>> MG_ELECTRONIC_LIST;
	
	public static Map<String,List<WebTtgElectronic>> TTG_ELECTRONIC_LIST;
	
	public static Map<String,List<WebPtElectronic>> PT_ELECTRONIC_LIST;
	
	public static List<WebNtElectronic> NT_ELECTRONIC_LIST;
	
	public static Map<String,List<WebGdElectronic>> GD_ELECTRONIC_LIST;
	
	public static Map<String,List<WebNewNtElectronic>> NEWNT_ELECTRONIC_LIST;
	
	public static Map<String, List<WebOsElectronic>> OS_ELECTRONIC_LIST;
	
	public static Map<String, String> ntElectronic = new HashMap<String, String>();
	public static Map<String, String> ptElectronic = new HashMap<String, String>();
	
	public static List<TWebFlatInfo> liveSite = new ArrayList<TWebFlatInfo>();
	public static List<TWebFlatInfo> slotSite = new ArrayList<TWebFlatInfo>();
	public static List<TWebFlatInfo> sportSite = new ArrayList<TWebFlatInfo>();
	public static List<TWebFlatInfo> cpSite = new ArrayList<TWebFlatInfo>();
	
	
	public static TMatchFtLimit matchFtLimit;//足球限额
	
	public static TMatchBkLimit matchBkLimit;//篮球限额
	
	//**********游戏类型代码***************/
	//六合彩
	public static final String HK6_GAME_CODE="HK6";
	//福彩3D
	public static final String FC3D_GAME_CODE="FC3D";
	//排列3
	public static final String PL3_GAME_CODE="PL3";
	//重庆时时彩
	public static final String CQSSC_GAME_CODE="CQSSC";
	//江西时时彩
	public static final String JXSSC_GAME_CODE="JXSSC";
	//天津时时彩
	public static final String	TJSSC_GAME_CODE="TJSSC";
	//新疆时时彩
	public static final String XJSSC_GAME_CODE="XJSSC";
	//天津快乐十分
	public static final String TJKLSF_GAME_CODE="TJKLSF";
	//广东快乐十分
	public static final String GDKLSF_GAME_CODE="GDKLSF";
	//北京pk拾
	public static final String BJPK10_GAME_CODE="BJPK10";
	//北京快乐8
	public static final String BJKL8_GAME_CODE="BJKL8";
	//加拿大28
	public static final String CAKENO_GAME_CODE="CAKENO";
	
	//**********************************六合彩类型代码**********************************/
	public static final String HK6_TYPE_TM_CODE="TM";//特码
	public static final String HK6_TYPE_ZMT_CODE="ZMT";//正码特
	public static final String HK6_TYPE_ZM_CODE="ZM";//正码
	public static final String HK6_TYPE_ZM16_CODE="ZM1-6";//正码1-6
	public static final String HK6_TYPE_GG_CODE="GG";//过关
	public static final String HK6_TYPE_LM_CODE="LM";//连码
	public static final String HK6_TYPE_BB_CODE="BB";//半波
	public static final String HK6_TYPE_YXYZTW_CODE="YXYZTW";//一肖&正特尾
	public static final String HK6_TYPE_TXTW_CODE="TXTW";//特肖头尾
	public static final String HK6_TYPE_ZYBZ_CODE="ZYBZ";//中&不中
	public static final String HK6_TYPE_WSL_CODE="WSL";//尾数连
	public static final String HK6_TYPE_LX_CODE="LX";//连肖
	public static final String HK6_TYPE_HX_CODE="HX";//合肖
	
	//彩票类别代码
	//正特码
	public static final String HK6_CATE_Z1T_CODE= "Z1T";//正1特
	public static final String HK6_CATE_Z2T_CODE= "Z2T";//正2特
	public static final String HK6_CATE_Z3T_CODE= "Z3T";//正3特
	public static final String HK6_CATE_Z4T_CODE= "Z4T";//正4特
	public static final String HK6_CATE_Z5T_CODE= "Z5T";//正5特
	public static final String HK6_CATE_Z6T_CODE= "Z6T";//正6特
	//正码1-6
	public static final String HK6_CATE_ZM1_CODE="ZM1";//正码1
	public static final String HK6_CATE_ZM2_CODE="ZM2";//正码2
	public static final String HK6_CATE_ZM3_CODE="ZM3";//正码3
	public static final String HK6_CATE_ZM4_CODE="ZM4";//正码4
	public static final String HK6_CATE_ZM5_CODE="ZM5";//正码5
	public static final String HK6_CATE_ZM6_CODE="ZM6";//正码6
	
	//连码
	public static final String HK6_CATE_SQZ_CODE = "SQZ";//三全中
	public static final String HK6_CATE_SZE_CODE = "SZE";//三中二
	public static final String HK6_CATE_EQZ_CODE = "EQZ";//二全中
	public static final String HK6_CATE_EZT_CODE = "EZT";//二中特
	public static final String HK6_CATE_TC_CODE = "TC";//特串
	
	//半波
	public static final String HK6_CATE_BB_CODE= "BB";//半波
	public static final String HK6_CATE_BBB_CODE= "BBB";//半半波
	
	//一肖&正特尾
	public static final String HK6_CATE_ZTWXZ_CODE= "ZTWXZ";//正特尾下注
	public static final String HK6_CATE_YXXZ_CODE= "YXXZ";//一肖下注
	
	//中&不中
	public static final String HK6_CATE_WBZ_CODE= "WBZ";//五不中
	public static final String HK6_CATE_QBZ_CODE= "QBZ";//七不中
	public static final String HK6_CATE_JBZ_CODE= "JBZ";//九不中
	public static final String HK6_CATE_SZY_CODE= "SZY";//四中一
	public static final String HK6_CATE_LZY_CODE= "LZY";//六中一
	
	//**********************************福彩3D类型代码**********************************/
	public static final String FC3D_TYPE_ZPS_CODE="ZPS";//主盘式
	public static final String FC3D_TYPE_YZ_CODE="YZ";//一字
	public static final String FC3D_TYPE_EZ_CODE="EZ";//二字
	public static final String FC3D_TYPE_SZ_CODE="SZ";//三字
	public static final String FC3D_TYPE_YZGG_CODE="YZGG";//一字过关
	public static final String FC3D_TYPE_KD_CODE="KD";//跨度
	public static final String FC3D_TYPE_ZXS_CODE="ZXS";//组选三
	public static final String FC3D_TYPE_ZXL_CODE="ZXL";//组选六
	public static final String FC3D_TYPE_FSZH_CODE="FSZH";//复式组合
	public static final String FC3D_TYPE_QT_CODE="QT";//其他
	
	//**********************************快乐十分类型代码**********************************/
	public static final String KLSF_TYPE_D1Q_CODE = "D1Q";//第一球
	public static final String KLSF_TYPE_D2Q_CODE = "D2Q";//第二球
	public static final String KLSF_TYPE_D3Q_CODE = "D3Q";//第三球
	public static final String KLSF_TYPE_D4Q_CODE = "D4Q";//第四球
	public static final String KLSF_TYPE_D5Q_CODE = "D5Q";//第五球
	public static final String KLSF_TYPE_D6Q_CODE = "D6Q";//第六球
	public static final String KLSF_TYPE_D7Q_CODE = "D7Q";//第七球
	public static final String KLSF_TYPE_D8Q_CODE = "D8Q";//第八球
	public static final String KLSF_TYPE_ZHLH_CODE = "ZHLH";//总和&龙虎
	public static final String KLSF_TYPE_LM_CODE = "LM";//连码
	
	//幸运28
	public static final String KL8="KL8";
	//时时彩大分类
	public static final String SSC = "SSC";
	//福体彩大分类
	public static final String FTC = "FTC";
	//快乐十分大分类
	public static final String KLSF = "KLSF";
	//PK10大分类
	public static final String PK10 = "PK10";
	public static List<String> SSC_TYPE = new ArrayList<String>();
	public static List<String> FTC_TYPE = new ArrayList<String>();
	public static List<String> KLSF_TYPE = new ArrayList<String>();
	public static List<String> PK10_TYPE = new ArrayList<String>();
	public static List<String> BJKL8_TYPE = new ArrayList<String>();
	
	static{
		SSC_TYPE.add(CQSSC_GAME_CODE);
		SSC_TYPE.add(JXSSC_GAME_CODE);
		SSC_TYPE.add(TJSSC_GAME_CODE);
		SSC_TYPE.add(XJSSC_GAME_CODE);
		
		FTC_TYPE.add(FC3D_GAME_CODE);
		FTC_TYPE.add(PL3_GAME_CODE);
		
		KLSF_TYPE.add(TJKLSF_GAME_CODE);
		KLSF_TYPE.add(GDKLSF_GAME_CODE);
		
		PK10_TYPE.add(BJPK10_GAME_CODE);
		
		BJKL8_TYPE.add(BJKL8_GAME_CODE);
		BJKL8_TYPE.add(CAKENO_GAME_CODE);
	}
}
