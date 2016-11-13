package com.mh.service;

import java.util.Collection;
import java.util.List;

import com.mh.entity.WebGdElectronic;
import com.mh.entity.WebNewNtElectronic;
import com.mh.entity.WebTtgElectronic;

public interface WebTtgElectronicService {
	public void insertElectronic(Collection<WebTtgElectronic> entities);
	
	public void insertGdElectronic(Collection<WebGdElectronic> entities);
	
	public List<WebGdElectronic> getGdList();
	
	public WebTtgElectronic findElectronic(String gameName);
	
	public void updateGdPic(String picName,int id);
	
	public List<WebTtgElectronic> getTtgList();
	
	public void insertNntElectronic(Collection<WebNewNtElectronic> entities);
	
	public void testRollBack();
}
