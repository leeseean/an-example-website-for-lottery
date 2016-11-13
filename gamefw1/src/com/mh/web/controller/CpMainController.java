/**   
 * 文件名称: CpMainController.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-12-31 下午4:06:28<br/>
 */
package com.mh.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import app.xb.cmbase.model.CpConfig;
import app.xb.cmbase.model.CpGame;
import app.xb.cmbase.model.CpType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.utils.AnimalUtil;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.MathUtil;
import com.mh.commons.utils.ResponsePrintUtils;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.CpHistoryResults;
import com.mh.entity.CpResults;
import com.mh.ifc.CpIfcUtil;
import com.mh.service.CpOrderService;
import com.mh.service.CpResultsService;
import com.mh.service.WebUserService;
import com.mh.web.job.TemplateHelper;
import com.mh.web.login.UserContext;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-12-31 下午4:06:28<br/>
 */

@Controller
@RequestMapping("/cp")
public class CpMainController extends BaseController {

	@Autowired
	private WebUserService webUserService;

	@Autowired
	private CpResultsService cpResultsService;
	
	@Autowired
	private CpOrderService cpOrderService;

	/**
	 * 
	 * 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("/betTemplate")
	public void bet(HttpServletRequest request, HttpServletResponse response) {
//		String _bet = CpCommonConstant.all_bet;
		String _bet = "";
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
//		HibernateTemplate hibernateTemplate_shared = (HibernateTemplate) wac.getBean("hibernateTemplate_shared");
		JdbcTemplate jdbcTemplate_shared = (JdbcTemplate) wac.getBean("jdbcTemplate_shared");
		TemplateHelper templateHelper = new TemplateHelper(jdbcTemplate_shared,request.getSession().getServletContext().getRealPath("/"));
		_bet =templateHelper.initBetTemplateData();
		
		
//		if (StringUtils.isEmpty(_bet)) {
//			_bet = this.readTemplateAllBetHtml(request, response);
//			CpCommonConstant.all_bet = _bet;
//		}
//		String[] betArr = _bet.split("êêê");
//		logger.info("-==============="+betArr[56]);
		ResponsePrintUtils.outPrintMsg(request, response, _bet);
	}


	/**
	 * 获取模板 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("/allTemplate")
	public void allTemplate(HttpServletRequest request,HttpServletResponse response) {
//		String _all = CpCommonConstant.all;
//		if (StringUtils.isEmpty(_all)) {
//			_all = this.readTemplateAllHtml(request, response);
//			CpCommonConstant.all = _all;
//		}
		
		String _all = "";
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
//		HibernateTemplate hibernateTemplate_shared = (HibernateTemplate) wac.getBean("hibernateTemplate_shared");
		JdbcTemplate jdbcTemplate_shared = (JdbcTemplate) wac.getBean("jdbcTemplate_shared");
		TemplateHelper templateHelper = new TemplateHelper(jdbcTemplate_shared,request.getSession().getServletContext().getRealPath("/"));
		_all =templateHelper.initAllTemplateData();
		
		ResponsePrintUtils.outPrintMsg(request, response, _all);
	}

	/**
	 * 调整到香港六合彩主页 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView hk6Main(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView("cp/main_cp");
		String cpparam = request.getParameter("cpparam");
		if(StringUtils.isNotEmpty(cpparam)){
			view.addObject("cpparam", cpparam);
		}
		view.addObject("cpmenu1", CpConfigCache.CP_MENU_1);//一级菜单
		view.addObject("cpmenu2", CpConfigCache.CP_MENU_2);//二级菜单
		view.addObject("cpmenu3", CpConfigCache.CP_MENU_3);//三级菜单
		
		UserContext webUser = this.getUserContext(request);
		view.addObject("webUser", webUser);
		return view;
	}
	
	@RequestMapping("/gameType")
	public ModelAndView gameType(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView("cp/gameType");
		return view;
	}

	/**
	 * 获取账户余额 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("/LottoGetBalance")
	public void LottoGetBalance(HttpServletRequest request,HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		Map<String, Object> valMap = new HashMap<String, Object>();
		if (uc == null) {
			valMap.put("balance", 0);
		} else {
			valMap.put("balance",this.webUserService.getWebUserMoney(uc.getUserName()));
		}
		ResponsePrintUtils.outPrintMsg(request, response,JSON.toJSONString(valMap));
	}

	/**
	 * 获取公告信息 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("/LottoGetAnnouncement")
	public void LottoGetAnnouncement(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> valMap = new HashMap<String, Object>();
		valMap.put("announcement", "");
		ResponsePrintUtils.outPrintMsg(request, response,JSON.toJSONString(valMap));
	}

	/**
	 * 获取开奖信息 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("/lottoGetGameInfo/{gameid}/{typeid}")
	public void lottoGetGameInfo(@PathVariable(value = "gameid") Integer gameid,@PathVariable(value = "typeid") Integer typeid,HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		try {
			UserContext uc = this.getUserContext(request);
			CpGame cpGame = CpConfigCache.GAME_CACHE_MAP.get(gameid);
			CpType cpType = CpConfigCache.TYPE_CACHE_MAP.get(typeid);
			
			String dateStr = CpIfcUtil.getCurrTime();// 远程获取日期时间
 
			CpResults results = this.cpResultsService.findResultByCode(cpGame.getGameTypeCode());// 远程请求  上期  的开奖结果和  下一期  的期数跟开奖结果
 
			JSONObject lastResult = new JSONObject();
			lastResult.put("last_qs", results.getQs());// 上一期开奖期数
			lastResult.put("last_result", results.getNumberList());// 上一期开奖结果			
			jsonObject.put("lastResult", lastResult);
			jsonObject.put("timeMap", results.getTimeMap());
			// 获取生肖
			shengXiao(jsonObject);

			JSONObject nextResult = new JSONObject();
			String nextQs = "";
			String closeTimeStr = "";
			if (results.getNextGtKjsj() == null) {
				jsonObject.put("status", "2");
				nextQs = results.getQs();
				closeTimeStr = results.getGtKjsj();
			} else {
				jsonObject.put("status", "0");
				Date nextGtKjsj = null;
				Date date2 = null;
				try {
					nextGtKjsj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(results.getNextGtKjsj());
					date2 = DateUtil.parse(StringUtils.replace(dateStr, "\"", ""),"yyyy-MM-dd HH:mm:ss");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// 距离开盘倒计时
//				long reciprocalOpenTime = (nextGtKjsj.getTime() - date2.getTime()) / 1000;
				// 距离封盘倒计时
				int closeSec=0;
				if(cpGame!=null){
					int colseM = (cpGame.getCloseM()==null?0:cpGame.getCloseM());
					int closeS = (cpGame.getCloseS()==null?0:cpGame.getCloseS());
					closeSec = colseM*60+closeS;
				}
				Date closeKjsj = DateUtil.addSecond(nextGtKjsj, -closeSec);
				long reciprocalCloseTime = (closeKjsj.getTime() - date2.getTime()) / 1000;
				if (reciprocalCloseTime <= 0) {
					jsonObject.put("status", "1");
				}
				nextQs = results.getNextQs();
				closeTimeStr = results.getNextGtKjsj();
			}

			 nextResult.put("next_qs",nextQs);
			//nextResult.put("next_qs", CommonUtils.getFormatQs(nextQs));
			nextResult.put("next_closetime", closeTimeStr);
			nextResult.put("next_openTime", dateStr);
			jsonObject.put("nextResult", nextResult);
			System.out.println("c========="+nextResult);
			double balance = 0;
			double betUsrWin=0;
			if (uc != null && uc.getUserName() != null) {
				balance = this.webUserService.getWebUserMoney(uc.getUserName());
				//今日输赢
				Map<String,Object> winlossMap = this.cpOrderService.getTodayOrderTj(cpGame.getGameTypeCode(), cpType.getCpTypeCode(),uc.getUserName());
				if(winlossMap!=null && winlossMap.get("betUsrWin")!=null){
					betUsrWin=Double.valueOf(winlossMap.get("betUsrWin").toString());
				}
			}
			jsonObject.put("balance", balance);
			jsonObject.put("game_type_code", cpGame.getGameTypeCode());
			jsonObject.put("cp_type_code", cpType.getCpTypeCode());
			jsonObject.put("win_loss", betUsrWin);
			if(CommonConstant.CAKENO_CODE_PARAM.equals(cpGame.getGameTypeCode()) || CommonConstant.BJKL8_CODE_PARAM.equals(cpGame.getGameTypeCode())){
				List<CpHistoryResults> list = cpResultsService.findHistoryResultsMap(cpGame.getGameTypeCode(),"10");
				JSONArray pcArray = new JSONArray();
				if(list!=null&&list.size()>0){				
					for(CpHistoryResults _results:list){
						String[] codeArr =_results.getKjjg().split(",");
						int total =0;
						for(int i=0;i<codeArr.length;i++){	
							total += Integer.valueOf(codeArr[i]);
						}
						pcArray.add(total);
					}
				}
				jsonObject.put("history_result_pc", pcArray);
			}
			

			if (CommonConstant.HK_CODE_PARAM.equals(cpGame.getGameTypeCode())|| CommonConstant.FC3D_CODE_PARAM.equals(cpGame.getGameTypeCode()) || CommonConstant.PL3_CODE_PARAM.equals(cpGame.getGameTypeCode())) {
				jsonObject.put("is_ssc", 0);
			} else {
				jsonObject.put("is_ssc", 1);
			}
			// 赔率
			getCpConfig(jsonObject, cpGame, cpType);
		} catch (Exception e) {
			jsonObject.put("status", "2");
			e.printStackTrace();
			logger.error("获取六合彩开奖信息失败!", e);
		}

		ResponsePrintUtils.outPrintMsg(request, response,JSON.toJSONString(jsonObject));
	}



	private void getCpConfig(JSONObject jsonObject, CpGame cpGame, CpType cpType) {
		List<CpConfig> configList = CpConfigCache.RATE_CACHE_KEY.get(cpGame.getGameTypeCode() + "-" + cpType.getCpTypeCode());
		
		JSONObject rateObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject typeObj = new JSONObject();
		JSONObject cateObj = new JSONObject();
		JSONObject numObj = new JSONObject();
		if(configList!=null && configList.size()>0){
			for (int i = 0; i < configList.size(); i++) {
				CpConfig cpConfig = configList.get(i);
				if (!StringUtils.isEmpty(cpConfig.getUid())) {
					rateObj.put(cpConfig.getUid(),MathUtil.round(cpConfig.getPl(), 2));
					typeObj.put(cpConfig.getCpTypeCode(), cpConfig.getCpTypeName());
					cateObj.put(cpConfig.getCpCateCode(), cpConfig.getCpCateName());
					numObj.put(cpConfig.getEnumCode(), cpConfig.getNumber());
				}
			}
		}
		jsonArray.add(typeObj);
		jsonArray.add(cateObj);
		jsonArray.add(numObj);
		jsonObject.put("odds", rateObj);
		jsonObject.put("betDesc", jsonArray);

		jsonObject.put("maxBetMap", this.getMaxBetMap(cpGame.getBigtypeCode()));
		jsonObject.put("minBetMap", this.getMinBetMap(cpGame.getBigtypeCode()));
		jsonObject.put("maxBatchMap",this.getMaxBetMap(cpGame.getBigtypeCode()));
	}

	

	private void shengXiao(JSONObject jsonObject) {
		// 生肖 鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
		JSONObject shengXiaoMap = new JSONObject();
		shengXiaoMap.put("SHU", AnimalUtil.getCurrAnimalCodeList().get("鼠"));
		shengXiaoMap.put("NIU", AnimalUtil.getCurrAnimalCodeList().get("牛"));
		shengXiaoMap.put("HU", AnimalUtil.getCurrAnimalCodeList().get("虎"));
		shengXiaoMap.put("TU", AnimalUtil.getCurrAnimalCodeList().get("兔"));
		shengXiaoMap.put("LONG", AnimalUtil.getCurrAnimalCodeList().get("龙"));
		shengXiaoMap.put("SHE", AnimalUtil.getCurrAnimalCodeList().get("蛇"));
		shengXiaoMap.put("MA", AnimalUtil.getCurrAnimalCodeList().get("马"));
		shengXiaoMap.put("YANG", AnimalUtil.getCurrAnimalCodeList().get("羊"));
		shengXiaoMap.put("HOU", AnimalUtil.getCurrAnimalCodeList().get("猴"));

		shengXiaoMap.put("JI", AnimalUtil.getCurrAnimalCodeList().get("鸡"));
		shengXiaoMap.put("GOU", AnimalUtil.getCurrAnimalCodeList().get("狗"));
		shengXiaoMap.put("ZHU", AnimalUtil.getCurrAnimalCodeList().get("猪"));
		jsonObject.put("shengXiaoMap", shengXiaoMap);
	}

	public JSONObject getMinBetMap(String bigTypeCode) {
		JSONObject minBetMap = new JSONObject();
		List<CpType> typeList = CpConfigCache.TYPE_LIST_MAP.get(bigTypeCode);
		for (int i = 0; i < typeList.size(); i++) {
			CpType cpType = typeList.get(i);
			minBetMap.put(cpType.getCpTypeCode(), cpType.getGminSingle());
		}

		return minBetMap;

	}

	public JSONObject getMaxBetMap(String bigTypeCode) {
		JSONObject maxBetMap = new JSONObject();
		List<CpType> typeList = CpConfigCache.TYPE_LIST_MAP.get(bigTypeCode);
		for (int i = 0; i < typeList.size(); i++) {
			CpType cpType = typeList.get(i);
			maxBetMap.put(cpType.getCpTypeCode(), cpType.getGmaxSingle());
		}

		return maxBetMap;
	}

	public JSONObject getMaxBatchMap(String bigTypeCode) {
		JSONObject maxMatchMap = new JSONObject();
		List<CpType> typeList = CpConfigCache.TYPE_LIST_MAP.get(bigTypeCode);
		for (int i = 0; i < typeList.size(); i++) {
			CpType cpType = typeList.get(i);
			maxMatchMap.put(cpType.getCpTypeCode(), cpType.getSingleCredit());
		}

		return maxMatchMap;
	}

	// 刷新赔率
	@RequestMapping("/server")
	public void server(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String code2 = request.getParameter("code2");
		String code3=request.getParameter("code3");
		String code4=request.getParameter("code4");
		String key = "";
		if(!StringUtils.isEmpty(code3) && !StringUtils.isEmpty(code4)){
			key = code2+"-"+code3+"-"+code4;
		}
		 
		List<CpConfig> configList = CpConfigCache.RATE_CACHE_KEY.get(code + "-" + code2);
		List<Object> dataList = new ArrayList<Object>();
		for (CpConfig data : configList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String number = data.getNumber();
			map.put("id", data.getUid());
			map.put("number", number);
			map.put("pl", MathUtil.round(data.getPl(), 2));
			String uid = data.getUid();
			if(!"".equals(key)){
				if(uid.contains(key)){
					dataList.add(map);
				}
			}else{
				dataList.add(map);
			}
		}
		ServletUtils.outPrintSuccess(request, response, dataList);
	}

}
