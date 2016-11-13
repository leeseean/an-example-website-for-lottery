/**   
* 文件名称: OrderServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-5 下午1:20:49<br/>
*/  
package com.mh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.xb.cmbase.model.CpCateGory;
import app.xb.cmbase.model.CpConfig;
import app.xb.cmbase.model.CpGame;
import app.xb.cmbase.model.CpType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.CpCommonConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.CommonUtils;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.MathUtil;
import com.mh.commons.utils.OrderNoUtils;
import com.mh.commons.utils.SecurityEncode;
import com.mh.dao.CpOrderDao;
import com.mh.dao.CpResultsDao;
import com.mh.dao.WebAccountsDao;
import com.mh.dao.WebUserDao;
import com.mh.entity.CpOrder;
import com.mh.entity.CpResults;
import com.mh.entity.CpTomResults;
import com.mh.entity.WebUser;
import com.mh.service.CpOrderService;
import com.mh.web.login.UserContext;

/** 
 * 类描述: TODO<br/>订单Service
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-5 下午1:20:49<br/>
 */
@SuppressWarnings("all")
@Service
public class CpOrderServiceImpl implements CpOrderService{
	
	
	
	@Autowired
	private WebUserDao webUserDao;
	
	@Autowired
	private CpOrderDao cpOrderDao;
	
	
	@Autowired
	private CpResultsDao cpResultsDao;
	
	@Autowired
	private WebAccountsDao webAccountsDao;
	
	
	/**
	 * 更新订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @return  
	 * boolean
	 */
	public void updateOrder(HttpServletRequest request, String gameCode,String jsonData, String orderFlag) throws RuntimeException{
		List<CpOrder> orderList = null;
		try {
			if ("normal".equals(orderFlag)) {//常规订单
				orderList = this.updateNormalOrder(request, gameCode, jsonData);
			}else if("cl".equals(orderFlag)){//串连订单
				orderList = this.updateClOrder(request, gameCode, jsonData);
			}else if("mul".equals(orderFlag)){//组合订单
				orderList = this.updateMulOrder(request, gameCode, jsonData);
			}else if("lm".equals(orderFlag)){//连码订单
				orderList = this.updateLmOrder(request, gameCode, jsonData);
			}else if("group".equals(orderFlag)){//时时彩 福彩3d pl3
				orderList = this.updateGroupOrder(request, gameCode, jsonData);
			}else if("yzgg".equals(orderFlag)){//一字过关
				orderList = this.updateYzggOrder(request, gameCode, jsonData);
			}
			/*int resultCode = this.webAccountsDao.saveShareadOrder(orderList);//shared订单
			if(resultCode != 200){
				throw new RuntimeException("注单提交失败");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("注单提交失败");
		}
	}
	
	
	/**
	 * 一字过关订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param code
	 * @param jsonData
	 * @return  
	 * boolean
	 */
	public List<CpOrder> updateYzggOrder(HttpServletRequest request,String code,String jsonData){
		UserContext userContext = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		WebUser webUser = this.webUserDao.findWebrUseByUserName(userContext.getUserName());
		
		String userName = userContext.getUserName();
		
		CpTomResults tomResults = this.cpResultsDao.getNextResults(code);
		
		JSONArray jsonArray=JSONArray.parseArray(jsonData);
		JSONObject json= jsonArray.getJSONObject(0);
		String _p = json.getString("token");
		_p = SecurityEncode.decoderByDES(_p, SecurityEncode.key);
		String cpTypeCode = json.getString("typeCode");
		String cpCateCode = json.getString("cateCode");
		
		CpGame cpGame = CpConfigCache.GAME_OBJ_CACHE_MAP.get(code);
		String cateKey = cpGame.getGameTypeCode()+"_"+cpTypeCode+"_"+cpCateCode;
		CpCateGory cpCate = CpConfigCache.CATE_OBJ_CACHE_MAP.get(cateKey);
		CpType cpType =CpConfigCache.TYPE_OBJ_CACHE_MAP.get(cpGame.getBigtypeCode()+"_"+cpTypeCode);
		double xzje =  json.getDoubleValue("xzje");
		
		
		String[] pArr = _p.split("\\|");
		double rate = Double.valueOf(pArr[1]);
		double zgje = MathUtil.mul(xzje, rate);
		double kyje = MathUtil.sub(zgje, xzje);
		
		String numbers = pArr[0];
		List<CpOrder> orderList = new ArrayList<CpOrder>();
		CpOrder order = new CpOrder(); 
		
 
		order.setNumber(pArr[0]);
		order.setPl(pArr[1]);
		order.setContent(cpType.getCpTypeName());
	
		order.setKyje(kyje);
		order.setZgje(zgje);
		order.setXzje(xzje);
		order.setYj(0D);
		order.setXzdh(OrderNoUtils.getOrderNo(code));
		
		StringBuffer titleBuffer = new StringBuffer("");
		titleBuffer.append(cpType.getCpTypeName());
		titleBuffer.append("[");
		titleBuffer.append(cpCate.getName());
		titleBuffer.append("]");		
		order.setCpCateCode(cpCate.getCpCateCode());
		order.setCpCateName(cpCate.getName());
		order.setBackWaterRate(cpCate.getBackWater()==null?0:cpCate.getBackWater());
		//备注
		StringBuffer bzBuffer=new StringBuffer("");
		bzBuffer.append(titleBuffer.toString());
		bzBuffer.append("：");
		bzBuffer.append(numbers);
		order.setBz(bzBuffer.toString());
		
		
		//内容
		StringBuffer contentBuffer=new StringBuffer("");
 
		
		contentBuffer.append(titleBuffer.toString());
		contentBuffer.append(" ");
		contentBuffer.append(numbers);
		contentBuffer.append("@");
		contentBuffer.append("<font color=\"#ff0000\">"+rate+"</font>");
		order.setContent(contentBuffer.toString());
		order.setDesc(numbers);
		order.setGameTypeCode(code);
		order.setGameTypeName(cpGame.getGameTypeName());
		order.setCpTypeCode(cpType.getCpTypeCode());
		order.setCpTypeName(cpType.getCpTypeName());
		
 
		order.setQs(tomResults.getFormatQs());
		order.setBz(bzBuffer.toString());
		Date currDate = DateUtil.currentDate();
		Date gtDate = DateUtil.getGMT_4_Date();
		
		order.setXzsj(gtDate);
		order.setUserIp(IPSeeker.getIpAddress(request));
		order.setSfjs(CommonConstant.CP_ORDER_STATUS_WJS);
		order.setCreateTime(currDate);
		order.setModifyTime(currDate);
		order.setBackWaterStatus(0);
		order.setUserName(userName);
		order.setIsSync(0);
		order.setUserAgent(webUser.getUserAgent());
		StringBuffer buff = new StringBuffer("");
		buff.append(order.getGameTypeName()).append("-").append(order.getCpTypeName());
		int rows1 = this.webAccountsDao.updateWebAccount(userName, CommonConstant.CP_ACT_PRO_TYPE, CommonConstant.CP_BET_OUT, 
				-order.getXzje(), buff.toString()+"，彩票下注", userName, order.getXzdh());
		if(rows1>0){
			this.cpOrderDao.saveOrUpdate(order);
		}else{
			throw new RuntimeException("下注失败！");
		}
		order.setWebFlag(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
	    orderList.add(order);
	    return orderList;
	}

 
	/**
	 * 组合订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param code
	 * @param jsonData
	 * @return  
	 * boolean
	 */
	public List<CpOrder> updateGroupOrder(HttpServletRequest request,String code,String jsonData){
		UserContext userContext = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		WebUser webUser = this.webUserDao.findWebrUseByUserName(userContext.getUserName());
		String userName = userContext.getUserName();
 
		CpTomResults tomResults = this.cpResultsDao.getNextResults(code);
		
		JSONArray jsonArray=JSONArray.parseArray(jsonData);
		JSONObject json= jsonArray.getJSONObject(0);
		String _p = json.getString("token");
		_p = SecurityEncode.decoderByDES(_p, SecurityEncode.key);
		String cpTypeCode = json.getString("typeCode");
		String cpCateCode = json.getString("cateCode");
		
		CpGame cpGame = CpConfigCache.GAME_OBJ_CACHE_MAP.get(code);
		String cateKey = cpGame.getGameTypeCode()+"_"+cpTypeCode+"_"+cpCateCode;
		CpCateGory cpCate = CpConfigCache.CATE_OBJ_CACHE_MAP.get(cateKey);
		CpType cpType =CpConfigCache.TYPE_OBJ_CACHE_MAP.get(cpGame.getBigtypeCode()+"_"+cpTypeCode);
		double xzje =  json.getDoubleValue("xzje");
		
		String[] pArr = _p.split("\\|");
		double rate = Double.valueOf(pArr[1]);
		double zgje = MathUtil.mul(xzje, rate);
		double kyje = MathUtil.sub(zgje, xzje);
		
		String numbers = pArr[0];
		String numbersTemp = "";
		if(numbers.indexOf("#")>0){
			StringBuffer buff = new StringBuffer("");
			String[] wsArr = numbers.split("#");
			List<String> wsList = CommonUtils.getNumberChinese(wsArr.length);
			for(int i=0;i<wsArr.length;i++){
				String val = wsArr[i];
				if(i>0){
					buff.append(" x ");
				}
				buff.append("(").append(wsList.get(i)).append(val).append(")");
			}
			numbersTemp = buff.toString();
		}else{
			numbersTemp = numbers;
		}
		
		List<CpOrder> orderList = new ArrayList<CpOrder>();
		CpOrder order = new CpOrder(); 
		order.setNumber(pArr[0]);
		order.setPl(pArr[1]);
		order.setContent(cpType.getCpTypeName());
 
		order.setKyje(kyje);
		order.setZgje(zgje);
		order.setXzje(xzje);
		order.setYj(0D);
		order.setXzdh(OrderNoUtils.getOrderNo(code));
		
		StringBuffer titleBuffer = new StringBuffer("");
		titleBuffer.append(cpType.getCpTypeName());
		titleBuffer.append("[");
		titleBuffer.append(cpCate.getName());
		titleBuffer.append("]");		
		order.setCpCateCode(cpCate.getCpCateCode());
		order.setCpCateName(cpCate.getName());
		order.setBackWaterRate(cpCate.getBackWater()==null?0:cpCate.getBackWater());
		//备注
		StringBuffer bzBuffer=new StringBuffer("");
		bzBuffer.append(titleBuffer.toString());
		bzBuffer.append("：");
		bzBuffer.append(numbersTemp);
		order.setBz(bzBuffer.toString());
		
		
		//内容
		StringBuffer contentBuffer=new StringBuffer("");
 
		
		contentBuffer.append(titleBuffer.toString());
		contentBuffer.append(" ");
		contentBuffer.append(numbersTemp);
		contentBuffer.append("@");
		contentBuffer.append("<font color=\"#ff0000\">"+rate+"</font>");
		order.setContent(contentBuffer.toString());
		
		order.setDesc(numbersTemp);
		
		order.setGameTypeCode(code);
		order.setGameTypeName(cpGame.getGameTypeName());
		
		order.setCpTypeCode(cpType.getCpTypeCode());
		order.setCpTypeName(cpType.getCpTypeName());
		
 
		order.setQs(tomResults.getFormatQs());
		order.setBz(bzBuffer.toString());
		Date currDate = DateUtil.currentDate();
		Date gtDate = DateUtil.getGMT_4_Date();
		
		order.setXzsj(gtDate);
		order.setUserIp(IPSeeker.getIpAddress(request));
		order.setSfjs(CommonConstant.CP_ORDER_STATUS_WJS);
		order.setBackWaterStatus(0);
		order.setUserName(userName);
		order.setCreateTime(currDate);
		order.setModifyTime(currDate);
		order.setUserName(userName);
		order.setUserAgent(webUser.getUserAgent());
		order.setIsSync(0);
		StringBuffer buff = new StringBuffer("");
		buff.append(order.getGameTypeName()).append("-").append(order.getCpTypeName());
		int rows1 = this.webAccountsDao.updateWebAccount(userName, CommonConstant.CP_ACT_PRO_TYPE, CommonConstant.CP_BET_OUT, 
				-order.getXzje(), buff.toString()+"，彩票下注", userName, order.getXzdh());
		if(rows1>0){
			this.cpOrderDao.saveOrUpdate(order);
		}else{
			throw new RuntimeException("下注失败！");
		}
		order.setWebFlag(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
	    orderList.add(order);
	    return orderList;
	}
	
	/**
	 * 串关
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param gameCode
	 * @param jsonData
	 * @return  
	 * boolean
	 */
	public List<CpOrder> updateClOrder(HttpServletRequest request,String code,String jsonData){
		UserContext userContext = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		WebUser webUser = this.webUserDao.findWebrUseByUserName(userContext.getUserName());
		String userName = userContext.getUserName();
		
		JSONArray jsonArray=JSONArray.parseArray(jsonData);
		StringBuffer contentBuffer=new StringBuffer("");
		StringBuffer bzBuffer=new StringBuffer("");
		StringBuffer numberBuffer=new StringBuffer("");
		StringBuffer idBuffer = new StringBuffer("");
		double rateTotal=1;
		double xzjeTotal =0;
		
		List<CpOrder> orderList = new ArrayList<CpOrder>();
		
		CpConfig config =null;
		for(int i=0;i<jsonArray.size();i++){
			JSONObject json=jsonArray.getJSONObject(i);
			String uidKey=code+"-"+json.getString("uid");
			double xzje = json.getDoubleValue("xzje");
			config = CpConfigCache.UID_CACHE_KEY.get(uidKey);

			
			rateTotal = rateTotal*config.getPl();
			xzjeTotal = xzjeTotal+xzje;
			
			StringBuffer tsBuffer = new StringBuffer("");
			tsBuffer.append(config.getCpCateName());
			if(StringUtils.isNotEmpty(config.getXzlxName())){
				tsBuffer.append("[");
				tsBuffer.append(config.getXzlxName());
				tsBuffer.append("]");
			}
			if(i>0){
				contentBuffer.append(",");
				bzBuffer.append(";");
				numberBuffer.append(",");
				idBuffer.append(",");
			}
			idBuffer.append(config.getId());
			
			if(StringUtils.isNotEmpty(config.getXzlxName())){
				numberBuffer.append(config.getXzlxName());
			}else{
				numberBuffer.append(config.getCpCateName());
			}
			numberBuffer.append(":").append(config.getNumber());
			
			//内容
			contentBuffer.append(config.getCpCateName());
//			if(StringUtils.isNotEmpty(config.getXzlxName())){
//				contentBuffer.append("[");
//				contentBuffer.append(config.getXzlxName());
//				contentBuffer.append("]");
//			}
			contentBuffer.append("");
			contentBuffer.append(config.getNumber());
 
			
			bzBuffer.append(config.getNumber());
			bzBuffer.append(":");
			bzBuffer.append(config.getPl());
			
		}
		CpTomResults tomResults = this.cpResultsDao.getNextResults(code);
		rateTotal = MathUtil.round(rateTotal, 2);
		xzjeTotal = MathUtil.round(xzjeTotal, 2);
		contentBuffer.append("@").append(rateTotal);
		
		
		double zgje = MathUtil.mul(xzjeTotal, rateTotal);
		double kyje = MathUtil.sub(zgje, xzjeTotal);
		
		CpOrder order = new CpOrder();
 
		order.setXzdh(OrderNoUtils.getOrderNo(code));
		order.setKyje(kyje);
		order.setZgje(zgje);
		order.setXzje(xzjeTotal);
		order.setYj(0D);
		order.setPl(rateTotal+"");
		order.setNumber(numberBuffer.toString());
		order.setCfgId(idBuffer.toString());
		order.setGameTypeCode(code);
		order.setGameTypeName(config.getGameTypeName());
		order.setCpTypeCode(config.getCpTypeCode());
		order.setCpTypeName(config.getCpTypeName());
		order.setCpCateCode(config.getCpCateCode());
		order.setCpCateName(config.getCpCateName());
		
		String cateKey = config.getGameTypeCode()+"_"+config.getCpTypeCode()+"_"+config.getCpCateCode();
		if(CpConfigCache.CATE_OBJ_CACHE_MAP.get(cateKey)!=null){
			order.setBackWaterRate(CpConfigCache.CATE_OBJ_CACHE_MAP.get(cateKey).getBackWater());
		}else{
			order.setBackWaterRate(0d);
		}
 
		order.setQs(tomResults.getFormatQs());
		order.setContent(contentBuffer.toString());
		order.setBz(bzBuffer.toString());
		
		Date currDate = DateUtil.currentDate();
		Date gtDate = DateUtil.getGMT_4_Date();
		order.setXzsj(gtDate);
		order.setUserIp(IPSeeker.getIpAddress(request));
		order.setSfjs(CommonConstant.CP_ORDER_STATUS_WJS);
		order.setCreateTime(currDate);
		order.setModifyTime(currDate);
		order.setIsSync(0);
		order.setUserName(userName);
		order.setOrderStatus("未结算");
		order.setBackWaterStatus(0);
		order.setUserAgent(webUser.getUserAgent());
		
		StringBuffer buff = new StringBuffer("");
		buff.append(order.getGameTypeName()).append("-").append(order.getCpTypeName());
		
		int rows1 = this.webAccountsDao.updateWebAccount(userName, CommonConstant.CP_ACT_PRO_TYPE, CommonConstant.CP_BET_OUT, 
				-order.getXzje(), buff.toString()+"，彩票下注", userName, order.getXzdh());
		if(rows1>0){
			this.cpOrderDao.saveOrUpdate(order);
		}else{
			throw new RuntimeException("下注失败！");
		}
		order.setWebFlag(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
	    orderList.add(order);
	    return orderList;
	}
	
	
	/**
	 * 组合订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param code
	 * @param jsonData
	 * @return  
	 * boolean
	 */
	public List<CpOrder> updateMulOrder(HttpServletRequest request,String code,String jsonData){
		UserContext userContext = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		if(userContext==null){
			throw new RuntimeException("用户登陆异常！");
		}
		WebUser webUser = this.webUserDao.findWebrUseByUserName(userContext.getUserName());
		
		JSONArray jsonArray = JSON.parseArray(jsonData);
		
		String userName = userContext.getUserName();
		JSONObject json= jsonArray.getJSONObject(0);
		String _p = json.getString("token");
		_p = SecurityEncode.decoderByDES(_p, SecurityEncode.key);
		String cpTypeCode = json.getString("typeCode");
		String cpCateCode = json.getString("cateCode");
		
		CpGame cpGame = CpConfigCache.GAME_OBJ_CACHE_MAP.get(code);
		
		String cateKey = cpGame.getGameTypeCode()+"_"+cpTypeCode+"_"+cpCateCode;
		CpCateGory cpCate = CpConfigCache.CATE_OBJ_CACHE_MAP.get(cateKey);
		CpType cpType =CpConfigCache.TYPE_OBJ_CACHE_MAP.get(cpGame.getBigtypeCode()+"_"+cpTypeCode);
		double xzje =  json.getDoubleValue("xzje");
		
		String[] pArr = _p.split("\\|");
		String postid = pArr[0];
		String number = pArr[1];
		String rateid = pArr[2];
		String[] idsArr = postid.split("/");
		String[] numbersArr = number.split("/");
		String[] rateArr = rateid.split("/");
		
		CpTomResults tomResults = this.cpResultsDao.getNextResults(code);
		List<CpOrder> orderList = new ArrayList<CpOrder>();
		
		for(int i=0;i<idsArr.length;i++){
			String ids = idsArr[i];
			String numbers = numbersArr[i];
			double rate = Double.valueOf(rateArr[i]);
			numbers = CommonUtils.getSortNumbers(numbers);
			
	 
			double zgje = MathUtil.mul(xzje, rate);
			double kyje = MathUtil.sub(zgje, xzje);
		 
			CpOrder order = new CpOrder(); 
			
 
			order.setPl(String.valueOf(rate));
			order.setNumber(numbers);
			order.setContent(cpCate.getName());
		
			order.setKyje(kyje);
			order.setZgje(zgje);
			order.setXzje(xzje);
			order.setYj(0D);
			order.setXzdh(OrderNoUtils.getOrderNo(code));
			
			StringBuffer contentBuffer=new StringBuffer("");
			StringBuffer bzBuffer=new StringBuffer("");
			//内容
			contentBuffer.append(cpCate.getName());
			contentBuffer.append(" ");
			contentBuffer.append(numbers);
			contentBuffer.append("@");
			contentBuffer.append("<font color=\"#ff0000\">"+rate+"</font>");
			order.setContent(contentBuffer.toString());
			
			bzBuffer.append(cpCate.getName());
			bzBuffer.append(" ");
			bzBuffer.append(numbers);
			order.setBz(bzBuffer.toString());
			
			order.setCfgId(ids);
			order.setGameTypeCode(code);
			order.setGameTypeName(cpGame.getGameTypeName());
			order.setCpTypeCode(cpType.getCpTypeCode());
			order.setCpTypeName(cpType.getCpTypeName());
			order.setCpCateCode(cpCate.getCpCateCode());
			order.setCpCateName(cpCate.getName());
			order.setQs(tomResults.getFormatQs());
			order.setContent(contentBuffer.toString());
			Date currDate = DateUtil.currentDate();
			Date gtDate = DateUtil.getGMT_4_Date();
			order.setXzsj(gtDate);
			order.setUserIp(IPSeeker.getIpAddress(request));
			order.setSfjs(CommonConstant.CP_ORDER_STATUS_WJS);
			order.setCreateTime(currDate);
			order.setModifyTime(currDate);
			order.setUserName(userContext.getUserName());
			order.setOrderStatus("未结算");
			order.setBackWaterRate(cpCate.getBackWater()==null?0:cpCate.getBackWater());
			order.setIsSync(0);
			order.setUserAgent(webUser.getUserAgent());
			StringBuffer buff = new StringBuffer("");
			buff.append(order.getGameTypeName()).append("-").append(order.getCpTypeName());
			
			int rows1 = this.webAccountsDao.updateWebAccount(userName, CommonConstant.CP_ACT_PRO_TYPE, CommonConstant.CP_BET_OUT, 
					-order.getXzje(), buff.toString()+"，彩票下注", userName, order.getXzdh());
			if(rows1>0){
				this.cpOrderDao.saveOrUpdate(order);
			}else{
				throw new RuntimeException("下注失败！");
			}
			order.setWebFlag(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
			orderList.add(order);
		}	
		return orderList;
	}
	
	
	/**
	 * 连码订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param code
	 * @param jsonData
	 * @return  
	 * boolean
	 */
	public List<CpOrder> updateLmOrder(HttpServletRequest request,String code,String jsonData){
		UserContext userContext = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		WebUser webUser = this.webUserDao.findWebrUseByUserName(userContext.getUserName());
		JSONArray jsonArray=JSONArray.parseArray(jsonData);
		String userName = userContext.getUserName();
		JSONObject json= jsonArray.getJSONObject(0);
		String _p = json.getString("token");
		_p = SecurityEncode.decoderByDES(_p, SecurityEncode.key);
		double xzje =  json.getDoubleValue("xzje");
		String[] dataArr = _p.split("\\|");
		String numbers = dataArr[0];
		String rateStr = dataArr[1];
		String rateid1 = "",rateid2="";
		CpConfig cpConfig1 =null;
		CpConfig cpConfig2 =null;
		String cfgid = "";
		if(rateStr.indexOf(",")>0){
			String[] rateidArr = rateStr.split(",");
			rateid1 = rateidArr[0];
			rateid2 = rateidArr[1];
			
			cpConfig1 =  CpConfigCache.UID_CACHE_KEY.get(rateid1);
			cpConfig2 = CpConfigCache.UID_CACHE_KEY.get(rateid2);
			rateStr = cpConfig1.getPl()+","+cpConfig2.getPl();
			cfgid=cpConfig1.getId()+","+cpConfig2.getId();
		}else{
			rateid1 =rateStr;
			cpConfig1 = CpConfigCache.UID_CACHE_KEY.get(rateid1);
			rateStr = cpConfig1.getPl()+"";
			cfgid=cpConfig1.getId()+"";
		}

		double rate = cpConfig1.getPl();
		double zgje = MathUtil.mul(xzje, rate);
		double kyje = MathUtil.sub(zgje, xzje);
		
		CpTomResults tomResults = this.cpResultsDao.getNextResults(code);
		
		String[] numArr = numbers.split("#");
		List<CpOrder> orderList = new ArrayList<CpOrder>();
		for(int i=0;i<numArr.length;i++){
			
			String number = numArr[i];
			
			CpOrder order = new CpOrder(); 
 
			order.setPl(rateStr);
			order.setNumber(number);
			order.setKyje(kyje);
			order.setZgje(zgje);
			order.setXzje(xzje);
			order.setXzdh(OrderNoUtils.getOrderNo(code));
			order.setYj(0D);
			//备注
			StringBuffer bzBuffer=new StringBuffer("");
			bzBuffer.append(cpConfig1.getCpCateName());
			bzBuffer.append("：");
			bzBuffer.append(number);
			order.setBz(bzBuffer.toString());
			
			//内容
			StringBuffer contentBuffer=new StringBuffer("");
			contentBuffer.append(cpConfig1.getCpCateName());
			contentBuffer.append(" ");
			contentBuffer.append(number);
			contentBuffer.append("@");
			contentBuffer.append("<font color=\"#ff0000\">"+rateStr+"</font>");
			order.setContent(contentBuffer.toString());			
			
			order.setCfgId(cfgid);
			order.setGameTypeCode(cpConfig1.getGameTypeCode());
			order.setGameTypeName(cpConfig1.getGameTypeName());
			order.setCpTypeCode(cpConfig1.getCpTypeCode());
			order.setCpTypeName(cpConfig1.getCpTypeName());
			
			order.setCpCateCode(cpConfig1.getCpCateCode());
			order.setCpCateName(cpConfig1.getCpCateName());
			order.setXzlxCode(cpConfig1.getXzlxCode());
			order.setXzlxName(cpConfig1.getXzlxName());
			
			String cateKey = cpConfig1.getGameTypeCode()+"_"+cpConfig1.getCpTypeCode()+"_"+cpConfig1.getCpCateCode();
			CpCateGory cpCate = CpConfigCache.CATE_OBJ_CACHE_MAP.get(cateKey);
			if(cpCate!=null){
				order.setBackWaterRate(cpCate.getBackWater()==null?0:cpCate.getBackWater());
			}else{
				order.setBackWaterRate(0d);
			}
			order.setQs(tomResults.getFormatQs());
			order.setBz(bzBuffer.toString());
			Date currDate = DateUtil.currentDate();
			Date gtDate = DateUtil.getGMT_4_Date();
			order.setXzsj(gtDate);
			order.setUserIp(IPSeeker.getIpAddress(request));
			order.setSfjs(CommonConstant.CP_ORDER_STATUS_WJS);
			order.setCreateTime(currDate);
			order.setModifyTime(currDate);
			order.setUserName(userName);
			order.setIsSync(0);
			order.setBackWaterStatus(0);
			order.setOrderStatus("未结算");
			order.setUserAgent(webUser.getUserAgent());
			StringBuffer buff = new StringBuffer("");
			buff.append(order.getGameTypeName()).append("-").append(order.getCpTypeName());
			int rows1 = this.webAccountsDao.updateWebAccount(userName, CommonConstant.CP_ACT_PRO_TYPE, CommonConstant.CP_BET_OUT, 
					-order.getXzje(), buff.toString()+"，彩票下注", userName, order.getXzdh());
			if(rows1>0){
				this.cpOrderDao.saveOrUpdate(order);
			}else{
				throw new RuntimeException("下注失败！");
			}
			order.setWebFlag(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
		    orderList.add(order);
		}
		return orderList;
	}
	
	
	
	/**
	 * 更新常规订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @return  
	 * boolean
	 */
	public List<CpOrder> updateNormalOrder(HttpServletRequest request,String gameCode,String jsonData){
		JSONArray jsonArray=JSONArray.parseArray(jsonData);
		UserContext userContext = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		if(userContext==null){
			throw new RuntimeException("用户未登陆异常！");
		}
		String userName = userContext.getUserName();
		WebUser webUser = this.webUserDao.findWebrUseByUserName(userName);
 
		CpTomResults tomResults = this.cpResultsDao.getNextResults(gameCode);
		List<CpOrder> orderList = new ArrayList<CpOrder>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject json=jsonArray.getJSONObject(i);
			String uid = json.getString("uid");
			String uidKey=gameCode+"-"+json.getString("uid");
			//  右边的快捷下注
			double xzje = json.getDoubleValue("xzje");
			//  左边的特码快捷下注
			double xzjeFast =  json.getDoubleValue("val");
			String number = json.getString("number");
			if( 0.0==xzje && 0.0==xzjeFast ){
				throw new RuntimeException("下注金额为0！");
			}else{
				xzje = ( 0.0==xzje  ? xzjeFast : xzje );
			}
			
			CpConfig config = CpConfigCache.UID_CACHE_KEY.get(uidKey);
			
			Date currDate = DateUtil.currentDate();
			Date gtDate = DateUtil.getGMT_4_Date();
			String orderNo = OrderNoUtils.getOrderNo(gameCode);

			CpOrder order = new CpOrder();

			double rate = config.getPl();
			double zgje = MathUtil.mul(xzje, rate);
			double kyje = MathUtil.sub(zgje, xzje);
	 
			order.setYj(0D);
			order.setKyje(kyje);
			order.setZgje(zgje);
			order.setXzje(xzje);
			order.setPl(String.valueOf(config.getPl()));
			if(!"TM-KL8TM-TMSB".equals(uid)){
				number = config.getNumber();
			}
			order.setNumber(number);
			
			
			order.setCfgId(config.getId()+"");
			order.setGameTypeCode(config.getGameTypeCode());
			order.setGameTypeName(config.getGameTypeName());
			order.setCpTypeCode(config.getCpTypeCode());
			order.setCpTypeName(config.getCpTypeName());
			order.setCpCateCode(config.getCpCateCode());
			order.setCpCateName(config.getCpCateName());
			order.setXzlxCode(config.getXzlxCode());
			order.setXzlxName(config.getXzlxName());
			order.setXzzuCode(config.getXzzuCode());
			order.setXzzuName(config.getXzzuName());
		 
			order.setBackWaterRate(config.getBackWater()==null?0:config.getBackWater());
			order.setQs(tomResults.getFormatQs());
			StringBuffer contentBuffer = new StringBuffer("");
			if(!"TM-KL8TM-TMSB".equals(uid)){
				contentBuffer.append(config.getCpCateName());
			}else{
				contentBuffer.append(config.getNumber());
			}
			
			if (StringUtils.isNotEmpty(config.getXzlxName())) {
				contentBuffer.append("[");
				contentBuffer.append(config.getXzlxName());
				contentBuffer.append("]");
			}
			order.setBz(contentBuffer.toString()+" ： "+number);
			order.setXzsj(gtDate);
			order.setUserIp(IPSeeker.getIpAddress(request));
			order.setSfjs(CommonConstant.CP_ORDER_STATUS_WJS);
			order.setXzdh(orderNo);
			order.setCreateTime(currDate);
			order.setModifyTime(currDate);
			order.setIsSync(0);
 
			order.setOrderStatus("未结算");
			order.setBackWaterStatus(0);
			
			contentBuffer.append(" ");
			contentBuffer.append(number);
			contentBuffer.append("@");
			contentBuffer.append("<font color=\"#ff0000\">"+config.getPl()+"</font>");
			order.setContent(contentBuffer.toString());
			order.setUserName(userName);
			order.setIsSync(0);
			order.setUserAgent(webUser.getUserAgent());
			
			StringBuffer buff = new StringBuffer("");
			buff.append(order.getGameTypeName()).append("-").append(order.getCpTypeName());
			int rows1 = this.webAccountsDao.updateWebAccount(userName, CommonConstant.CP_ACT_PRO_TYPE, CommonConstant.CP_BET_OUT, 
					-order.getXzje(), buff.toString()+"，彩票下注", userName, order.getXzdh());
			if(rows1>0){
				this.cpOrderDao.saveOrUpdate(order);
			}else{
				throw new RuntimeException("下注失败！");
			}
			order.setWebFlag(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
			orderList.add(order);
		}
		return orderList;
	}


	
	
	
	
	
	
	
	
	/**
	 * 获取最新的默认8条记录
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<CpOrder>
	 */
	public List<Map<String,Object>> getOrderResult8List(String gameTypeCode,String userName){
		return this.cpOrderDao.getOrderResult8List(gameTypeCode,userName);
	}

	/**
	 * 方法描述: 得到交易状况</br>
	 * 创建人: zoro<br/> 
	 * @param records
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getOrderList(CpOrder records) {
		return this.cpOrderDao.getOrderList(records);
	}

	/**
	 *  统计注单内容
	 * 创建人: zoro<br/> 
	 * @param bean
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Map<String,Object>> selectCountOrder(CpOrder bean) {
		return this.cpOrderDao.selectCountOrder(bean);
	}
	
	/**
	 * 
	 * 方法描述:得到注单集合</br>
	 * 创建人: zoro<br/> 
	 * @param bean
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getOrderRecordList(CpOrder bean,int start,int pageSize){
		return this.cpOrderDao.getOrderList(bean);
	}
	
	/**
	 * 今日订单统计
	 * 方法描述: TODO</br> 
	 * @param gameTypeCode
	 * @param cpTypeCode
	 * @return  
	 * Map<String,Object>
	 */
	public Map<String,Object> getTodayOrderTj(String gameTypeCode,String cpTypeCode,String userName){
		return this.cpOrderDao.getTodayOrderTj(gameTypeCode, cpTypeCode,userName);
	}
	
	/**
	 * 订单列表
	 * 方法描述: TODO</br> 
	 * @param bean
	 * @return  
	 * List<Map<String,Object>>
	 */
	@SuppressWarnings("rawtypes")
	public Page getReportOrderList(Page page,CpOrder bean){
		return this.cpOrderDao.getReportOrderList(page,bean);
	}


	public int saveMobileCpOrder(HttpServletRequest request,JSONArray jsonArray,Map<String, CpConfig> configMap,WebUser webUser,CpTomResults nextResult) {
		int resultCode = 200;//200成功响应 400失败
		try {
//			List<CpOrder> orderList = new ArrayList<CpOrder>();
			for (int i = 0; i < jsonArray.size(); i++) {
				CpOrder order = packOrderInfo(request,jsonArray, configMap,webUser, i,nextResult);
				StringBuffer buff = new StringBuffer("");
				buff.append(order.getGameTypeName()).append("-").append(order.getCpTypeName());
				int row1 = this.webAccountsDao.updateWebAccount(webUser.getUserName(), CommonConstant.CP_ACT_PRO_TYPE,CommonConstant.CP_BET_OUT, -order.getXzje(),buff.toString() + "，彩票下注", webUser.getUserName(),order.getXzdh());
				if(row1>0){
					this.cpOrderDao.saveOrUpdate(order);//owner订单
//					orderList.add(order);
				}else{
					throw new RuntimeException("下注失败！");
				}
			}
			
			//resultCode = this.webAccountsDao.saveShareadOrder(orderList);//shared订单
			//if(resultCode != 200){
			//	throw new RuntimeException("shared订单提交失败");
			//}
		} catch (Exception e) {
			resultCode = 400;
			e.printStackTrace();
			throw new RuntimeException("注单提交异常!!!");
		}
		return resultCode;
	}
	
	/**
	 * 组装订单信息
	 * @param jsonArray
	 * @param configMap
	 * @param nextResult
	 * @param webUser
	 * @param i
	 * @return
	 */
	private CpOrder packOrderInfo(HttpServletRequest request,JSONArray jsonArray,Map<String, CpConfig> configMap,WebUser webUser, int i,CpTomResults nextResult) {
		CpOrder order = new CpOrder();
		JSONObject json = jsonArray.getJSONObject(i);
		String cfgId = json.getString("id");
		double value = json.getDoubleValue("val");

		CpConfig config = configMap.get(cfgId);
		Date currDate = DateUtil.currentDate();
		Date gtDate = DateUtil.getGMT_4_Date();
		String orderNo = OrderNoUtils.getOrderNo(nextResult.getCode());

		double xzje = Double.valueOf(value);
		double rate = config.getPl();
		double zgje = MathUtil.mul(xzje, rate);
		double kyje = MathUtil.sub(zgje, xzje);

		order.setYj(0D);
		order.setKyje(kyje);
		order.setZgje(zgje);
		order.setXzje(Double.valueOf(value));
		order.setPl(String.valueOf(config.getPl()));
		if(config.getEnumCode().equals(CpCommonConstant.TMSB)){
			order.setNumber(request.getParameter("tmsb"));
		}else{
			order.setNumber(config.getNumber());
		}
		order.setCfgId(cfgId);
		order.setGameTypeCode(config.getGameTypeCode());
		order.setGameTypeName(config.getGameTypeName());
		order.setCpTypeCode(config.getCpTypeCode());
		order.setCpTypeName(config.getCpTypeName());
		order.setCpCateCode(config.getCpCateCode());
		order.setCpCateName(config.getCpCateName());
		order.setXzlxCode(config.getXzlxCode());
		order.setXzlxName(config.getXzlxName());
		order.setXzzuCode(config.getXzzuCode());
		order.setXzzuName(config.getXzzuName());

		order.setBackWaterRate(config.getBackWater() == null ? 0 : config.getBackWater());
		order.setIsSync(0);
		order.setBackWaterStatus(0);
		order.setQs(nextResult.getQs());
		StringBuffer contentBuffer = new StringBuffer("");
		contentBuffer.append(config.getCpCateName());
		if (StringUtils.isNotEmpty(config.getXzlxName())) {
			contentBuffer.append("&");
			contentBuffer.append(config.getXzlxName());
		}
		order.setBz(contentBuffer.toString() + " ： " + config.getNumber());
		order.setXzsj(gtDate);
		order.setUserIp(IPSeeker.getIpAddress(request));
		order.setSfjs(CommonConstant.CP_ORDER_STATUS_WJS);
		order.setXzdh(orderNo);
		order.setCreateTime(currDate);
		order.setModifyTime(currDate);
		order.setOrderStatus("未结算");
		order.setIsSync(0);
		order.setUserAgent(webUser.getUserAgent());

		contentBuffer.append(" ");
		contentBuffer.append(config.getNumber());
		contentBuffer.append("@");
		contentBuffer.append("<font color=\"#ff0000\">" + config.getPl() + "</font>");
		order.setContent(contentBuffer.toString());
		order.setUserName(webUser.getUserName());
		order.setWebFlag(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
		return order;
	}


	public double getUserSingleCredit(CpOrder order) {
		double price = 0.0;
		Map<String, Object> valMap = cpOrderDao.getUserSingleCredit(order);
		if(null == valMap || valMap.size() == 0){
			return price;
		}
		return (null == valMap.get("XZJE") ? price : Double.parseDouble(valMap.get("XZJE").toString()));
	}


	public double getUserSingleCreditForNumber(CpOrder order) {
		List<CpOrder> list = cpOrderDao.getUserSingleCreditForNumber(order);
		double xzje = 0.0;
		for (CpOrder cpOrder : list) {
			xzje += cpOrder.getXzje();
		}
		return xzje;
	}
	
	
	public CpOrder getCpOrderById(Integer id){
		return this.cpOrderDao.get(id);
	}
	
	
	/**
	 * 获取结算订单前几条
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<CpOrder>
	 */
	public List<CpOrder> getCpOrderLotteryUser(int limits){
		return this.cpOrderDao.getCpOrderLotteryUser(limits);
	}
}
