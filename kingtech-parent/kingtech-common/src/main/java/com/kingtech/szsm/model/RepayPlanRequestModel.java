package com.kingtech.szsm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepayPlanRequestModel {

	// 还款日期，格式YYYY-MM-DD
	private String endDate;

	// 还款本金（元）
	private String money;

	// 还款利息（元）
	private String interest;

}
