package com.mh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.mh.entity.WebEdu;
import com.mh.entity.WebUser;
import com.mh.entity.WebUserFlat;

/**
 * 平台接口
 * @author Administrator
 *
 */
public interface FlatInterfaceService {
	/**
	 * 平台登录
	 * @param request
	 * @return 登录url
	 */
	public abstract String login(HttpServletRequest request);
	
	/**
	 * 平台手机端登录
	 * @param request
	 * @return
	 */
	public abstract ModelAndView mobileLogin(HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 平台余额
	 * @return
	 */
	public abstract double searchUserBlance(WebUserFlat webUserFlat) throws Exception;
	
	/**
	 * 额度转换
	 * @return
	 */
	public abstract String updateEdu(WebUserFlat webUserFlat,WebUser webUser,String flatName,String optType,int points,WebEdu webEdu) throws RuntimeException;
}
