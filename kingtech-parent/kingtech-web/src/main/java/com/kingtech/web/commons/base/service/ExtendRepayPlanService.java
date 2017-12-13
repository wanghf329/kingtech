package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.ext.RepayExtendPlanModelExt;

public interface ExtendRepayPlanService {
	
	/**
	 * 
	 * @param id
	 * @param loanContractId
	 * @param extendCount
	 * @param extendTerm
	 * @param repayDate
	 * @param principal
	 * @param returnPrincipal
	 * @param interest
	 * @param returnInterest
	 * @param status
	 * @param overdueFlag
	 * @param overdueDays
	 */
	public void addNew(String id, String loanContractId, String extendCount, String extendTerm,
			String repayDate, String principal, String returnPrincipal,
			String interest, String returnInterest, String status,
			String overdueFlag, String overdueDays);
	
	/**
	 * 
	 * @return
	 */
	public  List<RepayExtendPlanModelExt> listAll();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public RepayExtendPlanModel getById(String id);
	
}
