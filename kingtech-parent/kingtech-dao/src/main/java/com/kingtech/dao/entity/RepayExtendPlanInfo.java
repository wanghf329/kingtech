package com.kingtech.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.LoanRecordEntity;
import com.kingtech.enums.PushStatus;

/**
 * 展期还款计划信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_REPAY_EXTEND_PLAN_INFO")
@NoArgsConstructor
public class RepayExtendPlanInfo extends LoanRecordEntity {
	
	// 第几次展期
	@Column(name="COUNT")
	private String count;

	public RepayExtendPlanInfo(String loanContractId, String reqId,
			PushStatus pushStatus, String count) {
		super(loanContractId, reqId, pushStatus);
		this.count = count;
	}
	
	


}
