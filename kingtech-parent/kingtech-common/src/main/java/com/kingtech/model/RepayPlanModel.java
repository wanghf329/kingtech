package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepayPlanModel {

	// 还款日期，格式YYYY-MM-DD
	private Date endDate;

	// 还款本金（元）
	private BigDecimal money;

	// 还款利息（元）
	private BigDecimal interest;

}
