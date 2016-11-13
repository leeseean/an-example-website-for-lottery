package com.mh.web.controller.m;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.SportConstant;
import com.mh.commons.constants.AgConstants;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.TBetRecordAg;
import com.mh.entity.TBetRecordMg;
import com.mh.entity.TBetRecordOs;
import com.mh.entity.TBetRecordPt;
import com.mh.entity.WebRecords;
import com.mh.service.WebRecordService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
import com.mh.web.util.MobilePage;
@Controller
@RequestMapping("/m/slot")
public class MSlotController extends BaseController {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private WebRecordService webRecordService;
	
	@RequestMapping("/goSlotOrder")
	public ModelAndView goSlotOrder(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="code",required=false,defaultValue="ag") String code){
		ModelAndView model = new ModelAndView("m/sport/slotorder_filter");
		Date currDate = DateUtil.currentDate();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd");
		Date nextDate = DateUtil.addDay(currDate, -30);
		String nextDateStr = DateUtil.format(nextDate, "yyyy-MM-dd");
		return model.addObject("currDateStr", currDateStr).addObject("nextDateStr", nextDateStr).addObject("code", code);
	}
	
	/**
	 * 电子注单
	 */
	@RequestMapping("/getslotOrderList")
	public ModelAndView getslotOrderList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String slotType = request.getParameter("cateType") == null ? "ag" : request.getParameter("cateType");
		String status = request.getParameter("status");
		
		int pageNum = SportConstant.PAGE_NUM;	//默认分五页
		int pageSize = SportConstant.PAGE_SIZE;	//每页20条
		
		Date now = new Date();
		if(StringUtils.isBlank(beginTime)){
			beginTime = sdf.format(now);
		}
		if(StringUtils.isBlank(endTime)){
			endTime = sdf.format(now);
		}
		
		UserContext uc = this.getUserContext(request);
		WebRecords records = new WebRecords();
		records.setStartTime(beginTime);
		records.setEndTime(endTime);
		records.setUserName(uc.getUserName());
		records.setBetSportType(slotType);
		records.setBetStatus(status);
		records.setCount(pageNum * pageSize);
		if(StringUtils.equals("ag", slotType)){
			
			List<TBetRecordAg> list = this.webRecordService.getAgRecordList(records);
			if (CollectionUtils.isNotEmpty(list)) {
				for (TBetRecordAg record : list) {
					record.setBetGameContent(AgConstants.gameType(record.getBetGameContent()) + " " + record.getBetGameContent());
				}
			}
			pageNum = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
			MobilePage<TBetRecordAg> mp = new MobilePage<TBetRecordAg>();
			Map<String, List<TBetRecordAg>> map = mp.toPage(list, pageNum, pageSize);
			model.addObject("map", map);
			model.setViewName("m/sport/agslotorder_list");
		}else if(StringUtils.equals("mg", slotType)){
			List<TBetRecordMg> list = this.webRecordService.getMgRecordList(records);
			pageNum = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
			MobilePage<TBetRecordMg> mp = new MobilePage<TBetRecordMg>();
			Map<String, List<TBetRecordMg>> map = mp.toPage(list, pageNum, pageSize);
			model.addObject("map", map);
			model.setViewName("m/sport/mgslotorder_list");
		}else if(StringUtils.equals("os", slotType)){
			List<TBetRecordOs> list = this.webRecordService.getOsRecordList(records);
			pageNum = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
			MobilePage<TBetRecordOs> mp = new MobilePage<TBetRecordOs>();
			Map<String, List<TBetRecordOs>> map = mp.toPage(list, pageNum, pageSize);
			model.addObject("map", map);
			model.setViewName("m/sport/osslotorder_list");
		}else if(StringUtils.equals("pt", slotType)){
			List<TBetRecordPt> list = this.webRecordService.getPtRecordList(records);
			pageNum = list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1;
			MobilePage<TBetRecordPt> mp = new MobilePage<TBetRecordPt>();
			Map<String, List<TBetRecordPt>> map = mp.toPage(list, pageNum, pageSize);
			model.addObject("map", map);
			model.setViewName("m/sport/ptslotorder_list");
		}
		//页脚按钮个数
		List<String> pageList = new ArrayList<String>();
		for (int i = 1; i <= pageNum; i++){
			pageList.add("page"+i);
		}
		return model.addObject("pageList", pageList);
	}
}
