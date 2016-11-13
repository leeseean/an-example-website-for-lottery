/**   
* 文件名称: TemplateHelper.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2016-1-9 上午4:00:17<br/>
*/  
package com.mh.web.job;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import app.xb.cmbase.model.CpBigType;
import app.xb.cmbase.model.CpType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mh.commons.utils.HtmlCompressor;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2016-1-9 上午4:00:17<br/>
 */
@SuppressWarnings("all")
public class TemplateHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(LotteryDataHelper.class);
	
	private JdbcTemplate jdbcTemplate;

 
	
	private String basePath = "";
	
	public void initData(){
		initAllTemplateData();
		initBetTemplateData();
		
	}
	
	
	
	public String initAllTemplateData(){
		
		String realPath = basePath+"commons/cp/template/all/";
		List<String> typeList = this.getAllTemplateList();
		StringBuilder allBuiler = new StringBuilder("");
		StringBuilder contBuilder= new StringBuilder("");
		
		allBuiler.append("{");
		for(int i=0;i<typeList.size();i++){
			String allName = typeList.get(i);
			String path = realPath+allName+".html";
			StringBuffer sbuff = new StringBuffer();
			try {
				BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					sbuff.append(inputLine);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(i>0){
				allBuiler.append(",");
				contBuilder.append("êêê");
			}
			allBuiler.append("\"").append(allName).append("\"");
			allBuiler.append(":");
			allBuiler.append(i+1);
//			String content=sbuff.toString().replaceAll("\r|\n","");//去除
			String content = sbuff.toString();
			contBuilder.append(content);
//			//读js
			String jspath = basePath+"commons/cp/js/all/"+allName+".js";
			File file = new File(jspath);
			if(file.exists()){
				StringBuffer jsBuffer = new StringBuffer();
				try {
					BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(jspath),"UTF-8"));
					String inputLine = null;
					while ((inputLine = reader.readLine()) != null) {
						jsBuffer.append(inputLine);
						jsBuffer.append("\n");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(!"".equals(jsBuffer.toString())){
					contBuilder.append("<script>");
//					contBuilder.append(jsBuffer.toString().replaceAll("\r|\n",""));
					contBuilder.append(HtmlCompressor.compressJavaScript(jsBuffer.toString().replaceAll("[\r\n]", " ").replaceAll("[\t]", " ")));
//					contBuilder.append(jsBuffer.toString());
					contBuilder.append("</script>");
				}
			}
		}
		logger.info("加载框架模板成功,共"+typeList.size()+"个模板！");
		allBuiler.append("}");
		StringBuilder builder = new StringBuilder("");
		builder.append(allBuiler.toString());
		builder.append("êêê");
//		builder.append(contBuilder.toString());
		builder.append(HtmlCompressor.processHtml(contBuilder.toString()));
		String outPath = basePath+"commons/cp/template/all.html";
		logger.info("模板路径："+outPath);
		OutputStreamWriter write=null;
		BufferedWriter bufw = null;
		try {
			write = new OutputStreamWriter(new FileOutputStream(outPath), "UTF-8");
			bufw = new BufferedWriter(write);
	        bufw.write(builder.toString());
	        bufw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (bufw != null) {
					bufw.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return builder.toString();
	}
	
	
	/**
	 * 初始化玩法模板
	 * 方法描述: TODO</br> 
	 * @param config  
	 * void
	 */
	public String initBetTemplateData(){
		String realPath = basePath+"commons/cp/template/bet/";
		Map<String,List<String>> itemMap = this.getGameItemMapList();
		List<String> typeList = this.getBigTypeList();
		int betIndex=1;
		StringBuilder betBuiler = new StringBuilder("");
		StringBuilder contBuilder= new StringBuilder("");
		
		betBuiler.append("{");
		for(int i=0;i<typeList.size();i++){
			String typeCode = typeList.get(i);
			if( itemMap.get(typeCode)==null){
				continue;
			}
			List<String> itemList = itemMap.get(typeCode);
			for(int j=0;j<itemList.size();j++){
				String itemCode = itemList.get(j);
				String path = realPath+"/"+typeCode.toLowerCase()+"/"+itemCode.toLowerCase()+".html";
				File file = new File(path);
				if(!file.exists()){
					continue;
				}
				
				
				StringBuffer sbuff = new StringBuffer();
				try {
					BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
					String inputLine = null;
					while ((inputLine = reader.readLine()) != null) {
						sbuff.append(inputLine);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				String[] betArr = sbuff.toString().split("êêê");
				String betJson = betArr[0];
				JSONArray jarray = JSON.parseArray(betJson);
				for(int k=0;k<jarray.size();k++){
					Map map1 = JSON.parseObject(jarray.getString(k)); 
				    for (Object o : map1.entrySet()) { 
				      Map.Entry<String,Object> entry = (Map.Entry<String,Object>)o; 
				      String key = entry.getKey();
				      int val = Integer.valueOf(entry.getValue().toString());
				      if(betIndex>1){
				    	  betBuiler.append(",");
				      }
				      String content = betArr[val];
//				      content=content.replaceAll("\r|\n","");//去除
				      betBuiler.append("\"").append(key).append("\"");
				      betBuiler.append(":");
				      betBuiler.append(betIndex);
				      betIndex++;
				      contBuilder.append("êêê");
					  try {
						contBuilder.append(HtmlCompressor.compress(content));
					} catch (Exception e) {
						e.printStackTrace();
					}
				    } 
				}
			}
		}
		betBuiler.append("}");
		StringBuilder builder = new StringBuilder("");
		builder.append(betBuiler.toString());
		builder.append(contBuilder.toString());
		String outPath = basePath+"commons/cp/template/all_bet.html";
		OutputStreamWriter write=null;
		BufferedWriter bufw = null;
		try {
			write = new OutputStreamWriter(new FileOutputStream(outPath), "UTF-8");
			bufw = new BufferedWriter(write);
	        bufw.write(builder.toString());
	        bufw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (bufw != null) {
					bufw.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return builder.toString();
	}
	
	
	
	public List<String> getBigTypeList(){
//		String sql = "select bigtype_code from cp_bigtype  order by pxh";
 
//		List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
		List<String> valList = new ArrayList<String>();
//		for(int i=0;i<list.size();i++){
//			Map<String,Object> map = list.get(i);
//			valList.add(map.get("bigtype_code").toString());
//		}
		CpCacheHelper helper = new CpCacheHelper();
		List<CpBigType> dataList = helper.getCpBigtypeList();
		CpBigType bigType = null;
		for(int i=0;i<dataList.size();i++){
			bigType = dataList.get(i);
			valList.add(bigType.getBigtypeCode());
		}
		
		
		return valList;
	}
	
	public Map<String,List<String>> getGameItemMapList(){
		Map<String,List<String>> valMap = new HashMap<String,List<String>>();
	/*	String sql = "select BIGTYPE_CODE,CP_TYPE_CODE from  cp_type  order by BIGTYPE_CODE,PXH";
		List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			String typeCode = map.get("CP_TYPE_CODE").toString();
			String bigtypeCode = map.get("BIGTYPE_CODE").toString();
			List<String> valList = null;
			if(valMap.containsKey(bigtypeCode)){
				valList = valMap.get(bigtypeCode);
			}else{
				valList = new ArrayList<String>();
			}
			valList.add(typeCode);
			valMap.put(bigtypeCode, valList);
		}
		return valMap;*/
		CpCacheHelper helper = new CpCacheHelper();
		List<CpType> typeList = helper.getCpTypeList();
		CpType cpType = null;
		for(int i=0;i<typeList.size();i++){
			cpType = typeList.get(i);
			String bigTypeCode = cpType.getBigtypeCode();
			String cpTypeCode = cpType.getCpTypeCode();
			List<String> valList = null;
			if(valMap.containsKey(bigTypeCode)){
				valList = valMap.get(bigTypeCode);
			}else{
				valList = new ArrayList<String>();
			}
			valList.add(cpTypeCode);
			valMap.put(bigTypeCode, valList);
		}
		
		return valMap;
		
	}
	
	
	
	
	
	
	public List<String> getAllTemplateList(){
		List<String> valList = new ArrayList<String>();
		valList.add("fastbet");
		valList.add("changlong");
		valList.add("transRecord");
//		valList.add("betDetail");
//		valList.add("betReport");
//		valList.add("openHistory");
//		valList.add("gameRule");
		return valList;
	}
	
	
	public TemplateHelper(JdbcTemplate jdbcTemplate,String basePath) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.basePath = basePath;
	}


}
