/**   
* 文件名称: WebUserDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-5 下午1:04:03<br/>
*/  
package com.mh.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.BaseDao;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.WebUser;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-5 下午1:04:03<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebUserDao extends BaseDao<WebUser,Integer>{

	
	/**
	 * 根据用户名查询用户信息
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * WebUser
	 */
	public WebUser findWebrUseByUserName(String userName){
		String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		WebUser user = null;
		String sql = "select t.* from t_web_user t where LOWER(t.user_name)=? and user_flag=? ";
		List<Map<String,Object>> valList = this.getJdbcTemplate().queryForList(sql,new Object[]{userName,userFlag});
		if(valList!=null && valList.size()>0){
			Map<String,Object> valMap = valList.get(0);
			user = new WebUser();
			user.setId(Integer.valueOf(valMap.get("id").toString()));
			user.setUserName(valMap.get("user_name").toString());
			user.setUserFlag(valMap.get("user_flag") == null?"":valMap.get("user_flag").toString());
			user.setUserPassword(valMap.get("user_password") ==null?"":valMap.get("user_password").toString());
			user.setUserStatus(Integer.valueOf(valMap.get("user_status").toString()));
			user.setUserType(Integer.valueOf(valMap.get("user_type").toString()));
			user.setUserMoney(valMap.get("user_money")==null?0:Double.valueOf(valMap.get("user_money").toString()));
			user.setUserRemark(valMap.get("user_remark") == null? "":valMap.get("user_remark").toString());
			
			user.setUserLastLoginIp(valMap.get("user_last_login_ip") == null? "":valMap.get("user_last_login_ip").toString());
			user.setUserLoginTimes(Integer.valueOf(valMap.get("user_login_times").toString()));
			
			user.setUserMail(valMap.get("user_mail") == null? "":valMap.get("user_mail").toString());
			user.setUserQq(valMap.get("user_qq") == null? "":valMap.get("user_qq").toString());
			user.setUserMobile(valMap.get("user_mobile") == null? "":valMap.get("user_mobile").toString());
			
			user.setUserRealName(valMap.get("user_real_name") == null? "":valMap.get("user_real_name").toString());
			user.setUserBankType(valMap.get("user_bank_type") == null? "":valMap.get("user_bank_type").toString());
			user.setUserBankCard(valMap.get("user_bank_card") == null? "":valMap.get("user_bank_card").toString());
			user.setUserBankAddress(valMap.get("user_bank_address") == null? "":valMap.get("user_bank_address").toString());
			user.setUserWithdrawPassword(valMap.get("user_withdraw_password") == null? "":valMap.get("user_withdraw_password").toString());
			
			user.setUserPsTimes(valMap.get("user_ps_times")==null?0:Integer.valueOf(valMap.get("user_ps_times").toString()));
			user.setUserAgent(valMap.get("user_agent") == null? "":valMap.get("user_agent").toString());
			user.setMineAgent(valMap.get("mine_agent") == null? "":valMap.get("mine_agent").toString());
			user.setUserSessionId(valMap.get("user_session_id") == null? "":valMap.get("user_session_id").toString());
			user.setBetFlat(valMap.get("bet_flat") == null? "":valMap.get("bet_flat").toString());
			Date createTime = null;
			Date modifyTime = null;
			Date userLastLoginTime = null;
			try {
				
				createTime = DateUtil.parse(valMap.get("create_time").toString(), "yyyy-MM-dd HH:mm:ss");
				modifyTime = DateUtil.parse(valMap.get("modify_time").toString(), "yyyy-MM-dd HH:mm:ss");
				userLastLoginTime = DateUtil.parse(valMap.get("user_last_login_time").toString(), "yyyy-MM-dd HH:mm:ss");
				
				user.setCreateTime(createTime);
				user.setModifyTime(modifyTime);
				user.setUserLastLoginTime(userLastLoginTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			user.setRegistIp(valMap.get("regist_ip") == null? "":valMap.get("regist_ip").toString());
 
		}
		
		
		return user;
	}
	
 
	/**
	 * @Description: 更新用户账户余额
	 * @param  String userName,double money
	 * @return int
	 * @author Andy
	 * @date 2015-6-9
	 */
	public int updateWebUserForMoney(String userName,double money){
		logger.info(userName+"充值金额:"+money);
		String sql=" update t_web_user set user_money=user_money+"+money+" where LOWER(user_name)='"+userName+"' and if(user_money+"+money+">=0,true,false)";
		int rows = this.getJdbcTemplate().update(sql);
		logger.info("执行:"+sql);
		logger.info("返回行数："+rows);
		
		return rows;
		
	}
	
	public int updateWebUser(String sql,List<Object> valList){
		
		return this.getJdbcTemplate().update(sql, valList.toArray());
	}
	
	
	/**
	 * 获取用户余额
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * double
	 */
	public double getWebUserMoney(String userName){
		String sql = "SELECT ROUND(user_money,2) as user_money  FROM t_web_user WHERE LOWER(user_name)=? ";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(sql, new Object[]{userName});
		double userMoney =0;
		if(list!=null && list.size()>0){
			Map<String,Object> map = list.get(0);
			if(map.get("user_money")!=null){
				userMoney = Double.valueOf(map.get("user_money").toString());
			}
		}
		return userMoney;
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
		String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String hql= "from WebUser t where LOWER(t.userName) = ? and t.userPassword = ? and userFlag=?";
	 
		List<WebUser> list = this.find(hql, new Object[]{loginName, password,userFlag});
		if(list == null || list.size()<1)
			return null;
		
		return list.get(0);
	}
	/**
	 * @Description: 修改用户密码
	 * @param  String userName,String newPassword
	 * @return int
	 * @author Channel
	 * @date 2015-7-7
	 */
	public int updatePassword(String newPassword,String userName){
		String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String hql="update WebUser set userPassword=? where LOWER(userName)=? and userFlag=? ";
		Long row=super.executeUpdate(hql,newPassword,userName,userFlag);
		return row.intValue();
	}
	/**
	 * @Description: 修改用户资金密码
	 * @param  String userName,String newUserWithdrawPassword
	 * @return int
	 * @author Channel
	 * @date 2015-7-7
	 */
	public int updateMoneyPassword(String newUserWithdrawPassword,String userName){
		String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String hql="update WebUser set userWithdrawPassword=? where LOWER(userName)=? and userFlag=? ";
		Long row=super.executeUpdate(hql,newUserWithdrawPassword,userName,userFlag);
		return row.intValue();
	}
	
	
	/**
	 * 获取系统时间 
	 * 方法描述: TODO</br> 
	 * @return  
	 * String
	 */
	public String getSysTime(){
		String sql = "SELECT SYSDATE() AS systime FROM DUAL;";
		Map<String,Object> map = this.getJdbcTemplate().queryForMap(sql);
		
		return map.get("systime").toString();
		
	}
	
	/**
	 * 统计旗下会员数
	 * 方法描述: TODO</br> 
	 * @param agentNo
	 * @return  
	 * long
	 */
	public int getUserAgentCont(String agentNo){
		String userFlag = CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG);
		String sql = "SELECT COUNT(*) AS total FROM t_web_user WHERE user_agent=? and user_flag=?";
		return this.getJdbcTemplate().queryForInt(sql,new Object[]{agentNo,userFlag});
	}
	
	/**
	 * 获取会员数
	 * 方法描述: TODO</br> 
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Integer> getUserAgentMap(){
		String sql = "SELECT user_agent,COUNT(*) AS total FROM t_web_user GROUP BY user_agent ";
		Map<String,Integer> valMap = new HashMap<String,Integer>();
		List<Map<String,Object>> valList = this.getJdbcTemplate().queryForList(sql);
		for(int i=0;i<valList.size();i++){
			Map<String,Object> map = valList.get(i);
			String user_agent = map.get("user_agent").toString();
			int total = (map.get("total")==null?0:Integer.valueOf(map.get("total").toString()));
			valMap.put(user_agent, total);
		}
		return valMap;
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:conf/applicationContext.xml");
		context.refresh();
		WebUserDao webUserDao = context.getBean(WebUserDao.class);
		webUserDao.updateWebUserForMoney("testou", 0.1);
	}
	
}
