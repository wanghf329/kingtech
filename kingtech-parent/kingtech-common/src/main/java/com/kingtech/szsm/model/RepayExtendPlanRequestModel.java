package com.kingtech.szsm.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.model.RepayExtendPlanModel;

/**
 * 展期还款计划
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepayExtendPlanRequestModel extends BaseRequestModel{
	
	
	@JSONField(label="sign")
	private String contractNumber;
	
	//展期次数
	@JSONField(label="sign")
	private String count;
	
	//展期计划包
	private List<ExtendPlanRequestModel> plans;

	public RepayExtendPlanRequestModel( String roundStr, String reqId,String contractNumber,
			String count, List<ExtendPlanRequestModel> plans) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.contractNumber = contractNumber;
		this.count = count;
		this.plans = plans;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	
}
