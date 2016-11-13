package com.mh.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.conf.WebConstants;
import com.mh.commons.utils.HttpClientUtil;
import com.mh.dao.WebAccountsDao;
import com.mh.dao.WebEduDao;
import com.mh.dao.WebUserDao;
import com.mh.dao.WebUserFlatDao;
import com.mh.entity.WebAccounts;
import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.service.WebUserFlatService;
import com.mh.web.login.UserContext;
import com.mh.web.servlet.MySessionContext;
@Component
public class BaseFlatInfo {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	protected WebEduDao webEduDao;
	
	@Autowired
	protected WebAccountsDao webAccountsDao;
	
	@Autowired
	protected WebUserFlatService webUserFlatService;
	
	@Autowired
	protected WebUserFlatDao webUserFlatDao;
	
	@Autowired
	protected WebUserDao webUserDao;
	
	/**
	 * 获取登录用户
	 * @param request
	 * @return
	 */
	protected UserContext getUserContext(HttpServletRequest request){
		UserContext uc = null;
		if(MySessionContext.getSession(request.getSession().getId())!=null){
			uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
			if(uc != null && uc.getUserId() != null){
				
				return uc;
			}
		}
		return uc;
	}
	
	/**
	 * 接口鉴权
	 * @param params
	 * @return
	 */
	protected boolean optInterfaceEdu(Map<String, String> params){
		PostMethod method = null;
		try {
			String url = CommonConstant.resCommMap.get(CommonConstant.INTERFACE_AUT_URL);
			try {
				String respJson = HttpClientUtil.post(url + "/com/agent/api/eduOption", params);
				JSONObject json =JSON.parseObject(respJson);
				if (StringUtils.equals("000000", json.getString("code"))) {
					return true;
				}
				return false;
			} catch (Exception e) {
				logger.info("校验接口失败",e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (method != null) {
				method.abort();
				method.releaseConnection();
			}
		}
		return false;
	}
	
	protected String getWebDomain(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		if (basePath.endsWith(":80/")) {
			basePath = basePath.substring(0, basePath.indexOf(":80/")) + "/";
		}
		return basePath;
	}
	
	protected String getRootWebDomain_(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		if (basePath.endsWith(":80/")) {
			basePath = basePath.substring(0, basePath.indexOf(":80/"));
		}
		return basePath;
	}
	
	protected void sendErrorMsg(HttpServletResponse response,String msg,String url){
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.print("<script>alert('"+msg+"');window.location.href='"+url+"';</script>");
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 金额流水记录
	 */
	protected void addWebEduRecord(WebUser webUser,int statusType, String eduOrder, int points, int eduForward, String eduForwardRemark, Date gmt_4_date, String flatName)  {
		 
		Date currDate = new Date();
		WebEdu edu = new WebEdu();
		edu.setEduOrder(eduOrder);
		edu.setUserName(webUser.getUserName());
		edu.setEduPoints(points);
		edu.setEduType(points > 0 ? 2 : 1);
		edu.setEduStatus(statusType);
		edu.setEduIp(webUser.getUserLastLoginIp());
		edu.setCreateTime(currDate);
		edu.setModifyTime(currDate);
		edu.setEduForward(eduForward);
		edu.setEduForwardRemark(eduForwardRemark);
		edu.setGmt4Time(gmt_4_date);
		edu.setFlatName(flatName);
		webEduDao.saveOrUpdate(edu);
	}
	
	/**
	 * 财务流水记录
	 */
	protected void addAccountRecord(WebUser webUser, String flatName, String optType, double money, String eduOrder, String remark, Date gmt_4_date) {
		
		optType = WebConstants.eduCodeMap.get(String.valueOf(optType));

		/** 财务记录 开始 **/
		WebAccounts account = new WebAccounts();
		account.setActOptMoney(-money);// 
		account.setActProType(flatName);// 取款
		account.setActOptType(optType);
		account.setActOrder(eduOrder);
		account.setActResultMoney(webUser.getUserMoney());
		account.setCreateTime(new Date());
		account.setModifyTime(new Date());
		account.setRemark(remark);
		account.setSysUserName(webUser.getUserName());
		account.setUserName(webUser.getUserName());
		account.setStatus(0);
		account.setGmt4Time(gmt_4_date);
		if(!StringUtils.isEmpty(webUser.getUserAgent())){
			account.setUserAgent(webUser.getUserAgent());
		}
	 
		webAccountsDao.saveOrUpdate(account);
 
	}
}
