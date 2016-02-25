package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.RechargeRecord;

public interface RechargeRecordDAO extends PagingAndSortingRepository<RechargeRecord, String> {
	
}
