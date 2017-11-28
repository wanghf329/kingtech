package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.Capital;
import com.kingtech.model.CapitalModel;




public interface CapitalService {
	
	
	public Capital addNew(String id,String financingChannel,
			 double financingMoney, String financingTime, 
			 String expirationTime,
			 String replyTime,String branchId);
	
	public List<Capital> listAll();
	
	
	public CapitalModel getById(String id);
	
	public void delById(String id);
}
