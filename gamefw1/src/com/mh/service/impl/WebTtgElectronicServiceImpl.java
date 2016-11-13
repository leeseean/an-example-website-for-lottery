package com.mh.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.dao.WebDamaDao;
import com.mh.dao.WebTtgElectronicDao;
import com.mh.entity.TWebDama;
import com.mh.entity.WebGdElectronic;
import com.mh.entity.WebNewNtElectronic;
import com.mh.entity.WebTtgElectronic;
import com.mh.service.WebTtgElectronicService;
@Service
public class WebTtgElectronicServiceImpl implements WebTtgElectronicService {
	
	@Autowired
	private WebTtgElectronicDao webTtgElectronicDao;
	
	@Autowired
	private WebDamaDao webDamaDao;
	
	public void insertElectronic(Collection<WebTtgElectronic> entities) {
		webTtgElectronicDao.insertElectronic(entities);
	}

	public WebTtgElectronic findElectronic(String gameName) {
		return this.webTtgElectronicDao.findElectronic(gameName);
	}

	public void insertGdElectronic(Collection<WebGdElectronic> entities) {
		webTtgElectronicDao.insertGdElectronic(entities);
	}

	public List<WebGdElectronic> getGdList() {
		return this.webTtgElectronicDao.getGdList();
	}

	public void updateGdPic(String picName,int id) {
		this.webTtgElectronicDao.updateGdPic(picName,id);
	}

	public List<WebTtgElectronic> getTtgList() {
		return this.webTtgElectronicDao.getTtgList();
	}

	public void insertNntElectronic(Collection<WebNewNtElectronic> entities) {
		this.webTtgElectronicDao.insertNntElectronic(entities);
	}

	public void testRollBack() {
		try {
			List<TWebDama> hibernateList = new ArrayList<TWebDama>();
			for (int i = 1; i <= 10; i++) {
				TWebDama dama = new TWebDama();
				dama.setUserName("王杰");
				hibernateList.add(dama);
			}
			this.webDamaDao.saveHibernate(hibernateList);
			
			List<TWebDama> jabcList = new ArrayList<TWebDama>();
			for (int i = 11; i <= 20; i++) {
				TWebDama dama = new TWebDama();
				dama.setId(i);
				dama.setUserName("王杰");
				jabcList.add(dama);
			}
			this.webDamaDao.saveJdbcTemplate(jabcList);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//throw new RuntimeException("回滚吧 我的异常");
	}

}
