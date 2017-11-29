package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.SettledInfo;

public interface SettledInfoDAO extends PagingAndSortingRepository<SettledInfo, String> {
	
}
