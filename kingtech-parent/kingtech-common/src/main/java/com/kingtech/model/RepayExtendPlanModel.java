package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.RepayStatusEnum;
import com.kingtech.enums.YesNoEnum;

/**
 * 展期还款计划
 * @author baijt
 *
 */
public class RepayExtendPlanModel extends BaseRequestModel{
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 展期次数
	@JSONField(name="EXTEND_COUNT")
	private long extendCount;

	// 展期期限
	@JSONField(name="EXTEND_TERM")
	private String extendTerm;

	// 展期后的新还款日期
	@JSONField(name="REPAY_DATE")
	private Date repayDate;

	// 展期后的新还款本金（元）
	@JSONField(name="PRINCIPAL")
	private BigDecimal principal;

	// 已还本金（元），有实际还款需填充数据
	@JSONField(name="RETURN_PRINCIPAL")
	private BigDecimal returnPrincipal;
	
	// 展期后的新还款利息（元）
	@JSONField(name="INTEREST")
	private BigDecimal interest;
		
	// 已还利息（元）
	@JSONField(name="RETURN_INTEREST")
	private BigDecimal returnInterest;
	
	// 状态，值为未还、已还
	@JSONField(name="STATUS")
	private RepayStatusEnum status;
	
	// 是否逾期
	@JSONField(name="OVERDUE_FLAG")
	private YesNoEnum overdueFlag;
	
	// 逾期天数
	@JSONField(name="OVERDUE_DAYS")
	private long overdueDays;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public RepayExtendPlanModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId, long extendCount,
			String extendTerm, Date repayDate, BigDecimal principal,
			BigDecimal returnPrincipal, BigDecimal interest,
			BigDecimal returnInterest, RepayStatusEnum status,
			YesNoEnum overdueFlag, long overdueDays, String creatTime,
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
	
}
