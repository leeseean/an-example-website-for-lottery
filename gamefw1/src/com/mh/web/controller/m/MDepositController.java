package com.mh.web.controller.m;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.SportConstant;
import com.mh.entity.TWebBankHuikuan;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUserWithdraw;
import com.mh.service.WebRecordService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
import com.mh.web.util.MobilePage;
@Controller
@RequestMapping("/m/deposit")
public class MDepositController extends BaseController 
{
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private WebRecordService webRecordService;
	
	@RequestMapping("/payHistory")
	public ModelAndView payHistory(HttpServletRequest request,HttpServletResponse response) 
	{
		int pageNum = SportConstant.PAGE_NUM;	//默认分五页
		int pageSize = SportConstant.PAGE_SIZE;	//每页20条
		
		String beginTime = request.getParameter("bet-order-filter-11");
		String endTime = request.getParameter("bet-order-filter-12");
		String hkmodel = request.getParameter("bet-order-filter-21");
		
		Date date = new Date();
		if(StringUtils.isBlank(beginTime))
			beginTime = sdf.format(date);
		if(StringUtils.isBlank(endTime))
			endTime = sdf.format(date);
		
		ModelAndView model = new ModelAndView("m/member/deposit_rec_list");
		UserContext uc = this.getUserContext(request);
		WebRecords rd = new WebRecords();
		rd.setUserName(uc.getUserName());
		rd.setStartTime(beginTime);
		rd.setEndTime(endTime);
		rd.setHkModel(hkmodel);
		rd.setCount(pageNum * pageSize);
		List<TWebBankHuikuan> list = webRecordService.getHuiKuan(rd);
		
		pageNum = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
		MobilePage<TWebBankHuikuan> mp = new MobilePage<TWebBankHuikuan>();
		Map<String, List<TWebBankHuikuan>> map = mp.toPage(list, pageNum, pageSize);
		//页脚按钮个数
		List<String> pageList = new ArrayList<String>();
		for (int i = 1; i <= pageNum; i++)
			pageList.add("page"+i);
		return model.addObject("map", map).addObject("pageList", pageList);
	}
	
	@RequestMapping("/withdrawHistory")
	public ModelAndView withdrawHistory(HttpServletRequest request,HttpServletResponse response) 
	{
		
		int pageNum = SportConstant.PAGE_NUM;	//默认分五页
		int pageSize = SportConstant.PAGE_SIZE;	//每页20条
		
		String beginTime = request.getParameter("bet-order-filter-11");
		String endTime = request.getParameter("bet-order-filter-12");
		String withdrawType = request.getParameter("bet-order-filter-21");
		
		Date date = new Date();
		if(StringUtils.isBlank(beginTime))
			beginTime = sdf.format(date);
		if(StringUtils.isBlank(endTime))
			endTime = sdf.format(date);
		
		ModelAndView model = new ModelAndView("m/member/withdrawal_rec_list");
		UserContext uc = this.getUserContext(request);
		WebRecords rd = new WebRecords();
		rd.setUserName(uc.getUserName());
		rd.setStartTime(beginTime);
		rd.setEndTime(endTime);
		rd.setWithdrawType(withdrawType);
		rd.setCount(pageNum * pageSize);
		List<WebUserWithdraw> list = webRecordService.getWithdrawList(rd);
		
		pageNum = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
		
		MobilePage<WebUserWithdraw> mp = new MobilePage<WebUserWithdraw>();
		Map<String, List<WebUserWithdraw>> map = mp.toPage(list, pageNum, pageSize);
		//页脚按钮个数
		List<String> pageList = new ArrayList<String>();
		for (int i = 1; i <= pageNum; i++)
			pageList.add("page"+i);
		return model.addObject("map", map).addObject("pageList", pageList);
	}
	
}
