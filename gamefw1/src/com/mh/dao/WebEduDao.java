/**   
* 文件名称: WebEduDao.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-2 下午2:39:07<br/>
*/  
package com.mh.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.commons.utils.DateUtil;
import com.mh.entity.WebEdu;

/** 
 * 额度转化记录表Dao
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-2 下午2:39:07<br/>
 */

@Repository
public class WebEduDao extends BaseDao<WebEdu,Integer>{
	
	/**
	 * 统计额度
	 * 方法描述: TODO</br> 
	 * @return  
	 * int
	 */
	public int getEduTotal(Integer eduType){
		String currDateStr = DateUtil.format(new Date(), "yyyy-MM");
		String sql = "SELECT SUM(edu_points) AS edu_points FROM t_web_edu WHERE edu_type=? AND edu_status=1 and DATE_FORMAT(create_time,'%Y-%m')='"+currDateStr+"' ";
		int total = this.getJdbcTemplate().queryForInt(sql,new Object[]{eduType});
		return total;
	}

	
	/**
	 * 额度转化记录流水列表
	 * 方法描述: TODO</br> 
	 * @param huikuan
	 * @return  
	 * List<WebBankHuikuan>
	 */
	@SuppressWarnings("unchecked")
	public List<WebEdu> getWebBankHuikuanList(WebEdu webEdu){
		List<Object> list = new ArrayList<Object>();
		
		String hql = " from WebEdu where userName=? ";
		list.add(webEdu.getUserName());
		
		if(!StringUtils.isEmpty(webEdu.getBeginDateStr()) && !StringUtils.isEmpty(webEdu.getEndDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d')>= ? and STR_TO_DATE(createTime,'%Y-%m-%d') <= ?";
			list.add(webEdu.getBeginDateStr());
			list.add(webEdu.getEndDateStr());
		}else if(!StringUtils.isEmpty(webEdu.getBeginDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d')>= ?  ";
			list.add(webEdu.getBeginDateStr());
		}else if(!StringUtils.isEmpty(webEdu.getEndDateStr())){
			hql += " and STR_TO_DATE(createTime,'%Y-%m-%d') <= ?";
			list.add(webEdu.getEndDateStr());
		}
		if(!StringUtils.isEmpty(webEdu.getFlatName())){
			hql += " and flatName=? ";
			list.add(webEdu.getFlatName());
		}
		
		if(webEdu.getEduType()!=null && webEdu.getEduType()!=0){
			hql += " and eduType=? ";
			list.add(webEdu.getEduType());
		}
		hql += " order by createTime desc ";
		
		return this.find(hql, list.toArray());
		
	}
	
	/**
	 * 更新额度转换记录
	 * 方法描述: TODO</br> 
	 * @param userName
	 * @param orderNo
	 * @param remark
	 * @param eduStatus  
	 * void
	 */
	public void updateEduRecord(String userName,String orderNo,String remark,int eduStatus){
		String sql = " update  t_web_edu set edu_forward_remark=?,edu_status=?  where user_name=? and edu_order=? ";
		this.getJdbcTemplate().update(sql, new Object[]{remark,eduStatus,userName,orderNo});
		
	}
	
	
	public int updateEdu(String userName,String orderNo,int valueStatus,int whereStatus){
		String sql = " update  t_web_edu set edu_status=?  where user_name=? and edu_order=? and edu_status=? ";
		return this.getJdbcTemplate().update(sql, new Object[]{valueStatus,userName,orderNo,whereStatus});
		
	}
	
}
