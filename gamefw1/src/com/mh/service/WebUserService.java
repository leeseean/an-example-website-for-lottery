/**   
* 文件名称: LoginService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-2 下午7:16:29<br/>
*/  
package com.mh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mh.entity.TUserPwdLog;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.entity.WebUserLog;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-2 下午7:16:29<br/>
 */
public interface WebUserService {
	
	/**
	 * 根据用户名查询用户信息
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebUser
	 */
	public WebUser findWebrUseByUserName(String userName);
	
	
	public WebUserFlat findWebUserFlatByUserName(String userName);
	
	
	/**
	 * 更新用户
	 * 方法描述: TODO</br> 
	 * @param webUser  
	 * void
	 */
	public void updateWebUser(WebUser webUser);
	
	/**
	 * 更新用户信息
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param webUserFlat  
	 * void
	 */
	public void updateWebUser(WebUser webUser,WebUserLog webUserLog);
	
	
	/**
	 * 新增用户
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * void
	 */
	public void saveWebUser(HttpServletRequest request,WebUser webUser);
	
	
	/**
	 * 根据用户ID查询用户信息
	 * 方法描述: TODO</br> 
	 * @param id
	 * @return  
	 * WebUser
	 */
	public WebUser findWebUserById(Integer id);
	
	/**
	 * 根据用户名密码查询
	 * 方法描述: TODO</br> 
	 * @param loginName
	 * @param password
	 * @return  
	 * WebUser
	 */
	public WebUser findWebrUser(String loginName,String password);

	/**
	 * 获取系统时间 
	 * 方法描述: TODO</br> 
	 * @return  
	 * String
	 */
	public String getSysTime();
	/**
	 * 修改登录密码
	 * 方法描述: TODO</br> 
	 * @param loginName
	 * @param NewPassword
	 * @return  
	 * WebUser
	 */
	public int updatePassword(String loginName,String newPassword);
	/**
	 * 修改资金密码
	 * 方法描述: TODO</br> 
	 * @param loginName
	 * @param newUserWithdrawPassword
	 * @return  
	 * WebUser
	 */
	public int updateMoneyPassword(String loginName,String newUserWithdrawPassword);
	
	
	/**
	 * 统计旗下会员数
	 * 方法描述: TODO</br> 
	 * @param agentNo
	 * @return  
	 * long
	 */
	public int getUserAgentCont(String agentNo);
	
	
	public double getWebUserMoney(String userName);
	
	
	public int updateWebUser(String userName,List<String> fieldList,List<Object> valList);

	public void insertTuserPwdLog(TUserPwdLog log);
	
	
	/**
	 * 获取会员数
	 * 方法描述: TODO</br> 
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Integer> getUserAgentMap();
}
