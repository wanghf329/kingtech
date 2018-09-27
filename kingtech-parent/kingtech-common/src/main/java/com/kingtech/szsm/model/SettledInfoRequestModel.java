package com.kingtech.szsm.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

@AllArgsConstructor
@NoArgsConstructor 
@Data
public class SettledInfoRequestModel extends BaseRequestModel{
	//合同编号
	private String contractNumber;

	// 放款金额(元)
	@JSONField(label ="sign")
	private String money;

	// 放款日期，格式YYYY-MM-DD
	@JSONField(label ="sign")
	private String loanTime;

	// 本笔放款债项开始日
	private String startDate;

	// 本笔放款债项结束日
	
	private String endDate;

	public SettledInfoRequestModel( String roundStr, String reqId,String contractNumber, String money,
			String loanTime, String startDate, String endDate) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.contractNumber = contractNumber;
		this.money = money;
		this.loanTime = loanTime;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public SettledInfoRequestModel( String roundStr, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
	}
	
	
	
	
}
