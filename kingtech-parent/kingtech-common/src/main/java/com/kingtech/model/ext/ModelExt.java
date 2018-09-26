package com.kingtech.model.ext;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.PushStatus;
import com.kingtech.model.BaseModel;

@Data
@NoArgsConstructor
public class ModelExt {
	
	private BaseModel model;
	
	private String loanContractNo;
	
	private String loanContractName;
	
	private PushStatus pushStatus;

	public ModelExt(BaseModel model, String loanContractNo,
			String loanContractName, PushStatus pushStatus) {
		super();
		this.model = model;
		this.loanContractNo = loanContractNo;
		this.loanContractName = loanContractName;
		this.pushStatus = pushStatus;
	}
	
	
	
	

}
