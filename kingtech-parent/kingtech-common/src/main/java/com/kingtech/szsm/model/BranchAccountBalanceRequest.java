package com.kingtech.szsm.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 机构银行账户月度余额信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchAccountBalanceRequest extends BaseRequestModel {
	
	// 年月，格式YYYYMM
	@JSONField(label="sign")
	private String dateMonth;
	
	// 账户号
	@JSONField(name ="account")
	private String account;
	
	// 账户余额（万元）
	@JSONField(name ="money")
	private String money;

	public BranchAccountBalanceRequest(String roundStr, String reqId, String dateMonth,
			String account, String money) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.dateMonth = dateMonth;
		this.account = account;
		this.money = money;
	}
	
	public BranchAccountBalanceRequest(String roundStr, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
	
	}
}
