package com.mh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.entity.WebPage;
/**
 * ClassName: EasyServiceController 
 * @Description: 一些简单的业务处理类,不参杂业务逻辑的边缘化业务
 * @author andy
 * @date 2015-11-4
 */
@Controller
public class EasyServiceController extends BaseController {
	@RequestMapping(value="/pg")
	public ModelAndView initWebData(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		String pgSn=request.getParameter("sn");
		WebPage wp=WebSiteManagerConstants.WEBPAGE_ALONE_MAP.get(pgSn);
		
		mv.addObject("wp", wp);
		mv.setViewName("web/rename");
		return mv;
	}
}
