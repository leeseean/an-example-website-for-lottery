/**   
 * 文件名称: WebGameController.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: alex<br/>  
 * 创建时间 : 2015-7-11 上午9:57:14<br/>
 */
package com.mh.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.mh.client.FlatClient;
import com.mh.commons.cache.CpConfigCache;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.ResourceConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.SecurityEncode;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.WebGdElectronic;
import com.mh.entity.WebGongGao;
import com.mh.entity.WebMgElectronic;
import com.mh.entity.WebNewNtElectronic;
import com.mh.entity.WebNtElectronic;
import com.mh.entity.WebOsElectronic;
import com.mh.entity.WebPtElectronic;
import com.mh.entity.WebTtgElectronic;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.NSBIfcUtil;
import com.mh.ifc.SBTIfcUtil;
import com.mh.ifc.http.NsbConts;
import com.mh.ifc.http.NsbResResult;
import com.mh.ifc.http.SbtApiConstants;
import com.mh.ifc.http.SbtResBean;
import com.mh.service.WebUserFlatService;
import com.mh.service.WebUserService;
import com.mh.web.login.UserContext;
import com.mh.web.servlet.MySessionContext;

/**
 * 
 * 游戏平台Controller 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-11 上午9:57:14<br/>
 */
@Controller
public class WebGameController extends BaseController {

	@Autowired
	private WebUserFlatService webUserFlatService;

	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private FlatClient flatClient;

	/**
	 * 跳转到相关页面 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param code
	 * @return ModelAndView
	 */
	@RequestMapping("/goPageCenter")
	public ModelAndView goPageCenter(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("code") String code) {
		/** 以下的webUser用途、公告不能从数据库取 **/
		WebUser webUser = null;
		if (MySessionContext.getSession(request.getSession().getId()) != null) {
			UserContext uc = this.getUserContext(request);
			if (uc != null) {
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		ModelAndView mv = new ModelAndView("web/" + code);

		mv.addObject("dataList", dataList);
		mv.addObject("webUser", webUser);
		mv.addObject("lottery_list", CpConfigCache.GAME_CACHE_MAP);//彩票类型

		if ("help".equals(code)) {
			mv.addObject("wpList", WebSiteManagerConstants.WEBPAGE_LIST);
		} else if ("promos".equals(code)) {
			mv.addObject("ptList", WebSiteManagerConstants.WEBPROMOSTYPE_LIST);
			mv.addObject("lbPromoList", WebSiteManagerConstants.LbWEBPROMOS_LIST);
		} else if("sb".equals(code)){
			/**判断是否维护**/
			String msg = WebSiteManagerConstants.ctxMap.get(CommonConstant.PLAT_PARAM_SB+"_"+WebConstants.WEB_WEIHU_STATUS_OFF);
			if(StringUtils.isNotBlank(msg)){
				mv.setViewName("sport/weihu");
				return mv;
			}
			String backUrl = this.sb(request, response);
			mv.setViewName("web/sport_sb");
			mv.addObject("backToUrl", backUrl);
			return new ModelAndView("redirect:"+backUrl);
		}else if("sbt".equals(code)){
			/**判断是否维护**/
			String msg = WebSiteManagerConstants.ctxMap.get(CommonConstant.PLAT_PARAM_SBT+"_"+WebConstants.WEB_WEIHU_STATUS_OFF);
			if(StringUtils.isNotBlank(msg)){
				mv.addObject("msg", msg);
				mv.setViewName("sport/weihu");
				return mv;
			}
			String backUrl = this.sbt(request);
			mv.setViewName("web/sport_sbt");
			mv.addObject("backToUrl", backUrl);
		}else if("lottery".equals(code)){//彩票
			String cpparam = request.getParameter("cpparam");
			if(StringUtils.isNotEmpty(cpparam)){
				mv.addObject("cpparam", cpparam);
			}
		}
		return mv;
	}
	
	
	@RequestMapping("/hg/goPageCenter")
	public ModelAndView gohgPageCenter(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("code") String code) {
		/** 以下的webUser用途、公告不能从数据库取 **/
		WebUser webUser = null;
		if (MySessionContext.getSession(request.getSession().getId()) != null) {
			UserContext uc = this.getUserContext(request);
			if (uc != null) {
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
		
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		ModelAndView mv = new ModelAndView("web/hgPlay/" + code);

		mv.addObject("dataList", dataList);
		mv.addObject("webUser", webUser);

		if ("help".equals(code)) {
			mv.addObject("wpList", WebSiteManagerConstants.WEBPAGE_LIST);
		} else if ("promos".equals(code)) {
			mv.addObject("ptList", WebSiteManagerConstants.WEBPROMOSTYPE_LIST);
			mv.addObject("lbPromoList", WebSiteManagerConstants.LbWEBPROMOS_LIST);
		}
		return mv;
	}

	/**
	 * 电子游戏平台入口 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param code
	 * @return ModelAndView
	 */
	@RequestMapping("/electronic")
	public ModelAndView electronic(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) {
		ModelAndView view = new ModelAndView("web/" + code + "Electronic");
		String gameType1 = request.getParameter("gameType1");
		String gameType2 = request.getParameter("gameType2");

		if (WebConstants.FLAT_NAME_MG.equals(code)) {
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1 + (StringUtils.isEmpty(gameType2) ? "" : "_" + gameType2);
			view.addObject("result", ResourceConstant.MG_ELECTRONIC_LIST.get(key));
			view.addObject("gameType1", gameType1);
			view.addObject("gameType2", gameType2);
		}else if(WebConstants.FLAT_NAME_TTG.equals(code)){
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1 + (StringUtils.isEmpty(gameType2) ? "" : "_" + gameType2);
			view.addObject("result", ResourceConstant.TTG_ELECTRONIC_LIST.get(key));
			view.addObject("gameType1", gameType1);
			view.addObject("gameType2", gameType2);
		}else if(WebConstants.FLAT_NAME_GD.equals(code)){
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1 + (StringUtils.isEmpty(gameType2) ? "" : "_" + gameType2);
			view.addObject("result", ResourceConstant.GD_ELECTRONIC_LIST.get(key));
			view.addObject("gameType1", gameType1);
			view.addObject("gameType2", gameType2);
		} 
		
		else if (WebConstants.FLAT_NAME_NT.equals(code)) {
			view.addObject("result", ResourceConstant.NT_ELECTRONIC_LIST);
		} else if (WebConstants.FLAT_NAME_PT.equals(code)) {
			String key =( StringUtils.isEmpty(gameType1) ? "all" : gameType1);
			
			view.addObject("result", ResourceConstant.PT_ELECTRONIC_LIST.get(key));
		} else if(WebConstants.FLAT_NAME_OS.equals(code)){
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1;
			view.addObject("result", ResourceConstant.OS_ELECTRONIC_LIST.get(key));
			view.addObject("gameType1", gameType1);
			view.addObject("gameType2", gameType2);
		} else if(WebConstants.FLAT_NAME_NEWNT.equals(code)){
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1;
			view.addObject("result", ResourceConstant.NEWNT_ELECTRONIC_LIST.get(key));
			view.addObject("gameType1", gameType1);
			view.addObject("gameType2", gameType2);
		}

		WebUser webUser = null;
		if (MySessionContext.getSession(request.getSession().getId()) != null) {
			UserContext uc = this.getUserContext(request);
			if (uc != null) {
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		return view.addObject("code", code).addObject("webUser", webUser)
				.addObject("dataList", dataList)
				.addObject("gameType1", gameType1)
				.addObject("gameType2", gameType2)
				.addObject("webUser", webUser);
	}
	
	/**
	 * 电子游戏平台入口 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param code
	 * @return ModelAndView
	 */
	@RequestMapping("/hg/electronic")
	public ModelAndView hgelectronic(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) {
		ModelAndView view = new ModelAndView("web/hgPlay/" + code + "Electronic");
		String gameType1 = request.getParameter("gameType1");
		String gameType2 = request.getParameter("gameType2");

		if (WebConstants.FLAT_NAME_MG.equals(code)) {
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1 + (StringUtils.isEmpty(gameType2) ? "" : "_" + gameType2);
			view.addObject("result", ResourceConstant.MG_ELECTRONIC_LIST.get(key));
			view.addObject("gameType1", gameType1);
			view.addObject("gameType2", gameType2);
		} else if (WebConstants.FLAT_NAME_NT.equals(code)) {
			view.addObject("result", ResourceConstant.NT_ELECTRONIC_LIST);
		} else if (WebConstants.FLAT_NAME_PT.equals(code)) {
			view.addObject("result", ResourceConstant.PT_ELECTRONIC_LIST);
		}

		WebUser webUser = null;
		if (MySessionContext.getSession(request.getSession().getId()) != null) {
			UserContext uc = this.getUserContext(request);
			if (uc != null) {
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		return view.addObject("code", code).addObject("webUser", webUser)
				.addObject("dataList", dataList)
				.addObject("gameType1", gameType1)
				.addObject("gameType2", gameType2)
				.addObject("webUser", webUser);
	}
	
	
	@RequestMapping("/yzc_electronic")
	public ModelAndView yzcElectronic(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) throws UnsupportedEncodingException {
		ModelAndView view = new ModelAndView("web/" + code + "Electronic");
		String gameType1 = request.getParameter("gameType1");
		String gameType2 = request.getParameter("gameType2");
		String status = request.getParameter("status");
		String gameName = request.getParameter("gameName");
		if(StringUtils.isNotEmpty(gameName)){
			gameName = new String(gameName.getBytes("ISO-8859-1"),"utf-8");
		}
		String search = request.getParameter("searchType");
		if (WebConstants.FLAT_NAME_MG.equals(code)) {
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1 + (StringUtils.isEmpty(gameType2) ? "" : "_" + gameType2);
			if(StringUtils.isNotEmpty(search)){
				List<WebMgElectronic> mgList = new ArrayList<WebMgElectronic>();
				Iterator<WebMgElectronic> iterator = ResourceConstant.MG_ELECTRONIC_LIST.get(key).iterator();
				while (iterator.hasNext()) {
					WebMgElectronic mg = iterator.next();
					if(mg.getEleGameCnname().contains(gameName)){
						mgList.add(mg);
					}
				}
				view.addObject("result", mgList);
			}else{
				view.addObject("result", ResourceConstant.MG_ELECTRONIC_LIST.get(key));
			}
			view.addObject("mg_all", ResourceConstant.MG_ELECTRONIC_LIST);
		} else if (WebConstants.FLAT_NAME_NT.equals(code)) {
			if(StringUtils.isNotEmpty(search)){
				List<WebNtElectronic> ntList = new ArrayList<WebNtElectronic>();
				Iterator<WebNtElectronic> iterator = ResourceConstant.NT_ELECTRONIC_LIST.iterator();
				while (iterator.hasNext()) {
					WebNtElectronic nt = iterator.next();
					if(nt.getEleGameCnname().contains(gameName)){
						ntList.add(nt);
					}
				}
				view.addObject("result", ntList);
			}else{
				view.addObject("result", ResourceConstant.NT_ELECTRONIC_LIST);
			}
		} else if (WebConstants.FLAT_NAME_PT.equals(code)) {
			String key =( StringUtils.isEmpty(gameType1) ? "all" : gameType1);
			if(StringUtils.isNotEmpty(search)){
				List<WebPtElectronic> ptList = new ArrayList<WebPtElectronic>();
				Iterator<WebPtElectronic> iterator = ResourceConstant.PT_ELECTRONIC_LIST.get(key).iterator();
				while (iterator.hasNext()) {
					WebPtElectronic pt = iterator.next();
					if(pt.getEleGameCnname().contains(gameName)){
						ptList.add(pt);
					}
				}
				view.addObject("result", ptList);
			}else{
				view.addObject("result", ResourceConstant.PT_ELECTRONIC_LIST.get(key));
			}
			view.addObject("pt_all", ResourceConstant.PT_ELECTRONIC_LIST);
		} else if(WebConstants.FLAT_NAME_OS.equals(code)){
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1;
			if(StringUtils.isNotEmpty(search)){
				List<WebOsElectronic> osList = new ArrayList<WebOsElectronic>();
				Iterator<WebOsElectronic> iterator = ResourceConstant.OS_ELECTRONIC_LIST.get(key).iterator();
				while (iterator.hasNext()) {
					WebOsElectronic os = iterator.next();
					if(os.getEleGameCnname().contains(gameName)){
						osList.add(os);
					}
				}
				view.addObject("result", osList);
			}else{
				view.addObject("result", ResourceConstant.OS_ELECTRONIC_LIST.get(key));
			}
			view.addObject("os_all", ResourceConstant.OS_ELECTRONIC_LIST);
		} else if(WebConstants.FLAT_NAME_TTG.equals(code)){		
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1;
			if(StringUtils.isNotEmpty(search)){
				List<WebTtgElectronic> ttgList = new ArrayList<WebTtgElectronic>();
				Iterator<WebTtgElectronic> iterator = ResourceConstant.TTG_ELECTRONIC_LIST.get(key).iterator();
				while (iterator.hasNext()) {
					WebTtgElectronic ttg = iterator.next();
					if(ttg.getEleGameCnname().contains(gameName)){
						ttgList.add(ttg);
					}
				}
				view.addObject("result", ttgList);
			}else{
				view.addObject("result", ResourceConstant.TTG_ELECTRONIC_LIST.get(key));
			}
			view.addObject("ttg_all", ResourceConstant.TTG_ELECTRONIC_LIST);
		
		} else if (WebConstants.FLAT_NAME_GD.equals(code)) {
			
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1;
			if(StringUtils.isNotEmpty(search)){
				List<WebGdElectronic> gdList = new ArrayList<WebGdElectronic>();
				Iterator<WebGdElectronic> iterator = ResourceConstant.GD_ELECTRONIC_LIST.get(key).iterator();
				while (iterator.hasNext()) {
					WebGdElectronic gd = iterator.next();
					if(gd.getEleGameCnname().contains(gameName)){
						gdList.add(gd);
					}
				}
				view.addObject("result", gdList);
			}else{
				view.addObject("result", ResourceConstant.GD_ELECTRONIC_LIST.get(key));
			}
			view.addObject("gd_all", ResourceConstant.GD_ELECTRONIC_LIST);
		} else if (WebConstants.FLAT_NAME_NEWNT.equals(code)) {
			
			String key = StringUtils.isEmpty(gameType1) ? "all" : gameType1;
			if(StringUtils.isNotEmpty(search)){
				List<WebNewNtElectronic> nntList = new ArrayList<WebNewNtElectronic>();
				Iterator<WebNewNtElectronic> iterator = ResourceConstant.NEWNT_ELECTRONIC_LIST.get(key).iterator();
				while (iterator.hasNext()) {
					WebNewNtElectronic nnt = iterator.next();
					if(nnt.getEleGameCnname().contains(gameName)){
						nntList.add(nnt);
					}
				}
				view.addObject("result", nntList);
			}else{
				view.addObject("result", ResourceConstant.NEWNT_ELECTRONIC_LIST.get(key));
			}
			view.addObject("newnt_all", ResourceConstant.NEWNT_ELECTRONIC_LIST);
		}

		WebUser webUser = null;
		if (MySessionContext.getSession(request.getSession().getId()) != null) {
			UserContext uc = this.getUserContext(request);
			if (uc != null) {
				webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
			}
		}
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		return view.addObject("code", code).addObject("webUser", webUser)
				.addObject("dataList", dataList)
				.addObject("gameType1", gameType1)
				.addObject("gameType2", gameType2)
				.addObject("webUser", webUser).addObject("status", status).addObject("gameName", gameName);
	}
	

	/**
	 * 游戏平台入口 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param gameType
	 * @return ModelAndView
	 */
	@RequestMapping("/forwardGame")
	public ModelAndView forwardGame(HttpServletRequest request, HttpServletResponse response, @RequestParam("gameType") String gameType) {
		ModelAndView view = flatClient.flatLogin(request, gameType);
		return view;
		/*basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";

		String backToUrl = "";
		if (StringUtils.equalsIgnoreCase(gameType, "bbin")) {
			backToUrl = this.newBBin(request);
			return new ModelAndView("redirect:"+backToUrl);
		} else if (StringUtils.equalsIgnoreCase(gameType, "ag")) {
			backToUrl = this.ag(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "ds")) {
			backToUrl = this.ds(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "hg")) {
			backToUrl = this.hg(request);
			return new ModelAndView("redirect:"+backToUrl);
		} else if (StringUtils.equalsIgnoreCase(gameType, "mgLive")) {
			backToUrl = this.mgLive(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "mgElectronic")) {
			backToUrl = this.mgElectronic(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "ntElectronic")) {
			backToUrl = this.ntElectronic(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "ptElectronic")) {
			backToUrl= this.newPtElectronic(request);
			return new ModelAndView("redirect:"+backToUrl);
		} else if (StringUtils.equalsIgnoreCase(gameType, "douji")) {
			backToUrl = this.dj(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "ab")) {
			backToUrl = this.ab(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "og")) {
			backToUrl = this.og(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "osElectronicReal")) {
			backToUrl = this.osElectronicReal(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, "sb")) {
			backToUrl = this.sb(request, response);
		} else if (StringUtils.equalsIgnoreCase(gameType, "gd")) {
			backToUrl = this.gd(request);
			String gameCode = request.getParameter("game");
			if(StringUtils.isNotEmpty(gameCode)){
				backToUrl += "&view="+gameCode;
			}
		} else if (StringUtils.equalsIgnoreCase(gameType, "ttg")) {
			backToUrl = this.ttg(request);
		} else if(StringUtils.equalsIgnoreCase(gameType, "sbt")){
			backToUrl = this.sbt(request);
		} else if(StringUtils.equalsIgnoreCase(gameType, "nnt")){
			backToUrl = this.nnt(request);
		}
		logger.info(backToUrl);

		return new ModelAndView("web/frameUrl").addObject("backToUrl", backToUrl);*/
	}
	
	/**
	 * SB 方法描述: TODO</br>
	 * 
	 * @param request
	 * @return String
	 */
	private String sb(HttpServletRequest request, HttpServletResponse response) {
		try {
			String typeCode = request.getParameter("typeCode");
			String url = "";
			String _p = "";
			UserContext uc = this.getUserContext(request);
			if(uc==null){
				if("sportbook".equals(typeCode)){
					url = CommonConstant.resCommMap.get(CommonConstant.SB_SPORTBOOK_LOGIN_URL);
					url += "/vender.aspx?lang=cs";
			    }
				return url;
			}
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(uc.getUserName());

			if (StringUtils.isEmpty(webUserFlat.getSbUserName())) {
				webUserFlat = NSBIfcUtil.registSbAccout(webUserFlat);
				if (webUserFlat.getSbStatus() != null && webUserFlat.getSbStatus().intValue() == 1) {
					this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}

			url = CommonConstant.resCommMap.get(CommonConstant.PT_LOGINAUT_URL);
			Map<String, String> params = new HashMap<String, String>();
			params.put(NsbConts.PLAYERNAME, webUserFlat.getSbUserName());// 游戏帐号
			NsbResResult result = NSBIfcUtil.login(params);
			if (result != null && NsbConts.RES_CODE_0.equals(result.getError_code())) {
				String token = result.getSessionToken();
				Double blance = 0.0;
				result = NSBIfcUtil.balance(params);
				if (result != null && NsbConts.RES_CODE_0.equals(result.getError_code())) {
					String data = result.getData();
					JSONArray array = JSONArray.parseArray(data);
					String _blance = array.getJSONObject(0).get("balance").toString();
					blance = Double.valueOf(_blance);
				}
				StringBuffer buff = new StringBuffer();
				
				buff.append(token);
				buff.append("|");
				buff.append(typeCode);
				buff.append("|");
				buff.append(blance);
				try {
					logger.info("沙巴加密参数:"+buff.toString());
					_p = SecurityEncode.encoderByDES(buff.toString(),SecurityEncode.key);
					logger.info("沙巴加密后参数:"+_p);
				} catch (Exception e) {
					e.printStackTrace();
				}
				url += "/spd/index/"+_p+"?lang=cs";
				return url;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * sbt
	 * @param request
	 * @return
	 */
	private String sbt(HttpServletRequest request) {
		try {

			UserContext uc = this.getUserContext(request);
			if(null == uc){
				Map<String, String> params = new HashMap<String, String>();
				params.put(SbtApiConstants.USER_ID, "");// 游戏帐号
				SbtResBean result = SBTIfcUtil.login(params);
				if (result != null) {
					String url = CommonConstant.resCommMap.get(CommonConstant.SBT_LOGIN_URL);
					url += "?stoken=" + CommonConstant.resCommMap.get(CommonConstant.MARCHID) +"_"+ result.getToken();
					url += "&langid=" + SbtApiConstants.LANGUAGE_CODE;
					url += "&oddsstyleid=" + SbtApiConstants.ODDS_STYLE;
					return url;
				}
			}
			WebUserFlat webUserFlat = this.webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getSbtUserName())) {
				webUserFlat = SBTIfcUtil.registSbtAccout(webUserFlat);
				if (webUserFlat.getSbtStatus() != null && webUserFlat.getSbtStatus().intValue() == 1) {
					this.webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put(SbtApiConstants.USER_ID, webUserFlat.getSbtUserName());// 游戏帐号
			SbtResBean result = SBTIfcUtil.login(params);
			if (result != null) {
				return result.getLoginUrl();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping("/valid/checkflatsatus")
	public void checkFlatSatus(HttpServletRequest request, HttpServletResponse response){
		String type = request.getParameter("code");
		String msg = "";
		boolean status = false;
		msg = WebSiteManagerConstants.ctxMap.get(type+"_"+WebConstants.WEB_WEIHU_STATUS_OFF);
		if(StringUtils.isNotBlank(msg)){
			status = true;
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("status", status);
		dataMap.put("msg", msg);
		
		
		//判断用户
		if(!status){
			List<String> list = new ArrayList<String>();
			list.add(CommonConstant.PLAT_PARAM_AG);
			list.add(CommonConstant.PLAT_PARAM_BBIN);
			list.add(CommonConstant.PLAT_PARAM_DS);
			list.add(CommonConstant.PLAT_PARAM_HG);
			list.add(CommonConstant.PLAT_PARAM_MG);
			list.add(CommonConstant.PLAT_PARAM_NT);
			list.add(CommonConstant.PLAT_PARAM_PT);
			list.add(CommonConstant.PLAT_PARAM_HUANGGUAN);
			list.add(CommonConstant.PLAT_PARAM_CAIPIAO);
			list.add(CommonConstant.PLAT_PARAM_DOUJI);
			list.add(CommonConstant.PLAT_PARAM_AB);
			list.add(CommonConstant.PLAT_PARAM_OG);
			list.add(CommonConstant.PLAT_PARAM_OS);
			list.add(CommonConstant.PLAT_PARAM_SB);
			list.add(CommonConstant.PLAT_PARAM_GD);
			list.add(CommonConstant.PLAT_PARAM_TTG);
			list.add(CommonConstant.PLAT_PARAM_SBT);
			list.add(CommonConstant.PLAT_PARAM_NEWNT);
			list.add(CommonConstant.PLAT_PARAM_AGFISH);
			list.add(CommonConstant.PLAT_PARAM_SA);
			list.add(CommonConstant.PLAT_PARAM_VG);
			
			if(list.contains(type)){
				UserContext uc = this.getUserContext(request);
				
				if(uc == null && type.equals(CommonConstant.PLAT_PARAM_SBT) || type.equals(CommonConstant.PLAT_PARAM_CAIPIAO)){//彩票 sbt可以不登陆
					ServletUtils.outPrintSuccess(request, response,dataMap);
					return;
				}
				
				if(uc==null){
					if(CommonConstant.PLAT_PARAM_HUANGGUAN.equals(type) || CommonConstant.PLAT_PARAM_SB.equals(type)){
						dataMap.put("status", false);
					}else{
						dataMap.put("status", true);
					}
					
					dataMap.put("msg", "请先登录！");
					ServletUtils.outPrintSuccess(request, response,dataMap);
					return;
				}
				WebUser webUser = this.webUserService.findWebrUseByUserName(uc.getUserName());
				if(webUser!=null && webUser.getBetFlat()!=null && !"".equals(webUser.getBetFlat())){
					String betFlat = webUser.getBetFlat();
					if(betFlat.indexOf(type)!=-1){
						dataMap.put("status", true);
						dataMap.put("msg", "无权限访问该平台，请跟管理员联系！");
					}
				}
			}
			
		}
		ServletUtils.outPrintSuccess(request, response,dataMap);
	}


	public WebUserFlatService getWebUserFlatService() {
		return webUserFlatService;
	}


	public void setWebUserFlatService(WebUserFlatService webUserFlatService) {
		this.webUserFlatService = webUserFlatService;
	}


	public WebUserService getWebUserService() {
		return webUserService;
	}


	public void setWebUserService(WebUserService webUserService) {
		this.webUserService = webUserService;
	}
}
