package com.mh.service;

import java.util.List;

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

public interface WapSportService {
	/**
	 * 获取单式足球
	 * @param curTag
	 * @return
	 */
	public List<TFtMatchR> getFtMatchR(String curTag,String gid);
	public List<TFtMatchR> getFtMatchR(TMatchControl control,String gid);
	
	/**
	 * 获取足球 波胆
	 * @param curTag
	 * @return
	 */
	public List<TFtMatchPD> getFtMatchPD(String curTag,String gid);
	public List<TFtMatchPD> getFtMatchPD(TMatchControl control,String gid);
	/**
	 * 获取足球 总入球
	 * @param curTag
	 * @return
	 */
	public List<TFtMatchT> getFtMatchT(String curTag,String gid);
	public List<TFtMatchT> getFtMatchT(TMatchControl control,String gid);
	/**
	 * 获取足球 全半场
	 * @param curTag
	 * @return
	 */
	public List<TFtMatchF> getFtMatchF(String curTag,String gid);
	public List<TFtMatchF> getFtMatchF(TMatchControl control,String gid);
	
	/**
	 * 获取篮球 单式
	 * @param curTag
	 * @return
	 */
	public List<TBkMatchRmain> getBkMatchRmain(String curTag,String gid);
	public List<TBkMatchRmain> getBkMatchRmain(TMatchControl control,String gid);
	/**
	 * 综合过关
	 * @param curTag
	 * @param gid
	 * @return
	 */
	public List<TFtMatchP3> getFtMatchP3(String curTag,String gid);
	public List<TFtMatchP3> getFtMatchP3(TMatchControl control,String gid);
	
	/**
	 * 获取滚球 足球
	 * @param curTag
	 * @return
	 */
	public List<TRMatchRE> getRollMatchRE(String curTag,String gid);
	public List<TRMatchRE> getRollMatchRE(TMatchControl control,String gid);
	/**
	 * 获取滚球 篮球
	 * @param curTag
	 * @return
	 */
	public List<TRMatchRemain> getRollMatchRemain(String curTag,String gid);
	public List<TRMatchRemain> getRollMatchRemain(TMatchControl control,String gid);
	/**
	 * 获取联赛名称
	 * @param tableName
	 * @param curTag
	 * @return  
	 */
	public List<String> getLeague(String tableName,String curTag);
	
	/**
	 * 获取注单列表
	 * @param records
	 * @return
	 */
	public List<TWebMatchBet> getWebMatchList(WebRecords records);
	
	public List<TWebMatchBet> getWebMatchLists(WebRecords records);
}
