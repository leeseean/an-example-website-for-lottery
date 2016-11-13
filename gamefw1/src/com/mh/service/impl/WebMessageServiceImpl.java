/**   
* 文件名称: WebMessageServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-1 下午5:12:51<br/>
*/  
package com.mh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.orm.Page;
import com.mh.dao.WebMessageDao;
import com.mh.entity.WebMessage;
import com.mh.service.WebMessageService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-1 下午5:12:51<br/>
 */
@SuppressWarnings("all")
@Service
public class WebMessageServiceImpl implements WebMessageService{
	
	@Autowired
	private WebMessageDao webMessageDao;
 
	public void saveOrUpdateWebMessage(WebMessage webMessage){
		this.webMessageDao.save(webMessage);
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
	public int getMessageNum(String userName,Integer msgType,Integer msgStatus){
		return this.webMessageDao.getMessageNum(userName, msgType, msgStatus);
	}
	
	public int getMessageNum(String userName,String msgType,Integer msgStatus){
		return this.webMessageDao.getMessageNum(userName, msgType, msgStatus);
	}

	/**
	 * 得到信息
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * Page
	 */
	public Page getMessageUserName(Page page,WebMessage webMessage) {
		// TODO Auto-generated method stub
		return this.webMessageDao.getMessageUserName(page,webMessage);
	}
	/**
	 * 得到系统信息
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * Page
	 */
	public Page getMessageByAdmin(Page page,WebMessage webMessage) {
		// TODO Auto-generated method stub
		return this.webMessageDao.getMessageByAdmin(page, webMessage);
	}
	/**
	 * 站内消息
	 * 方法描述: TODO</br> 
	 * @param id
	 * @return  
	 * WebMessage
	 */

	public WebMessage findMessageUserName(String userName) {
		// TODO Auto-generated method stub
		return this.webMessageDao.findWebMessage(userName);
	}


	/**
	 * 修改阅读状态
	 * 方法描述: TODO</br> 
	 * @param webMessage 
	 * @return  
	 * int
	 */
	public int updateUserMsgStatus(Integer id){
		return this.webMessageDao.updateUserMsgStatus(id);
	}


	/**
	 * 根据信息id删除信息
	 * 方法描述: TODO</br> 
	 * @param id
	 * @param userName
	 * @return  
	 * Lisrt<WebMessage>
	 */
	public int deleteUserMsg(Integer userId) {
		// TODO Auto-generated method stub
		return this.webMessageDao.deleteUserMsg(userId);
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
		try {
			WebMessage msg = this.webMessageDao.findMessageContent(id);
			/*if(null != msg){
				updateUserMsgStatus(msg.getId());//修改消息状态为已读
			}*/
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	public List<WebMessage> getMessageList(WebMessage msg) {
		return this.webMessageDao.getMessageList(msg);
	}


	public int updateMsgStatus(Integer status,Integer id) {
		return this.webMessageDao.updateMsgStatus(status, id);
	}

}
