/**   
* 文件名称: WebBankHuikuanServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 下午1:40:23<br/>
*/  
package com.mh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebBankHuikuanDao;
import com.mh.entity.TWebThirdPay;
import com.mh.entity.WebBank;
import com.mh.entity.WebBankHuikuan;
import com.mh.service.WebBankHuikuanService;

/** 
 * 类描述: TODO<br/>汇款流水Service实现类
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 下午1:40:23<br/>
 */

@Service
public class WebBankHuikuanServiceImpl implements WebBankHuikuanService{
	
	@Autowired
	private WebBankHuikuanDao webBankHuikuanDao;
	
 
	public void saveWebBankHuikuan(WebBankHuikuan huikuan){
		
		this.webBankHuikuanDao.saveOrUpdate(huikuan);
	}
	
	/**
	 * 统计汇款
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param startTimeStr
	 * @param endTimeStr
	 * @return  
	 * int
	 */
	public Map<String, Integer> getWebBankHuikuanTj(String userName,String currDateStr){
		return this.webBankHuikuanDao.getWebBankHuikuanTj(userName, currDateStr);
	}
	
	
	/**
	 * 汇款流水列表
	 * 方法描述: TODO</br> 
	 * @param huikuan
	 * @return  
	 * List<WebBankHuikuan>
	 */
	public List<WebBankHuikuan> getWebBankHuikuanList(WebBankHuikuan huikuan){
		return this.webBankHuikuanDao.getWebBankHuikuanList(huikuan);
	}
	
	
	/**
	 * 获取银行列表
	 * 方法描述: TODO</br> 
	 * @param userTypeId
	 * @return  
	 * List<WebBank>
	 */
	public List<WebBank> getWebBankList(Integer userTypeId){
		return this.webBankHuikuanDao.getWebBankList(userTypeId);
	}
	
	
	/**
	 * 获取第三方支付列表
	 * 方法描述: TODO</br> 
	 * @param userTypeId
	 * @return  
	 * List<WebBank>
	 */
	public List<TWebThirdPay> getWebThirdPayList(Integer userTypeId){
		return this.webBankHuikuanDao.getWebThirdPayList(userTypeId);
	}

}
