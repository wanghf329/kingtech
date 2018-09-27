package com.kingtech.model.ext;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.PushStatus;
import com.kingtech.model.BaseModel;

@Data
@NoArgsConstructor
public class ModelExt {
	
	private BaseModel model;
	
	private String contractNumber;
	
	private String contractName;
	
	private PushStatus pushStatus;

	public ModelExt(BaseModel model, String contractNumber,
			String contractName, PushStatus pushStatus) {
		super();
		this.model = model;
		this.contractNumber = contractNumber;
		this.contractName = contractName;
		this.pushStatus = pushStatus;
	}
	
	
	
	

}
