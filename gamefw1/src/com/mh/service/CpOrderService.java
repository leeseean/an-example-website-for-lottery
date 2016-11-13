/**   
* 文件名称: OrderService.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-5 下午1:20:13<br/>
*/  
package com.mh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import app.xb.cmbase.model.CpConfig;

import com.alibaba.fastjson.JSONArray;
import com.mh.commons.orm.Page;
import com.mh.entity.CpOrder;
import com.mh.entity.CpTomResults;
import com.mh.entity.WebUser;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-5 下午1:20:13<br/>
 */
public interface CpOrderService {
	/**
	 * 更新订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @return  
	 * boolean
	 */
	public void updateOrder(HttpServletRequest request, String gameCode,
			String jsonData, String orderFlag) throws RuntimeException;
	
	
	/**
	 * 获取最新的默认8条记录
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<CpOrder>
	 */
	public List<Map<String,Object>> getOrderResult8List(String gameTypeCode,String userName);
	
	/**
	 * 方法描述: 得到当前未结算的交易状况</br>
	 * 创建人: zoro<br/> 
	 * @param records
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getOrderList(CpOrder records);
	
	/**
	 *  统计注单内容
	 * 创建人: zoro<br/> 
	 * @param bean
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Map<String,Object>> selectCountOrder(CpOrder bean);
	
	
	/**
	 * 订单列表
	 * 方法描述: TODO</br> 
	 * @param bean
	 * @return  
	 * List<Map<String,Object>>
	 */
	@SuppressWarnings("rawtypes")
	public Page getReportOrderList(Page page,CpOrder bean);
	
	
	
	/**
	 * 
	 * 方法描述:得到注单集合</br>
	 * 创建人: zoro<br/> 
	 * @param bean
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getOrderRecordList(CpOrder bean,int start,int pageSize);
	
	
	/**
	 * 今日订单统计
	 * 方法描述: TODO</br> 
	 * @param gameTypeCode
	 * @param cpTypeCode
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Object> getTodayOrderTj(String gameTypeCode,String cpTypeCode,String userName);
	
	/**
	 * 彩票下注
	 * @return
	 */
	public int saveMobileCpOrder(HttpServletRequest request,JSONArray jsonArray,Map<String, CpConfig> configMap,WebUser webUser,CpTomResults nextResult) ;
	
	/**
	 * 当期盘口限额统计
	 * @param order
	 * @return
	 */
	public double getUserSingleCredit(CpOrder order);
	
	/**
	 * 用户号码限额统计
	 * @param order
	 * @return
	 */
	public double getUserSingleCreditForNumber(CpOrder order);
	
	
	public CpOrder getCpOrderById(Integer id);
	
	
	/**
	 * 获取结算订单前几条
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getCpOrderLotteryUser(int limits);
 
}
