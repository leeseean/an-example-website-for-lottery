package com.mh.web.util;

import org.apache.commons.lang3.StringUtils;

import com.mh.commons.conf.ResourceConstant;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.conf.SystemConstant;
import com.mh.entity.SportBet;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchBkLimit;
import com.mh.entity.TMatchFtLimit;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
import com.mh.entity.TWebMatchDetail;

public class MobileSportUtil
{
	/**
	 * 足球项目限额
	 * @return
	 */
	public static void limitFt(SportBet bet) {
		TMatchFtLimit limit = ResourceConstant.matchFtLimit;
		String rtype = bet.getRtype();
		String btype = bet.getBtype();
		String period = bet.getPeriod();
		String timeType = bet.getTimeType();
		if (StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_ROLL)) {
			if (StringUtils.equalsIgnoreCase(period, SportConstant.TAG_PERIOD_F)) {// 全场
				if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
					bet.setLimitBet(limit.getRollDyBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)) {// 让球
					bet.setLimitBet(limit.getRollRqBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
					bet.setLimitBet(limit.getRollDxBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds)) {// 单双
					bet.setLimitBet(limit.getRollDsBet());
				}
			} else if (StringUtils.equalsIgnoreCase(period, SportConstant.TAG_PERIOD_H)) {// 上半场
				if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
					bet.setLimitBet(limit.getRollDyHBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)) {// 让球
					bet.setLimitBet(limit.getRollRqHBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
					bet.setLimitBet(limit.getRollDxHBet());
				}
			}
		}else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_t)) {
			bet.setLimitBet(limit.gettBet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_f)) {
			bet.setLimitBet(limit.getfBet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_p3)) {
			bet.setLimitBet(limit.getP3Bet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_pd) && StringUtils.equalsIgnoreCase(period, SportConstant.TAG_PERIOD_F)) {
			bet.setLimitBet(limit.getPdBet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_hpd) && StringUtils.equalsIgnoreCase(period, SportConstant.TAG_PERIOD_H)) {
			bet.setLimitBet(limit.getPdHBet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_r)) {
			if (StringUtils.equalsIgnoreCase(period, SportConstant.TAG_PERIOD_F)) {// 全场
				if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
					bet.setLimitBet(limit.getDyBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)) {// 让球
					bet.setLimitBet(limit.getRqBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
					bet.setLimitBet(limit.getDxBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds)) {// 单双
					bet.setLimitBet(limit.getDsBet());
				}
			} else if (StringUtils.equalsIgnoreCase(period, SportConstant.TAG_PERIOD_H)) {// 上半场
				if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
					bet.setLimitBet(limit.getDyHBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)) {// 让球
					bet.setLimitBet(limit.getRqHBet());
				} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
					bet.setLimitBet(limit.getDxHBet());
				}
			}
		}
	}
	
	/**
	 * 篮球项目限额
	 * @return
	 */
	public static void limitBk(SportBet bet) {
		TMatchBkLimit limit = ResourceConstant.matchBkLimit;
		String rtype = bet.getRtype();
		String btype = bet.getBtype();
		String timeType = bet.getTimeType();
		if (StringUtils.equalsIgnoreCase(timeType, SportConstant.TIME_TYPE_ROLL)) {
			if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
				bet.setLimitBet(limit.getDyBet());
			} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rf)) {// 让分
				bet.setLimitBet(limit.getRqBet());
			} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
				bet.setLimitBet(limit.getDxBet());
			} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_jf)) {// 积分
				bet.setLimitBet(limit.getJfBet());
			}
		} else {
			if (StringUtils.equalsIgnoreCase(rtype, SportConstant.match_rtype_p3)) {// 综合过关
				bet.setLimitBet(limit.getP3Bet());
			} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
				bet.setLimitBet(limit.getDyBet());
			} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rf)) {// 让分
				bet.setLimitBet(limit.getRqBet());
			} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
				bet.setLimitBet(limit.getDxBet());
			} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_jf)) {// 积分
				bet.setLimitBet(limit.getJfBet());
			}
		}
 
	}
	
	
	public static void ft_r_order(String btype, String selection,String period, TFtMatchR t, TWebMatchDetail detail) 
	{
		if(StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)){	//独赢
			detail.setBetOddsMinus(1);
			detail.setBetVs(t.getTeamH() + " <font class='radio'> VS </font>" + t.getTeamC());
			if("f".equals(period)){
				detail.setBetOddsDes("独赢 - <font color='red'>全场</font>");
			}else{
				detail.setBetOddsDes("独赢 - <font color='red'>上半场</font>");
			}
			if (StringUtils.equalsIgnoreCase(selection, "H")){
				detail.setBetOddsName(t.getTeamH());
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorMh() : t.getIorHmh());
			}else if(StringUtils.equalsIgnoreCase(selection, "C")){
				detail.setBetOddsName(t.getTeamC());
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorMc() : t.getIorHmc());
			}else{
				detail.setBetOddsName("和局");
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorMn() : t.getIorHmn());
			}
		}else if(StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)){
			if("f".equals(period)){
				detail.setBetOddsDes("让球 - <font color='red'>全场</font>");
			}else{
				detail.setBetOddsDes("让球 - <font color='red'>上半场</font>");
			}
			String vs = StringUtils.equals(period, "f") ? t.getRatio() : t.getHratio();
			detail.setBetOddsMinus(0);
			if (StringUtils.equalsIgnoreCase(t.getStrong(), SystemConstant.team_H)) {
				detail.setBetVs(t.getTeamH() + " <font class='radio'> " + vs + " </font>" + t.getTeamC());
			} else {
				detail.setBetVs(t.getTeamC() + " <font class='radio'> " + vs + " </font>" + t.getTeamH());
			}
			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				detail.setBetOddsName(t.getTeamH());
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorRh() : t.getIorHrh());
			} else {
				detail.setBetOddsName(t.getTeamC());
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorRc() : t.getIorHrc());
			}
		}else if(StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)){	//大小
			if("f".equals(period)){
				detail.setBetOddsDes("大小 - <font color='red'>全场</font>");
			}else{
				detail.setBetOddsDes("大小 - <font color='red'>上半场</font>");
			}
			detail.setBetVs(t.getTeamH() + " <font class='radio'> VS </font>" + t.getTeamC());
			detail.setBetOddsMinus(0);
			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				detail.setBetOddsName("大" + (StringUtils.equalsIgnoreCase(period, "f") ? t.getRatioO() : t.getHratioO()));
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorOuc() : t.getIorHouc());
			} else {
				detail.setBetOddsName("小" + (StringUtils.equalsIgnoreCase(period, "f") ? t.getRatioU() : t.getHratioU()));
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorOuh() : t.getIorHouh());
			}
		}else if(StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds))	{//单双
			if("f".equals(period)){
				detail.setBetOddsDes("单双 - <font color='red'>全场</font>");
			}else{
				detail.setBetOddsDes("单双 - <font color='red'>上半场</font>");
			}
			detail.setBetOddsMinus(1);
			detail.setBetVs(t.getTeamH() + " <font class='radio'> VS </font>" + t.getTeamC());
			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				detail.setBetOddsName("单");
				detail.setBetOdds(t.getIorEoo());
			} else {
				detail.setBetOddsName("双");
				detail.setBetOdds(t.getIorEoe());
			}
		}
	}
	
	
	/**
	 * 全半场赔率
	 * @param dtype
	 * @param t
	 * @param detail
	 */
	public static void ft_f_order(String dtype, TFtMatchF t, TWebMatchDetail detail) 
	{
		detail.setBetOddsMinus(1);
		if("H_H".equals(dtype)){
			detail.setBetOdds(t.getIorFhh());
			detail.setBetOddsName(t.getTeamH() + "/" + t.getTeamH());
		}else if("H_N".equals(dtype)){
			detail.setBetOdds(t.getIorFhn());
			detail.setBetOddsName(t.getTeamH() + "/和");
		}else if("H_C".equals(dtype)){
			detail.setBetOdds(t.getIorFhc());
			detail.setBetOddsName(t.getTeamH() + "/" + t.getTeamC());
		}else if("N_H".equals(dtype)){
			detail.setBetOdds(t.getIorFnh());
			detail.setBetOddsName("和/" + t.getTeamH());
		}else if("N_N".equals(dtype)){
			detail.setBetOdds(t.getIorFnn());
			detail.setBetOddsName("和/和");
		}else if("N_C".equals(dtype)){
			detail.setBetOdds(t.getIorFnc());
			detail.setBetOddsName("和/" + t.getTeamC());
		}else if("C_H".equals(dtype)){
			detail.setBetOdds(t.getIorFch());
			detail.setBetOddsName(t.getTeamC() + "/" + t.getTeamH());
		}else if("C_N".equals(dtype)){
			detail.setBetOdds(t.getIorFcn());
			detail.setBetOddsName(t.getTeamC() + "/和");
		}else if("C_C".equals(dtype)){
			detail.setBetOdds(t.getIorFcc());
			detail.setBetOddsName(t.getTeamC() + "/" + t.getTeamC());
		}
	}
	
	public static void ft_pd_order(String dtype, TFtMatchPD t,TWebMatchDetail detail) {
		detail.setBetOddsMinus(1);	//赔率减倍
		if("1_0".equals(dtype)){
			detail.setBetOdds(t.getIorH1c0());
		}else if("2_0".equals(dtype)){
			detail.setBetOdds(t.getIorH2c0());
		}else if("2_1".equals(dtype)){
			detail.setBetOdds(t.getIorH2c1());
		}else if("3_0".equals(dtype)){
			detail.setBetOdds(t.getIorH3c0());
		}else if("3_1".equals(dtype)){
			detail.setBetOdds(t.getIorH3c1());
		}else if("3_2".equals(dtype)){
			detail.setBetOdds(t.getIorH3c2());
		}else if("4_0".equals(dtype)){
			detail.setBetOdds(t.getIorH4c0());
		}else if("4_1".equals(dtype)){
			detail.setBetOdds(t.getIorH4c1());
		}else if("4_2".equals(dtype)){
			detail.setBetOdds(t.getIorH4c2());
		}else if("4_3".equals(dtype)){
			detail.setBetOdds(t.getIorH4c3());
		}else if("0_1".equals(dtype)){
			detail.setBetOdds(t.getIorH0c1());
		}else if("0_2".equals(dtype)){
			detail.setBetOdds(t.getIorH0c2());
		}else if("1_2".equals(dtype)){
			detail.setBetOdds(t.getIorH1c2());
		}else if("0_3".equals(dtype)){
			detail.setBetOdds(t.getIorH0c3());
		}else if("1_3".equals(dtype)){
			detail.setBetOdds(t.getIorH1c3());
		}else if("2_3".equals(dtype)){
			detail.setBetOdds(t.getIorH2c3());
		}else if("0_4".equals(dtype)){
			detail.setBetOdds(t.getIorH0c4());
		}else if("1_4".equals(dtype)){
			detail.setBetOdds(t.getIorH1c4());
		}else if("2_4".equals(dtype)){
			detail.setBetOdds(t.getIorH2c4());
		}else if("3_4".equals(dtype)){
			detail.setBetOdds(t.getIorH3c4());
		}else if("0_0".equals(dtype)){
			detail.setBetOdds(t.getIorH0c0());
		}else if("1_1".equals(dtype)){
			detail.setBetOdds(t.getIorH1c1());
		}else if("2_2".equals(dtype)){
			detail.setBetOdds(t.getIorH2c2());
		}else if("3_3".equals(dtype)){
			detail.setBetOdds(t.getIorH3c3());
		}else if("4_4".equals(dtype)){
			detail.setBetOdds(t.getIorH4c4());
		}else if("other".equals(dtype)){
			detail.setBetOdds(t.getIorOvh());
		}
	}
	
	
	public static void ft_t_order(String dtype, TFtMatchT t, TWebMatchDetail detail) {
		detail.setBetOddsMinus(1);
		if("0_1".equals(dtype)){
			detail.setBetOdds(t.getIorT01());
		}else if("2_3".equals(dtype)){
			detail.setBetOdds(t.getIorT23());
		}else if("4_6".equals(dtype)){
			detail.setBetOdds(t.getIorT46());
		}else if("7up".equals(dtype)){
			detail.setBetOdds(t.getIorOver());
		}
	}
	
	public static void ft_re_order(String btype, String selection,String period, TRMatchRE t, TWebMatchDetail detail) {
		if(StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)){	//独赢
			detail.setBetOddsMinus(1);
			detail.setBetVs(t.getTeamH() + " <font class='radio'> VS </font>" + t.getTeamC());
			if("f".equals(period)){
				detail.setBetOddsDes("独赢 - <font color='red'>全场</font>");
			}else{
				detail.setBetOddsDes("独赢 - <font color='red'>上半场</font>");
			}
			if (StringUtils.equalsIgnoreCase(selection, "H")) {	//下注主队
				detail.setBetOddsName(t.getTeamH());
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorMh() : t.getIorHmh());
			}else if(StringUtils.equalsIgnoreCase(selection, "C")){
				detail.setBetOddsName(t.getTeamC());
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorMc() : t.getIorHmc());
			}else{
				detail.setBetOddsName("和局");
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorMn() : t.getIorHmn());
			}
		}else if(StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)){	//让球
			if("f".equals(period)){
				detail.setBetOddsDes("让球 - <font color='red'>全场</font>");
			}else{
				detail.setBetOddsDes("让球 - <font color='red'>上半场</font>");
			}
			String vs = StringUtils.equals(period, "f") ? t.getRatio() : t.getHratio();
			detail.setBetOddsMinus(0);
			if (StringUtils.equalsIgnoreCase(t.getStrong(), SystemConstant.team_H)) {
				detail.setBetVs(t.getTeamH() + " <font class='radio'> " + vs + " </font>" + t.getTeamC());
			} else {
				detail.setBetVs(t.getTeamC() + " <font class='radio'> " + vs + " </font>" + t.getTeamH());
			}
			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				detail.setBetOddsName(t.getTeamH());
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorRh() : t.getIorHrh());
			} else {
				detail.setBetOddsName(t.getTeamC());
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorRc() : t.getIorHrc());
			}
		}else if(StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)){	//大小
			if("f".equals(period)){
				detail.setBetOddsDes("大小 - <font color='red'>全场</font>");
			}else{
				detail.setBetOddsDes("大小 - <font color='red'>上半场</font>");
			}
			detail.setBetVs(t.getTeamH() + " <font class='radio'> VS </font>" + t.getTeamC());
			detail.setBetOddsMinus(0);
			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				detail.setBetOddsName("大" + (StringUtils.equalsIgnoreCase(period, "f") ? t.getRatioO() : t.getHratioO()));
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ?  t.getIorOuc() :  t.getIorHouc());
			}else {
				detail.setBetOddsName("小" + (StringUtils.equalsIgnoreCase(period, "f") ? t.getRatioU() : t.getHratioU()));
				detail.setBetOdds(StringUtils.equalsIgnoreCase(period, "f") ? t.getIorOuh() : t.getIorHouh());
			}
		}else if(StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds)){	//单双
			if("f".equals(period)){
				detail.setBetOddsDes("单双 - <font color='red'>全场</font>");
			}else{
				detail.setBetOddsDes("单双 - <font color='red'>上半场</font>");
			}
			detail.setBetOddsMinus(1);
			detail.setBetVs(t.getTeamH() + " <font class='radio'> VS </font>" + t.getTeamC());
			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				detail.setBetOddsName("单");
				detail.setBetOdds(t.getIorEoo());
			} else {
				detail.setBetOddsName("双");
				detail.setBetOdds(t.getIorEoe());
			}
		}
	}
	
	public static void bk_r_main_order(String btype,String dtype ,String selection, TBkMatchRmain t,TWebMatchDetail detail) {
		if("dy".equals(btype)){
			detail.setBetVs(t.getTeamH() + " <font class='radio'>vs</font> " + t.getTeamC());
			detail.setBetOddsMinus(1);
			detail.setBetOddsDes("独赢");
			//独赢
			if("H".equals(selection)){
				//主队
				detail.setBetOdds(t.getIorMh());
				detail.setBetOddsName(t.getTeamH());
				detail.setBetDx(t.getRatioU());
			}else{
				//客队
				detail.setBetOdds(t.getIorMc());
				detail.setBetOddsName(t.getTeamC());
				detail.setBetDx(t.getRatioO());
			}
		}else if("rf".equals(btype)){
			detail.setBetOddsDes("让分");
			detail.setBetOddsMinus(0);
			if("H".equals(t.getStrong())){
				detail.setBetVs(t.getTeamH() + " <font class='radio'>"+t.getRatio()+"</font> " + t.getTeamC());
			}else{
				detail.setBetVs(t.getTeamC() + " <font class='radio'>"+t.getRatio()+"</font> " + t.getTeamH());
			}
			//让分
			if("H".equals(selection)){
				detail.setBetOddsName(t.getTeamH());
				detail.setBetOdds(t.getIorRh());
				detail.setBetDx(t.getRatioU());
			}else{
				detail.setBetOddsName(t.getTeamC());
				detail.setBetOdds(t.getIorRc());
				detail.setBetDx(t.getRatioO());
			}
		}else if("dx".equals(btype)){
			detail.setBetOddsMinus(0);
			detail.setBetVs(t.getTeamH() + " <font class='radio'>vs</font> " + t.getTeamC());
			//大小
			detail.setBetOddsDes("大小");
			if("H".equals(selection)){
				detail.setBetOddsName("大" + t.getRatioO());
				detail.setBetOdds(t.getIorOuc());
				detail.setBetDx(t.getRatioU());
			}else{
				detail.setBetOddsName("小" + t.getRatioU());
				detail.setBetOdds(t.getIorOuh());
				detail.setBetDx(t.getRatioO());
			}
		}else if("jf".equals(btype)){
			detail.setBetVs(t.getTeamH() + " <font class='radio'>vs</font> " + t.getTeamC());
			detail.setBetOddsMinus(0);
			if("H".equals(selection)){
				detail.setBetOddsDes("球队得分：" + t.getTeamH() + " - 大/小");
				if(SystemConstant.BK_RF_TEAM_BIG.equals(dtype)){
					detail.setBetOddsName("大" + t.getRatioOuho());
					detail.setBetOdds(t.getIorOuho());
					detail.setBetDx(t.getRatioOuho());
				}else{
					detail.setBetOddsName("小" + t.getRatioOuhu());
					detail.setBetOdds(t.getIorOuhu());
					detail.setBetDx(t.getRatioOuhu());
				}
			}else{
				detail.setBetOddsDes("球队得分：" + t.getTeamC() + " - 大/小");
				if(SystemConstant.BK_RF_TEAM_BIG.equals(dtype)){
					detail.setBetOddsName("大" + t.getRatioOuco());
					detail.setBetOdds(t.getIorOuco());
					detail.setBetDx(t.getRatioOuco());
				}else{
					detail.setBetOddsName("小" + t.getRatioOucu());
					detail.setBetOdds(t.getIorOucu());
					detail.setBetDx(t.getRatioOucu());
				}
			}
		}
	}
	
	public static void bk_re_main_order(String btype,String dtype, String selection, TRMatchRemain t,TWebMatchDetail detail) {
		if("dy".equals(btype)){
			detail.setBetVs(t.getTeamH() + " <font class='radio'>vs</font> " + t.getTeamC());
			detail.setBetOddsMinus(1);
			detail.setBetOddsDes("独赢");
			//独赢
			if("H".equals(selection)){
				//主队
				detail.setBetOdds(t.getIorMh());
				detail.setBetOddsName(t.getTeamH());
				detail.setBetDx(t.getRatioU());
			}else{
				//客队
				detail.setBetOdds(t.getIorMc());
				detail.setBetOddsName(t.getTeamC());
				detail.setBetDx(t.getRatioO());
			}
		}else if("rf".equals(btype)){
			detail.setBetOddsDes("让分");
			detail.setBetOddsMinus(0);
			if("H".equals(t.getStrong())){
				detail.setBetVs(t.getTeamH() + " <font class='radio'>"+t.getRatio()+"</font> " + t.getTeamC());
			}else{
				detail.setBetVs(t.getTeamC() + " <font class='radio'>"+t.getRatio()+"</font> " + t.getTeamH());
			}
			//让分
			if("H".equals(selection)){
				detail.setBetOddsName(t.getTeamH());
				detail.setBetOdds(t.getIorRh());
				detail.setBetDx(t.getRatioU());
			}else{
				detail.setBetOddsName(t.getTeamC());
				detail.setBetOdds(t.getIorRc());
				detail.setBetDx(t.getRatioO());
			}
		}else if("dx".equals(btype)){
			detail.setBetOddsMinus(0);
			detail.setBetVs(t.getTeamH() + " <font class='radio'>vs</font> " + t.getTeamC());
			//大小
			detail.setBetOddsDes("大小");
			if("H".equals(selection)){
				detail.setBetOddsName("大" + t.getRatioO());
				detail.setBetOdds(t.getIorOuh());
				detail.setBetDx(t.getRatioU());
			}else{
				detail.setBetOddsName("小" + t.getRatioU());
				detail.setBetOdds(t.getIorOuc());
				detail.setBetDx(t.getRatioO());
			}
		}else if("jf".equals(btype)){
			detail.setBetVs(t.getTeamH() + " <font class='radio'>vs</font> " + t.getTeamC());
			detail.setBetOddsMinus(0);
			if("H".equals(selection)){
				detail.setBetOddsDes("球队得分：" + t.getTeamH() + " - 大/小");
				if(SystemConstant.BK_RF_TEAM_BIG.equals(dtype)){
					detail.setBetOddsName("大" + t.getRatioOuho());
					detail.setBetDx(t.getRatioOuho());
					detail.setBetOdds(t.getIorOuho());
				}else{
					detail.setBetOddsName("小" + t.getRatioOuhu());
					detail.setBetOdds(t.getIorOuhu());
					detail.setBetDx(t.getRatioOuhu());
				}
			}else{
				detail.setBetOddsDes("球队得分：" + t.getTeamC() + " - 大/小");
				if(SystemConstant.BK_RF_TEAM_BIG.equals(dtype)){
					detail.setBetOddsName("大" + t.getRatioOuco());
					detail.setBetOdds(t.getIorOuco());
					detail.setBetDx(t.getRatioOuco());
				}else{
					detail.setBetOddsName("小" + t.getRatioOucu());
					detail.setBetOdds(t.getIorOucu());
					detail.setBetDx(t.getRatioOucu());
				}
			}
		}
	}
}
