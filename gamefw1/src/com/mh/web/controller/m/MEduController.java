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
import com.mh.commons.utils.DateUtil;
import com.mh.entity.WebEdu;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUser;
import com.mh.service.WebRecordService;
import com.mh.service.WebUserService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
import com.mh.web.util.MobilePage;
@Controller
@RequestMapping("/m/edu")
public class MEduController extends BaseController 
{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private WebRecordService webRecordService;
	
	@RequestMapping("/goedu")
	public ModelAndView goedu(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("m//member/ag_accounts");
		UserContext uc = this.getUserContext(request);
		WebUser user = this.webUserService.findWebrUseByUserName(uc.getUserName());
		return model.addObject("user", user);
	}
	
	@RequestMapping("/eduHistory")
	public ModelAndView eduHistory(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("m//member/edu_filter");
		Date currDate = DateUtil.currentDate();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		Date nextDate = DateUtil.addDay(currDate, -30);
		String nextDateStr = DateUtil.format(nextDate, "yyyy-MM-dd");
		return model.addObject("currDateStr", currDateStr).addObject("nextDateStr", nextDateStr);
	}
	
	@RequestMapping("/eduHistoryList")
	public ModelAndView eduHistoryList(HttpServletRequest request,HttpServletResponse response)
	{
		
		int pageNum = SportConstant.PAGE_NUM;	//默认分五页
		int pageSize = SportConstant.PAGE_SIZE;	//每页20条
		
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String flatName = request.getParameter("flatName");
		String eduType = request.getParameter("eduType");
		
		Date date = new Date();
		if(StringUtils.isBlank(beginTime))
			beginTime = sdf.format(date);
		if(StringUtils.isBlank(endTime))
			endTime = sdf.format(date);
		
		ModelAndView model = new ModelAndView("m//member/edu_rec_list");
		UserContext uc = this.getUserContext(request);
		WebRecords record = new WebRecords();
		record.setUserName(uc.getUserName());
		record.setStartTime(beginTime);
		record.setEndTime(endTime);
		record.setFlatName(flatName);
		record.setEduType(eduType);
		record.setCount(pageNum * pageSize);
		List<WebEdu> list = this.webRecordService.getEduList(record);
		pageNum = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
		MobilePage<WebEdu> mp = new MobilePage<WebEdu>();
		Map<String, List<WebEdu>> map = mp.toPage(list, pageNum, pageSize);
		//页脚按钮个数
		List<String> pageList = new ArrayList<String>();
		for (int i = 1; i <= pageNum; i++)
			pageList.add("page"+i);
		return model.addObject("map", map).addObject("pageList", pageList);
	}
}
