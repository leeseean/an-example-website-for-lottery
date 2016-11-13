/**   
* 文件名称: WebClientInfoServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2016-1-14 上午10:41:02<br/>
*/  
package com.mh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebFlatInfoDao;
import com.mh.dao.WebWeihuDao;
import com.mh.service.WebClientInfoService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2016-1-14 上午10:41:02<br/>
 */
@Service
public class WebClientInfoServiceImpl implements WebClientInfoService {
	
	@Autowired
	private WebWeihuDao webWeihuDao;
	@Autowired
	private WebFlatInfoDao webFlatInfoDao;
	
	
	public void updateBatchWeihu(List<Object[]> batchArgs) {
		String updateSql = "UPDATE t_web_weihu SET weihu_content=?, status=? WHERE weihu_name=?";
		webWeihuDao.batchUpdateMg(updateSql, batchArgs);
	}
	
	public void updateBatchFlatInfo(List<Object[]> batchArgs) {
		String initSql = "UPDATE t_web_flat_info SET flat_status=0";
		webFlatInfoDao.executeUpdateBySql(initSql);//初始化
		
		String updateSql = "UPDATE t_web_flat_info SET flat_status=?, flat_index=? WHERE flat=? and flat_type=?";
		webFlatInfoDao.batchUpdateMg(updateSql, batchArgs);//批量更新
	}

}
