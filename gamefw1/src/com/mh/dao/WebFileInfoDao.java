package com.mh.dao;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.BaseDao;
import com.mh.entity.TWebFileInfo;

@Repository
public class WebFileInfoDao extends BaseDao<TWebFileInfo,Integer>{

	/**
	 * 保存上传文件信息
	 * @Description: TODO
	 * @param    
	 * @return void
	 * @author Andy
	 * @date 2015-10-29
	 */
	public void saveFileInfo(TWebFileInfo entity){
		super.save(entity);
	}
}
