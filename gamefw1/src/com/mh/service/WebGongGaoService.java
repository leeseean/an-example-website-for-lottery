/**   
* 文件名称: WebGongGaoService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-21 下午7:32:12<br/>
*/  
package com.mh.service;

import java.util.List;

import com.mh.entity.WebGongGao;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-21 下午7:32:12<br/>
 */
public interface WebGongGaoService {
	
	/**
	 * 查询所有公告信息
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<WebGongGao>
	 */
	public List<WebGongGao> getWebGongGaoList();

}
