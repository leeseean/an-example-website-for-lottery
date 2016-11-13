/**   
* 文件名称: WebUserWithdrawServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 下午2:11:09<br/>
*/  
package com.mh.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.conf.SystemConstant;
import com.mh.dao.WebAccountsDao;
import com.mh.dao.WebUserDao;
import com.mh.dao.WebUserWithdrawDao;
import com.mh.entity.WebAccounts;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserWithdraw;
import com.mh.service.WebUserWithdrawService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 下午2:11:09<br/>
 */

@Service
public class WebUserWithdrawServiceImpl implements WebUserWithdrawService{
	
	protected Logger logger = LoggerFactory.getLogger(WebUserWithdrawServiceImpl.class);
	
	@Autowired
	private WebUserWithdrawDao webUserWithdrawDao;
	
	@Autowired
	private WebUserDao webUserDao;
	
	@Autowired
	private WebAccountsDao webAccountsDao;
	
	
	/**
	 * 获取出款流水列表
	 * 方法描述: TODO</br> 
	 * @param userWithdraw
	 * @return  
	 * List<WebUserWithdraw>
	 */
	public List<WebUserWithdraw> getWebUserWithdrawList(WebUserWithdraw userWithdraw){
		return this.webUserWithdrawDao.getWebUserWithdrawList(userWithdraw);
	}


	/**
	 * 统计用户出款次数
	 * 方法描述: TODO</br> 
	 * @param userWithdraw
	 * @return  
	 * Map<String, Integer>
	 */
	public Map<String, Integer> countWithdrawSuccessTimes(
			WebUserWithdraw userWithdraw) {
		return this.webUserWithdrawDao.countWithdrawSuccessTimes(userWithdraw);
	}


	/****
	 * 用户提款
	 */
	public boolean saveWebUserWithdraw(WebUserWithdraw withdraw) {
		 
		/** 减少主帐号金额 **/
		int rows = this.webUserDao.updateWebUserForMoney(withdraw.getUserName(), -withdraw.getUserWithdrawMoney());
		if(rows<1){
			logger.info("提款余额不足！");
			return false;
		}
		WebUser user = this.webUserDao.findWebrUseByUserName(withdraw.getUserName());
	 
		/** 添加取款记录 **/
		this.webUserWithdrawDao.saveOrUpdate(withdraw);

		/** 财务记录 开始 **/
		WebAccounts account = new WebAccounts();
		account.setActOptMoney(-withdraw.getUserWithdrawMoney());
		account.setActProType(SystemConstant.project_withdraw);
		account.setActOptType(String.valueOf(withdraw.getWithdrawType()));
		account.setActOrder(withdraw.getUserOrder());
		account.setActResultMoney(user.getUserMoney());
		account.setCreateTime(withdraw.getCreateTime());
		account.setModifyTime(withdraw.getCreateTime());
		account.setRemark("客户提款申请");
		account.setSysUserName(withdraw.getUserName());// 操作者：客户本人
		account.setUserName(withdraw.getUserName());
		account.setStatus(0);
		account.setGmt4Time(withdraw.getGmt4Time());
		account.setUserAgent(user.getUserAgent());
		this.webAccountsDao.saveOrUpdate(account);
		/** 财务记录 结束 **/
		return true;
	}

}
