package com.mh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.SysFlatWeiHuDao;
import com.mh.entity.SysFlatWeiHu;
import com.mh.service.SysFlatWeiHuService;
@Service
public class SysFlatWeiHuServiceImpl implements SysFlatWeiHuService {

	@Autowired
	private SysFlatWeiHuDao sysFlatWeiHuDao;
	
	public SysFlatWeiHu getStatus(String flat) {
		return this.sysFlatWeiHuDao.getStatus(flat);
	}

}
