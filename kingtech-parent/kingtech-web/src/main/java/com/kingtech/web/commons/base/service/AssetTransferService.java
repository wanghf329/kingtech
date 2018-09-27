package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kingtech.dao.entity.AssetTransfer;
import com.kingtech.model.AssetTransferModel;

public interface AssetTransferService {
	/**
	 * 
	 * @param id
	 * @param loanContractId
	 * @param transferNumber
	 * @param transferMoney
	 * @param originalMoney
	 * @param discountMoney
	 * @param acceptUnit
	 * @param protocol
	 * @param transferDate
	 */
	public void addOrEdit(String id, String loanContractId, String transferNumber,
			BigDecimal transferMoney, BigDecimal originalMoney,
			BigDecimal discountMoney, String acceptUnit, String protocol,
			Date transferDate);
	/**
	 * 
	 * @return
	 */
	public List<AssetTransfer> listAll();
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AssetTransferModel getById(String id);
}
