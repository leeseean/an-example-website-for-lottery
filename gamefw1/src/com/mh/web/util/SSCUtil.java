/**   
* 文件名称: HK6Util.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-5-31 下午2:34:12<br/>
*/  
package com.mh.web.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.mh.entity.CpSscResults;

/**
 * 
 * 类描述: TODO<br/> 
 * 修改人: zoro<br/>
 * 修改时间: 2015-6-2 上午9:45:24<br/>
 */
public class SSCUtil {

	//大
	public static String[] bigArr = new String[]{"11","12","13","14","15","16","17","18","19","20"};
	//小
	public static String[] smallArr = new String[]{"01","02","03","04","05","06","07","08","09","10"};
	
	//单
	public static String[] singleArr= new String[]{"01","03","05","07","09","11","13","15","17","19"};
	//双
	public static String[] doubleArr= new String[]{"02","04","06","08","10","12","14","16","18","20"};

	//尾大
	public static String[] bigTail = new String[]{"05","06","07","08","09","15","16","17","18","19"};
	//尾小
	public static String[] smallTail = new String[]{"01","02","03","04","10","11","12","13","14","20"};
	
	//合单
	public static String[] hesingleArr= new String[]{"01","03","05","07","09","10","12","14","16","18"};
	//合双
	public static String[] hedoubleArr= new String[]{"02","04","06","08","11","13","15","17","19","20"};
	
	
	//东
	public static String[] eastArr = new String[]{"01","05","09","13","17"};
	//南
	public static String[] southArr = new String[]{"02","06","10","14","18"};
	//西
	public static String[] westArr = new String[]{"03","07","11","15","19"};
	//北
	public static String[] northArr = new String[]{"04","08","12","16","20"};
	
	//中
	public static String[] middleArr = new String[]{"01","02","03","04","05","06","07"};
	//发
	public static String[] faArr = new String[]{"08","09","10","11","12","13","14"};
	//白
	public static String[] whiteArr = new String[]{"15","16","17","18","19","20"};
	
	public static Map<String,List<String>> klsfRuleMap = new HashMap<String,List<String>>();
	static{
		
		klsfRuleMap.put("大", Arrays.asList(bigArr));
		klsfRuleMap.put("小", Arrays.asList(smallArr));
		klsfRuleMap.put("单", Arrays.asList(singleArr));
		klsfRuleMap.put("双", Arrays.asList(doubleArr));
		klsfRuleMap.put("尾大", Arrays.asList(bigTail));
		klsfRuleMap.put("尾小", Arrays.asList(smallTail));
		klsfRuleMap.put("合单", Arrays.asList(hesingleArr));
		klsfRuleMap.put("合双", Arrays.asList(hedoubleArr));
		
		klsfRuleMap.put("东", Arrays.asList(eastArr));
		klsfRuleMap.put("南", Arrays.asList(southArr));
		klsfRuleMap.put("西", Arrays.asList(westArr));
		klsfRuleMap.put("北", Arrays.asList(northArr));
		
		klsfRuleMap.put("中", Arrays.asList(middleArr));
		klsfRuleMap.put("发", Arrays.asList(faArr));
		klsfRuleMap.put("白", Arrays.asList(whiteArr));
		
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
			List<String> valList = klsfRuleMap.get(xzhmStr);
			if(valList.contains(kjhmStr)){
				return true;
			} else {
				return false;
			}

		}
	}
	
	/**
	 * 快乐十分总分结果分析
	 * 方法描述: TODO</br> 
	 * @param kjZFStr
	 * @param kjLHStr
	 * @param xzhmStr
	 * @return  
	 * boolean
	 */
	public static boolean getZFResult(int kjZFStr, String kjLHStr, String xzhmStr) {
		List<String> zfResult = new ArrayList<String>();
		int zfNum = Integer.valueOf(kjZFStr);
		if(zfNum != 84){
			if(zfNum >=85 && zfNum <= 135){
				zfResult.add("大");
			}
			if(zfNum >=36 && zfNum <= 83){
				zfResult.add("小");
			}
		}else{
			zfResult.add("和");
		}
		if (zfNum % 2 == 0) {
			zfResult.add("双");
		} else {
			zfResult.add("单");
		}
		if(zfNum % 100 % 10 >= 5){
			zfResult.add("尾大");
		}else {
			zfResult.add("尾小");
		}
		
		zfResult.add(kjLHStr);
		if(zfResult.contains(xzhmStr)){
			return true;
		}
		return false;
	}
	
	/**
	 * 时时彩5X8矩阵开奖图
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param results
	 * @return  
	 * List<Map<String,Object>>
	 */
	public static List<Map<String, Object>> sscGraph(List<CpSscResults> results){
		List<Map<String, Object>> list = Collections.synchronizedList(new ArrayList<Map<String, Object>>());
		for(int i = 0; i< results.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			CpSscResults ssc = results.get(i);
			String qs = ssc.getQs();
			if(qs.length()>8){
				qs = qs.substring(8);
			}
			
			map.put("qs", qs);
			map.put("wNum", ssc.getKjhmW());
			map.put("qNum", ssc.getKjhmQ());
			map.put("bNum", ssc.getKjhmB());
			map.put("sNum", ssc.getKjhmS());
			map.put("gNum", ssc.getKjhmG());
			list.add(map);
			if(list.size() == 8)
				break;
		}
		
		return list;
	}
	
	/**
	 * 获取时时彩开奖路图
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param results
	 * @return  
	 * String
	 */
	public static String _5qGraph(List<CpSscResults> results){
		List<List<Integer>> d1DxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d2DxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d3DxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d4DxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d5DxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		
		List<List<Integer>> d1DsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d2DsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d3DsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d4DsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d5DsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> sumDxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> sumDsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		String pre1QDx = "";
		String pre2QDx = "";
		String pre3QDx = "";
		String pre4QDx = "";
		String pre5QDx = "";
		String sumPrDx = "";
		
		String pre1QDs = "";
		String pre2QDs = "";
		String pre3QDs = "";
		String pre4QDs = "";
		String pre5QDs = "";
		String sumPrDs = "";
		
		List<Integer> dxl1 = new ArrayList<Integer>();
		List<Integer> dxl2 = new ArrayList<Integer>();
		List<Integer> dxl3 = new ArrayList<Integer>();
		List<Integer> dxl4 = new ArrayList<Integer>();
		List<Integer> dxl5 = new ArrayList<Integer>();
		List<Integer> dxSum = new ArrayList<Integer>();
		
		List<Integer> dsl1 = new ArrayList<Integer>();
		List<Integer> dsl2 = new ArrayList<Integer>();
		List<Integer> dsl3 = new ArrayList<Integer>();
		List<Integer> dsl4 = new ArrayList<Integer>();
		List<Integer> dsl5 = new ArrayList<Integer>();
		List<Integer> dsSum = new ArrayList<Integer>();
		
		for(int i=results.size()-1; i>=0; i--){
			CpSscResults result = results.get(i);
			String _1QDx = result.getwDx();
			String _2QDx = result.getqDx();
			String _3QDx = result.getbDx();
			String _4QDx = result.getsDx();
			String _5QDx = result.getgDx();
			String sumDx = result.getZhDx();
			
			String _1QDs = result.getwDs();
			String _2QDs = result.getqDs();
			String _3QDs = result.getbDs();
			String _4QDs = result.getsDs();
			String _5QDs = result.getgDs();
			String sumDs = result.getZhDs();
			
			dxl1= compareDx(pre1QDx, _1QDx, dxl1, d1DxList);
			dxl2 = compareDx(pre2QDx, _2QDx, dxl2, d2DxList);
			dxl3 = compareDx(pre3QDx, _3QDx, dxl3, d3DxList);
			dxl4 = compareDx(pre4QDx, _4QDx, dxl4, d4DxList);
			dxl5 = compareDx(pre5QDx, _5QDx, dxl5, d5DxList);
			dxSum = compareDx(sumPrDx, sumDx, dxSum, sumDxList);
			
			dsl1 = compareDs(pre1QDs, _1QDs, dsl1, d1DsList);
			dsl2 = compareDs(pre2QDs, _2QDs, dsl2, d2DsList);
			dsl3 = compareDs(pre3QDs, _3QDs, dsl3, d3DsList);
			dsl4 = compareDs(pre4QDs, _4QDs, dsl4, d4DsList);
			dsl5 = compareDs(pre5QDs, _5QDs, dsl5, d5DsList);
			dsSum = compareDs(sumPrDs, sumDs, dsSum, sumDsList);
			
			
			if(i==0){
				d1DxList.add(dxl1);
				d2DxList.add(dxl2);
				d3DxList.add(dxl3);
				d4DxList.add(dxl4);
				d5DxList.add(dxl5);
				sumDxList.add(dxSum);
				
				d1DsList.add(dsl1);
				d2DsList.add(dsl2);
				d3DsList.add(dsl3);
				d4DsList.add(dsl4);
				d5DsList.add(dsl5);
				sumDsList.add(dsSum);
			}
			
			pre1QDx = _1QDx;
			pre2QDx = _2QDx;
			pre3QDx = _3QDx;
			pre4QDx = _4QDx;
			pre5QDx = _5QDx;
			sumPrDx = sumDx;
			
			pre1QDs = _1QDs;
			pre2QDs = _2QDs;
			pre3QDs = _3QDs;
			pre4QDs = _4QDs;
			pre5QDs = _5QDs;
			sumPrDs = sumDs;
		}
		JSONObject d1qJson = new JSONObject();
		d1qJson.put("big_small", d1DxList);
		d1qJson.put("odd_even", d1DsList);
		
		JSONObject d2qJson = new JSONObject();
		d2qJson.put("big_small", d2DxList);
		d2qJson.put("odd_even", d2DsList);
		
		JSONObject d3qJson = new JSONObject();
		d3qJson.put("big_small", d3DxList);
		d3qJson.put("odd_even", d3DsList);
		
		JSONObject d4qJson = new JSONObject();
		d4qJson.put("big_small", d4DxList);
		d4qJson.put("odd_even", d4DsList);
		
		JSONObject d5qJson = new JSONObject();
		d5qJson.put("big_small", d5DxList);
		d5qJson.put("odd_even", d5DsList);
		
		JSONObject sumJson = new JSONObject();
		sumJson.put("big_small", sumDxList);
		sumJson.put("odd_even", sumDsList);
		
		JSONObject json = new JSONObject();
		json.put("1", d1qJson);
		json.put("2", d2qJson);
		json.put("3", d3qJson);
		json.put("4", d4qJson);
		json.put("5", d5qJson);
		json.put("7", sumJson);
		
		return json.toJSONString();
	}
	
	
	/**
	 * 比较当前球和前一期的大小，添加到List中
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param preDx
	 * @param qDx
	 * @param list
	 * @param qDxList
	 * @return  
	 * List<Integer>
	 */
	public static List<Integer> compareDx(String preDx, String qDx, List<Integer> list, List<List<Integer>> qDxList){
		if(list.size() == 4){
			qDxList.add(list);
			list = new ArrayList<Integer>();
		}
		if(preDx.equals(qDx)){
			list.add("大".equals(qDx)?1:2);
		}else{
			if(list.size()>0){
				qDxList.add(list);
				list = new ArrayList<Integer>();
			}
			
			list.add("大".equals(qDx)?1:2);
		}
		return list;
	}
	
	/**
	 * 比较当前球和前一期的单双，添加到List中
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param preDs
	 * @param qDs
	 * @param list
	 * @param qDsList
	 * @return  
	 * List<Integer>
	 */
	public static List<Integer> compareDs(String preDs, String qDs, List<Integer> list, List<List<Integer>> qDsList){
		if(list.size() == 4){
			qDsList.add(list);
			list = new ArrayList<Integer>();
		}
		if(preDs.equals(qDs)){
			list.add("单".equals(qDs)?1:2);
		}else{
			if(list.size()>0){
				qDsList.add(list);
				list = new ArrayList<Integer>();
			}
			list.add("单".equals(qDs)?1:2);
		}
		return list;
	}
	
	/**
	 * 统计时时彩5位的出球率
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param sscResults
	 * @return  
	 * String
	 */
	public static String reportSSCBallRate(List<CpSscResults> sscResults){
		Map<Integer, Integer> wMap = initMap();
		Map<Integer, Integer> qMap = initMap();
		Map<Integer, Integer> bMap = initMap();
		Map<Integer, Integer> sMap = initMap();
		Map<Integer, Integer> gMap = initMap();
		Map<Integer, Integer> qwMap = initMap();//全五无期数map
		for(int i=0; i< sscResults.size(); i++){
			CpSscResults result = sscResults.get(i);
			int wNum = result.getKjhmW();
			int qNum = result.getKjhmQ();
			int bNum = result.getKjhmB();
			int sNum = result.getKjhmS();
			int gNum = result.getKjhmG();
			putNum(wNum, wMap);
			putNum(qNum, qMap);
			putNum(bNum, bMap);
			putNum(sNum, sMap);
			putNum(gNum, gMap);
			quwCompare(wNum,qNum,bNum,sNum,gNum,qwMap);
		}
		
		return SSCUtil.htmlReportDatas(wMap,qMap,bMap,sMap,gMap,qwMap);
	}
	
	/**
	 * 初始化map
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @return  
	 * Map<Integer,Integer>
	 */
	private static Map<Integer, Integer> initMap(){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<10;i++){
			map.put(i, 0);
		}
		return map;
	}
	
	private static void quwCompare(int wNum,int qNum ,int bNum,int sNum,int gNum,Map<Integer, Integer> map){
		for(int i=0; i<10;i++){
			if(i==wNum || i==qNum || i==bNum || i==sNum|| i==gNum){
				
			}else{
				if(map.containsKey(i)){
					map.put(i, map.get(i)+1);
				}else{
					map.put(i, 1);
				}
			}
		}
	}
	
	
	/**
	 * 统计相同球的个数
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param num
	 * @param map  
	 * void
	 */
	private static void putNum(int num, Map<Integer, Integer> map){
		if(map.containsKey(num)){
			map.put(num, map.get(num)+1);
		}else{
			map.put(num, 1);
		}
	}
	
	/**
	 * 动态拼接html
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param wMap
	 * @param qMap
	 * @param bMap
	 * @param sMap
	 * @param gMap
	 * @return  
	 * String
	 */
	private static String htmlReportDatas(Map<Integer, Integer> wMap, Map<Integer, Integer> qMap, Map<Integer, Integer> bMap,
									Map<Integer, Integer> sMap, Map<Integer, Integer> gMap, Map<Integer, Integer> qwMap){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"1\" class=\"game_table\">\n");
		buffer.append("		 <tr class=\"tbtitle\" style=\"height:25px\">\n");
		buffer.append("		 	<th class=\"ball_ff\" style=\"width:40%\">今天</th>\n");
		buffer.append("			<th class=\"ball_ff\">0</th>\n");
		buffer.append("			<th class=\"ball_ff\">1</th>\n");
		buffer.append("			<th class=\"ball_ff\">2</th>\n");
		buffer.append("			<th class=\"ball_ff\">3</th>\n");
		buffer.append("			<th class=\"ball_ff\">4</th>\n");
		buffer.append("			<th class=\"ball_ff\">5</th>\n");
		buffer.append("		    <th class=\"ball_ff\">6</th>\n");
		buffer.append("		    <th class=\"ball_ff\">7</th>\n");
		buffer.append("		    <th class=\"ball_ff\">8</th>\n");
		buffer.append("		    <th class=\"ball_ff\">9</th>\n");
		buffer.append("		</tr><tr class=\"tbtitle\">\n");
		buffer.append("		 	<td class=\"ball_ff\">萬位OXXXX出球率</td>\n");
		buffer.append("		    <td style=\"\">"+wMap.get(0)+"</td>\n");
		buffer.append("			<td style=\"\">"+wMap.get(1)+"</td>\n");
		buffer.append("			<td style=\"\">"+wMap.get(2)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+wMap.get(3)+"</td>\n");
		buffer.append("			<td style=\"\">"+wMap.get(4)+"</td>\n");
		buffer.append("			<td style=\"\">"+wMap.get(5)+"</td>\n");
		buffer.append("			<td style=\"\">"+wMap.get(6)+"</td>\n");
		buffer.append("			<td style=\"\">"+wMap.get(7)+"</td>\n");
		buffer.append("			<td style=\"\">"+wMap.get(8)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+wMap.get(9)+"</td>\n");
		buffer.append("		</tr><tr class=\"tbtitle\">\n");
		buffer.append("		 	<td class=\"ball_ff\">千位XOXXX出球率</td>\n");
		buffer.append("		    <td style=\"\">"+qMap.get(0)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+qMap.get(1)+"</td>\n");
		buffer.append("			<td style=\"\">"+qMap.get(2)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+qMap.get(3)+"</td>\n");
		buffer.append("			<td style=\"\">"+qMap.get(4)+"</td>\n");
		buffer.append("			<td style=\"\">"+qMap.get(5)+"</td>\n");
		buffer.append("			<td style=\"\">"+qMap.get(6)+"</td>\n");
		buffer.append("			<td style=\"\">"+qMap.get(7)+"</td>\n");
		buffer.append("			<td style=\"\">"+qMap.get(8)+"</td>\n");
		buffer.append("			<td style=\"\">"+qMap.get(9)+"</td>\n");
		buffer.append("		</tr><tr class=\"tbtitle\">\n");
		buffer.append("		 	<td class=\"ball_ff\">百位XXOXX出球率</td>\n");
		buffer.append("		    <td style=\"\">"+bMap.get(0)+"</td>\n");
		buffer.append("			<td style=\"\">"+bMap.get(1)+"</td>\n");
		buffer.append("			<td style=\"\">"+bMap.get(2)+"</td>\n");
		buffer.append("			<td style=\"\">"+bMap.get(3)+"</td>\n");
		buffer.append("			<td style=\"\">"+bMap.get(4)+"</td>\n");
		buffer.append("			<td style=\"\">"+bMap.get(5)+"</td>\n");
		buffer.append("			<td style=\"\">"+bMap.get(6)+"</td>\n");
		buffer.append("			<td style=\"\">"+bMap.get(7)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+bMap.get(8)+"</td>\n");
		buffer.append("			<td style=\"\">"+bMap.get(9)+"</td>\n");
		buffer.append("		</tr><tr class=\"tbtitle\">\n");
		buffer.append("		 	<td class=\"ball_ff\">拾位XXXOX出球率</td>\n");
		buffer.append("		    <td style=\"\">"+sMap.get(0)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+sMap.get(1)+"</td>\n");
		buffer.append("			<td style=\"\">"+sMap.get(2)+"</td>\n");
		buffer.append("			<td style=\"\">"+sMap.get(3)+"</td>\n");
		buffer.append("			<td style=\"\">"+sMap.get(4)+"</td>\n");
		buffer.append("			<td style=\"\">"+sMap.get(5)+"</td>\n");
		buffer.append("			<td style=\"\">"+sMap.get(6)+"</td>\n");
		buffer.append("			<td style=\"\">"+sMap.get(7)+"</td>\n");
		buffer.append("			<td style=\"\">"+sMap.get(8)+"</td>\n");
		buffer.append("			<td style=\"\">"+sMap.get(9)+"</td>\n");
		buffer.append("		</tr><tr class=\"tbtitle\">\n");
		buffer.append("		 	<td class=\"ball_ff\">個位XXXXO出球率</td>\n");
		buffer.append("		    <td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(0)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(1)+"</td>\n");
		buffer.append("			<td style=\"\">"+gMap.get(2)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(3)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(4)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(5)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(6)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(7)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(8)+"</td>\n");
		buffer.append("			<td style=\"\">"+gMap.get(9)+"</td>\n");
		buffer.append("		</tr><tr class=\"tbtitle\">\n");
		buffer.append("		 	<td class=\"ball_ff\">全五無出期數</td>\n");
		buffer.append("		    <td style=\"\">"+qwMap.get(0)+"</td>\n");
		buffer.append("			<td style=\"\">"+qwMap.get(1)+"</td>\n");
		buffer.append("			<td style=\"color:#ff0000;font-weight:bold\">"+qwMap.get(2)+"</td>\n");
		buffer.append("			<td style=\"\">"+qwMap.get(3)+"</td>\n");
		buffer.append("			<td style=\"\">"+qwMap.get(4)+"</td>\n");
		buffer.append("			<td style=\"\">"+qwMap.get(5)+"</td>\n");
		buffer.append("			<td style=\"\">"+qwMap.get(6)+"</td>\n");
		buffer.append("			<td style=\"\">"+qwMap.get(7)+"</td>\n");
		buffer.append("			<td style=\"\">"+qwMap.get(8)+"</td>\n");
		buffer.append("			<td style=\"\">"+qwMap.get(9)+"</td>\n");
		buffer.append("			</tr></table>\n");
		
		return buffer.toString();
	}
}
