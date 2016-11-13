/**   
* 文件名称: WebSportResultsServiceImpl.java<br/>
* 版本号: V1.0<br/>   
* 创建人: Channel<br/>  
* 创建时间 : 2015-7-16 下午1:38:43<br/>
*/  
package com.mh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebSportResultsDao;
import com.mh.entity.TResultMatchBk;
import com.mh.entity.TResultMatchFt;
import com.mh.service.WebSportResultsService;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-16 下午1:38:43<br/>
 */

@Service
public class WebSportResultsServiceImpl implements WebSportResultsService{
	@Autowired
	private WebSportResultsDao webSportResultDao;

	/**
	 * 得到篮球赛果
	 * 方法描述: TODO</br> 
	 * @param tableName
	 * @param curTag
	 * @return  
	 * TResultMatchBk
	 */
	public List<TResultMatchBk> getResultBkSport(String serachDate) {
		// TODO Auto-generated method stub
		return webSportResultDao.getResultBkSport(serachDate);
	}

	public List<TResultMatchFt> getResultFtSport(String serachDate) {
		// TODO Auto-generated method stub
		return webSportResultDao.getResultFtSport(serachDate);
	}
	
	/**
     * 消息列表
     * 方法描述: TODO</br> 
     * @param dateStr
     * @return  
     * List<Map<String,Object>>
     */
    public List<Map<String,Object>> getMessageList(String dateStr,String fField){
    	return this.webSportResultDao.getMessageList(dateStr,fField);
    }
    
    
    /**
     * 获取公告前几条列表
     * 方法描述: TODO</br> 
     * @return  
     * List<String>
     */
    public List<String> getSportMessageLimit(){
    	return this.webSportResultDao.getSportMessageLimit();
    }
}
