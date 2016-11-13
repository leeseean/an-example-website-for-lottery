package com.mh.web.controller.m;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.SportBet;
import com.mh.entity.SportBetDetail;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchControl;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.WebRecords;
import com.mh.service.WapSportService;
import com.mh.service.WebSportService;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
import com.mh.web.util.MobilePage;
import com.mh.web.util.MobileSportUtil;
@Controller
@RequestMapping("/m/sport")
public class MSportController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(MSportController.class);
	
	private DecimalFormat df = new DecimalFormat("###0.000");
	
	@Autowired
	private WebSportService webSportService;
	
	@Autowired
	private WapSportService wapSportService;
	
	@Autowired
	private WebUserService webUserService;
	
	/**
	 * 串关记录
	 */
	private Map<String, SportBet> orderParam = new HashMap<String, SportBet>();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 体育首页
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView goSportList(HttpServletRequest request,HttpServletResponse response){
		UserContext webUser = this.getUserContext(request);
		return new ModelAndView("m/sport/sport_list").addObject("webUser", webUser);
	}
	
	/**
	 * 查看赛事详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goEventDetail")
	public ModelAndView goEventDetail(HttpServletRequest request,HttpServletResponse response){
		
		String matchType = request.getParameter("matchType");
		String timeType = request.getParameter("timeType");
		String rType = request.getParameter("rType");
		String tableName = request.getParameter("tableName");
		
		/**该参数用于下单逻辑中拼接返回url**/
		request.getSession().removeAttribute("tableName");
		if(StringUtils.isNotBlank(tableName)){
			request.getSession().setAttribute("tableName", tableName);
		}
				
		String pageName = "m/sport/" + matchType + "_" + rType;
		ModelAndView model = new ModelAndView();
		//获取控制器
		TMatchControl control = getControllerState(timeType,rType);
		/*if(null == control){
			return null;
		}*/
		//获取联赛分组
		//List<String> leagueList = wapSportService.getLeague(tableName, control.getCurtag());
		if(null != control && control.getCurstatus() == SportConstant.CURSTATUS_OK && control.getShowStatus() == SportConstant.SHOWSTATUS_OK){
			List<String> leagueList = webSportService.getLeague(control);
			model.addObject("leagueList", leagueList);
			
			//获取赛事详情
			getEventDetail(rType,"", model, control);
		}
		model.setViewName(pageName);
		
		UserContext webUser = this.getUserContext(request);
		return model.addObject("timeType", timeType).addObject("tableName", tableName).addObject("webUser", webUser);
	}
	
	/**
	 * 串关下注页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goP3Order")
	public ModelAndView goP3Order(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView("m/sport/order/order_p3_r");
		UserContext user = this.getUserContext(request);
		if(null == user){
			model.setViewName("m/no_login");
			return model;
		}
		
		SportBetDetail bet = initParams(request);
		//获取控制器
		TMatchControl control = getControllerState(bet.getTimeType(),bet.getRtype());
		/*if(null == control){
			return null;
		}*/
		
		model.addObject("bet", bet);
		double userMoney=webUserService.getWebUserMoney(user.getUserName());
		user.setUserMoney(Double.parseDouble(df.format(userMoney)));
		model.addObject("user", user);
		
		//TFtMatchP3 p3 = wapSportService.getFtMatchP3(control.getCurtag(),bet.getGid()).get(0);
		TFtMatchP3 p3 = wapSportService.getFtMatchP3(control,bet.getGid()).get(0);
		
		initP3Param(bet,p3);
		
		/**如果进入今日赛事的综合赛事页面  则清除早盘的综合赛事信息**/
		if(bet.getTimeType().equals("today")){
			SportConstant.ft_today.put(bet.getGid(), bet);
			orderParam = SportConstant.ft_today;
			SportConstant.ft_tom.clear();
		}else if(bet.getTimeType().equals("tom")){
			SportConstant.ft_tom.put(bet.getGid(), bet);
			orderParam = SportConstant.ft_tom;
			SportConstant.ft_today.clear();
		}
		double odds = 1;
		for (Map.Entry<String, SportBet> entry : orderParam.entrySet()) {
			odds *= Double.parseDouble(entry.getValue().getOdds());		//计算总赔率
		}
		
		/**综合赛事订单信息放在session中 下单后清除**/
		request.getSession().setAttribute("orderParam", orderParam);	 
		
		Map<String, String> param = initParam(request);
		model.addObject("param", param);
		return model.addObject("odds", df.format(odds)).addObject("size", orderParam.size());//串关数量 3个以上才能提交订单
	}
	
	/**
	 * 删除一条综合赛事订单 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/deleteEvent")
	public void deleteEvent(HttpServletRequest request,HttpServletResponse response){
		String gid = request.getParameter("gid");
		SportConstant.ft_today.remove(gid);
		SportConstant.ft_tom.remove(gid);
		Map<String, SportBet> map = (Map<String, SportBet>) request.getSession().getAttribute("orderParam");
		map.remove(gid);
		request.getSession().setAttribute("orderParam", map);
		
		double odds = 1;
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("rs", "0");//成功code
		resMap.put("size", map.size());
		
		for (Map.Entry<String, SportBet> entry : map.entrySet()) {
			odds *= Double.parseDouble(entry.getValue().getOdds());		//计算总赔率
		}
		
		resMap.put("odds", odds);
		
		respMessage(response, resMap);
		
		logger.info("删除串关gid" + gid + "结束");
	}
	
	private void respMessage(HttpServletResponse response,Map<String, Object> map){
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter print = null;
		try {
			print = response.getWriter();
			print.write(JSON.toJSONString(map));
		} 
		catch (IOException e) {
			logger.error("服务器异常" + e);
		}
		finally{
			if(null != print){
				print.flush();
				print.close();
			}
		}
	}
	
	/**
	 * 	初始化综合赛事信息
	 */
	public void initP3Param(SportBetDetail bet,TFtMatchP3 p3){
		String btype = bet.getBtype();
		String selection = bet.getSelection();
		String period = bet.getPeriod();
		
		bet.setLeague(p3.getLeague());
		bet.setTeam1(p3.getTeamH());
		bet.setTeam2(p3.getTeamC());
		
		bet.setPeriodName("f".equals(period) ? "全场" : "上半场");
		bet.setTeamName("H".equals(selection) ? p3.getTeamH() : "C".equals(selection) ? p3.getTeamC() : "和局");
		
		if("f".equals(period)){
			if("dy".equals(btype)){
				bet.setOddsDes("独赢 - <font color=\"red\">全场</font>");
			}else if("rq".equals(btype)){
				bet.setOddsDes("让球 - <font color=\"red\">全场</font>");
			}else if("dx".equals(btype)){
				bet.setOddsDes("大小 - <font color=\"red\">全场</font>");
			}else if("ds".equals(btype)){
				bet.setOddsDes("单双 - <font color=\"red\">全场</font>");
			}
		}else{
			if("dy".equals(btype)){
				bet.setOddsDes("独赢 - <font color=\"red\">上半场</font>");
			}else if("rq".equals(btype)){
				bet.setOddsDes("让球 - <font color=\"red\">上半场</font>");
			}else if("dx".equals(btype)){
				bet.setOddsDes("大小 - <font color=\"red\">上半场</font>");
			}
		}
		
		if("dy".equals(btype)){
			bet.setBtypeName("独赢");
		}else if("rq".equals(btype)){
			bet.setBtypeName("让球");
		}else if("dx".equals(btype)){
			bet.setBtypeName("大小");
		}else if("ds".equals(btype)){
			bet.setBtypeName("单双");
		}
		
		if(bet.getPeriod().equals("f")){
			if("dy".equals(btype)){//全场独赢赔率
				bet.setRemark(p3.getTeamH() + " VS " + p3.getTeamC());
				bet.setOdds("H".equals(selection) ? p3.getIorMh() : "C".equals(selection) ? p3.getIorMc() : p3.getIorMn());
			}else if("rq".equals(btype)){//全场让球赔率
				if("H".equals(p3.getStrong())){
					bet.setRemark(p3.getTeamH() + " " +  p3.getRatio() + " " + p3.getTeamC());
				}else{
					bet.setRemark(p3.getTeamC() + " " +  p3.getRatio() + " " + p3.getTeamH());
				}
				bet.setOdds("H".equals(selection) ? p3.getIorPrh() : p3.getIorPrc());
			}else if("dx".equals(btype)){//全场大小赔率
				bet.setRemark(p3.getTeamH() + " VS " + p3.getTeamC());
				bet.setOdds("H".equals(selection) ? p3.getIorPouc() : p3.getIorPouh());
				bet.setTeamName("H".equals(selection)? "大" + p3.getRatioU() : "小" + p3.getRatioO()) ;
			}else if("ds".equals(btype)){//全场单双赔率
				bet.setRemark(p3.getTeamH() + " VS " + p3.getTeamC());
				bet.setOdds("H".equals(selection) ? p3.getIorPeoo() : p3.getIorPeoe());
				bet.setTeamName("H".equals(selection)? "单 " : "双 ") ;
			}
		}else if(bet.getPeriod().equals("h")){
			if("dy".equals(btype)){//半场独赢赔率
				bet.setRemark(p3.getTeamH() + " VS " + p3.getTeamC());
				bet.setOdds("H".equals(selection) ? p3.getIorHmh() : "C".equals(selection) ? p3.getIorHmc() : p3.getIorHmn());
			}else if("rq".equals(btype)){//半场让球赔率
				if("H".equals(p3.getHstrong())){
					bet.setRemark(p3.getTeamH() + " " +  p3.getHratio() + " " + p3.getTeamC());
				}else{
					bet.setRemark(p3.getTeamC() + " " +  p3.getHratio() + " " + p3.getTeamH());
				}
				bet.setOdds("H".equals(selection) ? p3.getIorHprh() : p3.getIorHprc());
			}else if("dx".equals(btype)){//半场大小赔率
				bet.setRemark(p3.getTeamH() + " VS " + p3.getTeamC());
				bet.setOdds("H".equals(selection) ? p3.getIorHpouc() : p3.getIorHpouh());
				bet.setTeamName("H".equals(selection)? "大" + p3.getHratioU() : "小" + p3.getHratioO()) ;
			}
		}
	}
	
	
	/**
	 * 下注页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goOrder")
	public ModelAndView goOrder(HttpServletRequest request,HttpServletResponse response){
		UserContext user = this.getUserContext(request);
		ModelAndView model = new ModelAndView("m/sport/sport_order");
		
		if(null == user){
			model.setViewName("m/no_login");
			return model;
		}
		
		SportBetDetail bet = initParams(request);
		
		StringBuffer viewName = new StringBuffer("m/sport/order/");
		
		if(SportConstant.ft_rtype_pd.equals(bet.getRtype()) || SportConstant.ft_rtype_hpd.equals(bet.getRtype())){
			viewName.append("order_ft_pd");
		}else if(SportConstant.ft_rtype_r.equals(bet.getRtype()) || SportConstant.roll_rtype_re.equals(bet.getRtype())){
			viewName.append("order_ft_r");
		}else{
			viewName.append("order_" + bet.getMatchType().toLowerCase() + "_" + bet.getRtype());
		}
			
		model.setViewName(viewName.toString());
		
		//获取控制器
		TMatchControl control = getControllerState(bet.getTimeType(),bet.getRtype());
		/*if(null == control){
			return null;
		}*/
		
		double userMoney=webUserService.getWebUserMoney(user.getUserName());
		user.setUserMoney(Double.parseDouble(df.format(userMoney)));
		model.addObject("user", user);
		
		
		//赛事详情
		getEventDetail(bet.getRtype(),bet.getGid(),model,control);
		
		logger.info("页面跳转至:" + viewName + ".jsp");
		
		return model.addObject("bet", bet);
	}
	
	
	/**
	 	跳转注单查询
	 * @return
	 */
	@RequestMapping("/goOrderFilter")
	public ModelAndView goOrderFilter(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView("m/sport/order_filter");
		Date currDate = DateUtil.currentDate();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		Date nextDate = DateUtil.addDay(currDate, -30);
		String nextDateStr = DateUtil.format(nextDate, "yyyy-MM-dd");
		return model.addObject("currDateStr", currDateStr).addObject("nextDateStr", nextDateStr);
	}
	
	
	/**
	 * 注单查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getOrderList")
	public ModelAndView getOrderList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView("m/sport/order_list");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String sportType = request.getParameter("cateType");
		String status = request.getParameter("status");
		
		int pageNum = SportConstant.PAGE_NUM;	//默认分五页
		int pageSize = SportConstant.PAGE_SIZE;	//每页20条
		
		Date now = new Date();
		if(StringUtils.isBlank(beginTime)){
			beginTime = sdf.format(now);
		}
		if(StringUtils.isBlank(endTime)){
			endTime = sdf.format(now);
		}
		
		UserContext uc = this.getUserContext(request);
		WebRecords records = new WebRecords();
		records.setStartTime(beginTime);
		records.setEndTime(endTime);
		records.setUserName(uc.getUserName());
		records.setBetSportType(sportType);
		records.setBetStatus(status);
		records.setCount(pageNum * pageSize);
		List<TWebMatchBet> list = this.wapSportService.getWebMatchLists(records);
		
		pageNum = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
		MobilePage<TWebMatchBet> mp = new MobilePage<TWebMatchBet>();
		Map<String, List<TWebMatchBet>> map = mp.toPage(list, pageNum, pageSize);
		//页脚按钮个数
		List<String> pageList = new ArrayList<String>();
		for (int i = 1; i <= pageNum; i++){
			pageList.add("page"+i);
		}
		
		return model.addObject("map", map).addObject("pageList", pageList);
	}
	
	/**
	 * request参数
	 * @param request
	 * @return
	 */
	private Map<String, String> initParam(HttpServletRequest request){
		Map<String, String> param = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if(entry.getValue().length != 0){
				param.put(entry.getKey(), entry.getValue()[0]);
			}
		}
		return param;
	}
	

	/**
	 * 赛事详情
	 * @param rType
	 * @param model
	 * @param control
	 */
	private void getEventDetail(String rType, String gid, ModelAndView model,TMatchControl control) {
		if(SportConstant.ft_rtype_r.equals(rType)){
			//足球--单式
			//List<TFtMatchR> eventList = wapSportService.getFtMatchR(control.getCurtag(), gid);
			List<TFtMatchR> eventList = wapSportService.getFtMatchR(control, gid);
			model.addObject("eventList", eventList);
		}else if(SportConstant.ft_rtype_pd.equals(rType) || SportConstant.ft_rtype_hpd.equals(rType)){
			//足球--(半)波胆
			//List<TFtMatchPD> eventList = wapSportService.getFtMatchPD(control.getCurtag(), gid);
			List<TFtMatchPD> eventList = wapSportService.getFtMatchPD(control, gid);
			model.addObject("eventList", eventList);
		}else if(SportConstant.ft_rtype_t.equals(rType)){
			//足球--总入球
			//List<TFtMatchT> list = wapSportService.getFtMatchT(control.getCurtag(),gid);
			List<TFtMatchT> list = wapSportService.getFtMatchT(control,gid);
			model.addObject("eventList", list);
		}else if(SportConstant.ft_rtype_f.equals(rType)){
			//足球--半场/全场
			//List<TFtMatchF> list = wapSportService.getFtMatchF(control.getCurtag(),gid);
			List<TFtMatchF> list = wapSportService.getFtMatchF(control,gid);
			model.addObject("eventList", list);
		}else if(SportConstant.roll_rtype_re.equals(rType)){
			//足球--滚球
			//List<TRMatchRE> list = wapSportService.getRollMatchRE(control.getCurtag(),gid);
			List<TRMatchRE> list = wapSportService.getRollMatchRE(control,gid);
			model.addObject("eventList", list);
		}else if(SportConstant.bk_rtype_p3.equals(rType)){
			//足球--综合过关
			//List<TFtMatchP3> list = wapSportService.getFtMatchP3(control.getCurtag(),gid);
			List<TFtMatchP3> list = wapSportService.getFtMatchP3(control,gid);
			model.addObject("eventList", list);
		}else if(SportConstant.bk_rtype_rmain_cj.equals(rType)){
			//篮球--单式
			//List<TBkMatchRmain> list = wapSportService.getBkMatchRmain(control.getCurtag(),gid);
			List<TBkMatchRmain> list = wapSportService.getBkMatchRmain(control,gid);
			model.addObject("eventList", list);
		}else if(SportConstant.roll_rtype_remain.equals(rType)){
			//篮球--滚球
			//List<TRMatchRemain> list = wapSportService.getRollMatchRemain(control.getCurtag(), gid);
			List<TRMatchRemain> list = wapSportService.getRollMatchRemain(control, gid);
			model.addObject("eventList", list);
		}
	}
	
	/**
	 * 控制器状态
	 * @param timeType
	 * @param rType
	 * @return
	 */
	public TMatchControl getControllerState(String timeType,String rType){
		/*TMatchControl tMatchControl = webSportService.getMatchControl(timeType, rType);
		if(null != tMatchControl && SportConstant.CURSTATUS_OK == tMatchControl.getCurstatus() && SportConstant.SHOWSTATUS_OK == tMatchControl.getShowStatus()){
			return tMatchControl;
		}
		return null;*/
		return webSportService.getMatchControl(timeType, rType);
	}
	
	private SportBetDetail initParams(HttpServletRequest request) {
		SportBetDetail bet = new SportBetDetail();
		bet.setMatchType(request.getParameter("matchType").toUpperCase());
		bet.setTimeType(request.getParameter("timeType"));
		bet.setRtype(request.getParameter("rType"));
		bet.setPeriod(request.getParameter("period"));
		bet.setSelection(request.getParameter("selection"));
		bet.setBtype(request.getParameter("btype"));
		bet.setGid(request.getParameter("gid"));
		bet.setDtype(null == request.getParameter("dtype") ? request.getParameter("btype") : request.getParameter("dtype"));
		
		String money = null == request.getParameter("money") ? "0" : request.getParameter("money");
		if(money.endsWith(".")){
			money = money.substring(0, money.length() - 1);
		}
		bet.setMoney(Double.parseDouble(money));
		
		bet.setLimitBetMin(String.valueOf(SportConstant.FT_LIMIT_MIN));
		
		if(bet.getMatchType().equals("bk")){
			MobileSportUtil.limitBk(bet);
		}else{
			MobileSportUtil.limitFt(bet);
		}
		
		return bet;
	}
}
