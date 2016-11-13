/**   
* 文件名称: WebElectronicDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2015-7-11 下午2:57:17<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.conf.WebConstants;
import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.entity.WebMgElectronic;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-7-11 下午2:57:17<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebElectronicDao extends BaseDao{
	
	
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
	public Page findPage(Page page,String code, String gameType1,String gameType2) {
		
		List<Object> list = new ArrayList<Object>();
		String sql = "";
		if(WebConstants.FLAT_NAME_NT.equals(code)){
			sql = "select * from t_web_nt_electronic where 1=1 ";
		}else if(WebConstants.FLAT_NAME_PT.equals(code)){
			sql = "select * from t_web_pt_electronic where 1=1 ";
		}else if(WebConstants.FLAT_NAME_MG.equals(code)){
			sql = "select * from t_web_mg_electronic where 1=1 ";
		}
		
		if (!StringUtils.isEmpty(gameType1)) {
			sql += " and ele_game_type1=?";
			list.add(gameType1);
		}

		if (!StringUtils.isEmpty(gameType2)) {
			sql += " and ele_game_type2=?";
			list.add(gameType2);
		}
		return this.findPageBySql(page, sql, list.toArray());
		
		
	}
	
	public void insertBatch(List<WebMgElectronic> list){
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}
	
	
	public void batchUpdateMg(String sql, List<Object[]> batchArgs){
		this.getJdbcTemplate().batchUpdate(sql, batchArgs);
	}
	
 
}
