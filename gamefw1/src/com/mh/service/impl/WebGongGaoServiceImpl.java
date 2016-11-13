/**   
* 文件名称: WebGongGaoServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-21 下午7:32:42<br/>
*/  
package com.mh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebGongGaoDao;
import com.mh.entity.WebGongGao;
import com.mh.service.WebGongGaoService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-21 下午7:32:42<br/>
 */
@Service
public class WebGongGaoServiceImpl implements WebGongGaoService{

	@Autowired
	private WebGongGaoDao webGongGaoDao;
	
	/**
	 * 查询所有公告信息
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<WebGongGao>
	 */
	public List<WebGongGao> getWebGongGaoList(){
		return this.webGongGaoDao.getWebGongGaoList();
	}
}
