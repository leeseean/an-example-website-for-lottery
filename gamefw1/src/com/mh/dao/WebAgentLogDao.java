/**   
* 文件名称: WebUserAgentLogDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-8-28 上午3:58:55<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.entity.WebAgentLog;

/** 
 * 代理操作日志
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-8-28 上午3:58:55<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebAgentLogDao extends BaseDao<WebAgentLog, Integer> {
	
	
	
	/**
	 * 操作日志列表
	 * 方法描述: TODO</br> 
	 * @param page
	 * @param webAgentLog
	 * @return  
	 * Page
	 */
	public Page findPage(Page page, WebAgentLog webAgentLog) {
		List<Object> list = new ArrayList<Object>();

		String sql = "SELECT t.*  FROM t_web_agent_log t where 1=1  ";
		//日志类别名称（登录、添加等）
		if(!StringUtils.isEmpty(webAgentLog.getLogName())){
			sql += " and t.log_name = ? ";
			list.add(webAgentLog.getLogName());
		}
	 
		//操作目标
		if (StringUtils.isNotBlank(webAgentLog.getOptTarget())) {
			sql += " and t.opt_target like ? ";
			list.add("%"+webAgentLog.getOptTarget()+"%");
		}
		
		//操作者
		if (StringUtils.isNotBlank(webAgentLog.getOptUser())) {
			sql += " and t.opt_user like ? ";
			list.add(webAgentLog.getOptUser()+"%");
		}
		
		/** 美东时间 **/
		if (StringUtils.isNotBlank(webAgentLog.getBeginTimeStr()) && StringUtils.isNotBlank(webAgentLog.getEndTimeStr())) {
			sql += " and date_format(t.opt_time,'%Y-%m-%d %H:%i:%s') >= ?";
			list.add(webAgentLog.getBeginTimeStr());
			sql += " and date_format(t.opt_time,'%Y-%m-%d %H:%i:%s') <= ?";
			list.add(webAgentLog.getEndTimeStr());
		}else if (StringUtils.isNotBlank(webAgentLog.getBeginTimeStr())) {
			sql += " and date_format(t.opt_time,'%Y-%m-%d %H:%i:%s') >= ?";
			list.add(webAgentLog.getBeginTimeStr());
		}else if (StringUtils.isNotBlank(webAgentLog.getEndTimeStr())) {
			sql += " and date_format(t.opt_time,'%Y-%m-%d %H:%i:%s') <= ?";
			list.add(webAgentLog.getEndTimeStr());
		}
	 
		
		sql+=" order by t.opt_time desc ";
		return findPageBySql(page, sql, list.toArray());
	}

}
