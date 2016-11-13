/**   
* 文件名称: ActivityLogService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-18 上午5:02:44<br/>
*/  
package com.mh.service;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-18 上午5:02:44<br/>
 */
public interface ActivityLogService {
	
	/**
	 * 获取已中奖奖品数
	 * 方法描述: TODO</br> 
	 * @param prizeId
	 * @return  
	 * int
	 */
	public int getActivityPrizeNum(Integer prizeId,String beginTimeStr,String endTimeStr);

}
