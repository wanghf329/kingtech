package com.kingtech.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 坏账信息
 * 
 * @author baijt
 *
 */
@Data
public class AssetTransferModel extends BaseRequestModel {

	@JSONField(name = "loan_contract_id", label = "sign")
	private String loanContractId;

	// 转让编号
	@JSONField(name = "transferNumber")
	private String transferNumber;

	// 转让金（元）
	@JSONField(name = "transferMoney")
	private String transferMoney;

	// 原资产金额（元）
	@JSONField(name = "originalMoney")
	private String originalMoney;

	// 溢价\折价金（元）
	@JSONField(name = "discountMoney")
	private String discountMoney;

	// 受让单位
	@JSONField(name = "acceptUnit")
	private String acceptUnit;

	// 转让协议编号
	@JSONField(name = "protocol")
	private String protocol;

	// 转让日期，格式YYYY-MM-DD
	@JSONField(name = "transferDate")
	private String transferDate;

	@JSONField(name = "create_time")
	private String creatTime;

	@JSONField(name = "update_time")
	private String updateTime;

	public AssetTransferModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId, String transferNumber, String transferMoney, String originalMoney,
			String discountMoney, String acceptUnit, String protocol,
			String transferDate, String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils
				.getToken(roundStr), reqId, sign, null);
		this.loanContractId = loanContractId;
		this.transferNumber = transferNumber;
		this.transferMoney = transferMoney;
		this.originalMoney = originalMoney;
		this.discountMoney = discountMoney;
		this.acceptUnit = acceptUnit;
		this.protocol = protocol;
		this.transferDate = transferDate;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}

	public AssetTransferModel(String id, String transferNumber, String transferMoney, String originalMoney,
			String discountMoney, String acceptUnit, String protocol, String transferDate) {
		super(id);
		this.transferNumber = transferNumber;
		this.transferMoney = transferMoney;
		this.originalMoney = originalMoney;
		this.discountMoney = discountMoney;
		this.acceptUnit = acceptUnit;
		this.protocol = protocol;
		this.transferDate = transferDate;
	}

}
