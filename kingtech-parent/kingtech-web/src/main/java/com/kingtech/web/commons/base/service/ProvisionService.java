package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kingtech.dao.entity.ProvisionInfo;
import com.kingtech.enums.LoanClassificationEnum;
import com.kingtech.model.ProvisionInfoModel;

public interface ProvisionService {
	/**
	 * 
	 * @param provisionMoney
	 * @param provisionDate
	 * @param provisionScale
	 * @param loanClassification
	 * @param balance
	 */
	public void addOrEdit(String id,BigDecimal provisionMoney,
						  Date provisionDate, BigDecimal provisionScale,
						  LoanClassificationEnum loanClassification, 
						  BigDecimal balance);
	/**
	 * 
	 * @return
	 */
	public List<ProvisionInfo> listAll();
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ProvisionInfoModel getById(String id);
}
