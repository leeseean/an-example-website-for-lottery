/**   
* 文件名称: WebSportController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-14 上午9:49:19<br/>
*/  
package com.mh.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.WeekUtil;
import com.mh.entity.SportCount;
import com.mh.entity.SportRollCount;
import com.mh.entity.TBkMatchP3;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchControl;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
import com.mh.entity.TResultMatchBk;
import com.mh.entity.TResultMatchFt;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUser;
import com.mh.ifc.util.ComUtil;
import com.mh.service.WebRecordService;
import com.mh.service.WebSportResultsService;
import com.mh.service.WebSportService;
import com.mh.service.WebUserService;
import com.mh.web.login.UserContext;
import com.mh.web.servlet.MySessionContext;
import com.mh.web.util.SportFresherUtil;

/**
 * 体育Controller
 * 体育 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-14 上午9:49:19<br/>
 */

@Controller
@RequestMapping("/sport")
public class WebSportController extends BaseController{
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WebSportService webSportService;
	
	@Autowired
	private WebRecordService webRecordService;
	
	@Autowired
	private WebSportResultsService webSportResultsService;
	
	
	/** 视图常量 **/
	private int CURSTATUS_OK = 1;
	private int SHOWSTATUS_OK = 1;
	
	private int searchLeague = 0;// 是否是联赛选择
	private SportCount sc;
	private SportRollCount src;

	private String typeName;// 球队

	private TMatchControl control;
	private int curPage;// 当前页
	private List<String> leagues;
	
	
	private List<TFtMatchR> listR;
	private List<TFtMatchPD> listPD;
	private List<TFtMatchT> listT;
	private List<TFtMatchF> listF;
	private List<TFtMatchP3> listP3;
	private List<TBkMatchRmain> listBKRMAIN;
	private List<TBkMatchP3> listBKP3;
	private List<TRMatchRE> listRE;
	private List<TRMatchRemain> listREMAIN;
 



	/**
	 * 跳转到体育首页
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param code
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("sport/sport_main");
	}
	
	/**
	 * 跳转到导航菜单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param code
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/headNav")
	public ModelAndView headNav(HttpServletRequest request,HttpServletResponse response){
		WebUser webUser = null;
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			UserContext uc = this.getUserContext(request);
			if(uc!=null){
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
			
		String gmt_4_Date = DateUtil.getGMT_4_String("yyyy/MM/dd HH:mm:ss");
		return new ModelAndView("sport/nav_head")
			.addObject("gmt_4_Date", gmt_4_Date)
			.addObject("webUser", webUser);
	}
	
	
	/**
	 * 跳转到左侧订单列表
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @param code
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/orderSide")
	public ModelAndView orderSide(HttpServletRequest request,HttpServletResponse response){
		WebUser webUser = null;
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			UserContext uc = this.getUserContext(request);
			if(uc!=null){
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
		
		return new ModelAndView("sport/order_side").addObject("webUser", webUser);
	}
	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/none")
	public ModelAndView none(HttpServletRequest request,HttpServletResponse response){
		src = new SportRollCount();
		control = getMatchControl(SportConstant.TIME_TYPE_ROLL, SportConstant.roll_rtype_re);
		src.setFtCount(null == control ? 0 : control.getCurcount());
		control = getMatchControl(SportConstant.TIME_TYPE_ROLL, SportConstant.roll_rtype_remain);
		src.setBkCount(null == control ? 0 : control.getCurcount());
		
		WebUser webUser = null;
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			UserContext uc = this.getUserContext(request);
			if(uc!=null){
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
		return new ModelAndView("sport/none")
			.addObject("src", src)
			.addObject("control", control)
			.addObject("webUser", webUser);
	}
	
	
	
	/**
	 * 跳转到赛事中心列表
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goMatchCenter")
	public ModelAndView goMatchCenter(HttpServletRequest request,HttpServletResponse response,@RequestParam("timeType") String timeType,
			@RequestParam("matchType") String matchType,@RequestParam("rtype") String rtype){
		ModelAndView view = new ModelAndView();
		
		/**判断是否维护**/
		String msg = WebSiteManagerConstants.ctxMap.get(CommonConstant.PLAT_PARAM_HUANGGUAN+"_"+WebConstants.WEB_WEIHU_STATUS_OFF);
		if(StringUtils.isNotBlank(msg)){
			view.setViewName("sport/weihu");
			view.addObject("msg", msg);
			return view;
		}
		
		String reFlag = request.getParameter("reFlag");
		
		String rtypeTemp = rtype;
		
		initRTypeName(timeType, matchType,rtype);
	 
		if(!"roll".equals(timeType) && "bk".equals(matchType)){
			rtype = matchType+"_"+rtype;
		}
		curPage = NumberUtils.toInt(request.getParameter("matchPage"), 1);
		long start = System.currentTimeMillis();
		/** 控制器 **/
		control = getMatchControl(timeType, rtype);
		/** 采集没有异常，而且可展示 **/
		if (control != null && control.getCurstatus() == CURSTATUS_OK && control.getShowStatus() == SHOWSTATUS_OK) {
			String[] leaArr = request.getParameterValues("lea");
			String leas = "";
			if(leaArr!=null && leaArr.length>0){
				Map<String,String> leaMap = new HashMap<String, String>();
				List<String> leaList = new ArrayList<String>();
				for(String name:leaArr){
					if (StringUtils.isNotBlank(name)) {
						leaList.add(name.trim());
						leaMap.put(name, name);
					}
				}
				view.addObject("leaMap", leaMap);
				leas = StringUtils.join(leaList.toArray(),",");
				view.addObject("leas", leas);
			}
			if("1".equals(reFlag)){
				String leaTemp = request.getParameter("leas");
				if(!StringUtils.isEmpty(leaTemp)){
					try {
						leaTemp = URLDecoder.decode(leaTemp, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					leas = leaTemp;
				}
			}
			
			
			String tableName = "";
			//足球 -单式
			if(SportConstant.ft_rtype_r.equals(rtype)){
				tableName = SportConstant.TABLE_FT_MATCH_R;
				//List<TFtMatchR> list = this.webSportService.getFtMatchR(control.getCurtag(),curPage,leas);
				List<TFtMatchR> list = this.webSportService.getFtMatchR(control,curPage,leas);
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<list.size();i++){
					TFtMatchR matchR = list.get(i);
					matchR.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", matchR.getId());
					valMap.put("league", matchR.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				
				view.addObject("datas", jst);
				view.addObject("dataList", list);
			//足球 -波胆 半场 波胆
			}else if(SportConstant.ft_rtype_pd.equals(rtype) || SportConstant.ft_rtype_hpd.equals(rtype)){
				tableName = SportConstant.TABLE_FT_MATCH_PD;
				//List<TFtMatchPD> pdList = this.webSportService.getFtMatchPD(control.getCurtag(),curPage,leas);
				List<TFtMatchPD> pdList = this.webSportService.getFtMatchPD(control,curPage,leas);
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<pdList.size();i++){
					TFtMatchPD match = pdList.get(i);
					match.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				
				view.addObject("datas", jst);
				view.addObject("dataList", pdList);
			//足球-总入球
			}else if(SportConstant.ft_rtype_t.equals(rtype)){
				tableName = SportConstant.TABLE_FT_MATCH_T;
				//List<TFtMatchT> matchT =  this.webSportService.getFtMatchT(control.getCurtag(),curPage,leas);
				List<TFtMatchT> matchT =  this.webSportService.getFtMatchT(control,curPage,leas);
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<matchT.size();i++){
					TFtMatchT match = matchT.get(i);
					match.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				view.addObject("datas", jst);
				view.addObject("dataList", matchT);
			//足球 -全场/半场
			}else if(SportConstant.ft_rtype_f.equals(rtype)){
				tableName = SportConstant.TABLE_FT_MATCH_F;
				//List<TFtMatchF> matchT = this.webSportService.getFtMatchF(control.getCurtag(),curPage,leas);
				List<TFtMatchF> matchT = this.webSportService.getFtMatchF(control,curPage,leas);
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<matchT.size();i++){
					TFtMatchF match = matchT.get(i);
					match.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				view.addObject("datas", jst);
				view.addObject("dataList", matchT);
			//足球 -综合过关
			}else if(SportConstant.ft_rtype_p3.equals(rtype)){
				tableName = SportConstant.TABLE_FT_MATCH_P3;
				//List<TFtMatchP3> p3 = this.webSportService.getFtMatchP3(control.getCurtag(),curPage,leas);
				List<TFtMatchP3> p3 = this.webSportService.getFtMatchP3(control,curPage,leas);
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<p3.size();i++){
					TFtMatchP3 match = p3.get(i);
					match.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				view.addObject("datas", jst);
				view.addObject("dataList", p3);
			//篮球 -单式
			}else if(SportConstant.bk_rtype_rmain_cj.equals(rtype)){
				tableName = SportConstant.TABLE_BK_MATCH_RMAIN;
				//List<TBkMatchRmain> rmain = this.webSportService.getBkMatchRmain(control.getCurtag(),curPage,leas);
				List<TBkMatchRmain> rmain = this.webSportService.getBkMatchRmain(control,curPage,leas);
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<rmain.size();i++){
					TBkMatchRmain match = rmain.get(i);
					match.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				view.addObject("datas", jst);
				view.addObject("dataList", rmain);
			// 篮球- 综合过关
			}else if(SportConstant.bk_rtype_p3_cj.equals(rtype)){
				tableName = SportConstant.TABLE_BK_MATCH_P3;
				//List<TBkMatchP3> p3 = this.webSportService.getBkMatchP3(control.getCurtag(),curPage,leas);
				List<TBkMatchP3> p3 = this.webSportService.getBkMatchP3(control,curPage,leas);
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<p3.size();i++){
					TBkMatchP3 match = p3.get(i);
					match.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				view.addObject("datas", jst);
				view.addObject("dataList", p3);
			// 滚球-足球
			}else if(SportConstant.roll_rtype_re.equals(rtype)){
				tableName = SportConstant.TABLE_R_MATCH_RE;
				//List<TRMatchRE> rollFtList = this.webSportService.getRollMatchRE(control.getCurtag(),curPage,leas);
				List<TRMatchRE> rollFtList = this.webSportService.getRollMatchRE(control,curPage,leas);
				initRollTimerRE(rollFtList);
				rollFtList = SportFresherUtil.getRMathRE(listRE, rollFtList);
				listRE =rollFtList;
				 
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<rollFtList.size();i++){
					TRMatchRE match = rollFtList.get(i);
					match.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				
				view.addObject("datas", jst);
				
				view.addObject("dataList",rollFtList );
			//滚球-篮球
			}else if(SportConstant.roll_rtype_remain.equals(rtype)){
				tableName = SportConstant.TABLE_R_MATCH_REMAIN;
				//List<TRMatchRemain> rollBkList =  this.webSportService.getRollMatchRemain(control.getCurtag(),curPage,leas);
				List<TRMatchRemain> rollBkList =  this.webSportService.getRollMatchRemain(control,curPage,leas);
				initRollTimerRemain(rollBkList);
				
				rollBkList = SportFresherUtil.getRMatchRemain(listREMAIN, rollBkList);
				listREMAIN =rollBkList;
				List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<rollBkList.size();i++){
					TRMatchRemain match = rollBkList.get(i);
					match.setId(generateRandomNum());
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				view.addObject("datas", jst);
				view.addObject("dataList",rollBkList);
			}
			logger.info(tableName);
			
			
			//leagues = this.webSportService.getLeague(tableName, control.getCurtag());
			leagues = this.webSportService.getLeague(control);
			view.addObject("leagues", leagues);
		}
		view.addObject("typeName", typeName);
		view.addObject("control", control);
		view.addObject("sc", sc);
		view.addObject("curPage", curPage);
		view.addObject("searchLeague", searchLeague);
		view.addObject("timeType", timeType);
		view.addObject("matchType", matchType);
		view.addObject("rtype", rtypeTemp);
		
		String timeTypeTemp=timeType.toLowerCase();
		if("today".equals(timeType) || "tom".equals(timeType)){
			timeTypeTemp = "to";
		}
		 
		
		String pageView = "sport/match/"+timeTypeTemp+"_"+matchType.toLowerCase()+"_"+rtypeTemp.toLowerCase();
		if("1".equals(reFlag)){
			pageView = "sport/match/frasher/data_"+timeTypeTemp+"_"+matchType.toLowerCase()+"_"+rtypeTemp.toLowerCase();
		}
		long end = System.currentTimeMillis();
		logger.info("耗时:" + (end - start) + "ms");
		logger.info("跳转页面:"+pageView);
		view.setViewName(pageView);
		return view;
	}
	
	
 
	
	
	
	private void initRollTimerRE(List<TRMatchRE> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			String tmp = null;
			String str = null;
			String[] strs = null;
			for (TRMatchRE trMatchRE : list) {

				/** 上下半场 **/
				if (StringUtils.isNotBlank(trMatchRE.getRetimeset())) {
					str = trMatchRE.getRetimeset();
					strs = str.split("H\\^");
					if (strs != null && strs.length >= 2) {
						if (StringUtils.equalsIgnoreCase(strs[0], "1")) {
							tmp = "上半场 " + strs[1];
							trMatchRE.setRetimeset(tmp);
							trMatchRE.setSetType("1");
						} else if (StringUtils.equalsIgnoreCase(strs[0], "2")) {
							tmp = "下半场 " + strs[1];
							trMatchRE.setRetimeset(tmp);
							trMatchRE.setSetType("2");
						}
					}

					if (str.contains("半场")) {
						trMatchRE.setRetimeset("半场");
						trMatchRE.setSetType("3");
					}

					/** 中 **/
					tmp = trMatchRE.getTeamH();
					if (tmp.contains("[中]")) {
						trMatchRE.setTeamH(tmp.replace("[中]", "<font color='blue'>[中]</font>"));
					}
					tmp = trMatchRE.getTeamC();
					if (tmp.contains("[中]")) {
						trMatchRE.setTeamC(tmp.replace("[中]", "<font color='blue'>[中]</font>"));
					}

				}
			}
		}
	}

	
	
	private void initRollTimerRemain(List<TRMatchRemain> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			String tmp = null;
			String str = null;
			for (TRMatchRemain m : list) {

				/** 第几节 **/
				if (StringUtils.isNotBlank(m.getNowsession())) {
					str = m.getNowsession();
					if (str.startsWith("Q")) {
						tmp = str.substring(1);
						if (StringUtils.equals(tmp, "1")) {
							m.setNowsession("第一节");
						} else if (StringUtils.equals(tmp, "2")) {
							m.setNowsession("第二节");
						} else if (StringUtils.equals(tmp, "3")) {
							m.setNowsession("第三节");
						} else if (StringUtils.equals(tmp, "4")) {
							m.setNowsession("第四节");
						}
					}

				}

				/** 已开始时间(秒转换成分钟) **/
				if (StringUtils.isNotBlank(m.getLasttime())) {
					m.setLasttime(ComUtil.getMinSecondByMsec(Integer.parseInt(m.getLasttime()) * 1000));
				}
			}
		}
	}
	
	 

	private void initRTypeName(String timeType, String tag, String rtype) {
		if("hpd".equals(rtype)){
			rtype ="pd";
		}

		typeName = SportConstant.getTimeTypeName(timeType) + "：" + SportConstant.getRTypeName(tag + "_" + rtype);
		

//		typeName = SportConstant.getTimeTypeName(timeType) + SportConstant.getTypeName(tag) + "：" + SportConstant.getRTypeName(tag + "_" + rtype);
		/** 取control 条数 **/
		sc = new SportCount();
		sc.setTimeType(timeType);
		if (StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_TODAY) || StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_TOM)) {
			control = getMatchControl(timeType, SportConstant.ft_rtype_r);
			sc.setFtRtype(SportConstant.ft_rtype_r);
			sc.setFtCount(null == control ? 0 : control.getCurcount());
			control = getMatchControl(timeType, SportConstant.bk_rtype_rmain_cj);
			sc.setBkRtype(SportConstant.bk_rtype_rmain_cj);
			sc.setBkCount(null == control ? 0 : control.getCurcount());
		} else if (StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_ROLL)) {
			control = getMatchControl(timeType, SportConstant.roll_rtype_re);
			sc.setFtRtype(SportConstant.roll_rtype_re);
			sc.setFtCount(null == control ? 0 : control.getCurcount());
			control = getMatchControl(timeType, SportConstant.roll_rtype_remain);
			sc.setBkRtype(SportConstant.roll_rtype_remain);
			sc.setBkCount(null == control ? 0 : control.getCurcount());
		}
	}
	
	
	
	private TMatchControl getMatchControl(String timeType, String rtype) {
		return this.webSportService.getMatchControl(timeType, rtype);
	}
	
 
	/***
	 * 方法描述: 交易历史</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 * @throws Exception  
	 * ModelAndView
	 */
	@RequestMapping("accountHis")
	public ModelAndView goToAccountHis(HttpServletRequest request, HttpServletResponse response, WebRecords record) throws Exception{
		UserContext uc = this.getUserContext(request);
		String userName = uc.getUserName();
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		
		List<String> timeList = new ArrayList<String>();
		ModelAndView mv = new ModelAndView();
		String startTime = record.getStartTime();
		String endTime = record.getEndTime();
		String sportTypeCode = record.getBetSportType();
		sportTypeCode = "all".equals(sportTypeCode)?"":sportTypeCode;
		Date time = DateUtil.getGMT_4_Date();
		int days = 8;
		if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
			time = DateUtil.parse(endTime, "yyyy-MM-dd");
			Date start = DateUtil.parse(startTime, "yyyy-MM-dd");
			Date end = DateUtil.parse(endTime, "yyyy-MM-dd");
			days = DateUtil.beforeDays(start, end) + 1;
		}
		Date _time = DateUtil.getGMT_4_Date();
		for(int i=7; i>=0; i-- ){
			String begin = DateUtil.dayBegin(_time, -i);
			timeList.add(begin.substring(0, 10));
		}
		
		for(int i=0; i<days; i++ ){
			Map<String, Object> map = new HashMap<String, Object>();
			String begin = DateUtil.dayBegin(time, -i);
			String end = DateUtil.dayEnd(time, -i);
			WebRecords bean = new WebRecords();
			bean.setBetSportType(sportTypeCode);
			bean.setStartTime(begin);
			bean.setEndTime(end);
			bean.setUserName(userName);
			bean.setBetStatus("2");//已结算
			map = this.webRecordService.selectCountBetOrder(bean);
			map.put("time", begin);
			map.put("week", WeekUtil.getWeekOfDate(time, -i));
			recordList.add(map);
		}
		
		WebRecords bean = new WebRecords();
		bean.setBetSportType(sportTypeCode);
		bean.setStartTime(DateUtil.dayBegin(time, -days +1).substring(0, 10));
		bean.setEndTime(DateUtil.dayEnd(time, 0).substring(0, 10));
		bean.setUserName(userName);
		bean.setBetStatus("2");//已结算
		Map<String, Object> totals = this.webRecordService.selectCountBetOrder(bean);
		
		mv.addObject("timeList", timeList);
		mv.addObject("accounts", recordList);
		mv.addObject("total", totals);
		mv.addObject("record", bean);
		mv.addObject("types", SportConstant.typeSportMap);
		mv.setViewName("sport/account/accountHis");
		return mv;
		
	}
	
	/**
	 * 
	 * 方法描述: 交易详情（已结算）</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 * @throws ParseException 
	 */
	@RequestMapping("accountDetail")
	public ModelAndView goToAccountDetail(HttpServletRequest request, HttpServletResponse response) throws ParseException{
		
		UserContext uc = this.getUserContext(request);
		String userName = uc.getUserName();
		
		String searchTime = request.getParameter("searchTime");
		String betSportType = request.getParameter("betSportType");
		betSportType = "all".equals(betSportType)?"":betSportType;
		Date start = DateUtil.parse(searchTime, "yyyy-MM-dd");
		
		
		WebRecords records = new WebRecords();//已经被结算的注单
		records.setBetSportType(betSportType);
		records.setUserName(userName);
		records.setBetStatus("0,2,3");//无效，已结算，异常
		records.setStartTime(DateUtil.dayBegin(start, 0));
		records.setEndTime(DateUtil.dayEnd(start, 0));
		List<TWebMatchBet> bets = this.webRecordService.findListForSport(records);
		
		WebRecords bean = new WebRecords();
		bean.setUserName(userName);
		bean.setBetStatus("0,2,3");//无效，已结算，异常
		Map<String, Object> totals = this.webRecordService.selectCountBetOrder(bean);
		
		return new ModelAndView("sport/account/accountDetail")
					.addObject("bets",bets)
					.addObject("total",totals);
		
	}
	
	/**
	 * 交易订单（未结算）
	 * 方法描述: TODO</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("accountOrder")
	public ModelAndView goToAccountOrder(HttpServletRequest request, HttpServletResponse response){
		UserContext uc = this.getUserContext(request);
		String userName = uc.getUserName();
		
		WebRecords records = new WebRecords();//没有被结算的注单
		records.setUserName(userName);
		records.setBetStatus("-2,-1,1");//正在确认，待确认，可结算
//		records.setStartTime(DateUtil.todayBegin());
		records.setStartTime(DateUtil.dayBegin(new Date(), -30));
		records.setEndTime(DateUtil.todayEnd());
		List<TWebMatchBet> bets = this.webRecordService.findListForSport(records);
		
		Map<String, Object> totals = this.webRecordService.selectCountBetOrder(records);
		
		return new ModelAndView("sport/account/orderList")
					.addObject("bets",bets)
					.addObject("total",totals);
		
	}
	
	
	/**
	 * 查询最新十条订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("searchNewestTenOrders")
	public ModelAndView searchNewestTenOrders(HttpServletRequest request,HttpServletResponse response){
//		
//		betSportMap = ComUtil.sortMapByKey(SystemConstant.betStatusCodeValueShowNameMap);
		UserContext uc = this.getUserContext(request);
		WebRecords record = new WebRecords();
		record.setUserName(uc.getUserName());
		record.setStartTime(DateUtil.todayBegin());
		record.setEndTime(DateUtil.todayEnd());
		
		record.setBetStatus("-2,-1,0,1");//正在确认，待确认，可结算
		
		List<TWebMatchBet> matchBetList = this.webSportService.getMatchBetResult(record);
		int total = 0;
		for(TWebMatchBet bet:matchBetList){
			total+= bet.getDetails().size();
		}

		return new ModelAndView("sport/orderResult")
			.addObject("total", total)
			.addObject("src", src)
			.addObject("dataList", matchBetList);
	}
	
	
	/**
	 * 
	 * 方法描述: 除足球 篮球外页面跳转</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response
	 * @param matchType
	 * @param timeType
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goMatchCenter2")
	public ModelAndView goMatchCenter2(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam("matchType") String matchType, @RequestParam("timeType") String timeType ){
		
		String url = "sport/match/to_" + matchType + "_match";
		String title =  SportConstant.timeTypeNameMap.get(timeType) + SportConstant.typeSportMap.get(matchType);
		if("roll".equals(timeType)){
			title = SportConstant.typeSportMap.get(matchType) +"：" + SportConstant.timeTypeNameMap.get(timeType);
		}
		
		if("bs".equals(matchType) && "roll".equals(timeType) ){
			url = "sport/match/roll_" + matchType + "_match";
		}
		if("op".equals(matchType) && "roll".equals(timeType) ){
			url = "sport/match/roll_" + matchType + "_match";
		}
		
		return new ModelAndView(url)
					.addObject("title", title)
					.addObject("matchType", matchType)
					.addObject("timeType", timeType);
	}
	
	/**
	 * 足球篮球赛果
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 * @throws ParseException 
	 */
	@RequestMapping("/getftRes")
	public ModelAndView getftRes(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		ModelAndView view = new ModelAndView();
		String searchDate = StringUtils.defaultString(request.getParameter("searchDate"), DateUtil.formatToday("yyyy-MM-dd"));
		Date  date = DateUtil.parse(searchDate, "yyyy-MM-dd");
		String dateFormat=DateUtil.format(date,"yyyy-MM-dd");
		String beforeDate = DateUtil.format(DateUtil.addDay(date, -1), "yyyy-MM-dd") ;
		String afterDate = DateUtil.format(DateUtil.addDay(date, 1), "yyyy-MM-dd");
		
		String today = DateUtil.formatToday("yyyy-MM-dd");
		if(today.equals(searchDate)){
			afterDate = "";
		}
        String type=request.getParameter("selgtype");
        if(StringUtils.isEmpty(type)){
    			 List<TResultMatchFt> result = this.webSportResultsService.getResultFtSport(searchDate);
  	    		List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
    				for(int i=0;i<result.size();i++){
    					TResultMatchFt match = result.get(i);
    					match.setMatchType(type);
    					Map<String,Object> valMap = new HashMap<String,Object>();
    					valMap.put("id", match.getId());
    					valMap.put("league", match.getLeague());
    					valList.add(valMap);
    				}
    				String jst = JSON.toJSONString(valList);
    				view.addObject("list",result);
    				view.addObject("datas", jst);
    				view.setViewName("sport/result/ft_Result_Match");
         }else if(type.equals("FT")){
			  List<TResultMatchFt> result = this.webSportResultsService.getResultFtSport(searchDate);
			  List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
				for(int i=0;i<result.size();i++){
					TResultMatchFt match = result.get(i);
					Map<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("id", match.getId());
					valMap.put("league", match.getLeague());
					valList.add(valMap);
				}
				String jst = JSON.toJSONString(valList);
				view.addObject("list",result);
				view.addObject("datas", jst);
				
				view.setViewName("sport/result/ft_Result_Match");
		 }else if(type.equals("BK")){
		        	List<TResultMatchBk> result = this.webSportResultsService.getResultBkSport(searchDate);
		    		List<Map<String,Object>> valList = new ArrayList<Map<String,Object>>();
		    		for(int i=0;i<result.size();i++){
		    			TResultMatchBk match = result.get(i);
		    			Map<String,Object> valMap = new HashMap<String,Object>();
		    			valMap.put("id", match.getId());
		    			valMap.put("league", match.getLeague());
		    			valList.add(valMap);
		    		}
		    		String jst = JSON.toJSONString(valList);
		    		view.addObject("list",result);
					view.addObject("datas", jst);
				
		    		view.setViewName("sport/result/bk_Result_Match");
		        }
			    	view.addObject("beforeDate", beforeDate);
					view.addObject("searchDate", dateFormat);
					view.addObject("afterDate", afterDate);
					view.addObject("selgtype",type);
					return view;
	}
	
	/**
	 * 消息列表
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/goMessageList")
	public ModelAndView goMessageList(HttpServletRequest request,HttpServletResponse response){
		String fField = request.getParameter("fField");
		String select_date = request.getParameter("select_date");
		if(StringUtils.isEmpty(select_date)){
			select_date = "all";
		}
		
		List<Map<String,Object>> dataList = this.webSportResultsService.getMessageList(select_date,fField);
		
		return new ModelAndView("sport/sport_message")
			.addObject("select_date", select_date)
			.addObject("dataList", dataList);
	}
	
	/**
	 * 生成8位随机数
	 * @return
	 */
	private Integer generateRandomNum(){
		StringBuffer str=new StringBuffer();
		Random random=new Random();
		for(int i=0;i<8;i++){
			str.append(random.nextInt(10));
		}
		return Integer.parseInt(str.toString());
	}

	public int getSearchLeague() {
		return searchLeague;
	}

	public void setSearchLeague(int searchLeague) {
		this.searchLeague = searchLeague;
	}

	public SportCount getSc() {
		return sc;
	}

	public void setSc(SportCount sc) {
		this.sc = sc;
	}

	public SportRollCount getSrc() {
		return src;
	}

	public void setSrc(SportRollCount src) {
		this.src = src;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public TMatchControl getControl() {
		return control;
	}

	public void setControl(TMatchControl control) {
		this.control = control;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public List<String> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<String> leagues) {
		this.leagues = leagues;
	}

	public List<TFtMatchR> getListR() {
		return listR;
	}

	public void setListR(List<TFtMatchR> listR) {
		this.listR = listR;
	}

	public List<TFtMatchPD> getListPD() {
		return listPD;
	}

	public void setListPD(List<TFtMatchPD> listPD) {
		this.listPD = listPD;
	}

	public List<TFtMatchT> getListT() {
		return listT;
	}

	public void setListT(List<TFtMatchT> listT) {
		this.listT = listT;
	}

	public List<TFtMatchF> getListF() {
		return listF;
	}

	public void setListF(List<TFtMatchF> listF) {
		this.listF = listF;
	}

	public List<TFtMatchP3> getListP3() {
		return listP3;
	}

	public void setListP3(List<TFtMatchP3> listP3) {
		this.listP3 = listP3;
	}

	public List<TBkMatchRmain> getListBKRMAIN() {
		return listBKRMAIN;
	}

	public void setListBKRMAIN(List<TBkMatchRmain> listBKRMAIN) {
		this.listBKRMAIN = listBKRMAIN;
	}

	public List<TBkMatchP3> getListBKP3() {
		return listBKP3;
	}

	public void setListBKP3(List<TBkMatchP3> listBKP3) {
		this.listBKP3 = listBKP3;
	}

	public List<TRMatchRE> getListRE() {
		return listRE;
	}

	public void setListRE(List<TRMatchRE> listRE) {
		this.listRE = listRE;
	}

	public List<TRMatchRemain> getListREMAIN() {
		return listREMAIN;
	}

	public void setListREMAIN(List<TRMatchRemain> listREMAIN) {
		this.listREMAIN = listREMAIN;
	}
	
	
	
	
}
