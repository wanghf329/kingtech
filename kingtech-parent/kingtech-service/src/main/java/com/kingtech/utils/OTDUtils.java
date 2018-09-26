package com.kingtech.utils;

import org.apache.commons.lang3.StringUtils;

import com.kingtech.dao.entity.Employee;
import com.kingtech.model.EmployeeModel;


public class OTDUtils {
	
	public static Employee transterEmployeeModelToEntity(EmployeeModel model) {
		if(model == null)
			return null;
		Employee entity =  new Employee(model.getName(), 
									 	 model.getPhone(),
									 	 model.getEmail(),
									 	 model.getAddress(),
									 	 model.getDepartment(),
									 	 model.getSex(),
									 	 model.getCardType(),
									 	 model.getCardNumber(),
									 	 model.getEducation(),
									 	 model.getExecutiveFlag(),
									 	 model.getPosition(),
									 	 model.getEntryTime(),
									 	 model.getQuitTime(),
									 	 model.getReqId(),
									 	 model.getPushStatus());
		if (!StringUtils.isBlank(model.getId())) {
			entity.setId(model.getId());
		}
		
		return entity;
	} 
}
