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

/**
 * 机构资本信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_CAPITAL")
@NoArgsConstructor
public class Capital extends RecordEntity {
	
	// 融资渠道
	@Column(name="FINANCING_CHANNEL")
	private String financingChannel;

	// 融资金额
	@Column(name="FINANCING_MONEY")
	private BigDecimal financingMoney;

	// 融资时间
	@Column(name="FINANCING_TIME")
	private Date financingTime;

	// 到期时间
	@Column(name="EXPIRATION_TIME")
	private Date expirationTime;

	// 实际还款时间
	@Column(name="REPLY_TIME")
	private Date replyTime;
	
	// 机构编号
	@Column(name="BRANCH_ID")
	private String branchId;

	
	public Capital(String financingChannel, BigDecimal financingMoney,
				   Date financingTime, Date expirationTime, Date replyTime,
				   String branchId,String reqId,PushStatus status) {
		super(reqId,status);
		this.financingChannel = financingChannel;
		this.financingMoney = financingMoney;
		this.financingTime = financingTime;
		this.expirationTime = expirationTime;
		this.replyTime = replyTime;
		this.branchId = branchId;
	}
}
