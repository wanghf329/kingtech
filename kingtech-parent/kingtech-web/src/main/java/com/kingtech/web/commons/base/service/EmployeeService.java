package com.kingtech.web.commons.base.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.Employee;
import com.kingtech.model.ContractModel;
import com.kingtech.model.EmployeeModel;
import com.kingtech.model.misc.PagedResult;






public interface EmployeeService {
	
	
	public Employee addNew(EmployeeModel model);
	
	public List<Employee> listAll();
	
	public Employee getById(String id);
	
	public PagedResult<Employee> pageList(Pageable pageAble);
	
	public void syncEmployeePushStatus();
}
