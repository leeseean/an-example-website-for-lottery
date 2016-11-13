/**   
* 文件名称: CpMainController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-12-31 下午4:06:28<br/>
*/  
package com.mh.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.xb.cmbase.model.CpGame;

import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.CpCommonConstant;
import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.ResponsePrintUtils;
import com.mh.entity.CpHistoryResults;
import com.mh.entity.CpResults;
import com.mh.service.CpResultsService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-12-31 下午4:06:28<br/>
 */

@Controller
@RequestMapping("/cpResult")
public class CpKjResultController extends BaseController{
	@Resource
	CpResultsService cpResultsService;
	
	
 
	/**
	 * 获取开奖结果(手机端跟PC端的近期开奖号码)
	 * 方法描述: TODO</br> 
	 * @param gameid
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/getNewsResult/{gameid}")
	public void getNewsResult(@PathVariable(value="gameid") Integer gameid,HttpServletRequest request,HttpServletResponse response){
		try{
			CpGame cpGame = CpConfigCache.GAME_CACHE_MAP.get(gameid);
			
			List<CpHistoryResults> historyList = this.cpResultsService.findHistoryResultsMap(cpGame.getGameTypeCode(), "5");
			
			ResponsePrintUtils.outPrintSuccess(request, response, historyList);
		}catch(RuntimeException e){
			ResponsePrintUtils.outPrintFail(request, response, "获取开奖结果异常！");
			logger.error("获取开奖结果异常！",e);
		}
	}
	 
	@RequestMapping("/goList")
	public ModelAndView goList(HttpServletRequest request,HttpServletResponse response,CpResults results) {
		String gameTypeCode = request.getParameter("gameTypeCode");
		if(!StringUtils.isEmpty(gameTypeCode)){
			results.setCode(gameTypeCode);
		}
		if(StringUtils.isEmpty(results.getCode())){
			results.setCode(CpCommonConstant.HK_CODE_PARAM);
		}
		Date currDate = new Date();
		String dateStr =DateUtil.format(currDate, "yyyy-MM-dd");
		if(StringUtils.isEmpty(results.geteTime())){
			results.seteTime(dateStr);
		}
		if(StringUtils.isEmpty(results.getbTime())){
			int days=7;
			if(CpCommonConstant.HK_CODE_PARAM.equals(results.getCode())||CpCommonConstant.FC3D_CODE_PARAM.equals(results.getCode())||
					CpCommonConstant.PL3_CODE_PARAM.equals(results.getCode())){
				days=30;
			}
			
			Date preDate = DateUtil.addDay(currDate, -days);
			String endTimeStr =DateUtil.format(preDate, "yyyy-MM-dd");
			results.setbTime(endTimeStr);
		}
		
		
		Page<?> page = this.newPage(request);
		this.cpResultsService.findPage(page, results);//远程调用开奖结果
		String pageView = results.getCode().toLowerCase();
		page.setColspanNum(19);
		if(CpCommonConstant.FC3D_CODE_PARAM.equals(results.getCode())|| CpCommonConstant.PL3_CODE_PARAM.equals(results.getCode())){
			pageView = "fc3dpl3";
			page.setColspanNum(17);
		}else if(CpCommonConstant.CQSSC_CODE_PARAM.equals(results.getCode()) || CpCommonConstant.JXSSC_CODE_PARAM.equals(results.getCode())
				|| CpCommonConstant.TJSSC_CODE_PARAM.equals(results.getCode()) || CpCommonConstant.XJSSC_CODE_PARAM.equals(results.getCode())){
			pageView = "ssc";
			page.setColspanNum(26);
		}else if(CpCommonConstant.TJKLSF_CODE_PARAM.equals(results.getCode()) || CpCommonConstant.GDKLSF_CODE_PARAM.equals(results.getCode())){
			pageView = "klsf";
			page.setColspanNum(44);
		}else if(CpCommonConstant.BJPK10_CODE_PARAM.equals(results.getCode())){
			pageView="bjpk10";
			page.setColspanNum(15);
		}
		
		results.setName(CpConfigCache.GAME_OBJ_CACHE_MAP.get(results.getCode()).getGameTypeName());
		
		return new ModelAndView("cp/data_center/openHistory")
			.addObject("pageName", pageView)
			.addObject("gameMap", CpConfigCache.GAME_OBJ_CACHE_MAP)
			.addObject(CommonConstant.PAGE_KEY, page)
			.addObject("results", results);

	}
}
