/**   
* 文件名称: IfcErrorCode.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-4 下午2:22:36<br/>
*/  
package com.mh.commons.conf;

import java.util.HashMap;
import java.util.Map;

/** 
 * 接口错误代码
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-4 下午2:22:36<br/>
 */
public class IfcErrorCode {
	
	public static Map<String,Map<Integer,String>> ERROR_MAP = new HashMap<String,Map<Integer,String>>();
	static{
		Map<Integer,String> bbiErrorMap = new HashMap<Integer,String>();
		bbiErrorMap.put(0, "成功");
		bbiErrorMap.put(2, "參數錯誤，請檢查參數");
		bbiErrorMap.put(3, "介接廠商帳號錯誤");
		bbiErrorMap.put(5, "驗證碼錯誤");
		bbiErrorMap.put(7, "帳號已存在");
		bbiErrorMap.put(9997, "維護");
		ERROR_MAP.put(WebConstants.FLAT_NAME_BBIN, bbiErrorMap);
	}

}
