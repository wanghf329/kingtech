package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.Guarantee;

public interface GuaranteeDAO extends PagingAndSortingRepository<Guarantee, String> {
	
}
