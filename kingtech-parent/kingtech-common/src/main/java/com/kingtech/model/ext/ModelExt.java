package com.kingtech.model.ext;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.PushStatus;
import com.kingtech.model.BaseRequestModel;

@Data
@NoArgsConstructor
public class ModelExt {
	
	private BaseRequestModel model;
	
	private String loanContractNo;
	
	private String loanContractName;
	
	private PushStatus pushStatus;

	public ModelExt(BaseRequestModel model, String loanContractNo,
			String loanContractName, PushStatus pushStatus) {
		super();
		this.model = model;
		this.loanContractNo = loanContractNo;
		this.loanContractName = loanContractName;
		this.pushStatus = pushStatus;
	}
	
	
	
	

}
