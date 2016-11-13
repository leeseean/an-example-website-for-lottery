/**   
* 文件名称: MatchSettledDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-17 下午3:27:10<br/>
*/  
package com.mh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.TMatchSettled;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-17 下午3:27:10<br/>
 */

@Repository
public class WebMatchSettledDao  extends BaseDao<TMatchSettled,String>{

	public void saveOrUpdateAll(List<TMatchSettled> list){
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}
}
