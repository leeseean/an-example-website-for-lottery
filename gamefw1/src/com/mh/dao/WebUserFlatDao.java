/**   
* 文件名称: WebUserFlatDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-3 下午3:36:14<br/>
*/  
package com.mh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.BaseDao;
import com.mh.entity.WebUserFlat;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-3 下午3:36:14<br/>
 */
@Repository
public class WebUserFlatDao  extends BaseDao<WebUserFlat,Integer>{
	
	
	/**
	 * 根据用户查询平台用户表
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebUserFlat
	 */
	public WebUserFlat getWebUserFlat(String userName){
		String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String hql = "from WebUserFlat where userFlag=? and userName=?";
		@SuppressWarnings("unchecked")
		List<WebUserFlat> list = this.getHibernateTemplate().find(hql, new Object[]{userFlag,userName});
		WebUserFlat webUserFlat = null;
		if(list!=null && list.size()>0){
			webUserFlat = list.get(0);
		}
		return webUserFlat;
		
	}
	/**
	 * 根据用户查询平台用户表
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebUserFlat
	 */
	public List<WebUserFlat> getWebUserFlat(List<String> userName){
		//String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String hql = "from WebUserFlat where user_name in ( ? )";
		
		@SuppressWarnings("unchecked")
		List<WebUserFlat> list = this.getHibernateTemplate().findByNamedQueryAndNamedParam(hql, "userName",userName );
		return list;
		
	}
	
	
	
	public WebUserFlat selectUserFlatByMgUserName(String mgUserName){
		String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String hql = "from WebUserFlat where userFlag=? and mg_user_name=?";
		@SuppressWarnings("unchecked")
		List<WebUserFlat> list = this.getHibernateTemplate().find(hql, new Object[]{userFlag,mgUserName});
		WebUserFlat webUserFlat = null;
		if(list!=null && list.size()>0){
			webUserFlat = list.get(0);
		}
		return webUserFlat;
		
	}

}
