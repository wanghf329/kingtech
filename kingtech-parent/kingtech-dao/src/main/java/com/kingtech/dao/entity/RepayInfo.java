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
import com.kingtech.enums.RecordStatus;

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
	private Date repayTime;
	
	// 还款本金
	@Column(name="REPAY_PRINCIPAL_AMOUNT")
	private BigDecimal money;

	// 还款利息
	@Column(name="REPAY_INTEREST_AMOUNT")
	private BigDecimal interest;
	
	//罚息
	@Column(name="PENALTY_INTEREST_AMOUNT")
	private BigDecimal penaltyInterest;
	
	//违约金
	@Column(name="PENALTY_AMOUNT")
	private BigDecimal penalty;
	
	//服务费
	@Column(name="SERVICE_FEE_AMOUNT")
	private BigDecimal serviceCharge;
	
	//其他费用
	@Column(name="OTHER_FEE_AMOUNT")
	private BigDecimal otherCharge;

	public RepayInfo(String loanContractId, String reqId,
			PushStatus pushStatus, RecordStatus recordStatus, Date repayTime, BigDecimal money,
			BigDecimal interest, BigDecimal penaltyInterest,
			BigDecimal penalty, BigDecimal serviceCharge, BigDecimal otherCharge) {
		super(loanContractId, reqId, pushStatus, recordStatus);
		this.repayTime = repayTime;
		this.money = money;
		this.interest = interest;
		this.penaltyInterest = penaltyInterest;
		this.penalty = penalty;
		this.serviceCharge = serviceCharge;
		this.otherCharge = otherCharge;
	}
}
