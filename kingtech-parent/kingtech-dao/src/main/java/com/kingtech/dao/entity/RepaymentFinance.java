package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;

/**
 * @author Tson
 * 融资实际还款信息
 */
@Data
@Entity
@Table(name="TB_REPAYMENT_FINANCE")
@NoArgsConstructor
public class RepaymentFinance extends RecordEntity {
	
	// 融资编号
	@Column(name="FINANCE_NUMBER", nullable = false)
	private String financeNumber;
	
	// 还款日期 格式yyyy-MM-dd
	@Column(name="REPAY_DATE", nullable = false)
	private Date repayDate;
	
	// 还款本金（元）
	@Column(name="MONEY", nullable = false)
	private BigDecimal money;
	
	// 还款利息（元）
	@Column(name="INTEREST", nullable = false)
	private BigDecimal interest;
	
	// 其它费用（元）
	@Column(name="CHARGES", nullable = false)
	private BigDecimal charges;

}
