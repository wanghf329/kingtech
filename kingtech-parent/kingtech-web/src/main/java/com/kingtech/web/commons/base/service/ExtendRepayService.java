package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.ext.RepayExtendInfoModelExt;
import com.kingtech.model.misc.PagedResult;

public interface ExtendRepayService {
	/**
	 * 
	 * @param id
	 * @param loanContractId
	 * @param extendNum
	 * @param repayDate
	 * @param repayAmount
	 * @param repayPrincipalAmount
	 * @param repayInterestAmount
	 */
	public void addOrEdit(String id,String loanContractId,long extendNum, 
						  Date repayDate,BigDecimal repayAmount, 
						  BigDecimal repayPrincipalAmount,
						  BigDecimal repayInterestAmount);
	/**
	 * 
	 * @return
	 */
	public List<RepayExtendInfoModelExt> listAll();
	/**
	 * 
	 * @param id
	 * @return
	 */
	public RepayExtendInfoModel getById(String id);
	
	
	public PagedResult<RepayExtendInfoModelExt> pageList(Pageable pageAble);
}
