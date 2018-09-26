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
public class RepayExtendInfoModel extends BaseRequestModel{
	//合同编号
	@JSONField(name="contract_number",label="sign")
	private String contractNumber;

	// 还款日期
	@JSONField(name="repay_time",label="sign")
	private String repayTime;

	// 还款本金
	@JSONField(name="money",label="sign")
	private String money;

	// 还款本金
	@JSONField(name="interest",label="sign")
	private String interest;
	
	// 罚息
	@JSONField(name="penalty_interest",label="sign")
	private String penaltyInterest;
	
	// 违约金
	@JSONField(name="penalty",label="sign")
	private String penalty;
	
	// 服务费
	@JSONField(name="service_charge",label="sign")
	private String serviceCharge;
	
	// 其他费用
	@JSONField(name="other_charge",label="sign")
	private String otherCharge;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	public RepayExtendInfoModel(String id, String contractNumber,
			String repayTime, String money, String interest,
			String penaltyInterest, String penalty, String serviceCharge,
			String otherCharge) {
		super(id);
		this.contractNumber = contractNumber;
		this.repayTime = repayTime;
		this.money = money;
		this.interest = interest;
		this.penaltyInterest = penaltyInterest;
		this.penalty = penalty;
		this.serviceCharge = serviceCharge;
		this.otherCharge = otherCharge;
	}

	public RepayExtendInfoModel(String id, String contractNumber,
			String repayTime, String money, String interest,
			String penaltyInterest, String penalty, String serviceCharge,
			String otherCharge, String creatTime, String updateTime) {
		super(id);
		this.contractNumber = contractNumber;
		this.repayTime = repayTime;
		this.money = money;
		this.interest = interest;
		this.penaltyInterest = penaltyInterest;
		this.penalty = penalty;
		this.serviceCharge = serviceCharge;
		this.otherCharge = otherCharge;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	
}
