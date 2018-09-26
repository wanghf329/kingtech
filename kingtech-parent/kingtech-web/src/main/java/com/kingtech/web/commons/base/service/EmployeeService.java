package com.kingtech.web.commons.base.service;

import java.util.Date;
import java.util.List;

import com.kingtech.dao.entity.Employee;
import com.kingtech.enums.CertType;
import com.kingtech.enums.EducationEnum;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.model.EmployeeModel;






public interface EmployeeService {
	
	
	public Employee addNew(String id, String name, String phone, String email, String address, String department,
					  SexEnum sex, CertType cardType, String cardNumber, EducationEnum education,
					  YesNoEnum executiveFlag, String position, Date entryTime, Date quitTime);
	
	public List<Employee> listAll();
	
	
	public EmployeeModel getById(String id);
	
	public void delById(String id);
}
