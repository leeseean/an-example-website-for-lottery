/**   
* 文件名称: WebMatchDetailDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-18 上午10:30:34<br/>
*/  
package com.mh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.TWebMatchDetail;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-18 上午10:30:34<br/>
 */

@Repository
public class WebMatchDetailDao  extends BaseDao<TWebMatchDetail,Integer>{

	public void saveOrUpdateAll(List<TWebMatchDetail> list){
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}
}
