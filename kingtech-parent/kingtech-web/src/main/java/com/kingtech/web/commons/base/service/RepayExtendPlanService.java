package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.model.RepayExtendPlanModel;

public interface RepayExtendPlanService {
	
	
	public void addNew(String id, String loanContractId, String extendCount, String extendTerm,
			String repayDate, String principal, String returnPrincipal,
			String interest, String returnInterest, String status,
			String overdueFlag, String overdueDays);
	
	
	public  List<RepayExtendPlan> listAll();
	
	public RepayExtendPlanModel getById(String id);
	
}
