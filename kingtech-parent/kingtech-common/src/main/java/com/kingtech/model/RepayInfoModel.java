package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 还款信息
 * @author baijt
 *
 */
public class RepayInfoModel   extends BaseRequestModel{
	
	
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 还款金额
	@JSONField(name="money",label="sign")
	private String repayAmount;
	
	//还款本金
	@JSONField(name="principal",label="sign")
	private String repayPrincipalAmount;

	// 还款利息
	@JSONField(name="interest",label="sign")
	private BigDecimal repayInterestAmount;

	// 还款利息
	@JSONField(name="repay_date",label="sign")
	private String  repayDate;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public RepayInfoModel( String roundStr, String identifier, String reqId, String sign,
			String id, String loanContractId, String repayAmount,
			String repayPrincipalAmount, BigDecimal repayInterestAmount,
			String repayDate, String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign,null);
		this.loanContractId = loanContractId;
		this.repayAmount = repayAmount;
		this.repayPrincipalAmount = repayPrincipalAmount;
		this.repayInterestAmount = repayInterestAmount;
		this.repayDate = repayDate;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	
	
	
	
	
	
	
}
