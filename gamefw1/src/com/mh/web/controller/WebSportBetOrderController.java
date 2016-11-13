/**   
* 文件名称: WebSportBetOrderController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-16 下午7:34:08<br/>
*/  
package com.mh.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.ResourceConstant;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.conf.SystemConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.CommonUtils;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.SportBet;
import com.mh.entity.SportBetBean;
import com.mh.entity.SysParameter;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchBkLimit;
import com.mh.entity.TMatchControl;
import com.mh.entity.TMatchFtLimit;
import com.mh.entity.TMatchRelate;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.TWebMatchDetail;
import com.mh.entity.WebUser;
import com.mh.exceptions.SportBetMatchNoViableException;
import com.mh.ifc.util.ComUtil;
import com.mh.service.SysParameterService;
import com.mh.service.WebSportService;
import com.mh.service.WebUserService;
import com.mh.web.job.CodeDataHelper;
import com.mh.web.login.UserContext;
import com.mh.web.util.SportBetUtil;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-16 下午7:34:08<br/>
 */

@Controller
@RequestMapping("/sport")
public class WebSportBetOrderController extends BaseController{
	
	@Autowired
	private WebSportService webSportService;
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private SysParameterService sysParameterService;
 
	private String msg;

	private SportBet bet;
	
	private SportBetBean sportBetBean;

	/** 基本信息 **/
	private String gid;// 赛事编号
	private String timeType;// 大类型
	private String matchType;// 体育类型
	private String rtype;//
	private String btype;//
	private String dtype;//
	private String selection;// 下注主队还是客队(H C N)
	private String period;// 全场还是上半场

	/** 其他 **/
	private String gold;// 下注金额
	private String oddsName;
	private String betWagersId;

	private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

 

	private void initParams(HttpServletRequest request) {
		gid = request.getParameter("gid");
		timeType = request.getParameter("timeType");
		matchType = request.getParameter("matchType");
		rtype = request.getParameter("rtype");
		btype = request.getParameter("btype");
		dtype = request.getParameter("dtype");
		selection = request.getParameter("selection");
		period = request.getParameter("period");
		gold = request.getParameter("gold");
		
		
		bet = new SportBet();
		bet.setTimeType(timeType);
		bet.setMatchType(matchType);
		bet.setRtype(rtype);
		bet.setBtype(btype);
		bet.setDtype(dtype);
		bet.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));
		bet.setGid(gid);
		bet.setPeriod(period);
		
		bet.setSelection(selection);
		bet.setOddsDes(CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, btype));
	}
	
	@RequestMapping("/orderBet")
	public synchronized ModelAndView orderBet(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		
		/**判断是否维护**/
		String msg = WebSiteManagerConstants.ctxMap.get(CommonConstant.PLAT_PARAM_HUANGGUAN+"_"+WebConstants.WEB_WEIHU_STATUS_OFF);
		if(StringUtils.isNotBlank(msg)){
			view.addObject("msg", "下单失败，系统正在维护...");
			view.setViewName("sport/betResult");
			return view;
		}
		try {
			if(StringUtils.isEmpty(request.getParameter("gold"))){
				view.addObject("msg", "请输入交易金额！");
				view.setViewName("sport/betResult");
				return view;
			}
			initParams(request);

			/** 余额判断 **/
			if (!remainMoneyCheck(request)) {
//				msg = "<p><span style='color:red'>余额不足，投注失败。</span> </p>";
				msg = "余额不足，投注失败。";
			} else {
				doLimitBet();// 读限额
				// checkLimitBet();// 限额判断
				doBusi(request);
				
				if (StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_ROLL)) {// 滚球提示不一样
//					msg = "<p><span style='color:red'>注单确认中…</span> </p><p style='margin-top:5px'>注单号：<font color=red>" + betWagersId + "</font></p>";
					msg = "注单确认中…";
				} else {
//					msg = "<p>成功提交注单，注单号：</p><p style='margin-top:5px'><font color=red>" + betWagersId + "</font></p>";
					msg = "成功提交注单。";
				}
				sportBetBean = new SportBetBean();
				if (!StringUtils.equalsIgnoreCase(rtype, SportConstant.bk_rtype_p3) && 
						!StringUtils.equalsIgnoreCase(rtype, SportConstant.bk_rtype_p3_cj) ){
					sportBetBean.setMatchType(matchType);
					List<SportBet> betList = new ArrayList<SportBet>();
					betList.add(bet);
					sportBetBean.setSportBetList(betList);
				}
			 
				sportBetBean.setBetWagersId(betWagersId);
				sportBetBean.setGold(gold);
				view.addObject("sportBetBean", sportBetBean);
				sportBetBean = null;
			}
		} catch (Exception e){
			if(e instanceof SportBetMatchNoViableException){
				logger.error("所下注的比赛已过期",e);
				msg = "您下注盘口已变更，请刷新页面，重新下注！";
			}else{
				logger.error("注单提交失败",e);
				e.printStackTrace();
//				msg = "<p>注单提交失败!</p><p style='margin-top:5px'><font color=red>请重新投注或联系我们处理！</font></p>";
				msg = "注单提交失败,请重新投注或联系我们处理！";
			}
		}
		
		view.addObject("msg", msg);
		view.setViewName("sport/betResult");

		return view;
	}

 
 

	private void doLimitBet() {
		 
		if (StringUtils.equalsIgnoreCase(matchType, SportConstant.match_type_ft)) {
			TMatchFtLimit ftLimit = ResourceConstant.matchFtLimit;
			SportBetUtil.limitFt(bet,ftLimit);
		} else if (StringUtils.equalsIgnoreCase(matchType, SportConstant.match_type_bk)) {
			TMatchBkLimit bkLimit = ResourceConstant.matchBkLimit;
			SportBetUtil.limitBk(bet,bkLimit);
		}
	}

	
	
	private synchronized boolean remainMoneyCheck(HttpServletRequest request) {
		UserContext uc = this.getUserContext(request);
		double userMoney = this.webUserService.getWebUserMoney(uc.getUserName());
		if(userMoney<=0){
			return false;
		}
		
		if (userMoney >= Double.valueOf(gold)) {
			return true;
		}
		return false;
	}

	public void doBusi(HttpServletRequest request) throws Exception {

		/** ##滚球## **/
		if (StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_ROLL)) {
			if (StringUtils.equalsIgnoreCase(rtype, SportConstant.roll_rtype_re)) {// 足球
				doRtype_RE(request);
			} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.roll_rtype_remain)) {// 篮球
				doRtype_REMain(request);
			}

		} else {

			/** ##今日赛事/早盘:足球## **/
			if (StringUtils.equalsIgnoreCase(matchType, SportConstant.match_type_ft)) {
				/** 单式 **/
				if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_r)) {
					doRtype_R(request);
				}
				/** 波胆 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_pd)) {
					doRtype_PD(request);
				}
				/** 总入球 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_t)) {
					doRtype_T(request);
				}
				/** 半场/全场 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_f)) {
					doRtype_F(request);
				}
				/** 综合过关 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_p3)) {
					doRtype_P3(request);
				}
			}

			/** ##今日赛事/早盘:篮球## **/
			if (StringUtils.equalsIgnoreCase(matchType, SportConstant.match_type_bk)) {
				/** 单式 **/
				if (StringUtils.equalsIgnoreCase(rtype, SportConstant.bk_rtype_rmain)) {
					doRtype_BK_Rmain(request);
				}
				/** 综合过关 **/
				else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.bk_rtype_p3)) {
					doRtype_BK_P3(request);
				}
			}
		}
	}

	private void initOrder(TWebMatchBet order, WebUser user,HttpServletRequest request) throws Exception {
		order.setWebFlat(user.getUserFlag());
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));
		order.setBetUserName(user.getUserName());
		order.setBetWagersId(ComUtil.getSportsOrder());
		order.setBetIn(Double.valueOf(gold));
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_UNSETTLE, 17));
		order.setBetSportType(matchType);
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));
		order.setBetUserAgent(user.getUserAgent());
		order.setBackWaterStatus(0);
		order.setMatchRtype(rtype);
		order.setConfirmTime(order.getCreateTime());
		order.setRemark("体育下注");
		order.setTimeType(timeType);
		
		if(!StringUtils.equalsIgnoreCase(rtype, SportConstant.bk_rtype_p3)){
			//可赢金额
			double odds = Double.parseDouble(bet.getOdds());
			int minus = NumberUtils.toInt(bet.getOddsMinus(), 0);
			double canSum =order.getBetIn()*(odds-minus);

			if(canSum>=0){
				order.setBetCanWin(canSum);
			}else{
				order.setBetCanWin(0.0);
			}
		}
		
		/** 设定滚球确认注单时间 **/
		if (StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_ROLL)) {
	 
			SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName("web_match_roll_confirm_time");
			long millis = 90*1000;
			if(sysParameter!=null){
				millis = NumberUtils.toInt(sysParameter.getParamValue(), 90) * 1000;
			}
			order.setConfirmTime(ComUtil.addDateTime(order.getCreateTime(), millis));
		}

	}

	private String initOrderMatchTeam(String gid, String league, String teamH, String teamC, String matchDate) throws Exception {
		/** 查询球队：通过gid **/
		
		if (teamH.lastIndexOf("[中]") != -1) {
			teamH = ComUtil.trim(teamH.substring(0, teamH.length() - 3));
		}
		TMatchRelate tMatchRelate = this.webSportService.searchMatchByGid(gid);
		if (tMatchRelate == null) {// 不存在比赛
			/** 查询球队：通过联盟名称+主队名+客队名+日期 **/
			List<TMatchRelate> list = webSportService.searchMatchForMulConditions(league,teamH,teamC,matchDate);
			if (list.size()==0) {// 不存在比赛
				throw new RuntimeException("下注注单失败！球赛不存在！赛事gid:" + gid);
			} else if (list.size()>1) {
				throw new RuntimeException("下注注单失败！球赛重复！赛事gid:" + gid);
			}
			tMatchRelate = list.get(0);

		}
		return tMatchRelate.getMatchId();
	}

	/**
	 * 滚球:单式情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_RE(HttpServletRequest request) throws Exception {
		String errorMsg = "";
		try {
			TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), bet.getRtype());
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			//TRMatchRE record = this.webSportService.getRollMatchRE(gid,timeType);
			TRMatchRE record = this.webSportService.getRollMatchRE(control,bet);
			if(record==null){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			if(!matchDateViable(now, record.getModifyTime())){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
				logger.error(errorMsg);
				throw new SportBetMatchNoViableException(errorMsg);
			}
			
			/** 赛事信息 **/
			String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			if("".equals(matchId)){
				errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
		 
			bet.setLeague(record.getLeague());
			SportBetUtil.initSportBet_RE(bet, record);

			/** 订单信息 **/
			TWebMatchBet order = new TWebMatchBet();
			order.setOrderTime(nowGMT4);
			order.setCreateTime(now);
			order.setModifyTime(now);
			initOrder(order, webUser,request);
			order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			order.setBetType(SportConstant.BET_TYPE_2);// 滚球
			order.setStatus(SportConstant.ORDER_STATUS_m1);// 待确认订单
			order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_CONFIRM, 15));
			order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
					+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, btype));

			order.setBetMatchIds(matchId);
			/** 详细信息 **/
			TWebMatchDetail detail = new TWebMatchDetail();
			detail.setBetWagersId(order.getBetWagersId());
			detail.setGid(record.getGid());
			detail.setLeague(record.getLeague());
			detail.setTeamH(record.getTeamH());
			detail.setTeamC(record.getTeamC());
			detail.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));
			detail.setTimeType(timeType);
			detail.setMatchType(matchType);
			detail.setRtype(rtype);
			detail.setBtype(btype);
			detail.setDtype(btype);
			detail.setSelection(selection);
			detail.setPeriod(period);
			detail.setBetIndex(timeType + ":" + matchType + ":" + rtype + ":" + btype + ":" + selection + ":" + period);
			detail.setBetOdds(bet.getOdds());
			detail.setBetOddsName(bet.getOddsName());
			detail.setBetRqTeam(record.getStrong());
			detail.setBetRq(record.getRatio());
			detail.setBetRqTeamH(record.getHstrong());
			detail.setBetRqH(record.getHratio());
			detail.setBetDx(record.getRatioO());
			detail.setBetDxH(record.getHratioO());
			detail.setBetTime(nowGMT4);
			detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			detail.setStatus(SportConstant.ORDER_STATUS_0);
			detail.setBetStatus(SportConstant.BET_STATUS_0);
			detail.setCreateTime(now);
			detail.setModifyTime(now);
			detail.setBetOddsDes(bet.getOddsDes());
			detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
			detail.setBetVs(bet.getTeam1() + " <font class='radio'>" + bet.getVs() + "</font> " + bet.getTeam2());
			detail.setMatchId(matchId);
			detail.setBetScoreHCur(record.getScoreH());// 滚球主队当前比分
			detail.setBetScoreCCur(record.getScoreC());// 滚球客队当前比分
			detail.setBetUuid(CommonUtils.generateUUID());

			List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
			details.add(detail);
			order.setDetails(details);

			boolean result = this.webSportService.updateWebSportBet(order);
			if (!result) {
				throw new RuntimeException("单式投注异常");
			} else {
				this.betWagersId = order.getBetWagersId();
			}

			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 滚球 篮球：单式情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_REMain(HttpServletRequest request) throws Exception {
		String errorMsg = "";
		try {
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), bet.getRtype());
			//TRMatchRemain record = this.webSportService.getRollMatchRemain(gid,timeType);
			TRMatchRemain record = this.webSportService.getRollMatchRemain(control,bet);
			if(record==null){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			if(!matchDateViable(now, record.getModifyTime())){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
				logger.error(errorMsg);
				throw new SportBetMatchNoViableException(errorMsg);
			}
			
			/** 赛事信息 **/
			String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			if("".equals(matchId)){
				errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
 

			bet.setLeague(record.getLeague());
			SportBetUtil.initSportBet_REMAIN(bet, record);
			/** 订单信息 **/
			TWebMatchBet order = new TWebMatchBet();
			order.setOrderTime(nowGMT4);
			order.setCreateTime(now);
			order.setModifyTime(now);
			initOrder(order, webUser,request);
			order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			order.setBetType(SportConstant.BET_TYPE_2);
			order.setStatus(SportConstant.ORDER_STATUS_m1);
			order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_CONFIRM, 15));
			order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
					+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, btype));

			order.setBetMatchIds(matchId);
			/** 详细信息 **/
			TWebMatchDetail detail = new TWebMatchDetail();
			detail.setBetWagersId(order.getBetWagersId());
			detail.setGid(record.getGid());
			detail.setLeague(record.getLeague());
			detail.setTeamH(record.getTeamH());
			detail.setTeamC(record.getTeamC());
			detail.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));
			detail.setTimeType(timeType);
			detail.setMatchType(matchType);
			detail.setRtype(rtype);
			detail.setBtype(btype);
			detail.setDtype(dtype);
			detail.setSelection(selection);
			detail.setPeriod(period);
			detail.setBetIndex(timeType + ":" + matchType + ":" + rtype + ":" + btype + ":" + selection + ":" + period);
			detail.setBetOdds(bet.getOdds());
			detail.setBetOddsName(bet.getOddsName());
			detail.setBetRqTeam(record.getStrong());
			detail.setBetRq(record.getRatio());
			// detail.setBetRqTeamH(record.getHstrong());
			// detail.setBetRqH(record.getHratio());
			/** 大小 **/
			detail.setBetDx(record.getRatioO());
			if (StringUtils.equalsIgnoreCase(bet.getBtype(), "jf")) {
				if (StringUtils.equalsIgnoreCase(bet.getSelection(), "H")) {
					detail.setBetDx(record.getRatioOuho());
				} else if (StringUtils.equalsIgnoreCase(bet.getSelection(), "C")) {
					detail.setBetDx(record.getRatioOuco());
				}
			}

			// detail.setBetDxH(record.getHratioO());
			detail.setBetTime(nowGMT4);
			detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			detail.setStatus(SportConstant.ORDER_STATUS_0);
			detail.setBetStatus(SportConstant.BET_STATUS_0);
			detail.setCreateTime(now);
			detail.setModifyTime(now);
			detail.setBetOddsDes(bet.getOddsDes());
			detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
			detail.setBetVs(bet.getTeam1() + " <font class='radio'>" + bet.getVs() + "</font> " + bet.getTeam2());
			detail.setMatchId(matchId);
			detail.setBetScoreHCur(record.getScoreH());// 滚球主队当前比分
			detail.setBetScoreCCur(record.getScoreC());// 滚球客队当前比分
			detail.setBetUuid(CommonUtils.generateUUID());
			List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
			details.add(detail);
			order.setDetails(details);

			boolean result = this.webSportService.updateWebSportBet(order);
			if (!result) {
				throw new RuntimeException("篮球单式投注异常");
			} else {
				this.betWagersId = order.getBetWagersId();
			}

		 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 单式情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_R(HttpServletRequest request) throws Exception {
		String errorMsg = "";
		try {
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), bet.getRtype());
			//TFtMatchR record = this.webSportService.getFtMatchR(gid,timeType);
			TFtMatchR record = this.webSportService.getFtMatchR(control,bet);
			if(record==null){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			if(!matchDateViable(now, record.getModifyTime())){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
				logger.error(errorMsg);
				throw new SportBetMatchNoViableException(errorMsg);
			}
			
			/** 赛事信息 **/
			String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			if("".equals(matchId)){
				errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			bet.setLeague(record.getLeague());
			SportBetUtil.initSportBet_R(bet, record);
			/** 订单信息 **/
			TWebMatchBet order = new TWebMatchBet();
			order.setOrderTime(nowGMT4);
			order.setCreateTime(now);
			order.setModifyTime(now);
			initOrder(order, webUser,request);
			order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			order.setBetType(SportConstant.BET_TYPE_1);
			order.setStatus(SportConstant.ORDER_STATUS_1);
			order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
					+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, btype));
			order.setBetMatchIds(matchId);

			/** 详细信息 **/
			TWebMatchDetail detail = new TWebMatchDetail();
			detail.setBetWagersId(order.getBetWagersId());
			detail.setGid(record.getGid());
			detail.setLeague(record.getLeague());
			detail.setTeamH(record.getTeamH());
			detail.setTeamC(record.getTeamC());
			detail.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));
			detail.setTimeType(timeType);
			detail.setMatchType(matchType);
			detail.setRtype(rtype);
			detail.setBtype(btype);
			detail.setDtype(btype);
			detail.setSelection(selection);
			detail.setPeriod(period);
			detail.setBetIndex(timeType + ":" + matchType + ":" + rtype + ":" + btype + ":" + selection + ":" + period);
			detail.setBetOdds(bet.getOdds());
			detail.setBetOddsName(bet.getOddsName());
			detail.setBetRqTeam(record.getStrong());
			detail.setBetRq(record.getRatio());
			detail.setBetRqTeamH(record.getHstrong());
			detail.setBetRqH(record.getHratio());
			detail.setBetDx(record.getRatioO());
			detail.setBetDxH(record.getHratioO());
			detail.setBetTime(nowGMT4);
			detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			detail.setStatus(SportConstant.ORDER_STATUS_0);
			detail.setBetStatus(SportConstant.BET_STATUS_0);
			detail.setCreateTime(now);
			detail.setModifyTime(now);
			detail.setBetOddsDes(bet.getOddsDes());
			detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
			detail.setBetVs(bet.getTeam1() + " <font class='radio'>" + bet.getVs() + "</font> " + bet.getTeam2());
			detail.setMatchId(matchId);
			detail.setBetUuid(CommonUtils.generateUUID());
			List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
			details.add(detail);
			order.setDetails(details);

			boolean result = this.webSportService.updateWebSportBet(order);
			if (!result) {
				throw new RuntimeException("单式投注异常");
			} else {
				this.betWagersId = order.getBetWagersId();
			}

			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 波胆情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_PD(HttpServletRequest request) throws Exception {
		String errorMsg = "";
		try {
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			int tag = StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? SportConstant.PD_TAG_2 : SportConstant.PD_TAG_1;
			String rtypeTemp = bet.getRtype();
			if(StringUtils.equalsIgnoreCase(bet.getPeriod(), "h")){
				rtypeTemp = "hpd";
			}
			TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), rtypeTemp);
			//TFtMatchPD record = this.webSportService.getFtMatchPD(gid,timeType,tag+"");
			TFtMatchPD record = this.webSportService.getFtMatchPD(control,bet,String.valueOf(tag));
			if(record==null){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			if(!matchDateViable(now, record.getModifyTime())){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
				logger.error(errorMsg);
				throw new SportBetMatchNoViableException(errorMsg);
			}
			/** 赛事信息 **/ 
			String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			if("".equals(matchId)){
				errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}

			bet.setLeague(record.getLeague());
			SportBetUtil.initSportBet_PD(bet, record);
			/** 订单信息 **/
			TWebMatchBet order = new TWebMatchBet();
			order.setOrderTime(nowGMT4);
			order.setCreateTime(now);
			order.setModifyTime(now);
			initOrder(order, webUser,request);
			order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			order.setBetType(SportConstant.BET_TYPE_1);
			order.setStatus(SportConstant.ORDER_STATUS_1);
			order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
					+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + (StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? "(全场)" : "(上半场)") + ":" + dtype);

			order.setBetMatchIds(matchId);

			/** 详细信息 **/
			TWebMatchDetail detail = new TWebMatchDetail();
			detail.setBetWagersId(order.getBetWagersId());
			detail.setGid(record.getGid());
			detail.setLeague(record.getLeague());
			detail.setTeamH(record.getTeamH());
			detail.setTeamC(record.getTeamC());
			detail.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));
			detail.setTimeType(timeType);
			detail.setMatchType(matchType);
			detail.setRtype(rtype);
			detail.setBtype(btype);// pd
			detail.setDtype(dtype);
			detail.setSelection(selection);
			detail.setPeriod(period);
			detail.setBetIndex(timeType + ":" + matchType + ":" + rtype + ":" + dtype + ":" + selection + ":" + period);
			detail.setBetOdds(bet.getOdds());
			detail.setBetOddsName(bet.getOddsName());
			detail.setBetRqTeam(record.getStrong());
			// detail.setBetRq(record.getRatio());
			// detail.setBetRqTeamH(record.getHstrong());
			// detail.setBetRqH(record.getHratio());
			// detail.setBetDx(record.getRatioO());
			// detail.setBetDxH(record.getHratioO());
			detail.setBetTime(nowGMT4);
			detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			detail.setStatus(SportConstant.ORDER_STATUS_0);
			detail.setBetStatus(SportConstant.BET_STATUS_0);
			detail.setCreateTime(now);
			detail.setModifyTime(now);
			detail.setBetOddsDes(bet.getOddsDes());
			detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
			detail.setBetVs(bet.getTeam1() + " <font class='radio'>" + bet.getVs() + "</font> " + bet.getTeam2());
			detail.setMatchId(matchId);
			detail.setBetUuid(CommonUtils.generateUUID());
			List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
			details.add(detail);
			order.setDetails(details);

			boolean result = this.webSportService.updateWebSportBet(order);
			if (!result) {
				throw new RuntimeException("波胆投注异常");
			} else {
				this.betWagersId = order.getBetWagersId();
			}

			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 总入球情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_T(HttpServletRequest request) throws Exception {
		try {
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());

			TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), bet.getRtype());
			
			//TFtMatchT record = this.webSportService.getFtMatchT(gid,timeType);
			TFtMatchT record = this.webSportService.getFtMatchT(control,bet);
			String errorMsg = "";
			if(record==null){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			if(!matchDateViable(now, record.getModifyTime())){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
				logger.error(errorMsg);
				throw new SportBetMatchNoViableException(errorMsg);
			}
			
			/** 赛事信息 **/
			String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			if("".equals(matchId)){
				errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			
			bet.setLeague(record.getLeague());
			SportBetUtil.initSportBet_T(bet, record);
 

			/** 订单信息 **/
			TWebMatchBet order = new TWebMatchBet();
			order.setOrderTime(nowGMT4);
			order.setCreateTime(now);
			order.setModifyTime(now);
			initOrder(order, webUser,request);
			order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			order.setBetType(SportConstant.BET_TYPE_1);
			order.setStatus(SportConstant.ORDER_STATUS_1);
			order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
					+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + ":" + dtype);

			order.setBetMatchIds(matchId);

			/** 详细信息 **/
			TWebMatchDetail detail = new TWebMatchDetail();
			detail.setBetWagersId(order.getBetWagersId());
			detail.setGid(record.getGid());
			detail.setLeague(record.getLeague());
			detail.setTeamH(record.getTeamH());
			detail.setTeamC(record.getTeamC());
			detail.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));
			detail.setTimeType(timeType);
			detail.setMatchType(matchType);
			detail.setRtype(rtype);
			detail.setBtype(btype);// t
			detail.setDtype(dtype);
			detail.setSelection(selection);
			detail.setPeriod(period);
			detail.setBetIndex(timeType + ":" + matchType + ":" + rtype + ":" + dtype + ":" + selection + ":" + period);
			detail.setBetOdds(bet.getOdds());
			detail.setBetOddsName(bet.getOddsName());
			detail.setBetRqTeam(record.getStrong());
			// detail.setBetRq(record.getRatio());
			// detail.setBetRqTeamH(record.getHstrong());
			// detail.setBetRqH(record.getHratio());
			// detail.setBetDx(record.getRatioO());
			// detail.setBetDxH(record.getHratioO());
			detail.setBetTime(nowGMT4);
			detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			detail.setStatus(SportConstant.ORDER_STATUS_0);
			detail.setBetStatus(SportConstant.BET_STATUS_0);
			detail.setCreateTime(now);
			detail.setModifyTime(now);
			detail.setBetOddsDes(bet.getOddsDes());
			detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
			detail.setBetVs(bet.getTeam1() + " <font class='radio'>" + bet.getVs() + "</font> " + bet.getTeam2());
			detail.setMatchId(matchId);
			detail.setBetUuid(CommonUtils.generateUUID());
			List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
			details.add(detail);
			order.setDetails(details);

			boolean result = this.webSportService.updateWebSportBet(order);
			if (!result) {
				throw new RuntimeException("总入球投注异常");
			} else {
				this.betWagersId = order.getBetWagersId();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 全场/半场 情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_F(HttpServletRequest request) throws Exception {
		try {
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), bet.getRtype());
			//TFtMatchF record = this.webSportService.getFtMatchF(gid,timeType);
			TFtMatchF record = this.webSportService.getFtMatchF(control,bet);
			String errorMsg = "";
			if(record==null){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			if(!matchDateViable(now, record.getModifyTime())){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
				logger.error(errorMsg);
				throw new SportBetMatchNoViableException(errorMsg);
			}
			
			/** 赛事信息 **/
			String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			if("".equals(matchId)){
				errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			bet.setLeague(record.getLeague());
			SportBetUtil.initSportBet_F(bet, record);
			 

			/** 订单信息 **/
			TWebMatchBet order = new TWebMatchBet();
			order.setOrderTime(nowGMT4);
			order.setCreateTime(now);
			order.setModifyTime(now);
			initOrder(order, webUser,request);
			order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			order.setBetType(SportConstant.BET_TYPE_1);
			order.setStatus(SportConstant.ORDER_STATUS_1);
			order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
					+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + ":" + dtype);

			order.setBetMatchIds(matchId);

			/** 详细信息 **/
			TWebMatchDetail detail = new TWebMatchDetail();
			detail.setBetWagersId(order.getBetWagersId());
			detail.setGid(record.getGid());
			detail.setLeague(record.getLeague());
			detail.setTeamH(record.getTeamH());
			detail.setTeamC(record.getTeamC());
			detail.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));
			detail.setTimeType(timeType);
			detail.setMatchType(matchType);
			detail.setRtype(rtype);
			detail.setBtype(btype);// t
			detail.setDtype(dtype);
			detail.setSelection(selection);
			detail.setPeriod(period);
			detail.setBetIndex(timeType + ":" + matchType + ":" + rtype + ":" + dtype + ":" + selection + ":" + period);
			detail.setBetOdds(bet.getOdds());
			detail.setBetOddsName(bet.getOddsName());
			detail.setBetRqTeam(record.getStrong());
			// detail.setBetRq(record.getRatio());
			// detail.setBetRqTeamH(record.getHstrong());
			// detail.setBetRqH(record.getHratio());
			// detail.setBetDx(record.getRatioO());
			// detail.setBetDxH(record.getHratioO());
			detail.setBetTime(nowGMT4);
			detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			detail.setStatus(SportConstant.ORDER_STATUS_0);
			detail.setBetStatus(SportConstant.BET_STATUS_0);
			detail.setCreateTime(now);
			detail.setModifyTime(now);
			detail.setBetOddsDes(bet.getOddsDes());
			detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
			detail.setBetVs(bet.getTeam1() + " <font class='radio'>" + bet.getVs() + "</font> " + bet.getTeam2());
			detail.setMatchId(matchId);
			detail.setBetUuid(CommonUtils.generateUUID());
			List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
			details.add(detail);
			order.setDetails(details);

			boolean result = this.webSportService.updateWebSportBet(order);
			if (!result) {
				throw new RuntimeException("半场/全场投注异常");
			} else {
				this.betWagersId = order.getBetWagersId();
			}

			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 综合过关情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_P3(HttpServletRequest request) throws Exception {
		try {
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());

			SportBetBean sportBetBean = (SportBetBean) request.getSession().getAttribute("sportBetBean");
			if (sportBetBean != null) {
				List<SportBet> betList = sportBetBean.getSportBetList();
				if (CollectionUtils.isNotEmpty(betList)) {

					/** 订单信息 **/
					TWebMatchBet order = new TWebMatchBet();
					order.setOrderTime(nowGMT4);
					order.setCreateTime(now);
					order.setModifyTime(now);
					initOrder(order, webUser,request);
					order.setBetTime(null);// 综合过关没有时间
					order.setBetType(SportConstant.BET_TYPE_1);
					order.setStatus(SportConstant.ORDER_STATUS_1);
					order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
							+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + ":综合过关");
					List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
					order.setDetails(details);

					String[] betMatchIds = new String[betList.size()];
					int i = 0;
					String matchId = null;
					double tempOdds = 1.0;
					TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), bet.getRtype());
					for (SportBet bet : betList) {
						//System.out.println("gid:" + bet.getGid());
						//TFtMatchP3 record = this.webSportService.getFtMatchP3(bet.getGid(),bet.getTimeType());
						TFtMatchP3 record = this.webSportService.getFtMatchP3(control,bet);
						String errorMsg = "";
						if(record==null){
							errorMsg = "比赛项目gid:" + bet.getGid() + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
							logger.error(errorMsg);
							throw new RuntimeException(errorMsg);
						}
						if(!matchDateViable(now, record.getModifyTime())){
							errorMsg = "比赛项目gid:" + bet.getGid() + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
							logger.error(errorMsg);
							throw new SportBetMatchNoViableException(errorMsg);
						}
						/** 赛事信息 **/
						matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
						if("".equals(matchId)){
							errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
							logger.error(errorMsg);
							throw new RuntimeException(errorMsg);
						}

						
						bet.setLeague(record.getLeague());
						betMatchIds[i] = matchId;
						i++;

						/** 详细信息 **/
						TWebMatchDetail detail = new TWebMatchDetail();
						detail.setBetWagersId(order.getBetWagersId());
						detail.setGid(record.getGid());
						detail.setLeague(record.getLeague());
						detail.setTeamH(record.getTeamH());
						detail.setTeamC(record.getTeamC());
						detail.setRtypeName(SportConstant.getRTypeName(bet.getMatchType().toLowerCase() + "_" + rtype));
						detail.setTimeType(bet.getTimeType());
						detail.setMatchType(bet.getMatchType());
						detail.setRtype(bet.getRtype());
						detail.setBtype(bet.getBtype());// t
						detail.setDtype(bet.getDtype());
						detail.setSelection(bet.getSelection());
						detail.setPeriod(bet.getPeriod());
						detail.setBetIndex(bet.getTimeType() + ":" + bet.getMatchType() + ":" + bet.getRtype() + ":" + bet.getDtype() + ":" + bet.getSelection() + ":"
								+ bet.getPeriod());
						detail.setBetOdds(bet.getOdds());
						detail.setBetOddsName(bet.getOddsName());
						detail.setBetRqTeam(record.getStrong());
						detail.setBetRq(record.getRatio());
						detail.setBetRqTeamH(record.getHstrong());
						detail.setBetRqH(record.getHratio());
						detail.setBetDx(record.getRatioO());
						detail.setBetDxH(record.getHratioO());
						detail.setBetTime(nowGMT4);
						detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
						detail.setStatus(SportConstant.ORDER_STATUS_0);
						detail.setBetStatus(SportConstant.BET_STATUS_0);
						detail.setCreateTime(now);
						detail.setModifyTime(now);
						detail.setBetOddsDes(bet.getOddsDes());
						detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
						detail.setBetVs(bet.getTeam1() + " <font class='radio'>" + bet.getVs() + "</font> " + bet.getTeam2());
						detail.setMatchId(matchId);
						detail.setBetUuid(CommonUtils.generateUUID());
						details.add(detail);
						
						tempOdds = tempOdds * Double.parseDouble(bet.getOdds());
					}
					//可赢金额
					BigDecimal decimal = new BigDecimal(tempOdds);
					double odds = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					double canSum = order.getBetIn()*(odds-1);
					
					order.setBetCanWin(canSum);
					String tmp = ArrayUtils.toString(betMatchIds);
					tmp = tmp.replace("{", "").replace("}", "").trim();
					order.setBetMatchIds(tmp);

					/** 保存数据 **/
					boolean result = this.webSportService.updateWebSportBet(order);
					if (!result) {
						throw new RuntimeException("综合过关投注异常[数据保存]");
					} else {
						this.betWagersId = order.getBetWagersId();
					}

				}
			} else {
				throw new RuntimeException("综合过关投注异常[注单会话过期]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			request.getSession().removeAttribute("sportBetBean");
		}
	}

	/**
	 * 篮球：单式情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_BK_Rmain(HttpServletRequest request) throws Exception {
		try {
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			String rtypeTemp = bet.getRtype();
			if(rtypeTemp.equals(SportConstant.bk_rtype_rmain)){
				rtypeTemp = SportConstant.bk_rtype_rmain_cj;
			}
			TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), rtypeTemp);
			
			//TBkMatchRmain record = this.webSportService.getBkMatchRmain(gid,timeType);
			TBkMatchRmain record = this.webSportService.getBkMatchRmain(control,bet);
			String errorMsg = "";
			if(record==null){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			if(!matchDateViable(now, record.getModifyTime())){
				errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
				logger.error(errorMsg);
				throw new SportBetMatchNoViableException(errorMsg);
			}
			/** 赛事信息 **/
			String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			if("".equals(matchId)){
				errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			
			
			bet.setLeague(record.getLeague());
			SportBetUtil.initSportBet_BK_Rmain(bet, record);
 

			/** 订单信息 **/
			TWebMatchBet order = new TWebMatchBet();
			order.setOrderTime(nowGMT4);
			order.setCreateTime(now);
			order.setModifyTime(now);
			initOrder(order, webUser,request);
			order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			order.setBetType(SportConstant.BET_TYPE_1);
			order.setStatus(SportConstant.ORDER_STATUS_1);
			order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
					+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, btype));

			order.setBetMatchIds(matchId);
			/** 详细信息 **/
			TWebMatchDetail detail = new TWebMatchDetail();
			detail.setBetWagersId(order.getBetWagersId());
			detail.setGid(record.getGid());
			detail.setLeague(record.getLeague());
			detail.setTeamH(record.getTeamH());
			detail.setTeamC(record.getTeamC());
			detail.setRtypeName(SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype));
			detail.setTimeType(timeType);
			detail.setMatchType(matchType);
			detail.setRtype(rtype);
			detail.setBtype(btype);
			detail.setDtype(dtype);
			detail.setSelection(selection);
			detail.setPeriod(period);
			detail.setBetIndex(timeType + ":" + matchType + ":" + rtype + ":" + btype + ":" + selection + ":" + period);
			detail.setBetOdds(bet.getOdds());
			detail.setBetOddsName(bet.getOddsName());
			detail.setBetRqTeam(record.getStrong());
			detail.setBetRq(record.getRatio());
			// detail.setBetRqTeamH(record.getHstrong());
			// detail.setBetRqH(record.getHratio());
			/** 大小 **/
			detail.setBetDx(record.getRatioO());
			if (StringUtils.equalsIgnoreCase(bet.getBtype(), "jf")) {
				if (StringUtils.equalsIgnoreCase(bet.getSelection(), "H")) {
					detail.setBetDx(record.getRatioOuho());
				} else if (StringUtils.equalsIgnoreCase(bet.getSelection(), "C")) {
					detail.setBetDx(record.getRatioOuco());
				}
			}

			// detail.setBetDxH(record.getHratioO());
			detail.setBetTime(nowGMT4);
			detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
			detail.setStatus(SportConstant.ORDER_STATUS_0);
			detail.setBetStatus(SportConstant.BET_STATUS_0);
			detail.setCreateTime(now);
			detail.setModifyTime(now);
			detail.setBetOddsDes(bet.getOddsDes());
			detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
			detail.setBetVs(bet.getTeam1() + " <font class='radio'>" + bet.getVs() + "</font> " + bet.getTeam2());
			detail.setMatchId(matchId);
			detail.setBetUuid(CommonUtils.generateUUID());
			List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
			details.add(detail);
			order.setDetails(details);

			boolean result = this.webSportService.updateWebSportBet(order);
			if (!result) {
				throw new RuntimeException("篮球单式投注异常");
			} else {
				this.betWagersId = order.getBetWagersId();
			}

			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 篮球：综合过关情况
	 * 
	 * @throws Exception
	 **/
	private void doRtype_BK_P3(HttpServletRequest request) throws Exception {
		try {
			Date now = new Date();// 当前北京时间
			Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
			UserContext uc = this.getUserContext(request);
			WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());

			sportBetBean = (SportBetBean) request.getSession().getAttribute("sportBetBean");
			if (sportBetBean != null) {
				List<SportBet> betList = sportBetBean.getSportBetList();
				if (CollectionUtils.isNotEmpty(betList)) {

					/** 订单信息 **/
					TWebMatchBet order = new TWebMatchBet();
					order.setOrderTime(nowGMT4);
					order.setCreateTime(now);
					order.setModifyTime(now);
					initOrder(order, webUser,request);
					order.setBetTime(null);
					order.setBetType(SportConstant.BET_TYPE_1);
					order.setStatus(SportConstant.ORDER_STATUS_1);
					order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType) + "-"
							+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rtype) + ":综合过关");
					List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
					order.setDetails(details);

					String[] betMatchIds = new String[betList.size()];
					int i = 0;

					String matchId = null;
					
					double tempOdds = 1.0 ;
					String rtypeTemp = bet.getRtype();
					if("p3".equals(rtypeTemp)){
						rtypeTemp = "bk_p3";
					}
					TMatchControl control = webSportService.getMatchControl(bet.getTimeType(), rtypeTemp);
					for (SportBet bet : betList) {
							
						//TBkMatchRmain record = this.webSportService.getBkMatchRmain(bet.getGid(),bet.getTimeType());
						TBkMatchRmain record = this.webSportService.getBkMatchRmain(control,bet);
						String errorMsg = "";
						if(record==null){
							errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "不存在(" + timeType + "->" + matchType + "->" + rtype + ")";
							logger.error(errorMsg);
							throw new RuntimeException(errorMsg);
						}
						if(!matchDateViable(now, record.getModifyTime())){
							errorMsg = "比赛项目gid:" + gid + " timeType:" + timeType + "比赛过期(now:" + f.format(now) + "-> modify:" + f.format(record.getModifyTime()) +")";
							logger.error(errorMsg);
							throw new SportBetMatchNoViableException(errorMsg);
						}
						/** 赛事信息 **/
						matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
						if("".equals(matchId)){
							errorMsg = "下注注单失败！球赛不存在！比赛时间:" + record.getMatchTime() + "主队:" + record.getTeamH() + "客队:" + record.getTeamC();
							logger.error(errorMsg);
							throw new RuntimeException(errorMsg);
						}
						
						
						
						bet.setLeague(record.getLeague());
						betMatchIds[i] = matchId;
						i++;

						/** 详细信息 **/
						TWebMatchDetail detail = new TWebMatchDetail();
						detail.setBetWagersId(order.getBetWagersId());
						detail.setGid(record.getGid());
						detail.setLeague(record.getLeague());
						detail.setTeamH(record.getTeamH());
						detail.setTeamC(record.getTeamC());
						detail.setRtypeName(SportConstant.getRTypeName(bet.getMatchType().toLowerCase() + "_" + bet.getRtype()));
						detail.setTimeType(bet.getTimeType());
						detail.setMatchType(bet.getMatchType());
						detail.setRtype(bet.getRtype());
						detail.setBtype(bet.getBtype());
						detail.setDtype(bet.getDtype());
						detail.setSelection(bet.getSelection());
						detail.setPeriod(bet.getPeriod());
						detail.setBetIndex(bet.getTimeType() + ":" + bet.getMatchType() + ":" + bet.getRtype() + ":" + bet.getBtype() + ":" + bet.getSelection() + ":"
								+ bet.getPeriod());
						detail.setBetOdds(bet.getOdds());
						detail.setBetOddsName(bet.getOddsName());
						detail.setBetRqTeam(record.getStrong());
						detail.setBetRq(record.getRatio());
						// detail.setBetRqTeamH(record.getHstrong());
						// detail.setBetRqH(record.getHratio());

						/** 大小 **/
						detail.setBetDx(record.getRatioO());
						if (StringUtils.equalsIgnoreCase(bet.getBtype(), "jf")) {
							if (StringUtils.equalsIgnoreCase(bet.getSelection(), "H")) {
								detail.setBetDx(record.getRatioOuho());
							} else if (StringUtils.equalsIgnoreCase(bet.getSelection(), "C")) {
								detail.setBetDx(record.getRatioOuco());
							}
						}

						// detail.setBetDxH(record.getHratioO());
						detail.setBetTime(nowGMT4);
						detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
						detail.setStatus(SportConstant.ORDER_STATUS_0);
						detail.setBetStatus(SportConstant.BET_STATUS_0);
						detail.setCreateTime(now);
						detail.setModifyTime(now);
						detail.setBetOddsDes(bet.getOddsDes());
						detail.setBetOddsMinus(NumberUtils.toInt(bet.getOddsMinus(), 0));
						detail.setBetVs(bet.getTeam1() + "<font class='radio'>" + bet.getVs() + "</font>" + bet.getTeam2());
						detail.setMatchId(matchId);
						detail.setBetUuid(CommonUtils.generateUUID());
						details.add(detail);
						
						tempOdds = tempOdds * Double.parseDouble(bet.getOdds());
					}
					 
					//可赢金额
					BigDecimal decimal = new BigDecimal(tempOdds);
					double odds = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					double canSum = order.getBetIn()*(odds-1);
					order.setBetCanWin(canSum);
					String tmp = ArrayUtils.toString(betMatchIds);
					tmp = tmp.replace("{", "").replace("}", "").trim();
					order.setBetMatchIds(tmp);

					/** 保存数据 **/
					boolean result = this.webSportService.updateWebSportBet(order);
					if (!result) {
						throw new RuntimeException("综合过关投注异常[数据保存]");
					} else {
						this.betWagersId = order.getBetWagersId();
					}

				}
			} else {
				throw new RuntimeException("综合过关投注异常[注单会话过期]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			request.getSession().removeAttribute("sportBetBean");
		}
	}
	
	
	/**
	 * 校验余额
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/checkMoney")
	public void checkMoney(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserContext uc = this.getUserContext(request);
			String goldStr = request.getParameter("gold");
			if(StringUtils.isEmpty(goldStr)){
				ServletUtils.outPrintWithToken(request, response, "请输入交易金额！");
				return;
			}
			double gold = Double.valueOf(goldStr);
			if(gold<=0){
				ServletUtils.outPrintWithToken(request, response,"交易金额不能小于0！");
				return;
			}
			
			double userMoney = this.webUserService.getWebUserMoney(uc.getUserName());
			if(userMoney<=0){
				ServletUtils.outPrintWithToken(request, response,"余额不足！");
				return;
			}
			
			if(Double.valueOf(gold)>userMoney){
				ServletUtils.outPrintWithToken(request, response,"余额不足！");
				return;
			}
			
			ServletUtils.outPrintSuccess(request, response,"校验成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("校验余额异常！",e);
			ServletUtils.outPrintWithToken(request, response,"校验余额异常！");
		}

	}

	//判断比赛是否过期
	public boolean matchDateViable(Date nowTime, Date modifyTime){
		
		SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName("web_sport_bet_viable_time");
		String _value = StringUtils.defaultString(sysParameter.getParamValue(), "5");
		int value = Integer.valueOf(_value);
		long divTime = nowTime.getTime()-modifyTime.getTime();
		int minuter = (int)(divTime/(60*1000));
		if(minuter > value){
			return false;
		}
		return true;
		
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

	public String getOddsName() {
		return oddsName;
	}

	public void setOddsName(String oddsName) {
		this.oddsName = oddsName;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getBetWagersId() {
		return betWagersId;
	}

	public void setBetWagersId(String betWagersId) {
		this.betWagersId = betWagersId;
	}

 

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public SportBetBean getSportBetBean() {
		return sportBetBean;
	}

	public void setSportBetBean(SportBetBean sportBetBean) {
		this.sportBetBean = sportBetBean;
	}
	
	

}
