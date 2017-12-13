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
import com.kingtech.enums.YesNoEnum;

/**
 * 展期还款计划表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_REPAY_EXTEND_PLAN")
@NoArgsConstructor
public class RepayExtendPlan extends LoanRecordEntity {
	
	// 展期次数
	@Column(name="EXTEND_COUNT")
	private long extendCount;

	// 展期期限
	@Column(name="EXTEND_TERM")
	private String extendTerm;

	// 展期后的新还款日期
	@Column(name="REPAY_DATE")
	private Date repayDate;

	// 展期后的新还款本金（元）
	@Column(name="PRINCIPAL")
	private BigDecimal principal;

	// 已还本金（元），有实际还款需填充数据
	@Column(name="RETURN_PRINCIPAL")
	private BigDecimal returnPrincipal;
	
	// 展期后的新还款利息（元）
	@Column(name="INTEREST")
	private BigDecimal interest;
		
	// 已还利息（元）
	@Column(name="RETURN_INTEREST")
	private BigDecimal returnInterest;
	
	// 状态，值为未还、已还
	@Column(name="STATUS")
	private String status;
	
	// 是否逾期
	@Column(name="OVERDUE_FLAG")
	private YesNoEnum overdueFlag;
	
	// 逾期天数
	@Column(name="OVERDUE_DAYS")
	private long overdueDays;

	public RepayExtendPlan(String loanContractId, String reqId,
			PushStatus pushStatus, long extendCount, String extendTerm,
			Date repayDate, BigDecimal principal, BigDecimal returnPrincipal,
			BigDecimal interest, BigDecimal returnInterest, String status,
			YesNoEnum overdueFlag, long overdueDays) {
		super(loanContractId, reqId, pushStatus);
		this.extendCount = extendCount;
		this.extendTerm = extendTerm;
		this.repayDate = repayDate;
		this.principal = principal;
		this.returnPrincipal = returnPrincipal;
		this.interest = interest;
		this.returnInterest = returnInterest;
		this.status = status;
		this.overdueFlag = overdueFlag;
		this.overdueDays = overdueDays;
	}


}
