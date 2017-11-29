package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.PushStatus;

/**
 * 股东信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_SHAREHOLDER")
@NoArgsConstructor
public class Shareholder extends RecordEntity {
	
	// 股东类型
	@Column(name="PARTNER_TYPE")
	private String partnerType;

	// 持股人
	@Column(name="HOLDER")
	private String holder;

	// 持股比例（%）
	@Column(name="HOLDING_SCALE")
	private BigDecimal holdingScale;

	// 出资金额（万元）
	@Column(name="CONTRIBUTION_AMOUNT")
	private BigDecimal contributionAmount;

	// 入股时间
	@Column(name="JOIN_TIME")
	private Date joinTime;
	
	// 性别
	@Column(name="GENDER")
	private int gender;
	
	// 离职时间
	@Column(name="QUIT_TIME")
	private Date quitTime;
	
	// 机构编号
	@Column(name="BRANCH_ID")
	private String branchId;

	public Shareholder(String partnerType, String holder, BigDecimal holdingScale, BigDecimal contributionAmount,
			Date joinTime, int gender, Date quitTime, String branchId, String reqId, PushStatus pushStatus) {
		super(reqId, pushStatus);
		this.partnerType = partnerType;
		this.holder = holder;
		this.holdingScale = holdingScale;
		this.contributionAmount = contributionAmount;
		this.joinTime = joinTime;
		this.gender = gender;
		this.quitTime = quitTime;
		this.branchId = branchId;
	}
	
	
}
