package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.RepayPlan;

public interface RepayPlanDAO extends PagingAndSortingRepository<RepayPlan, String> {
	
}
