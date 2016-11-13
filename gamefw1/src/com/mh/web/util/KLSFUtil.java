/**   
* 文件名称: KLSFUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-6-23 下午4:54:26<br/>
*/  
package com.mh.web.util;

import java.util.List;

import com.mh.entity.CpKlsfResults;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-6-23 下午4:54:26<br/>
 */
public class KLSFUtil {
	
	public static List<CpKlsfResults> klsfGraph(List<CpKlsfResults> results){
		for(int i = 0; i< results.size(); i++){
			CpKlsfResults klsf = results.get(i);
			String qs = klsf.getQs();
			qs = qs.substring(qs.length()-2, qs.length());
			klsf.setQs(qs);
			int zf = klsf.getZf();
			if(zf<=84){
				klsf.setZfdx("小");
			}else{
				klsf.setZfdx("大");
			}
			if(zf%2==0){
				klsf.setZfds("双");
			}else{
				klsf.setZfds("单");
			}
			if((zf%10)<=4){
				klsf.setZfwdx("小");
			}else{
				klsf.setZfwdx("大");
			}
		}
		return results;
	}

}
