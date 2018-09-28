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
	
	
	public List<RepayExtendPlanModel> listByPlanInfoId(String infoId);
	
	/**
	 * 添加还款计划
	 * @param plans
	 */
	public void addRepayExtendPlan(List<RepayExtendPlanModel> plans);
	/**
	 * 推送
	 * @param id
	 */
	public void pushRepayExtendPlanInfo(String id);
 }
