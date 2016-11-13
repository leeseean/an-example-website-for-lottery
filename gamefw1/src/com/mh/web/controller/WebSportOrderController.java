/**   
* 文件名称: WebSportOrderController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-16 上午10:47:02<br/>
*/  
package com.mh.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.ResourceConstant;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.entity.SportBet;
import com.mh.entity.SportBetBean;
import com.mh.entity.SportCount;
import com.mh.entity.SportRollCount;
import com.mh.entity.TBkMatchP3;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchBkLimit;
import com.mh.entity.TMatchControl;
import com.mh.entity.TMatchFtLimit;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.WebUser;
import com.mh.service.WebSportService;
import com.mh.service.WebUserService;
import com.mh.web.job.CodeDataHelper;
import com.mh.web.login.UserContext;
import com.mh.web.servlet.MySessionContext;
import com.mh.web.util.SportBetUtil;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-16 上午10:47:02<br/>
 */

@Controller
@RequestMapping("/sport")
public class WebSportOrderController extends BaseController{
	
	@Autowired
	private WebSportService webSportService;
	
	@Autowired
	private WebUserService webUserService;
	
	/** 与视图共享成员变量 **/
	private String backToUrl;
	private String msg;

	private SportBet bet;

	/****/
	private String gid;// 赛事编号
	private String timeType;// 大类型
	private String matchType;// 体育类型
	private String rtype;//
	private String btype;//
	private String dtype;//
	private String selection;// 下注主队还是客队(H C N)
	private String period;// 全场还是上半场
	private String gidm;// 现一赛事标识

	private List<TWebMatchBet> list;

	private Map<String, String> betSportMap;
	
	private SportCount sc;
	private SportRollCount src;
 

	private TMatchControl control;
	
	
	private void initParams(HttpServletRequest request) {
		gid = request.getParameter("gid");
		timeType = request.getParameter("timeType");
		matchType = request.getParameter("matchType");
		rtype = request.getParameter("rtype");
		btype = request.getParameter("btype");
		dtype = request.getParameter("dtype");
		selection = request.getParameter("selection");
		period = request.getParameter("period");
		gidm = request.getParameter("gidm");

		bet = new SportBet();
		bet.setTimeType(timeType);
		bet.setMatchType(matchType);
		bet.setRtype(rtype);
		bet.setBtype(btype);
		bet.setDtype(dtype);
		bet.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));// ft_rtype
		bet.setGid(gid);
		bet.setPeriod(period);
		bet.setSelection(selection);
		bet.setOddsDes(CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, btype));// 码表odds-btype
		bet.setGidm(gidm);
		
		src = new SportRollCount();
		control = getMatchControl(SportConstant.TIME_TYPE_ROLL, SportConstant.roll_rtype_re);
		src.setFtCount(control.getCurcount());
		control = getMatchControl(SportConstant.TIME_TYPE_ROLL, SportConstant.roll_rtype_remain);
		src.setBkCount(control.getCurcount());

	}

 
	
	
	/**
	 * 下注
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/xiazhu")
	public ModelAndView xiazhu(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		/**判断是否维护**/
		String msg = WebSiteManagerConstants.ctxMap.get(CommonConstant.PLAT_PARAM_HUANGGUAN+"_"+WebConstants.WEB_WEIHU_STATUS_OFF);
		if(StringUtils.isNotBlank(msg)){
			view.addObject("msg", "下单失败，系统正在维护...");
			view.setViewName("sport/betResult");
			return view;
		}
		try{
			initParams(request);
			doBusi(request);
			doLimitBet();
			if (StringUtils.equals(rtype, "p3")) {// 综合过关
				view.setViewName("sport/xiazhup3");
				 
			} else {
				view.setViewName("sport/xiazhu");
			}
			view.addObject("bet", bet);
			
 
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("下注失败!", e);
		}

		return view;
	}
	

	private void doLimitBet() {
		bet.setLimitBetMin(String.valueOf(WebSiteManagerConstants.SYSPARAMMAP.get("web_match_bet_limit_min"))	);
		if (StringUtils.equalsIgnoreCase(matchType, SportConstant.match_type_ft)) {
			TMatchFtLimit ftLimit = ResourceConstant.matchFtLimit;
			SportBetUtil.limitFt(bet,ftLimit);
		} else if (StringUtils.equalsIgnoreCase(matchType, SportConstant.match_type_bk)) {
			TMatchBkLimit bkLimit = ResourceConstant.matchBkLimit;
			SportBetUtil.limitBk(bet,bkLimit);
		}
	}

	private void doBusi(HttpServletRequest request) throws Exception {
		String rtypeTemp = rtype;
		if(rtype.equals(SportConstant.bk_rtype_rmain)){
			rtypeTemp = SportConstant.bk_rtype_rmain_cj;
		}else if(rtype.equals(SportConstant.match_rtype_p3) && matchType.equals("BK")){
			rtypeTemp = SportConstant.bk_rtype_p3_cj;
		}else if(rtype.equals("pd") && StringUtils.equalsIgnoreCase(bet.getPeriod(), "h")){
			rtypeTemp = "hpd";
		}
		
		TMatchControl control = getMatchControl(timeType, rtypeTemp);
		SportBet bet = new SportBet();
		bet.setGid(gid);
		/** ##滚球## **/
		if (StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_ROLL)) {
			if (StringUtils.equalsIgnoreCase(rtype, SportConstant.roll_rtype_re)) {// 足球
				//doRtype_RE();
				doRtype_RE(control,bet);
			} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.roll_rtype_remain)) {// 篮球
				//doRtype_REMain();
				doRtype_REMain(control,bet);
			}

		} else {

			/** ##今日赛事/早盘:足球## **/
			if (StringUtils.equalsIgnoreCase(matchType, SportConstant.match_type_ft)) {
				/** 单式 **/
				if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_r)) {
					//doRtype_R();
					doRtype_R(control,bet);
				}
				/** 波胆 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_pd)) {
					//doRtype_PD();
					doRtype_PD(control,bet);
				}
				/** 总入球 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_t)) {
					//doRtype_T();
					doRtype_T(control,bet);
				}
				/** 半场/全场 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_f)) {
					//doRtype_F();
					doRtype_F(control,bet);
				}
				/** 综合过关 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_p3)) {
					//doRtype_P3();
					doRtype_P3(control,bet);
					doInitP3Session(request);
				}
				/** ##end## **/
			}

			/** ##今日赛事/早盘:篮球## **/
			else if (StringUtils.equalsIgnoreCase(matchType, SportConstant.match_type_bk)) {
				/** 单式 **/
				if (StringUtils.equalsIgnoreCase(rtype, SportConstant.bk_rtype_rmain)) {
					//doRtype_BK_RMain();
					doRtype_BK_RMain(control,bet);
				}
				/** 综合过关 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.bk_rtype_p3)) {
					//doRtype_BK_P3();
					doRtype_BK_P3(control,bet);
					doInitP3Session(request);
				}
			}
			/** ##end## **/

		}
	}

	/**
	 * 多种盘口，只能下注一种
	 */
	private void doInitP3Session(HttpServletRequest request) {

		SportBetBean sportBetBean = (SportBetBean) request.getSession().getAttribute("sportBetBean");
		String tagP3 = timeType + "_" + matchType + "_p3";
		if (sportBetBean != null) {
			sportBetBean.setMatchType(matchType);
			if (StringUtils.equalsIgnoreCase(tagP3, sportBetBean.getTagP3())) {
				List<SportBet> list = sportBetBean.getSportBetList();
				if (CollectionUtils.isEmpty(list)) {
					list = new ArrayList<SportBet>();
					sportBetBean.setSportBetList(list);
				} else {
					/** 3串10 **/
					if (list.size() >= 10) {
						return;
					}
				}

				boolean hasAdd = false;
				if (CollectionUtils.isNotEmpty(list)) {
					SportBet sportBet = null;
					for (int i = 0; i < list.size(); i++) {
						sportBet = list.get(i);
						if (StringUtils.equals(sportBet.getGidm(), gidm)) {
							list.set(i, bet);
							hasAdd = true;
						}
					}
				}
				if (!hasAdd)
					list.add(bet);

			} else {
				sportBetBean.setTagP3(tagP3);
				List<SportBet> list = sportBetBean.getSportBetList();
				if (CollectionUtils.isEmpty(list)) {
					list = new ArrayList<SportBet>();
					sportBetBean.setSportBetList(list);
				} else {
					list.clear();
				}
				list.add(bet);
			}
		} else {
			sportBetBean = new SportBetBean();
			sportBetBean.setTagP3(tagP3);
			sportBetBean.setMatchType(matchType);
			ArrayList<SportBet> list = new ArrayList<SportBet>();
			list.add(bet);
			sportBetBean.setSportBetList(list);
			request.getSession().setAttribute("sportBetBean", sportBetBean);
		}
		request.setAttribute("sportBetBean", sportBetBean);

	}

	
	@RequestMapping("/delteam")
	public ModelAndView delteam(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		String gid = request.getParameter("gid");
		if (StringUtils.isNotBlank(gid)) {
			SportBetBean sportBetBean = (SportBetBean) request.getSession().getAttribute("sportBetBean");
			if (sportBetBean != null) {
				List<SportBet> list = sportBetBean.getSportBetList();
				if (CollectionUtils.isNotEmpty(list)) {
					for (int i = 0; i < list.size(); i++) {
						if (StringUtils.equals(list.get(i).getGid(), gid)) {
							list.remove(i);
						}
					}
				}
			}
		}

		SportBetBean sportBetBean = (SportBetBean) request.getSession().getAttribute("sportBetBean");
		if (sportBetBean != null) {
			if (CollectionUtils.isEmpty(sportBetBean.getSportBetList())) {
				view.setViewName("sport/none");
			}else{
				view.setViewName("sport/xiazhup3");
			}
		} else {
			view.setViewName("sport/none");
		}
		view.addObject("src", src);
		
		
		return view;
	}

	
	
	@RequestMapping("/delteams")
	public ModelAndView delteams(HttpServletRequest request,HttpServletResponse response){
	 
		request.getSession().removeAttribute("sportBetBean");
		return new ModelAndView("sport/none").addObject("src", src);
	}

	
	/**
	 * 取消订单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/cancelBetOrder")
	public ModelAndView cancelBetOrder(HttpServletRequest request,HttpServletResponse response){
		
		request.getSession().removeAttribute("sportBetBean");
		WebUser webUser = null;
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			UserContext uc = this.getUserContext(request);
			if(uc!=null){
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
		return new ModelAndView("sport/none").addObject("src", src).addObject("webUser", webUser);
	}
	private TMatchControl getMatchControl(String timeType, String rtype) {
		return this.webSportService.getMatchControl(timeType, rtype);
	}

	/**
	 * 滚球：单式情况
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_RE() throws Exception {
		TRMatchRE record = this.webSportService.getRollMatchRE(gid,timeType);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_RE(bet, record);
	}*/
	
	private void doRtype_RE(TMatchControl control,SportBet bets) throws Exception {
		TRMatchRE record = this.webSportService.getRollMatchRE(control,bets);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_RE(bet, record);
	}

	/**
	 * 滚球:单式情况
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_REMain() throws Exception {
		
		TRMatchRemain record = this.webSportService.getRollMatchRemain(gid,timeType);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_REMAIN(bet, record);
	}*/
	
	private void doRtype_REMain(TMatchControl control,SportBet bets) throws Exception {
		
		//TRMatchRemain record = this.webSportService.getRollMatchRemain(gid,timeType);
		TRMatchRemain record = this.webSportService.getRollMatchRemain(control,bets);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_REMAIN(bet, record);
	}

	/**
	 * 单式情况
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_R() throws Exception {
		
		TFtMatchR record = this.webSportService.getFtMatchR(gid,timeType);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_R(bet, record);
	}*/
	
	private void doRtype_R(TMatchControl control,SportBet bets) throws Exception {
		
		TFtMatchR record = this.webSportService.getFtMatchR(control,bets);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_R(bet, record);
	}

	/**
	 * 波胆情况
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_PD() throws Exception {
		int tag = StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? SportConstant.PD_TAG_2 : SportConstant.PD_TAG_1;
		TFtMatchPD record = this.webSportService.getFtMatchPD(gid,timeType,tag+"");
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_PD(bet, record);
	}*/
	
	private void doRtype_PD(TMatchControl control,SportBet bets) throws Exception {
		int tag = StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? SportConstant.PD_TAG_2 : SportConstant.PD_TAG_1;
		//TFtMatchPD record = this.webSportService.getFtMatchPD(gid,timeType,tag+"");
		TFtMatchPD record = this.webSportService.getFtMatchPD(control,bets,tag+"");
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_PD(bet, record);
	}

	/**
	 * 总入球情况
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_T() throws Exception {
		TFtMatchT record = this.webSportService.getFtMatchT(gid,timeType);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_T(bet, record);
	}*/
	
	private void doRtype_T(TMatchControl control,SportBet bets) throws Exception {
		TFtMatchT record = this.webSportService.getFtMatchT(control,bets);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_T(bet, record);
	}

	/**
	 * 半场/全场情况
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_F() throws Exception {
		TFtMatchF record = this.webSportService.getFtMatchF(gid,timeType);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_F(bet, record);
	}*/
	
	private void doRtype_F(TMatchControl control,SportBet bets) throws Exception {
		TFtMatchF record = this.webSportService.getFtMatchF(control,bets);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_F(bet, record);
	}

	/**
	 * 综合过关情况
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_P3() throws Exception {
		TFtMatchP3 record = this.webSportService.getFtMatchP3(gid,timeType);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_P3(bet, record);
	}*/
	
	private void doRtype_P3(TMatchControl control,SportBet bets) throws Exception {
		TFtMatchP3 record = this.webSportService.getFtMatchP3(control,bets);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_P3(bet, record);
	}

	/**
	 * 篮球:单式情况(多种盘口)
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_BK_RMain() throws Exception {
		TBkMatchRmain record = this.webSportService.getBkMatchRmain(gid,timeType);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_BK_Rmain(bet, record);
	}*/
	
	private void doRtype_BK_RMain(TMatchControl control,SportBet bets) throws Exception {
		TBkMatchRmain record = this.webSportService.getBkMatchRmain(control,bets);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_BK_Rmain(bet, record);
	}

	/**
	 * 篮球:综合过关情况
	 * 
	 * @throws Exception
	 **/
	/*private void doRtype_BK_P3() throws Exception {
		TBkMatchP3 record = this.webSportService.getBkMatchP3(gid,timeType);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_BK_P3(bet, record);
	}*/
	
	private void doRtype_BK_P3(TMatchControl control,SportBet bets) throws Exception {
		TBkMatchP3 record = this.webSportService.getBkMatchP3(control,bets);
		bet.setLeague(record.getLeague());
		SportBetUtil.initSportBet_BK_P3(bet, record);
	}
	
	
	
	

	 

	public String getBackToUrl() {
		return backToUrl;
	}

	public void setBackToUrl(String backToUrl) {
		this.backToUrl = backToUrl;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SportBet getBet() {
		return bet;
	}

	public void setBet(SportBet bet) {
		this.bet = bet;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getGidm() {
		return gidm;
	}

	public void setGidm(String gidm) {
		this.gidm = gidm;
	}

	public List<TWebMatchBet> getList() {
		return list;
	}

	public void setList(List<TWebMatchBet> list) {
		this.list = list;
	}

	public Map<String, String> getBetSportMap() {
		return betSportMap;
	}

	public void setBetSportMap(Map<String, String> betSportMap) {
		this.betSportMap = betSportMap;
	}




	public WebSportService getWebSportService() {
		return webSportService;
	}




	public void setWebSportService(WebSportService webSportService) {
		this.webSportService = webSportService;
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




	public TMatchControl getControl() {
		return control;
	}




	public void setControl(TMatchControl control) {
		this.control = control;
	}
 
	
	
	
}
