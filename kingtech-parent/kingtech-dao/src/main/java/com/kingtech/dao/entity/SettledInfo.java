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
@Table(name="TB_LOAN_CONTRACT_SETTLED")
@NoArgsConstructor
public class SettledInfo extends PackageEntity {
	
	// 放款金额(元)
	@Column(name="MONEY")
	private BigDecimal money;

	// 放款日期，格式YYYY-MM-DD
	@Column(name="LOAN_TIME")
	private Date loanTime;

	// 本笔放款债项开始日
	@Column(name="START_DATE")
	private Date StartDate;

	// 本笔放款债项结束日
	@Column(name="END_DATE")
	private Date endDate;
}
