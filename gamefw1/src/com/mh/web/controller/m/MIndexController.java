package com.mh.web.controller.m;




import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.utils.DateUtil;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;


@Controller
@RequestMapping("/m/main")
public class MIndexController extends BaseController{
	@Resource
	private WebUserService webUserService;
	
	@RequestMapping("/index")
	public ModelAndView goToIndex(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		if(null!=uc){
			return this.doLogin(request, response);
		}
		mv.setViewName("m/mobile_login");
		return mv;
	}
	
	@RequestMapping("/indexLogin")
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("m/index_login");
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		if(null==uc)
		{
			mv.setViewName("m/mobile_login");
		}
		return mv;
	}
	
	/**
	 * 下注状况查询跳转
	 * @Description: TODO
	 * @param    
	 * @return ModelAndView
	 * @author Andy
	 * @date 2015-10-9
	 */
	@RequestMapping("/selectOrderPage")
	public ModelAndView selectOrderPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		List<String> dateList=new ArrayList<String>();
		Date date = new Date();
		for(int i=0;i<7;i++){
			 Date d=DateUtil.addDay(date, -i);
			String dateStr=DateUtil.format(d, "yyyy-MM-dd");
			dateList.add(dateStr);
		}
		String initDate=DateUtil.format(date, "yyyy-MM-dd");
		mv.addObject("initDate", initDate);
		mv.addObject("dateList", dateList);
//		String viewName="m/order_now";
		String viewName="m/bet_order_filter";
		mv.setViewName(viewName);
		return mv;
	}
	
	
	/**
	 * 账户历史查询跳转
	 * @Description: TODO
	 * @param    
	 * @return ModelAndView
	 * @author Andy
	 * @date 2015-10-9
	 */
	@RequestMapping("/selectOrderHis")
	public ModelAndView selectOrderHis(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		List<String> dateList=new ArrayList<String>();
		Date date = new Date();
		for(int i=0;i<7;i++){
			 Date d=DateUtil.addDay(date, -i);
			String dateStr=DateUtil.format(d, "yyyy-MM-dd");
			dateList.add(dateStr);
		}
		String initDate=DateUtil.format(date, "yyyy-MM-dd");
		mv.addObject("initDate", initDate);
		mv.addObject("dateList", dateList);
		
		String viewName="m/order_history";
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 会员资料
	 * @Description: TODO
	 * @param    
	 * @return void
	 * @author Andy
	 * @date 2015-10-13
	 */
	@RequestMapping("/userInfo")
	public ModelAndView userInfo(HttpServletRequest request, HttpServletResponse response){ 
		ModelAndView mv=new ModelAndView();
		UserContext uc = (UserContext)request.getSession().getAttribute(CommonConstant.USER_CONTEXT_KEY);
		double userMoney=this.webUserService.getWebUserMoney(uc.getUserName());
		BigDecimal bd = new BigDecimal(userMoney);
		mv.addObject("userMoney", bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());//保留两位小数
		mv.addObject("userName", uc.getUserName());
		
		mv.setViewName("m/user_info");
		return mv;
	}
	/**
	 * 菜单管理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/memuManager")
	public ModelAndView memuManager(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="code",required=false,defaultValue="manager_memu") String code)
	{
		ModelAndView model = new ModelAndView("m/"+code);
		return model;
	}
	
	@RequestMapping("/order")
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("m/order");
		return model;
	}
	@RequestMapping("/noLogin")
	public ModelAndView noLogin(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("m/no_login");
		return model;
	}
	
}
