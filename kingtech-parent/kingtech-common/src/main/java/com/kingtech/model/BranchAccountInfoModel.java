package com.kingtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Tson
 * 机构银行账户信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchAccountInfoModel extends BaseRequestModel {
	
	// 银行名称（全称）
	@JSONField(name ="bank")
	private String bank;
	
	// 银行账号
	@JSONField(name ="account")
	private String account;
	
	// 账户类型：1）.基本户，2）.一般户，3）.监管专用户
	@JSONField(name ="type")
	private String type;
	
	// 账户状态
	@JSONField(name ="accountStatus")
	private String accountStatus;
	
	// 开户时间
	@JSONField(name ="openTime")
	private String openTime;

}
