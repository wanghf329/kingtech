package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.model.EnterpriseCustomerModel;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.misc.PagedResult;

public interface BorrowerService {
	public List<EnterpriseCustomer> listAllEnterpries();

	public List<PersonalCustomer> listAllPersonal();
	
	public EnterpriseCustomer getEnterprise(String id);
	
	public PersonalCustomer getPersonnel(String id);
	
	public void addEnterprise(EnterpriseCustomerModel enterprise);
	
	public void addPersonnel(PersonalCustomerModel model);
	
	public PagedResult<PersonalCustomer> pageList(Pageable pageAble);
}
