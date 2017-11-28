package com.kingtech.web.commons.base.service;

import java.util.Date;

import com.kingtech.enums.EmployeeStatus;





public interface EmployeeService {
	
	
	public void addNew(String name, String loginName, String phone, String email, String postalAddress,
			String department, String sex, String idNumber, String education,
			String executiveFlag, String post, String replyTime, String entryTime,
			String status, String quitTime, String branchId);
}
