/**   
* 文件名称: WebWeihuDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2016-1-14 上午10:15:32<br/>
*/  
package com.mh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2016-1-14 上午10:15:32<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebFlatInfoDao extends BaseDao{
	public void batchUpdateMg(String sql, List<Object[]> batchArgs){
		this.getJdbcTemplate().batchUpdate(sql, batchArgs);
	}
}
