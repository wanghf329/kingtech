package com.kingtech.model.ext;

import java.math.BigDecimal;
import java.util.Date;

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

	public RepayExtendPlanModelExt(String id, BigDecimal interest,
			Date endDate, BigDecimal principal, String loanContractNo,
			String loanContractName, PushStatus pushStatus) {
		super(id, interest, endDate, principal);
		this.loanContractNo = loanContractNo;
		this.loanContractName = loanContractName;
		this.pushStatus = pushStatus;
	}

}
