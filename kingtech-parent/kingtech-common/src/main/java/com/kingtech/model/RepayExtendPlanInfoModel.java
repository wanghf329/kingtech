package com.kingtech.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 展期还款计划信息
 * @author wxe
 *
 */
@Data
@NoArgsConstructor
public class RepayExtendPlanInfoModel extends BaseRequestModel{
	
	@JSONField(name="contract_number",label="sign")
	private String contractNumber;
	
	//展期次数
	@JSONField(name="count",label="sign")
	private String count;
	
	//展期计划包
	private List<RepayExtendPlanModel> plans;
	
	@JSONField(name="create_time")
	private String creatTime;
	
	@JSONField(name="update_time")
	private String updateTime;

	
}
