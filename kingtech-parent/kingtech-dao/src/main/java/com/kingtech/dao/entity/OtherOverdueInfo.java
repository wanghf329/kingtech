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
 * 逾期信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_OTHER_OVERDUE_INFO")
@NoArgsConstructor
public class OtherOverdueInfo extends LoanRecordEntity {
	
	// 逾期金额(元)
	@Column(name="OVERDUE_MONEY")
	private BigDecimal overdueMoney;

	// 逾期日期
	@Column(name="OVERDUE_DATE")
	private Date overdueDate;

	// 逾期利息
	@Column(name="OVERDUE_INTEREST")
	private BigDecimal overdueInterest;

	// 贷款余额
	@Column(name="BALANCE")
	private BigDecimal balance;

	// 备注
	@Column(name="REMARKS")
	private String remarks;

	public OtherOverdueInfo(String loanContractId, String reqId,
			PushStatus pushStatus, BigDecimal overdueMoney, Date overdueDate,
			BigDecimal overdueInterest, BigDecimal balance, String remarks) {
		super(loanContractId, reqId, pushStatus);
		this.overdueMoney = overdueMoney;
		this.overdueDate = overdueDate;
		this.overdueInterest = overdueInterest;
		this.balance = balance;
		this.remarks = remarks;
	}

}
