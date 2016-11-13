/**   
* 文件名称: ActivityServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-16 上午4:08:39<br/>
*/  
package com.mh.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.ActivityLogDao;
import com.mh.dao.ActivityPrizeRuleDao;
import com.mh.dao.ActivityUserDao;
import com.mh.dao.ActivityWinningListDao;
import com.mh.entity.ActivityLog;
import com.mh.entity.ActivityPrize;
import com.mh.entity.ActivityRule;
import com.mh.entity.ActivityWinningList;
import com.mh.service.ActivityService;

/** 
 * 活动信息Service接口
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-16 上午4:08:39<br/>
 */
@Service
public class ActivityServiceImpl implements ActivityService{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ActivityLogDao activityLogDao;
	@Autowired
	private ActivityWinningListDao activityWinningListDao;
	@Autowired
	private ActivityUserDao activityUserDao;
	@Autowired
	private ActivityPrizeRuleDao activityPrizeRuleDao;
 
	
	/**
	 * 获取中奖名单列表
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @return  
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getWinningList(String userName){
		return this.activityWinningListDao.getWinningList(userName);
	}

 
	
	/**
	 * 添加活动日志
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param prizeId
	 * @param prizeIndex
	 * @param isWinning
	 * @param ip
	 * @param remark  
	 * void
	 */
	public void saveActivity(ActivityPrize activityPrize,String userName,Integer prizeIndex,Integer isWinning,String ip,String remark){
		logger.info("用户【"+userName+"】"+remark);
		
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserName(userName);
		activityLog.setPrizeId(activityPrize.getId());
		activityLog.setPrizeName(activityPrize.getPrizeName());
		activityLog.setActivityVal(prizeIndex+"");
		activityLog.setIsWinning(isWinning);
		activityLog.setIp(ip);
		activityLog.setRemark(remark);
		activityLog.setCreateTime(new Date());
		this.activityLogDao.save(activityLog);
		//中奖名单
		if(isWinning==1){
			ActivityWinningList activityWinningList = new ActivityWinningList();
			activityWinningList.setPrizeId(activityPrize.getId());
			activityWinningList.setPrizeName(activityPrize.getPrizeName());
			activityWinningList.setCreateTime(new Date());
			activityWinningList.setUserName(userName);
			activityWinningList.setUserIp(ip);
			this.activityWinningListDao.save(activityWinningList);
		}
		int rows = this.activityUserDao.updateActivityUser(userName);
		if(rows<=0){
			throw new RuntimeException("更新抽奖用户信息失败!");
		}
	}
	
	/**
	 * 查询活动规则
	 * @Description: TODO
	 * @param    
	 * @return List<ActivityRule>
	 * @author Andy
	 * @date 2015-9-18
	 */
	public List<ActivityRule> findRuleList(){
		return this.activityPrizeRuleDao.findRuleList();
	}
 
}
