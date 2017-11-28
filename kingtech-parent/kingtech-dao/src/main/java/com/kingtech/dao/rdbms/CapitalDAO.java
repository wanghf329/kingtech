package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.Capital;

public interface CapitalDAO extends PagingAndSortingRepository<Capital, String> {
	
}
