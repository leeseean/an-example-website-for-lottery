/**   
* 文件名称: LotteryDataHelper.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-9-17 上午4:55:48<br/>
*/  
package com.mh.web.job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mh.commons.utils.DateUtil;
import com.mh.commons.utils.MathUtil;

/** 
 * 
 * 抽奖结算
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-9-17 上午4:55:48<br/>
 */
public class LotteryDataHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(LotteryDataHelper.class);
	
	private JdbcTemplate jdbcTemplate;

	private HibernateTemplate hibernateTemplate;
	
	int maxTimes = 999;
	
	
	public synchronized void init(){
		logger.info("抽奖结算开始....");
		Date currDate = new Date();
		
		Date lastDate = DateUtil.addDay(currDate, -1);
		String dateStr = DateUtil.format(lastDate, "yyyy-MM-dd");
		String maxTimesStr = this.getActivityInfo("max_sum_times");
		if(!StringUtils.isEmpty(maxTimesStr)){
			maxTimes = Integer.valueOf(maxTimesStr);
		}
 
		String isDai = this.getActivityInfo("is_dai_rule");
		if(StringUtils.isEmpty(isDai)){
			isDai = "0";
		}
		
		Map<String,Double> depositMap = this.getUserDepositFree(dateStr);
		Map<String,Double> incomeMap = this.getUserIncome(dateStr);
		Map<String,Integer> lottTimesMap = getActivityUserLottTimes();
		if("0".equals(isDai)){
			this.updateLottDai(lottTimesMap, incomeMap, depositMap);
		}else if("1".equals(isDai)){
			this.updateLott(lottTimesMap, incomeMap, "1");
		}else if("2".equals(isDai)){
			this.updateLott(lottTimesMap, depositMap, "2");
		}else{
			logger.info("配置错误,请检查!");
			return;
		}
 
		logger.info("抽奖结算结束....");
	}
	
	public void updateLottDai(Map<String,Integer> lottTimesMap,Map<String,Double> incomeMap,Map<String,Double> depositMap){
		Date currDate = new Date();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd HH:mm:ss");
		
		for(String userName:incomeMap.keySet()){
			double income=incomeMap.get(userName);
			if(depositMap.get(userName)!=null ){
				double deposit = depositMap.get(userName);
				List<Object> ruleList = this.getActivityRule(deposit);
				if(ruleList!=null){
					int inBs = Integer.valueOf(ruleList.get(0).toString());
					int lottTimes = Integer.valueOf(ruleList.get(1).toString());
					double beginDeposit =Double.valueOf(ruleList.get(2).toString());
					double endDeposit =Double.valueOf(ruleList.get(3).toString());
					//有效投注是存款的X倍以上
					double AA1 = MathUtil.mul(deposit, inBs);
					AA1 = MathUtil.round(AA1,2);
					logger.info("用户【"+userName+"】，存款【"+deposit+"】,有效投注【"+income+"】,存款倍数【"+inBs+"】");
					if(income>=AA1){
						List<Object> usrList = new ArrayList<Object>();
						usrList.add(userName);
						usrList.add(lottTimes);
						usrList.add(1);
						usrList.add(currDateStr);
						usrList.add(currDateStr);
						if(lottTimesMap!=null && lottTimesMap.get(userName)!=null){
							logger.info("用户【"+userName+"】已经超过最高累计次数:"+maxTimes);
							 continue;
						}else{
							logger.info("用户【"+userName+"】赠送抽奖次数【"+lottTimes+"】");
							this.updateActivityUser(usrList);
						}
						//用户结算日志
						List<Object> usrLogList = new ArrayList<Object>();
						usrLogList.add(userName);
						usrLogList.add(income);
						usrLogList.add(deposit);
						usrLogList.add(inBs);
						usrLogList.add(AA1);
						usrLogList.add(lottTimes);
						usrLogList.add(currDateStr);
						usrLogList.add(beginDeposit);
						usrLogList.add(endDeposit);
						this.updateActivityUserLog(usrLogList);
					}else{
						logger.info("用户【"+userName+"】,有效投注【"+income+"】小于最低存款倍数【"+AA1+"】，不符合条件！！");
					}
					
				}	
			}
		}
		
		
	}
	
	
	public void updateLott(Map<String,Integer> lottTimesMap,Map<String,Double> valMap,String reFlag){
		String remark="";
		if("1".equals(reFlag)){
			remark = "有效投注量";
		}else if("2".equals(reFlag)){
			remark = "存款金额";
		}
		Date currDate = new Date();
		String currDateStr = DateUtil.format(currDate, "yyyy-MM-dd HH:mm:ss");
		for(String userName:valMap.keySet()){
			double total=valMap.get(userName);
			List<Object> ruleList = this.getActivityRule(total);
			if(ruleList==null){
				logger.info("用户【"+userName+"】"+remark+"【"+total+"】不符合条件!!!!!!!");
				continue;
			}else{
				logger.info("用户【"+userName+"】"+remark+"【"+total+"】符合条件!");
			}
			int inBs = Integer.valueOf(ruleList.get(0).toString());
			int lottTimes = Integer.valueOf(ruleList.get(1).toString());
			double beginDeposit =Double.valueOf(ruleList.get(2).toString());
			double endDeposit =Double.valueOf(ruleList.get(3).toString());
			List<Object> usrList = new ArrayList<Object>();
			usrList.add(userName);
			usrList.add(lottTimes);
			usrList.add(1);
			usrList.add(currDateStr);
			usrList.add(currDateStr);
			if(lottTimesMap!=null && lottTimesMap.get(userName)!=null){
				logger.info("用户【"+userName+"】已经超过最高累计次数:"+maxTimes);
				continue;
			}else{
				logger.info("用户【"+userName+"】赠送抽奖次数【"+lottTimes+"】");
				this.updateActivityUser(usrList);
			}
			double AA1=0;
			//用户结算日志
			List<Object> usrLogList = new ArrayList<Object>();
			usrLogList.add(userName);
			if("1".equals(reFlag)){//投注量
				usrLogList.add(total);
				usrLogList.add(0);
			}else{//存款
				usrLogList.add(0);
				usrLogList.add(total);
			}
			usrLogList.add(inBs);
			usrLogList.add(AA1);
			usrLogList.add(lottTimes);
			usrLogList.add(currDateStr);
			usrLogList.add(beginDeposit);
			usrLogList.add(endDeposit);
			this.updateActivityUserLog(usrLogList);
		}
		
		
		
	}
	
	
	/**
	 * 存取款
	 * 方法描述: TODO</br> 
	 * @return  
	 * Map<String,Double>
	 */
	public Map<String, Double> getUserDepositFree(String betDateStr) {
		
		Map<String, Double> valMap = new HashMap<String,Double>();
		String sql = "SELECT user_name,ROUND(SUM(act_opt_money),2) AS act_opt_money FROM t_web_account  ";
		sql += " where date_format(gmt_4_time,'%Y-%m-%d') ='"+betDateStr+"' ";
		sql += " and FIND_IN_SET(act_opt_type,'1,2,4') ";
		sql += " GROUP BY user_name ";
		 
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(sql);
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			if(map.get("user_name")!=null){
				String user_name = map.get("user_name").toString();
				Double money = 0d;
				if(map.get("act_opt_money")!=null){
					money = Double.valueOf(map.get("act_opt_money").toString());
				}
				valMap.put(user_name, money);
			}
		}
		return valMap;
	}
	
	
	/**
	 * 获取用户有效投注量
	 * 方法描述: TODO</br> 
	 * @param betDateStr
	 * @return  
	 * Map<String,Double>
	 */
	public Map<String, Double> getUserIncome(String betDateStr) {
		Map<String, Double> valMap = new HashMap<String,Double>();
		String sql = " SELECT bet_user_name,ROUND(SUM(bet_income),2) AS bet_income  FROM t_bet_day_report_user" +
				" WHERE date_format(bet_date,'%Y-%m-%d') = '"+betDateStr+"' GROUP BY bet_user_name ";
		 
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(sql);
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			if(map.get("bet_user_name")!=null){
				String user_name = map.get("bet_user_name").toString();
				Double money = 0d;
				if(map.get("bet_income")!=null){
					money = Double.valueOf(map.get("bet_income").toString());
				}
				valMap.put(user_name, money);
			}
		}
		return valMap;
	}
	
	
	/**
	 * 获取活动规则
	 * 方法描述: TODO</br> 
	 * @param totalMoney
	 * @return  
	 * List<String>
	 */
	public List<Object> getActivityRule(double totalMoney){
		BigDecimal bd= new BigDecimal(Double.toString(totalMoney)).setScale(0, BigDecimal.ROUND_HALF_UP);
		int totalM = bd.intValue();
		
		String sql = "select t.* from t_activity_rule t where t.begin_deposit<="+totalM+" and t.end_deposit >="+totalM;
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(sql);
		
		List<Object> valList = null;
		if(list!=null && list.size()>0){
			valList = new ArrayList<Object>();
			for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				
				valList.add(map.get("in_bs"));
				valList.add(map.get("lott_times"));
				valList.add(map.get("begin_deposit"));
				valList.add(map.get("end_deposit"));
			}
		}
		return valList;
	}
	
	
	
	public void updateActivityUser(List<Object> list){
		 
		StringBuffer values = new StringBuffer("");
		for(int i=0;i<list.size();i++){
			Object o = list.get(i);
			if(i>0){
				values.append(",");
			}
			if (o instanceof Double || o instanceof Integer) {
				values.append(o.toString());
			} else{
				values.append("'").append(o.toString()).append("'");
			} 
		}
		
		String sql = "insert  into  t_activity_user(user_name,lott_times,add_times,create_time,modify_time) " +
				"values " +
				"("+values.toString()+ ")";
		sql += " ON DUPLICATE KEY UPDATE user_name=VALUES(user_name), modify_time=VALUES(modify_time),lott_times=lott_times+VALUES(lott_times),add_times=add_times+1";
		this.jdbcTemplate.update(sql);
 
		
	}
 
	
	public Map<String,Integer> getActivityUserLottTimes(){
		
	 
		
		String sql = "select user_name,add_times from t_activity_user where add_times >="+maxTimes;
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(sql);
		
		Map<String,Integer> valMap = null;
		if(list!=null && list.size()>0){
			valMap = new HashMap<String,Integer>();
			for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				String userName = map.get("user_name").toString();
				int addtimes = 0;
				if(map.get("add_times")!=null){
					addtimes = Integer.valueOf(map.get("add_times").toString());
				}
				valMap.put(userName, addtimes);
			}
		}
		return valMap;
		
	}
	
	
	
	public boolean updateActivityUserLog(List<Object> list){
		boolean reFlag = true;
		try{
			StringBuffer values = new StringBuffer("");
			for(int i=0;i<list.size();i++){
				Object o = list.get(i);
				if(i>0){
					values.append(",");
				}
				if (o instanceof Double || o instanceof Integer) {
					values.append(o.toString());
				} else{
					values.append("'").append(o.toString()).append("'");
				} 
			}
			
			String sql = "insert  into  t_activity_user_log(user_name,bet_income,deposit,in_bs,bs_deposit_total,lott_times,update_time,begin_val,end_val) " +
					"values " +
					"("+values.toString()+ ")";
			this.jdbcTemplate.update(sql);
		}catch(Exception e){
			reFlag = false;
			e.printStackTrace();
			logger.error("更新活动用户日志异常!",e);
		}
		return reFlag;
		
	}
	
	
//	public String getSysParamval(String paramName){
//		String sql = "select param_value from t_sys_parameter where param_name=?";
//		Map<String,Object> valMap =this.getJdbcTemplate().queryForMap(sql, new Object[]{paramName});
//		String paramVal="";
//		if(valMap!=null && valMap.size()>0){
//			paramVal = valMap.get("param_value").toString();
//		}
//		
//		return paramVal;
//	}
	
	
	public String getActivityInfo(String columnName){
		String sql = "select * from t_activity_info where status=1";
		String val = "";
		List<Map<String,Object>> list= this.getJdbcTemplate().queryForList(sql);
		if(list!=null && list.size()>0){
			Map<String,Object> map = list.get(0);
			if(map.get(columnName)!=null){
				val = map.get(columnName).toString();
			}
			
		}
		
		return val;	
	}

	public LotteryDataHelper(JdbcTemplate jdbcTemplate,
			HibernateTemplate hibernateTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.hibernateTemplate = hibernateTemplate;
	}



	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
