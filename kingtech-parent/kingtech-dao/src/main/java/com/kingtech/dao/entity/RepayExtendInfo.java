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
 * 展期还款信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_REPAY_EXTEND_INFO")
@NoArgsConstructor
public class RepayExtendInfo extends LoanRecordEntity {
	
	//还款日期，格式YYYY-MM-DD
	@Column(name="REPAY_TIME")
	private Date repayTime;
	
	//还款本金
	@Column(name="MONEY")
	private BigDecimal money;
	
	//还款利息
	@Column(name="INTEREST")
	private BigDecimal interest;
	
	//罚息
	@Column(name="PENALTY_INTEREST")
	private BigDecimal penaltyInterest;
	
	//违约金
	@Column(name="PENALTY")
	private BigDecimal penalty;
	
	//服务费
	@Column(name="SERVICE_CHARGE")
	private BigDecimal serviceCharge;
	
	// 其它费用（不确定到以上5个科目的，均放其它），用户从申请借款开始向小贷支付的金额均反映在还款（展期还款）接口
	@Column(name="OTHER_CHARGE")
	private BigDecimal otherCharge;

	public RepayExtendInfo(String loanContractId, String reqId,
			PushStatus pushStatus, Date repayTime, BigDecimal money,
			BigDecimal interest, BigDecimal penaltyInterest,
			BigDecimal penalty, BigDecimal serviceCharge, BigDecimal otherCharge) {
		super(loanContractId, reqId, pushStatus);
		this.repayTime = repayTime;
		this.money = money;
		this.interest = interest;
		this.penaltyInterest = penaltyInterest;
		this.penalty = penalty;
		this.serviceCharge = serviceCharge;
		this.otherCharge = otherCharge;
	}

}
