package com.kingtech.szsm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * @author Tson
 * 机构银行账户信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchAccountInfoRequest extends BaseRequestModel {
	
	// 银行名称（全称）
	private String bank;
	
	// 银行账号
	private String account;
	
	// 账户类型：1）.基本户，2）.一般户，3）.监管专用户
	private int type;
	
	// 账户状态
	private String accountStatus;
	
	// 开户时间
	private String openTime;

	public BranchAccountInfoRequest(String roundStr, String reqId,  String bank, String account,
			int type, String accountStatus, String openTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.bank = bank;
		this.account = account;
		this.type = type;
		this.accountStatus = accountStatus;
		this.openTime = openTime;
	}
	

	public BranchAccountInfoRequest(String roundStr, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
	}
	

}
