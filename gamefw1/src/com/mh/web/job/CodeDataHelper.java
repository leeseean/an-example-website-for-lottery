/**   
* 文件名称: CodeDataHelper.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-16 下午2:20:32<br/>
*/  
package com.mh.web.job;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-16 下午2:20:32<br/>
 */
public class CodeDataHelper {
	
	private static List<Map<String,Object>> codeList = new ArrayList<Map<String,Object>>();
	
	private static final Logger logger = LoggerFactory.getLogger(CodeDataHelper.class);
	
	private JdbcTemplate jdbcTemplate;
	
	private HibernateTemplate hibernateTemplate;
	
	
	public  synchronized void initData(){
		logger.info("***********初始化系统字典表开始***********");
		try{
			codeList.clear();
			String sql = "SELECT * FROM t_sys_code  ORDER BY code_type,code_sort ";
			codeList = this.getJdbcTemplate().queryForList(sql);
		}catch(Exception e){
			logger.info("初始化数据出错...");
			e.printStackTrace();
		}
		logger.info("***********初始化系统字典表开始***********");
	}
 
	
	
	/**
	 * 根据字典类型查询字典值、显示名称
	 * 方法描述: TODO</br> 
	 * @param codeType
	 * @return  
	 * Map<String,String>
	 */
	public static Map<String,String> getCodeValueShowNameMapByCodeType(String codeType){
 
		Map<String,Map<String,String>> codeMap = new LinkedHashMap<String, Map<String,String>>();
		for(int i=0;i<codeList.size();i++){
			Map<String,Object> valMap = codeList.get(i);
			String code = valMap.get("code_type").toString();
//			String codeName = valMap.get("code_name").toString();
			String codeValue = valMap.get("code_value").toString();
			String codeShowName = valMap.get("code_show_name").toString();
//			String codeDescript = "";
//			if(valMap.get("codeDescript")!=null){
//				codeDescript = valMap.get("codeDescript").toString();
//			}
			Map<String,String> map = null;
			if(codeMap.containsKey(code)){
				map = codeMap.get(code);
			}else{
				map = new LinkedHashMap<String, String>();
			}
			map.put(codeValue, codeShowName);
			codeMap.put(code, map);
		}
		if(codeMap.get(codeType)!=null){
			return codeMap.get(codeType);
		}
		return null;
		
	}
	
	
	/**
	 * 根据字典类型获取字典名字和字典值
	 * 方法描述: TODO</br> 
	 * @param codeType
	 * @return  
	 * Map<String,String>
	 */
	public static Map<String,String> getCodeNameValueMapByCodeType(String codeType){
		 
		Map<String,Map<String,String>> codeMap = new LinkedHashMap<String, Map<String,String>>();
		for(int i=0;i<codeList.size();i++){
			Map<String,Object> valMap = codeList.get(i);
			String code = valMap.get("code_type").toString();
			String codeName = valMap.get("code_name").toString();
			String codeValue = valMap.get("code_value").toString();
//			String codeShowName = valMap.get("code_show_name").toString();
//			String codeDescript = "";
//			if(valMap.get("codeDescript")!=null){
//				codeDescript = valMap.get("codeDescript").toString();
//			}
			Map<String,String> map = null;
			if(codeMap.containsKey(code)){
				map = codeMap.get(code);
			}else{
				map = new LinkedHashMap<String, String>();
			}
			map.put(codeName, codeValue);
			codeMap.put(code, map);
		}
		if(codeMap.get(codeType)!=null){
			return codeMap.get(codeType);
		}
		return null;
		
	}
	
	
	
	/**
	 * 
	 * 根据字典类型和字典名字查询字典值
	 * 方法描述: TODO</br> 
	 * @param codeType 字典类型
	 * @param codeName 字典名字
	 * @return  
	 * String
	 */
	public static String getCodeValueByCodeTypeAndCodeName(String codeType,String codeName){
		 
		Map<String,String> codeMap = new LinkedHashMap<String, String>();
		for(int i=0;i<codeList.size();i++){
			Map<String,Object> valMap = codeList.get(i);
			String code = valMap.get("code_type").toString();
			String name = valMap.get("code_name").toString();
			String codeValue = valMap.get("code_value").toString();
//			String codeShowName = valMap.get("code_show_name").toString();
			String key = code+"_"+name;
			codeMap.put(key, codeValue);
		}
		if(codeMap.get(codeType+"_"+codeName)!=null){
			return codeMap.get(codeType+"_"+codeName);
		}
		return null;
		
	}
	
	
	/**
	 * 根据字典类型和字典名字查询显示名称
	 * 方法描述: TODO</br> 
	 * @param codeType
	 * @param codeName
	 * @return  
	 * String
	 */                  
	public static String getCodeShowNameByCodeTypeAndCodeName(String codeType, String codeName){
		 
		Map<String,String> codeMap = new LinkedHashMap<String, String>();
		for(int i=0;i<codeList.size();i++){
			Map<String,Object> valMap = codeList.get(i);
			String code = valMap.get("code_type").toString();
			String name = valMap.get("code_name").toString();
//			String codeValue = valMap.get("code_value").toString();
			String codeShowName = valMap.get("code_show_name").toString();
			String key = code+"_"+name;
			codeMap.put(key, codeShowName);
		}
		if(codeMap.get(codeType+"_"+codeName)!=null){
			return codeMap.get(codeType+"_"+codeName);
		}
		return null;
		
	}
	
	
	
	
	
	public CodeDataHelper(JdbcTemplate jdbcTemplate,
			HibernateTemplate hibernateTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.hibernateTemplate = hibernateTemplate;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
