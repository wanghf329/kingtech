package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;

/**
 * 机构资本信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_CAPITAL")
@NoArgsConstructor
@AllArgsConstructor
public class Capital extends RecordEntity {
	
	// 融资渠道
	@Column(name="FINANCING_CHANNEL")
	private String financingChannel;

	// 融资金额
	@Column(name="FINANCING_MONEY")
	private Double financingMoney;

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
}
