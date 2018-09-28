package com.kingtech.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.PushStatus;

/**
 * 坏账信息
 * @author baijt
 *
 */
@Data
public class OtherBaddebtModel extends BaseModel{
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 坏账金额(元)
	@JSONField(name="badMoney",label="sign")
	private String badMoney;

	// 定损日期
	@JSONField(name="lossDate",label="sign")
	private String lossDate;
	
	// 坏账类型
	@JSONField(name="badType")
	private String badType;

	// 后续工作描述
	@JSONField(name="followUp")
	private String followUp;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;
	
	private String pushStatus;

	public OtherBaddebtModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId, String badMoney,
			String lossDate, String badType, String followUp,
			String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, sign, null);
		this.loanContractId = loanContractId;
		this.badMoney = badMoney;
		this.lossDate = lossDate;
		this.badType = badType;
		this.followUp = followUp;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}

	public OtherBaddebtModel(String id, String loanContractId, String badMoney,
			String lossDate, String badType, String followUp, String pushStatus) {
		super(id);
		this.loanContractId = loanContractId;
		this.badMoney = badMoney;
		this.lossDate = lossDate;
		this.badType = badType;
		this.followUp = followUp;
		this.pushStatus = pushStatus;
	}
	
	

}
