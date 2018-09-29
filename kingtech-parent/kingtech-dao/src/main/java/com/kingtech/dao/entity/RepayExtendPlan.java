package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.UuidEntity;

/**
 * 展期还款计划表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_REPAY_EXTEND_PLAN")
@NoArgsConstructor
public class RepayExtendPlan extends UuidEntity {
	
	@Column(name="REPAY_EXTEND_PLAN_INFO_ID")
	private String repayExtendPlanInfoId;
	
	// 展期后的新还款日期,格式YYYY-MM-DD
	@Column(name="END_DATE")
	private Date endDate;

	// 展期后的新还款本金（元）
	@Column(name="PRINCIPAL")
	private BigDecimal principal;
	
	// 展期后的新还款利息（元）
	@Column(name="INTEREST")
	private BigDecimal interest;
	
	//期数
	@Column(name="ORDER_NUMBER")
	private int orderNumber;

	public RepayExtendPlan(String repayExtendPlanInfoId, 
			Date endDate, BigDecimal principal, BigDecimal interest) {
		super();
		this.repayExtendPlanInfoId = repayExtendPlanInfoId;
		this.endDate = endDate;
		this.principal = principal;
		this.interest = interest;
	}

}
