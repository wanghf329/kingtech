package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.PackageEntity;

/**
 * 放款信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_SETTLED")
@NoArgsConstructor
public class SettledInfo extends PackageEntity {
	
	// 放款金额(元)
	@Column(name="MONEY")
	private BigDecimal money;

	// 放款日期，格式YYYY-MM-DD
	@Column(name="LOAN_DATE")
	private Date loanDate;

	// 本笔放款债项开始日
	@Column(name="DEBT_START_DATE")
	private Date debtStartDate;

	// 本笔放款债项结束日
	@Column(name="DEBT_END_DATE")
	private Date debtEndDate;

	public SettledInfo(String loanContractId, BigDecimal money, Date loanDate,
			Date debtStartDate, Date debtEndDate) {
		super(loanContractId);
		this.money = money;
		this.loanDate = loanDate;
		this.debtStartDate = debtStartDate;
		this.debtEndDate = debtEndDate;
	}
	
}
