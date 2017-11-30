package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.SettledInfo;

public interface SettledInfoDAO extends PagingAndSortingRepository<SettledInfo, String> {
	
	@Query("select s from SettledInfo s where s.loanContractId = :loanContractId")
	public SettledInfo getByloanContractId(@Param("loanContractId")String loanContractId);
}
