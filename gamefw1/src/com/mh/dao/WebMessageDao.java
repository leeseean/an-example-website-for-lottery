/**   
* 文件名称: WebMessageDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-1 下午5:10:06<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.entity.WebMessage;

/** 
 * 类描述: 站内信息Dao
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-1 下午5:10:06<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebMessageDao extends BaseDao<WebMessage,Integer>{
	
	
	
	/**
	 * 统计信息条数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param msgType
	 * @param msgStatus
	 * @return  
	 * int
	 */
	public int getMessageNum(String userName,Integer msgType,Integer msgStatus){
		List<Object> list = new ArrayList<Object>();
		String sql = " SELECT COUNT(*) AS total FROM t_web_message WHERE read_status=0 ";
		if(!StringUtils.isEmpty(userName)){
			sql += " and USER_NAME= ?";
			list.add(userName);
		}
		if(msgType!=null){
			sql += " and msg_type= ?";
			list.add(msgType);
		}
		if(msgStatus!=null){
			sql += " and msg_status= ?";
			list.add(msgStatus);
		}
		return this.getJdbcTemplate().queryForInt(sql, list.toArray());
		
	}
	
	/**
	 * 统计信息条数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param msgType
	 * @param msgStatus
	 * @return  
	 * int
	 */
	public int getMessageNum(String userName,String msgType,Integer msgStatus){
		String sql = " SELECT COUNT(*) AS total FROM t_web_message WHERE read_status=0 ";
		if(!StringUtils.isEmpty(userName)){
			sql += " and USER_NAME= '"+userName+"'";
		}
		if(msgType!=null){
			sql += " and FIND_IN_SET(msg_type, '"+msgType+"') ";
		}
		if(msgStatus!=null){
			sql += " and msg_status="+msgStatus;
		}
		return this.getJdbcTemplate().queryForInt(sql);
		
	}
	/**
	 * 
	 * @Description: 根据名字查询站内消息  
	 * 方法描述: TODO</br> 
	 * @return WebMessage  
	 * @param userName
	 * @author Channel
	 * @date 2015-7-2
	 */
		public WebMessage findWebMessage(String userName){
			String hql = "from WebMessage a where 1=1 and a.userName=?";
			List<WebMessage> list= this.getHibernateTemplate().find(hql,new String[]{userName});
			return list.size()>0?list.get(0):null;
		}
		
	public List<WebMessage> getMessageList(WebMessage msg){
		List<Object> list = new ArrayList<Object>();
		String hql = "from WebMessage a where 1=1 ";
		
		if(StringUtils.isNotBlank(msg.getUserName())){
			hql += " and a.userName = ?";
			list.add(msg.getUserName());
		}
		if(msg.getMsgType() != null){
			hql += " and a.msgType = ?";
			list.add(msg.getMsgType());
		}
		hql += " and a.msgStatus = 1";//0:停用  1:启用
		hql += " ORDER BY readStatus ASC,createTime DESC";
		return this.getHibernateTemplate().find(hql,list.toArray());
	}
	
	public int updateMsgStatus(Integer status,Integer id){
		String sql = "update  t_web_message set msg_status = ? where id=?";
		return this.getJdbcTemplate().update(sql,new Object[]{status,id});
	}
	
	/**
	 * 得到信息
	 * 方法描述: TODO</br> 
	 * @param Page
	 * @param webMessage 
	 * @return  
	 * Page
	 */
	public Page getMessageUserName(Page page,WebMessage webMessage) {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from t_web_message WHERE 1=1";
		if(StringUtils.isNotEmpty(webMessage.getUserName())){
			sql += " and user_name = ? ";
			list.add(webMessage.getUserName());
		}
		sql += " and msg_status = 1";
		return this.findPageBySql(page,sql,list.toArray());
	
	}
	/**
	 * 得到系统信息
	 * 方法描述: TODO</br> 
	 * @param Page
	 * @param webMessage 
	 * @return  
	 * Page
	 */
	public Page getMessageByAdmin(Page page,WebMessage webMessage) {
		String sql = "select * from t_web_message WHERE 1=1";
		if(StringUtils.isNotEmpty(webMessage.getUserName())){
			sql += " and msg_type =?";
		}
		return this.findPageBySql(page,sql,new Object[]{webMessage.getMsgType()});
	
	}
	/**
	 * 查看消息內容
	 * 方法描述: TODO</br> 
	 * @param id
	 * @param userName
	 * @return  
	 * WebMessage
	 */
	public WebMessage findMessageContent(Integer id) {
           String hql="from WebMessage w WHERE w.id=? ";
           List<WebMessage> list=this.getHibernateTemplate().find(hql, new Object[]{id});
           WebMessage webMessage = null;
   		if(list!=null && list.size()>0){
   			webMessage = list.get(0);
   		}
           return webMessage;
	}
	/**
	 * 修改阅读状态
	 * 方法描述: TODO</br> 
	 * @param webMessage 
	 * @return  
	 * int
	 */
	public int updateUserMsgStatus(Integer id) {
		String sql = "update t_web_message set read_status=1 where id=?";
		return this.getJdbcTemplate().update(sql,new Object[]{id});
	}
	/**
	 * 删除信息 
	 * 方法描述：TODO</br>
	 * 
	 * @param webMessage
	 * @return int
	 * 
	 * */
	public int deleteUserMsg(Integer userId) {
		String sql = "delete from  t_web_message  WHERE id=?";
		return this.getJdbcTemplate().update(sql,new Object[]{userId});
	}
	
}
