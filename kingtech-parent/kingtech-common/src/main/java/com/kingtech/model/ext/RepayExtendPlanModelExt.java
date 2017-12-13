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

	public RepayExtendPlanModelExt(String id, String loanContractId,
			String extendCount, String extendTerm, String repayDate,
			String principal, String returnPrincipal, String interest,
			String returnInterest, String status, String overdueFlag,
			String overdueDays, String loanContractNo, String loanContractName,
			PushStatus pushStatus) {
		super(id, loanContractId, extendCount,
				extendTerm, repayDate, principal, returnPrincipal, interest,
				returnInterest, status, overdueFlag, overdueDays);
		this.loanContractNo = loanContractNo;
		this.loanContractName = loanContractName;
		this.pushStatus = pushStatus;
	}
	
	
}
