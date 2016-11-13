package com.mh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mh.dao.WapMatchBetDao;
import com.mh.dao.WapSportDao;
import com.mh.entity.SportScore;
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
import com.mh.entity.TWebMatchDetail;
import com.mh.entity.WebRecords;
import com.mh.service.WapSportService;

@Service
public class WapSportServiceImpl implements WapSportService {
	@Autowired
	private WapSportDao wapSportDao;
	
	@Autowired
	private WapMatchBetDao wapMatchBetDao;
	
	
	public List<TFtMatchR> getFtMatchR(String curTag,String gid) {
		return wapSportDao.getFtMatchR(curTag,gid);
	}
	public List<TFtMatchR> getFtMatchR(TMatchControl control,String gid) {
		return wapSportDao.getFtMatchR(control,gid);
	}
	
	public List<TFtMatchPD> getFtMatchPD(String curTag,String gid) {
		return wapSportDao.getFtMatchPD(curTag,gid);
	}
	public List<TFtMatchPD> getFtMatchPD(TMatchControl control,String gid) {
		return wapSportDao.getFtMatchPD(control,gid);
	}
	
	public List<TFtMatchT> getFtMatchT(String curTag,String gid) {
		return wapSportDao.getFtMatchT(curTag,gid);
	}
	public List<TFtMatchT> getFtMatchT(TMatchControl control,String gid) {
		return wapSportDao.getFtMatchT(control,gid);
	}
	
	public List<TFtMatchF> getFtMatchF(String curTag,String gid) {
		return wapSportDao.getFtMatchF(curTag,gid);
	}
	public List<TFtMatchF> getFtMatchF(TMatchControl control,String gid) {
		return wapSportDao.getFtMatchF(control,gid);
	}
	
	public List<TBkMatchRmain> getBkMatchRmain(String curTag,String gid) {
		return wapSportDao.getBkMatchRmain(curTag,gid);
	}
	public List<TBkMatchRmain> getBkMatchRmain(TMatchControl control,String gid) {
		return wapSportDao.getBkMatchRmain(control,gid);
	}
	
	public List<TRMatchRE> getRollMatchRE(String curTag,String gid) {
		return wapSportDao.getRollMatchRE(curTag,gid);
	}
	public List<TRMatchRE> getRollMatchRE(TMatchControl control,String gid) {
		return wapSportDao.getRollMatchRE(control,gid);
	}
	
	public List<TRMatchRemain> getRollMatchRemain(String curTag,String gid) {
		return wapSportDao.getRollMatchRemain(curTag,gid);
	}
	public List<TRMatchRemain> getRollMatchRemain(TMatchControl control,String gid) {
		return wapSportDao.getRollMatchRemain(control,gid);
	}
	
	public List<TFtMatchP3> getFtMatchP3(String curTag, String gid) {
		return wapSportDao.getFtMatchP3(curTag, gid);
	}
	public List<TFtMatchP3> getFtMatchP3(TMatchControl control, String gid) {
		return wapSportDao.getFtMatchP3(control, gid);
	}
	
	public List<String> getLeague(String tableName,String curTag){
		List<Map<String, Object>> list = this.wapSportDao.getLeague(tableName, curTag);
		if(null != list){
			List<String> resultList = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				resultList.add((String)map.get("league"));
			}
			return resultList;
		}
		return null;
	}

	
	public List<TWebMatchBet> getWebMatchList(WebRecords records) {
		List<TWebMatchBet> betList = this.wapMatchBetDao.getWebMatchBetListByBetName(records);
		if(null != betList){
			for(int i=0;i<betList.size();i++){
				TWebMatchBet bet = betList.get(i);
				List<TWebMatchDetail> detailList = this.wapMatchBetDao.getWebMatchBetDetailByBetWagersId(bet.getBetWagersId());
				bet.setDetails(detailList);
			}
		}
		return betList;
	}

	public List<TWebMatchBet> getWebMatchLists(WebRecords records) {
		List<TWebMatchBet> betList = this.wapMatchBetDao.getSportOrder(records);
		String tmp1 = null;
		if(null != betList){
			for(int i=0;i<betList.size();i++){
				TWebMatchBet bet = betList.get(i);
				List<TWebMatchDetail> detailList = this.wapMatchBetDao.getWebMatchBetDetailByBetWagersId(bet.getBetWagersId());
				for (TWebMatchDetail detail : detailList) {
					tmp1 = detail.getTmp1();
					if (StringUtils.isNotBlank(tmp1)) {
						SportScore ss = JSON.parseObject(tmp1, SportScore.class);
						detail.setScore(ss);
					}
				}
				bet.setDetails(detailList);
			}
		}
		return betList;
	}
}
