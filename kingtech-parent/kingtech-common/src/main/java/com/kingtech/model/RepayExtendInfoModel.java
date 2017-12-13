package com.kingtech.model;

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
	@JSONField(name="extend_num",label="sign")
	private String extendNum;

	// 还款日期
	@JSONField(name="repay_date",label="sign")
	private String repayDate;

	// 还款金额
	@JSONField(name="money",label="sign")
	private String repayAmount;

	// 还款本金
	@JSONField(name="principal",label="sign")
	private String repayPrincipalAmount;
	
	// 还款利息
	@JSONField(name="interest",label="sign")
	private String repayInterestAmount;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public RepayExtendInfoModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId, String extendNum,
			String repayDate, String repayAmount,
			String repayPrincipalAmount, String repayInterestAmount,
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
