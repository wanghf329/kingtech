package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.model.RepayExtendPlanInfoModel;
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
						String count);
	/**
	 * 添加展期计划
	 * @param id
	 * @param principal
	 * @param endDate
	 * @param interest
	 */
	public void addRepayExtendPlan(String id,BigDecimal principal ,Date endDate,BigDecimal interest);
	
	public RepayExtendPlanInfoModel getPlanInfoById(String id);
				
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
	
	
	public PagedResult<RepayExtendPlanInfoModel> pageList(Pageable pageAble);
}
