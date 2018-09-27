package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.FinanceRepayPlan;
import com.kingtech.dao.rdbms.FinanceRepayPlanDAO;
import com.kingtech.model.FinanceRepayPlanModel;
import com.kingtech.web.commons.base.service.FinanceRepayPlanService;

@Service
public class FinanceRepayPlanServiceImpl implements FinanceRepayPlanService{
	
	@Autowired
	private FinanceRepayPlanDAO financingRepayPlanDAO;

	@Override
	public List<FinanceRepayPlan> listfinanceRepayById(String financeId) {
		return financingRepayPlanDAO.listByFinanceId(financeId);
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



	@Override
	@Transactional
	public void deleteFinanceRepayPlanByFinanceId(String financeId) {
		financingRepayPlanDAO.delectByFinanceId(financeId);
	}

	

}
