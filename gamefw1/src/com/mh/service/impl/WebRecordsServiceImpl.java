/**   
 * 文件名称: WebRecordsServiceImpl.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: zoro<br/>  
 * 创建时间 : 2015-7-2 下午2:56:27<br/>
 */
package com.mh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.constants.AgConstants;
import com.mh.commons.orm.Page;
import com.mh.dao.WebDamaDao;
import com.mh.dao.WebRecordsDao;
import com.mh.entity.SportScore;
import com.mh.entity.TBetRecordAg;
import com.mh.entity.TBetRecordMg;
import com.mh.entity.TBetRecordOs;
import com.mh.entity.TBetRecordPt;
import com.mh.entity.TWebBankHuikuan;
import com.mh.entity.TWebDama;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.TWebMatchDetail;
import com.mh.entity.WebEdu;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUserWithdraw;
import com.mh.service.WebRecordService;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-7-2 下午2:56:27<br/>
 */
@SuppressWarnings("all")
@Service
public class WebRecordsServiceImpl implements WebRecordService {

	@Autowired
	private WebRecordsDao webRecordsDao;

	@Autowired
	private WebDamaDao webDamaDao;

	/**
	 * 
	 * 查询财务记录 方法描述: TODO</br>
	 * 
	 * @param page
	 * @param records
	 * @return Page
	 */
	public Page findFinancePage(Page page, WebRecords records) {
		return this.webRecordsDao.findFinancePage(page, records);
	}

	/**
	 * 注单记录 方法描述: TODO</br>
	 * 
	 * @param page
	 * @param records
	 * @return Page
	 */
	public Page findPage(Page page, WebRecords records) {
		return this.webRecordsDao.findRecordsPage(page, records);
	}

	public List<Map<String, Object>> btReport(WebRecords records) {
		List<Map<String, Object>> list1 = webRecordsDao.report(records);
		return list1;
	}

	public Page<TWebMatchBet> findPageForSport(Page<TWebMatchBet> page, WebRecords records) {
		Page<TWebMatchBet> p = webRecordsDao.pageForSportBet(page, records);
		List<TWebMatchDetail> details = webRecordsDao.listForMatchDetail(records);

		Map<String, List<TWebMatchDetail>> map = new HashMap<String, List<TWebMatchDetail>>();
		for (int i = 0; i < details.size(); i++) {
			TWebMatchDetail matchDetail = details.get(i);
			String betWagerId = matchDetail.getBetWagersId();
			if (map.containsKey(betWagerId)) {
				map.get(betWagerId).add(matchDetail);
			} else {
				List<TWebMatchDetail> bets = new ArrayList<TWebMatchDetail>();
				bets.add(matchDetail);
				map.put(betWagerId, bets);
			}
		}

		List<TWebMatchBet> list = p.getResult();
		for (int j = 0; j < list.size(); j++) {
			TWebMatchBet bet = list.get(j);
			bet.setDetails(map.get(bet.getBetWagersId()));
		}
		return p;
	}

	public TWebDama findWebDama(TWebDama dama) {
		List<TWebDama> damas = this.webDamaDao.findWebDama(dama);
		TWebDama webDama = null;
		if (null != damas && damas.size() > 0) {
			webDama = damas.get(0);
		}
		return webDama;
	}

	public WebRecordsDao getWebRecordsDao() {
		return webRecordsDao;
	}

	public void setWebRecordsDao(WebRecordsDao webRecordsDao) {
		this.webRecordsDao = webRecordsDao;
	}

	public WebDamaDao getWebDamaDao() {
		return webDamaDao;
	}

	public void setWebDamaDao(WebDamaDao webDamaDao) {
		this.webDamaDao = webDamaDao;
	}

	public Map<String, Object> selectCountBetOrder(WebRecords records) {
		return this.webRecordsDao.selectCountBetOrder(records);
	}

	public List<TWebMatchBet> findListForSport(WebRecords records) {
		List<TWebMatchBet> list = webRecordsDao.listSportBets(records);
		List<TWebMatchDetail> details = webRecordsDao.listForMatchDetail(records);

		if (null != list && list.size() == 0) {
			return list;
		}
		String tmp1 = null;
		Map<String, List<TWebMatchDetail>> map = new HashMap<String, List<TWebMatchDetail>>();
		for (int i = 0; i < details.size(); i++) {
			TWebMatchDetail matchDetail = details.get(i);
			String betWagerId = matchDetail.getBetWagersId();
			if (map.containsKey(betWagerId)) {
				map.get(betWagerId).add(matchDetail);
			} else {
				List<TWebMatchDetail> bets = new ArrayList<TWebMatchDetail>();
				bets.add(matchDetail);
				map.put(betWagerId, bets);
			}
			String _scoreStr = matchDetail.getTmp1();
			if (StringUtils.isBlank(_scoreStr)) {
				continue;
			}
			JSONObject scoreStr = JSON.parseObject(_scoreStr);
			String matchType = matchDetail.getMatchType();
			String period = matchDetail.getPeriod();
			String scoreH = "";
			String scoreC = "";
			if (SportConstant.match_type_ft.equals(matchType)) {// 足球
				if (SportConstant.TAG_PERIOD_F.equals(period)) {// 全场
					scoreH = scoreStr.getString("flScoreH");
					scoreC = scoreStr.getString("flScoreC");
				} else {
					scoreH = scoreStr.getString("hrScoreH");
					scoreC = scoreStr.getString("hrScoreC");
				}
			}

			if (SportConstant.match_type_bk.equals(matchType)) {// 篮球
				scoreH = scoreStr.getString("stageHF");
				scoreC = scoreStr.getString("stageCF");
			}

			matchDetail.setBetScoreH(scoreH);
			matchDetail.setBetScoreC(scoreC);

			tmp1 = details.get(i).getTmp1();
			if (StringUtils.isNotBlank(tmp1)) {
				SportScore ss = JSON.parseObject(tmp1, SportScore.class);
				details.get(i).setScore(ss);
			}

		}

		for (int j = 0; j < list.size(); j++) {
			TWebMatchBet bet = list.get(j);
			bet.setDetails(map.get(bet.getBetWagersId()));
		}
		return list;
	}

	public List<Map<String, Object>> btReportUser(WebRecords records) {
		return this.webRecordsDao.reportUser(records);
	}

	public List<TWebBankHuikuan> getHuiKuan(WebRecords records) {
		return this.webRecordsDao.getHuiKuan(records);
	}

	public List<WebUserWithdraw> getWithdrawList(WebRecords records) {
		// TODO Auto-generated method stub
		return this.webRecordsDao.getWithdrawList(records);
	}

	public List<WebEdu> getEduList(WebRecords records) {
		// TODO Auto-generated method stub
		return this.webRecordsDao.getEduList(records);
	}

	public List<TBetRecordAg> getAgRecordList(WebRecords record) {
		// TODO Auto-generated method stub
		return this.webRecordsDao.getAgRecordList(record);
	}

	public Page findEleRecordPage(Page page, WebRecords records) {
		Page p = webRecordsDao.findEleRecordPage(page, records);
		List<Map<String, String>> list = p.getResult();
		for (int j = 0; j < list.size(); j++) {
			Map<String, String> map = list.get(j);
			if (StringUtils.equals("ag", records.getBetSportType())) {
				String platType = AgConstants.platformType(map.get("bet_content"));
				String gameType = AgConstants.gameType(map.get("bet_game_content"));
				if (StringUtils.equals("其他", platType) && StringUtils.equals("其他", gameType)) {
					map.put("bet_game_content", "未知");
				} else if (StringUtils.equals("其他", platType) && !StringUtils.equals("其他", gameType)) {
					map.put("bet_game_content", gameType);
				} else if (!StringUtils.equals("其他", platType) && StringUtils.equals("其他", gameType)) {
					map.put("bet_game_content", platType);
				} else {
					map.put("bet_game_content", platType + " " + gameType);
				}
			} else if (StringUtils.equals("mg", records.getBetSportType()) || StringUtils.equals("os", records.getBetSportType()) || StringUtils.equals("ttg", records.getBetSportType())) {
				if (StringUtils.isBlank(map.get("bet_content"))) {
					map.put("bet_content", "未知");
				}
			} else {
				if (StringUtils.isBlank(map.get("bet_game_content"))) {
					map.put("bet_game_content", "未知");
				}
			}
		}
		return p;
	}

	public List<TBetRecordMg> getMgRecordList(WebRecords record) {
		// TODO Auto-generated method stub
		return this.webRecordsDao.getMgRecordList(record);
	}

	public List<TBetRecordPt> getPtRecordList(WebRecords record) {
		// TODO Auto-generated method stub
		return this.webRecordsDao.getPtRecordList(record);
	}

	public List<TBetRecordOs> getOsRecordList(WebRecords record) {
		// TODO Auto-generated method stub
		return this.webRecordsDao.getOsRecordList(record);
	}
}
