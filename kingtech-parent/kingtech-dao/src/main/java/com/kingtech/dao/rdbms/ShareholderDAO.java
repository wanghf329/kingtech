package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.Shareholder;

public interface ShareholderDAO extends PagingAndSortingRepository<Shareholder, String> {
	
}
