package com.kingtech.szsm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 展期还款计划
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
public class RepayExtendPlanModel extends BaseRequestModel{
	
	@JSONField(name="contract_number",label="sign")
	private String contractNumber;
	
	// 展期后的新还款利息（元）
	@JSONField(name="interest",label="sign")
	private String interest;
	
	//展期后的新还款日期
	@JSONField(name="end_date",label="sign")
	private String endDate;
	
	// 展期后的新还款本金（元）
	@JSONField(name="principal",label="sign")
	private String principal;
	
	
	//第几次展期
	@JSONField(name="count")
	private String count;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;
	

	public RepayExtendPlanModel(String id, String contractNumber,
			String interest, String endDate, String principal,
			String creatTime, String updateTime) {
		super(id);
		this.contractNumber = contractNumber;
		this.interest = interest;
		this.endDate = endDate;
		this.principal = principal;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}

	public RepayExtendPlanModel(String id, String contractNumber,
			String interest, String endDate, String principal) {
		super(id);
		this.contractNumber = contractNumber;
		this.interest = interest;
		this.endDate = endDate;
		this.principal = principal;
	}

	public RepayExtendPlanModel(String id, String contractNumber,
			String interest, String endDate, String principal, String count) {
		super(id);
		this.contractNumber = contractNumber;
		this.interest = interest;
		this.endDate = endDate;
		this.principal = principal;
		this.count = count;
	}

	
}
