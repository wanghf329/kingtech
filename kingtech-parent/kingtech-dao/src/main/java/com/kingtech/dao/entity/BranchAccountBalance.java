package com.kingtech.dao.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.PushStatus;

/**
 * @author Tson
 * 机构银行账户月度余额信息
 */
@Data
@Entity
@Table(name="TB_BRANCH_ACCOUNT_BALANCE")
@NoArgsConstructor
public class BranchAccountBalance extends RecordEntity {
	
	// 年月，格式YYYYMM
	@Column(name="DATE_MONTH", nullable = false)
	private String dateMonth;
	
	// 账户号
	@Column(name="ACCOUNT", nullable = false)
	private String account;
	
	// 账户余额（万元）
	@Column(name="MONEY", nullable = false)
	private BigDecimal money;

	public BranchAccountBalance(String reqId, PushStatus pushStatus,
			String dateMonth, String account, BigDecimal money) {
		super(reqId, pushStatus);
		this.dateMonth = dateMonth;
		this.account = account;
		this.money = money;
	}
	
	

}
