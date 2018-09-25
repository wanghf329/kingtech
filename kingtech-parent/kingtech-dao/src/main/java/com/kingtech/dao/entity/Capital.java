package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.ChannelTypeEnum;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RateTypeEnum;

/**
 * 机构资本信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_BRANCH_CAPITAL")
@NoArgsConstructor
public class Capital extends RecordEntity {
	
	// 融资编号
	@Column(name="FINANCE_NUMBER", nullable = false)
	private String financeNumber;
	
	// 融资名称
	@Column(name="FINANCE_NAME", nullable = false)
	private String financeName;
	
	// 出借方名称
	@Column(name="LENDER", nullable = false)
	private String lender;
	
	// 融资渠道
	@Enumerated(EnumType.STRING)
	@Column(name="CHANNEL", nullable = false)
	private ChannelTypeEnum channel;

	// 融资金额（元）
	@Column(name="MONEY", nullable = false)
	private BigDecimal money;
	
	// 应付利息
	@Column(name="INTEREST", nullable = false)
	private BigDecimal interest;
	
	// 手续费
	@Column(name="CHARGE", nullable = false)
	private BigDecimal charge;
	
	// 合同担保金额
	@Column(name="GUARANTEE_MONEY")
	private BigDecimal guaranteeMoney;
	
	// 备注
	@Column(name="REMARK")
	private String remark;

	// 融资日期
	@Column(name="FINANCE_DATE", nullable = false)
	private Date financeDate;
	
	// 到期日期
	@Column(name="END_DATE", nullable = false)
	private Date endDate;
	
	// 融资利率
	@Column(name="RATE", nullable = false)
	private BigDecimal rate;
	
	// 利率类型
	@Enumerated(EnumType.STRING)
	@Column(name="RATE_TYPE", nullable = false)
	private RateTypeEnum rateType;
	
	// 合同编号
	@Column(name="LOAN_CONTRACT_NUMBER")
	private String loanContractNumber;

	public Capital(String reqId, PushStatus pushStatus, String financeNumber,
			String financeName, String lender, ChannelTypeEnum channel,
			BigDecimal money, BigDecimal interest, Date financeDate,
			Date endDate, BigDecimal rate, RateTypeEnum rateType,
			String loanContractNumber) {
		super(reqId, pushStatus);
		this.financeNumber = financeNumber;
		this.financeName = financeName;
		this.lender = lender;
		this.channel = channel;
		this.money = money;
		this.interest = interest;
		this.financeDate = financeDate;
		this.endDate = endDate;
		this.rate = rate;
		this.rateType = rateType;
		this.loanContractNumber = loanContractNumber;
	}

	public Capital(String reqId, PushStatus pushStatus, String financeNumber,
			String financeName, String lender, ChannelTypeEnum channel,
			BigDecimal money, BigDecimal interest, BigDecimal guaranteeMoney,
			String remark, Date financeDate, Date endDate, BigDecimal rate,
			RateTypeEnum rateType, String loanContractNumber) {
		super(reqId, pushStatus);
		this.financeNumber = financeNumber;
		this.financeName = financeName;
		this.lender = lender;
		this.channel = channel;
		this.money = money;
		this.interest = interest;
		this.guaranteeMoney = guaranteeMoney;
		this.remark = remark;
		this.financeDate = financeDate;
		this.endDate = endDate;
		this.rate = rate;
		this.rateType = rateType;
		this.loanContractNumber = loanContractNumber;
	}
	
	

	
}
