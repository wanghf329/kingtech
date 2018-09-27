package com.kingtech.model;

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
public class RepayExtendPlanModel extends BaseModel{
	
	// 展期后的新还款利息（元）
	private String interest;
	
	//展期后的新还款日期
	private String endDate;
	
	// 展期后的新还款本金（元）
	private String principal;
	
	//第几次展期
	private String count;


	public RepayExtendPlanModel(String id, String interest, String endDate,
			String principal, String count) {
		super(id);
		this.interest = interest;
		this.endDate = endDate;
		this.principal = principal;
		this.count = count;
	}
	
	
	
}
