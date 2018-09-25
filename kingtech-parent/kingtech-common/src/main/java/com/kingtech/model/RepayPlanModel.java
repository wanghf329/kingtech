package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;

>>>>>>> 75f1a3fc34ca31dbb628c4fd2925c0db00b56d40
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepayPlanModel {

	// 还款日期，格式YYYY-MM-DD
	private String endDate;

	// 还款本金（元）
	private BigDecimal money;

	// 还款利息（元）
	private BigDecimal interest;

}
