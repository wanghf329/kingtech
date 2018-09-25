package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kingtech.dao.entity.ProvisionInfo;
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
	public void addOrEdit(String id, Date dateMonth,
			BigDecimal normalBalance, BigDecimal normalRate,
			BigDecimal normalReal, BigDecimal followBalance,
			BigDecimal followRate, BigDecimal followReal,
			BigDecimal minorBalance, BigDecimal minorRate,
			BigDecimal minorReal, BigDecimal suspiciousBalance,
			BigDecimal suspiciousRate, BigDecimal suspiciousReal,
			BigDecimal lossBalance, BigDecimal lossRate, BigDecimal lossReal);
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
