package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;

public interface BorrowerService {
	public List<EnterpriseCustomer> listAllEnterpries();

	public List<PersonalCustomer> listAllPersonal();
}
