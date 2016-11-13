/**   
* 文件名称: LoginServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-2 下午7:16:40<br/>
*/  
package com.mh.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.SysParameterDao;
import com.mh.dao.TUserPwdLogDao;
import com.mh.dao.WebAgentDao;
import com.mh.dao.WebUserDao;
import com.mh.dao.WebUserFlatDao;
import com.mh.dao.WebUserLogDao;
import com.mh.entity.SysParameter;
import com.mh.entity.TUserPwdLog;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.entity.WebUserLog;
import com.mh.service.WebUserService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-2 下午7:16:40<br/>
 */
@Service
public class WebUserServiceImpl implements WebUserService{
	
 
	@Autowired
	private WebUserDao webUserDao;
	@Autowired
	private WebUserFlatDao webUserFlatDao;
	@Autowired
	private WebUserLogDao webUserLogDao;
	@Autowired
	private SysParameterDao sysParameterDao;
	@Autowired
	private WebAgentDao webAgentDao;
	@Autowired
	private TUserPwdLogDao pwdLogDao;
	
	
	public WebUserFlat findWebUserFlatByUserName(String userName){
		return this.webUserFlatDao.getWebUserFlat(userName);
	}
	 
	
	/**
	 * 新增用户
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param webUserFlat  
	 * void
	 */
	public void saveWebUser(HttpServletRequest request,WebUser webUser){
		SysParameter paramter = this.sysParameterDao.getSysParameterByPramName("web_flag_name");
		String webName = "";
		if(paramter!=null){
			webName = paramter.getParamName();
		}
		
		WebUserLog webUserLog = new WebUserLog();
		webUserLog.setCreateTime(new Date());
		webUserLog.setLogWebFlag(webUser.getUserFlag());// 网站标识
		webUserLog.setLogWebUserName(webUser.getUserName());// 帐号
		webUserLog.setLogAddress(webUser.getUserLastLoginIp());// 地址
		webUserLog.setLogWebName(webName);// 网站名称
		webUserLog.setLogWebDomain(this.getWebDomain(request));
		
		webUserLogDao.saveOrUpdate(webUserLog);
 
		//添加主帐号
		WebUserFlat webUserFlat = new WebUserFlat();
		webUserFlat.setUserFlag(webUser.getUserFlag());
		webUserFlat.setUserName(webUser.getUserName());
		webUserFlat.setUserAgent(webUser.getUserAgent());
		
		this.webUserFlatDao.saveOrUpdate(webUserFlat);
		this.webUserDao.saveOrUpdate(webUser);
		/** 代理线加数 **/
		this.webAgentDao.updateAgentCount(webUser.getUserAgent());
	}
	
	
	/**
	 * 更新用户
	 * 方法描述: TODO</br> 
	 * @param webUser  
	 * void
	 */
	public void updateWebUser(WebUser webUser){
		this.webUserDao.update(webUser);
	}
	
	
	
	public int updateWebUser(String userName,List<String> fieldList,List<Object> valList){
		StringBuffer buffer = new StringBuffer("");
		for(int i=0;i<fieldList.size();i++){
			String field=fieldList.get(i);
			if(i>0){
				buffer.append(",");
			}
			buffer.append(field);
			buffer.append("=");
			buffer.append("?");
		}
		String sql = " update t_web_user set "+buffer.toString()+" where user_name='"+userName+"' ";
 
		return  this.webUserDao.updateWebUser(sql,valList);
	}
	
	/**
	 * 更新用户信息
	 * 方法描述: TODO</br> 
	 * @param webUser
	 * @param webUserFlat  
	 * void
	 */
	public void updateWebUser(WebUser webUser,WebUserLog webUserLog){
		//this.webUserDao.update(webUser);
		this.webUserLogDao.saveOrUpdate(webUserLog);
	}
	
	
	
	/**
	 * 根据用户ID查询用户信息
	 * 方法描述: TODO</br> 
	 * @param id
	 * @return  
	 * WebUser
	 */
	public WebUser findWebUserById(Integer id){
		return this.webUserDao.get(id);
	}
	
	
	/**
	 * 获取系统时间 
	 * 方法描述: TODO</br> 
	 * @return  
	 * String
	 */
	public String getSysTime(){
		return this.webUserDao.getSysTime();
	}
	
	
	
	/**
	 * 根据用户名查询用户信息
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebUser
	 */
	public WebUser findWebrUseByUserName(String userName){
		return this.webUserDao.findWebrUseByUserName(userName);
	}
	
	public double getWebUserMoney(String userName){
		return this.webUserDao.getWebUserMoney(userName);
	}
	
 
	
	
	/**
	 * 根据用户名密码查询
	 * 方法描述: TODO</br> 
	 * @param loginName
	 * @param password
	 * @return  
	 * WebUser
	 */
	public WebUser findWebrUser(String loginName,String password){
		return this.webUserDao.findWebrUser(loginName, password);
	}


	/**
	 * 修改登录密码
	 * 方法描述: TODO</br> 
	 * @param loginName
	 * @param NewPassword
	 * @return  
	 * WebUser
	 */
	public int updatePassword(String loginName,String newPassword){
		return this.webUserDao.updatePassword(loginName, newPassword);
	}

	/**
	 * 修改资金密码
	 * 方法描述: TODO</br> 
	 * @param loginName
	 * @param NewPassword
	 * @return  
	 * WebUser
	 */

	public int updateMoneyPassword(String loginName,String newUserWithdrawPassword) {
		// TODO Auto-generated method stub
		return this.webUserDao.updateMoneyPassword(loginName, newUserWithdrawPassword);
	}
	
	/**
	 * 统计旗下会员数
	 * 方法描述: TODO</br> 
	 * @param agentNo
	 * @return  
	 * long
	 */
	public int getUserAgentCont(String agentNo){
		return this.webUserDao.getUserAgentCont(agentNo);
	}
	
	private String getWebDomain(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		if (basePath.endsWith(":80/")) {
			basePath = basePath.substring(0, basePath.indexOf(":80/")) + "/";
		}
		return basePath;
	}

	public void insertTuserPwdLog(TUserPwdLog log) {
		this.pwdLogDao.saveOrUpdate(log);
	}
	
	/**
	 * 获取会员数
	 * 方法描述: TODO</br> 
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Integer> getUserAgentMap(){
		return this.webUserDao.getUserAgentMap();
	}
	
}
