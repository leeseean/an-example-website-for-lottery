package com.mh.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.WebAgentPoundage;
@Repository
public class WebAgentPoundageDao extends BaseDao<WebAgentPoundage, Integer>
{
	/**
	 * 获取行政费用
	 * @return
	 */
	public String getAgentPoundage()
	{
		String sql = "SELECT pd_xz FROM t_web_agent_poundage";
		Map<String, Object> map = this.getJdbcTemplate().queryForMap(sql);
		return (String) map.get("pd_xz");
	}
}
