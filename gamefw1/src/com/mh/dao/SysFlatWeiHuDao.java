package com.mh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mh.commons.orm.SharedBaseDao;
import com.mh.entity.SysFlatWeiHu;

@SuppressWarnings("all")
@Repository
public class SysFlatWeiHuDao extends SharedBaseDao<SysFlatWeiHu, String> {
	public SysFlatWeiHu getStatus(String flat) {
		List<SysFlatWeiHu> list = this.getHibernateTemplate_shared().find(" from SysFlatWeiHu where flat = ?", new Object[] { flat });
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
