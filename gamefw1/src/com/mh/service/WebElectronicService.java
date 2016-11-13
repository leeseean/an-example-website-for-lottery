/**   
* 文件名称: WebElectronicService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-7-11 下午2:42:25<br/>
*/  
package com.mh.service;

import com.mh.commons.orm.Page;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-7-11 下午2:42:25<br/>
 */
@SuppressWarnings("all")
public interface WebElectronicService {
	/**
	 * 获取游戏列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param code
	 * @param gameType1
	 * @param gameType2
	 * @return  
	 * Page
	 */
	public Page findPage(Page page,String code, String gameType1,String gameType2);
	
	
 
}
