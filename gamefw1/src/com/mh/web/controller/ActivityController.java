/**   
* 文件名称: ActivityController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午2:17:04<br/>
*/  
package com.mh.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.orm.Page;
import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.ActivityInfo;
import com.mh.entity.ActivityPrize;
import com.mh.service.ActivityInfoService;
import com.mh.service.ActivityLogService;
import com.mh.service.ActivityPrizeService;
import com.mh.service.ActivityService;
import com.mh.service.ActivityUserService;
import com.mh.service.ActivityWinningListService;
import com.mh.web.login.UserContext;

/** 
 * 活动Controller
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午2:17:04<br/>
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private ActivityInfoService activityInfoService;
	@Autowired
	private ActivityPrizeService activityPrizeService;
	@Autowired
	private ActivityUserService activityUserService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private ActivityWinningListService activityWinningListService;
	
	
	List<ActivityPrize> prizeList;
	

	
	
	/**
	 * 
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response
	 * @return  
	 * ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ActivityInfo activityInfo = this.activityInfoService.getActivityInfo();
 
		return new ModelAndView("activity/index")
			.addObject("activityInfo", activityInfo);
	}
	
	
	/**
	 * 活动抽奖
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/doLottery")
	public synchronized void doLottery(HttpServletRequest request,HttpServletResponse response) {
		String msg = "抱歉，没有中奖，";
		try {
			UserContext uc = this.getUserContext(request);
			if(uc==null){
				ServletUtils.outPrintFail(request, response, "您还未登录!");
				return;
			}
			String userName = uc.getUserName();
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("prizeIndex",0);
			
			
			ActivityInfo activityInfo = this.activityInfoService.getActivityInfo();
			if(activityInfo==null){
				ServletUtils.outPrintFail(request, response, "活动不存在!");
				return;
			}
			if(activityInfo.getStatus()==0){
				ServletUtils.outPrintFail(request, response, "活动已经下线!");
				return;
			}
			
			Date currDate = new Date();
			String currDateStr = DateUtil.formatDate(currDate, DateUtil.YMDHMS_PATTERN);
			String dateStr = DateUtil.format(currDate, "yyyy-MM-dd");
			long times = currDate.getTime();
			Date startTime = activityInfo.getBeginTime();
			Date endTime = activityInfo.getEndTime();
			long times1=startTime.getTime();
			long times2=endTime.getTime();
			if(times<times1){
				logger.info("活动还未开始");
				ServletUtils.outPrintFail(request, response, "活动还未开始!");
				return;
			}
			if(times>times2){
				logger.info("活动已结束");
				ServletUtils.outPrintFail(request, response, "活动已结束");
				return;
			}
			boolean isLot = this.activityUserService.isLotteryUser(userName);
			if(!isLot){
				logger.info("由于您没有达到活动要求，所以无法参与抽奖!");
				ServletUtils.outPrintFail(request, response, "抱歉，由于您没有达到活动要求，所以无法参与抽奖!");
				return;
			}
			int lottNums = this.activityUserService.getLotteryNums(userName);
			if(lottNums<=0){
				logger.info("您的抽奖次数已经用完!");
				ServletUtils.outPrintFail(request, response, "您的抽奖次数已经用完!");
				return;
			}
			if(lottNums-1<=0){
				msg = msg+"您的抽奖次数已经用完！";
			}else{
				msg = msg+"您还有"+(lottNums-1)+"次机会！";
			}

			
			
			
			prizeList = this.activityPrizeService.getActivityPrize();
			if(prizeList==null){
				logger.info("请配置相关奖品信息!");
				ServletUtils.outPrintFail(request, response, "请配置相关奖品信息!");
				return;
			}
			String ip = IPSeeker.getIpAddress(request);
			 
			Map<Integer,ActivityPrize> prizeIndexMap = new TreeMap<Integer,ActivityPrize>();
			List<Integer> noprizeIndexList = new ArrayList<Integer>();
			
			for(int i=0;i<prizeList.size();i++){
				ActivityPrize prize = prizeList.get(i);
				if(prize.getIsPrize()!=null && prize.getIsPrize()==0){
					noprizeIndexList.add(i);
				}
				prizeIndexMap.put(i, prize);
			}
			double totalRs = 0;
			int initNums=0;
			if(activityInfo.getInitNums()!=null){
				initNums = activityInfo.getInitNums();
			}
			double growthRate=0;
			if(activityInfo.getGrowthRate()!=null){
				growthRate= activityInfo.getGrowthRate();
			}
			totalRs = initNums +initNums*(growthRate/100);
			int noPrizeIndex = this.getNoPrizeIndex(noprizeIndexList);
			int prizeIndex = this.getPrizeIndex(activityInfo,totalRs,userName);
			ActivityPrize prize = prizeIndexMap.get(prizeIndex);
			ActivityPrize noPrize = prizeIndexMap.get(noPrizeIndex);
			
			if(prizeIndex<0){
				
				dataMap.put("prizeIndex",noPrizeIndex);
				dataMap.put("tsMsg", msg);
				String remark = "当次中奖序号【"+noPrizeIndex+"】没中奖";
				activityService.saveActivity(noPrize,userName, noPrizeIndex, 0, ip, remark);
				 
			}else{
				String prizeId = prize.getId()+"";
				//是否为奖品
				int isPrize = 0;
				if(prize.getIsPrize()!=null){
					isPrize = prize.getIsPrize();
				}
				if(isPrize==0){
					dataMap.put("prizeIndex",prizeIndex);
					dataMap.put("tsMsg", msg);
					//记录中奖日志
					String remark = "当次中奖序号【"+prizeIndex+"】奖品ID【"+prize.getId()+"】没中奖，原因抽中的不是奖品";
					logger.info(remark);
					activityService.saveActivity(prize,userName, prizeIndex, 0, ip, remark);
				}else{
					//奖品数量
					long prizeNum =0;
					if(prize.getPrizeNums()!=null){
						prizeNum = prize.getPrizeNums();
					}
					//判断奖品总数是否超了
					long winNumTotal = this.activityLogService.getActivityPrizeNum(prize.getId(),"","");
					if(winNumTotal>=prizeNum){
						dataMap.put("prizeIndex",noPrizeIndex);
						dataMap.put("tsMsg", msg);
						String remark = "当次中奖序号【"+noPrizeIndex+"】奖品ID【"+noPrize.getId()+"】没中奖，原因：中奖总数【"+winNumTotal+"】超过奖品总数【"+prizeNum+"】";
						activityService.saveActivity(noPrize,userName, noPrizeIndex, 0, ip, remark);
					}else{
						//判断是否在锁定时间内
						long lockTimes1=0;
						long lockTimes2=0;
						String lockStartDateStr="";
						String lockEndDateStr = "";
						if(prize.getLockBeginTime()!=null){
							lockTimes1 = prize.getLockBeginTime().getTime();
						}
						if(prize.getLockEndTime()!=null){
							lockTimes2 = prize.getLockEndTime().getTime();
						}
						if(times>=lockTimes1 && times<=lockTimes2){
							String remark="当次中奖序号【"+noPrizeIndex+"】奖品ID【"+noPrize.getId()+"】没中奖，原因奖品在锁定时间段【"+lockStartDateStr+"】到【"+lockEndDateStr+"】不能中奖";
							activityService.saveActivity(noPrize,userName, noPrizeIndex, 0, ip, remark);
							dataMap.put("prizeIndex",noPrizeIndex);
							dataMap.put("tsMsg", msg);
						}else{
							boolean bFalg = true;
							String zjsm = "";
							//奖品天发放数
							if(prize.getDayNums()!=null){
								int dayNums = prize.getDayNums();
								int dayWinTotal = this.activityLogService.getActivityPrizeNum(prize.getId(), dateStr, dateStr);
								if(dayNums>0 && dayWinTotal>=dayNums){
									zjsm = "当次中奖序号【"+prizeIndex+"】奖品ID【"+prizeId+"】没中奖，原因【当天中奖次数"+dayNums+"超过奖品天发放数"+dayWinTotal+"】";
									dataMap.put("prizeIndex",noPrizeIndex);
									dataMap.put("tsMsg", msg);
									bFalg = false;
								}
							}
		 
							if(bFalg){
								//中奖处理
								dataMap.put("prizeIndex",prizeIndex);
								dataMap.put("tsMsg", "恭喜您!获得"+prize.getPrizeName());
								//记录中奖日志
								String remark = "当次中奖序号【"+prizeIndex+"】奖品ID【"+prizeId+"】中奖,用户"+userName+"在"+currDateStr+"抽中了"+prize.getPrizeName();
								activityService.saveActivity(prize,userName, prizeIndex, 1, ip, remark);
							}else{
								activityService.saveActivity(noPrize,userName, noPrizeIndex, 0, ip, zjsm);
							}
						}
						
					}
				}
			}
	 
			ServletUtils.outPrintSuccess(request, response, dataMap);
		} catch (RuntimeException e) {
			logger.error("活动抽奖异常: "+e.getMessage(), e);
			ServletUtils.outPrintFail(request, response, "活动抽奖异常");
		}
	}
	
	

	/**
	 * 
	 * 方法描述: 获取奖品数值
	 * @param list
	 * @return  
	 */
	public int getPrizeIndex(ActivityInfo activityInfo,double totalRs,String userName){
		//获取总的中奖概率
		double winRateTotal = 0;
		for(int i=0;i<prizeList.size();i++){
			ActivityPrize prize = prizeList.get(i);
			double winRate = 0;
			if(prize.getWinningRate()!=null){
				winRate = prize.getWinningRate();
			}
			winRateTotal+=winRate;
		}
		if(totalRs>winRateTotal){
			winRateTotal = totalRs;
		}
 
		
		int result =-1;
		Random random=new Random();
		//中奖随机数
		int winIndex=0;
//		int winIndex = random.nextInt(list.size()-1);
		int max = prizeList.size();
		int min = 0;
		winIndex = random.nextInt(max) % (max-min + 1) + min;
		if(winIndex>max-1){
			winIndex = max-1;
		}
		
		logger.info("用户【"+userName+"】抽中奖品序号【"+winIndex+"】");
		ActivityPrize activityPrize = prizeList.get(winIndex);
		double winRate = 0;
		if(activityPrize.getWinningRate()!=null){
			winRate = activityPrize.getWinningRate().doubleValue();
		}
		int rateRadom=0;
		if(winRateTotal>0){
			rateRadom = new Random().nextInt((int)Math.floor(winRateTotal));
		}

		double winRadom = (winRate/100)*winRateTotal;
		logger.info("用户【"+userName+"】随机数【"+rateRadom+"】，中奖随机数【"+winRadom+"】");
		if(rateRadom<winRadom){//中奖
			logger.info("用户【"+userName+"】中奖序号【"+winIndex+"】");
			result = winIndex;
		}
		return result;
		
//		for(int i=0;i<list.size();i++){
//			Map<String,Object> map = list.get(i);
//			double winRate = 0;
//			if(map.get("WINNING_RATE")!=null){
//				winRate = Double.valueOf(map.get("WINNING_RATE").toString());
//			}
//			int random = new Random().nextInt((int)Math.floor(winRateTotal));
//			log.info("随机数【"+random+"】，中奖随机数【"+winRate+"】");
//			if(random<winRate){//中奖
//				log.info("中奖序号【"+i+"】");
//				result = i;
//				break;
//			}else{
//				winRateTotal -=winRate;
//			}
//			
//		}
 
	}
	
	
	
	
	/**
	 * 获取中奖名单
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/getWinningListData")
	public synchronized void getWinningListData(HttpServletRequest request,HttpServletResponse response) {
		try{
		 
			List<Map<String,Object>> dataList = this.activityWinningListService.getWinningList("");
			
			ServletUtils.outPrintSuccess(request, response, dataList);
		}catch(Exception e){
			ServletUtils.outPrintFail(request, response, "获取中奖名单出错");
			logger.error("获取中奖名单出错~",e);
		}
	}
	
	/**
	 * 获取中奖名单分页
	 * 方法描述: TODO</br> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/getWinningListPage")
	public synchronized void getWinningListPage(HttpServletRequest request,HttpServletResponse response) {
		try{
			UserContext uc = this.getUserContext(request);
			if(uc==null){
				ServletUtils.outPrintFail(request, response, "您还未登录!");
				return;
			}
			
			String userName = uc.getUserName();
			Page page = newPage(request);
			this.activityWinningListService.findPage(page, userName);
			ServletUtils.outPrintSuccess(request, response, page);
		}catch(Exception e){
			ServletUtils.outPrintFail(request, response, "获取中奖名单列表异常");
			logger.error("获取中奖名单列表异常~",e);
		}
	}
	
	
	/**
	 * 
	 * 方法描述: 获取无奖品序号
	 * @param list
	 * @return  
	 * int
	 */
	public int getNoPrizeIndex(List<Integer> list){
 
		Random random=new Random();
		//中奖随机数
		int winIndex=0;
		int max = list.size();
		int min = 0;
		winIndex = random.nextInt(max) % (max-min + 1) + min;
		if(winIndex>max-1){
			winIndex = max-1;
		}
		return list.get(winIndex);
	}



	public List<ActivityPrize> getPrizeList() {
		return prizeList;
	}



	public void setPrizeList(List<ActivityPrize> prizeList) {
		this.prizeList = prizeList;
	}

}
