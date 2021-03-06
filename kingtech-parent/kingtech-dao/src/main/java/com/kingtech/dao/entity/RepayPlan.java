package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.PackageEntity;

/**
 * 还款计划表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_CONTRACT_REPAY_PLAN")
@NoArgsConstructor
public class RepayPlan extends PackageEntity {
	
	// 还款日期，格式YYYY-MM-DD
	@Column(name="END_DATE")
	private Date endDate;

	// 还款本金（元）
	@Column(name="PRINCIPAL")
	private BigDecimal money;

	// 还款利息（元）
	@Column(name="INTEREST")
	private BigDecimal interest;

	public RepayPlan(String loanContractId, int orderNum, Date endDate,
			BigDecimal money, BigDecimal interest) {
		super(loanContractId, orderNum);
		this.endDate = endDate;
		this.money = money;
		this.interest = interest;
	}
}
