/**   
 * 文件名称: ResultsDao.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-6-4 上午10:39:32<br/>
 */
package com.mh.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import app.xb.cmbase.model.CpGame;

import com.mh.commons.cache.CpCacheUtil;
import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.CpCommonConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.CpBjpk10Results;
import com.mh.entity.CpCanada28Results;
import com.mh.entity.CpFc3dypl3Results;
import com.mh.entity.CpHistoryResults;
import com.mh.entity.CpHk6Results;
import com.mh.entity.CpKlsfResults;
import com.mh.entity.CpResults;
import com.mh.entity.CpSscResults;
import com.mh.entity.CpTomResults;
import com.mh.entity.CpXy28Results;
import com.mh.ifc.CpIfcUtil;

/**
 * 类描述: TODO<br/>
 * 結果集DAO 创建人: TODO alex<br/>
 * 创建时间: 2015-6-4 上午10:39:32<br/>
 */
@SuppressWarnings("all")
@Repository
public class CpResultsDao {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取开奖记录
	 * @param page
	 * @param results
	 */
	public Page findPage(Page page, CpResults results){
		Map<String, String> params = cPParams(results);
		String cpRJson = CpIfcUtil.getCpHistoryResult(params); 
		JSONObject parseObject = JSONObject.fromObject(cpRJson);
		JSONArray jsonArray=null;
		if (StringUtils.equals("000000", parseObject.getString("code"))&&null!=parseObject.get("msInfo")) {
			jsonArray = (JSONArray) parseObject.get("msInfo");
			String errorMsg = parseObject.get("erroMessage") == null ? "" : (String)parseObject.get("erroMessage") ;
			if (CpCommonConstant.HK_CODE_PARAM.equals(results.getCode())) {
				List<CpHk6Results> listResult = jsonArray.toList(jsonArray,CpHk6Results.class);
				page.setResult(StringUtils.isNotEmpty(errorMsg) ? null : listResult);
			} else if (CpCommonConstant.FC3D_CODE_PARAM.equals(results.getCode()) || CpCommonConstant.PL3_CODE_PARAM.equals(results.getCode())) {
				List<CpFc3dypl3Results> listResult = jsonArray.toList(jsonArray,CpFc3dypl3Results.class);
				page.setResult(StringUtils.isNotEmpty(errorMsg) ? null : listResult);
			} else if (CpCommonConstant.BJPK10_CODE_PARAM.equals(results.getCode())) {
				List<CpBjpk10Results> listResult = jsonArray.toList(jsonArray,CpBjpk10Results.class);
				page.setResult(StringUtils.isNotEmpty(errorMsg) ? null : listResult);
			} else if (CpCommonConstant.TJKLSF_CODE_PARAM.equals(results.getCode()) || CpCommonConstant.GDKLSF_CODE_PARAM.equals(results.getCode())) {
				List<CpKlsfResults> listResult = jsonArray.toList(jsonArray,CpKlsfResults.class);
				page.setResult(StringUtils.isNotEmpty(errorMsg) ? null : listResult);
			} else if (CpCommonConstant.BJKL8_CODE_PARAM.equals(results.getCode())) {
				List<CpXy28Results> listResult = jsonArray.toList(jsonArray, CpXy28Results.class);
				page.setResult(StringUtils.isNotEmpty(errorMsg) ? null : listResult);
			
			} else if (CpCommonConstant.CAKENO_CODE_PARAM.equals(results.getCode())) {
				List<CpCanada28Results> listResult = jsonArray.toList(jsonArray, CpCanada28Results.class);
				page.setResult(StringUtils.isNotEmpty(errorMsg) ? null : listResult);
			} else {
				List<CpSscResults> listResult = jsonArray.toList(jsonArray,CpSscResults.class);
				page.setResult(StringUtils.isNotEmpty(errorMsg) ? null : listResult);
			}
			page.setPageNo(Integer.valueOf(params.get("pageNo")));
			page.setPageSize(Integer.valueOf(params.get("pageSize")));
			page.setTotalCount(Integer.valueOf(parseObject.get("totalPages").toString()));
			logger.info(results.getCode() + "开奖结果返回报文--->>" + cpRJson);
		}
		
		return page;
	}

	private Map<String, String> cPParams(CpResults results) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("code", results.getCode());
		params.put("bTime", results.getbTime());
		params.put("eTime", results.geteTime());
		params.put("qs", results.getQs());
		params.put("pageNo", null == results.getPageNo() ? "1" : results.getPageNo());// 第几页
		params.put("pageSize", CommonConstant.DEFAULT_PAGE_SIZE+"");// 每页显示多少条
		return params;
	}

	/**
	 * 获取上一期开奖结果、下一期排期
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public CpResults findResultByCode(String code) throws Exception {
		CpHistoryResults historyResults = null;
		List<CpHistoryResults> resultList = this.findHistoryResultsMap(code, "1");
		CpResults cpResults = null;
		if(resultList!=null && resultList.size()>0){
			cpResults = new CpResults();
			
			historyResults = resultList.get(0);
			cpResults.setQs(historyResults.getQs());
			cpResults.setKjsj(historyResults.getKjjg());
			cpResults.setGtKjsj(historyResults.getGtKjsj());
			String[] codeArr = historyResults.getKjjg().split(",");
			List<String> numberList = Arrays.asList(codeArr);
			cpResults.setNumberList(numberList);
			cpResults.setName(CpConfigCache.GAME_OBJ_CACHE_MAP.get(code).getGameTypeName());
			CpTomResults tomResults = this.getNextResults(code);
			if(null!=tomResults){
				cpResults.setNextKjsj(tomResults.getKjsj());
				cpResults.setNextGtKjsj(tomResults.getGtKjsj());
				cpResults.setNextQs(tomResults.getFormatQs());
			}
		}
		//获取二级菜单彩种下期开奖时间
		CpGame cpGame = CpConfigCache.GAME_OBJ_CACHE_MAP.get(code);
		List<CpGame> gameList = CpConfigCache.CP_MENU_2.get(cpGame.getHandicapCode());
		List<String> codeList = new ArrayList<String>();
		
		Map<String,String> gameCodeMap = new LinkedHashMap<String,String>();
		for(CpGame _game:gameList){
			codeList.add(_game.getGameTypeCode());
			gameCodeMap.put(_game.getGameTypeCode(), _game.getGameTypeCode());
		}
		List<CpTomResults> tomList = this.getBatchNextResults(codeList);
		Map<String,CpTomResults> tomMap = new HashMap<String,CpTomResults>();
		CpTomResults tomResults = null;
		for(int i=0;i<tomList.size();i++){
			tomResults = tomList.get(i);
			if(tomResults!=null){
				tomMap.put(tomResults.getCode(), tomResults);
			}
		}
		
		CpGame _cpGame =null;		
		Map<String, String> timeMap = new LinkedHashMap<String,String>();
		String dateStr = CpIfcUtil.getCurrTime();
		for(String _code:gameCodeMap.keySet()){
			if(tomMap.get(_code)!=null){
				tomResults = tomMap.get(_code);
				String _fortmatQs = tomResults.getFormatQs();
				_cpGame = CpConfigCache.GAME_OBJ_CACHE_MAP.get(_code);
				String _bigtype = _cpGame.getBigtypeCode();
				String _gtkjsj = tomResults.getGtKjsj();
				Date _nextGtKjsj = DateUtil.parse(_gtkjsj, "yyyy-MM-dd HH:mm:ss");
				
				Date _now = DateUtil.parse(dateStr,  "yyyy-MM-dd HH:mm:ss");
				String _nowYMD = DateUtil.format(_now,"yyyy-MM-dd");
				
				int closeSec = 0;
				if (cpGame != null) {
					int colseM = (cpGame.getCloseM() == null ? 0 : cpGame.getCloseM());
					int closeS = (cpGame.getCloseS() == null ? 0 : cpGame.getCloseS());
					closeSec = colseM * 60 + closeS;
				}
				long reciprocalOpenTime = (_nextGtKjsj.getTime() - _now.getTime()) / 1000;
				Date closeKjsj = DateUtil.addSecond(_nextGtKjsj, -closeSec);
				long reciprocalCloseTime = (closeKjsj.getTime() - _now.getTime()) / 1000;
				int qsIndex=0;
				if(_fortmatQs.indexOf("-")!=-1){
					String _subQs = _fortmatQs.substring(_fortmatQs.indexOf("-")+1);
					qsIndex = Integer.valueOf(_subQs);
				}else{
					qsIndex = Integer.valueOf(_fortmatQs);
				}
				//下一期开盘时间
				//一般彩球
				long openSec=0;
				if(!StringUtils.isEmpty(tomResults.getPreGtKjsj())){
					if(CpCommonConstant.HK_CODE_PARAM.equals(_code) || CpCommonConstant.FC3D_CODE_PARAM.equals(_code) || CpCommonConstant.PL3_CODE_PARAM.equals(_code)){
						String preGtKjsj = tomResults.getPreGtKjsj();
						Date preGtKjsjDate = DateUtil.parse(preGtKjsj, DateUtil.YMDHMS_PATTERN);
						Date _preGtKjsjDate = DateUtil.addHour(preGtKjsjDate, 12);
						if(_preGtKjsjDate.getTime()>_now.getTime()){
							openSec =  (_preGtKjsjDate.getTime() - _now.getTime()) / 1000;
						}
					}else{
						//时时彩
						if(CpCommonConstant.BIGTYPE_SSC.equals(_bigtype) || CpCommonConstant.BIGTYPE_KLSF.equals(_bigtype)){
							//重庆时时彩
							if(CpCommonConstant.CQSSC_CODE_PARAM.equals(_code)){
								if(qsIndex==24){
									Date _preDate = DateUtil.addMinute(_nextGtKjsj, -10);
									if(_preDate.getTime()>_now.getTime()){
										openSec =  (_preDate.getTime() - _now.getTime()) / 1000;
									}
								}
							}else{
								if(qsIndex==1){
									Date _preDate = DateUtil.addMinute(_nextGtKjsj, -10);
									if(_preDate.getTime()>_now.getTime()){
										openSec =  (_preDate.getTime() - _now.getTime()) / 1000;
									}
								}
							}
							//北京快乐十分，幸运28
						}else{
							Date _preDate = DateUtil.addMinute(_nextGtKjsj, -10);
							if(_preDate.getTime()>_now.getTime()){
								openSec =  (_preDate.getTime() - _now.getTime()) / 1000;
							}
							
						}
					}
				}
				timeMap.put(_code, reciprocalOpenTime + "_" + reciprocalCloseTime + "_0_"+openSec);
			}else{
				timeMap.put(_code, "0_0_-1_0");
			}
		}
		
		cpResults.setTimeMap(timeMap);	
		return cpResults;
	}




	
	/**
	 * 获取最新的历史开奖结果
	 * 方法描述: TODO</br> 
	 * @param gameCode
	 * @param rows
	 * @return  
	 * Map<String,String>
	 */
	public List<CpHistoryResults> findHistoryResultsMap(String gameCode,String rows) {
		List<CpHistoryResults> resultList =null; 
		try {
			resultList = CpCacheUtil.getResultCache(gameCode,Integer.valueOf(rows));
			if(resultList==null){
				logger.info("获取最新的历史开奖结果缓存为空~·");
				
				resultList = CpIfcUtil.cpUpNewResult(gameCode,rows);
			}
		} catch (Exception e) {
			logger.error("获取最新的历史开奖结果异常",e);
			e.printStackTrace();
		}
		return resultList; 
	}
 
	
	/**
	 * 获取下一期排期
	 * 方法描述: TODO</br> 
	 * @param code
	 * @return  
	 * CpTomResults
	 */
	public CpTomResults getNextResults(String code) {
		CpTomResults tomResults= null;
		try {
			Date _date = new Date();
			String dateStr="";
			try{
				dateStr = CpIfcUtil.getCurrTime();
				if("".equals(dateStr)){
					dateStr = DateUtil.format(_date, "yyyy-MM-dd HH:mm:ss");
				}
			}catch(Exception e){
				dateStr = DateUtil.format(_date, "yyyy-MM-dd HH:mm:ss");
				e.printStackTrace();
			}finally{
				try {
					_date = DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			tomResults = CpCacheUtil.getTomCache(code,_date);
			if(tomResults==null){
				logger.info(code+"获取下一期排期缓存为空~~~!");
				tomResults = CpIfcUtil.getCpNextResult(code);
			}
		} catch (Exception e) {
			logger.error(code+"获取下一期排期",e);
			e.printStackTrace();
		}
		return tomResults;
	}
	
	
	
	/**
	 * 批量获取下一期排期
	 * 方法描述: TODO</br> 
	 * @param codeList
	 * @return  
	 * List<CpTomResults>
	 */
	public List<CpTomResults> getBatchNextResults(List<String> codeList){
		List<CpTomResults> tomList = null;	
		try {
			tomList = CpCacheUtil.getBatchTomCache(codeList);
			if(tomList==null){
				logger.info("批量获取下一期排期缓存为空~~~!");
				tomList = CpIfcUtil.getCpBatchNextResult(codeList);
			}
		} catch (Exception e) {
			logger.error("批量获取下一期排期异常",e);
			e.printStackTrace();
		}
		return tomList;
	}

	
}
