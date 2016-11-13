/**   
* 文件名称: SportBetUtil.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-16 下午2:07:56<br/>
*/  
package com.mh.web.util;

import org.apache.commons.lang3.StringUtils;

import com.mh.commons.conf.SportConstant;
import com.mh.commons.conf.SystemConstant;
import com.mh.entity.SportBet;
import com.mh.entity.TBkMatchP3;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchBkLimit;
import com.mh.entity.TMatchFtLimit;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
import com.mh.web.job.CodeDataHelper;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-16 下午2:07:56<br/>
 */
public class SportBetUtil {
	
	public static void initSportBet_R(SportBet bet, TFtMatchR record) {
		String btype = bet.getBtype();
		String selection = bet.getSelection();
		String period = bet.getPeriod();
		if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {// 下注主队
				bet.setOddsName(record.getTeamH());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMh() : record.getIorHmh());// 全场/上半场
			} else if (StringUtils.equalsIgnoreCase(selection, "C")) {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMc() : record.getIorHmc());// 全场/上半场
			} else {// 和
				bet.setOddsName("和局");
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMn() : record.getIorHmn());// 全场/上半场
			}

			bet.setOddsMinus("1");

		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)) {// 让球
			bet.setVs(StringUtils.equals(period, "f") ? record.getRatio() : record.getHratio());// 让球数
			if (StringUtils.equalsIgnoreCase(record.getStrong(), SystemConstant.team_H)) {// 让球方:主队
				bet.setTeam1(record.getTeamH());
				bet.setTeam2(record.getTeamC());
			} else {
				bet.setTeam1(record.getTeamC());
				bet.setTeam2(record.getTeamH());
			}

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName(record.getTeamH());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorRh() : record.getIorHrh());// 全场/上半场
			} else {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorRc() : record.getIorHrc());// 全场/上半场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("大" + (StringUtils.equalsIgnoreCase(period, "f") ? record.getRatioO() : record.getHratioO()));
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorOuc() : record.getIorHouc());// 全场/上半场
			} else {
				bet.setOddsName("小" + (StringUtils.equalsIgnoreCase(period, "f") ? record.getRatioU() : record.getHratioU()));
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorOuh() : record.getIorHouh());// 全场/上半场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds)) {// 单双
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("单");
				bet.setOdds(record.getIorEoo());
			} else {
				bet.setOddsName("双");
				bet.setOdds(record.getIorEoe());
			}

			bet.setOddsMinus("1");

		}

		bet.setOddsDes(bet.getOddsDes() + " - <font color=\"red\">" + (StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? "全场" : "上半场") + "</font>");

	}

	public static void initSportBet_PD(SportBet bet, TFtMatchPD record) {
		String btype = bet.getBtype();
		String dtype = bet.getDtype();

		bet.setVs("vs");
		bet.setTeam1(record.getTeamH());
		bet.setTeam2(record.getTeamC());

		if (!StringUtils.equalsIgnoreCase(dtype, "other")) {
			bet.setOddsName(dtype.replace("_", "-"));
		} else {
			bet.setOddsName("其他比分");
		}

		if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_1_0)) {
			bet.setOdds(record.getIorH1c0());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_2_0)) {
			bet.setOdds(record.getIorH2c0());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_2_1)) {
			bet.setOdds(record.getIorH2c1());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_3_0)) {
			bet.setOdds(record.getIorH3c0());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_3_1)) {
			bet.setOdds(record.getIorH3c1());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_3_2)) {
			bet.setOdds(record.getIorH3c2());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_4_0)) {
			bet.setOdds(record.getIorH4c0());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_4_1)) {
			bet.setOdds(record.getIorH4c1());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_4_2)) {
			bet.setOdds(record.getIorH4c2());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_4_3)) {
			bet.setOdds(record.getIorH4c3());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_0_0)) {
			bet.setOdds(record.getIorH0c0());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_1_1)) {
			bet.setOdds(record.getIorH1c1());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_2_2)) {
			bet.setOdds(record.getIorH2c2());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_3_3)) {
			bet.setOdds(record.getIorH3c3());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_4_4)) {
			bet.setOdds(record.getIorH4c4());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_other)) {
			bet.setOdds(record.getIorOvh());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_0_1)) {
			bet.setOdds(record.getIorH0c1());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_0_2)) {
			bet.setOdds(record.getIorH0c2());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_1_2)) {
			bet.setOdds(record.getIorH1c2());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_0_3)) {
			bet.setOdds(record.getIorH0c3());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_1_3)) {
			bet.setOdds(record.getIorH1c3());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_2_3)) {
			bet.setOdds(record.getIorH2c3());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_0_4)) {
			bet.setOdds(record.getIorH0c4());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_1_4)) {
			bet.setOdds(record.getIorH1c4());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_pd_3_4)) {
			bet.setOdds(record.getIorH3c4());
		}

		bet.setOddsMinus("1");
		bet.setOddsDes(CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SystemConstant.MATCH_ODDS_CONS, btype));
		bet.setOddsDes(bet.getOddsDes() + " - <font color=\"red\">" + (StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? "全场" : "上半场") + "</font>");

	}

	public static void initSportBet_T(SportBet bet, TFtMatchT record) {
		String btype = bet.getBtype();
		String dtype = bet.getDtype();

		bet.setVs("vs");
		bet.setTeam1(record.getTeamH());
		bet.setTeam2(record.getTeamC());

		if (!StringUtils.equalsIgnoreCase(dtype, "7up")) {
			bet.setOddsName(dtype.replace("_", "-"));
		} else {
			bet.setOddsName("7或以上");
		}

		if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_t_0_1)) {
			bet.setOdds(record.getIorT01());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_t_2_3)) {
			bet.setOdds(record.getIorT23());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_t_4_6)) {
			bet.setOdds(record.getIorT46());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_t_7up)) {
			bet.setOdds(record.getIorOver());
		}

		bet.setOddsMinus("1");
		bet.setOddsDes(CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SystemConstant.MATCH_ODDS_CONS, btype));
		bet.setOddsDes(bet.getOddsDes() + " - <font color=\"red\">" + (StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? "全场" : "上半场") + "</font>");

	}

	public  static void initSportBet_F(SportBet bet, TFtMatchF record) {
		String btype = bet.getBtype();
		String dtype = bet.getDtype();

		bet.setVs("vs");
		bet.setTeam1(record.getTeamH());
		bet.setTeam2(record.getTeamC());

		String oddsName = dtype;
		oddsName = oddsName.replace("H", record.getTeamH());
		oddsName = oddsName.replace("C", record.getTeamC());
		oddsName = oddsName.replace("N", "和");
		oddsName = oddsName.replace("_", " / ");

		bet.setOddsName(oddsName);

		if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_H_H)) {
			bet.setOdds(record.getIorFhh());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_H_N)) {
			bet.setOdds(record.getIorFhn());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_H_C)) {
			bet.setOdds(record.getIorFhc());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_N_H)) {
			bet.setOdds(record.getIorFnh());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_N_N)) {
			bet.setOdds(record.getIorFnn());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_N_C)) {
			bet.setOdds(record.getIorFnc());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_C_H)) {
			bet.setOdds(record.getIorFch());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_C_N)) {
			bet.setOdds(record.getIorFcn());
		} else if (StringUtils.equalsIgnoreCase(dtype, SystemConstant.ODDS_f_C_C)) {
			bet.setOdds(record.getIorFcc());
		}

		bet.setOddsMinus("1");
		bet.setOddsDes(CodeDataHelper.getCodeShowNameByCodeTypeAndCodeName(SystemConstant.MATCH_ODDS_CONS, btype));
	}

	public static void initSportBet_P3(SportBet bet, TFtMatchP3 record) {
		String btype = bet.getBtype();
		String selection = bet.getSelection();
		String period = bet.getPeriod();
		if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {// 下注主队
				bet.setOddsName(record.getTeamH());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMh() : record.getIorHmh());// 全场/上半场
			} else if (StringUtils.equalsIgnoreCase(selection, "C")) {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMc() : record.getIorHmc());// 全场/上半场
			} else {// 和
				bet.setOddsName("和局");
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMn() : record.getIorHmn());// 全场/上半场
			}

		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)) {// 让球
			bet.setVs(StringUtils.equals(period, "f") ? record.getRatio() : record.getHratio());// 让球数
			if (StringUtils.equalsIgnoreCase(record.getStrong(), SystemConstant.team_H)) {// 让球方:主队
				bet.setTeam1(record.getTeamH());
				bet.setTeam2(record.getTeamC());
			} else {
				bet.setTeam1(record.getTeamC());
				bet.setTeam2(record.getTeamH());
			}

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName(record.getTeamH());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorPrh() : record.getIorHprh());// 全场/上半场
			} else {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorPrc() : record.getIorHprc());// 全场/上半场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("大" + (StringUtils.equalsIgnoreCase(period, "f") ? record.getRatioO() : record.getHratioO()));
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorPouc() : record.getIorHpouc());// 全场/上半场
			} else {
				bet.setOddsName("小" + (StringUtils.equalsIgnoreCase(period, "f") ? record.getRatioU() : record.getHratioU()));
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorPouh() : record.getIorHpouh());// 全场/上半场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds)) {// 单双
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("单");
				bet.setOdds(record.getIorPeoo());
			} else {
				bet.setOddsName("双");
				bet.setOdds(record.getIorPeoe());
			}

		}

		bet.setOddsDes(bet.getOddsDes() + " - <font color=\"red\">" + (StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? "全场" : "上半场") + "</font>");

	}

	public static void initSportBet_BK_Rmain(SportBet bet, TBkMatchRmain record) {
		String btype = bet.getBtype();
		String selection = bet.getSelection();
		if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {// 下注主队
				bet.setOddsName(record.getTeamH());
				bet.setOdds(record.getIorMh());// 全场
			} else if (StringUtils.equalsIgnoreCase(selection, "C")) {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(record.getIorMc());// 全场
			} else {// 和
				bet.setOddsName("和局");
				bet.setOdds(record.getIorMn());// 全场
			}

			bet.setOddsMinus("1");

		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rf)) {// 让分
			bet.setVs(record.getRatio());// 让分
			if (StringUtils.equalsIgnoreCase(record.getStrong(), SystemConstant.team_H)) {// 让球方:主队
				bet.setTeam1(record.getTeamH());
				bet.setTeam2(record.getTeamC());
			} else {
				bet.setTeam1(record.getTeamC());
				bet.setTeam2(record.getTeamH());
			}

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName(record.getTeamH());
				bet.setOdds(record.getIorRh());// 全场
			} else {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(record.getIorRc());// 全场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("大" + record.getRatioO());
				bet.setOdds(record.getIorOuc());// 全场
			} else {
				bet.setOddsName("小" + record.getRatioU());
				bet.setOdds(record.getIorOuh());// 全场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_jf)) {// 球队积分
			// 大/小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsDes("球队得分：" + record.getTeamH() + " - 大/小");

				if (StringUtils.equalsIgnoreCase(bet.getDtype(), SystemConstant.BK_RF_TEAM_BIG)) {
					bet.setOddsName("大" + record.getRatioOuho());
					bet.setOdds(record.getIorOuho());// 
				} else if (StringUtils.equalsIgnoreCase(bet.getDtype(), SystemConstant.BK_RF_TEAM_SMALL)) {
					bet.setOddsName("小" + record.getRatioOuhu());
					bet.setOdds(record.getIorOuhu());//
				}
			} else {
				bet.setOddsDes("球队得分：" + record.getTeamC() + " - 大/小");

				if (StringUtils.equalsIgnoreCase(bet.getDtype(), SystemConstant.BK_RF_TEAM_BIG)) {
					bet.setOddsName("大" + record.getRatioOuco());
					bet.setOdds(record.getIorOuco());// 
				} else if (StringUtils.equalsIgnoreCase(bet.getDtype(), SystemConstant.BK_RF_TEAM_SMALL)) {
					bet.setOddsName("小" + record.getRatioOucu());
					bet.setOdds(record.getIorOucu());//
				}
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds)) {// 单双
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("单");
				bet.setOdds(record.getIorEoo());
			} else {
				bet.setOddsName("双");
				bet.setOdds(record.getIorEoe());
			}

			bet.setOddsMinus("1");

		}
	}

	public static void initSportBet_BK_P3(SportBet bet, TBkMatchP3 record) {
		String btype = bet.getBtype();
		String selection = bet.getSelection();
		if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {// 下注主队
				bet.setOddsName(record.getTeamH());
				bet.setOdds(record.getIorMh());// 全场
			} else if (StringUtils.equalsIgnoreCase(selection, "C")) {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(record.getIorMc());// 全场
			}
			bet.setOddsMinus("1");

		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rf)) {// 让分
			bet.setVs(record.getRatio());// 让分数
			if (StringUtils.equalsIgnoreCase(record.getStrong(), SystemConstant.team_H)) {// 让分方:主队
				bet.setTeam1(record.getTeamH());
				bet.setTeam2(record.getTeamC());
			} else {
				bet.setTeam1(record.getTeamC());
				bet.setTeam2(record.getTeamH());
			}

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName(record.getTeamH());
				bet.setOdds(record.getIorPrh());// 全场
			} else {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(record.getIorPrc());// 全场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("大" + record.getRatioO());
				bet.setOdds(record.getIorPouc());// 全场
			} else {
				bet.setOddsName("小" + record.getRatioU());
				bet.setOdds(record.getIorPouh());// 全场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_jf)) {// 球队积分
			// 大/小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsDes("球队得分：" + record.getTeamH() + " - 大/小");

				if (StringUtils.equalsIgnoreCase(bet.getDtype(), "dx_big")) {
					bet.setOddsName("大" + record.getRatioPouho());
					bet.setOdds(record.getIorPouho());// 
				} else if (StringUtils.equalsIgnoreCase(bet.getDtype(), "dx_small")) {
					bet.setOddsName("小" + record.getRatioPouhu());
					bet.setOdds(record.getIorPouhu());//
				}
			} else {
				bet.setOddsDes("球队得分：" + record.getTeamC() + " - 大/小");

				if (StringUtils.equalsIgnoreCase(bet.getDtype(), "dx_big")) {
					bet.setOddsName("大" + record.getRatioPouco());
					bet.setOdds(record.getIorPouco());// 
				} else if (StringUtils.equalsIgnoreCase(bet.getDtype(), "dx_small")) {
					bet.setOddsName("小" + record.getRatioPoucu());
					bet.setOdds(record.getIorPoucu());//
				}
			}
		}
	}

	public static void initSportBet_RE(SportBet bet, TRMatchRE record) {
		String btype = bet.getBtype();
		String selection = bet.getSelection();
		String period = bet.getPeriod();
		if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {// 下注主队
				bet.setOddsName(record.getTeamH());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMh() : record.getIorHmh());// 全场/上半场
			} else if (StringUtils.equalsIgnoreCase(selection, "C")) {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMc() : record.getIorHmc());// 全场/上半场
			} else {// 和
				bet.setOddsName("和局");
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorMn() : record.getIorHmn());// 全场/上半场
			}

			bet.setOddsMinus("1");

		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rq)) {// 让球
			bet.setVs(StringUtils.equals(period, "f") ? record.getRatio() : record.getHratio());// 让球数
			if (StringUtils.equalsIgnoreCase(record.getStrong(), SystemConstant.team_H)) {// 让球方:主队
				bet.setTeam1(record.getTeamH());
				bet.setTeam2(record.getTeamC());
			} else {
				bet.setTeam1(record.getTeamC());
				bet.setTeam2(record.getTeamH());
			}

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName(record.getTeamH());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorRh() : record.getIorHrh());// 全场/上半场
			} else {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorRc() : record.getIorHrc());// 全场/上半场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("大" + (StringUtils.equalsIgnoreCase(period, "f") ? record.getRatioO() : record.getHratioO()));
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorOuc() : record.getIorHouc());// 全场/上半场
			} else {
				bet.setOddsName("小" + (StringUtils.equalsIgnoreCase(period, "f") ? record.getRatioU() : record.getHratioU()));
				bet.setOdds(StringUtils.equalsIgnoreCase(period, "f") ? record.getIorOuh() : record.getIorHouh());// 全场/上半场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds)) {// 单双
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("单");
				bet.setOdds(record.getIorEoo());
			} else {
				bet.setOddsName("双");
				bet.setOdds(record.getIorEoe());
			}

			bet.setOddsMinus("1");

		}

		bet.setOddsDes(bet.getOddsDes() + " - <font color=\"red\">" + (StringUtils.equalsIgnoreCase(bet.getPeriod(), "f") ? "全场" : "上半场") + "</font>");

	}

	public static void initSportBet_REMAIN(SportBet bet, TRMatchRemain record) {
		String btype = bet.getBtype();
		String selection = bet.getSelection();
		if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dy)) {// 独赢
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {// 下注主队
				bet.setOddsName(record.getTeamH());
				bet.setOdds(record.getIorMh());// 全场
			} else if (StringUtils.equalsIgnoreCase(selection, "C")) {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(record.getIorMc());// 全场
			} 

			bet.setOddsMinus("1");

		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_rf)) {// 让分
			bet.setVs(record.getRatio());// 让分
			if (StringUtils.equalsIgnoreCase(record.getStrong(), SystemConstant.team_H)) {// 让球方:主队
				bet.setTeam1(record.getTeamH());
				bet.setTeam2(record.getTeamC());
			} else {
				bet.setTeam1(record.getTeamC());
				bet.setTeam2(record.getTeamH());
			}

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName(record.getTeamH());
				bet.setOdds(record.getIorRh());// 全场
			} else {
				bet.setOddsName(record.getTeamC());
				bet.setOdds(record.getIorRc());// 全场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_dx)) {// 大小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("大" + record.getRatioO());
				bet.setOdds(record.getIorOuc());// 全场
			} else {
				bet.setOddsName("小" + record.getRatioU());
				bet.setOdds(record.getIorOuh());// 全场
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_jf)) {// 球队积分
			// 大/小
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsDes("球队得分：" + record.getTeamH() + " - 大/小");

				if (StringUtils.equalsIgnoreCase(bet.getDtype(), SystemConstant.BK_RF_TEAM_BIG)) {
					bet.setOddsName("大" + record.getRatioOuho());
					bet.setOdds(record.getIorOuho());// 
				} else if (StringUtils.equalsIgnoreCase(bet.getDtype(), SystemConstant.BK_RF_TEAM_SMALL)) {
					bet.setOddsName("小" + record.getRatioOuhu());
					bet.setOdds(record.getIorOuhu());//
				}
			} else {
				bet.setOddsDes("球队得分：" + record.getTeamC() + " - 大/小");

				if (StringUtils.equalsIgnoreCase(bet.getDtype(), SystemConstant.BK_RF_TEAM_BIG)) {
					bet.setOddsName("大" + record.getRatioOuco());
					bet.setOdds(record.getIorOuco());// 
				} else if (StringUtils.equalsIgnoreCase(bet.getDtype(), SystemConstant.BK_RF_TEAM_SMALL)) {
					bet.setOddsName("小" + record.getRatioOucu());
					bet.setOdds(record.getIorOucu());//
				}
			}
		} else if (StringUtils.equalsIgnoreCase(btype, SystemConstant.btype_ds)) {// 单双
			bet.setVs("vs");
			bet.setTeam1(record.getTeamH());
			bet.setTeam2(record.getTeamC());

			if (StringUtils.equalsIgnoreCase(selection, "H")) {
				bet.setOddsName("单");
				bet.setOdds(record.getIorEoo());
			} else {
				bet.setOddsName("双");
				bet.setOdds(record.getIorEoe());
			}

			bet.setOddsMinus("1");

		}
	}
	
	
	
	
	
	
	public static void limitBk(SportBet bet,TMatchBkLimit limit) {
		 
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

	public static void limitFt(SportBet bet,TMatchFtLimit limit) {
		 
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
		}

		else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_t)) {
			bet.setLimitBet(limit.gettBet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_f)) {
			bet.setLimitBet(limit.getfBet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_p3)) {
			bet.setLimitBet(limit.getP3Bet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_pd) && StringUtils.equalsIgnoreCase(period, SportConstant.TAG_PERIOD_F)) {
			bet.setLimitBet(limit.getPdBet());
		} else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_pd) && StringUtils.equalsIgnoreCase(period, SportConstant.TAG_PERIOD_H)) {
			bet.setLimitBet(limit.getPdHBet());
		}

		else if (StringUtils.equalsIgnoreCase(rtype, SportConstant.ft_rtype_r)) {
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
	 

}
