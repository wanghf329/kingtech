package com.kingtech.web.commons.base.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.ext.ModelExt;
import com.kingtech.model.ext.RepayExtendPlanModelExt;
import com.kingtech.model.misc.PagedResult;

public interface ExtendRepayPlanService {
	/**
	 * 添加展期计划信息
	 * @param id
	 * @param loanContractId
	 * @param count
	 * @param endDate
	 * @param principal
	 * @param interest
	 */
	public void addNew(String id, 
						String loanContractId, 
						String count,
						String endDate, 
						String principal,
						String interest);
				
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
	
	
	public PagedResult<ModelExt> pageList(Pageable pageAble);
}
