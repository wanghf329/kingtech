package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.PersonalCustomer;

public interface PersonalCustomerDAO extends PagingAndSortingRepository<PersonalCustomer, String> {
	
}
