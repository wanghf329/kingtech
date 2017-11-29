package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.PersonalCustomer;

public interface PersonalCustomerDao extends PagingAndSortingRepository<PersonalCustomer, String> {

}
