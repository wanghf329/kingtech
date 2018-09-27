package com.kingtech.dao.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.PushStatus;

/**
 * @author Tson
 * 月度融资余额信息
 */
@Data
@Entity
@Table(name="TB_FINANCE_MONTH_BALANCE")
@NoArgsConstructor
public class FinanceMonthBalance extends RecordEntity{
	
	
	// 融资年月 格式YYYY-MM
	@Column(name="FINANCE_MOTTH", nullable = false)
	private String financeMonth;
	
	// 融资余额（万元）
	@Column(name="BALANCE", nullable = false)
	private BigDecimal balance;
	
	


}
