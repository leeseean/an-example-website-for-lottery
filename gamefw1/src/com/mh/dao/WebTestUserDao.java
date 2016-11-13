/**   
* 文件名称: WebTestUserDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-26 下午1:43:57<br/>
*/  
package com.mh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.WebTestUser;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-26 下午1:43:57<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebTestUserDao extends BaseDao<WebTestUser, Integer> {


	/**
	 * 根据用户名查询试用账号
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebTestUser
	 */
	public WebTestUser getWebTestUserByUserName(String userName){
		String hql = "from WebTestUser where userName=? ";
		List<WebTestUser> list = this.find(hql, new Object[]{userName});
		WebTestUser webTestUser = null;
		if(list!=null && list.size()>0){
			webTestUser = list.get(0);
		}
		return webTestUser;
		
	}
	
	
	/**
	 * 获取用户名最大值
	 * 方法描述: TODO</br> 
	 * @return  
	 * int
	 */
	public int getMaxUserIndex(){
		String sql = "SELECT MAX(SUBSTRING(user_name,3)) AS userIndex FROM t_web_test_user ";
		Map<String,Object> map = this.getJdbcTemplate().queryForMap(sql);
		int userIndex = 100000;
		if(map!=null && map.get("userIndex")!=null && !"".equals(map.get("userIndex"))){
			userIndex = Integer.valueOf(map.get("userIndex").toString());
		}
		return userIndex;
	}
}
