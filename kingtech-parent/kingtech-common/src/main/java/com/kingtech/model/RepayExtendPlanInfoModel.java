package com.kingtech.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;

/**
 * 展期还款计划信息
 * @author wxe
 *
 */
@Data
@NoArgsConstructor
public class RepayExtendPlanInfoModel extends BaseModel{
	
	private String loanContractId;
	
	//合同编号
	private String contractNumber;
	
	private String contractName;
	
	//展期次数
	private String count;
	
	//展期计划包
	private List<RepayExtendPlanModel> plans;
	
	//推送状态
	private PushStatus pushStatus;
	
	//删除状态
	private RecordStatus recordStatus;
	
	private String creatTime;
	
	private String updateTime;

	public RepayExtendPlanInfoModel(String id, String loanContractId,
			String contractNumber, String count,
			List<RepayExtendPlanModel> plans, PushStatus pushStatus,
			RecordStatus recordStatus, String creatTime, String updateTime) {
		super(id);
		this.loanContractId = loanContractId;
		this.contractNumber = contractNumber;
		this.count = count;
		this.plans = plans;
		this.pushStatus = pushStatus;
		this.recordStatus = recordStatus;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}

	public RepayExtendPlanInfoModel(String id, String loanContractId,
			String contractNumber, String contractName, String count,
			List<RepayExtendPlanModel> plans, PushStatus pushStatus,
			RecordStatus recordStatus) {
		super(id);
		this.loanContractId = loanContractId;
		this.contractNumber = contractNumber;
		this.contractName = contractName;
		this.count = count;
		this.plans = plans;
		this.pushStatus = pushStatus;
		this.recordStatus = recordStatus;
	}
	
	
	
	

	
}
