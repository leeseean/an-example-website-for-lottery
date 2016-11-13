/**   
* 文件名称: CpMainController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-12-31 下午4:06:28<br/>
*/  
package com.mh.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.WeekUtil;
import com.mh.entity.CpOrder;
import com.mh.service.CpOrderService;
import com.mh.web.login.UserContext;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-12-31 下午4:06:28<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/cpHistory")
public class CpOrderHistoryController extends BaseController{
	@Resource
	private CpOrderService cpOrderService;
 
	
	/**
	 * 查看下注明细
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/betDetail")
	public ModelAndView betDetail(HttpServletRequest request,HttpServletResponse response,CpOrder record){
		ModelAndView mv=new ModelAndView();
		UserContext uc = this.getUserContext(request);
		
		
		record.setUserName(uc.getUserName());
		record.setSfjs("0");
		Page page = newPage(request);
		this.cpOrderService.getReportOrderList(page,record);
		mv.addObject("page", page);
		
		mv.addObject("gameMap", CpConfigCache.GAME_OBJ_CACHE_MAP);
		mv.addObject("record", record);
		
		mv.setViewName("cp/data_center/betDetail");
		return mv;
	}
	
	/**
	 * 得到周结算订单历史账户
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 * @throws ParseException 
	 */
	@RequestMapping("/week")
	public ModelAndView week(HttpServletRequest request,
			HttpServletResponse response, CpOrder record) throws ParseException{
		UserContext uc = this.getUserContext(request);
		
		ModelAndView mv = new ModelAndView();
		Date currDate = DateUtil.currentDate();
		record.setUserName(uc.getUserName());
 
		
		int days = 6;
		if(StringUtils.isEmpty(record.getStartTimeStr()) && StringUtils.isEmpty(record.getEndTimeStr())){
			String startTimeStr = DateUtil.format(currDate, "yyyy-MM-dd");
			Date preDate = DateUtil.addDay(currDate, -days);
			String endTimeStr =  DateUtil.format(preDate, "yyyy-MM-dd");
			record.setStartTimeStr(endTimeStr);
			record.setEndTimeStr(startTimeStr);
		}else{
			Date start = DateUtil.parse(record.getStartTimeStr(), "yyyy-MM-dd");
			Date end = DateUtil.parse(record.getEndTimeStr(), "yyyy-MM-dd");
			days = DateUtil.beforeDays(start, end);
		}
		
		
		List<Map<String, Object>> timeList = new ArrayList<Map<String, Object>>();
		
		Date startTime = DateUtil.parse(record.getStartTimeStr(), "yyyy-MM-dd");
		Map<String,Map<String,Object>> dataMap = this.cpOrderService.selectCountOrder(record);
		for(int i=0; i<=days; i++ ){
			Date betTime = DateUtil.addDay(startTime, i);
			String betTimeStr = DateUtil.format(betTime, "yyyy-MM-dd");
			Map<String, Object> map = new HashMap<String, Object>();
			double xzje=0,winMoney=0,betUsrWin=0,backWaterMoney=0;
			if(dataMap.get(betTimeStr)!=null){
				Map<String,Object> orderMap = dataMap.get(betTimeStr);
				
				xzje =(orderMap.get("xzje")==null?0:Double.valueOf(orderMap.get("xzje").toString()));
				winMoney =(orderMap.get("winMoney")==null?0:Double.valueOf(orderMap.get("winMoney").toString()));
				betUsrWin =(orderMap.get("betUsrWin")==null?0:Double.valueOf(orderMap.get("betUsrWin").toString()));
				backWaterMoney =(orderMap.get("backWaterMoney")==null?0:Double.valueOf(orderMap.get("backWaterMoney").toString()));
			}
			map.put("xzje", xzje);
			map.put("winMoney",winMoney);
			map.put("backWaterMoney", backWaterMoney);
			
			map.put("betUsrWin", betUsrWin);
			map.put("betTime",betTimeStr);
			map.put("week",WeekUtil.getWeekOfDate(betTime));
			timeList.add(map);
		}
		mv.addObject("dataList", timeList);
		 
		
		mv.addObject("record", record);
		mv.addObject("gameMap", CpConfigCache.GAME_OBJ_CACHE_MAP);
		mv.setViewName("cp/data_center/betReport");
		return mv;
	}
	
	
	/**
	 * 日报表详情
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 * @throws ParseException  
	 * ModelAndView
	 */
	@RequestMapping("/day")
	public ModelAndView day(HttpServletRequest request,
			HttpServletResponse response) throws ParseException{
		UserContext uc = this.getUserContext(request);
		
		
		ModelAndView mv = new ModelAndView();
		String dateStr = request.getParameter("dateStr");
		String gameTypeCode = request.getParameter("gameTypeCode");
		
		CpOrder record = new CpOrder();
		record.setUserName(uc.getUserName());
		record.setStartTimeStr(dateStr);
		record.setEndTimeStr(dateStr);
		record.setGameTypeCode(gameTypeCode);
		record.setSfjs("1");
		Page page = newPage(request);
		
		
		this.cpOrderService.getReportOrderList(page,record);
		mv.addObject("page", page);
		
		mv.addObject("dateStr", dateStr);
		mv.addObject("gameTypeCode", gameTypeCode);
		mv.addObject("lx", "day");
		mv.addObject("gameMap", CpConfigCache.GAME_OBJ_CACHE_MAP);
		mv.setViewName("cp/data_center/betReport");
		return mv;
	}
	
	
}
