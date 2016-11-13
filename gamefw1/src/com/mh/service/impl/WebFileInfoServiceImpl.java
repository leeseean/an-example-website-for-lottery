
package com.mh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebFileInfoDao;
import com.mh.entity.TWebFileInfo;
import com.mh.service.WebFileInfoService;


@Service
public class WebFileInfoServiceImpl implements WebFileInfoService{
	
	@Autowired
	private WebFileInfoDao webFileInfoDao;
	
	/**
	 * 保存上传文件信息
	 * @Description: TODO
	 * @param    
	 * @return void
	 * @author Andy
	 * @date 2015-10-29
	 */
	public void saveFileInfo(TWebFileInfo entity){
		this.webFileInfoDao.saveFileInfo(entity);
	}
	
	
	
}
