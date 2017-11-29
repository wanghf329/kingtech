package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.Contract;

public interface ContractDAO extends PagingAndSortingRepository<Contract, String> {
	
}
