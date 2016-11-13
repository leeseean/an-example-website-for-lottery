/**   
* 文件名称: WebElectronicServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-7-11 下午2:55:51<br/>
*/  
package com.mh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.orm.Page;
import com.mh.dao.WebElectronicDao;
import com.mh.service.WebElectronicService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-7-11 下午2:55:51<br/>
 */
@SuppressWarnings("all")
@Service
public class WebElectronicServiceImpl implements WebElectronicService {

	@Autowired
	private WebElectronicDao webElectronicDao;
	
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
	public Page findPage(Page page,String code, String gameType1,String gameType2){
		return this.webElectronicDao.findPage(page, code, gameType1, gameType2);
	}
 

}
