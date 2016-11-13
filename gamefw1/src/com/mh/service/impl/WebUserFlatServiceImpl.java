/**   
* 文件名称: WebUserFlatServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-3 下午4:41:01<br/>
*/  
package com.mh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebUserFlatDao;
import com.mh.entity.WebUserFlat;
import com.mh.service.WebUserFlatService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午4:41:01<br/>
 */

@Service
public class WebUserFlatServiceImpl implements  WebUserFlatService{
	
	@Autowired
	private  WebUserFlatDao webUserFlatDao;
	
	
	/**
	 * 添加平台用户信息
	 * 方法描述: TODO</br> 
	 * @param webUserFlat  
	 * void
	 */
	public  void saveOrUpdateWebUserFlat(WebUserFlat webUserFlat){
		this.webUserFlatDao.saveOrUpdate(webUserFlat);
	}
	
	
	/**
	 * 根据用户查询平台用户表
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebUserFlat
	 */
	public WebUserFlat getWebUserFlat(String userName){
		return this.webUserFlatDao.getWebUserFlat(userName);
	}
	public List<WebUserFlat> getWebUserFlat(List<String> userName){
		return this.webUserFlatDao.getWebUserFlat(userName);
	}


	public WebUserFlat selectUserFlatByMgUserName(String mgUserName) {
		return this.webUserFlatDao.selectUserFlatByMgUserName(mgUserName);
	}

}
