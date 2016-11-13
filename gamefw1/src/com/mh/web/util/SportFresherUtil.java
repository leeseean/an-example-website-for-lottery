/**   
* 文件名称: SportFresherUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-18 下午9:17:56<br/>
*/  
package com.mh.web.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-18 下午9:17:56<br/>
 */
@SuppressWarnings("all")
public class SportFresherUtil {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 足球滚球
	 * 方法描述: TODO</br> 
	 * @param oldList
	 * @param newList
	 * @return  
	 * List<TRMatchRE>
	 */
	public static List<TRMatchRE> getRMathRE(List<TRMatchRE> oldList,List<TRMatchRE> newList){
		List<String> paramList = new ArrayList<String>();
		paramList.add("iorMh");
		paramList.add("iorRh");
		paramList.add("iorOuc");
		paramList.add("iorEoo");
		paramList.add("iorHmh");
		paramList.add("iorHrh");
		paramList.add("iorHouc");
		paramList.add("iorMc");
		paramList.add("iorRc");
		paramList.add("iorOuh");
		paramList.add("iorEoe");
		paramList.add("iorHmc");
		paramList.add("iorHrc");
		paramList.add("iorHouh");
		paramList.add("iorMn");
		paramList.add("iorHmn");
 
		
		//比较变色
		if(oldList==null){
			return newList;
		}
		Map<String,TRMatchRE> oldMap = new HashMap<String,TRMatchRE>();
		Map<String,TRMatchRE> newMap = new HashMap<String,TRMatchRE>();
		for(int i=0;i<oldList.size();i++){
			TRMatchRE oldObj = oldList.get(i);
			oldMap.put(oldObj.getGid(), oldObj);
		}
		for(int i=0;i<newList.size();i++){
			TRMatchRE newObj = newList.get(i);
			newMap.put(newObj.getGid(), newObj);
		}
		
		for(int i=0;i<newList.size();i++){
			TRMatchRE obj = newList.get(i);
			String gid = obj.getGid();
			if(oldMap.get(gid)!=null){
				TRMatchRE oldObj = oldMap.get(gid);
				StringBuffer buff = new StringBuffer("");
				for(int k=0;k<paramList.size();k++){
					String val = paramList.get(k);
					String oldVal = getClassValue(oldObj,val);
					String newVal = getClassValue(obj,val);
					if(!oldVal.equals(newVal)){
						buff.append(",");
						buff.append(val);
					}
				}
				if(!"".equals(buff.toString())){
					obj.setFrasherDate(buff.toString());
				}
			}
			
		}
		return newList;
		
	}
	
	/**
	 * 篮球
	 * 方法描述: TODO</br> 
	 * @param oldList
	 * @param newList
	 * @return  
	 * List<TRMatchRemain>
	 */
	public static List<TRMatchRemain> getRMatchRemain(List<TRMatchRemain> oldList,List<TRMatchRemain> newList){
		List<String> paramList = new ArrayList<String>();
		paramList.add("iorMh");
		paramList.add("iorRh");
		paramList.add("iorOuc");
		paramList.add("iorOuho");
		
		paramList.add("iorOuhu");
		paramList.add("iorMc");
		paramList.add("iorRc");
		paramList.add("iorOuh");
		paramList.add("iorOuco");
		paramList.add("iorOucu");
 
 
 
		
		//比较变色
		if(oldList==null){
			return newList;
		}
		Map<String,TRMatchRemain> oldMap = new HashMap<String,TRMatchRemain>();
		Map<String,TRMatchRemain> newMap = new HashMap<String,TRMatchRemain>();
		for(int i=0;i<oldList.size();i++){
			TRMatchRemain oldObj = oldList.get(i);
			oldMap.put(oldObj.getGid(), oldObj);
		}
		for(int i=0;i<newList.size();i++){
			TRMatchRemain newObj = newList.get(i);
			newMap.put(newObj.getGid(), newObj);
		}
		
		for(int i=0;i<newList.size();i++){
			TRMatchRemain obj = newList.get(i);
			String gid = obj.getGid();
			if(oldMap.get(gid)!=null){
				TRMatchRemain oldObj = oldMap.get(gid);
				StringBuffer buff = new StringBuffer("");
				for(int k=0;k<paramList.size();k++){
					String val = paramList.get(k);
					String oldVal = getClassValue(oldObj,val);
					String newVal = getClassValue(obj,val);
					if(!oldVal.equals(newVal)){
						buff.append(",");
						buff.append(val);
					}
				}
				if(!"".equals(buff.toString())){
					obj.setFrasherDate(buff.toString());
				}
			}
			
		}
		return newList;
		
	}
	
	
 
	
	 /**
     * 根据字段名称取值
     * @param obj
     * @param fieldName
     * @return
     */
    public static String getClassValue(Object obj, String fieldName) {
        if (obj == null) {
            return "";
        }
        try {
        	fieldName = fieldName.toUpperCase();
            Class beanClass = obj.getClass();
            Map<String,String> map = new HashMap<String,String>();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                // 非get方法不取
                if (!ms[i].getName().startsWith("get")) {
                    continue;
                }
                Object objValue = null;
                try {
                    objValue = ms[i].invoke(obj, new Object[] {});
                } catch (Exception e) {
                    System.out.println("反射取值出错：" + e.toString());
                    continue;
                }
                if (objValue == null) {
                    continue;
                }
                String name = ms[i].getName().substring(3).toUpperCase();
                map.put(name, objValue.toString());
            }
            if(map.get(fieldName)!=null){
            	return map.get(fieldName);
            }
            return "";
            
        } catch (Exception e) {
        	e.printStackTrace();
            // logger.info("取方法出错！" + e.toString());
        }
        return null;
    }

}
