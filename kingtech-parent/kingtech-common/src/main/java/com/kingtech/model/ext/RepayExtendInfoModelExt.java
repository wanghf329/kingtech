package com.kingtech.model.ext;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.PushStatus;
import com.kingtech.model.RepayExtendInfoModel;

/**
 * 展期还款信息
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
public class RepayExtendInfoModelExt extends RepayExtendInfoModel{
	
	private String loanContractNo;
	
	private String loanContractName;
	
	private PushStatus pushStatus;

	public RepayExtendInfoModelExt(String id, String loanContractId,
			String extendNum, String repayDate, String repayAmount,
			String repayPrincipalAmount, String repayInterestAmount,
			String loanContractNo,String loanContractName,PushStatus pushStatus) {
		super(id,loanContractId,extendNum,repayDate,repayAmount,repayPrincipalAmount,repayInterestAmount);
		this.loanContractNo = loanContractNo;
		this.loanContractName = loanContractName;
		this.pushStatus = pushStatus;
	}
}
