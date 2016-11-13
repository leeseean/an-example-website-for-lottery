package com.mh.web.controller.m;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.utils.DateUtil;
import com.mh.dao.CpResultsDao;
import com.mh.entity.CpHistoryResults;
import com.mh.entity.CpOrder;
import com.mh.service.CpOrderService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;


@Controller
@RequestMapping("/m/mobileCpAccount")
public class MCpAccountController extends BaseController{
	@Autowired
	private CpResultsDao cpResultsDao;
	
	@Autowired
	private CpOrderService cpOrderService;
 
	
	
	/**
	 *
	 */
	@RequestMapping("/goCpOrderSelect")
	public ModelAndView goCpOrderSelect(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		
		List<String> dateList=new ArrayList<String>();
		Date date = new Date();
		for(int i=0;i<7;i++){
			 Date d=DateUtil.addDay(date, -i);
			String dateStr=DateUtil.format(d, "yyyy-MM-dd");
			dateList.add(dateStr);
		}
		String initDate=DateUtil.format(date, "yyyy-MM-dd");
		mv.addObject("initDate", initDate);
		mv.addObject("dateList", dateList);
		
		mv.setViewName("m/cp/cpbet_order_filter");
		return mv;
	}
	
	
	/**
	 * 获取订单列表
	 * @Description: TODO 新版不区分是否结算订单查询
	 * @param    
	 * @return ModelAndView
	 * @author Andy
	 * @throws ServletRequestBindingException 
	 * @date 2015-10-9
	 */
	@RequestMapping("/getOrderList")
	public ModelAndView accountHis(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, ServletRequestBindingException{
		ModelAndView mv = new ModelAndView();
		UserContext userContext = this.getUserContext(request);
		String userName = userContext.getUserName();
		CpOrder bean = new CpOrder();
		
//		String pageName="cp_order_list";
		String pageName="lottery_order_list";
		String beginTime=ServletRequestUtils.getStringParameter(request, "beginTime","");
		String endTime=ServletRequestUtils.getStringParameter(request, "endTime","");
		String gameTypeCode = ServletRequestUtils.getStringParameter(request, "gameTypeCode","");
		String status=ServletRequestUtils.getStringParameter(request, "order_status");
		
		int start=0;
		int pageSize=20;//页面显示条数
		int countSize=100;//最多查询条数
		int pageNum=0;//分页的页面数量
		if(StringUtils.isNotBlank(status)){
			bean.setOrderStatus(status);
		}
		if(StringUtils.isBlank(beginTime)||StringUtils.isBlank(endTime)){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date nowDate=new Date();
			beginTime=sdf.format(nowDate);
			endTime=sdf.format(nowDate);		
		}
		
		
		bean.setStartTimeStr(beginTime);
		bean.setEndTimeStr(endTime);
		bean.setGameTypeCode(gameTypeCode);
		bean.setUserName(userName);
		
		

		List<CpOrder> orderList = this.cpOrderService.getOrderRecordList(bean,start,countSize);
		
		//Map<String,List<CpOrderRecord>> mapList=new HashMap<String, List<CpOrderRecord>>();
		List<List<CpOrder>> groupList= new ArrayList<List<CpOrder>>();
		if(orderList.size()>0){
			
			if(orderList.size()%pageSize==0){
				pageNum=orderList.size()/pageSize;
			}else{
				pageNum=(orderList.size()/pageSize)+1;
			}
			if(pageNum==0){
				groupList.add(orderList);
			}else{
				for(int i=1;i<=pageNum;i++){
					List<CpOrder> tmpList=new ArrayList<CpOrder>();
					
					if(i==pageNum){
						tmpList=getPageOrderList(orderList,(i-1)*pageSize,orderList.size());
					}else{
						tmpList=getPageOrderList(orderList,(i-1)*pageSize,i*pageSize);
					}
					groupList.add(tmpList);
				}
			}
			
		}
		mv.addObject("pageNum", pageNum);
		mv.addObject("groupList",groupList);
		//mv.addObject("orderList", orderList);
		mv.addObject("gameMap", CpConfigCache.CP_GAME_MAP);
		mv.setViewName("m/cp/"+pageName);
		return mv;
	}
	/**分页list取值**/
	
	private List<CpOrder> getPageOrderList(List<CpOrder> orderList,int start,int end){
		List<CpOrder> list=new ArrayList<CpOrder>();
		for(int j=start;j<end;j++){
			list.add(orderList.get(j));
		}
		return list;
	}
	/**
	 * 彩票开奖结果
	 */
	@RequestMapping("/findKjjgList")
	public ModelAndView findKjjgList(HttpServletRequest request,
			HttpServletResponse response){
		UserContext webUser = this.getUserContext(request);
		ModelAndView mv=new ModelAndView();
		String gameCode=request.getParameter("gameCode");
		gameCode=gameCode.toUpperCase();
		String rows="50";
		String viewName="m/cp/game_results";
		List<CpHistoryResults> resultList =  this.cpResultsDao.findHistoryResultsMap(gameCode, rows);
		mv.setViewName(viewName);
		mv.addObject("resultList", resultList);
		return mv.addObject("webUser", webUser);
		
		
		
	}
		
	
	
	
}
