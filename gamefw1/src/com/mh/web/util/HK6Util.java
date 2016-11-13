/**   
* 文件名称: HK6Util.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-5-31 下午2:34:12<br/>
*/  
package com.mh.web.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.utils.AnimalUtil;
import com.mh.entity.CpHk6Results;

/** 
 * 类描述: TODO<br/>六合彩结算工具类
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-5-31 下午2:34:12<br/>
 */
public class HK6Util {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	//红波
	public static String[] redArr = new String[]{"01","02","07","08","12","13","18","19","23","24","29","30","34","35","40","45","46"};
	//蓝波
	public static String[] blueArr= new String[]{"03","04","09","10","14","15","20","25","26","31","36","37","41","42","47","48"};
	//绿波
	public static String[] greenArr = new String[]{"05","06","11","16","17","21","22","27","28","32","33","38","39","43","44","49"};
	//合单
	public static String[] hesingleArr= new String[]{"01","03","05","07","09","10","12","14","16","18","21","23","25","27","29","30","32","34","36","38","41","43","45","47"};
	//合双
	public static String[] hedoubleArr= new String[]{"02","04","06","08","11","13","15","17","19","20","22","24","26","28","31","33","35","37","39","40","42","44","46","48"};
	//大
	public static String[] bigArr= new String[]{"25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48"};
	//小
	public static String[] smallArr= new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
	//单
	public static String[] singleArr= new String[]{"01","03","05","07","09","11","13","15","17","19","21","23","25","27","29","31","33","35","37","39","41","43","45","47"};
	//双
	public static String[] doubleArr= new String[]{"02","04","06","08","10","12","14","16","18","20","22","24","26","28","30","32","34","36","38","40","42","44","46","48"};
	//合大
	public static String[] hebigArr= new String[]{"07","08","09","16","17","18","19","25","26","27","28","29","34","35","36","37","38","39","43","44","45","46","47","48"};
	//合小
	public static String[] hesmallArr= new String[]{"01","02","03","04","05","06","10","11","12","13","14","15","20","21","22","23","24","30","31","32","33","40","41","42"};

	
	//红单
	public static String[] redSingleArr = new String[]{"01","07","13","19","23","29","35","45"};
	//红双
	public static String[] redDoubleArr = new String[]{"02","08","12","18","24","30","34","40","46"};
	//红大
	public static String[] redBigArr = new String[]{"29","30","34","35","40","45","46"};
	//红小
	public static String[] redSmallArr = new String[]{"01","02","07","08","12","13","18","19","23","24"};
	//蓝单
	public static String[] blueSingleArr = new String[]{"03","09","15","25","31","37","41","47"};
	//蓝双
	public static String[] blueDoubleArr = new String[]{"04","10","14","20","26","36","42","48"};
	//蓝大
	public static String[] blueBigArr = new String[]{"25","26","31","36","37","41","42","47","48"};
	//蓝小
	public static String[] blueSmallArr = new String[]{"03","04","09","10","14","15","20"};
	//绿单
	public static String[] greenSingleArr = new String[]{"05","11","17","21","27","33","39","43","49"};
	//绿双
	public static String[] greenDoubleArr = new String[]{"06","16","22","28","32","38","44"};
	//绿大
	public static String[] greenBigArr = new String[]{"27","28","32","33","38","39","43","44","49"};
	//绿小
	public static String[] greenSmallArr = new String[]{"05","06","11","16","17","21","22"};
	//0头
	public static String[] head0Arr = new String[]{"01","02","03","04","05","06","07","08","09"};
	//1头
	public static String[] head1Arr = new String[]{"10","11","12","13","14","15","16","17","18","19"};
	//2头
	public static String[] head2Arr = new String[]{"20","21","22","23","24","25","26","27","28","29"};
	//3头
	public static String[] head3Arr = new String[]{"30","31","32","33","34","35","36","37","38","39"};
	//4头
	public static String[] head4Arr = new String[]{"40","41","42","43","44","45","46","47","48","49"};
	//0尾
	public static String[] tail0Arr = new String[]{"10","20","30","40"};
	//1尾
	public static String[] tail1Arr = new String[]{"01","11","21","31","41"};
	//2尾
	public static String[] tail2Arr = new String[]{"02","12","22","32","42"};
	//3尾
	public static String[] tail3Arr = new String[]{"03","13","23","33","43"};
	//4尾
	public static String[] tail4Arr = new String[]{"04","14","24","34","44"};
	//5尾
	public static String[] tail5Arr = new String[]{"05","15","25","35","45"};
	//6尾
	public static String[] tail6Arr = new String[]{"06","16","26","36","46"};
	//7尾
	public static String[] tail7Arr = new String[]{"07","17","27","37","47"};
	//8尾
	public static String[] tail8Arr = new String[]{"08","18","28","38","48"};
	//9尾
	public static String[] tail9Arr = new String[]{"09","19","29","39","49"};
	
	//尾大
	public static String[] tailBigArr = new String[]{"05","06","07","08","09","15","16","17","18","19","25","26","27","28","29","35","36","37","38","39","45","46","47","48"};
	//尾小
	public static String[] tailSmallArr = new String[]{"01","02","03","04","10","11","12","13","14","20","21","22","23","24","30","31","32","33","34","40","41","42","43","44"};
	
	
	//大单
	public static String[] bigSingleArr = new String[]{"25","27","29","31","33","35","37","39","41","43","45","47"};
	//大双
	public static String[] bigDoubleArr = new String[]{"26","28","30","32","34","36","38","40","42","44","46","48"};
	//小单
	public static String[] smallSingleArr = new String[]{"01","03","05","07","09","11","13","15","17","19","21","23"};
	//小双
	public static String[] smallDoubleArr = new String[]{"02","04","06","08","10","12","14","16","18","20","22","24"};
	//红单大
	public static String[] redSingleBigArr = new String[]{"29","35","45"};
	//红单小
	public static String[] redSingleSmallArr = new String[]{"01","07","13","19","23"};
	//红双大
	public static String[] redDoubleBigArr = new String[]{"30","34","40","46"};
	//红双小
	public static String[] redDoubleSmallArr = new String[]{"02","08","12","18","24"};
	//蓝单大
	public static String[] blueSingleBigArr = new String[]{"25","31","37","41","47"};
	//蓝单小
	public static String[] blueSingleSmallArr = new String[]{"03","09","15"};
	//蓝双大
	public static String[] blueDoubleBigArr = new String[]{"26","36","42","48"};
	//蓝双小
	public static String[] blueDoubleSmallArr = new String[]{"04","10","14","20"};
	//绿单大
	public static String[] greenSingleBigArr = new String[]{"27","33","39","43","49"};
	//绿单小
	public static String[] greenSingleSmallArr = new String[]{"05","11","17","21"};
	//绿双大
	public static String[] greenDoubleBigArr = new String[]{"28","32","38","44"};
	//绿双小
	public static String[] greenDoubleSmallArr = new String[]{"06","16","22"};
	
	//鼠
	public static List<String> ratList = AnimalUtil.getCurrAnimalCodeList().get("鼠");
	//牛
	public static List<String> cattleList = AnimalUtil.getCurrAnimalCodeList().get("牛");
	//虎
	public static List<String> tigerList = AnimalUtil.getCurrAnimalCodeList().get("虎");
	//兔
	public static List<String> rabbitList = AnimalUtil.getCurrAnimalCodeList().get("兔");
	//龙
	public static List<String> loongList = AnimalUtil.getCurrAnimalCodeList().get("龙");
	
	//蛇
	public static List<String> snakeList = AnimalUtil.getCurrAnimalCodeList().get("蛇");
	//马
	public static List<String> horseList = AnimalUtil.getCurrAnimalCodeList().get("马");
	//羊
	public static List<String> sheepList = AnimalUtil.getCurrAnimalCodeList().get("羊");
	//猴
	public static List<String> monkeyList = AnimalUtil.getCurrAnimalCodeList().get("猴");
	//鸡
	public static List<String> chickenList = AnimalUtil.getCurrAnimalCodeList().get("鸡");
	//狗
	public static List<String> dogList = AnimalUtil.getCurrAnimalCodeList().get("狗");
	//猪
	public static List<String> pigList = AnimalUtil.getCurrAnimalCodeList().get("猪");
		
	//家禽
	public static List<String> fowlList = new ArrayList<String>();
	//野兽
	public static List<String> beastList =new ArrayList<String>();
	
	
	public static Map<String,List<String>> hk6RuleMap = new HashMap<String,List<String>>();
	
	public static Map<String,String> colorMap = new HashMap<String,String>();
	
	public static Map<String,String> colorClassNameMap = new HashMap<String,String>();
	
	public static Map<String, String> lxCode = new HashMap<String, String>();
	
	public static List<String> tempBefClear = new ArrayList<String>();
	
	static{
		
		colorMap.put("01", "红波");
		colorMap.put("02", "红波");
		colorMap.put("03", "蓝波");
		colorMap.put("04", "蓝波");
		colorMap.put("05", "绿波");
		colorMap.put("06", "绿波");
		colorMap.put("07", "红波");
		colorMap.put("08", "红波");
		colorMap.put("09", "蓝波");
		colorMap.put("10", "蓝波");
		colorMap.put("11", "绿波");
		colorMap.put("12", "红波");
		colorMap.put("13", "红波");
		colorMap.put("14", "蓝波");
		colorMap.put("15", "蓝波");
		colorMap.put("16", "绿波");
		colorMap.put("17", "绿波");
		colorMap.put("18", "红波");
		colorMap.put("19", "红波");
		colorMap.put("20", "蓝波");	
		colorMap.put("21", "绿波");
		colorMap.put("22", "绿波");
		colorMap.put("23", "红波");
		colorMap.put("24", "红波");	
		colorMap.put("25", "蓝波");	
		colorMap.put("26", "蓝波");	
		colorMap.put("27", "绿波");	
		colorMap.put("28", "绿波");	
		colorMap.put("29", "红波");
		colorMap.put("30", "红波");
		colorMap.put("31", "蓝波");
		colorMap.put("32", "绿波");
		colorMap.put("33", "绿波");
		colorMap.put("34", "红波");
		colorMap.put("35", "红波");
		colorMap.put("36", "蓝波");
		colorMap.put("37", "蓝波");
		colorMap.put("38", "绿波");
		colorMap.put("39", "绿波");
		colorMap.put("40", "红波");
		colorMap.put("41", "蓝波");
		colorMap.put("42", "蓝波");
		colorMap.put("43", "绿波");
		colorMap.put("44", "绿波");
		colorMap.put("45", "红波");
		colorMap.put("46", "红波");
		colorMap.put("47", "蓝波");
		colorMap.put("48", "蓝波");
		colorMap.put("49", "绿波");
		
		
		colorClassNameMap.put("01", "r");
		colorClassNameMap.put("02", "r");
		colorClassNameMap.put("03", "b");
		colorClassNameMap.put("04", "b");
		colorClassNameMap.put("05", "g");
		colorClassNameMap.put("06", "g");
		colorClassNameMap.put("07", "r");
		colorClassNameMap.put("08", "r");
		colorClassNameMap.put("09", "b");
		colorClassNameMap.put("10", "b");
		colorClassNameMap.put("11", "g");
		colorClassNameMap.put("12", "r");
		colorClassNameMap.put("13", "r");
		colorClassNameMap.put("14", "b");
		colorClassNameMap.put("15", "b");
		colorClassNameMap.put("16", "g");
		colorClassNameMap.put("17", "g");
		colorClassNameMap.put("18", "r");
		colorClassNameMap.put("19", "r");
		colorClassNameMap.put("20", "b");	
		colorClassNameMap.put("21", "g");
		colorClassNameMap.put("22", "g");
		colorClassNameMap.put("23", "r");
		colorClassNameMap.put("24", "r");	
		colorClassNameMap.put("25", "b");	
		colorClassNameMap.put("26", "b");	
		colorClassNameMap.put("27", "g");	
		colorClassNameMap.put("28", "g");	
		colorClassNameMap.put("29", "r");
		colorClassNameMap.put("30", "r");
		colorClassNameMap.put("31", "b");
		colorClassNameMap.put("32", "g");
		colorClassNameMap.put("33", "g");
		colorClassNameMap.put("34", "r");
		colorClassNameMap.put("35", "r");
		colorClassNameMap.put("36", "b");
		colorClassNameMap.put("37", "b");
		colorClassNameMap.put("38", "g");
		colorClassNameMap.put("39", "g");
		colorClassNameMap.put("40", "r");
		colorClassNameMap.put("41", "b");
		colorClassNameMap.put("42", "b");
		colorClassNameMap.put("43", "g");
		colorClassNameMap.put("44", "g");
		colorClassNameMap.put("45", "r");
		colorClassNameMap.put("46", "r");
		colorClassNameMap.put("47", "b");
		colorClassNameMap.put("48", "b");
		colorClassNameMap.put("49", "g");
		
		//家禽 牛+马+羊+鸡+狗+猪
		fowlList.addAll(cattleList);
		fowlList.addAll(horseList);
		fowlList.addAll(sheepList);
		fowlList.addAll(chickenList);
		fowlList.addAll(dogList);
		fowlList.addAll(pigList);
		hk6RuleMap.put("家禽",fowlList);
		//野兽 鼠+虎+兔+龙+蛇+猴
	
		
		beastList.addAll(ratList);
		beastList.addAll(tigerList);
		beastList.addAll(rabbitList);
		beastList.addAll(loongList);
		beastList.addAll(snakeList);
		beastList.addAll(monkeyList);
		hk6RuleMap.put("野兽",beastList);
		
		
		//鼠
		hk6RuleMap.put("鼠",ratList);
		//牛
		hk6RuleMap.put("牛", cattleList);
		//虎
		hk6RuleMap.put("虎",tigerList);
		//兔
		hk6RuleMap.put("兔",rabbitList);
		//龙
		hk6RuleMap.put("龙",loongList);
		//蛇
		hk6RuleMap.put("蛇",snakeList);
		//马
		hk6RuleMap.put("马",horseList);
		//羊
		hk6RuleMap.put("羊",sheepList);
		//猴
		hk6RuleMap.put("猴",monkeyList);
		//鸡
		hk6RuleMap.put("鸡",chickenList);
		//狗
		hk6RuleMap.put("狗",dogList);
		//猪
		hk6RuleMap.put("猪",pigList);
		
		hk6RuleMap.put("红波", Arrays.asList(redArr));
		hk6RuleMap.put("蓝波", Arrays.asList(blueArr));
		hk6RuleMap.put("绿波", Arrays.asList(greenArr));
		hk6RuleMap.put("合单", Arrays.asList(hesingleArr));
		hk6RuleMap.put("合双", Arrays.asList(hedoubleArr));
		hk6RuleMap.put("大", Arrays.asList(bigArr));
		hk6RuleMap.put("小", Arrays.asList(smallArr));
		hk6RuleMap.put("单", Arrays.asList(singleArr));
		hk6RuleMap.put("双", Arrays.asList(doubleArr));
		hk6RuleMap.put("合大", Arrays.asList(hebigArr));
		hk6RuleMap.put("合小", Arrays.asList(hesmallArr));
		
		hk6RuleMap.put("红单", Arrays.asList(redSingleArr));
		hk6RuleMap.put("红双", Arrays.asList(redDoubleArr));
		hk6RuleMap.put("红大", Arrays.asList(redBigArr));
		hk6RuleMap.put("红小", Arrays.asList(redSmallArr));
		
		hk6RuleMap.put("蓝单", Arrays.asList(blueSingleArr));
		hk6RuleMap.put("蓝双", Arrays.asList(blueDoubleArr));
		hk6RuleMap.put("蓝大", Arrays.asList(blueBigArr));
		hk6RuleMap.put("蓝小", Arrays.asList(blueSmallArr));
		
		hk6RuleMap.put("绿单", Arrays.asList(greenSingleArr));
		hk6RuleMap.put("绿双", Arrays.asList(greenDoubleArr));
		hk6RuleMap.put("绿大", Arrays.asList(greenBigArr));
		hk6RuleMap.put("绿小", Arrays.asList(greenSmallArr));
		
		hk6RuleMap.put("0头", Arrays.asList(head0Arr));
		hk6RuleMap.put("1头", Arrays.asList(head1Arr));
		hk6RuleMap.put("2头", Arrays.asList(head2Arr));
		hk6RuleMap.put("3头", Arrays.asList(head3Arr));
		hk6RuleMap.put("4头", Arrays.asList(head4Arr));
		
		hk6RuleMap.put("0尾", Arrays.asList(tail0Arr));
		hk6RuleMap.put("1尾", Arrays.asList(tail1Arr));
		hk6RuleMap.put("2尾", Arrays.asList(tail2Arr));
		hk6RuleMap.put("3尾", Arrays.asList(tail3Arr));
		hk6RuleMap.put("4尾", Arrays.asList(tail4Arr));
		hk6RuleMap.put("5尾", Arrays.asList(tail5Arr));
		hk6RuleMap.put("6尾", Arrays.asList(tail6Arr));
		hk6RuleMap.put("7尾", Arrays.asList(tail7Arr));
		hk6RuleMap.put("8尾", Arrays.asList(tail8Arr));
		hk6RuleMap.put("9尾", Arrays.asList(tail9Arr));
		
		hk6RuleMap.put("尾大", Arrays.asList(tailBigArr));
		hk6RuleMap.put("尾小", Arrays.asList(tailSmallArr));
		
		hk6RuleMap.put("大单", Arrays.asList(bigSingleArr));
		hk6RuleMap.put("大双", Arrays.asList(bigDoubleArr));
		hk6RuleMap.put("小单", Arrays.asList(smallSingleArr));
		hk6RuleMap.put("小双", Arrays.asList(smallDoubleArr));
		
		
		hk6RuleMap.put("红单大", Arrays.asList(redSingleBigArr));
		hk6RuleMap.put("红单小", Arrays.asList(redSingleSmallArr));
		hk6RuleMap.put("红双大", Arrays.asList(redDoubleBigArr));
		hk6RuleMap.put("红双小", Arrays.asList(redDoubleSmallArr));
		
		hk6RuleMap.put("蓝单大", Arrays.asList(blueSingleBigArr));
		hk6RuleMap.put("蓝单小", Arrays.asList(blueSingleSmallArr));
		hk6RuleMap.put("蓝双大", Arrays.asList(blueDoubleBigArr));
		hk6RuleMap.put("蓝双小", Arrays.asList(blueDoubleSmallArr));
		
		hk6RuleMap.put("绿单大", Arrays.asList(greenSingleBigArr));
		hk6RuleMap.put("绿单小", Arrays.asList(greenSingleSmallArr));
		hk6RuleMap.put("绿双大", Arrays.asList(greenDoubleBigArr));
		hk6RuleMap.put("绿双小", Arrays.asList(greenDoubleSmallArr));
		

	}
	
	
	/**
	 * 中奖结果判断
	 * 方法描述: TODO</br> 
	 * @param kjhmStr
	 * @param xzhmStr
	 * @return  
	 * boolean
	 */
	public static boolean getLotResult(String kjhmStr, String xzhmStr) {
		if (StringUtils.isNumeric(xzhmStr)) {
			int kjhm = Integer.valueOf(kjhmStr);
			int xzhm = Integer.valueOf(xzhmStr);
			if (kjhm == xzhm) {
				return true;
			} else {
				return false;
			}
		} else {
			List<String> valList = hk6RuleMap.get(xzhmStr);
			if(valList.contains(kjhmStr)){
				return true;
			} else {
				return false;
			}

		}
	}

	/**
	 * 特码　单　双：特码为双数叫特双，如8、16；特码为单数叫特单，如21、35，开出49为和。 方法描述: TODO</br>
	 * 
	 * @param tm
	 * @return String
	 */
	public static String getDs(String tm) {
		int num = Integer.valueOf(tm);
		if (num < 49) {
			if (num % 2 == 0) {
				return "双";
			} else {
				return "单";
			}
		} else {
			return "和";
		}
	}

	/**
	 * 特码　大　小：开出之特码大于或等于25为特码大， 小于或等于24为特码小，开出49为和。 方法描述: TODO</br>
	 * 
	 * @param tm
	 * @return String
	 */
	public static String getDx(String tm) {
		int num = Integer.valueOf(tm);
		if (num < 49) {
			if (num >= 25) {
				return "大";
			} else {
				return "小";
			}
		} else {
			return "和";
		}
	}

	/**
	 * 特码和数大小：以特码个位和十位数字之和来判断胜负，和数大于或等于7为大，小于或等于6为小，开出49号为和。 方法描述: TODO</br>
	 * 
	 * @param tm
	 * @return String
	 */
	public static String getHsDx(String tm) {
		int num = Integer.valueOf(tm);
		if (num < 49) {
			int hstotal = 0;
			char[] chArr = tm.toCharArray();
			for (char ch : chArr) {
				hstotal += Integer.valueOf(ch + "").intValue();
			}
			if (hstotal >= 7) {
				return "合大";
			} else {
				return "合小";
			}
		} else {
			return "和";
		}
	}

	/**
	 * 特码和数单双：以特码个位和十位数字之和来判断单双，如01，12，32为和单，02，11，33为和双，开出49号为和。 方法描述:
	 * TODO</br>
	 * 
	 * @param tm
	 * @return String
	 */
	public static String getHsDs(String tm) {
		int num = Integer.valueOf(tm);
		if (num < 49) {
			int hstotal = 0;
			char[] chArr = tm.toCharArray();
			for (char ch : chArr) {
				hstotal += Integer.valueOf(ch + "").intValue();
			}
			if (hstotal % 2 == 0) {
				return "合双";
			} else {
				return "合单";
			}
		} else {
			return "和";
		}

	}

	/**
	 * 特码尾数大小：以特别号尾数若0尾~4尾为小、5尾~9尾为大；如01、32、44为特尾小；如05、18、19为特尾大，开出49号为和 方法描述:
	 * TODO</br>
	 * 
	 * @param tm
	 * @return String
	 */
	public static String getWsDx(String tm) {
		if (Integer.valueOf(tm) < 49) {
			char[] chArr = tm.toCharArray();
			int ws;
			if (chArr.length > 1) {
				ws = Integer.valueOf(chArr[1] + "").intValue();
			} else {
				ws = Integer.valueOf(chArr[0] + "").intValue();
			}
			if (ws > 4) {
				return "尾大";
			} else {
				return "尾小";
			}
		} else {
			return "和";
		}
	}

	/**
	 * 得到特码大小的走势二维数组
	 * 方法描述: TODO</br> 
	 * @param results
	 * @return  
	 * String[][]
	 */
	public static String[][] TMDXGraph(List<CpHk6Results> results){
		String[][] str = new String[7][7];
		String preTm = "";
		int m = 7, n = 0;
		for(int i = 0; i<= results.size(); i++){
			CpHk6Results cp1 = results.get(i);
			String tmDx = cp1.getTmDx();
			if(preTm.equals(tmDx)){
				n++;
				if(n>6){
					m--;
					n=0;
				}
			}else{
				m--;
				n=0;
			}
			
			if(m < 0){
				break;
			}
			str[n][m] = tmDx;
			preTm = tmDx;
		}
		return str;
	}
	
	/**
	 * 得到特码单双的走势二维数组
	 * 方法描述: TODO</br> 
	 * @param results
	 * @return  
	 * String[][]
	 */
	public static String[][] TMDSGraph(List<CpHk6Results> results){
		String[][] str = new String[7][7];
		String preTm = "";
		int m = 7, n = 0;;
		for(int i = 0; i<= results.size(); i++){
			CpHk6Results cp1 = results.get(i);
			String tmDs = cp1.getTmDs();
			if(preTm.equals(tmDs)){
				n++;
				if(n>6){
					m--;
					n=0;
				}
			}else{
				m--;
				n=0;
			}
			
			if(m < 0){
				break;
			}
			str[n][m] = tmDs;
			preTm = tmDs;
		}
		return str;
	}
	
	/**
	 * 得到特码特的走势二维数组
	 * 方法描述: TODO</br> 
	 * @param results
	 * @return  
	 * String[][]
	 */
	public static String[][] TMTGraph(List<CpHk6Results> results){
		String[][] str = new String[7][7];
		String preTm = "";
		int m = 7, n = 0;;
		for(int i = 0; i<= results.size(); i++){
			CpHk6Results cp1 = results.get(i);
			String tmt = cp1.getTmDx() + cp1.getTmDs();
			if(preTm.equals(tmt)){
				n++;
				if(n>6){
					m--;
					n=0;
				}
			}else{
				m--;
				n=0;
			}
			
			if(m < 0){
				break;
			}
			str[n][m] = tmt;
			preTm = tmt;
		}
		return str;
	}
	
	/**
	 * 左边HK6特码单号
	 * 方法描述: TODO</br> 
	 * @param results
	 * @return  
	 * List<Map<String, String>>
	 */
	public static List<Map<String, String>> TMDH(List<CpHk6Results> results){
		List<Map<String, String>> tm = Collections.synchronizedList(new ArrayList<Map<String, String>>());
		
		for(int i = 9; i>=5; i--){
			Map<String, String> map = new HashMap<String, String>();
			CpHk6Results cp1 = results.get(i);
			map.put("qs1", cp1.getQs());
			map.put("tm1", cp1.getTm());
			map.put("cl1",HK6Util.colorClassNameMap.get(cp1.getTm()));
			
			
			CpHk6Results cp2 = results.get(i-5);
			map.put("qs2", cp2.getQs());
			map.put("tm2", cp2.getTm());
			map.put("cl2",HK6Util.colorClassNameMap.get(cp2.getTm()));
			tm.add(map);
		}
		
		return tm;
	}
	
	/**
	 * 得到当前期数
	 * 方法描述: TODO</br> 
	 * @param topQs
	 * @return  
	 * String
	 */
	public static String getNowQs(String topQs){
		
		if(!StringUtils.isEmpty(topQs)){
			BigInteger qs = new BigInteger(topQs);
			return String.valueOf(qs.add(new BigInteger("1")));
		}
		return "";
	}
	
	/**
	 * 获取样式
	 * 方法描述: TODO</br> 
	 * @param number
	 * @param color
	 * @return  
	 * String
	 */
	public static String getClassName(String number,String color) {
		if(StringUtils.isNumeric(number)){
			if("RED".equals(color)){
				return "ball_r";
			}else if("BLUE".equals(color)){
				return "ball_b";
			}else if("GREEN".equals(color)){
				return "ball_g";
			}
		}else{
			if("RED".equals(color)){
				return "ball_bg ball_reb";
			}else if("BLUE".equals(color)){
				return "ball_bg ball_blue";
			}else if("GREEN".equals(color)){
				return "ball_bg ball_gree";
			}
		}
		return "ball_bg";
	}
	
	/***
	 * 
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param a
	 * @param n
	 * @param m
	 * @param b
	 * @param M
	 * @param array
	 * @return  
	 * List<String>
	 */
	public static List<String> combine(Object a[], int n, int m, int b[], int N, int M, List<String> array)
	{
		for(int i=n; i<=N-M+n; i++)   // 注意这里的循环范围
		{
			if(i >= N){
				return array;
			}
			b[m-M] = i;
			if (m < 2*M-1)
		      combine(a,i+1,m+1,b,N,M,array);
		    else			// m == 1, 输出一个组合
		    {
		    	String tmp = "";
		      for(int j=0; j<=M-1; j++)
		      {
		    	  tmp += a[b[j]] + ",";
		      }
		      tmp = tmp.substring(0,tmp.length()-1);
		      if(!array.contains(tmp)){
		    	  array.add(tmp);
		      }
		    }
		}
		return array;
	}
	
	public static void combine( int a[], int n, int m, int b[], int N, int M )
	{
		for(int i=n; i<=N-M+n; i++)   // 注意这里的循环范围
		{
			if(i >= N){
				return;
			}
		    b[m-M] = i;
		    if (m < 2*M-1)
		      combine(a,i+1,m+1,b,N,M);
		    else			// m == 1, 输出一个组合
		    {
		      for(int j=0; j<=M-1; j++)
		      {
		    	  System.out.print(a[b[j]] + " ");
		      }
		      System.out.println();
		    }
	  }
	}
	
	public static Map<String, List<String>> getZodiac(){
		Map<String, List<String>> zodiac = new HashMap<String, List<String>>();
		zodiac.put("鼠",ratList);
		zodiac.put("牛", cattleList);
		zodiac.put("虎",tigerList);
		zodiac.put("兔",rabbitList);
		zodiac.put("龙",loongList);
		zodiac.put("蛇",snakeList);
		zodiac.put("马",horseList);
		zodiac.put("羊",sheepList);
		zodiac.put("猴",monkeyList);
		zodiac.put("鸡",chickenList);
		zodiac.put("狗",dogList);
		zodiac.put("猪",pigList);
		return zodiac;
	}
	
	
	/**
	 * 排除过滤49
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<String>
	 */
	public static  List<String> getTMFiler49List(){
		List<String> list = new ArrayList<String>();
		list.add("单");
		list.add("双");
		list.add("大");
		list.add("小");
		list.add("合单");
		list.add("合双");
		list.add("合大");
		list.add("合小");
		list.add("尾大");
		list.add("尾小");
		list.add("大单");
		list.add("小单");
		list.add("大双");
		list.add("小双");
		
		return list;
	}
	
	
	/**
	 * 校验正码
	 * 方法描述: TODO</br> 
	 * @param hk6Results
	 * @param xzhm
	 * @return  
	 * boolean
	 */
	public static boolean valiZm(CpHk6Results hk6Results,String xzhm){
		if ("总单".equals(xzhm)) {
			if (hk6Results.getZfDs().equals("单")) {
				return true;
			}
		} else if ("总双".equals(xzhm)) {
			if (hk6Results.getZfDs().equals("双")) {
				return true;
			}
		} else if ("总大".equals(xzhm)) {
			if (hk6Results.getZfDs().equals("大")) {
				return true;
			}
		} else if ("总小".equals(xzhm)) {
			if (hk6Results.getZfDs().equals("小")) {
				return true;
			}
		}
		return false;
		
	}
	
	public static void hk6leftGrap(List<CpHk6Results> results){
		for(int i = 0; i< results.size(); i++){
			CpHk6Results cp = results.get(i);
			String tmt = cp.getTm();
			String tmw =tmt.substring(tmt.length()-1, tmt.length());
			String qs = cp.getQs();
			//qs = qs.substring(qs.length()-2, qs.length());
			Map<String, String> map = AnimalUtil.getCurrAnimalCode();
			String hmsx = "";
			hmsx += map.get(cp.getP1()) + " ";
			hmsx += map.get(cp.getP2()) + " ";
			hmsx += map.get(cp.getP3()) + " ";
			hmsx += map.get(cp.getP4()) + " ";
			hmsx += map.get(cp.getP5()) + " ";
			hmsx += map.get(cp.getP6()) + " + ";
			hmsx += "<font color=\"#FF0000\">" + map.get(cp.getTm())+"</font>";
			cp.setTmw(tmw);
			cp.setQs(qs);
			cp.setHmsx(hmsx);
		}
	}
	
	/**
	 * 开奖结果列表页面
	 * @Description: TODO
	 * @param    
	 * @return void
	 * @author Andy
	 * @date 2016-1-1
	 */
	public static String parseHk6ResultToJson(List<CpHk6Results> list){
		JSONArray jsonArray=new JSONArray();
		for(CpHk6Results c:list){
			JSONObject json=new JSONObject();
			json.put("kjsj", c.getKjsj());
			json.put("qs", c.getQs());
			json.put("p1", c.getP1());
			json.put("p2", c.getP2());
			json.put("p3", c.getP3());
			json.put("p4", c.getP4());
			json.put("p5", c.getP5());
			json.put("p6", c.getP6());
			json.put("tm", c.getTm());
			json.put("tmDs", c.getTmDs());
			json.put("tmDx", c.getTmDx());
			json.put("zh",c.getZfS());
			json.put("zhdx", c.getZfDx());
			json.put("zhds", c.getZfDs());
			json.put("hds", c.getHsDs());
			json.put("sx", c.getKjjgSx());
			
			jsonArray.add(json);
		}
		return jsonArray.toJSONString();
	}
}
