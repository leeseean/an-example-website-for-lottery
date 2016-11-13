package com.mh.web.controller.m;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.conf.SystemConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.CommonUtils;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.SportBet;
import com.mh.entity.SportBetDetail;
import com.mh.entity.SysParameter;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchControl;
import com.mh.entity.TMatchRelate;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.TWebMatchDetail;
import com.mh.entity.WebUser;
import com.mh.ifc.util.ComUtil;
import com.mh.service.SysParameterService;
import com.mh.service.WapSportService;
import com.mh.service.WebSportService;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.job.CodeDataHelper;
import com.mh.web.login.UserContext;
import com.mh.web.util.MobileSportUtil;
@Controller
@RequestMapping("/m/sportOrders")
public class MSportOrdersController extends BaseController {
	private SportBetDetail bet;
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WapSportService wapSportService;
	
	@Autowired
	private WebSportService webSportService;
	
	@Autowired
	private SysParameterService sysParameterService;
	
	@RequestMapping("/doSubmit")
	public synchronized void doSubmit(HttpServletRequest request,HttpServletResponse response){
		try{
			/**判断是否维护**/
			String status = WebSiteManagerConstants.ctxMap.get(CommonConstant.PLAT_PARAM_HUANGGUAN);
			if("0".equals(status)){
				sendErrorMessage(request, response, WebSiteManagerConstants.ctxMap.get(CommonConstant.PLAT_PARAM_HUANGGUAN+"_"+WebConstants.WEB_WEIHU_STATUS_OFF));
				return;
			}
			
			UserContext user = getUserContext(request);
			//初始化订单参数
			initParams(request);
			
			if(bet.getMoney() < 0){
				sendErrorMessage(request, response,"请输入金额!");
				return;
			}
			
			if(!this.checkMoney(bet.getMoney(), user)){
				sendErrorMessage(request, response,"您的余额不足!");
				return;
			}
			
			if(bet.getMatchType().equals("BK")){
				MobileSportUtil.limitBk(bet);
			}else{
				MobileSportUtil.limitFt(bet);
			}
			//单注最大限额
			if(bet.getMoney() > Double.parseDouble(bet.getLimitBet())){
				sendErrorMessage(request, response,"单注最大限额:" + Double.parseDouble(bet.getLimitBet()));
				return;
			}
			//单注最小限额
			if(SportConstant.FT_LIMIT_MIN > bet.getMoney()){
				sendErrorMessage(request, response,"单注最小限额:" + SportConstant.FT_LIMIT_MIN);
				return;
			}
			//控制器
			TMatchControl control = getControllerState(bet.getTimeType(),bet.getRtype());
			if(null == control){
				sendErrorMessage(request, response,"数据异常,请联系24小时在线客服！");
				return;
			}
			//String curtag = control.getCurtag();
			if(SportConstant.ft_rtype_r.equals(bet.getRtype())){
				do_ft_r(control, user, request, response);	//足球单式
			}else if(SportConstant.ft_rtype_f.equals(bet.getRtype())){
				do_ft_f(control, user, request, response);	//足球全半场
			}else if(SportConstant.ft_rtype_pd.equals(bet.getRtype()) || SportConstant.ft_rtype_hpd.equals(bet.getRtype())){
				do_ft_pd(control, user, request, response);	//足球(半)波胆
			}else if(SportConstant.ft_rtype_t.equals(bet.getRtype())){
				do_ft_t(control, user, request, response);	//足球总入球
			}else if(SportConstant.roll_rtype_re.equals(bet.getRtype())){
				do_ft_re(control, user, request, response);	//足球滚球
			}else if(SportConstant.bk_rtype_rmain_cj.equals(bet.getRtype())){
				do_bk_r_main(control, user, request, response);//篮球单式
			}else if(SportConstant.roll_rtype_remain.equals(bet.getRtype())){
				do_bk_re_main(control, user, request, response);//篮球滚球
			}else if(SportConstant.ft_rtype_p3.equals(bet.getRtype())){
				do_ft_p3(control, user, request, response);	//足球 综合过关
			}
		} catch (Exception e){
			cleanSession(request);//清空串关session
			logger.error("数据异常,请联系24小时在线客服！");
			sendErrorMessage(request, response,"数据异常,请联系24小时在线客服！");
			e.printStackTrace();
		}
	}

	/**
	 * 返回错误信息提示
	 * @param request
	 * @param response
	 * @param msg
	 */
	private void sendErrorMessage(HttpServletRequest request,HttpServletResponse response,String msg){
		bet.setMsg(msg);
		request.getSession().setAttribute("bet", bet);
		ServletUtils.outPrintFail(request, response, bet);
	}
	
	/**
	 * 返回成功订单信息  将订单详情放入session中  展示完后清除
	 * @param request
	 * @param response
	 */
	private void sendSuccessMessage(HttpServletRequest request,HttpServletResponse response){
		request.getSession().setAttribute("bet", bet);
		ServletUtils.outPrintSuccess(request, response, bet);
	}
	/**
	 *	足球--综合过关
	 *	1.取出session中赛事  
	 *	2.判断是否满足3种赛事
	 *	3.判断N种赛事有效性  有一种赛事无效则清空session中所有赛事记录
	 *	4.组装订单
	 *	5.清除session
	 */
	@SuppressWarnings("unchecked")
	public void do_ft_p3(TMatchControl control,UserContext user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Date now = new Date();// 当前北京时间
		Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
		UserContext uc = this.getUserContext(request);
		WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());

		Map<String, SportBetDetail> map = (Map<String, SportBetDetail>) request.getSession().getAttribute("orderParam");
		if (map.isEmpty()) {
			sendErrorMessage(request, response, "无订单信息");
			return;
		}
		
		if(map.size() < 3){
			sendErrorMessage(request, response, "该项目至少需要三条订单信息");
			return;	
		}
		
		/**检查每条赛事有效性**/
		for (Map.Entry<String, SportBetDetail> entry : map.entrySet()) {
			SportBetDetail sport = entry.getValue();
			//TFtMatchP3 record = this.webSportService.getFtMatchP3(sport.getGid(),sport.getTimeType());
			SportBet bet = new SportBet();
			bet.setTimeType(sport.getTimeType());
			bet.setGid(entry.getKey());
			TFtMatchP3 record = this.webSportService.getFtMatchP3(control,bet);
			if(null == record){
				cleanSession(request);//清空串关session
				sendErrorMessage(request, response, "gid:"+sport.getGid()+"赛事不存在");
				return;
			}
			//判断赛事是否过期
			if(!matchDateViable(now, record.getModifyTime())){
				cleanSession(request);//清空串关session
				sendErrorMessage(request, response, "gid:"+sport.getGid()+"赛事已过期");
				return;
			}
			//赛事信息
			String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			if(!StringUtils.isNotBlank(matchId)){
				cleanSession(request);//清空串关session
				sendErrorMessage(request, response, "gid:"+sport.getGid()+"赛事无效");
				return;
			}
		}
		/**检查每条赛事有效性**/
		
		/**组装主订单信息**/
		TWebMatchBet order = new TWebMatchBet();
		order.setWebFlat(webUser.getUserFlag());	//网站数据标示
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));//网站名称
		order.setMatchRtype(SportConstant.bk_rtype_p3);			//rType
		order.setBetType(SportConstant.BET_TYPE_1);				//投注类型
		order.setBetUserName(webUser.getUserName());	//会员账号
		//order.setBetMatchContent("");		//投注详细内容
		//order.setBetMatchResult();			//投注结果（输2、赢1）
		order.setBetWagersId(ComUtil.getSportsOrder());			//投注单号
		order.setBetIn(bet.getMoney());	//投注金额
		//order.setBetIncome();			//有效投注金额
		//order.setBetUsrWin();			//会员赢的金额（负数）
		//order.setBetNetWin();			//平台赢的金额（正数）
		//order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setBetTime(null);		//投注时间
		order.setStatus(SportConstant.ORDER_STATUS_1);					//订单状态
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_UNSETTLE, 17));	
		//order.setBetVoidReason();			//无效原因
		order.setTimeType(bet.getTimeType());		//timeType 早盘 滚球 今日赛事
		order.setBetSportType(bet.getMatchType());	//体育类型（FT足球、BK篮球）
		order.setBetSportName(SportConstant.getTimeTypeName(bet.getTimeType()) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, bet.getMatchType()) + "-"
				+ SportConstant.getRTypeName("ft_p3") + ":" + SportConstant.getRTypeName("ft_p3"));			//投注具体类型名称如今日赛-足球-单式
		//order.setBetMatchIds(matchId);			//球队IDs,多个用","格开
		//order.setBetSettledTime();//结算时间
		order.setRemark("体育下注");				//备注
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));	//投注Ip
		order.setOrderTime(nowGMT4);		//美东时间
		order.setCreateTime(now);
		order.setConfirmTime(order.getCreateTime());	//滚球确定时间（默认比下注时间延迟90秒）
		order.setModifyTime(now);	//修改时间
		order.setBetUserAgent(webUser.getUserAgent());			//代理账号
		//order.setBackWater();				//返水比例
		order.setBackWaterStatus(0);		//返水（0未返水，1已返水）
		//order.setBackWaterTime(new Date());	//返水时间
		//order.setBackSysUserName();		//返水操作人
		//order.setBetCanWin();			//可赢金额
		/**主订单信息**/
		
		/**订单详情集合**/
		List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
		/**赛事matchid数组**/
		String[] betMatchIds = new String[map.size()];
		int i = 0;
		String matchId = null;
		/**下单成功后订单详情集合**/
		List<SportBetDetail> betDetail = new ArrayList<SportBetDetail>();
		double tempOdds = 1.0;
		/**子订单信息**/
		for (Map.Entry<String, SportBetDetail> entry : map.entrySet()) {
			SportBetDetail sport = entry.getValue();
			SportBet bet = new SportBet();
			bet.setTimeType(sport.getTimeType());
			bet.setGid(entry.getKey());
			TFtMatchP3 record = this.webSportService.getFtMatchP3(control,bet);
			//TFtMatchP3 record = this.webSportService.getFtMatchP3(sport.getGid(),sport.getTimeType());
			matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
			betMatchIds[i] = matchId;
			i++;
			TWebMatchDetail detail = new TWebMatchDetail();
			
			detail.setBetWagersId(order.getBetWagersId());
			detail.setGid(record.getGid());
			detail.setLeague(record.getLeague());
			detail.setTeamH(record.getTeamH());
			detail.setTeamC(record.getTeamC());
			detail.setRtypeName(SportConstant.getRTypeName(sport.getMatchType().toLowerCase() + "_" + sport.getRtype()));
			detail.setTimeType(sport.getTimeType());
			detail.setMatchType(sport.getMatchType());
			detail.setRtype(sport.getRtype());
			detail.setBtype(sport.getBtype());// t
			detail.setDtype(sport.getDtype());
			detail.setSelection(sport.getSelection());
			detail.setPeriod(sport.getPeriod());
			detail.setBetIndex(sport.getTimeType() + ":" + sport.getMatchType() + ":" + sport.getRtype() + ":" + sport.getDtype() + ":" + sport.getSelection() + ":"
					+ sport.getPeriod());
			detail.setBetOdds(sport.getOdds());
			detail.setBetOddsName(sport.getOddsName());
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
			detail.setBetOddsDes(sport.getOddsDes());
			detail.setBetOddsMinus(NumberUtils.toInt(sport.getOddsMinus(), 0));
			//detail.setBetVs(sport.getTeam1() + " <font class='radio'> VS </font> " + sport.getTeam2());
			detail.setBetUuid(CommonUtils.generateUUID()); 
			
			if("rq".equals(sport.getBtype())){
				if("f".equals(sport.getPeriod())){
					if("H".equals(record.getStrong())){
						detail.setBetVs(sport.getTeam1() + " <font class='radio'> "+record.getRatio()+" </font> " + sport.getTeam2());
					}else{
						detail.setBetVs(sport.getTeam2() + " <font class='radio'> "+record.getRatio()+" </font> " + sport.getTeam1());
					}
				}else{
					if("H".equals(record.getHstrong())){
						detail.setBetVs(sport.getTeam1() + " <font class='radio'> "+record.getHratio()+" </font> " + sport.getTeam2());
					}else{
						detail.setBetVs(sport.getTeam2() + " <font class='radio'> "+record.getHratio()+" </font> " + sport.getTeam1());
					}
				}
			}else{
				detail.setBetVs(sport.getTeam1() + " <font class='radio'> VS </font> " + sport.getTeam2());
			}
			detail.setMatchId(matchId);
			if("dx".equals(sport.getBtype())){
				if("f".equals(sport.getPeriod())){
					if(sport.getSelection().equals("H")){
						detail.setBetOddsName("大" + record.getRatioU());
					}else{
						detail.setBetOddsName("小" + record.getRatioO());
					}
				}else{
					if(sport.getSelection().equals("H")){
						detail.setBetOddsName("大" + record.getHratioU());
					}else{
						detail.setBetOddsName("小" + record.getHratioO());
					}
				}
			}else if("ds".equals(sport.getBtype())){
				if(sport.getSelection().equals("H")){
					detail.setBetOddsName("单");
				}else if(sport.getSelection().equals("C")){
					detail.setBetOddsName("双");
				}
			}else{
				if(sport.getSelection().equals("H")){
					detail.setBetOddsName(record.getTeamH());
				}else if(sport.getSelection().equals("C")){
					detail.setBetOddsName(record.getTeamC());
				}else{
					detail.setBetOddsName("和局");
				}
			}
			
			details.add(detail);
			sport.setLeague(detail.getLeague());
			sport.setTeamH(detail.getTeamH());
			sport.setTeamC(detail.getTeamC());
			sport.setOddsName(detail.getBetOddsName());
			sport.setOddsDes(detail.getBetOddsDes());
			betDetail.add(sport);
			
			tempOdds = tempOdds * Double.parseDouble(detail.getBetOdds());
		}
		//可赢金额
		BigDecimal decimal = new BigDecimal(tempOdds);
		double odds = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		double canSum = order.getBetIn()*(odds-1);
		
		order.setBetCanWin(canSum);
		
		/**子订单信息**/
		order.setDetails(details);
		String tmp = ArrayUtils.toString(betMatchIds);
		tmp = tmp.replace("{", "").replace("}", "").trim();
		order.setBetMatchIds(tmp);
		boolean result = this.webSportService.updateWebSportBet(order);
		if(!result){
			cleanSession(request);//清空串关session
			sendErrorMessage(request, response, "下注失败！如有疑问请与24小时在线客服联系");
			return;
		}
		bet.setFlag(true);
		bet.setOrderNO(order.getBetWagersId());
		/**将订单详情放入session中  展示完后清除**/
		request.getSession().setAttribute("betp3", betDetail);
		request.getSession().setAttribute("bet", bet);	
		
		ServletUtils.outPrintSuccess(request, response, betDetail);
		cleanSession(request);//清空串关session
	}
	
	/**
	 * 篮球--滚球
	 */
	public void do_bk_re_main(TMatchControl control,UserContext user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		bet.setRoll(true);
		//TRMatchRemain t = wapSportService.getRollMatchRemain(control.getCurtag(), bet.getGid()).get(0);
		TRMatchRemain t = wapSportService.getRollMatchRemain(control, bet.getGid()).get(0);
		if(null == t){
			sendErrorMessage(request, response, "该赛事不存在或已过期");
			return;
		}
		Date now = new Date();// 当前北京时间
		Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
		
		//赛事信息
		String matchId = initOrderMatchTeam(t.getGid(), t.getLeague(), t.getTeamH(), t.getTeamC(), StringUtils.split(t.getMatchTime(), " ")[0]);
		if(!StringUtils.isNotBlank(matchId)){
			sendErrorMessage(request, response, "该赛事无效");
			return;
		}
		WebUser webUser = this.webUserService.findWebrUseByUserName(user.getUserName());
		
		/**组装订单信息**/
		TWebMatchBet order = new TWebMatchBet();
		
		/** 组装订单详细信息 **/
		TWebMatchDetail detail = new TWebMatchDetail();
		MobileSportUtil.bk_re_main_order(bet.getBtype(),bet.getDtype(), bet.getSelection(), t, detail);
		
		order.setWebFlat(webUser.getUserFlag());	//网站数据标示
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));//网站名称
		order.setMatchRtype(bet.getRtype());			//rType
		order.setBetType(SportConstant.BET_TYPE_2);				//投注类型
		order.setBetUserName(webUser.getUserName());	//会员账号
		//order.setBetMatchContent("");		//投注详细内容
		//order.setBetMatchResult();			//投注结果（输2、赢1）
		order.setBetWagersId(ComUtil.getSportsOrder());			//投注单号
		order.setBetIn(bet.getMoney());	//投注金额
		//order.setBetIncome();			//有效投注金额
		//order.setBetUsrWin();			//会员赢的金额（负数）
		//order.setBetNetWin();			//平台赢的金额（正数）
		order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setStatus(SportConstant.ORDER_STATUS_m1);					//订单状态
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_CONFIRM, 15));	
		//order.setBetVoidReason();			//无效原因
		order.setTimeType(bet.getTimeType());		//timeType 早盘 滚球 今日赛事
		order.setBetSportType(bet.getMatchType());	//体育类型（FT足球、BK篮球）
		order.setBetSportName(SportConstant.getTimeTypeName(bet.getTimeType()) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, bet.getMatchType()) + "-"
				+ SportConstant.getRTypeName("roll_re_main") + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, bet.getBtype()));			//投注具体类型名称如今日赛-足球-单式
		order.setBetMatchIds(matchId);			//球队IDs,多个用","格开
		//order.setBetSettledTime();//结算时间
		order.setRemark("体育下注");				//备注
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));	//投注Ip
		order.setOrderTime(nowGMT4);		//美东时间
		order.setCreateTime(now);
		
		SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName("web_match_roll_confirm_time");
		long millis = 90 * 1000;
		if(null != sysParameter){
			millis = NumberUtils.toInt(sysParameter.getParamValue(), 90) * 1000;
			order.setConfirmTime(ComUtil.addDateTime(order.getCreateTime(), millis));//滚球确定时间（默认比下注时间延迟90秒）
		}
		//order.setConfirmTime(order.getCreateTime());	
		order.setModifyTime(now);	//修改时间
		order.setBetUserAgent(webUser.getUserAgent());			//代理账号
		//order.setBackWater();				//返水比例
		order.setBackWaterStatus(0);		//返水（0未返水，1已返水）
		//order.setBackWaterTime(new Date());	//返水时间
		//order.setBackSysUserName();		//返水操作人
		double odds = Double.parseDouble(detail.getBetOdds());
		int minus = null == detail.getBetOddsMinus() ? 0 : detail.getBetOddsMinus();
		double canSum =order.getBetIn()*(odds-minus);
		if(canSum>=0){
			order.setBetCanWin(canSum);
		}else{
			order.setBetCanWin(0.0);
		}
		
		
		detail.setBetWagersId(order.getBetWagersId());	//所属投注单[关联bet订单表]
		detail.setGid(t.getGid());			//比赛赛编[A队与B队比赛，唯一编号]
		detail.setLeague(t.getLeague());		//联盟名称[比赛所属联赛名称]
		detail.setTeamH(t.getTeamH());		//主队[上队]
		detail.setTeamC(t.getTeamC());		//客队[下队]
		detail.setRtypeName(SportConstant.getRTypeName("roll_re_main"));	//赛事类型[单式、波胆等]
		detail.setTimeType(order.getTimeType());		//大类[roll滚球、today今日赛事、tom早盘]
		detail.setMatchType(order.getBetSportType());	//FT足球、BK篮球
		detail.setRtype(order.getMatchRtype());		//投注类型[r,pd等]
		detail.setBtype(bet.getBtype());		//盘口类型[dy独赢 rq让球 rf让分 jf球队得分 dx大小 ds单双 pd波胆 t总入球 f半场/全场]
		detail.setDtype(bet.getDtype());		//结算盘口类型
		detail.setSelection(bet.getSelection());	//投注主队还是客队[H主，C客,N平局]
		detail.setPeriod(bet.getPeriod());		//f-全场 h-半场
		detail.setBetIndex(bet.getTimeType() + ":" + bet.getMatchType() + ":" + bet.getRtype() + ":" + bet.getBtype() + ":" + bet.getSelection() + ":" + bet.getPeriod());		//下注盘口描述
		//detail.setBetOddsDes("");
		//detail.setBetOdds("");		//盘口赔率
		//detail.setBetOddsMinus(0);	//赔率减倍
		//detail.setBetOddsName("");	//盘口赔率盘口名称
		detail.setBetRqTeam(t.getStrong());	//让分/让球方:[H主队、C客队]
		detail.setBetRq(t.getRatio());
		//detail.setBetRqTeamH(t.getHstrong());	//让分/让球方-上半场:[H主队、C客队]
		//detail.setBetRqH(t.getHratio());		//让分/让球-上半场:盘口
		//detail.setBetDx(t.getRatioO());		//大小球
		//detail.setBetDxH(t.getHratioO());		//大小球(半场)
		//detail.setBetScoreH("");	//主队得分
		//detail.setBetScoreC("");	//客队得分
		detail.setBetScoreHCur(t.getScoreH());	//当前主队比分（滚球）
		detail.setBetScoreCCur(t.getScoreC());	//当前客队比分（滚球）
		detail.setBetTime(nowGMT4);	//投注时间
		detail.setMatchTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));//比赛时间
		detail.setStatus(SportConstant.ORDER_STATUS_0);		//结算状态
		detail.setBetStatus(SportConstant.BET_STATUS_0);		//注单状态
		detail.setCreateTime(now);
		detail.setModifyTime(now);
		//detail.setBetVs(t.getTeamH() + "<font class='radio'>vs</font>" + t.getTeamC());
		detail.setMatchId(matchId);		//赛事编号
		detail.setBetUuid(CommonUtils.generateUUID());
		List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
		details.add(detail);
		order.setDetails(details);
		
		boolean result = this.webSportService.updateWebSportBet(order);
		if(!result){
			sendErrorMessage(request, response, "下注失败！如有疑问请与24小时在线客服联系");
			return;
		}
		initOrderParam(detail);
		sendSuccessMessage(request, response);

	}
	
	
	/**
	 * 篮球--单式 
	 */
	public void do_bk_r_main(TMatchControl control,UserContext user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//TBkMatchRmain t = wapSportService.getBkMatchRmain(control.getCurtag(),bet.getGid()).get(0);
		TBkMatchRmain t = wapSportService.getBkMatchRmain(control,bet.getGid()).get(0);
		if(null == t){
			sendErrorMessage(request, response, "该赛事不存在或已过期");
			return;
		}
		Date now = new Date();// 当前北京时间
		Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
		
		//判断赛事是否过期
		if(!matchDateViable(now, t.getModifyTime())){
			sendErrorMessage(request, response, "该赛事已过期");
			return;
		}
		//赛事信息
		String matchId = initOrderMatchTeam(t.getGid(), t.getLeague(), t.getTeamH(), t.getTeamC(), StringUtils.split(t.getMatchTime(), " ")[0]);
		if(!StringUtils.isNotBlank(matchId)){
			sendErrorMessage(request, response, "该赛事无效");
			return;
		}
		WebUser webUser = this.webUserService.findWebrUseByUserName(user.getUserName());
		
		/**组装订单信息**/
		TWebMatchBet order = new TWebMatchBet();
		
		/** 组装订单详细信息 **/
		TWebMatchDetail detail = new TWebMatchDetail();
		MobileSportUtil.bk_r_main_order(bet.getBtype(),bet.getDtype(), bet.getSelection(), t, detail);
		
		order.setWebFlat(webUser.getUserFlag());	//网站数据标示
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));//网站名称
		order.setMatchRtype(SportConstant.bk_rtype_rmain);			//rType
		order.setBetType(SportConstant.BET_TYPE_1);				//投注类型
		order.setBetUserName(webUser.getUserName());	//会员账号
		//order.setBetMatchContent("");		//投注详细内容
		//order.setBetMatchResult();			//投注结果（输2、赢1）
		order.setBetWagersId(ComUtil.getSportsOrder());			//投注单号
		order.setBetIn(bet.getMoney());	//投注金额
		//order.setBetIncome();			//有效投注金额
		//order.setBetUsrWin();			//会员赢的金额（负数）
		//order.setBetNetWin();			//平台赢的金额（正数）
		order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setStatus(SportConstant.ORDER_STATUS_1);					//订单状态
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_UNSETTLE, 17));	
		//order.setBetVoidReason();			//无效原因
		order.setTimeType(bet.getTimeType());		//timeType 早盘 滚球 今日赛事
		order.setBetSportType(bet.getMatchType());	//体育类型（FT足球、BK篮球）
		order.setBetSportName(SportConstant.getTimeTypeName(bet.getTimeType()) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, bet.getMatchType()) + "-"
				+ SportConstant.getRTypeName("ft_r") + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, bet.getBtype()));			//投注具体类型名称如今日赛-足球-单式
		order.setBetMatchIds(matchId);			//球队IDs,多个用","格开
		//order.setBetSettledTime();//结算时间
		order.setRemark("体育下注");				//备注
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));	//投注Ip
		order.setOrderTime(nowGMT4);		//美东时间
		order.setCreateTime(now);
		order.setConfirmTime(order.getCreateTime());	//滚球确定时间（默认比下注时间延迟90秒）
		order.setModifyTime(now);	//修改时间
		order.setBetUserAgent(webUser.getUserAgent());			//代理账号
		//order.setBackWater();				//返水比例
		order.setBackWaterStatus(0);		//返水（0未返水，1已返水）
		//order.setBackWaterTime(new Date());	//返水时间
		//order.setBackSysUserName();		//返水操作人
		double odds = Double.parseDouble(detail.getBetOdds());
		int minus = null == detail.getBetOddsMinus() ? 0 : detail.getBetOddsMinus();
		double canSum =order.getBetIn()*(odds-minus);
		if(canSum>=0){
			order.setBetCanWin(canSum);
		}else{
			order.setBetCanWin(0.0);
		}
		
		
		
		detail.setBetWagersId(order.getBetWagersId());	//所属投注单[关联bet订单表]
		detail.setGid(t.getGid());			//比赛赛编[A队与B队比赛，唯一编号]
		detail.setLeague(t.getLeague());		//联盟名称[比赛所属联赛名称]
		detail.setTeamH(t.getTeamH());		//主队[上队]
		detail.setTeamC(t.getTeamC());		//客队[下队]
		detail.setRtypeName(SportConstant.getRTypeName("ft_r"));	//赛事类型[单式、波胆等]
		detail.setTimeType(order.getTimeType());		//大类[roll滚球、today今日赛事、tom早盘]
		detail.setMatchType(order.getBetSportType());	//FT足球、BK篮球
		detail.setRtype(SportConstant.bk_rtype_rmain);		//投注类型[r,pd等]
		detail.setBtype(bet.getBtype());		//盘口类型[dy独赢 rq让球 rf让分 jf球队得分 dx大小 ds单双 pd波胆 t总入球 f半场/全场]
		detail.setDtype(bet.getDtype());		//结算盘口类型
		detail.setSelection(bet.getSelection());	//投注主队还是客队[H主，C客,N平局]
		detail.setPeriod(bet.getPeriod());		//f-全场 h-半场
		detail.setBetIndex(bet.getTimeType() + ":" + bet.getMatchType() + ":" + SportConstant.bk_rtype_rmain + ":" + bet.getBtype() + ":" + bet.getSelection() + ":" + bet.getPeriod());		//下注盘口描述
		//detail.setBetOddsDes();
		//detail.setBetOdds("");		//盘口赔率
		//detail.setBetOddsMinus(1);	//赔率减倍
		//detail.setBetOddsName(dtype);	//盘口赔率盘口名称
		detail.setBetRqTeam(t.getStrong());	//让分/让球方:[H主队、C客队]
		detail.setBetRq(t.getRatio());
		//detail.setBetRqTeamH(t.getHstrong());	//让分/让球方-上半场:[H主队、C客队]
		//detail.setBetRqH(t.getHratio());		//让分/让球-上半场:盘口
		//detail.setBetDx(t.getRatioO());		//大小球
		//detail.setBetDxH(t.getHratioO());		//大小球(半场)
		//detail.setBetScoreH("");	//主队得分
		//detail.setBetScoreC("");	//客队得分
		//detail.setBetScoreHCur("");	//当前主队比分（滚球）
		//detail.setBetScoreCCur("");	//当前客队比分（滚球）
		detail.setBetTime(nowGMT4);	//投注时间
		detail.setMatchTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));//比赛时间
		detail.setStatus(SportConstant.ORDER_STATUS_0);		//结算状态
		detail.setBetStatus(SportConstant.BET_STATUS_0);		//注单状态
		detail.setCreateTime(now);
		detail.setModifyTime(now);
		//detail.setBetVs(t.getTeamH() + "<font class='radio'>vs</font>" + t.getTeamC());
		detail.setMatchId(matchId);		//赛事编号
		detail.setBetUuid(CommonUtils.generateUUID());
		List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
		details.add(detail);
		order.setDetails(details);
		boolean result = this.webSportService.updateWebSportBet(order);
		if(!result){
			sendErrorMessage(request, response, "下注失败！如有疑问请与24小时在线客服联系");
			return;
		}
		initOrderParam(detail);
		sendSuccessMessage(request, response);
	}
	
	/**
	 * 足球--滚球
	 * @throws Exception
	 */
	public void do_ft_re(TMatchControl control,UserContext user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		bet.setRoll(true);
		//滚球-足球-单式
		//TRMatchRE t = wapSportService.getRollMatchRE(control.getCurtag(), bet.getGid()).get(0);
		TRMatchRE t = wapSportService.getRollMatchRE(control, bet.getGid()).get(0);
		if(null == t){
			sendErrorMessage(request, response, "该赛事不存在或已过期");
			return;
		}
		Date now = new Date();// 当前北京时间
		Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
		
		//赛事信息
		String matchId = initOrderMatchTeam(t.getGid(), t.getLeague(), t.getTeamH(), t.getTeamC(), StringUtils.split(t.getMatchTime(), " ")[0]);
		if(!StringUtils.isNotBlank(matchId)){
			sendErrorMessage(request, response, "该赛事无效");
			return;
		}
		WebUser webUser = this.webUserService.findWebrUseByUserName(user.getUserName());
		
		/**组装订单信息**/
		TWebMatchBet order = new TWebMatchBet();
		
		/** 组装订单详细信息 **/
		TWebMatchDetail detail = new TWebMatchDetail();
		MobileSportUtil.ft_re_order(bet.getBtype(), bet.getSelection(), bet.getPeriod(), t, detail);
		
		order.setWebFlat(webUser.getUserFlag());	//网站数据标示
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));//网站名称
		order.setMatchRtype(bet.getRtype());			//rType
		order.setBetType(SportConstant.BET_TYPE_2);				//投注类型
		order.setBetUserName(webUser.getUserName());	//会员账号
		//order.setBetMatchContent("");		//投注详细内容
		//order.setBetMatchResult();			//投注结果（输2、赢1）
		order.setBetWagersId(ComUtil.getSportsOrder());			//投注单号
		order.setBetIn(bet.getMoney());	//投注金额
		//order.setBetIncome();			//有效投注金额
		//order.setBetUsrWin();			//会员赢的金额（负数）
		//order.setBetNetWin();			//平台赢的金额（正数）
		//order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setStatus(SportConstant.ORDER_STATUS_m1);					//订单状态
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_CONFIRM, 15));	
		//order.setBetVoidReason();			//无效原因
		order.setTimeType(bet.getTimeType());		//timeType 早盘 滚球 今日赛事
		order.setBetSportType(bet.getMatchType());	//体育类型（FT足球、BK篮球）
		order.setBetSportName(SportConstant.getTimeTypeName(bet.getTimeType()) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, bet.getMatchType()) + "-"
				+ SportConstant.getRTypeName("roll_re") + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, bet.getBtype()));			//投注具体类型名称如今日赛-足球-单式
		order.setBetMatchIds(matchId);			//球队IDs,多个用","格开
		//order.setBetSettledTime();//结算时间
		order.setRemark("体育下注");				//备注
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));	//投注Ip
		order.setOrderTime(nowGMT4);		//美东时间
		order.setCreateTime(now);
		
		SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName("web_match_roll_confirm_time");
		long millis = 90 * 1000;
		if(null != sysParameter){
			millis = NumberUtils.toInt(sysParameter.getParamValue(), 90) * 1000;
			order.setConfirmTime(ComUtil.addDateTime(order.getCreateTime(), millis));//滚球确定时间（默认比下注时间延迟90秒）
		}
		//order.setConfirmTime(order.getCreateTime());	
		order.setModifyTime(now);	//修改时间
		order.setBetUserAgent(webUser.getUserAgent());			//代理账号
		//order.setBackWater();				//返水比例
		order.setBackWaterStatus(0);		//返水（0未返水，1已返水）
		//order.setBackWaterTime(new Date());	//返水时间
		//order.setBackSysUserName();		//返水操作人
		double odds = Double.parseDouble(detail.getBetOdds());
		int minus = null == detail.getBetOddsMinus() ? 0 : detail.getBetOddsMinus();
		double canSum =order.getBetIn()*(odds-minus);
		if(canSum>=0){
			order.setBetCanWin(canSum);
		}else{
			order.setBetCanWin(0.0);
		}
		
		detail.setBetWagersId(order.getBetWagersId());	//所属投注单[关联bet订单表]
		detail.setGid(t.getGid());			//比赛赛编[A队与B队比赛，唯一编号]
		detail.setLeague(t.getLeague());		//联盟名称[比赛所属联赛名称]
		detail.setTeamH(t.getTeamH());		//主队[上队]
		detail.setTeamC(t.getTeamC());		//客队[下队]
		detail.setRtypeName("足球");	//赛事类型[单式、波胆等]
		detail.setTimeType(order.getTimeType());		//大类[roll滚球、today今日赛事、tom早盘]
		detail.setMatchType(order.getBetSportType().toUpperCase());	//FT足球、BK篮球
		detail.setRtype(order.getMatchRtype());		//投注类型[r,pd等]
		detail.setBtype(bet.getBtype());		//盘口类型[dy独赢 rq让球 rf让分 jf球队得分 dx大小 ds单双 pd波胆 t总入球 f半场/全场]
		detail.setDtype(bet.getBtype());		//结算盘口类型
		detail.setSelection(bet.getSelection());	//投注主队还是客队[H主，C客,N平局]
		detail.setPeriod(bet.getPeriod());		//f-全场 h-半场
		detail.setBetIndex(bet.getTimeType() + ":" + bet.getMatchType() + ":" + bet.getRtype() + ":" + bet.getBtype() + ":" + bet.getSelection() + ":" + bet.getPeriod());		//下注盘口描述
		//detail.setBetOdds("");		//盘口赔率
		//detail.setBetOddsMinus(0);	//赔率减倍
		//detail.setBetOddsName("");	//盘口赔率盘口名称
		detail.setBetRqTeam(t.getStrong());	//让分/让球方:[H主队、C客队]
		detail.setBetRq(t.getRatio());
		detail.setBetRqTeamH(t.getHstrong());	//让分/让球方-上半场:[H主队、C客队]
		detail.setBetRqH(t.getHratio());		//让分/让球-上半场:盘口
		detail.setBetDx(t.getRatioO());		//大小球
		detail.setBetDxH(t.getHratioO());		//大小球(半场)
		//detail.setBetScoreH("");	//主队得分
		//detail.setBetScoreC("");	//客队得分
		detail.setBetScoreHCur(t.getScoreH());	//当前主队比分（滚球）
		detail.setBetScoreCCur(t.getScoreC());	//当前客队比分（滚球）
		detail.setBetTime(nowGMT4);	//投注时间
		detail.setMatchTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));//比赛时间
		detail.setStatus(SportConstant.ORDER_STATUS_0);		//结算状态
		detail.setBetStatus(SportConstant.BET_STATUS_0);		//注单状态
		detail.setCreateTime(now);
		detail.setModifyTime(now);
		//detail.setBetVs("");
		detail.setMatchId(matchId);		//赛事编号
		detail.setBetUuid(CommonUtils.generateUUID());
		List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
		details.add(detail);
		order.setDetails(details);
		
		boolean result = this.webSportService.updateWebSportBet(order);
		if(!result)
		{
			sendErrorMessage(request, response, "下注失败！如有疑问请与24小时在线客服联系");
			return;
		}
		initOrderParam(detail);
		sendSuccessMessage(request, response);
	}
	
	/**
	 * 足球--总入球
	 * @throws Exception
	 */
	public void do_ft_t(TMatchControl control,UserContext user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//总入球-足球
		//TFtMatchT t = wapSportService.getFtMatchT(control.getCurtag(),bet.getGid()).get(0);
		TFtMatchT t = wapSportService.getFtMatchT(control,bet.getGid()).get(0);
		if(null == t){
			sendErrorMessage(request, response, "该赛事不存在或已过期");
			return;
		}
		Date now = new Date();// 当前北京时间
		Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
		
		//判断赛事是否过期
		if(!matchDateViable(now, t.getModifyTime())){
			sendErrorMessage(request, response, "该赛事已过期");
			return;
		}
		//赛事信息
		String matchId = initOrderMatchTeam(t.getGid(), t.getLeague(), t.getTeamH(), t.getTeamC(), StringUtils.split(t.getMatchTime(), " ")[0]);
		if(!StringUtils.isNotBlank(matchId)){
			sendErrorMessage(request, response, "该赛事无效");
			return;
		}
		WebUser webUser = this.webUserService.findWebrUseByUserName(user.getUserName());
		
		/**组装订单信息**/
		TWebMatchBet order = new TWebMatchBet();
		
		/** 组装订单详细信息 **/
		TWebMatchDetail detail = new TWebMatchDetail();
		MobileSportUtil.ft_t_order(bet.getBtype(), t, detail);
		
		order.setWebFlat(webUser.getUserFlag());	//网站数据标示
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));//网站名称
		order.setMatchRtype(bet.getRtype());			//rType
		order.setBetType(SportConstant.BET_TYPE_1);				//投注类型
		order.setBetUserName(webUser.getUserName());	//会员账号
		//order.setBetMatchContent("");		//投注详细内容
		//order.setBetMatchResult();			//投注结果（输2、赢1）
		order.setBetWagersId(ComUtil.getSportsOrder());			//投注单号
		order.setBetIn(bet.getMoney());	//投注金额
		//order.setBetIncome();			//有效投注金额
		//order.setBetUsrWin();			//会员赢的金额（负数）
		//order.setBetNetWin();			//平台赢的金额（正数）
		order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setStatus(SportConstant.ORDER_STATUS_1);					//订单状态
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_UNSETTLE, 17));	
		//order.setBetVoidReason();			//无效原因
		order.setTimeType(bet.getTimeType());		//timeType 早盘 滚球 今日赛事
		order.setBetSportType(bet.getMatchType());	//体育类型（FT足球、BK篮球）
		order.setBetSportName(SportConstant.getTimeTypeName(bet.getTimeType()) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, bet.getMatchType()) + "-"
				+ SportConstant.getRTypeName("ft_t") + ":" + bet.getBtype());			//投注具体类型名称如今日赛-足球-单式
		order.setBetMatchIds(matchId);			//球队IDs,多个用","格开
		//order.setBetSettledTime();//结算时间
		order.setRemark("体育下注");				//备注
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));	//投注Ip
		order.setOrderTime(nowGMT4);		//美东时间
		order.setCreateTime(now);
		order.setConfirmTime(order.getCreateTime());	//滚球确定时间（默认比下注时间延迟90秒）
		order.setModifyTime(now);	//修改时间
		order.setBetUserAgent(webUser.getUserAgent());			//代理账号
		//order.setBackWater();				//返水比例
		order.setBackWaterStatus(0);		//返水（0未返水，1已返水）
		//order.setBackWaterTime(new Date());	//返水时间
		//order.setBackSysUserName();		//返水操作人
		double odds = Double.parseDouble(detail.getBetOdds());
		int minus = null == detail.getBetOddsMinus() ? 0 : detail.getBetOddsMinus();
		double canSum =order.getBetIn()*(odds-minus);
		if(canSum>=0){
			order.setBetCanWin(canSum);
		}else{
			order.setBetCanWin(0.0);
		}
		
		
		detail.setBetWagersId(order.getBetWagersId());	//所属投注单[关联bet订单表]
		detail.setGid(t.getGid());			//比赛赛编[A队与B队比赛，唯一编号]
		detail.setLeague(t.getLeague());		//联盟名称[比赛所属联赛名称]
		detail.setTeamH(t.getTeamH());		//主队[上队]
		detail.setTeamC(t.getTeamC());		//客队[下队]
		detail.setRtypeName(SportConstant.getRTypeName("ft_t"));	//赛事类型[单式、波胆等]
		detail.setTimeType(order.getTimeType());		//大类[roll滚球、today今日赛事、tom早盘]
		detail.setMatchType(order.getBetSportType().toUpperCase());	//FT足球、BK篮球
		detail.setRtype(order.getMatchRtype());		//投注类型[r,pd等]
		detail.setBtype(order.getMatchRtype());		//盘口类型[dy独赢 rq让球 rf让分 jf球队得分 dx大小 ds单双 pd波胆 t总入球 f半场/全场]
		detail.setDtype(bet.getBtype());		//结算盘口类型
		detail.setSelection(bet.getSelection());	//投注主队还是客队[H主，C客,N平局]
		detail.setPeriod(bet.getPeriod());		//f-全场 h-半场
		detail.setBetIndex(bet.getTimeType() + ":" + bet.getMatchType() + ":" + bet.getRtype() + ":" + bet.getBtype() + ":" + bet.getSelection() + ":" + bet.getPeriod());		//下注盘口描述
		detail.setBetOddsDes("总入球 - <font color='red'>全场</font>");
		//detail.setBetOdds("");		//盘口赔率
		detail.setBetOddsMinus(1);	//赔率减倍
		detail.setBetOddsName(bet.getBtype().equals("7up") ? "7或以上" : bet.getBtype());	//盘口赔率盘口名称
		detail.setBetRqTeam(t.getStrong());	//让分/让球方:[H主队、C客队]
		//detail.setBetRq(t.getRatio());
		//detail.setBetRqTeamH(t.getHstrong());	//让分/让球方-上半场:[H主队、C客队]
		//detail.setBetRqH(t.getHratio());		//让分/让球-上半场:盘口
		//detail.setBetDx(t.getRatioO());		//大小球
		//detail.setBetDxH(t.getHratioO());		//大小球(半场)
		//detail.setBetScoreH("");	//主队得分
		//detail.setBetScoreC("");	//客队得分
		//detail.setBetScoreHCur("");	//当前主队比分（滚球）
		//detail.setBetScoreCCur("");	//当前客队比分（滚球）
		detail.setBetTime(nowGMT4);	//投注时间
		detail.setMatchTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));//比赛时间
		detail.setStatus(SportConstant.ORDER_STATUS_0);		//结算状态
		detail.setBetStatus(SportConstant.BET_STATUS_0);		//注单状态
		detail.setCreateTime(now);
		detail.setModifyTime(now);
		detail.setBetVs(t.getTeamH() + "<font class='radio'>vs</font>" + t.getTeamC());
		detail.setMatchId(matchId);		//赛事编号
		detail.setBetUuid(CommonUtils.generateUUID());
		List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
		details.add(detail);
		order.setDetails(details);
		
		boolean result = this.webSportService.updateWebSportBet(order);
		if(!result){
			sendErrorMessage(request, response, "下注失败！如有疑问请与24小时在线客服联系");
			return;
		}
		initOrderParam(detail);
		sendSuccessMessage(request, response);
	}
	
	/**
	 *  足球波胆 
	 */
	public void do_ft_pd(TMatchControl control,UserContext user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//波胆足球
		//TFtMatchPD t = wapSportService.getFtMatchPD(control.getCurtag(),bet.getGid()).get(0);
		TFtMatchPD t = wapSportService.getFtMatchPD(control,bet.getGid()).get(0);
		if(null == t){
			sendErrorMessage(request, response, "该赛事不存在或已过期");
			return;
		}
		Date now = new Date();// 当前北京时间
		Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
		
		//判断赛事是否过期
		if(!matchDateViable(now, t.getModifyTime())){
			sendErrorMessage(request, response, "该赛事已过期");
			return;
		}
		//赛事信息
		String matchId = initOrderMatchTeam(t.getGid(), t.getLeague(), t.getTeamH(), t.getTeamC(), StringUtils.split(t.getMatchTime(), " ")[0]);
		if(!StringUtils.isNotBlank(matchId)){
			sendErrorMessage(request, response, "该赛事无效");
			return;
		}
		WebUser webUser = this.webUserService.findWebrUseByUserName(user.getUserName());
		
		/**组装订单信息**/
		TWebMatchBet order = new TWebMatchBet();
		
		TWebMatchDetail detail = new TWebMatchDetail();
		MobileSportUtil.ft_pd_order(bet.getBtype(), t, detail);
		order.setWebFlat(webUser.getUserFlag());	//网站数据标示
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));//网站名称
		order.setMatchRtype("pd");			//rType
		order.setBetType(SportConstant.BET_TYPE_1);				//投注类型
		order.setBetUserName(webUser.getUserName());	//会员账号
		//order.setBetMatchContent("");		//投注详细内容
		//order.setBetMatchResult();			//投注结果（输2、赢1）
		order.setBetWagersId(ComUtil.getSportsOrder());			//投注单号
		order.setBetIn(bet.getMoney());	//投注金额
		//order.setBetIncome();			//有效投注金额
		//order.setBetUsrWin();			//会员赢的金额（负数）
		//order.setBetNetWin();			//平台赢的金额（正数）
		//order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setStatus(SportConstant.ORDER_STATUS_1);					//订单状态
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_UNSETTLE, 17));	
		//order.setBetVoidReason();			//无效原因
		order.setTimeType(bet.getTimeType());		//timeType 早盘 滚球 今日赛事
		order.setBetSportType(bet.getMatchType());	//体育类型（FT足球、BK篮球）
		order.setBetSportName(SportConstant.getTimeTypeName(bet.getTimeType()) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, bet.getMatchType()) + "-"
				+ "波胆" + (StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? "(全场)" : "(上半场)") + ":" + bet.getBtype());
		//order.setBetSportName(SportConstant.getTimeTypeName(timeType) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, matchType.toUpperCase()) + "-"
		//		+ SportConstant.getRTypeName(matchType.toLowerCase() + "_" + rType) + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, btype));			//投注具体类型名称如今日赛-足球-单式
		order.setBetMatchIds(matchId);			//球队IDs,多个用","格开
		//order.setBetSettledTime();//结算时间
		order.setRemark("体育下注");				//备注
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));	//投注Ip
		order.setOrderTime(nowGMT4);		//美东时间
		order.setCreateTime(now);
		order.setConfirmTime(order.getCreateTime());	//滚球确定时间（默认比下注时间延迟90秒）
		order.setModifyTime(now);	//修改时间
		order.setBetUserAgent(webUser.getUserAgent());			//代理账号
		//order.setBackWater();				//返水比例
		order.setBackWaterStatus(0);		//返水（0未返水，1已返水）
		//order.setBackWaterTime(new Date());	//返水时间
		//order.setBackSysUserName();		//返水操作人
		double odds = Double.parseDouble(detail.getBetOdds());
		int minus = null == detail.getBetOddsMinus() ? 0 : detail.getBetOddsMinus();
		double canSum =order.getBetIn()*(odds-minus);
		if(canSum>=0){
			order.setBetCanWin(canSum);//可赢金额
		}else{
			order.setBetCanWin(0.0);
		}			
		
		
		/** 组装订单详细信息 **/
		
		detail.setBetWagersId(order.getBetWagersId());	//所属投注单[关联bet订单表]
		detail.setGid(t.getGid());			//比赛赛编[A队与B队比赛，唯一编号]
		detail.setLeague(t.getLeague());		//联盟名称[比赛所属联赛名称]
		detail.setTeamH(t.getTeamH());		//主队[上队]
		detail.setTeamC(t.getTeamC());		//客队[下队]
		detail.setRtypeName("波胆");	//赛事类型[单式、波胆等]
		detail.setTimeType(order.getTimeType());		//大类[roll滚球、today今日赛事、tom早盘]
		detail.setMatchType(order.getBetSportType().toUpperCase());	//FT足球、BK篮球
		detail.setRtype(order.getMatchRtype());		//投注类型[r,pd等]
		detail.setBtype(order.getMatchRtype());		//盘口类型[dy独赢 rq让球 rf让分 jf球队得分 dx大小 ds单双 pd波胆 t总入球 f半场/全场]
		detail.setDtype(bet.getBtype());		//结算盘口类型
		detail.setSelection(bet.getSelection());	//投注主队还是客队[H主，C客,N平局]
		detail.setPeriod(bet.getPeriod());		//f-全场 h-半场
		detail.setBetIndex(bet.getTimeType() + ":" + bet.getMatchType() + ":" + "pd" + ":" + bet.getBtype() + ":" + bet.getSelection() + ":" + bet.getPeriod());		//下注盘口描述
		
		detail.setBetOddsDes("波胆 - <font color=\"red\">上半场</font>");
		if("f".equals(bet.getPeriod())){
			detail.setBetOddsDes("波胆 - <font color=\"red\">全场</font>");
		}
		//detail.setBetOdds("");		//盘口赔率
		detail.setBetOddsName(bet.getBtype().equals("other") ? "其他比分" : bet.getBtype());	//盘口赔率盘口名称
		detail.setBetRqTeam(t.getStrong());	//让分/让球方:[H主队、C客队]
		//detail.setBetRq(t.getRatio());
		//detail.setBetRqTeamH(t.getHstrong());	//让分/让球方-上半场:[H主队、C客队]
		//detail.setBetRqH(t.getHratio());		//让分/让球-上半场:盘口
		//detail.setBetDx(t.getRatioO());		//大小球
		//detail.setBetDxH(t.getHratioO());		//大小球(半场)
		//detail.setBetScoreH("");	//主队得分
		//detail.setBetScoreC("");	//客队得分
		//detail.setBetScoreHCur("");	//当前主队比分（滚球）
		//detail.setBetScoreCCur("");	//当前客队比分（滚球）
		detail.setBetTime(nowGMT4);	//投注时间
		detail.setMatchTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));//比赛时间
		detail.setStatus(SportConstant.ORDER_STATUS_0);		//结算状态
		detail.setBetStatus(SportConstant.BET_STATUS_0);		//注单状态
		detail.setCreateTime(now);
		detail.setModifyTime(now);
		detail.setBetVs(t.getTeamH() + "<font class='radio'>vs</font>" + t.getTeamC());
		detail.setMatchId(matchId);		//赛事编号
		detail.setBetUuid(CommonUtils.generateUUID());
		
		List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
		details.add(detail);
		order.setDetails(details);
		
		boolean result = this.webSportService.updateWebSportBet(order);
		if(!result){
			sendErrorMessage(request, response, "下注失败！如有疑问请与24小时在线客服联系");
			return;
		}
		initOrderParam(detail);
		sendSuccessMessage(request, response);
	}
	
	
	/**
	 * 足球--单式
	 * @param model
	 * @throws Exception 
	 */
	public void do_ft_r(TMatchControl control,UserContext user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//单式足球
		//TFtMatchR record = wapSportService.getFtMatchR(control.getCurtag(), bet.getGid()).get(0);
		TFtMatchR record = wapSportService.getFtMatchR(control, bet.getGid()).get(0);
		if(null == record){
			sendErrorMessage(request, response, "该赛事不存在或已过期");
			return;
		}
		Date now = new Date();// 当前北京时间
		Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
		
		//判断赛事是否过期
		if(!matchDateViable(now, record.getModifyTime())){
			sendErrorMessage(request, response, "该赛事已过期");
			return;
		}
		//赛事信息
		String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
		if(!StringUtils.isNotBlank(matchId)){
			sendErrorMessage(request, response, "该赛事无效");
			return;
		}
		WebUser webUser = this.webUserService.findWebrUseByUserName(user.getUserName());
		
		/**组装订单信息**/
		TWebMatchBet order = new TWebMatchBet();
		
		/** 组装订单详细信息 **/
		TWebMatchDetail detail = new TWebMatchDetail();
		MobileSportUtil.ft_r_order(bet.getBtype(), bet.getSelection(), bet.getPeriod(), record, detail);
		
		order.setWebFlat(webUser.getUserFlag());	//网站数据标示
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));//网站名称
		order.setMatchRtype(bet.getRtype());			//rType
		order.setBetType(SportConstant.BET_TYPE_1);				//投注类型
		order.setBetUserName(webUser.getUserName());	//会员账号
		//order.setBetMatchContent("");		//投注详细内容
		//order.setBetMatchResult();			//投注结果（输2、赢1）
		order.setBetWagersId(ComUtil.getSportsOrder());			//投注单号
		order.setBetIn(bet.getMoney());	//投注金额
		//order.setBetIncome();			//有效投注金额
		//order.setBetUsrWin();			//会员赢的金额（负数）
		//order.setBetNetWin();			//平台赢的金额（正数）
		//order.setBetTime(DateUtil.parse(t.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setStatus(SportConstant.ORDER_STATUS_1);					//订单状态
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_UNSETTLE, 17));	
		//order.setBetVoidReason();			//无效原因
		order.setTimeType(bet.getTimeType());		//timeType 早盘 滚球 今日赛事
		order.setBetSportType(bet.getMatchType());	//体育类型（FT足球、BK篮球）
		order.setBetSportName(SportConstant.getTimeTypeName(bet.getTimeType()) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, bet.getMatchType()) + "-"
				+ SportConstant.getRTypeName("ft_r") + ":" + CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SportConstant.MATCH_ODDS_CONS, bet.getBtype()));			//投注具体类型名称如今日赛-足球-单式
		order.setBetMatchIds(matchId);			//球队IDs,多个用","格开
		//order.setBetSettledTime();//结算时间
		order.setRemark("体育下注");				//备注
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));	//投注Ip
		order.setOrderTime(nowGMT4);		//美东时间
		order.setCreateTime(now);
		order.setConfirmTime(order.getCreateTime());	//滚球确定时间（默认比下注时间延迟90秒）
		order.setModifyTime(now);	//修改时间
		order.setBetUserAgent(webUser.getUserAgent());			//代理账号
		//order.setBackWater();				//返水比例
		order.setBackWaterStatus(0);		//返水（0未返水，1已返水）
		//order.setBackWaterTime(new Date());	//返水时间
		//order.setBackSysUserName();		//返水操作人
		double odds = Double.parseDouble(detail.getBetOdds());
		int minus = null == detail.getBetOddsMinus() ? 0 : detail.getBetOddsMinus();
		double canSum =order.getBetIn()*(odds-minus);
		if(canSum>=0){
			order.setBetCanWin(canSum);//可赢金额
		}else{
			order.setBetCanWin(0.0);
		}
					
		
		
		
		detail.setBetWagersId(order.getBetWagersId());	//所属投注单[关联bet订单表]
		detail.setGid(record.getGid());			//比赛赛编[A队与B队比赛，唯一编号]
		detail.setLeague(record.getLeague());		//联盟名称[比赛所属联赛名称]
		detail.setTeamH(record.getTeamH());		//主队[上队]
		detail.setTeamC(record.getTeamC());		//客队[下队]
		detail.setRtypeName(SportConstant.getRTypeName("ft_r"));	//赛事类型[单式、波胆等]
		detail.setTimeType(order.getTimeType());		//大类[roll滚球、today今日赛事、tom早盘]
		detail.setMatchType(order.getBetSportType().toUpperCase());	//FT足球、BK篮球
		detail.setRtype(order.getMatchRtype());		//投注类型[r,pd等]
		detail.setBtype(bet.getBtype());		//盘口类型[dy独赢 rq让球 rf让分 jf球队得分 dx大小 ds单双 pd波胆 t总入球 f半场/全场]
		detail.setDtype(bet.getBtype());		//结算盘口类型
		detail.setSelection(bet.getSelection());	//投注主队还是客队[H主，C客,N平局]
		detail.setPeriod(bet.getPeriod());		//f-全场 h-半场
		detail.setBetIndex(bet.getTimeType() + ":" + bet.getMatchType() + ":" + bet.getRtype() + ":" + bet.getBtype() + ":" + bet.getSelection() + ":" + bet.getPeriod());		//下注盘口描述
		//detail.setBetOdds("");		//盘口赔率
		//detail.setBetOddsMinus(0);	//赔率减倍
		//detail.setBetOddsName("");	//盘口赔率盘口名称
		detail.setBetRqTeam(record.getStrong());	//让分/让球方:[H主队、C客队]
		detail.setBetRq(record.getRatio());
		detail.setBetRqTeamH(record.getHstrong());	//让分/让球方-上半场:[H主队、C客队]
		detail.setBetRqH(record.getHratio());		//让分/让球-上半场:盘口
		detail.setBetDx(record.getRatioO());		//大小球
		detail.setBetDxH(record.getHratioO());		//大小球(半场)
		//detail.setBetScoreH("");	//主队得分
		//detail.setBetScoreC("");	//客队得分
		//detail.setBetScoreHCur("");	//当前主队比分（滚球）
		//detail.setBetScoreCCur("");	//当前客队比分（滚球）
		detail.setBetTime(nowGMT4);	//投注时间
		detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));//比赛时间
		detail.setStatus(SportConstant.ORDER_STATUS_0);		//结算状态
		detail.setBetStatus(SportConstant.BET_STATUS_0);		//注单状态
		detail.setCreateTime(now);
		detail.setModifyTime(now);
		//detail.setBetVs("");
		detail.setMatchId(matchId);		//赛事编号
		detail.setBetUuid(CommonUtils.generateUUID());
		List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
		details.add(detail);
		order.setDetails(details);
		boolean result = this.webSportService.updateWebSportBet(order);
		if(!result){
			sendErrorMessage(request, response, "下注失败！如有疑问请与24小时在线客服联系");
			return;
		}
		initOrderParam(detail);
		sendSuccessMessage(request, response);
	}
	
	/**
	 * 足球--全半场
	 * @return
	 * @throws Exception 
	 */
	public void do_ft_f(TMatchControl control,UserContext user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//TFtMatchF record = wapSportService.getFtMatchF(control.getCurtag(), bet.getGid()).get(0);
		TFtMatchF record = wapSportService.getFtMatchF(control, bet.getGid()).get(0);
		if(null == record){
			sendErrorMessage(request, response, "该赛事不存在或已过期");
			return;
		}
		Date now = new Date();// 当前北京时间
		Date nowGMT4 = DateUtil.getGMT_4_Date();// 当前美东时间
		
		//判断赛事是否过期
		if(!matchDateViable(now, record.getModifyTime())){
			sendErrorMessage(request, response, "该赛事已过期");
			return;
		}
		
		String matchId = initOrderMatchTeam(record.getGid(), record.getLeague(), record.getTeamH(), record.getTeamC(), StringUtils.split(record.getMatchTime(), " ")[0]);
		if(!StringUtils.isNotBlank(matchId)){
			sendErrorMessage(request, response, "该赛事无效");
			return;
		}
		WebUser webUser = this.webUserService.findWebrUseByUserName(user.getUserName());
		
		/**组装订单信息**/
		TWebMatchBet order = new TWebMatchBet();
		
		/** 组装订单详细信息 **/
		TWebMatchDetail detail = new TWebMatchDetail();
		MobileSportUtil.ft_f_order(bet.getBtype(), record, detail);
		
		order.setWebFlat(webUser.getUserFlag());	//网站数据标示
		order.setWebRemark(CommonConstant.resCommMap.get(CommonConstant.WEB_USER_FLAG));//网站名称
		order.setMatchRtype(bet.getRtype());			//rType
		order.setBetType(SportConstant.BET_TYPE_1);				//投注类型
		order.setBetUserName(webUser.getUserName());	//会员账号
		//order.setBetMatchContent("");		//投注详细内容
		//order.setBetMatchResult();			//投注结果（输2、赢1）
		order.setBetWagersId(ComUtil.getSportsOrder());			//投注单号
		order.setBetIn(bet.getMoney());	//投注金额
		//order.setBetIncome();			//有效投注金额
		//order.setBetUsrWin();			//会员赢的金额（负数）
		//order.setBetNetWin();			//平台赢的金额（正数）
		order.setBetTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));		//投注时间
		order.setStatus(SportConstant.ORDER_STATUS_1);					//订单状态
		order.setBetStatus(NumberUtils.toInt(SystemConstant.BET_STATUS_BET_UNSETTLE, 17));	
		//order.setBetVoidReason();			//无效原因
		order.setTimeType(bet.getTimeType());		//timeType 早盘 滚球 今日赛事
		order.setBetSportType(bet.getMatchType());	//体育类型（FT足球、BK篮球）
		order.setBetSportName(SportConstant.getTimeTypeName(bet.getTimeType()) + "|" + CodeDataHelper.getCodeValueByCodeTypeAndCodeName(SportConstant.MATCH_TYPE_CONS, bet.getMatchType()) + "-"
				+ SportConstant.getRTypeName("ft_f") + ":" + bet.getBtype());			//投注具体类型名称如今日赛-足球-单式
		order.setBetMatchIds(matchId);			//球队IDs,多个用","格开
		//order.setBetSettledTime();//结算时间
		order.setRemark("体育下注");				//备注
		order.setBetMemberIpAddress(IPSeeker.getIpAddress(request));	//投注Ip
		order.setOrderTime(nowGMT4);		//美东时间
		order.setCreateTime(now);
		order.setConfirmTime(order.getCreateTime());	//滚球确定时间（默认比下注时间延迟90秒）
		order.setModifyTime(now);	//修改时间
		order.setBetUserAgent(webUser.getUserAgent());			//代理账号
		//order.setBackWater();				//返水比例
		order.setBackWaterStatus(0);		//返水（0未返水，1已返水）
		//order.setBackWaterTime(new Date());	//返水时间
		//order.setBackSysUserName();		//返水操作人
		double odds = Double.parseDouble(detail.getBetOdds());
		int minus = null == detail.getBetOddsMinus() ? 0 : detail.getBetOddsMinus();
		double canSum =order.getBetIn()*(odds-minus);
		if(canSum>=0){
			order.setBetCanWin(canSum);
		}else{
			order.setBetCanWin(0.0);
		}
		
		detail.setBetWagersId(order.getBetWagersId());	//所属投注单[关联bet订单表]
		detail.setGid(record.getGid());			//比赛赛编[A队与B队比赛，唯一编号]
		detail.setLeague(record.getLeague());		//联盟名称[比赛所属联赛名称]
		detail.setTeamH(record.getTeamH());		//主队[上队]
		detail.setTeamC(record.getTeamC());		//客队[下队]
		detail.setRtypeName(SportConstant.getRTypeName("ft_f"));	//赛事类型[单式、波胆等]
		detail.setTimeType(order.getTimeType());		//大类[roll滚球、today今日赛事、tom早盘]
		detail.setMatchType(order.getBetSportType().toUpperCase());	//FT足球、BK篮球
		detail.setRtype(order.getMatchRtype());		//投注类型[r,pd等]
		detail.setBtype(order.getMatchRtype());		//盘口类型[dy独赢 rq让球 rf让分 jf球队得分 dx大小 ds单双 pd波胆 t总入球 f半场/全场]
		detail.setDtype(bet.getBtype());		//结算盘口类型
		detail.setSelection(bet.getBtype());	//投注主队还是客队[H主，C客,N平局]
		detail.setPeriod(bet.getPeriod());		//f-全场 h-半场
		detail.setBetIndex(order.getTimeType() + ":" + bet.getMatchType() + ":" + bet.getRtype() + ":" + bet.getBtype() + ":" + bet.getBtype() + ":" + bet.getPeriod());		//下注盘口描述
		detail.setBetOddsDes("半场/全场");
		//detail.setBetOdds("");		//盘口赔率
		detail.setBetOddsMinus(1);	//赔率减倍
		//detail.setBetOddsName(record.getTeamH() + "/" + record.getTeamC());	//盘口赔率盘口名称
		detail.setBetRqTeam(record.getStrong());	//让分/让球方:[H主队、C客队]
		//detail.setBetRq(t.getRatio());
		//detail.setBetRqTeamH(t.getHstrong());	//让分/让球方-上半场:[H主队、C客队]
		//detail.setBetRqH(t.getHratio());		//让分/让球-上半场:盘口
		//detail.setBetDx(t.getRatioO());		//大小球
		//detail.setBetDxH(t.getHratioO());		//大小球(半场)
		//detail.setBetScoreH("");	//主队得分
		//detail.setBetScoreC("");	//客队得分
		//detail.setBetScoreHCur("");	//当前主队比分（滚球）
		//detail.setBetScoreCCur("");	//当前客队比分（滚球）
		detail.setBetTime(nowGMT4);	//投注时间
		detail.setMatchTime(DateUtil.parse(record.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));//比赛时间
		detail.setStatus(SportConstant.ORDER_STATUS_0);		//结算状态
		detail.setBetStatus(SportConstant.BET_STATUS_0);		//注单状态
		detail.setCreateTime(now);
		detail.setModifyTime(now);
		detail.setBetVs(record.getTeamH() + "<font class='radio'>vs</font>" + record.getTeamC());
		detail.setMatchId(matchId);		//赛事编号
		detail.setBetUuid(CommonUtils.generateUUID());
		List<TWebMatchDetail> details = new ArrayList<TWebMatchDetail>();
		details.add(detail);
		order.setDetails(details);
		boolean result = this.webSportService.updateWebSportBet(order);
		if(!result){
			sendErrorMessage(request, response, "下注失败！如有疑问请与24小时在线客服联系");
			return;
		}
		initOrderParam(detail);
		sendSuccessMessage(request, response);
	}

	/**
	 * 下单成功后订单详情
	 * @param detail
	 */
	private void initOrderParam(TWebMatchDetail detail) {
		bet.setFlag(true);
		bet.setLeague(detail.getLeague());
		bet.setOrderNO(detail.getBetWagersId());
		bet.setTeamH(detail.getTeamH());
		bet.setTeamC(detail.getTeamC());
		bet.setOdds(detail.getBetOdds());
		bet.setOddsName(detail.getBetOddsName());
		bet.setOddsDes(detail.getBetOddsDes());
	}
	
	
	
	/**
	 * 控制器状态  如果不存在说明该赛事无效
	 * @return
	 */
	public TMatchControl getControllerState(String timeType,String rType){
		TMatchControl tMatchControl = webSportService.getMatchControl(timeType, rType);
		if(null != tMatchControl && SportConstant.CURSTATUS_OK == tMatchControl.getCurstatus() && SportConstant.SHOWSTATUS_OK == tMatchControl.getShowStatus()){
			return tMatchControl;
		}
		return null;
	}
	
	/**
	 * 检查金额
	 * @return
	 */
	private boolean checkMoney(double money,UserContext user){
		//余额判断
		double userMoney=webUserService.getWebUserMoney(user.getUserName());
		if(userMoney < money){
			return false;
		}
		return true;
	}
	
	/**
	 * 初始化订单参数
	 * @param request
	 */
	private void initParams(HttpServletRequest request) {
		bet = new SportBetDetail();
		
		bet.setFlag(false);//默认订单失败
		bet.setRoll(false);//默认不是滚球
		bet.setMatchType(request.getParameter("matchType").toUpperCase());
		bet.setTimeType(request.getParameter("timeType"));
		bet.setRtype(request.getParameter("rType"));
		bet.setPeriod(request.getParameter("period"));
		bet.setSelection(request.getParameter("selection"));
		bet.setBtype(request.getParameter("btype"));
		bet.setGid(request.getParameter("gid"));
		//dtype只有篮球有  如果为空取值btype
		bet.setDtype(null == request.getParameter("dtype") ? request.getParameter("btype") : request.getParameter("dtype"));
		
		/**拼接下单成功后返回url**/
		String tableName = (String) request.getSession().getAttribute("tableName");
		String demain = this.getWebDomain(request);
		demain += "m/sport/goEventDetail?";
		demain += "matchType="+bet.getMatchType().toLowerCase();
		demain += "&timeType="+bet.getTimeType();
		demain += "&rType="+bet.getRtype();
		demain += "&tableName=" + tableName;
		bet.setUrl(demain);
		
		request.getSession().removeAttribute("tableName");
		String money = null == request.getParameter("money") ? "0" : request.getParameter("money");
		if(money.endsWith(".")){
			money = money.substring(0, money.length() - 1);
		}
		bet.setMoney(Double.parseDouble(money));
	}
	
	
	/**
	 * 判断赛事是否过期 
	 * @param nowTime
	 * @param modifyTime
	 * @return
	 */
	private boolean matchDateViable(Date nowTime, Date modifyTime){
		SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName("web_sport_bet_viable_time");
		String _value = StringUtils.defaultString(sysParameter.getParamValue(), "5");
		int value = Integer.valueOf(_value);
		long divTime = nowTime.getTime() - modifyTime.getTime();
		int minuter = (int)(divTime / (60*1000));
		if(minuter > value){
			return false;
		}
		return true;
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
			if(list.size() > 0){
				tMatchRelate = list.get(0);
			}else{
				return "";
			}
		}
		return tMatchRelate.getMatchId();
	}
	/**
	 * 清空串关session
	 * @param request
	 */
	private void cleanSession(HttpServletRequest request){
		SportConstant.ft_today.clear();
		SportConstant.ft_tom.clear();
		request.getSession().removeAttribute("orderParam");
	}
}
