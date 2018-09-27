package com.kingtech.szsm.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 资产转让
 * 
 * @author baijt
 *
 */
@Data
public class AssetTransferModel extends BaseRequestModel {

	@JSONField( label = "sign")
	private String loanContractNumber;

	// 转让编号
	@JSONField( label = "sign")
	private String transferNumber;

	// 转让金（元）
	@JSONField( label = "sign")
	private String transferMoney;

	// 原资产金额（元）
	private String originalMoney;

	// 溢价\折价金（元）
	private String discountMoney;

	// 受让单位
	@JSONField( label = "sign")
	private String acceptUnit;

	// 转让协议编号
	private String protocol;

	// 转让日期，格式YYYY-MM-DD
	@JSONField( label = "sign")
	private String transferDate;

	public AssetTransferModel(String roundStr, String reqId,String loanContractNumber,
			String transferNumber, String transferMoney, String originalMoney,
			String discountMoney, String acceptUnit, String protocol,
			String transferDate) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.loanContractNumber = loanContractNumber;
		this.transferNumber = transferNumber;
		this.transferMoney = transferMoney;
		this.originalMoney = originalMoney;
		this.discountMoney = discountMoney;
		this.acceptUnit = acceptUnit;
		this.protocol = protocol;
		this.transferDate = transferDate;
	}
	
	
	



}
