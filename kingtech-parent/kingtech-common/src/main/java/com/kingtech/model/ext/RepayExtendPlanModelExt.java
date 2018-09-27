package com.kingtech.model.ext;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.PushStatus;
import com.kingtech.model.RepayExtendPlanModel;

/**
 * 展期还款信息
 * @author wangyu
 *
 */
@Data
@NoArgsConstructor
public class RepayExtendPlanModelExt extends RepayExtendPlanModel{
	
	private String loanContractNo;
	
	private String loanContractName;
	
	private PushStatus pushStatus;

	public RepayExtendPlanModelExt(String id, String interest, String endDate,
			String principal, String count, String loanContractNo,
			String loanContractName, PushStatus pushStatus) {
		super(id, interest, endDate, principal, count);
		this.loanContractNo = loanContractNo;
		this.loanContractName = loanContractName;
		this.pushStatus = pushStatus;
	}
	
	

	
}
