package com.mh.client;

import static com.mh.commons.conf.WebConstants.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.service.FlatInterfaceService;
import com.mh.service.WebUserService;
import com.mh.web.login.UserContext;
/**
 * 平台API状态切换
 * @author zhaosi
 *
 */
@Component
public class FlatClient {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private WebUserService webUserService;
	
	@Resource(name="newBBinInterfaceServiceImpl")
	private FlatInterfaceService newBBinInterfaceService;
	
	@Resource(name="agInterfaceServiceImpl")
	private FlatInterfaceService agInterfaceService;
	
	@Resource(name="dsInterfaceServiceImpl")
	private FlatInterfaceService dsInterfaceService;
	
	@Resource(name="hgInterfaceServiceImpl")
	private FlatInterfaceService hgInterfaceService;
	
	@Resource(name="mgLiveInterfaceServiceImpl")
	private FlatInterfaceService mgLiveInterfaceService;
	
	@Resource(name="mgElectronicInterfaceServiceImpl")
	private FlatInterfaceService mgElectronicInterfaceService;
	
	@Resource(name="ntInterfaceServiceImpl")
	private FlatInterfaceService ntInterfaceService;
	
	@Resource(name="ptInterfaceServiceImpl")
	private FlatInterfaceService ptInterfaceService;
	
	@Resource(name="djInterfaceServiceImpl")
	private FlatInterfaceService djInterfaceService;
	
	@Resource(name="abInterfaceServiceImpl")
	private FlatInterfaceService abInterfaceService;
	
	@Resource(name="ogInterfaceServiceImpl")
	private FlatInterfaceService ogInterfaceService;
	
	@Resource(name="osInterfaceServiceImpl")
	private FlatInterfaceService osInterfaceService;
	
	@Resource(name="sbInterfaceServiceImpl")
	private FlatInterfaceService sbInterfaceService;
	
	@Resource(name="gdInterfaceServiceImpl")
	private FlatInterfaceService gdInterfaceService;
	
	@Resource(name="ttgInterfaceServiceImpl")
	private FlatInterfaceService ttgInterfaceService;
	
	@Resource(name="sbtInterfaceServiceImpl")
	private FlatInterfaceService sbtInterfaceService;
	
	@Resource(name="newNtInterfaceServiceImpl")
	private FlatInterfaceService newNtInterfaceService;
	
	@Resource(name="saInterfaceServiceImpl")
	private FlatInterfaceService saInterfaceService;
	
	@Resource(name="vgInterfaceServiceImpl")
	private FlatInterfaceService vgInterfaceService;
	
	/**
	 * 登录
	 * @param request
	 * @param gameType
	 * @return
	 */
	public ModelAndView flatLogin(HttpServletRequest request,String gameType){
		String backToUrl = "";
		if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_BBIN)) {
			backToUrl = newBBinInterfaceService.login(request);
			return new ModelAndView("redirect:"+backToUrl);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_AG) || StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_AGFISH)) {
			backToUrl = agInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_DS)) {
			backToUrl = dsInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_HG)) {
			backToUrl = hgInterfaceService.login(request);
			return new ModelAndView("redirect:"+backToUrl);
		} else if (StringUtils.equalsIgnoreCase(gameType, MG_LIVE)) {
			backToUrl = mgLiveInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, MG_ELECTRONIC)) {
			backToUrl = mgElectronicInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, NT_ELECTRONIC)) {
			backToUrl = ntInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, PT_ELECTRONIC)) {
			backToUrl= ptInterfaceService.login(request);
			return new ModelAndView("redirect:"+backToUrl);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_DJ)) {
			backToUrl = djInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_AB)) {
			backToUrl = abInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_OG)) {
			backToUrl = ogInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, OS_ELECTRONIC_REAL)) {
			backToUrl = osInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_SB)) {
			backToUrl = sbInterfaceService.login(request);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_GD)) {
			backToUrl = gdInterfaceService.login(request);
			String gameCode = request.getParameter("game");
			if(StringUtils.isNotEmpty(gameCode)){
				backToUrl += "&view="+gameCode;
			}
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_TTG)) {
			backToUrl = ttgInterfaceService.login(request);
		} else if(StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_SBT)){
			backToUrl = sbtInterfaceService.login(request);
		} else if(StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_NEWNT)){
			backToUrl = newNtInterfaceService.login(request);
		} else if(StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_SA)){
			backToUrl = saInterfaceService.login(request);
			String[] split = backToUrl.split("\\^");
			return new ModelAndView("web/saframeUrl").addObject("url",split[0]).addObject("userName", split[1])
					.addObject("token", split[2]).addObject("lobby", split[3]).addObject("lang", split[4]);
		} else if(StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_VG)){
			backToUrl = vgInterfaceService.login(request);
		}
		logger.info(backToUrl);
		return new ModelAndView("web/frameUrl").addObject("backToUrl", backToUrl);
	}
	
	/**
	 * 手机端登录
	 * @param request
	 * @param gameType
	 * @return
	 */
	public ModelAndView flatMobileLogin(HttpServletRequest request,HttpServletResponse response){
		String gameType = request.getParameter("gameType");
		ModelAndView view = null;
		if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_BBIN)) {
			view = newBBinInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_AG)) {
			view = agInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_MG)) {
			view = mgElectronicInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_PT)) {
			view= ptInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_OS)) {
			view = osInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_SB)) {
			view = sbInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_TTG)) {
			view = ttgInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_HG)){
			view = hgInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_SA)){
			view = saInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_DS)){
			view = dsInterfaceService.mobileLogin(request,response);
		} else if (StringUtils.equalsIgnoreCase(gameType, FLAT_NAME_OS)){
			view = ogInterfaceService.mobileLogin(request,response);
		}
		return view;
	}
	
	/**
	 * 查询平台余额
	 * @param webUserFlat
	 * @param uc
	 * @param flatName
	 * @return 余额
	 * @throws Exception
	 */
	public double searchUserBlance(WebUserFlat webUserFlat,UserContext uc,String flatName) throws Exception{
		double blance = 0.0;
		if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_BBIN)) {
			blance = newBBinInterfaceService.searchUserBlance(webUserFlat);
		} else if (StringUtils.endsWithIgnoreCase(flatName, FLAT_NAME_MG)){
			blance = mgElectronicInterfaceService.searchUserBlance(webUserFlat);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_AG)) {
			blance = agInterfaceService.searchUserBlance(webUserFlat);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_DS)) {
			blance = dsInterfaceService.searchUserBlance(webUserFlat);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_NT)) {
			blance = ntInterfaceService.searchUserBlance(webUserFlat);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_PT)) {
			blance = ptInterfaceService.searchUserBlance(webUserFlat);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_HG)) {
			blance = hgInterfaceService.searchUserBlance(webUserFlat);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_DJ)) {
			blance = djInterfaceService.searchUserBlance(webUserFlat);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_AB)) {
			blance = abInterfaceService.searchUserBlance(webUserFlat);
		} else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_OG)) {
			blance = ogInterfaceService.searchUserBlance(webUserFlat);
		} else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_OS)) {
			blance = osInterfaceService.searchUserBlance(webUserFlat);
		} else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_SB)) {
			blance = sbInterfaceService.searchUserBlance(webUserFlat);
		} else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_GD)){
			blance = gdInterfaceService.searchUserBlance(webUserFlat);
		}else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_TTG)){
			blance = ttgInterfaceService.searchUserBlance(webUserFlat);
		}else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_SBT)){
			blance = sbtInterfaceService.searchUserBlance(webUserFlat);
		}else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_NEWNT)){
			blance = newNtInterfaceService.searchUserBlance(webUserFlat);
		}else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_SA)){
			blance = saInterfaceService.searchUserBlance(webUserFlat);
		}else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_VG)){
			blance = vgInterfaceService.searchUserBlance(webUserFlat);
		}else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_ACCOUNT)) {
			WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
			blance = user.getUserMoney().doubleValue();
		}
		return blance;
	}
	
	/**
	 * 额度转换
	 * @return
	 * @throws RuntimeException
	 */
	public String updateEdu(WebUserFlat webUserFlat,WebUser webUser,String flatName,String optType,int points,WebEdu webEdu) throws RuntimeException{
		String msg = "";
		if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_BBIN)) {
			msg = newBBinInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_MG)) {
			msg = mgElectronicInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_AG)) {
			msg = agInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_DS)) {
			msg = dsInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_NT)) {
			msg = ntInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_PT)) {
			msg = ptInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		} else if (StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_HG)) {
			msg = hgInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		} else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_DJ)) {
			msg = djInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		} else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_AB)) {
			msg = abInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		} else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_OG)) {
			msg = ogInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if(StringUtils.equalsIgnoreCase(flatName, FLAT_NAME_OS)) {
			msg = osInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if(StringUtils.endsWithIgnoreCase(flatName, FLAT_NAME_SB)) {
			msg = sbInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if(StringUtils.endsWithIgnoreCase(flatName, FLAT_NAME_GD)) {
			msg = gdInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if(StringUtils.endsWithIgnoreCase(flatName, FLAT_NAME_TTG)) {
			msg = ttgInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if(StringUtils.endsWithIgnoreCase(flatName, FLAT_NAME_SBT)) {
			msg = sbtInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if(StringUtils.endsWithIgnoreCase(flatName, FLAT_NAME_NEWNT)){
			msg = newNtInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if(StringUtils.endsWithIgnoreCase(flatName, FLAT_NAME_SA)){
			msg = saInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}else if(StringUtils.endsWithIgnoreCase(flatName, FLAT_NAME_VG)){
			msg = vgInterfaceService.updateEdu(webUserFlat, webUser, flatName, optType, points, webEdu);
		}
		return msg;
	}
}
