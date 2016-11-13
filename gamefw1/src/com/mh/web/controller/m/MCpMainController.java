package com.mh.web.controller.m;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.cache.CpConfigCache;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;


@Controller
@RequestMapping("/m/mobileCpMain")
public class MCpMainController extends BaseController{
 
	
	@RequestMapping("/menu")
	public ModelAndView goCpMenu(HttpServletRequest request, HttpServletResponse response){
		UserContext webUser = this.getUserContext(request);
		ModelAndView mv=new ModelAndView("m/cp/lottery_category");
		mv.addObject("gameMap", CpConfigCache.CP_GAME_MAP);
		mv.addObject("webUser", webUser);
		return mv;
	}
}
