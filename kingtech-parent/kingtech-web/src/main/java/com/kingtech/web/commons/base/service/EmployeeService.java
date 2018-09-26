package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.Employee;
import com.kingtech.model.EmployeeModel;






public interface EmployeeService {
	
	
	public Employee addNew(EmployeeModel model);
	
	public List<Employee> listAll();
	
	public Employee getById(String id);
}
