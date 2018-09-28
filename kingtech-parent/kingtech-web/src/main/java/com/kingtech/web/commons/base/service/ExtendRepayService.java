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
	 * @param repayTime
	 * @param money
	 * @param interest
	 * @param penaltyInterest
	 * @param penalty
	 * @param serviceCharge
	 * @param otherCharge
	 */
	public void addOrEdit(String id,
			String loanContractId,
			Date repayTime,
			BigDecimal money, 
			BigDecimal interest,
			BigDecimal penaltyInterest, 
			BigDecimal penalty,
			BigDecimal serviceCharge, 
			BigDecimal otherCharge);
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
	
	
	//查询
	public void syncextendRepayInfoPushStatus();
	
	
}
