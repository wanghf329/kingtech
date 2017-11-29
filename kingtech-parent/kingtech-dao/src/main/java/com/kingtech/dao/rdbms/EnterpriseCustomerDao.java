package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.EnterpriseCustomer;

public interface EnterpriseCustomerDao extends PagingAndSortingRepository<EnterpriseCustomer, String> {

}
