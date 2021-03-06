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
	
	private String loanContractNumber;
	
	private String loanContractName;
	
	private PushStatus pushStatus;

	public RepayExtendInfoModelExt(String id, String contractNumber,
			String repayTime, String money, String interest,
			String penaltyInterest, String penalty, String serviceCharge,
			String otherCharge, String loanContractNumber, String loanContractName,
			PushStatus pushStatus) {
		super(id, contractNumber, repayTime, money, interest, penaltyInterest,
				penalty, serviceCharge, otherCharge);
		this.loanContractNumber = loanContractNumber;
		this.loanContractName = loanContractName;
		this.pushStatus = pushStatus;
	}
	
	

}
