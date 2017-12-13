package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.LoanClassificationEnum;

/**
 * 计提信息
 * @author baijt
 *
 */
public class ProvisionInfoModel extends BaseRequestModel{
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 计提金额（万元）
	@JSONField(name="PROVISION_MONEY")
	private BigDecimal provisionMoney;
	
	// 计提日期
	@JSONField(name="PROVISION_DATE")
	private Date provisionDate;
	
	// 计提比例
	@JSONField(name="PROVISION_SCALE")
	private BigDecimal provisionScale;

	// 贷款分类
	@JSONField(name="LOAN_CLASSIFICATION")
	private LoanClassificationEnum loanClassification;

	// 贷款余额（万元）
	@JSONField(name="BALANCE")
	private BigDecimal balance;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public ProvisionInfoModel(String roundStr, String identifier, String reqId, String sign,
			String loanContractId, BigDecimal provisionMoney,
			Date provisionDate, BigDecimal provisionScale,
			LoanClassificationEnum loanClassification, BigDecimal balance,
			String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign, null);
		this.loanContractId = loanContractId;
		this.provisionMoney = provisionMoney;
		this.provisionDate = provisionDate;
		this.provisionScale = provisionScale;
		this.loanClassification = loanClassification;
		this.balance = balance;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	
}
