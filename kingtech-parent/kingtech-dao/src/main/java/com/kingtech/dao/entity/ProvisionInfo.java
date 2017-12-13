package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.PackageEntity;
import com.kingtech.enums.LoanClassificationEnum;

/**
 * 计提信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_PROVISION_INFO")
@NoArgsConstructor
public class ProvisionInfo extends PackageEntity {
	
	// 计提金额（万元）
	@Column(name="PROVISION_MONEY")
	private BigDecimal provisionMoney;
	
	// 计提日期
	@Column(name="PROVISION_DATE")
	private Date provisionDate;
	
	// 计提比例
	@Column(name="PROVISION_SCALE")
	private BigDecimal provisionScale;

	// 贷款分类
	@Column(name="LOAN_CLASSIFICATION")
	private LoanClassificationEnum loanClassification;

	// 贷款余额（万元）
	@Column(name="BALANCE")
	private BigDecimal balance;

	public ProvisionInfo(String loanContractId, BigDecimal provisionMoney,
			Date provisionDate, BigDecimal provisionScale,
			LoanClassificationEnum loanClassification, BigDecimal balance) {
		super(loanContractId);
		this.provisionMoney = provisionMoney;
		this.provisionDate = provisionDate;
		this.provisionScale = provisionScale;
		this.loanClassification = loanClassification;
		this.balance = balance;
	}

}
