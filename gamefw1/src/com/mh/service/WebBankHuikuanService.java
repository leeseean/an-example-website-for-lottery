/**   
* 文件名称: WebBankHuikuanService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 下午1:39:47<br/>
*/  
package com.mh.service;

import java.util.List;
import java.util.Map;

import com.mh.entity.TWebThirdPay;
import com.mh.entity.WebBank;
import com.mh.entity.WebBankHuikuan;

/** 
 * 类描述: TODO<br/>汇款流水Service
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 下午1:39:47<br/>
 */
public interface WebBankHuikuanService {
	
	
	public void saveWebBankHuikuan(WebBankHuikuan huikuan);
	
	/**
	 * 汇款流水列表
	 * 方法描述: TODO</br> 
	 * @param huikuan
	 * @return  
	 * List<WebBankHuikuan>
	 */
	public List<WebBankHuikuan> getWebBankHuikuanList(WebBankHuikuan huikuan);
	
	
	
	/**
	 * 获取银行列表
	 * 方法描述: TODO</br> 
	 * @param userTypeId
	 * @return  
	 * List<WebBank>
	 */
	public List<WebBank> getWebBankList(Integer userTypeId);
	
	
	/**
	 * 获取第三方支付列表
	 * 方法描述: TODO</br> 
	 * @param userTypeId
	 * @return  
	 * List<WebBank>
	 */
	public List<TWebThirdPay> getWebThirdPayList(Integer userTypeId);
	
	
	
	/**
	 * 统计汇款
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param startTimeStr
	 * @param endTimeStr
	 * @return  
	 * int
	 */
	public Map<String, Integer> getWebBankHuikuanTj(String userName,String currDateStr);

}
