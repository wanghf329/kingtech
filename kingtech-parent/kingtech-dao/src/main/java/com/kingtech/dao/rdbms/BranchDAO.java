package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.Branch;

public interface BranchDAO extends PagingAndSortingRepository<Branch, String> {
	
}
