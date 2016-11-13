/**   
* 文件名称: WebSportHelpGameController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-15 下午8:20:18<br/>
*/  
package com.mh.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 类描述: TODO<br/>体育帮助
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-15 下午8:20:18<br/>
 */
@Controller
@RequestMapping("/sport")
public class WebSportHelpGameController  extends BaseController {
	/**
	 * 一般规则
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/help/qa")
	public ModelAndView goHelp(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("sport/rule/qa");
		return view;
	}
	/**
	 * 篮球规则
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/help/basketball")
	public ModelAndView goHelpBasketBall(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("sport/rule/basketball");
		return view;
	}
	/**
	 * 足球规则
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/help/football")
	public ModelAndView goHelpFootBall(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("sport/rule/football");
		return view;
	}
	/**
	 * 冠军规则
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/help/outright")
	public ModelAndView goOutright(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("sport/rule/outright");
		return view;
	}
	/**
	 * 连串过关 / 复式过关规则
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/help/parlays_multiples")
	public ModelAndView goParlaysMultiples(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("sport/rule/parlays_multiples");
		return view;
	}
	/**
	 * 规则条款
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/help/rule")
	public ModelAndView goHelpRule(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("sport/rule/rule");
		return view;
	}
	
	
	
	
}
