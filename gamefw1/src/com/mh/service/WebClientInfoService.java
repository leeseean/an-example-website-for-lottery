/**   
* 文件名称: WebClientInfoService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2016-1-14 上午10:35:06<br/>
*/  
package com.mh.service;

import java.util.List;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2016-1-14 上午10:35:06<br/>
 */
public interface WebClientInfoService {
	public void updateBatchWeihu(List<Object[]> batchArgs);
	
	public void updateBatchFlatInfo(List<Object[]> batchArgs);

}
