package com.mh.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.SharedBaseDao;
import com.mh.entity.WebGdElectronic;
import com.mh.entity.WebNewNtElectronic;
import com.mh.entity.WebTtgElectronic;
@SuppressWarnings("all")
@Repository
public class WebTtgElectronicDao extends SharedBaseDao {
	
	public void insertElectronic(Collection<WebTtgElectronic> entities){
		this.getHibernateTemplate_shared().saveOrUpdateAll(entities);
	}
	
	public void insertGdElectronic(Collection<WebGdElectronic> entities){
		this.getHibernateTemplate_shared().saveOrUpdateAll(entities);
	}
	
	public List<WebTtgElectronic> getTtgList(){
		String hql = " from WebTtgElectronic where 1 = 1";
		return this.getHibernateTemplate_shared().find(hql);
	}
	
	public WebTtgElectronic findElectronic(String gameName){
		String hql = " from WebTtgElectronic where eleGameEnname = ? and status = 1";
		List<WebTtgElectronic> list = this.getHibernateTemplate_shared().find(hql,new Object[]{gameName});
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public void updateGdPic(String picName,int id){
		String sql = "update t_web_gd_electronic set ele_game_pic = '" + picName + "' where id = " + id;
		this.getJdbcTemplate_shared().execute(sql);
	}
	
	public List<WebGdElectronic> getGdList(){
		String hql = " from WebGdElectronic where 1 = 1";
		return this.getHibernateTemplate_shared().find(hql);
	}
	
	public void insertNntElectronic(Collection<WebNewNtElectronic> entities){
		this.getHibernateTemplate_shared().saveOrUpdateAll(entities);
	}
}
