package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
import com.kingtech.model.EnterpriseCustomerModel;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.BorrowerService;

@Service
public class BorrowerServiceImpl implements BorrowerService{
	@Autowired
	private EnterpriseCustomerDAO enterpriseDao;
	
	@Autowired
	private PersonalCustomerDAO personalDao;
	
	@Autowired
	private DynamicQuery dq;

	@Override
	public List<EnterpriseCustomer> listAllEnterpries() {
		return (List<EnterpriseCustomer>)enterpriseDao.findAll();
	}

	@Override
	public List<PersonalCustomer> listAllPersonal() {
		return (List<PersonalCustomer>)personalDao.findAll();
	}
	
	@Override
	@Transactional
	public void addEnterprise(EnterpriseCustomerModel model) {
		EnterpriseCustomer enterprise = null;
		try {
			if(StringUtils.isEmpty(model.getId())){
				enterprise = new EnterpriseCustomer();
				BeanUtils.copyProperties(enterprise, model);					
				
			} else {
				enterprise = enterpriseDao.findOne(model.getId());
				BeanUtils.copyProperties(enterprise, model);
				enterprise.setId(model.getId());				
			}
			enterpriseDao.save(enterprise);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPersonnel(PersonalCustomerModel model) {
		try {
			PersonalCustomer personalCustomer = null;
			if(StringUtils.isEmpty(model.getId())){
				personalCustomer =	new PersonalCustomer();
				BeanUtils.copyProperties(personalCustomer, model);
			} else {
				personalCustomer = personalDao.findOne(model.getId());
				BeanUtils.copyProperties(personalCustomer, model);
				personalCustomer.setId(model.getId());
			}
			personalCustomer = personalDao.save(personalCustomer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public EnterpriseCustomer getEnterprise(String id) {
		// TODO Auto-generated method stub
		return enterpriseDao.findOne(id);
	}

	@Override
	public PersonalCustomer getPersonnel(String id) {
		// TODO Auto-generated method stub
		return personalDao.findOne(id);
	}

	@Override
	public PagedResult<PersonalCustomer> pageList(Pageable pageAble) {
		String sql = " SELECT * FROM TB_BORROWER_PERSONAL t ";
		
		String[] params = new String[0];
		List<PersonalCustomer> list = dq.nativeQueryPagingList(PersonalCustomer.class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
//		List<PersonalCustomerModel> result = new ArrayList<PersonalCustomerModel>();
		return new PagedResult(list,count);
	}
}
