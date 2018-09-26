package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.Capital;
import com.kingtech.model.CapitalModel;




public interface CapitalService {
	
	public Capital addNew(CapitalModel model);
	
	public List<Capital> listAll();
	
	public Capital getById(String id);
}
