package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 逾期信息
 * @author baijt
 *
 */
public class OtherOverdueInfoModel extends BaseRequestModel{
	
	@JSONField(name="loan_contract_id",label="sign")
	private String loanContractId;
	
	// 逾期金额(元)
	@JSONField(name="OVERDUE_MONEY")
	private BigDecimal overdueMoney;

	// 逾期日期
	@JSONField(name="OVERDUE_DATE")
	private Date overdueDate;

	// 逾期利息
	@JSONField(name="OVERDUE_INTEREST")
	private BigDecimal overdueInterest;

	// 贷款余额
	@JSONField(name="BALANCE")
	private BigDecimal balance;

	// 备注
	@JSONField(name="REMARKS")
	private String remarks;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public OtherOverdueInfoModel(String roundStr, String identifier, String reqId,
			String sign, String loanContractId,
			BigDecimal overdueMoney, Date overdueDate,
			BigDecimal overdueInterest, BigDecimal balance, String remarks,
			String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign, null);
		this.loanContractId = loanContractId;
		this.overdueMoney = overdueMoney;
		this.overdueDate = overdueDate;
		this.overdueInterest = overdueInterest;
		this.balance = balance;
		this.remarks = remarks;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	
}
