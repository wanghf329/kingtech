package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.Collateral;

public interface CollateralDAO extends PagingAndSortingRepository<Collateral, String> {
	
}
