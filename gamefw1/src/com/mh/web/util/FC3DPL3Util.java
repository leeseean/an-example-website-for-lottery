/**   
* 文件名称: FC3PL3Util.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-6-4 上午10:42:19<br/>
*/  
package com.mh.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mh.commons.utils.LotUtil;
import com.mh.entity.CpFc3dypl3Results;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-6-4 上午10:42:19<br/>
 */
public class FC3DPL3Util {
	
	/**
	 * 得到福彩3D排列3近期开奖图
	 * 方法描述: TODO</br> 
	 * @param results
	 * @return  
	 * List<Map<String,Object>>
	 */
	public static List<Map<String, Object>> fc3dpl3Graph(List<CpFc3dypl3Results> results){
		List<Map<String, Object>> list = Collections.synchronizedList(new ArrayList<Map<String, Object>>());
		for(int i = 0; i< results.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			CpFc3dypl3Results cpFc3dPl3 = results.get(i);
			String qs = cpFc3dPl3.getQs();
			if(qs.length()>8){
				qs = qs.substring(8);
			}
			
			map.put("qs", qs.substring(qs.length()-3, qs.length()));
			map.put("bNum", cpFc3dPl3.getKjhmB());
			map.put("sNum", cpFc3dPl3.getKjhmS());
			map.put("gNum", cpFc3dPl3.getKjhmG());
			list.add(map);
			if(list.size() == 8)
				break;
		}
		
		return list;
	}
	
	/**
	 * 获取福彩3D排列3开奖路图
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param results
	 * @return  
	 * String
	 */
	public static String _3qGraph(List<CpFc3dypl3Results> results){
		List<List<Integer>> d1DxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d2DxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d3DxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d1DsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d2DsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> d3DsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> sumDxList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		List<List<Integer>> sumDsList = Collections.synchronizedList(new ArrayList<List<Integer>>());
		String pre1QDx = "";
		String pre2QDx = "";
		String pre3QDx = "";
		String sumPrDx = "";
		
		String pre1QDs = "";
		String pre2QDs = "";
		String pre3QDs = "";
		String sumPrDs = "";
		
		List<Integer> dxl1 = new ArrayList<Integer>();
		List<Integer> dxl2 = new ArrayList<Integer>();
		List<Integer> dxl3 = new ArrayList<Integer>();
		List<Integer> dxSum = new ArrayList<Integer>();
		
		List<Integer> dsl1 = new ArrayList<Integer>();
		List<Integer> dsl2 = new ArrayList<Integer>();
		List<Integer> dsl3 = new ArrayList<Integer>();
		List<Integer> dsSum = new ArrayList<Integer>();
		
		for(int i=results.size()-1; i>=0; i--){
			CpFc3dypl3Results result = results.get(i);
			String _1QDx = result.getbDx();
			String _2QDx = result.getsDx();
			String _3QDx = result.getgDx();
			int sum = result.getHs();
			String sumDx = LotUtil.getFC3DPL3HeDx(sum);
			
			String _1QDs = result.getbDs();
			String _2QDs = result.getsDs();
			String _3QDs = result.getgDs();
			String sumDs = LotUtil.getDs(sum);
			
			dxl1= compareDx(pre1QDx, _1QDx, dxl1, d1DxList);
			dxl2 = compareDx(pre2QDx, _2QDx, dxl2, d2DxList);
			dxl3 = compareDx(pre3QDx, _3QDx, dxl3, d3DxList);
			dxSum = compareDx(sumPrDx, sumDx, dxSum, sumDxList);
			
			dsl1 = compareDs(pre1QDs, _1QDs, dsl1, d1DsList);
			dsl2 = compareDs(pre2QDs, _2QDs, dsl2, d2DsList);
			dsl3 = compareDs(pre3QDs, _3QDs, dsl3, d3DsList);
			dsSum = compareDs(sumPrDs, sumDs, dsSum, sumDsList);
			
			if(i==0){
				d1DxList.add(dxl1);
				d2DxList.add(dxl2);
				d3DxList.add(dxl3);
				sumDxList.add(dxSum);
				
				d1DsList.add(dsl1);
				d2DsList.add(dsl2);
				d3DsList.add(dsl3);
				sumDsList.add(dsSum);
			}
			
			pre1QDx = _1QDx;
			pre2QDx = _2QDx;
			pre3QDx = _3QDx;
			sumPrDx = sumDx;
			
			pre1QDs = _1QDs;
			pre2QDs = _2QDs;
			pre3QDs = _3QDs;
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
		
		JSONObject sumJson = new JSONObject();
		sumJson.put("big_small", sumDxList);
		sumJson.put("odd_even", sumDsList);
		
		JSONObject json = new JSONObject();
		json.put("1", d1qJson);
		json.put("2", d2qJson);
		json.put("3", d3qJson);
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
	 * 统计福彩3D排列3  3位的出球率
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param results
	 * @return  
	 * String
	 */
	public static String reportFC3DPL3BallRate(List<CpFc3dypl3Results> results){
		Map<Integer, Integer> bMap = initMap();
		Map<Integer, Integer> sMap = initMap();
		Map<Integer, Integer> gMap = initMap();
		Map<Integer, Integer> qwMap = initMap();//全五无期数map
		for(int i=0; i< results.size(); i++){
			CpFc3dypl3Results result = results.get(i);
			int bNum = result.getKjhmB();
			int sNum = result.getKjhmS();
			int gNum = result.getKjhmG();
			putNum(bNum, bMap);
			putNum(sNum, sMap);
			putNum(gNum, gMap);
			quwCompare(bNum,sNum,gNum,qwMap);
		}
		
		return htmlReportDatas(bMap,sMap,gMap,qwMap);
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
	
	private static void quwCompare(int bNum,int sNum,int gNum,Map<Integer, Integer> map){
		for(int i=0; i<10;i++){
			if(i==bNum || i==sNum|| i==gNum){
				
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
	 * @param bMap
	 * @param sMap
	 * @param gMap
	 * @param qwMap
	 * @return  
	 * String
	 */
	private static String htmlReportDatas(Map<Integer, Integer> bMap, Map<Integer, Integer> sMap, 
									Map<Integer, Integer> gMap, Map<Integer, Integer> qwMap){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"1\" class=\"game_table\">\n");
		buffer.append("		<tr class=\"tbtitle\" style=\"height:25px\">\n");
		buffer.append("		<th  class=\"ball_ff\" style=\"width:40%\">近30期</th>\n");
		buffer.append("		<th class=\"ball_ff\">0</th>\n");
		buffer.append("	    <th class=\"ball_ff\">1</th>\n");
		buffer.append("		<th class=\"ball_ff\">2</th>\n");
		buffer.append("		<th class=\"ball_ff\">3</th>\n");
		buffer.append("		<th class=\"ball_ff\">4</th>\n");
		buffer.append("		<th class=\"ball_ff\">5</th>\n");
		buffer.append("		<th class=\"ball_ff\">6</th>\n");
		buffer.append("		<th class=\"ball_ff\">7</th>\n");
		buffer.append("		<th class=\"ball_ff\">8</th>\n");
		buffer.append("		<th class=\"ball_ff\">9</th>\n");
		buffer.append("</tr><tr class=\"tbtitle\">\n");
		buffer.append("		<td class=\"ball_ff\">百位XXOXX出球率</td>\n");
		buffer.append("		<td style=\"\">"+bMap.get(0)+"</td>\n");
		buffer.append("		<td style=\"\">"+bMap.get(1)+"</td>\n");
		buffer.append("		<td style=\"color:#ff0000;font-weight:bold\">"+bMap.get(2)+"</td>\n");
		buffer.append("		<td style=\"color:#ff0000;font-weight:bold\">"+bMap.get(3)+"</td>\n");
		buffer.append("		<td style=\"color:#ff0000;font-weight:bold\">"+bMap.get(4)+"</td>\n");
		buffer.append("		<td style=\"\">"+bMap.get(5)+"</td>\n");
		buffer.append("		<td style=\"\">"+bMap.get(6)+"</td>\n");
		buffer.append("		<td style=\"\">"+bMap.get(7)+"</td>\n");
		buffer.append("		<td style=\"\">"+bMap.get(8)+"</td>\n");
		buffer.append("		<td style=\"\">"+bMap.get(9)+"</td>\n");
		buffer.append("</tr><tr class=\"tbtitle\">\n");
		buffer.append("		<td class=\"ball_ff\">拾位XXXOX出球率</td>\n");
		buffer.append("		<td style=\"\">"+sMap.get(0)+"</td>\n");
		buffer.append("		<td style=\"\">"+sMap.get(1)+"</td>\n");
		buffer.append("		<td style=\"\">"+sMap.get(2)+"</td>\n");
		buffer.append("		<td style=\"color:#ff0000;font-weight:bold\">"+sMap.get(3)+"</td>\n");
		buffer.append("		<td style=\"\">"+sMap.get(4)+"</td>\n");
		buffer.append("		<td style=\"\">"+sMap.get(5)+"</td>\n");
		buffer.append("		<td style=\"\">"+sMap.get(6)+"</td>\n");
		buffer.append("		<td style=\"\">"+sMap.get(7)+"</td>\n");
		buffer.append("		<td style=\"color:#ff0000;font-weight:bold\">"+sMap.get(8)+"</td>\n");
		buffer.append("		<td style=\"\">"+sMap.get(9)+"</td>\n");
		buffer.append("</tr><tr class=\"tbtitle\">\n");
		buffer.append("		<td class=\"ball_ff\">個位XXXXO出球率</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(0)+"</td>\n");
		buffer.append("		<td style=\"color:#ff0000;font-weight:bold\">"+gMap.get(1)+"</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(2)+"</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(3)+"</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(4)+"</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(5)+"</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(6)+"</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(7)+"</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(8)+"</td>\n");
		buffer.append("		<td style=\"\">"+gMap.get(9)+"</td>\n");
		buffer.append("</tr><tr class=\"tbtitle\">\n");
		buffer.append("		<td class=\"ball_ff\">全三無出期數</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(0)+"</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(1)+"</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(2)+"</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(3)+"</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(4)+"</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(5)+"</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(6)+"</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(7)+"</td>\n");
		buffer.append("		<td style=\"\">"+qwMap.get(8)+"</td>\n");
		buffer.append("<td style=\"color:#ff0000;font-weight:bold\">"+qwMap.get(9)+"</td>\n");
		buffer.append("</tr></table>\n");
		return buffer.toString();
	}
	
}
