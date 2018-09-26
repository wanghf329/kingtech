package com.kingtech.web.commons.base.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.kingtech.common.utils.HttpUtil;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.enums.CertType;
import com.kingtech.enums.EducationEnum;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.model.AsyReponseModel;
import com.kingtech.model.EmployeeModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.EmployeeService;
import com.kingtech.web.commons.base.utils.DTOUtils;

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
	public Employee addNew(String id, String name, String phone, String email, String address, String department,
			SexEnum sex, CertType cardType, String cardNumber, EducationEnum education,
			YesNoEnum executiveFlag, String position, Date entryTime, Date quitTime) {
		try {
			Employee employee = null;
			if (StringUtils.isEmpty(id)) {
				employee = new Employee(name, phone, email, address, department, sex, cardType, cardNumber,
						education, executiveFlag, position, entryTime, quitTime);
			} else {
				employee = employeeDao.findOne(id);
				employee.setName(name);
				employee.setPhone(phone);
				employee.setEmail(email);
				employee.setAddress(address);
				employee.setDepartment(department);
				employee.setSex(sex);
				employee.setCardType(cardType);
				employee.setCardNumber(cardNumber);
				employee.setEducation(education);
				employee.setExecutiveFlag(executiveFlag);
				employee.setPosition(position);
				employee.setEntryTime(entryTime);
				employee.setQuitTime(quitTime);
				employee.setUpdateTime(new Date());
				employee.setPushStatus(PushStatus.INITATION);
			}
			employee.setId(id);	
			
			employee = employeeDao.save(employee);
			
			paymentApi.employeeInfoApi(employee.getId(), StringUtils.isEmpty(id) ?  IdentifierType.A : IdentifierType.U);
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
	public EmployeeModel getById(String id) {
		Employee employee =  employeeDao.findOne(id);
		return new EmployeeModel(employee.getId(), 
								 employee.getName(),
								 employee.getPhone(),
								 employee.getEmail(),
								 employee.getAddress(),
								 employee.getDepartment(),
								 DTOUtils.getEnumIntVal(employee.getSex()),
								 DTOUtils.getEnumIntVal(employee.getCardType()),
								 employee.getCardNumber(),
								 DTOUtils.getEnumIntVal(employee.getEducation()),
								 DTOUtils.getEnumIntVal(employee.getExecutiveFlag()),
								 employee.getPosition(),
								 employee.getEntryTime(),
								 employee.getQuitTime());
				
	}

	@Override
	public void delById(String id) {
		employeeDao.delete(id);
	}
	
	public static void main(String[] args) throws IOException {
		AsyReponseModel asyReponseModel = new AsyReponseModel("135465", "132465",  "132465",  "132465",  "132465",  "132465",  "132465",  "132465", "A");
		Map<String, String> aMap = JSON.parseObject(JSON.toJSONString(asyReponseModel), Map.class);
		System.err.println(aMap);
		HttpUtil.postJsonResponse("http://localhost:8084/bus/refresh", "");
		
	}

}
