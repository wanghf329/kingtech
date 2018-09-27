package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.FinanceRepayPlan;
import com.kingtech.model.FinanceRepayPlanModel;

public interface FinanceRepayPlanService {
	
	public List<FinanceRepayPlan> listfinanceRepayById(String financeId);
	
	public void deleteFinanceRepayPlanByFinanceId (String financeId);
	
	public FinanceRepayPlan addNew(FinanceRepayPlanModel model);
	
	public FinanceRepayPlan getFinanceRepayPlanById(String id);

}
