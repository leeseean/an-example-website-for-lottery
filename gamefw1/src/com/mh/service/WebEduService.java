/**   
* 文件名称: WebEduService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 下午2:43:44<br/>
*/  
package com.mh.service;

import java.util.List;
import java.util.Map;

import com.mh.entity.WebEdu;
import com.mh.entity.WebUserFlat;

/** 
 *  额度转化记录Service
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 下午2:43:44<br/>
 */
public interface WebEduService {
	
	/**
	 * 统计额度
	 * 方法描述: TODO</br> 
	 * @return  
	 * int
	 */
	public int getEduTotal(Integer eduType);
	
	/**
	 * 额度转化记录流水列表
	 * 方法描述: TODO</br> 
	 * @param huikuan
	 * @return  
	 * List<WebBankHuikuan>
	 */
	public List<WebEdu> getWebBankHuikuanList(WebEdu webEdu);
	
	
	/**
	 * 更新额度转换
	 * 方法描述: TODO</br> 
	 * @param webEdu
	 * @param webUserFlat
	 * @param flatName
	 * @param optType
	 * @param points
	 * @return  
	 * boolean
	 */
	public Map<String,Object>  updateEdu(WebUserFlat webUserFlat,String flatName,String optType,int points,WebEdu webEdu);

}
