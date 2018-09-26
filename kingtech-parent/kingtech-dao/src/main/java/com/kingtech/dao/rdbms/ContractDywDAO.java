package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.ContractDyw;

public interface ContractDywDAO extends PagingAndSortingRepository<ContractDyw, String> {
	
	
	@Query("select c from ContractDyw c where c.loanContractId = :loanContractId")
	public List<ContractDyw> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Modifying
	@Query("delete ContractDyw c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
}
