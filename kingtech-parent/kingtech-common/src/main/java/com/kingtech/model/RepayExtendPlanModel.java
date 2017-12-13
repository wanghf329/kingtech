package com.kingtech.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 展期还款计划
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
public class RepayExtendPlanModel extends BaseRequestModel{
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 展期次数
	@JSONField(name="extend_count",label="sign")
	private String extendCount;

	// 展期期限
	@JSONField(name="extend_term",label="sign")
	private String extendTerm;

	// 展期后的新还款日期
	@JSONField(name="repay_date",label="sign")
	private String repayDate;

	// 展期后的新还款本金（元）
	@JSONField(name="principal",label="sign")
	private String principal;

	// 已还本金（元），有实际还款需填充数据
	@JSONField(name="return_principal")
	private String returnPrincipal;
	
	// 展期后的新还款利息（元）
	@JSONField(name="interest",label="sign")
	private String interest;
		
	// 已还利息（元）
	@JSONField(name="return_interest")
	private String returnInterest;
	
	// 状态，值为未还、已还
	@JSONField(name="status",label="sign")
	private String status;
	
	// 是否逾期
	@JSONField(name="overdue_flag")
	private String overdueFlag;
	
	// 逾期天数
	@JSONField(name="overdue_days")
	private String overdueDays;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public RepayExtendPlanModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId, String extendCount,
			String extendTerm, String repayDate, String principal,
			String returnPrincipal, String interest,
			String returnInterest, String status,
			String overdueFlag, String overdueDays, String creatTime,
			String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign, null);
		this.loanContractId = loanContractId;
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
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	
	public RepayExtendPlanModel(String id, String loanContractId, String extendCount,
			String extendTerm, String repayDate, String principal,
			String returnPrincipal, String interest,
			String returnInterest, String status,
			String overdueFlag, String overdueDays) {
		super(id);
		this.loanContractId = loanContractId;
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
