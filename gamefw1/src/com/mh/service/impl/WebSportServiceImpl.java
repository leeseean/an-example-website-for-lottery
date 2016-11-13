/**   
 * 文件名称: WebSportServiceImpl.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-7-14 下午7:48:43<br/>
 */
package com.mh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.commons.conf.SportConstant;
import com.mh.commons.conf.SystemConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.dao.WebAccountsDao;
import com.mh.dao.WebMatchBetDao;
import com.mh.dao.WebMatchDetailDao;
import com.mh.dao.WebMatchSettledDao;
import com.mh.dao.WebSportDao;
import com.mh.dao.WebUserDao;
import com.mh.entity.SportBet;
import com.mh.entity.SysParameter;
import com.mh.entity.TBkMatchP3;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchControl;
import com.mh.entity.TMatchRelate;
import com.mh.entity.TMatchSettled;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.TWebMatchDetail;
import com.mh.entity.WebAccounts;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUser;
import com.mh.service.SysParameterService;
import com.mh.service.WebSportService;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-14 下午7:48:43<br/>
 */

@Service
public class WebSportServiceImpl implements WebSportService {

	private static final Logger logger = LoggerFactory.getLogger(WebSportServiceImpl.class);

	@Autowired
	private WebSportDao webSportDao;

	@Autowired
	private WebUserDao webUserDao;

	@Autowired
	private WebAccountsDao webAccountsDao;

	@Autowired
	private WebMatchBetDao webMatchBetDao;

	@Autowired
	private WebMatchDetailDao webMatchDetailDao;

	@Autowired
	private WebMatchSettledDao webMatchSettledDao;

	@Autowired
	private SysParameterService sysParameterService;

	/**
	 * 获取订单列表 方法描述: TODO</br>
	 * 
	 * @param betUserName
	 * @return List<TWebMatchBet>
	 */
	public List<TWebMatchBet> getMatchBetResult(WebRecords records) {
		List<TWebMatchBet> betList = this.webMatchBetDao.getWebMatchBetListByBetName(records);
		for (int i = 0; i < betList.size(); i++) {
			TWebMatchBet bet = betList.get(i);
			List<TWebMatchDetail> detailList = this.webMatchBetDao.getWebMatchBetDetailByBetWagersId(bet.getBetWagersId());
			bet.setDetails(detailList);
		}
		return betList;
	}

	/**
	 * 获取注单列表 方法描述: TODO</br>
	 * 
	 * @param betUserName
	 * @return List<TWebMatchBet>
	 */
	public List<TWebMatchBet> getWebMatchBetListByBetName(String betUserName) {
		WebRecords record = new WebRecords();
		record.setUserName(betUserName);
		return this.webMatchBetDao.getWebMatchBetListByBetName(record);
	}

	public boolean updateWebSportBet(TWebMatchBet mbet) {
		String logStrPrefix = "体育下注" + mbet.getBetUserName();
		try {

			logger.info(logStrPrefix);
			// ** 主帐号金额 **//*

			int rows = this.webUserDao.updateWebUserForMoney(mbet.getBetUserName(), -mbet.getBetIn());
			if (rows < 1) {
				logger.info("用户余额不足！");
				return false;
			}

			WebUser webUser = this.webUserDao.findWebrUseByUserName(mbet.getBetUserName());

			// ** 财务记录 开始 **//*

			WebAccounts account = new WebAccounts();
			account.setActOptMoney(-mbet.getBetIn());// 负数
			account.setActProType(SystemConstant.project_huangguan);// 体育
			account.setActOptType(SystemConstant.bet_out);// 支出
			account.setActOrder(mbet.getBetWagersId());
			account.setActResultMoney(webUser.getUserMoney());
			account.setCreateTime(mbet.getCreateTime());
			account.setModifyTime(mbet.getModifyTime());
			account.setRemark(mbet.getRemark());
			account.setUserName(mbet.getBetUserName());
			account.setStatus(0);
			account.setGmt4Time(mbet.getOrderTime());
			account.setUserAgent(webUser.getUserAgent());

			this.webAccountsDao.saveOrUpdate(account);
			// ** 财务记录 结束 **//*

			// ** 保存投注记录 **//*
			this.webMatchBetDao.saveOrUpdate(mbet);
			// 投注记录明细
			this.webMatchDetailDao.saveOrUpdateAll(mbet.getDetails());

			String[] matchIdsS = StringUtils.split(mbet.getBetMatchIds(), ",");
			List<TMatchSettled> settleds = new ArrayList<TMatchSettled>(matchIdsS.length);
			TMatchSettled settled = null;
			Date now = new Date();
			for (String matchId : matchIdsS) {

				settled = new TMatchSettled();
				settled.setMatchId(StringUtils.trim(matchId));
				settled.setCreateTime(now);
				settled.setModifyTime(now);
				settled.setGmt4Time(DateUtil.getGMT_4_Date());
				settled.setStatus(SportConstant.RESULT_MATCH_SETTLED_0);
				settleds.add(settled);
			}
			this.webMatchSettledDao.saveOrUpdateAll(settleds);
			String code = this.webSportDao.saveOrUpdateAll(settleds);
			if ("000000".equals(code)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error("提交订单异常", e);
			throw new RuntimeException("提交订单异常");
		}

	}

	/**
	 * 
	 * 方法描述: TODO</br>
	 * 
	 * @param timeType大类型
	 *            （今日赛事、早盘、滚球）
	 * @param rtype
	 * @return TMatchControl
	 */
	public TMatchControl getMatchControl(String timeType, String rtype) {
		// return this.webSportDao.getMatchControl(timeType, rtype);
		TMatchControl control = this.webSportDao.getMatchControl(timeType, rtype);
		if (control != null) {
			SysParameter param = sysParameterService.getSysParameterByPramName("web_sport_valid_show_time");
			String[] value = param.getParamValue().split("_");
			int time = 0;
			if (StringUtils.equals(control.getTimeType(), SportConstant.TIME_TYPE_ROLL)) {
				time = Integer.parseInt(value[2]);
			} else if (StringUtils.equals(control.getTimeType(), SportConstant.TIME_TYPE_TOM)) {
				time = Integer.parseInt(value[1]);
			} else if (StringUtils.equals(control.getTimeType(), SportConstant.TIME_TYPE_TODAY)) {
				time = Integer.parseInt(value[0]);
			}
			long nowTime = new Date().getTime();
			long successTime = control.getSuccessTime().getTime();
			// logger.info(nowTime + ":" + successTime + ":" +
			// (nowTime-successTime) + ":" + (time * 1000));
			if (nowTime - successTime > (time * 1000)) {
				return null;
			}
		}
		return control;
	}

	/**
	 * 获取联赛名称 方法描述: TODO</br>
	 * 
	 * @param tableName
	 * @param curTag
	 * @return List<String>
	 */
	public List<String> getLeague(String tableName, String curTag) {
		return this.webSportDao.getLeague(tableName, curTag);
	}

	public List<String> getLeague(TMatchControl control) {
		return this.webSportDao.getLeague(control);
	}

	/**
	 * 
	 * 单式 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchR> getFtMatchR(String curTag, int curPage, String league) {
		return this.webSportDao.getFtMatchR(curTag, curPage, league);
	}

	public List<TFtMatchR> getFtMatchR(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getFtMatchR(control, curPage, league);
	}

	/**
	 * 
	 * 波胆 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchPD> getFtMatchPD(String curTag, int curPage, String league) {
		return this.webSportDao.getFtMatchPD(curTag, curPage, league);
	}

	public List<TFtMatchPD> getFtMatchPD(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getFtMatchPD(control, curPage, league);
	}

	/**
	 * 
	 * 总入球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchT> getFtMatchT(String curTag, int curPage, String league) {
		return this.webSportDao.getFtMatchT(curTag, curPage, league);
	}

	public List<TFtMatchT> getFtMatchT(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getFtMatchT(control, curPage, league);
	}

	/**
	 * 
	 * 总入球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchF> getFtMatchF(String curTag, int curPage, String league) {
		return this.webSportDao.getFtMatchF(curTag, curPage, league);
	}

	public List<TFtMatchF> getFtMatchF(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getFtMatchF(control, curPage, league);
	}

	/**
	 * 
	 * 总和过关 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchP3> getFtMatchP3(String curTag, int curPage, String league) {
		return this.webSportDao.getFtMatchP3(curTag, curPage, league);
	}

	public List<TFtMatchP3> getFtMatchP3(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getFtMatchP3(control, curPage, league);
	}

	/**
	 * 篮球 单式 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public List<TBkMatchRmain> getBkMatchRmain(String curTag, int curPage, String league) {
		return this.webSportDao.getBkMatchRmain(curTag, curPage, league);
	}

	public List<TBkMatchRmain> getBkMatchRmain(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getBkMatchRmain(control, curPage, league);
	}

	/**
	 * 篮球 综合过关 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public List<TBkMatchP3> getBkMatchP3(String curTag, int curPage, String league) {
		return this.webSportDao.getBkMatchP3(curTag, curPage, league);
	}

	public List<TBkMatchP3> getBkMatchP3(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getBkMatchP3(control, curPage, league);
	}

	/**
	 * 滚球-足球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public List<TRMatchRE> getRollMatchRE(String curTag, int curPage, String league) {
		return this.webSportDao.getRollMatchRE(curTag, curPage, league);
	}

	public List<TRMatchRE> getRollMatchRE(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getRollMatchRE(control, curPage, league);
	}

	/**
	 * 滚球-篮球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public List<TRMatchRemain> getRollMatchRemain(String curTag, int curPage, String league) {
		return this.webSportDao.getRollMatchRemain(curTag, curPage, league);
	}

	public List<TRMatchRemain> getRollMatchRemain(TMatchControl control, int curPage, String league) {
		return this.webSportDao.getRollMatchRemain(control, curPage, league);
	}

	/**
	 * 
	 * 单式 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchR getFtMatchR(String gid, String timeType) {
		return this.webSportDao.getFtMatchR(gid, timeType);
	}

	public TFtMatchR getFtMatchR(TMatchControl control, SportBet bet) {
		return this.webSportDao.getFtMatchR(control, bet);
	}

	/**
	 * 
	 * 波胆 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchPD getFtMatchPD(String gid, String timeType, String tag) {
		return this.webSportDao.getFtMatchPD(gid, timeType, tag);
	}

	public TFtMatchPD getFtMatchPD(TMatchControl control, SportBet bet, String tag) {
		return this.webSportDao.getFtMatchPD(control, bet, tag);
	}

	/**
	 * 
	 * 总入球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchT getFtMatchT(String gid, String timeType) {
		return this.webSportDao.getFtMatchT(gid, timeType);
	}

	public TFtMatchT getFtMatchT(TMatchControl control, SportBet bet) {
		return this.webSportDao.getFtMatchT(control, bet);
	}

	/**
	 * 
	 * 半场/全场 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchF getFtMatchF(String gid, String timeType) {
		return this.webSportDao.getFtMatchF(gid, timeType);
	}

	public TFtMatchF getFtMatchF(TMatchControl control, SportBet bet) {
		return this.webSportDao.getFtMatchF(control, bet);
	}

	/**
	 * 
	 * 总和过关 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchP3 getFtMatchP3(String gid, String timeType) {
		return this.webSportDao.getFtMatchP3(gid, timeType);
	}

	public TFtMatchP3 getFtMatchP3(TMatchControl control, SportBet bet) {
		return this.webSportDao.getFtMatchP3(control, bet);
	}

	/**
	 * 篮球 单式 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public TBkMatchRmain getBkMatchRmain(String gid, String timeType) {
		return this.webSportDao.getBkMatchRmain(gid, timeType);
	}

	public TBkMatchRmain getBkMatchRmain(TMatchControl control, SportBet bet) {
		return this.webSportDao.getBkMatchRmain(control, bet);
	}

	/**
	 * 篮球 综合过关 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public TBkMatchP3 getBkMatchP3(String gid, String timeType) {
		return this.webSportDao.getBkMatchP3(gid, timeType);
	}

	public TBkMatchP3 getBkMatchP3(TMatchControl control, SportBet bet) {
		return this.webSportDao.getBkMatchP3(control, bet);
	}

	/**
	 * 滚球-足球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public TRMatchRE getRollMatchRE(String gid, String timeType) {
		return this.webSportDao.getRollMatchRE(gid, timeType);
	}

	public TRMatchRE getRollMatchRE(TMatchControl control, SportBet bet) {
		return this.webSportDao.getRollMatchRE(control, bet);
	}

	/**
	 * 滚球-篮球 方法描述: TODO</br>
	 * 
	 * @param gid
	 * @return List<TBkMatchRmain>
	 */
	public TRMatchRemain getRollMatchRemain(String gid, String timeType) {
		return this.webSportDao.getRollMatchRemain(gid, timeType);
	}

	public TRMatchRemain getRollMatchRemain(TMatchControl control, SportBet bet) {
		return this.webSportDao.getRollMatchRemain(control, bet);
	}

	/**
	 * 查询赛程 方法描述: TODO</br>
	 * 
	 * @param matchTime
	 * @param teamH
	 * @param teamC
	 * @return TMatchRelate
	 */
	public TMatchRelate getTMatchRelate(String matchTime, String teamH, String teamC) {
		return this.webSportDao.getTMatchRelate(matchTime, teamH, teamC);
	}

	/**
	 * 通过Gid查询球赛</br> 创建人: zoro<br/>
	 * 
	 * @param gid
	 * @return TMatchRelate
	 */
	public TMatchRelate searchMatchByGid(String gid) {
		return this.webSportDao.searchMatchByGid(gid);
	}

	/**
	 * 通过联盟名称+主队名+客队名+日期 询球赛</br> 创建人: zoro<br/>
	 * 
	 * @param league
	 * @param teamH
	 * @param teamC
	 * @param matchDate
	 * @return List<TMatchRelate>
	 */
	public List<TMatchRelate> searchMatchForMulConditions(String league, String teamH, String teamC, String matchDate) {
		return this.webSportDao.searchMatchForMulConditions(league, teamH, teamC, matchDate);
	}

	@SuppressWarnings("rawtypes")
	public Page findSportRecordPage(Page page, WebRecords records) {
		return this.webMatchBetDao.findSportRecordPage(page, records);
	}
}
