package com.kingtech.szsm.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentFinanceRequestModel extends BaseRequestModel {

	// 融资编号
	@JSONField(name = "financeNumber", label = "sign")
	private String financeNumber;

	// 还款日期 格式yyyy-MM-dd
	@JSONField(name = "repayDate", label = "sign")
	private String repayDate;

	// 还款本金（元）
	@JSONField(name = "money", label = "sign")
	private String money;

	// 还款利息（元）
	@JSONField(name = "interest", label = "sign")
	private String interest;

	// 其它费用（元）
	@JSONField(name = "charges", label = "sign")
	private String charges;

	public RepaymentFinanceRequestModel(String roundStr, String reqId, String financeNumber,
			String repayDate, String money, String interest, String charges) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.financeNumber = financeNumber;
		this.repayDate = repayDate;
		this.money = money;
		this.interest = interest;
		this.charges = charges;
	}


	public RepaymentFinanceRequestModel(String roundStr, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
	}


}
