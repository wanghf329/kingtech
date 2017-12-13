package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 展期还款信息
 * @author baijt
 *
 */
public class RepayExtendInfoModel extends BaseRequestModel{
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 展期次数对应展期计划中的展期次数（第几次展期）
	@JSONField(name="EXTEND_NUM")
	private long extendNum;

	// 还款日期
	@JSONField(name="REPAY_DATE")
	private Date repayDate;

	// 还款金额
	@JSONField(name="REPAY_AMOUNT")
	private BigDecimal repayAmount;

	// 还款本金
	@JSONField(name="REPAY_PRINCIPAL_AMOUNT")
	private BigDecimal repayPrincipalAmount;
	
	// 还款利息
	@JSONField(name="REPAY_INTEREST_AMOUNT")
	private BigDecimal repayInterestAmount;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public RepayExtendInfoModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId, long extendNum,
			Date repayDate, BigDecimal repayAmount,
			BigDecimal repayPrincipalAmount, BigDecimal repayInterestAmount,
			String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign, null);
		this.loanContractId = loanContractId;
		this.extendNum = extendNum;
		this.repayDate = repayDate;
		this.repayAmount = repayAmount;
		this.repayPrincipalAmount = repayPrincipalAmount;
		this.repayInterestAmount = repayInterestAmount;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	
}
