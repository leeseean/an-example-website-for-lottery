package com.mh.web.controller.m;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.xb.cmbase.model.CpConfig;
import app.xb.cmbase.model.CpGame;
import app.xb.cmbase.model.CpType;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.CpCommonConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.MathUtil;
import com.mh.entity.CpOrder;
import com.mh.entity.CpTomResults;
import com.mh.entity.WebUser;
import com.mh.service.CpConfigService;
import com.mh.service.CpOrderService;
import com.mh.service.CpResultsService;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;

@Controller
@RequestMapping("/m/mobileCpOrder")
public class MCpOrderController extends BaseController {

	@Autowired
	private CpResultsService cpResultsService;
	@Autowired
	private WebUserService webUserService;
	@Autowired
	private CpConfigService cpConfigService;
	@Autowired
	private CpOrderService cpOrderService;

	/**
	 * 列表形式下单页面
	 * 
	 * @Description: TODO
	 * @param
	 * @return ModelAndView
	 * @author Andy
	 * @date 2015-10-5
	 */
	@RequestMapping("/orderIndex")
	public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("m/cp/templ/lottery_order");
		UserContext uc = this.getUserContext(request);

		String gameTypeCode = request.getParameter("gameTypeCode");
		String cpTypeCode = request.getParameter("cpTypeCode");

		String cpCateCode = request.getParameter("cpCateCode");
		String xzlxCode = request.getParameter("xzlxCode");

		String gameTypeName = CpConfigCache.CP_GAME_MAP.get(gameTypeCode);
		mv.addObject("gameTypeName", gameTypeName);

		/*** 彩票类型 ****/
		String mapKey = gameTypeCode + "_" + cpTypeCode;
		CpType cpType = CpConfigCache.CP_TYPE_MAP.get(mapKey);
		mv.addObject("cpType", cpType);

		/*** 开奖信息 ***/
		CpTomResults nextResult = cpResultsService.getNextResults(gameTypeCode);
		mv.addObject("nextResult", nextResult);
		/*** 用户金额 ***/
		if (null != uc) {
			double userMoney = webUserService.getWebUserMoney(uc.getUserName());
			mv.addObject("userMoney", userMoney);
		}

		/*** CP信息 ***/
		List<CpConfig> confList = cpConfigService.getConfigListForCode(gameTypeCode, cpTypeCode, cpCateCode, xzlxCode);

		mv.addObject("confList", confList);
		return mv;
	}

	/**
	 * 三字定位页面跳转
	 * 
	 * @Description: TODO
	 * @param
	 * @return ModelAndView
	 * @author Andy
	 * @date 2015-10-5
	 */
	@RequestMapping("/szdwOrderPage")
	public ModelAndView szdwOrderPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("m/cp/templ/lottery_order_szdw");
		String gameTypeCode = request.getParameter("gameTypeCode");
		String cpTypeCode = request.getParameter("cpTypeCode");
		UserContext uc = this.getUserContext(request);

		String gameTypeName = CpConfigCache.CP_GAME_MAP.get(gameTypeCode);
		mv.addObject("gameTypeName", gameTypeName);

		/*** 彩票类型 ****/
		String mapKey = gameTypeCode + "_" + cpTypeCode;
		CpType cpType = CpConfigCache.CP_TYPE_MAP.get(mapKey);
		mv.addObject("cpType", cpType);

		/*** 开奖信息 ***/

		CpTomResults nextResult = cpResultsService.getNextResults(gameTypeCode);
		mv.addObject("nextResult", nextResult);
		/*** 用户金额 ***/
		if (null != uc) {
			double userMoney = webUserService.getWebUserMoney(uc.getUserName());
			mv.addObject("userMoney", userMoney);
		}
		return mv;
	}

	/**
	 * 三字定位页面配置信息加载
	 * 
	 * @Description: TODO
	 * @param
	 * @return ModelAndView
	 * @author Andy
	 * @date 2015-10-5
	 */
	@RequestMapping("/getConfForNumber")
	public void getConfForNumber(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject jsonMessage = new JSONObject();
		int resultCode = 400;
		String gameTypeCode = request.getParameter("gameTypeCode");
		String cpTypeCode = request.getParameter("cpTypeCode");
		String cpCateCode = request.getParameter("cpCateCode");
		String xzlxCode = request.getParameter("xzlxCode");
		String number = request.getParameter("number");

		CpConfig conf = new CpConfig();
		List<CpConfig> confList = new ArrayList<CpConfig>();
		try {
			confList = this.cpConfigService.getConfigListForNumber(gameTypeCode, cpTypeCode, cpCateCode, xzlxCode, number);
			if (null == confList || confList.size() < 1) {
				resultCode = 0;
			} else {
				conf = confList.get(0);
				jsonMessage.put("id", conf.getId());
				jsonMessage.put("num", conf.getNumber());
				jsonMessage.put("rate", conf.getPl());
				jsonMessage.put("cateName", conf.getCpCateName());
				jsonMessage.put("xzlxName", conf.getXzlxName() == null ? "" : conf.getXzlxName());
				jsonMessage.put("number", number);
			}
			resultCode = 200;
		} catch (Exception e) {
			resultCode = 400;
			logger.error("三字定位获取配置异常:" + number + ",参数信息:" + gameTypeCode + "||" + cpTypeCode + "||" + cpCateCode + "||" + xzlxCode, e);
		} finally {
			jsonMessage.put("resultCode", resultCode);
			responseSendMessage(response, jsonMessage.toString());
		}
	}

	/**
	 * 跳转到订单列表 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param gameTypeCode
	 * @param cpTypeCode
	 * @param cateName
	 * @return ModelAndView
	 */
	@RequestMapping("/goOrderList")
	public ModelAndView goOrderList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		double total = 0;// 下注总金额
		List<CpOrder> orderList = new ArrayList<CpOrder>();
		String cpTypeCode = request.getParameter("cpTypeCode");
		String gameTypeCode = request.getParameter("gameTypeCode");
		String gameTypeName = request.getParameter("gameTypeName");
		String tmsb = request.getParameter("tmsb");
		String jsonDatas = request.getParameter("jsonDatas");
		JSONArray jsonArray = new JSONArray();

		jsonArray = JSONArray.parseArray(jsonDatas);
		for (int i = 0; i < jsonArray.size(); i++) {
			CpOrder order = new CpOrder();
			JSONObject json = jsonArray.getJSONObject(i);
			String cfgId = json.getString("id");
			String rate = json.getString("rate");
			String val = json.getString("val");
			String number = json.getString("num");
			String cateName = json.getString("cateName");
			String xlzxName = json.getString("xzlxName");

			double xzje = Double.parseDouble(val);
			double pl = Double.parseDouble(rate);

			double zgje = MathUtil.mul(xzje, pl);
			double kyje = MathUtil.sub(zgje, xzje);

			order.setCfgId(cfgId);
			order.setPl(rate);
			order.setNumber(number);
			order.setXzje(xzje);
			order.setKyje(kyje);
			order.setZgje(zgje);

			StringBuffer content = new StringBuffer();
			if (StringUtils.isNotBlank(cateName)) {
				content.append(cateName);
			}
			if (StringUtils.isNotBlank(xlzxName)) {
				content.append("&" + xlzxName);
			}
			content.append("-" + number + "@" + "<font color='#CC0000'>" + rate + "</font>");
			order.setContent(content.toString());
			total += xzje;
			orderList.add(order);
		}
		mv.addObject("tmsb",tmsb);
		mv.addObject("total", total);
		mv.addObject("orderList", orderList);
		mv.addObject("gameTypeCode", gameTypeCode);
		mv.addObject("gameTypeName", gameTypeName);
		mv.addObject("cpTypeCode", cpTypeCode);
		/*** 用户金额 ***/
		UserContext uc = getUserContext(request);
		double userMoney = webUserService.getWebUserMoney(uc.getUserName());
		BigDecimal bd = new BigDecimal(userMoney); // 保留两位小数
		mv.addObject("userName", uc.getUserName());
		mv.addObject("userMoney", bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		/*** 彩票类型 ***/
		String mapKey = gameTypeCode + "_" + cpTypeCode;
		CpType cpType = CpConfigCache.CP_TYPE_MAP.get(mapKey);
		mv.addObject("cpType", cpType);
		mv.setViewName("m/cp/lottery_order_confirm");
		return mv;

	}

	/**
	 * 订单提交
	 * 
	 * @Description: TODO
	 * @param
	 * @return resultCode:0-非法注单，-1=用户余额不足.400-异常错误,401-彩票已封盘,200-下注成功
	 * @author Andy
	 * @date 2015-10-2
	 */
	@RequestMapping("/saveOrder")
	public void saveOrder(HttpServletRequest request,HttpServletResponse response) {
		JSONObject jsonMessage = new JSONObject();
		UserContext uc = this.getUserContext(request);
		int resultCode = 200;
		String gameTypeCode = request.getParameter("gameTypeCode");
		String cpTypeCode = request.getParameter("cpTypeCode");
		String ids = request.getParameter("ids");
		String jsonDatas = request.getParameter("jsonDatas");
		//String bigtypeCode = request.getParameter("bigtypeCode");


		Map<String, CpConfig> configMap = new HashMap<String, CpConfig>();
		String message = "";
		try {
			String status = WebSiteManagerConstants.ctxMap.get(gameTypeCode.toUpperCase());
			if ("0".equals(status)) {//判断是否维护
				jsonMessage.put("message", WebSiteManagerConstants.ctxMap.get(gameTypeCode.toUpperCase() + "_" + WebConstants.WEB_WEIHU_STATUS_OFF));
				resultCode = 110;
			} else {
				String cp_time_is_open = CommonConstant.resCommMap.get("CP_TIME_IS_OPEN");//检查开奖时间类型 0北京时间 1美东时间
				// 获取下一期期数
				CpTomResults nextResult = cpResultsService.getNextResults(gameTypeCode);
				boolean isClose = false;
				if (null != nextResult) {
					if (StringUtils.isNotBlank(cp_time_is_open) && "0".equals(cp_time_is_open)) {
						isClose = checkKjsj(gameTypeCode,nextResult.getKjsj());
					} else {// 1美东时间
						isClose = checkKjsj(gameTypeCode,nextResult.getGtKjsj());
					}
				}

				if (isClose) {// 未封盘
					JSONArray jsonArray = JSONArray.parseArray(jsonDatas);
					WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());

					configMap = this.cpConfigService.getCpConfigAllByIds(ids,gameTypeCode,cpTypeCode);
					
					CpType type = CpConfigCache.CP_TYPE_MAP.get(gameTypeCode + "_" + cpTypeCode);
					CpGame game = CpConfigCache.GAME_OBJ_CACHE_MAP.get(gameTypeCode);
					
					checkIsEnable(type, game);//检查盘口是否停用
					
					String tmsb = request.getParameter("tmsb");
					validOrderInfo(configMap, webUser, jsonArray,tmsb, type,nextResult);//检查注单数据正确性
					
					resultCode = this.cpOrderService.saveMobileCpOrder(request,jsonArray, configMap, webUser,nextResult);//提交注单
				} else {
					resultCode = 401;
				}
			}

		} catch (Exception e) {
			resultCode = 400;
			message = e.getMessage();
			if(StringUtils.isEmpty(message)){
				message = "提交订单异常";
			}
			logger.error("提交订单异常:", e);
			e.printStackTrace();
		} finally {
			jsonMessage.put("message", message);
			jsonMessage.put("resultCode", resultCode);
			responseSendMessage(response, jsonMessage.toString());
		}
	}

	private void checkIsEnable(CpType type, CpGame game) throws Exception {
		if((int)game.getIsEnable() == (int)CpCommonConstant.IS_ENABLE){//验证大盘口是否启用
			throw new Exception(game.getGameTypeName() + "已进入暂时维护状态,抱歉!");
		}else{//验证小盘口是否启用
			if((int)type.getIsEnable() == (int)CpCommonConstant.IS_ENABLE){
				throw new Exception(type.getCpTypeName() + "已进入暂时维护状态,抱歉!");
			}
		}
	}

	/**
	 * 检查注单数据正确性
	 * @param configMap
	 * @param webUser
	 * @param jsonArray
	 * @param tmsb
	 * @param type
	 * @throws Exception
	 */
	private void validOrderInfo(Map<String, CpConfig> configMap,WebUser webUser, JSONArray jsonArray, String tmsb, CpType type,CpTomResults nextResult) throws Exception {
		double sumMoney = 0;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject json = jsonArray.getJSONObject(i);
			String cfgId = json.getString("id");
			CpConfig config = configMap.get(cfgId);
			if(config.getEnumCode().equals(CpCommonConstant.TMSB)){
				if(!checkTmsb(tmsb)){
					throw new Exception("特码包三号码异常！！");
				}
			}
			double value = json.getDoubleValue("val");
			if(null != config.getGminSingle() || null != config.getGmaxSingle()){//config没有配置最低最高限额就去cptype配置表查询
				if(config.getGminSingle() > value){
					throw new Exception("号码"+config.getEnumCode()+"单注最低限额为：" + config.getGminSingle() + "元");
				}
				if(config.getGmaxSingle() < value){
					throw new Exception("号码"+config.getEnumCode()+"单注最高限额为：" + config.getGmaxSingle() + "元");
				}
			}else{
				if(type.getGminSingle() > value || type.getGmaxSingle() < value){
					throw new Exception("单注最低限额为：" + type.getGminSingle() + "元");
				}
				if(type.getGmaxSingle() < value){
					throw new Exception("单注最高限额为：" + type.getGmaxSingle() + "元");
				}
			}
			sumMoney += value;//金额总额
			
			CpOrder order = new CpOrder();
			order.setUserName(webUser.getUserName());
			order.setQs(nextResult.getQs());
			order.setGameTypeCode(config.getGameTypeCode());
			order.setCpTypeCode(config.getCpTypeCode());
			order.setCpCateCode(config.getCpCateCode());
			order.setXzlxCode(config.getXzlxCode());
			order.setXzzuCode(config.getXzzuCode());
			order.setNumber(config.getNumber());
			double totalXzje = this.cpOrderService.getUserSingleCreditForNumber(order);
			if((totalXzje + value) > config.getSingleCredit()){
				throw new Exception("号码"+config.getNumber()+"当期限额:" + config.getSingleCredit() + "元！您下注累积金额为:" + (totalXzje + value) + "元");
			}
		}
		double userMoney = webUserService.getWebUserMoney(webUser.getUserName());
		if(sumMoney > userMoney){
			throw new Exception("用户"+webUser.getUserName()+"下注总额为:" + sumMoney + "元，账户余额为" + webUser.getUserMoney() + "元");
		}
	}
	
	/**
	 * 验证特码包三数据格式正确性
	 * @param tmsb
	 * @return
	 */
	private boolean checkTmsb(String tmsb){
		if(StringUtils.isEmpty(tmsb)){
			return false;
		}
		String[] num = tmsb.split(",");
		if(3 != num.length){
			return false;
		}
		Pattern p = Pattern.compile("[0-9]*");
		if(!p.matcher(num[0]).matches() || !p.matcher(num[1]).matches() || !p.matcher(num[2]).matches()){
			return false;
		}
		int num1 = Integer.parseInt(num[0]);
		int num2 = Integer.parseInt(num[1]);
		int num3 = Integer.parseInt(num[2]);
		if(num1 < 0 || num2 < 0 || num3 < 0){
			return false;
		}
		if(num1 > 27 || num2 > 27 || num3 > 27){
			return false;
		}
		if((num1 ==  num2) || (num2 == num3) || (num1 == num3)){
			return false;
		}
		return true;
	}

	// 封盘时间验证
	private boolean checkKjsj(String gameTypeCode, String kjsj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
		Date date2 = null;
		try {
			// String closeType="CP_"+gameTypeCode+"_CLOSE_TIME";
			Integer closeS = CpConfigCache.GAME_OBJ_CACHE_MAP.get(gameTypeCode).getCloseS();
			Integer closeM = CpConfigCache.GAME_OBJ_CACHE_MAP.get(gameTypeCode).getCloseM() * 60;
			// String
			// closeTime=CommonConstant.resCommMap.get(closeType.toUpperCase());
			long ct = (closeS + closeM) * 1000;
			date2 = sdf.parse(kjsj);
			long dt1 = date1.getTime();
			long dt2 = date2.getTime();
			if (dt2 - dt1 > ct) {
				return true;
			}
		} catch (ParseException e) {
			logger.error("开奖时间出现错误,请速检查开奖时间、封盘时间等配置!");
		}
		return false;
	}

	public CpResultsService getCpResultsService() {
		return cpResultsService;
	}

	public void setCpResultsService(CpResultsService cpResultsService) {
		this.cpResultsService = cpResultsService;
	}

	public WebUserService getWebUserService() {
		return webUserService;
	}

	public void setWebUserService(WebUserService webUserService) {
		this.webUserService = webUserService;
	}

	public CpConfigService getCpConfigService() {
		return cpConfigService;
	}

	public void setCpConfigService(CpConfigService cpConfigService) {
		this.cpConfigService = cpConfigService;
	}

	public CpOrderService getCpOrderService() {
		return cpOrderService;
	}

	public void setCpOrderService(CpOrderService cpOrderService) {
		this.cpOrderService = cpOrderService;
	}
}
