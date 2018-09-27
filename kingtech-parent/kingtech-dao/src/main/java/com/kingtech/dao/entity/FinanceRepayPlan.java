package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.UuidEntity;

/**
 * @author Tson
 * 单笔融资还款计划信息
 */
@Data
@Entity
@Table(name="TB_FINANCING_REPAY_PLAN")
@NoArgsConstructor
@AllArgsConstructor
public class FinanceRepayPlan extends UuidEntity{
	
	// 融资编号
	@Column(name="FINANCE_ID", nullable = false)
	private String financeId;
	
	// 还款利息
	@Column(name="INTEREST", nullable = false)
	private BigDecimal interest;
	
	// 还款截止日期
	@Column(name="REPAY_DATE", nullable = false)
	private Date endDate;
	
	// 还款本金（元）
	@Column(name="MONEY", nullable = false)
	private BigDecimal money;
	
	@Column(name="ORDER_NUM", nullable = false)
	private int orderNum;

}
