package com.mh.web.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.DesUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserLog;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.servlet.MySessionContext;

/**
 * 皇冠体育登录
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/hglogin")
public class HgLoginController extends BaseController 
{

	@Autowired
	private WebUserService webUserService;

	@Autowired(required = false)
	private List<UserContextAccessor> accessores;

	/**
	 * 判断是否登录,如果已登录 跳转至主页,未登录 跳转至登录页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView goToIndex(HttpServletRequest request, HttpServletResponse response) 
	{
		ModelAndView mv = new ModelAndView();
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		if(uc == null)
		{
			mv.setViewName("web/hg_login");
			return mv;
		}
		this.doLogin(request, response);
		return null;
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response) 
	{
		UserContext uc = (UserContext) request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		if (null == uc) 
		{
			try 
			{
				String loginName = request.getParameter("loginName");
				String password = request.getParameter("password");

				if (StringUtils.isBlank(loginName)) 
				{
					outPut(request,response, "请输入用户名",1);
					logger.info("用户名为空，返回登入界面");
					return;
				}
				if (StringUtils.isBlank(password)) 
				{
					outPut(request,response, "请输入密码",1);
					logger.info("密码为空，返回登入界面");
					return;
				}
					
				password = DesUtil.encrypt(password,CommonConstant.resCommMap.get("app.client.des.key"));
				
				int userPsTimes = NumberUtils.toInt(WebSiteManagerConstants.SYSPARAMMAP.get("user_ps_times").toString(), 5);

				logger.info("根据用户名={} 密码={} 获取用户对象", loginName, password);
				WebUser webUser = this.webUserService.findWebrUseByUserName(loginName);
				List<String> fieldList = new ArrayList<String>();
				List<Object> valList = new ArrayList<Object>();
				Date currDate = new Date();
				if (webUser == null) 
				{
					outPut(request,response, "帐号不存在!",1);
					return;
				} 
				else 
				{
					if (!webUser.getUserPassword().equals(password)) 
					{
						String warn = "";
						String lastTimeStr = DateUtil.format(webUser.getModifyTime(), "yyyy-MM-dd");
						String currDateStr = DateUtil.format(new Date(),"yyyy-MM-dd");
						if (!currDateStr.equals(lastTimeStr)) 
						{
							webUser.setUserPsTimes(0);
						}
						int userHasPsTimes = 0;
						if (webUser.getUserPsTimes() != null) 
						{
							userHasPsTimes = webUser.getUserPsTimes().intValue();
						}
						userHasPsTimes++;
						if (userHasPsTimes >= userPsTimes) 
						{
							// 冻结帐号
							webUser.setUserStatus(0);
							warn = "密码输入错误超过限制，冻结被帐号。请联系我们24小时在线客服！";
						} 
						else 
						{
							warn = "密码输入错误，超过" + userPsTimes + "次将被冻结。目前第"+ (userHasPsTimes) + "次";
						}
						fieldList.add("user_login_times");
						fieldList.add("user_status");
						fieldList.add("modify_time");

						valList.add(userHasPsTimes);
						valList.add(webUser.getUserStatus());
						valList.add(currDate);

						int rows = this.webUserService.updateWebUser(webUser.getUserName(), fieldList, valList);
						if (rows < 1) 
						{
							throw new RuntimeException("用户信息更新失败!");
						}
						outPut(request,response, warn,1);
						logger.info(warn);
						return;
					}
				}

				if (!CommonConstant.STATUS_USABLE.equals(String.valueOf(webUser.getUserStatus()))) 
				{
					logger.info("用户 ID={} 的状态不可用", webUser.getUserStatus());
					outPut(request,response, "帐号已被冻结。请联系我们24小时在线客服！",1);
					return;
				}

				if (MySessionContext.getSession(webUser.getUserSessionId()) != null) 
				{
					HttpSession oldSession = MySessionContext.getSession(webUser
							.getUserSessionId());
					// 移除session
					oldSession.removeAttribute(CommonConstant.USER_CONTEXT_KEY);
					MySessionContext.delSession(oldSession);
				}

				request.getSession().invalidate();// 清除之前的session创建新的

				// 限制同时只能一个登陆
				MySessionContext.addSession(request.getSession());

				this.doLogon(request, webUser);
				this.updateUser(webUser, request, response);
				outPut(request,response, "",0);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				outPut(request,response, "登录异常",1);
			}
		} else {
			// 如果已经登录,直接跳到首页
			outPut(request,response, "",0);
		}
	}

	/**
	 * 输出
	 * 
	 * @param response
	 * @param msg
	 * @param code 0:成功 1:失败
	 */
	private void outPut(HttpServletRequest request,HttpServletResponse response, String msg,int code) 
	{
		StringBuffer innerHtml = new StringBuffer();
		String doMain = getWebDomain(request);
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		if(1 == code)
		{
			//失败
			innerHtml.append("<form id=\"goLogin\" name=\"goLogin\" method=\"POST\" action=\""+doMain+"hglogin/index\">");
			innerHtml.append("</form>");
			innerHtml.append("<script>");
			innerHtml.append("alert('"+msg+"');");
			innerHtml.append("document.getElementById('goLogin').submit();");
			innerHtml.append("</script>");
		}
		else
		{
			//成功
			innerHtml.append("<form id=\"goSport\" name=\"goSport\" method=\"POST\" action=\""
					+ doMain + "sport/main\">");
			innerHtml.append("</form>");
			innerHtml.append("<script>");
			innerHtml.append("document.getElementById('goSport').submit();");
			innerHtml.append("</script>");
		}
		PrintWriter print = null;
		try 
		{
			print = response.getWriter();
			print.write(innerHtml.toString());
		} 
		catch (IOException e) 
		{
			logger.error("服务器异常" + e);
		} 
		finally 
		{
			if (null != print) 
			{
				print.flush();
				print.close();
			}
		}
	}

	/**
	 * 
	 * 功能说明: 登入操作
	 * 
	 * @param request
	 * @param userId
	 *            void
	 * 
	 */
	public void doLogon(HttpServletRequest request, WebUser user) 
	{
		if (user == null)
			throw new IllegalArgumentException("用户不能为空");
		UserContext uc = (UserContext) request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		if (uc != null && uc.getUserId().equals(user.getId()))
			return;
		bind(user, request);
	}

	/**
	 * 绑定用户上下文数据到session中
	 * 
	 * @param user
	 * @param request
	 */
	private void bind(WebUser user, HttpServletRequest request) 
	{
		if (null == user)
			throw new RuntimeException("待载入缓存的用户对象为空错误！");
		logger.info("用户登入验证通过，开始绑定用户到Session中");

		UserContext uc = new UserContext();
		try 
		{
			uc.setUserId(user.getId());
			uc.setUserName(user.getUserName());
			uc.setLastLogonTime(user.getUserLastLoginTime());
			uc.setIp(user.getUserLastLoginIp());
			uc.setUserType(user.getUserType());
			uc.setUserMemberType(user.getUserType());
			uc.setUserMoney(user.getUserMoney());

			// 绑定用户权限到用户上下文
			this.bindCustomDatas(uc);
		} catch (RuntimeException e) {
			logger.error("绑定用户上下文数据出现异常：", e);
			throw new RuntimeException(e);
		}

		logger.info("绑定用户到Session中结束");
		uc.setSessionId(request.getSession().getId());
		MySessionContext.getSession(request.getSession().getId()).setAttribute(
				CommonConstant.USER_CONTEXT_KEY, uc);
	}

	/**
	 * 绑定jar包外部用户定义数据到Session
	 * 
	 * @param uc
	 */
	private void bindCustomDatas(UserContext uc) {
		if (this.accessores != null && this.accessores.size() > 0) {
			for (UserContextAccessor accessor : accessores) {
				if (accessor != null)
					accessor.addCustomDatas(uc);
			}
		}
	}

	/**
	 * 更新用户登入信息
	 * 
	 * @param user
	 * @param request
	 */
	private void updateUser(WebUser user, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("更新用户登入信息");

		List<String> fieldList = new ArrayList<String>();
		List<Object> valList = new ArrayList<Object>();
		Date currDate = new Date();
		fieldList.add("user_last_login_ip");
		fieldList.add("user_last_login_domain");
		fieldList.add("user_last_login_time");
		fieldList.add("user_login_times");
		fieldList.add("modify_time");
		fieldList.add("user_ps_times");
		fieldList.add("user_session_id");

		valList.add(IPSeeker.getIpAddress(request));
		valList.add(this.getUserLoginDomain(request));
		valList.add(currDate);
		valList.add(user.getUserLoginTimes() + 1);
		valList.add(currDate);
		valList.add(0);
		valList.add(request.getSession().getId());
		int rows = this.webUserService.updateWebUser(user.getUserName(),
				fieldList, valList);
		if (rows < 1) {
			throw new RuntimeException("更新用户信息失败！");
		}

		// 登录日志
		WebUserLog webUserLog = new WebUserLog();
		webUserLog.setCreateTime(new Date());
		webUserLog.setLogWebFlag(user.getUserFlag());// 网站标识
		webUserLog.setLogWebUserName(user.getUserName());// 帐号
		webUserLog.setLogAddress(IPSeeker.getIpAddress(request));// 地址
		webUserLog
				.setLogWebName(WebSiteManagerConstants.ctxMap.get("siteName"));// 网站名称
		webUserLog.setLogWebDomain(WebSiteManagerConstants.ctxMap
				.get("siteDomain"));

		this.webUserService.updateWebUser(null, webUserLog);

	}
}
