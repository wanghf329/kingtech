package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.AccountStatusEmun;
import com.kingtech.enums.AccountTypeEnum;

/**
 * @author Tson
 * 机构银行账户信息
 */
@Data
@Entity
@Table(name="TB_BRANCH_ACCOUNT_INFO")
@NoArgsConstructor
public class BranchAccountInfo extends RecordEntity{
	
	// 银行名称（全称）
	@Column(name="BANK", nullable = false)
	private String bank;
	
	// 银行账号
	@Column(name="ACCOUNT", nullable = false)
	private String account;
	
	// 账户类型：1）.基本户，2）.一般户，3）.监管专用户
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE", nullable = false)
	private AccountTypeEnum type;
	
	// 账户状态
	@Enumerated(EnumType.STRING)
	@Column(name="ACCOUNT_STATUS", nullable = false)
	private AccountStatusEmun accountStatus;
	
	// 开户时间
	@Column(name="OPEN_TIME", nullable = false)
	private Date openTime;

	public BranchAccountInfo(String bank, String account, AccountTypeEnum type,
			AccountStatusEmun accountStatus, Date openTime) {
		super();
		this.bank = bank;
		this.account = account;
		this.type = type;
		this.accountStatus = accountStatus;
		this.openTime = openTime;
	}
	
	
}
