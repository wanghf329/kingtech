package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Collateral;

public interface CollateralDAO extends PagingAndSortingRepository<Collateral, String> {
	
	
	@Query("select c from Collateral c where c.loanContractId = :loanContractId")
	public List<Collateral> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Modifying
	@Query("delete Collateral c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
}
