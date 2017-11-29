package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.Employee;
import com.kingtech.model.EmployeeModel;






public interface EmployeeService {
	
	
	public Employee addNew(String id, String name, String phone, String email, String postalAddress,
			String department, String sex, String idNumber, String education,
			String executiveFlag, String post, String replyTime, String entryTime,
			String status, String quitTime, String branchId);
	
	public List<Employee> listAll();
	
	
	public EmployeeModel getById(String id);
	
	public void delById(String id);
}
