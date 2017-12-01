package com.kingtech.web.commons.base.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.kingtech.common.utils.HttpUtil;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.enums.EmployeeStatus;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.AsyReponseModel;
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
	public Employee addNew(String id, String name, String phone, String email, String postalAddress,
			String department, String sex, String idNumber, String education,
			String executiveFlag, String post, String replyTime, String entryTime,
			String status, String quitTime, String branchId) {
		try {
			Employee employee = null;
			if (StringUtils.isEmpty(id)) {
				employee = new Employee(name,phone,phone,email,
						postalAddress,department,sex,idNumber,education,
						Integer.valueOf(executiveFlag),post,
						StringUtils.isEmpty(replyTime) ? null : DateUtils.parseDate(replyTime, "yyyy-MM-dd"),
						DateUtils.parseDate(entryTime, "yyyy-MM-dd"),
						EmployeeStatus.getValue(status),
						StringUtils.isEmpty(quitTime) ? null : DateUtils.parseDate(quitTime, "yyyy-MM-dd"),
						branchId,creatRequstId.getReqId(),PushStatus.INITATION);
			} else {
				employee = employeeDao.findOne(id);
				employee.setName(name);
				employee.setLoginName(phone);
				employee.setPhone(phone);
				employee.setEmail(email);
				employee.setPostalAddress(postalAddress);
				employee.setDepartment(department);
				employee.setSex(sex);
				employee.setIdNumber(idNumber);
				employee.setEducation(education);
				employee.setExecutiveFlag(Integer.valueOf(executiveFlag));
				employee.setPost(post);
				employee.setReplyTime(StringUtils.isEmpty(replyTime) ? null : DateUtils.parseDate(replyTime, "yyyy-MM-dd"));
				employee.setEntryTime(DateUtils.parseDate(entryTime, "yyyy-MM-dd"));
				employee.setStatus(EmployeeStatus.getValue(status));
				employee.setQuitTime(StringUtils.isEmpty(quitTime) ? null : DateUtils.parseDate(quitTime, "yyyy-MM-dd"));
				employee.setUpdateTime(new Date());
				employee.setPushStatus(PushStatus.INITATION);
			}
			employee.setId(id);	
			
			if (StringUtils.isEmpty(post)) {
				employee.setPost("普通员工");
			}
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
		return new EmployeeModel(employee.getId(),employee.getName(),employee.getPhone(),
				employee.getEmail(),employee.getPostalAddress(),employee.getDepartment(),employee.getSex(),
				employee.getIdNumber(),employee.getEducation(),employee.getExecutiveFlag()+"",employee.getPost(),
				employee.getReplyTime() == null ? null : DateFormatUtils.format(employee.getReplyTime(), "yyyy-MM-dd"),
				DateFormatUtils.format(employee.getEntryTime(), "yyyy-MM-dd"),
				employee.getStatus().getKey(),
				employee.getQuitTime() == null ? null : DateFormatUtils.format(employee.getQuitTime(), "yyyy-MM-dd"));
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
