package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
import com.kingtech.web.commons.base.service.BorrowerService;

@Service
public class BorrowerServiceImpl implements BorrowerService{
	@Autowired
	private EnterpriseCustomerDAO enterpriseDao;
	
	@Autowired
	private PersonalCustomerDAO personalDao;

	@Override
	public List<EnterpriseCustomer> listAllEnterpries() {
		return (List<EnterpriseCustomer>)enterpriseDao.findAll();
	}

	@Override
	public List<PersonalCustomer> listAllPersonal() {
		return (List<PersonalCustomer>)personalDao.findAll();
	}
}
