package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.FinanceRepayPlan;
import com.kingtech.dao.rdbms.FinancingRepayPlanDAO;
import com.kingtech.model.FinanceRepayPlanModel;
import com.kingtech.web.commons.base.service.FinanceRepayPlanService;

@Service
public class FinancingRepayPlanServiceImpl implements FinanceRepayPlanService{
	
	@Autowired
	private FinancingRepayPlanDAO financingRepayPlanDAO;

	@Override
	public List<FinanceRepayPlan> listfinanceRepayById(String financeId) {
		return financingRepayPlanDAO.listByFinanceId(financeId);
	}

	@Override
	@Transactional
	public void deleteFinanceRepayPlan(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FinanceRepayPlan addNew(FinanceRepayPlanModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinanceRepayPlan getFinanceRepayPlanById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
