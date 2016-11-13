package com.mh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebAgentPoundageDao;
import com.mh.service.WebAgentPoundageServive;

@Service
public class WebAgentPoundageServiveImpl implements WebAgentPoundageServive 
{

	@Autowired
	private WebAgentPoundageDao webAgentPoundageDao;
	
 
	public String getAgentPoundage() 
	{
		return this.webAgentPoundageDao.getAgentPoundage();
	}

}
