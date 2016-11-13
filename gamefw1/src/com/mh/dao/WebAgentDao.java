/**   
 * 文件名称: WebAgentDao.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: Channel<br/>  
 * 创建时间 : 2015-7-2 上午10:29:38<br/>
 */
package com.mh.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.entity.WebAgent;
import com.mh.entity.WebAgentTuishui;
import com.mh.entity.WebAgentTuiyong;

/**
 * 
 * 代理Dao接口
 * 类描述: TODO<br/>
 * 代理中心dao 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-2 上午10:29:38<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebAgentDao extends BaseDao<WebAgent, Integer> {
	
	
	/**
	 * 代理列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param webAgent
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, WebAgent webAgent) {
		List<Object> list = new ArrayList<Object>();

		String sql = "SELECT t.*  FROM t_web_agent t where 1=1  ";
		 
		if(!StringUtils.isEmpty(webAgent.getUserName())){
			sql += " and t.user_name like ? ";
			list.add("%"+webAgent.getUserName()+"%");
		}
		
		if(webAgent.getStatus()!=null && webAgent.getStatus()!=-1){
			sql += " and t.status like ? ";
			list.add(webAgent.getStatus());
		}
		
		sql+=" order by t.create_time desc ";
		
		return findPageBySql(page, sql, list.toArray());
	}
	

	/**
	 * 
	 * @Description: 根据名字查询代理人的信息
	 * @param
	 * @return List<WebAgent>
	 * @throws
	 * @author Channel
	 * @date 2015-7-2
	 */
	public WebAgent findWebAgent(String userName) {
		String hql = "from WebAgent a where 1=1 and a.userName=?";
		List<WebAgent> list = this.getHibernateTemplate().find(hql,new String[] { userName });
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 
	 * @Description: 查询代理人旗下会员信息
	 * @param
	 * @return Page
	 * @throws
	 * @author Channel
	 * @date 2015-7-2
	 */
	public Page findWebAgentUsers(Page page, String agentName, String userName) {
		String hql = "select * from t_web_user WHERE user_agent='"+agentName+"' ";
		if(StringUtils.isNotEmpty(userName)){
			hql += " and user_name like '%"+userName+"%' ";
		}
		hql += " order by create_time desc";
		return this.findPageBySql(page, hql);

	}

	/**
	 * 
	 * @Description:查询信息
	 * @param
	 * @return List<WebAgent>
	 * @throws
	 * @author Channel
	 * @date 2015-7-2
	 */
	public List<WebAgent> findWebAgentReport(WebAgent webAgent) {
		List<Object> list = new ArrayList<Object>();
		String hql = " from WebAgent where userName=? ";
		list.add(webAgent.getUserName());
		return this.find(hql, list.toArray());
	}
	
	
	/***
	 * 根据代理no 查询代理信息
	 * 方法描述: TODO</br> 
	 * @param angetNo
	 * @return  
	 * @author alex
	 * WebAgent
	 */
	public WebAgent getWebAgentByAngetNo(String angetNo){
		String hql = "from WebAgent where  agentNo=? and status=1";
		List<WebAgent> list= this.find(hql, new Object[]{angetNo});
		WebAgent webAgent = null;
		if(list!=null && list.size()>0){
			webAgent = list.get(0);
		}
		return webAgent;
	}
	
	 
	 /**
	  * 注册代理累加</br>
	  * 创建人: zoro<br/> 
	  * @param userName  
	  * void
	  */
	public void updateAgentCount(String userName){
		String sql = "UPDATE t_web_agent SET COUNT = COUNT+1 WHERE user_name = '"+userName+"'";
		 this.getJdbcTemplate().update(sql);
	}
	
	public Page findWebAgentList(Page page, String agentName){
		String hql = "select * from t_web_agent WHERE 1=1 ";
		if(StringUtils.isNotEmpty(agentName)){
			hql += " and user_name='"+agentName+"' ";
		}
		hql += " order by count desc";
		return this.findPageBySql(page, hql);
	}
	
	public Page findWebAgentListFuzzy(Page page, String agentName){
		String hql = "select * from t_web_agent WHERE 1=1 ";
		if(StringUtils.isNotEmpty(agentName)){
			hql += " and user_name like '%"+agentName.replaceAll("'", "''")+"%'";
		}
		hql += " order by count desc";
		return this.findPageBySql(page, hql);
	}
	
	public Map<String, Object> findAgentType(String agentName){
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT * FROM  t_web_agent_type WHERE id = (SELECT agent_type FROM t_web_agent WHERE user_name=?)";
		list.add(agentName);
		return this.getJdbcTemplate().queryForMap(sql, list.toArray());
	}
	
	public List<WebAgentTuiyong> findWebAgentTuiyong(int agentType){
		List<Object> list = new ArrayList<Object>();
		String hql = " from WebAgentTuiyong WHERE tyType=? order by tyBegin ";
		list.add(agentType);
		return this.getHibernateTemplate().find(hql, list.toArray());
	}
	
	public List<WebAgentTuishui> findWebAgentTuishui(int agentType){
		List<Object> list = new ArrayList<Object>();
		String hql = " from WebAgentTuishui WHERE tyType=? order by tyBegin";
		list.add(agentType);
		return this.getHibernateTemplate().find(hql, list.toArray());
	}
}