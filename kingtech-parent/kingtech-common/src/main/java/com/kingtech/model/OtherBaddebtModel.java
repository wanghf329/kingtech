package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 坏账信息
 * @author baijt
 *
 */
public class OtherBaddebtModel extends BaseRequestModel{
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 坏账金额(元)
	@JSONField(name="BAD_MONEY")
	private BigDecimal badMoney;

	// 定损日期
	@JSONField(name="SET_DATE")
	private Date setDate;

	// 后续工作描述
	@JSONField(name="FOLLOWUP_WORK")
	private String followupWork;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public OtherBaddebtModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId, BigDecimal badMoney,
			Date setDate, String followupWork, String creatTime,
			String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign, null);
		this.loanContractId = loanContractId;
		this.badMoney = badMoney;
		this.setDate = setDate;
		this.followupWork = followupWork;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}

}
