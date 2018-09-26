package com.kingtech.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.enums.AccountStatusEmun;
import com.kingtech.enums.AccountTypeEnum;
import com.kingtech.enums.PushStatus;

/**
 * @author Tson
 * 机构银行账户信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchAccountInfoModel extends BaseRequestModel {
	
	// 银行名称（全称）
	private String bank;
	
	// 银行账号
	private String account;
	
	// 账户类型：1）.基本户，2）.一般户，3）.监管专用户
	private AccountTypeEnum type;
	
	// 账户状态
	private AccountStatusEmun accountStatus;
	
	// 开户时间
	private Date openTime;
	
	private PushStatus pushStatus;

	public BranchAccountInfoModel(String id, String bank, String account,
			AccountTypeEnum type, AccountStatusEmun accountStatus,
			Date openTime, PushStatus pushStatus, String reqId) {
		super(id, reqId);
		this.bank = bank;
		this.account = account;
		this.type = type;
		this.accountStatus = accountStatus;
		this.openTime = openTime;
		this.pushStatus = pushStatus;
	}
	
	
	
}
