package com.kingtech.szsm.model;

import java.util.List;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 机构资本信息表
 * @author wangyu
 * @version: v1.0
 */
@Data
public class FinanceInfoRequestModel extends BaseRequestModel {
	
	// 融资编号
	@JSONField(name="financeNumber", label="sign")
	private String financeNumber;
	
	// 融资名称
	@JSONField(name="financeName")
	private String financeName;
	
	// 出借方名称
	@JSONField(name="lender", label="sign")
	private String lender;
	
	// 融资渠道
	@JSONField(name="channel", label="sign")
	private int channel;

	// 融资金额（元）
	@JSONField(name="money", label="sign")
	private String money;
	
	// 应付利息
	@JSONField(name="interest", label="sign")
	private String interest;
	
	// 手续费
	@JSONField(name="charge")
	private String charge;
	
	// 合同担保金额
	@JSONField(name="guaranteeMoney")
	private String guaranteeMoney;
	
	// 备注
	@JSONField(name="remark")
	private String remark;

	// 融资日期
	@JSONField(name="financeDate", label="sign")
	private String financeDate;
	
	// 到期日期
	@JSONField(name="endDate", label="sign")
	private String endDate;
	
	// 融资利率
	@JSONField(name="rate")
	private String rate;
	
	// 利率类型
	@JSONField(name="rateType")
	private int rateType;
	
	// 合同编号
	@JSONField(name="loanContractNumber")
	private String loanContractNumber;
	
	private List<FinanceRepayPlanRequest> repayPlans;
	

	public FinanceInfoRequestModel( String roundStr, String reqId,String financeNumber, String financeName,
			String lender, int channel, String money, String interest,
			String charge, String guaranteeMoney, String remark,
			String financeDate, String endDate, String rate, int rateType,
			String loanContractNumber,List<FinanceRepayPlanRequest> repayPlans) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.financeNumber = financeNumber;
		this.financeName = financeName;
		this.lender = lender;
		this.channel = channel;
		this.money = money;
		this.interest = interest;
		this.charge = charge;
		this.guaranteeMoney = guaranteeMoney;
		this.remark = remark;
		this.financeDate = financeDate;
		this.endDate = endDate;
		this.rate = rate;
		this.rateType = rateType;
		this.loanContractNumber = loanContractNumber;
		this.repayPlans = repayPlans;
	}
	
	
}
