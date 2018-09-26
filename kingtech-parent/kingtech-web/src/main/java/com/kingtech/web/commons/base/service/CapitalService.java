package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kingtech.dao.entity.Capital;
import com.kingtech.enums.ChannelTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.model.CapitalModel;




public interface CapitalService {
	
	
	public Capital addNewaddNew(String id, String financeNumber,
							 String financeName, String lender, ChannelTypeEnum channel,
							 BigDecimal money, BigDecimal interest, BigDecimal guaranteeMoney,
							 String remark, Date financeDate, Date endDate, BigDecimal rate,
							 RateTypeEnum rateType, String loanContractNumber);
	
	public List<Capital> listAll();
	
	
	public CapitalModel getById(String id);
	
	public void delById(String id);
}
