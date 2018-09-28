package com.kingtech.web.commons.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.Capital;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.FinanceRepayPlanModel;
import com.kingtech.model.misc.PagedResult;




public interface CapitalService {
	
	public Capital addNew(CapitalModel model);
	
	public List<Capital> listAll();
	
	public Capital getById(String id);
	
	public PagedResult<Capital> pageList(Pageable pageAble);
	
	public void addRepayPlan(String financeId, List<FinanceRepayPlanModel> repayPlanList);
	
	public List<Capital> listFinanceNumberByStatus(RecordStatus status);
	
	public void syncCapitalPushStatus();
}
