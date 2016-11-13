/**   
* 文件名称: ActivityUserService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-18 上午4:25:58<br/>
*/  
package com.mh.service;

/** 
 * 
 * 抽奖用户Service接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-18 上午4:25:58<br/>
 */
public interface ActivityUserService {
	
	
	/**
	 * 获取抽奖次数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * int
	 */
	public int getLotteryNums(String userName);
	
	/**
	 * 根据用户名获取活动用户
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * ActivityUser
	 */
	public boolean isLotteryUser(String userName);
	
	
	/**
	 * 更新抽奖次数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * int
	 */
	public int updateActivityUser(String userName);

}
