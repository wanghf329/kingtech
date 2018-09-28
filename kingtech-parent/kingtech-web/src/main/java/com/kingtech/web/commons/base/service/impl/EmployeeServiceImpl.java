package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.EmployeeModel;
import com.kingtech.model.misc.PagedResult;
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
	
	@Autowired
	private DynamicQuery dq;
	
	private static final String LISTEMPLOYEE = "SELECT * from TB_BRANCH_EMPLOYEE t  order by t.CREATE_TIME DESC";

	@Override
	@Transactional
	public Employee addNew(EmployeeModel model) {
		try {
			Employee employee = null;
			IdentifierType type = null;
			if (StringUtils.isEmpty(model.getId())) {
				employee = new Employee();
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(employee, model);
				type = IdentifierType.A;
				
			} else {
				employee = employeeDao.findOne(model.getId());
				String reqId = employee.getReqId();
				BeanUtils.copyProperties(employee, model);
				employee.setReqId(reqId);
				employee.setPushStatus(PushStatus.INITATION);
				type = IdentifierType.U;
			}
			employee.setRecordStatus(RecordStatus.NORMAL);
			employee = employeeDao.save(employee);
			paymentApi.employeeInfoApi(employee.getId(), type);
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

	@Override
	public PagedResult<Employee> pageList(Pageable pageAble) {
		String[] params = new String[0];
		List<Employee> list = dq.nativeQueryPagingList(Employee.class, pageAble, LISTEMPLOYEE, params);
		Long count = dq.nativeQueryCount(LISTEMPLOYEE, params);
		return new PagedResult(list,count);
	}

	@Override
	public void syncEmployeePushStatus() {
		employeeDao.listBypushStatus(Lists.newArrayList(PushStatus.INPROSESS)).forEach(s->{
			paymentApi.queryTranInfoApi(s.getId(), Cmd.pushCompanyEmployeeData, s.getReqId(),s.getPushStatus());
		});
		
	}

}
