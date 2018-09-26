package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.EmployeeModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;

	@Override
	@Transactional
	public Employee addNew(EmployeeModel model) {
		try {
			Employee employee = null;
			if (StringUtils.isEmpty(model.getId())) {
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(employee, model);
			} else {
				employee = employeeDao.findOne(model.getId());
				String reqId = employee.getReqId();
				BeanUtils.copyProperties(employee, model);
				employee.setId(reqId);
				employee.setPushStatus(PushStatus.INITATION);
			}
			
			employee = employeeDao.save(employee);
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Employee> listAll() {
		return (List<Employee>)employeeDao.findAll();
	}

	@Override
	public Employee getById(String id)  {
		return  employeeDao.findOne(id);
	}

}
