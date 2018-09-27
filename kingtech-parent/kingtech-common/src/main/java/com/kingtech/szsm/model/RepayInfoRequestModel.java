package com.kingtech.szsm.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 还款信息
 * @author baijt
 *
 */
@Data
public class RepayInfoRequestModel extends BaseRequestModel{
	
	@JSONField(label="sign")
	private String contractNumber;
	
	
	//还款本金
	@JSONField(label="sign")
	private String money;

	// 还款利息
	@JSONField(label="sign")
	private String interest;
	
	
	// 罚息
	@JSONField(label="sign")
	private String penaltyInterest;

	// 违约金
	@JSONField(label="sign")
	private String penalty;
	
	//服务费
	@JSONField(label="sign")
	private String serviceCharge;
	
	
	// 其它费用（不确定到以上5个科目的，均放其它），用户从申请借款开始向小贷支付的金额均反映在还款（展期还款）接口（参与签名）
	@JSONField(label="sign")
	private String otherCharge;


	public RepayInfoRequestModel( String roundStr, String reqId, String contractNumber,
			String money, String interest, String penaltyInterest,
			String penalty, String serviceCharge, String otherCharge) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.contractNumber = contractNumber;
		this.money = money;
		this.interest = interest;
		this.penaltyInterest = penaltyInterest;
		this.penalty = penalty;
		this.serviceCharge = serviceCharge;
		this.otherCharge = otherCharge;
	}
	
	
	

	
	
}
