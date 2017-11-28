package com.kingtech.web.commons.base.service.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.enums.EmployeeStatus;
import com.kingtech.enums.PushStatus;
import com.kingtech.web.commons.base.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDAO employeeDao;

	@Override
	@Transactional
	public void addNew(String name, String loginName, String phone, String email, String postalAddress,
			String department, String sex, String idNumber, String education,
			String executiveFlag, String post, String replyTime, String entryTime,
			String status, String quitTime, String branchId) {
		try {
			employeeDao.save(new Employee(name,loginName,phone,email,
					postalAddress,department,sex,idNumber,education,
					Integer.valueOf(executiveFlag),post,
					DateUtils.parseDate(replyTime, "yyyy-MM-dd"),
					DateUtils.parseDate(entryTime, "yyyy-MM-dd"),
					EmployeeStatus.valueOf(status),
					DateUtils.parseDate(quitTime, "yyyy-MM-dd"),
					branchId,
					"11100011",PushStatus.INPROSESS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
