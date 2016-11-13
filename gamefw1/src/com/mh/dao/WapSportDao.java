package com.mh.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.cache.MemCachedUtil;
import com.mh.commons.conf.SportConstant;
import com.mh.commons.orm.SharedBaseDao;
import com.mh.commons.utils.JackUtil;
import com.mh.entity.TBkMatchRmain;
import com.mh.entity.TFtMatchF;
import com.mh.entity.TFtMatchP3;
import com.mh.entity.TFtMatchPD;
import com.mh.entity.TFtMatchR;
import com.mh.entity.TFtMatchT;
import com.mh.entity.TMatchControl;
import com.mh.entity.TRMatchRE;
import com.mh.entity.TRMatchRemain;
@SuppressWarnings("all")
@Repository
public class WapSportDao extends SharedBaseDao {
	
	private List<String> innerSql(StringBuffer sql,String gid,String curTag){
		List<String> param = new ArrayList<String>();
		if(StringUtils.isNotBlank(curTag)){
			sql.append("and curtag = ?");
			param.add(curTag);
		}
		if(StringUtils.isNotBlank(gid)){
			sql.append(" and gid = ?");
			param.add(gid);
		}
		return param;
	}
	
	/**
	 * 获取足球 单式
	 * @param curTag
	 * @return
	 */
	public List<TFtMatchR> getFtMatchR(String curTag,String gid){
		StringBuffer sql = new StringBuffer(" from TFtMatchR where 1 = 1 ");
		List<String> param = innerSql(sql,gid,curTag);
		return this.getHibernateTemplate_shared().find(sql.toString(), param.toArray());
	}
	
	public List<TFtMatchR> getFtMatchR(TMatchControl control,String gid){
		List<TFtMatchR> list = new ArrayList<TFtMatchR>();
		if(StringUtils.isNotBlank(control.getCurtag()) && StringUtils.isNotBlank(gid)){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchR> matchList = (List<TFtMatchR>) JackUtil.toList(value, TFtMatchR.class);
				for (TFtMatchR match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag()) && StringUtils.equals(gid, match.getGid())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(control.getCurtag())){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchR> matchList = (List<TFtMatchR>) JackUtil.toList(value, TFtMatchR.class);
				for (TFtMatchR match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(gid)){
			boolean flag = false;
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				if(StringUtils.isNotEmpty(value)){
					List<TFtMatchR> matchList = (List<TFtMatchR>) JackUtil.toList(value, TFtMatchR.class);
					for (TFtMatchR match : matchList) {
						if(match.getGid().equals(gid)){
							list.add(match);
							flag = true;
							break;
						}
					}
				}
				if(flag){
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取足球 波胆
	 * @param curTag
	 * @return
	 */
	
	public List<TFtMatchPD> getFtMatchPD(String curTag,String gid){
		StringBuffer sql = new StringBuffer(" from TFtMatchPD where 1 = 1 ");
		List<String> param = innerSql(sql,gid,curTag);
		return this.getHibernateTemplate_shared().find(sql.toString(), param.toArray());
	}
	
	public List<TFtMatchPD> getFtMatchPD(TMatchControl control,String gid){
		List<TFtMatchPD> list = new ArrayList<TFtMatchPD>();
		if(StringUtils.isNotBlank(control.getCurtag()) && StringUtils.isNotBlank(gid)){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchPD> matchList = (List<TFtMatchPD>) JackUtil.toList(value, TFtMatchPD.class);
				for (TFtMatchPD match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag()) && StringUtils.equals(gid, match.getGid())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(control.getCurtag())){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchPD> matchList = (List<TFtMatchPD>) JackUtil.toList(value, TFtMatchPD.class);
				for (TFtMatchPD match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(gid)){
			boolean flag = false;
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				if(StringUtils.isNotEmpty(value)){
					List<TFtMatchPD> matchList = (List<TFtMatchPD>) JackUtil.toList(value, TFtMatchPD.class);
					for (TFtMatchPD match : matchList) {
						if(match.getGid().equals(gid)){
							list.add(match);
							flag = true;
							break;
						}
					}
				}
				if(flag){
					break;
				}
			}
		}
		return list;
	}
	/**
	 * 获取足球 总入球
	 * @param curTag
	 * @return
	 */
	
	public List<TFtMatchT> getFtMatchT(String curTag,String gid){
		StringBuffer sql = new StringBuffer(" from TFtMatchT where 1 = 1 ");
		List<String> param = innerSql(sql,gid,curTag);
		return this.getHibernateTemplate_shared().find(sql.toString(), param.toArray());
	}
	public List<TFtMatchT> getFtMatchT(TMatchControl control,String gid){
		List<TFtMatchT> list = new ArrayList<TFtMatchT>();
		if(StringUtils.isNotBlank(control.getCurtag()) && StringUtils.isNotBlank(gid)){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchT> matchList = (List<TFtMatchT>) JackUtil.toList(value, TFtMatchT.class);
				for (TFtMatchT match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag()) && StringUtils.equals(gid, match.getGid())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(control.getCurtag())){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchT> matchList = (List<TFtMatchT>) JackUtil.toList(value, TFtMatchT.class);
				for (TFtMatchT match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(gid)){
			boolean flag = false;
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				if(StringUtils.isNotEmpty(value)){
					List<TFtMatchT> matchList = (List<TFtMatchT>) JackUtil.toList(value, TFtMatchT.class);
					for (TFtMatchT match : matchList) {
						if(match.getGid().equals(gid)){
							list.add(match);
							flag = true;
							break;
						}
					}
				}
				if(flag){
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取足球 全半场
	 * @param curTag
	 * @return
	 */
	
	public List<TFtMatchF> getFtMatchF(String curTag,String gid){
		StringBuffer sql = new StringBuffer(" from TFtMatchF where 1 = 1 ");
		List<String> param = innerSql(sql,gid,curTag);
		return this.getHibernateTemplate_shared().find(sql.toString(), param.toArray());
	}
	public List<TFtMatchF> getFtMatchF(TMatchControl control,String gid){
		List<TFtMatchF> list = new ArrayList<TFtMatchF>();
		if(StringUtils.isNotBlank(control.getCurtag()) && StringUtils.isNotBlank(gid)){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchF> matchList = (List<TFtMatchF>) JackUtil.toList(value, TFtMatchF.class);
				for (TFtMatchF match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag()) && StringUtils.equals(gid, match.getGid())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(control.getCurtag())){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchF> matchList = (List<TFtMatchF>) JackUtil.toList(value, TFtMatchF.class);
				for (TFtMatchF match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(gid)){
			boolean flag = false;
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				if(StringUtils.isNotEmpty(value)){
					List<TFtMatchF> matchList = (List<TFtMatchF>) JackUtil.toList(value, TFtMatchF.class);
					for (TFtMatchF match : matchList) {
						if(match.getGid().equals(gid)){
							list.add(match);
							flag = true;
							break;
						}
					}
				}
				if(flag){
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取篮球 单式
	 * @param curTag
	 * @return
	 */
	
	public List<TBkMatchRmain> getBkMatchRmain(String curTag,String gid){
		StringBuffer sql = new StringBuffer(" from TBkMatchRmain where 1 = 1 ");
		List<String> param = innerSql(sql,gid,curTag);
		return this.getHibernateTemplate_shared().find(sql.toString(), param.toArray());
	}
	public List<TBkMatchRmain> getBkMatchRmain(TMatchControl control,String gid){
		List<TBkMatchRmain> list = new ArrayList<TBkMatchRmain>();
		if(StringUtils.isNotBlank(control.getCurtag()) && StringUtils.isNotBlank(gid)){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TBkMatchRmain> matchList = (List<TBkMatchRmain>) JackUtil.toList(value, TBkMatchRmain.class);
				for (TBkMatchRmain match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag()) && StringUtils.equals(gid, match.getGid())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(control.getCurtag())){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TBkMatchRmain> matchList = (List<TBkMatchRmain>) JackUtil.toList(value, TBkMatchRmain.class);
				for (TBkMatchRmain match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(gid)){
			boolean flag = false;
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				if(StringUtils.isNotEmpty(value)){
					List<TBkMatchRmain> matchList = (List<TBkMatchRmain>) JackUtil.toList(value, TBkMatchRmain.class);
					for (TBkMatchRmain match : matchList) {
						if(match.getGid().equals(gid)){
							list.add(match);
							flag = true;
							break;
						}
					}
				}
				if(flag){
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 综合过关
	 * @param curTag
	 * @param gid
	 * @return
	 */
	
	public List<TFtMatchP3> getFtMatchP3(String curTag,String gid){
		StringBuffer sql = new StringBuffer(" from TFtMatchP3 where 1 = 1 ");
		List<String> param = innerSql(sql,gid,curTag);
		return this.getHibernateTemplate_shared().find(sql.toString(), param.toArray());
	}
	public List<TFtMatchP3> getFtMatchP3(TMatchControl control,String gid){
		List<TFtMatchP3> list = new ArrayList<TFtMatchP3>();
		if(StringUtils.isNotBlank(control.getCurtag()) && StringUtils.isNotBlank(gid)){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchP3> matchList = (List<TFtMatchP3>) JackUtil.toList(value, TFtMatchP3.class);
				for (TFtMatchP3 match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag()) && StringUtils.equals(gid, match.getGid())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(control.getCurtag())){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TFtMatchP3> matchList = (List<TFtMatchP3>) JackUtil.toList(value, TFtMatchP3.class);
				for (TFtMatchP3 match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(gid)){
			boolean flag = false;
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				if(StringUtils.isNotEmpty(value)){
					List<TFtMatchP3> matchList = (List<TFtMatchP3>) JackUtil.toList(value, TFtMatchP3.class);
					for (TFtMatchP3 match : matchList) {
						if(match.getGid().equals(gid)){
							list.add(match);
							flag = true;
							break;
						}
					}
				}
				if(flag){
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取滚球 足球
	 * @param curTag
	 * @return
	 */
	
	public List<TRMatchRE> getRollMatchRE(String curTag,String gid){
		StringBuffer sql = new StringBuffer(" from TRMatchRE where 1 = 1 ");
		List<String> param = innerSql(sql,gid,curTag);
		return this.getHibernateTemplate_shared().find(sql.toString(), param.toArray());
	}
	public List<TRMatchRE> getRollMatchRE(TMatchControl control,String gid){
		List<TRMatchRE> list = new ArrayList<TRMatchRE>();
		if(StringUtils.isNotBlank(control.getCurtag()) && StringUtils.isNotBlank(gid)){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TRMatchRE> matchList = (List<TRMatchRE>) JackUtil.toList(value, TRMatchRE.class);
				for (TRMatchRE match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag()) && StringUtils.equals(gid, match.getGid())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(control.getCurtag())){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TRMatchRE> matchList = (List<TRMatchRE>) JackUtil.toList(value, TRMatchRE.class);
				for (TRMatchRE match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(gid)){
			boolean flag = false;
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				if(StringUtils.isNotEmpty(value)){
					List<TRMatchRE> matchList = (List<TRMatchRE>) JackUtil.toList(value, TRMatchRE.class);
					for (TRMatchRE match : matchList) {
						if(match.getGid().equals(gid)){
							list.add(match);
							flag = true;
							break;
						}
					}
				}
				if(flag){
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取滚球 篮球
	 * @param curTag
	 * @return
	 */
	
	public List<TRMatchRemain> getRollMatchRemain(String curTag,String gid){
		StringBuffer sql = new StringBuffer(" from TRMatchRemain where 1 = 1");
		List<String> param = innerSql(sql,gid,curTag);
		return this.getHibernateTemplate_shared().find(sql.toString(), param.toArray());
	}
	public List<TRMatchRemain> getRollMatchRemain(TMatchControl control,String gid){
		List<TRMatchRemain> list = new ArrayList<TRMatchRemain>();
		if(StringUtils.isNotBlank(control.getCurtag()) && StringUtils.isNotBlank(gid)){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TRMatchRemain> matchList = (List<TRMatchRemain>) JackUtil.toList(value, TRMatchRemain.class);
				for (TRMatchRemain match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag()) && StringUtils.equals(gid, match.getGid())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(control.getCurtag())){
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				List<TRMatchRemain> matchList = (List<TRMatchRemain>) JackUtil.toList(value, TRMatchRemain.class);
				for (TRMatchRemain match : matchList) {
					if(StringUtils.equals(control.getCurtag(), match.getCurtag())){
						list.add(match);
					}
				}
			}
		}else if(StringUtils.isNotBlank(gid)){
			boolean flag = false;
			for (int i = 1; i <= control.getCurpages(); i++) {
				String key = control.getTimeType() + "_" + control.getRtype() + "_" + control.getVersion() + "_" + i;
				Map<String, String> map = MemCachedUtil.getForMap(key);
				String value = map.get(SportConstant.DATA);
				if(StringUtils.isNotEmpty(value)){
					List<TRMatchRemain> matchList = (List<TRMatchRemain>) JackUtil.toList(value, TRMatchRemain.class);
					for (TRMatchRemain match : matchList) {
						if(match.getGid().equals(gid)){
							list.add(match);
							flag = true;
							break;
						}
					}
				}
				if(flag){
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取联赛名称
	 * 方法描述: TODO</br> 
	 * @param tableName
	 * @param curTag
	 * @return  
	 * List<String>
	 */
	public List<Map<String, Object>> getLeague(String tableName,String curTag){
		String sql = "SELECT league FROM  "+tableName+" WHERE curTag=? GROUP BY league";
		return this.getJdbcTemplate_shared().queryForList(sql,new Object[]{curTag});
	}
}
