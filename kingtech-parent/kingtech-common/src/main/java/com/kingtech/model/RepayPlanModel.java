package com.kingtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepayPlanModel {

	// 还款日期，格式YYYY-MM-DD
	@JSONField(name="repay_date")
	private String repayDate;

	// 还款本金（元）
	@JSONField(name="principal")
	private String principal;

	// 还款利息（元）
	@JSONField(name="interest")
	private String interest;




}
