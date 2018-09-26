package com.kingtech.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 逾期信息
 * @author baijt
 *
 */
@Data
public class OtherOverdueInfoModel extends BaseRequestModel{
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 逾期金额(元)
	@JSONField(name="overdue_money",label="sign")
	private String overdueMoney;

	// 逾期日期
	@JSONField(name="overdue_date",label="sign")
	private String overdueDate;

	// 逾期利息
	@JSONField(name="overdue_interest",label="sign")
	private String overdueInterest;

	// 贷款余额
	@JSONField(name="balance",label="sign")
	private String balance;

	// 备注
	@JSONField(name="remarks")
	private String remarks;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public OtherOverdueInfoModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId,
			String overdueMoney, String overdueDate,
			String overdueInterest, String balance, String remarks,
			String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, sign, null);
		this.loanContractId = loanContractId;
		this.overdueMoney = overdueMoney;
		this.overdueDate = overdueDate;
		this.overdueInterest = overdueInterest;
		this.balance = balance;
		this.remarks = remarks;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}

	public OtherOverdueInfoModel(String id, String loanContractId,
			String overdueMoney, String overdueDate, String overdueInterest,
			String balance, String remarks) {
		super(id);
		this.loanContractId = loanContractId;
		this.overdueMoney = overdueMoney;
		this.overdueDate = overdueDate;
		this.overdueInterest = overdueInterest;
		this.balance = balance;
		this.remarks = remarks;
	}
	
	
	
}
