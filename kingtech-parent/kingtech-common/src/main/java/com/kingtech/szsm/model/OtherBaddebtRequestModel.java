package com.kingtech.szsm.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 坏账信息
 * @author baijt
 *
 */
@Data
public class OtherBaddebtRequestModel extends BaseRequestModel{
	
	@JSONField(label="sign")
	private String contractNumber;
	
	// 坏账金额(元)
	@JSONField(label="sign")
	private String badMoney;

	// 定损日期
	@JSONField(label="sign")
	private String lossDate;
	
	// 坏账类型
	private int badType;

	// 后续工作描述
	private String followUp;

	public OtherBaddebtRequestModel(String roundStr, String reqId,String contractNumber, String badMoney,
			String lossDate, int badType, String followUp) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.contractNumber = contractNumber;
		this.badMoney = badMoney;
		this.lossDate = lossDate;
		this.badType = badType;
		this.followUp = followUp;
	}

	
	public OtherBaddebtRequestModel(String roundStr, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
	}
	

}
