package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.LoanRecordEntity;
import com.kingtech.enums.BadTypeEnum;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;

/**
 * 资产转让接口表
 * 
 * @version: v1.0
 */
@Data
@Entity
@Table(name = "TB_LOAN_ASSET_TRANSFER")
@NoArgsConstructor
public class AssetTransfer extends LoanRecordEntity {

	// 转让编号
	@Column(name = "TRANSFER_NUMBER")
	private String transferNumber;

	// 转让金（元）
	@Column(name = "TRANSFER_MONEY")
	private BigDecimal transferMoney;

	// 原资产金额（元）
	@Column(name = "ORIGINAL_MONEY")
	private BigDecimal originalMoney;

	// 溢价\折价金（元）
	@Column(name = "DISCOUNT_MONEY")
	private BigDecimal discountMoney;

	// 受让单位
	@Column(name = "ACCEPT_UNIT")
	private String acceptUnit;

	// 转让协议编号
	@Column(name = "PROTOCOL")
	private String protocol;

	// 转让日期，格式YYYY-MM-DD
	@Column(name = "TRANSFER_DATE")
	private Date transferDate;

	public AssetTransfer(String loanContractId, String reqId,
			PushStatus pushStatus, RecordStatus recordStatus, String transferNumber,
			BigDecimal transferMoney, BigDecimal originalMoney,
			BigDecimal discountMoney, String acceptUnit, String protocol,
			Date transferDate) {
		super(loanContractId, reqId, pushStatus, recordStatus);
		this.transferNumber = transferNumber;
		this.transferMoney = transferMoney;
		this.originalMoney = originalMoney;
		this.discountMoney = discountMoney;
		this.acceptUnit = acceptUnit;
		this.protocol = protocol;
		this.transferDate = transferDate;
	}
	
}
