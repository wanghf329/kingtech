package com.kingtech.szsm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * @author Tson
 * 单笔融资还款计划
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancingRepayPlanModel {
	
	// 融资编号ID
	private String financeId;
	
	// 还款利息
	@JSONField(name="interest")
	private String interest;
	
	// 还款截止日期
	@JSONField(name="endDate")
	private String endDate;
	
	// 还款本金（元）
	@JSONField(name="money")
	private String money;

}
