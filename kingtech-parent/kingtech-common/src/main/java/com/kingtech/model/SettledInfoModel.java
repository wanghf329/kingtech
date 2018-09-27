package com.kingtech.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.enums.PushStatus;

@Data
@NoArgsConstructor 
public class SettledInfoModel {
	
	private String id;
	
	private String loanContractId;
	//合同编号
	private String contractName;
	//合同编号
	private String contractNumber;

	// 放款金额(元)
	private BigDecimal money;

	// 放款日期，格式YYYY-MM-DD
	private String loanTime;

	// 本笔放款债项开始日
	private String startDate;

	// 本笔放款债项结束日
	private String endDate;
	
	private PushStatus pushStatus;

	public SettledInfoModel(String loanContractId, String contractName,
			String contractNumber, BigDecimal money, String loanTime,
			String startDate, String endDate,PushStatus pushStatus,String id) {
		super();
		this.loanContractId = loanContractId;
		this.contractName = contractName;
		this.contractNumber = contractNumber;
		this.money = money;
		this.loanTime = loanTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pushStatus = pushStatus;
		this.id = id;
	}
}
