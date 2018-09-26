package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.LoanRecordEntity;
import com.kingtech.enums.BadTypeEnum;
import com.kingtech.enums.PushStatus;

/**
 * 坏账信息接口表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_OTHER_BADDEBT")
@NoArgsConstructor
public class OtherBaddebt extends LoanRecordEntity {
	
	// 坏账金额(元)
	@Column(name="BAD_MONEY")
	private BigDecimal badMoney;

	// 定损日期
	@Column(name="LOSS_DATE")
	private Date lossDate;
	
	// 坏账类型
	@Enumerated(EnumType.STRING)
	@Column(name="BAD_TYPE")
	private BadTypeEnum badType;

	// 后续工作描述
	@Column(name="FOLLOW_UP")
	private String followUp;

	public OtherBaddebt(String loanContractId, String reqId,
			PushStatus pushStatus, BigDecimal badMoney, Date lossDate,
			BadTypeEnum badType, String followUp) {
		super(loanContractId, reqId, pushStatus);
		this.badMoney = badMoney;
		this.lossDate = lossDate;
		this.badType = badType;
		this.followUp = followUp;
	}

}
