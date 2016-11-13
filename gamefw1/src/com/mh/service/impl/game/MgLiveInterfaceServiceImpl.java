package com.mh.service.impl.game;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mh.client.BaseFlatInfo;
import com.mh.commons.utils.IPSeeker;
import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;
import com.mh.ifc.NMGIfcUtil;
import com.mh.ifc.http.NMGConts;
import com.mh.ifc.http.NMgResResult;
import com.mh.service.FlatInterfaceService;
import com.mh.web.login.UserContext;
/**
 * MG真人
 * @author Administrator
 *
 */
@Service("mgLiveInterfaceServiceImpl")
public class MgLiveInterfaceServiceImpl extends BaseFlatInfo implements FlatInterfaceService {
	
	public String login(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		try {
			UserContext uc = getUserContext(request);
			WebUserFlat webUserFlat = webUserFlatService.getWebUserFlat(uc.getUserName());
			if (StringUtils.isEmpty(webUserFlat.getMgUserName())) {
				webUserFlat = NMGIfcUtil.registNMgAccout(webUserFlat);
				if (StringUtils.isNotEmpty(webUserFlat.getMgUserName()) || StringUtils.isNotEmpty(webUserFlat.getMgId())) {
					webUserFlatService.saveOrUpdateWebUserFlat(webUserFlat);
				} else {
					return "";
				}
			}

			Map<String, String> params = new HashMap<String, String>();
			params.put(NMGConts.USERNAME, webUserFlat.getMgUserName());// 游戏帐号
			params.put(NMGConts.PASSWORD, webUserFlat.getMgPassword());// 游戏帐号密码
			params.put(NMGConts.IP, IPSeeker.getIpAddress2(request));//IP
			
			params.put(NMGConts.LOBBY_URL, basePath);// 游戏帐号
			params.put(NMGConts.LOGOUTREDIRECT_URL, basePath);// 游戏帐号密码
			params.put(NMGConts.BANKING_URL, basePath);//IP
			
			NMgResResult result = NMGIfcUtil.loginlive(params);
			return result.getLoginUrl();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public double searchUserBlance(WebUserFlat webUserFlat) throws Exception {
		return 0;
	}

	public String updateEdu(WebUserFlat webUserFlat, WebUser webUser,
			String flatName, String optType, int points, WebEdu webEdu)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ModelAndView mobileLogin(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
}
