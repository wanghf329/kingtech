package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;

/**
 * 计提信息表
 * 
 * @version: v1.0
 */
@Data
@Entity
@Table(name = "TB_PROVISION_INFO")
@NoArgsConstructor
public class ProvisionInfo extends RecordEntity {

	// 计提月份 YYYYMM
	@Column(name = "DATE_MONTH")
	private Date dateMonth;

	// 正常类贷款余额（万元）
	@Column(name = "NORMAL_BALANCE")
	private BigDecimal normalBalance;

	// 正常类计提比例（%）
	@Column(name = "NORMAL_RATE")
	private BigDecimal normalRate;

	// 正常类计提金额（万元）
	@Column(name = "NORMAL_REAL")
	private BigDecimal normalReal;

	// 关注类贷款余额（万元）
	@Column(name = "FOLLOW_BALANCE")
	private BigDecimal followBalance;

	// 关注类计提比例（%）
	@Column(name = "FOLLOW_RATE")
	private BigDecimal followRate;

	// 关注类计提金额（万元）
	@Column(name = "FOLLOW_REAL")
	private BigDecimal followReal;

	// 次级类贷款余额（万元）
	@Column(name = "MINOR_BALANCE")
	private BigDecimal minorBalance;

	// 次级类计提比例（%）
	@Column(name = "MINOR_RATE")
	private BigDecimal minorRate;

	// 次级类计提金额（万元）
	@Column(name = "MINOR_REAL")
	private BigDecimal minorReal;

	// 可疑类贷款余额（万元）
	@Column(name = "SUSPICIOUS_BALANCE")
	private BigDecimal suspiciousBalance;

	// 可疑类计提比例（%）
	@Column(name = "SUSPICIOUS_RATE")
	private BigDecimal suspiciousRate;

	// 可疑类计提金额（万元）
	@Column(name = "SUSPICIOUS_REAL")
	private BigDecimal suspiciousReal;

	// 损失类贷款余额（万元）
	@Column(name = "LOSS_BALANCE")
	private BigDecimal lossBalance;

	// 损失类计提比例（%）
	@Column(name = "LOSS_RATE")
	private BigDecimal lossRate;

	// 损失类计提金额（万元）
	@Column(name = "LOSS_REAL")
	private BigDecimal lossReal;

	public ProvisionInfo(String reqId, PushStatus pushStatus,RecordStatus recordStatus, Date dateMonth,
			BigDecimal normalBalance, BigDecimal normalRate,
			BigDecimal normalReal, BigDecimal followBalance,
			BigDecimal followRate, BigDecimal followReal,
			BigDecimal minorBalance, BigDecimal minorRate,
			BigDecimal minorReal, BigDecimal suspiciousBalance,
			BigDecimal suspiciousRate, BigDecimal suspiciousReal,
			BigDecimal lossBalance, BigDecimal lossRate, BigDecimal lossReal) {
		super(reqId, pushStatus,recordStatus);
		this.dateMonth = dateMonth;
		this.normalBalance = normalBalance;
		this.normalRate = normalRate;
		this.normalReal = normalReal;
		this.followBalance = followBalance;
		this.followRate = followRate;
		this.followReal = followReal;
		this.minorBalance = minorBalance;
		this.minorRate = minorRate;
		this.minorReal = minorReal;
		this.suspiciousBalance = suspiciousBalance;
		this.suspiciousRate = suspiciousRate;
		this.suspiciousReal = suspiciousReal;
		this.lossBalance = lossBalance;
		this.lossRate = lossRate;
		this.lossReal = lossReal;
	}
	
	

}
