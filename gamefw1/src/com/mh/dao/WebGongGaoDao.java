/**   
* 文件名称: WebGongGaoDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-21 下午7:26:38<br/>
*/  
package com.mh.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.WebGongGao;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-21 下午7:26:38<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebGongGaoDao extends BaseDao<WebGongGao,Integer>{

	/**
	 * 查询所有公告信息
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<WebGongGao>
	 */
	public List<WebGongGao> getWebGongGaoList(){
		final String sql = "select * from t_web_gonggao where gg_status=1 order by create_time desc limit 20 ";
		List<WebGongGao> list =this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session){
				SQLQuery sqlQuery = (SQLQuery)session.createSQLQuery(sql).addEntity(WebGongGao.class);
				return sqlQuery.list();
			}
		});
		return list;
	}
}
