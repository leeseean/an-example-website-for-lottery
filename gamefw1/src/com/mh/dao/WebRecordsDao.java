/**   
 * 文件名称: WebRecordsDao.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: zoro<br/>  
 * 创建时间 : 2015-7-2 下午3:03:11<br/>
 */
package com.mh.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mh.commons.conf.WebConstants;
import com.mh.commons.orm.BaseDao;
import com.mh.commons.orm.Page;
import com.mh.entity.TBetRecordAg;
import com.mh.entity.TBetRecordMg;
import com.mh.entity.TBetRecordOs;
import com.mh.entity.TBetRecordPt;
import com.mh.entity.TWebBankHuikuan;
import com.mh.entity.TWebMatchBet;
import com.mh.entity.TWebMatchDetail;
import com.mh.entity.WebEdu;
import com.mh.entity.WebRecords;
import com.mh.entity.WebUserWithdraw;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-7-2 下午3:03:11<br/>
 */
@SuppressWarnings("all")
@Repository
public class WebRecordsDao extends BaseDao {

	public List<Map<String, Object>> report(WebRecords records) {

		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT bet_flat AS betFlat, bet_user_agent AS userAgent, ROUND(SUM(bet_in),2) AS betIn, ROUND(SUM(bet_income),2) AS betIncome,");
		sqlBuff.append(" ROUND(SUM(bet_net_win),2) AS betNetWin ,SUM(bet_total) AS betTotal ");
		sqlBuff.append(" FROM t_bet_day_report_agent where 1=1 ");
		sqlBuff.append(appendReportSql4(records));
		sqlBuff.append(" GROUP BY betFlat");

		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sqlBuff.toString());
		return list2;
	}

	public List<Map<String, Object>> reportUser(WebRecords records) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT bet_flat AS betFlat,  ROUND(SUM(bet_in),2) AS betIn, ROUND(SUM(bet_income),2) AS betIncome,");
		sqlBuff.append(" ROUND(SUM(bet_net_win),2) AS betNetWin ,SUM(bet_total) AS betTotal ");
		sqlBuff.append(" FROM t_bet_day_report_user where 1=1 ");
		sqlBuff.append(appendReportSql4(records));
		sqlBuff.append(" GROUP BY betFlat");

		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sqlBuff.toString());
		return list2;
	}

	private String appendReportSql4(WebRecords records) {
		String sql = "";
		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sql += " and STR_TO_DATE(bet_date,'%Y-%m-%d')>= '" + records.getStartTime() + "' and STR_TO_DATE(bet_date,'%Y-%m-%d') <= '" + records.getEndTime() + "'";
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sql += " and STR_TO_DATE(bet_date,'%Y-%m-%d')>= '" + records.getStartTime() + "' ";
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sql += " and STR_TO_DATE(bet_date,'%Y-%m-%d') <= '" + records.getEndTime() + "' ";
		}
		if (StringUtils.isNotEmpty(records.getUserAgent())) {
			sql += " AND bet_user_agent = '" + records.getUserAgent() + "'";
		}
		if (StringUtils.isNotEmpty(records.getUserName())) {
			sql += " AND bet_user_name = '" + records.getUserName() + "'";
		}
		return sql;
	}

	/**
	 * 
	 * 查询财务记录 方法描述: TODO</br>
	 * 
	 * @param page
	 * @param records
	 * @return Page
	 */
	public Page findFinancePage(Page page, WebRecords records) {
		List<Object> list = new ArrayList<Object>();
		String sql = "";
		if ("eduHistory".equals(records.getCode())) {
			sql = " select * from  t_web_edu where 1=1 ";
		} else if ("payHistory".equals(records.getCode())) {
			sql = " select * from t_web_bank_huikuan where 1=1 ";
		} else if ("withdrawHistory".equals(records.getCode())) {
			sql = " select * from t_web_user_withdraw where 1=1 ";
		}

		if (StringUtils.isNotEmpty(records.getUserName())) {
			sql += " and user_name = ? ";
			list.add(records.getUserName());
		}
		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sql += " and STR_TO_DATE(create_time,'%Y-%m-%d')>= ? and STR_TO_DATE(create_time,'%Y-%m-%d') <= ?";
			list.add(records.getStartTime());
			list.add(records.getEndTime());
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sql += " and STR_TO_DATE(create_time,'%Y-%m-%d')>= ?  ";
			list.add(records.getStartTime());
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sql += " and STR_TO_DATE(create_time,'%Y-%m-%d') <= ?";
			list.add(records.getEndTime());
		}

		if (!StringUtils.isEmpty(records.getHkModel())) {
			sql += " and hk_model =?";
			list.add(records.getHkModel());
		}

		if (!StringUtils.isEmpty(records.getWithdrawType())) {
			sql += " and withdraw_type =?";
			list.add(records.getWithdrawType());
		}

		if (!StringUtils.isEmpty(records.getFlatName())) {
			sql += " and flat_name =?";
			list.add(records.getFlatName());
		}

		if (!StringUtils.isEmpty(records.getEduType())) {
			sql += " and edu_Type=? ";
			list.add(records.getEduType());
		}

		sql += " order by create_time desc ";
		return this.findPageBySql(page, sql, list.toArray());
	}

	public List<WebEdu> getEduList(final WebRecords records) {
		StringBuffer sb = new StringBuffer(" from WebEdu where 1 = 1 and userName = '" + records.getUserName() + "'");
		if (StringUtils.isNotBlank(records.getFlatName()))
			sb.append(" and flatName = '" + records.getFlatName() + "'");

		if (StringUtils.isNotBlank(records.getEduType()))
			sb.append(" and eduType = '" + records.getEduType() + "'");
		if (StringUtils.isNotBlank(records.getStartTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d')>= '" + records.getStartTime() + "'");
		if (StringUtils.isNotBlank(records.getEndTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d') <= '" + records.getEndTime() + "'");
		sb.append(" ORDER BY createTime DESC");

		final String sql = sb.toString();
		List<WebEdu> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final Query query = session.createQuery(sql);
				query.setFirstResult(0);
				query.setMaxResults(records.getCount());
				return query.list();
			}
		});
		return list;

	}

	public List<TWebBankHuikuan> getHuiKuan(final WebRecords records) {
		StringBuffer sb = new StringBuffer(" FROM TWebBankHuikuan WHERE 1 = 1");
		sb.append(" AND userName = '" + records.getUserName() + "'");

		if (StringUtils.isNotBlank(records.getHkModel()))
			sb.append(" and hkModel = '" + records.getHkModel() + "'");
		if (StringUtils.isNotBlank(records.getStartTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d')>= '" + records.getStartTime() + "'");
		if (StringUtils.isNotBlank(records.getEndTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d') <= '" + records.getEndTime() + "'");
		sb.append(" ORDER BY createTime DESC");

		final String sql = sb.toString();
		List<TWebBankHuikuan> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final Query query = session.createQuery(sql);
				query.setFirstResult(0);
				query.setMaxResults(records.getCount());
				return query.list();
			}
		});
		return list;
	}

	public List<WebUserWithdraw> getWithdrawList(final WebRecords records) {
		StringBuffer sb = new StringBuffer(" from WebUserWithdraw where 1 = 1");
		sb.append(" AND userName = '" + records.getUserName() + "'");
		if (StringUtils.isNotBlank(records.getWithdrawType()))
			sb.append(" and withdrawType = " + records.getWithdrawType() + "");
		if (StringUtils.isNotBlank(records.getStartTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d')>= '" + records.getStartTime() + "'");
		if (StringUtils.isNotBlank(records.getEndTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d') <= '" + records.getEndTime() + "'");
		sb.append(" ORDER BY createTime DESC");

		final String sql = sb.toString();
		List<WebUserWithdraw> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final Query query = session.createQuery(sql);
				query.setFirstResult(0);
				query.setMaxResults(records.getCount());
				return query.list();
			}
		});
		return list;
	}

	public Page<TWebMatchBet> pageForSportBet(Page<TWebMatchBet> page, WebRecords records) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" from TWebMatchBet WHERE 1=1 ");
		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" AND STR_TO_DATE(orderTime,'%Y-%m-%d')>=? and STR_TO_DATE(orderTime,'%Y-%m-%d') <=? ");
			list.add(records.getStartTime());
			list.add(records.getEndTime());
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sqlBuff.append(" AND STR_TO_DATE(orderTime,'%Y-%m-%d')>=? ");
			list.add(records.getStartTime());
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" AND STR_TO_DATE(orderTime,'%Y-%m-%d') <=? ");
			list.add(records.getEndTime());
		}
		if (!StringUtils.isEmpty(records.getUserName())) {
			sqlBuff.append(" AND betUserName = ?");
			list.add(records.getUserName());
		}
		if (!StringUtils.isEmpty(records.getBetSportType())) {
			sqlBuff.append(" AND betSportType = ?");
			list.add(records.getBetSportType());
		}
		
		if (!StringUtils.isEmpty(records.getStatus())) {
			String[] statusArray = records.getStatus().split(",");
			if (statusArray.length == 1) {
				sqlBuff.append(" AND status = ? ");
				list.add(Integer.valueOf(records.getStatus()));
			} else {
				sqlBuff.append(" AND (status = ? OR status = ? OR status = ?) ");
				list.add(Integer.valueOf(statusArray[0]));
				list.add(Integer.valueOf(statusArray[1]));
				list.add(Integer.valueOf(statusArray[2]));
			}

		}
		sqlBuff.append(" order by id desc");
		return this.findPage(page, sqlBuff.toString(), list.toArray());
	}

	public List<TWebMatchDetail> listForMatchDetail(WebRecords records) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" from TWebMatchDetail where 1=1 ");
		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" and STR_TO_DATE(betTime,'%Y-%m-%d')>=? and STR_TO_DATE(betTime,'%Y-%m-%d') <=? ");
			list.add(records.getStartTime());
			list.add(records.getEndTime());
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sqlBuff.append(" and STR_TO_DATE(betTime,'%Y-%m-%d')>=? ");
			list.add(records.getStartTime());
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" and STR_TO_DATE(betTime,'%Y-%m-%d') <=? ");
			list.add(records.getEndTime());
		}
		return this.find(sqlBuff.toString(), list.toArray());
	}

	public List<TWebMatchBet> listSportBets(WebRecords records) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" select * from t_web_match_bet WHERE 1=1 ");
		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='" + records.getStartTime() + "' and STR_TO_DATE(order_time,'%Y-%m-%d') <='" + records.getEndTime() + "' ");
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='" + records.getStartTime() + "' ");
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sqlBuff.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d') <='" + records.getEndTime() + "' ");
		}
		if (!StringUtils.isEmpty(records.getUserName())) {
			sqlBuff.append(" and bet_user_name='" + records.getUserName() + "' ");
		}
		if (!StringUtils.isEmpty(records.getBetStatus())) {
			sqlBuff.append(" and FIND_IN_SET(status, '" + records.getBetStatus() + "') ");
		}
		sqlBuff.append(" order by id desc");
		final String sql = sqlBuff.toString();
		List<TWebMatchBet> resultList = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(sql).addEntity(TWebMatchBet.class);
				return sqlQuery.list();
			}
		});
		return resultList;
	}

	/***
	 * 方法描述: 统计注单内容 创建人: zoro<br/>
	 * 
	 * @param bean
	 * @return Map<String,Object>
	 */
	public Map<String, Object> selectCountBetOrder(WebRecords bean) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select IFNULL(SUM(bet_in),0) as sumIn, count(1) as count, IFNULL(SUM(bet_in),0) as sumInCome, IFNULL(SUM(bet_usr_win),0) as sumWin, IFNULL(SUM(bet_can_win),0) as sumCanWin from t_web_match_bet ");
		buffer.append(" where 1 = 1 ");

		if (StringUtils.isNotEmpty(bean.getBetSportType())) {
			buffer.append(" and bet_sport_type = '" + bean.getBetSportType() + "' ");
		}
		if (StringUtils.isNotEmpty(bean.getUserName())) {
			buffer.append(" and bet_user_name='" + bean.getUserName() + "' ");
		}
		if (!StringUtils.isEmpty(bean.getStartTime()) && !StringUtils.isEmpty(bean.getEndTime())) {
			buffer.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='" + bean.getStartTime() + "' and STR_TO_DATE(order_time,'%Y-%m-%d') <='" + bean.getEndTime() + "' ");
		} else if (!StringUtils.isEmpty(bean.getStartTime())) {
			buffer.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d')>='" + bean.getStartTime() + "' ");
		} else if (!StringUtils.isEmpty(bean.getEndTime())) {
			buffer.append(" AND STR_TO_DATE(order_time,'%Y-%m-%d') <='" + bean.getEndTime() + "' ");
		}
		if (StringUtils.isNotEmpty(bean.getBetStatus())) {
			buffer.append(" and FIND_IN_SET(status, '" + bean.getBetStatus() + "') ");
		}
		final String sqlstr = buffer.toString();
		Map<String, Object> resultList = this.getJdbcTemplate().queryForMap(sqlstr);
		return resultList;
	}

	public Page findRecordsPage(Page page, WebRecords records) {
		List<Object> list = new ArrayList<Object>();
		String sql = "";

		if (WebConstants.FLAT_NAME_SPORT.equals(records.getCode())) {
			sql = " select * from  t_web_match_bet where 1=1 ";
		} else if (WebConstants.FLAT_NAME_AG.equals(records.getCode())) {
			sql = " select * from t_bet_record_ag where 1=1 ";
		} else if (WebConstants.FLAT_NAME_HG.equals(records.getCode())) {
			sql = " select * from t_bet_record_hg where 1=1 ";
		} else if (WebConstants.FLAT_NAME_BBIN.equals(records.getCode())) {
			sql = " select * from t_bet_record_bbin where 1=1 ";
		} else if (WebConstants.FLAT_NAME_MG.equals(records.getCode())) {
			sql = " select * from t_bet_record_mg where 1=1 ";
		} else if (WebConstants.FLAT_NAME_DS.equals(records.getCode())) {
			sql = " select * from t_bet_record_ds where 1=1 ";
		} else if (WebConstants.FLAT_NAME_NEWNT.equals(records.getCode())) {
			sql = " select * from t_bet_record_nt where 1=1 ";
		} else if (WebConstants.FLAT_NAME_PT.equals(records.getCode())) {
			sql = " select * from t_bet_record_pt where 1=1 ";
		} else if (WebConstants.FLAT_NAME_DJ.equals(records.getCode())) {
			sql = " select * from t_bet_record_douji where 1=1 ";
		} else if (WebConstants.FLAT_NAME_AB.equals(records.getCode())) {
			sql = " select * from t_bet_record_ab where 1=1 ";
		} else if (WebConstants.FLAT_NAME_OG.equals(records.getCode())) {
			sql = " select * from t_bet_record_og where 1=1 ";
		} else if (WebConstants.FLAT_NAME_OS.equals(records.getCode())) {
			sql = " select * from t_bet_record_os where 1=1 ";
		} else if (WebConstants.PLAT_PARAM_SB_SPORT.equals(records.getCode())) {
			sql = " select * from t_bet_record_sb where 1=1 and bet_content_mark='" + WebConstants.SB_SPORT + "' ";
		} else if (WebConstants.PLAT_PARAM_SB_CASION.equals(records.getCode())) {
			sql = " select * from t_bet_record_sb where 1=1 and bet_content_mark='" + WebConstants.SB_CASION + "' ";
		} else if (WebConstants.FLAT_NAME_GD.equals(records.getCode())) {
			sql = " select * from t_bet_record_gd where 1=1 ";
		} else if (WebConstants.FLAT_NAME_TTG.equals(records.getCode())) {
			sql = " select * from t_bet_record_ttg where 1=1 ";
		} else if (WebConstants.FLAT_NAME_SBT.equals(records.getCode())) {
			sql = " select * from t_bet_record_sbt where 1=1 ";
		} else if (WebConstants.FLAT_NAME_SA.equals(records.getCode())) {
			sql = " select * from t_bet_record_sa where 1=1 ";
		} else if (WebConstants.FLAT_NAME_VG.equals(records.getCode())) {
			sql = " select * from t_bet_record_vg where 1=1 ";
		}

		if (StringUtils.isNotEmpty(records.getUserName())) {
			sql += " AND bet_user_name = ? ";
			list.add(records.getUserName());
		}
		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sql += " and STR_TO_DATE(bet_time,'%Y-%m-%d')>= ? and STR_TO_DATE(bet_time,'%Y-%m-%d') <= ?";
			list.add(records.getStartTime());
			list.add(records.getEndTime());
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sql += " and STR_TO_DATE(bet_time,'%Y-%m-%d')>= ?  ";
			list.add(records.getStartTime());
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sql += " and STR_TO_DATE(bet_time,'%Y-%m-%d') <= ?";
			list.add(records.getEndTime());
		}

		sql += " order by bet_time desc ";

		return this.findPageBySql(page, sql, list.toArray());
	}

	/**
	 * ag注单
	 * 
	 * @param ag
	 * @return
	 */
	public List<TBetRecordAg> getAgRecordList(final WebRecords record) {
		StringBuffer sb = new StringBuffer(" FROM TBetRecordAg WHERE 1 = 1");
		sb.append(" AND betUserName = '" + record.getUserName() + "'");
		if (StringUtils.isNotBlank(record.getBetStatus()))
			sb.append(" and status = '" + record.getBetStatus() + "'");
		if (StringUtils.isNotBlank(record.getStartTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d')>= '" + record.getStartTime() + "'");
		if (StringUtils.isNotBlank(record.getEndTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d') <= '" + record.getEndTime() + "'");
		sb.append(" ORDER BY createTime DESC");

		final String sql = sb.toString();
		List<TBetRecordAg> list = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final Query query = session.createQuery(sql);
				query.setFirstResult(0);
				query.setMaxResults(record.getCount());
				return query.list();
			}
		});
		return list;
	}

	/**
	 * 电子注单查询
	 * 
	 * @param page
	 * @param records
	 * @return
	 */
	public Page findEleRecordPage(Page page, WebRecords records) {
		List<Object> list = new ArrayList<Object>();
		String sql = " select * from  t_bet_record_" + records.getBetSportType() + " where 1=1 ";

		if (StringUtils.isNotEmpty(records.getUserName())) {
			sql += " and bet_user_name = ? ";
			list.add(records.getUserName());
		}
		if (StringUtils.isNotEmpty(records.getBetStatus())) {
			sql += " and status = ? ";
			list.add(records.getBetStatus());
		}

		if (!StringUtils.isEmpty(records.getStartTime()) && !StringUtils.isEmpty(records.getEndTime())) {
			sql += " and STR_TO_DATE(bet_time,'%Y-%m-%d') >= ? and STR_TO_DATE(bet_time,'%Y-%m-%d') <= ?";
			list.add(records.getStartTime());
			list.add(records.getEndTime());
		} else if (!StringUtils.isEmpty(records.getStartTime())) {
			sql += " and STR_TO_DATE(bet_time,'%Y-%m-%d') >= ?  ";
			list.add(records.getStartTime());
		} else if (!StringUtils.isEmpty(records.getEndTime())) {
			sql += " and STR_TO_DATE(bet_time,'%Y-%m-%d') <= ?";
			list.add(records.getEndTime());
		}

		sql += " order by bet_time desc ";
		return this.findPageBySql(page, sql, list.toArray());
	}

	/**
	 * mg注单
	 * 
	 * @param ag
	 * @return
	 */
	public List<TBetRecordMg> getMgRecordList(final WebRecords record) {
		StringBuffer sb = new StringBuffer(" FROM TBetRecordMg WHERE 1 = 1");
		sb.append(" AND betUserName = '" + record.getUserName() + "'");
		if (StringUtils.isNotBlank(record.getBetStatus()))
			sb.append(" and status = '" + record.getBetStatus() + "'");
		if (StringUtils.isNotBlank(record.getStartTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d')>= '" + record.getStartTime() + "'");
		if (StringUtils.isNotBlank(record.getEndTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d') <= '" + record.getEndTime() + "'");
		sb.append(" ORDER BY createTime DESC");

		final String sql = sb.toString();
		List<TBetRecordMg> list = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final Query query = session.createQuery(sql);
				query.setFirstResult(0);
				query.setMaxResults(record.getCount());
				return query.list();
			}
		});
		return list;
	}

	public List<TBetRecordPt> getPtRecordList(final WebRecords record) {
		StringBuffer sb = new StringBuffer(" FROM TBetRecordPt WHERE 1 = 1");
		sb.append(" AND betUserName = '" + record.getUserName() + "'");
		if (StringUtils.isNotBlank(record.getBetStatus()))
			sb.append(" and status = '" + record.getBetStatus() + "'");
		if (StringUtils.isNotBlank(record.getStartTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d')>= '" + record.getStartTime() + "'");
		if (StringUtils.isNotBlank(record.getEndTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d') <= '" + record.getEndTime() + "'");
		sb.append(" ORDER BY createTime DESC");

		final String sql = sb.toString();
		logger.info(sql);
		List<TBetRecordPt> list = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final Query query = session.createQuery(sql);
				query.setFirstResult(0);
				query.setMaxResults(record.getCount());
				return query.list();
			}
		});
		return list;
	}

	public List<TBetRecordOs> getOsRecordList(final WebRecords record) {
		StringBuffer sb = new StringBuffer(" FROM TBetRecordOs WHERE 1 = 1");
		sb.append(" AND betUserName = '" + record.getUserName() + "'");
		if (StringUtils.isNotBlank(record.getBetStatus()))
			sb.append(" and status = '" + record.getBetStatus() + "'");
		if (StringUtils.isNotBlank(record.getStartTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d')>= '" + record.getStartTime() + "'");
		if (StringUtils.isNotBlank(record.getEndTime()))
			sb.append(" and STR_TO_DATE(createTime,'%Y-%m-%d') <= '" + record.getEndTime() + "'");
		sb.append(" ORDER BY createTime DESC");

		final String sql = sb.toString();
		List<TBetRecordOs> list = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final Query query = session.createQuery(sql);
				query.setFirstResult(0);
				query.setMaxResults(record.getCount());
				return query.list();
			}
		});
		return list;
	}

}
