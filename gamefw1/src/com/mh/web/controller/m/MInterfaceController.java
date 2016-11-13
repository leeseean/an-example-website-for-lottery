/**   
* 文件名称: MInterfaceController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2016-7-2 上午10:57:49<br/>
*/  
package com.mh.web.controller.m;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.xb.cmbase.model.CpGame;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.CommonUtils;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.MathUtil;
import com.mh.commons.utils.ResponsePrintUtils;
import com.mh.dao.CpResultsDao;
import com.mh.entity.CpHistoryResults;
import com.mh.entity.CpOrder;
import com.mh.entity.WebUser;
import com.mh.service.CpOrderService;
import com.mh.service.CpResultsService;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;

/** 
 * 
 * 彩票接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2016-7-2 上午10:57:49<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/m")
public class MInterfaceController extends BaseController {
	
	@Autowired
	private CpOrderService cpOrderService;
	@Autowired
	private CpResultsService cpResultsService;
	@Autowired
	private CpResultsDao cpResultsDao;
	@Autowired
	private WebUserService webUserService;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/wap")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
	
 
		return new ModelAndView("wap/main");
	}
	
	@RequestMapping("/wap/member")
	public ModelAndView member(HttpServletRequest request,
			HttpServletResponse response) {
	
		UserContext uc = this.getUserContext(request);
		return new ModelAndView("wap/member").addObject("webUser", uc);
	}
	
	
	/**
	 * 当前期历史开奖结果
	 * 方法描述: TODO</br> 
	 * @param letterid
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/results/current")
	public void resultsCurrent(HttpServletRequest request, HttpServletResponse response){
		try{
			Map<String,String> gameMap = CpConfigCache.CP_GAME_MAP;
			JSONArray jsonArray = new JSONArray();
			List<CpHistoryResults> list =null;
			for(String code:gameMap.keySet()){
				list = cpResultsService.findHistoryResultsMap(code,"1");
				if(list.size()>0){
					JSONObject obj = new JSONObject();
					CpHistoryResults result = list.get(0);
					obj.put("game_code", result.getCode());
					obj.put("game_name", gameMap.get(code));
					obj.put("opencode", result.getKjjg());
					String formatCode = result.getKjjg();
					StringBuffer buff = new StringBuffer("");
					if(CommonConstant.CAKENO_CODE_PARAM.equals(result.getCode()) || CommonConstant.BJKL8_CODE_PARAM.equals(result.getCode())){
						String[] codeArr =result.getKjjg().split(",");
						int total =0;
						for(int i=0;i<codeArr.length;i++){			
							buff.append(codeArr[i]);
							if(i==codeArr.length-1){
								buff.append("=");
							}else{
								buff.append("+");
							}
							total += Integer.valueOf(codeArr[i]);
						}
						buff.append(total);	
					}else if(CommonConstant.HK_CODE_PARAM.equals(result.getCode())){
						String[] codeArr =result.getKjjg().split(",");
					 
						for(int i=0;i<codeArr.length;i++){			
							
							if(i==codeArr.length-1){
								buff.append("+");
							}else{
								if(i>0){
									buff.append(",");
								}
								
							}
							buff.append(codeArr[i]);
						}
						
					}else{
						buff.append(formatCode);
					}
					obj.put("formatOpenCode",buff.toString());
	 
					Date openTime = null;
					try {
						openTime= DateUtil.parse(result.getKjsj(), DateUtil.YMDHMS_PATTERN);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					obj.put("open_time",openTime.getTime()/1000);
					
					obj.put("qs", result.getQs());
					jsonArray.add(obj);
				}
			}
			ResponsePrintUtils.outPrintSuccess(request, response, jsonArray);
		}catch(RuntimeException e){
			ResponsePrintUtils.outPrintFail(request, response, "获取当前期历史开奖结果");
			logger.error("获取当前期历史开奖结果",e);
		}
	}
	
	
	/**
	 * 历史开奖结果
	 * 方法描述: TODO</br> 
	 * @param gameCode 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/results/history/{gameCode}")
	public void resultsHistory(@PathVariable(value = "gameCode") String gameCode,HttpServletRequest request, HttpServletResponse response){
		try{
			JSONArray jsnoArray = new JSONArray();
			JSONObject all = new JSONObject();
			String gameName = CpConfigCache.CP_GAME_MAP.get(gameCode);
			List<CpHistoryResults> list = cpResultsService.findHistoryResultsMap(gameCode,"1");
			JSONObject current = new JSONObject();
			if(list!=null&&list.size()>0){
				CpHistoryResults results =list.get(0);
				current.put("game_code", results.getCode());
				current.put("game_name",gameName);
				current.put("opencode", results.getKjjg());
				Date openTime = null;
				try {
					openTime= DateUtil.parse(results.getKjsj(), DateUtil.YMDHMS_PATTERN);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				current.put("open_time",openTime.getTime()/1000);
				current.put("qs", results.getQs());
			}
			all.put("current", current);
			
		
			List<CpHistoryResults> resultList =  this.cpResultsDao.findHistoryResultsMap(gameCode.toUpperCase(), "200");
			for (CpHistoryResults cpHistoryResults : resultList) {
				JSONObject obj = new JSONObject();
				obj.put("game_code", cpHistoryResults.getCode());
				obj.put("game_name",gameName);
				obj.put("opencode", cpHistoryResults.getKjjg());
				Date openTime = null;
				try {
					openTime= DateUtil.parse(cpHistoryResults.getKjsj(), DateUtil.YMDHMS_PATTERN);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String formatCode = cpHistoryResults.getKjjg();
				StringBuffer buff = new StringBuffer("");
				if(CommonConstant.CAKENO_CODE_PARAM.equals(cpHistoryResults.getCode()) || CommonConstant.BJKL8_CODE_PARAM.equals(cpHistoryResults.getCode())){
					String[] codeArr =cpHistoryResults.getKjjg().split(",");
					int total =0;
					for(int i=0;i<codeArr.length;i++){			
						buff.append(codeArr[i]);
						if(i==codeArr.length-1){
							buff.append("=");
						}else{
							buff.append("+");
						}
						total += Integer.valueOf(codeArr[i]);
					}
					buff.append(total);	
				}else if(CommonConstant.HK_CODE_PARAM.equals(cpHistoryResults.getCode())){
					String[] codeArr =cpHistoryResults.getKjjg().split(",");				 
					for(int i=0;i<codeArr.length;i++){									
						if(i==codeArr.length-1){
							buff.append("+");
						}else{
							if(i>0){
								buff.append(",");
							}							
						}
						buff.append(codeArr[i]);
					}
					
				}else{
					buff.append(formatCode);
				}
				obj.put("formatOpenCode",buff.toString());
				
				obj.put("open_time",openTime.getTime()/1000);
				obj.put("qs", cpHistoryResults.getQs());
				jsnoArray.add(obj);
			}
			all.put("history", jsnoArray);
			ResponsePrintUtils.outPrintSuccess(request, response, all);
		}catch(RuntimeException e){
			ResponsePrintUtils.outPrintFail(request, response, "获取历史开奖结果");
			logger.error("获取当历史开奖结果",e);
		}
	}
	
	
	/**
	 * 获取游戏列表
	 * 方法描述: TODO</br> 
	 * @param gameCode
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/game/list")
	public void gameList(HttpServletRequest request, HttpServletResponse response){
		try{
			Map<String,CpGame> gameMap = CpConfigCache.GAME_OBJ_CACHE_MAP;
			JSONArray jsonArray = new JSONArray();
			CpGame cpGame = null;
			for(String code:gameMap.keySet()){
				cpGame = gameMap.get(code);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", cpGame.getId());
				jsonObject.put("type_code", cpGame.getBigtypeCode());
				jsonObject.put("game_code", cpGame.getGameTypeCode());
				jsonObject.put("game_name", cpGame.getGameTypeName());
				jsonArray.add(jsonObject);
			}
			ResponsePrintUtils.outPrintSuccess(request, response,jsonArray);
		}catch(RuntimeException e){
			ResponsePrintUtils.outPrintFail(request, response, "获取游戏列表失败");
			logger.error("获取游戏列表失败",e);
		}
	}

	/**
	 * 订单列表接口
	 * 方法描述: TODO</br> 
	 * @param gameCode
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/order/history")
	public void orderHistory(HttpServletRequest request, HttpServletResponse response){
		try{
			UserContext uc = this.getUserContext(request);
			JSONArray jsonArray = new JSONArray();
			JSONObject all = new JSONObject();
			
			CpOrder bean = new  CpOrder();
			String gameCode = request.getParameter("gameCode");
			String pageNo = request.getParameter("pageNo");
			String pageSize = request.getParameter("pageSize");
			String day = request.getParameter("day");
			if(!StringUtils.isBlank(gameCode)){
				bean.setGameTypeCode(gameCode);
			}
			if(StringUtils.isBlank(pageNo)){
				pageNo = "1";
			}
			if(StringUtils.isBlank(pageSize)){
				pageSize = "20";
			}
			
			Date currDate = new Date();
			if(StringUtils.isBlank(day)){
				day = "1";
			}
			int _pageNo = Integer.valueOf(pageNo);
			int _pageSize = Integer.valueOf(pageSize);
			
			int _day = Integer.valueOf(day);
			Date beginDate = DateUtil.addDay(currDate, -_day);
			if(_day != 0) {
				bean.setStartTimeStr(DateUtil.format(beginDate, DateUtil.YEAR_MONTH_DAY_PATTERN_MIDLINE));
				bean.setEndTimeStr(DateUtil.format(currDate, DateUtil.YEAR_MONTH_DAY_PATTERN_MIDLINE));
			}
			bean.setUserName(uc.getUserName());
			Page page = new Page();
			page.setPageNo(_pageNo);
			page.setPageSize(_pageSize);
			
			cpOrderService.getReportOrderList(page, bean);
			List list = page.getResult();
			for(int i=0;i<list.size();i++){
				Map<String,Object> valMap = (Map<String, Object>) list.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id",valMap.get("ID").toString());
				jsonObject.put("user_name", valMap.get("USER_NAME").toString());
				jsonObject.put("game_code",valMap.get("GAME_TYPE_CODE").toString());
				jsonObject.put("game_name", valMap.get("GAME_TYPE_NAME").toString());
				jsonObject.put("cp_type_name", valMap.get("CP_TYPE_NAME").toString());
				jsonObject.put("cp_cate_name",valMap.get("CP_CATE_NAME").toString());
				jsonObject.put("bet_number", valMap.get("NUMBER").toString());
				String xzsj = valMap.get("XZSJ").toString();
				Date betTime = DateUtil.parse(xzsj, DateUtil.YMDHMS_PATTERN);
 
				jsonObject.put("bet_time",betTime.getTime()/1000);
				jsonObject.put("qs",   valMap.get("QS")==null?"":valMap.get("QS").toString());
				jsonObject.put("pl",   valMap.get("PL") == null?0:valMap.get("PL").toString());
				jsonObject.put("xzje", valMap.get("XZJE")==null?0:Double.valueOf(valMap.get("XZJE").toString()));
				jsonObject.put("kyje", valMap.get("KYJE")==null?0:Double.valueOf(valMap.get("KYJE").toString()));
				jsonObject.put("zgje", valMap.get("ZGJE")==null?0:Double.valueOf(valMap.get("ZGJE").toString()));
				jsonObject.put("hysf", valMap.get("HYSF")==null?0:Double.valueOf(valMap.get("HYSF").toString()));
				
				jsonObject.put("open_code", valMap.get("KJJG")==null?"":valMap.get("KJJG").toString());
				jsonObject.put("open_time", valMap.get("OPEN_TIME")==null?"":valMap.get("OPEN_TIME").toString());
				jsonObject.put("order_status", valMap.get("ORDER_STATUS")==null?"尚未开奖":valMap.get("ORDER_STATUS").toString());
				jsonObject.put("back_water_rate",  valMap.get("BACK_WATER_RATE")==null?0:Double.valueOf(valMap.get("BACK_WATER_RATE").toString()));
				jsonObject.put("back_water_money", valMap.get("BACK_WATER_MONEY")==null?0:Double.valueOf(valMap.get("BACK_WATER_MONEY").toString()));
				jsonArray.add(jsonObject);
			}
			
			all.put("pageNo", page.getPageNo());
			all.put("pageSize", page.getPageSize());
			all.put("totalPage", page.getTotalPages());
			all.put("totalCount", page.getTotalCount());
			all.put("records", jsonArray);
			
			ResponsePrintUtils.outPrintSuccess(request, response,all);
		}catch(Exception e){
			ResponsePrintUtils.outPrintFail(request, response, "获取订单列表接口失败");
			logger.error("获取订单列表接口失败",e);
		}
	}
	
	
	/**
	 * 订单详情接口
	 * 方法描述: TODO</br> 
	 * @param gameCode
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/order/detail/{orderid}")
	public void orderDetail(@PathVariable(value = "orderid") Integer orderid,HttpServletRequest request, HttpServletResponse response){
		try{
			CpOrder cpOrder = this.cpOrderService.getCpOrderById(orderid);
 
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", cpOrder.getId());
			jsonObject.put("user_name", cpOrder.getUserName());
			jsonObject.put("game_code", cpOrder.getGameTypeCode());
			jsonObject.put("game_name", cpOrder.getGameTypeName());
			jsonObject.put("cp_type_name", cpOrder.getCpTypeName());
			jsonObject.put("cp_cate_name", cpOrder.getCpCateName());
			jsonObject.put("bet_number", cpOrder.getNumber());
			jsonObject.put("bet_time",cpOrder.getXzsj().getTime()/1000);
			jsonObject.put("qs", cpOrder.getQs()==null?"":cpOrder.getQs());
			jsonObject.put("pl", cpOrder.getPl());
			jsonObject.put("xzje", cpOrder.getXzje());
			jsonObject.put("kyje", cpOrder.getKyje()==null?0:cpOrder.getKyje());
			jsonObject.put("zgje", cpOrder.getZgje()==null?0:cpOrder.getZgje());
			jsonObject.put("hysf", cpOrder.getHysf()==null?0:cpOrder.getHysf());
			
			jsonObject.put("open_code", cpOrder.getKjjg()==null?"": cpOrder.getKjjg());
			jsonObject.put("open_time", cpOrder.getOpenTime()==null?"": cpOrder.getOpenTime());
			jsonObject.put("order_status", cpOrder.getOrderStatus()==null?"": cpOrder.getOrderStatus());
			jsonObject.put("back_water_rate", cpOrder.getBackWaterRate()==null?0:cpOrder.getBackWaterRate());
			jsonObject.put("back_water_money", cpOrder.getBackWaterMoney()==null?0: cpOrder.getBackWaterMoney());
			 
			ResponsePrintUtils.outPrintSuccess(request, response,jsonObject);
		}catch(RuntimeException e){
			ResponsePrintUtils.outPrintFail(request, response, "订单详情接口失败");
			logger.error("订单详情接口失败",e);
		}
	}
	
	
	/**
	 * 订单列表接口
	 * 方法描述: TODO</br> 
	 * @param gameCode
	 * @param request
	 * @param response  
	 * void
	 */ 
	@RequestMapping("/userInfo")
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response){
		try{
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("user_name", webUser.getUserName());
			jsonObject.put("balance", MathUtil.round(webUser.getUserMoney(), 3));
			ResponsePrintUtils.outPrintSuccess(request, response,jsonObject);
		}catch(RuntimeException e){
			ResponsePrintUtils.outPrintFail(request, response, "订单详情接口失败");
			logger.error("订单详情接口失败",e);
		}
	}
	
	/**
	 * 获取抽奖用户信息
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/lottery/info")
	public void getLotteryInfo(HttpServletRequest request, HttpServletResponse response){
		try{
			List<CpOrder> orderList = this.cpOrderService.getCpOrderLotteryUser(20);
			JSONArray jsonArray = new JSONArray();
			CpOrder cpOrder = null;
			for(int i=0;i<orderList.size();i++){
				 cpOrder = orderList.get(i);
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("user_name", CommonUtils.getBeginStartString(cpOrder.getUserName()));
				 jsonObject.put("game_name", cpOrder.getGameTypeName());
				 jsonObject.put("win_money",cpOrder.getWinMoney()==null?0:cpOrder.getWinMoney());
				 jsonArray.add(jsonObject);
			}
			ResponsePrintUtils.outPrintSuccess(request, response,jsonArray);
		}catch(RuntimeException e){
			ResponsePrintUtils.outPrintFail(request, response, "获取抽奖用户信息失败");
			logger.error("获取抽奖用户信息失败",e);
		}
	}
	
}
