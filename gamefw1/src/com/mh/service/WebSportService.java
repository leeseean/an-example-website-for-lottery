/**   
 * 文件名称: WebSportService.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-7-14 下午7:48:27<br/>
 */
package com.mh.service;

import java.util.List;

import com.mh.commons.orm.Page;
import com.mh.entity.SportBet;
import com.mh.entity.TBkMatchP3;
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
import com.mh.entity.WebRecords;

/**
 * 体育Dao 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-14 下午7:48:27<br/>
 */
public interface WebSportService {

	/**
	 * 获取订单列表 方法描述: TODO</br>
	 * 
	 * @param betUserName
	 * @return List<TWebMatchBet>
	 */
	public List<TWebMatchBet> getMatchBetResult(WebRecords record);

	public boolean updateWebSportBet(TWebMatchBet mbet);

	/**
	 * 
	 * 方法描述: TODO</br>
	 * 
	 * @param timeType大类型
	 *            （今日赛事、早盘、滚球）
	 * @param rtype
	 * @return TMatchControl
	 */
	public TMatchControl getMatchControl(String timeType, String rtype);

	/**
	 * 获取联赛名称 方法描述: TODO</br>
	 * 
	 * @param tableName
	 * @param curTag
	 * @return List<String>
	 */
	public List<String> getLeague(String tableName, String curTag);

	public List<String> getLeague(TMatchControl control);

	/**
	 * 
	 * 单式 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchR> getFtMatchR(String curTag, int curPage, String league);

	public List<TFtMatchR> getFtMatchR(TMatchControl control, int curPage, String league);

	/**
	 * 
	 * 波胆 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchPD> getFtMatchPD(String curTag, int curPage, String league);

	public List<TFtMatchPD> getFtMatchPD(TMatchControl control, int curPage, String league);

	/**
	 * 
	 * 总入球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchT> getFtMatchT(String curTag, int curPage, String league);

	public List<TFtMatchT> getFtMatchT(TMatchControl control, int curPage, String league);

	/**
	 * 
	 * 总入球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchF> getFtMatchF(String curTag, int curPage, String league);

	public List<TFtMatchF> getFtMatchF(TMatchControl control, int curPage, String league);

	/**
	 * 
	 * 总和过关 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public List<TFtMatchP3> getFtMatchP3(String curTag, int curPage, String league);

	public List<TFtMatchP3> getFtMatchP3(TMatchControl control, int curPage, String league);

	/**
	 * 篮球 单式 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public List<TBkMatchRmain> getBkMatchRmain(String curTag, int curPage, String league);

	public List<TBkMatchRmain> getBkMatchRmain(TMatchControl control, int curPage, String league);

	/**
	 * 篮球 综合过关 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public List<TBkMatchP3> getBkMatchP3(String curTag, int curPage, String league);

	public List<TBkMatchP3> getBkMatchP3(TMatchControl control, int curPage, String league);

	/**
	 * 滚球-足球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public List<TRMatchRE> getRollMatchRE(String curTag, int curPage, String league);

	public List<TRMatchRE> getRollMatchRE(TMatchControl control, int curPage, String league);

	/**
	 * 滚球-篮球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public List<TRMatchRemain> getRollMatchRemain(String curTag, int curPage, String league);

	public List<TRMatchRemain> getRollMatchRemain(TMatchControl control, int curPage, String league);

	/**
	 * 
	 * 单式 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchR getFtMatchR(String gid, String timeType);

	public TFtMatchR getFtMatchR(TMatchControl control, SportBet bet);

	/**
	 * 
	 * 波胆 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchPD getFtMatchPD(String gid, String timeType, String tag);

	public TFtMatchPD getFtMatchPD(TMatchControl control, SportBet bet, String tag);

	/**
	 * 
	 * 总入球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchT getFtMatchT(String gid, String timeType);

	public TFtMatchT getFtMatchT(TMatchControl control, SportBet bet);

	/**
	 * 
	 * 半场/全场 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchF getFtMatchF(String gid, String timeType);

	public TFtMatchF getFtMatchF(TMatchControl control, SportBet bet);

	/**
	 * 
	 * 总和过关 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @return List<TFtMatchR>
	 */
	public TFtMatchP3 getFtMatchP3(String gid, String timeType);

	public TFtMatchP3 getFtMatchP3(TMatchControl control, SportBet bet);

	/**
	 * 篮球 单式 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public TBkMatchRmain getBkMatchRmain(String gid, String timeType);

	public TBkMatchRmain getBkMatchRmain(TMatchControl control, SportBet bet);

	/**
	 * 篮球 综合过关 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public TBkMatchP3 getBkMatchP3(String gid, String timeType);

	public TBkMatchP3 getBkMatchP3(TMatchControl control, SportBet bet);

	/**
	 * 滚球-足球 方法描述: TODO</br>
	 * 
	 * @param curTag
	 * @param curPage
	 * @param league
	 * @return List<TBkMatchRmain>
	 */
	public TRMatchRE getRollMatchRE(String gid, String timeType);

	public TRMatchRE getRollMatchRE(TMatchControl control, SportBet bet);

	/**
	 * 滚球-篮球 方法描述: TODO</br>
	 * 
	 * @param gid
	 * @return List<TBkMatchRmain>
	 */
	public TRMatchRemain getRollMatchRemain(String gid, String timeType);

	public TRMatchRemain getRollMatchRemain(TMatchControl control, SportBet bet);

	/**
	 * 查询赛程 方法描述: TODO</br>
	 * 
	 * @param matchTime
	 * @param teamH
	 * @param teamC
	 * @return TMatchRelate
	 */
	public TMatchRelate getTMatchRelate(String matchTime, String teamH, String teamC);

	/**
	 * 获取注单列表 方法描述: TODO</br>
	 * 
	 * @param betUserName
	 * @return List<TWebMatchBet>
	 */
	public List<TWebMatchBet> getWebMatchBetListByBetName(String betUserName);

	/**
	 * 通过Gid查询球赛</br> 创建人: zoro<br/>
	 * 
	 * @param gid
	 * @return TMatchRelate
	 */
	public TMatchRelate searchMatchByGid(String gid);

	/**
	 * 通过联盟名称+主队名+客队名+日期 询球赛</br> 创建人: zoro<br/>
	 * 
	 * @param league
	 * @param teamH
	 * @param teamC
	 * @param matchDate
	 * @return List<TMatchRelate>
	 */
	public List<TMatchRelate> searchMatchForMulConditions(String league, String teamH, String teamC, String matchDate);

	public Page findSportRecordPage(Page page, WebRecords records);
}
