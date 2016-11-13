package com.mh.web.controller.m;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.entity.SportBetDetail;
import com.mh.web.controller.BaseController;

@Controller
@RequestMapping("/m/dialog")
public class MDialogController extends BaseController 
{
	/**
	 * 足球下注弹框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sport")
	public ModelAndView dialog(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView model = new ModelAndView();
		SportBetDetail bet = (SportBetDetail) request.getSession().getAttribute("bet");
		request.getSession().removeAttribute("bet");
		if(bet.isFlag())
			model.setViewName("m/sport/dialog/sport_success_dialog");
		else
			model.setViewName("m/sport/dialog/sport_error_dialog");
		return model.addObject("bet", bet);
	}
	
	/**
	 * 串关弹框
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/sportp3")
	public ModelAndView dialogp3(HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView model = new ModelAndView("m/sport/dialog/sport_error_dialog");
		SportBetDetail bet = (SportBetDetail) request.getSession().getAttribute("bet");
		request.getSession().removeAttribute("bet");
		if(bet.isFlag())
		{
			List<SportBetDetail> list = (List<SportBetDetail>) request.getSession().getAttribute("betp3");
			request.getSession().removeAttribute("betp3");
			model.setViewName("m/sport/dialog/sportp3_success_dialog");
			model.addObject("betlist", list);
		}
		return model.addObject("bet", bet);
	}
}
