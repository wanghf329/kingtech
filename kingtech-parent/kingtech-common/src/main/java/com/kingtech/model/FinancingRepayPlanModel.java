package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.enums.PushStatus;


/**
 * @author Tson
 * 单笔融资还款计划
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancingRepayPlanModel {
	
	private String Id;
	
	// 融资编号
	private String financeNumber;
	
	// 还款利息
	private BigDecimal interest;
	
	// 还款截止日期
	private Date endDate;
	
	// 还款本金（元）
	private BigDecimal money;

}
