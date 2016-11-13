package com.mh.web.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mh.web.servlet.MyTokenSessionContext;

/**
 * 系统URL权限过滤类，用于判断当前用户是否登入，是否有相应的权限，没有权限则抛出异常，跳转到禁止访问页面 类描述: TODO<br/>
 * 修改人: alex<br/>
 * 修改时间: 2015-6-25 上午9:56:59<br/>
 */
@Component("mgClientForbidVisitInterceptor")
public class MgClientForbidVisitInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(MgClientForbidVisitInterceptor.class);
	private static List<String> visitList = new ArrayList<String>();
	static{
		visitList.add("");
		visitList.add("/mg/client/install");
		visitList.add("/mg/client/news");
		visitList.add("/mg/client/contact");
		//visitList.add("/mg/client/bank");
		//visitList.add("/mg/client/account");
		visitList.add("/mg/client/login");
		visitList.add("/mg/client/login2");
		visitList.add("/mg/client/doLogin");
		visitList.add("/mg/client/doUserLogin");
		visitList.add("/mg/client/active");
		visitList.add("/mg/client/loginhelp");
		visitList.add("/mg/client/account");
		visitList.add("/mg/client/bank");
		
		/*visitList.add("/mg/client/doEdu");//do额度转换
		visitList.add("/mg/client/saveWithdraw");//do用户提款
		visitList.add("/mg/client/doKjPay");//do微信支付
		visitList.add("/mg/client/doBankPay");//do公司入款
		visitList.add("/mg/client/getPayImg");//微信二维码*/
	}
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI().substring(request.getContextPath().length());
		// 登录页链接
		String loginUrl = this.getWebDomain(request) + "mg/client/login2";
		
		if(visitList.contains(uri)){
			//count.cgi 漏洞 解决
			//if(this.hasCountCgiError(request))
			//	throw new NotLoginException("用户未登入");
		} else {
			Object userName = request.getSession().getAttribute("userName");
			if(userName == null || !MyTokenSessionContext.sessionUser.containsKey(userName)){
				//拦截
				logger.info("session为空");
				logger.info("拦截url "+uri);
				response.sendRedirect(loginUrl);
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

	private String getWebDomain(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		if (basePath.endsWith(":80/")) {
			basePath = basePath.substring(0, basePath.indexOf(":80/")) + "/";
		}
		return basePath;
	}
	
}
