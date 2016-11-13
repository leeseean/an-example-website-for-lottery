/**   
* 文件名称: WebMessageService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-1 下午5:11:54<br/>
*/  
package com.mh.service;

import java.util.List;

import com.mh.commons.orm.Page;
import com.mh.entity.WebMessage;

/** 
 * 类描述: TODO<br/>站内信息Service
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-1 下午5:11:54<br/>
 */
@SuppressWarnings("all")
public interface WebMessageService {
	
	public void saveOrUpdateWebMessage(WebMessage webMessage);
	
	
	/**
	 * 统计信息条数
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param msgType
	 * @param msgStatus
	 * @return  
	 * int
	 */
	public int getMessageNum(String userName,Integer msgType,Integer msgStatus);
	public int getMessageNum(String userName,String msgType,Integer msgStatus);
	
	/**
	 * 得到站内信息
	 * 方法描述: TODO</br> 
	 * @param id
	 * @return  
	 * WebMessage
	 */
	public WebMessage findMessageUserName(String userName);
	
	public List<WebMessage> getMessageList(WebMessage msg);

	/**
	 * 查看消息內容
	 * 方法描述: TODO</br> 
	 * @param id
	 * @param userName
	 * @return  
	 *WebMessage
	 */
	public WebMessage findMessageContent(Integer id);
	
	/**
	 * 得到信息分页
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param page
	 * @return  
	 * WebMessage
	 */
	public Page getMessageUserName(Page page,WebMessage userName);

	/**
	 * 得到系统信息
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * Page
	 */
	public Page getMessageByAdmin(Page page,WebMessage webMessage);
	/**
	 * 修改阅读状态
	 * 方法描述: TODO</br> 
	 * @param webMessage 
	 * @return  
	 * int
	 */
	public int updateUserMsgStatus(Integer id);
	
	/**
	 * 修改信息停启用状态
	 * @param id
	 * @return
	 */
	public int updateMsgStatus(Integer status,Integer id);
	/**
	 * 删除信息 方法描述：TODO</br>
	 * 
	 * @param webMessage
	 * @return int
	 * 
	 * */
	public int deleteUserMsg(Integer userId);
}
