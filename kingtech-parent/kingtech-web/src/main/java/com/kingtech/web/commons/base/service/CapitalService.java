package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.Capital;




public interface CapitalService {
	
	
	public Capital addNew(String financingChannel,
			 double financingMoney, String financingTime, 
			 String expirationTime,
			 String replyTime,String branchId);
	
	public List<Capital> listAll();
}
