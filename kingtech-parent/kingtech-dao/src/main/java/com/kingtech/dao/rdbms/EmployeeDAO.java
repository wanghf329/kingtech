package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.Employee;

public interface EmployeeDAO extends PagingAndSortingRepository<Employee, String> {
	
}
