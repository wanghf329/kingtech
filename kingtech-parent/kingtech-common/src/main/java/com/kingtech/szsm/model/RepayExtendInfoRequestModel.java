package com.kingtech.szsm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 展期还款信息
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
public class RepayExtendInfoRequestModel extends BaseRequestModel{
	//合同编号
	@JSONField(label="sign")
	private String contractNumber;

	// 还款日期
	@JSONField(label="sign")
	private String repayTime;

	// 还款本金
	@JSONField(label="sign")
	private String money;

	// 还款本金
	@JSONField(label="sign")
	private String interest;
	
	// 罚息
	@JSONField(label="sign")
	private String penaltyInterest;
	
	// 违约金
	@JSONField(label="sign")
	private String penalty;
	
	// 服务费
	@JSONField(label="sign")
	private String serviceCharge;
	
	// 其他费用
	@JSONField(label="sign")
	private String otherCharge;

	public RepayExtendInfoRequestModel( String roundStr, String reqId,String contractNumber,
			String repayTime, String money, String interest,
			String penaltyInterest, String penalty, String serviceCharge,
			String otherCharge) {
		this.contractNumber = contractNumber;
		this.repayTime = repayTime;
		this.money = money;
		this.interest = interest;
		this.penaltyInterest = penaltyInterest;
		this.penalty = penalty;
		this.serviceCharge = serviceCharge;
		this.otherCharge = otherCharge;
	}
	
	
	

	
}
