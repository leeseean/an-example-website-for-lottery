/**   
* 文件名称: WebTestUserService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-26 下午1:45:01<br/>
*/  
package com.mh.service;

import com.mh.entity.WebTestUser;

/** 
 * 
 * 测试账号Service接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-26 下午1:45:01<br/>
 */
public interface WebTestUserService {
	

	/**
	 * 更新试用账号
	 * 方法描述: TODO</br> 
	 * @param webTestUser  
	 * void
	 */
	public void saveOrUpdateWebTestUser(WebTestUser webTestUser);
	
	
	/**
	 * 根据ID查询试用账号
	 * 方法描述: TODO</br> 
	 * @param id
	 * @return  
	 * WebTestUser
	 */
	public WebTestUser getWebTestUserById(Integer id);
	
	
	
	/**
	 * 根据用户名查询试用账号
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebTestUser
	 */
	public WebTestUser getWebTestUserByUserName(String userName);
	
	
	/**
	 * 获取用户名最大值
	 * 方法描述: TODO</br> 
	 * @return  
	 * int
	 */
	public int getMaxUserIndex();

}
