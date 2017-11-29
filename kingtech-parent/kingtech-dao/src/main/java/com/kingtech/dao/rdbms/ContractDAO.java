package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Contract;
import com.kingtech.enums.PushStatus;

public interface ContractDAO extends PagingAndSortingRepository<Contract, String> {
	
}
