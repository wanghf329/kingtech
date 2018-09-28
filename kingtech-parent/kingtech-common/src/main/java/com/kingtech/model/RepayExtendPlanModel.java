package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 展期还款计划
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
public class RepayExtendPlanModel extends BaseModel{
	
	// 展期后的新还款利息（元）
	private BigDecimal interest;
	
	//展期后的新还款日期
	private Date endDate;
	
	// 展期后的新还款本金（元）
	private BigDecimal principal;
	
	private String repayExtendPlanInfoId;
	
	private int orderNumber;


	public RepayExtendPlanModel(String id, BigDecimal interest, Date endDate,
			BigDecimal principal) {
		super(id);
		this.interest = interest;
		this.endDate = endDate;
		this.principal = principal;
	}


	public RepayExtendPlanModel(BigDecimal interest, Date endDate,
			BigDecimal principal, String repayExtendPlanInfoId,
			int orderNumber) {
		this.interest = interest;
		this.endDate = endDate;
		this.principal = principal;
		this.repayExtendPlanInfoId = repayExtendPlanInfoId;
		this.orderNumber = orderNumber;
	}
	
	
	
}
