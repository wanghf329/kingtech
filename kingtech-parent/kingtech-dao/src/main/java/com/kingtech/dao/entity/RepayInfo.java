package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.LoanRecordEntity;
import com.kingtech.enums.PushStatus;

/**
 * 实际还款信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_REPAY_INFO")
@NoArgsConstructor
public class RepayInfo extends LoanRecordEntity {
	
	// 还款日期
	@Column(name="REPAY_DATE")
	private Date repayDate;
	
	// 还款金额
	@Column(name="REPAY_AMOUNT")
	private BigDecimal repayAmount;

	// 还款本金
	@Column(name="REPAY_PRINCIPAL_AMOUNT")
	private BigDecimal repayPrincipalAmount;

	// 还款利息
	@Column(name="REPAY_INTEREST_AMOUNT")
	private BigDecimal repayInterestAmount;

	public RepayInfo(String loanContractId, String reqId,
			PushStatus pushStatus, Date repayDate, BigDecimal repayAmount,
			BigDecimal repayPrincipalAmount, BigDecimal repayInterestAmount) {
		super(loanContractId, reqId, pushStatus);
		this.repayDate = repayDate;
		this.repayAmount = repayAmount;
		this.repayPrincipalAmount = repayPrincipalAmount;
		this.repayInterestAmount = repayInterestAmount;
	}

}
