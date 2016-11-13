/**   
* 文件名称: WebTestUserServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-26 下午1:45:18<br/>
*/  
package com.mh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebTestUserDao;
import com.mh.entity.WebTestUser;
import com.mh.service.WebTestUserService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-26 下午1:45:18<br/>
 */
@Service
public class WebTestUserServiceImpl implements WebTestUserService{
	
	@Autowired
	private WebTestUserDao webTestUserDao;

	
	/**
	 * 更新试用账号
	 * 方法描述: TODO</br> 
	 * @param webTestUser  
	 * void
	 */
	public void saveOrUpdateWebTestUser(WebTestUser webTestUser){
		this.webTestUserDao.saveOrUpdate(webTestUser);
	}
	
	/**
	 * 根据ID查询试用账号
	 * 方法描述: TODO</br> 
	 * @param id
	 * @return  
	 * WebTestUser
	 */
	public WebTestUser getWebTestUserById(Integer id){
		return this.webTestUserDao.get(id);
	}
	
	
	
	/**
	 * 根据用户名查询试用账号
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebTestUser
	 */
	public WebTestUser getWebTestUserByUserName(String userName){
		return this.webTestUserDao.getWebTestUserByUserName(userName);
	}
	
	
	/**
	 * 获取用户名最大值
	 * 方法描述: TODO</br> 
	 * @return  
	 * int
	 */
	public int getMaxUserIndex(){
		return this.webTestUserDao.getMaxUserIndex();
	}
}
