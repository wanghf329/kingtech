package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.LoanClassificationEnum;
import com.kingtech.enums.PushStatus;

/**
 * 计提信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_PROVISION_INFO")
@NoArgsConstructor
public class ProvisionInfo extends RecordEntity {
	
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
	@Enumerated(EnumType.STRING)
	@Column(name="LOAN_CLASSIFICATION")
	private LoanClassificationEnum loanClassification;

	// 贷款余额（万元）
	@Column(name="BALANCE")
	private BigDecimal balance;

	public ProvisionInfo(String reqId,
			PushStatus pushStatus, BigDecimal provisionMoney,
			Date provisionDate, BigDecimal provisionScale,
			LoanClassificationEnum loanClassification, BigDecimal balance) {
		super(reqId, pushStatus);
		this.provisionMoney = provisionMoney;
		this.provisionDate = provisionDate;
		this.provisionScale = provisionScale;
		this.loanClassification = loanClassification;
		this.balance = balance;
	}

}
