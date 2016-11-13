package com.mh.web.controller.m;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.client.FlatClient;
import com.mh.web.controller.BaseController;
@Controller
@RequestMapping("/m/game")
public class MGameController extends BaseController{
	
	@Autowired
	private FlatClient flatClient;
	/**
	 * 手机端游戏平台登录入口
	 */
	@RequestMapping("/forwardGame")
	public ModelAndView forwardGame(HttpServletRequest request,HttpServletResponse response){
		return flatClient.flatMobileLogin(request, response);
	}
}
