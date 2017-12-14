package com.kingtech.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 计提信息
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
public class ProvisionInfoModel extends BaseRequestModel{
	
	// 计提金额（万元）
	@JSONField(name="provision_money",label="sign")
	private String provisionMoney;
	
	// 计提日期
	@JSONField(name="provision_date",label="sign")
	private String provisionDate;
	
	// 计提比例
	@JSONField(name="provision_scale",label="sign")
	private String provisionScale;

	// 贷款分类
	@JSONField(name="loan_classification",label="sign")
	private String loanClassification;

	// 贷款余额（万元）
	@JSONField(name="balance",label="sign")
	private String balance;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;
	

	public ProvisionInfoModel(String id, String provisionMoney,
			String provisionDate, String provisionScale,
			String loanClassification, String balance) {
		super(id);
		this.provisionMoney = provisionMoney;
		this.provisionDate = provisionDate;
		this.provisionScale = provisionScale;
		this.loanClassification = loanClassification;
		this.balance = balance;
	}


	public ProvisionInfoModel(String roundStr, String identifier, String reqId, String sign,
			String provisionMoney,
			String provisionDate, String provisionScale,
			String loanClassification, String balance,
			String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign, null);
		this.provisionMoney = provisionMoney;
		this.provisionDate = provisionDate;
		this.provisionScale = provisionScale;
		this.loanClassification = loanClassification;
		this.balance = balance;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	
}
