package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.SettledInfo;

public interface SettledInfoDAO extends PagingAndSortingRepository<SettledInfo, String> {
	
	@Query("select s from SettledInfo s where s.loanContractId = :loanContractId")
	public List<SettledInfo> listByloanContractId(@Param("loanContractId")String loanContractId);
}
